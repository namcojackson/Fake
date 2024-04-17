/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0270.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270Constant {

    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSBL0270";

    /**
     * Screen Id
     */
    public static final String SCR_ID_00 = "NSBL0270Scrn00";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Button name attribute [InsertRow]
     */
    public static final String BTN_INSERT_ROW = "InsertRow";

    /**
     * Button name attribute [DeleteRow]
     */
    public static final String BTN_DELETE_ROW = "DeleteRow";

    /**
     * Button name attribute [Upload]
     */
    public static final String BTN_UPLOAD = "Upload";

    /**
     * Function Id: Inquiry
     */
    public static final String FUNC_ID_INQ = "NSBL0270T010";

    /**
     * Function Id: Update
     */
    public static final String FUNC_ID_UPD = "NSBL0270T020";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * @ cannot be added because it is exceeding the maximum number of row [@].
     */
    public static final String NSAM0320E = "NSAM0320E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * Time is invalid. Please use a valid format, [hh:mm:ss or hh:mm]
     */
    public static final String NSAM0466E = "NSAM0466E";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    /** file extension */
    public static final String[] FILE_EXTENSION = {"csv", "txt", "xls", "xlsx"};

    /** HHmmss:length */
    public static final int LENGTH_HHMMSS = 6;

    /** HHmm:length */
    public static final int LENGTH_HHMM = 4;

    /** HHmm:Start Point */
    public static final int POINT_HHMM_S = 4;

    /** HHmmss:Start Point */
    public static final int POINT_HHMMSS_S = 6;

    /** HHmmss:End Point */
    public static final int POINT_HHMMSS_E = 8;

    /** HH:mm:End Point */
    public static final int POINT_HH_MM_E = 5;

    /** SS:value */
    public static final String VALUE_SS = "00";

    /** COLON */
    public static final String COLON = ":";
}
