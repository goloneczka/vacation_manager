/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables.records;


import com.vacation.manager.jooq.tables.PaidLeave;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaidLeaveRecord extends UpdatableRecordImpl<PaidLeaveRecord> implements Record4<Integer, LocalDate, Float, Integer> {

    private static final long serialVersionUID = -2136704596;

    /**
     * Setter for <code>company.paid_leave.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>company.paid_leave.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>company.paid_leave.start_date</code>.
     */
    public void setStartDate(LocalDate value) {
        set(1, value);
    }

    /**
     * Getter for <code>company.paid_leave.start_date</code>.
     */
    public LocalDate getStartDate() {
        return (LocalDate) get(1);
    }

    /**
     * Setter for <code>company.paid_leave.days</code>.
     */
    public void setDays(Float value) {
        set(2, value);
    }

    /**
     * Getter for <code>company.paid_leave.days</code>.
     */
    public Float getDays() {
        return (Float) get(2);
    }

    /**
     * Setter for <code>company.paid_leave.employee_id</code>.
     */
    public void setEmployeeId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>company.paid_leave.employee_id</code>.
     */
    public Integer getEmployeeId() {
        return (Integer) get(3);
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
    public Row4<Integer, LocalDate, Float, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, LocalDate, Float, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return PaidLeave.PAID_LEAVE.ID;
    }

    @Override
    public Field<LocalDate> field2() {
        return PaidLeave.PAID_LEAVE.START_DATE;
    }

    @Override
    public Field<Float> field3() {
        return PaidLeave.PAID_LEAVE.DAYS;
    }

    @Override
    public Field<Integer> field4() {
        return PaidLeave.PAID_LEAVE.EMPLOYEE_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public LocalDate component2() {
        return getStartDate();
    }

    @Override
    public Float component3() {
        return getDays();
    }

    @Override
    public Integer component4() {
        return getEmployeeId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public LocalDate value2() {
        return getStartDate();
    }

    @Override
    public Float value3() {
        return getDays();
    }

    @Override
    public Integer value4() {
        return getEmployeeId();
    }

    @Override
    public PaidLeaveRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public PaidLeaveRecord value2(LocalDate value) {
        setStartDate(value);
        return this;
    }

    @Override
    public PaidLeaveRecord value3(Float value) {
        setDays(value);
        return this;
    }

    @Override
    public PaidLeaveRecord value4(Integer value) {
        setEmployeeId(value);
        return this;
    }

    @Override
    public PaidLeaveRecord values(Integer value1, LocalDate value2, Float value3, Integer value4) {
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
     * Create a detached PaidLeaveRecord
     */
    public PaidLeaveRecord() {
        super(PaidLeave.PAID_LEAVE);
    }

    /**
     * Create a detached, initialised PaidLeaveRecord
     */
    public PaidLeaveRecord(Integer id, LocalDate startDate, Float days, Integer employeeId) {
        super(PaidLeave.PAID_LEAVE);

        set(0, id);
        set(1, startDate);
        set(2, days);
        set(3, employeeId);
    }
}