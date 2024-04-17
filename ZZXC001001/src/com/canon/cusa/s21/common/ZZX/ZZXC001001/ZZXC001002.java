package com.canon.cusa.s21.common.ZZX.ZZXC001001;

//2011.11.17 Start ABR Removal
import parts.common.EZDSystemEnv;
// 2011.11.17 End   ABR Removal

public interface ZZXC001002 {

    /** The Constant BTN_SAVE. */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** The Constant BTN_SUBMIT. */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** The Constant BTN_APPLY. */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** The Constant BTN_APPROVE. */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** The Constant BTN_REJECT. */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** The Constant BTN_DOWNLOAD. */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** The Constant BTN_DELETE. */
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** The Constant BTN_CLEAR. */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** The Constant BTN_RESET. */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** The Constant BTN_RETURN. */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** The Constant BTN_CLOSE. */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };
    
    /** No search result */
    public final String NORESULT_MSG = "ZZZM9001E";
    
    /** Max number of search result is exceeded */
    public final String OVERROW_MSG = "ZZZM9002W";
    
    /** The data specified already exists. */
    public final String EXISTDATA_MSG = "ZZZM9015W";
    
    /** S21 or Parts Flag */
    public final String SYS_FLG_PARTS = "P";
    public final String SYS_FLG_S21 = "S";
    public final String SYS_FLG = SYS_FLG_S21;
    // 2011.11.17 Start ABR Removal
    // public final String PARTS_GLBL_CMPY_CD = "ABR";
    public final String PARTS_GLBL_CMPY_CD = EZDSystemEnv.getString("S21.global_company_code");
    // 2011.11.17 End   ABR Removal
}

