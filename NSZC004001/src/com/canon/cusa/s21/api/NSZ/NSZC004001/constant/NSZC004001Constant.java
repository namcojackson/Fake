/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC004001.constant;

/**
 * <pre>
 * NSZC002001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/31/2013   Fujitsu         J.Sakamoto      Create
 * 09/15/2015   Hitachi         A.Kohinata      Update          NA#Add Param FSR_VISIT_STS_CD
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 01/09/2018   Hitachi         U.Kim           Update          QC#19702
 * </pre>
 */
public interface NSZC004001Constant {

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";

    /** Input parameter "FSR Number" is a mandatory field. */
    String NSZM0291E = "NSZM0291E";

    /** Input parameter "FSR VISIT Number" is a mandatory field. */
    String NSZM0292E = "NSZM0292E";

    /** Input parameter "Service Task Number" is a mandatory field. */
    String NSZM0082E = "NSZM0082E";

    /** Input parameter "Technician Code" is a mandatory field. */
    String NSZM0052E = "NSZM0052E";

    /** Failed to update the SVC_TASK. */
    String NSZM0167E = "NSZM0167E";

    /** Input parameter "User ID" is a mandatory field. */
    String NSZM0293E = "NSZM0293E";

    /**
     * Input parameter "Service Charge Continuation Flag" is a
     * mandatory field.
     */
    String NSZM0294E = "NSZM0294E";

    /** The entered 'FSR Number' does not exist. */
    String NSZM0295E = "NSZM0295E";

    /** The entered 'FSR VISIT Number' does not exist. */
    String NSZM0296E = "NSZM0296E";

    /** The entered 'Tech Code' does not exist. */
    String NSZM0287E = "NSZM0287E";

    /**
     * The process cannot be executed because the "FSR VISIT Status"
     * is not "Completed".
     */
    String NSZM0297E = "NSZM0297E";

    /** It failed to insert the FSR_VISIT. */
    String NSZM0298E = "NSZM0298E";

    /** It failed to update the FSR_VISIT. */
    String NSZM0299E = "NSZM0299E";

    /** It failed to update the FSR_EVENT. */
    String NSZM0300E = "NSZM0300E";

    /**
     * Next FSR Visit Number already exists.
     */
    String NSZM0301E = "NSZM0301E";

    /** The entered 'Svc Task Number' does not exist. */
    String NSZM0302E = "NSZM0302E";

    /**
     * Input parameter "Bypass Preferred Tech Flag" is a mandatory
     * field.
     */
    String NSZM0582E = "NSZM0582E";

    /**
     * Input parameter "Process Mode" is a mandatory field.
     */
    String NSZM0003E = "NSZM0003E";

    // Add Start 09/15/2015 [NA#Add Param FSR_VISIT_STS_CD]
    /** Input parameter "FSR Visit Status Code" is a mandatory field. */
    String NSZM0624E = "NSZM0624E";
    // Add End 09/15/2015 [NA#Add Param FSR_VISIT_STS_CD]

    /** The entered Resource as 'Technician' does not exist. */
    String NSZM0602E = "NSZM0602E";

    /** The entered Resource as 'Technician' is not active. */
    String NSZM0603E = "NSZM0603E";

    /** Failed to search Task Status. */
    String NSZM0728E = "NSZM0728E";

    /** Process Mode is not valid. */
    String NSZM0039E = "NSZM0039E";

    /** PROCESS_MODE_CONTINUOUS_CALL : 01 */
    String PROCESS_MODE_CONTINUOUS_CALL = "01";

    /** PROCESS_MODE_ADD_TASK : 02 */
    String PROCESS_MODE_ADD_TASK = "02";

//NA#ASCC CLICK BugFix ADD Start
    /** PROCESS_MODE_ADD_TASK : 03 */
    String PROCESS_MODE_FOLLOWUP_TASK = "03";

    /** PROCESS_MODE_CALLAVOID_DISPTTECH : 04 */
    String PROCESS_MODE_CALLAVOID_DISPTTECH = "04";
//NA#ASCC CLICK BugFix ADD End
    // START 2018/01/09 U.Kim [QC#19702, ADD]
    /** Waiting second for find by key*/
    String WAIT_SEC_UPD_TASK_OTHER = "WAIT_SEC_UPD_TASK_OTHER";
    // END 2018/01/09 U.Kim [QC#19702, ADD]

}
