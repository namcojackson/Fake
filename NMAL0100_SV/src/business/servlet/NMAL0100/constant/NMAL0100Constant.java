package business.servlet.NMAL0100.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 * 2015/06/19   Fujitsu         M.Yamada        Update          Spec0601
 * 2020/07/06   CITS            M.Furugoori     Update          QC#55448
 * 2022/10/28   Hitachi         S.Nakatani      Update          QC#60399
 *</pre>
 */
public class NMAL0100Constant {

    /**
     * Business ID -- NMAL0100
     */
    public static final String BUSINESS_ID = "NMAL0100";

    // START 2020/07/10 [QC#55448,ADD]
    /**
     * Business ID -- NMAL0110
     */
    public static final String BUSINESS_ID_NMAL0110 = "NMAL0110";
    // END 2020/07/10 [QC#55448,ADD]

    /**
     * Screen ID.
     */
    public static final String SCREEN_ID = "NMAL0100Scrn00";

    /**
     * ERROR_CODE_E -- E
     */
    public static final String ERROR_CODE_E = "E";

    /**
     * FUNC_CD_SRCH -- 20
     */
    public static final String FUNC_CD_SRCH = "20";

    /** FUNC_CD_UPDT -- 40 */
    public static final String FUNC_CD_UPDT = "40";

    /**
     * CMN_CLOSE -- CMN_Close
     */
    public static final String CMN_CLOSE = "CMN_Close";

    /**
     * CMN_BTN1 -- Save
     */
    public static final String[] CMN_BTN1 = {"btn1", "", "Save" };

    /**
     * CMN_BTN2 -- Submit
     */
    public static final String[] CMN_BTN2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * CMN_BTN3 -- Apply
     */
    public static final String[] CMN_BTN3 = {"btn3", "", "Apply" };

    /**
     * CMN_BTN4 -- Approve
     */
    public static final String[] CMN_BTN4 = {"btn4", "", "Approve" };

    /**
     * CMN_BTN5 -- Reject
     */
    public static final String[] CMN_BTN5 = {"btn5", "", "Reject" };

    /**
     * CMN_BTN6 -- Download
     */
    public static final String[] CMN_BTN6 = {"btn6", "CMN_Download", "Download" };

    /**
     * CMN_BTN7 -- Delete
     */
    public static final String[] CMN_BTN7 = {"btn7", "", "Delete" };

    /**
     * CMN_BTN8 -- Clear
     */
    public static final String[] CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * CMN_BTN9 -- Reset
     */
    public static final String[] CMN_BTN9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * CMN_BTN10 -- Return
     */
    public static final String[] CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Search Button
     */
    public static final String[] SEARCH_BTN = {"btn11", "Search", "Search" };

    /**
     * View Item Button
     */
    public static final String[] VIEW_ITEM_BTN = {"btn12", "ViewItem", "View Item" };

    // START 2020/07/06 [QC#55448,ADD]
    /**
     * Upload Button
     */
    public static final String[] UPLOAD_BTN = {"btn13", "Upload", "Upload" };
    // END 2020/07/06 [QC#55448,ADD]

    /**
     * Read Authorization
     */
    public static final String AUTH_READ = "NMAL0100T010";

    /**
     * Down load Authorization
     */
    public static final String AUTH_DOWNLOAD = "NMAL0100T020";

    /**
     * Please set at least one search criteria.
     */
    public static final String NMAM0192E = "NMAM0192E";
    
    // START 2020/07/10 [QC#55448,ADD]
    /**
     * NMAL0110 Item Detail Screen:Taxing Tab Authorization
     */
    public static final String AUTH_TAX = "NMAL0110T190";

    /** screen transition condition to Item Taxing Info(Move Window) */
    public static final String SCRN_TRANS_COND_ZYP0210_TAX = "NMA0100001";

    // START 2022/10/28 [QC#60399,ADD]
    /**
     * NMAL0110 Item Detail Screen:Header Tab Authorization
     */
    public static final String AUTH_HEADER = "NMAL0110T010";

    /** screen transition condition to Item Header Info(Move Window) */
    public static final String SCRN_TRANS_COND_ZYP0210_HEADER = "NMA0100002";
    // END 2022/10/28 [QC#60399,ADD]

    /**
     * Please select [@].
     */
    public static final String NMAM8461E = "NMAM8461E";

    /**
     * You don't have the authorization to update this Upload.
     */
    public static final String NMAM8708E = "NMAM8708E";
    // END 2020/07/10 [QC#55448,ADD]
}
