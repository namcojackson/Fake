package business.servlet.NMAL7440.constant;

/** 
 *<pre>
 * NMAL7440Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7440Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7440";

    /** Max Input Data Count(CSV) */
    public static final int MAX_INPUT_DATA_CNT = 99;

    /** Max Value Item Length */
    public static final int MAX_VALUE_ITEM_LENGTH = 50;

    /** Comma */
    public static final String COMMA = ",";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    // Popup Parameter No
    // --------------------------------
    /** 0.Price Group ID */
    public static final int FILTER_PRC_GRP_PK = 0;

    /** 1.Price Group Name */
    public static final int FILTER_PRC_GRP_NM = 1;

    /** 2.Price Group Type Code */
    public static final int FILTER_PRC_GRP_TP_CD = 2;

    /** 3.Target Context Code */
    public static final int FILTER_PRC_GRP_TRGT_TP_CD = 3;

    /** 4.Target Attribute Code */
    public static final int FILTER_PRC_GRP_TRGT_ATTRB_CD = 4;

    /** 5.Target From */
    public static final int FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TF = 5;

    /** 6.Target To */
    public static final int FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TT = 6;

    /** 7.Precedence# */
    public static final int FILTER_PRC_GRP_PRCD_NUM = 7;

    /** 8.Date From(FROM) */
    public static final int FILTER_EFF_FROM_DT_H1 = 8;

    /** 9.Date From(TO) */
    public static final int FILTER_EFF_FROM_DT_H2 = 9;

    /** 10.Date To(FROM) */
    public static final int FILTER_EFF_THRU_DT_H1 = 10;

    /** 11.Date To(TO) */
    public static final int FILTER_EFF_THRU_DT_H2 = 11;

    /** 12.Account Name(Description) */
    public static final int DS_ACCT_NM = 12;

    // --------------------------------
    // Message ID
    // --------------------------------
    /**  @ exceed the maximum length.  */
    public static final String NMAM8579E = "NMAM8579E";

    /**  The number of data for @ exceeded the maximum[@].  */
    public static final String NMAM8696E = "NMAM8696E";

    /**  [@] field is mandatory.  */
    public static final String NMAM0836E = "NMAM0836E";
}
