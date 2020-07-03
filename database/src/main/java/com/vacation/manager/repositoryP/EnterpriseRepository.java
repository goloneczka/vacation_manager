package com.vacation.manager.repositoryP;

import com.vacation.manager.model.Enterprise;
import org.jooq.DSLContext;
import static com.vacation.manager.jooq.enterprises.tables.Enterprise.ENTERPRISE;
import java.util.Optional;

public class EnterpriseRepository {

    private final DSLContext dsl;

    public EnterpriseRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<Enterprise> createEnterprise(Enterprise enterprise){
        var result = dsl.insertInto(ENTERPRISE)
                .set(ENTERPRISE.ENTERPRISE_NAME, enterprise.getEnterpriseName())
                .returning()
                .fetchOne();
        return Optional.ofNullable(result.into(Enterprise.class));
    }
}
