/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC012001.constant;

/**
 * <pre>
 * Technician Arrived Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/20/2013   Fujitsu         M.Nakamura      Create
 * 01/15/2018   Hitachi         U.Kim           Update          QC#19702
 * </pre>
 */
public class NSZC012001Constant {

    /** PROCESS MODE ETA. */
    public static String PROCESS_MODE_ETA = "01";

    /** PROCESS MODE ARRIVED. */
    public static String PROCESS_MODE_ARRIVED = "02";

    /** Format System Time stamp **/
    public static String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static String NSZM0002E = "NSZM0002E";

    /** Input parameter "Process Mode" is a mandatory field. */
    public static String NSZM0003E = "NSZM0003E";

    /** Failed to insert a FSR Event record. */
    public static String NSZM0083E = "NSZM0083E";

    /** Input parameter "FSR#" is a mandatory field. */
    public static String NSZM0124E = "NSZM0124E";

    /** Input parameter "FSR Visit#" is a mandatory field. */
    public static String NSZM0125E = "NSZM0125E";

    /** Input parameter "Task#" is a mandatory field. */
    public static String NSZM0156E = "NSZM0156E";

    /** Input parameter "Execute User Id" is a mandatory field. */
    public static String NSZM0163E = "NSZM0163E";

    /** The entered 'FSR Number' does not exist. */
    public static String NSZM0295E = "NSZM0295E";

    /** The entered 'FSR VISIT Number' does not exist. */
    public static String NSZM0296E = "NSZM0296E";

    /** It failed to update the FSR_VISIT. */
    public static String NSZM0299E = "NSZM0299E";

    /** The entered 'Svc Task Number' does not exist. */
    public static String NSZM0302E = "NSZM0302E";

    /** Input parameter "FSR Visit ETA Date" is a mandatory field. */
    public static String NSZM0424E = "NSZM0424E";

    /** Input parameter "FSR Visit ETA Time" is a mandatory field. */
    public static String NSZM0425E = "NSZM0425E";

    /** Input parameter "FSR Visit Arrival Date" is a mandatory field. */
    public static String NSZM0426E = "NSZM0426E";

    /** Input parameter "FSR Visit Arrival Time" is a mandatory field. */
    public static String NSZM0427E = "NSZM0427E";

    /** Arrived Date/Time is the date and time in the past than Dispatch Date/Time. */
    public static String NSZM0444E = "NSZM0444E";

    /** The process cannot be updated because the "ETA Date/Time" does not match. */
    public static String NSZM0453E = "NSZM0453E";

    /** The process cannot be updated because the "Arrived Date/Time" does not match. */
    public static String NSZM0454E = "NSZM0454E";

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    /** Waiting second for find by key*/
    public static String WAIT_SEC_UPD_TASK_OTHER = "WAIT_SEC_UPD_TASK_OTHER";
    // END 2018/01/15 U.Kim [QC#19702, ADD]
}
