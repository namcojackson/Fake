/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB044001;

public interface NFAB044001Constant {

    /** INTERFACE ID */
    static final String INTFC_ID = "NFAI0050";

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Exchange Rate IF";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Fixed Value : NONE */
    static final String NONE = "NONE";

    // ** Message ID ** //
    /** Information Message() */

    /** Information Message() */
    static final String ZZIM0009I = "ZZIM0009I";

    /** TMsg Column Name */
    static final String INTERFACE_ID = "interfaceId";

    // ** DB Item Column Name's Fixed Value ** //

    /** DB Item Column Name */
    static final String TRANSACTION_ID = "TRANSACTION_ID";

    /** DB Item Column Name */
    static final String IS_EXISTS = "IS_EXISTS";

    /** DB Item Column Name */
    static final String TO_CCY_CD = "TO_CCY_CD";

    /** DB Item Column Name */
    static final String ACTL_EXCH_RATE_ENT_DT = "ACTL_EXCH_RATE_ENT_DT";

    /** DB Item Column Name */
    static final String ACTL_EXCH_RATE = "ACTL_EXCH_RATE";

    /** DB Item Column Name */
    static final String APPL_YR_MTH = "APPL_YR_MTH";

    /** DB Item Column Name */
    static final String AVG_EXCH_RATE = "AVG_EXCH_RATE";

    /** DB Item Column Name */
    static final String STD_EXCH_RATE = "STD_EXCH_RATE";
}
