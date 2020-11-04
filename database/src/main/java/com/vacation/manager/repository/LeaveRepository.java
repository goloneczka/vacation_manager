package com.vacation.manager.repository;

import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.api.WorkerLeaveListApi;
import com.vacation.manager.model.api.WorkerLeaveApi;
import org.jooq.DSLContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.vacation.manager.jooq.tables.Enterprise.ENTERPRISE;
import static com.vacation.manager.jooq.tables.PaidLeave.PAID_LEAVE;
import static com.vacation.manager.jooq.tables.Worker.WORKER;


public class LeaveRepository {

    private final DSLContext dsl;

    public LeaveRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<PaidLeave> createPaidLeave(PaidLeave paidLeave, Long id) {
        try {
            return Optional.ofNullable(dsl.insertInto(PAID_LEAVE)
                    .set(PAID_LEAVE.DAYS, paidLeave.getDays())
                    .set(PAID_LEAVE.DESCRIBE, paidLeave.getDescribe())
                    .set(PAID_LEAVE.EMPLOYEE_ID, (int) (long) id)
                    .set(PAID_LEAVE.END_DATE, paidLeave.getEndDate())
                    .set(PAID_LEAVE.START_DATE, paidLeave.getStartDate())
                    .set(PAID_LEAVE.STATUS, paidLeave.getStatus())
                    .set(PAID_LEAVE.DESCRIBE, paidLeave.getDescribe())
                    .returning()
                    .fetchOne()
                    .into(PaidLeave.class));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    public List<PaidLeave> getPaidLeavesByWorkerId(Long id) {
        return dsl.selectFrom(PAID_LEAVE)
                .where(PAID_LEAVE.EMPLOYEE_ID.eq((int) (long) id))
                .fetchInto(PaidLeave.class);
    }

    public List<WorkerLeaveListApi> getUnresolvedByCompanyName(String enterpriseName) {
        return dsl.select(PAID_LEAVE.ID, PAID_LEAVE.START_DATE, PAID_LEAVE.END_DATE, WORKER.NAME, WORKER.OCCUPATION)
                .from(PAID_LEAVE)
                .join(WORKER)
                .on(WORKER.ID.eq(PAID_LEAVE.EMPLOYEE_ID))
                .where(PAID_LEAVE.EMPLOYEE_ID.in(
                        dsl.select(WORKER.ID)
                                .from(WORKER)
                                .where(WORKER.ENTERPRISE_NAME.eq(enterpriseName))))
                .and(PAID_LEAVE.STATUS.eq("NEW"))
                .limit(10)
                .fetchInto(WorkerLeaveListApi.class);

    }

    public List<WorkerLeaveListApi> getResolvedByCompanyName(String enterpriseName, int page) {
        return dsl.select(PAID_LEAVE.ID, PAID_LEAVE.START_DATE, PAID_LEAVE.END_DATE, WORKER.NAME, WORKER.OCCUPATION, PAID_LEAVE.STATUS)
                .from(PAID_LEAVE)
                .join(WORKER)
                .on(WORKER.ID.eq(PAID_LEAVE.EMPLOYEE_ID))
                .where(PAID_LEAVE.EMPLOYEE_ID.in(
                        dsl.select(WORKER.ID)
                                .from(WORKER)
                                .where(WORKER.ENTERPRISE_NAME.eq(enterpriseName))))
                .andNot(PAID_LEAVE.STATUS.eq("NEW"))
                .limit(10)
                .offset(page * 10)
                .fetchInto(WorkerLeaveListApi.class);
    }

    public Optional<WorkerLeaveApi> getDetailsById(Long leaveId) {
        return dsl.select(PAID_LEAVE.ID, PAID_LEAVE.START_DATE, PAID_LEAVE.END_DATE, WORKER.NAME, WORKER.OCCUPATION,
                PAID_LEAVE.DAYS, PAID_LEAVE.DESCRIBE, PAID_LEAVE.STATUS)
                .from(PAID_LEAVE)
                .join(WORKER)
                .on(WORKER.ID.eq(PAID_LEAVE.EMPLOYEE_ID))
                .where(PAID_LEAVE.ID.eq((int) (long) leaveId))
                .fetchOptionalInto(WorkerLeaveApi.class);
    }

    public Optional<PaidLeave> setStatus(Long id, String status) {
        return dsl.update(PAID_LEAVE)
                .set(PAID_LEAVE.STATUS, status)
                .where(PAID_LEAVE.ID.eq((int) (long) id))
                .returning()
                .fetchOptional()
                .map(record -> record.into(PaidLeave.class));
    }

    public int deleteOutdatedLeavesInCompany(String enterpriseName, LocalDate time) {
        try {
            return dsl.delete(PAID_LEAVE)
                    .where(PAID_LEAVE.END_DATE.lessThan(time).or(PAID_LEAVE.STATUS.eq("REJECTED")))
                    .and(PAID_LEAVE.EMPLOYEE_ID.in(
                            dsl.select(WORKER.ID)
                                    .from(WORKER)
                                    .where(WORKER.ENTERPRISE_NAME.eq(enterpriseName))))
                    .execute();
        } catch (RuntimeException ex) {
            return -1;
        }
    }

        //      ---     SCHEDULER
    public void deleteOutdatedLeavesByScheduler(LocalDate time) {
        dsl.delete(PAID_LEAVE)
                .where(PAID_LEAVE.END_DATE.lessThan(time))
                .execute();
    }
}
