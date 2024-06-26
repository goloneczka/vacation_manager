/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.tables;


import com.vacation.manager.jooq.Company;
import com.vacation.manager.jooq.Keys;
import com.vacation.manager.jooq.tables.records.RoleRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class Role extends TableImpl<RoleRecord> {

    private static final long serialVersionUID = 2015943158;

    /**
     * The reference instance of <code>company.role</code>
     */
    public static final Role ROLE = new Role();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RoleRecord> getRecordType() {
        return RoleRecord.class;
    }

    /**
     * The column <code>company.role.id</code>.
     */
    public final TableField<RoleRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('company.role_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>company.role.name</code>.
     */
    public final TableField<RoleRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(127).nullable(false), this, "");

    /**
     * Create a <code>company.role</code> table reference
     */
    public Role() {
        this(DSL.name("role"), null);
    }

    /**
     * Create an aliased <code>company.role</code> table reference
     */
    public Role(String alias) {
        this(DSL.name(alias), ROLE);
    }

    /**
     * Create an aliased <code>company.role</code> table reference
     */
    public Role(Name alias) {
        this(alias, ROLE);
    }

    private Role(Name alias, Table<RoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private Role(Name alias, Table<RoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Role(Table<O> child, ForeignKey<O, RoleRecord> key) {
        super(child, key, ROLE);
    }

    @Override
    public Schema getSchema() {
        return Company.COMPANY;
    }

    @Override
    public Identity<RoleRecord, Integer> getIdentity() {
        return Keys.IDENTITY_ROLE;
    }

    @Override
    public UniqueKey<RoleRecord> getPrimaryKey() {
        return Keys.ROLE_PKEY;
    }

    @Override
    public List<UniqueKey<RoleRecord>> getKeys() {
        return Arrays.<UniqueKey<RoleRecord>>asList(Keys.ROLE_PKEY);
    }

    @Override
    public Role as(String alias) {
        return new Role(DSL.name(alias), this);
    }

    @Override
    public Role as(Name alias) {
        return new Role(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(String name) {
        return new Role(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(Name name) {
        return new Role(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
