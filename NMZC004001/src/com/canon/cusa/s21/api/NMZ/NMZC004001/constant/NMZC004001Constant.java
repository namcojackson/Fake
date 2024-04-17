/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC004001.constant;

import java.math.BigDecimal;

/**
 * <pre> 
 * NMZC004001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         C.Tanaka        Create          N/A
 * 02/26/2016   SRAA            K.Aratani       Update          QC3767, QC4507, V1.5, V1.6
 * 03/16/2016   SRAA            K.Aratani       Update          QC#5580
 * 05/16/2016   SRAA            Y.Chen          Update          QC#4342
 * 05/18/2016   SRAA            K.Aratani       Update          QC#4203
 * 05/27/2016   Fujitsu         M.Suzuki        Update          QC#9073
 * 12/15/2016   SRAA            K.Aratani       Update          QC#16686
 * 06/10/2019   Fujitsu         M.Ohno          Update          QC#50763
 * 12/13/2019   Fujitsu         A.Kazuki        Update          QC#54218
 * 2019/12/16   Fujitsu         K.Kato          Update          QC#54859
 * 2020/04/13   CITS            K.Ogino         Update          QC#56494
 * 2023/01/05   Hitachi         E.Watabe        Update          QC#60892
 * 2023/03/27   Hitachi         E.Watabe        Update          QC#61207
 * 2023/04/07   CITS            F.Komaki        Update          QC#61371
 *</pre>
 */
public class NMZC004001Constant {

    /** Return Code: 0000 */
    public static final String RTNCD_NORMAL = "0000";

    /** 1 mg = 0.00000220462 LB */
    public static final String LB = "0.00000220462";

    /** 2 */
    public static final BigDecimal TWO = new BigDecimal(2);

    /** Date format */
    public static final String DATE_PATTERN = "yyyyMMdd";

    /** INVTY_DIST_TP_CD: 3 */
    public static final String INVTY_DIST_TP_CD_3 = "3";

    /** INVTY_SOFT_ALLOC_TP_CD: 4 */
    public static final String INVTY_SOFT_ALLOC_TP_CD_4 = "4";

    /** QTY_PKG_APVL_STS_CD: Submit */
    public static final String QTY_PKG_APVL_STS_CD_SU = "SU";

    /** AMER_XPND_TP_CD: 1 - Open */
    public static final String AMER_XPND_TP_CD_1 = "1";

    /** AMER_MDSE_CLS_TP_CD: A - Mass Product */
    public static final String AMER_MDSE_CLS_TP_CD_A = "A";

    /** ITEM IMPORT */
    public static final String ITEM_IMPORT = "ITEM IMPORT";

    /** ITEM */
    public static final String REF_ENTRY_ITEM = "ITEM";

    /** MDSE_CD */
    public static final String ATTR_NM_MDSE_CD = "MDSE_CD";

    /** Error Max Count */
    public static final int ERR_MAX_CNT = 20;

    /** List Max Length */
    public static final int LIST_MAX_LG = 200;

    /** Length: 8 */
    public static final int LG_8 = 8;

    /** Decimal Place: 4 */
    public static final int DECL_PLACE_4 = 4;

    // --------------------------------
    // VAR_CHAR_COST_NMt
    // --------------------------------
    /** CUSA Global Company Code */
    public static final String CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** CUSA Parts table name */
    public static final String CUSA_USMM4100 = "CUSA_USMM4100";

    /** CUSA Merchandise table name */
    public static final String CUSA_MDSE = "CUSA_MDSE";

    /** CUSA Merchandise Safety Information table name */
    public static final String CUSA_MDSE_SFTY_INFO = "CUSA_MDSE_SFTY_INFO";

    /** CUSA Composition table name */
    public static final String CUSA_CMPSN = "CUSA_CMPSN";

    /** CUSA Merchandise Store Package table name */
    public static final String CUSA_MDSE_STORE_PKG = "CUSA_MDSE_STORE_PKG";

    /** CUSA Merchandise Serial Number Range table name */
    public static final String CUSA_MDSE_SER_NUM_RNG = "CUSA_MDSE_SER_NUM_RNG";

    /** Default Days Primary Allocation Number */
    public static final String DEF_DAYS_PRI_ALLOC_NUM = "DEF_DAYS_PRI_ALLOC_NUM";

    // --------------------------------
    // Message
    // --------------------------------
    /** Message Kind: Error */
    public static final String MSG_KIND_E = "E";

    /** Message Kind: Warning */
    public static final String MSG_KIND_W = "W";

    /** Error occurred in item registration API. */
    public static final String NMAM8304E = "NMAM8304E";

    /** Warning occurred in item registration API. */
    public static final String NMAM8305W = "NMAM8305W";

    /** If [@] value is [@], then [@] must be [@]. */
    public static final String NMZM0133E = "NMZM0133E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /**
     * If Item Type is Software, Software License Control Type is not
     * "No", then Inventory Trackable must be "Y".
     */
    public static final String NMZM0134E = "NMZM0134E";

    /** Set compoments structure is different from CUSA and CSA. */
    public static final String NMZM0135E = "NMZM0135E";

    /** BOM child item code can not be same as parent item code. */
    public static final String NMZM0136E = "NMZM0136E";

    /** Failed to send message to Deal Configurator. */
    public static final String NMZM0137W = "NMZM0137W";

    /** The corresponding [@] does not exist. */
    public static final String NMZM0138E = "NMZM0138E";

    /** The value for [@] must be over [@]. */
    public static final String NMZM0139E = "NMZM0139E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NMZM0140E = "NMZM0140E";

    /** The value for [@] must be below [@]. */
    public static final String NMZM0141E = "NMZM0141E";

    /** The value for [@] must be the same as [@]. */
    public static final String NMZM0142E = "NMZM0142E";

    /** @ is invalid. */
    public static final String NMZM0143E = "NMZM0143E";

    /** @ is duplicated. */
    public static final String NMZM0144E = "NMZM0144E";

    /** It exceeds the maximum hierarchy layers allowed. */
    public static final String NMZM0145E = "NMZM0145E";

    /** The entered [@] does not exist in [@]. */
    public static final String NMZM0146E = "NMZM0146E";

    /** [@] does not exist in Americas Mercury. */
    public static final String NMZM0147E = "NMZM0147E";

    /** [@] has not been disclosed to @ in Americas Mercury. */
    public static final String NMZM0148E = "NMZM0148E";

    /** The disclosure process of [@] to @ is not completed. */
    public static final String NMZM0149E = "NMZM0149E";

    /** Data insert failure.@ */
    public static final String NMZM0150E = "NMZM0150E";

    /** [@] already exists in [@] */
    public static final String NMZM0151E = "NMZM0151E";

    /** Please enter a number equal to or greater than 0 in [@]. */
    public static final String NMZM0152E = "NMZM0152E";

    /** The value for [@] must be equal to or later than [@] */
    public static final String NMZM0153E = "NMZM0153E";

    /** The entered [@] does not exist in master. */
    public static final String NMZM0154E = "NMZM0154E";

    /** You can enter the BOM only in case of Set or Kit of ITEM TYPE. */
    public static final String NMZM0155E = "NMZM0155E";

    /** Can not set MERCHANDISE TYPE[@] in case of ITEM TYPE[@] */
    public static final String NMZM0156E = "NMZM0156E";

    /** RECEIPT&SHIPMENT is mandatory in case of IB TRACKABLE. */
    public static final String NMZM0157E = "NMZM0157E";

    /**
     * IB Trackable is mandatory when Receipt Serial Take Flag or
     * Shipment Serial Take Flag are Y.
     */
    public static final String NMZM0180E = "NMZM0180E";

    /** @ should be @. */
    public static final String NMZM0158E = "NMZM0158E";

    /** Please set either @ or @. */
    public static final String NMZM0159E = "NMZM0159E";

    /** You cannot set 8 length Kit component. */
    public static final String NMZM0160E = "NMZM0160E";

    /** Invalid combination. @ & @ */
    public static final String NMZM0161E = "NMZM0161E";

    /** When @ is @, @ is required. */
    public static final String NMZM0162E = "NMZM0162E";

    /** [@] field is mandatory. */
    public static final String NMZM0163E = "NMZM0163E";

    /**
     * The corresponding data does not exist. Table Name: [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NMZM0164E = "NMZM0164E";

    /** Corresponding data already exists. */
    public static final String NMZM0165E = "NMZM0165E";

    /** [@] cannot be set to a future date. */
    public static final String NMZM0166E = "NMZM0166E";

    /**
     * Default Mdse Item Status does not exists in MDSE_ITEM_STS
     * Table.
     */
    public static final String NMZM0181E = "NMZM0181E";
    
    // QC#4342
    /**
     * T&C Option should be selected only one Response Time.
     */
    public static final String NMAM8325E = "NMAM8325E";
    
    /**
     * If [@], [@] cannot be set.
     */
    public static final String NMAM0076E = "NMAM0076E";
    
    /**
     * If Item Type is KIT, then Inventory Trackable must be "Y".
     */
    public static final String NMAM8590E = "NMAM8590E";
    
    /**
     * On Receipt&Shipment or On Shipment should be On when License Control is Yes.
     */
    public static final String NMAM8591E = "NMAM8591E";
    
    // 2019/06/10 QC#50763 Add Start
    /**
     * [@] cannot be entered [@]
     */
    public static final String NMAM0086E = "NMAM0086E";
    // 2019/06/10 QC#50763 Add End
    
    // START 2023/03/27 E.Watabe [QC#61207,ADD]
    
    /**
     * Data insert failure.  [ TableName = @ , key = @, value = @ ]
     */
    public static final String ZZMM0001E = "ZZMM0001E";
    
    /**
     * Data update failure.  [ TableName = @ , key = @, value = @ ]
     */
    public static final String ZZMM0015E = "ZZMM0015E";
    
    // END 2023/03/27 E.Watabe [QC#61207,ADD]
    
    // --------------------------------
    // @ value for Message
    // --------------------------------
    // Mandatory Check
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String SLS_DT = "Sales Date";

    /** Item List */
    public static final String XX_ITEM_LIST = "Item List";

    /** Merchandise Code */
    public static final String MDSE_CD = "Merchandise Code";

    /** Merchandise Name */
    public static final String MDSE_NM = "Merchandise Name";

    /** Merchandise Formal Name */
    public static final String MDSE_FML_NM = "Merchandise Formal Name";

    //QC#5580
    /** Merchandise Description Short Text */
    public static final String MDSE_DESC_SHORT_TXT = "Merchandise Description Short Text";

    /** Merchandise Description Long Text */
    public static final String MDSE_DESC_LONG_TXT = "Merchandise Description Long Text";

    /** Manufacture Item Code */
    public static final String MNF_ITEM_CD = "Manufacture Item Code";

    /** Chart of Account Product Code */
    public static final String COA_PROD_CD = "Chart of Account Product Code";

    /** Inventory Control Flag */
    public static final String INVTY_CTRL_FLG = "Inventory Control Flag";

    /** Inventory Value Flag */
    public static final String INVTY_VAL_FLG = "Inventory Value Flag";

    /** This Month Total Standard Cost Amount */
    public static final String THIS_MTH_TOT_STD_COST_AMT = "This Month Total Standard Cost Amount";

    /** Product Control Code */
    public static final String PROD_CTRL_CD = "Product Control Code";

    /** Product Control Name */
    public static final String PROD_CTRL_NM = "Product Control Name";

    /** Received Serial Taking Flag */
    public static final String RCV_SER_TAKE_FLG = "Received Serial Taking Flag";

    /** Shipping Serial Taken Flag */
    public static final String SHPG_SER_TAKE_FLG = "Shipping Serial Taken Flag";

    /** Item Type */
    public static final String MDSE_ITEM_TP_CD = "Item Type";

    /** COA Account Code for Revenue */
    public static final String REV_COA_ACCT_CD = "COA Account Code for Revenue";

    /** Cost of Goods */
    public static final String COGS_COA_ACCT_CD = "Cost of Goods";

    /** Expense COA Account Code */
    public static final String EXP_COA_ACCT_CD = "Expense COA Account Code";

    /** Accrual */
    public static final String ACRL_COA_ACCT_CD = "Accrual";

    /** Deferred Accounting Rule */
    public static final String DFRD_ACCTG_RULE_CD = "Deferred Accounting Rule";

    /** Deferred Invoicing Rule */
    public static final String DFRD_INV_RULE_CD = "Deferred Invoicing Rule";

    /** Warranty Days Amount Of Time */
    public static final String WTY_DAYS_AOT = "Warranty Days Amount Of Time";

    /** Metered Machine */
    public static final String MTR_MACH_FLG = "Metered Machine";

    /** InstallBase Controlled */
    public static final String INSTL_BASE_CTRL_FLG = "InstallBase Controlled";

    /** Service Call Enabled */
    public static final String SVC_CALL_ENBL_FLG = "Service Call Enabled";

    /** Created From Template */
    public static final String MDSE_CRAT_TMPL_PK = "Created From Template";

    /** Chart Of Account Merchandise Type */
    public static final String COA_MDSE_TP_CD = "Chart Of Account Merchandise Type";

    /** Package UOM Class Code */
    public static final String PKG_UOM_CLS_CD = "Package UOM Class Code";

    /** Line of Business Type Code */
    public static final String LINE_BIZ_TP_CD = "Line of Business Type Code";

    /** Parts Survey Required Flag */
    public static final String PRT_SRVY_REQ_FLG = "Parts Survey Required Flag";

    /** Maintenance Point of Purchase Available Flag */
    public static final String MAINT_POP_AVAL_FLG = "Maintenance Point of Purchase Available Flag";

    /** Extended Maintenance Point of Purchase Available Flag */
    public static final String EXT_MAINT_POP_AVAL_FLG = "Extended Maintenance Point of Purchase Available Flag";

    /** Storage List */
    public static final String XX_STORE_LIST = "Storage List";

    /** CMPSN: Parent Merchandise Code */
    public static final String PRNT_MDSE_CD_C = "Parent Merchandise Code";

    /** CMPSN: Code Organization Level */
    public static final String MDSE_CMPSN_TP_CD_C = "Code Organization Level";

    /** CMPSN: Child Merchandise Quantity */
    public static final String CHILD_MDSE_QTY_C = "Child Merchandise Quantity";

    /** CMPSN: Effective From Date */
    public static final String EFF_FROM_DT_C = "Effective From Date";

    /** CMPSN: Unit of Measure Code */
    public static final String PKG_UOM_CD_DC = "Unit of Measure Code";

    /** MDSE_STORE_PKG: Primary Package UOM Flag */
    public static final String PRIM_PKGUOM_FLG = "Primary Package UOM Flag";

    /** MDSE_STORE_PKG: Unit of Measure Code */
    public static final String PKG_UOM_CD_S = "Unit of Measure Code";

    /** MDSE_STORE_PKG: Merchandise Code */
    public static final String MDSE_CD_S = "Merchandise Code (Store Pkg List)";

    /** MDSE_STORE_PKG: in Each Quantity */
    public static final String IN_EACH_QTY_S = "in Each Quantity";

    /** MDSE_STORE_PKG: Primary Package UOM Flag */
    public static final String PRIM_PKG_UOM_FLG_DS = "Primary Package UOM Flag";

    /** MDSE_SER_NUM_RNG: Merchandise Code */
    public static final String MDSE_CD_SN = "Merchandise Code (Serial# List)";

    /** MDSE_SER_NUM_RNG: From Serial Number */
    public static final String FROM_SER_NUM_SN = "From Serial Number";

    /** MDSE_SER_NUM_RNG: Through Serial Number */
    public static final String THRU_SER_NUM_SN = "Through Serial Number";

    /** SUPD:Merchandise Code */
    public static final String MDSE_CD_SD = "Merchandise Code (Supd List)";

    /** SUPD_RELN: Supersede to Merchandise Code */
    public static final String SUPD_TO_MDSE_CD_SR = "Supersede to Merchandise Code";

    /** MDSE_ITEM_FLIP_SET: Merchandise Code */
    public static final String MDSE_CD_F = "Merchandise Code (Flip List)";

    /** MDSE_ITEM_FLIP_SET: Related Merchandise Code */
    public static final String RELN_MDSE_CD_F = "Related Merchandise Code";

    /** MDSE_ITEM_FLIP_SET: Merchandise Item Relationship Type */
    public static final String MDSE_ITEM_RELN_TP_CD_F = "Merchandise Item Relationship Type";

    /** CUST_MDSE_XREF: Merchandise Code */
    public static final String MDSE_CD_X = "Merchandise Code (Cust Mdse List)";

    /** CUST_MDSE_XREF: Customer Merchandise Code */
    public static final String CUST_MDSE_CD_X = "Customer Merchandise Code";

    /** CUST_MDSE_XREF: Direct Sales Account Number */
    public static final String DS_ACCT_NUM_X = "Direct Sales Account Number";

    /** CUST_MDSE_XREF: Effective From Date */
    public static final String EFF_FROM_DT_X = "Effective From Date";

    /** ASL_HDR: Parent Vendor Code */
    public static final String PRNT_VND_CD_A = "Parent Vendor Code";

    /** ASL_HDR: Merchandise Code */
    public static final String MDSE_CD_A = "Merchandise Code (ASL List)";

    /** ASL_HDR: Supplier Item Number */
    public static final String SPLY_ITEM_NUM_A = "Supplier Item Number";

    /** ASL_HDR: Vendor Code */
    public static final String VND_CD_A = "Vendor Code";

    /** ASL_HDR: Unit Price Amount */
    public static final String UNIT_PRC_AMT_A = "Unit Price Amount";

    /** ASL_HDR: Effective From Date */
    public static final String EFF_FROM_DT_A = "Effective From Date";
    
    // QC#4342
    /** MDSE_TERM_COND_OPT: Term Condition Option Type Code */
    public static final String MDSE_TERM_COND_OPT_TP_CD_T = "T&C Option";

    // Flag Check
    /** Configuration Flag */
    public static final String CONFIG_FLG = "Configuration Flag";

    /** Return Required Parts Flag */
    public static final String RTRN_REQ_PRT_FLG = "Return Required Parts Flag";

    /** RMA Inspection Required */
    public static final String RMA_INSP_REQ_FLG = "RMA Inspection Required";

    /** Invoiceable */
    public static final String INV_PSBL_FLG = "Invoiceable";

    /** IWR Enabled Flag */
    public static final String IWR_ENBL_FLG = "IWR Enabled Flag";

    /** Customer Orderable */
    public static final String CUST_ORD_ENBL_FLG = "Customer Orderable";

    /** Remanufactured Item Exists */
    public static final String RE_MNF_ITEM_EXST_FLG = "Remanufactured Item Exists";

    /** MAIN ENGINE */
    public static final String MAIN_MACH_FLG = "MAIN ENGINE";

    /** REMAN AVAILABLE */
    public static final String RE_MNF_AVAL_FLG = "REMAN AVAILABLE";

    /** Parts Dropship Disabled */
    public static final String PRT_DROP_SHIP_DSBL_FLG = "Parts Dropship Disabled";

    /** Internet Connect Software Controlled Flag */
    public static final String INTNT_CONN_SW_CTRL_FLG = "Internet Connect Software Controlled Flag";

    /** Statement Of Work */
    public static final String SOW_REQ_FLG = "Statement Of Work";

    /** Parent Company Set Merchandise Flag */
    public static final String PRNT_CMPY_SET_MDSE_FLG = "Parent Company Set Merchandise Flag";

    /** Third Party Item Flag */
    public static final String THIRD_PTY_ITEM_FLG = "Third Party Item Flag";

    /** FCC Required Flag */
    public static final String FCC_REQ_FLG = "FCC Required Flag";

    /** FCC Part 15/18 required Flag */
    public static final String FCC_PRT_15OVER18_REQ_FLG = "FCC Part 15/18 required Flag";

    /** Flag */
    public static final String FCC_VER_FLG = "Flag";

    /** Flag */
    public static final String FCC_CRTIF_FLG = "Flag";

    /** Flag */
    public static final String FCC_DOC_FLG = "Flag";

    /** Flag */
    public static final String FCC_PRT68_REQ_FLG = "Flag";

    /** FDA Required Flag */
    public static final String FDA_REQ_FLG = "FDA Required Flag";

    /** FDA Radiologocal Flag */
    public static final String FDA_RDLG_PROD_FLG = "FDA Radiologocal Flag";

    /** Flag */
    public static final String FDA_MEDC_DVC_FLG = "Flag";

    /** TSCA Required Flag */
    public static final String TSCA_REQ_FLG = "TSCA Required Flag";

    /** UL Required Flag */
    public static final String UL_REQ_FLG = "UL Required Flag";

    /** CFC Required Flag */
    public static final String CFC_REQ_FLG = "CFC Required Flag";

    /** MSDS Required Flag */
    public static final String MSDS_REQ_FLG = "MSDS Required Flag";

    /** Hazmat Flag */
    public static final String HAZ_MAT_FLG = "Hazmat Flag";

    /** Mercury Contained Flag */
    public static final String MERC_CNTN_FLG = "Mercury Contained Flag";

    /** ODC Tax Flag */
    public static final String ODC_TAX_FLG = "ODC Tax Flag";

    /** Primary Package UOM Flag */
    public static final String PRIM_PKG_UOM_FLG = "Primary Package UOM Flag";

    /** Interchangeability Flag */
    public static final String INCG_FLG = "Interchangeability Flag";

    /** Customer Merchandise Cross Reference Enable Flag */
    public static final String CUST_MDSE_XREF_ENBL_FLG = "Customer Merchandise Cross Reference Enable Flag";

    /** Primary Supplier Flag */
    public static final String PRIM_SPLY_FLG = "Primary Supplier Flag";

    /** Base Component Flag */
    public static final String BASE_CMPT_FLG = "Base Component Flag";

    /** Mandatory Component Flag */
    public static final String MND_CMPT_FLG = "Mandatory Component Flag";

    // Code Table Check
    /** Inventory Hard Allocation Type Code */
    public static final String INVTY_HARD_ALLOC_TP_CD = "Inventory Hard Allocation Type Code";

    /** RMA Requirement Type Code */
    public static final String RMA_REQ_TP_CD = "RMA Requirement Type Code";

    /** Easy pack Type Code */
    public static final String EASY_PACK_TP_CD = "Easy pack Type Code";

    /** Merchandise Registration Type Code */
    public static final String MDSE_RGTN_TP_CD = "Merchandise Registration Type Code";

    /** Merchandise Pricing Group */
    public static final String MDSE_PRC_GRP_CD = "Merchandise Pricing Group";

    /** Cycle Count Category Code */
    public static final String CYCLE_CNT_CATG_CD = "Cycle Count Category Code";

    /** Item Classfication */
    public static final String MDSE_ITEM_CLS_TP_CD = "Item Classfication";

    /** Marketing Segment */
    public static final String MKT_MDL_SEG_CD = "Marketing Segment";

    /** Status */
    public static final String MDSE_ITEM_STS_CD = "Status";

    /** Return Control Type */
    public static final String RTRN_CTRL_TP_CD = "Return Control Type";

    /** Return Disposition */
    public static final String RTRN_DSPL_TP_CD = "Return Disposition";

    /** Return To Parent Vendor Code */
    public static final String RTRN_TO_PRNT_VND_CD = "Return To Parent Vendor Code";

    //QC#4203
    /** Return Control Type Vendor Relation PK */
    public static final String RTRN_CTRL_TP_VND_RELN_PK = "Return Control Type Vendor Relation PK";

    /** Intangible Type */
    public static final String DS_INTG_MDSE_TP_CD = "Intangible Type";

    /** Tariff Code */
    public static final String TRF_CD = "Tariff Code";

    /** Return WH Code */
    public static final String RTRN_WH_CD = "Return WH Code";
    
    /** Return To Vendor Code */
    public static final String RTRN_TO_VND_CD = "Return To Vendor Code";

    /** Tax Code */
    public static final String TAX_EXEM_TP_CD = "Tax Code";

    /** Service Warranty Type Code */
    public static final String SVC_WTY_TP_CD = "Service Warranty Type Code";

    /** ImageWARE Remote Model */
    public static final String IWR_MDL_CD = "ImageWARE Remote Model";

    /** Manufacturer */
    public static final String MDSE_ITEM_MNF_CD = "Manufacturer";

    /** Back Order Impact Type */
    public static final String BACK_ORD_IMPCT_TP_CD = "Back Order Impact Type";

    /** Imaging Supply TYPE */
    public static final String IMG_SPLY_TP_CD = "Imaging Supply TYPE";

    /** Imaging Supply Color */
    public static final String IMG_SPLY_COLOR_TP_CD = "Imaging Supply Color";

    /** Imaging Supply Yield UOM */
    public static final String IMG_SPLY_YIELD_UOM_CD = "Imaging Supply Yield UOM";

    /** Imaging Supply Yield Type */
    public static final String IMG_SPLY_YIELD_TP_CD = "Imaging Supply Yield Type";

    /** Service Coverate Template Type */
    public static final String SVC_COV_TMPL_TP_CD = "Service Coverate Template Type";

    /** Service Allocation Type Code */
    public static final String SVC_ALLOC_TP_CD = "Service Allocation Type Code";

    /** Software Category */
    public static final String SW_CATG_CD = "Software Category";

    /** Software Type */
    public static final String SW_TP_CD = "Software Type";

    /** License Control */
    public static final String SW_LIC_CTRL_TP_CD = "License Control";

    /** Software Maintenance Controlled */
    public static final String SW_MAINT_CTRL_TP_CD = "Software Maintenance Controlled";

    /** Software Maintenance Type */
    public static final String SW_MAINT_TP_CD = "Software Maintenance Type";

    /** Service Charge Item Type Code */
    public static final String SVC_CHRG_ITEM_TP_CD = "Service Charge Item Type Code";

    /** Merchandise Item Billing Type Code */
    public static final String MDSE_ITEM_BILL_TP_CD = "Merchandise Item Billing Type Code";

    /** Default Source Procurement Type Code */
    public static final String DEF_SRC_PROCR_TP_CD = "Default Source Procurement Type Code";

    /** Parts Item Type Code */
    public static final String PRT_ITEM_TP_CD = "Parts Item Type Code";

    /** Software Product Category Code */
    public static final String SW_PROD_CATG_CD = "Software Product Category Code";

    /** Maintenance Item Type Code */
    public static final String MAINT_ITEM_TP_CD = "Maintenance Item Type Code";

    /** Service Coverage Base Type Code */
    public static final String SVC_COV_BASE_TP_CD = "Service Coverage Base Type Code";

    ///** National Motor Freight Classification Class Type Code */
    //public static final String NMFC_CLS_TP_CD = "National Motor Freight Classification Class Type Code";

    /** Freight Classification Code */
    public static final String FRT_CLS_CD = "Freight Classification Code";

    /** Imaging Supply Private Label Type Code */
    public static final String IMG_SPLY_PVT_LB_TP_CD = "Imaging Supply Private Label Type Code";

    /** Made in Country Code */
    public static final String MADE_IN_CTRY_CD = "Made in Country Code";

    /** Country Code */
    public static final String ASM_IN_CTRY_CD = "Country Code";

    /** MSRP Price Category Code */
    public static final String MSRP_PRC_CATG_CD = "MSRP Price Category Code";

    /** Floor Price Category Code */
    public static final String FL_PRC_CATG_CD = "Floor Price Category Code";

    /** Code Organization Level */
    public static final String MDSE_CMPSN_TP_CD = "Code Organization Level";

    /** Merchandise Item Relationship Type */
    public static final String MDSE_ITEM_RELN_TP_CD = "Merchandise Item Relationship Type";

    /** Vendor UOM Code */
    public static final String VND_UOM_CD = "Vendor UOM Code";

    // Master Check
    /** Planning Group Code */
    public static final String PRCH_GRP_CD = "Planning Group Code";

    /** Marketing Model */
    public static final String MKT_MDL_CD = "Marketing Model";

    /** Default Source Warehouse */
    public static final String DEF_SRC_WH_CD = "Default Source Warehouse";

    /** Default Source Sub Warehouse */
    public static final String DEF_SRC_SUB_WH_CD = "Default Source Sub Warehouse";

    /** ImageWARE remote Item */
    public static final String IWR_MDSE_CD = "ImageWARE remote Item";

    /** Bundle Maintenance Merchandise Code */
    public static final String BDL_MAINT_MDSE_CD = "Bundle Maintenance Merchandise Code";

    /** Merchandise Code */
    public static final String CHILD_MDSE_CD = "Merchandise Code (BOM List)";

    /** Order Taken Merchandise Code */
    public static final String CHILD_ORD_TAKE_MDSE_CD = "Order Taken Merchandise Code";

    // Integrity Check
    /** Item Number */
    public static final String MSG_ITEM_NUM = "Item Number";

    /** Mercury */
    public static final String MSG_AMER_MSTR = "Mercury";

    /** Merchandise Structure */
    public static final String MSG_MDSE_STRU = "Merchandise Structure";

    /** UOM Merchandise Code */
    public static final String MSG_MDSE_CD_S = "UOM Merchandise Code";

    /** Registration Target Merchandise Code */
    public static final String MSG_MDSE_CD = "Registration Target Merchandise Code";

    /** UOM EA */
    public static final String MSG_UOM_EA = "UOM EA";

    /** UOM Code */
    public static final String MSG_PKG_UOM_CD = "UOM Code";

    /** Primary UOM Flag */
    public static final String MSG_PRIM_PKG_UOM_FLG = "Primary UOM Flag";

    /** UOM */
    public static final String MSG_UOM = "UOM";

    /** EACH */
    public static final String MSG_PKG_UOM_CD_EA = "EACH";

    /** EACH Unbox */
    public static final String MSG_PKG_UOM_CD_EU = "EACH Unbox";

    /** Inch Length */
    public static final String MSG_IN_INCH_LG = "Inch Length";

    /** Inch Width */
    public static final String MSG_IN_INCH_WDT = "Inch Width";

    /** Inch Height */
    public static final String MSG_IN_INCH_HGT = "Inch Height";

    /** Inch Length */
    public static final String MSG_IN_POUND_WT = "Pound Weight";

    /** UOM's Package UOM Class Code */
    public static final String MSG_PKG_UOM_CLS_CD = "UOM's Package UOM Class Code";

    /** Hazardous Number */
    public static final String MSG_HAZ_MAT_CD = "Hazerdous Number";

    /** Manufactured Country */
    public static final String MSG_MADE_IN_CTRY_CD = "Manufactured Country";

    /** National Motor Freight Classification Class */
    public static final String MSG_NMFC_CLS_TP_CD = "National Motor Freight Classification Class";

    /** Set */
    public static final String MDSE_ITEM_TP_CD_SET = "Set";

    /** Inventory Tractable */
    public static final String MSG_INVTY_CTRL_FLG = "Inventory Trackable";

    /** Default Source Warehouse and Sub Warehouse */
    public static final String MDS_RTL_SWH = "Default Source Warehouse and Sub Warehouse";

    /** Serial Number Merchandise Code */
    public static final String MSG_MDSE_CD_SN = "Serial Number Merchandise Code";

    /** From Length */
    public static final String MSG_FROM_LEN = "From Length";

    /** To Length */
    public static final String MSG_TO_LEN = "To Length";

    /** Accounting Rules and Invoicing Rules */
    public static final String MSG_ACCT_INV_RULE = "Accounting Rules and Invoicing Rules";

    /** Deferred - Advance */
    public static final String MSG_DFRD_AD = "Deferred - Advance";

    /** Standard Cost */
    public static final String MSG_THIS_MTH_TOT_STD_COST_AMT = "Standard Cost";

    /** ARV Cost */
    public static final String MSG_ASSET_RECOV_COST_AMT = "ARV Cost";

    /** On */
    public static final String MSG_ON = "On";

    /** Warranty */
    public static final String MSG_SVC_WTY_TP_CD = "Warranty";

    /** Service Call Enabled */
    public static final String MSG_SVC_CALL_ENB_FLG = "Service Call Enabled";

    /** Install Base Trackable */
    public static final String MSG_INSTL_BASE_CTRL_FLG = "Install Base Trackable";

    /** ImageWARE Remote Enabled */
    public static final String MSG_IWR_ENB_FLG = "ImageWARE Remote Enabled";

    /** CPO Minimum Order Quantity */
    public static final String CPO_MIN_ORD_QTY = "CPO Minimum Order Quantity";

    /** CPO Maximum Order Quantity */
    public static final String CPO_MAX_ORD_QTY = "CPO Maximum Order Quantity";

    /** CPO Increment Order Quantity */
    public static final String CPO_INCR_ORD_QTY = "CPO Increment Order Quantity";

    /** Minimum Qty */
    public static final String MSG_CPO_MIN_ORD_QTY = "Minimum Qty";

    /** Maximum Qty */
    public static final String MSG_CPO_MAX_ORD_QTY = "Maximum Qty";

    /** Increment Qty */
    public static final String MSG_CPO_INCR_ORD_QTY = "Increment Qty";

    /** (Maximum Qty - Minimum Qty) */
    public static final String MSG_INCR_QTY = "(Maximum Qty - Minimum Qty)";

    /** MSRP Price */
    public static final String MSG_MSRP_PRC = "MSRP Price";

    /** Floor Price */
    public static final String MSG_FL_PRC = "Floor Price";

    /** MSRP Price Category */
    public static final String MSG_MSRP_PRC_CATG = "MSRP Price Category";

    /** Floor Price Category */
    public static final String MSG_FL_PRC_CATG = "Floor Price Category";

    /** Service Model */
    public static final String MSG_SVC_MDL = "Service Model";

    /** CMPSN: BOM Merchandise Code */
    public static final String MSE_PRNT_MDSE_CD_C = "BOM Merchandise Code";

    /** CMPSN: BOM Child Quantity */
    public static final String MSG_CHILD_MDSE_QTY_C = "BOM Child Quantity";

    /** CMPSN: BOM Effective From Date */
    public static final String MSG_EFF_FROM_DT_C = "BOM Effective From Date";

    /** CMPSN: BOM Child Merchandise Code */
    public static final String MSG_CHILD_MDSE_CD_C = "BOM Child Merchandise Code";

    /** CMPSN: Child Merchandise Code */
    public static final String CHILD_MDSE_CD_C = "Child Merchandise Code";

    /** CMPSN: Child Order Take Merchandise Code */
    public static final String CHILD_ORD_TAKE_MDSE_CD_C = "Child Order Take Merchandise Code";

    /** CMPSN: Child Kit Material Code */
    public static final String MSG_CHILD_KIT_MAT_CD_C = "Child Kit Material Code";

    /** CMPSN: Component Type */
    public static final String MSG_MDSE_CMPSN_TP_CD_C = "Component Type";

    /** Child Item Code */
    public static final String MSG_CHILD_ITEM_CD = "Child Item Code";

    /** SUPD: Supersede Merchandise Code */
    public static final String MSG_MDSE_CD_SD = "Supersede Merchandise Code";

    /** Supersede Create Date */
    public static final String SUPD_CRAT_DT = "Supersede Create Date";

    /** Supersede To Merchandise Code */
    public static final String SUPD_TO_MDSE_CD = "Supersede To Merchandise Code";

    /** MDSE_ITEM_FLIP_SET: Relation Merchandise Code */
    public static final String MSG_MDSE_CD_F = "Relation Merchandise Code";

    /** CUST_MDSE_XREF: Customer Cross Reference Merchandise Code */
    public static final String MSG_MDSE_CD_X = "Customer Cross Reference Merchandise Code";

    /** Customer Number and Customer Merchandise Code */
    public static final String MSG_DS_ACCT_NUM_CUST_MDSE_CD = "Customer Number and Customer Merchandise Code";

    /** ASL_HDR: ASL Header */
    public static final String MSG_ASL_HDR = "ASL Header";

    /** ASL_DTL: ASL Merchandise Code */
    public static final String MSG_MDSE_CD_A = "ASL Merchandise Code";

    /** ASL_DTL: ASL Effective From Date */
    public static final String MSG_EFF_FROM_DT_A = "ASL Effective From Date";

    /** ASL_DTL: ASL Effective Through Date */
    public static final String MSG_EFF_THRU_DT_A = "ASL Effective Through Date";

    /** Supplier/Supplier Site */
    public static final String MSG_SPLY_ITEM_NUM_VND_CD = "Supplier/Supplier Site";

    /** ASL Line */
    public static final String MSG_ASL_LINE = "ASL_LINE";

    /** ASL Primary Supplier Site */
    public static final String MSG_PRIM_VND = "ASL Primary Supplier Site";

    // Table
    /** MDSE */
    public static final String MSG_MDSE = "MDSE";

    /** MDSE_SFTY_INFO */
    public static final String MSG_MDSE_SFTY_INFO = "MDSE_SFTY_INFO";

    /** ORD_TAKE_MDSE */
    public static final String MSG_ORD_TAKE_MDSE = "ORD_TAKE_MDSE";

    /** MDSE_CST_UPD_HIST_HDR */
    public static final String MSG_MDSE_CST_UPD_HIST_HDR = "MDSE_CST_UPD_HIST_HDR";

    /** MDSE_CST_UPD_HIST_DTL */
    public static final String MSG_MDSE_CST_UPD_HIST_DTL = "MDSE_CST_UPD_HIST_DTL";

    /** CMPSN */
    public static final String MSG_CMPSN = "CMPSN";

    /** MDSE_STORE_PKG */
    public static final String MSG_MDSE_STORE_PKG = "MDSE_STORE_PKG";

    /** MDSE_SER_NUM_RNG */
    public static final String MSG_MDSE_SER_NUM_RNG = "MDSE_SER_NUM_RNG";

    /** SUPD_RELN_STAGE */
    public static final String MSG_SUPD_RELN_STAGE = "SUPD_RELN_STAGE";

    /** MDSE_ITEM_FLIP_SET */
    public static final String MSG_MDSE_ITEM_FLIP_SET = "MDSE_ITEM_FLIP_SET";

    /** CUST_MDSE_XREF */
    public static final String MST_CUST_MDSE_XREF = "CUST_MDSE_XREF";

    /** ASL_DTL */
    public static final String MST_ASL_DTL = "ASL_DTL";

    /** PRC_LIST_EQUIP */
    public static final String MST_PRC_LIST_EQUIP = "PRC_LIST_EQUIP";

    /** DS_MDL_CONFIG */
    public static final String MSG_DS_MDL_CONFIG = "DS_MDL_CONFIG";
    
    // QC#4342
    /** ASL_DTL */
    public static final String MST_MDSE_TERM_COND_OPT = "MDSE_TERM_COND_OPT";
    
    // START 2023/03/27 E.Watabe [QC#61207,ADD]
    /** MDSE_CD */
    public static final String MSG_PRM_MDSE_CD = "MDSE_CD";

    /** SUPD */
    public static final String MSG_PRM_TBL_SUPD = "SUPD";

    /** SUPD_RELN */
    public static final String MSG_PRM_TBL_SUPD_RELN = "SUPD_RELN";
    // END 2023/03/27 E.Watabe [QC#61207,ADD]

    // CUSA table
    /** CUSA Global Company Code */
    public static final String MSG_CUSA_GLBL_CMPY_CD = "CUSA Global Company Code";

    /** Parts Master Table ID */
    public static final String MSG_CUSA_USMM4100 = "Parts Master Table ID";

    /** CUSA_MDSE */
    public static final String MSG_CUSA_MDSE = CUSA_MDSE;

    /** CUSA_MDSE_SFTY_INFO */
    public static final String MSG_CUSA_MDSE_SFTY_INFO = CUSA_MDSE_SFTY_INFO;

    /** CUSA_CMPSN */
    public static final String MSG_CUSA_CMPSN = CUSA_CMPSN;

    /** CUSA_MDSE_STORE_PKG */
    public static final String MSG_CUSA_MDSE_STORE_PKG = CUSA_MDSE_STORE_PKG;

    /** CUSA_MDSE_SER_NUM_RNG */
    public static final String MSG_CUSA_MDSE_SER_NUM_RNG = CUSA_MDSE_SER_NUM_RNG;

    /** DEF_DAYS_PRI_ALLOC_NUM */
    public static final String MSG_DEF_DAYS_PRI_ALLOC_NUM = DEF_DAYS_PRI_ALLOC_NUM;

    /** S21 Parts */
    public static final String MSG_CUSA_PARTS = "S21 Parts";

    // Other
    /** INTG_PROD_CATG_CONV: @ 1 */
    public static final String MSG_INTG_PROD_CATG_CONV_01 = "INTG_PROD_CATG_CONV";

    /** INTG_PROD_CATG_CONV: @ 2 */
    public static final String MSG_INTG_PROD_CATG_CONV_02 = "MDSE_ITEM_TP_CD,COA_MDSE_TP_CD,SW_LIC_CTRL_TP_CD,SW_CATG_CD,INTNT_CONN_SW_CTRL_FLG";

    /** Sales Material Group */
    public static final String SLS_MAT_GRP = "Sales Material Group";

    /** Direct Sales Commission Group */
    public static final String DS_CMSN_GRP = "Direct Sales Commission Group";

    /** Custom Required Flag */
    public static final String CUSTOM_REQ_FLG = "Custom Required Flag";

    /** Show ETA Flag */
    public static final String SHOW_ETA_FLG = "Show ETA Flag";

    /** Production Auth Required Flag */
    public static final String PROD_AUTH_REQ_FLG = "Production Auth Required Flag";

    /** Write Down Flag */
    public static final String WRT_DOWN_FLG = "Write Down Flag";

    /** FOB Re-Calc Execute Flag */
    public static final String FOB_RE_CALC_EXEC_FLG = "FOB Re-Calc Execute Flag";

    /** Carry Cahnge Calc Manual Flag */
    public static final String CARRY_CHRG_CALC_MAN_FLG = "Carry Cahnge Calc Manual Flag";

    /** Shipping Order Hold Flag */
    public static final String SHPG_ORD_HLD_FLG = "Shipping Order Hold Flag";

    /** SO Auth Required Flag */
    public static final String SO_AUTH_REQ_FLG = "SO Auth Required Flag";

    /** Expense Item Flag */
    public static final String EXP_ITEM_FLG = "Expense Item Flag";

    /** Distribution By WH Flag */
    public static final String DIST_BY_WH_FLG = "Distribution By WH Flag";

    /** Manual Price Allow On Order Flag */
    public static final String MAN_PRC_ALLW_ON_ORD_FLG = "Manual Price Allow On Order Flag";

    /** Automatic Licence Flag */
    public static final String AUTO_LIC_ALLOC_FLG = "Automatic Licence Flag";

    /** Customer Install Flag */
    public static final String CUST_ISTL_FLG = "Customer Install Flag";

    /** Site Survey Required Flag */
    public static final String SITE_SRVY_REQ_FLG = "Site Survey Required Flag";

    /** Vendor Return Flag */
    public static final String VND_RTRN_FLG = "Vendor Return Flag";

    /** Asset Manage Flag */
    public static final String ASSET_MGT_FLG = "Asset Manage Flag";

    /** UPC or SCC14 Label Flag */
    public static final String UPC_OR_SCC14_LB_FLG = "UPC or SCC14 Label Flag";
    // Add Start QC#9073 2016/05/27
    /** Max Date Value */
    public static final String MAX_DATE_VALUE = "99991231";
    // Add End QC#9073 2016/05/27

    // 2019/06/10 QC#50763 Add Start
    /** REV_ACCT_START_WITH_CD */
    public static final String REV_ACCT_START_WITH_CD = "4";

    /** Product Code:ZZ(ADMINISTRATION) @ 1 */
    public static final String MSG_PROD_CD_ZZ_01 = "Product Code:ZZ(ADMINISTRATION)";

    /** Product Code:ZZ(ADMINISTRATION) @ 2 */
    public static final String MSG_PROD_CD_ZZ_02 = "because entered Sales Account in Revenue.";
    // 2019/06/10 QC#50763 Add End

    // Add Start 2019/12/13 QC#54218
    /** Price Category Code */
    public static final String PRC_LIST_EQUIP_PRC_AMT = "Price List Equipment Price Amount";
    // Add End   2019/12/13 QC#54218

    // 2019/12/16 QC#54859 Add Start
    /** Inch Length Uom Code */
    public static final String MSG_IN_INCH_LG_UOM_CD = "Inch Length Uom Code";

    /** Inch Width Uom Code */
    public static final String MSG_IN_INCH_WDT_UOM_CD = "Inch Width Uom Code";

    /** Inch Height Uom Code */
    public static final String MSG_IN_INCH_HGT_UOM_CD = "Inch Height Uom Code";

    /** Pound Weight Uom Code */
    public static final String MSG_IN_POUND_WT_UOM_CD = "Pound Weight Uom Code";
    // 2019/12/16 QC#54859 Add End

    // 2020/04/13 QC#56494 Add Start
    /** internal Item Flag */
    public static final String ITRL_ITEM_FLG = "Internal Item Flag";
    // 2020/04/13 QC#56494 Add End
    
    // Add start 2023/01/05 QC#60892
    public static final String PARTS_PRC_CATG_CD = "0000226";
    // Add end 2023/01/05 QC#60892

    // 2023/04/07 QC#61371 START
    /** DB Param Name: Global Company Code */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB Param Name: Search Option Application Id */
    public static final String DB_PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /** DB Param Name: Supplier Site */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /** The length of [@] is over than definition. Please enter within [@] characters. */
    public static final String NPAM1652E = "NPAM1652E";

    /** Supplier Item Code */
    public static final String SPLY_ITEM_CD = "Supplier Item Code";
    // 2023/04/07 QC#61371 END

}
