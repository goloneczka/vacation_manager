/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq;


import com.vacation.manager.jooq.tables.Enterprise;
import com.vacation.manager.jooq.tables.PaidLeave;
import com.vacation.manager.jooq.tables.Role;
import com.vacation.manager.jooq.tables.RoleWorker;
import com.vacation.manager.jooq.tables.Worker;
import com.vacation.manager.jooq.tables.records.EnterpriseRecord;
import com.vacation.manager.jooq.tables.records.PaidLeaveRecord;
import com.vacation.manager.jooq.tables.records.RoleRecord;
import com.vacation.manager.jooq.tables.records.RoleWorkerRecord;
import com.vacation.manager.jooq.tables.records.WorkerRecord;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>company</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<EnterpriseRecord, Integer> IDENTITY_ENTERPRISE = Identities0.IDENTITY_ENTERPRISE;
    public static final Identity<PaidLeaveRecord, Integer> IDENTITY_PAID_LEAVE = Identities0.IDENTITY_PAID_LEAVE;
    public static final Identity<RoleRecord, Integer> IDENTITY_ROLE = Identities0.IDENTITY_ROLE;
    public static final Identity<WorkerRecord, Integer> IDENTITY_WORKER = Identities0.IDENTITY_WORKER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EnterpriseRecord> ENTERPRISE_PKEY = UniqueKeys0.ENTERPRISE_PKEY;
    public static final UniqueKey<EnterpriseRecord> ENTERPRISE_ENTERPRISE_NAME_KEY = UniqueKeys0.ENTERPRISE_ENTERPRISE_NAME_KEY;
    public static final UniqueKey<PaidLeaveRecord> PAID_LEAVE_PKEY = UniqueKeys0.PAID_LEAVE_PKEY;
    public static final UniqueKey<RoleRecord> ROLE_PKEY = UniqueKeys0.ROLE_PKEY;
    public static final UniqueKey<WorkerRecord> WORKER_PKEY = UniqueKeys0.WORKER_PKEY;
    public static final UniqueKey<WorkerRecord> WORKER_EMAIL_ENTERPRISE_ID_KEY = UniqueKeys0.WORKER_EMAIL_ENTERPRISE_ID_KEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<PaidLeaveRecord, WorkerRecord> PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY = ForeignKeys0.PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY;
    public static final ForeignKey<PaidLeaveRecord, WorkerRecord> PAID_LEAVE__CASCADE_WORKER = ForeignKeys0.PAID_LEAVE__CASCADE_WORKER;
    public static final ForeignKey<RoleWorkerRecord, WorkerRecord> ROLE_WORKER__ROLE_WORKER_WORKER_ID_FKEY = ForeignKeys0.ROLE_WORKER__ROLE_WORKER_WORKER_ID_FKEY;
    public static final ForeignKey<RoleWorkerRecord, WorkerRecord> ROLE_WORKER__CASCADE_WORKER = ForeignKeys0.ROLE_WORKER__CASCADE_WORKER;
    public static final ForeignKey<RoleWorkerRecord, RoleRecord> ROLE_WORKER__ROLE_WORKER_ROLE_ID_FKEY = ForeignKeys0.ROLE_WORKER__ROLE_WORKER_ROLE_ID_FKEY;
    public static final ForeignKey<WorkerRecord, EnterpriseRecord> WORKER__WORKER_ENTERPRISE_ID_FKEY = ForeignKeys0.WORKER__WORKER_ENTERPRISE_ID_FKEY;
    public static final ForeignKey<WorkerRecord, EnterpriseRecord> WORKER__CASCADE_ENTERPRISE = ForeignKeys0.WORKER__CASCADE_ENTERPRISE;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<EnterpriseRecord, Integer> IDENTITY_ENTERPRISE = Internal.createIdentity(Enterprise.ENTERPRISE, Enterprise.ENTERPRISE.ID);
        public static Identity<PaidLeaveRecord, Integer> IDENTITY_PAID_LEAVE = Internal.createIdentity(PaidLeave.PAID_LEAVE, PaidLeave.PAID_LEAVE.ID);
        public static Identity<RoleRecord, Integer> IDENTITY_ROLE = Internal.createIdentity(Role.ROLE, Role.ROLE.ID);
        public static Identity<WorkerRecord, Integer> IDENTITY_WORKER = Internal.createIdentity(Worker.WORKER, Worker.WORKER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<EnterpriseRecord> ENTERPRISE_PKEY = Internal.createUniqueKey(Enterprise.ENTERPRISE, "enterprise_pkey", new TableField[] { Enterprise.ENTERPRISE.ID }, true);
        public static final UniqueKey<EnterpriseRecord> ENTERPRISE_ENTERPRISE_NAME_KEY = Internal.createUniqueKey(Enterprise.ENTERPRISE, "enterprise_enterprise_name_key", new TableField[] { Enterprise.ENTERPRISE.ENTERPRISE_NAME }, true);
        public static final UniqueKey<PaidLeaveRecord> PAID_LEAVE_PKEY = Internal.createUniqueKey(PaidLeave.PAID_LEAVE, "paid_leave_pkey", new TableField[] { PaidLeave.PAID_LEAVE.ID }, true);
        public static final UniqueKey<RoleRecord> ROLE_PKEY = Internal.createUniqueKey(Role.ROLE, "role_pkey", new TableField[] { Role.ROLE.ID }, true);
        public static final UniqueKey<WorkerRecord> WORKER_PKEY = Internal.createUniqueKey(Worker.WORKER, "worker_pkey", new TableField[] { Worker.WORKER.ID }, true);
        public static final UniqueKey<WorkerRecord> WORKER_EMAIL_ENTERPRISE_ID_KEY = Internal.createUniqueKey(Worker.WORKER, "worker_email_enterprise_id_key", new TableField[] { Worker.WORKER.EMAIL, Worker.WORKER.ENTERPRISE_ID }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<PaidLeaveRecord, WorkerRecord> PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY = Internal.createForeignKey(Keys.WORKER_PKEY, PaidLeave.PAID_LEAVE, "paid_leave_employee_id_fkey", new TableField[] { PaidLeave.PAID_LEAVE.EMPLOYEE_ID, PaidLeave.PAID_LEAVE.EMPLOYEE_ID }, true);
        public static final ForeignKey<PaidLeaveRecord, WorkerRecord> PAID_LEAVE__CASCADE_WORKER = Internal.createForeignKey(Keys.WORKER_PKEY, PaidLeave.PAID_LEAVE, "cascade_worker", new TableField[] { PaidLeave.PAID_LEAVE.EMPLOYEE_ID }, true);
        public static final ForeignKey<RoleWorkerRecord, WorkerRecord> ROLE_WORKER__ROLE_WORKER_WORKER_ID_FKEY = Internal.createForeignKey(Keys.WORKER_PKEY, RoleWorker.ROLE_WORKER, "role_worker_worker_id_fkey", new TableField[] { RoleWorker.ROLE_WORKER.WORKER_ID }, true);
        public static final ForeignKey<RoleWorkerRecord, WorkerRecord> ROLE_WORKER__CASCADE_WORKER = Internal.createForeignKey(Keys.WORKER_PKEY, RoleWorker.ROLE_WORKER, "cascade_worker", new TableField[] { RoleWorker.ROLE_WORKER.WORKER_ID }, true);
        public static final ForeignKey<RoleWorkerRecord, RoleRecord> ROLE_WORKER__ROLE_WORKER_ROLE_ID_FKEY = Internal.createForeignKey(Keys.ROLE_PKEY, RoleWorker.ROLE_WORKER, "role_worker_role_id_fkey", new TableField[] { RoleWorker.ROLE_WORKER.ROLE_ID, RoleWorker.ROLE_WORKER.ROLE_ID }, true);
        public static final ForeignKey<WorkerRecord, EnterpriseRecord> WORKER__WORKER_ENTERPRISE_ID_FKEY = Internal.createForeignKey(Keys.ENTERPRISE_PKEY, Worker.WORKER, "worker_enterprise_id_fkey", new TableField[] { Worker.WORKER.ENTERPRISE_ID, Worker.WORKER.ENTERPRISE_ID }, true);
        public static final ForeignKey<WorkerRecord, EnterpriseRecord> WORKER__CASCADE_ENTERPRISE = Internal.createForeignKey(Keys.ENTERPRISE_PKEY, Worker.WORKER, "cascade_enterprise", new TableField[] { Worker.WORKER.ENTERPRISE_ID }, true);
    }
}
