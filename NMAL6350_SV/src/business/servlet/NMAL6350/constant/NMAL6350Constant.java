/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6350.constant;

public interface NMAL6350Constant {

    /** Function Button */
    String[] BTN_CMN_SAVE     = {"btn1",  "CMN_Save"    , "Save"};
    String[] BTN_CMN_SUBMIT   = {"btn2",  "CMN_Submit"  , "Submit"};
    String[] BTN_CMN_APPLY    = {"btn3",  "CMN_Apply"   , "Apply"};
    String[] BTN_CMN_APPROVE  = {"btn4",  "CMN_Approve" , "Approve"};
    String[] BTN_CMN_REJECT   = {"btn5",  "CMN_Reject"  , "Reject"};
    String[] BTN_CMN_DOWNLOAD = {"btn6",  "CMN_Download", "Download"};
    String[] BTN_CMN_DELETE   = {"btn7",  "CMN_Delete"  , "Delete"};
    String[] BTN_CMN_CLEAR    = {"btn8",  "CMN_Clear"   , "Clear"};
    String[] BTN_CMN_RESET    = {"btn9",  "CMN_Reset"   , "Reset"};
    String[] BTN_CMN_RETURN   = {"btn10", "CMN_Return"  , "Return"};

    // [0]:Button Name [1]:Event Name
    /** Another Button */
    String[] BTN_INSERT_ROW = {"Insert_Row", "Insert_Row" };
    String[] BTN_DELETE_ROW = {"Delete_Row", "Delete_Row"};
    
    /** Screen ID */
    String SCREEN_ID = "NMAL6350Scrn00";
    String BUSINESS_APPLICATION_ID = "NMAL6350";

    String TABLE_ID_A = "A";
 
    // Function ID
    String FUNC_REFER  = "NMAL6350T010";
    String FUNC_UPDATE = "NMAL6350T020";

    int MAX_RECORD_COUNT = 999;
    
    enum MESSAGE_ID {
        NZZM0002I
       ,NMAM0072E
       ,ZZM9004E;
    }
    enum DISPLAY_NAME {
        PackageLevelNumber
   }

}
