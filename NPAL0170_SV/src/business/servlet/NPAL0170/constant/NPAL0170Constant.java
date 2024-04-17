/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0170.constant;

/**
 * <pre>
 * PO Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/25/2012   SRA             N.Otsuji        Create          N/A
 * 02/05/2013   CSAI            K.Lee           Update          WDS#69
 * 03/08/2016   CITS            K.Ogino         Update          QC#4975
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * </pre>
 */
public interface NPAL0170Constant {

    /**
     * Business ID
     */
    String BUSINESS_ID = "NPAL0170";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NPAL0170T020";

    /**
     * Function Button 1
     */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Function Button 2
     */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Function Button 3
     */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Function Button 4
     */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Function Button 5
     */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Function Button 6
     */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Function Button 7
     */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Function Button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Function Button 9
     */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Function Button 10
     */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Function Code : Search
     */
    String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    String FUNCTION_CD_UPDATE = "40";

    /**
     * NPAL0170T020
     */
    String FUNCTION_ID_INQUIRY = "NPAL0170T020";

    /**
     * NPAL0170T010
     */
    String FUNCTION_ID_UPDATE = "NPAL0170T010";

    /**
     * Key to retrieve maximum number of bytes allowed in a PO text
     * from NUM_CONST
     */
    String NUM_CONST_PO_MSG_MAX_BYTES = "NPAL0170_PO_MSG_MAX_BYTES";

    /**
     * The Constant [@] was not found on Constant table.
     */
    String NPAM1010E = "NPAM1010E";

    /**
     * Message is too long [@ characters]. It must be less than or
     * equal to @ characters.
     */
    String NPAM1264E = "NPAM1264E";

    /**
     * Missing required field [@].
     */
    String ZZSM4208E = "ZZSM4208E";

    // START 2013/02/07 K.Lee Upd
    /**
     * The process has been successfully completed.
     */
    String NPAM0005I = "NPAM0005I";
    // END 2013/02/07 K.Lee Upd

    /**
     * Max loop count
     */
    int MAX_COUNT = 10;    
}
