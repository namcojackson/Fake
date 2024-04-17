/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB118001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NLCB118001:Tech PI Count Printing from Tech Mobile
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2018   CITS            K.Fukumura      Create          QC#10572
 *</pre>
 */
public class NLCB118001Constant {

    // =================================================
    // Const
    // =================================================
    /** BUSHINESS_ID. */
    public static final String BUSINESS_ID = "NLCB118001";

    /** ONE_BLANK . */
    public static final String ONE_BLANK = " ";

    /** COMMA. */
    public static final String COMMA = ",";

    /** SLASH. */
    public static final String SLASH = "/";

    /** PERIOD. */
    public static final String PERIOD = ".";

    /** HYPHEN. */
    public static final String HYPHEN = "-";

    /** DEF_TS_ZERO . */
    public static final String DEF_TS_ZERO = "00000000000000";

    /** Date Format */
    public static final String DATE_DATA_FORMAT = "yyyyMMdd";

    /** Date Format */
    public static final String DATE_PRINT_FORMAT = "dd-MMM-yyyy";

    /** Time Format */
    public static final String TIME_PRINT_FORMAT = "HH:mm:ss";

    /** Date Format */
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    /** Date Format */
    public static final String DATE_TIME_FORMAT_17 = "yyyyMMddHHmmssSSS";

    /** DEFAULT_AMOUNT_SACALE . */
    public static final int DEFAULT_AMOUNT_SACALE = 2;

    /** PERCENT_AMOUNT_SACALE . */
    public static final int PERCENT_AMOUNT_SACALE = 1;

    /** DETAIL_UOM_VALUE. */
    public static final String DETAIL_UOM_VALUE = "EA";

    /** ONE_HUNDRED. */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    /** TWO. */
    public static final BigDecimal TWO = new BigDecimal("2");

    /** TDATE_BEGININDEXWO. */
    public static final int DATE_BEGININDEX = 0;

    /** DATE_ENDINDEX. */
    public static final int DATE_ENDINDEX = 8;

    /** REPORT_LOGICAL_PRINT_KEY_DELIMITER. */
    public static final String REPORT_LOGICAL_PRINT_KEY_DELIMITER = ":";

    // =================================================
    // EIP Parameter
    // =================================================
    /** RPT_ID NLCF0020:Tech PI Result Report EIP. */
    public static final String RPT_ID_NLCF0030 = "NLCF0030";

    /** GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** RPT_PRINT_RQST_SQ. */
    public static final String RPT_PRINT_RQST_SQ = "RPT_PRINT_RQST_SQ";

    /** INTL_LANG_VAL_COL_NM. */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** DEF_SRC_LOC_CD. */
    public static final String DEF_SRC_LOC_CD = "DEF_SRC_LOC_CD";

    /** TECH_CD. */
    public static final String TECH_CD = "TECH_CD";

    /** USER_ID. */
    public static final String USER_ID = "USER_ID";

    /** PHYS_INVTY_CTRL_NM. */
    public static final String PHYS_INVTY_CTRL_NM = "PHYS_INVTY_CTRL_NM";

    /** HAIFUN */
    public static final String HAIFUN = "-";

    /** SPACE */
    public static final String SPACE = " ";


    // =================================================
    // Message Const
    // =================================================
    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** It failed to insert the [@]. PK [@]. */
    public static final String NPAM1499E = "NPAM1499E";

    // =================================================
    // DB_PARAM Const
    // =================================================
    /** DB_PARAM_GLBL_CMPY_CD : glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    // =================================================
    // DB_COLUMN Const
    // =================================================
    /** DB Column Name: PHYS_INVTY_CTRL_PK. */
    public static final String DB_COLUMN_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /** DB Column Name: USER_ID. */
    public static final String DB_COLUMN_USER_ID = "USER_ID";

    /** DB Column Name: REQ_TIME. */
    public static final String DB_COLUMN_REQ_TIME = "REQ_TIME";

    /** DB Column Name: GLBL_CMPY_CD. */
    public static final String DB_COLUMN_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column Name: DEF_SRC_LOC_CD. */
    public static final String DB_COLUMN_DEF_SRC_LOC_CD = "DEF_SRC_LOC_CD";

    /** DB Column Name: RTL_WH_NM. */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /** DB Column Name: TECH_TOC_CD. */
    public static final String DB_COLUMN_TECH_TOC_CD = "TECH_TOC_CD";

    /** DB Column Name: TECH_NM. */
    public static final String DB_COLUMN_TECH_NM = "TECH_NM";

    /** DB Column Name: HR_SUPV_ID. */
    public static final String DB_COLUMN_HR_SUPV_ID = "HR_SUPV_ID";

    /** DB Column Name: HR_SUPV_NM. */
    public static final String DB_COLUMN_HR_SUPV_NM = "HR_SUPV_NM";

    /** DB Column Name: PHYS_INVTY_CTRL_NM. */
    public static final String DB_COLUMN_PHYS_INVTY_CTRL_NM = "PHYS_INVTY_CTRL_NM";

    /** DB Column Name: PHYS_INVTY_DT. */
    public static final String DB_COLUMN_PHYS_INVTY_DT = "PHYS_INVTY_DT";

    /** DB Column Name: RTL_WH_CD. */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column Name: RTL_SWH_CD. */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /** DB Column Name: MDSE_CD. */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /** DB Column Name: MDSE_DESC_SHORT_TXT. */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** DB Column Name: CNT_INP_QTY. */
    public static final String DB_COLUMN_CNT_INP_QTY = "CNT_INP_QTY";
}
