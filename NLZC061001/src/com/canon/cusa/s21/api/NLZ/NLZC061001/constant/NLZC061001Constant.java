/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC061001.constant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 *<pre>
 * Tech-PI Approval to WF API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            K.Ogino           Create          Initial
 * 03/30/2016   CITS            K.Ogino           Update          QC#6060
 * 04/22/2016   CITS            K.Ogino           Update          QC#6724
 *</pre>
 */
public class NLZC061001Constant extends S21ApiCommonBase {

    /** WF_ID */
    public static final String WF_ID = "NLZC06100101";

    /** WF_BIZ_ID */
    public static final String WF_BIZ_ID = "NLZC06100101";
    /** WF_BIZ_ID_PI */
    public static final String WF_BIZ_ID_PI = "NLZC06100102";

    /** WF_BIZ_SCREEN_NAME */
    public static final String WF_BIZ_SCREEN_NAME = "Tech-PI Approval";

    /** Return Code : 0 (Normal) */
    public static final String RTRN_CD_NORMAL = "0";

    /** VAR_CHAR_CONST_NAME */
    public static final String VAR_CHAR_CONST_NAME = "TECH_PI_CANC_CNT_STS_CD";

    /** It failed to cancel workflow. */
    public static final String NWZM1756E = "NWZM1756E";

    /** An error occure in NPXC001001CreateApprovalHistory. */
    public static final String NPZM0212E = "NPZM0212E";

    /** Invalid value is set for the process mode. */
    public static final String NWBM0032E = "NWBM0032E";

    /** An error occure in Creation of Workflow Process. */
    public static final String NPZM0213E = "NPZM0213E";

    /** System error has occurred. */
    public static final String NFDM0008E = "NFDM0008E";

    /** Global Company Code is mandatory. */
    public static final String NDAM0013E = "NDAM0013E";

    /** Input Parameter Sales Date is mandatory field. */
    public static final String NMZM0011E = "NMZM0011E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** [Physical Inventory Number] parameter is required. */
    public static final String NLCM0155E = "NLCM0155E";

    /** [User Id] parameter is required. */
    public static final String NLCM0156E = "NLCM0156E";

    /** [Physical Inventory Control Name] parameter is required. */
    public static final String NLCM0157E = "NLCM0157E";

    /** [Physical Inventory Date] parameter is required. */
    public static final String NLCM0158E = "NLCM0158E";

    /** [Tech Toc Code] parameter is required. */
    public static final String NLCM0159E = "NLCM0159E";

    /** [Tech Name] parameter is required. */
    public static final String NLCM0160E = "NLCM0160E";

    /** TECH_PI_CANC_CNT_STS_CD doesn't exist in the VAR_CHAR_CONST. */
    public static final String NLCM0161E = "NLCM0161E";
    
    /** The code you entered cannot be found in the master.*/
    public static final String NLZM2451E = "NLZM2451E";

    /** CREATE_MODE */
    public static final String CREATE_MODE = "01";

    /** CANCEL_MODE */
    public static final String CANCEL_MODE = "09";

    /** CANCEL_TXT */
    public static final String CANCEL_TXT = "Cancelled by user";

    /** BIND_PARAM_GLBL_CMPY_CD */
    public static final String BIND_PARAM_S21_PSN_GCC = "glblCmpyCd01";

    /** BIND_PARAM_SALES_DATE */
    public static final String BIND_PARAM_SALES_DATE = "salesDate";

    /** BIND_PARAM_PSN_CD */
    public static final String BIND_PARAM_S21_PSN_PC = "psnCd01";

    /** BIND_PARAM_GLBL_CMPY_CD */
    public static final String BIND_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** BIND_PARAM_PSN_CD */
    public static final String BIND_PARAM_PSN_CD = "psnCd";

    /** BIND_PARAM_PHYS_INVTY_NUM */
    public static final String BIND_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /** BIND_PARAM_CNT_STS_CD_LIST */
    public static final String BIND_PARAM_CNT_STS_CD_LIST = "cntStsCdList";

    /** BIND_PARAM_ADJ_VAR_FLG */
    public static final String BIND_PARAM_ADJ_VAR_FLG = "adjVarFlg";

    /** BIND_PARAM_BIZ_AREA_ORG_CD */
    public static final String BIND_PARAM_BIZ_AREA_ORG_CD = "bizAreaOrgCd";

    /** BIND_PARAM_BR_FLG */
    public static final String BIND_PARAM_BR_FLG = "brFlg";

    /** BIND_PARAM_ORG_STRU_TP_CD */
    public static final String BIND_PARAM_ORG_STRU_TP_CD = "orgStruTpCd";

    /** BIND_PARAM_GNRN_TP_CD_LIST */
    public static final String BIND_PARAM_GNRN_TP_CD_LIST = "gnrnTpCdList";

    /** DB_RESULT_COL_CNT */
    public static final String DB_RESULT_COL_CNT = "CNT";

    /** DB_RESULT_COL_BR_NM */
    public static final String DB_RESULT_COL_BR_NM = "BR_NM";

    /** DB_RESULT_COL_TECH_TOC_CD */
    public static final String DB_RESULT_COL_TECH_TOC_CD = "TECH_TOC_CD";

    /** DB_RESULT_COL_PHYS_INVTY_CTRL_NM */
    public static final String DB_RESULT_COL_PHYS_INVTY_CTRL_NM = "PHYS_INVTY_CTRL_NM";

    /** DB_RESULT_COL_PHYS_INVTY_DT */
    public static final String DB_RESULT_COL_PHYS_INVTY_DT = "PHYS_INVTY_DT";

    /** DB_RESULT_COL_TOT_VAR_GRS_QTY */
    public static final String DB_RESULT_COL_TOT_VAR_GRS_QTY = "TOT_VAR_GRS_QTY";

    /** DB_RESULT_COL_TOT_VAR_GRS_AMT */
    public static final String DB_RESULT_COL_TOT_VAR_GRS_AMT = "TOT_VAR_GRS_AMT";

    /** DB_RESULT_COL_TOT_VAR_NET_QTY */
    public static final String DB_RESULT_COL_TOT_VAR_NET_QTY = "TOT_VAR_NET_QTY";

    /** DB_RESULT_COL_TOT_VAR_NET_AMT */
    public static final String DB_RESULT_COL_TOT_VAR_NET_AMT = "TOT_VAR_NET_AMT";

    /** DB_RESULT_COL_RTL_WH_CD */
    public static final String DB_RESULT_COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB_RESULT_BR_NM */
    public static final String DB_RESULT_BR_NM = "BR_NM";

    /** ATTL1_LBL_TXT */
    public static final String ATTR1_LBL_TXT = "PI#";

    /** ATTL2_LBL_TXT */
    public static final String ATTR2_LBL_TXT = "Technician Name";

    /** ATTL2_LBL_TXT_PI */
    public static final String ATTR2_LBL_TXT_PI = "Physcal Inventory";

    /** ATTL3_LBL_TXT */
    public static final String ATTR3_LBL_TXT = "Tech Inventory";

    /** ATTL3_LBL_TXT_PI */
    public static final String ATTR3_LBL_TXT_PI = "PI Adj Name";

    /** ATTL4_LBL_TXT */
    public static final String ATTR4_LBL_TXT = "Scheduled Date";

    /** ATTL5_LBL_TXT */
    public static final String ATTR5_LBL_TXT = "Variance (Gross) Amount";

    /** Date Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** S21_PSN SQL_ID */
    public static final String S21_PSN_SQL_ID = "001";
    /** TABLE ID **/
    public static final String TBL_ID_PHYS_INVTY_CTRL = "PHYS_INVTY_CTRL";
    /** COL_NAME ON PHYS_INVTY_CTRL **/
    public static final String COL_NM_LOC_TP_CD = "LOC_TP_CD";
    /** COL_NAME ON PHYS_INVTY_CTRL **/
    public static final String COL_NM_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";
    /** DB_RESULT_COL_PHYS_INVTY_NUM */
    public static final String DB_RESULT_COL_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";
    /** DB_RESULT_COL_PHYS_INVTY_NUM */
    public static final String DB_RESULT_COL_PHYS_INVTY_ADJ_NM = "PHYS_INVTY_ADJ_NM";
    /**
     * Inconsistent data. TableID. Attribute[@], Key[@], Value[@]
     */
    public static final String NLCM0049E = "NLCM0049E";
    
    public static final String PI_ADJ_NM_PREFIX = "PI ADJ NM:";
}
