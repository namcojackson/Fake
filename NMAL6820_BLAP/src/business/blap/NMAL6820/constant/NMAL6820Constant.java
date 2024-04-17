/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6820.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 1590
 * 02/16/2016   CSAI            D.Fukaya        Delete          QC# 2368
 * 02/18/2016   CSAI            D.Fukaya        Update          QC# 3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC# 2369
 * 08/02/2016   CITS            S.Endo          Update          QC#10838
 * 10/25/2016   CITS            Y.IWASAKI       Update          QC#15120
 * 02/07/2017   CITS            Y.IWASAKI       Update          QC#17233
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 01/19/2018   CITS            T.Tokutomi      Update          QC#21852
 * 04/08/2019   Fujitsu         T.Ogura         Update          QC#28667
 *</pre>
 */
public class NMAL6820Constant {

    /**
     * BusinessID
     */
    public static final String BUSINESS_ID = "NMAL6820";

    /**
     * Warehouse
     */
    public static final String WH = "WH";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NMAL6820_INIT = "NMAL6820_INIT";

    /** Event Name : Search */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_SEARCH = "NMAL6820Scrn00_OnClick_Search";

    /** Event Name : get Manager Name */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_MGRNM = "NMAL6820Scrn00_OnClick_MgrNm";

    /** Event Name : get Tech Name */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_ALTOWNRNM = "NMAL6820Scrn00_OnClick_AltOwnrNm";

    /** Event Name : get Branch Name */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_BRANCH = "NMAL6820Scrn00_OnClick_Branch";

    /** Event Name : Reset */
    public static final String EVENT_NM_NMAL6820SCRN00_CMN_RESET = "NMAL6820Scrn00_CMN_Reset";

    /** Event Name : Clear */
    public static final String EVENT_NM_NMAL6820SCRN00_CMN_CLEAR = "NMAL6820Scrn00_CMN_Clear";

    /** Event Name : Submit */
    public static final String EVENT_NM_NMAL6820SCRN00_CMN_SUBMIT = "NMAL6820Scrn00_CMN_Submit";

    /** Event Name : TAB - SWH */
    public static final String EVENT_NM_NMAL6820SCRN00_TAB_SWH = "NMAL6820Scrn00_TAB_SWH";

    /** Event Name : TAB - Sourcing */
    public static final String EVENT_NM_NMAL6820SCRN00_TAB_SRC = "NMAL6820Scrn00_TAB_Sourcing";

    /** Event Name : TAB - Address */
    public static final String EVENT_NM_NMAL6820SCRN00_TAB_ADDR = "NMAL6820Scrn00_TAB_Address";

    /** Event Name : Win - Tech */
    public static final String EVENT_NM_NMAL6820_NMAL6050 = "NMAL6820_NMAL6050";

    /** Event Name : OnBlur - WHCategory */
    public static final String EVENT_NM_NMAL6820SCRN00_ONBLUR_WHCATG = "NMAL6820Scrn00_OnBlur_WHCategory";

    /** Event name : OpenWin_Wh */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_WH = "NMAL6820Scrn00_OpenWin_Wh";

    /** Event name : OpenWin_Mgr */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR = "NMAL6820Scrn00_OpenWin_Mgr";

    /** Event name : OpenWin_AltOwnr */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_ALT_OWNR = "NMAL6820Scrn00_OpenWin_AltOwnr";

    /** Event name : OpenWin_Crr */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_CRR = "NMAL6820Scrn00_OpenWin_Crr";

    /** Event name : OpenWin_ShipToState */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_STATE = "NMAL6820Scrn00_OpenWin_ShipToState";

    /** Event name : OpenWin_ReturnToState */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_STATE = "NMAL6820Scrn00_OpenWin_ReturnToState";

    /** Event name : OpenWin_Branch */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_BRANCH = "NMAL6820Scrn00_OpenWin_Branch";

    /** Event name : OnClick_SetShipTo */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_SET_SHIP_TO = "NMAL6820Scrn00_OnClick_SetShipTo";

    /** Event name : OnClick_ClearShipTo */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_CLEAR_SHIP_TO = "NMAL6820Scrn00_OnClick_ClearShipTo";

    /** Event name : OnClick_SetReturnTo */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_SET_RTRN_TO = "NMAL6820Scrn00_OnClick_SetReturnTo";

    /** Event name : OnClick_ClearReturnTo */
    public static final String EVENT_NM_NMAL6820SCRN00_ONCLICK_CLEAR_RTRN_TO = "NMAL6820Scrn00_OnClick_ClearReturnTo";

    /** Event name : OpenWin_ShipToCode */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_CODE = "NMAL6820Scrn00_OpenWin_ShipToCode";

    /** Event name : OpenWin_ReturnToCode */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_RTRN_TO_CODE = "NMAL6820Scrn00_OpenWin_ReturnToCode";

    /** Event name : NMAL6820_NMAL6760 */
    public static final String EVENT_NM_NMAL6820_NMAL6760 = "NMAL6820_NMAL6760";

    /** Event name : NMAL6820_NPAL1010 */
    public static final String EVENT_NM_NMAL6820_NPAL1010 = "NMAL6820_NPAL1010";

    /** Event name : NMAL6820_NWAL1130 */
    public static final String EVENT_NM_NMAL6820_NWAL1130 = "NMAL6820_NWAL1130";

    /** Event name : NMAL6820_GetAddressShipTo */
    public static final String EVENT_NM_NMAL6820SCRN00_GETADDRESS_SHIPTO = "NMAL6820Scrn00_GetAddressShipTo";

    /** Event name : NMAL6820_GetAddressReturnTo */
    public static final String EVENT_NM_NMAL6820SCRN00_GETADDRESS_RETURNTO = "NMAL6820Scrn00_GetAddressReturnTo";

    /** Event name : NMAL6820Scrn00_Get_SupplierName */
    public static final String EVENT_NM_NMAL6820SCRN00_GET_SUPPLIER_NAME = "NMAL6820Scrn00_Get_SupplierName";

    /** Event name : NMAL6820Scrn00_Get_SupplierSiteName */
    public static final String EVENT_NM_NMAL6820SCRN00_GET_SUPPLIER_SITE_NAME = "NMAL6820Scrn00_Get_SupplierSiteName";

    /**
     * Define Table Column to create Pulldown
     */
    /** RTL_WH_CATG_CD */
    public static final String RTL_WH_CATG_CD_DBCOLUMN = "RTL_WH_CATG_CD";

    /** RTL_WH_CATG_NM */
    public static final String RTL_WH_CATG_NM_DBCOLUMN = "RTL_WH_CATG_NM";

    /** INVTY_ACCT_CD */
    public static final String INVTY_ACCT_CD_DBCOLUMN = "INVTY_ACCT_CD";

    /** INVTY_ACCT_NM */
    public static final String INVTY_ACCT_NM_DBCOLUMN = "INVTY_ACCT_NM";

    /** INVTY_OWNR_CD */
    public static final String INVTY_OWNR_CD_DBCOLUMN = "INVTY_OWNR_CD";

    /** INVTY_OWNR_NM */
    public static final String INVTY_OWNR_NM_DBCOLUMN = "INVTY_OWNR_NM";

    /** TM_ZONE_CD */
    public static final String TM_ZONE_CD_DBCOLUMN = "TM_ZONE_CD";

    /** WH_OWNR_CD */
    public static final String WH_OWNR_CD_DBCOLUMN = "WH_OWNR_CD";

    /** WH_OWNR_NM */
    public static final String WH_OWNR_NM_DBCOLUMN = "WH_OWNR_NM";

    /** WH_SYS_TP_CD */
    public static final String WH_SYS_TP_CD_DBCOLUMN = "WH_SYS_TP_CD";

    /** WH_SYS_TP_NM */
    public static final String WH_SYS_TP_NM_DBCOLUMN = "WH_SYS_TP_NM";

    /** PO_RCPT_RTE_TP_CD */
    public static final String PO_RCPT_RTE_TP_CD_DBCOLUMN = "PO_RCPT_RTE_TP_CD";

    /** PO_RCPT_RTE_TP_NM */
    public static final String PO_RCPT_RTE_TP_NM_DBCOLUMN = "PO_RCPT_RTE_TP_NM";

    /** RMA_RCPT_RTE_TP_CD */
    public static final String RMA_RCPT_RTE_TP_CD_DBCOLUMN = "RMA_RCPT_RTE_TP_CD";

    /** RMA_RCPT_RTE_TP_NM */
    public static final String RMA_RCPT_RTE_TP_NM_DBCOLUMN = "RMA_RCPT_RTE_TP_NM";

    /** SHIP_TO_CNTY_PK */
    public static final String SHIP_TO_CNTY_PK_DBCOLUMN = "SHIP_TO_CNTY_PK";

    /** SHIP_TO_CNTY_NM */
    public static final String SHIP_TO_CNTY_NM_DBCOLUMN = "SHIP_TO_CNTY_NM";

    /** SHIP_TO_CTRY_CD */
    public static final String SHIP_TO_CTRY_CD_DBCOLUMN = "SHIP_TO_CTRY_CD";

    /** SHIP_TO_CTRY_NM */
    public static final String SHIP_TO_CTRY_NM_DBCOLUMN = "SHIP_TO_CTRY_NM";

    /** RTRN_TO_CNTY_PK */
    public static final String RTRN_TO_CNTY_PK_DBCOLUMN = "RTRN_TO_CNTY_PK";

    /** RTRN_TO_CNTY_NM */
    public static final String RTRN_TO_CNTY_NM_DBCOLUMN = "RTRN_TO_CNTY_NM";

    /** RTRN_TO_CTRY_CD */
    public static final String RTRN_TO_CTRY_CD_DBCOLUMN = "RTRN_TO_CTRY_CD";

    /** RTRN_TO_CTRY_NM */
    public static final String RTRN_TO_CTRY_NM_DBCOLUMN = "RTRN_TO_CTRY_NM";

    /** PLN_ITEM_INSRC_CD */
    public static final String PLN_ITEM_INSRC_CD_DBCOLUMN = "PLN_ITEM_INSRC_CD";

    /** PLN_ITEM_INSRC_NM */
    public static final String PLN_ITEM_INSRC_NM_DBCOLUMN = "PLN_ITEM_INSRC_NM";

    /** RTL_WH_CSRC_ZN_CDATG_CD */
    public static final String SRC_ZN_CD_DBCOLUMN = "SRC_ZN_CD";

    /** SRC_ZN_NM */
    public static final String SRC_ZN_NM_DBCOLUMN = "SRC_ZN_NM";

    /** DEF_SRC_PROCR_TP_CD */
    public static final String DEF_SRC_PROCR_TP_CD_DBCOLUMN = "DEF_SRC_PROCR_TP_CD";

    /** DEF_SRC_PROCR_TP_NM */
    public static final String DEF_SRC_PROCR_TP_NM_DBCOLUMN = "DEF_SRC_PROCR_TP_NM";

    /** DEF_RTRN_TO_PROCR_TP_CD */
    public static final String DEF_RTRN_TO_PROCR_TP_CD_DBCOLUMN = "DEF_RTRN_TO_PROCR_TP_CD";

    /** DEF_RTRN_TO_PROCR_TP_NM */
    public static final String DEF_RTRN_TO_PROCR_TP_NM_DBCOLUMN = "DEF_RTRN_TO_PROCR_TP_NM";

    /** GND_SVC_LVL_CD */
    public static final String GND_SVC_LVL_CD_DBCOLUMN = "GND_SVC_LVL_CD";

    /** GND_SVC_LVL_NM */
    public static final String GND_SVC_LVL_NM_DBCOLUMN = "GND_SVC_LVL_NM";

    /** FIRST_OVNGT_SVC_LVL_CD */
    public static final String FIRST_OVNGT_SVC_LVL_CD_DBCOLUMN = "FIRST_OVNGT_SVC_LVL_CD";

    /** FIRST_OVNGT_SVC_LVL_NM */
    public static final String FIRST_OVNGT_SVC_LVL_NM_DBCOLUMN = "FIRST_OVNGT_SVC_LVL_NM";

    /** SCD_OVNGT_SVC_LVL_CD */
    public static final String SCD_OVNGT_SVC_LVL_CD_DBCOLUMN = "SCD_OVNGT_SVC_LVL_CD";

    /** SCD_OVNGT_SVC_LVL_NM */
    public static final String SCD_OVNGT_SVC_LVL_NM_DBCOLUMN = "SCD_OVNGT_SVC_LVL_NM";

    /** RTL_WH_ACCT_TP_NM */
    public static final String RTL_WH_ACCT_TP_NM_DBCOLUMN = "RTL_WH_ACCT_TP_NM";

    /** TCH_EMR_PROCR_TP_CD */
    public static final String TCH_EMR_PROCR_TP_CD_DBCOLUMN = "TCH_EMR_PROCR_TP_CD";

    /** TCH_EMR_PROCR_TP_NM */
    public static final String TCH_EMR_PROCR_TP_NM_DBCOLUMN = "TCH_EMR_PROCR_TP_NM";

    /** RTL_WH_CD */
    public static final String RTL_WH_CD_DBCOLUMN = "RTL_WH_CD";

    /** RTL_WH_NM */
    public static final String RTL_WH_NM_DBCOLUMN = "RTL_WH_NM";

    /** RTL_SWH_CD */
    public static final String RTL_SWH_CD_DBCOLUMN = "RTL_SWH_CD";

    /** RTL_SWH_NM */
    public static final String RTL_SWH_NM_DBCOLUMN = "RTL_SWH_NM";

    /** DS_ACCT_NUM */
    public static final String DS_ACCT_NUM_DBCOLUMN = "DS_ACCT_NUM";

    /** DS_ACCT_NM */
    public static final String DS_ACCT_NM_DBCOLUMN = "DS_ACCT_NM";

    /** PRNT_VND_CD */
    public static final String PRNT_VND_CD_DBCOLUMN = "PRNT_VND_CD";

    /** VND_CD */
    public static final String VND_CD_DBCOLUMN = "VND_CD";

    // =================================================
    // html name value
    // =================================================
    /** Return to Location Check Box */
    public static final String RTRN_TO_LOC_CHK_BOX_NAME_VALUE = "xxChkBox_R1";

    /** Disable Flag Check Box */
    public static final String RTL_SWH_DSBL_FLG_CHK_BOX_NAME_VALUE = "xxChkBox_D1";

    // =================================================
    // API parameter
    // =================================================
    /** API Mode : create */
    public static final String RTL_WH_SETUP_API_MODE_CREATE = "1";

    /** API Mode : update */
    public static final String RTL_WH_SETUP_API_MODE_UPDATE = "2";

    /** API Mode : delete */
    public static final String RTL_WH_SETUP_API_MODE_DELETE = "3";

    // =================================================
    // value for message kind error
    // =================================================
    /** error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // =================================================
    // Message ID
    // =================================================
    /** This Location has inventory, so you can not delete. */
    public static final String NMAM0850E = "NMAM0850E";

    /** The entered [@] does not exist. */
    public static final String NMAM0009E = "NMAM0009E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] is already registered. */
    public static final String NMAM0010E = "NMAM0010E";

    /** Please enter both '@' and '@'. */
    public static final String NMAM8289E = "NMAM8289E";

    /** [@] has to be [@]-digit long. */
    public static final String NMAM0216E = "NMAM0216E";

    /** Invalid combination. @ & @ */
    public static final String NMAM0070E = "NMAM0070E";

    /**
     * WH Category, Inventory Account, Inventory Ownership can't be
     * changed because Purchase Requisition or PO exist.
     */
    public static final String NMAM8314E = "NMAM8314E";

    /**
     * Default Account# is not set up in the system. Please contact to
     * the system administrator.
     */
    public static final String NMAM8383E = "NMAM8383E";

    /**
     * Account# corresponding to entered @ can not be used for
     * warehouse. Please enter different @ or enter address
     * information manually.
     */
    public static final String NMAM8384E = "NMAM8384E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /**
     * Suffix for Return-To Code is not set up in the system. Please
     * contact to the system administrator.
     */
    public static final String NMAM8387E = "NMAM8387E";

    // /** Entered warehouse code can not be used since entered
    // warehouse code + @ has been already registered as Ship-To Code.
    // */
    // public static final String NMAM8388E = "NMAM8388E";

    /**
     * [@] must be unique within the same [@].
     */
    public static final String NMAM8625E = "NMAM8625E";

    /**
     * QC#21852 Add.
     * Duplicate error occured. Table name=[@], Primary Key=[@].
     */
    public static final String ZZPM0068E = "ZZPM0068E";

    // =================================================
    // Message Parameter
    // =================================================
    /**
     * The message parameter : Source (WH).
     */
    public static final String MESSAGE_PARAM_RTL_WH_NM_OF_SRC = "Source";

    /**
     * The message parameter : Sub Source (SWH).
     */
    public static final String MESSAGE_PARAM_RTL_SWH_NM_OF_SRC = "Sub Source";

    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
//    /**
//     * The message parameter : Emergency Source (WH).
//     */
//    public static final String MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC = "Emergency Source";
//
//    /**
//     * The message parameter : Emergency Sub Source (SWH).
//     */
//    public static final String MESSAGE_PARAM_RTL_SWH_NM_OF_EMERG_SRC = "Emergency Sub Source";
    /**
     * The message parameter : Satellite Warehouse (WH).
     */
    public static final String MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC = "Satellite Warehouse";

    /**
     * The message parameter : Satellite Sub Warehouse (SWH).
     */
    public static final String MESSAGE_PARAM_RTL_SWH_NM_OF_EMERG_SRC = "Satellite Sub Warehouse";
    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END
    /**
     * The message parameter : Return To (WH).
     */
    public static final String MESSAGE_PARAM_RTL_WH_NM_OF_RTRN = "Return To";

    /**
     * The message parameter : Return Sub Source (SWH).
     */
    public static final String MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN = "Return Sub Source";

    // =================================================
    // Display character string for message
    // =================================================
    /** WH Code */
    public static final String NAME_FOR_MESSAGE_RTL_WH_CD = "WH Code";

    /** WH Name */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM = "WH Name";

    /** WH Category */
    public static final String NAME_FOR_MESSAGE_RTL_WH_CATG = "WH Category";

    /** Tech Toc */
    public static final String NAME_FOR_MESSAGE_TECH_TOC = "Tech Toc";

    /** Owner */
    public static final String NAME_FOR_MESSAGE_OWNER = "Owner";

    /** Alternate Owner */
    public static final String NAME_FOR_MESSAGE_ALT_OWNER = "Alternate Owner";

    /** Branch */
    public static final String NAME_FOR_MESSAGE_BRANCH = "Branch";

    /** Inventory Ownership */
    public static final String NAME_FOR_MESSAGE_INVTY_OWNR = "Inventory Ownership";

    /** Effective Thru Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU = "Effective Thru Date";

    /** Branch */
    public static final String NAME_FOR_MESSAGE_BR = "Branch";

    /** Time Zone */
    public static final String NAME_FOR_MESSAGE_TM_ZONE = "Time Zone";

    /** WH System Type */
    public static final String NAME_FOR_MESSAGE_WH_SYS_TP = "WH System Type";

    /** Ship-To Code */
    public static final String NAME_FOR_MESSAGE_SHIP_TO_CODE = "Ship-To Code";

    /** Return-To Code */
    public static final String NAME_FOR_MESSAGE_RTRN_TO_CODE = "Return-To Code";

    /** PO Receipt Routing */
    public static final String NAME_FOR_MESSAGE_PO_RCPT_RTE_TP = "PO Receipt Routing";

    /** RMA Receipt Routing */
    public static final String NAME_FOR_MESSAGE_RMA_RCPT_RTE_TP = "RMA Receipt Routing";

    /** Address Line1 */
    public static final String NAME_FOR_MESSAGE_FIRST_LINE_ADDR = "Address Line1";

    /** City */
    public static final String NAME_FOR_MESSAGE_CTY_ADDR = "City";

    /** State */
    public static final String NAME_FOR_MESSAGE_ST = "State";

    /** Postal Code */
    public static final String NAME_FOR_MESSAGE_POST = "Postal Code";

    /** Country */
    public static final String NAME_FOR_MESSAGE_CTRY = "Country";

    /** Telephone */
    public static final String NAME_FOR_MESSAGE_TEL = "Telephone";

    /** Fax */
    public static final String NAME_FOR_MESSAGE_FAX = "Fax";

    /** Priority */
    public static final String NAME_FOR_MESSAGE_PRTY_LOC_FLG = "Priority";

    /** Source WH/SWH */
    public static final String NAME_FOR_MESSAGE_DEF_SRC_WH = "Source WH/SWH";

    /** Emergency To WH/SWH */
    public static final String NAME_FOR_MESSAGE_DEF_EMRG_WH = "Emergency To WH/SWH";

    /** Return To WH/SWH */
    public static final String NAME_FOR_MESSAGE_DEF_RTRN_WH = "Return To WH/SWH";

    /** Location Type Code */
    public static final String NAME_FOR_MESSAGE_LOC_TP_CD = "Location Type Code";

    /** WMS WH Code */
    public static final String NAME_FOR_MESSAGE_WMS_WH_CD = "WMS WH Code";

    /** WH Group Name */
    public static final String NAME_FOR_MESSAGE_WH_GROUP_NAME = "WH Group Name";

    /** Preferred Carrier */
    public static final String NAME_FOR_MESSAGE_PRF_CARR_CD = "Preferred Carrier";

    // =================================================
    // key value of ssm parameter
    // =================================================
    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * date format for db
     */
    public static final String DATE_FORMATT_FOR_DB = "yyyyMMdd";

    /**
     * Under tab [Address]
     */
    public static final String TAB_ADDR = "Address";

    /**
     * Under tab [Sourcing]
     */
    public static final String TAB_SRC = "Sourcing";

    /**
     * Under tab [SWH]
     */
    public static final String TAB_SWH = "SWH";

    /** FLG_ON */
    public static final String FLG_ON = "flgOn";

    /** WH_END_DATE */
    public static final String WH_END_DATE = "99991231";

    /**
     * TIMESTAMP_PATTERN
     */
    public static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    // =================================================
    // Pulldown List Code / Name
    // =================================================
    /** Yes/No Pulldown Code */
    public static final String[] YES_NO_PULLDOWN_CD = {"Y", "N" };

    /** Yes/No Pulldown Name */
    public static final String[] YES_NO_PULLDOWN_NM = {"Yes", "No" };

    // =================================================
    // Parameter for Popup
    // =================================================
    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_WH = "RTL_WH";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_OWNER = "S21_PSN_V";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_TECH = "Technician Search Popup";

    // =================================================
    // Var Char Const Key
    // =================================================
    /** Var Char Const Key : NMZC601001_SUFFIX_FOR_RTRN_TO */
    public static final String NMZC601001_SUFFIX_FOR_RTRN_TO = "NMZC601001_SUFFIX_FOR_RTRN_TO";

    /** Link OpenWin_ShipTo_ST Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_ST_EVENT_NM = "OpenWin_ShipTo_ST";

    /** Link OpenWin_ShipTo_PostCd Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM = "OpenWin_ShipTo_PostCd";

    /** Link OpenWin_ShipTo_City Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_CTY_EVENT_NM = "OpenWin_ShipTo_City";

    /** Link OpenWin_ShipTo_Cnty Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM = "OpenWin_ShipTo_Cnty";

    /** Link OpenWin_ReturnTo_ST Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_ST_EVENT_NM = "OpenWin_ReturnTo_ST";

    /** Link OpenWin_ReturnTo_PostCd Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM = "OpenWin_ReturnTo_PostCd";

    /** Link OpenWin_ReturnTo_City Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_CTY_EVENT_NM = "OpenWin_ReturnTo_City";

    /** Link OpenWin_ReturnTo_Cnty Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM = "OpenWin_ReturnTo_Cnty";

    /** SUGGESTED */
    public static final String NMZC0030_SUGGESTED = "1";

    /** ERROR */
    public static final String NMZC0030_ERROR = "9";

    /** Error Message: [@] is not exist in Master. */
    public static final String NMAM8454E = "NMAM8454E";

    /** VarcharConst Key */
    public static final String NMAL6820_ADDR_CHK_FLG = "NMAL6820_ADDR_CHK_FLG";

    /** Var Char Const Key : NMAL6820_CHOICE_WH_OWNR_CD */
    public static final String NMAL6820_CHOICE_WH_OWNR_CD = "NMAL6820_CHOICE_WH_OWNR_CD";    // 2019/04/08 T.Ogura [QC#28667,ADD]

    //QC#21852 Add.
    // =================================================
    // WH_LEAD_TM Default Parameter
    // =================================================

    /** DEF_EFF_THRU_DT */
    public static final String DEF_EFF_THRU_DT = "99991231";

    /** DEF_SHPG_CLO_TM_TS */
    public static final String DEF_SHPG_CLO_TM_TS = "2359";

    /** DEF_PICK_PACK_AOT */
    public static final BigDecimal DEF_PICK_PACK_AOT = BigDecimal.ZERO;

    //QC#21852 Add.
    // =================================================
    // WH_END_MTH Default Parameter
    // =================================================

    /** DEF_END_MTH_BIZ_DAYS_AOT */
    public static final BigDecimal DEF_END_MTH_BIZ_DAYS_AOT = BigDecimal.ZERO;

    //QC#21852 Add.
    // =================================================
    // WH_DROP_TM Default Parameter
    // =================================================

    /** DEF_WH_DROP_LINE_NUM */
    public static final String DEF_WH_DROP_LINE_NUM = "1";

    /** DEF_FROM_WH_DROP_TM_TS */
    public static final String DEF_FROM_WH_DROP_TM_TS = "0000";

    /** DEF_PICK_PACK_AOT */
    public static final String DEF_TO_WH_DROP_TM_TS = "2400";

    //QC#21852 Add.
    // =================================================
    // AREA_LEAD_TM Default Parameter
    // =================================================

    /** DEF_DELY_LEAD_AOT */
    public static final BigDecimal DEF_DELY_LEAD_AOT = new BigDecimal("216");

    /** DEF_EFF_FROM_DT */
    public static final String DEF_EFF_FROM_DT = "00010101";
}
