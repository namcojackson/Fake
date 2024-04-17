/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0060.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura         Create          N/A
 * 2022/03/31   CITS            A.Cullano          Update          QC#59828
 * 2022/10/09   Hitachi         S.Nakatani         Update          QC#56656
 *</pre>
 */
public class NWCL0060Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NWCL0060";

    /** Screen ID */
    public static final String SCREEN_ID = "NWCL0060Scrn00";

    /** Mail Template */
    public static final String ML_TMPL = "NWCL0050M001";

    /** Temporary Comments */
    public static final String TMP_CMNT = "TempComments";

    /** Comma */
    public static final String COMMA = ",";

    /** Windows Line Code */
    public static final String WIN_CRLF = "\r\n";

    // START 2018/09/19 [QC#19578, ADD]
    /** Mail Template Key (BILL_TO_DS_ACCT_NM) */
    public static final String ML_TMPL_KEY_BILL_TO_DS_ACCT_NM = "BillToDsAcctNm";
    // END   2018/09/19 [QC#19578, ADD]

    // START 2022/03/31 A.Cullano [QC#59828, ADD]
    /** var_char_const : STGY_DEF_CLTR_EML */
    public static final String STGY_DEF_CLTR_EML = "STGY_DEF_CLTR_EML";

    /** var_char_const : MAIL_GROUP_FROM */
    public static final String MAIL_GROUP_FROM = "NWCB0180FROM";

    /** var_char_const : NWCL0060_DEF_FROM_EML_ADDR */
    public static final String NWCL0060_DEF_FROM_EML_ADDR = "NWCL0060_DEF_FROM_EML_ADDR";
    // END 2022/03/31 A.Cullano [QC#59828, ADD]

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Not found record. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
}
