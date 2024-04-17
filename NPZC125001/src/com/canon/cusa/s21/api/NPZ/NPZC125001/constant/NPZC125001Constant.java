/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NPZ.NPZC125001.constant;

/**
 * <pre>
 * PO/PR Approval from WF API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 * 05/04/2016   CITS            K.Lee           Update          QC#5839
 * 12/07/2018   Fujitsu         S.Takami        Update          QC#27052
 *</pre>
 */
public class NPZC125001Constant {

    /** Program ID */
    public static final String PROGRAM_ID = "NPZC1300";

    /** Approve */
    public static final String APPROVE = "APPROVE";

    /** Reject */
    public static final String REJECT = "REJECT";

    /** Cancel */
    public static final String CANCEL = "CANCEL";

    /** Return Code : 0 (Normal) */
    public static final String RTRN_CD_NORMAL = "0";

    /** Mode : 2 (Update) */
    public static final String MODE_UPDATE = "2";

    /** Event ID : 3 (Approval) */
    public static final String EVENT_ID_APPROVAL = "3";

    /** YYYYMMDD SIZE */
    public static final int YMD_SIZE = 8;

    /** VarChar Constant Name : DRCT_CPO_CRAT_FLG */
    public static final String DRCT_CPO_CRAT_FLG = "DRCT_CPO_CRAT_FLG";

    /** VarChar Constant Name : SEND_PO_IF_CRAT_FLG */
    public static final String SEND_PO_IF_CRAT_FLG = "SEND_PO_IF_CRAT_FLG";

    /** Message type is Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** An error occure in NPXC001001CreateApprovalHistory. */
    public static final String NPZM0212E = "NPZM0212E";

    /** The approver is not found. Please set up it at PO/Inventory Approval Maintenance. */
    public static final String NPZM0258E = "NPZM0258E";

    /** The approver is not found. Please make sure that requester and the manager can access to S21 system. */
    public static final String NPZM0259E = "NPZM0259E";

    /** Date Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /*****************************************************************
     * WorkFlow ID
     ****************************************************************/

    // 2018/12/07 QC#27052 Add Start
    /** Mail Send Process Target Screen */
    public static final String NPZC1250_ML_SEND_TRGT = "NPZC1250_ML_SEND_TRGT";

    /** Mail Send Target Transmission Method Type Code */
    public static final String NPZC1250_ML_SEND_TRGT_TRSMT_METH_TP = "NPZC1250_ML_SEND_TRSMT_METH_TP";

    /** FAX Send Target Transmission Method Type Code */
    public static final String NPZC1250_FX_SEND_TRGT_TRSMT_METH_TP = "NPZC1250_FX_SEND_TRSMT_METH_TP";

    /** Default Through Data */
    public static final String DEF_THRU_DT = "99991231";
    // 2018/12/07 QC#27052 Add END
}
