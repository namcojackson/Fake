/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0110.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110Constant {

    /*****************************************************************
     * Bisiness ID
     ****************************************************************/

    /** ZZPL0110 */
    public static final String BUSINESS_ID = "ZZPL0110";

    /*****************************************************************
     * screen ID name
     ****************************************************************/

    /** ZZPL0110Scrn00 */
    public static final String SCREENID_SCRN00 = "ZZPL0110Scrn00";

    /*****************************************************************
     * Function Code
     ****************************************************************/

    /** Search */
    public static final String FUNCTION_CD_SEARCH = "20";

    /** Update */
    public static final String FUNCTION_CD_UPDATE = "40";

    /*****************************************************************
     * button property
     ****************************************************************/

    /** common button1 */
    public static final String[] BTN_CMN_BTN1 = {"btn1", "", "Save" };

    /** common button2 */
    public static final String[] BTN_CMN_BTN2 = {"btn2", "CMN_Submit", "Submit" };

    /** common button3 */
    public static final String[] BTN_CMN_BTN3 = {"btn3", "", "Apply" };

    /** common button4 */
    public static final String[] BTN_CMN_BTN4 = {"btn4", "", "Approve" };

    /** common button5 */
    public static final String[] BTN_CMN_BTN5 = {"btn5", "", "Reject" };

    /** common button6 */
    public static final String[] BTN_CMN_BTN6 = {"btn6", "", "Download" };

    /** common button7 */
    public static final String[] BTN_CMN_BTN7 = {"btn7", "", "Delete" };

    /** common button8 */
    public static final String[] BTN_CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    /** common button9 */
    public static final String[] BTN_CMN_BTN9 = {"btn9", "", "Reset" };

    /** common button10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** prev button */
    public static final String[] BTN_PREV = {"Prev", "Prev" };

    /** next button */
    public static final String[] BTN_NEXT = {"Next", "Next" };

    /*****************************************************************
     * report job status
     ****************************************************************/

    /** FAIL */
    public static final String RPT_JOB_STS_FAIL = "FAIL";

    /** ALL */
    public static final String RPT_JOB_STS_ALL = "ALL";

    /** CANCELED */
    public static final String RPT_JOB_STS_CANCELED = "CANCELED";

    /** SUSPENDED */
    public static final String RPT_JOB_STS_SUSPENDED = "SUSPENDED";
    
    /** user changeable statuses */
    public static final String[] RPT_JOB_STS_CHANGEABLE = {RPT_JOB_STS_FAIL, RPT_JOB_STS_CANCELED, RPT_JOB_STS_SUSPENDED, };

    /*****************************************************************
     * Date Format
     ****************************************************************/

    /** System Date Format */
    public static final String SYSTEM_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Display Date Format */
    public static final String DISPLAY_DATE_FORMAT = "yyyy/MM/dd HH:mm";
}
