/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 * 2013/06/03   Hitachi         T.Tomita        Update          QC1209
 * 2017/01/16   CITS            T.Kikuhara      Update          QC16256
 *</pre>
 */
package business.servlet.NLAL0070.constant;

public interface NLAL0070Constant {

    /**
     * Screen ID
     */
    String SCRN_ID = "NLAL0070Scrn00";

    // 2013/06/03 QC1209 T.Tomita Add Start
    /**
     * BusinessID
     */
    String BUSINESS_ID = "NLAL0070";
    // 2013/06/03 QC1209 T.Tomita Add End

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLAL0070T020";

    /**
     * Table ID(A_Left)
     */
    String TBL_ID_A_LEFT = "A_Table_Left";

    /**
     * Table ID(A_Right)
     */
    String TBL_ID_A_RIGHT = "A_Table_Right";

    /**
     * Table ID(B_Left)
     */
    String TBL_ID_B_LEFT = "B_Table_Left";

    /**
     * Table ID(B_right)
     */
    String TBL_ID_B_RIGHT = "B_Table_Right";

    /**
     */
    String TB_SCHEDULE = "Schedule";

    /**
     */
    String TB_SUMMARY = "Summary";

    /**
     */
    String MDSE_FOR_SCE_V = "MDSE_FOR_SCE_V";

    /**
     */
    String MDSE_CD = "MDSE_CD";

    /**
     */
    String MDSE_NM = "MDSE_NM";

    /**
     */
    String MDSE_FOR_SCE_POP_UP = "MDSE for SCE Pop Up";

    /**
     */
    String MDS = "mds";

    /**
     */
    String CLOSE = "CMN_Close";    

    /**
     * Common button 1
     */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** File Name For Download of Schedule List */
    String CSV_FILE_NAME_SCHEDULE = "NLAL0070WHScheduleList";
   
    /** File Name For Download of Summary List */
    String CSV_FILE_NAME_SUMMARY = "NLAL0070WHSummaryList";  

    /**
     * Default_Scrn_Num_InvoiceList
     */
    String INV_POP_SCR_NUM_00 = "00";

    /**
     * Default_Scrn_Num_Container
     */
    String INV_POP_SCR_NUM_02 = "02";

    /**
     * Default_Scrn_Num_Asn
     */
    String INV_POP_SCR_NUM_06 = "06";
    
    /**
     * style sheet for text bg color in table.(even number record)
     */
    String STYLE_TEXT_BG_COLOR_EVEN_NUM = "#f0f0f0";
    
    /**
     * style sheet for text bg color in table.(even number record)
     */
    String STYLE_TEXT_BG_COLOR_ODD_NUM = "#FFFAFA";
    
    /** Radio Button: MDSE */
    int RADIO_MDSE = 1;
    
    /** Radio Button: CNTNR/INV */
    int RADIO_CNTNR_INV = 2;
}
