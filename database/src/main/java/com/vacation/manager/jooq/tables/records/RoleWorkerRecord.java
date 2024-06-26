/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables.records;


import com.vacation.manager.jooq.tables.RoleWorker;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleWorkerRecord extends TableRecordImpl<RoleWorkerRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = -1575571954;

    /**
     * Setter for <code>company.role_worker.worker_id</code>.
     */
    public void setWorkerId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>company.role_worker.worker_id</code>.
     */
    public Integer getWorkerId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>company.role_worker.role_id</code>.
     */
    public void setRoleId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>company.role_worker.role_id</code>.
     */
    public Integer getRoleId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RoleWorker.ROLE_WORKER.WORKER_ID;
    }

    @Override
    public Field<Integer> field2() {
        return RoleWorker.ROLE_WORKER.ROLE_ID;
    }

    @Override
    public Integer component1() {
        return getWorkerId();
    }

    @Override
    public Integer component2() {
        return getRoleId();
    }

    @Override
    public Integer value1() {
        return getWorkerId();
    }

    @Override
    public Integer value2() {
        return getRoleId();
    }

    @Override
    public RoleWorkerRecord value1(Integer value) {
        setWorkerId(value);
        return this;
    }

    @Override
    public RoleWorkerRecord value2(Integer value) {
        setRoleId(value);
        return this;
    }

    @Override
    public RoleWorkerRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RoleWorkerRecord
     */
    public RoleWorkerRecord() {
        super(RoleWorker.ROLE_WORKER);
    }

    /**
     * Create a detached, initialised RoleWorkerRecord
     */
    public RoleWorkerRecord(Integer workerId, Integer roleId) {
        super(RoleWorker.ROLE_WORKER);

        set(0, workerId);
        set(1, roleId);
    }
}
