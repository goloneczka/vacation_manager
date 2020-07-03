/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.enterprises.tables;


import com.vacation.manager.jooq.enterprises.Enterprises;
import com.vacation.manager.jooq.enterprises.Keys;
import com.vacation.manager.jooq.enterprises.tables.records.EnterpriseRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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

    private static final long serialVersionUID = 1573612126;

    /**
     * The reference instance of <code>enterprises.enterprise</code>
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
     * The column <code>enterprises.enterprise.id</code>.
     */
    public final TableField<EnterpriseRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('enterprises.enterprise_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>enterprises.enterprise.enterprise_name</code>.
     */
    public final TableField<EnterpriseRecord, String> ENTERPRISE_NAME = createField(DSL.name("enterprise_name"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * The column <code>enterprises.enterprise.free_days</code>.
     */
    public final TableField<EnterpriseRecord, Float> FREE_DAYS = createField(DSL.name("free_days"), org.jooq.impl.SQLDataType.REAL.nullable(false).defaultValue(org.jooq.impl.DSL.field("20", org.jooq.impl.SQLDataType.REAL)), this, "");

    /**
     * Create a <code>enterprises.enterprise</code> table reference
     */
    public Enterprise() {
        this(DSL.name("enterprise"), null);
    }

    /**
     * Create an aliased <code>enterprises.enterprise</code> table reference
     */
    public Enterprise(String alias) {
        this(DSL.name(alias), ENTERPRISE);
    }

    /**
     * Create an aliased <code>enterprises.enterprise</code> table reference
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
        return Enterprises.ENTERPRISES;
    }

    @Override
    public Identity<EnterpriseRecord, Integer> getIdentity() {
        return Keys.IDENTITY_ENTERPRISE;
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
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Float> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
