/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.workers.tables.records;


import com.vacation.manager.jooq.workers.tables.Worker;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkerRecord extends UpdatableRecordImpl<WorkerRecord> implements Record6<Integer, String, Integer, String, String, String> {

    private static final long serialVersionUID = 664664151;

    /**
     * Setter for <code>workers.worker.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>workers.worker.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>workers.worker.email</code>.
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>workers.worker.email</code>.
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>workers.worker.enterprise_id</code>.
     */
    public void setEnterpriseId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>workers.worker.enterprise_id</code>.
     */
    public Integer getEnterpriseId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>workers.worker.password</code>.
     */
    public void setPassword(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>workers.worker.password</code>.
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>workers.worker.name</code>.
     */
    public void setName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>workers.worker.name</code>.
     */
    public String getName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>workers.worker.occupation</code>.
     */
    public void setOccupation(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>workers.worker.occupation</code>.
     */
    public String getOccupation() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Integer, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, Integer, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Worker.WORKER.ID;
    }

    @Override
    public Field<String> field2() {
        return Worker.WORKER.EMAIL;
    }

    @Override
    public Field<Integer> field3() {
        return Worker.WORKER.ENTERPRISE_ID;
    }

    @Override
    public Field<String> field4() {
        return Worker.WORKER.PASSWORD;
    }

    @Override
    public Field<String> field5() {
        return Worker.WORKER.NAME;
    }

    @Override
    public Field<String> field6() {
        return Worker.WORKER.OCCUPATION;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEmail();
    }

    @Override
    public Integer component3() {
        return getEnterpriseId();
    }

    @Override
    public String component4() {
        return getPassword();
    }

    @Override
    public String component5() {
        return getName();
    }

    @Override
    public String component6() {
        return getOccupation();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEmail();
    }

    @Override
    public Integer value3() {
        return getEnterpriseId();
    }

    @Override
    public String value4() {
        return getPassword();
    }

    @Override
    public String value5() {
        return getName();
    }

    @Override
    public String value6() {
        return getOccupation();
    }

    @Override
    public WorkerRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public WorkerRecord value2(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public WorkerRecord value3(Integer value) {
        setEnterpriseId(value);
        return this;
    }

    @Override
    public WorkerRecord value4(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public WorkerRecord value5(String value) {
        setName(value);
        return this;
    }

    @Override
    public WorkerRecord value6(String value) {
        setOccupation(value);
        return this;
    }

    @Override
    public WorkerRecord values(Integer value1, String value2, Integer value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WorkerRecord
     */
    public WorkerRecord() {
        super(Worker.WORKER);
    }

    /**
     * Create a detached, initialised WorkerRecord
     */
    public WorkerRecord(Integer id, String email, Integer enterpriseId, String password, String name, String occupation) {
        super(Worker.WORKER);

        set(0, id);
        set(1, email);
        set(2, enterpriseId);
        set(3, password);
        set(4, name);
        set(5, occupation);
    }
}
