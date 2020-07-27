package com.vacation.manager.repository;

import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.WorkerLeaveApi;
import com.vacation.manager.model.api.WorkerLeaveListApi;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

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

    public List<WorkerLeaveListApi> getActivePaidLeavesByWorkerEnterpriseId(Long enterpriseId) {
        return dsl.select(PAID_LEAVE.ID, PAID_LEAVE.START_DATE, PAID_LEAVE.END_DATE, WORKER.NAME, WORKER.OCCUPATION)
                .from(PAID_LEAVE)
                .join(WORKER)
                .on(WORKER.ID.eq(PAID_LEAVE.EMPLOYEE_ID))
                .where(PAID_LEAVE.EMPLOYEE_ID.in(
                        dsl.select(WORKER.ID)
                                .from(WORKER)
                                .where(WORKER.ENTERPRISE_ID.eq((int) (long) enterpriseId))))
                .and(PAID_LEAVE.STATUS.eq("NEW"))
                .limit(10)
                .fetchInto(WorkerLeaveListApi.class);

    }

    public List<PaidLeave> getHistoryLeavesByWorkerEnterpriseId(Long enterpriseId, int page) {
        return dsl.selectFrom(PAID_LEAVE)
                .where(PAID_LEAVE.EMPLOYEE_ID.in(
                        dsl.select(WORKER.ID)
                                .from(WORKER)
                                .where(WORKER.ENTERPRISE_ID.eq((int) (long) enterpriseId))))
                .andNot(PAID_LEAVE.STATUS.eq("NEW"))
                .limit(10)
                .offset(page * 10)
                .fetchInto(PaidLeave.class);
    }

    public Optional<WorkerLeaveApi> getDetailsById(Long leaveId) {
        return dsl.select(PAID_LEAVE.ID, PAID_LEAVE.START_DATE, PAID_LEAVE.END_DATE, WORKER.NAME, WORKER.OCCUPATION,
                PAID_LEAVE.DAYS, PAID_LEAVE.DESCRIBE, PAID_LEAVE.STATUS)
                .from(PAID_LEAVE)
                .join(WORKER)
                .on(WORKER.ID.eq(PAID_LEAVE.EMPLOYEE_ID))
                .where(PAID_LEAVE.ID.eq((int) (long)leaveId))
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
}
