package business.servlet.NWCL0130.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public interface NWCL0130Constant {

	/**
     * Business ID.
     */
    String BUSINESS_ID = "NWCL0130";

    /**
     * Screen ID.
     */
    String SCREEN_ID = "NWCL0130Scrn00";

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
     * Drop Button
     */
    String[] Drop_BTN = {"btn12", "Drop", "Drop" };

    /**
     * Retransmit Button
     */
    String[] Retransmit_BTN = {"btn13", "Retransmit", "Retransmit" };

    /**
     * Delete Button
     */
    String[] Del_BTN = {"btn14", "Del", "Del" };

    /**
     * Read Authorization
     */
	String AUTH_READ = "NWCL0130T010";

    /**
     * Edit Authorization
     */
	String AUTH_EDIT = "NWCL0130T020";

    /** length 11 */
    int LENGTH_11 = 11;
    
    /** The specified format is incorrect. It must be @. */
    String NMAM8075E = "NMAM8075E";

}