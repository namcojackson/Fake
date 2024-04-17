/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved./Pre>
 */
package business.blap.NLCL1020.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 02/16/2017   CITS            M.Naito         Update          QC#12673
 * 03/11/2019   Fujitsu         T.Ogura         Update          QC#30705
 *</pre>
 */
public class NLCL1020Constant {

    /***/
    public static final int LOC_TO_CUT = 0;

    /***/
    public static final int SHPG_SVC_LVL_CUT = 20;

    /***/
    public static final int MAX_COUNT = 99;

    /***/
    public static final int MAXLENGTH = 3;

    /***/
    public static final String PADDING_STR = "0";

    /***/
    public static final String SEPARATE = ":";

    /***/
    public static final String BUSINESS_ID = "NLCL1020";

    /***/
    public static final String WH = "WH";

    /***/
    public static final String STOCK_STATUS = "Stock Status";

    /***/
    public static final String MDSE_CODE = "Merchandise Code";

    /***/
    public static final String QTY = "Qty";

    /***/
    public static final String CURRENT_AVAILABLE = "Current Available";

    /***/
    public static final String CURRENT_AVAILABLE_QTY = "Current Available Qty";

    /***/
    public static final String LOC_FROM = "Loc From";

    /***/
    public static final String LOC_TO = "Loc To";

    /***/
    public static final String FROM_TECH_WH = "From Tech WH"; // 10/20/2015 add 

    /***/
    public static final String FROM_TECH_SWH = "From Tech SWH"; // 10/20/2015 add 

    /***/
    public static final String TO_TECH_WH = "To Tech WH"; // 10/20/2015 add 

    /***/
    public static final String TO_TECH_SWH = "To Tech SWH"; // 10/20/2015 add 

    /***/
    public static final String TRX_LINE_SUB_NUM001 = "001";

    /***/
    public static final String ERRFLAG_RG0 = "0";

    /***/
    public static final String ERRFLAG_RG1 = "1";

    /***/
    public static final String SYSTIME_FORMATE = "yyyyMMddHHmmssSSS";

    /***/
    public static final String NLCL1020SCRN00_ONCHANGE_STK_STS = "NLCL1020Scrn00_Onchange_StkSts";

    /***/
    public static final String NLCL1020SCRN00_DISPLAY_AVAL_QTY = "NLCL1020Scrn00_Display_AvalQty";
    /***/
    public static final String NLCL1020SCRN00_DISPLAY_MDSENAME = "NLCL1020Scrn00_Display_MDSEName";

    /***/
    public static final String NLCL1020SCRN00_CMN_SUBMIT = "NLCL1020Scrn00_CMN_Submit";

    /***/
    public static final String NLCL1020SCRN00_CMN_APPLY = "NLCL1020Scrn00_CMN_Apply";

    /***/
    public static final String NLCL1020SCRN00_CMN_DOWNLOAD = "NLCL1020Scrn00_CMN_Download";

    /***/
    public static final String NLCL1020SCRN00_ADD_DTAILL_LINE = "NLCL1020Scrn00_Add_Dtaill_Line";

    /***/
    public static final String NLCL1020SCRN00_SEARCH_TRX = "NLCL1020Scrn00_Search_Trx";

    /***/
    public static final String NLCL1020_INIT = "NLCL1020_INIT";

    /** File extension for CSV download */
    public static final String CSV_EXT = ".csv";

    /** File extension for CSV download file name */
    public static final String CSV_FILE_NAME = "NLCL1020_FieldTransferOrderEntry";

    /**
     * The maximum limit of CSV Donwload number
     */
    public static final int DOWNLOAD_MAX_COUNT = 65000;

    // Message
    /** [@] is [@]. */
    public static final String NLCM0111E = "NLCM0111E";

    /** Entered "Merchandise Code" does not exist. */
    public static final String NLCM0002E = "NLCM0002E";

    /** Due to an error, process cannot be completed.  Please contact IT Department. */
    public static final String NLCM0001E = "NLCM0001E";

    /** Inventory with the entered Key does not exist. */
    public static final String NLCM0021E = "NLCM0021E";

    /** Entered @ cannot handle the target inventory. */
    public static final String NLCM0067E = "NLCM0067E";

    /** "Current Available" has a negative value. */
    public static final String NLCM0032E = "NLCM0032E";

    /** Entered @ exceeded @. */
    public static final String NLCM0016E = "NLCM0016E";

    /** The same Code cannot be selected for @ and @. */
    public static final String NLCM0035E = "NLCM0035E";

    /** Entered "Location Code" does not exist. */
    public static final String NLCM0004E = "NLCM0004E";

    /** Please enter the same value for "From Sub Warehouse" and "To Sub Warehouse". */
    public static final String NLZM2302E = "NLZM2302E"; // 10/20/2015 add

    /** Tech WH/SWH[@] can not be used because Tech PI is being executed. */
    public static final String NLCM0232E = "NLCM0232E"; // 2019/03/11 T.Ogura [QC#30705,ADD]

}
