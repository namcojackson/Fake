/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC049001.constant;

/**
 * <pre>
 * Register to UGW API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Hitachi         K.Kasai         Create          N/A
 * 2016/10/17   Hitachi         Y.Zhang         Update          QC#15162
 * 2016/10/26   Hitachi         Y.Zhang         Update          QC#15437
 * 2016/11/04   Hitachi         T.Mizuki        Update          QC#15437
 * 2016/11/24   Hitachi         N.Arai          Update          QC#15829
 *</pre>
 */
public class NSZC049001Constant {

    /**
     * Const Value:UGW_CSV_DBL_QUOT_FLG
     */
    public static final String CONST_UGW_CSV_DBL_QUOT_FLG = "UGW_CSV_DBL_QUOT_FLG";

    /**
     * Const Value:IWR_ISTL_SVC_CALL_TP
     */
    public static final String CONST_IWR_ISTL_SVC_CALL_TP = "IWR_ISTL_SVC_CALL_TP";

    /**
     * Const Value:IWR_ISTL_SVC_PBLM_TP
     */
    public static final String CONST_IWR_ISTL_SVC_PBLM_TP = "IWR_ISTL_SVC_PBLM_TP";

    /**
     * Const Value:IWR_SVC_CALL_BILL_TP
     */
    public static final String CONST_IWR_SVC_CALL_BILL_TP = "IWR_SVC_CALL_BILL_TP";

    /**
     * Const Value:IWR_CUST_CLLR_TXT
     */
    public static final String CONST_IWR_CUST_CLLR_TXT = "IWR_CUST_CLLR_TXT";

    /**
     * Const Value:NSZC0490_NORMAL_MSG
     */
    public static final String CONST_NORMAL_MSG = "NSZC0490_NORMAL_MSG";

    /**
     * Const Value:NSZC0490_ERR_MSG_DUP
     */
    public static final String CONST_ERR_MSG_DUP = "NSZC0490_ERR_MSG_DUP";

    /**
     * Const Value:NSZC0490_ERR_MSG_NOT_EXIST
     */
    public static final String CONST_ERR_MSG_NOT_EXIST = "NSZC0490_ERR_MSG_NOT_EXIST";

    /**
     * Const Value:NSZC0490_ERR_MSG_CHECK_CONT
     */
    public static final String CONST_ERR_MSG_CHECK_CONT = "NSZC0490_ERR_MSG_CHECK_CONT";

    /**
     * Empty field
     */
    public static final String EMP = "";

   // START 2016/10/17 Y.Zhang [QC#15162, MOD]
    /**
     * UGW_LINE_FEED_CD
     */
    public static final String CONST_UGW_LINE_FEED_CD = "UGW_LINE_FEED_CD";

    /**
     * LF
     */
    public static final String LF_CODE = "\n";
    /**
     * CRLF
     */
    public static final String CRLF = "CRLF";
    /**
     * LF
     */
    public static final String LF = "LF";

    /**
     * CR+LF
     */
    public static final String CRLF_CODE = "\r\n";
  // END 2016/10/17 Y.Zhang [QC#15162, MOD]

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Format Date Time */
    public static final String FORMAT_SYS_DT_TM = "yyyyMMddHHmmss";
    // mod start 2016/11/07 CSA QC#15437
    /** Date Format */
    public static final String DATE_FORMAT_ddMMyyyy = "ddMMyyyy";
    // mod end 2016/11/07 CSA QC#15437
    /**
     * DS_COND_CONST_GRP_ID
     */
    public static final String DS_COND_CONST_GRP_ID = "NSZC0490_CUST_INFO";

    /**
     * DS_COND_CONST_CD:CUST_TEL_NUM
     */
    public static final String CONST_CUST_TEL_NUM = "CUST_TEL_NUM";

    /**
     * DS_COND_CONST_CD:SVC_CUST_ATTN_TXT
     */
    public static final String CONST_SVC_CUST_ATTN_TXT = "SVC_CUST_ATTN_TXT";

    /**
     * DS_COND_CONST_CD:CUST_EML_ADDR
     */
    public static final String CONST_CUST_EML_ADDR = "CUST_EML_ADDR";

    /**
     *Column Value:VRSN_REC_NM
     */
    public static final String COLUMN_VRSN_REC_NM = "VRSN_REC_NM";

    /**
     *Column Value:VRSN_NUM
     */
    public static final String COLUMN_VRSN_NUM = "VRSN_NUM";

    /**
     *Column Value:CUST_NM
     */
    public static final String COLUMN_CUST_NM = "CUST_NM";

    /**
     *Column Value:ORG_ID
     */
    public static final String COLUMN_ORG_ID = "ORG_ID";

    /**
     *Column Value:CUST_CTRY_NM
     */
    public static final String COLUMN_CUST_CTRY_NM = "CUST_CTRY_NM";

    /**
     *Column Value:CUST2_REC_NM
     */
    public static final String COLUMN_CUST2_REC_NM = "CUST2_REC_NM";

    /**
     *Column Value:CUST2_PRD_SET
     */
    public static final String COLUMN_CUST2_PRD_SET = "CUST2_PRD_SET";

    /**
     *Column Value:CUST2_FIRM_DIST
     */
    public static final String COLUMN_CUST2_FIRM_DIST = "CUST2_FIRM_DIST";

    /**
     *Column Value:ADMIN_REC_NM
     */
    public static final String COLUMN_ADMIN_REC_NM = "ADMIN_REC_NM";

    /**
     *Column Value:ADMIN_NM
     */
    public static final String COLUMN_ADMIN_NM = "ADMIN_NM";

    /**
     *Column Value:ADMIN_EML_ADDL
     */
    public static final String COLUMN_ADMIN_EML_ADDL = "ADMIN_EML_ADDL";

    /**
     *Column Value:CONTR_REC_NM
     */
    public static final String COLUMN_CONTR_REC_NM = "CONTR_REC_NM";

    /**
     *Column Value:CONTR_SVC_OPT
     */
    public static final String COLUMN_CONTR_SVC_OPT = "CONTR_SVC_OPT";

    /**
     *Column Value:CONTR2_REC_NM
     */
    public static final String COLUMN_CONTR2_REC_NM = "CONTR2_REC_NM";

    /**
     *Column Value:RDS_REC_NM
     */
    public static final String COLUMN_RDS_REC_NM = "RDS_REC_NM";

    /**
     *Column Value:RDS_VRSN_NM
     */
    public static final String COLUMN_RDS_VRSN_NM = "RDS_VRSN_NM";

    /**
     *Column Value:RDS_FUT_USE1
     */
    public static final String COLUMN_RDS_FUT_USE1 = "RDS_FUT_USE1";

    /**
     *Column Value:RDS_FUT_USE2
     */
    public static final String COLUMN_RDS_FUT_USE2 = "RDS_FUT_USE2";

    /**
     *Column Value:RDS_FUT_USE3
     */
    public static final String COLUMN_RDS_FUT_USE3 = "RDS_FUT_USE3";

    /**
     *Column Value:RDS_FUT_USE4
     */
    public static final String COLUMN_RDS_FUT_USE4 = "RDS_FUT_USE4";

    /**
     *Column Value:RDS_FUT_USE5
     */
    public static final String COLUMN_RDS_FUT_USE5 = "RDS_FUT_USE5";

    /**
     *Column Value:RDS_CTRY_NM
     */
    public static final String COLUMN_RDS_CTRY_NM = "RDS_CTRY_NM";

    /**
     *Column Value:RDS_AVAL_CD
     */
    public static final String COLUMN_RDS_AVAL_CD = "RDS_AVAL_CD";

    /**
     *Column Value:RDS2_REC_NM
     */
    public static final String COLUMN_RDS2_REC_NM = "RDS2_REC_NM";

    /**
     *Column Value:RDS2_SVC_OPT_BLLG
     */
    public static final String COLUMN_RDS2_SVC_OPT_BLLG = "RDS2_SVC_OPT_BLLG";

    /**
     *Column Value:RDS2_SVC_OPT_JAM
     */
    public static final String COLUMN_RDS2_SVC_OPT_JAM = "RDS2_SVC_OPT_JAM";

    /**
     *Column Value:RDS2_SVC_OPT_OP
     */
    public static final String COLUMN_RDS2_SVC_OPT_OP = "RDS2_SVC_OPT_OP";

    /**
     *Column Value:RDS2_SVC_OPT_CNSMR
     */
    public static final String COLUMN_RDS2_SVC_OPT_CNSMR = "RDS2_SVC_OPT_CNSMR";

    /**
     *Column Value:RDS2_SVC_OPT_OUT_STK
     */
    public static final String COLUMN_RDS2_SVC_OPT_OUT_STK = "RDS2_SVC_OPT_OUT_STK";

    /**
     *Column Value:RDS2_DTL_CNTR_TRSMT
     */
    public static final String COLUMN_RDS2_DTL_CNTR_TRSMT = "RDS2_DTL_CNTR_TRSMT";

    /**
     *Column Value:RDS2_PAPER_SIZE_TRSMT
     */
    public static final String COLUMN_RDS2_PAPER_SIZE_TRSMT = "RDS2_PAPER_SIZE_TRSMT";

    /**
     *Column Value:RDS2_PART_CNTR_TRSMT
     */
    public static final String COLUMN_RDS2_PART_CNTR_TRSMT = "RDS2_PART_CNTR_TRSMT";

    /**
     *Column Value:RDS2_JAM_CNTR_TRSMT
     */
    public static final String COLUMN_RDS2_JAM_CNTR_TRSMT = "RDS2_JAM_CNTR_TRSMT";

    /**
     *Column Value:RDS2_ALARM_LOG_TRSMT
     */
    public static final String COLUMN_RDS2_ALARM_LOG_TRSMT = "RDS2_ALARM_LOG_TRSMT";

    /**
     *Column Value:RDS2_ROM_VRSN_TRSMT
     */
    public static final String COLUMN_RDS2_ROM_VRSN_TRSMT = "RDS2_ROM_VRSN_TRSMT";

    /**
     *Column Value:RDS2_CNTR_FUNC_TRSMT
     */
    public static final String COLUMN_RDS2_CNTR_FUNC_TRSMT = "RDS2_CNTR_FUNC_TRSMT";

    /**
     *Column Value:RDS2_CNSMR_CNTR_TRSMT
     */
    public static final String COLUMN_RDS2_CNSMR_CNTR_TRSMT = "RDS2_CNSMR_CNTR_TRSMT";

    /**
     *Column Value:RDS2_DEBUG_LOG_TRSMT
     */
    public static final String COLUMN_RDS2_DEBUG_LOG_TRSMT = "RDS2_DEBUG_LOG_TRSMT";

    /**
     *Column Value:DVC_REC_NM
     */
    public static final String COLUMN_DVC_REC_NM = "DVC_REC_NM";

    /**
     *Column Value:DVC_FIX_VAL1
     */
    public static final String COLUMN_DVC_FIX_VAL1 = "DVC_FIX_VAL1";

    /**
     *Column Value:DVC_FIX_VAL2
     */
    public static final String COLUMN_DVC_FIX_VAL2 = "DVC_FIX_VAL2";

    /**
     *Column Value:DVC_FIX_VAL3
     */
    public static final String COLUMN_DVC_FIX_VAL3 = "DVC_FIX_VAL3";

    /**
     *Column Value:DVC_FIX_VAL4
     */
    public static final String COLUMN_DVC_FIX_VAL4 = "DVC_FIX_VAL4";

    /**
     *Column Value:DVC_FIX_VAL5
     */
    public static final String COLUMN_DVC_FIX_VAL5 = "DVC_FIX_VAL5";

    /**
     *Column Value:DVC_EFF_TERM_YR
     */
    public static final String COLUMN_DVC_EFF_TERM_YR = "DVC_EFF_TERM_YR";

    /**
     *Column Value:DVC_UNIT_EXST
     */
    public static final String COLUMN_DVC_UNIT_EXST = "DVC_UNIT_EXST";

    /**
     *Column Value:DVC_ERR_NTFY
     */
    public static final String COLUMN_DVC_ERR_NTFY = "DVC_ERR_NTFY";

    /**
     *Column Value:DVC_JAM_NUM_NTFY
     */
    public static final String COLUMN_DVC_JAM_NUM_NTFY = "DVC_JAM_NUM_NTFY";

    /**
     *Column Value:DVC_JAM_RATE_NTFY
     */
    public static final String COLUMN_DVC_JAM_RATE_NTFY = "DVC_JAM_RATE_NTFY";

    /**
     *Column Value:DVC_PORT_NUM
     */
    public static final String COLUMN_DVC_PORT_NUM = "DVC_PORT_NUM";

    /**
     *Column Value:DVC_AVAL_CD
     */
    public static final String COLUMN_DVC_AVAL_CD = "DVC_AVAL_CD";

    /**
     *Column Value:DVC2_REC_NM
     */
    public static final String COLUMN_DVC2_REC_NM = "DVC2_REC_NM";

    /**
     *Column Value:DVC2_SVC_OPT_BLLG
     */
    public static final String COLUMN_DVC2_SVC_OPT_BLLG = "DVC2_SVC_OPT_BLLG";

    /**
     *Column Value:DVC2_SVC_OPT_ERR
     */
    public static final String COLUMN_DVC2_SVC_OPT_ERR = "DVC2_SVC_OPT_ERR";

    /**
     *Column Value:DVC2_SVC_OPT_OP
     */
    public static final String COLUMN_DVC2_SVC_OPT_OP = "DVC2_SVC_OPT_OP";

    /**
     *Column Value:DVC2_SVC_OPT_OUT_STK
     */
    public static final String COLUMN_DVC2_SVC_OPT_OUT_STK = "DVC2_SVC_OPT_OUT_STK";

    /**
     *Column Value:DVC2_SVC_OPT_CNSMR
     */
    public static final String COLUMN_DVC2_SVC_OPT_CNSMR = "DVC2_SVC_OPT_CNSMR";

    /**
     *Column Value:DVC2_ENBL_EFI_CTRL
     */
    public static final String COLUMN_DVC2_ENBL_EFI_CTRL = "DVC2_ENBL_EFI_CTRL";

    /**
     *Column Value:DVC2_IPC_CONN_AVAL
     */
    public static final String COLUMN_DVC2_IPC_CONN_AVAL = "DVC2_IPC_CONN_AVAL";

    /**
     *Column Value:DVC_CT_CONN_AVAL
     */
    public static final String COLUMN_DVC_CTRY_NM = "DVC_CTRY_NM";

    // START 2016/10/25 Y.Zhang [QC#15437, MOD]
    /**
     *Column Value:CUST_CLO_DAY
     */
    public static final String COLUMN_CUST_CLO_DAY = "1";

    /**
     *Column CUST_INDY
     */
    public static final String COLUMN_CUST_INDY = "Others";

    /**
     *Column DEPT_CNTR
     */
    public static final String COLUMN_DEPT_CNTR = "Y";

    /**
     *Column CONTR_TERM
     */
    public static final String COLUMN_CONTR_TERM = "72";

    /**
     *Column COLUMN_ADD_MONTH
     */
    public static final int COLUMN_ADD_MONTH = 120;

    /**
     * Because contract info is not exists,can not resist to contract info
     */
    public static final String NSZM1071E = "NSZM1071E";
    // END 2016/10/25 Y.Zhang [QC#15437, MOD]

    /**
     *Column Value:DVC2_SVC_TP
     */
    public static final String COLUMN_DVC2_SVC_TP = "DVC2_SVC_TP";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "User ID" is a mandatory field.
     */
    public static final String NSZM0293E = "NSZM0293E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /**
     * Service machine master is not found.
     */
    public static final String NSZM0006E = "NSZM0006E";

    /**
     * This machine is already registered to UGW.
     */
    public static final String NSZM1038E = "NSZM1038E";

    /**
     * This machine can't be registered, because it is shared. 
     */
    public static final String NSZM1039E = "NSZM1039E";

    /**
     * This machine Information already exist in IWR registration Table.
     */
    public static final String NSZM1040E = "NSZM1040E";

    /**
     * Configuration of Power Supply does not exist in the Master.
     */
    public static final String NSZM1041E = "NSZM1041E";

    /**
     * More than two Configuration of Power Supply exists.
     */
    public static final String NSZM1042E = "NSZM1042E";

    /**
     * This machine Information has been registered as Shared Machine, because it already exists in IWR registration Table.
     */
    public static final String NSZM1043W = "NSZM1043W";

    /**
     * Failed to insert the machine information to UGW.
     */
    public static final String NSZM1044W = "NSZM1044W";

    /**
     * Failed to insert IWR Registration Condition record.
     */
    public static final String NSZM1046E = "NSZM1046E";

    /**
     * Failed to update IWR Registration Condition record.
     */
    public static final String NSZM1047E = "NSZM1047E";

    /**
     * Failed to update Ds Contract Detail record.
     */
    public static final String NSZM1048E = "NSZM1048E";

    /**
     * An error occurred in UGW API.
     */
    public static final String NSZM1049E = "NSZM1049E";

    /**
     * Failed to update a Service Machine Master record.
     */
    public static final String NSZM0036E = "NSZM0036E";

    /**
     * Response message from UGW.Serial#:@, Message:@.
     */
    public static final String NSZM1068I = "NSZM1068I";

    /**
     * LENGTH:ORD_TAKE_MDSE
     */
    public static final int LENGTH_ORD_TAKE_MDSE = 8;

    /**
     * LENGTH:API_MSG
     */
    public static final int LENGTH_API_MSG = 1000;

    /**
     * Normal Message for jar file
     */
    public static final String NORMAL_MSG = "Successful";

    /**
     * Err Message for jar file:duplicate
     */
    public static final String ERR_MSG_DUP = "has already been registered";

    /**
     * Err Message for jar file:Not exist RDS_ID
     */
    public static final String ERR_MSG_NOT_EXIST_RDS_ID = "Could not register the [RDS ID] on line";

    /**
     * Err Message for jar file:Check Contents
     */
    public static final String ERR_MSG_CHECK_CONTENTS = "Check the contents of the file.";

    /** HHMMDD_START:8 */
    public static final int HHMMDD_START = 8;

    /** HHMMDD_END:14 */
    public static final int HHMMDD_END = 14;

    /** Message:Nomal End. */
    public static final String MSG_NML = "Done.";

    /** Message:Error Occurred. */
    public static final String MSG_ERR = "Error Occured.";

    /** NSZC0460_RSP_MSG */
    public static final String NSZC0460_RSP_MSG = "NSZC0460_RSP_MSG";

// START 2016/11/24 N.Arai [QC#15829, MOD]
    /** column name:IWR_RGTN_STS_CD */
    public static final String IWR_RGTN_STS_CD = "IWR_RGTN_STS_CD";

    /** column name:SHR_DLR_FLG */
    public static final String SHR_DLR_FLG = "SHR_DLR_FLG";

    /** Message : Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";
//END 2016/11/24 N.Arai [QC#15829, MOD]
}
