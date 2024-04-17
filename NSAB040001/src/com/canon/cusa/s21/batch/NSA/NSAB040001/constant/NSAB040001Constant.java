/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB040001.constant;

/**
 *<pre>
 * CFS Contract Validation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   CITS            T.Kikuhara      Create          N/A
 * 02/22/2017   Hitachi         K.Kitachi       Update          QC#17626
 * 03/27/2017   Hitachi         T.Tomita        Update          QC#18073
 * 02/02/2018   Hitachi         U.Kim           Update          QC#23687
 *</pre>
 */
public class NSAB040001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** An error occurred in API. <APIID: [@], Error Code: [@], Data Key: [@]>. */
    public static final String NSAM0003E = "NSAM0003E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** System Error : [@] was not found on a constant table. */
    public static final String NSZM0543E = "NSZM0543E";

    // START 2018/02/02 U.Kim [QC#23687, ADD]
    /**  Message : @ cannot be obtained. */
    public static final String NSZM0392E = "NSZM0392E";
    // END 2018/02/02 U.Kim [QC#23687, ADD]

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Sales Date. */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** message Item: InterfaceId. */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    // add start 2017/03/27 CSA Defect#18073
    /** message Item: Contract# */
    public static final String MSG_ITEM_DS_CONTR_NUM = "Contract#";

    /** message Item: colon(:) */
    public static final String MSG_ITEM_COLON = ":";
    // add end 2017/03/27 CSA Defect#18073

    /** NUM_CONST: NUM_CONST_NM. */
    public static final String CFS_SER_AUD_QLTY_ASRN_DEL_VAL = "CFS_SER_AUD_QLTY_ASRN_DEL_VAL";

    /** NSZC0570 Contract Validation API ID. */
    public static final String NSZC0570 = "NSZC0570";

    /** Batch ID. */
    public static final String BATCH_ID = "NSAB040001";

    /** Date time format. */
    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy hh:mm";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NSAB0400M001";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NSAB0400";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_DATE = "errDate";

    /** Mail Bind. */
    public static final String EMAIL_PARAM_MSG = "message";

    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String SALES_DT = "SALES_DT";
    /** . */
    public static final String DS_CONTR_EDI_CD = "DS_CONTR_EDI_CD";
    /** . */
    public static final String DS_CONTR_VLD_STS_CD = "DS_CONTR_VLD_STS_CD";
    /** . */
    public static final String DS_CONTR_VLD_CATG_CD = "DS_CONTR_VLD_CATG_CD";
    /** . */
    public static final String CFS_QLTY_ASRN_CHK_FLG = "CFS_QLTY_ASRN_CHK_FLG";
    /** . */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    /** . */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";
    /** . */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    /** . */
    public static final String DS_CONTR_VLD_RSLT_WRK_PK = "DS_CONTR_VLD_RSLT_WRK_PK";
    /** . */
    public static final String LEASE_CMPY_CD = "LEASE_CMPY_CD";
    /** . */
    public static final String SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";
    /** . */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";
    /** . */
    public static final String DS_CONTR_VLD_RSLT_MSG_TXT = "DS_CONTR_VLD_RSLT_MSG_TXT";
    /** . */
    public static final String SER_NUM = "SER_NUM";

    // START 2017/02/22 K.Kitachi [QC#17626, ADD]
    /** Error Contract Row Number */
    public static final int ERR_CONTR_ROW_NUM = 2000;

    /** Max memo size */
    public static final int SVC_MEMO_SIZE = 4000;

    /** Business application ID */
    public static final String BIZ_APP_ID = "NSAB0400";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Column Name: DS_CONTR_CTRL_STS_CD */
    public static final String DS_CONTR_CTRL_STS_CD = "DS_CONTR_CTRL_STS_CD";

    /** VAR_CHAR_CONST:SVC_MEMO_RSN_FOR_CPLT_CONTR */
    public static final String SVC_MEMO_RSN_FOR_CPLT_CONTR = "SVC_MEMO_RSN_FOR_CPLT_CONTR";
    // END 2017/02/22 K.Kitachi [QC#17626, ADD]

    // START 2018/02/02 U.Kim [QC#23687, ADD]
    /** MultiCnt */ 
    public static final String MULTI_CNT = "multiCnt";

    /** UsrVar1 */
    public static final String USR_VAL_1 = "usrVar1";

    /** NUM_CONST : NSAB0400MULTI_CONTR_CALC_CNT */
    public static final String NSAB0400MULTI_CONTR_CALC_CNT = "NSAB0400MULTI_CONTR_CALC_CNT";

    // END 2018/02/02 U.Kim [QC#23687, ADD]
}
