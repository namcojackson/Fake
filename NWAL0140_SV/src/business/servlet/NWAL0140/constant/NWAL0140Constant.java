package business.servlet.NWAL0140.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public interface NWAL0140Constant {

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** 「Edit」ボタン : 業務ボタン */
    String EDIT_BTN = "Ship_To_Edit";

    /** 「State」イベント ： NMAL6050呼び出し */
    String EVENT_OPEN_WIN_SHIP_TO_STATE = "OpenWin_ShipToState";

    /** 「Country」イベント ： NMAL6050呼び出し */
    String EVENT_OPEN_WIN_SHIP_TO_COUNTRY = "OpenWin_ShipToCountry";

// QC#4505
//    /** 「Post Code」イベント ： NMAL6050呼び出し */
//    String EVENT_OPEN_WIN_POST_CODE = "OpenWin_PostCode";

    /** 「Ship To」イベント ： NMAL6010呼び出し */
    String EVENT_OPEN_WIN_SHIP_TO = "OpenWin_ShipTo";
    
    // QC#4505
    /** Get Address Button */
    String GET_ADDR_BTN = "GetAddress";
    
    /** EVENT_OPEN_WIN_CITY */
    String EVENT_OPEN_WIN_CITY = "OpenWin_City";

    /** EVENT_OPEN_WIN_POST */
    String EVENT_OPEN_WIN_POST = "OpenWin_Post";

    /** EVENT_OPEN_WIN_CNTY */
    String EVENT_OPEN_WIN_CNTY = "OpenWin_Cnty";

}
