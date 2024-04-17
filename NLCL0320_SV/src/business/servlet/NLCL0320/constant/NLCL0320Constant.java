/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.servlet.NLCL0320.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public interface NLCL0320Constant {

    /** Function Button */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // [0]:Button Name [1]:Event Name
    /** Another Button */
    String[] BTN_SEARCH = {"Search", "Search" };

    String[] BTN_INSERT_ROW = {"Click_Btn_InsertRow", "Click_Btn_InsertRow" };

    String[] BTN_DELETE_ROW = {"Click_Btn_DeleteRow", "Click_Btn_DeleteRow" };

    String[] BTN_RWH_NM = {"Click_Btn_RetailWh", "Click_Btn_RetailWh" };

    String[] BTN_ACCOUNT = {"Click_Btn_Account", "Click_Btn_Account" };

    /** Screen ID */
    String SCREEN_ID = "NLCL0320Scrn00";

    String APPLICATION_ID = "NLCL0320";

    String TABLE_ID_A = "A";

    // Function ID
    String FUNC_REFER = "NLCL0320T010";

    String FUNC_UPDATE = "NLCL0320T020";

    int MAX_RECORD_COUNT = 200;

    enum MESSAGE_ID {
        NZZM0002I, NMAM0072E, NMAM0180E, NMAM0181E, ZZM9008E;
    }
}
