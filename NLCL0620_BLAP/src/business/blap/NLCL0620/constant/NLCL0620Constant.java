/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0620.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS         Makoto Okigami     Create          N/A
 * 12/06/2018   Fujitsu         S.Ohki          Update          QC#28128
 * 12/11/2018   Fujitsu         S.Ohki          Update          QC#28755
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 * 06/25/2020   CITS          M.Furugoori       Update          QC#56979
 * 09/29/2021   CITS            R.Azucena       Update          QC#59216
 *</pre>
 */
public class NLCL0620Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0620";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL0620Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NLCL0620_INIT = "NLCL0620_INIT";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NLCL0620_SEARCH = "NLCL0620Scrn00_Search";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NLCL0620_CMN_RESET = "NLCL0620Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NLCL0620_CMN_SUBMIT = "NLCL0620Scrn00_CMN_Submit";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NLCL0620_PAGE_NEXT = "NLCL0620Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NLCL0620_PAGE_PREV = "NLCL0620Scrn00_PagePrev";

    /**
     * Event Name : SearchTechnician
     */
    public static final String EVENT_NM_NLCL0620_SEARCH_TECHNICIAN = "NLCL0620Scrn00_SearchTechnician";

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * Event Name : Search Location
     */
    public static final String EVENT_NM_NLCL0620_SEARCH_LOCATION = "NLCL0620Scrn00_SearchLocation";
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NLCL0620_CMN_DOWNLOAD = "NLCL0620Scrn00_CMN_Download";
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : TECH_TOC_CD
     */
    public static final String DISPLAY_NM_TECH_TOC_CD = "Technician Code";

    /**
     * Display Name : PHYS_INVTY_CTRL_NM
     */
    public static final String DISPLAY_NM_PHYS_INVTY_CTRL_NM = "Tech Physical";

    /**
     * Display Name : Technician
     */
    public static final String DISPLAY_NM_TECHNICIAN = "Technician";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name: User Id
     */
    public static final String DB_PARAM_USER_ID = "userId";

    /**
     * DB Param Name: TECH_TOC_CD
     */
    public static final String DB_PARAM_TECH_TOC_CD = "techTocCd";

    /**
     * DB Param Name: PRCH_REQ_REC_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /**
     * DB Param Name: PRCH_REQ_LINE_SUB_NUM
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_SUB_NUM = "prchReqLineSubNum";

    /**
     * DB Param Name: OPEN_STS_FLG
     */
    public static final String DB_PARAM_OPEN_STS_FLG = "openStsFlg";

    /**
     * DB Param Name: PROCR_TP_CD (1)
     */
    public static final String DB_PARAM_PROCR_TP_CD_1 = "procrTpCd1";

    /**
     * DB Param Name: PROCR_TP_CD (2)
     */
    public static final String DB_PARAM_PROCR_TP_CD_2 = "procrTpCd2";

    /**
     * DB Param Name: ADJ_SUBMT_TS
     */
    public static final String DB_PARAM_ADJ_SUBMT_TS = "adjSubmtTs";

    /**
     * DB Param Name: PHYS_INVTY_CTRL_NM
     */
    public static final String DB_PARAM_PHYS_INVTY_CTRL_NM = "physInvtyCtrlNm";

    /**
     * DB Param Name: RQST_TECH_TOC_CD
     */
    public static final String DB_PARAM_RQST_TECH_TOC_CD = "rqstTechTocCd";

    /**
     * DB Param Name: thresholdOpenOrderDays
     */
    public static final String DB_PARAM_THRESHOLD_OPEN_ORDER_DAYS = "thresholdOpenOrderDays";

    /**
     * DB Param Name: locTpCd
     */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /**
     * DB Param Name: PHYS_INVTY_DT
     */
    public static final String DB_PARAM_PHYS_INVTY_DT = "physInvtyDt";

    /**
     * DB Param Name: PHYS_INVTY_STS_CD
     */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD = "physInvtyStsCd";

    /**
     * DB Param Name: PHYS_INVTY_CNT_STS_CD
     */
    public static final String DB_PARAM_PHYS_INVTY_CNT_STS_CD = "physInvtyCntStsCd";

    /**
     * DB Param Name: PHYS_INVTY_NUM
     */
    public static final String DB_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /**
     * DB Param Name: Max ROWNUM
     */
    public static final String DB_PARAM_MAX_ROWNUM = "maxRownum";

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: DEST_RTL_WH_CD
     */
    public static final String DB_PARAM_DEST_RTL_WH_CD = "destRtlWhCd";
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * DB Param Name: DB_PARAM_INSOURCED_PO
     */
    public static final String DB_PARAM_INSOURCED_PO = "InsourcingPO";

    /**
     * DB Param Name: DB_PARAM_CHOICE_SHIP_CONFIRMATION
     */
    // START 2021/09/29 R.Azucena[QC#59216, DEL]
    // public static final String DB_PARAM_CHOICE_SHIP_CONFIRMATION = "choiceShipConfirmation";
    // END 2021/09/29 R.Azucena[QC#59216, DEL]
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: MAX_ADJ_SUBMT_TS
     */
    public static final String DB_COLUMN_MAX_ADJ_SUBMT_TS = "MAX_ADJ_SUBMT_TS";

    /**
     * DB Column Name: ADJ_SUBMT_TS
     */
    public static final String DB_COLUMN_ADJ_SUBMT_TS = "ADJ_SUBMT_TS";

    /**
     * DB Column Name: TOTAL_ADJ_GRS_AMT
     */
    public static final String DB_COLUMN_TOTAL_ADJ_GRS_AMT = "TOTAL_ADJ_GRS_AMT";

    /**
     * DB Column Name: TOTAL_ADJ_NET_AMT
     */
    public static final String DB_COLUMN_TOTAL_ADJ_NET_AMT = "TOTAL_ADJ_NET_AMT";

    /**
     * DB Column Name: CNT
     */
    public static final String DB_COLUMN_CNT = "CNT";

    /**
     * DB Column Name: CNT_OPEN_ORDR
     */
    public static final String DB_COLUMN_CNT_OPEN_ORDR = "CNT_OPEN_ORDR";

    /**
     * DB Column Name: INVTY_LOC_CD
     */
    public static final String DB_COLUMN_INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * DB Column Name: RTL_WH_CD
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: RTL_SWH_CD
     */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB Column Name: LOC_TP_CD
     */
    public static final String DB_COLUMN_LOC_TP_CD = "LOC_TP_CD";

    /**
     * DB Column Name: TECH_NM
     */
    public static final String DB_COLUMN_TECH_NM = "TECH_NM";

    /**
     * DB Column Name: PHYS_INVTY_NUM
     */
    public static final String DB_COLUMN_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: TECH_TOC_CD
     */
    public static final String DB_COLUMN_TECH_TOC_CD = "TECH_TOC_CD";
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    // =================================================
    // DB Param Value
    // =================================================
    /**
     * DB Param Name: Exclusion Line Sub Number
     */
    public static final BigDecimal DB_PARAM_VALUE_EXCLUSION_LINE_SUB_NUM = BigDecimal.ZERO;

    // =================================================
    // Cost value key
    // =================================================

    /**
     * Key Name : TECH_PI_THRHD_OPEN_ORD_DAYS
     */
    public static final String CONST_VALUE_KEY_TECH_PI_THRHD_OPEN_ORD_DAYS = "TECH_PI_THRHD_OPEN_ORD_DAYS";

    /**
     * Key Name : TECH_PI_LOC_TP_CD
     */
    public static final String CONST_VALUE_KEY_TECH_PI_LOC_TP_CD = "TECH_PI_LOC_TP_CD";

    // =================================================
    // Cost value
    // =================================================

    /**
     * Const Value Delim
     */
    public static final String VAR_CHAR_CONST_VAL_DELIM = ",";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NLZM2278E: The entered @ does not exist in Master.
     */
    public static final String NLZM2278E = "NLZM2278E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NLCM0143E: The  [@] is already registered in Tech Physical.
     */
    public static final String NLCM0143E = "NLCM0143E";

    /**
     * NLCM0144E: There is some open order older than [@] days.
     */
    public static final String NLCM0144E = "NLCM0144E";

    /**
     * ZZM8100I: Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * NMAM0176E: It failed to register [@].
     */
    public static final String NMAM0176E = "NMAM0176E";

    /**
     * NLCM0162E: There is no data to reigster an avaiable technician's location.
     */
    public static final String NLCM0162E = "NLCM0162E";

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * NLCM0229E: Location owner information is not correctly setup.
     */
    public static final String NLCM0229E = "NLCM0229E";
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    // =================================================
    // Message Value
    // =================================================
    /**
     * Message Value :Physical Inventory Control
     */
    public static final String MSG_VALUE_PHYS_INVTY_CTRL = "Physical Inventory Control";

    // START 2018/12/06 S.Ohki [QC#28128,ADD]
    /**
     * Function ID for all reference
     */
    public static final String FUNC_ID_ALL_USERS = "NLCL0620T030";
    // END 2018/12/06 S.Ohki [QC#28128,ADD]

    // =================================================
    // Download Value
    // =================================================
    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * FETCH_SIZE
     */
    public static final int FETCH_SIZE = 1000;
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

    // =================================================
    // Replace Value
    // =================================================
    // START 2020/06/25 [QC#56979,ADD]
    /**
     * Replace Character at CARR_URL.CARR_TRK_URL.
     */
    public static final String REPLACE_CHAR = "\\$\\$";
    // END   2020/06/25 [QC#56979,ADD]

}
