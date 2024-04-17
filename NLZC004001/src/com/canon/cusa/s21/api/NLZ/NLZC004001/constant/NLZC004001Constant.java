/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC004001.constant;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;

/**
 * <pre>
 * Adjustment Order API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CSAI            Y.Imazu         Create          CSA
 * 04/12/2018   CITS            S.Katsuma       Update          SOL#078,160
 * 04/23/2020   CITS            M.Furugoori     Update          QC#56461
 * 10/29/2021   CITS            R.Azucena       Update          QC#58899
 * </pre>
 */
public class NLZC004001Constant {

    /*****************************************************************
     * Process Type
     ****************************************************************/

    /** Process Type : Create */
    public static final String PROC_TP_CRAT = "1";

    /** Process Type : Close */
    public static final String PROC_TP_CLO = "3";

    // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
    /** Process Type : Cancel */
    public static final String PROC_TP_CANC = "4";

    /** Process Type : Create & Close */
    public static final String PROC_TP_CRAT_CLO = "5";

    /** Process Type List */
    private static final String[] PROC_TYPE = {PROC_TP_CRAT, PROC_TP_CLO, PROC_TP_CANC, PROC_TP_CRAT_CLO };
    // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

    /**
     * getProcType
     * @return PROC_TYPE[]
     */
    public static String[] getProcType() {

        String[] procType = PROC_TYPE;
        return procType;
    }

    /*****************************************************************
     * Data Type
     ****************************************************************/

    /** Data Type : Header */
    public static final String DT_TP_HDR = NLZC003001.DT_TP_HDR;

    /** Data Type : Detail */
    public static final String DT_TP_DTL = NLZC003001.DT_TP_DTL;

    /** Data Type List */
    private static final String[] DATA_TYPE = {DT_TP_HDR, DT_TP_DTL };

    /**
     * getDataType
     * @return DATA_TYPE[]
     */
    public static String[] getDataType() {

        String[] dataType = DATA_TYPE;
        return dataType;
    }

    /*****************************************************************
     * Request Type
     ****************************************************************/

    /** Request Type Code : Stock-in */
    public static final String RQST_STOCK_IN = NLZC002001.RQST_STOCK_IN;

    /** Request Type Code : Stock-out */
    public static final String RQST_STOCK_OUT = NLZC002001.RQST_STOCK_OUT;

    /*****************************************************************
     * Inventory Order Type
     ****************************************************************/

    /** Inventory Order Type List */
    // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
    private static final String[] INVTY_ORD_TYPE = {INVTY_ORD_TP.ADJUSTMENT, INVTY_ORD_TP.SUB_WH_CHANGE };
    // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]

    /**
     * getInvtyOrdType
     * @return INVTY_ORD_TYPE[]
     */
    public static String[] getInvtyOrdType() {

        String[] invtyOrdType = INVTY_ORD_TYPE;
        return invtyOrdType;
    }

    /*****************************************************************
     * Error Message
     ****************************************************************/

    /** This is an unauthorized Process Type. */
    public static final String NLZM0001E = "NLZM0001E";

    /** Data Company Code is not entered. */
    public static final String NLZM0003E = "NLZM0003E";

    /** Merchandise Code is not entered. */
    public static final String NLZM0005E = "NLZM0005E";

    /** Location Status Code is not entered. */
    public static final String NLZM0007E = "NLZM0007E";

    /** Stock Status Code is not entered. */
    public static final String NLZM0008E = "NLZM0008E";

    /** Data does not exist in Merchandise Master. */
    public static final String NLZM0021E = "NLZM0021E";

    /** Transaction Source Type Code is not entered. */
    public static final String NLZM0026E = "NLZM0026E";

    /** Incorrect Data Type. */
    public static final String NLZM0047E = "NLZM0047E";

    /** Inventory Order Number is not entered. */
    public static final String NLZM0048E = "NLZM0048E";

    /** Target Inventory Order is being updated by another user. */
    public static final String NLZM0050E = "NLZM0050E";

    /** Process Type has an invalid value. */
    public static final String NLCM0052E = "NLCM0052E";

    /** System Source Code is not entered. */
    public static final String NLZM0056E = "NLZM0056E";

    /** Data does not exist in Global Company. */
    public static final String NLZM0095E = "NLZM0095E";

    /** Data does not exist in Inventory Order. */
    public static final String NLZM0096E = "NLZM0096E";

    /** Incorrect Inventory Order Type. */
    public static final String NLZM0115E = "NLZM0115E";

    /**
     * Incorrect combination between the specified process and the
     * Inventory Order Type.
     */
    public static final String NLZM0117E = "NLZM0117E";

    /** Sales Date is not set. */
    public static final String NLZM2079E = "NLZM2079E";

    /** Order Qty is not set. */
    public static final String NLZM2083E = "NLZM2083E";

    /** For close mode, please set Header to data type. */
    public static final String NLZM2373E = "NLZM2373E";

    /** Inventory Order Type is not entered. */
    public static final String NLZM2374E = "NLZM2374E";

    /** Process Type is not entered. */
    public static final String NLZM2375E = "NLZM2375E";

    /** Data Type is not entered. */
    public static final String NLZM2376E = "NLZM2376E";

    /** Adjustment Transaction Type is not found. */
    public static final String NLZM2377E = "NLZM2377E";

    /** There are multiple Process Type. */
    public static final String NLZM2378E = "NLZM2378E";

    /**
     * Transaction Code or Transaction Reason Code could not be
     * obtained from the Adjustment Transaction Type Table.
     */
    public static final String NLZM2379E = "NLZM2379E";

    /** Inventory Location Code is not entered. */
    public static final String NLZM2380E = "NLZM2380E";

    /** Adjustment Transaction Type is not entered. */
    public static final String NLZM2381E = "NLZM2381E";

    /** Please set value except 0 to the order qty. */
    public static final String NLZM2382E = "NLZM2382E";

    /** Detail Inventory Location is not entered. */
    public static final String NLZM2383E = "NLZM2383E";

    /** Detail Location Status is not entered. */
    public static final String NLZM2384E = "NLZM2384E";

    /** Any of COA Segments is not entered. */
    public static final String NLZM2385E = "NLZM2385E";

    /** The Serial # specified exists in other location. */
    public static final String NLBM1337E = "NLBM1337E";

    /**
     * The serial number does not include in the specified
     * configuration.
     */
    public static final String NLZM2452E = "NLZM2452E";

    /** The specified Serial # already exists. */
    public static final String NLZM2408E = "NLZM2408E";

    /**
     * The specified Serial Number is already allocated by other
     * order.
     */
    public static final String NLZM2409E = "NLZM2409E";

    /** The specified Serial Number is located at customer site. */
    public static final String NLZM2410E = "NLZM2410E";

    /**
     * The specified Serial number is the component of the other
     * configuration.
     */
    public static final String NLZM2411E = "NLZM2411E";

    /**
     * The specified Serial number is the Main Unit of the
     * configuration.
     */
    public static final String NLZM2412E = "NLZM2412E";

    /**
     * Location status of the specified Serial number is different
     * from IB.
     */
    public static final String NLZM2413E = "NLZM2413E";

    /**
     * Stock status of the specified Serial number is different from
     * IB.
     */
    public static final String NLZM2414E = "NLZM2414E";

    /** The specified Configuration does not exist. */
    public static final String NLZM2423E = "NLZM2423E";

    /**
     * The specified Configuration is already allocated by other
     * order.
     */
    public static final String NLZM2424E = "NLZM2424E";

    /** The specified Configuration exists in other location. */
    public static final String NLZM2425E = "NLZM2425E";

    /**
     * The location status of the specified Configuration is different
     * from Main Machine.
     */
    public static final String NLZM2426E = "NLZM2426E";

    /** The status of the specified Configuration is inactive. */
    public static final String NLZM2427E = "NLZM2427E";

    /** Install Base cannot be uniquely specified. */
    public static final String NLZM2428E = "NLZM2428E";

    /** The specified Serial Number does not exist. */
    public static final String NLZM2429E = "NLZM2429E";

    /** Install Base quantity is shortage. */
    public static final String NLZM2430E = "NLZM2430E";

    /** There is no Install Base to be processed. */
    public static final String NLZM2431E = "NLZM2431E";

    /*****************************************************************
     * Warning Message
     ****************************************************************/

    /** The Serial # specified exists in other location. */
    public static final String NLBM1337W = "NLBM1337W";

    /**
     * The serial number does not include in the specified
     * configuration.
     */
    public static final String NLZM2452W = "NLZM2452W";

    /** The specified Serial # already exists. */
    public static final String NLZM2408W = "NLZM2408W";

    /**
     * The specified Serial Number is already allocated by other
     * order.
     */
    public static final String NLZM2409W = "NLZM2409W";

    /** The specified Serial Number is located at customer site. */
    public static final String NLZM2410W = "NLZM2410W";

    /**
     * The specified Serial number is the component of the other
     * configuration.
     */
    public static final String NLZM2411W = "NLZM2411W";

    /**
     * The specified Serial number is the Main Unit of the
     * configuration.
     */
    public static final String NLZM2412W = "NLZM2412W";

    /**
     * Location status of the specified Serial number is different
     * from IB.
     */
    public static final String NLZM2413W = "NLZM2413W";

    /**
     * Stock status of the specified Serial number is different from
     * IB.
     */
    public static final String NLZM2414W = "NLZM2414W";

    /** The specified Configuration does not exist. */
    public static final String NLZM2423W = "NLZM2423W";

    /**
     * The specified Configuration is already allocated by other
     * order.
     */
    public static final String NLZM2424W = "NLZM2424W";

    /** The specified Configuration exists in other location. */
    public static final String NLZM2425W = "NLZM2425W";

    /**
     * The location status of the specified Configuration is different
     * from Main Machine.
     */
    public static final String NLZM2426W = "NLZM2426W";

    /** The status of the specified Configuration is inactive. */
    public static final String NLZM2427W = "NLZM2427W";

    /** Install Base cannot be uniquely specified. */
    public static final String NLZM2428W = "NLZM2428W";

    /** The specified Serial Number does not exist. */
    public static final String NLZM2429W = "NLZM2429W";

    /** Install Base quantity is shortage. */
    public static final String NLZM2430W = "NLZM2430W";

    /** There is no Install Base to be processed. */
    public static final String NLZM2431W = "NLZM2431W";

    // START 04/23/2020 [QC#56461,ADD]
    /**
     * The location Qty is a negative number.
     * WH:[@] Item#:[@] Stock Status#:[@]
     */
    public static final String NLZM2525E = "NLZM2525E";
    // END   04/23/2020 [QC#56461,ADD]

    /*****************************************************************
     * Constant
     ****************************************************************/

    /** Line Num length : 3 */
    public static final int LINE_NUM_LENGTH = 3;

    /** TimeStamp Format */
    public static final String TIMESTAMPFORMAT_MS = "yyyyMMddHHmmssSSS";

    /** TimeStamp Format */
    public static final String TIMESTAMPFORMAT = "yyyyMMddHHmmss";

    /** MODE : NO_WARNING */
    public static final String MODE_NO_WARNING = "1";

    // START 2021/10/29 R.Azucena[QC#58899, ADD]
    /** Comma Separator */
    public static final String COMMA_SEP = ",";
    // END 2021/10/29 R.Azucena[QC#58899, ADD]
}
