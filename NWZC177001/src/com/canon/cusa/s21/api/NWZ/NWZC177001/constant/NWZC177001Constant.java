/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC177001.constant;

/**
 * <pre>
 * NWZC1770 Order Header Workflow Cancel API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/24   SRAA            K.Aratani       Update          QC4665
 * 2016/02/29   Fujitsu         S.Takami        Update          S21_NA#4291
 * 2016/11/07   Fujitsu         S.Takami        Update          S21_NA#14186
 * 2018/08/01   Fujitsu         Mz.Takahashi    Update          S21_NA#27087
 * 2019/01/18   Fujitsu         S.Takami        Update          S21_NA#28773
 * 2019/12/14   Fujitsu         Mz.Takahashi    Update          QC#53588
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWZC177001Constant {
    /*****************************************************************
     * Mode CD
     ****************************************************************/
    // 2018/08/01 S21_NA#27087 Add Start
    /** Cancel without checking status */
    public static final String FORCE_CANCEL = "00";

    /** Cancel with checking status */
    public static final String CANCEL = "01";
    // 2018/08/01 S21_NA#27087 Add End

    // 2019/12/14 QC#53588 Add Start
    /** Automatic judgment */
    public static final String AUTO = "02";
    // 2019/12/14 QC#53588 Add End

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** The data does not exist in CPO_DTL. */
    public static final String NWZM0074E = "NWZM0074E";

    /** The data does not exist in SHPG_PLN. */
    public static final String NWZM0075E = "NWZM0075E";

    /** Details that can be cancelled do not exist. */
    public static final String NWZM0817E = "NWZM0817E";

    /** The parameter's "Order Number" is not set. */
    public static final String NWZM0912E = "NWZM0912E";

    /** It failed to cancel workflow. */
    public static final String NWZM1756E = "NWZM1756E";

    /** You cannot cancel this order. Please ask the approver to cancel this order. */
    public static final String NWZM1922E = "NWZM1922E";

    // 2016/11/07 S21_NA#14186 Add Start
    /** Please contact with administrator. Workflow cancel error (Business Exception). */
    public static final String NWZM2047E = "NWZM2047E";

    /** Please contact with administrator. Workflow cancel error (Invalid Statement). */
    public static final String NWZM2048E = "NWZM2048E";

    /** Please contact with administrator. Workflow cancel error (System Exception). */
    public static final String NWZM2049E = "NWZM2049E";

    /** Please contact with administrator. Workflow cancel error (Workflow Exception). */
    public static final String NWZM2050E = "NWZM2050E";
    // 2016/11/07 S21_NA#14186 Add End

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

    /*****************************************************************
     * Valiable
     ****************************************************************/
    /** Sub System ID : NWZ */
    public static final String SUB_SYS_ID = "NWZ";

    /** Workflow Cancel */
    public static final String WF_CANCEL = "WF Canc";

    /** Process ID : OM */
    public static final String PROC_ID = "OM";

    /** Document Type Code : OM */
    public static final String DOC_TP_CD = "OM";


    /** Event ID : Validation WorkFlow. */
    public static final String EVENTID_CANC_VALID_WF = "WF Canc(Validation Hold)";

    /** Event ID : Profitability WorkFlow. */
    public static final String EVENTID_CANC_PRFT_WF = "WF Canc(Profitability Hold)";

    /** Event ID : Supply Abuse (Yield) */
    public static final String EVENTID_CANC_SPLY_ABUSE_YEILD = "WF Canc(Supply Yield Valid Hold)";

    /** Event ID : Supply Abuse (Pending Order) */
    public static final String EVENTID_CANC_SPLY_ABUSE_PEND_ORD = "WF Canc(Pending Order Hold)";

    /** Event ID : Supply Abuse (Contact Status) */
    public static final String EVENTID_CANC_SPLY_ABUSE_CTAC_STS = "WF Canc(Contract Status Hold)";

    /** Event ID : Supply Abuse (Supply Enforcement) */
    public static final String EVENTID_CANC_SPLY_ABUSE_SPLY_ENFORCE = "WF Canc(Supply Enforcement Hold)";

    /** Event ID : Credit Review. */
    public static final String EVENTID_CANC_CREDIT_REVIEW = "WF Canc(Credit Approval)";

    /** Event ID : Lease Buyout */
    public static final String EVENTID_CANC_LEASE_BUYOUT = "WF Canc(Buyout Approval)";

    // 2019/01/18 S21_NA#28773 Add Start
    /** Fixed Reject Comment Varchar COnst Name */
    public static final String NWZC1770_WF_REJ_CMNT = "NWZC1770_WF_REJ_CMNT";

    /** Fixed Reject Comment if no data in var char const. */
    public static final String FIXED_REJECT_COMMENT = "The workflow of this order is rebooked by the requester.";
    // 2019/01/18 S21_NA#28773 Add End

    // add start 2023/04/25 QC#61337
    /** WorkFlow ID : Manual Price Approval */
    public static final String WFID_MAN_PRC_APVL = "NWWP0010";

    /** Event ID : WF Canc(Manual Price Approval) */
    public static final String EVENTID_CANC_MAN_PRC_APVL = "WF Canc(Manual Price Approval)";
    // add end 2023/04/25 QC#61337
}
