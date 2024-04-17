/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1260.constant;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1260Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** SCRN_ID : NSAL1260Scrn00 */
    public static final String SCREEN_ID = "NSAL1260Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1260";

    /** Function code : Search */
    public static final String FUNC_SEARCH = "20";

    /** file extension */
    public static final String FILE_EXTENSION = "csv";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    /** Function Button : Upload */
    public static final String BTN_UPLOAD = "Upload";

    /** Function Button : Add_Machines */
    public static final String BTN_ADD_MACHINE = "Add_Machines";

    /** Default fraction digit */
    public static final int DEF_FRAC_DIGIT_NUM = 2;
}
