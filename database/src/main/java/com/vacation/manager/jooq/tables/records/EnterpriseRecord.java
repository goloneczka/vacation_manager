/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables.records;


import com.vacation.manager.jooq.tables.Enterprise;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EnterpriseRecord extends UpdatableRecordImpl<EnterpriseRecord> implements Record4<Integer, String, Float, Boolean> {

    private static final long serialVersionUID = 540458570;

    /**
     * Setter for <code>company.enterprise.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>company.enterprise.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>company.enterprise.enterprise_name</code>.
     */
    public void setEnterpriseName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>company.enterprise.enterprise_name</code>.
     */
    public String getEnterpriseName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>company.enterprise.free_days</code>.
     */
    public void setFreeDays(Float value) {
        set(2, value);
    }

    /**
     * Getter for <code>company.enterprise.free_days</code>.
     */
    public Float getFreeDays() {
        return (Float) get(2);
    }

    /**
     * Setter for <code>company.enterprise.confirmed</code>.
     */
    public void setConfirmed(Boolean value) {
        set(3, value);
    }

    /**
     * Getter for <code>company.enterprise.confirmed</code>.
     */
    public Boolean getConfirmed() {
        return (Boolean) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Float, Boolean> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, Float, Boolean> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Enterprise.ENTERPRISE.ID;
    }

    @Override
    public Field<String> field2() {
        return Enterprise.ENTERPRISE.ENTERPRISE_NAME;
    }

    @Override
    public Field<Float> field3() {
        return Enterprise.ENTERPRISE.FREE_DAYS;
    }

    @Override
    public Field<Boolean> field4() {
        return Enterprise.ENTERPRISE.CONFIRMED;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEnterpriseName();
    }

    @Override
    public Float component3() {
        return getFreeDays();
    }

    @Override
    public Boolean component4() {
        return getConfirmed();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEnterpriseName();
    }

    @Override
    public Float value3() {
        return getFreeDays();
    }

    @Override
    public Boolean value4() {
        return getConfirmed();
    }

    @Override
    public EnterpriseRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public EnterpriseRecord value2(String value) {
        setEnterpriseName(value);
        return this;
    }

    @Override
    public EnterpriseRecord value3(Float value) {
        setFreeDays(value);
        return this;
    }

    @Override
    public EnterpriseRecord value4(Boolean value) {
        setConfirmed(value);
        return this;
    }

    @Override
    public EnterpriseRecord values(Integer value1, String value2, Float value3, Boolean value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EnterpriseRecord
     */
    public EnterpriseRecord() {
        super(Enterprise.ENTERPRISE);
    }

    /**
     * Create a detached, initialised EnterpriseRecord
     */
    public EnterpriseRecord(Integer id, String enterpriseName, Float freeDays, Boolean confirmed) {
        super(Enterprise.ENTERPRISE);

        set(0, id);
        set(1, enterpriseName);
        set(2, freeDays);
        set(3, confirmed);
    }
}
