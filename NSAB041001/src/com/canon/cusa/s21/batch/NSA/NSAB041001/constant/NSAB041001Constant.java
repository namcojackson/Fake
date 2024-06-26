/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB041001.constant;

/**
 * <pre>
 * CFS Invoice Interface
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         O.Okuma         Create          N/A
 * 2016/09/27   Hitachi         N.Arai          Update          QC#12670
 * 2017/02/24   Hitachi         Y.Takeno        Update          QC#17569
 * 2017/07/24   Hitachi         K.Kojima        Update          QC#20090
 * </pre>
 */
public class NSAB041001Constant {

    /** BUSINESS_ID (NSAB0410) */
    public static final String BUSINESS_ID = "NSAB0410";

    /** PROGRAM_ID(NSAB0410) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** @ cannot be obtained. */
    public static final String NSZM0392E = "NSZM0392E";

    /** It failed to register @ Table.[@] */
    public static final String NSAM0469E = "NSAM0469E";

    /** It failed to update @ Table.[@] */
    public static final String NSAM0470E = "NSAM0470E";

    /** Table Name : CFS_MTR_READ */
    public static final String TBL_NM_CFS_MTR_READ = "CFS_MTR_READ";

    /** Table Name : SVC_INV */
    public static final String TBL_NM_SVC_INV = "SVC_INV";

    /** Column Name : SVC_INV_PK */
    public static final String SVC_INV_PK = "SVC_INV_PK";

    /** Column Name : DS_ACCT_DLR_CD */
    public static final String DS_ACCT_DLR_CD = "DS_ACCT_DLR_CD";

    /** Column Name : DS_CONTR_PK */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** Column Name : DS_CONTR_NUM */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** Column Name : CFS_LEASE_NUM */
    public static final String CFS_LEASE_NUM = "CFS_LEASE_NUM";

    /** Column Name : SVC_INV_NUM */
    public static final String SVC_INV_NUM = "SVC_INV_NUM";

    /** Column Name : SVC_INV_LINE_PK */
    public static final String SVC_INV_LINE_PK = "SVC_INV_LINE_PK";

    /** Column Name : BLLG_PER_FROM_DT */
    public static final String BLLG_PER_FROM_DT = "BLLG_PER_FROM_DT";

    /** Column Name : BLLG_PER_THRU_DT */
    public static final String BLLG_PER_THRU_DT = "BLLG_PER_THRU_DT";

    /** Column Name : SVC_MACH_MSTR_PK */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Column Name : SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** Column Name : TOT_COPY_QTY */
    public static final String TOT_COPY_QTY = "TOT_COPY_QTY";

    /** Column Name : PREV_TOT_COPY_QTY */
    public static final String PREV_TOT_COPY_QTY = "PREV_TOT_COPY_QTY";

    /** Column Name : TEST_COPY_QTY */
    public static final String TEST_COPY_QTY = "TEST_COPY_QTY";

    /** Column Name : MTR_READ_DT */
    public static final String MTR_READ_DT = "MTR_READ_DT";

    /** Column Name : DS_CONTR_CATG_CD */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";

    /** Column Name : SVC_INV_LINE_MTR_PK */
    public static final String SVC_INV_LINE_MTR_PK = "SVC_INV_LINE_MTR_PK";

    /** Column Name : DS_CONTR_BLLG_MTR_PK */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";

    /** Column Name : MTR_LB_CD */
    public static final String MTR_LB_CD = "MTR_LB_CD";

    /** Column Name : DS_CONTR_CATG_ABBR_NM */
    public static final String DS_CONTR_CATG_ABBR_NM = "DS_CONTR_CATG_ABBR_NM";

    // START 2017/02/24 [QC#17569, ADD]
    /** Column Name : SVC_PHYS_MTR_READ_GRP_SQ */
    public static final String SVC_PHYS_MTR_READ_GRP_SQ = "SVC_PHYS_MTR_READ_GRP_SQ";
    // END   2017/02/24 [QC#17569, ADD]

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM  = "FROM0003";

    /** Mail Group KEY (From) */
    public static final String MAIL_GROUP_KEY_FROM  = "NS";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NSAB0410M001";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";
    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

// START 2016/09/27 N.Arai [QC#12670, ADD]
    /** CFS_PROC_CPLT_STS_CD(COMPLETED) : CFS_BAT_PROC_STS_CD */
    public static final String CFS_PROC_CPLT_STS_CD = "CFS_PROC_CPLT_STS_CD";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Error Msg : Sales date cannot be obtained.
     */
    public static final String NSAM0178E = "NSAM0178E";
// END 2016/09/27 N.Arai [QC#12670, ADD]

    // START 2017/07/24 K.Kojima [QC#20090,ADD]
    /** VAR_CHAR_CONST : CSA_DEALER_CODE */
    public static final String CSA_DEALER_CODE = "CSA_DEALER_CODE";

    /** COMMA : , */
    public static final String COMMA = ",";
    // END 2017/07/24 K.Kojima [QC#20090,ADD]

}
