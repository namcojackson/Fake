/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB312001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * NLAB312001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/19/2015   Hitachi         J.Kim           Create          N/A
 * 08/25/2016   Hitachi         K.Kasai         Update          CSA QC#13805
 * 05/23/2017   Hitachi         K.Kitachi       Update          QC#18374
 * 2022/02/14   Hitachi         K.Kitachi       Update          QC#59695
 * 2022/03/09   Hitachi         K.Kitachi       Update          QC#59695
 * </pre>
 */
public class NLAB312001Constant {

    /** A value is not set in the required field. Field Name: [@] */
    public static final String NLAM1118E = "NLAM1118E";

    /** It failed to get the [@]. */
    public static final String NLAM1285E = "NLAM1285E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    // START 2022/02/14 K.Kitachi [QC#59695, ADD]
    /** Error Message Text: Variable Character Constant */
    public static final String MSG_TXT_VAR_CHAR_CONST = "Variable Character Constant";
    // END 2022/02/14 K.Kitachi [QC#59695, ADD]

    // START 2022/03/09 K.Kitachi [QC#59695, ADD]
    /** Error Message Text: Process Date */
    public static final String MSG_TXT_PROCESS_DATE = "Process Date";
    // END 2022/03/09 K.Kitachi [QC#59695, ADD]

    /** Column name : TECH_TOC_CD */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    /** Column name : RQST_TECH_TOC_CD */
    public static final String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /** Column name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name : MDSE_NM */
    public static final String MDSE_NM = "MDSE_NM";

    /** Column name : SUPD_TO_MDSE_CD */
    public static final String SUPD_TO_MDSE_CD = "SUPD_TO_MDSE_CD";

    // START 2016/08/25 [QC#13805, MOD]
//    /** Column name : AVG_USG_NUM */
//    public static final String AVG_USG_NUM = "AVG_USG_NUM";

    /** Column name : AVG_USG_QTY */
    public static final String AVG_USG_QTY = "AVG_USG_QTY";
    // END 2016/08/25 [QC#13805, MOD]

    /** Column name : SITE_SRVY_REQ_FLG */
    public static final String SITE_SRVY_REQ_FLG = "SITE_SRVY_REQ_FLG";

    /** Column name : ORD_QTY */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** Column name : TECH_MDSE_LIST_WRK_NUM */
    public static final String TECH_MDSE_LIST_WRK_NUM = "TECH_MDSE_LIST_WRK_NUM";

    /** Column name : TECH_MDSE_LIST_WRK_PK */
    public static final String TECH_MDSE_LIST_WRK_PK = "TECH_MDSE_LIST_WRK_PK";

    /** Column name : TECH_MDSE_LIST_WRK_DT */
    public static final String TECH_MDSE_LIST_WRK_DT = "TECH_MDSE_LIST_WRK_DT";

    /** Column name : TECH_TOC_COUNT */
    public static final String TECH_TOC_COUNT = "TECH_TOC_COUNT";

    /** Column name : TTC_ROW_NUM */
    public static final String TTC_ROW_NUM = "TTC_ROW_NUM";

    // START 2022/02/14 K.Kitachi [QC#59695, ADD]
    /** Column name : DEL_FLG */
    public static final String DEL_FLG = "DEL_FLG";

    /** Const Key NLAB3120_ALL_SEND_FLG */
    public static final String CONST_KEY_ALL_SEND_FLG = "NLAB3120_ALL_SEND_FLG";
    // END 2022/02/14 K.Kitachi [QC#59695, ADD]

    /** ROW_NUM */
    public static final BigDecimal ROW_NUM = new BigDecimal(500);

    /** ROW NUMBER_1 */
    public static final String ROW_NUM_1 = "1";

    /** MAX COUNT NUMBER : */
    public static final int MAX_NUMBER = 1000;

    /** SQLID(TECH_MDSE_LIST_WRK) */
    public static final String SQLID = "001";

    // START 2017/05/23 K.Kitachi [QC#18374, ADD]
    /** SORT_NUM_1 */
    public static final String SORT_NUM_1 = "1";

    /** SORT_NUM_2 */
    public static final String SORT_NUM_2 = "2";

    /** SORT_NUM_3 */
    public static final String SORT_NUM_3 = "3";

    /** SORT_NUM_4 */
    public static final String SORT_NUM_4 = "4";
    // END 2017/05/23 K.Kitachi [QC#18374, ADD]
}
