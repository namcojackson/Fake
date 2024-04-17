/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1600.constant;

/**
 *<pre>
 * NWAL1600Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/27   Fujitsu         T.Murai         Update          S21_NA#4618
 * 2017/11/01   Fujitsu         K.Ishizuka      Update          S21_NA#20146
 *</pre>
 */
public class NWAL1600Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** "Sales Rep" could not be obtained. */
    public static final String NWZM0642E = "NWZM0642E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Salesrep should include Salesrep which is in SALES. */
    public static final String NWAM0661E = "NWAM0661E";

    /** "Writer" has not been selected. */
    public static final String NWAM0679E = "NWAM0679E";

    /** Please correct the error fields, and then try again. */
    public static final String NWAM0010E = "NWAM0010E";

    /** Please select only one "Writer".*/
    public static final String NWAM0853E = "NWAM0853E"; //S21_NA#4618 Add

    /** The maximum number of data has been exceeded.[@]*/
    public static final String NWAM0920E = "NWAM0920E"; //S21_NA#16750 Add

    // --------------------------------
    // Valiable
    // --------------------------------
    /** Mode Type : Reference. */
    public static final String MODE_REFERENCE = "0";

    /** Mode Type : Edit All. */
    public static final String MODE_EDIT_ALL = "1";

    /** Mode Type : Edit Quote Only. */
    public static final String MODE_QUOTE_ONLY = "2";

    /** Request Type : New. */
    public static final String REQ_NEW = "1";

    /** Request Type : Modify. */
    public static final String REQ_MOD = "2";

    /** Request Type : Delete. */
    public static final String REQ_DEL = "3";

    /** Add Row for Quote */
    public static final String BTN_ADD_QUOTE = "AddRowQuote";

    /** Delete Row for Quote */
    public static final String BTN_DEL_QUOTE = "DeleteRowQuote";

    /** Add Row for Non Quote */
    public static final String BTN_ADD_NON_QUOTE = "AddRowNonQuote";

    /** Delete Row for Non Quote */
    public static final String BTN_DEL_NON_QUOTE = "DeleteRowNonQuote";

    /** Row Number for SSM. */
    public static final String ROW_NUM = "1";

    /** Sales Rep Max number */
    public static final int SLS_REP_MAX_NUM = 10;
    
    /** Colon */ //S21_NA ADD QC#20146
    public static final String COLON = ":";

    // --------------------------------
    // Suffix
    // --------------------------------
    /** Quote Suffix "A" */
    public static final String QUOTE_SFX = "A";

    /** Non Quote Suffix "B" */
    public static final String NONQUOTE_SFX = "B";

    /** Delete Suffix "C" */
    public static final String DEL_SFX = "C";
}
