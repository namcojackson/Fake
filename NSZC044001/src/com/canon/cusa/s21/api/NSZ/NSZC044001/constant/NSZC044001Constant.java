/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC044001.constant;

/**
 * <pre>
 * Call Avoidance API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/04/2015   Fujitsu         Y.Kamide        Create
 * 06/28/2018   CITS            M.Naito         Update          QC#26858
 *</pre>
 */
public interface NSZC044001Constant {
    // -------------- Mode ---------------
    /** Mode: 01 : Call Resolve */
    String MODE_CALL_RESOLVE = "01";

    /** Mode: 02 : Dispatch Tech */
    String MODE_DISPATCH_TECH = "02";

    /** Mode: 03 : Need More Time */
    String MODE_NEED_MORE_TIME = "03";

    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";

    /** Input parameter "Process Mode" is a mandatory field. */
    String NSZM0003E = "NSZM0003E";

    /** Input parameter "Bill To Code" is a mandatory field. */
    String NSZM0015E = "NSZM0015E";

    /** Input parameter "Ship To Code" is a mandatory field. */
    String NSZM0017E = "NSZM0017E";

    /**
     * Input parameter "Service Call Priority Code" is a mandatory
     * field.
     */
    String NSZM0043E = "NSZM0043E";

    /**
     * Input parameter "DS Service Call Type Code" is a mandatory
     * field.
     */
    String NSZM0049E = "NSZM0049E";

    /**
     * Input parameter "Service Task Received Date" is a mandatory
     * field.
     */
    String NSZM0053E = "NSZM0053E";

    /**
     * Input parameter "Service Task Received Time" is a mandatory
     * field.
     */
    String NSZM0054E = "NSZM0054E";

    /** Input parameter "Execute User Id" is a mandatory field. */
    String NSZM0163E = "NSZM0163E";

    /** Input parameter "xxSvcTaskList" is a mandatory array. */
    String NSZM0164E = "NSZM0164E";

    /**
     * "Service Call Complete Type Code" does not exist in the master.
     */
    String NSZM0212E = "NSZM0212E";

    /**
     * Input parameter "Service Problem Type Code" is a mandatory
     * field.
     */
    String NSZM0243E = "NSZM0243E";

    /**
     * Input parameter "Service Problem Location Type Code" is a
     * mandatory field.
     */
    String NSZM0244E = "NSZM0244E";

    /**
     * Input parameter "Service Problem Reason Type Code" is a
     * mandatory field.
     */
    String NSZM0245E = "NSZM0245E";

    /**
     * Input parameter "Service Problem Correct Type Code" is a
     * mandatory field.
     */
    String NSZM0246E = "NSZM0246E";

    /**
     * Input parameter "Service Call Complete Type Code" is a
     * mandatory field.
     */
    String NSZM0466E = "NSZM0466E";

    /**
     * Input parameter "Service Call Source Type Code" is a mandatory
     * field.
     */
    String NSZM0544E = "NSZM0544E";

    /**
     * Input parameter "Service Call Avoidance Code" is a mandatory
     * field.
     */
    String NSZM0545E = "NSZM0545E";

    /**
     * Input parameter "Service Call Request Owner TOC Code" is a
     * mandatory field.
     */
    String NSZM0546E = "NSZM0546E";

    /** The entered 'DS Service Call Type Code' does not exist. */
    String NSZM0549E = "NSZM0549E";

    /** Input parameter "Service Task Type Code" is a mandatory field. */
    String NSZM0561E = "NSZM0561E";

    /** Input parameter "Sales Date" is a mandatory field. */
    String NSZM0580E = "NSZM0580E";

    /** The entered 'Service Problem Type Code' does not exist. */
    String NSZM0550E = "NSZM0550E";

    /** The entered 'Service Problem Location Type Code' does not exist. */
    String NSZM0729E = "NSZM0729E";

    /** The entered 'Service Problem Reason Type Code' does not exist. */
    String NSZM0730E = "NSZM0730E";

    /** The entered 'Service Problem Correct Type Code' does not exist. */
    String NSZM0731E = "NSZM0731E";

    /** multiple : * */
    String MULTIPLE = "*";

    // START 2018/06/28 M.Naito [QC#26858,ADD]
    /** SVC_PBLM_LOC_TP : NO PROBLEM FOUND */
    String SVC_PBLM_LOC_TP_NO_PROBLEM_FOUND = "0000";

    /** SVC_PBLM_RSN_TP : OTHER */
    String SVC_PBLM_RSN_TP_OTHER = "99";

    /** SVC_PBLM_CRCT_TP : Unsuccessful Phone Fix */
    String SVC_PBLM_CRCT_TP_UNSUCCESSFUL_PHONE_FIX = "62";

    /** SVC_PBLM_CRCT_TP : Dispatch Phone Fix */
    String SVC_PBLM_CRCT_TP_DISPATCH_PHONE_FIX = "60";
    // END 2018/06/28 M.Naito [QC#26858,ADD]
}
