package business.servlet.ZYPL0220.constant;

public interface ZYPL0220Constant {
    // --------------------------------------------------------------
    // Button Control Info
    // --------------------------------------------------------------
    /** Button Control Info(buttion1) */
    String[] BTN_CMN_BTN1 = {"btn1", "", "" };

    /** Button Control Info(buttion2) */
    String[] BTN_CMN_BTN2 = {"btn2", "", "" };

    /** Button Control Info(buttion3) */
    String[] BTN_CMN_BTN3 = {"btn3", "", "" };

    /** Button Control Info(buttion4) */
    String[] BTN_CMN_BTN4 = {"btn4", "", "" };

    /** Button Control Info(buttion5) */
    String[] BTN_CMN_BTN5 = {"btn5", "", "" };

    /** Button Control Info(buttion6) */
    String[] BTN_CMN_BTN6 = {"btn6", "", "" };

    /** Button Control Info(buttion7) */
    String[] BTN_CMN_BTN7 = {"btn7", "", "" };

    /** Button Control Info(buttion8) */
    String[] BTN_CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    /** Button Control Info(buttion9) */
    String[] BTN_CMN_BTN9 = {"btn9", "", "" };

    /** Button Control Info(buttion10) */
    String[] BTN_CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    // --------------------------------------------------------------
    // Button on screen
    // --------------------------------------------------------------
    /** TOP Button */
    String BTN_TOP = "Top";

    /** Restriction Button */
    String BTN_RESTRICTION = "Restriction";

    /** Update Button */
    String BTN_UPDATE = "Update";

    /** Load Names Button */
    String BTN_LOAD = "LoadNames";

    /** Search Button */
    String BTN_SEARCH = "Search";

    /** Add Button */
    String BTN_ADD = "Add";

    // --------------------------------------------------------------
    // Message Code
    // --------------------------------------------------------------
    /** Returnボタンの確認メッセージ */
    String MSG_CD_CONFIRM_RETURN_EVENT = "ZZM8102I";

    /** LoadNamesボタン押下時にファイルから項目を読み込みなおすか確認する */
    String MSG_CD_CONFIRM_LOAD_NAMES = "ZYPM0102W";

    /** LoadNamesボタン押下後にFile IDが変更されていないか確認する。 */
    String MSG_CD_INCONSISTENT_FILE_ID = "ZYPM0112E";

    /** Template Header 表に行が1行も登録されていない場合。 */
    String MSG_CD_NO_RECORD_FILE_ID = "ZYPM0113E";

    /** Searchイベントに成功した場合 */
    String MSG_CD_SUCCESS_SEARCH = "ZZM8002I";
    
    /** LoadNamesイベントに成功した場合 */
    String MSG_CD_SUCCESS_LOAD = "ZZM8001I";
    
    /** AddまたはUploadイベントに成功した場合 */
    String MSG_CD_SUCCESS_PROCESS = "ZZM8100I";

    /** Searchを行っていないUploadCsvIdでUpldateしようとした場合。 */
    String MSG_CD_NO_SEARCHED_UPLD_CSV_ID = "ZYPM0118E";
   
    /** 登録情報が重複している場合 */
    String MSG_CD_NO_DUPLICATE_RECORD= "ZYPM0119E";
   
}
