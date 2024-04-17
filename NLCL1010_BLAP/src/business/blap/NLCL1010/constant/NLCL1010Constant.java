/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1010.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 * 08/12/2013   Fujitsu         Y.Taoka         Update          Defect#1627
 * 02/09/2017   CITS            M.Naito         Update          QC#12673
 *</pre>
 */
public class NLCL1010Constant {
    /**
     * Business id
     */
    public static final String BUSINESS_ID = "NLCL1010";

    /**
     * Screen id
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Please enter at least one search criteria
     */
    public static final String NLCM0114E = "NLCM0114E";

    /**
     * The chronological sequence between the dates in the "@" field is wrong
     */
    public static final String NLCM0115E = "NLCM0115E";

    /**
     * No search results found.
     */
    public static final String NLCM0002I = "NLCM0002I";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Too many search results. Please narrow your search criteria and retry.
     */
    public static final String NZZM0007E = "NZZM0007E";

    /**
     * Too many search results. Please narrow your search criteria and retry.
     */
    public static final String ZZZM9002W = "ZZZM9002W";

    /**
     * Old Merchandise Code required when Item Change Stock In selected
     */
    public static final String NLCM0116E = "NLCM0116E";

    /**
     * Serial Transaction Source type required when Serial Transaction Source Header# inputed
     */
    public static final String NLCM0117E = "NLCM0117E";

    /**
     * Merchandise is not Serialized Tangible
     */
    public static final String NLCM0118E = "NLCM0118E";

    /**
     * Merchandise is not Refurbish
     */
    public static final String NLCM0119E = "NLCM0119E";

    /**
     * Merchandise Code is not registered as "Original Merchandise Code" On Merchandise Master
     */
    public static final String NLCM0120E = "NLCM0120E";

    /**
     * The entered Merchandise Code does not exist in Master
     */
    public static final String NLCM0108E = "NLCM0108E";

    /**
     * Entered "Location Code" does not exist
     */
    public static final String NLCM0110E = "NLCM0110E";

    /**
     * The number of Detail rows reached to the maximum. No more Details can be registered
     */
    public static final String NLCM0025E = "NLCM0025E";

    /**
     * The Length of Serial # is different from master's value
     */
    public static final String NLCM0121W = "NLCM0121W";

    /**
     * The Range of Serial # is different from master's value
     */
    public static final String NLCM0122W = "NLCM0122W";

    /**
     * The target data for the update does not exist
     */
    public static final String NLCM0123E = "NLCM0123E";

    /**
     * The process [@] has been successfully completed
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * This data has been updated by another user
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * [@] does not exist
     */
    public static final String NLCM0124W = "NLCM0124W";

    /**
     * Min value of time part
     */
    public static final String MIN_HHMMSS = "000000";

    /**
     * Max value of time part
     */
    public static final String MAX_HHMMSS = "235959";

    /**
     * timestamp format (time part)
     */
    public static final String FORMAT_TIMESTAMP_TIME = "HHMMSS";

    /**
     * A
     */
    public static final String TABLE_A = "A";

    // START 08/12/2013 ADD Defect#1627
    /**
     * TMsg Columns : PO_ORD_NUM
     */
    public static final String PO_ORD_NUM = "poOrdNum";
    /**
     * TMsg Columns : RWS_REF_NUM
     */
    public static final String RWS_REF_NUM = "rwsRefNum";
    /**
     * TMsg Columns : CPO_ORD_NUM
     */
    public static final String CPO_ORD_NUM = "cpoOrdNum";
    /**
     * TMsg Columns : SO_NUM
     */
    public static final String SO_NUM = "soNum";
    /**
     * TMsg Columns : RMA_NUM
     */
    public static final String RMA_NUM = "rmaNum";
    /**
     * TMsg Columns : RCV_RPT_NUM
     */
    public static final String RCV_RPT_NUM = "rcvRptNum";
    /**
     * TMsg Columns : VND_RTRN_NUM
     */
    public static final String VND_RTRN_NUM = "vndRtrnNum";
    /**
     * TMsg Columns : INVTY_ORD_NUM
     */
    public static final String INVTY_ORD_NUM = "invtyOrdNum";
    /**
     * TMsg Columns : WRK_ORD_NUM
     */
    public static final String WRK_ORD_NUM = "wrkOrdNum";
    // END   08/12/2013 ADD Defect#1627 

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NLCL1010_SerialTransactionInquiry";
}
