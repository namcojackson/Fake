/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC155001.constant;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 * 2016/07/15   Fujitsu         K.Sato          Update          S21_NA#7123
 * 2016/07/20   Fujitsu         K.Sato          Update          S21_NA#8611
 * 2016/07/21   Fujitsu         K.Sato          Update          S21_NA#5383
 * 2016/09/01   Fujitsu         Y.Taoka         Update          S21_NA#7942
 * 2016/09/07   Hitachi         T.Mizuki        Update          S21_NA#11774
 * 2016/12/14   Fujitsu         Y.Kanefusa      Update          S21_NA#16603
 * 2017/02/06   Fujitsu         T.Yoshida       Update          S21_NA#15510
 * 2017/08/04   Fujitsu         S.Ohki          Update          S21_NA#20374
 * 2017/08/22   Fujitsu         S.Ohki          Update          S21_NA#20022
 * 2017/09/16   Fujitsu         S.Ohki          Update          S21_NA#21064
 * 2017/10/20   Fujitsu         N.Sugiura       Update          S21_NA#20246
 * 2017/10/26   Fujitsu         A.Kosai         Update          S21_NA#21100
 * 2017/12/06   Fujitsu         K.Ishizuka      Update          S21_NA#22664
 * 2017/12/15   Fujitsu         K.Ishizuka      Update          S21_NA#19804(Sol#349)
 * 2018/03/01   Fujitsu         M.Ohno          Update          S21_NA#24117
 * 2018/03/15   Fujitsu         K.Ishizuka      Update          S21_NA#24253
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2019/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#30819
 * 2019/04/24   Fujitsu         Hd.Sugawara     Update          S21_NA#31125,QC#31126
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 * 2022/10/07   CITS            F.Fadriquela    Update          QC#60623
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/01/30   CITS            R.Azucena       Update          QC#61094
 * 2023/10/06   Hitachi         K.Watanabe      Update          QC#54186
 * 2024/03/01   CITS            T.Aizawa        Update          QC#54186
 *</pre>
 */
public class NWZC155001Constant {
    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** "Global Company Code" is required. */
    public static final String NWZM0188E = "NWZM0188E";

    /** CPO Order Number for the parameter is not set. */
    public static final String NWZM1205E = "NWZM1205E";

    /** The data specified does not exist in DS_CPO. */
    public static final String NWZM1207E = "NWZM1207E";

    /** Sales Date of the parameter is not set. */
    public static final String NWZM1309E = "NWZM1309E";

    /** JOB ID for the parameter is not set. */
    public static final String NWZM1579E = "NWZM1579E";

    /** User ID is required. */
    public static final String NWZM0336E = "NWZM0336E";

    /** Sales Date is an illegal format. */
    public static final String NWZM1753E = "NWZM1753E";

    /** "Order Date" is not entered. */
    public static final String NWZM0394E = "NWZM0394E";

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** Failed to insert the DS_CPO_DI_CHK_RSLT_HDR. */
    public static final String NWZM1580E = "NWZM1580E";

    /** Failed to insert the DS_CPO_DI_CHK_RSLT_DTL. */
    public static final String NWZM1581E = "NWZM1581E";

    /** Failed updating of DS_CPO. */
    public static final String NWZM1208E = "NWZM1208E";

    /** Failed to update the DS_CPO_DI_CHK_RSLT_DTL. */
    public static final String NWZM1582E = "NWZM1582E";

    /** It failed to update the HLD. */
    public static final String NWZM0080E = "NWZM0080E";

    /** It failed to get GP%. */
    public static final String NWZM1942E = "NWZM1942E";

    /** Mandatory definition error. (Table: DI_MND_CHK_DFN) */
    public static final String NWZM1945E = "NWZM1945E";

    /** Mode (New) */
    public static final String MODE_NEW = "01";

    /** Mode (Modify) */
    public static final String MODE_MOD = "02";

    /** Mode (Delete) */
    public static final String MODE_DEL = "03";

    /** Check Mode (Line and RMA) */
    public static final String CHK_MODE_LINE_RMA = "01";

    /** Check Mode (Line Only) */
    public static final String CHK_MODE_LINE = "02";

    /** Check Mode (RMA Only) */
    public static final String CHK_MODE_RMA = "03";

    /** Check Mode (Header Only) */
    public static final String CHK_MODE_HEADER = "04";

    /** Lease PO Format */
    public static final String LEASE_PO_FORMAT = "[0-9]+/[0-9]+";

    /** Row Number */
    public static final String ROW_NUM = "1";

    /** Header Level Check */
    public static final int HDR_LVL_CHK = -1;

    /** HDD Removal Code : HDD Remove(01) */
    public static final String HDD_REMOVE = "01";

    /** HDD Removal Code : HDD Erase(02) */
    public static final String HDD_ERASE = "02";

    /** CPO Detail Line Sub Number : Set Item(000) */
    public static final String CPO_DTL_LINE_SUB_NUM_SET = "000";

    /** MDSE_TP_CTXT_TP : BUYOUTS_REBATES */
    public static final String MDSE_CTX_BUYOUTS_REBATES = "BUYOUTS_REBATES";

    /** MDSE_TP_CTXT_TP : SUBSCRIPTION_SPRT */
    public static final String MDSE_CTX_SUBSCRIPT_SPRT = "SUBSCRIPTION_SPRT";

    // /** MDSE_TP_CTXT_TP : E099_1025_MERCH_TYPE */
    // public static final String E099_1025_MERCH_TYPE = "E099_1025_MERCH_TYPE"; // QC#16603 2016/12/14 Del

    // /** MDSE_TP_CTXT_TP : E099_1030_MERCH_TYPE */
    // public static final String E099_1030_MERCH_TYPE = "E099_1030_MERCH_TYPE"; // QC#16603 2016/12/14 Del

    /** MDSE_TP_CTXT_TP : MDSE_CTX_PROF_SERVICES */
    public static final String MDSE_CTX_PROF_SERVICES = "MDSE_CTX_PROF_SERVICES";

    /** MDSE_TP_CTXT_TP : PROF_SERVICES */
    public static final String PROF_SERVICES = "PROF_SERVICES";

    /** MDSE_TP_CTXT_TP : CPO_SVC_CONFIG_ITEMS */
    public static final String CPO_SVC_CONFIG_ITEMS = "CPO_SVC_CONFIG_ITEMS"; // QC#16603 2016/12/14 Add

    /** MDSE_TP_CTXT_TP : CPO_SVC_ADDL_BASE_ITEMS */
    public static final String CPO_SVC_ADDL_BASE_ITEMS = "CPO_SVC_ADDL_BASE_ITEMS"; // QC#16603 2016/12/14 Add

    /** ORD_CATG_CTX_TP : SERVICING_DEALER_ORDERS */
    public static final String SERVICING_DEALER_ORDERS = "SERVICING_DEALER_ORDERS";

    /** ORD_CATG_CTX_TP : RENTAL_SHELL_REQUIRED */
    public static final String RENTAL_SHELL_REQUIRED = "RENTAL_SHELL_REQUIRED";

    /** ORD_CATG_CTX_TP : DEALER_ORDER */
    public static final String DEALER_ORDER = "DEALER_ORDER";

    // START 2024/03/01 t.aizawa [QC#61771, ADD]
    /** DS_COND_CONST.DS_COND_CONST_GRP_ID : NWZC1550_LINE_CATG */
    public static final String NWZC1550_LINE_CATG = "NWZC1550_LINE_CATG";
    // END 2024/03/01 t.aizawa [QC#61771, ADD]

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmss";

    /** DI CHECK :E099-1030 Error Message Parameter(Rental Shell IB Control) */
    public static final String RENTAL_SHELL_IB_CONTROL_MSGPARAM = "There is shell Accessory Charge item or Rental Equipment Charge Base & Accessory item which is not IB controlled.";

    /** DI CHECK :E099-1060 Error Message Parameter(HDD Erase) */
    public static final String HDD_ERASE_MSGPARAM = "Hard Drive has to be erased";

    /** DI CHECK :E099-1060 Error Message Parameter(HDD Remove) */
    public static final String HDD_RMV_MSGPARAM = "Hard Drive has to be replaced";

    /** DI CHECK :E099-1066 Error Message Parameter(Contact Person Validation) */
    public static final String MSGPARAM_CTAC_PSN_VALID_CHECK = "Contact Person have to be entered for ";

    /** DI CHECK :E099-1065 Error Message Parameter(eManage Contact Person) */
    public static final String E099_1065_MSG_EMANAGE = "MyCSA Contact Person";  // QC#20246 Mod

    /** DI CHECK :E099-1065 Error Message Parameter(Subscription Service) */
    public static final String E099_1065_MSG_SUBSCRIPT_SERVICE = "Subscription Service";

    /** DI CHECK :E099-1065 Error Message Parameter(Equipment/Parts/Supply Order) */
    public static final String E099_1065_MSG_EQUIPMENT_ORDER = "Equipment/Parts/Supply Order";

    /** DI CHECK :E099-1065 Error Message Parameter(Equipment/Service) */
    public static final String E099_1065_MSG_EQUIPMENT_SERVICE = "Equipment/Service";

    /** VAR_CHAR_CONST Key CTAC_PSN_TP_CD_EMANAGE */
    public static final String VAR_CHAR_CONST_NM_EMANAGE = "CTAC_PSN_TP_CD_EMANAGE";

    /** VAR_CHAR_CONST Key CTAC_PSN_TP_CD_SUBSCRIPTION_SUPPORT */
    public static final String VAR_CHAR_CONST_NM_SUBSCRIPTION_SUPPORT = "CTAC_PSN_TP_CD_SUBSCRIPTION_SU";

    /** VAR_CHAR_CONST Key CTAC_PSN_TP_CD_EQUIP_SERVICE */
    public static final String VAR_CHAR_CONST_NM_EQUIP_SERVICE = "CTAC_PSN_TP_CD_EQUIP_SERVICE";

    /** Missing required Rebate Item %s */
    public static final String MSG_NO_REBATE = "Missing required Rebate Item %s"; // QC#10734 Add

    /** Incorrect, Inactive or Missing, Compliance Code/Association Value */
    public static final String MSG_NO_ASSOCIATION = "Incorrect, Inactive or Missing, Compliance Code/Association Value"; // QC#10441 Mod

    /** Customer CSMP# %s eligible for both Rebate Item %s and Association Value : %s missing. */
    public static final String MSG_NO_ASS_AND_REB = "Customer CSMP# %s eligible for both Rebate Item %s and Association Value : %s missing. ";

    /** DI CHECK :E099-1220 Error Message Parameter(EMSD Sales Rep) */
    public static final String EMSD_SLS_REP_MSGPARAM = "EMSD Non-Quota Sales Rep is required."; // Add S21_NA#15510

    /** Auto Add Supply item to be added is not registered. */
    public static final String AUTO_ADD_SPLY_ITEM_MSGPARAM = "Auto Add Supply item to be added is not registered."; // QC#20022 Add

    /** Service Installation Rule (No Install:03) */
    public static final String SVC_ISTL_RULE_NUM_NO_INSTALL = "03"; // Add 2017/10/26 CSA S21_NA#21100

    // 2019/11/22 QC#54213 Add Start
    /** Machine Status (No Required Service Shell) */
    public static final String RTL_WH_BILL_ONLY = "BO";
    // 2019/11/22 QC#54213 Add End

    /** DI CHECK :E099-1076,E099-1081 Error Message Parameter(Install Division Required) */
    public static final String ISTL_DIV_REQUIRED_MSGPARAM = "'Install Division' is mandatory to be entered on the order "; // Add 2017/10/26 CSA S21_NA#21100

    /** DI CHECK :E099-1110 Item#[@] is not allowed to enter here. Service allocation type of the item must be blank except for Optima/Subs Service. */
    public static final String E099_1110_ERROR_MSG = "Item#[@] is not allowed to enter here. Service allocation type of the item must be blank except for Optima/Subs Service.";

    /** DI Check :E099-1250 Error Message */
    public static final String E099_1250_ERROR_MSG = "CFS Account Number must be selected for the Bill-To.";

    /** DI Check :E099-1250 Error Message */
    public static final String MSG_ADD_ACCESSORY_SHELL_REQUEST = "Add To Contract on Shell Line is required.";

    // START 2023/01/30 R.Azucena [QC#61094, ADD]
    /** DI Check :E099-1330 Error Message */
    public static final String E099_1330_MSG = "LOAN Order Type should have LOAN ORDERS Line Category.";
    // END 2023/01/30 R.Azucena [QC#61094, ADD]

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    /** DI Check E099_1340 Error Message */
    public static final String E099_1340_MSG = "To Be Installed By or Service Provided By is not set.";
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]
    /*****************************************************************
     * DI Check Code
     ****************************************************************/

    /** DI Check Code : E099-1000 */
    public static final String E099_1000 = "E099-1000";

    /** DI Check Code : E099-1005 */
    public static final String E099_1005 = "E099-1005";

    /** DI Check Code : E099-1010A */
    public static final String E099_1010A = "E099-1010A";

    /** DI Check Code : E099-1010B */
    public static final String E099_1010B = "E099-1010B";

    /** DI Check Code : E099-1015 */
    public static final String E099_1015 = "E099-1015";

    /** DI Check Code : E099-1020A */
    public static final String E099_1020A = "E099-1020A";

    /** DI Check Code : E099-1020B */
    public static final String E099_1020B = "E099-1020B";

    /** DI Check Code : E099-1025 */
    public static final String E099_1025 = "E099-1025";

    // 2017/08/04 S21_NA#20374 Add Start
    /** DI Check Code : E099-1025A */
    public static final String E099_1025A = "E099-1025A";
    // 2017/08/04 S21_NA#20374 Add End

    /** DI Check Code : E099-1030 */
    public static final String E099_1030 = "E099-1030";

    /** DI Check Code : E099-1035A */
    public static final String E099_1035A = "E099-1035A";

    /** DI Check Code : E099-1035B */
    public static final String E099_1035B = "E099-1035B";

    /** DI Check Code : E099-1040 */
    public static final String E099_1040 = "E099-1040";

    /** DI Check Code : E099-1045 */
    public static final String E099_1045 = "E099-1045";

    /** DI Check Code : E099-1050 */
    public static final String E099_1050 = "E099-1050";

    /** DI Check Code : E099-1055 */
    public static final String E099_1055 = "E099-1055";

    /** DI Check Code : E099-1060 */
    public static final String E099_1060 = "E099-1060";

    /** DI Check Code : E099-1065A */
    public static final String E099_1065A = "E099-1065A";

    /** DI Check Code : E099-1065B */
    public static final String E099_1065B = "E099-1065B";

    /** DI Check Code : E099-1065C */
    public static final String E099_1065C = "E099-1065C";
    
    /** DI Check Code : E099-1065D */
    public static final String E099_1065D = "E099-1065D"; // ADD 2017/12/06 CSA S21_NA#22664

    /** DI Check Code : E099-1070 */
    public static final String E099_1070 = "E099-1070";

    /** DI Check Code : E099-1075 */
    public static final String E099_1075 = "E099-1075";

    /** DI Check Code : E099-1080 */
    public static final String E099_1080 = "E099-1080";

    /** DI Check Code : E099-1081 */
    public static final String E099_1081 = "E099-1081"; // Add 2017/10/26 CSA S21_NA#21100

    /** DI Check Code : E099-1085 */
    public static final String E099_1085 = "E099-1085";

    /** DI Check Code : E099-1090 */
    public static final String E099_1090 = "E099-1090";

    /** DI Check Code : E099-1100 */
    public static final String E099_1100 = "E099-1100";

    /** DI Check Code : E099-1110 */
    public static final String E099_1110 = "E099-1110";

    /** DI Check Code : E099-IT01 */
    public static final String E099_IT01 = "E099-IT01";

    /** DI Check Code : E099-IT02 */
    public static final String E099_IT02 = "E099-IT02";

    /** DI Check Code : E099-IT03 */
    public static final String E099_IT03 = "E099-IT03";

    /** DI Check Code : E099-1210 */
    public static final String E099_1210 = "E099-1210";

    /** DI Check Code : E099-1200 */
    public static final String E099_1200 = "E099-1200"; // Mod 2016/09/07 CSA S21_NA#11774

    /** DI Check Code : E099-1200 */
    public static final String E099_1200A = "E099-1200A"; // QC#24245 2018/06/13 Add

    /** DI Check Code : E099-1220 */
    public static final String E099_1220 = "E099-1220"; // Add S21_NA#15510

    /** DI Check Code : E099-1230 */
    public static final String E099_1230 = "E099-1230"; // Add S21_NA#20022

    /** DI Check Code : E099-1240 */
    public static final String E099_1240 = "E099-1240"; // Add S21_NA#21064

    /** DI Check Code : E099-1250 */
    public static final String E099_1250 = "E099-1250"; // Add S21_NA#21471

    /** DI Check Code : E099-1260A */
    public static final String E099_1260A = "E099-1260A"; // ADD 2017/12/15 CSA S21_NA#19804(Sol#349)

    /** DI Check Code : E099-1260B */
    public static final String E099_1260B = "E099-1260B"; // ADD 2017/12/15 CSA S21_NA#19804(Sol#349)

    /** DI Check Code : E099-1270 */
    public static final String E099_1270 = "E099-1270"; // add 2018/03/01 S21_NA#24117
    
    /** DI Check Code : E099-1280 */
    public static final String E099_1280 = "E099-1280"; // 2018/03/15 S21_NA#24253 Add
    
    /** DI Check Code : E099-1290 */
    public static final String E099_1290 = "E099-1290"; // 2019/04/04 S21_NA#30819 Add

    // Add Start 2019/04/24 QC#31125,QC#31126
    /** DI Check Code : E099-1300A */
    public static final String E099_1300A = "E099-1300A";

    /** DI Check Code : E099-1300B */
    public static final String E099_1300B = "E099-1300B";
    // Add End 2019/04/24 QC#31125,QC#31126

    // 2019/11/22 QC#54213 Add Start
    /** DI Check Code : E099-1310 */
    public static final String E099_1310 = "E099-1310";
    // 2019/11/22 QC#54213 Add End

    // 2022/11/25 QC#60398 Add Start
    /** DI Check Code : E099-1310 */
    public static final String E099_1320 = "E099-1320";
    // 2022/11/25 QC#60398 Add Start

    // START 2022/10/07 F.Fadriquela [QC#60623, ADD]
    /** Being updated by another user, therefore update could not be processed. */
    public static final String NWAM0242E = "NWAM0242E";
    // END 2022/10/07 F.Fadriquela [QC#60623, ADD]

    // START 2023/01/30 R.Azucena [QC#61094, ADD]
    /** DI Check Code : E099-1330 */
    public static final String E099_1330 = "E099-1330";
    // END 2023/01/30 R.Azucena [QC#61094, ADD]

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    /** DI Check Code : E099-1340 */
    public static final String E099_1340 = "E099-1340";
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]
}
