/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables.records;


import com.vacation.manager.jooq.tables.Worker;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkerRecord extends UpdatableRecordImpl<WorkerRecord> implements Record8<Integer, String, Integer, String, String, String, Boolean, LocalDate> {

    private static final long serialVersionUID = -1621220581;

    /**
     * Setter for <code>company.worker.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>company.worker.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>company.worker.email</code>.
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>company.worker.email</code>.
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>company.worker.enterprise_id</code>.
     */
    public void setEnterpriseId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>company.worker.enterprise_id</code>.
     */
    public Integer getEnterpriseId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>company.worker.password</code>.
     */
    public void setPassword(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>company.worker.password</code>.
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>company.worker.name</code>.
     */
    public void setName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>company.worker.name</code>.
     */
    public String getName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>company.worker.occupation</code>.
     */
    public void setOccupation(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>company.worker.occupation</code>.
     */
    public String getOccupation() {
        return (String) get(5);
    }

    /**
     * Setter for <code>company.worker.confirmed</code>.
     */
    public void setConfirmed(Boolean value) {
        set(6, value);
    }

    /**
     * Getter for <code>company.worker.confirmed</code>.
     */
    public Boolean getConfirmed() {
        return (Boolean) get(6);
    }

    /**
     * Setter for <code>company.worker.hired</code>.
     */
    public void setHired(LocalDate value) {
        set(7, value);
    }

    /**
     * Getter for <code>company.worker.hired</code>.
     */
    public LocalDate getHired() {
        return (LocalDate) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, String, Integer, String, String, String, Boolean, LocalDate> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, String, Integer, String, String, String, Boolean, LocalDate> valuesRow() {
        return (Row8) super.valuesRow();
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
    public Field<Boolean> field7() {
        return Worker.WORKER.CONFIRMED;
    }

    @Override
    public Field<LocalDate> field8() {
        return Worker.WORKER.HIRED;
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
    public Boolean component7() {
        return getConfirmed();
    }

    @Override
    public LocalDate component8() {
        return getHired();
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
    public Boolean value7() {
        return getConfirmed();
    }

    @Override
    public LocalDate value8() {
        return getHired();
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
    public WorkerRecord value7(Boolean value) {
        setConfirmed(value);
        return this;
    }

    @Override
    public WorkerRecord value8(LocalDate value) {
        setHired(value);
        return this;
    }

    @Override
    public WorkerRecord values(Integer value1, String value2, Integer value3, String value4, String value5, String value6, Boolean value7, LocalDate value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
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
    public WorkerRecord(Integer id, String email, Integer enterpriseId, String password, String name, String occupation, Boolean confirmed, LocalDate hired) {
        super(Worker.WORKER);

        set(0, id);
        set(1, email);
        set(2, enterpriseId);
        set(3, password);
        set(4, name);
        set(5, occupation);
        set(6, confirmed);
        set(7, hired);
    }
}
