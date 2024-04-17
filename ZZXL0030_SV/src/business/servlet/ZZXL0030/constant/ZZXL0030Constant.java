package business.servlet.ZZXL0030.constant;

// 2011.11.17 Start ABR Removal
import parts.common.EZDSystemEnv;
// 2011.11.17 End   ABR Removal
import com.canon.cusa.s21.common.ZZX.ZZXC001001.ZZXC001002;

public interface ZZXL0030Constant extends ZZXC001002 {

    public final int CALENDAR_NUM = 42;  
    public final String BUSINESS_ID = "ZZXL0030";

    /** Each button's number */
    public final String NEXT_BUTTON = "0";
    public final String PREV_BUTTON = "1";
    public final String SEARCH_BUTTON = "2";
    public final String TODAY_BUTTON = "3";
    public final String RETURN_BUTTON = "4";
    public final String SUBMIT_BUTTON = "5";
    public final String WEEKDAY_A_BUTTON = "6";
    public final String WEEKDAY_B_BUTTON = "7";
    public final String FIND_BUTTON = "8";
    
    /** Each button's name */
    public final String NEXT_BUTTON_NAME = "NextMonth";
    public final String PREV_BUTTON_NAME = "PrevMonth";
    public final String SEARCH_BUTTON_NAME = "Search";
    public final String TODAY_BUTTON_NAME = "Today";
    public final String RETURN_BUTTON_NAME = BTN_CMN_RETURN[1];
    public final String SUBMIT_BUTTON_NAME = BTN_CMN_SUBMIT[1];
    public final String WEEKDAY_A_BUTTON_NAME = "CheckWeekDaysA";
    public final String WEEKDAY_B_BUTTON_NAME = "CheckWeekDaysB";
    public final String FIND_BUTTON_NAME = "Find";
    
    public final String DIRTYDATA_MSG = "ZZZM9016W";
    public final String CHANGETYPE_MSG = "ZZZM9017E";
    public final String SAVEDATA_MSG = "ZZZM9003I";
    public final String NOCALTYPE_MSG = "ZZZM9023E";
    
    /** S21 or Parts Flag */
    public final String SYS_FLG_PARTS = "P";
    public final String SYS_FLG_S21 = "S";
    public final String SYS_FLG = SYS_FLG_S21;
    // 2011.11.17 Start ABR Removal
    // public final String PARTS_GLBL_CMPY_CD = "ABR";
    public final String PARTS_GLBL_CMPY_CD = EZDSystemEnv.getString("S21.global_company_code");
    // 2011.11.17 End   ABR Removal
    
    /** Flag to which month to check if it is modified. */
    public final int CHECK_BOTH_MONTHS =0;
    public final int CHECK_THIS_MONTH =1;
    public final int CHECK_NEXT_MONTH =2;
    
    /** dtAttrbCd field of CAL table  */
    public final String ATTRIB_CD = "01";
    
}
