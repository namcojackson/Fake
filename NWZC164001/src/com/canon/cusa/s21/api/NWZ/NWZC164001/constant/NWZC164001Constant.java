/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC164001.constant;

/**
 * <pre>
 * NWZC164001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         H.Nagashima     Create          N/A
 * 2016/04/21   Fujitsu         M.Yamada        Update          QC#7257
 * 2016/07/25   Fujitsu         T.Ishii         Update          S21_NA#11179
 * 2016/08/16   SRAA            K.Aratani       Update          S21_NA#12904
 * 2018/01/12   Hitachi         Y.Takeno        Update          QC#21078
 * 2018/09/25   Hitachi         K.Kim           Update          QC#28318
 * 2019/02/05   Hitachi         E.Kameishi      Update          QC#30163
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWZC164001Constant {

    /** Message ID : "Data Global Company Code" is required. */
    public static final String NMZM0011E = "NMZM0011E";

    /**
     * Message ID : A system error occurred. Please contact your
     * system administrator.
     */
    public static final String NWZM0474E = "NWZM0474E";

    /**
     * Message ID : "Sales Date" for the entered parameter is not set.
     */
    public static final String NWZM0978E = "NWZM0978E";

    /** Message ID : CPO Order Number for the parameter is not set. */
    public static final String NWZM1205E = "NWZM1205E";

    /** Message ID : Workflow has already been started. */
    public static final String NWZM1754E = "NWZM1754E";

    /**
     * Message ID : There is a Workflow that has already been started.
     */
    public static final String NWZM1755I = "NWZM1755I";

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
    
    
    /** VAR_CAR_CONST Key : Validation Approval Workflow ID */
    public static final String CONST_KEY_VLD_APVL_WF_ID = "VLD_APVL_WF_ID";

    /** VAR_CAR_CONST Key : Profitability Approval Workflow ID */
    public static final String CONST_KEY_PRFT_APVL_WF_ID = "PRFT_APVL_WF_ID";

    /** VAR_CAR_CONST Key : Supply Abuse Yield Approval Workflow ID */
    public static final String CONST_KEY_SPLY_ABUSE_YIELD_APVL_WF_ID = "SPLY_ABUS_YIELD_APVL_WF_ID";

    /**
     * VAR_CAR_CONST Key : Supply Abuse Contract Status Approval
     * Workflow ID
     */
    public static final String CONST_KEY_SPLY_ABUS_CONTR_STS_APVL_WF_ID = "SPLY_ABUS_CONTR_STS_APVL_WF_ID";

    /**
     * VAR_CAR_CONST Key : Supply Abuse Pending Order Approval
     * Workflow ID
     */
    public static final String CONST_KEY_SPLY_ABUS_PND_ORD_APVL_WF_ID = "SPLY_ABUS_PND_ORD_APVL_WF_ID";

    /**
     * VAR_CAR_CONST Key : Supply Abuse Enforcement Hold Approval
     * Workflow ID
     */
    public static final String CONST_KEY_SPLY_ABUSE_ENF_HLD_APVL_WF_ID = "SPLY_ABUS_ENF_HLD_APVL_WF_ID";

    /** VAR_CAR_CONST Key : Credit Approval Workflow ID */
    public static final String CONST_KEY_CR_APVL_WF_ID = "CR_APVL_WF_ID";

    /** VAR_CAR_CONST Key : Buyout Approval Workflow ID */
    public static final String CONST_KEY_BYOT_APVL_WF_ID = "BYOT_APVL_WF_ID";

    /** Biz Screen ID : Order Entry Screen */
    public static final String CONST_ORDER_ENTRY_SCREEN_ID = "NWAL1500";

    // START 2018/01/12 [QC#21078, ADD]
    /** Biz Screen ID : Collection Header Screen */
    public static final String CONST_COLLECTION_HEADER_SCREEN_ID = "NFDL0020";
    // END   2018/01/12 [QC#21078, ADD]

    // START 2018/09/25 [QC#28318,ADD]
    /**
     * TERM_COND_CAP LENGTH
     */
    public static final int TERM_COND_CAP_LENGTH = 3;

    /**
     * Cap - B/W Toner Original
     */
    public static final String TERM_COND_CAP_BW_ORG_ATTRB_NM = "TERM_COND_CAP_BW_ORG_ATTRB_NM";

    /**
     * Cap - Color Toner Original
     */
    public static final String TERM_COND_CAP_CLR_ORG_ATTRB_NM = "TERM_COND_CAP_CLR_ORG_ATTRB_NM";

    /**
     * Cap - Total Toner Original
     */
    public static final String TERM_COND_CAP_TOT_ORG_ATTRB_NM = "TERM_COND_CAP_TOT_ORG_ATTRB_NM";
    // END 2018/09/25 [QC#28318,ADD]

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    // QC#7257
    /** Event ID : WF Open(Validation Hold) */
    public static final String WF_OPEN_VALIDATION_HOLD = "WF Open(Validation Hold)";

    /** Event ID : WF Open(Profitability Hold) */
    public static final String WF_OPEN_PROFITABILITY_HOLD = "WF Open(Profitability Hold)";

    /** Event ID : WF Open(Supply Yield Valid Hold) */
    public static final String WF_OPEN_SUPPLY_YIELD_HOLD = "WF Open(Supply Yield Valid Hold)";

    /** Event ID : WF Open(Pending Order Hold) */
    public static final String WF_OPEN_PENDING_ORDER_HOLD = "WF Open(Pending Order Hold)";

    /** Event ID : WF Open(Contract Status Hold) */
    public static final String WF_OPEN_CONTRACT_STATUS_HOLD = "WF Open(Contract Status Hold)";

    /** Event ID : WF Open(Supply Enforcement Hold) */
    public static final String WF_OPEN_SUPPLY_ENFORCEMENT_HOLD = "WF Open(Supply Enforcement Hold)";

    /** Event ID : WF Open(Credit Approval) */
    public static final String WF_OPEN_CREDIT_APPROVAL_HOLD = "WF Open(Credit Approval)";

    /** Event ID : WF Open(Buyout Approval) */
    public static final String WF_OPEN_BUYOUT_APPROVAL_HOLD = "WF Open(Buyout Approval)";

    /** Sub System Id : NWZ */
    public static final String SUB_SYS_ID = "NWZ";

    /** Process Id : OM */
    public static final String PROCESS_ID = "OM";

    /** Document Type : OM */
    public static final String DOCUMENT_TYPE = "OM";

    /** VALIDATION_APPROVAL : 01 */
    public static final String VALIDATION_APPROVAL = "01";

    /** PROFITABILITY_OR_APPRV : 03 */
    public static final String PROFITABILITY_OR_APPRV = "03";

    /** SUPPLY_ABUSE : 07 */
    public static final String SUPPLY_ABUSE = "07";

    /** CREDIT_APPROVAL : 04 */
    public static final String CREDIT_APPROVAL = "04";

    /** BUYOUT_APPROVAL : 11 */
    public static final String BUYOUT_APPROVAL = "11";

    /** work flow profitability no process */
    public static final String WF_PRFT_NO_PROC = "Next2"; // S21_NA#11179
    // START 2019/02/05 E.Kameishi [QC#30163,ADD]
    public static final String  STYLE_01 = "style=\"display:none;\"";
    // END 2019/02/05 E.Kameishi [QC#30163,ADD]

    // add start 2023/04/25 QC#61337
    /** VAR_CAR_CONST Key : Manual Price Approval Workflow ID */
    public static final String CONST_KEY_MAN_PRC_APVL_WF_ID = "MAN_PRC_APVL_WF_ID";

    /**  Event ID : WF Open(Manual Price Approval) */ 
    public static final String WF_OPEN_MAN_PRC_APVL = "WF Open(Manual Price Approval)";
    // add end 2023/04/25 QC#61337
}
