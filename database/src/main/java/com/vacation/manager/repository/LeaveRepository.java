package com.vacation.manager.repository;

import com.vacation.manager.model.PaidLeave;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static com.vacation.manager.jooq.tables.PaidLeave.PAID_LEAVE;


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
}
