/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0020.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi         T.Yonekura      Create          N/A
 * 2013/08/27   Hitachi         Y.Igarashi      Update          QC1851
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 * 2015/12/08   Hitachi         K.Kasai         Update          QC1644
 * 2016/05/12   Hitachi         M.Gotou         Update          QC7856
 * 2018/06/01   Fujitsu         T.Murai         Update          QC25167
 * 2019/01/16   Hitachi         E.Kameishi      Update          QC#29891
 *</pre>
 */
public class NSAL0020Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0020";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = "NSAL0020Scrn00";

    /**
     * Common button 1
     */
    public static final String[] SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Search button
     */
    public static final String[] SEARCH = {"Search", "Search", "Search" };

    // START 2016/05/12 M.Gotou [QC7856, ADD]
    /**
     * New Machine button
     */
    public static final String[] NEW_MACHINE = {"NewMachine", "NewMachine", "New Machine" };
    // END 2016/05/12 M.Gotou [QC7856, ADD]

    /**
     * Mach Mst Mnt button
     */
    public static final String[] MACHINE_MASTER_MAINTENANCE = {"Disp_Machine_Master_Maintenance", "Disp_Machine_Master_Maintenance", "SvcCall" };
    // ADD START 2015/12/08 K.Kasai [Unit Test #71]
    /**
     * Meter button
     */
    public static final String[] METER_ENTRY = {"OpenWin_NSAL0150", "OpenWin_NSAL0150", "Meter" };
    // ADD END 2015/12/08 K.Kasai [Unit Test #71]
    /**
     * Config Mnt button
     */
    public static final String[] CONFIG_MAINTENANCE = {"Disp_Configuration_Maintenance", "Disp_Configuration_Maintenance", "Config Mnt" };

    /**
     * Contract Mnt button
     */
    public static final String[] CONTRACT_MAINTENANCE = {"Disp_Contract_Maintenance", "Disp_Contract_Maintenance", "Contract Mnt" };

    /**
     * Repair Hist button
     */
    public static final String[] REPAIR_HISTORY = {"OpenWin_Repair_History", "OpenWin_Repair_History", "SvcCall" };

    /**
     * Dispatch Sum button
     */
    public static final String[] DISPATCH_SUMMARY = {"OpenWin_Dispatch_Summary", "OpenWin_Dispatch_Summary", "Dispatch Sum" };

    /**
     * Invoice button
     */
    public static final String[] INVOICE = {"Disp_Invoice_Maintenance", "Disp_Invoice_Maintenance", "Invoice" };

    /**
     * Benchmark button
     */
    public static final String[] BENCHMARK = {"OpenWin_Benchmark", "OpenWin_Benchmark", "Benchmark" };

    /**
     * Version Hist button
     */
    public static final String[] VERSION_HISTORY = {"OpenWin_Version_History", "OpenWin_Version_History", "Version Hist" };

    /**
     * Max line count per page
     */
    public static final int MAX_LINE_COUNT = 20;

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * Button is inacitve
     */
    public static final int BTN_INACTIVE = 0;

    /**
     * Machine Master Maintenance Screen
     */
    public static final String NSAL0010 = "NSAL0010";

    /**
     * Configuration Maintenance Screen
     */
    public static final String NSAL0040 = "NSAL0040";

    // ADD START 2015/12/08 K.Kasai [QC1644]
    /**
     * Meter Entry Screen
     */
    public static final String NSAL0150 = "NSAL0150";
    // ADD END 2015/12/08 K.Kasai [QC1644]

    /**
     * Contract Maintenance Screen
     */
    public static final String NSAL0080 = "NSAL0080";

    /**
     * Contract Popup
     */
    public static final String NSAL0110 = "NSAL0110";

    /**
     * Dispatch Summary Popup
     */
    public static final String NSAL0140 = "NSAL0140";

    /**
     * Repair History by Serial# Popup
     */
    public static final String NSBL0060 = "NSBL0060";

    /**
     * FUNC_ID_T010
     */
    public static final String FUNC_ID_T010 = "NSAL0020T010";

    // START 2016/05/12 M.Gotou [QC7856, ADD]
    /**
     * FUNC_ID_T020
     */
    public static final String FUNC_ID_T020 = "NSAL0020T020";
    // END 2016/05/12 M.Gotou [QC7856, ADD]

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * The process cannot be executed because the "Machine Type" is
     * "Accessory".
     */
    public static final String NSAM0016E = "NSAM0016E";

    // START 2018/06/01 T.Murai [QC25167, ADD]
    /**
     * Please set at least one search criteria.
     */
    public static final String NSAM0017E = "NSAM0017E";
    // END 2018/06/01 T.Murai [QC25167, ADD]

    // START 2019/01/16 E.Kameishi [QC#29891,ADD]
    /**
     * The format of [@] is incorrect.
     */
    public static final String NSAM0206E = "NSAM0206E";
    // END 2019/01/16 E.Kameishi [QC#29891,ADD]

    /**
     * Search mode: Search all machine master items
     */
    public static final String MODE_ALL_SERIALIZED_ITEMS = "1";

    /**
     * Evevnt Name: Layer 1
     */
    public static final String LAYER_1 = "Layer1";

    /**
     * Evevnt Name: Layer 2
     */
    public static final String LAYER_2 = "Layer2";

    /**
     * Evevnt Name: Layer 3
     */
    public static final String LAYER_3 = "Layer3";

    /**
     * Evevnt Name: Layer 4
     */
    public static final String LAYER_4 = "Layer4";

    /**
     * Evevnt Name: Layer 5
     */
    public static final String LAYER_5 = "Layer5";

    /**
     * Argument Disp_Contract_Maintenance
     */
    public static final int ARGS_CONTRACT_MNT_COUNT = 3;

    /**
     * Screen transition parameter
     */
    public static final String SCRN_EVENT_EDIT = "01";
}
