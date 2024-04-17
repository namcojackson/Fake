/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.servlet.NLBL3100.constant;

/**
 *<pre>
 * Reman Labor/Expense Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public interface NLBL3100Constant {

    /**
     * SCREEN_ID
     */
    String SCREEN_ID = "NLBL3100Scrn00";

    /**
     * BUSINESS_ID
     */
    String BUSINESS_ID = "NLBL3100";

    /**
     * FUNCID_REFERENCE
     */
    String FUNCID_REFERENCE = "NLBL3100T010";

    /**
     * FUNCID_INSERT_UPDATE
     */
    String FUNCID_INSERT_UPDATE = "NLBL3100T020";

    /**
     * FUNCTION_CD_SEARCH
     */
    String FUNCTION_CD_SEARCH = "20";

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    // =================================================
    // Display character string for message
    // =================================================
    /** Warehouse Code */
    public static final String NAME_FOR_MESSAGE_RTL_WH_CD = "Warehouse Code";

    /** Warehouse Name */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM = "Warehouse Name";

    /** Coordinator Code */
    public static final String NAME_FOR_MESSAGE_SCHD_COORD_PSN_CD = "Coordinator Code";

    /** Coordinator Name */
    public static final String NAME_FOR_MESSAGE_SCHD_COORD_PSN_NM = "Coordinator Name";

    /** Manager Code */
    public static final String NAME_FOR_MESSAGE_MGR_PSN_CD = "Manager Code";

    /** Manager Name */
    public static final String NAME_FOR_MESSAGE_MGR_PSN_NM = "Manager Name";

    /** Supervisor Code */
    public static final String NAME_FOR_MESSAGE_SUPV_PSN_CD = "Supervisor Code";

    /** Supervisor Name */
    public static final String NAME_FOR_MESSAGE_SUPV_PSN_NM = "Supervisor Name";

    /** Carrier Code */
    public static final String NAME_FOR_MESSAGE_CARR_CD = "Carrier Code";

    /** Carrier Name */
    public static final String NAME_FOR_MESSAGE_CARR_NM = "Carrier Name";

    /** State */
    public static final String NAME_FOR_MESSAGE_ST = "State";

    /** Effective From Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Effective From Date";

    /** Effective Thru Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "Effective Thru Date";

}
