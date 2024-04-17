/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250.constant;

/**
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 09/14/2016   CSAI            Y.Imazu         Update          QC#13187
 * 10/20/2016   CSAI            Y.Imazu         Update          QC#14081
 * 01/16/2017   CITS            T.Kikuhara      Update          QC#16256
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 * 05/15/2023   Hitachi         S.Dong          Update          QC#61398
 *</pre>
 */
public class NLCL0250Constant {

    /** Screen ID */
    public static final String SCREEN_ID = "NLCL0250Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NLCL0250";

    /** Read User*/
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** Function Update */
    public static final String FUNCTION_UPDATE = BUSINESS_ID + "T020";

    /***************************************************
     * Function Button
     ***************************************************/

    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };
    // START 2023/05/15 S.Dong [QC#61398, ADD]
    /** Button 3rd Party Inv Material */
    public static final String BTN_3RD_PARTY_INV_MATERIAL = "3rd Party Inv Material";
    // END 2023/05/15 S.Dong [QC#61398, ADD]
    /***************************************************
     * Event Name
     ***************************************************/

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: Product_Line_Group_Link */
    public static final String EVENT_NM_PROD_LINE_GRP_LINK = "Product_Line_Group_Link";

    /** Event Name: Product_Line_Level_2_Link */
    public static final String EVENT_NM_PROD_LINE_LVL2_LINK = "Product_Line_Level_2_Link";

    /** Event Name: Product_Line_Level_3_Link */
    public static final String EVENT_NM_PROD_LINE_LVL3_LINK = "Product_Line_Level_3_Link";

    /** Event Name: Product_Line_Level_4_Link */
    public static final String EVENT_NM_PROD_LINE_LVL4_LINK = "Product_Line_Level_4_Link";

    /** Event Name: Product_Line_Link */
    public static final String EVENT_NM_PROD_LINE_LINK = "Product_Line_Link";

    /** Event Name: OpenWin_LocInfoRtlWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLWH = "OpenWin_LocInfoRtlWh";

    /** Event Name: OpenWin_LocInfoRtlSWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLSWH = "OpenWin_LocInfoRtlSWh";

    /** Event Name: OpenWin_Serial */
    public static final String EVENT_NM_OPENWIN_SERIAL = "OpenWin_Serial";

    /** Event Name: OpenWin_Config */
    public static final String EVENT_NM_OPENWIN_CONFIG = "OpenWin_Config";

    // START 2017/12/18 S.Katsuma [QC#22469,ADD]
    /** Event Name: Rtrn_Ctrl_Tp_Link */
    public static final String EVENT_NM_RTRN_CTRL_TP_LINK = "Rtrn_Ctrl_Tp_Link";
    // END 2017/12/18 S.Katsuma [QC#22469,ADD]

    /***************************************************
     * Message
     ***************************************************/

    /** Entered date is later (future date) than today. */
    public static final String NLAM1087E = "NLAM1087E";

    /** Please enter a value for either @ or @. */
    public static final String NLAM1292E = "NLAM1292E";

    /** The chronological sequence between the dates in the "@" field is wrong. */
    public static final String NLCM0115E = "NLCM0115E";

    /** Please set at least one search criteria. */
    public static final String NLZM2276E = "NLZM2276E";

    /** Please select @ when @ is entered. */
    public static final String NMAM8179E = "NMAM8179E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /***************************************************
     * Constant
     ***************************************************/

    /** Header Table ID (H) */
    public static final String HEADER_TABLE_ID = "H";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Detail Search */
    public static final String DETAIL_SEARCH = "2";

    /** NMAL6800 Parameter : Mode Code (All) */
    public static final String ITEM_SEARCH_AL = "AL";
}
