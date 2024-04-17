/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC003001.constant;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/12   SRA             E.Inada         Create          N/A
 * 08/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 12/21/2015   Hitachi         J.Kim           Update          NA#1112
 * 09/08/2017   Hitachi         T.Tomita        Update          QC#19242
 * 01/15/2018   Hitachi         U.Kim           Update          QC#19702
 * 2018/09/06   Hitachi         K.Kojima        Update          QC#28064
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#30165
 * 2021/10/06   CITS            R.Jabal         Update          QC#59183
 *</pre>
 */
public interface NSZC003001Constant {
    // -------------- Mode ---------------
    /** Mode: 01 : Schedule */
    String MODE_SCHEDULE = "01";

    /** Mode: 02 : Dispatch */
    String MODE_DISPATCH = "02";

    /** Mode: 03 : Cancel */
    String MODE_CANCEL = "03";

    /** Mode: 04 : Accept */
    String MODE_ACCEPT = "04";

    /** Mode: 05 : Post Dispatch */
    String MODE_POST_DISPT = "05";

    /** Mode: 06 : CreateCall */
    String MODE_CREATE_CALL = "06";

    /** Mode: 07 : UpdateCall */
    String MODE_UPDATE_CALL = "07";

    /** Mode: 08 : CancelCall */
    String MODE_CANCEL_CALL = "08";

    // add start 2015/12/21 CSA Defect#1112
    /** Mode: 1 ALL CANCEL */
    String MODE_ALL_CANCEL = "1";

    /** Mode: 2 ALL CLOSE */
    String MODE_ALL_CLOSE = "2";

    /** Mode: 3 NORMAL */
    String MODE_ALL_NORMAL = "3";

    /** Mode 2: PARTIAL CANCEL */
    String PARTIAL_CANCEL = "2";

    /** Debrief Mode */
    String MODE_DEBRIEF = "01";
    // add end 2015/12/21 CSA Defect#1112
    // -----------------------------
    /** Auto Seq Code : "FSR_NUM" */
    String FSR_NUM_SEQ_CD = "FSR_NUM";

    /** Format System Time stamp **/
    String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /** Format System Date Time */
    String FORMAT_SYS_DT_TM = "yyyyMMddHHmmss";

    /** format */
    String FORMAT_RSP_TM = "%d:%02d";

    /** 60 min */
    int HOUR_MIN = 60;

    /** Date Time length */
    int LEN_DT_TM = 14;

    /** Date length */
    int LEN_DT = 8;

    /** FSR Visit Number */
    String FSR_VISIT_NUM_01 = "01";

    /** Sequence */
    String FSR_EVENT_SQ = "FSR_EVENT_SQ";

    /** Time Format */
    String TM_FORMAT = "^([0-1][0-9]|2[0-3])([0-5][0-9])$";

//NA#ASCC CLICK ADD Start
    /** TS_POSTFIX : 000 */
    String TS_POSTFIX = "000";
//NA#ASCC CLICK ADD End

    // -------------- Mail ----------------
    /** Template ID */
    String MAIL_TEMPLATE_ID_SCHEDULE = "NSZC0030M001";

    /** Template ID */
    String MAIL_TEMPLATE_ID_CANCEL = "NSZC0030M002";

    /** Mail Group ID */
    String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group Key */
    String MAIL_KEY_FROM = "NSZ";

    /** Mail Mapping : svcTaskRcvDt */
    String ML_RCV_DT = "svcTaskRcvDt";

    /** Mail Mapping : schdFromDt */
    String ML_FROM_DT = "schdFromDt";

    /** Mail Mapping : schdToDt */
    String ML_TO_DT = "schdToDt";

    /** Mail Mapping : shipToAddr */
    String ML_SHIP_TO_ADDR = "shipToAddr";

    /** Mail Mapping : dsSvcCallTpNm */
    String ML_CALL_TP_NM = "dsSvcCallTpNm";

    /** Mail Mapping : machDownFlg */
    String ML_MACH_DOWN_FLG = "machDownFlg";

    /** Mail Mapping : svcRspGrpCd */
    String ML_SVC_RSP_GRP_CD = "svcRspGrpCd";

    /** Mail Mapping : svcTaskNum */
    String ML_TASK_NUM = "svcTaskNum";

    /** Mail Mapping : fsrNum */
    String ML_FSR_NUM = "fsrNum";

    /** Column Name : SVC_TASK_NUM */
    String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** Column Name : SVC_TASK_STS_CD */
    String SVC_TASK_STS_CD = "SVC_TASK_STS_CD";

    /** Column Name : FSR_NUM */
    String FSR_NUM = "FSR_NUM";

    /** Column Name : FSR_VISIT_NUM */
    String FSR_VISIT_NUM = "FSR_VISIT_NUM";

    /** Column Name : SVC_CR_HLD_FLG */
    String SVC_CR_HLD_FLG = "SVC_CR_HLD_FLG";

    /** Column Name : SO_NUM */
    String SO_NUM = "SO_NUM";

    /** Column Name : ORG_LAYER_NUM */
    String ORG_LAYER_NUM = "ORG_LAYER_NUM";

    /** Column Name : ORG_CD */
    String ORG_CD = "ORG_CD";

    /** Column Name : MDL_NM */
    String MDL_NM = "MDL_NM";

    /** Column Name : TECH_CD */
    String TECH_CD = "TECH_CD";

    /** Column Name : ORIG_INV_CCY_CD */
    String ORIG_INV_CCY_CD = "ORIG_INV_CCY_CD";

    /** Column Name : INV_CCY_CD */
    String INV_CCY_CD = "INV_CCY_CD";

    /** Column Name : SVC_MACH_MSTR_PK */
    String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Column Name : SHIP_TO_CUST_CD */
    String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column Name : SER_NUM */
    String SER_NUM = "SER_NUM";

    /** Column Name : CCY_EXCH_RATE */
    String CCY_EXCH_RATE = "CCY_EXCH_RATE";

    /** Column Name : FSR_VISIT_STS_CD */
    String FSR_VISIT_STS_CD = "FSR_VISIT_STS_CD";

    /** Column Name : SVC_TASK_RCV_DT */
    String SVC_TASK_RCV_DT = "SVC_TASK_RCV_DT";

    /** Column Name : SVC_TASK_RCV_TM */
    String SVC_TASK_RCV_TM = "SVC_TASK_RCV_TM";

    /** Column Name : DS_SVC_CALL_TP_CD */
    String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";

    /** Column Name : MACH_DOWN_FLG */
    String MACH_DOWN_FLG = "MACH_DOWN_FLG";

    /** Column Name : SVC_RSP_TM_MN_AOT */
    String SVC_RSP_TM_MN_AOT = "SVC_RSP_TM_MN_AOT";

    /** Column Name : CUST_TEL_NUM */
    String CUST_TEL_NUM = "CUST_TEL_NUM";

    /** Column Name : CUST_TEL_EXTN_NUM */
    String CUST_TEL_EXTN_NUM = "CUST_TEL_EXTN_NUM";

    /** Column Name : TECH_SCHD_FROM_DT */
    String TECH_SCHD_FROM_DT = "TECH_SCHD_FROM_DT";

    /** Column Name : TECH_SCHD_FROM_TM */
    String TECH_SCHD_FROM_TM = "TECH_SCHD_FROM_TM";

    /** Column Name : TECH_SCHD_TO_DT */
    String TECH_SCHD_TO_DT = "TECH_SCHD_TO_DT";

    /** Column Name : TECH_SCHD_TO_TM */
    String TECH_SCHD_TO_TM = "TECH_SCHD_TO_TM";

    /** Column Name : LOC_NUM */
    String LOC_NUM = "LOC_NUM";

    /** Column Name : FIRST_LINE_ADDR */
    String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** Column Name : SCD_LINE_ADDR */
    String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** Column Name : THIRD_LINE_ADDR */
    String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** Column Name : FRTH_LINE_ADDR */
    String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** Column Name : CTY_ADDR */
    String CTY_ADDR = "CTY_ADDR";

    /** Column Name : CNTY_NM */
    String CNTY_NM = "CNTY_NM";

    /** Column Name : ST_CD */
    String ST_CD = "ST_CD";

    /** Column Name : POST_CD */
    String POST_CD = "POST_CD";

    /** Column Name : DS_SVC_CALL_TP_NM */
    String DS_SVC_CALL_TP_NM = "DS_SVC_CALL_TP_NM";

    /** Column Name : EML_ADDR */
    String EML_ADDR = "EML_ADDR";

    /** Column Name : PMT_TERM_CASH_DISC_CD */
    String PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** Massage String. */
    String CRLF = System.getProperty("line.separator");

    /** String : " " */
    String STR_SP = " ";

    /** String : "," */
    String STR_COMMA = ",";

    /** String : "(" */
    String STR_START = "(";

    /** String : ")" */
    String STR_END = ")";

    /** format */
    String FORMAT_DT_FROM = "^(....)(..)(..)$";

    /** format */
    String FORMAT_DT_TO = "$1/$2/$3";

    /** format */
    String FORMAT_TM_FROM = "^(..)(..).+$";

    /** format */
    String FORMAT_TM_TO = "$1:$2";

//NA#ASCC CLICK Bugfix ADD Start
    // -------------- Avoidance Mode ---------------
    /** Mode: 01 : Call Resolve */
    String AD_MODE_CALL_RESOLVE = "01";

    /** Mode: 02 : Dispatch Tech */
    String AD_MODE_DISPATCH_TECH = "02";

    /** Mode: 03 : Need More Time */
    String AD_MODE_NEED_MORE_TIME = "03";
//NA#ASCC CLICK Bugfix ADD End

    // -------------- Error Code ----------------
    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";

    /** Input parameter "Process Mode" is a mandatory field. */
    String NSZM0003E = "NSZM0003E";

    /** Time is invalid. Please use a valid format, [@] */
    String NSBM0004E = "NSBM0004E";

    /** Input parameter "Service Machine Master ID" is a mandatory field. */
    String NSZM0011E = "NSZM0011E";

    /** Input parameter "Serial Number" is a mandatory field. */
    String NSZM0012E = "NSZM0012E";

    /** Input parameter "Bill To Code" is a mandatory field. */
    String NSZM0015E = "NSZM0015E";

    /** Input parameter "Ship To Code" is a mandatory field. */
    String NSZM0017E = "NSZM0017E";

    /** Input parameter "DS Service Call Type Code" is a mandatory field. */
    String NSZM0049E = "NSZM0049E";

    /** Input parameter "Technician Code" is a mandatory field. */
    String NSZM0052E = "NSZM0052E";

    /** The entered 'Technician Code' does not exist. */
    String NSZM0076E = "NSZM0076E";

    /** Failed to search a Service Task record. */
    String NSZM0079E = "NSZM0079E";

    /** The entered 'Ship To Customer Code' does not exist. */
    String NSZM0084E = "NSZM0084E";

    /**
     * The process cannot be executed because the "Task Status" is
     * incorrect.
     */
    String NSZM0146E = "NSZM0146E";

    /** The Priority format is incorrect. */
    String NSZM0147E = "NSZM0147E";

    /**
     * The process cannot be executed because the "Machine Master PK"
     * is not match.
     */
    String NSZM0148E = "NSZM0148E";

    /**
     * The process cannot be executed because the "Model Name" is not
     * match.
     */
    String NSZM0149E = "NSZM0149E";

    /**
     * The process cannot be executed because the "Technician Code" is
     * not match.
     */
    String NSZM0150E = "NSZM0150E";

    /** The process cannot be executed because the Task is on Hold. */
    String NSZM0151E = "NSZM0151E";

    /**
     * The process cannot be executed because the
     * "Local Currency Code" is not match.
     */
    String NSZM0152E = "NSZM0152E";

    /**
     * The process cannot be executed because the "Currency Code" is
     * not match.
     */
    String NSZM0153E = "NSZM0153E";

    /**
     * The process cannot be executed because the "FSR Status" is
     * incrrect.
     */
    String NSZM0154E = "NSZM0154E";

    /** The process cannot be executed because the "SO#" is not match. */
    String NSZM0155E = "NSZM0155E";

    /** Input parameter "Task#" is a mandatory field. */
    String NSZM0156E = "NSZM0156E";

    /**
     * Input parameter "Schedule Dispatch Email Flag" is a mandatory
     * field.
     */
    String NSZM0157E = "NSZM0157E";

    /** Input parameter "Schedule From Date" is a mandatory field. */
    String NSZM0158E = "NSZM0158E";

    /** Input parameter "Schedule From Time" is a mandatory field. */
    String NSZM0159E = "NSZM0159E";

    /** Input parameter "Schedule To Date" is a mandatory field. */
    String NSZM0160E = "NSZM0160E";

    /** Input parameter "Schedule To Time" is a mandatory field. */
    String NSZM0161E = "NSZM0161E";

    /** Input parameter "Schedule Timezone" is a mandatory field. */
    String NSZM0162E = "NSZM0162E";

    /** Input parameter "Execute User Id" is a mandatory field. */
    String NSZM0163E = "NSZM0163E";

    /** Input parameter "xxSvcTaskList" is a mandatory array. */
    String NSZM0164E = "NSZM0164E";

    /**
     * The process cannot be executed because the "Schedule Date/Time"
     * is not match.
     */
    String NSZM0165E = "NSZM0165E";

    /**
     * The process cannot be executed because the "Ship to" is not
     * match.
     */
    String NSZM0166E = "NSZM0166E";

    /** It failed to update the SVC_TASK. */
    String NSZM0167E = "NSZM0167E";

    /** It failed to update the FSR. */
    String NSZM0168E = "NSZM0168E";

    /** It failed to update the FSR_VISIT. */
    String NSZM0169E = "NSZM0169E";

    /** It failed to update the FSR_EVEINT. */
    String NSZM0170E = "NSZM0170E";

    /** It failed to insert the FSR. */
    String NSZM0171E = "NSZM0171E";

    /** It failed to insert the FSR_VISIT. */
    String NSZM0172E = "NSZM0172E";

    /** It failed to insert the FSR_EVEINT. */
    String NSZM0173E = "NSZM0173E";

    /** Input parameter "FSR#" is a mandatory field. */
    String NSZM0180E = "NSZM0180E";

    /** Failed to search a FSR record. */
    String NSZM0181E = "NSZM0181E";

    /** Failed to search a FSR Visit record. */
    String NSZM0182E = "NSZM0182E";

    /** Could not get FROM mail-address. */
    String NSZM0184E = "NSZM0184E";

    /** Could not get Mailtemplate. */
    String NSZM0185E = "NSZM0185E";

    /** Could not get Technician mail-address. */
    String NSZM0186E = "NSZM0186E";

    /** Input parameter "Service Problem Type Code" is a mandatory field. */
    String NSZM0243E = "NSZM0243E";

    /**
     * The process cannot be executed because the
     * "Payment Term Cash Discount Code" is not match.
     */
    String NSZM0352E = "NSZM0352E";

    /** Input parameter "Layer" is a mandatory field. */
    String NSZM0384E = "NSZM0384E";

    /** Input parameter "Org Code" is a mandatory field. */
    String NSZM0385E = "NSZM0385E";

    /**
     * The process cannot be executed because the "Layer" is not
     * match.
     */
    String NSZM0386E = "NSZM0386E";

    /**
     * The process cannot be executed because the "Org Code" is not
     * match.
     */
    String NSZM0387E = "NSZM0387E";

    /** Input parameter "Service Call Source Type Code" is a mandatory field. */
    String NSZM0544E = "NSZM0544E";

    /** Input parameter "Service Call Avoidance Code" is a mandatory field. */
    String NSZM0545E = "NSZM0545E";

    /** Input parameter "Service Call Request Owner TOC Code" is a mandatory field. */
    String NSZM0546E = "NSZM0546E";

    /** The entered 'Service Memo Type Code' does not exist. */
    String NSZM0547E = "NSZM0547E";

    /** The entered 'Service Memo Control Code' does not exist. */
    String NSZM0548E = "NSZM0548E";

    /** The entered 'DS Service Call Type To Code' does not exist. */
    String NSZM0549E = "NSZM0549E";

    /** The entered 'Service Problem Type Code' does not exist. */
    String NSZM0550E = "NSZM0550E";

    /** The entered 'Service Call Avoidance Code' does not exist. */
    String NSZM0551E = "NSZM0551E";

    /** The entered 'Service Call Source Type Code' does not exist. */
    String NSZM0552E = "NSZM0552E";

    /** The entered 'Service Call Category Code' does not exist. */
    String NSZM0553E = "NSZM0553E";

    /** The entered 'Svc Call Request Owner TOC Code' does not exist. */
    String NSZM0554E = "NSZM0554E";

    /** The entered 'State Code' does not exist. */
    String NSZM0555E = "NSZM0555E";

    /** The entered 'Postal Code' does not exist. */
    String NSZM0556E = "NSZM0556E";

    /** The entered 'Country Code' does not exist. */
    String NSZM0557E = "NSZM0557E";

    /** The entered 'Bill To Customer Code' does not exist. */
    String NSZM0558E = "NSZM0558E";

    /** The entered 'Payment Term Cash Discount Code' does not exist. */
    String NSZM0559E = "NSZM0559E";

    /** The entered 'Ship To Customer Code' does not exist. */
    String NSZM0560E = "NSZM0560E";

    /** The entered 'Update Bill To Customer Code' does not exist. */
    String NSZM0593E = "NSZM0593E";

    /** The entered 'Update Ship To Customer Code' does not exist. */
    String NSZM0594E = "NSZM0594E";

    /** The entered 'FSR Type' does not exist. */
    String NSZM0597E = "NSZM0597E";

    /** The entered Resource as 'Technician' does not exist. */
    String NSZM0602E = "NSZM0602E";

    /** The entered Resource as 'Technician' is not active. */
    String NSZM0603E = "NSZM0603E";

    /** The entered 'Assign Type' does not exist. */
    String NSZM0606E = "NSZM0606E";

    /** Failed to search Task Status. */
    String NSZM0728E = "NSZM0728E";

    /** ZZBM0083E */
    String ZZBM0083E = "ZZBM0083E";

    // add start 2015/12/24 CSA Defect#1112
    /** Unable to proceed due to existing status tasks can not be cancelled. */
    String NSZM0814E = "NSZM0814E";

    /** Unable to cancel some of the tasks in current mode. Need to call "Partial Cancel" mode. */
    String NSZM0815E = "NSZM0815E";

    /** Unable to proceed Full Task cancellantion in this current mode. Need to call "All Cancel" mode. */
    String NSZM0816E = "NSZM0816E";
    // add end 2015/12/24 CSA Defect#1112

    // START 2018/09/06 K.Kojima [QC#28064,ADD]
    /** Failed to search a Bill To Customer record. */
    String NSZM1348E = "NSZM1348E";

    /** Failed to search a Direct Sales Account Credit Profile record. */
    String NSZM1349E = "NSZM1349E";

    // END 2018/09/06 K.Kojima [QC#28064,ADD]

    // Add Start 2017/09/08 QC#19242
    /** VAR_CHAR_CONST_NM: OUT_TRTY_SVC_BR_CD */
    String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";
    // Add End 2017/09/08 QC#19242
    // START 2018/01/15 U.Kim [QC#19702, ADD]
    /** Waiting second for find by key*/
    String WAIT_SEC_UPD_TASK_OTHER = "WAIT_SEC_UPD_TASK_OTHER";
    // END 2018/01/15 U.Kim [QC#19702, ADD]

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** VAR_CHAR_CONST_NM: CLICK_SEND_EXCL_CALL_TP */
    String CLICK_SEND_EXCL_CALL_TP = "CLICK_SEND_EXCL_CALL_TP";
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    // START 2021/10/06 R.Jabal [QC#59183, ADD]
    /** SVC_ACCS_PMIT_NUM. */
    String SVC_ACCS_PMIT_NUM = "SVC_ACCS_PMIT_NUM";
    
    /** SVC_ACCS_PMIT_DESC_TXT. */
    String SVC_ACCS_PMIT_DESC_TXT = "SVC_ACCS_PMIT_DESC_TXT";
    // END 2021/10/06 R.Jabal [QC#59183, ADD]
}
