/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.leave;


import com.vacation.manager.jooq.leave.tables.PaidLeave;
import com.vacation.manager.jooq.leave.tables.records.PaidLeaveRecord;
import com.vacation.manager.jooq.workers.tables.records.WorkerRecord;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>leave</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<PaidLeaveRecord, Integer> IDENTITY_PAID_LEAVE = Identities0.IDENTITY_PAID_LEAVE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PaidLeaveRecord> PAID_LEAVE_PKEY = UniqueKeys0.PAID_LEAVE_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<PaidLeaveRecord, WorkerRecord> PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY = ForeignKeys0.PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<PaidLeaveRecord, Integer> IDENTITY_PAID_LEAVE = Internal.createIdentity(PaidLeave.PAID_LEAVE, PaidLeave.PAID_LEAVE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<PaidLeaveRecord> PAID_LEAVE_PKEY = Internal.createUniqueKey(PaidLeave.PAID_LEAVE, "paid_leave_pkey", new TableField[] { PaidLeave.PAID_LEAVE.ID }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<PaidLeaveRecord, WorkerRecord> PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY = Internal.createForeignKey(com.vacation.manager.jooq.workers.Keys.WORKER_PKEY, PaidLeave.PAID_LEAVE, "paid_leave_employee_id_fkey", new TableField[] { PaidLeave.PAID_LEAVE.EMPLOYEE_ID }, true);
    }
}
