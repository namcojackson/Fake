/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC038001.constant;

/**
 * <pre>
 * Service Credit Review API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         K.Yamada        Create          N/A
 * 2015/12/14   Hitachi         K.Yamada        Update          CSA QC#895
 * 2016/03/23   Hitachi         T.Tomita        Update          CSA QC#895
 * 2016/11/18   Hitachi         T.Mizuki        Update          CSA QC#15954
 * 2017/09/08   Hitachi         T.Tomita        Update          CSA QC#19242
 * 2019/02/15   Hitachi         K.Kitachi       Update          CSA QC#30165
 *</pre>
 */
public class NSZC038001Constant {

    /** Process Mode : Approve */
    public static final String MODE_APPROVE = "01";

    /** Process Mode : Reject */
    public static final String MODE_REJECT = "02";

    /** An input parameter, [@],  has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** Process Mode is not valid. */
    public static final String NSZM0039E = "NSZM0039E";

    /** FSR is not found. */
    public static final String NSZM0498E = "NSZM0498E";

    /** Failed to update the SVC_TASK. */
    public static final String NSZM0167E = "NSZM0167E";

    /** Failed to update the SVC_TASK_HLD. */
    public static final String NSZM0203E = "NSZM0203E";

    /** Failed to insert a Service Memo record. */
    public static final String NSZM0081E = "NSZM0081E";

    /** FSR Status is incorrect. */
    public static final String NSZM0193E = "NSZM0193E";

    // add start 2015/12/14 CSA Defect#895
    /** FSR_VISIT is not found. */
    public static final String NSZM0485E = "NSZM0485E";

    /** The @ is incorrect. Please check it. */
    public static final String NSZM0629E = "NSZM0629E";

    /** The entered Resource as 'Technician' does not exist. */
    public static final String NSZM0602E = "NSZM0602E";

    /** Failed to update the FSR_VISIT. */
    public static final String NSZM0169E = "NSZM0169E";

    /** Failed to update the FSR_EVENT. */ 
    public static final String NSZM0300E = "NSZM0300E";
    // add end 2015/12/14 CSA Defect#895
    // add start 2016/08/01 CSA Defect#4324
    /** You can not release the credit hold. Please confirm the credit profile for this customer.*/
    public static final String NSZM1045E = "NSZM1045E";
    // add end 2016/08/01 CSA Defect#4324

    /** Approve comment */
    public static final String APRV_CMNT = "The Credit check has been approved.";

    /** Approve comment */
    public static final String REJ_CMNT = "The Credit check has been rejected.";

    // add start 2016/03/23 CSA Defect#895
    /** Process OK */
    public static final int PROC_OK = 0;

    /** Process NG */
    public static final int PROC_NG = 9;

    /** Process None */
    public static final int PROC_NONE = 1;
    // add end 2016/03/23 CSA Defect#895

    // mod start 2016/11/18 CSA QC#15954
    /** TS_POSTFIX : 000 */
    public static final String TS_POSTFIX = "000";

    /** Format System Time stamp **/
    public static final String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /** Date Time length */
    public static final int LEN_DT_TM = 14;

    /** Date length */
    public static final int LEN_DT = 8;
    // mod end 2016/11/18 CSA QC#15954
    // Add Start 2017/09/08 QC#19242
    /** VAR_CHAR_CONST_NM: OUT_TRTY_SVC_BR_CD */
    public static final String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";
    // Add End 2017/09/08 QC#19242

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** String : "," */
    public static final String STR_COMMA = ",";

    /** VAR_CHAR_CONST_NM: CLICK_SEND_EXCL_CALL_TP */
    public static final String CLICK_SEND_EXCL_CALL_TP = "CLICK_SEND_EXCL_CALL_TP";
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]
}
