/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.enterprises;


import com.vacation.manager.jooq.DefaultCatalog;
import com.vacation.manager.jooq.enterprises.tables.Enterprise;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Enterprises extends SchemaImpl {

    private static final long serialVersionUID = 931233103;

    /**
     * The reference instance of <code>enterprises</code>
     */
    public static final Enterprises ENTERPRISES = new Enterprises();

    /**
     * The table <code>enterprises.enterprise</code>.
     */
    public final Enterprise ENTERPRISE = Enterprise.ENTERPRISE;

    /**
     * No further instances allowed
     */
    private Enterprises() {
        super("enterprises", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ENTERPRISE_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Enterprise.ENTERPRISE);
    }
}