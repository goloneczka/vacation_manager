/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.enterprises;


import com.vacation.manager.jooq.enterprises.tables.Enterprise;
import com.vacation.manager.jooq.enterprises.tables.records.EnterpriseRecord;

import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>enterprises</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<EnterpriseRecord, Integer> IDENTITY_ENTERPRISE = Identities0.IDENTITY_ENTERPRISE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EnterpriseRecord> ENTERPRISE_PKEY = UniqueKeys0.ENTERPRISE_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<EnterpriseRecord, Integer> IDENTITY_ENTERPRISE = Internal.createIdentity(Enterprise.ENTERPRISE, Enterprise.ENTERPRISE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<EnterpriseRecord> ENTERPRISE_PKEY = Internal.createUniqueKey(Enterprise.ENTERPRISE, "enterprise_pkey", new TableField[] { Enterprise.ENTERPRISE.ID }, true);
    }
}