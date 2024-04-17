/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0270.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270Constant {

    /***/
    public static final String NFAL0270_INIT = "NFAL0270_INIT";
    /***/
    public static final String NFAL0270_NMAL6050 = "NFAL0270_NMAL6050";
    /***/
    public static final String NFAL0270SCRN00_ADD_LINE = "NFAL0270Scrn00_Add_Line";
    /***/
    public static final String NFAL0270SCRN00_CMN_CLEAR = "NFAL0270Scrn00_CMN_Clear";
    /***/
    public static final String NFAL0270SCRN00_CMN_COLCLEAR = "NFAL0270Scrn00_CMN_ColClear";
    /***/
    public static final String NFAL0270SCRN00_CMN_COLSAVE = "NFAL0270Scrn00_CMN_ColSave";
    /***/
    public static final String NFAL0270SCRN00_CMN_DOWNLOAD = "NFAL0270Scrn00_CMN_Download";
    /***/
    public static final String NFAL0270SCRN00_CMN_RETURN = "NFAL0270Scrn00_CMN_Return";
    /***/
    public static final String NFAL0270SCRN00_CMN_SUBMIT = "NFAL0270Scrn00_CMN_Submit";
    /***/
    public static final String NFAL0270SCRN00_DEL_LINE = "NFAL0270Scrn00_Del_Line";
    /***/
    public static final String NFAL0270SCRN00_OPENWIN_MDLGRPID = "NFAL0270Scrn00_OpenWin_mdlGrpId";
    /***/
    public static final String NFAL0270SCRN00_PAGENEXT = "NFAL0270Scrn00_PageNext";
    /***/
    public static final String NFAL0270SCRN00_PAGEPREV = "NFAL0270Scrn00_PagePrev";
    /***/
    public static final String NFAL0270SCRN00_SEARCH = "NFAL0270Scrn00_Search";
    /***/
    public static final String NFAL0270SCRN00_TBLCOLUMNSORT = "NFAL0270Scrn00_TBLColumnSort";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Too many search results.  Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please check at least 1 checkbox. */
    public static final String NFAM0075E = "NFAM0075E";

    /** Cannot be added over the limit of [@]. */
    public static final String NFAM0210E = "NFAM0210E";

    /** Data insert failure.[@] */
    public static final String NFAM0180E = "NFAM0180E";

    /** Data update failure.[@] */
    public static final String NFAM0181E = "NFAM0181E";

    /** Total of allocation percentage must be 100%. */
    public static final String NFAM0209E = "NFAM0209E";

    /** It failed to remove [@]. Please contact IT Support. */
    public static final String NFAM0211E = "NFAM0211E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * This data has been updated by another user.*/
    public static final String ZZZM9004E = "ZZZM9004E";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NFAL0270";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65000;

    /** Allocation Persont Min */
    public static final BigDecimal ZERO = BigDecimal.valueOf(0.00);

    /** Allocation Persont Max */
    public static final BigDecimal HUNDRED_DECIMAL_POINT = BigDecimal.valueOf(100.00);

    /** Allocation Persont Max */
    public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

}
