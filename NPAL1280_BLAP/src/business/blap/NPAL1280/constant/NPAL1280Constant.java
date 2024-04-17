/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1280.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/18/2016   CITS            K.Ogino          Update          QC#3676
 * 02/29/2016   CITS            K.Ogino          Update          QC#4743
 * 03/01/2016   CITS            K.Ogino          Update          QC#4636
 * 03/08/2016   CITS            K.Ogino          Update          QC#5156
 * 04/04/2016   CITS            K.Ogino          Update          QC#5964
 * 11/02/2016   CITS            Y.IWASAKI        Update          QC#15072
 * 01/19/2016   CITS            S.Endo           Update          QC#15717
 * 03/01/2017   CITS            Y.IWASAKI        Update          QC#17487
 * 08/03/2017   CITS            R.Shimamoto      Update          QC#18671
 * 08/23/2017   CITS            H.Naoi           Update          Sol#097(QC#18398)
 * 02/08/2018   CITS            K.Ogino          Update          QC#21169
 * 04/04/2018   CITS            T.Wada           Update          QC#21170
 * 04/16/2018   CITS            K.Fukumura       Update          QC#21170
 * 04/20/2018   CITS            Y.Iwasaki        Update          QC#25019
 * 06/26/2018   CITS            Y.Iwasaki        Update          QC#26548
 * 07/09/2018   CITS            K.Ogino          Update          QC#24918
 * 07/19/2018   CITS            K.Kameoka        Update          QC#26990
 * 08/20/2018   CITS            K.Ogino          Update          QC#27846
 * 08/21/2018   CITS            T.Tokutomi       Update          QC#27655
 * 09/14/2018   CITS            K.Ogino          Update          QC#28216/QC#28143
 * 09/26/2018   CITS            T.Tokutomi       Update          QC#28216
 * 10/25/2018   CITS            T.Tokutomi       Update          QC#28941
 * 10/24/2018   CITS            K.Kameoka        Update          QC#27770
 * 11/12/2018   CITS            T.Tokutomi       Update          QC#28939
 * 11/15/2018   CITS            T.Tokutomi       Update          QC#29155
 * 12/17/2018   Fujitsu         S.Takami         Update          QC#29397
 * 01/11/2019   CITS            T.Tokutomi       Update          QC#28709
 * 2019/01/15   Fujitsu         S.Takami         Update          QC#29778
 * 2019/03/20   Fujitsu         T.Ogura          Update          QC#30769
 * 2019/04/17   CITS            K.Ogino          Update          QC#31087
 * 2019/10/04   CITS            R.Shimamoto      Update          QC#53300
 * 10/18/2019   CITS            K.Ogino          Update          QC#53483
 * 04/23/2021   CITS            M.Furugoori      Update          QC#58645
 * 2022/10/31   Hitachi         N.Takatsu        Update          QC#60604
 * 2022/11/18   Hitachi         M.Kikushima      Update          QC#60605
 * 2022/12/15   CITS            F.Fadriquela     Update          QC#60917
 * 2023/01/06   Hitachi         M.Kikushima      Update          QC#60605
 * 2023/02/02   Hitachi         T.Kuroda         Update          QC#60966
 * 2023/04/05   Hitachi         TZ.Win           Update          QC#60966
 * 2024/01/15   CITS            K.Iwamoto        Update          QC#62443
 * 2024/03/04   CITS            S.Okamoto        Update          QC#62443
 *  </pre>
 */
public class NPAL1280Constant {

    /**
     * Business application id
     */
    public static final String BUSINESS_APPL_ID = "NPAL1280";

    /**
     * Business screen id
     */
    public static final String BUSINESS_SCREEN_ID = "NPAL1280Scrn00";

    /**
     * Init evnet
     */
    public static final String NPAL1280_INIT = "NPAL1280_INIT";

    /**
     * CMN Save event
     */
    public static final String CMN_SAVE = "NPAL1280Scrn00_CMN_Save";

    /**
     * CMN Submit event
     */
    public static final String CMN_SUBMIT = "NPAL1280Scrn00_CMN_Submit";

    /**
     * CMN Reset event
     */
    public static final String NPAL1280_CMN_RESET = "NPAL1280Scrn00_CMN_Reset";

    /**
     * CMN Clear event
     */
    public static final String NPAL1280_CMN_CLEAR = "NPAL1280Scrn00_CMN_Clear";

    /**
     * Copy button event
     */
    public static final String NPAL1280_COPY = "NPAL1280Scrn00_Copy";

    /**
     * Search button event
     */
    public static final String NPAL1280_SEARCH = "NPAL1280Scrn00_Search";

    /**
     * Header cancel button event
     */
    public static final String NPAL1280_HEADER_CANCEL = "NPAL1280Scrn00_HeaderCancel";

    /**
     * Line cancel button event
     */
    public static final String NPAL1280_LINE_CANCEL = "NPAL1280Scrn00_LineCancel";

    /**
     * Add new line button event
     */
    public static final String NPAL1280_ADD_NEW_LINE = "NPAL1280Scrn00_Add_NewLine";

    /**
     * Order qty onBlur event
     */
    public static final String NPAL1280_ON_BLUR_ORDER_QTY = "NPAL1280Scrn00_OnBlur_OrderQty";

    /**
     * Unit price onBlur event
     */
    public static final String NPAL1280_ON_BLUR_UNIT_PRICE = "NPAL1280Scrn00_OnBlur_UnitPrice";

    /**
     * Item code onBlur event
     */
    public static final String NPAL1280_ON_BLUR_ITEM_CD = "NPAL1280Scrn00_OnBlur_ItemCode";

    /**
     * NPAL1010 Popup return event
     */
    public static final String NPAL1280_NPAL1010 = "NPAL1280_NPAL1010";

    /**
     * NMAL6800 Popup return event
     */
    public static final String NPAL1280_NMAL6800 = "NPAL1280_NMAL6800";

    /**
     * CMN ColClear event
     */
    public static final String NPAL1280_CMN_COL_CLEAR = "NPAL1280Scrn00_CMN_ColClear";

    /**
     * CMN ColSave event
     */
    public static final String NPAL1280_CMN_COL_SAVE = "NPAL1280Scrn00_CMN_ColSave";

    /**
     * Pagenext button event
     */
    public static final String NPAL1280_NEXT = "NPAL1280Scrn00_PageNext";

    /**
     * Pageprev button event
     */
    public static final String NPAL1280_PREV = "NPAL1280Scrn00_PagePrev";

    /**
     * CMN Download button event
     */
    public static final String NPAL1280_CMN_DOWNLOAD = "NPAL1280Scrn00_CMN_Download";

    /**
     * Detail tab event
     */
    public static final String NPAL1280_TAB_HEADER = "NPAL1280Scrn00_TAB_Detail";

    /**
     * Addl header tab event
     */
    public static final String NPAL1280_TAB_DETAIL = "NPAL1280Scrn00_TAB_AddlHeader";

    /**
     * Auto create po button event
     */
    public static final String NPAL1280_AUTO_CREATE_PO = "NPAL1280Scrn00_AutoCreatePO";

    /**
     * line type pulldown onchange event
     */
    public static final String NPAL1280_ON_CHANGE_LINE_TYPE = "NPAL1280Scrn00_OnChange_LineType";

    /**
     * Get Mdse Info button event
     */
    public static final String NPAL1280_GET_MDSE_INFO = "NPAL1280Scrn00_Get_MdseInfo";

    /**
     * return from NPAL0170
     */
    public static final String NPAL1280_NPAL0170 = "NPAL1280_NPAL0170";
    
    /**
     * Get Address button event Add QC#28709
     */
    public static final String NPAL1280_GET_ADDRESS = "NPAL1280Scrn00_GetAddress";

    // QC#21170 Start
    /**
     * Detail tab name
     */
    public static final String TAB_DETAIL = "TAB_Detail";

    // QC#21170 End

    // QC#53300 2019/10/04 Add Start
    /** EVENT_NM_GET_SUPPLIER_NAME */
    public static final String EVENT_NM_GET_SUPPLIER_NAME = "NPAL1280Scrn00_Get_SupplierName";
    /** EVENT_NM_GET_SUPPLIER_SITE_NAME */
    public static final String EVENT_NM_GET_SUPPLIER_SITE_NAME = "NPAL1280Scrn00_Get_SupplierSiteName";
    /** EVENT_NM_NPAL1280_GET_SHIP_TO_INFO */
    public static final String EVENT_NM_NPAL1280_GET_SHIP_TO_INFO = "NPAL1280Scrn00_Get_ShipToInfo";
    /** EVENT_NM_NPAL1280_NMAL2550 */
    public static final String EVENT_NM_NPAL1280_GET_SHIP_TO_INFO_CUSTOMER = "NPAL1280Scrn00_Get_ShipToInfoCustomer";
    /** DISPLAY_PRNT_VND_CD */
    public static final String DISPLAY_PRNT_VND_CD = "Supplier";
    /** DISPLAY_VND_CD */
    public static final String DISPLAY_VND_CD = "SupplierSite";
    /** VAR_CHAR_CONST: Po Qlfy Cd for Customer Drop Ship. */
    public static final String VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD = "PO_CUST_DROP_SHIP_QLFY_CD";
    /** CUSA Global Company Code.(Default Value) */
    public static final String DEF_CUSA_GLBL_CMPY_CD = "ABR";

    /**
     * Set name for message.
     */
    public static final String DISPLAY_DEST_RTL_WH_CD = "Destination WH";
    // QC#53300 2019/10/04 Add End
    /**
     * Failed to insert. [@]
     */
    public static final String NPAM1172E = "NPAM1172E";

    /**
     * Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * [@] does not exist in Master.
     */
    public static final String NPAM0076E = "NPAM0076E";

    /**
     * The entered @ does not exist in Master.
     */
    public static final String NPAM1361E = "NPAM1361E";

    /**
     * The combination of specified input parameters [@] and [@] is
     * incorrect.
     */
    public static final String NPAM1363E = "NPAM1363E";

    /**
     * The combination of specified input parameters Supplier, Site
     * and Item does not exist in ASL.
     */
    public static final String NPAM1364E = "NPAM1364E";

    /**
     * For [@], '0' or less than '0' cannot be specified.
     */
    public static final String NPAM0046E = "NPAM0046E";

    /**
     * The relationship between 'Carrier' and 'Shipping Service Level
     * Code' is incorrect.
     */
    public static final String NPAM1365E = "NPAM1365E";

    /**
     * The relationship between 'Freight Term', 'Carrier ' and
     * 'Shipping Service Level Code' is incorrect.
     */
    public static final String NPAM1366E = "NPAM1366E";

    /**
     * If Freight Condition Code is 'Collect', Carrier Account Number
     * must be entered.
     */
    public static final String NPAM1367E = "NPAM1367E";

    /**
     * This data has been updated by another user.
     */
    public static final String NPAM0006E = "NPAM0006E";

    /**
     * ZZM8100I
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * The details of the process target have not been selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * You can add Details up to [@].
     */
    public static final String NPAM0077E = "NPAM0077E";

    /**
     * QC#27655 Update.
     * Since the target MDSE CD is set as "DISCONTINUED"
     */
    public static final String NPAM1627E = "NPAM1627E";

    /**
     * Please Enter @.
     */
    public static final String NAMM0027E = "NAMM0027E";

    /**
     * Please execute again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * The request will be cancelled. OK?
     */
    public static final String NPAM1359I = "NPAM1359I";

    /**
     * Please set up the business days.
     */
//    public static final String NPAM0094E = "NPAM0094E";    // 2019/03/20 T.Ogura [QC#30769,DEL]

    /**
     * Details must have at least one row. Please enter data.
     */
    public static final String NPAM1360E = "NPAM1360E";

    /**
     * @ is a mandatory field.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Please enter the account information when line type is Expense.
     */
    public static final String NPAM1489E = "NPAM1489E";

    /**
     * You can not select multiple line type.
     */
    public static final String NPAM1490E = "NPAM1490E";

    /**
     * Please enter today's or later date.
     */
    public static final String NPAM0079E = "NPAM0079E";

    /**
     * PO will not be created , since this PO Requisition is not in
     * approved status, Please approve before create PO.
     */
    public static final String NPAM1544E = "NPAM1544E";

    /**
     * Multiple records are found with the entered [@] value. Please
     * select from a popup screen.
     */
    public static final String NPAM1589E = "NPAM1589E";

    /**
     * QC#18671 Add. Suppier Item Code is duplicated, Please select
     * another item or review ASL.
     */
    public static final String NPAM1602E = "NPAM1602E";

    /**
     * NPZM0268E:Supplier Item Number cannot be obtained from ASL Table.
     */
    public static final String NPZM0268E = "NPZM0268E";
    
    // QC#28939 Add Message.
    /**
     * NPAM1611E: Blank Item# have exceeded TEXT ITEM in Item Master.
     */
    public static final String NPAM1611E = "NPAM1611E";

    // START 2022/10/31 N.Takatsu[QC#60604, ADD]
    /**
     * The specified "Destination Warehouse" is Manual Drop Ship and the "Ship to Custormer" is CSA WH. Please check if it is correct.
     */
    public static final String NPAM1655W = "NPAM1655W";
    // END 2022/10/31 N.Takatsu[QC#60604, ADD]

 // QC#28400 del start
    /**
     * Default Account Search Cd
     */
 //  public static final String APP_FUNC_ID_GOODS = "NPAL12802001";

    /**
     * Default Account Search Cd
     */
 //   public static final String APP_FUNC_ID_ASSETS = "NPAL12802002";
    // QC#28400 del end
    /**
     * Exch rate
     */
    public static final BigDecimal EXCH_RATE_100 = new BigDecimal(100);

    /**
     * MULTIPLE
     */
    public static final String MULTIPLE = "MULTIPLE";

    /**
     * CSA_RET_ADDR
     */
    public static final String CSA_RET_ADDR = "CSA Return Address";

    /**
     * ORDER_QTY_D
     */
    public static final String ORDER_QTY_D = "Order Qty";

    /**
     * ASTARISK
     */
    public static final String ASTARISK = "*";

    /**
     * ITEM_D
     */
    public static final String ITEM_D = "Item#";

    /**
     * CARRIER_D
     */
    public static final String CARRIER_D = "Carrier";

    /**
     * SUPPLIER_D
     */
    public static final String SUPPLIER_D = "Supplier";

    /**
     * SITE_D
     */
    public static final String SITE_D = "Site";

    /**
     * UNIT_PRICE_D
     */
    public static final String UNIT_PRICE_D = "Unit Price";

    /**
     * ITEM_NUMBER_TXT
     */
    public static final String ITEM_NUMBER_TXT = "Item Number";

    /** DEST_WH_CD */
    public static final String DEST_WH_CD = "Destination WH Code";

    /**
     * PRCH_REQ_ENT
     */
    public static final String PRCH_REQ_ENT = "PO Requisition Entry";

    /**
     * CPO_DTL_LINE_SUB_NUM_000
     */
    public static final String CPO_DTL_LINE_SUB_NUM_000 = "000";

    /**
     * FIRST_DTL_LINE_NUM
     */
    public static final String FIRST_DTL_LINE_NUM = "001";

    /**
     * PRCH_REC_NUM_SQ
     */
    public static final String PRCH_REC_NUM_SQ = "PRCH_REQ_NUM_SQ";

    /**
     * PO_ACRL_ACCT_TP
     */
    public static final String PO_ACRL_ACCT_TP = "PO_ACRL_ACCT_TP";

    /**
     * Var char const : CUST_DROP_SHIP_PO_QULF
     */
    public static final String CONST_CUST_DROP_SHIP_PO_QULF = "CUST_DROP_SHIP_PO_QULF";

    /** PO Message */
    public static final String PO_MSG = "PO Message";

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

    /** CSV_FILE_NAME */
    public static final String CSV_FILE_NAME = "NPAL1280_PORequisitionEntry";

    /** EXTN_CSV */
    public static final String EXTN_CSV = ".csv";

    /** CSV_HDR_H1 */
    public static final String CSV_HDR_H1 = "Requisition Number";

    /** CSV_HDR_H2 */
    public static final String CSV_HDR_H2 = "Requisition Type";

    /** CSV_HDR_H3 */
    public static final String CSV_HDR_H3 = "Requisition Status";

    /** CSV_HDR_H4 */
    public static final String CSV_HDR_H4 = "Approval Status";

    /** CSV_HDR_H24 */
    public static final String CSV_HDR_H24 = "Date Created";

    /** CSV_HDR_H5 */
    public static final String CSV_HDR_H5 = "Date & Time Needed (Date)";

    /** CSV_HDR_H6 */
    public static final String CSV_HDR_H6 = "Date & Time Needed (Time)";

    /** CSV_HDR_H7 */
    public static final String CSV_HDR_H7 = "Document Source Type";

    /** CSV_HDR_H8 */
    public static final String CSV_HDR_H8 = "Source Document#";

    /** CSV_HDR_H9 */
    public static final String CSV_HDR_H9 = "Qualifier";

    /** CSV_HDR_H10 */
    public static final String CSV_HDR_H10 = "Buyer";

    /** CSV_HDR_H11 */
    public static final String CSV_HDR_H11 = "Requester";

    /** CSV_HDR_H12 */
    public static final String CSV_HDR_H12 = "Planning Group";

    /** CSV_HDR_H13 */
    public static final String CSV_HDR_H13 = "Description";

    /** CSV_HDR_H14 */
    public static final String CSV_HDR_H14 = "Special Instructions";

    /** CSV_HDR_H15 */
    public static final String CSV_HDR_H15 = "Supplier Code";

    /** CSV_HDR_H16 */
    public static final String CSV_HDR_H16 = "Supplier Name";

    /** CSV_HDR_H17 */
    public static final String CSV_HDR_H17 = "Supplier Site Code";

    /** CSV_HDR_H18 */
    public static final String CSV_HDR_H18 = "Supplier Site Name";

    /** CSV_HDR_H19 */
    public static final String CSV_HDR_H19 = "Destination WH Code";

    /** CSV_HDR_H20 */
    public static final String CSV_HDR_H20 = "Destination WH Name";

    /** CSV_HDR_H21 */
    public static final String CSV_HDR_H21 = "Destination SWH Code";

    /** CSV_HDR_H22 */
    public static final String CSV_HDR_H22 = "Destination SWH Name";

    /** CSV_HDR_H25 */
    public static final String CSV_HDR_H25 = "Ship To Location Name";

    /** CSV_HDR_H23 */
    public static final String CSV_HDR_H23 = "Currency";

    // 08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /** CSV_HDR_H26 */
    public static final String CSV_HDR_H26 = "Plan Name";
    // 08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
    /** CSV_HDR_H27 */
    public static final String CSV_HDR_H27 = "Vendor Ship Date";
    // END   2023/02/03 T.Kuroda [QC#60966, ADD]

    /** CSV_HDR_2 */
    public static final String CSV_HDR_2 = "Line#";

    /** CSV_HDR_3 */
    public static final String CSV_HDR_3 = "Line Type";

    /** CSV_HDR_4 */
    public static final String CSV_HDR_4 = "Item#";

    /** CSV_HDR_5 */
    public static final String CSV_HDR_5 = "Supplier Item# (Pin)";

    /** CSV_HDR_6 */
    public static final String CSV_HDR_6 = "Item Description";

    /** CSV_HDR_7 */
    public static final String CSV_HDR_7 = "Order Qty";

    /** CSV_HDR_8 */
    public static final String CSV_HDR_8 = "UOM ";

    /** CSV_HDR_9 */
    public static final String CSV_HDR_9 = "Supplier ";

    /** CSV_HDR_10 */
    public static final String CSV_HDR_10 = "Site ";

    /** CSV_HDR_11 */
    public static final String CSV_HDR_11 = "Status ";

    /** CSV_HDR_12 */
    public static final String CSV_HDR_12 = "Unit Price ";

    /** CSV_HDR_13 */
    public static final String CSV_HDR_13 = "Ext. Price ";

    /** CSV_HDR_14 */
    public static final String CSV_HDR_14 = "Charge Account";

    /** CSV_HDR_15 */
    public static final String CSV_HDR_15 = "MT";

    /** CSV_HDR_16 */
    public static final String CSV_HDR_16 = "PC";

    /** CSV_HDR_17 */
    public static final String CSV_HDR_17 = "Text";

    /** CSV_HDR_18 */
    public static final String CSV_HDR_18 = "Source Line #";

    /** CSV_HDR_19 */
    public static final String CSV_HDR_19 = "Add to PO";

    /** CSV_HDR_20 */
    public static final String CSV_HDR_20 = "Scheduled Create PO Date";

    /** CSV_HDR_21 */
    public static final String CSV_HDR_21 = "PO#";

    /** CSV_HDR_22 */
    public static final String CSV_HDR_22 = "PO Line#";

    /** CSV_HDR_23 */
    public static final String CSV_HDR_23 = "PO Create Date";

    /** CSV_HDR_24 */
    public static final String CSV_HDR_24 = "Release Error Message";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_TP_NM = "PRCH_REQ_TP_NM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /** DB Result Column */
    public static final String RS_PRCH_GRP_CD = "PRCH_GRP_CD";

    /** DB Result Column */
    public static final String RS_PRCH_GRP_DESC_TXT = "PRCH_GRP_DESC_TXT";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_TP_DESC_TXT = "PRCH_REQ_LINE_TP_DESC_TXT";

    /** DB Result Column */
    public static final String RS_COA_CMPY_CD = "COA_CMPY_CD";

    /** DB Result Column */
    public static final String RS_COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Result Column */
    public static final String RS_COA_CC_CD = "COA_CC_CD";

    /** DB Result Column */
    public static final String RS_COA_PROD_CD = "COA_PROD_CD";

    /** DB Result Column */
    public static final String RS_COA_CH_CD = "COA_CH_CD";

    /** DB Result Column */
    public static final String RS_COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Result Column */
    public static final String RS_COA_EXTN_CD = "COA_EXTN_CD";

    /** DB Result Column */
    public static final String RS_PRNT_VND_CD = "PRNT_VND_CD";

    /** DB Result Column */
    public static final String RS_PRNT_VND_NM = "PRNT_VND_NM";

    /** DB Result Column */
    public static final String RS_VND_CD = "VND_CD";

    /** DB Result Column */
    public static final String RS_VND_NM = "VND_NM";

    /** DB Result Column */
    public static final String RS_LOC_NM = "LOC_NM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_STS_NM = "PRCH_REQ_STS_NM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_APVL_STS_NM = "PRCH_REQ_APVL_STS_NM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";

    /** DB Result Column */
    public static final String RS_RQST_RCV_TM = "RQST_RCV_TM";

    /** DB Result Column */
    public static final String RS_RQST_RCV_DT = "RQST_RCV_DT";

    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
    /** DB Result Column */
    public static final String RS_RQST_SHIP_DT = "RQST_SHIP_DT";
    // END   2023/02/03 T.Kuroda [QC#60966, ADD]

    /** DB Result Column */
    public static final String RS_PRCH_REQ_SRC_TP_NM = "PRCH_REQ_SRC_TP_NM";

    /** DB Result Column */
    public static final String RS_TRX_REF_NUM = "TRX_REF_NUM";

    /** DB Result Column */
    public static final String RS_PO_QLFY_CD = "PO_QLFY_CD";

    // 08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /** DB Result Column */
    public static final String RS_MRP_PLN_NM = "MRP_PLN_NM";

    // 08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /** DB Result Column */
    public static final String RS_PRCH_REQ_CRAT_BY_NM = "PRCH_REQ_CRAT_BY_NM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_RQST_BY_PSN_CD = "PRCH_REQ_RQST_BY_PSN_CD";

    /** DB Result Column */
    public static final String RS_FULL_PSN_NM = "FULL_PSN_NM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_CMNT_TXT = "PRCH_REQ_CMNT_TXT";

    /** DB Result Column */
    public static final String RS_SPCL_INSTN_CMNT_TXT = "SPCL_INSTN_CMNT_TXT";

    /** DB Result Column */
    public static final String RS_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** DB Result Column */
    public static final String RS_RTL_WH_NM = "RTL_WH_NM";

    /** DB Result Column */
    public static final String RS_DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** DB Result Column */
    public static final String RS_RTL_SWH_NM = "RTL_SWH_NM";

    /** DB Result Column */
    public static final String RS_XX_LOC_NM = "XX_LOC_NM";

    /** DB Result Column */
    public static final String RS_CCY_CD = "CCY_CD";

    /** DB Result Column */
    public static final String RS_SHIP_TO_LOC_NM_HS = "SHIP_TO_LOC_NM_HS";

    /** DB Result Column */
    public static final String RS_SHIP_TO_ADDL_LOC_NM_HS = "SHIP_TO_ADDL_LOC_NM_HS";

    /** DB Result Column */
    public static final String RS_XX_ALL_LINE_ADDR_HS = "XX_ALL_LINE_ADDR_HS";

    /** DB Result Column */
    public static final String RS_SHIP_TO_POST_CD_HS = "SHIP_TO_POST_CD_HS";

    /** DB Result Column */
    public static final String RS_SHIP_TO_CTY_ADDR_HS = "SHIP_TO_CTY_ADDR_HS";

    /** DB Result Column */
    public static final String RS_SHIP_TO_CNTY_NM_HS = "SHIP_TO_CNTY_NM_HS";

    /** DB Result Column */
    public static final String RS_SHIP_TO_ST_CD_HS = "SHIP_TO_ST_CD_HS";

    /** DB Result Column */
    public static final String RS_SHIP_TO_PROV_NM_HS = "SHIP_TO_PROV_NM_HS";

    /** DB Result Column */
    public static final String RS_CTRY_NM_HS = "CTRY_NM_HS";

    /** DB Result Column */
    public static final String RS_CTAC_PSN_NM_HS = "CTAC_PSN_NM_HS";

    /** DB Result Column */
    public static final String RS_FRT_COND_CD_HF = "FRT_COND_CD_HF";

    /** DB Result Column */
    public static final String RS_SHPG_SVC_LVL_CD_HF = "SHPG_SVC_LVL_CD_HF";

    /** DB Result Column */
    public static final String RS_CARR_CD_HF = "CARR_CD_HF";

    /** DB Result Column */
    public static final String RS_LOC_NM_HF = "LOC_NM_HF";

    /** DB Result Column */
    public static final String RS_CARR_ACCT_NUM_HF = "CARR_ACCT_NUM_HF";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_REL_ERR_MSG_TXT_HP = "PRCH_REQ_REL_ERR_MSG_TXT_HP";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_REL_DT_TM_TS_HP = "PRCH_REQ_REL_DT_TM_TS_HP";

    /** DB Result Column */
    public static final String RS_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Result Column */
    public static final String RS_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_SAVED_FLG = "PRCH_REQ_SAVED_FLG";

    /** DB Result Column */
    public static final String RS_OPEN_STS_FLG_PR = "OPEN_STS_FLG_PR";

    /** DB Result Column */
    public static final String RS_XX_LINE_NUM = "XX_LINE_NUM";

    /** DB Result Column */
    public static final String RS_MDSE_CD = "MDSE_CD";

    /** DB Result Column */
    public static final String RS_ASL_MDSE_CD = "ASL_MDSE_CD";

    /** DB Result Column */
    public static final String RS_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_DISP_QTY = "PRCH_REQ_DISP_QTY";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_DSPL_UOM_CD = "PRCH_REQ_DSPL_UOM_CD";

    /** DB Result Column */
    public static final String RS_PRNT_VND_NM_A1 = "PRNT_VND_NM_A1";

    /** DB Result Column */
    public static final String RS_LOC_NM_A1 = "LOC_NM_A1";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_STS_NM = "PRCH_REQ_LINE_STS_NM";

    /** DB Result Column */
    public static final String RS_ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** DB Result Column */
    public static final String RS_XX_EXT_PRICE = "XX_EXT_PRICE";

    /** DB Result Column */
    public static final String RS_DTL_RQST_RCV_DT = "DTL_RQST_RCV_DT";

    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
    /** DB Result Column */
    public static final String RS_DTL_RQST_SHIP_DT = "DTL_RQST_SHIP_DT";
    // END   2023/02/03 T.Kuroda [QC#60966, ADD]

    /** DB Result Column */
    public static final String RS_PO_SUBMT_TS = "PO_SUBMT_TS";

    /** DB Result Column */
    public static final String RS_XX_CHG_ACCOUNT = "XX_CHG_ACCOUNT";

    /** DB Result Column */
    public static final String RS_COA_MDSE_TP_CD = "COA_MDSE_TP_CD";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /** DB Result Column */
    public static final String RS_TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** DB Result Column */
    public static final String RS_DPLY_LINE_NUM = "DPLY_LINE_NUM";

    /** DB Result Column */
    public static final String RS_REL_RQST_TO_PO_ORD_NUM = "REL_RQST_TO_PO_ORD_NUM";

    /** DB Result Column */
    public static final String RS_PO_SCHD_REL_DT = "PO_SCHD_REL_DT";

    /** DB Result Column */
    public static final String RS_PO_ORD_NUM = "PO_ORD_NUM";

    /** DB Result Column */
    public static final String RS_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_REL_DT_TM_TS = "PRCH_REQ_REL_DT_TM_TS";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_REL_ERR_MSG_TXT = "PRCH_REQ_REL_ERR_MSG_TXT";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** DB Result Column */
    public static final String RS_PO_MDSE_CMPSN_TP_CD = "PO_MDSE_CMPSN_TP_CD";

    /** DB Result Column */
    public static final String RS_PRNT_VND_CD_A1 = "PRNT_VND_CD_A1";

    /** DB Result Column */
    public static final String RS_VND_CD_A1 = "VND_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_CMPY_CD_A1 = "COA_CMPY_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_AFFL_CD_A1 = "COA_AFFL_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_BR_CD_A1 = "COA_BR_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_CH_CD_A1 = "COA_CH_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_CC_CD_A1 = "COA_CC_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_ACCT_CD_A1 = "COA_ACCT_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_PROJ_CD_A1 = "COA_PROJ_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_EXTN_CD_A1 = "COA_EXTN_CD_A1";

    /** DB Result Column */
    public static final String RS_COA_PROD_CD_A1 = "COA_PROD_CD_A1";

    /** DB Result Column */
    public static final String RS_PRNT_CMPY_SET_MDSE_FLG = "PRNT_CMPY_SET_MDSE_FLG";

    /** DB Result Column */
    public static final String RS_EZUPTIME_AH = "EZUPTIME_AH";

    /** DB Result Column */
    public static final String RS_EZUPTIMEZONE_AH = "EZUPTIMEZONE_AH";

    /** DB Result Column */
    public static final String RS_EZUPTIME_AD = "EZUPTIME_AD";

    /** DB Result Column */
    public static final String RS_EZUPTIMEZONE_AD = "EZUPTIMEZONE_AD";

    /** DB Result Column */
    public static final String RS_OPEN_STS_FLG_PRD = "OPEN_STS_FLG_PRD";

    /** DB Result Column */
    public static final String RS_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** DB Result Column */
    public static final String RS_CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** DB Result Column */
    public static final String RS_RTRN_SHIP_TO_RTL_WH_CD = "RTRN_SHIP_TO_RTL_WH_CD";

    /** DB Result Column */
    public static final String RS_XX_ALL_LINE_ADDR = "XX_ALL_LINE_ADDR";

    /** DB Result Column */
    public static final String RS_POST_CD = "POST_CD";

    /** DB Result Column */
    public static final String RS_CTY_ADDR = "CTY_ADDR";

    /** DB Result Column */
    public static final String RS_ST_CD = "ST_CD";

    /** DB Result Column */
    public static final String RS_PROV_NM = "PROV_NM";

    /** DB Result Column */
    public static final String RS_ADDL_LOC_NM = "ADDL_LOC_NM";

    /** DB Result Column */
    public static final String RS_VND_PK = "VND_PK";

    /** DB Result Column */
    public static final String RS_RDD_DT = "RDD_DT";

    /** DB Result Column */
    public static final String RS_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB Result Column */
    public static final String RS_DS_CPO_CONFIG_PK = "DS_CPO_CONFIG_PK";

    /** DB Result Column */
    public static final String RS_CNTY_NM = "CNTY_NM";

    /** DB Result Column */
    public static final String RS_SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /** DB Result Column */
    public static final String RS_CTRY_NM = "CTRY_NM";

    /** DB Result Column */
    public static final String RS_SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** DB Result Column */
    public static final String RS_VND_UOM_CD = "VND_UOM_CD";

    /** DB Result Column */
    public static final String RS_ASL_DTL_PK = "ASL_DTL_PK";

    /** DB Result Column */
    public static final String RS_UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** DB Result Column */
    public static final String RS_MAX_LINE_NUM = "MAX_LINE_NUM";

    /** DB Result Column */
    public static final String RS_CHILD_MDSE_QTY = "CHILD_MDSE_QTY";

    /** DB Result Column */
    public static final String RS_ORD_QTY = "ORD_QTY";

    /** DB Result Column */
    public static final String RS_PRCH_AVAL_FLG = "PRCH_AVAL_FLG";

    /** DB Result Column */
    public static final String RS_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Result Column */
    public static final String RS_CTAC_PERSON = "CTAC_PERSON";

    /** DB Result Column */
    public static final String RS_PRF_CARR_CD = "PRF_CARR_CD";

    /** DB Result Column */
    public static final String RS_CARR_NM = "CARR_NM";

    // QC#28941 Add
    /** DB Result Column */
    public static final String RS_FRT_COND_CD = "FRT_COND_CD";
    // QC#28941 Add
    /** DB Result Column */
    public static final String RS_REQ_SHPG_SVC_LVL_CD = "REQ_SHPG_SVC_LVL_CD";
    // QC#28941 Add
    /** DB Result Column */
    public static final String RS_RQST_CARR_CD = "RQST_CARR_CD";
    // QC#28941 Add
    /** DB Result Column */
    public static final String RS_CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** DB Result Column */
    public static final String RS_PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_CRAT_TS = "XX_REC_HIST_CRAT_TS";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_CRAT_BY_NM = "XX_REC_HIST_CRAT_BY_NM";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_UPD_TS = "XX_REC_HIST_UPD_TS";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_UPD_BY_NM = "XX_REC_HIST_UPD_BY_NM";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_TBL_NM = "XX_REC_HIST_TBL_NM";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_CRAT_TS_A1 = "XX_REC_HIST_CRAT_TS_A1";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_CRAT_BY_NM_A1 = "XX_REC_HIST_CRAT_BY_NM_A1";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_UPD_TS_A1 = "XX_REC_HIST_UPD_TS_A1";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_UPD_BY_NM_A1 = "XX_REC_HIST_UPD_BY_NM_A1";

    /** DB Result Who Column */
    public static final String XX_REC_HIST_TBL_NM_A1 = "XX_REC_HIST_TBL_NM_A1";

    /** DB Result Column */
    public static final String PO_MSG_PK = "PO_MSG_PK";

    /** DB Result Column */
    public static final String PO_MSG_SEG_ID = "PO_MSG_SEG_ID";

    /** DB Result Column */
    public static final String PO_MSG_SUBMT_PSN_CD = "PO_MSG_SUBMT_PSN_CD";

    /** DB Result Column */
    public static final String PO_MSG_TXT = "PO_MSG_TXT";

    /** DB Result Column */
    public static final String RS_CM_DEF_ACCT_CD = "RS_CM_DEF_ACCT_CD";

    // START 2022/11/18 [QC#60605,ADD]
    /** DB Result Column */
    public static final String RS_LINE_TP = "RS_LINE_TP";
    // END 2022/11/18 [QC#60605,ADD]

    /** Bind parameter name */
    public static final String BIND_GLBL_CMPY_CD_01 = "glblCmpyCd01";

    /** Bind parameter name */
    public static final String BIND_MDSE_CD_01 = "mdseCd01";

    /** Bind parameter name */
    public static final String BIND_CARR_CD_01 = "carrCd01";

    /** Bind parameter name */
    public static final String BIND_CARR_NM_01 = "carrNm01";

    /** Bind parameter name */
    public static final String BIND_SHPG_SVC_LVL_CD_01 = "shpgSvcLvlCd01";

    /** Bind parameter name */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** Bind parameter name */
    public static final String BIND_PO_REQUISITION = "poRequisition";

    /** Bind parameter name */
    public static final String BIND_SCR_ENT_AVAL_FLG = "scrEntAvalFlg";

    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /** Bind parameter name */
    public static final String BIND_SHIP_TO_ST_CD = "shipToStCd";

    /** Bind parameter name */
    public static final String BIND_FROM_SHIP_TP_POST_CD = "fromShipToPostCd";

    /** Bind parameter name */
    public static final String BIND_TO_SHIP_TP_POST_CD = "toShipToPostCd";

    /** Bind parameter name */
    public static final String BIND_RTL_WH_CD = "rtlWhCd";

    /** Bind parameter name */
    public static final String BIND_SHIP_TO_CUST_CD = "shipToCustCd";

    /** Bind parameter name */
    public static final String BIND_APP_FUNC_ID = "appFuncId";

    /** Bind parameter name */
    public static final String BIND_BILL_TO_CUST_CD = "billToCustCd";

    /** Bind parameter name */
    public static final String BIND_PRNT_VND_CD = "prntVndCd";

    /** Bind parameter name */
    public static final String BIND_PRNT_VND_NM = "prntVndNm";

    /** Bind parameter name */
    public static final String BIND_LOC_NM = "locNm";

    /** Bind parameter name */
    public static final String BIND_VND_CD = "vndCd";

    /** Bind parameter name */
    public static final String BIND_VND_NM = "vndNm";

    /** Bind parameter name */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** Bind parameter name */
    public static final String BIND_SALES_DATE = "salesDate";

    /** Bind parameter name */
    public static final String BIND_RGTN_STS_CD = "rgtnStsCd";

    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_NUM = "prchReqNum";

    // START 2022/12/15 F.Fadriquela [QC#60917, ADD]
    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_LINE_STS_CD = "prchReqLineStsCd";
    // END 2022/12/15 F.Fadriquela [QC#60917, ADD]

    /** Bind parameter name */
    public static final String BIND_PO_MSG_TP_CD = "poMsgTpCd";

    /** Bind parameter name */
    public static final String BIND_COA_CMPY_CD = "coaCmpyCd";

    /** Bind parameter name */
    public static final String BIND_COA_AFFL_CD = "coaAfflCd";

    /** Bind parameter name */
    public static final String BIND_COA_CC_CD = "coaCcCd";

    /** Bind parameter name */
    public static final String BIND_COA_PROD_CD = "coaProdCd";

    /** Bind parameter name */
    public static final String BIND_COA_CH_CD = "coaChCd";

    /** Bind parameter name */
    public static final String BIND_COA_PROJ_CD = "coaProjCd";

    /** Bind parameter name */
    public static final String BIND_COA_EXTN_CD = "coaExtnCd";

    /** Bind parameter name */
    public static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    /** Bind parameter name */
    public static final String BIND_PRNT_CMPY_SET_MDSE_FLG = "prntCmpySetMdseFlg";

    /** Bind parameter name */
    public static final String BIND_COA_PROJ_ACCT_TP_CD = "coaProjAcctTpCd";

    /** Bind parameter name */
    public static final String BIND_CPO_DTL_LINE_NUM = "cpoDtlLineNum";

    /** Bind parameter name */
    public static final String BIND_CPO_DTL_LINE_SUB_NUM = "cpoDtlLineSubNum";

    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_LINE_TO_CD_GOODS = "prchReqLineTpCdGoods";

    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_LINE_TO_CD_ASSETS = "prchReqLineTpCdAssets";

    /** Bind parameter name */
    public static final String BIND_DS_CPO_CONFIG_PK = "dsCpoConfigPk";

    /** Bind parameter name */
    public static final String BIND_FRT_COND_CD = "frtCondCd";

    /** Bind parameter name */
    public static final String BIND_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /** Bind parameter name */
    public static final String BIND_CARR_SVC_LVL_CD = "carrSvcLvlCd";

    /** Bind parameter name */
    public static final String BIND_LINE_BIZ_TP_CD = "lineBizTpCd";

    /** Bind parameter name */
    public static final String BIND_ROW_NUM = "rowNum";

    /** Bind parameter name */
    public static final String BIND_PO_ACCT_TP_CD = "poAcctTpCd";

    /** Bind parameter name */
    public static final String BIND_DEST_WH_CD = "destWhCd";

    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_LINE_TP_CD = "prchReqLineTpCd";

    /** Bind parameter name */
    public static final String BIND_MDSE_CMPSN_TP_CD = "mdseCmpsnTpCd";

    /** Bind parameter name */
    public static final String BIND_CM_DEF_ACCT_CD = "cmDefAcctCd";

    /** Bind parameter name */
    public static final String BIND_LOC_TP_CD = "locTpCd";

    /** Bind parameter name */
    public static final String BIND_PRCH_REQ_APVL_FLG = "prchRedApvlFlg";

    //QC#28939 Add.
    /** Bind parameter name */
    public static final String BIND_MDSE_ITEM_TP_TEXTITEM = "mdseItemTpTextItem";

    //QC#28709 Add.
    /** Bind parameter name */
    public static final String BIND_POST_CD = "postCd";

    // START 2022/11/18 [QC#60605,ADD]
    /** Bind parameter name */
    public static final String BIND_COA_MDSE_TP_CD = "coaMdseTpCd";

    /** Bind parameter name */
    public static final String BIND_INVTY_CTRL_FLG = "InvtyCtrlFlg";

    /** Bind parameter name */
    public static final String BIND_LINE_TP_EXPENSE = "lineTpExpense";

    /** Bind parameter name */
    public static final String BIND_LINE_TP_GOODS = "lineTpGoods";

    // END 2022/11/18 [QC#60605,ADD]

    // START 2023/01/06 M.Kikushima[QC#60605, ADD]
    /** Bind parameter name */
    public static final String BIND_PRNT_CMPY_SET_MDSE_FLG_Y = "prntCmpySetMdseFlgY";
    // END 2023/01/06 M.Kikushima[QC#60605, ADD]

    /** AM/PM Pulldown Code */
    public static final String AM_PM_PULLDOWN_CD_AM = "0";

    /** AM/PM Pulldown Code */
    public static final String AM_PM_PULLDOWN_CD_PM = "1";

    /** AM/PM Pulldown Name */
    public static final String AM_PM_PULLDOWN_NM_AM = "AM";

    /** AM/PM Pulldown Code */
    public static final String AM_PM_PULLDOWN_NM_PM = "PM";

    /**
     * REQNUM_OR_ORDER_REL
     */
    public static final String REQNUM_OR_ORDER_REL = "Requisition Number or Order#";

    /** CUST_DROP_SHIP_PO_QULF */
    public static final String CUST_DROP_SHIP_PO_QULF = "CUST_DROP_SHIP_PO_QULF";

    /** TECH_INSRC_PO_QLFY_CD */
    public static final String TECH_INSRC_PO_QLFY_CD = "TECH_INSRC_PO_QLFY_CD";

    /**
     * update mode
     */
    public static final String UPDATE_MODE = "2";

    /**
     * cancel mode
     */
    public static final String CANCEL_MODE = "3";

    /**
     * index 0
     */
    public static final int IDX_0 = 0;

    /**
     * index 1
     */
    public static final int IDX_1 = 1;

    /**
     * index 2
     */
    public static final int IDX_2 = 2;

    /**
     * index 3
     */
    public static final int IDX_3 = 3;

    /**
     * index 4
     */
    public static final int IDX_4 = 4;

    /**
     * index 5
     */
    public static final int IDX_5 = 5;

    /**
     * index 6
     */
    public static final int IDX_6 = 6;

    /**
     * index 7
     */
    public static final int IDX_7 = 7;

    /**
     * index 8
     */
    public static final int IDX_8 = 8;

    /**
     * index 10
     */
    public static final int IDX_10 = 10;

    /**
     * index 12
     */
    public static final int IDX_12 = 12;

    /**
     * index 22
     */
    public static final int IDX_22 = 22;

    /**
     * Padding ZERO
     */
    public static final String ZERO = "0";

    /**
     * MDESE_8_DIGIT 8
     */
    public static final int MDESE_8_DIGIT = 8;

    /**
     * Set name for message.
     */
    public static final String LINE_TYPE_D = "Line Type";

    /**
     * Set name for message.
     */
    public static final String MDSE_CD_D = "Item#";

    /**
     * Set name for message.
     */
    public static final String CHARGE_ACCOUNT_D = "Charge Account";

    /**
     * Set name for message.
     */
    public static final String DATE_NEEDED = "Date & Time Needed";

    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
    /**
     * Set name for message.
     */
    public static final String VENDOR_SHIP_DATE = "Vendor Ship Date";
    // END   2023/02/03 T.Kuroda [QC#60966, ADD]

    /** ZZZM9003I */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** For SET items, Please include the Parent Details. */
    public static final String NPAM1588E = "NPAM1588E";

    /**
     * NPAM0008E: Your request cannot be processed under this status.
     */
    public static final String NPAM0008E = "NPAM0008E";

    /** The format of [@] is incorrect. */
    public static final String NPAM1193E = "NPAM1193E";

    /** Maximum number of digits exceeded.[@] */
    public static final String NPAM1320E = "NPAM1320E";

    /**
     * .
     */
    public static final String DOT = ".";

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";

    /** PR_SVC_LVL_WH_OWNR_CD */
    public static final String VAR_CHAR_PR_SVC_LVL_WH_OWNR_CD = "PR_SVC_LVL_WH_OWNR_CD";

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
     * Apply button event Add QC#21169
     */
    public static final String NPAL1280_APPLY = "NPAL1280Scrn00_Apply";

    // START 2023/02/02 T.Kuroda T.Kuroda [QC#60966, ADD]
    /**
     * Vendor Ship Date Apply button event
     */
    public static final String NPAL1280_RQSTSHIPDTAPPLY = "NPAL1280Scrn00_RqstShipDt_Apply";
    // END   2023/02/02 T.Kuroda T.Kuroda [QC#60966, ADD]

    // QC#21170 Add S
    public static final String RS_VND_LT_DAYS_NUM = "VND_LT_DAYS_NUM";

    // START 2023/02/02 T.Kuroda T.Kuroda [QC#60966, ADD]
    public static final String RS_VND_SHIP_LT_DAYS_NUM = "VND_SHIP_LT_DAYS_NUM";
    // END   2023/02/02 T.Kuroda T.Kuroda [QC#60966, ADD]

    /**
     * Time format check pattern
     */
    public static final String CHK_TIME_PATTERN = "(0[0-9]|1[0-1]):[0-5][0-9]";

    /**
     * Time format charactor
     */
    public static final String COLON = ":";

    /**
     * [@] is not selected.
     */
    public static final String NLZM2274E = "NLZM2274E";

    /**
     * Time is invalid. Please use a valid format, [hh:mm]
     */
    public static final String NPAM1515E = "NPAM1515E";

    /**
     * AM/PM Time check val
     */
    public static final int TIME_CHECK_VAL = 1200;

    /**
     * Time format error parameter
     */
    public static final String TIME_FORMAT = "12:00";

    /**
     * Please enter the details of the customized parts. QC#24918
     */
    public static final String NPAM1579E = "NPAM1579E";

    /**
     * VAR_CONST: CREATE_MATERIAL_PARTS. QC#24918
     */
    public static final String VAR_CONST_CREATE_MATERIAL_PARTS = "CREATE_MATERIAL_PARTS";
    
    //QC#26990 Add Start
    /**
     * Apply button event Add QC#21169
     */
    public static final String NPAL1280_NMAL6760 = "NPAL1280_NMAL6760";

    /** Var char key : MANUAL_DROPSHIP_WAREHOUSE_CD string */
    public static final String VAR_CHAR_KEY_MANUAL_DROPSHIP_WAREHOUSE_CD = "MANUAL_DROPSHIP_WAREHOUSE_CD";

    /** MANUAL_DIRECT_SHIP_CUST_CD */
    public static final String MANUAL_DIRECT_SHIP_CUST_CD = "MD";

    /** String: COMMA "," */
    public static final String COMMA = ",";

    /** DB_COLUMN_RTL_WH_CD */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /** DB_COLUMN_RTL_SWH_CD */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";
    
    /** DB_COLUMN_RTL_WH_NM */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /** DB_SHIP_TO_FIRST_LINE_ADDR */
    public static final String DB_SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";
    /** DB_SHIP_TO_SCD_LINE_ADDR */
    public static final String DB_SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";
    /** DB_SHIP_TO_THIRD_LINE_ADDR */
    public static final String DB_SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";
    /** DB_SHIP_TO_FRTH_LINE_ADDR */
    public static final String DB_SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";
    /** DB_CTRY_CD */
    public static final String DB_CTRY_CD = "CTRY_CD";
    //QC#26990 Add End

    /** Bind parameter name. ADD QC#27846 */
    public static final String BIND_DS_ORD_POSN_NUM = "dsOrdPosnNum";
    
    /** There are multiple Ship To Location. used the Ship To Location of the first Config #. */
    public static final String NPAM1626W = "NPAM1626W";

    /**
     * Open Win Supplier button event Add QC#28216
     */
    public static final String NPAL1280_NWAL1130 = "NPAL1280_NWAL1130";


    // 2019/01/15 QC#29778 Add Start
    public static final String NPAL1280_NMAL2550 = "NPAL1280_NMAL2550";
    // 2019/01/15 QC#29778 Add End
    //QC#27770 Add Start
    /**
     */
    public static final String NLAM0077E = "NLAM0077E";
    //QC#27770 Add End

    /** There are multiple Destination WH. used the Destination WH of the First Item. */
    public static final String NPAM1635W = "NPAM1635W";
    
    //QC#29155 Add.
    /**
     * MSG_ERR_CD : 0(normal)
     */
    public static final int MSG_ERR_CD_NORMAL = 0;

    //QC#29155 Add.
    /**
     * NPAM1636W : The relationship between 'Carrier' and 'Shipping Service Level Code' is incorrect.
     */
    public static final String NPAM1636W = "NPAM1636W";

    // 2018/12/17 QC#29397 Add Start
    /** The Set Merchandise does not exist in the Vender. Please enter child item. */
    public static final String NPAM1639E = "NPAM1639E";
    // 2018/12/17 QC#29397 Add End

    // QC#28709
    /** Address validation Error */
    public static final String NMZC0030_ERROR = "9";
    
    /**  The corresponding [@] does not exist.  */
    public static final String NMAM0039E = "NMAM0039E";

    /** Add QC#32087
     * The Destination WH is GMD WH. then Supplier site must be "non EDI vendor site" 
     * */
    public static final String NPAM1643E = "NPAM1643E";

    /** DB Result Column */
    public static final String RS_TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";

    // START 2021/04/23 [QC#58645,ADD]
    /** VAR_CHAR_AUTO_APPROVE_AMT */
    public static final String VAR_CHAR_NPAL1280_AUTO_APPROVE_AMT = "NPAL1280_AUTO_APPROVE_AMT";
    // END 2021/04/23 [QC#58645,ADD]

    // START 2023/04/05 TZ.Win[QC#60966, ADD]
    /**
     * Vendor Ship Date should be later than Create Date.
     */
    public static final String NPAM1657E = "NPAM1657E";
    /**
     * Vendor Ship Date should be earlier than Date & Time Needed.
     */
    public static final String NPAM1658E = "NPAM1658E";
    // END 2023/04/05 TZ.Win[QC#60966, ADD]
    // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
    public static final String MDSE_TP_SET = "2";
    /** NPAM1329E */
    public static final String NPAM1329E = "NPAM1329E";
    
    public static final String NOT_SET = "-1";
    // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
    
    //QC#62443 2024/3/4 Add Start
    /** VAR_CHAR_EXP_SPLY_COA_CMPY_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_CMPY_CD = "EXP_SPLY_COA_CMPY_CD";

    /** VAR_CHAR_EXP_SPLY_COA_BR_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_BR_CD = "EXP_SPLY_COA_BR_CD";

    /** VAR_CHAR_EXP_SPLY_COA_ACCT_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_ACCT_CD = "EXP_SPLY_COA_ACCT_CD";

    /** VAR_CHAR_EXP_SPLY_COA_CH_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_CH_CD = "EXP_SPLY_COA_CH_CD";

    /** VAR_CHAR_EXP_SPLY_COA_AFFL_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_AFFL_CD = "EXP_SPLY_COA_AFFL_CD";

    /** VAR_CHAR_EXP_SPLY_COA_PROJ_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_PROJ_CD = "EXP_SPLY_COA_PROJ_CD";

    /** VAR_CHAR_EXP_SPLY_COA_EXTN_CD */
    public static final String VAR_CHAR_EXP_SPLY_COA_EXTN_CD = "EXP_SPLY_COA_EXTN_CD";

    /** Period */
    public static final String PERIOD = ".";
    //QC#62443 2024/3/4 Add End

}
