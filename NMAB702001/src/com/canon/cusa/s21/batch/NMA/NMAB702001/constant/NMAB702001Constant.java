/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB702001.constant;

/**
 * <pre>
 * Mass Upload Customer Regstration
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         O.Okuma         Create          N/A
 * </pre>
 */
public class NMAB702001Constant {

    /** BUSINESS_ID (NMAB7020) */
    public static final String BUSINESS_ID = "NMAB7020";

    /** PROGRAM_ID(NMAB7020) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** UploadCsvId : NMA7020001 */
    public static final String UPLOAD_CSV_ID_APPEND = "NMA7020001";

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /**
     * The value for [*Date From(YYYYMMDD)] must be smaller than [Date
     * To(YYYYMMDD)].
     */
    public static final String NMAM8458E = "NMAM8458E";

    /** [@] is not exists in master. */
    public static final String NMAM8454E = "NMAM8454E";

    /** The specified format of "@" is incorrect. It must be Y/N. */
    public static final String NMAM8455E = "NMAM8455E";

    /** Upload CSV Request PK was not found at Upload Work Table. */
    public static final String NMAM8456E = "NMAM8456E";

    /** Please confirm the error details. */
    public static final String NMAM8457E = "NMAM8457E";

    /** The (@) was (@) . ResultCount = (@) */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Column Name : UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** Column Name : PRC_CATG_NM */
    public static final String PRC_CATG_NM = "PRC_CATG_NM";

    /** Column Name : DS_ACCT_NUM */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** Column Name : REQ_FLG */
    public static final String REQ_FLG = "REQ_FLG";

    /** Column Name : EFF_FROM_DT */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /** Column Name : EFF_THRU_DT */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";

    /** Column Name : DEF_PRC_LIST_FLG */
    public static final String DEF_PRC_LIST_FLG = "DEF_PRC_LIST_FLG";

    /** CSV Column Name : PRC_CATG_NM */
    public static final String CSV_PRC_CATG_NM = "*Price List Name";

    /** CSV Column Name : DS_ACCT_NUM */
    public static final String CSV_DS_ACCT_NUM = "*Account# Value (1)";

    /** CSV Column Name : REQ_FLG */
    public static final String CSV_REQ_FLG = "Required(Y or N)";

    /** CSV Column Name : EFF_FROM_DT */
    public static final String CSV_EFF_FROM_DT = "*Date From(YYYYMMDD)";

    /** CSV Column Name : DEF_PRC_LIST_FLG */
    public static final String CSV_DEF_PRC_LIST_FLG = "Default Price List(Y or N)";
}
