/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC165001.constant;

/**
 * <pre>
 * NWZC1650 Order Header Workflow Rejection And Approval API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/24   SRAA            K.Aratani       Create          QC4665
 * 2016/07/25   Fujitsu         T.Ishii         Update          S21_NA#11179
 * 2016/07/26   Fujitsu         T.Ishii         Update          S21_NA#4324
 * 2016/08/16   SRAA            K.Aratani       Update          S21_NA#12904
 * 2019/01/30   Fujitsu         S.Takami        Update          S21_NA#28799
 * 2022/11/17   Hitachi         S.Fujita        Update          QC60406
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWZC165001Constant {
    /** Approve Request Type : Credit Check Hold */
    public static final String CR_CHK_HLD = "Credit Check Hold";

    /** Approve Request Type : Lease Buy Out Hold */
    public static final String LEASE_BUYOUT_HLD = "Lease Buy Out Hold";

    /** HDD Removal Code : HDD Remove(01) */
    public static final String HDD_REMOVE = "01";

    /** HDD Removal Code : HDD Erase(02) */
    public static final String HDD_ERASE = "02";

    /** Business ID : NWZC1650 */
    public static final String BIZ_ID = "NWZC1650";

    /** Sub System ID : NWZ */
    public static final String SUB_SYS_ID = "NWZ";

    /** Workflow Rejection */
    public static final String WF_REJECT = "WF Rej";

    /** Workflow Approval */
    public static final String WF_APPROVE = "WF Apvl";

    /** Process ID : OM */
    public static final String PROC_ID = "OM";

    /** Document Type Code : OM */
    public static final String DOC_TP_CD = "OM";

    /** ROWNUM (1 Row) */
    public static final String ROWNUM = "1";

    /** Approve */
    public static final String APPROVE = "APPROVE";

    /** Reject */
    public static final String REJECT = "REJECT";

    /** Cancel */
    public static final String CANCEL = "CANCEL";

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** The data does not exist in CPO_DTL. */
    public static final String NWZM0074E = "NWZM0074E";

    /** The data does not exist in SHPG_PLN. */
    public static final String NWZM0075E = "NWZM0075E";

    /** The data does not exist in HOLD. */
    public static final String NWZM0253E = "NWZM0253E";

    /** Details that can be cancelled do not exist. */
    public static final String NWZM0817E = "NWZM0817E";

    /** Undefined value is set in Process mode. */
    public static final String NWZM1154E = "NWZM1154E";

    /** The Parameter is invalid. */
    public static final String NWZM1780E = "NWZM1780E";

    /** Failed to reject the workflow. */
    public static final String NWZM1781E = "NWZM1781E";

    // QC#5591
    /**
     * The credit authorizer is not found. Please set up it at
     * workflow.
     */
    public static final String NWZM1929E = "NWZM1929E";

    //QC#12904
    /**
     * Profitability approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1982E = "NWZM1982E";
    
    /**
     * No user to approve. Please set up it at workflow.
     */
    public static final String NWZM1983E = "NWZM1983E";
    
    /**
     * Supply Abuse (Contact Status) approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1984E = "NWZM1984E";

    /**
     * Supply Abuse (Pending Order) approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1985E = "NWZM1985E";

    /**
     * Supply Abuse (Supply Enforcement) approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1986E = "NWZM1986E";

    /**
     * Supply Abuse (Yield) approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1987E = "NWZM1987E";

    /**
     * Lease Buyout approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1988E = "NWZM1988E";
    
    /**
     * Validation WorkFlow approver is not found. Please set up it at workflow.
     */
    public static final String NWZM1989E = "NWZM1989E";
    
    
    /**
     * You can not release the credit hold. Please confirm the credit
     * profile for this customer.
     */
    public static final String NWZM1951E = "NWZM1951E"; // S21_NA#4324

    /*****************************************************************
     * WorkFlow ID
     ****************************************************************/
    /** WorkFlow ID : Credit Review. */
    public static final String WFID_CREDIT_REVIEW = "NWWP0001";

    /** WorkFlow ID : Validation WorkFlow. */
    public static final String WFID_VALID_WF = "NWWP0002";

    /** WorkFlow ID : Profitability WorkFlow. */
    public static final String WFID_PRFT_WF = "NWWP0003";

    /** WorkFlow ID : Supply Abuse (Yield) */
    public static final String WFID_SPLY_ABUSE_YEILD = "NWWP0004";

    /** WorkFlow ID : Supply Abuse (Contact Status) */
    public static final String WFID_SPLY_ABUSE_CTAC_STS = "NWWP0005";

    /** WorkFlow ID : Supply Abuse (Pending Order) */
    public static final String WFID_SPLY_ABUSE_PEND_ORD = "NWWP0006";

    /** WorkFlow ID : Supply Abuse (Supply Enforcement) */
    public static final String WFID_SPLY_ABUSE_SPLY_ENFORCE = "NWWP0007";

    /** WorkFlow ID : Lease Buyout */
    public static final String WFID_LEASE_BUYOUT = "NWWP0008";

    /** Event ID : Validation WorkFlow. */
    public static final String EVENTID_APVL_VALID_WF = "WF Apvl(Validation Hold)";

    /** Event ID : Profitability WorkFlow. */
    public static final String EVENTID_APVL_PRFT_WF = "WF Apvl(Profitability Hold)";

    /** Event ID : Supply Abuse (Yield) */
    public static final String EVENTID_APVL_SPLY_ABUSE_YEILD = "WF Apvl(Supply Yield Valid Hold)";

    /** Event ID : Supply Abuse (Pending Order) */
    public static final String EVENTID_APVL_SPLY_ABUSE_PEND_ORD = "WF Apvl(Pending Order Hold)";

    /** Event ID : Supply Abuse (Contact Status) */
    public static final String EVENTID_APVL_SPLY_ABUSE_CTAC_STS = "WF Apvl(Contract Status Hold)";

    /** Event ID : Supply Abuse (Supply Enforcement) */
    public static final String EVENTID_APVL_SPLY_ABUSE_SPLY_ENFORCE = "WF Apvl(Supply Enforcement Hold)";

    /** Event ID : Credit Review. */
    public static final String EVENTID_APVL_CREDIT_REVIEW = "WF Apvl(Credit Approval)";

    /** Event ID : Lease Buyout */
    public static final String EVENTID_APVL_LEASE_BUYOUT = "WF Apvl(Buyout Approval)";

    /** Event ID : Validation WorkFlow. */
    public static final String EVENTID_REJ_VALID_WF = "WF Rej(Validation Hold)";

    /** Event ID : Profitability WorkFlow. */
    public static final String EVENTID_REJ_PRFT_WF = "WF Rej(Profitability Hold)";

    /** Event ID : Supply Abuse (Yield) */
    public static final String EVENTID_REJ_SPLY_ABUSE_YEILD = "WF Rej(Supply Yield Valid Hold)";

    /** Event ID : Supply Abuse (Pending Order) */
    public static final String EVENTID_REJ_SPLY_ABUSE_PEND_ORD = "WF Rej(Pending Order Hold)";

    /** Event ID : Supply Abuse (Contact Status) */
    public static final String EVENTID_REJ_SPLY_ABUSE_CTAC_STS = "WF Rej(Contract Status Hold)";

    /** Event ID : Supply Abuse (Supply Enforcement) */
    public static final String EVENTID_REJ_SPLY_ABUSE_SPLY_ENFORCE = "WF Rej(Supply Enforcement Hold)";

    /** Event ID : Credit Review. */
    public static final String EVENTID_REJ_CREDIT_REVIEW = "WF Rej(Credit Approval)";

    /** Event ID : Lease Buyout */
    public static final String EVENTID_REJ_LEASE_BUYOUT = "WF Rej(Buyout Approval)";

    /** work flow profitability no process */
    public static final String WF_PRFT_NO_PROC = "Next2"; // S21_NA#11179

    // 2019/01/30 S21_NA#28799 Add Start
    /** Varchar Const Name: NWZC1650_CLT_DTL_NOTE_TXT  */
    public static final String VAR_CHAR_CONST_NWZC1650_CLT_DTL_NOTE_TXT = "NWZC1650_CLT_DTL_NOTE_TXT";
    /** Detail Note Text Template  */
    public static final String NWZC1650_DTL_NOTE_TXT_TMPL = "Order Rejected.%nComment:%s%nOrder#:%s%nOrder Amount:%,.2f%nEntered by:%s%nRejected by:%s";
    // 2019/01/30 S21_NA#28799 Add Start
    // add start 2022/11/07 QC#60406
    public static final String VAR_CHAR_CONST_NWZC1650_APVL_CLT_DTL_NOTE_TXT = "NWZC1650_APVL_CLT_DTL_NOTE_TXT";

    public static final String NWZC1650_APVL_DTL_NOTE_TXT_TMPL = "Order Approved.%nComment:%s%nOrder#:%s%nOrder Amount:%,.2f%nEntered by:%s%nApproved by:%s";
    // add end 2022/11/07 QC#60406

    // add start 2023/04/25 QC#61337
    /** Please contact with administrator. Workflow cancel error (System Exception). */
    public static final String NWZM2049E = "NWZM2049E";

    /** WorkFlow ID : Manual Price Approval */
    public static final String WFID_MAN_PRC_APVL = "NWWP0010";

    /** Event ID : WF Apvl(Manual Price Approval) */
    public static final String EVENTID_APVL_MAN_PRC_APVL = "WF Apvl(Manual Price Approval)";

    /** Event ID : WF Rej(Manual Price Approval) */
    public static final String EVENTID_REJ_MAN_PRC_APVL = "WF Rej(Manual Price Approval)";
    // add end 2023/04/25 QC#61337}
}
