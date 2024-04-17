/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0760.constant;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public final class NSAL0760Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0760";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * The specified record cannot be updated.  SQLID[@], Set Value[@]
     */
    public static final String NDAM0021E = "NDAM0021E";

    /**
     * No search results found.
     */
    public static final String ZZOM0011E = "ZZOM0011E";

    /**
     * argument is null or empty string. [@]
     */
    public static final String ZZXM0006E = "ZZXM0006E";

    /**
     * Display Text base
     */
    public static final String DISP_TXT_BASE = "base";

    /**
     * Display Text overage
     */
    public static final String DISP_TXT_OVERAGE = "overage-";
    
    /**
     * Data Level 20
     */
    public static final int LVL20 = 20;
    
    /**
     * Data Level 30
     */
    public static final int LVL30 = 30;
    
    /**
     * Data Level 40
     */
    public static final int LVL40 = 40;
}
