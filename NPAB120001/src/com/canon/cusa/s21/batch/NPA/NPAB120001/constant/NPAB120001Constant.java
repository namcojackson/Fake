/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB120001.constant;

/**
 *<pre>
 * NPAB120001:Send PO Form
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/07/2013   Hitachi         T.Kanasaka      Create          N/A
 * 03/29/2013   Hitachi         S.Tanabe        Update          QC799
 * 01/27/2016   CITS            R Shimamoto     Update          V1.0
 * 12/05/2016   CITS            Y.Fujii         Update          R350
 * 03/05/2019   CITS            T.Tokutomi      Update          QC#30350
 *</pre>
 */
public class NPAB120001Constant {

    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** Target data for @ is being used by others. Cannot process. */
    public static final String NPAM1267E = "NPAM1267E";

    /**
     * Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@]
     * MAIL_KEY1 : [@]
     */
    public static final String NPAM0063E = "NPAM0063E";

    /** Could not get Mail Template. MAIL_TEMPLATE_ID : [@] */
    public static final String NPAM0064E = "NPAM0064E";

    /** Failed to remove. [@] */
    public static final String NPAM1342E = "NPAM1342E";

    /** Wait Time */
    public static final int WAIT_TIME = 10;

    /** Retry Count */
    public static final int RETRY_COUNT = 3;

    /** SQL Item : GLBL_CMPY_CD */
    public static final String SQL_ITEM_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Item : PRCH_REQ_NUM */
    public static final String SQL_ITEM_PO_STS_CD = "poStsCd";

    // STR 2016/01/27 R.Shimamoto [V1.0 DEL]
    // /** SQL Item : TRSMT_METH_TP_CD_01 */
    // String SQL_ITEM_TRSMT_METH_TP_CD_01 = "trsmtMethTpCd01";
    // END 2016/01/27 R.Shimamoto [V1.0 DEL]

    // STR 2016/01/27 R.Shimamoto [V1.0 MOD]
    /** SQL Item : TRSMT_METH_TP_CD_05 */
    public static final String SQL_ITEM_TRSMT_METH_TP_CD_05 = "trsmtMethTpCd05";

    /** SQL Item : TRSMT_METH_TP_CD_06 */
    public static final String SQL_ITEM_TRSMT_METH_TP_CD_06 = "trsmtMethTpCd06";

    // END 2016/01/27 R.Shimamoto [V1.0 MOD]

    /** SQL Item : PO_ORD_NUM */
    public static final String SQL_ITEM_PO_ORD_NUM = "poOrdNum";

    /** SQL Item : PO_APVL_STS_CD_01 */
    public static final String SQL_ITEM_PO_APVL_STS_CD_01 = "poApvlStsCd01";

    /** SQL Item : PO_APVL_STS_CD_02 */
    public static final String SQL_ITEM_PO_APVL_STS_CD_02 = "poApvlStsCd02";

    /** SQL Item : PO_SEND_FLG */
    public static final String SQL_ITEM_PO_SEND_FLG = "poSendFlg";

    /** SQL Item : EDI_SEND_FLG */
    public static final String SQL_ITEM_EDI_SEND_FLG = "ediSendFlg";

    /** SQL Item : PURGE_TS */
    public static final String SQL_ITEM_PURGE_TS = "purgeTs";

    // QC#30350 Add
    /** SQL Item : SALES_DATE */
    public static final String SQL_ITEM_SALES_DATE = "salesDate";

    /** DB Item : PO */
    public static final String DB_ITEM_PO = "PO";

    /** DB Item : PO_ORD_NUM */
    public static final String DB_ITEM_PO_ORD_NUM = "PO_ORD_NUM";

    /** DB Item : INVTY_LOC_CD */
    public static final String DB_ITEM_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Item : PO_CHRG_CD */
    public static final String DB_ITEM_PO_CHRG_CD = "PO_CHRG_CD";

    /** DB Item : VND_CD */
    public static final String DB_ITEM_VND_CD = "VND_CD";

    /** DB Item : PO_SUBMT_PSN_CD */
    public static final String DB_ITEM_PO_SUBMT_PSN_CD = "PO_SUBMT_PSN_CD";

    /** DB Item : DP_TRSMT_METH_TP_CD */
    public static final String DB_ITEM_DP_TRSMT_METH_TP_CD = "DP_TRSMT_METH_TP_CD";

    /** DB Item : PO_HDR_TXT */
    public static final String DB_ITEM_PO_HDR_TXT = "PO_HDR_TXT";

    // 2013/03/29 QC799 S.Tanabe Update Start
    // /** DB Item : SEND_PO_EML_ADDR */
    // String DB_ITEM_SEND_PO_EML_ADDR = "SEND_PO_EML_ADDR";
    /** DB Item : DP_SEND_PO_EML_ADDR */
    public static final String DB_ITEM_DP_SEND_PO_EML_ADDR = "DP_SEND_PO_EML_ADDR";

    /** DB Item : VD_SEND_PO_EML_ADDR */
    public static final String DB_ITEM_VD_SEND_PO_EML_ADDR = "VD_SEND_PO_EML_ADDR";

    // 2013/03/29 QC799 S.Tanabe Update End
    /** DB Item : SEND_PO_FAX_NUM */
    public static final String DB_ITEM_SEND_PO_FAX_NUM = "SEND_PO_FAX_NUM";

    /** DB Item : VD_TRSMT_METH_TP_CD */
    public static final String DB_ITEM_VD_TRSMT_METH_TP_CD = "VD_TRSMT_METH_TP_CD";

    /** DB Item : FAX_NUM */
    public static final String DB_ITEM_FAX_NUM = "FAX_NUM";

    /** DB Item : SHIP_TO_CUST_CD */
    public static final String DB_ITEM_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Item : PO_RPT_WRK_PK */
    public static final String DB_ITEM_PO_RPT_WRK_PK = "PO_RPT_WRK_PK";

    /** DB Item Length : MAIL_ADDRESS_LENGTH */
    public static final int MAIL_ADDRESS_LENGTH = 700;

    /** BLANK */
    public static final String BLANK = "";

    /** Comma */
    public static final String COMMA = ",";

    /** Space */
    public static final String SPACE = " ";

    /** Full Space */
    public static final String FULL_SPACE = "                                                                ";

    /** Space */
    public static final String INDENT = "    ";

    /** CRLF */
    public static final String CRLF = "\r\n";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Group ID From : FROM0002 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0002";

    /** Mail Group ID To : */
    public static final String MAIL_GROUP_ID_TO = "NPAB0010";

    /** Mail Key From : DP */
    public static final String MAIL_KEY_FROM = "NP";

    /** Mail Key To : */
    public static final String MAIL_KEY_TO = "To";

    /** Mail Template ID : */
    public static final String MAIL_TEMPLATE_ID = "NPAB1200M001";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail tenplete METH_NM. */
    public static final String MT_METH_NM = "METH_NM";

    /** Mail tenplete KEY_NM. */
    public static final String MT_KEY_NM = "KEY_NM";

    /** Mail Item : Error Date */
    public static final String MAIL_ITEM_ERROR_DATE = "errDate";

    /** Mail Item : Error Warehouse Transfer Map */
    public static final String MAIL_ITEM_DETAIL_ERROR_MAP = "errDetailMap";

    /** MAIL FORMAT SIZE 16 */
    public static final int MAIL_FORMAT_SIZE_16 = 16;

    /** MAIL FORMAT SIZE 30 */
    public static final int MAIL_FORMAT_SIZE_30 = 30;

    /** Date Format */
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    /** NUM_CONST Key: NPAF0010_PURGE_DT */
    public static final String NPAF0010_PURGE_DT = "NPAF0010_PURGE_DT";
}
