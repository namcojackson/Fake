/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC107001.constant;

/**
 * <pre>
 * Send Task Info to Click Software
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2016   Fujitsu         S.Nakai         Create          QC#9677
 * 06/16/2016   Hitachi         T.Iwamoto       Update          QC#9677
 * 06/28/2016   Hitachi         T.Iwamoto       Update          QC#4467
 * 07/05/2016   Hitachi         T.Iwamoto       Update          QC#11338
 * 09/12/2016   Hitachi         K.Yamada        Update          QC#14357
 * 11/02/2016   Hitachi         N.Arai          Update          QC#15654
 * 11/07/2016   Hitachi         N.Arai          Update          QC#15784
 * 11/15/2016   Hitachi         T.Mizuki        Update          QC#15891
 * 11/17/2016   Hitachi         N.Arai          Update          QC#16020
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 03/07/2017   Hitachi         K.Kojima        Update          QC#17943
 * 09/04/2017   Hitachi         M.Kidokoro      Update          QC#20862
 * 2018/01/31   Hitachi         K.Kojima        Update          QC#21112
 * 2018/02/19   CITS            M.Naito         Update          QC#21112
 * 2018/03/22   Hitachi         T.Tomita        Update          QC#18713
 * 2018/06/19   CITS            M.Naito         Update          QC#26763
 * 2018/10/23   Hitachi         T.Tomita        Update          QC#28565
 * 2019/04/02   Hitachi         A.Kohinata      Update          QC#31027
 * 2019/08/22   Hitachi         K.Fujimoto      Update          QC#51206
 * 2019/10/02   Hitachi         K.Fujimoto      Update          QC#53864
 * 2019/10/21   Hitachi         K.Fujimoto      Update          QC#53441
 * 2019/11/18   Hitachi         K.Fujimoto      Update          QC#54391
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 * 2020/04/08   Hitachi         A.Kohinata      Update          QC#56328
 * 2020/06/18   Hitachi         K.Yamada        Update          QC#57175
 * 2020/12/22   CITS            T.Wada          Update          QC#58154
 * 2022/05/23   Hitachi         D.Yoshida       Update          QC#60058
 * 2022/09/02   Hitachi         K.Kitachi       Update          QC#60536
 * 2023/03/02   Hitachi         K.Watanabe      Update          QC#60926
 * 2023/05/16   Hitachi         K.Kitachi       Update          QC#61085
 *</pre>
 */
public interface NSZC107001Constant {

    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";
    /** Input parameter "Sales Date" is a mandatory field. */
    String NSZM0002E = "NSZM0002E";
    /** Input parameter "Service Task Number" is a mandatory field. */
    String NSZM0082E = "NSZM0082E";
    /** Failed to update the SVC_TASK. */
    String NSZM0167E = "NSZM0167E";
    /** Failed to update the FSR_EVEINT. */
    String NSZM0170E = "NSZM0170E";
    /** It failed to send to Click. (Send Task CallID is not found.) */
    String NSZM1001W = "NSZM1001W";
    /** It failed to send to Click. (Send Task Status is not found.) */
    String NSZM1002W = "NSZM1002W";
    /** It failed to send to Click. (Send Task CSAMachineSerialNo is not found.) */
    String NSZM1003W = "NSZM1003W";
    /** It failed to send to Click. (Send Task CustomerEmail is not found.) */
    String NSZM1004W = "NSZM1004W";
    /** It failed to send to Click. (Send Task ExternalRefID is not found.) */
    String NSZM1005W = "NSZM1005W";
    /** It failed to send to Click. (Send Task TaskType is not found.) */
    String NSZM1006W = "NSZM1006W";
    /** It failed to send to Click. (Send Task District is not found.) */
    String NSZM1007W = "NSZM1007W";
    /** It failed to send to Click. (Send Task Street is not found.) */
    String NSZM1008W = "NSZM1008W";
    /** It failed to send to Click. (Send Task City is not found.) */
    String NSZM1009W = "NSZM1009W";
    /** It failed to send to Click. (Send Task State is not found.) */
    String NSZM1010W = "NSZM1010W";
    /** It failed to send to Click. (Send Task CSA_Postalcode is not found.) */
    String NSZM1011W = "NSZM1011W";
    /** It failed to send to Click. (Send Task CountryID is not found.) */
    String NSZM1012W = "NSZM1012W";
    /** It failed to send to Click. (Send Task CSA_CustomerName is not found.) */
    String NSZM1013W = "NSZM1013W";
    /** It failed to send to Click. (Send Task CSA Territory Mapper is not found.) */
    String NSZM1014W = "NSZM1014W";
    /** The corresponding data does not exist in "VAR_CHAR_CONST". */
    String NSZM0102E = "NSZM0102E";
    /** The corresponding data does not exist in "NUM_CONST" .*/
    String NSZM1015E = "NSZM1015E";
    /** Error occurred while calling send to Click API. SVC_TASK_NUM[@] */
    String NSZM1016E = "NSZM1016E";
    /** Error occurred while calling send Task API. @ SVC_TASK_NUM[@] */
    String NSZM1017E = "NSZM1017E";
    /** Warning occurred while calling send Task API. @ SVC_TASK_NUM[@] */
    String NSZM1018W = "NSZM1018W";
    /** The target Service Task is not found. */
    String NSZM1019I = "NSZM1019I";
    /** Error occurred while calling send Multiple to Click API. SVC_TASK_NUM[@] */
    String NSZM1020E = "NSZM1020E";
    /** Error occurred while calling send Update Task to Click API. SVC_TASK_NUM[@] */
    String NSZM1021E = "NSZM1021E";
    // Add Start 2018/10/23 QC#28565
    /** Error occurred while calling send Process Task EX to Click API. */
    String NSZM1350E = "NSZM1350E";
    /** Error occurred while calling send Multiple Operations to Click API. */
    String NSZM1351E = "NSZM1351E";
    // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//    /** Error occurred while calling send Related Update Task to Click API. */
//    String NSZM1352E = "NSZM1352E";
    // END 2023/05/16 K.Kitachi [QC#61085, DEL]
    /** Error occurred while calling send Update Task to Click API. */
    String NSZM1353E = "NSZM1353E";
    // Add Start 2019/08/23 QC#51206 K.Fujimoto
    /** Error occurred while calling send Process Task EX to Click API.(No Send Mail) */
    String NSZM1362E = "NSZM1362E";
    /** Error occurred while calling send Multiple Operations to Click API.(No Send Mail) */
    String NSZM1363E = "NSZM1363E";
    // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//    /** Error occurred while calling send Related Update Task to Click API.(No Send Mail) */
//    String NSZM1364E = "NSZM1364E";
    // END 2023/05/16 K.Kitachi [QC#61085, DEL]
    /** Error occurred while calling send Update Task to Click API.(No Send Mail) */
    String NSZM1365E = "NSZM1365E";
    // End Start 2019/08/23 QC#51206 K.Fujimoto

    /** varchar const : NSZC1070_TIME_FORMAT */
    String NSZC1070_TIME_FORMAT = "NSZC1070_TIME_FORMAT";
    /** varchar const : NSZC1070_CALENDAR */
    String NSZC1070_CALENDAR = "NSZC1070_CALENDAR";
    /** varchar const : NSZC1070_GIS_DATA_SRC */
    String NSZC1070_GIS_DATA_SRC = "NSZC1070_GIS_DATA_SRC";
    /** Num const : NSZC1070_MAX_READ_RATE */
    String NSZC1070_MAX_READ_RATE = "NSZC1070_MAX_READ_RATE";
    /** Num const : NSZC1070_MDL_DURN_TM_SEC */
    String NSZC1070_MDL_DURN_TM_SEC = "NSZC1070_MDL_DURN_TM_SEC";
    /** Num const : NSZC1070_INFO_LOG */
    String NSZC1070_INFO_LOG = "NSZC1070_INFO_LOG";
    /** Num const : NSZC1070_INFO_LOG */
    String NSZC1070_ERR_LOG = "NSZC1070_ERR_LOG";
    // Add Start 2019/08/23 QC#51206 K.Fujimoto
    /** Num const : NSZC1070_SEND_MAIL_EXCLD_COND */
    String NSZC1070_SEND_MAIL_EXCLD_COND = "NSZC1070_SEND_MAIL_EXCL_COND";
    /** Num const : NSZC1070_RTRY_EXCLD_COND */
    String NSZC1070_RTRY_EXCLD_COND = "NSZC1070_RTRY_EXCLD_COND";
    // add start 2020/03/03 QC#56123
    /** Num const : NSXC0030_MTR_UP_THRHD_FCT_NUM */
    String NSXC0030_MTR_UP_THRHD_FCT_NUM = "NSXC0030_MTR_UP_THRHD_FCT_NUM";
    // add end 2020/03/03 QC#56123

    /** COMMA. */
    String COMMA = ",";
    // Add End   2019/08/23 QC#51206 K.Fujimoto

    // Send task Info Column Name
    /** SVC_TASK_NUM. */
    String SVC_TASK_NUM = "SVC_TASK_NUM";
    /** TECH_CD. */
    String TECH_CD = "TECH_CD";
    /** SER_NUM. */
    String SER_NUM = "SER_NUM";
    /** SVC_MACH_MSTR_PK. */
    String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** CUST_MACH_CTRL_NUM. */
    String CUST_MACH_CTRL_NUM = "CUST_MACH_CTRL_NUM";
    /** SVC_PBLM_TP_CD. */
    String SVC_PBLM_TP_CD = "SVC_PBLM_TP_CD";
    /** SVC_BILL_TP_CD. */
    String SVC_BILL_TP_CD = "SVC_BILL_TP_CD";
    // START 2017/03/07 K.Kojima [QC#17943,ADD]
    /** SVC_BILL_TP_DESC_TXT. */
    String SVC_BILL_TP_DESC_TXT = "SVC_BILL_TP_DESC_TXT";
    // END 2017/03/07 K.Kojima [QC#17943,ADD]
    /** CUST_EML_ADDR. */
    String CUST_EML_ADDR = "CUST_EML_ADDR";
    /** MACH_DOWN_FLG. */
    String MACH_DOWN_FLG = "MACH_DOWN_FLG";
    /** FSR_NUM. */
    String FSR_NUM = "FSR_NUM";
    /** DS_SVC_CALL_TP_CD. */
    String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";
    /** XTRNL_CALL_TP_REF_TXT */
    String XTRNL_CALL_TP_REF_TXT = "XTRNL_CALL_TP_REF_TXT";
    /** CUST_TEL_NUM. */
    String CUST_TEL_NUM = "CUST_TEL_NUM";
    /** ERL_START_TS. */
    String ERL_START_TS = "ERL_START_TS";
    /** LATE_START_TS. */
    String LATE_START_TS = "LATE_START_TS";
    /** MDL_DURN_TM_NUM. */
    String MDL_DURN_TM_NUM = "MDL_DURN_TM_NUM";
    /** T_MDL_NM. */
    String T_MDL_NM = "T_MDL_NM";
    /** SVC_TASK_STS_NM. */
    String SVC_TASK_STS_NM = "SVC_TASK_STS_NM";
    /** TECH_NM. */
    String TECH_NM = "TECH_NM";
    /** FLD_SVC_BR. */
    String FLD_SVC_BR = "FLD_SVC_BR";
    /** FLD_SVC_BR_CD. */
    String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";
    /** FIRST_LINE_ADDR. */
    String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
    /** SCD_LINE_ADDR. */
    String SCD_LINE_ADDR = "SCD_LINE_ADDR";
    /** CTY_ADDR. */
    String CTY_ADDR = "CTY_ADDR";
    /** ST_CD. */
    String ST_CD = "ST_CD";
    /** POST_CD. */
    String POST_CD = "POST_CD";
    /** CTRY_CD. */
    String CTRY_CD = "CTRY_CD";
    // START 2019/10/02 K.Fujimoto [QC#53864, ADD]
    String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    // END   2019/10/02 K.Fujimoto [QC#53864, ADD]
    // START 2017/09/04 M.Kidokoro [QC#20862,ADD]
    /** SHIP_TO_UPD_CUST_CD. */
    String SHIP_TO_UPD_CUST_CD = "SHIP_TO_UPD_CUST_CD";
    // END 2017/09/04 M.Kidokoro [QC#20862,ADD]
    /** DS_ACCT_NM. */
    String DS_ACCT_NM = "DS_ACCT_NM";
    /** DS_KEY_ACCT_FLG. */
    String DS_KEY_ACCT_FLG = "DS_KEY_ACCT_FLG";
    /** PHONE_FIX. */
    String PHONE_FIX = "PHONE_FIX";
    /** SVC_BY_LINE_BIZ_TP_CD. */
    String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";
    /** IWR_COND_FLG. */
    String IWR_COND_FLG = "IWR_COND_FLG";
    /** SVC_SKILL_NUM. */
    String SVC_SKILL_NUM = "SVC_SKILL_NUM";
    /** SVC_SKILL_NM */
    String SVC_SKILL_NM = "SVC_SKILL_NM";
    /** PRF_TECH_CD */
    String PRF_TECH_CD = "PRF_TECH_CD";
    /** PRF_TECH_NM */
    String PRF_TECH_NM = "PRF_TECH_NM";
    /** REQ_TECH_CD */
    String REQ_TECH_CD = "REQ_TECH_CD";
    /** REQ_TECH_NM */
    String REQ_TECH_NM = "REQ_TECH_NM";
    /** NON_PRF_TECH_NM. */
    String NON_PRF_TECH_NM = "NON_PRF_TECH_NM";
    /** NON_PRF_TECH_CD */
    String NON_PRF_TECH_CD = "NON_PRF_TECH_CD";
    /** SVC_ACCS_PMIT_NUM. */
    String SVC_ACCS_PMIT_NUM = "SVC_ACCS_PMIT_NUM";
    /** SVC_ACCS_PMIT_DESC_TXT. */
    String SVC_ACCS_PMIT_DESC_TXT = "SVC_ACCS_PMIT_DESC_TXT";
    /** SVC_MOD_PLN_ID. */
    String SVC_MOD_PLN_ID = "SVC_MOD_PLN_ID";
    /** MTR_LB_CD. */
    String MTR_LB_CD = "MTR_LB_CD";
    /** READ_MTR_CNT. */
    String READ_MTR_CNT = "READ_MTR_CNT";
    /** MTR_READ_DT. */
    String MTR_READ_DT = "MTR_READ_DT";
    // START 2020/12/22 [QC#58154, ADD]
    String SVC_TASK_RCV_DT = "SVC_TASK_RCV_DT";
    // End   2020/12/22 [QC#58154, ADD]
    /** SVC_TASK_STS_CD. */
    String SVC_TASK_STS_CD = "SVC_TASK_STS_CD";
    /** FSR_VISIT_STS_CD */
    String FSR_VISIT_STS_CD = "FSR_VISIT_STS_CD";
    /** MDSE_CD. */
    String MDSE_CD = "MDSE_CD";
    /** FSR_EVENT_PK. */
    String FSR_EVENT_PK = "FSR_EVENT_PK";
    /** SVC_CMNT_TXT. */
    String SVC_CMNT_TXT = "SVC_CMNT_TXT";
    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    /** ONS_SPRT_CALL_FLG. */
    String ONS_SPRT_CALL_FLG = "ONS_SPRT_CALL_FLG";
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]
    // START 2019/10/21 K.Fujimoto [QC#53441, MOD]
    /** MAX_COPY_PER_DAY. */
    String MAX_COPY_PER_DAY = "MAX_COPY_PER_DAY";
    /** EXPECTED_MAX_COPIES */
    String EXPECTED_MAX_COPIES = "EXPECTED_MAX_COPIES";
    /** NSZC1070_MODS_SEND */
    String NSZC1070_MODS_SEND = "NSZC1070_MODS_SEND";
    /** NSZC1070_MTR_SEND */
    String NSZC1070_MTR_SEND = "NSZC1070_MTR_SEND";
    /** NSZC1070_INSTL_CHK_LIST_SEND */
    String NSZC1070_INSTL_CHK_LIST_SEND = "NSZC1070_INSTL_CHK_LIST_SEND";
    // END   2019/10/21 K.Fujimoto [QC#53441, MOD]
    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    /** NSZC1070_L2_MODS_SEND */
    String NSZC1070_L2_MODS_SEND = "NSZC1070_L2_MODS_SEND";
    /** NSZC1070_L2_MTR_SEND */
    String NSZC1070_L2_MTR_SEND = "NSZC1070_L2_MTR_SEND";
    /** NSZC1070_L2_ISTL_CHK_LST_SEND */
    String NSZC1070_L2_ISTL_CHK_LST_SEND = "NSZC1070_L2_ISTL_CHK_LST_SEND";
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]
    // START 2022/09/02 K.Kitachi [QC#60536, ADD]
    /** NSZC1070_OFS_SEND */
    String NSZC1070_OFS_SEND = "NSZC1070_OFS_SEND";
    // END 2022/09/02 K.Kitachi [QC#60536, ADD]
    /** MAN_LBOR_ALLW. */
    String MAN_LBOR_ALLW = "MAN_LBOR_ALLW";
    /** SVC_ASG_TP_CD */
    String SVC_ASG_TP_CD = "SVC_ASG_TP_CD";
    /** SVC_ASG_TECH_CD */
    String SVC_ASG_TECH_CD = "SVC_ASG_TECH_CD";
    /** SVC_CUST_ATTN_TXT */
    String SVC_CUST_ATTN_TXT = "SVC_CUST_ATTN_TXT";
    /** SVC_CUST_CLLR_TXT */
    String SVC_CUST_CLLR_TXT = "SVC_CUST_CLLR_TXT";
    /** CUST_TEL_EXTN_NUM */
    String CUST_TEL_EXTN_NUM = "CUST_TEL_EXTN_NUM";
    /** MTR_LB_DESC_TXT */
    String MTR_LB_DESC_TXT = "MTR_LB_DESC_TXT";
    // add start 2016/09/12 CSA Defect#14357
    /** MTR_LB_NM */
    String MTR_LB_NM = "MTR_LB_NM";
    // add end 2016/09/12 CSA Defect#14357
    /** TOT_MTR_FLG */
    String TOT_MTR_FLG = "TOT_MTR_FLG";
    /** MAX_COPY_PER_DAY_TOT_CNT */
    String MAX_COPY_PER_DAY_TOT_CNT = "MAX_COPY_PER_DAY_TOT_CNT";
    /** EST_SVC_LBOR_HOUR_MN */
    String EST_SVC_LBOR_HOUR_MN = "EST_SVC_LBOR_HOUR_MN";
    /** EZUPTIME */
    String EZUPTIME = "EZUPTIME";
    /** SVC_ISTL_REQ_FLG */
    String SVC_ISTL_REQ_FLG = "SVC_ISTL_REQ_FLG";
    // START 2018/01/31 K.Kojima [QC#21112,ADD]
    /** SVC_DEINS_REQ_FLG */
    String SVC_DEINS_REQ_FLG = "SVC_DEINS_REQ_FLG";
    // END 2018/01/31 K.Kojima [QC#21112,ADD]
    /** SVC_CONFIG_MSTR_PK */
    String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** MDSE_DESC_SHORT_TXT */
    String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    // START 2018/02/19 M.Naito [QC#21112, ADD]
    /** SVC_MACH_MSTR_STS_CD */
    String SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";
    // END 2018/02/19 M.Naito [QC#21112, ADD]
    // Add Start 2018/03/22 QC#18713
    /** CELL_PHO_NUM */
    String CELL_PHO_NUM = "CELL_PHO_NUM";
    // Add End 2018/03/22 QC#18713
    // START 2018/06/19 M.Naito [QC#26763, ADD]
    /** GLBL_CMPY_CD */
    String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    // END 2018/06/19 M.Naito [QC#26763, ADD]
    // START 2019/10/24 K.Fujimoto [QC#53870,ADD]
    /** GLBL_CMPY_CD */
    String SVC_MOD_FROM_SER_NUM = "SVC_MOD_FROM_SER_NUM";
    /** GLBL_CMPY_CD */
    String SVC_MOD_TO_SER_NUM = "SVC_MOD_TO_SER_NUM";
    // END   2019/10/24 K.Fujimoto [QC#53870,ADD]

// START 2016/11/02 N.Arai [QC#15654, MOD]
    /** SVC_CALL_SRC_TP_DESC_TXT */
    String SVC_CALL_SRC_TP_DESC_TXT = "SVC_CALL_SRC_TP_DESC_TXT";
    /** SVC_MEMO_TP_DESC_TXT */
    String SVC_MEMO_TP_DESC_TXT = "SVC_MEMO_TP_DESC_TXT";
    /** LAST_UPD_TS */
    String LAST_UPD_TS = "LAST_UPD_TS";
    /** LAST_UPD_USR_ID */
    String LAST_UPD_USR_ID = "LAST_UPD_USR_ID";

    /** SubmittedBy: SYSTEM */
    String SUBMITTED_BY_SYSTEM = "SYSTEM";
// END 2016/11/02 N.Arai [QC#15654, MOD]

    /** EXPECTED_MAX_COPY_PER_DAY */
    String EXPECTED_MAX_COPY_PER_DAY = "EXPECTED_MAX_COPY_PER_DAY";

    /** CHECK_MAX */
    String CHECK_MAX = "CHECK_MAX";
    /** CHECK_MAX:All */
    String CHECK_MAX_ALL = "ALL";
    /** CHECK_MAX:Total */
    String CHECK_MAX_TOTAL = "TOTAL";

    /** Length 8 */
    int LEN_8 = 8;

    // START 2023/03/02 K.Watanabe [QC#60926, ADD]
    /** MTR_READ_REQ_FLG */
    String MTR_READ_REQ_FLG = "MTR_READ_REQ_FLG";
    // END 2023/03/02 K.Watanabe [QC#60926, ADD]

    // START 2019/10/02 K.Fujimoto [QC#53864, ADD]
    public static final int NUM_0 = 0;
    public static final int LEN_5 = 5;
    // END 2019/10/02 K.Fujimoto [QC#53864, ADD]
    /** DEF_TM */
    String DEF_TS = "000000000";

    /** - */
    String HYPHEN = "-";

    /** Action:UpdateOrCreate */
    String ACTION = "UpdateOrCreate";

// START 2016/11/07 N.Arai [QC#15784, MOD]
    /** @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** It failed to update the @ table. */
    public static final String NSAM0031E = "NSAM0031E";
// END 2016/11/07 N.Arai [QC#15784, MOD]

// mod start 2016/11/15 CSA QC#15891
    /** NON_PRF_DISTRICT */
    public static final String NON_PRF_DISTRICT = "NON_PRF_DISTRICT";

    /** PRF_DISTRICT */
    public static final String PRF_DISTRICT = "PRF_DISTRICT";

    /** REQ_DISTRICT */
    public static final String REQ_DISTRICT = "REQ_DISTRICT";

    /** SVC_ASG_DISTRICT */
    public static final String SVC_ASG_DISTRICT = "SVC_ASG_DISTRICT";
// mod end 2016/11/15 CSA QC#15891

 // START 2016/11/17 N.Arai [QC#16020, MOD]
    /** num const : NSZC1070_MAX_DAILY_COPY */
    public static final String NSZC1070_MAX_DAILY_COPY = "NSZC1070_MAX_DAILY_COPY";
 // END 2016/11/17 N.Arai [QC#16020, MOD]

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1020";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]

    // add start 2019/04/02 QC#31027
    /** MAX_DATE */
    public static final String MAX_DATE = "99991231";
    // add end 2019/04/02 QC#31027

    // START 2019/10/29 K.Fujimoto [QC#53441, ADD]
    /** Format Time stamp **/
    public static final String FORMAT_TS = "yyyyMMddHHmmssSSS";
    /** Failed to insert a FSR Event record. */
    public static final String NSZM0083E = "NSZM0083E";
    // END 2019/10/29 K.Fujimoto [QC#53441, ADD]

    // add start 2020/04/08 QC#56328
    /** SVC_MOD_PK */
    public static final String SVC_MOD_PK = "SVC_MOD_PK";

    /** SVC_MOD_DTL_PK */
    public static final String SVC_MOD_DTL_PK = "SVC_MOD_DTL_PK";
    // add end 2020/04/08 QC#56328
    
    // add start 2020/06/18 QC#57175
    /** Num const : NSXC0030_MTR_LOW_THRHD_FCT_NUM */
    public static final String NSXC0030_MTR_LOW_THRHD_FCT_NUM = "NSXC0030_MTR_LOW_THRHD_FCT_NUM";
    /** num const : NSZC1070_MAX_TEST_COPY */
    public static final String NSZC1070_MAX_TEST_COPY = "NSZC1070_MAX_TEST_COPY";
    /** num const : NSZC1070_ADCV_HIGH */
    public static final String NSZC1070_ADCV_HIGH = "NSZC1070_ADCV_HIGH";
    /** num const : NSZC1070_ADCV_LOW */
    public static final String NSZC1070_ADCV_LOW = "NSZC1070_ADCV_LOW";
    /** MAX_COPY_TEST_CNT. */
    public static final String MAX_COPY_TEST_CNT = "MAX_COPY_TEST_CNT";
    /** ADCV_HIGH. */
    public static final String ADCV_HIGH = "ADCV_HIGH";
    /** ADCV_LOW. */
    public static final String ADCV_LOW = "ADCV_LOW";
    // add end 2020/06/18 QC#57175
    // START 2022/05/23 [QC#60058, ADD]
    /** PIPE : | */
    public static final String PIPE = "\\|";
    // END   2022/05/23 [QC#60058, ADD]
}
