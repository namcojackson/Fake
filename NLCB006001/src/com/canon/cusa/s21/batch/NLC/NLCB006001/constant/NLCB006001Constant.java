/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB006001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NLCB0060:Tech PI Result Printing Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/28/2016   CITS            Y.Fujii         Create          R308
 * 05/24/2018   CITS            K.Kameoka       Create          QC#10572
 * 09/20/2018   CITS            K.Ogino         Update          QC#28192
 * 10/16/2018   CITS            M.Naito         Update          QC#28770
 *</pre>
 */
public class NLCB006001Constant {

    // =================================================
    // Const
    // =================================================
    /** BUSHINESS_ID. */
    public static final String BUSINESS_ID = "NLCB006001";

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
    public static final String DATE_TIME_PRINT_FORMAT = "MM/dd/yyyy";

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
    public static final String RPT_ID_NLCF0020 = "NLCF0020";

    /** GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** RPT_PRINT_RQST_SQ. */
    public static final String RPT_PRINT_RQST_SQ = "RPT_PRINT_RQST_SQ";

    /** INTL_LANG_VAL_COL_NM. */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** RTL_WH_CD. */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** PHYS_INVTY_CTRL_NM. */
    public static final String PHYS_INVTY_CTRL_NM = "PHYS_INVTY_CTRL_NM";

    /** ADJ_SUBMIT_TS. */
    public static final String ADJ_SUBMIT_TS = "ADJ_SUBMIT_TS";

    /** HAIFUN */
    public static final String HAIFUN = "-";

    /** SPACE */
    public static final String SPACE = " ";


    // =================================================
    // Message Const
    // =================================================
    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** Failed to get @. */
    public static final String NPAM1328E = "NPAM1328E";

    /** Failed to update @ table. @ is @. */
    public static final String NPAM1003E = "NPAM1003E";

    /** It failed to insert the [@]. PK [@]. */
    public static final String NPAM1499E = "NPAM1499E";

    /** Failed to remove. [@] */
    public static final String NPAM1342E = "NPAM1342E";


    // =================================================
    // Var Char / Number Const Name
    // =================================================
    /** Number Const Name : NLCF0020_PURGE_DT */
    public static final String CONST_NLCF0020_PURGE_DT = "NLCF0020_PURGE_DT";

    /** Number Const Name : NLCF0020_LINE_NUM_OF_1ST_PAGE */
    public static final String CONST_NLCF0020_LINE_NUM_OF_1ST_PAGE = "NLCF0020_LINE_NUM_OF_1ST_PAGE";

    /** Number Const Name : NLCF0020_LINE_NUM_OF_2ND_PAGE */
    public static final String CONST_NLCF0020_LINE_NUM_OF_2ND_PAGE = "NLCF0020_LINE_NUM_OF_2ND_PAGE";

    /** Number Const Name : NLCF0020_NUM_OF_DESC_LTR */
    public static final String CONST_NLCF0020_NUM_OF_DESC_LTR = "NLCF0020_NUM_OF_DESC_LTR";

    // QC#10572 Start.
    /** VAR_CHAR_CONST Key : PHYS_INVTY_STS_CD */
    public static final String CONST_NLCB0060_PRINT_PHYS_INVTY_STS_CD = "NLCB0060_PHYS_INVTY_STS_CD";
    // QC#10572 End.

    // QC#28192
    /** VAR_CHAR_CONST Key : PHYS_INVTY_STS_CD */
    public static final String CONST_NLCB0060_PHYS_INVTY_NO_ADJ_STS_CD = "PHYS_INVTY_NO_ADJ_STS_CD";

    /** VAR_CHAR_CONST Key : PHYS_INVTY_NO_ADJ_TXT */
    public static final String CONST_NLCB0060_PHYS_INVTY_NO_ADJ_TXT = "PHYS_INVTY_NO_ADJ_TXT";

    // START 2018/10/16 M.Naito [QC#28770, ADD]
    /** VAR_CHAR_CONST Key : NLCB0060_PHYS_INVTY_CNT_STS_CD */
    public static final String CONST_NLCB0060_PRINT_PHYS_INVTY_CNT_STS_CD = "NLCB0060_PHYS_INVTY_CNT_STS_CD";
    // END 2018/10/16 M.Naito [QC#28770, ADD]
    // =================================================
    // DB_PARAM Const
    // =================================================
    /** DB_PARAM_GLBL_CMPY_CD : glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM_PROC_PGM_ID : procPgmId */
    public static final String DB_PARAM_PROC_PGM_ID = "procPgmId";

    /** DB_PARAM_PRNT_PURGE_TS : purgeTs */
    public static final String DB_PARAM_PRNT_PURGE_TS = "purgeTs";

    /** DB_PARAM_PROC_DT : procDt */
    public static final String DB_PARAM_PROC_DT = "procDt";

    /** DB_PARAM_LOC_TP_CD : locTpCd */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /** DB_PARAM_ADJ_SUBMT_TS : adjSubmtTs */
    public static final String DB_PARAM_ADJ_SUBMT_TS = "adjSubmtTs";

    /** DB_PARAM_PHYS_INVTY_STS_CD : physInvtyStsCd */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD = "physInvtyStsCd";

    // QC#10572 Start.
    /** DB_PARAM_PHYS_INVTY_STS_CD : physInvtyStsCd */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD_LIST = "physInvtyStsCdList";
    // QC#10572 End.

    // QC#28192
    /** DB_PARAM_PHYS_INVTY_STS_CD : physInvtyNoAdjStsCd */
    public static final String DB_PARAM_PHYS_INVTY_NO_ADJ_STS_CD_LIST = "physInvtyNoAdjStsCdList";

    /** DB_PARAM_PHYS_INVTY_STS_CD : physInvtyNoAdjTxt */
    public static final String DB_PARAM_PHYS_INVTY_NO_ADJ_TXT = "physInvtyNoAdjTxt";

    /** DB_PARAM_PHYS_INVTY_CNT_STS_CD_LIST : physInvtyCntStsCdList */
    public static final String DB_PARAM_PHYS_INVTY_CNT_STS_CD_LIST = "physInvtyCntStsCdList";

    /** DB_PARAM_GNRN_TP_CD_LIST : gnrnTpCdList */
    public static final String DB_PARAM_GNRN_TP_CD_LIST = "gnrnTpCdList";

    /** DB_PARAM_ORG_STRU_TP_CD : orgStruTpCd */
    public static final String DB_PARAM_ORG_STRU_TP_CD = "orgStruTpCd";

    /** DB_PARAM_BIZ_AREA_ORG_CD : bizAreaOrgCd */
    public static final String DB_PARAM_BIZ_AREA_ORG_CD = "bizAreaOrgCd";

    /** DB_PARAM_BR_FLG : brFlg */
    public static final String DB_PARAM_BR_FLG = "brFlg";

    /** DB_PARAM_RG_FLG : rgFlg */
    public static final String DB_PARAM_RG_FLG = "rgFlg";

    /** DB_PARAM_PSN_CD : psnCd */
    public static final String DB_PARAM_PSN_CD = "psnCd";



    // =================================================
    // DB_COLUMN Const
    // =================================================
    /** DB Column Name: RPT_ID. */
    public static final String DB_COLUMN_RPT_ID = "RPT_ID";

    /** DB Column Name: DS_BIZ_PROC_LOG_PK. */
    public static final String DB_COLUMN_DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** DB Column Name: DS_BIZ_LAST_UPD_TS. */
    public static final String DB_COLUMN_DS_BIZ_LAST_UPD_TS = "DS_BIZ_LAST_UPD_TS";

    /** DB Column Name: PIC_WH_CD. */
    public static final String DB_COLUMN_PIC_WH_CD = "PIC_WH_CD";

    /** DB Column Name: PIC_RTL_WH_CD. */
    public static final String DB_COLUMN_PIC_RTL_WH_CD = "PIC_RTL_WH_CD";

    /** DB Column Name: PIC_RTL_SWH_CD. */
    public static final String DB_COLUMN_PIC_RTL_SWH_CD = "PIC_RTL_SWH_CD";

    /** DB Column Name: PIC_TECH_TOC_CD. */
    public static final String DB_COLUMN_PIC_TECH_TOC_CD = "PIC_TECH_TOC_CD";

    /** DB Column Name: PIC_PHYS_INVTY_CTRL_NM. */
    public static final String DB_COLUMN_PIC_PHYS_INVTY_CTRL_NM = "PIC_PHYS_INVTY_CTRL_NM";

    /** DB Column Name: PIH_INVTY_AVAL_QTY. */
    public static final String DB_COLUMN_PIH_INVTY_AVAL_QTY = "PIH_INVTY_AVAL_QTY";

    /** DB Column Name: PIH_LTST_CNT_INP_QTY. */
    public static final String DB_COLUMN_PIH_LTST_CNT_INP_QTY = "PIH_LTST_CNT_INP_QTY";

    /** DB Column Name: PIC_ADJ_NET_AMT. */
    public static final String DB_COLUMN_PIC_ADJ_NET_AMT = "PIC_ADJ_NET_AMT";

    /** DB Column Name: PIH_ADJ_VAR_FLG. */
    public static final String DB_COLUMN_PIH_ADJ_VAR_FLG = "PIH_ADJ_VAR_FLG";

    /** DB Column Name: PIC_ADJ_SUBMT_TS. */
    public static final String DB_COLUMN_PIC_ADJ_SUBMT_TS = "PIC_ADJ_SUBMT_TS";

    /** DB Column Name: PIC_ADJ_GRS_AMT. */
    public static final String DB_COLUMN_PIC_ADJ_GRS_AMT = "PIC_ADJ_GRS_AMT";

    /** DB Column Name: PIH_MDSE_CD. */
    public static final String DB_COLUMN_PIH_MDSE_CD = "PIH_MDSE_CD";

    /** DB Column Name: DMI_MDSE_DESC_SHORT_TXT. */
    public static final String DB_COLUMN_DMI_MDSE_DESC_SHORT_TXT = "DMI_MDSE_DESC_SHORT_TXT";

    /** DB Column Name: PIH_ADJ_VAR_QTY. */
    public static final String DB_COLUMN_PIH_ADJ_VAR_QTY = "PIH_ADJ_VAR_QTY";

    /** DB Column Name: PIH_ADJ_VAR_AMT. */
    public static final String DB_COLUMN_PIH_ADJ_VAR_AMT = "PIH_ADJ_VAR_AMT";

    /** DB Column Name: OFA_PSN_CD. */
    public static final String DB_COLUMN_OFA_PSN_CD = "OFA_PSN_CD";

    /** DB Column Name: OFA_TOC_CD. */
    public static final String DB_COLUMN_OFA_TOC_CD = "OFA_TOC_CD";

    /** DB Column Name: ORG_TIER. */
    public static final String DB_COLUMN_ORG_TIER = "ORG_TIER";

    /** DB Column Name: ORG_ORG_CD. */
    public static final String DB_COLUMN_ORG_ORG_CD = "ORG_ORG_CD";

    /** DB Column Name: ORG_ORG_NM. */
    public static final String DB_COLUMN_ORG_ORG_NM = "ORG_ORG_NM";

    /** DB Column Name: ORG_RG_FLG. */
    public static final String DB_COLUMN_ORG_RG_FLG = "ORG_RG_FLG";

    /** DB Column Name: ORG_BR_FLG. */
    public static final String DB_COLUMN_ORG_BR_FLG = "ORG_BR_FLG";

    /** DB Column Name: TECH_PI_RSLT_RPT_WRK_PK. */
    public static final String DB_COLUMN_TECH_PI_RSLT_RPT_WRK_PK = "TECH_PI_RSLT_RPT_WRK_PK";



}
