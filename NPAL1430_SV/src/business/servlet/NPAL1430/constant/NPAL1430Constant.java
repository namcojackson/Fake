/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430.constant;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 *</pre>
 */

public class NPAL1430Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1430";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1430Scrn00";

    /**
     * Function Inquiry
     */
    public static final String FUNC_INQUIRY = "NPAL1430T010";

    /**
     * Function Edit
     */
    public static final String FUNC_EDIT = "NPAL1430T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

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
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

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
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Search
     */
    public static final String BTN_DELETE_LINE = "DeleteLine";

    /**
     * Business button Search
     */
    public static final String BTN_ADD_LINE = "AddLine";

    /**
     * Business button OpenWin_Item
     */
    public static final String BTN_SEARCH_LINE_ITEM_NAME = "OpenWin_Item";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_MKT_MDL = "MKT_MDL";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_MKT_MDL = "MKT_MDL_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_MKT_MDL = "MKT_MDL_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_MKT_MDL = "MKT_MDL_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_MKT_MDL = "Model Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_MKT_MDL = "Model Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_MKT_MDL = "Model Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_MKT_MDL = "Model Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_MKT_MDL = "Model Name";

    /**
     * Code Column for NMAL6800 Item Master Search Popup : Mode Code =
     * 10
     */
    public static final String MODE_CODE_10 = "10";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    public static final String EVENT_NM_CMN_DELETE_LINE = "DeleteLine";

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZZM9007E:The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * NPAM1351E: It cannot be processed since there is no detail.
     */
    public static final String NPAM1351E = "NPAM1351E";

    /**
     * NPAM1234E: Enter a positive integral number for [@].
     */
    public static final String NPAM1234E = "NPAM1234E";

    /**
     * NPAM1216E: The details to the process are not selected.
     */
    public static final String NPAM1216E = "NPAM1216E";

    /**
     * NPAM0087E:The data specified already exists.
     */
    public static final String NPAM0087E = "NPAM0087E";
}
