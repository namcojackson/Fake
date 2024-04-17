/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB004001.constant;


/**
 * <pre>
 * Batch Program Constant Class for PI Start Job.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016    CITS            N.Akaishi       Create          n/a
 *</pre>
 */
public class NLCB004001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLCB0400";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NLCB040001:";

    // -- Error Message Code --------------------
    /** Message ID : NLGM0007E */
    public static final String MSG_ID_NLGM0007E = "NLGM0007E";

    /** Message ID : NLGM0008E */
    public static final String MSG_ID_NLGM0008E = "NLGM0008E";

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /**
     * The value which is not numerical was input to the field of [@].
     */
    public static final String MSG_ID_ZZM9004E = "ZZM9004E";

    /**
     * Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@]
     * MAIL_KEY1 : [@]
     */
    public static final String NPAM0063E = "NPAM0063E";

    /** Could not get Mail Template. MAIL_TEMPLATE_ID : [@] */
    public static final String NPAM0064E = "NPAM0064E";

    /** Message ID : NPAM1172E Failed to insert. [@] */
    public static final String MSG_ID_NPAM1172E = "NPAM1172E";

    /** Message ID : NPAM1173E The field of [@] is not input. */
    public static final String MSG_ID_NPAM1173E = "NPAM1173E";

    /** Message ID : NPAM1200E Failed to register mail. */
    public static final String MSG_ID_NPAM1200E = "NPAM1200E";

    /** Message ID : NPAM1321E Mandatory parameter is not set. Item Name: [@], Table Name: [@], Key Item: [@]. */
    public static final String MSG_ID_NPAM1321E = "NPAM1321E";

    /** Message ID : NLCM0174W Invalid PI. PI#: [@], Retail WH: [@],  Effective: From:[@], To:[@] */
    public static final String MSG_ID_NLCM0174W = "NLCM0174W";

    /** Message ID : NPAM1321E Mandatory parameter is not set. Item Name: [@], Table Name: [@], Key Item: [@]. */
    public static final String MSG_ID_NLGM0004E = "NLGM0004E";

    /** Message ID : NLGM0044E The corresponding data does not exist. Table Name : [@], Key Field Name:  [@], Key Value:  [@] */
    public static final String MSG_ID_NLGM0044E = "NLGM0044E";

    /** Message ID : NLGM0046E The record cannot be updated. Table Name:  [@], Key Field Name:  [@], Key Value:  [@] */
    public static final String MSG_ID_NLGM0046E = "NLGM0046E";

    /** Message ID : NLGM0048E Exclusive control error of @ table. @ is @. */
    public static final String MSG_ID_NLGM0048E = "NLGM0048E";

    // -- Error Message Parameter --------------------
    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Commit Count */
    public static final String MSG_STR_PARAM_03 = "Commit Count(VAR_USER3)";

    /** Message string : Warehouse PI LocTpCd */
    public static final String MSG_STR_WH_PI_LOC_TP_CD = "WH_PI_LOC_TP_CD(VAR_CHAR_CONST)";
    // -- SQL parameter -----------------------------

    // -- Other Internal constants --------------
    /** Debug level for Debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** 0 */
    public static final int ZERO = 0;

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** DATE FORMAT TimeStamp (DB INPUT) */
    public static final String DATE_FORMAT_TS_DB_INPUT = "yyyyMMddHHmmssSSS";

    // -- Mail --------------
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

    /** Mail Group ID From : FROM0003 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group ID To : */
    public static final String MAIL_GROUP_ID_TO = "NLCB0040";

    /** Mail Key From : DP */
    public static final String MAIL_KEY_FROM = "NLC";

    /** Mail Key To : */
    public static final String MAIL_KEY_TO = "To";

    /** Mail Template ID : */
    public static final String MAIL_TEMPLATE_ID = "NLCB0040M001";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail tenplete METH_NM. */
    public static final String MT_METH_NM = "METH_NM";

    /** Mail tenplete KEY_NM. */
    public static final String MT_KEY_NM = "KEY_NM";

    /** Mail Date Format : MM/dd/yyyy */
    public static final String MAIL_DATE_FORMAT_MDY = "MM/dd/yyyy";

    /** Mail Date Format : HH:mm:ss.SSS */
    public static final String MAIL_DATE_FORMAT_HMS = "HH:mm:ss.SSS";

    /** Mail Item : TODAY */
    public static final String MAIL_ITEM_TODAY = "TODAY";

    /** Mail Item : TIME */
    public static final String MAIL_ITEM_TIME = "TIME";

    /** Mail Item : ERR_MSG */
    public static final String MAIL_ITEM_ERR_MSG = "ERR_MSG";

    /** MAIL FORMAT SIZE 15 */
    public static final int MAIL_FORMAT_SIZE_15 = 15;

    /** MAIL FORMAT SIZE 20(RTL_WH) */
    public static final int MAIL_FORMAT_SIZE_20 = 20;

    /** MAIL FORMAT SIZE 10(EFF_FROM_DT,EFF_THRU_DT) */
    public static final int MAIL_FORMAT_SIZE_10 = 10;

    /** Mail  : PI#: */
    public static final String MAIL_KEY_PI_NUM = "PI#:";

    /** Mail  : Reason: */
    public static final String MAIL_KEY_REASON = "Reason:";

    // -- Mail Detail Header --------------
    /** Mail Detail Header Line : PI# */
    public static final String MAIL_DETAIL_HEADER_PI_NUM = "PI#";

    /** Mail Detail Header Line : RWH */
    public static final String MAIL_DETAIL_HEADER_RWH = "RWH";

    /** Mail Detail Header Line : FROM(  Date)  */
    public static final String MAIL_DETAIL_HEADER_FROM = "FROM";

    /** Mail Detail Header Line : THRU */
    public static final String MAIL_DETAIL_HEADER_THRU = "THRU";

    // -- DB COLUMN --------------
    /** DB_COLUMN : PHYS_INVTY_CTRL_PK  */
    public static final String DB_COLUMN_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /** DB_COLUMN : PHYS_INVTY_NUM  */
    public static final String DB_COLUMN_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    /** DB_COLUMN : RTL_WH_CD  */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /** DB_COLUMN : EFF_FROM_DT  */
    public static final String DB_COLUMN_EFF_FROM_DT = "EFF_FROM_DT";

    /** DB_COLUMN : EFF_THRU_DT  */
    public static final String DB_COLUMN_EFF_THRU_DT = "EFF_THRU_DT";

    // -- BIND --------------
    /** BIND : GLBL_CMPY_CD  */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** BIND : PHYS_INVTY_DT  */
    public static final String BIND_PHYS_INVTY_DT = "physInvtyDt";

    /** BIND : PHYS_INVTY_STS_CD  */
    public static final String BIND_PHYS_INVTY_STS_CD = "physInvtyStsCd";

    /** BIND : DS_COND_CONST_GRP_ID  */
    public static final String BIND_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /** BIND : LOC_TP_CD  */
    public static final String BIND_LOC_TP_CD = "whLocTpCdList";

    // -- VAR CHAR CONST --------------
    /** WH_PI_LOC_TP_CD  */
    public static final String VAR_CHAR_CONST_WH_PI_LOC_TP_CD = "WH_PI_LOC_TP_CD";

    // -- Table ID --------------
    /** Table ID : PHYS_INVTY_CTRL */
    public static final String TBL_ID_PHYS_INVTY_CTRL = "PHYS_INVTY_CTRL";

    // -- SQL Map StatementID --------------
    /** SQL Map StatementID : getPiList */
    public static final String SQL_STID_GET_PI_LIST = "getPiList";
}
