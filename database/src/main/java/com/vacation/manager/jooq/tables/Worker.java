/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables;


import com.vacation.manager.jooq.Company;
import com.vacation.manager.jooq.Keys;
import com.vacation.manager.jooq.tables.records.WorkerRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Worker extends TableImpl<WorkerRecord> {

    private static final long serialVersionUID = 1657354949;

    /**
     * The reference instance of <code>company.worker</code>
     */
    public static final Worker WORKER = new Worker();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WorkerRecord> getRecordType() {
        return WorkerRecord.class;
    }

    /**
     * The column <code>company.worker.id</code>.
     */
    public final TableField<WorkerRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('company.worker_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>company.worker.email</code>.
     */
    public final TableField<WorkerRecord, String> EMAIL = createField(DSL.name("email"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * The column <code>company.worker.enterprise_id</code>.
     */
    public final TableField<WorkerRecord, Integer> ENTERPRISE_ID = createField(DSL.name("enterprise_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>company.worker.password</code>.
     */
    public final TableField<WorkerRecord, String> PASSWORD = createField(DSL.name("password"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * The column <code>company.worker.name</code>.
     */
    public final TableField<WorkerRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * The column <code>company.worker.occupation</code>.
     */
    public final TableField<WorkerRecord, String> OCCUPATION = createField(DSL.name("occupation"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * The column <code>company.worker.confirmed</code>.
     */
    public final TableField<WorkerRecord, Boolean> CONFIRMED = createField(DSL.name("confirmed"), org.jooq.impl.SQLDataType.BOOLEAN.defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>company.worker.hired</code>.
     */
    public final TableField<WorkerRecord, LocalDate> HIRED = createField(DSL.name("hired"), org.jooq.impl.SQLDataType.LOCALDATE.defaultValue(org.jooq.impl.DSL.field("CURRENT_DATE", org.jooq.impl.SQLDataType.LOCALDATE)), this, "");

    /**
     * The column <code>company.worker.employee_vars_id</code>.
     */
    public final TableField<WorkerRecord, Integer> EMPLOYEE_VARS_ID = createField(DSL.name("employee_vars_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>company.worker</code> table reference
     */
    public Worker() {
        this(DSL.name("worker"), null);
    }

    /**
     * Create an aliased <code>company.worker</code> table reference
     */
    public Worker(String alias) {
        this(DSL.name(alias), WORKER);
    }

    /**
     * Create an aliased <code>company.worker</code> table reference
     */
    public Worker(Name alias) {
        this(alias, WORKER);
    }

    private Worker(Name alias, Table<WorkerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Worker(Name alias, Table<WorkerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Worker(Table<O> child, ForeignKey<O, WorkerRecord> key) {
        super(child, key, WORKER);
    }

    @Override
    public Schema getSchema() {
        return Company.COMPANY;
    }

    @Override
    public Identity<WorkerRecord, Integer> getIdentity() {
        return Keys.IDENTITY_WORKER;
    }

    @Override
    public UniqueKey<WorkerRecord> getPrimaryKey() {
        return Keys.WORKER_PKEY;
    }

    @Override
    public List<UniqueKey<WorkerRecord>> getKeys() {
        return Arrays.<UniqueKey<WorkerRecord>>asList(Keys.WORKER_PKEY, Keys.WORKER_EMAIL_ENTERPRISE_ID_KEY);
    }

    @Override
    public List<ForeignKey<WorkerRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WorkerRecord, ?>>asList(Keys.WORKER__WORKER_ENTERPRISE_ID_FKEY, Keys.WORKER__WORKER_EMPLOYEE_VARS_ID_FKEY);
    }

    public Enterprise enterprise() {
        return new Enterprise(this, Keys.WORKER__WORKER_ENTERPRISE_ID_FKEY);
    }

    public WorkerExtraDays workerExtraDays() {
        return new WorkerExtraDays(this, Keys.WORKER__WORKER_EMPLOYEE_VARS_ID_FKEY);
    }

    @Override
    public Worker as(String alias) {
        return new Worker(DSL.name(alias), this);
    }

    @Override
    public Worker as(Name alias) {
        return new Worker(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Worker rename(String name) {
        return new Worker(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Worker rename(Name name) {
        return new Worker(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, String, Integer, String, String, String, Boolean, LocalDate, Integer> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
