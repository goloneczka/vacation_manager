/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables;


import com.vacation.manager.jooq.Company;
import com.vacation.manager.jooq.Keys;
import com.vacation.manager.jooq.tables.records.PaidLeaveRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaidLeave extends TableImpl<PaidLeaveRecord> {

    private static final long serialVersionUID = -445401632;

    /**
     * The reference instance of <code>company.paid_leave</code>
     */
    public static final PaidLeave PAID_LEAVE = new PaidLeave();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PaidLeaveRecord> getRecordType() {
        return PaidLeaveRecord.class;
    }

    /**
     * The column <code>company.paid_leave.id</code>.
     */
    public final TableField<PaidLeaveRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('company.paid_leave_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>company.paid_leave.start_date</code>.
     */
    public final TableField<PaidLeaveRecord, LocalDate> START_DATE = createField(DSL.name("start_date"), org.jooq.impl.SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>company.paid_leave.days</code>.
     */
    public final TableField<PaidLeaveRecord, Float> DAYS = createField(DSL.name("days"), org.jooq.impl.SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>company.paid_leave.employee_id</code>.
     */
    public final TableField<PaidLeaveRecord, Integer> EMPLOYEE_ID = createField(DSL.name("employee_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>company.paid_leave.end_date</code>.
     */
    public final TableField<PaidLeaveRecord, LocalDate> END_DATE = createField(DSL.name("end_date"), org.jooq.impl.SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>company.paid_leave.describe</code>.
     */
    public final TableField<PaidLeaveRecord, String> DESCRIBE = createField(DSL.name("describe"), org.jooq.impl.SQLDataType.VARCHAR(1024), this, "");

    /**
     * The column <code>company.paid_leave.status</code>.
     */
    public final TableField<PaidLeaveRecord, String> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.VARCHAR(128).defaultValue(org.jooq.impl.DSL.field("'NEW'::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>company.paid_leave</code> table reference
     */
    public PaidLeave() {
        this(DSL.name("paid_leave"), null);
    }

    /**
     * Create an aliased <code>company.paid_leave</code> table reference
     */
    public PaidLeave(String alias) {
        this(DSL.name(alias), PAID_LEAVE);
    }

    /**
     * Create an aliased <code>company.paid_leave</code> table reference
     */
    public PaidLeave(Name alias) {
        this(alias, PAID_LEAVE);
    }

    private PaidLeave(Name alias, Table<PaidLeaveRecord> aliased) {
        this(alias, aliased, null);
    }

    private PaidLeave(Name alias, Table<PaidLeaveRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> PaidLeave(Table<O> child, ForeignKey<O, PaidLeaveRecord> key) {
        super(child, key, PAID_LEAVE);
    }

    @Override
    public Schema getSchema() {
        return Company.COMPANY;
    }

    @Override
    public Identity<PaidLeaveRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PAID_LEAVE;
    }

    @Override
    public UniqueKey<PaidLeaveRecord> getPrimaryKey() {
        return Keys.PAID_LEAVE_PKEY;
    }

    @Override
    public List<UniqueKey<PaidLeaveRecord>> getKeys() {
        return Arrays.<UniqueKey<PaidLeaveRecord>>asList(Keys.PAID_LEAVE_PKEY);
    }

    @Override
    public List<ForeignKey<PaidLeaveRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PaidLeaveRecord, ?>>asList(Keys.PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY);
    }

    public Worker worker() {
        return new Worker(this, Keys.PAID_LEAVE__PAID_LEAVE_EMPLOYEE_ID_FKEY);
    }

    @Override
    public List<Check<PaidLeaveRecord>> getChecks() {
        return Arrays.<Check<PaidLeaveRecord>>asList(
              Internal.createCheck(this, DSL.name("paid_leave_status_check"), "((((status)::text = 'NEW'::text) OR ((status)::text = 'ACCEPTED'::text) OR ((status)::text = 'REJECTED'::text)))", true)
        );
    }

    @Override
    public PaidLeave as(String alias) {
        return new PaidLeave(DSL.name(alias), this);
    }

    @Override
    public PaidLeave as(Name alias) {
        return new PaidLeave(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PaidLeave rename(String name) {
        return new PaidLeave(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PaidLeave rename(Name name) {
        return new PaidLeave(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, LocalDate, Float, Integer, LocalDate, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
