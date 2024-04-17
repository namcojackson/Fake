/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001.constant;


/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/25/2016   Hitachi         T.Aoyagi        Update          QC#5435
 * 04/07/2016   Hitachi         T.Aoyagi        Update          QC#6647
 * 05/10/2016   Hitachi         T.Aoyagi        Update          QC#7734
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 12/14/2016   Hitachi         Y.Takeno        Update          QC#16285
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 * 02/22/2017   Hitachi         Y.Takeno        Update          QC#16285-1
 * 10/13/2017   Hitachi         M.Kidokoro      Update          QC#21792
 * 2017/10/17   Hitachi         K.Kitachi       Update          QC#21792
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22669
 * 2018/03/16   Hitachi         K.Kojima        Update          QC#24497
 * 2018/03/27   CITS            M.Naito         Update          QC#18672
 * 2018/07/18   Hitachi         K.Kojima        Update          QC#26791
 * 2019/08/09   Hitachi         K.Kitachi       Update          QC#52540
 * 2020/01/08   Hitachi         K.Kim           Update          QC#55170
 * 2020/06/25   Hitachi         K.Kitachi       Update          QC#56211
 * 2022/09/01   CITS            L.Mandanas      Update          QC#58350
 * 2022/11/02   CITS            L.Mandanas      Update          QC#60652
 * </pre>
 */
public class NSZC053001Constant {

    /** Process Mode : CI Entry */
    public static final String MODE_CI_ENTRY = "1";

    /** Process Mode : CI Update */
    public static final String MODE_CI_UPDATE = "2";

    /** Process Mode : Submit for Approval */
    public static final String MODE_SUBMIT_FOR_APPROVAL = "3";

    /** Process Mode : CI Cancel */
    public static final String MODE_CI_CANCEL = "4";

    /** Process Mode : Override Approver */
    public static final String MODE_OVERRIDE_APPROVER = "5";

    /** Process Mode : Work Flow Approve */
    public static final String MODE_WORK_FLOW_APPROVE = "6";

    /** Process Mode : CI Reject */
    public static final String MODE_CI_REJECT = "7";

    /** Process Mode : Invoicing from Billing */
    public static final String MODE_INVOICING_FROM_BILLING = "8";

    /** Max Date */
    public static final String MAX_DATE = "29991231";

    /** Result Status Code : Normal */
    public static final String RSLT_STS_CD_NORMAL = "1";

    /** Result Status Code : Error */
    public static final String RSLT_STS_CD_ERROR = "9";

    /** Workflow process name */
    public static final String WF_PROCESS_NM = "NSWP0003";

    /** From Mail Address Group */
    public static final String FROM_ADDR_GRP_CD = "FROM0003";

    /** Reject notification Mail Template Code */
    public static final String REJECT_MAIL_TMPL_ID = "NSZC0530M001";

    /** Completion notification Mail Template Code */
    public static final String CMPL_MAIL_TMPL_ID = "NSZC0530M002";

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** Input parameter "Mode Code" is a mandatory field. */
    public static final String NSZM0003E = "NSZM0003E";

    /** Process Mode is not valid. */
    public static final String NSZM0039E = "NSZM0039E";

    /** It failed to cancel workflow. */
    public static final String NSZM0866E = "NSZM0866E";

    /** Failed to update DS_CONTR. */
    public static final String NSZM0873E = "NSZM0873E";

    /** Failed to update DS_CONTR_BLLG_MTR. */
    public static final String NSZM0874E = "NSZM0874E";

    /** Failed to update DS_CONTR_DTL. */
    public static final String NSZM0875E = "NSZM0875E";

    /** Failed to update DS_CONTR_PRC_EFF. */
    public static final String NSZM0876E = "NSZM0876E";

    /** Failed to update SVC_CR_REBIL. */
    public static final String NSZM0877E = "NSZM0877E";

    /** Failed to update SVC_CR_REBIL_BASE. */
    public static final String NSZM0878E = "NSZM0878E";

    /** Failed to update SVC_CR_REBIL_DTL. */
    public static final String NSZM0879E = "NSZM0879E";

    /** Failed to update SVC_CR_REBIL_MTR_BLLG. */
    public static final String NSZM0880E = "NSZM0880E";

    /** Failed to update SVC_CR_REBIL_MTR_READ. */
    public static final String NSZM0881E = "NSZM0881E";

    /** Failed to update SVC_CR_REBIL_XS_MTR_BLLG. */
    public static final String NSZM0882E = "NSZM0882E";

    /** Failed to insert SVC_CR_REBIL. */
    public static final String NSZM0883E = "NSZM0883E";

    /** Failed to insert SVC_CR_REBIL_BASE_BLLG. */
    public static final String NSZM0884E = "NSZM0884E";

    /** Failed to insert SVC_CR_REBIL_DTL. */
    public static final String NSZM0885E = "NSZM0885E";

    /** Failed to insert SVC_CR_REBIL_MTR_BLLG. */
    public static final String NSZM0886E = "NSZM0886E";

    /** Failed to insert SVC_CR_REBIL_MTR_READ. */
    public static final String NSZM0887E = "NSZM0887E";

    /** Failed to insert SVC_CR_REBIL_XS_MTR_BLLG. */
    public static final String NSZM0888E = "NSZM0888E";

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    /** Failed to insert SVC_CR_REBIL_ADDL_BLLG. */
    public static final String NSZM1324E = "NSZM1324E";
    
    /** Failed to update SVC_CR_REBIL_ADDL_BLLG. */
    public static final String NSZM1325E = "NSZM1325E";

    /** SVC_CR_REBIL_ADDL_BLLG is not found. */
    public static final String NSZM1326E = "NSZM1326E";
     // END 2018/03/27 M.Naito [QC#18672, ADD]

    /** Specified "Customer Incident ID" already exists. */
    public static final String NSZM0889E = "NSZM0889E";

    /** SVC_CR_REBIL is not found. */
    public static final String NSZM0890E = "NSZM0890E";

    /** SVC_CR_REBIL_BASE_BLLG is not found. */
    public static final String NSZM0891E = "NSZM0891E";

    /** SVC_CR_REBIL_DTL is not found. */
    public static final String NSZM0892E = "NSZM0892E";

    /** SVC_CR_REBIL_MTR_BLLG is not found. */
    public static final String NSZM0893E = "NSZM0893E";

    /** SVC_CR_REBIL_MTR_READ is not found. */
    public static final String NSZM0894E = "NSZM0894E";

    /** SVC_CR_REBIL_XS_MTR_BLLG is not found. */
    public static final String NSZM0895E = "NSZM0895E";

    /** Service Invoice Info is not found. */
    public static final String NSZM0896E = "NSZM0896E";

    /** Input parameter "Base Billing From Date" is a mandatory field. */
    public static final String NSZM0897E = "NSZM0897E";

    /** Input parameter "Base Billing Thru Date" is a mandatory field. */
    public static final String NSZM0898E = "NSZM0898E";

    /** Input parameter "Billing Meter Label Code" is a mandatory field. */
    public static final String NSZM0899E = "NSZM0899E";

    /** Input parameter "Credit Service Invoice Number" is a mandatory field. */
    public static final String NSZM0900E = "NSZM0900E";

    /** Input parameter "Customer Care Registration Person Code" is a mandatory field. */
    public static final String NSZM0901E = "NSZM0901E";

    /** Input parameter "Customer Incident ID" is a mandatory field. */
    public static final String NSZM0902E = "NSZM0902E";

    /** Input parameter "Customer Incident Line ID" is a mandatory field. */
    public static final String NSZM0903E = "NSZM0903E";

    /** Input parameter "End Meter Read Date" is a mandatory field. */
    public static final String NSZM0904E = "NSZM0904E";

    /** Input parameter "Invoice Print Flag" is a mandatory field. */
    public static final String NSZM0905E = "NSZM0905E";

    /** Input parameter "Meter Billing From Date" is a mandatory field. */
    public static final String NSZM0906E = "NSZM0906E";

    /** Input parameter "Meter Billing Thru Date" is a mandatory field. */
    public static final String NSZM0907E = "NSZM0907E";

    /** Input parameter "New Base Deal Amount" is a mandatory field. */
    public static final String NSZM0908E = "NSZM0908E";

    /** Input parameter "Old Excess Copy Quantity" is a mandatory field. */
    public static final String NSZM0909E = "NSZM0909E";

    /** Input parameter "Old Excess Meter Amount Rate" is a mandatory field. */
    public static final String NSZM0910E = "NSZM0910E";

    /** Input parameter "Original Service Invoice Number" is a mandatory field. */
    public static final String NSZM0911E = "NSZM0911E";

    /** Input parameter "Physical Meter Label Code" is a mandatory field. */
    public static final String NSZM0912E = "NSZM0912E";

    /** Input parameter "Rebill Service Invoice Number" is a mandatory field. */
    public static final String NSZM0913E = "NSZM0913E";

    /** Input parameter "Service Credit Rebill Detail PK" is a mandatory field. */
    public static final String NSZM0914E = "NSZM0914E";

    /** Input parameter "Service Credit Rebill PK" is a mandatory field. */
    public static final String NSZM0915E = "NSZM0915E";

    /** Input parameter "Service Credit Rebill Process Code" is a mandatory field. */
    public static final String NSZM0916E = "NSZM0916E";

    /** Input parameter "Service Credit Rebill Reason Code" is a mandatory field. */
    public static final String NSZM0917E = "NSZM0917E";

    /** Input parameter "Service Credit Rebill Reason Text" is a mandatory field. */
    public static final String NSZM0918E = "NSZM0918E";

    /** Input parameter "Service Credit Rebill Source Name" is a mandatory field. */
    public static final String NSZM0919E = "NSZM0919E";

    /** Input parameter "Start Meter Read Date" is a mandatory field. */
    public static final String NSZM0920E = "NSZM0920E";

    // START 03/25/2016 T.Aoyagi [QC#5435, ADD]
    /** An error occurred in Workflow Process. */
    public static final String NSZM0926E = "NSZM0926E";
    // END 03/25/2016 T.Aoyagi [QC#5435, ADD]
    // START 04/07/2016 T.Aoyagi [QC#6647, ADD]
    /** Input parameter "Service Contract Branch Code" is a mandatory field. */
    public static final String NSZM0959E = "NSZM0959E";

    /** Input parameter "Customer Incident Assign Person Code" is a mandatory field. */
    public static final String NSZM0960E = "NSZM0960E";
    // END 04/07/2016 T.Aoyagi [QC#6647, ADD]

    /** Could not get FROM mail-address. */
    public static final String NSZM0184E = "NSZM0184E";

    /** Could not get TO mail-address. */
    public static final String NSZM0436E = "NSZM0436E";

    /** Could not get Mailtemplate. */
    public static final String NSZM0185E = "NSZM0185E";

    // START 05/10/2016 T.Aoyagi [QC#7734, ADD]
    /** @ has been already registered. */
    public static final String NSAM0079E = "NSAM0079E";
    // END 05/10/2016 T.Aoyagi [QC#7734, ADD]

    // Add Start 12/14/2016 <QC#16285>
    /** Failed to insert the SVC_MEMO. (System Error) */
    public static final String NSZM0475E = "NSZM0475E";
    // Add End   12/14/2016 <QC#16285>

    // add start 01/24/2017 CSA Defect#17261
    /** Failed to update ticket for the following reason : [@] */
    public static final String NSZM1098E = "NSZM1098E";

    /** Exception occurred while updating customer care ticket. */
    public static final String NSZM1099E = "NSZM1099E";
    // add end 01/24/2017 CSA Defect#17261

    // START 2017/10/13 M.Kidokoro [QC#21792, ADD]
    /** Failed to update CONTR_XS_COPY. */
    public static final String NSZM1307E = "NSZM1307E";

    /** Failed to insert CONTR_XS_COPY. */
    public static final String NSZM1308E = "NSZM1308E";
    // END 2017/10/13 M.Kidokoro [QC#21792, ADD]

    // START 2017/10/17 K.Kitachi [QC#21792, ADD]
    /** Failed to delete CONTR_XS_COPY. */
    public static final String NSZM1310E = "NSZM1310E";

    /** Failed to update DS_CONTR_PRC_EFF_MTR. */
    public static final String NSZM1311E = "NSZM1311E";
    // END 2017/10/17 K.Kitachi [QC#21792, ADD]
    
    // START 2018/03/15 K.Kojima [QC#22669,ADD]
    /** SYSTEM GENERATE NOTES: Credit-Rebill has been processed. CI is now being closed. */
    public static final String NSZM1323I = "NSZM1323I";
    // END 2018/03/15 K.Kojima [QC#22669,ADD]

    // START 2018/07/18 K.Kojima [QC#26791,ADD]
    /** Message ID : NSZM1333E */
    public static final String NSZM1333E = "NSZM1333E";
    // END 2018/07/18 K.Kojima [QC#26791,ADD]

    // START 2020/01/08 [QC#55170, ADD]
    /** Failed to update a SVC_CONTR_BLLG record. */
    public static final String NSZM0787E = "NSZM0787E";
    // END 2020/01/08 [QC#55170, ADD]

    // START 2020/06/25 K.Kitachi [QC#56211, ADD]
    /** The account is already inactive. Please activate the account. [@] */
    public static final String NSZM1377E = "NSZM1377E";

    // START 2022/11/02 L.Mandanas [QC#60652, ADD]
    /** Bill To Code is inactive. Please activate Bill To Code. [@] */
    public static final String NSZM1380E = "NSZM1380E";

    /** Ship To Code is inactive. Please activate Ship To Code. [@] */
    public static final String NSZM1381E = "NSZM1381E";
    // END 2022/11/02 L.Mandanas [QC#60652, ADD]

    /** Delimiter for message parameter of NSZM1377E */
    public static final String NSZM1377E_DELIMITER = "], [";

    /** Length 100 */
    public static final int LEN_100 = 100;
    // END 2020/06/25 K.Kitachi [QC#56211, ADD]

    //Add Start 09/12/2016 <QC#5566>
    /** customer care calling statement */
    public final static String CALL_PRC_CANON_E193_UPDATE_TICKET = "{call CANON_E193_CS_EVOLUTION_PKG.UPDATE_TICKET(?,?,?,?,?,?,?,?)}";
    
    /** customer care parameter: notes detail approval */
    public final static String CALL_PRC_CANON_E193_NOTES_DETAIL_APPROVAL = " *** CREDIT-REBILL SYSTEM UPDATE *** Credit Rebill request Submitted for approval.<br>Approvers:";

    /** customer care parameter: notes detail approval */
    public final static String CALL_PRC_CANON_E193_PARM_BR = "<br>";

    // START 2019/08/09 K.Kitachi [QC#52540, MOD]
    /** customer care parameter: notes detail rejected */
    public final static String CALL_PRC_CANON_E193_NOTES_DETAIL_REJECTED = "UPDATE CUSTOMER - REJECTED";
    // END 2019/08/09 K.Kitachi [QC#52540, MOD]

    /** customer care parameter: notes detail system approval */
    public final static String CALL_PRC_CANON_E193_NOTES_DETAIL_SYSTEM_APPROVAL = " *** CREDIT-REBILL SYSTEM UPDATE *** Credit Rebill request is approved by all approvers.";

    /** customer care parameter: notes detail close */
    public final static String CALL_PRC_CANON_E193_NOTES_DETAIL_CLOSE = "CLOSE";

    // START 2018/03/16 K.Kojima [QC#24497,DEL]
    // /** date format MM/dd/yyyy KK:mm:ss a */
    // public final static String DATE_FORMAT = "MM/dd/yyyy KK:mm:ss a";
    // END 2018/03/16 K.Kojima [QC#24497,DEL]

    // START 2018/03/16 K.Kojima [QC#24497,ADD]
    /** date format dd-MMM-yyyy KK:mm:ss a */
    public static final String DATE_FORMAT_DDMMMYYYY_KKMMSS_A = "dd-MMM-yyyy KK:mm:ss a";
    // END 2018/03/16 K.Kojima [QC#24497,ADD]

    //Add End   09/12/2016 <QC#5566>

    // Add Start 12/14/2016 <QC#16285>
    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";
    
    /** SVC Memo (SVC_CMNT_TXT, Header Level) */
    // Mod Start 02/22/2017 <QC#16285-1>
    // public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR = "Manual update - Contract# [%s]";
    public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR = "Open Credit Rebill - Ticket# [%s] on Contract# [%s]";
    // Mod End   02/22/2017 <QC#16285-1>
    // Add End   12/14/2016 <QC#16285>

    /** CONTR_BR_FIRST_ORG_CD */
    public static final String CONTR_BR_FIRST_ORG_CD = "CONTR_BR_FIRST_ORG_CD";

    // START 2018/03/15 K.Kojima [QC#22669,ADD]
    /** UPDATE USER (CREDIT-REBILL SYSTEM UPDATE) */
    public static final String UPDATE_USER_SYSTEM_UPDATE = "-990";
    // END 2018/03/15 K.Kojima [QC#22669,ADD]

    // START 2022/09/01 L.Mandanas [QC#58350, ADD]
    /** ORIG_SVC_INV_NUM */
    public static final String ORIG_SVC_INV_NUM = "ORIG_SVC_INV_NUM";
    // END 2022/09/01 L.Mandanas [QC#58350, ADD]
}
