/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550.constant;

public interface NMAL6550Constant {

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
    String[] BTN_INSERT_ROW       = {"Insert_Row",       "Insert_Row" };
    String[] BTN_DELETE_ROW       = {"Delete_Row",       "Delete_Row"};
    String[] BTN_OPENWIN_CTRY     = {"OpenWin_CTRY",     "OpenWin_CTRY"};
    String[] BTN_OPENWIN_CCY      = {"OpenWin_CCY",      "OpenWin_CCY"};
    String[] BTN_OPENWIN_COA_AFFL = {"OpenWin_COA_AFFL", "OpenWin_COA_AFFL"};
    String[] BTN_OPENWIN_ACCT     = {"OpenWin_ACCT",     "OpenWin_ACCT"};
    String[] BTN_SEARCH_COA_AFFL  = {"Search_COA_AFFL",  "Search_COA_AFFL"};
    String[] BTN_SEARCH_ACCT      = {"Search_ACCT",      "Search_ACCT"};

    
    /** Screen ID */
    String SCREEN_ID = "NMAL6550Scrn00";
    String BUSINESS_APPLICATION_ID = "NMAL6550";

    String TABLE_ID_A = "A";
 
    // Function ID
    String FUNC_REFER  = "NMAL6550T010";
    String FUNC_UPDATE = "NMAL6550T020";

    int MAX_RECORD_COUNT = 999;
    
    enum MESSAGE_ID {
        NZZM0002I
       ,NMAM0072E;
   }
}
