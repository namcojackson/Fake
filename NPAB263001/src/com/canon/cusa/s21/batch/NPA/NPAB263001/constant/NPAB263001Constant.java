/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB263001.constant;

/**
 * <pre>
 * NPAB263001 : Receiving Inventory from EDI Vendor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/22/2015   Hitachi         S.Dong          Create          #QC61128
 *</pre>
 */
public class NPAB263001Constant {

    /** program id */
    public static final String PROGRAM_ID = "NPAB263001";

    /** message id : The parameter is null. [@] */
    public static final String ZZXM0020E = "ZZXM0020E";

    /** message id : Cannot retrieve mail address. */
    public static final String NPAM1265E = "NPAM1265E";

    /** message id : Cannot retrieve mail template. */
    public static final String NPAM1266E = "NPAM1266E";

    /** message id : Failed to delete the data. */
    public static final String NPAM1298E = "NPAM1298E";

    /** message id : Failed to register the data. */
    public static final String NPAM1300E = "NPAM1300E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Interface Id */
    public static final String MSG_TXT_INTERFACE_ID = "Interface Id";
    
    /** VAR_CHAR_CONST_VAR_CHAR_CONST_NM for Hytec */
    public static final String VAR_CHAR_CONST_VAR_CHAR_CONST_NM_HYTEC = "NPAB2630_HYTEC_WH_CD";
    
    /** VAR_CHAR_CONST_VAR_CHAR_CONST_VAL for Hytec */
    public static final String VAR_CHAR_CONST_VAR_CHAR_CONST_VAL_HYTEC = "V06";
    
    /** VAR_CHAR_CONST_VAR_CHAR_CONST_NM for Oracle */
    public static final String VAR_CHAR_CONST_VAR_CHAR_CONST_NM_ORACLE= "ORACLE_VND_CD";
    
    /** VAR_CHAR_CONST_VAR_CHAR_CONST_VAL for Oracle */
    public static final String VAR_CHAR_CONST_VAR_CHAR_CONST_VAL_ORACLE = "0001005989";

    /** System Error Mail group id for To. */
    public static final String MAIL_GROUP_ID = "NPAB2630";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_NPAB2630M001 = "NPAB2630M001";

    /** Mail String Field : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail String Field : errDate */
    public static final String MAIL_FIELD_ERR_DATE = "errDate";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail String Field : message */
    public static final String MAIL_FIELD_MESSAGE = "message";
    
    /* Table column RCV_ASN_VND */
    public static final String RCV_ASN_VND_RCV_ASN_VND_CD = "RCV_ASN_VND_CD";
    
    /* Table column PO_VND_V_PRNT_VND_CD */
    public static final String PO_VND_V_PRNT_VND_CD = "PRNT_VND_CD";
    
    /* Table column PO_VND_V_VND_CD */
    public static final String PO_VND_V_VND_CD = "VND_CD";

    /* Table column INTERFACE_ID NPAI0430_01 */
    
    /** NPAI043001_INTERFACE_ID for Hytec */
    public static final String NPAI043001_INTERFACE_ID_HYTEC = "NPAI0630";
    
    /** NPAI043001_INTERFACE_ID for Oracle */
    public static final String NPAI043001_INTERFACE_ID_ORACLE = "NPAI0730";

    /** NPAI043001_INTERFACE_ID */
    public static final String NPAI043001_INTERFACE_ID = "INTERFACE_ID";

    /** NPAI043001_TRANSACTION_ID */
    public static final String NPAI043001_TRANSACTION_ID = "TRANSACTION_ID";

    /** NPAI043001_MDSE_CD */
    public static final String NPAI043001_MDSE_CD = "MDSE_CD";

    /** NPAI043001_WH_CD */
    public static final String NPAI043001_WH_CD = "WH_CD";
    
    /** NPAI043001_STK_STS_TXT */
    public static final String NPAI043001_STK_STS_TXT = "STK_STS_TXT";
    
    /** NPAI043001_STK_STS_TXT_GOOD */
    public static final String NPAI043001_STK_STS_TXT_GOOD = "GOOD";
    
    /** NPAI043001_STK_STS_TXT_TBR */
    public static final String NPAI043001_STK_STS_TXT_TBR = "TBR";

    /** NPAI043001_SEGMENT_ID */
    public static final String NPAI043001_SEGMENT_ID = "SEGMENT_ID";

    /** NPAI043001_SEGMENT_ID */
    public static final String NPAI043001_UNIT_ID = "UNIT_ID";

    /** NPAI043001_SEQ_NUMBER */
    public static final String NPAI043001_SEQ_NUMBER = "SEQ_NUMBER";

    /** NPAI043001_INVTY_QTY */
    public static final String NPAI043001_INVTY_QTY = "INVTY_QTY";
}
