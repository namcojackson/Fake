/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB009001.constant;

/**
 * <pre>
 * Dunning Letter EIP Request
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Hitachi         T.Tsuchida      Create          N/A
 * 2018/01/18   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/07/11   Fujitsu         S.Ohki          Update          QC#27002
 * 2018/08/21   CITS            K.Kameoka       Update          QC#25825
 * 2021/05/10   CITS            G.Delgado       Update          QC#58340
 * 2021/05/19   CITS            K.Suzuki        Update          QC#58340-1 from class to interface
 * 2021/07/14   CITS            K.Suzuki        Update          QC#58340-2
 * 2021/09/14   CITS            K.Suzuki        Update          QC#59188
 * 2021/09/21   CITS            K.Suzuki        Update          QC#59212
 * </pre>
 */
public interface NFDB009001Constant {
    /**
     * BUSINESS_ID : NFDB0090
     */
    final String BUSINESS_ID = "NFDB0090";

    /**
     * BATCH_NM : Dunning letter EIP Request
     */
    final String BATCH_NM = "Dunning letter EIP Request";

    /**
     * Failed to update [@].
     */
    final String NFDM0004E = "NFDM0004E";

    /**
     * The mail template cannot be acquired. <Template ID: [@]>
     */
    final String NFBM0184E = "NFBM0184E";

    /**
     * [@] is mandatory.
     */
    final String ZZZM9025E = "ZZZM9025E";

    // START 2021/05/10 G.Delgado [QC#58340,ADD]
    /**
     * [@] is invalid value
     */
    final String ZZZM9026E = "ZZZM9026E";
    // END 2021/05/10 G.Delgado [QC#58340,ADD]

    /**
     * VAR_CHAR_CONST : AR_CLT_DEF_EML_ADDR
     */
    final String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";

    /**
     * DIV_CONMA : ,
     */
    final String DIV_CONMA = ",";

    /**
     * FETCH_SIZE_MAX : 1000
     */
    final int FETCH_SIZE_MAX = 1000;

    /**
     * MAP_KEY : CLT_STRGY_WRK_ITEM_TRX_PK
     */
    final String CLT_STRGY_WRK_ITEM_TRX_PK = "CLT_STRGY_WRK_ITEM_TRX_PK";

    /**
     * MAP_KEY : BILL_TO_CUST_CD
     */
    final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**
     * MAP_KEY : CLT_ACCT_CD
     */
    final String CLT_ACCT_CD = "CLT_ACCT_CD";

    /**
     * MAP_KEY : CLT_PSN_CD
     */
    final String CLT_PSN_CD = "CLT_PSN_CD";

    /**
     * MAP_KEY : CLT_DUN_LTR_TP_CD
     */
    final String CLT_DUN_LTR_TP_CD = "CLT_DUN_LTR_TP_CD";

    /**
     * MAP_KEY : DUN_FLG
     */
    final String DUN_FLG = "DUN_FLG";

    /**
     * MAP_KEY : INV_PAST_DUE_FLG
     */
    final String INV_PAST_DUE_FLG = "INV_PAST_DUE_FLG";

    /**
     * MAP_KEY : RPT_GRP_PRINT_FLG
     */
    final String RPT_GRP_PRINT_FLG = "RPT_GRP_PRINT_FLG";

    /**
     * MAP_KEY : RPT_PRINT_OTPT_FLG
     */
    final String RPT_PRINT_OTPT_FLG = "RPT_PRINT_OTPT_FLG";

    /**
     * MAP_KEY : RPT_EML_OTPT_FLG
     */
    final String RPT_EML_OTPT_FLG = "RPT_EML_OTPT_FLG";

    /**
     * MAP_KEY : RPT_FAX_OTPT_FLG
     */
    final String RPT_FAX_OTPT_FLG = "RPT_FAX_OTPT_FLG";

    /**
     * MAP_KEY : RPT_FILE_OTPT_FLG
     */
    final String RPT_FILE_OTPT_FLG = "RPT_FILE_OTPT_FLG";

    /**
     * MAP_KEY : RPT_FTP_OTPT_FLG
     */
    final String RPT_FTP_OTPT_FLG = "RPT_FTP_OTPT_FLG";

    /**
     * MAP_KEY : DUN_ML_TMPL_ID
     */
    final String DUN_ML_TMPL_ID = "DUN_ML_TMPL_ID";

    /**
     * MAP_KEY : DEST_TP_CD
     */
    final String DEST_TP_CD = "DEST_TP_CD";

    /**
     * MAP_KEY : RPT_ID
     */
    final String RPT_ID = "RPT_ID";

    /**
     * MAP_KEY : RPT_TTL_NM
     */
    final String RPT_TTL_NM = "RPT_TTL_NM";

    /**
     * MAP_KEY : CLT_DUN_LTR_TP_DESC_TXT
     */
    final String CLT_DUN_LTR_TP_DESC_TXT = "CLT_DUN_LTR_TP_DESC_TXT";

    /**
     * MAP_KEY : DS_CTAC_PNT_VAL_TXT_BC
     */
    final String DS_CTAC_PNT_VAL_TXT_BC = "DS_CTAC_PNT_VAL_TXT_BC";

    /**
     * MAP_KEY : DS_CTAC_PNT_VAL_TXT_AC
     */
    final String DS_CTAC_PNT_VAL_TXT_AC = "DS_CTAC_PNT_VAL_TXT_AC";

    /**
     * MAP_KEY : RPT_BR_NUM
     */
    final String RPT_BR_NUM = "RPT_BR_NUM";

    // START 2021/05/10 G.Delgado [QC#58340,ADD]
    /**
     * MAP_KEY : EIP_RPT_RQST_PK
     */
    final String EIP_RPT_RQST_PK = "EIP_RPT_RQST_PK";

    /**
     * MAP_KEY : RPT_RQST_STS_TXT
     */
    final String RPT_RQST_STS_TXT = "RPT_RQST_STS_TXT";

    /**
     * MAP_KEY : CLT_DUN_LTR_WRK_PK
     */
    final String CLT_DUN_LTR_WRK_PK = "CLT_DUN_LTR_WRK_PK";

    /**
     * ERR_PRM_VAR_USER1 : User Variable1
     */
    final String ERR_PRM_VAR_USER1 = "User Variable1";
    // END 2021/05/10 G.Delgado [QC#58340,ADD]

    /**
     * ERR_PRM_GLBL_CMPY_CD : Global Company Code
     */
    final String ERR_PRM_GLBL_CMPY_CD = "Global Company Code";

    /**
     * ERR_PRM_DEF_CLT_EMAIL_ADDRESS : Default Collector's Email Address
     */
    final String ERR_PRM_DEF_CLT_EMAIL_ADDRESS = "Default Collector's Email Address";

    /**
     * ERR_PRM_BATCH_PRC_DT : Batch Process Date
     */
    final String ERR_PRM_BATCH_PRC_DT = "Batch Process Date";

    /**
     * ERR_PRM_CLT_STRGY_WRK_ITEM_TRX : Collection Strategy Item Transaction
     */
    final String ERR_PRM_CLT_STRGY_WRK_ITEM_TRX = "Collection Strategy Item Transaction";

    /**
     * ERR_PRM_CLT_DUN_LTR_WRK : Collector Dunning Letter Work
     */
    final String ERR_PRM_CLT_DUN_LTR_WRK = "Collector Dunning Letter Work";

    /**
     * TYPE_TIME_STAMP : yyyyMMddHHmmss
     */
    final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    /**
     * MAIL_GROUP_ID_FROM : FROM0003
     */
    final String MAIL_GROUP_ID_FROM = "FROM0003";

    /**
     * MAIL_GROUP_KEY_NFD : NFD
     */
    final String MAIL_GROUP_KEY_NFD = "NFD";

    /**
     * MAIL_TEMPLATE_KEY_CLT_ACCT_CD : CltAcctCd
     */
    // START 2018/01/18 [QC#21734,MOD]
    // final String MAIL_TEMPLATE_KEY_CLT_ACCT_CD = "cltAcctCd";
    final String MAIL_TEMPLATE_KEY_CLT_ACCT_CD = "CltAcctCd";
    // END   2018/01/18 [QC#21734,MOD]

    /**
     * ATTACH_FILE_NM : Attatched PDF file name
     */
    final String ATTACH_FILE_NM = "Attatched PDF file name";

    /** DB Field Name */
    final String RPT_BR_NUM_EML = "RPT_EML_OTPT_RPT_BR_NUM";

    /** DB Field Name */
    final String RPT_BR_NUM_PRINT = "RPT_PRINT_OTPT_RPT_BR_NUM";

    /** DB Field Name */
    final String RPT_BR_NUM_FAX = "RPT_FAX_OTPT_RPT_BR_NUM";

    /** DB Field Name */
    final String RPT_BR_NUM_FILE = "RPT_FILE_OTPT_RPT_BR_NUM";

    /** DB Field Name */
    final String RPT_BR_NUM_FTP = "RPT_FTP_OTPT_RPT_BR_NUM";

    // START 2018/07/11 [QC#27002, ADD]
    /** EXTENSION_PDF */
    final String EXTENSION_PDF = ".pdf";
    // END   2018/07/11 [QC#27002, ADD]

    //QC#25825 Mod Start for CUPS
    /** Mail Info : ID */
    final String KEYID = "ID";

    /** Mail Info : Message : Bill/Invoice No. */
    final String NUMBER = "NUMBER";

    /** Mail Info : Message */
    final String MESSAGE = "MESSAGE";
    //QC#25825 Mod End for CUPS

    // START 2021/05/10 G.Delgado [QC#58340,ADD]
    /**
     * Process Mode PDF Creation : 01
     */
    final String PROCESS_MODE_PDF_CREATION = "01";

    /**
     * Process Mode Print Request : 02
     */
    final String PROCESS_MODE_PDF_PRINT_REQUEST = "02";

    /**
     * RPT_RQST_STS_TXT : SUCCESS
     */
    final String RPT_RQST_STS_TXT_SUCCESS = "SUCCESS";

    /**
     * DATE_TIME_PATTERN : yyyyMMddHHmmssSSS
     */
    final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * UNDERSCORE : _
     */
    final String UNDERSCORE = "_";
    // END 2021/05/10 G.Delgado [QC#58340,ADD]

    // START 2021/07/14 K.Suzuki [QC#58340-2,ADD]
    /**
     * AR_DUN_LTR_ADD_BARCODE_FLG
     */
    final String AR_DUN_LTR_ADD_BARCODE_FLG = "AR_DUN_LTR_ADD_BARCODE_FLG";
    // END 2021/07/14 K.Suzuki [QC#58340-2,ADD]

    // START 2021/09/14 K.Suzuki [QC#59188,ADD]
    /**
     * AR_DUN_LTR_CUPS_PAGE_COUNT
     */
    final String AR_DUN_LTR_CUPS_PAGE_COUNT = "NWCB011001_CUPS_PAGE_COUNT";
    // END 2021/09/14 K.Suzuki [QC#59188,ADD]

    // START 2021/09/21 K.Suzuki [QC#59212,ADD]
    /**
     * It failed to print the dunning letters.
     */
    final String NFDM0058E = "NFDM0058E";
    // END   2021/09/21 K.Suzuki [QC#59212,ADD]
}
