/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq;


import com.vacation.manager.jooq.tables.Enterprise;
import com.vacation.manager.jooq.tables.PaidLeave;
import com.vacation.manager.jooq.tables.Role;
import com.vacation.manager.jooq.tables.RoleWorker;
import com.vacation.manager.jooq.tables.Worker;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Company extends SchemaImpl {

    private static final long serialVersionUID = -1027934639;

    /**
     * The reference instance of <code>company</code>
     */
    public static final Company COMPANY = new Company();

    /**
     * The table <code>company.enterprise</code>.
     */
    public final Enterprise ENTERPRISE = Enterprise.ENTERPRISE;

    /**
     * The table <code>company.paid_leave</code>.
     */
    public final PaidLeave PAID_LEAVE = PaidLeave.PAID_LEAVE;

    /**
     * The table <code>company.role</code>.
     */
    public final Role ROLE = Role.ROLE;

    /**
     * The table <code>company.role_worker</code>.
     */
    public final RoleWorker ROLE_WORKER = RoleWorker.ROLE_WORKER;

    /**
     * The table <code>company.worker</code>.
     */
    public final Worker WORKER = Worker.WORKER;

    /**
     * No further instances allowed
     */
    private Company() {
        super("company", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ENTERPRISE_ID_SEQ,
            Sequences.PAID_LEAVE_ID_SEQ,
            Sequences.ROLE_ID_SEQ,
            Sequences.WORKER_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Enterprise.ENTERPRISE,
            PaidLeave.PAID_LEAVE,
            Role.ROLE,
            RoleWorker.ROLE_WORKER,
            Worker.WORKER);
    }
}