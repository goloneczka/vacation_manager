package com.vacation.manager.repository;

import com.vacation.manager.model.Enterprise;
import org.jooq.DSLContext;

import static com.vacation.manager.jooq.tables.Enterprise.ENTERPRISE;

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
        } catch (RuntimeException runtimeException) {
            return Optional.empty();
        }

    }
}
