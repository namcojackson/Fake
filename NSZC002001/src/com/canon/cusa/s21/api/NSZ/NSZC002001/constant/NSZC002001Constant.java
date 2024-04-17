/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC002001.constant;

/**
 * <pre>
 * NSZC002001Constant
 * Service Task Creation/Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/02/2013   Fujitsu         J.Sakamoto      Create
 * 08/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 02/10/2016   Hitachi         T.Iwamoto       Update          CSA QC#2863
 * 01/09/2018   Hitachi         U.Kim           Update          CSA QC#19702
 * 2019/10/24   Hitachi         K.Fujimoto      Update          QC#53441
 * 2022/02/10   Hitachi         D.Yoshida       Update          CSA QC#57338
 * </pre>
 */
public interface NSZC002001Constant {

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";

    /** Input parameter "Process Mode" is a mandatory field. */
    String NSZM0003E = "NSZM0003E";

    /** The input is not a defined "Process Mode". */
    String NSZM0004E = "NSZM0004E";

    /** Input parameter "Serial Number" is a mandatory field. */
    String NSZM0012E = "NSZM0012E";

    /** Input parameter "Merchandise Code" is a mandatory field. */
    String NSZM0013E = "NSZM0013E";

    /** Process Mode is not valid. */
    String NSZM0039E = "NSZM0039E";

    // /** Input parameter "Service Zone Code" is a mandatory field.
    // */
    // String NSZM0040E = "NSZM0040E";
    //
    // /** Input parameter "Service Page Code" is a mandatory field.
    // */
    // String NSZM0041E = "NSZM0041E";

    /**
     * Input parameter "First Product Control Code" is a mandatory
     * field.
     */
    String NSZM0042E = "NSZM0042E";

    /**
     * Input parameter "Service Call Priority Code" is a mandatory
     * field.
     */
    String NSZM0043E = "NSZM0043E";

    /** Input parameter "Model Name" is a mandatory field. */
    String NSZM0044E = "NSZM0044E";

    /** Input parameter "Model Group Name" is a mandatory field. */
    String NSZM0045E = "NSZM0045E";

    /** An error occurred when attempting to change date format. */
    String NSZM0046E = "NSZM0046E";

    /** Input parameter "Ship To Customer Code" is a mandatory field. */
    String NSZM0047E = "NSZM0047E";

    /**
     * Input parameter "Customer Telephone Number" is a mandatory
     * field.
     */
    String NSZM0048E = "NSZM0048E";

    /**
     * Input parameter "DS Service Call Type Code" is a mandatory
     * field.
     */
    String NSZM0049E = "NSZM0049E";

    /** Input parameter "Service Bill Type Code" is a mandatory field. */
    String NSZM0050E = "NSZM0050E";

    /**
     * Input parameter "Service Trouble Symptom Code" is a mandatory
     * field.
     */
    String NSZM0051E = "NSZM0051E";

    /** Input parameter "Technician Code" is a mandatory field. */
    String NSZM0052E = "NSZM0052E";

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

    /**
     * Input parameter "Service Task Received Time Zone" is a
     * mandatory field.
     */
    String NSZM0055E = "NSZM0055E";

    /**
     * Input parameter "Service Response Group Code" is a mandatory
     * field.
     */
    String NSZM0056E = "NSZM0056E";

    /** Input parameter "Parts Charge Flag" is a mandatory field. */
    String NSZM0057E = "NSZM0057E";

    /**
     * Input parameter "Service Labor Charge Flag" is a mandatory
     * field.
     */
    String NSZM0058E = "NSZM0058E";

    /**
     * Input parameter "Service Labor Unit Quantity" is a mandatory
     * field.
     */
    String NSZM0059E = "NSZM0059E";

    /**
     * Input parameter "Service Labor Unit Amount" is a mandatory
     * field.
     */
    String NSZM0060E = "NSZM0060E";

    /**
     * Input parameter "Service Labor Full Deal Amount" is a mandatory
     * field.
     */
    String NSZM0061E = "NSZM0061E";

    /**
     * Input parameter "Service Labor Full Function Amount" is a
     * mandatory field.
     */
    String NSZM0062E = "NSZM0062E";

    /** Input parameter "Service Labor Tax Rate" is a mandatory field. */
    String NSZM0063E = "NSZM0063E";

    /**
     * Input parameter "Service Labor Deal Tax Amount" is a mandatory
     * field.
     */
    String NSZM0064E = "NSZM0064E";

    /**
     * Input parameter "Service Labor Function Tax Amount" is a
     * mandatory field.
     */
    String NSZM0065E = "NSZM0065E";

    /**
     * Input parameter "Service Labor Discount Rate" is a mandatory
     * field.
     */
    String NSZM0066E = "NSZM0066E";

    /**
     * Input parameter "Service Labor Deal Discount Amount" is a
     * mandatory field.
     */
    String NSZM0067E = "NSZM0067E";

    /**
     * Input parameter "Service Labor Function Discount Amount" is a
     * mandatory field.
     */
    String NSZM0068E = "NSZM0068E";

    /**
     * Input parameter "Original Invoice Currency Code" is a mandatory
     * field.
     */
    String NSZM0069E = "NSZM0069E";

    /** Input parameter "Invoice Currency Code" is a mandatory field. */
    String NSZM0070E = "NSZM0070E";

    /** Input parameter "Currency Exchange Rate" is a mandatory field. */
    String NSZM0071E = "NSZM0071E";

    /** Input parameter "Service Comment Text" is a mandatory field. */
    String NSZM0072E = "NSZM0072E";

    /** Input parameter "SO Number" is a mandatory field. */
    String NSZM0073E = "NSZM0073E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    String NSZM0074E = "NSZM0074E";

    /**
     * Input parameter "Customer Machine Control Number" is a
     * mandatory field.
     */
    String NSZM0075E = "NSZM0075E";

    /** The entered 'Technician Code' does not exist. */
    String NSZM0076E = "NSZM0076E";

    /**
     * Input parameter "Service Task Status Code" is a mandatory
     * field.
     */
    String NSZM0077E = "NSZM0077E";

    /** Failed to remove a Service Memo record. */
    String NSZM0078E = "NSZM0078E";

    /** Failed to search a Service Task record. */
    String NSZM0079E = "NSZM0079E";

    /** Failed to insert a Service Task record. */
    String NSZM0080E = "NSZM0080E";

    /** Failed to insert a Service Memo record. */
    String NSZM0081E = "NSZM0081E";

    /** Input parameter "Service Task Number" is a mandatory field. */
    String NSZM0082E = "NSZM0082E";

    /** Failed to insert a FSR Event record. */
    String NSZM0083E = "NSZM0083E";

    /** The entered 'Ship To Customer Code' does not exist. */
    String NSZM0084E = "NSZM0084E";

    /** Invalid Date. Date cannot be a future date. */
    String NSZM0085E = "NSZM0085E";

    /** Failed to remove a Service Task record. */
    String NSZM0086E = "NSZM0086E";

    /** The entered 'SO Number' does not exist. */
    String NSZM0087E = "NSZM0087E";

    /** The entered 'First Product Control Code' does not exist. */
    String NSZM0088E = "NSZM0088E";

    /** The entered 'Service Machine Master PK' does not exist. */
    String NSZM0089E = "NSZM0089E";

    /** The entered 'Model Name' does not exist. */
    String NSZM0090E = "NSZM0090E";

    /**
     * Input parameter "Machine Down Flag" is a mandatory field.
     */
    String NSZM0281E = "NSZM0281E";

    /**
     * Please enter Serial# or SO#.
     */
    String NSZM0307E = "NSZM0307E";

    /** The entered 'Symptom Code' does not exist. */
    String NSZM0285E = "NSZM0285E";

    /** The entered 'Currency Code' does not exist. */
    String NSZM0286E = "NSZM0286E";

    /** The entered 'Tech Code' does not exist. */
    String NSZM0287E = "NSZM0287E";

    /** 'Serial#, Customer Ref#, Model Name' are not consistent. */
    String NSZM0288E = "NSZM0288E";

    /** Time is invalid. Please use a valid format, [hh:mm] */
    String NSZM0303E = "NSZM0303E";

    /** Time is invalid. Please use a valid format, [hh:mm] */
    String NSZM0304E = "NSZM0304E";

    /** Target Serial# is currently processing. */
    String NSZM0310E = "NSZM0310E";

    /** 'Previous Task' does not exist. */
    String NSZM0311E = "NSZM0311E";

    /** It failed to update the SVC_TASK_HLD. */
    String NSZM0203E = "NSZM0203E";

    /** Input parameter "Layer" is a mandatory field. */
    String NSZM0384E = "NSZM0384E";

    /** Input parameter "Org Code" is a mandatory field. */
    String NSZM0385E = "NSZM0385E";

    /**
     * Input parameter "Service Call Request Owner TOC Code" is a
     * mandatory field.
     */
    String NSZM0546E = "NSZM0546E";

    /**
     * The entered 'Svc Call Request Owner TOC Code' does not exist.
     */
    String NSZM0554E = "NSZM0554E";

    /**
     * Input parameter "Service Task Type Code" is a mandatory field.
     */
    String NSZM0561E = "NSZM0561E";

    /**
     * Input parameter "First Service Task Flag" is a mandatory field.
     */
    String NSZM0562E = "NSZM0562E";

    /**
     * Input parameter "FSR Event Execution User ID" is a mandatory
     * field.
     */
    String NSZM0563E = "NSZM0563E";

    /**
     * The entered 'Service Task Type Code' does not exist.
     */
    String NSZM0564E = "NSZM0564E";

    /**
     * The entered 'FSR Event Execution User ID' does not exist.
     */
    String NSZM0565E = "NSZM0565E";

    /**
     * "Customer PO Date" is entered in wrong format.
     */
    String NSZM0566E = "NSZM0566E";

    /**
     * The combination of "DS Service Call Type Code" and
     * "Service Task Type Code" is incorrect.
     */
    String NSZM0567E = "NSZM0567E";

    /**
     * A required privilege is not held by @.
     */
    String NSZM0568E = "NSZM0568E";

//NA#ASCC CLICK ADD Start
    /**
     * Multiple Recall Type which are related with call type have been registered.
     */
    String NSZM0592E = "NSZM0592E";

    /** The entered 'Resource' as Branch Manager does not exist. */
    String NSZM0596E = "NSZM0596E";

    /** The entered Resource as 'Branch Manager' is not active. */
    String NSZM0590E = "NSZM0590E";

    /** The entered Resource as 'Territory Manager' does not exist. */
    String NSZM0598E = "NSZM0598E";

    /** The entered Resource as 'Territory Manager' is not active. */
    String NSZM0600E = "NSZM0600E";

    /** The entered Resource as 'Team Manager' does not exist. */
    String NSZM0599E = "NSZM0599E";

    /** The entered Resource as 'Team Manager' is not active. */
    String NSZM0601E = "NSZM0601E";

    /** Early Start Format is not valid. */
    String NSZM0604E = "NSZM0604E";

    /** Late Start  Format is not valid. */
    String NSZM0605E = "NSZM0605E";

    /** "Field Service Branch" in IB is mandatory for this process. */
    String NSZM0607E = "NSZM0607E";
//NA#ASCC CLICK ADD End

    // add start 2016/02/12 CSA Defect#2863
    /** Failed to update a Service Task Hold record. */
    String NSZM0865E = "NSZM0865E";
    // add end 2016/02/12 CSA Defect#2863

    // START 2019/10/24 K.Fujimoto [QC#53441, ADD]
    /** Failed to update SVC_PHYS_MTR_READ. */
    String NSZM0368E = "NSZM0368E";
    // END   2019/10/24 K.Fujimoto [QC#53441, ADD]
    /*****************************************************************
     * Process Mode
     ****************************************************************/
    /** PROCESS MODE SAVE */
    String PROCESS_MODE_SAVE = "01";

    /** PROCESS MODE SUBMIT */
    String PROCESS_MODE_SUBMIT = "02";

    /** PROCESS MODE UPDATE */
    String PROCESS_MODE_UPDATE = "03";

    /** PROCESS MODE PREVENTIVE MAINTENANCE */
    String PROCESS_MODE_PM = "04";

    /** PROCESS MODE CANCEL */
    String PROCESS_MODE_CANCEL = "05";

    /** PROCESS MODE POST DISPATCH */
    String PROCESS_MODE_DISPATCH = "06";

    /*****************************************************************
     * Constant
     ****************************************************************/
    int SVC_TASK_RCV_COLUMN_SIZE = 2;

//NA#ASCC CLICK ADD Start
    /** TS_POSTFIX : 000 */
    String TS_POSTFIX = "000";

    /** FORMAT_SYS_DT_TM : yyyyMMddHHmmss */
    String FORMAT_SYS_DT_TM = "yyyyMMddHHmmss";

    /** FORMAT_SYS_TS : yyyyMMddHHmmssSSS */
    String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";
//NA#ASCC CLICK ADD End
// add start 2016/02/10 CSA Defect#2863
    /** Time Start Position */
    int TIME_START_POS = 8;

    /** Time End Position */
    int TIME_END_POS = 6;
// add end 2016/02/10 CSA Defect#2863

    // START 2018/01/09 U.Kim [QC#19702, ADD]
    /** Waiting second for find by key*/
    String WAIT_SEC_UPD_TASK_OTHER = "WAIT_SEC_UPD_TASK_OTHER";
    // END 2018/01/09 U.Kim [QC#19702, ADD]

    // START 2022/02/10 [QC#57338, ADD]
    /** Response Time Target DS_SVC_CALL_TP : DS_COND_CONST */
    public static final String RSP_TM_TGT_CALL_TP = "RSP_TM_TGT_CALL_TP";
    // END   2022/02/10 [QC#57338, ADD]
}
