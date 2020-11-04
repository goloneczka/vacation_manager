package com.vacation.manager.integration.tests.enterprise;

import com.vacation.manager.jooq.Tables;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;


public class WeatherDbUtils {

    private final DSLContext dsl;

    public WeatherDbUtils(Map<String, String> config) throws SQLException {
        dsl = DSL.using(DriverManager.getConnection(config.get("url"), config.get("username"), config.get("password")));
    }

    private boolean deleteAll() {
        return 0 < dsl
                .deleteFrom(Tables.ENTERPRISE)
                .execute();
    }

    public void clearEnterprisesData() {
        deleteAll();
    }
}
