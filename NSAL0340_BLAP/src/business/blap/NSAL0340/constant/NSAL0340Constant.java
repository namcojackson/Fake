/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0340.constant;

import java.util.HashMap;
import java.util.Map;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/20   Hitachi         T.Tomita        Update          N/A
 *</pre>
 */
public class NSAL0340Constant {

    /** System Error : @ */
    public static final String NSAM0219E = "NSAM0219E";

    /** The previous month of the recover must be skip. */
    public static final String NSAM0220E = "NSAM0220E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Process Name (Submit) */
    public static final String SUBMIT = "Submit";

    /** Message Parameter (No Input Parameter) */
    public static final String NO_INPUT = "No input parameter found";

    /** Message Parameter (Invalid Parameter) */
    public static final String INVALID_PARAM = "Invalid parameter found";

    /** Message Parameter (No Skip Recover Type) */
    public static final String NO_SKIP_RECOV_TP = "Skip Recover Type is not correctly setup";

    /** Message Parameter (No Header Data) */
    public static final String NO_HDR_DATA = "The DS Contract Detail pk is not valid";

    /** December Index Number */
    public static final int DEC_INDEX_NUM = 11;

    /** Month List Map */
    public static final Map<String, String> MONTH_LIST_MAP = new HashMap<String, String>() {

        private static final long serialVersionUID = 1L;

        {
            put("01", "January");
            put("02", "February");
            put("03", "March");
            put("04", "April");
            put("05", "May");
            put("06", "June");
            put("07", "July");
            put("08", "August");
            put("09", "September");
            put("10", "October");
            put("11", "November");
            put("12", "December");
        }
    };

    // START 2015/10/20 T.Tomita [N/A, ADD]
    /** Contract Billing Schedule API Call Mode : Skip Month */
    public static final String SKIP_MONTH = "03";

    /**
     * Error Msg : An error occurred in API. <APIID: [@], Error Code:
     * [@], Data Key: [@]>
     */
    public static final String NSAM0003E = "NSAM0003E";

    /** XX_MODE_CD: reference mode */
    public static final String REF_MODE = "0";

    /** XX_MODE_CD: edit mode */
    public static final String EDIT_MODE = "1";
    // END 2015/10/20 T.Tomita [N/A, ADD]
}
