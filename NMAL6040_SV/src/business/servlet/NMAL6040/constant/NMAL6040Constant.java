/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6040.constant;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 *</pre>
 */
public interface NMAL6040Constant {

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
     * Business button Search
     */
    String[] BTN_SEARCH = {"Search_merchandise", "Search" };

    /**
     * Screen ID
     */
    String SCREEN_ID = "NMAL6040Scrn00";

    /**
     * Business ID
     */
    String BUSINESS_ID = "NMAL6040";

    /**
     * If Detail column of Merchandise Code link has Selected
     */
    Integer SELECT_MDSE_CD = 6;

    /**
     * If Detail column of Merchandise Group Code link has Selected
     */
    Integer SELECT_MDSE_GRP_CD = 5;

    /**
     * If Detail column of Product Level 2 link has Selected
     */
    Integer SELECT_PROD_LVL_2_CD = 2;

    /**
     * If Detail column of Product Level 3 link has Selected
     */
    Integer SELECT_PROD_LVL_3_CD = 3;

    /**
     * If Detail column of Product Level 4 link has Selected
     */
    Integer SELECT_PROD_LVL_4_CD = 4;

    /**
     * If Detail column of Product Line link has Selected
     */
    Integer SELECT_PROD_LINE = 1;

    /**
     * If Detail column of Product Line Group link has Selected
     */
    Integer SELECT_PROD_LINE_GRP = 0;
}
