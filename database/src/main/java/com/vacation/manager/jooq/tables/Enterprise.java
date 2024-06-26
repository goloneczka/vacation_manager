/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables;


import com.vacation.manager.jooq.Company;
import com.vacation.manager.jooq.Keys;
import com.vacation.manager.jooq.tables.records.EnterpriseRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
public class Enterprise extends TableImpl<EnterpriseRecord> {

    private static final long serialVersionUID = 1772526471;

    /**
     * The reference instance of <code>company.enterprise</code>
     */
    public static final Enterprise ENTERPRISE = new Enterprise();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EnterpriseRecord> getRecordType() {
        return EnterpriseRecord.class;
    }

    /**
     * The column <code>company.enterprise.name</code>.
     */
    public final TableField<EnterpriseRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * The column <code>company.enterprise.free_days</code>.
     */
    public final TableField<EnterpriseRecord, Float> FREE_DAYS = createField(DSL.name("free_days"), org.jooq.impl.SQLDataType.REAL.defaultValue(org.jooq.impl.DSL.field("20", org.jooq.impl.SQLDataType.REAL)), this, "");

    /**
     * The column <code>company.enterprise.confirmed</code>.
     */
    public final TableField<EnterpriseRecord, Boolean> CONFIRMED = createField(DSL.name("confirmed"), org.jooq.impl.SQLDataType.BOOLEAN.defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>company.enterprise.restart_time</code>.
     */
    public final TableField<EnterpriseRecord, LocalDate> RESTART_TIME = createField(DSL.name("restart_time"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * Create a <code>company.enterprise</code> table reference
     */
    public Enterprise() {
        this(DSL.name("enterprise"), null);
    }

    /**
     * Create an aliased <code>company.enterprise</code> table reference
     */
    public Enterprise(String alias) {
        this(DSL.name(alias), ENTERPRISE);
    }

    /**
     * Create an aliased <code>company.enterprise</code> table reference
     */
    public Enterprise(Name alias) {
        this(alias, ENTERPRISE);
    }

    private Enterprise(Name alias, Table<EnterpriseRecord> aliased) {
        this(alias, aliased, null);
    }

    private Enterprise(Name alias, Table<EnterpriseRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Enterprise(Table<O> child, ForeignKey<O, EnterpriseRecord> key) {
        super(child, key, ENTERPRISE);
    }

    @Override
    public Schema getSchema() {
        return Company.COMPANY;
    }

    @Override
    public UniqueKey<EnterpriseRecord> getPrimaryKey() {
        return Keys.ENTERPRISE_PKEY;
    }

    @Override
    public List<UniqueKey<EnterpriseRecord>> getKeys() {
        return Arrays.<UniqueKey<EnterpriseRecord>>asList(Keys.ENTERPRISE_PKEY);
    }

    @Override
    public Enterprise as(String alias) {
        return new Enterprise(DSL.name(alias), this);
    }

    @Override
    public Enterprise as(Name alias) {
        return new Enterprise(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Enterprise rename(String name) {
        return new Enterprise(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Enterprise rename(Name name) {
        return new Enterprise(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, Float, Boolean, LocalDate> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
