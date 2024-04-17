/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB002001.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.mail.S21Mail;


/**
 * <pre>
 * Batch Program Constant Class for Adjust Physical Inventory from IDS Interface.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/11/2016   CITS            N.Akaishi       Update          V1.0
 * 11/11/2016   CITS            S.Endo          Update          QC#14479
 * 11/14/2016   CITS            S.Endo          Update          QC#14598/QC#15058
 * 03/15/2018    CITS           K.Ogino         Update          QC#24449
 * 08/17/2018    CITS           T.hakodate      UPDATE          QC#27709
 *</pre>
 */
public class NLCB002001Constant {

    /** business_id */
    public static final String BUSINESS_ID = "NLCB0020";

    /** Message ID : NLGM0007E */
    public static final String MSG_ID_NLGM0007E = "NLGM0007E";

    /** Message ID : NLGM0008E */
    public static final String MSG_ID_NLGM0008E = "NLGM0008E";

    /** Message ID : NLCM0047E */
    public static final String MSG_ID_NLCM0047E = "NLCM0047E";

    /** Message ID : NLCM0048E */
    public static final String MSG_ID_NLCM0048E = "NLCM0048E";

    /** Message ID : NLCM0049E */
    public static final String MSG_ID_NLCM0049E = "NLCM0049E";

    /** Message ID : NLCM0050E */
    public static final String MSG_ID_NLCM0050E = "NLCM0050E";

    /** Message ID : NLCM0063E */
    public static final String MSG_ID_NLCM0063E = "NLCM0063E";

    /** Message ID : ZZBM0009I */
    public static final String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** Message ID : ZZM9000E */
    public static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /** Message ID : NLCM0148E */
    public static final String MSG_ID_NLCM0148E = "NLCM0148E";

    /** Message ID : The Serial# is mandatory for a serialized item[@]. */
    public static final String MSG_ID_NLCM0181E = "NLCM0181E";

    /** Message ID : The Serial#[@] is unnecessary for a non-serialized item[@]. */
    public static final String MSG_ID_NLCM0186W = "NLCM0186W";
    
    
    public static final String NMAM0177E = "NMAM0177E";

    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Interface ID */
    public static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : WorkFlow API */
    public static final String MSG_STR_WORKFLOW_API = "WorkFlow API(startProcessAndCommit())";

    /** Table ID : NLCI0330_01 */
    public static final String TBL_ID_NLCI0330_01 = "NLCI0330_01";

    /** Table ID : INVTY_ORD */
    public static final String TBL_ID_INVTY_ORD = "INVTY_ORD";

    /** Table ID : INVTY_ORD_DTL */
    public static final String TBL_ID_INVTY_ORD_DTL = "INVTY_ORD_DTL";

    /** Table ID : MDSE */
    public static final String TBL_ID_MDSE = "MDSE";

    /** Table ID : PHYS_INVTY_CNT_HDR */
    public static final String TBL_ID_PHYS_INVTY_CNT_HDR = "PHYS_INVTY_CNT_HDR";

    /** Table ID : PHYS_INVTY_CNT_DTL */
    public static final String TBL_ID_PHYS_INVTY_CNT_DTL = "PHYS_INVTY_CNT_DTL";

    /** Table ID : PHYS_INVTY_CTRL */
    public static final String TBL_ID_PHYS_INVTY_CTRL = "PHYS_INVTY_CTRL";

    /** Table ID : PHYS_INVTY_AJT */
    public static final String TBL_ID_PHYS_INVTY_AJT = "PHYS_INVTY_AJT";

    /** Column Name : GLBL_CMPY_CD */
    public static final String COL_NM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column Name : NLCI0330_01.CODE_WH_IF */
    public static final String COL_NM_CODE_WH_IF = "CODE_WH_IF";

    /** Column Name : NLCI0330_01.CODE_ITEM_IF */
    public static final String COL_NM_CODE_ITEM_IF = "CODE_ITEM_IF";

    /** Column Name : NLCI0330_01.CODE_STAT_STOCK_IF */
    public static final String COL_NM_CODE_STAT_STOCK_IF = "CODE_STAT_STOCK_IF";

    /** Column Name : NLCI0330_01.SIGN_IF */
    public static final String COL_NM_SIGN_IF = "SIGN_IF";

    /** Column Name : NLCI0330_01.QTY_ADJUST_IF */
    public static final String COL_NM_QTY_ADJUST_IF = "QTY_ADJUST_IF";

    /** Column Name : NLCI0330_01.INTERFACE_ID */
    public static final String COL_NM_INTERFACE_ID = "INTERFACE_ID";

    /** Column Name : NLCI0330_01.TRANSACTION_ID */
    public static final String COL_NM_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column Name : NLCI0330_01.SEGMENT_ID */
    public static final String COL_NM_SEGMENT_ID = "SEGMENT_ID";

    /** Column Name : NLCI0330_01.UNIT_ID */
    public static final String COL_NM_UNIT_ID = "UNIT_ID";

    /** Column Name : NLCI0330_01.SEQ_NUMBER */
    public static final String COL_NM_SEQ_NUMBER = "SEQ_NUMBER";

    /** Column Name : MDSE.MDSE_CD */
    public static final String COL_NM_MDSE_CD = "MDSE_CD";

    /** Column Name : MDSE.FIRST_PROD_CTRL_CD */
    public static final String COL_NM_FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /** Column Name : INVTY.INVTY_QTY */
    public static final String COL_NM_INVTY_QTY = "INVTY_QTY";

    // 2016/03/18 N.Akaishi [V1.0 Add] Start
    /** Column Name : NLCI0330_01.SER_NUM */
    public static final String COL_NM_SER_NUM = "SER_NUM";

    /** Column Name : INVTY.INVTY_LOC_CD */
    public static final String COL_NM_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Column Name : PHYS_INVTY_CTRL.PHYS_INVTY_STS_CD */
    public static final String COL_NM_PHYS_INVTY_STS_CD = "PHYS_INVTY_STS_CD";

    /** Column Name : PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_PK */
    public static final String COL_NM_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /** Column Name : PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_NM */
    public static final String COL_NM_PHYS_INVTY_CTRL_NM = "PHYS_INVTY_CTRL_NM";

    /** Column Name : NLCI0330_01.PHYS_INVTY_STS_CD */
    public static final String COL_NM_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    /** Column Name : PHYS_INVTY_CTRL.PHYS_INVTY_DT */
    public static final String COL_NM_PHYS_INVTY_DT = "PHYS_INVTY_DT";

    /** Column Name : PHYS_INVTY_CTRL.RTL_WH_CD */
    public static final String COL_NM_RTL_WH_CD = "RTL_WH_CD";

    /** Column Name : PHYS_INVTY_CTRL.RTL_SWH_CD */
    public static final String COL_NM_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column Name : PHYS_INVTY_ADJ.ADJ_VAR_QTY */
    public static final String COL_NM_ADJ_VAR_QTY = "ADJ_VAR_QTY";

    /** Column Name : PHYS_INVTY_CNT_HDR.PHYS_INVTY_CNT_HDR_PK */
    public static final String COL_NM_PHYS_INVTY_CNT_HDR_PK = "PHYS_INVTY_CNT_HDR_PK";

    /** Column Name : PHYS_INVTY_ADJ.PHYS_INVTY_ADJ_PK */
    public static final String COL_NM_PHYS_INVTY_ADJ_PK = "PHYS_INVTY_ADJ_PK";
    // 2016/03/18 N.Akaishi [V1.0 Add] End

    /** Column Name : MDSE.RCV_SER_TAKE_FLG */
    public static final String COL_NM_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /** Column Name : WH.LOC_NM */
    public static final String COL_NM_LOC_NM = "LOC_NM";

    /** Column Name : AVAL_INVTY_APP_ID.STK_STS_CD  */
    public static final String COL_STK_STS_CD = "STK_STS_CD";

    /** Column Name : RTL_WH.WMS_WH_CD  */
    public static final String COL_NM_WMS_WH_CD = "WMS_WH_CD";

    /** Column Name : NLCI0330_01.PHYS_INVTY_ADJ_NM  */
    public static final String COL_NM_PHYS_INVTY_ADJ_NM = "PHYS_INVTY_ADJ_NM";

    /** Column Name : NLCI0330_01.CONFIG_MSTR_NUM  */
    public static final String COL_NM_CONFIG_MSTR_NUM = "CONFIG_MSTR_NUM";

    /** Column Name : PHYS_INVTY_CNT_DTL.PHYS_INVTY_CNT_DTL_PK */
    public static final String COL_NM_PHYS_INVTY_CNT_DTL_PK = "PHYS_INVTY_CNT_DTL_PK";

    /** SQL Map StatementID : getAdjustment */
    public static final String SQL_STID_GET_ADJUSTMENT = "getAdjustmentData";

    /** SQL Map StatementID : getPhysInvtyCtrlPk */
    public static final String SQL_STID_GET_PHYS_INVTY_CTRL_PK = "getPhysInvtyCtrlPk";

    /** SQL Map StatementID : getConfigId */
    public static final String SQL_STID_GET_CONFIG_ID = "getConfigId";

    /** SQL Map bind key : GLBL_CMPY_CD */
    public static final String SQL_PRM_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Map bind key : INTERFACE_ID */
    public static final String SQL_PRM_INTERFACE_ID = "interfaceId";

    /** SQL Map bind key : TRANSACTION_ID */
    public static final String SQL_PRM_TRANSACTION_ID = "transactionId";

    /** SQL Map bind key : LOC_STS_CD */
    public static final String SQL_PRM_LOC_STS_CD = "locStsCd";

    /** SQL Map bind key : PHYS_INVTY_NUM */
    public static final String SQL_PRM_PHYS_INVTY_NUM = "physInvtyNum";

    /** SQL Map bind key : BIZ_APP_ID  */
    public static final String SQL_BIZ_APP_ID = "bizAppId";

    /** SQL Map bind key : CONFIG_MSTR_PK  */
    public static final String SQL_PRM_CONFIG_MSTR_PK = "configMstrPk";

    /** SQL Map bind key : CUR_LOC_NUM  */
    public static final String SQL_PRM_CUR_LOC_NUM = "curlocNum";

    /** Batch ID : NLCB0020 */
    public static final String BATCH_ID_NLCB0020 = "NLCB0020";

    /** API ID : NLZC0010 (Inventory Allocation API) */
    public static final String API_ID_NLZC0010 = "NLZC0010";

    /** API ID : NLZC0030 (Inventory Order API) */
    public static final String API_ID_NLZC0030 = "NLZC0030";

    /** API ID : NLZC0040 (Adjustment Order API) */
    public static final String API_ID_NLZC0040 = "NLZC0040";

    /** API ID : NLZC0630 (PI Close API) */
    public static final String API_ID_NLZC0630 = "NLZC0630";

    /** API ID : NLZC4030 (Serial Validation API) */
    public static final String API_ID_NLZC4030 = "NLZC4030";

    /** INTERFACE ID : NLCI0330 */
    public static final String INTERFACE_ID_NLCI0330 = "NLCI0330";

    /** Workflow Document Id : Adjustment Entry */
    public static final String DOC_ID_ADJUSTMENT = "Adjustment Entry";

    /** Workflow Process Code : NLCP0020 */
    public static final String PROC_CD_NLCP0020 = "NLCP0020";

    /** Workflow Task Code : NLCL0100T010 */
    public static final String TASK_CD_NLCL0100T010 = "NLCL0100T010";

    /** Mail Header for mismatch data */
    public static final String MAIL_HEADER_DATA = S21Mail.LINE_SEPARATOR + "Message#  Message";

    /** Mail Header for API error */
    public static final String MAIL_HEADER_API = S21Mail.LINE_SEPARATOR + "API_ID#  Message#  Message";

    /** Plus string */
    public static final String STR_PLUS = "+";

    /** Minus string */
    public static final String STR_MINUS = "-";

    /** Adjustment Order Comment string */
    public static final String STR_ORD_COMMENT = "THE ADJUSTMENT IS CREATED FOR YEARLY PHYSICAL INVENTORY";

    /** Digit of Inventory Order Line Number */
    public static final int DIGIT_INVTY_ORDER_LINE_NUM = 3;

    /** Digit of Location Name for Workflow parameter */
    public static final int DIGIT_LOC_NM = 15;

    /** The maximum number of one order */
    public static final int DETAIL_MAX_CNT = 100;

    /** The maximum number of serial count for one line */
    public static final int SERIAL_MAX_CNT = 100;

    /** WF variable key : INVTY_ORD_STS_NM */
    public static final String WF_VARS_INVTY_ORD_STS_NM = "InventoryOrderSts";

    /** WF variable key : INVTY_LOC_CD */
    public static final String WF_VARS_INVTY_LOC_CD = "InvtyLocCd";

    /** WF variable key : LOC_NM */
    public static final String WF_VARS_LOC_NM = "LocationName";

    /** Adjust Screen ID : LOC_NM */
    public static final String ADJUST_SRC_ID = "NLCL0100";

    /** key info delim : / */
    public static final String KEY_INFO_DELIM = "/";

    /** for Adjustment (if SIGN_IF = "+") */
    public static final BigDecimal ADJUSTMENT_SIGN = BigDecimal.valueOf(-1);

    /** 0 */
    public static final int ZERO = 0;

    /** " "(blank) */
    public static final String BLANK = " ";

    /** "" (Empty) */
    public static final String EMPTY = "";

    /** COMMENT_SUBSTR_INDEX */
    public static final int COMMENT_SUBSTR_INDEX = 64;

    /** MSG_KIND : WARNING */
    public static final String MSG_KIND_WARNING =  "W";

    /** MSG_KIND : ERROR */
    public static final String MSG_KIND_ERROR =  "E";

    /** Array Index : 0  */
    public static final int IDX_0 =  0;

    /** Array Index : 1  */
    public static final int IDX_1 =  1;

    /** Array Index : 2  */
    public static final int IDX_2 =  2;

    /** Array Index : 3  */
    public static final int IDX_3 =  3;

    /** Array Index : 4  */
    public static final int IDX_4 =  4;

    /** Array Index : 5  */
    public static final int IDX_5 =  5;

    /** Array Index : 6  */
    public static final int IDX_6 =  6;

    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BUSINESS_ID;

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** QC#24449 template ID */
    public static final String MAIL_TEMPLATE_ID_NO_APPROVE = BUSINESS_ID + "M002";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** line separator java system property key for mail */
    public static final String LINE_SEPARATOR_KEY_FOR_MAIL = "line.separator";

    /** mail template parameter key */
    public static final String MAIL_TEMPLATE_PARAM_KEY_BATCHID = "batchId";

    /** mail template parameter key */
    public static final String MAIL_TEMPLATE_PARAM_KEY_TIMESTAMP = "TIMESTAMP";

    /** mail template parameter key */
    public static final String MAIL_TEMPLATE_PARAM_KEY_ERRDATE = "errDate";

    /** mail template parameter key */
    public static final String MAIL_TEMPLATE_PARAM_KEY_MESSAGE = "message";

    /** mail key1 value */
    public static final String MAIL_KEY1_VALUE_TO = "To";

    /** NLZC0610 PI Approval To WF API call mode**/
    public static final String CREATE_MODE = "01";

    /** JOBID **/
    public static final String JOBID = "S21SWD_SLCJ0130010";

    /** SYSTEM USER ID**/
    public static final String SYSTEM_USER = "SYSTEM";

}
