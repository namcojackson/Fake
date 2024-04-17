/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140.constant;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 * 2017/10/05   Fujitsu         H.Sugawara      Update          QC#19922
 *</pre>
 */
public class NWCL0140Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NWCL0140";

    /**
     * Eligible Account and Bill To area Table
     */
    public static final String TABLE_A = "A";

    /**
     * Exclusion/Inclusion Order Category area Table
     */
    public static final String TABLE_B = "B";

    /**
     * Reference function code
     */
    public static final String FUNCTION_UPDATE = "NWCL0140T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NWCL0140Scrn00";

    // =================================================
    // Business Button Name
    // =================================================
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "Add_NewLine", "" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * ADD_LINE_ELIG_ACCT
     */
    public static final String ADD_LINE_ELIG_ACCT = "AddLineEligAcct";

    /**
     * DELETE_LINE_ELIG_ACCT
     */
    public static final String DELETE_LINE_ELIG_ACCT = "DeleteLineEligAcct";

    /**
     * ADD_LINE_ELIG_ORD_CATG
     */
    public static final String ADD_LINE_ELIG_ORD_CATG = "AddLineEligOrdCatg";

    /**
     * DELETE_LINE_ELIG_ORD_CATG
     */
    public static final String DELETE_LINE_ELIG_ORD_CATG = "DeleteLineEligOrdCatg";

    // =================================================
    // Message Code
    // =================================================
    /**
     * No Details are selected.
     */
    public static final String NWCM0127E = "NWCM0127E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // =================================================
    // setNameForMessage
    // ================================================
    /**
     * ACCT_NUM
     */
    public static final String ACCT_NUM = "Account Number";

    /**
     * LOC_NUM
     */
    // Mod Start 2017/10/05 QC#19922
    //public static final String LOC_NUM = "Location Number";
    public static final String LOC_NUM = "Bill To Code";
    // Mod End 2017/10/05 QC#19922

    /**
     * INV_THRES_HOLD
     */
    public static final String INV_THRES_HOLD = "Invoiced Threshold";

    /**
     * ORD_CATEGORY
     */
    public static final String ORD_CATEGORY = "Order Category";

    /**
     * REASON
     */
    public static final String REASON = "Reason";

    /**
     * INCLUDE
     */
    public static final String INCLUDE = "Include";

    // =================================================
    // Event Name
    // =================================================
    /**
     * CMN_Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NWCL0140_INIT = "NWCL0140_INIT";

    /**
     * Event Name : DeleteLineEligAcct
     */
    public static final String EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ACCT = "NWCL0140Scrn00_DeleteLineEligAcct";

    /**
     * Event Name : DeleteLineEligOrdCatg
     */
    public static final String EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ORD_CATG = "NWCL0140Scrn00_DeleteLineEligOrdCatg";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NWCL0140_CMN_RESET = "NWCL0140Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NWCL0140_CMN_SUBMIT = "NWCL0140Scrn00_CMN_Submit";

    /**
     * Event Name : AddLineEligAcct
     */
    public static final String EVENT_NM_NWCL0140_ADD_LINE_ELIG_ACCT = "NWCL0140Scrn00_AddLineEligAcct";

    /**
     * Event Name : AddLineEligOrdCatg
     */
    public static final String EVENT_NM_NWCL0140_ADD_LINE_ELIG_ORD_CATG = "NWCL0140Scrn00_AddLineEligOrdCatg";

    /**
     * Event Name : PageJumpEL
     */
    public static final String EVENT_NM_NWCL0140_PAGE_JUMP_EL = "NWCL0140Scrn00_PageJumpEL";

    /**
     * Event Name : PageNextEL
     */
    public static final String EVENT_NM_NWCL0140_PAGE_NEXT_EL = "NWCL0140Scrn00_PageNextEL";

    /**
     * Event Name : PagePrevEL
     */
    public static final String EVENT_NM_NWCL0140_PAGE_PREV_EL = "NWCL0140Scrn00_PagePrevEL";

    /**
     * Event Name : SearchEligAcct
     */
    public static final String EVENT_NM_NWCL0140_SEARCH_ELIG_ACCT = "NWCL0140Scrn00_SearchEligAcct";

    /**
     * Event Name : PageJumpEX
     */
    public static final String EVENT_NM_NWCL0140_PAGE_JUMP_EX = "NWCL0140Scrn00_PageJumpEX";

    /**
     * Event Name : PageNextEX
     */
    public static final String EVENT_NM_NWCL0140_PAGE_NEXT_EX = "NWCL0140Scrn00_PageNextEX";

    /**
     * Event Name : PagePrevEX
     */
    public static final String EVENT_NM_NWCL0140_PAGE_PREV_EX = "NWCL0140Scrn00_PagePrevEX";

    /**
     * Event Name : SearchEligOrdCatg
     */
    public static final String EVENT_NM_NWCL0140_SEARCH_ELIG_ORD_CATG = "NWCL0140Scrn00_SearchEligOrdCatg";

    /**
     * Event Name : OpenWin_ReasonH
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_REASON_H = "NWCL0140Scrn00_OpenWin_ReasonH";

    /**
     * Event Name : OpenWin_ReasonD
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_REASON_D = "NWCL0140Scrn00_OpenWin_ReasonD";

    /**
     * Event Name : OpenWin_AcctH
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_ACCT_H = "NWCL0140Scrn00_OpenWin_AcctH";

    /**
     * Event Name : OpenWin_AcctD
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_ACCT_D = "NWCL0140Scrn00_OpenWin_AcctD";

    /**
     * Event Name : OpenWin_LocH
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_LOC_H = "NWCL0140Scrn00_OpenWin_LocH";

    /**
     * Event Name : OpenWin_LocD
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_LOC_D = "NWCL0140Scrn00_OpenWin_LocD";

    /**
     * Event Name : OpenWin_OrderCategoryH
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_H = "NWCL0140Scrn00_OpenWin_OrderCategoryH";

    /**
     * Event Name : OpenWin_OrderCategoryD
     */
    public static final String EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_D = "NWCL0140Scrn00_OpenWin_OrderCategoryD";

    // =================================================
    // Other
    // =================================================
    /** Percent */
    public static final String PERCENT = "%";

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 13 */
    public static final int IDX_13 = 13;

    /** Index Number 15 */
    public static final int IDX_15 = 15;

    /** Index Number 50 */
    public static final int IDX_50 = 50;
}
