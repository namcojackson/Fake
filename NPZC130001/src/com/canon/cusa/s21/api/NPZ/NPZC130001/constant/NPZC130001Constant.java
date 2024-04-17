/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NPZ.NPZC130001.constant;

/**
 * <pre>
 * PO/PR Approval to WF API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 * 04/27/2023   Hitachi         T.Kuroda        Update          QC#61245
 * 05/19/2023   Hitachi         T.Kuroda        Update          QC#61211
 *</pre>
 */
public class NPZC130001Constant {

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

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Input Parameter "Process Type Code" is a mandatory field. */
    public static final String NPZM0210E = "NPZM0210E";

    /** Input Parameter "Approval history Source Type Code" is a mandatory field. */
    public static final String NPZM0211E = "NPZM0211E";

    /** An error occure in NPXC001001CreateApprovalHistory. */
    public static final String NPZM0212E = "NPZM0212E";

    /** An error occure in Creation of Workflow Process. */
    public static final String NPZM0213E = "NPZM0213E";

    /** Input Parameter "Global Company Code" is a mandatory field. */
    public static final String NPZM0179E = "NPZM0179E";

    /** Input Parameter "Sales Date" is a mandatory field. */
    public static final String NPZM0180E = "NPZM0180E";

    /** Mode Code is required. */
    public static final String NPZM0035E = "NPZM0035E";

    /** Input Parameter "Transaction Reference Number" is a mandatory field. */
    public static final String NPZM0190E = "NPZM0190E";

    /** The data specified does not exist. */
    public static final String NPAM0059E = "NPAM0059E";

    /**  */
    public static final String NFDM0008E = "NFDM0008E";

    /**  */
    public static final String NWZM0474E = "NWZM0474E";

    /** You are not authorized to cancel approval request. */
    public static final String NPAM1603E = "NPAM1603E";

    // START 2023/04/27 T.Kuroda [QC#61245, ADD]
    /** TR Tool request requires approval. */
    public static final String NPZM0314I = "NPZM0314I";

    /** Next Day Early AM request requires approval. */
    public static final String NPZM0315I = "NPZM0315I";

    /** Exceeds business unit limit of $@ */
    public static final String NPZM0316I = "NPZM0316I";
    // END   2023/04/27 T.Kuroda [QC#61245, ADD]

    // START 2023/05/19 T.Kuroda [QC#61211, ADD]
    /** Premium Rush Orders totaling less than $@ */
    public static final String NPZM0317I = "NPZM0317I";
    // END   2023/05/19 T.Kuroda [QC#61211, ADD]

    /*****************************************************************
     * WorkFlow ID & Mode & Name
     ****************************************************************/

    /** WorkFlow ID : Credit Review. */
    public static final String WFID_CREDIT_REVIEW = "NWWP0001";

    /** WorkFlow Mode : PO Request Approval */
    public static final String WFMD_PO = "01";

    /** WorkFlow Name : PO Request Approval */
    public static final String WFNM_PO = "NPZC13000101";

    /** Business ID : NPZC13000101 */
    public static final String BIZ_ID_NPZC13000101 = "NPZC13000101";

    /** WorkFlow Mode : Tech Request Approval */
    public static final String WFMD_TECH_REQ = "02";

    /** WorkFlow Name : Tech Request Approval */
    public static final String WFNM_TECH_REQ = "NPZC13000102";

    /** Business ID : NPZC13000102 */
    public static final String BIZ_ID_NPZC13000102 = "NPZC13000102";

    /** WorkFlow Mode : Tech Request Rush Approval */
    public static final String WFMD_TECH_REQ_RUSH = "03";

    /** WorkFlow Name : Tech Request Rush Approval */
    public static final String WFNM_TECH_REQ_RUSH = "NPZC13000103";

    /** Business ID : NPZC13000103 */
    public static final String BIZ_ID_NPZC13000103 = "NPZC13000103";

    /** WorkFlow Mode : PO Requisition Approval */
    public static final String WFMD_PO_REQ = "04";

    /** WorkFlow Name : PO Requisition Approval */
    public static final String WFNM_PO_REQ = "NPZC13000104";

    /** Business ID : NPZC13000104 */
    public static final String BIZ_ID_NPZC13000104 = "NPZC13000104";

    /** WorkFlow Mode : Inventory Request Approval */
    public static final String WFMD_INVTY_REQ = "05";

    /** WorkFlow Name : Inventory Request Approval */
    public static final String WFNM_INVTY_REQ = "NPZC13000105";

    /** Business ID : NPZC13000105 */
    public static final String BIZ_ID_NPZC13000105 = "NPZC13000105";

    /** Business Screen ID : NPAL1500 */
    public static final String BIZ_SCRN_ID_NPAL1500 = "NPAL1500";

    /** Business Screen ID : NPAL1080 */
    public static final String BIZ_SCRN_ID_NPAL1080 = "NPAL1080";

    /** Business Screen ID : NPAL1280 */
    public static final String BIZ_SCRN_ID_NPAL1280 = "NPAL1280";

    /** Business Screen ID : NPBL0020 */
    public static final String BIZ_SCRN_ID_NPBL0020 = "NPBL0020";

    /*****************************************************************
     * WorkFlow Label
     ****************************************************************/

    /** WorkFlow Label : PO# */
    public static final String WF_LBL_1_PO = "PO#";

    /** WorkFlow Label : Request# */
    public static final String WF_LBL_1_PRCH = "Request#";

    /** WorkFlow Label : Buyer */
    public static final String WF_LBL_2_PO = "Buyer";

    /** WorkFlow Label : Requester */
    public static final String WF_LBL_2_PRCH = "Requester";

    /** WorkFlow Label : Total Amount */
    public static final String WF_LBL_3 = "Total Amount";

    /** WorkFlow Label : PO Type */
    public static final String WF_LBL_4_PO = "PO Type";

    /** WorkFlow Label : Request Type */
    public static final String WF_LBL_4_PRCH = "Request Type";

    /** WorkFlow Label : Document Source Type */
    public static final String WF_LBL_5 = "Document Source Type";

    /*****************************************************************
     * DB Parameter
     ****************************************************************/

    /** Merchandise Category Code : PARTS_ITEM */
    public static final String PARTS_ITEM = "PARTS_ITEM";

    /** PO Status Code : 06 (Canceled) */
    public static final String PO_STS_CD_CANCELED = "06";

    /** PO Message Type Code : 02 (Internal Message) */
    public static final String PO_MSG_TP_CD_INTERNAL_MSG = "02";

    /** APVL_HRCH_TP_CD : 1 (POSITIONAL) */
    public static final String APVL_HRCH_TP_CD_POSITIONAL = "1";

    /** APVL_HRCH_TP_CD : 2 (EMPLOYEE) */
    public static final String APVL_HRCH_TP_CD_EMPLOYEE = "2";

    /** PO Merchandise Type Code : 2 (Parts) */
    public static final String PO_MDSE_TP_CD_PARTS = "2";

    /** PO Merchandise Type Code : 1 (Merchandise) */
    public static final String PO_MDSE_TP_CD_MDSE = "1";

    /** VarChar Constant Name : PO_CUST_DROP_SHIP_QLFY_CD */
    public static final String PO_CUST_DROP_SHIP_QLFY_CD = "PO_CUST_DROP_SHIP_QLFY_CD";

    /** Location Status Code : 03 (DC Stock) */
    public static final String LOC_STS_CD_DC_STOCK = "03";

    /** Location Status Code : 01 (In-Transit) */
    public static final String LOC_STS_CD_IN_TRANSIT = "01";

    /** Location Status Code : 16 (In-Transit (WH)) */
    public static final String LOC_STS_CD_IN_TRANSIT_WH = "16";

    /** Stock Status Code : 1 (Good) */
    public static final String STK_STS_CD_GOOD = "1";

    /** First Date of Month */
    public static final String FIRST_DT_OF_MONTH = "01";

    /** Invoice Type Code : 1 */
    public static final String INV_TP_CD = "1";

    /** Date Format : YYYY/MM/DD */
    public static final String DT_FMT_YYYYMMDD = "YYYY/MM/DD";

    /** Date Format : YYYYMM */
    public static final String DT_FMT_YYYYMM = "YYYYMM";

    /** Date Format : yyyymmddhhmmss */
    public static final String DT_FMT_YYYYMMDDHHMMSS = "yyyymmddhhmmss";

    /** Date Format : HH12:MI */
    public static final String DT_FMT_HH12MI = "hh:mm";

    /** AM */
    public static final String AM = "AM";

    /** PM */
    public static final String PM = "PM";

    /** Purchase Requisition Line Status : 99 (Canceled) */
    public static final String PRCH_REQ_LINE_STS_CANCELED = "99";

    /** Purchase Merchandise Composition Type Code : 2 (Child) */
    public static final String PO_MDSE_CMPSN_TP_CD_CHILD = "2";

    /** Number Const Name : PO_WF_NTFY_OTPT_REC_CNT */
    public static final String PO_WF_NTFY_OTPT_REC_CNT = "PO_WF_NTFY_OTPT_REC_CNT";

}
