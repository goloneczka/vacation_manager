/*
 * This file is generated by jOOQ.
 */
package com.vacation.manager.jooq.leave;


import org.jooq.Sequence;
import org.jooq.impl.Internal;


/**
 * Convenience access to all sequences in leave
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>leave.paid_leave_id_seq</code>
     */
    public static final Sequence<Integer> PAID_LEAVE_ID_SEQ = Internal.createSequence("paid_leave_id_seq", Leave.LEAVE, org.jooq.impl.SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);
}