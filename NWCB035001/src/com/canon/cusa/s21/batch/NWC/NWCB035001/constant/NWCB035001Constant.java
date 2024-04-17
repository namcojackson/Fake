/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB035001.constant;

/**
 *<pre>
 * NWCB0350:CFS Lease Package Create & Send Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         W.Honda         Create          N/A
 * 2018/01/22   Fujitsu         K.Ishizuka      Update          S21_NA#23439
 * 2022/09/30   Hitachi         H.Watanabe      Update          QC#60253
 *</pre>
 */

public class NWCB035001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWCB0350";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** VAR_CHAR_CONST_KEY : THEREFORE_LOC_PATH */
    public static final String THEREFORE_LOC_PATH = "THEREFORE_LOC_PATH";

    /** VAR_CHAR_CONST_KEY : NWCB0350_THEREFORE_ASYNC_FLG */
    public static final String THEREFORE_ASYNC_PROC_FLG = "NWCB0350_THEREFORE_ASYNC_FLG";

    /** SUB_SYSTEM : NWC */
    public static final String SUB_SYSTEM = "NWC";

    /** DB_PARAM_GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Name : LEASE_PKG_RPT_WRK */
    public static final String TABLE_LEASE_PKG_RPT_WRK = "LEASE_PKG_RPT_WRK";

    /** Auto Sequence Key : LEASE_PKG_RPT_WRK */
    public static final String AUTO_SEQ_KEY = "LEASE_PACKAGE_ID";

    /** COL_CFS_APP_NUM */
    public static final String COL_CFS_APP_NUM = "CFS_APP_NUM";

    /** COL_CFS_PO_NUM */
    public static final String COL_CFS_PO_NUM = "CFS_PO_NUM";

    /** COL_DS_ACCT_NM */
    public static final String COL_DS_ACCT_NM = "DS_ACCT_NM";

    /** COL_SLS_REP_TOC_CD */
    public static final String COL_SLS_REP_TOC_CD = "SLS_REP_TOC_CD";
    
    /** COL_TOC_NM */
    public static final String COL_TOC_NM = "TOC_NM"; // 2018/01/22 S21_NA#23439 Add

    /** COL_CFS_PO_AMT */
    public static final String COL_CFS_PO_AMT = "CFS_PO_AMT";

    /** COL_INV_CPLT_TOT_DEAL_NET_AMT */
    public static final String COL_INV_CPLT_TOT_DEAL_NET_AMT = "INV_CPLT_TOT_DEAL_NET_AMT";

    /** COL_INV_CPLT_AMT_RATE */
    public static final String COL_INV_CPLT_AMT_RATE = "INV_CPLT_AMT_RATE";

    /** COL_PSN_NM */
    public static final String COL_PSN_NM = "PSN_NM";

    /** COL_DTL_CNT */
    public static final String COL_DTL_CNT = "DTL_CNT";

    /** COL_INV_NUM */
    public static final String COL_INV_NUM = "INV_NUM";

    /** COL_CPO_ORD_NUM */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** COL_INV_TOT_DEAL_NET_AMT */
    public static final String COL_INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";

    /** COL_GL_DT */
    public static final String COL_GL_DT = "GL_DT";

    /** COL_LEASE_CMPY_USR_ID */
    public static final String COL_LEASE_CMPY_USR_ID = "LEASE_CMPY_USR_ID";

    /** COL_LEASE_CMPY_USR_NM */
    public static final String COL_LEASE_CMPY_USR_NM = "LEASE_CMPY_USR_NM";

    /** CONSL_BILL_FLG */
    public static final String COL_CONSL_BILL_FLG = "CONSL_BILL_FLG";

    /** INV_PRT_CTRL_PK */
    public static final String COL_INV_PRT_CTRL_PK = "INV_PRT_CTRL_PK";

    /** CFS_LEASE_PKG_PO_HDR_PK */
    public static final String COL_CFS_LEASE_PKG_PO_HDR_PK = "CFS_LEASE_PKG_PO_HDR_PK";

    // 2016/11/10 Unit Test#201-50 Add Start
    /** LEASE_PKG_RPT_WRK_PK */
    public static final String COL_LEASE_PKG_RPT_WRK_PK = "LEASE_PKG_RPT_WRK_PK";
    // 2016/11/10 Unit Test#201-50 Add End

    /** NULL_BIGDECIMAL_REPLACE_CHAR : 0 */
    public static final String NULL_STRING_REPLACE_CHAR = "";

    /** BigDecimal Format : #.# */
    public static final String BIGDECIMAL_FORMAT = "#.#";

    /** Amount Fraction Digit : 2 */
    public static final int AMT_FRACTION_DIGIT = 2;

    /** NULL_BIGDECIMAL_REPLACE_CHAR : 0 */
    public static final String NULL_BIGDECIMAL_REPLACE_CHAR = "0";

    /** XML ELEMNT : LeasePackage */
    public static final String XML_ELEMENT_LEASE_PACKAGE = "LeasePackage";

    /** XML ELEMNT : PackageLocation */
    public static final String XML_ELEMENT_PKG_LOC = "PackageLocation";

    /** XML ELEMNT : PONumber */
    public static final String XML_ELEMENT_PO_NUM = "PONumber";

    /** XML ELEMNT : ShipTo */
    public static final String XML_ELEMENT_SHIP_TO = "ShipTo";

    /** XML ELEMNT : CFSOwner */
    public static final String XML_ELEMENT_CFS_OWN_NM = "CFSOwner";

    /** XML ELEMNT : CFSOwnerID */
    public static final String XML_ELEMENT_CFS_OWN_ID = "CFSOwnerID";

    /** XML ELEMNT : InvoiceList */
    public static final String XML_ELEMENT_INV_LIST = "InvoiceList";

    /** XML ELEMNT : Invoice */
    public static final String XML_ELEMENT_INV = "Invoice";

    /** XML ELEMNT : OrderNumber */
    public static final String XML_ELEMENT_ORD_NUM = "OrderNumber";

    /** XML ELEMNT : InvoiceNumber */
    public static final String XML_ELEMENT_INV_NUM = "InvoiceNumber";

    /** XML ELEMNT : InvoiceDate */
    public static final String XML_ELEMENT_INV_DT = "InvoiceDate";

    /** XML ELEMNT : Amount */
    public static final String XML_ELEMENT_AMT = "Amount";

    /** XML FILE EXTENSION : .xml */
    public static final String XML_EXTENSION = ".xml";

    /** PDF FILE EXTENSION : .pdf */
    public static final String PDF_EXTENSION = ".pdf";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String DATE_FORMAT = "MM/dd/yyyy";

    /** YYYYMMDD Length : 8 */
    public static final int YYYYMMDD_LENGTH = 8;

    /** HYPHEN : - */
    public static final Character HYPHEN = '-';

    /** UNDERSCORE : _ */
    public static final Character UNDERSCORE = '_';

    //2022/09/30 QC#60253 Add Start
    /** CFS_APP_NUM_TNT_VAL */
    public static final String CFS_APP_NUM_TENTATIVE_VAL = "//";
    //2022/09/30 QC#60253 Add End

    /** Cover Report ID : TEST */
    public static final String COVER_REPORT_ID = "NWCF0110";

    /** Invoice Report ID : NWCF0100 */
    public static final String INVOICE_REPORT_ID = "NWCF0100";

    /** Report Parameter Name : GLBL_CMPY_CD */
    public static final String RPT_PRM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Report Parameter Name : CFS_PO_NUM */
    public static final String RPT_PRM_CFS_PO_NUM = "CFS_PO_NUM";

    /** Report Parameter Name : CFS_APP_NUM */
    public static final String RPT_PRM_CFS_APP_NUM = "CFS_APP_NUM";

    /** Report Parameter Name : INTL_LANG_VAL_COL_NM */
    public static final String RPT_PRM_INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** Report Parameter Name : INV_BILL_NUM */
    public static final String RPT_PRM_INV_BILL_NUM = "INV_BILL_NUM";

    /** Report Parameter Name : CONSL_BILL_FLG */
    public static final String RPT_PRM_CONSL_BILL_FLG = "CONSL_BILL_FLG";

    /** Report Parameter Name : INV_PRT_CTRL_PK */
    public static final String RPT_PRM_INV_PRT_CTRL_PK = "INV_PRT_CTRL_PK";

    // =================================================
    // Message Code
    // =================================================

    /** It failed to register @ Table.[@] */
    public static final String NWCM0109E = "NWCM0109E";

    /** It failed to update @ Table.[@] */
    public static final String NWCM0110E = "NWCM0110E";

    /** It failed to get [@].(@) */
    public static final String NWCM0112E = "NWCM0112E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Code Master @ is invalid Registered. Please call IT Division. */
    public static final String NWAM0811E = "NWAM0811E";

    /** merge report bytes failure */
    public static final String MERGE_REPORT_BYTES_FAILURE = "merge report bytes failure";
    
    public static final String NWCB035001_MAX_PCT = "NWCB035001_MAX_PCT";
    

}
