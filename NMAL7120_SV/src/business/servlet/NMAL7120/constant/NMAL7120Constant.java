package business.servlet.NMAL7120.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 09/23/2016   Hitachi         Y.Takeno        Update          QC#13083
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 *</pre>
 */
public interface NMAL7120Constant {

	/**
     * Business ID.
     */
    String BUSINESS_ID = "NMAL7120";

    /**
     * Screen ID.
     */
    String SCREEN_ID = "NMAL7120Scrn00";

    /**
     * FUNC_CD_SRCH -- 20
     */
    String FUNC_CD_SRCH = "20";

    /**
     * FUNC_CD_UPD -- 40
     */
    String FUNC_CD_UPD = "40";

    /**
     * CMN_CLOSE -- CMN_Close
     */
    String CMN_CLOSE = "CMN_Close";

    /**
     * CMN_BTN1 -- Save
     */
    String[] CMN_BTN1 = {"btn1", "", "Save" };

    /**
     * CMN_BTN2 -- Submit
     */
    String[] CMN_BTN2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * CMN_BTN3 -- Apply
     */
    String[] CMN_BTN3 = {"btn3", "", "Apply" };

    /**
     * CMN_BTN4 -- Approve
     */
    String[] CMN_BTN4 = {"btn4", "", "Approve" };

    /**
     * CMN_BTN5 -- Reject
     */
    String[] CMN_BTN5 = {"btn5", "", "Reject" };

    /**
     * CMN_BTN6 -- Download
     */
    String[] CMN_BTN6 = {"btn6", "CMN_Download", "Download" };

    /**
     * CMN_BTN7 -- Delete
     */
    String[] CMN_BTN7 = {"btn7", "", "Delete" };

    /**
     * CMN_BTN8 -- Clear
     */
    String[] CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * CMN_BTN9 -- Reset
     */
    String[] CMN_BTN9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * CMN_BTN10 -- Return
     */
    String[] CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    
    /**
     * Search Button
     */
    String[] Search_BTN = {"btn0", "Search", "Search" };
    
    /**
     * Add Button
     */
    String[] Add_BTN = {"btn11", "Add", "Add" };

    /**
     * LineCust Button
     */
    String[] Line_Cust_BTN = {"btn12", "LineCustNum", "LineCustNum" };

    /**
     * LinePrcCatgCd Button
     */
    String[] Line_Prc_Catg_BTN = {"btn12", "LinePrcCatgCd", "LinePrcCatgCd" };

    /**
     * LinePrcContrNum Button
     */
    String[] Line_Prc_Contr_BTN = {"btn12", "LinePrcContrNum", "LinePrcContrNum" };

    /**
     * LineCoaBrCd Button
     */
    String[] Line_Coa_Br_BTN = {"btn12", "LineCoaBrCd", "LineCoaBrCd" };
    
    
    /**
     * Delete Button
     */
    String[] Del_BTN = {"btn13", "Del", "Del" };

    /**
     * Read Authorization
     */
	String AUTH_READ = "NMAL7120T010";

    /**
     * Edit Authorization
     */
	String AUTH_EDIT = "NMAL7120T020";

    /** CSMP# format */
    String CHECK_CSMP_FORMAT = "([0-9]|[A-Z]){5}-([0-9]|[A-Z]){5}";
    
    /** CSMP# formatTxt */
    String CHECK_CSMP_FORMAT_TXT = "XXXXX-XXXXX";

    /** length 11 */
    int LENGTH_11 = 11;
    
    /** The specified format is incorrect. It must be @. */
    String NMAM8075E = "NMAM8075E";

// 09/23/2016 QC#13083 Add Start
    /** parameter length : NMAL6760 */
    int PARAM_SIZE_NMAL6760 = 34;

    /** parameter index NMAL6760 xxDplyCtrlFlg_HC */
    int PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_HC = 24;

    /** parameter index NMAL6760 xxDplyCtrlFlg_AC */
    int PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_AC = 25;

    /** parameter index NMAL6760 xxDplyCtrlFlg_SC */
    int PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_SC = 26;

    /** parameter index NMAL6760 xxDplyCtrlFlg_CT */
    int PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_CT = 33;
// 09/23/2016 QC#13083 Add End
    
    /** Jump Page Number Item Name */ //S21_NA ADD QC#20206(L3#269)
    public static final String JUMP_PAGE_NUMBER_ITNM = "Jump Page Number";
}