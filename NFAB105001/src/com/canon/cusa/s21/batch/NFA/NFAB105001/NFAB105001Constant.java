package com.canon.cusa.s21.batch.NFA.NFAB105001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Daily Exchange Rate Notification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/11/2016   CSAI            K.Uramori       Create          N/A
 * 09/08/2016   Hitachi         T.Tsuchida      Update          QC#14290
 * 2016/10/17   Hitachi         K.Kojima        Update          QC#14039
 * </pre>
 */
public class NFAB105001Constant {

    /** business_id : NFAB1000 */
    public static final String BUSINESS_ID = "NFAB105001";

    /** INTERFACE ID */
    static final String INTFC_ID = "NFAI0100";

    /** Message */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Message */
    public static final String NFAM0035E = "NFAM0035E";

    /** Insert Count */
    public static final int BULK_INSERT_CNT = 1000;

    /** Message */
    public static final String MSG_PARAM = "COA Combination Update";

    // START 2016/10/17 K.Kojima [QC#14039,ADD]
    /** SQL Column Name */
    public static final String COA_GL_CMBN_4_SEG_CD = "COA_GL_CMBN_4_SEG_CD";

    /** SQL Column Name */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** SQL Column Name */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** SQL Column Name */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** SQL Column Name */
    public static final String COA_CH_CD = "COA_CH_CD";
    // END 2016/10/17 K.Kojima [QC#14039,ADD]
}
