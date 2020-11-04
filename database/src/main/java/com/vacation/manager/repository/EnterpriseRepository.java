package com.vacation.manager.repository;

import com.vacation.manager.model.Enterprise;
import com.vacation.manager.model.Worker;
import org.jooq.DSLContext;

import static com.vacation.manager.jooq.tables.Enterprise.ENTERPRISE;
import static com.vacation.manager.jooq.tables.Worker.WORKER;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EnterpriseRepository {

    private final DSLContext dsl;

    public EnterpriseRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<Enterprise> createEnterprise(Enterprise enterprise) {
        try {
            return Optional.ofNullable(dsl.insertInto(ENTERPRISE)
                    .set(ENTERPRISE.NAME, enterprise.getName())
                    .returning()
                    .fetchOne()
                    .into(Enterprise.class));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }

    }

    public Optional<Enterprise> confirmEnterprise(String enterpriseName) {
        return dsl
                .update(ENTERPRISE)
                .set(ENTERPRISE.CONFIRMED, true)
                .where(ENTERPRISE.NAME.eq(enterpriseName))
                .returning()
                .fetchOptional()
                .map(record -> record.into(Enterprise.class));

    }


    public Optional<Enterprise> getEnterpriseByName(String name) {
        return dsl.selectFrom(ENTERPRISE)
                .where(ENTERPRISE.NAME.eq(name))
                .fetchOptionalInto(Enterprise.class);
    }

    public Optional<Enterprise> setUpdatedTime(String enterpriseName, LocalDate now) {
        return dsl
                .update(ENTERPRISE)
                .set(ENTERPRISE.RESTART_TIME, now)
                .where(ENTERPRISE.NAME.eq(enterpriseName))
                .returning()
                .fetchOptional()
                .map(record -> record.into(Enterprise.class));
    }

    //      ---     SCHEDULER
    public void updateRestartByScheduler(LocalDate now) {
        dsl
                .update(ENTERPRISE)
                .set(ENTERPRISE.RESTART_TIME, now)
                .execute();
    }

    public List<Enterprise> getAll() {
        return dsl.selectFrom(ENTERPRISE)
                .fetchInto(Enterprise.class);
    }
}
