/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB121001.constant;

/**
 *<pre>
 * Account Territory Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB121001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert "@". */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Failed to delete "@". */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String DWH_TRGT_DT = "DWH_TRGT_DT";
    /** . */
    public static final String ACCT_TRTY_ROLE_ASG_PK = "ACCT_TRTY_ROLE_ASG_PK";
    /** . */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    /** . */
    public static final String LOC_NUM = "LOC_NUM";
    /** . */
    public static final String ORG_CD = "ORG_CD";
    /** . */
    public static final String ORG_NM = "ORG_NM";
    /** . */
    public static final String LINE_BIZ_ROLE_TP_CD = "LINE_BIZ_ROLE_TP_CD";
    /** . */
    public static final String LINE_BIZ_ROLE_TP_DESC_TXT = "LINE_BIZ_ROLE_TP_DESC_TXT";
    /** . */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";
    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    /** . */
    public static final String DS_ACCT_TP_CD = "DS_ACCT_TP_CD";
    /** . */
    public static final String DS_ACCT_TP_DESC_TXT = "DS_ACCT_TP_DESC_TXT";
    /** . */
    public static final String DS_ACCT_CLS_CD = "DS_ACCT_CLS_CD";
    /** . */
    public static final String DS_ACCT_CLS_DESC_TXT = "DS_ACCT_CLS_DESC_TXT";
    /** . */
    public static final String FIRST_DS_ACCT_GRP_TP_CD = "FIRST_DS_ACCT_GRP_TP_CD";
    /** . */
    public static final String FIRST_DS_ACCT_GRP_DESC_TXT = "FIRST_DS_ACCT_GRP_DESC_TXT";
    /** . */
    public static final String SCD_DS_ACCT_GRP_TP_CD = "SCD_DS_ACCT_GRP_TP_CD";
    /** . */
    public static final String SCD_DS_ACCT_GRP_DESC_TXT = "SCD_DS_ACCT_GRP_DESC_TXT";
    /** . */
    public static final String THIRD_DS_ACCT_GRP_TP_CD = "THIRD_DS_ACCT_GRP_TP_CD";
    /** . */
    public static final String THIRD_DS_ACCT_GRP_DESC_TXT = "THIRD_DS_ACCT_GRP_DESC_TXT";
    /** . */
    public static final String FRTH_DS_ACCT_GRP_TP_CD = "FRTH_DS_ACCT_GRP_TP_CD";
    /** . */
    public static final String FRTH_DS_ACCT_GRP_DESC_TXT = "FRTH_DS_ACCT_GRP_DESC_TXT";
    /** . */
    public static final String FIFTH_DS_ACCT_GRP_TP_CD = "FIFTH_DS_ACCT_GRP_TP_CD";
    /** . */
    public static final String FIFTH_DS_ACCT_GRP_DESC_TXT = "FIFTH_DS_ACCT_GRP_DESC_TXT";
    /** . */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
    /** . */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";
    /** . */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";
    /** . */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";
    /** . */
    public static final String CTY_ADDR = "CTY_ADDR";
    /** . */
    public static final String ST_CD = "ST_CD";
    /** . */
    public static final String PROV_NM = "PROV_NM";
    /** . */
    public static final String CNTY_NM = "CNTY_NM";
    /** . */
    public static final String POST_CD = "POST_CD";
    /** . */
    public static final String TRTY_GRP_TP_CD = "TRTY_GRP_TP_CD";
    /** . */
    public static final String ORG_RANK_NUM = "ORG_RANK_NUM";
    /** . */
    public static final String TOC_CD = "TOC_CD";
    /** . */
    public static final String TOC_NM = "TOC_NM";
    /** . */
    public static final String PSN_CD = "PSN_CD";
    /** . */
    public static final String PSN_NUM = "PSN_NUM";
    /** . */
    public static final String PSN_FIRST_NM = "PSN_FIRST_NM";
    /** . */
    public static final String PSN_LAST_NM = "PSN_LAST_NM";
    /** . */
    public static final String MAN_ENTRY_FLG = "MAN_ENTRY_FLG";
    /** . */
    public static final String TRTY_TP_CD = "TRTY_TP_CD";
    /** . */
    public static final String TRTY_TP_DESC_TXT = "TRTY_TP_DESC_TXT";
    /** . */
    public static final String ASG_TRTY_ATTRB_CD = "ASG_TRTY_ATTRB_CD";
    /** . */
    public static final String ASG_TRTY_ATTRB_DESC_TXT = "ASG_TRTY_ATTRB_DESC_TXT";
    /** . */
    public static final String NON_SLS_REP_FLG = "NON_SLS_REP_FLG";
    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";
    /** . */
    public static final String DS_CUST_SIC_CD = "DS_CUST_SIC_CD";

}
