/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0210.constant;

/**
 *<pre>
 * Labor Charge Table Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0210Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0210";

    /** SCRN_ID : NSBL0210Scrn00 */
    public static final String SCRN_ID = "NSBL0210Scrn00";

    /** status code : 1000 */
    public static final int CSV_READ_STATUS_CODE_1000 = 1000;

    /** status code : 2000 */
    public static final int CSV_READ_STATUS_CODE_2000 = 2000;

    /** check-box name : XX_CHK_BOX_A */
    public static final String XX_CHK_BOX = "xxChkBox";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /** The data @ does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** Please select at least 1 check-box. */
    public static final String NSBM0007E = "NSBM0007E";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSBM0058E = "NSBM0058E";

    /** [@] doesn't exist in the table [@]. */
    public static final String NSBM0151E = "NSBM0151E";

    /** Selected item is invalid Intangible Item. */
    public static final String NSBM0152E = "NSBM0152E";

    /** Model Group [@], Line of Business [@] , Shift#[@] is duplicated. */
    public static final String NSBM0156E = "NSBM0156E";

    /** Shift# [@] is not a shift of Line of Business [@]. */
    public static final String NSBM0157E = "NSBM0157E";

    /** Model Group [@], Line of Business [@] , Shift#[@] already exists in the table [@] . */
    public static final String NSBM0158E = "NSBM0158E";

    /** The number of items Unjust error. */
    public static final String NSBM0159E = "NSBM0159E";

    /** Number of digits over error (item name [@]). */
    public static final String NSBM0160E = "NSBM0160E";

    /** Item classification error (item name [@]). */
    public static final String NSBM0161E = "NSBM0161E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and can not process.
     */
    public static final String NSBM0162E = "NSBM0162E";

    /** Failed to update "@". */
    public static final String NSBM0163E = "NSBM0163E";

    /** Failed to insert "@". */
    public static final String NSBM0164E = "NSBM0164E";

    /** Failed to delete "@". */
    public static final String NSBM0165E = "NSBM0165E";

    /** Table name : DS_MDL_GRP */
    public static final String TBL_NM_SVC_DS_MDL_GRP = "Model Group";

    /** Table name : SVC_PRC_SHIFT */
    public static final String TBL_NM_SVC_PRC_SHIFT = "Service Price Shift";

    /** Table name : SVC_LBOR_CHRG */
    public static final String TBL_NM_SVC_LBOR_CHRG = "Service Labor Charge";

    /** Table name : MDSE */
    public static final String TBL_NM_MDSE = "Mdse";

    /** Item name : Model Group */
    public static final String ITEM_NM_MDL_GRP_NM = "Model Group";

    /** Item name : Shift# */
    public static final String ITEM_NM_SHIFT_NUM = "Shift#";

    /** Item name : Default Intangible Item# */
    public static final String ITEM_NM_DEF_INTG_MDSE_CD = "Default Intangible Item#";

    /** delimiter character */
    public static final String DELIM = "|";

    /** down-load limit */
    public static final int LIMIT_DOWNLOAD = 65000;
}
