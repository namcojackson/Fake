/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB014001.constant;

/** <pre>
 * Adjustment from WMS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CSAI            Y.Imazu         Create          N/A
 * 07/18/2016   CITS            T.Wada          Update          
 * 03/02/2017   CITS            Y.Fujii         Update          QC#17653-2
 * 06/12/2017   CITS            T.Kikuhara      Update          QC#18727
 * 01/15/2019   CITS            T.Hakodate      Update          QC#29946
 * 08/29/2019   CITS            K.Ogino         Update          QC#52941
 * 05/15/2020   CITS            K.Fukumura      Update          QC#56071
 * 07/15/2021   CITS            A.Marte         Update          QC#58480
 * </pre>
 */
public class NLGB014001Constant {

    /** Fatch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Business ID */
    public static final String BUSINESS_ID = "NLGB014001";

    /** The entered "Quantity" exceeds the "Current On-Hand Quantity". */
    public static final String NLCM0039E = "NLCM0039E";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0044E = "NLGM0044E";

    /**
     * Warehouse code to be processed is not set. Please check the WMS
     * warehouse table. MW_REPL_TRGT_GRP_CD: [@]
     */
    public static final String NLGM0047E = "NLGM0047E";

    /** [@] Parameter has not been set. */
    public static final String NLGM0049E = "NLGM0049E";

    /** Batch Operation Date cannot be obtained. */
    public static final String NDMM0016E = "NDMM0016E";

    /**
     * An error was returned against the calling API.  APIID[@]  WMS_INBD_TRX_PK[@]  MDSE_CD[@]  WH_CD[@]  MESSAGE[@]
     */
    public static final String NLGM0016E = "NLGM0016E";

    /**
     * An error was returned against the calling API. APIID[@]
     * MSGID[@] MSGTXT[@]
     */
    public static final String NLCM0125E = "NLCM0125E";

    /**
     * Serial # is not set.
     */
    public static final String NLZM2091E = "NLZM2091E";

    /**
     * Adjustment Order can't be created because inventory is not
     * enough. Location: [@], MDSE: [@], SS: [@], Loc Status: [@],
     * Order Qty: [@]
     */
    public static final String NLGM0078E = "NLGM0078E";

    /** Error has occurred during the same transaction process. */
    public static final String NLGM0079 = "NLGM0079";

    /**
     * The specified Serial Number is already allocated by other
     * order.
     */
    public static final String NLZM2409E = "NLZM2409E";

    /**
     * Install Base quantity is shortage.
     */
    public static final String NLZM2430E = "NLZM2430E";

    /**
     * The serial number does not include in the specified configuration.
     */
    public static final String NLZM2452E = "NLZM2452E";

    /**
     * The Machine is not existed. Please check the "Service Machine Master PK".
     */
    public static final String NLZM2500E = "NLZM2500E";

    /**
     * The entered Serial Number is already allocated by other order.
     */
    public static final String NLZM2317E = "NLZM2317E";
    /**
     * There is an error in @.  @, @
     */
    public static final String NLBM1018E = "NLBM1018E";
    /**
     * The Serial # specified exists in other location.
     */
    public static final String NLBM1337E = "NLBM1337E";
    /**
     * Stock status of the specified Serial number is different from IB.
     */
    public static final String NLZM2414E = "NLZM2414E";

    /**
     * The specified Serial Number is located at customer site.
     */
    public static final String NLZM2410E = "NLZM2410E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0008E = "NLGM0008E";

    /**
     * The specified Serial# already exists as different item. Please
     * check the Serial#.
     */
    public static final String NLZM2450E = "NLZM2450E";

    /**
     * WMS process qty is greater than s21 machine qty.
     */
    public static final String NLGM0085W = "NLGM0085W";

    // START 2021/07/15 A.Marte [QC#58480, ADD]
    /**
     * Does not No Single Item. Please request by Config# or use Config Change into a single item. WMS_INBD_TRX_PK[@]  MDSE_CD[@]  WH_CD[@]
     */
    public static final String NLGM0096E = "NLGM0096E";

    /**
     * There are missing items in Config#[@]. Please process it manually. WMS_INBD_TRX_PK[@] WH_CD[@]
     */
    public static final String NLGM0097E = "NLGM0097E";

    /** MACH_MSTR_MAIN_AVAL_FLG Constant */
    public static final String MACH_AVAIL_FLG = "Y";
    // END 2021/07/15 A.Marte [QC#58480, ADD]

    /** Deteal Line Number Constant */
    public static final String LINE_SUB_NUM_001 = "001";

    /**
     * FORMAT_TIMESTAMP
     */
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmss";

    /** Processing mode : Adjustment*/
    public static final String ADJ = "ADJ";

    /** Processing mode : Stock Status Change*/
    public static final String SSC = "SSC";

    /** Key Name: PROC_STS_CD */
    public static final String KEY_PROC_STS_CD = "procStsCd";

    /** Key Name: ERR_MSG_CD */
    public static final String KEY_ERR_MSG_CD = "errMsgCd";

    /** TRANS_KEY_SEPARATOR */
    public static final String TRANS_KEY_SEPARATOR = ":";

    /** KEY_QTY_SIGN_PLUS */
    public static final String KEY_QTY_SIGN_PLUS = "+";

    /** KEY_QTY_SIGN_MINUS */
    public static final String KEY_QTY_SIGN_MINUS = "-";

    /** Default Interval time 24 hours (millisecond) */
    public static final long DEF_INTVL_TIME = 24 * 60 * 60 * 1000;

    /** Minutes to MilliSecond */
    public static final long MIN_TO_MILLSEC = 60 * 1000;
    
    /** Hour to MilliSecond */
    public static final long HOUR_TO_MILLSEC =60 *  60 * 1000;

    /** Add QC#52941. Mail Line Separator */
    public static final String LINE_SEPT = System.getProperty("line.separator");

    /** Add QC#52941. Value Blank */
    public static final String VAL_BLANK_14 = "              ";

    /** Add QC#52941. Error Mail Split Line */
    public static final String VAL_SEP_LINE = "----------------------------------------------------------------------------------------------------";

    /** Add QC#52941 */
    public static final int NOT_SKIP = 0;

    /** Add QC#52941 */
    public static final int SKIP = 1;

    /** Add QC#52941 */
    public static final int EXPIRED = 2;
    
    /** Add QC#56071 */
    public static final String WH_GP_CD_TECSYS = "1";
    
    /** Add QC#56071 */
    public static final String ADUST_RESON_DEFAULT_COMMENTS = "SYSTEM COMMENTS";

}
