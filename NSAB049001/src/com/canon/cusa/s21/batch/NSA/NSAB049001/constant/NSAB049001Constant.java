/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB049001.constant;

/**
 * <pre>
 * Batch Program Class for CUSA Retail Meter Reads Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2016   CITS            T.Wada          Create          None
 * 02/08/2017   Hitachi         K.Kojima        Update          QC#16600
 * 2017/05/26   Hitachi         K.Kojima        Update          QC#18710
 * 2017/06/06   Hitachi         K.Kitachi       Update          QC#18342
 *</pre>
 */
public class NSAB049001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAB0490";

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /**
     * Error for Sr#@ Item#@ -> Invalid Serial Number. CSA Contract
     * Administration needs to do the serial number investigation and
     * update the serial number in the contract if necessary.
     */
    public static final String NSAM0524E = "NSAM0524E";

    /** Error for Sr#@ Item#@ -> Number of Ross meters for this meter date is: @ */
    public static final String NSAM0525E = "NSAM0525E";

    /** Error for Sr#@ Item#@ -> Not found in Install Base. */
    public static final String NSAM0526E = "NSAM0526E";

    /** Error for Sr#@ Item#@ -> There are more CSA meters than Ross meters. */
    public static final String NSAM0527E = "NSAM0527E";

    /** Error for Sr#@ Item#@ -> BW (Hard Read) meter not found in Install Base.  */
    public static final String NSAM0528E = "NSAM0528E";

    /** Error for Sr#@ Item#@ -> Total (Hard Read) meter not found in Install Base. */
    public static final String NSAM0529E = "NSAM0529E";

    /**
     * Error for Sr#@ Item#@ -> For the same date, if there is Total Hard
     * Read already exist in the CSA meter history for a serial number
     * with Reading Type “B”, the meter should not be imported.
     */
    public static final String NSAM0530E = "NSAM0530E";

    /**
     * Error for Sr#@ Item#@ -> Invalid Meter Count Error. NAD Billing
     * Administration needs to do the investigation and update the
     * meter reading if necessary.
     */
    public static final String NSAM0531E = "NSAM0531E";

    /** An error occurred in API.  <APIID: [@], Error Code: [@], Data Key: [@]>  */
    public static final String NSAM0003E = "NSAM0003E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Sales Date */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** Date time format. */
    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy hh:mm";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Batch ID */
    public static final String BATCH_ID = "NSAB049001";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NSAB0490M001";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NSAB0490";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_DATE = "errDate";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_MSG = "message";

    /** DB COLUMN :GLBL_CMPY_CD **/
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN :SER_NUM **/
    public static final String SER_NUM = "SER_NUM";

    /** DB COLUMN :MDSE_CD **/
    public static final String MDSE_CD = "MDSE_CD";

    /** DB COLUMN :BLLG_MSTR_REVN_CD **/
    public static final String BLLG_MSTR_REVN_CD = "BLLG_MSTR_REVN_CD";

    /** DB COLUMN :SVC_DLR_CD **/
    public static final String SVC_DLR_CD = "SVC_DLR_CD";

    /** DB COLUMN :MACH_MSTR_PK **/
    public static final String MACH_MSTR_PK = "MACH_MSTR_PK";

    /** DB COLUMN :PHYS_MTR_NUM **/
    public static final String PHYS_MTR_NUM = "PHYS_MTR_NUM";

    /** DB COLUMN :PHYS_MTR_NM **/
    public static final String PHYS_MTR_NM = "PHYS_MTR_NM";

    /** DB COLUMN :PHYS_MTR_READ_PK **/
    public static final String PHYS_MTR_READ_PK = "PHYS_MTR_READ_PK";

    /** DB COLUMN :MTR_DT **/
    public static final String MTR_DT = "MTR_DT";

    /** DB COLUMN :MTR_CNT **/
    public static final String MTR_CNT = "MTR_CNT";

    /** DB COLUMN :MTR_TEST_CNT **/
    public static final String MTR_TEST_CNT = "MTR_TEST_CNT";

    /** DB COLUMN :MRC_CRAT_DT **/
    public static final String MRC_CRAT_DT = "MRC_CRAT_DT";

    /** DB COLUMN :SVC_PHYS_MTR_PK **/
    public static final String SVC_PHYS_MTR_PK = "SVC_PHYS_MTR_PK";

    /** DB COLUMN :ROSS_INTFC_MTR_READ_PK **/
    public static final String ROSS_INTFC_MTR_READ_PK = "ROSS_INTFC_MTR_READ_PK";

    /** DB COLUMN :ROSS_INTFC_MTR_PROC_CD **/
    public static final String ROSS_INTFC_MTR_PROC_CD = "ROSS_INTFC_MTR_PROC_CD";

    /** DB COLUMN :ROSS_INTFC_MTR_PROC_TXT **/
    public static final String ROSS_INTFC_MTR_PROC_TXT = "ROSS_INTFC_MTR_PROC_TXT";

    /** DB COLUMN :MTR_READ_SRC_TP_CD **/
    public static final String MTR_READ_SRC_TP_CD = "MTR_READ_SRC_TP_CD";

    /** DB COLUMN :DS_MTR_READ_TP_CD **/
    public static final String DS_MTR_READ_TP_CD = "DS_MTR_READ_TP_CD";

    /** DB COLUMN :BLLG_REVN_CD **/
    public static final String BLLG_REVN_CD = "BLLG_REVN_CD";

    /** DB COLUMN :MTR_READ_DT **/
    public static final String MTR_READ_DT = "MTR_READ_DT";

    /** DB COLUMN :RGTN_MTR_DT **/
    public static final String RGTN_MTR_DT = "RGTN_MTR_DT";

    /** DB COLUMN :MTR_LB_CD **/
    public static final String MTR_LB_CD = "MTR_LB_CD";

    /** DB COLUMN :READ_MTR_CNT **/
    public static final String READ_MTR_CNT = "READ_MTR_CNT";

    /** DB COLUMN :TEST_MTR_CNT **/
    public static final String TEST_MTR_CNT = "TEST_MTR_CNT";

    /** DB COLUMN :EST_FLG **/
    public static final String EST_FLG = "EST_FLG";

    /** DB COLUMN :MRC_MOD_DT **/
    public static final String MRC_MOD_DT = "MRC_MOD_DT";

    /** DB COLUMN :TOT_MTR_FLG **/
    public static final String TOT_MTR_FLG = "TOT_MTR_FLG";

    /** DB COLUMN :BW_MTR_FLG **/
    public static final String BW_MTR_FLG = "BW_MTR_FLG";

    /** DB COLUMN :COLOR_MTR_FLG **/
    public static final String COLOR_MTR_FLG = "COLOR_MTR_FLG";

    /** DB COLUMN :SVC_MACH_MSTR_PK **/
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** DB COLUMN :DS_MTR_PROC_STS_CD **/
    public static final String DS_MTR_PROC_STS_CD = "DS_MTR_PROC_STS_CD";

    /** DB COLUMN :DS_MSG_TXT **/
    public static final String DS_MSG_TXT = "DS_MSG_TXT";

    /** DB COLUMN : DS_MTR_INTFC_PK */
    public static final String DS_MTR_INTFC_PK = "DS_MTR_INTFC_PK";

    /**
     * ROSS_INTFC_MTR_PROC_CD_NORMAL
     */
    public static final String ROSS_INTFC_MTR_PROC_CD_NORMAL = "N";

    /**
     * ROSS_INTFC_MTR_PROC_CD_PROCESSED
     */
    public static final String ROSS_INTFC_MTR_PROC_CD_PROCESSED = "P";

    /**
     * ROSS_INTFC_MTR_PROC_CD_WARN
     */
    public static final String ROSS_INTFC_MTR_PROC_CD_WARN = "W";

    /**
     * ROSS_INTFC_MTR_PROC_CD_ERR
     */
    public static final String ROSS_INTFC_MTR_PROC_CD_ERR = "E";

    /**
     * DS_MTR_PROC_STS_CD_ERR
     */
    public static final String DS_MTR_PROC_STS_CD_ERR = "9";

    /**
     * DS_MTR_PROC_STS_CD_WARN
     */
    public static final String DS_MTR_PROC_STS_CD_WARN = "0";

    /**
     * MAX_PHYS_MTR_CNT
     */
    public static final int MAX_PHYS_MTR_CNT = 3;

    /** DEFAULT_FETCH_SIZE */
    public static final String DEFAULT_FETCH_SIZE = "1000";

    /** createPhysMtrRead API ID */
    public static final String NSZC051001 = "NSZC051001";

    /** Return Code Value for Validation Check Error*/
    public static final int VAL_CHECK_ERR = 1;

    /** Return Code Value for API Execution Error */
    public static final int API_EXEC_ERR = 3;

    // START 2017/05/26 K.Kojima [QC#18710,ADD]
    /** ROSS_INTFC_MTR_PROC_TXT Column Length */
    public static final int ROSS_INTFC_MTR_PROC_TXT_LENGTH = 200;
    // END 2017/05/26 K.Kojima [QC#18710,ADD]

    // START 2017/06/06 K.Kitachi [QC#18342, ADD]
    /** Format System Time stamp **/
    public static final String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";
    // END 2017/06/06 K.Kitachi [QC#18342, ADD]
}
