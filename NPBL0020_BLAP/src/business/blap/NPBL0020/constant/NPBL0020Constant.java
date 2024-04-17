/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0020.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS            Makoto Okigami  Create          N/A
 * 04/21/2016   CITS            K.Ogino         Update          QC#7045
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9297
 * 06/24/2016   CSAI            D.Fukaya        Update          QC#9292
 * 06/30/2016   CSAI            D.Fukaya        Update          QC#7735
 * 07/29/2016   CITS            K.Ogino         Update          QC#8288
 * 08/03/2016   CITS            K.Ogino         Update          QC#9050
 * 08/03/2016   CITS            K.Ogino         Update          QC#12517
 * 09/08/2016   CITS            K.Ogino         Update          QC#14169
 * 01/10/2017   CITS            K.Ogino         Update          QC#16296
 * 01/13/2017   CITS            R.Shimamoto     Update          QC#17098
 * 02/09/2017   CITS            K.Ogino         Update          QC#17483
 * 08/03/2017   CITS            K.Ogino         Update          QC#18718
 * 09/26/2017   CITS            K.Ogino         Update          QC#21288
 * 10/24/2017   CITS            K.Ogino         Update          QC#22026
 * 11/16/2017   CITS            K.Ogino         Update          QC#22346
 * 11/28/2017   CITS            K.Ogino         Update          QC#22481
 * 12/26/2017   CITS            K.Ogino         Update          QC#22467
 * 03/27/2018   CITS            T.Wada          Update          QC#22517
 * 06/11/2018   CITS            S.Katsuma       Update          QC#26193
 * 11/06/2018   CITS            M.Naito         Update          QC#28698
 * 12/12/2018   Fujitsu         S.Takami        Update          QC#29456
 * 03/20/2019   Fujitsu         T.Ogura         Update          QC#30769
 * 05/17/2019   CITS            M.Naito         Update          QC#50076
 * 08/27/2019   CITS            M.Naito         Update          QC#52276
 * 09/11/2019   CITS            K.Ogino         Update          QC#52809
 * 10/03/2019   CITS            M.Naito         Update          QC#52809
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 * 06/12/2020   Fujitsu         T.Ogura         Update          QC#57002
 * 11/09/2021   CITS            R.Azucena       Update          QC#58586
 * 11/16/2022   CITS            R.Azucena       Update          QC#60808
 * 03/13/2023   CITS            R.Azucena       Update          QC#61282
 * 07/04/2023   CITS            T.Kuroda        Update          QC#61440
 *</pre>
 */
public class NPBL0020Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPBL0020";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPBL0020Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPBL0020_INIT = "NPBL0020_INIT";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPBL0020_SEARCH = "NPBL0020Scrn00_Search";

    /**
     * Event Name : OnChange_PRType
     */
    public static final String EVENT_NM_NPBL0020_ON_CHANGE_PR_TYPE = "NPBL0020Scrn00_OnChange_PRType";

    /**
     * Event Name : Add_NewLine
     */
    public static final String EVENT_NM_NPBL0020_ADD_NEW_LINE = "NPBL0020Scrn00_Add_NewLine";

    /**
     * Event Name : Add_NewConfig
     */
    public static final String EVENT_NM_NPBL0020_ADD_NEW_CONFIG = "NPBL0020Scrn00_Add_NewConfig";

    /**
     * Event Name : Add_NewConfig
     */
    public static final String EVENT_NM_NPBL0020_ADD_EXISTING_CONFIG = "NPBL0020Scrn00_Add_ExistingConfig";

    /**
     * Event Name : Return NWAL1130
     */
    public static final String EVENT_NM_NPBL0020_NWAL1130 = "NPBL0020_NWAL1130";

    /**
     * Event Name : Return NPAL1010
     */
    public static final String EVENT_NM_NPBL0020_NPAL1010 = "NPBL0020_NPAL1010";

    /**
     * Event Name : Return NPAL1360
     */
    public static final String EVENT_NM_NPBL0020_NPAL1360 = "NPBL0020_NPAL1360";

    /**
     * Event Name : OpenWin_Account
     */
    public static final String EVENT_NM_NPBL0020_OPEN_WIN_ACCOUNT = "NPBL0020Scrn00_OpenWin_Account";

    /**
     * Event Name : Upload
     */
    public static final String EVENT_NM_NPBL0020_UPLOAD = "NPBL0020Scrn00_Upload";

    /**
     * Event Name : Upload
     */
    public static final String EVENT_NM_NPBL0020_TEMPLATE_DOWNLOAD = "NPBL0020Scrn00_OnClick_TemplateDownload";

    /**
     * Event Name : Header Cancel
     */
    public static final String EVENT_NM_NPBL0020_HEADER_CANCEL = "NPBL0020Scrn00_HeaderCancel";

    /**
     * Event Name : Header Close
     */
    public static final String EVENT_NM_NPBL0020_HEADER_CLOSE = "NPBL0020Scrn00_HeaderClose";

    /**
     * Event Name : Line Cancel
     */
    public static final String EVENT_NM_NPBL0020_LINE_CANCEL = "NPBL0020Scrn00_LineCancel";

    /**
     * Event Name : Copy
     */
    public static final String EVENT_NM_NPBL0020_COPY = "NPBL0020Scrn00_Copy";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPBL0020_PAGE_NEXT = "NPBL0020Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPBL0020_PAGE_PREV = "NPBL0020Scrn00_PagePrev";

    /**
     * Event Name : CMN_Save
     */
    public static final String EVENT_NM_NPBL0020_CMN_SAVE = "NPBL0020Scrn00_CMN_Save";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NPBL0020_CMN_SUBMIT = "NPBL0020Scrn00_CMN_Submit";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NPBL0020_CMN_RESET = "NPBL0020Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NPBL0020_CMN_CLEAR = "NPBL0020Scrn00_CMN_Clear";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPBL0020_CMN_COL_CLEAR = "NPBL0020Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPBL0020_CMN_COL_SAVE = "NPBL0020Scrn00_CMN_ColSave";

    /**
     * Event Name : MoveTo_OnHandInv
     */
    public static final String EVENT_NM_NPBL0020_MOVE_TO_ON_HAND_INV = "NPBL0020Scrn00_MoveTo_OnHandInv";

    /**
     * Event Name : OpenWin_SerEnt
     */
    public static final String EVENT_NM_NPBL0020_OPENWIN_SERENT = "NPBL0020Scrn00_OpenWin_SerEnt";

    /**
     * Event Name : GetItemInfo
     */
    public static final String EVENT_NM_NPBL0020_GET_ITEM_INFO = "NPBL0020Scrn00_GetItemInfo";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name: Retail sub Warehouse Code
     */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    /**
     * DB Param Name: PR Record Type
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /**
     * DB Param Name:
     */
    public static final String DB_PARAM_SCR_ENT_AVAL_FLG = "scrEntAvalFlg";

    /**
     * DB Param Name: PR Requistion Type
     */
    public static final String DB_PARAM_PRCH_REQ_TP_CD = "prchReqTpCd";

    /**
     * DB Param Name: Shipping Service Level Code
     */
    public static final String DB_PARAM_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /**
     * DB Param Name: Carrier Code
     */
    public static final String DB_PARAM_CARR_CD = "carrCd";

    /**
     * DB Param Name: Line Status Code
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_STS_CD = "prchReqLineStsCd";

    /**
     * DB Param Name: Config Type Code
     */
    public static final String DB_PARAM_CONFIG_TP_CD = "configTpCd";

    /**
     * DB Param Name: Service Config Master PK
     */
    public static final String DB_PARAM_SVC_CONFIG_MSTR_PK = "svcConfigMstrPk";

    /**
     * DB Param Name: Service Machine Master Status Codes
     */
    public static final String DB_PARAM_SVC_MACH_MSTR_STS_CD_LIST = "svcMachMstrStsCdList";

    /**
     * DB Param Name: Current Location Number
     */
    public static final String DB_PARAM_CUR_LOC_NUM = "curLocNum";

    /**
     * DB Param Name: Service Machine Maintenance Avairable Flag
     */
    public static final String DB_PARAM_SVC_MACH_MAINT_AVAL_FLG = "svcMachMaintAvalFlg";

    /**
     * DB Param Name: MDSE Code
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * DB Param Name: Registration Status Code
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * DB Param Name: PO#
     */
    public static final String DB_PARAM_PO_ORD_NUM = "poOrdNum";

    /**
     * DB Param Name: PO Line#
     */
    public static final String DB_PARAM_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /**
     * DB Param Name: PO Line#
     */
    public static final String DB_PARAM_FULL_PSN_NM = "fullPsnNm";

    /**
     * DB Param Name: Rtl WH Code
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: Rtl WH Name
     */
    public static final String DB_PARAM_RTL_WH_NM = "rtlWhNm";

    /**
     * DB Param Name: Rtl SWH Name
     */
    public static final String DB_PARAM_RTL_SWH_NM = "rtlSwhNm";

    /**
     * DB Param Name: Source Rtl WH Code
     */
    public static final String DB_PARAM_SRC_RTL_WH_CD = "srcRtlWhCd";

    /**
     * DB Param Name: Destination Rtl WH Code
     */
    public static final String DB_PARAM_DEST_RTL_WH_CD = "destRtlWhCd";

    /**
     * DB Param Name: Ship To Location Code
     */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /**
     * DB Param Name: Ship To Location Name
     */
    public static final String DB_PARAM_DPLY_VND_NM = "dplyVndNm";

    /**
     * DB Param Name: Serial#
     */
    public static final String DB_PARAM_SER_NUM = "serNum";

    /**
     * DB Param Name: ShipToCustomer Code
     */
    public static final String DB_PARAM_SHIP_TO_CUST_CD = "shipToCustCd";

    /**
     * DB Param Name: PRCH_REQ_TP
     */
    public static final String DB_PARAM_PRCH_REQ_TP = "prchReqTp";

    /**
     * DB Param Name: PRCH_REQ_STS_CD
     */
    public static final String DB_PARAM_PRCH_REQ_STS_CD = "prchReqStsCd";

    /**
     * DB Param Name: PRCH_REQ_APVL_STS_CD
     */
    public static final String DB_PARAM_PRCH_REQ_APVL_STS_CD = "prchReqApvlStsCd";

    /**
     * DB Param Name: PRCH_REQ_SRC_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_SRC_TP_CD = "prchReqSrcTpCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param rowNum
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    /**
     * DB Param Name:appFuncId
     */
    public static final String DB_PARAM_APP_FUNC_ID = "appFuncId";

    /**
     * DB Param Name:prchReqNum
     */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /**
     * DB Param Name:prchReqLineTpCd
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP = "prchReqLineTpCd";

    /**
     * DB Param Name:dsAcctNm
     */
    public static final String DB_PARAM_DS_ACCT_NM = "dsAcctNm";

    /**
     * DB Param Name:expenseOrderTp
     */
    public static final String DB_PARAM_EXPENSE_ORDER_TP = "expenseOrderTp";

    /**
     * DB Param coaBrCd
     */
    public static final String DB_PARAM_COA_BR_CD = "coaBrCd";

    /**
     * DB Param coaChCd
     */
    public static final String DB_PARAM_COA_CH_CD = "coaChCd";

    /**
     * DB Param coaCcCd
     */
    public static final String DB_PARAM_COA_CC_CD = "coaCcCd";

    /**
     * DB Param coaProdCd
     */
    public static final String DB_PARAM_COA_PROD_CD = "coaProdCd";

    /**
     * DB Param coaGlCmbnActvFlg
     */
    public static final String DB_PARAM_COA_GL_CMBN_ACTV_FLG = "coaGlCmbnActvFlg";

    /**
     * DB Param Flg N
     */
    public static final String DB_PARAM_FLG_N = "flgN";

    /**
     * DB Param Flg Y
     */
    public static final String DB_PARAM_FLG_Y = "flgY";

    /**
     * DB Param FRT_CHRG_METH_CD
     */
    public static final String DB_PARAM_FRT_CHRG_METH_CD = "frtChrgMethCd";

    /**
     * DB Param FRT_CHRG_TO_CD
     */
    public static final String DB_PARAM_FRT_CHRG_TO_CD = "frtChrgToCd";

    // START 2022/11/16 R.Azucena[QC#60808, ADD]
    /**
     * DB Param svcMachMstrPk
     */
    public static final String DB_PARAM_SVC_MACH_MSTR_PK = "svcMachMstrPk";

    /**
     * DB Param svcMachMstrStsCd
     */
    public static final String DB_PARAM_SVC_MACH_MSTR_STS_CD = "svcMachMstrStsCd";

    /**
     * DB Param ordCatgCtxCd
     */
    public static final String DB_PARAM_ORD_CATG_CTX_CD = "ordCatgCtxCd";

    /**
     * DB Param ordHdrStsCdList
     */
    public static final String DB_PARAM_ORD_HDR_STS_CD_LIST = "ordHdrStsCdList";
    // END 2022/11/16 R.Azucena[QC#60808, ADD]

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: Requisition Type CD
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /**
     * DB Column Name: Requisition Type Name
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /**
     * DB Column Name: Line Type CD
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /**
     * DB Column Name: Line Type Name
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT = "PRCH_REQ_LINE_TP_DESC_TXT";

    /**
     * DB Column Name: From Stock Status CD
     */
    public static final String DB_COLUMN_FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /**
     * DB Column Name: Stock Status CD
     */
    public static final String DB_COLUMN_STK_STS_CD = "STK_STS_CD";

    /**
     * DB Column Name: Stock Status Name
     */
    public static final String DB_COLUMN_STK_STS_DESC_TXT = "STK_STS_DESC_TXT";

    /**
     * DB Column Name: Default PR Line Type Code
     */
    public static final String DB_COLUMN_DEF_PRCH_REQ_LINE_TP_CD = "DEF_PRCH_REQ_LINE_TP_CD";

    /**
     * DB Column Name: Line Status Name
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT = "PRCH_REQ_LINE_STS_DESC_TXT";

    /**
     * DB Column Name: Config Type Name
     */
    public static final String DB_COLUMN_CONFIG_TP_DESC_TXT = "CONFIG_TP_DESC_TXT";

    /**
     * DB Column Name: Current Location Number
     */
    public static final String DB_COLUMN_CUR_LOC_NUM = "CUR_LOC_NUM";

    /**
     * DB Column Name: Merchandise Code
     */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    // START 2022/11/16 R.Azucena[QC#60808, ADD]
    /**
     * DB Column Name: SVC_MACH_MSTR_PK
     */
    public static final String DB_COLUMN_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    // END 2022/11/16 R.Azucena[QC#60808, ADD]

    /**
     * DB Column Name: Serial Number
     */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /**
     * DB Column Name: MDSE Description Short Name
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: PO Status Code
     */
    public static final String DB_COLUMN_PO_STS_CD = "PO_STS_CD";

    /**
     * DB Column Name: Requester Code
     */
    public static final String DB_COLUMN_PRCH_REQ_RQST_BY_PSN_CD = "PRCH_REQ_RQST_BY_PSN_CD";

    /**
     * DB Column Name: Requester Code
     */
    public static final String DB_COLUMN_PSN_CD = "PSN_CD";

    /**
     * DB Column Name: Rtl WH Code
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: Rtl WH Name
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: Rtl SWH Name
     */
    public static final String DB_COLUMN_RTL_SWH_NM = "RTL_SWH_NM";

    /**
     * DB Column Name: Rtl SWH Code
     */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB Column Name: Rtl WH Category Code
     */
    public static final String DB_COLUMN_RTL_WH_CATG_CD = "RTL_WH_CATG_CD";

    /**
     * DB Column Name: Ship To Location
     */
    public static final String DB_COLUMN_PRNT_VND_CD = "PRNT_VND_CD";

    /**
     * DB Column Name: Ship To Location
     */
    public static final String DB_COLUMN_VND_CD = "VND_CD";

    /**
     * DB Column Name: Ship To Location
     */
    public static final String DB_COLUMN_DPLY_VND_NM = "DPLY_VND_NM";

    /**
     * DB Column Name: Location Name
     */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";

    /**
     * DB Column Name: Additional Location Name
     */
    public static final String DB_COLUMN_ADDL_LOC_NM = "ADDL_LOC_NM";

    /**
     * DB Column Name: First Line Address
     */
    public static final String DB_COLUMN_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * DB Column Name: Secound Line Address
     */
    public static final String DB_COLUMN_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * DB Column Name: Third Line Address
     */
    public static final String DB_COLUMN_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * DB Column Name: Fourth Line Address
     */
    public static final String DB_COLUMN_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * DB Column Name: Ship Vender Address
     */
    public static final String DB_COLUMN_SHIP_VND_ADDR = "SHIP_VND_ADDR";

    /**
     * DB Column Name: Country Code
     */
    public static final String DB_COLUMN_CTRY_CD = "CTRY_CD";

    /**
     * DB Column Name: Post Code
     */
    public static final String DB_COLUMN_POST_CD = "POST_CD";

    /**
     * DB Column Name: City Address
     */
    public static final String DB_COLUMN_CTY_ADDR = "CTY_ADDR";

    /**
     * DB Column Name: State Code
     */
    public static final String DB_COLUMN_ST_CD = "ST_CD";

    /**
     * DB Column Name: Province Name
     */
    public static final String DB_COLUMN_PROV_NM = "PROV_NM";

    /**
     * DB Column Name: County Name
     */
    public static final String DB_COLUMN_CNTY_NM = "CNTY_NM";

    /**
     * DB Column Name: Country Name
     */
    public static final String DB_COLUMN_CTRY_NM = "CTRY_NM";

    /**
     * DB Column Name: Preferred Carrier Code
     */
    public static final String DB_COLUMN_PRF_CARR_CD = "PRF_CARR_CD";

    /**
     * DB Column Name: Carrier Name
     */
    public static final String DB_COLUMN_CARR_NM = "CARR_NM";

    /**
     * DB Column Name: Vendor Return COA Company Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_CMPY_CD = "VND_RTRN_COA_CMPY_CD";

    /**
     * DB Column Name: Vendor Return COA Affiliate Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_AFFL_CD = "VND_RTRN_COA_AFFL_CD";

    /**
     * DB Column Name: Vendor Return COA Branch Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_BR_CD = "VND_RTRN_COA_BR_CD";

    /**
     * DB Column Name: Vendor Return COA CostCentor Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_CC_CD = "VND_RTRN_COA_CC_CD";

    /**
     * DB Column Name: Vendor Return COA Account Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_ACCT_CD = "VND_RTRN_COA_ACCT_CD";

    /**
     * DB Column Name: Vendor Return COA Product Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_PROD_CD = "VND_RTRN_COA_PROD_CD";

    /**
     * DB Column Name: Vendor Return COA Channel Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_CH_CD = "VND_RTRN_COA_CH_CD";

    /**
     * DB Column Name: Vendor Return COA Project Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_PROJ_CD = "VND_RTRN_COA_PROJ_CD";

    /**
     * DB Column Name: Vendor Return COA Extension Code
     */
    public static final String DB_COLUMN_VND_RTRN_COA_EXTN_CD = "VND_RTRN_COA_EXTN_CD";

    /**
     * DB Column Name: COA Company Dply Flag
     */
    public static final String DB_COLUMN_COA_CMPY_DPLY_FLG = "COA_CMPY_DPLY_FLG";

    /**
     * DB Column Name: COA Affiliate Dply Flag
     */
    public static final String DB_COLUMN_COA_AFFL_DPLY_FLG = "COA_AFFL_DPLY_FLG";

    /**
     * DB Column Name: COA Branch Dply Flag
     */
    public static final String DB_COLUMN_COA_BR_DPLY_FLG = "COA_BR_DPLY_FLG";

    /**
     * DB Column Name: COA CostCentor Dply Flag
     */
    public static final String DB_COLUMN_COA_CC_DPLY_FLG = "COA_CC_DPLY_FLG";

    /**
     * DB Column Name: COA Account Dply Flag
     */
    public static final String DB_COLUMN_COA_ACCT_DPLY_FLG = "COA_ACCT_DPLY_FLG";

    /**
     * DB Column Name: COA Product Dply Flag
     */
    public static final String DB_COLUMN_COA_PROD_DPLY_FLG = "COA_PROD_DPLY_FLG";

    /**
     * DB Column Name: COA Channel Dply Flag
     */
    public static final String DB_COLUMN_COA_CH_DPLY_FLG = "COA_CH_DPLY_FLG";

    /**
     * DB Column Name: COA Project Dply Flag
     */
    public static final String DB_COLUMN_COA_PROJ_DPLY_FLG = "COA_PROJ_DPLY_FLG";

    /**
     * DB Column Name: COA Extension Dply Flag
     */
    public static final String DB_COLUMN_COA_EXTN_DPLY_FLG = "COA_EXTN_DPLY_FLG";

    /**
     * DB Column Name: COA Company Code
     */
    public static final String DB_COLUMN_COA_CMPY_CD = "COA_CMPY_CD";

    /**
     * DB Column Name: COA Affiliate Code
     */
    public static final String DB_COLUMN_COA_AFFL_CD = "COA_AFFL_CD";

    /**
     * DB Column Name: COA Branch Code
     */
    public static final String DB_COLUMN_COA_BR_CD = "COA_BR_CD";

    /**
     * DB Column Name: COA CostCentor Code
     */
    public static final String DB_COLUMN_COA_CC_CD = "COA_CC_CD";

    /**
     * DB Column Name: COA Account Code
     */
    public static final String DB_COLUMN_COA_ACCT_CD = "COA_ACCT_CD";

    /**
     * DB Column Name: COA Product Code
     */
    public static final String DB_COLUMN_COA_PROD_CD = "COA_PROD_CD";

    /**
     * DB Column Name: COA Channel Code
     */
    public static final String DB_COLUMN_COA_CH_CD = "COA_CH_CD";

    /**
     * DB Column Name: COA Project Code
     */
    public static final String DB_COLUMN_COA_PROJ_CD = "COA_PROJ_CD";

    /**
     * DB Column Name: COA Extension Code
     */
    public static final String DB_COLUMN_COA_EXTN_CD = "COA_EXTN_CD";

    /**
     * DB Column Name: Header Status Name
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /**
     * DB Column Name: Header Status Name
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /**
     * DB Column Name: Document Source Type Name
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT = "PRCH_REQ_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_LINE_NUM
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /**
     * DB Column Name: PRCH_REQ_LINE_SUB_NUM
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /**
     * DB Column Name: PRCH_REQ_DISP_QTY
     */
    public static final String DB_COLUMN_PRCH_REQ_DISP_QTY = "PRCH_REQ_DISP_QTY";

    /**
     * DB Column Name: INSTL_BASE_CTRL_FLG
     */
    public static final String DB_COLUMN_INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /**
     * DB Column Name: MDSE_TP_CTX_TP_CD
     */
    public static final String DB_COLUMN_MDSE_TP_CTX_TP_CD = "MDSE_TP_CTX_TP_CD";

    /**
     * DB Column Name: SHPG_SER_TAKE_FLG
     */
    public static final String DB_COLUMN_SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    /**
     * DB Column Name: Ship to Customer Code
     */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB Column Name: Direct Sales Account Name
     */
    public static final String DB_COLUMN_DS_ACCT_NM = "DS_ACCT_NM";

    /**
     * DB Column Name: Direct Sales Account Number
     */
    public static final String DB_COLUMN_DS_ACCT_NUM = "DS_ACCT_NUM";

    /**
     * DB Column Name: SHPG_SVC_LVL_CD
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB Column Name: SHPG_SVC_LVL_DESC_TXT
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    // START 2023/03/13 R.Azucena[QC#61282, ADD]
    /**
     * DB Column Name: SVC_MACH_MSTR_STS_CD
     */
    public static final String DB_COLUMN_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";

    /**
     * DB Column Name: SVC_MACH_MAINT_AVAL_FLG
     */
    public static final String DB_COLUMN_SVC_MACH_MAINT_AVAL_FLG = "SVC_MACH_MAINT_AVAL_FLG";

    /**
     * DB Column Name: TRX_HDR_NUM
     */
    public static final String DB_COLUMN_TRX_HDR_NUM = "TRX_HDR_NUM";
    // END 2023/03/13 R.Azucena[QC#61282, ADD]

    // =================================================
    // Screen Control Value
    // =================================================
    /**
     * Screen Control Value: Multiple
     */
    public static final String SCREEN_CTRL_VALUE_MULTIPLE = "MULTIPLE";

    /**
     * Screen Control Value: Serial# Delimiter
     */
    public static final String SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM = ",";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : FULL_PSN_NM
     */
    public static final String DISPLAY_NM_FULL_PSN_NM = "Requester";

    /**
     * Display Name : CARR_NM
     */
    public static final String DISPLAY_NM_CARR_NM = "Carrier";

    /**
     * Display Name : SRC_RTL_WH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_WH_CD = "Header Source WH Code";

    /**
     * Display Name : RTL_WH_NM_SW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_SW = "Source WH Name";

    /**
     * Display Name : RTL_SWH_NM_SW
     */
    public static final String DISPLAY_NM_RTL_SWH_NM_SS = "Source SWH Name";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD = "Source SWH Code";

    /**
     * Display Name : SRC_RTL_SWH_CD Add QC#22026
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_CD = "Destination SWH Code";

    /**
     * Display Name : DEST_RTL_WH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_WH_CD = "Destination WH";

    /**
     * Display Name : RTL_WH_NM_DW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_DW = "Destination WH Name";

    /**
     * Display Name : RTL_SWH_NM_DW
     */
    public static final String DISPLAY_NM_RTL_SWH_NM_DS = "Destination SWH Name";

    /**
     * Display Name : VND_CD
     */
    public static final String DISPLAY_NM_VND_CD = "Ship To Supplier";

    /**
     * Display Name : DPLY_VND_NM
     */
    public static final String DISPLAY_NM_DPLY_VND_NM = "Ship To Supplier Name";

    /**
     * Display Name : PRCH_REQ_LINE_TP_DESC_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_LINE_TP_DESC_TXT = "Line Type";

    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item#";

    /**
     * Display Name : PRCH_REQ_DISP_QTY
     */
    public static final String DISPLAY_NM_PRCH_REQ_DISP_QTY = "Transfer Qty";

    /**
     * Display Name : SER_NUM
     */
    public static final String DISPLAY_NM_SER_NUM = "Serial#";

    /**
     * Display Name : RTL_WH_NM_LINE_SW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_LINE_SW = "Source WH";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD_LINE = "Source SWH";

    /**
     * Display Name : RTL_WH_NM_LINE_DW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_LINE_DW = "Dest WH";

    /**
     * Display Name : DEST_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_CD_LINE = "Dest SWH";

    /**
     * Display Name : SHIP_TO_CUST_CD
     */
    public static final String DISPLAY_NM_SHIP_TO_CUST_CD = "Ship To Customer Code";

    /**
     * Display Name : SELL_TO_CUST_CD
     */
    public static final String DISPLAY_NM_SELL_TO_CUST_CD = "Sell To Customer Code";

    /**
     * Display Name : BILL_TO_CUST_CD
     */
    public static final String DISPLAY_NM_BILL_TO_CUST_CD = "Bill To Customer Code";

    /**
     * Display Name : SHIP_TO_LOC_NM
     */
    public static final String DISPLAY_NM_SHIP_TO_LOC_NM = "Ship To Customer Name";

    /**
     * Display Name : STK_STS_CD
     */
    public static final String DISPLAY_NM_STOCK_STATUS_CD = "Stock Status";

    /**
     * Display Name : Configuration Number
     */
    public static final String DISPLAY_NM_CONFIG_NUM = "Config#";

    /**
     * Display Name : Upload Data Format
     */
    public static final String UPLOAD_DATA_FORMAT = "CSV,TXT";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @
     * records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NPAM0077E: You can add Details up to [@].
     */
    public static final String NPAM0077E = "NPAM0077E";

    /**
     * NPAM1239E: Select only one detail to process.
     */
    public static final String NPAM1239E = "NPAM1239E";

    /**
     * NPAM1361E: The entered @ does not exist in Master.
     */
    public static final String NPAM1361E = "NPAM1361E";

    /**
     * This merchandise is not for inventory management.
     */
    public static final String NPAM0084E = "NPAM0084E";

    /**
     * NPAM0032E: The process cannot be executed because the
     * "PO Status" is not "Saved".
     */
    public static final String NPAM0032E = "NPAM0032E";

    /**
     * ZZZM9025E: [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * NPAM0046E: For [@], '0' or less than '0' cannot be specified.
     */
    public static final String NPAM0046E = "NPAM0046E";

    /**
     * NPAM1190E: If [@] is entered, please enter [@], too.
     */
    public static final String NPAM1190E = "NPAM1190E";

    /**
     * NPBM0001E: The entered @ cannot be uniquely specified.
     */
    public static final String NPBM0001E = "NPBM0001E";

    /**
     * NPAM1363E: The combination of specified input parameters [@]
     * and [@] is incorrect.
     */
    public static final String NPAM1363E = "NPAM1363E";

    /**
     * NPAM1365E: The relationship between 'Carrier' and 'Shipping
     * Service Level Code' is incorrect.
     */
    public static final String NPAM1365E = "NPAM1365E";

    /**
     * NPBM0002E: The relationship between 'Serial#' and 'Source WH
     * Code' is incorrect.
     */
    public static final String NPBM0002E = "NPBM0002E";

    /**
     * NPBM0003E: The relationship between 'Config#' and 'Source WH
     * Code' is incorrect.
     */
    public static final String NPBM0003E = "NPBM0003E";

    /**
     * ZZM8100I: Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * NPAM0006E: This data has been updated by another user.
     */
    public static final String NPAM0006E = "NPAM0006E";

    /**
     * NWAM0682E: Selected line cannot process.
     */
    public static final String NWAM0682E = "NWAM0682E";

    /**
     * NPAM0076E: The entered @ does not exist in Master.
     */
    public static final String NPAM0076E = "NPAM0076E";

    /**
     * ZYEM0004E:The Upload File is empty or only has a header line,
     * therefore it could not be processed. Please confirm the content
     * of the Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * NMAM0052E:The format of [@] is incorrect.
     */
    public static final String NMAM0052E = "NMAM0052E";

    /**
     * NPAM0008E: Your request cannot be processed under this status.
     */
    public static final String NPAM0008E = "NPAM0008E";

    /**
     * NMAM0177E: It failed to update [@].
     */
    public static final String NMAM0177E = "NMAM0177E";

    /**
     * NPAM0094E: Please set up the business days.
     */
//    public static final String NPAM0094E = "NPAM0094E";    // 2019/03/20 T.Ogura [QC#30769,DEL]

    /**
     * NWZM0949E: Calendar Type is not found. Please confirm the
     * Master Data.
     */
    public static final String NWZM0949E = "NWZM0949E";

    /**
     * NWZM0673E: Multiple Warehouse Calendars are obtained. Please
     * check Master data.
     */
    public static final String NWZM0673E = "NWZM0673E";

    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NPBM0004E: The relationship between '@' and '@' is incorrect.
     */
    public static final String NPBM0004E = "NPBM0004E";

    /**
     * ZZM9002E: [@] field exceeded maximum value.
     */
    public static final String ZZM9002E = "ZZM9002E";

    /**
     * NMAM8191E: Error has occurred while uploading. Please verify
     * the upload file format. (@)
     */
    public static final String NMAM8191E = "NMAM8191E";

    /**
     * NPAM1532E: Your request cannot be processed when related order
     * exsits.
     */
    public static final String NPAM1532E = "NPAM1532E";

    /**
     * NWZM1482E: The Base Component does not exists in config.
     */
    public static final String NWZM1482E = "NWZM1482E";

    /**
     * NPAM1534E: Please enter 1 in transfer qty when the entered item
     * is Machine.
     */
    public static final String NPAM1534E = "NPAM1534E";

    /**
     * NPAM1535E: Please enter 1 in transfer qty when the entered item
     * is serialzed item.
     */
    public static final String NPAM1535E = "NPAM1535E";

    /**
     * [@] is duplicated.
     */
    public static final String NPAM0073E = "NPAM0073E";

    /** The format of [@] is incorrect. */
    public static final String NPBM0005E = "NPBM0005E";

    /** Number of digits over error (item name [@]). */
    public static final String NPBM0006E = "NPBM0006E";

    /** Entered @ is invalid. */
    public static final String NPBM0007E = "NPBM0007E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * The relationship of Product, Branch, Channel, and Cost Center
     * is incorrect.
     */
    public static final String NPBM0010E = "NPBM0010E";

    /** Please enter 10 digit merchandise code */
    public static final String NEAM0018E = "NEAM0018E";

    /** Wrong combination: Freight Terms and Service Level.  */
    public static final String NPBM0014E = "NPBM0014E";

    /** Could not retrieve Ship To Address Information. */
    public static final String NPZM0146E = "NPZM0146E";

    /** WH Transfer can not specify the same warehouse. */
    public static final String NPBM0015E = "NPBM0015E";

    // START 2018/06/11 S.Katsuma [QC#26193]
    /** Transfer Qty does not match the number of serial. */
    public static final String NPBM0017E = "NPBM0017E";
    // END 2018/06/11 S.Katsuma [QC#26193]

    // START 06/12/2020 T.Ogura [QC#57002,ADD]
    /** Transfer Qty should be equal to minimum qty or more.(Minimum Qty=[@]) */
    public static final String NPBM0019E = "NPBM0019E";

    /** Transfer Qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
    public static final String NPBM0020E = "NPBM0020E";

    /** Transfer Qty should be multiple of increment qty.(Increment Qty=[@]) */
    public static final String NPBM0021E = "NPBM0021E";
    // END   06/12/2020 T.Ogura [QC#57002,ADD]

    // START 2023/07/04 T.Kuroda [QC#61440, ADD]
    /** Product is not in U00 SubWarehouse, please press save/submit again to continue. */
    public static final String NPBM0025W = "NPBM0025W";
    // END 2023/07/04 T.Kuroda [QC#61440, ADD]

    // =================================================
    // Message Value
    // =================================================
    /**
     * Procurement Order
     */
    public static final String MSG_VALUE_PO = "Procurement Order";

    /**
     * Charge Account
     */
    public static final String MSG_VALUE_CA = "Charge Account";

    /**
     * Request Number
     */
    public static final String MSG_VALUE_PR = "Request Number";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_WAREHOUSE = "0";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_SUB_WAREHOUSE = "1";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_WAREHOUSE = "2";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_SUB_WAREHOUSE = "3";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_WAREHOUSE_FOR_LINE = "4";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_WAREHOUSE_FOR_LINE = "5";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_SUB_WAREHOUSE_FOR_LINE = "6";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_SUB_WAREHOUSE_FOR_LINE = "7";

    // =================================================
    // API Param Value
    // =================================================
    /** Mode : Valuation for Showroom */
    public static final String MODE_VAL_SHRM = "02";

    /**
     * PRCH_REQ_DSPL_UOM_CD : EA
     */
    public static final String PRCH_REQ_DSPL_UOM_CD_EA = "EA";

    /**
     * EXCH_RATE : 1
     */
    public static final BigDecimal EXCH_RATE_1 = BigDecimal.ONE;

    /**
     * Upload Template File Name
     */
    public static final String CSV_FILE_NAME = "InventoryRequestEntry_UploadTemplate";

    /**
     * Upload Template File Extension
     */
    public static final String TMPL_FILE_EXTENSION = ".csv";

    /**
     * Mod QC#22467
     * Upload Template File Header
     */
    public static final String[] CSV_HEADER = new String[] {"Item#", "Transfer Qty", "Source WH Code", "Source SWH Code", "Stock Status Code", "Destination WH Code", "Destination SWH Code", "Ship To Customer Code", "Ship To Customer Name",
            "Charge Account", "Special Instructions" };

    /**
     * Serial Validation API(NLZC4030) : Mode : Outbound
     */
    public static final String SER_VAL_API_MODE_OUTBOUND = "01";

    /**
     * Serial Validation API(NLZC4030) : Return Code : Error
     */
    public static final String SER_VAL_API_RETURN_CODE_ERROR = "9";

    // START 2019/05/17 M.Naito [QC#50076,ADD]
    /**
     * ASTERISK : *
     */
    public static final String ASTERISK = "*";
    // END 2019/05/17 M.Naito [QC#50076,ADD]

    // =================================================
    // index
    // =================================================
    /**
     * IDX_0
     */
    public static final int IDX_0 = 0;

    /**
     * IDX_1
     */
    public static final int IDX_1 = 1;

    /**
     * IDX_2
     */
    public static final int IDX_2 = 2;

    /**
     * IDX_3
     */
    public static final int IDX_3 = 3;

    /**
     * IDX_4
     */
    public static final int IDX_4 = 4;

    /**
     * IDX_5
     */
    public static final int IDX_5 = 5;

    /**
     * IDX_6
     */
    public static final int IDX_6 = 6;

    /**
     * IDX_7
     */
    public static final int IDX_7 = 7;

    /**
     * IDX_8
     */
    public static final int IDX_8 = 8;

    /**
     * IDX_9
     */
    public static final int IDX_9 = 9;

    /**
     * IDX_10
     */
    public static final int IDX_10 = 10;

    /**
     * IDX_11
     */
    public static final int IDX_11 = 11;

    /**
     * IDX_12
     */
    public static final int IDX_12 = 12;

    /**
     * IDX_1000
     */
    public static final int IDX_1000 = 1000;

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    /** The request will be cancelled. OK? */
    public static final String NPAM1359I = "NPAM1359I";
    // QC#56867 Add
    /** The request will be closed. OK? */
    public static final String NPAM1598I = "NPAM1598I";

    // =================================================
    // Var Char Const Name
    // =================================================
    public static final String INVTY_REQ_DEF_SHPG_SVC_LVL_CD = "INVTY_REQ_DEF_SHPG_SVC_LVL_CD";

    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";

    // =================================================
    // Map Key
    // =================================================
    /**
     * Config Item Map Key: PRCH_REQ_LINE_NUM
     */
    public static final String MAP_KEY_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /**
     * Config Item Map Key: PRCH_REQ_LINE_SUB_NUM
     */
    public static final String MAP_KEY_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /**
     * Config Item Map Key: MDSE_CD
     */
    public static final String MAP_KEY_MDSE_CD = "MDSE_CD";

    /**
     * Config Item Map Key: PRCH_REQ_DISP_QTY
     */
    public static final String MAP_KEY_PRCH_REQ_DISP_QTY = "PRCH_REQ_DISP_QTY";

    /**
     * Config Item Map Key: INSTL_BASE_CTRL_FLG
     */
    public static final String MAP_KEY_INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /**
     * Config Item Map Key: MDSE_TP_CTX_TP_CD
     */
    public static final String MAP_KEY_MDSE_TP_CTX_TP_CD = "MDSE_TP_CTX_TP_CD";

    /**
     * Config Item Map Key: SHPG_SER_TAKE_FLG
     */
    public static final String MAP_KEY_SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    // =================================================
    // 9seg
    // =================================================
    /** message parameter : Segment */
    public static final String MSG_PARAM_SEGMENT = "Segment";

    /** message parameter : Company */
    public static final String MSG_PARAM_CMPY = "Company";

    /** message parameter : Extension */
    public static final String MSG_PARAM_EXTN = "Extension";

    /** message parameter : Cost Center */
    public static final String MSG_PARAM_CC = "Cost Center";

    /** message parameter : Account */
    public static final String MSG_PARAM_ACCT = "Account";

    /** message parameter : Company */
    public static final String MSG_PARAM_PROJ = "Project";

    /** message parameter : Product */
    public static final String MSG_PARAM_PROD = "Product";

    /** message parameter : Affiliate */
    public static final String MSG_PARAM_AFFL = "Affiliate";

    /** message parameter : Channel */
    public static final String MSG_PARAM_CH = "Channel";

    /** message parameter : Branch */
    public static final String MSG_PARAM_BR = "Branch";

    /** segment token list index : COA_CMPY_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD = 0;

    /** segment token list index : COA_BR_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD = 1;

    /** segment token list index : COA_CC_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD = 2;

    /** segment token list index : COA_ACCT_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD = 3;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD = 4;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD = 5;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD = 6;

    /** segment token list index : COA_PROJ_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD = 7;

    /** segment token list index : COA_EXTN_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD = 8;

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 4;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 8;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment element length : COA_BR_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_BR = 3;

    /** NPZC610001 Mode code */
    public static final String PROCESS_DEFAULT_BILL_SHIP = "04";

    /**
     * Event Name : Get_DestSwhH
     */
    public static final String EVENT_NM_NPBL0020_GET_DEST_SWH_H = "NPBL0020Scrn00_Get_DestSwhH";

    /**
     * Event Name : Get_DestWhH
     */
    public static final String EVENT_NM_NPBL0020_GET_DEST_WH_H = "NPBL0020Scrn00_Get_DestWhH";

    /**
     * Event Name : Get_ShipToCusthH
     */
    public static final String EVENT_NM_NPBL0020_GET_SHIP_TO_CUST_H = "NPBL0020Scrn00_Get_ShipToCustH";

    /**
     * Event Name : Get_ShipToSplyH
     */
    public static final String EVENT_NM_NPBL0020_GET_SHIP_TO_SPLY_H = "NPBL0020Scrn00_Get_ShipToSplyH";

    /**
     * Event Name : Get_SrcSwhH
     */
    public static final String EVENT_NM_NPBL0020_GET_SRC_SWH_H = "NPBL0020Scrn00_Get_SrcSwhH";

    /**
     * Event Name : Get_SrcWhH
     */
    public static final String EVENT_NM_NPBL0020_GET_SRC_WH_H = "NPBL0020Scrn00_Get_SrcWhH";

    /**
     * Event Name : Get_SrcWhH
     */
    public static final String EVENT_NM_OPEN_WIN_SHIP_TO_ADDR = "NPBL0020Scrn00_OpenWin_ShipToAddr";

    /**
     * Event Name : Return NMAL6760
     */
    public static final String EVENT_NM_NPBL0020_NMAL6760 = "NPBL0020_NMAL6760";

    /**
     * Event Name : Return Detail Tab
     */
    public static final String EVENT_NM_TAB_DETAIL = "NPBL0020Scrn00_TAB_Detail";

    /**
     * Event Name : Return Header 
     */
    public static final String EVENT_NM_TAB_HEADER = "NPBL0020Scrn00_TAB_Header";

    // QC#22517 Add
    /** EVENT_NM_GET_ADDRESS */
    public static final String EVENT_NM_GET_ADDRESS = "NPBL0020Scrn00_GetAddress";

    // 2018/12/12 QC#29456 Add Start
    /**
     * Event Name : NMAL6800
     */
    public static final String EVENT_NM_NPBL0020_NMAL6800 = "NPBL0020_NMAL6800";
    // 2018/12/12 QC#29456 Add End

    /**
     * DB Param Name: Inventory Location Code
     */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /**
     * DB Param Name: Location Status Code
     */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /**
     * DB Param Name: Stock Status Code
     */
    public static final String DB_PARAM_STK_STS_CD = "stkStsCd";

    /** The "Request Qty" is bigger than the "Available Qty". */
    public static final String NPBM0016E = "NPBM0016E";

    // START 2018/11/06 M.Naito [QC#28698,ADD]
    /** The configration data already exists. Please select by [Select from CONFIG]. */
    public static final String NPBM0018E = "NPBM0018E";
    // END 2018/11/06 M.Naito [QC#28698,ADD]

    // START 2019/08/27 M.Naito [QC#52276,ADD]
    /** Stock status of the specified Serial number is different from IB. */
    public static final String NLZM2414E = "NLZM2414E";
    // END 2019/08/27 M.Naito [QC#52276,ADD]

    /**
     * DB Param Name:whTransferTp
     */
    public static final String DB_PARAM_WH_TRANSFER_TP = "whTransferTp";

    /**
     * DB Param Name:whTransferTp
     */
    public static final String DB_PARAM_KITTING_TP = "kittingTp";

    /**
     * NUM_CONST DEF_PRCH_REQ_TP_AOT_DAYS Add QC#22346
     */
    public static final String DEF_PRCH_REQ_TP_AOT_DAYS = "DEF_PRCH_REQ_TP_AOT_DAYS";

    /**
     * DB Column Name: PO_ORD_DTL_LINE_NUM add QC#22481
     */
    public static final String DB_COLUMN_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /**
     * VAR CHAR CONST KEY: NPBL0020_SER_NEED_PRCH_REQ_TP
     */
    public static final String NPBL0020_SER_NEED_PRCH_REQ_TP = "NPBL0020_SER_NEED_PRCH_REQ_TP";

    // QC#52809
    public static final String DB_COLUMN_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DS_COND_CONST_GRP_ID */
    public static final String DS_COND_CONST_GRP_ID = "DS_COND_CONST_GRP_ID";

    /** DS_COND_CONST_VAL_TXT_01 */
    public static final String DS_COND_CONST_VAL_TXT_01 = "DS_COND_CONST_VAL_TXT_01";

    /** DS_COND_CONST_VAL_TXT_02 */
    public static final String DS_COND_CONST_VAL_TXT_02 = "DS_COND_CONST_VAL_TXT_02";

    /** DS_COND_CONST : DS_COND_CONST_GRP_ID : NPBB0010_DS_ORD_TP */
    public static final String NPBB0010_DS_ORD_TP = "NPBB0010_DS_ORD_TP";

    /** DS_COND_CONST : DS_COND_CONST_VAL_TXT_02 : EO */
    public static final String EXPENSE_ORDER = "EO";

    /** . */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** . */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** Blank */
    public static final String BLANK = "";
    // QC#52809

    /**
     * DB Column Name: PO_ORD_DTL_LINE_NUM add QC#55313
     */
    public static final String DB_COLUMN_PO_DISP_QTY = "PO_DISP_QTY";

    /**
     * Event Name : Line Cancel
     */
    public static final String EVENT_NM_NPBL0020_LINE_CLOSE = "NPBL0020Scrn00_LineClose";

    /**
     * NPAM0049E: The details of the process target have not been
     * selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    // START 2021/11/09 R.Azucena[QC#58586, ADD]
    /** NPBM0022E: The "Request Qty" is bigger than "Available Single Item Qty". */
    public static final String NPBM0022E = "NPBM0022E";

    /** RQST_TYPES_SINGLE_ITEM_CHECK - Contains request type codes which performs single item check */
    public static final String RQST_TYPES_SINGLE_ITEM_CHECK = "RQST_TYPES_SINGLE_ITEM_CHECK";
    // END 2021/11/09 R.Azucena[QC#58586, ADD]

    // START 2022/11/16 R.Azucena[QC#60808, ADD]
    /** NPBM0023E: This Config cannot register a request because Service Exchange has not yet been completed. */
    public static final String NPBM0023E = "NPBM0023E";
    // END 2022/11/16 R.Azucena[QC#60808, ADD]

    // START 2023/03/13 R.Azucena[QC#61282, ADD]
    /** NPBM0024E: Allocation has already been done in another Order#, so please complete the other Order. Source Order:@ */
    public static final String NPBM0024E = "NPBM0024E";
    // END 2023/03/13 R.Azucena[QC#61282, ADD]
}
