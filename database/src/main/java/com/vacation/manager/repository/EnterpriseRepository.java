package com.vacation.manager.repository;

import com.vacation.manager.model.Enterprise;
import org.jooq.DSLContext;

import static com.vacation.manager.jooq.tables.Enterprise.ENTERPRISE;

import java.time.LocalDate;
import java.util.Optional;

public class EnterpriseRepository {

    private final DSLContext dsl;

    public EnterpriseRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<Enterprise> createEnterprise(Enterprise enterprise) {
        try {
            return Optional.ofNullable(dsl.insertInto(ENTERPRISE)
                    .set(ENTERPRISE.ENTERPRISE_NAME, enterprise.getEnterpriseName())
                    .returning()
                    .fetchOne()
                    .into(Enterprise.class));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }

    }

    public Optional<Enterprise> confirmEnterprise(Long enterpriseId) {
        return dsl
                .update(ENTERPRISE)
                .set(ENTERPRISE.CONFIRMED, true)
                .where(ENTERPRISE.ID.eq((int) (long) enterpriseId))
                .returning()
                .fetchOptional()
                .map(record -> record.into(Enterprise.class));

    }

    public Optional<Enterprise> getEnterprise(Long enterpriseId) {
        return dsl.selectFrom(ENTERPRISE)
                .where(ENTERPRISE.ID.eq((int) (long) enterpriseId))
                .fetchOptionalInto(Enterprise.class);
    }

    public Optional<Enterprise> getEnterpriseByName(String name) {
        return dsl.selectFrom(ENTERPRISE)
                .where(ENTERPRISE.ENTERPRISE_NAME.eq(name))
                .fetchOptionalInto(Enterprise.class);
    }

    public void updateRestartByScheduler(LocalDate now) {
        dsl
                .update(ENTERPRISE)
                .set(ENTERPRISE.RESTART_TIME, now)
                .execute();
    }

    public Optional<Enterprise> setUpdatedTime(String name, LocalDate now) {
        return dsl
                .update(ENTERPRISE)
                .set(ENTERPRISE.RESTART_TIME, now)
                .where(ENTERPRISE.ENTERPRISE_NAME.eq(name))
                .returning()
                .fetchOptional()
                .map(record -> record.into(Enterprise.class));
    }
}
