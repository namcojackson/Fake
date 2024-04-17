/*
 * <Pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </Pre>
 */
package business.servlet.NLBL3030.constant;

/**
 * <pre>
 * Message Entry PopUo
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/16   Fujitsu         C.Naito         Create          (From NATL6050)
 * </pre>
 */
public interface NLBL3030Constant {
    /**
     * Process Mode:Entry
     */
    String EDIT_MODE = "E";

    /**
     * Process Mode:Inquiry
     */
    String INQUIRY_MODE = "I";

    /**
     * Process Mode:Multi Message Entry
     */
    String MULTI_INQUIRY_MODE = "L";

    /**
     * Process Mode:Init Error
     */
    String INIT_ERR = "IE";

    /**
     * Common button 1
     */
    String[] BTN_CMN_BTN_1 = {"btn1", "", "" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_BTN_2 = {"btn2", "", "" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_BTN_3 = {"btn3", "", "" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_BTN_4 = {"btn4", "", "" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_BTN_5 = {"btn5", "", "" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_BTN_6 = {"btn6", "", "" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_BTN_7 = {"btn7", "", "" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_BTN_9 = {"btn9", "", "" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Business button OK
     */
    String[] BTN_OK = {"OK_Message", "OK" };

    /**
     * Business button Check
     */
    String[] BTN_CHK = {"Chk_Message", "Check" };

    /**
     * The maximum number of line breaks has been exceeded. Please
     * reduce the number of line breaks to less than [@].
     */
    String NATM0006E = "NATM0006E";

    /**
     * [@] field exceeded maximum value.
     */
    String ZZM9001E = "ZZM9001E";

    /**
     * Could not get the initial parameter.
     */
    String NWCM0070E = "NWCM0070E";

}
