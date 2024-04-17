/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC224001.constant;

/**
 *<pre>
 * NWZC2240:Therefore Indexed Attribute Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/25   Fujitsu         W.Honda         Create          N/A
 * 2016/09/16   Fujitsu         S.Iidaka        Update          QC#14569
 * 2016/10/14   Fujitsu         S.Iidaka        Update          QC#15194
 * 2018/01/19   Fujitsu         W.Honda         Update          QC#23598
 * 2018/01/30   SRAA            K.Aratani       Update          QC#23439
 * 2019/08/29   Fujitsu         C.Hara          Update          QC#52938
 *</pre>
 */

public class NWZC224001Constant {

    /**
     * 
     */
    public static final String THEREFORE_CONN_AVAL_FLG = "THEREFORE_CONN_AVAL_FLG";

    /**
     * VAR_CHAR_CONST_NM : THEREFORE_ERR_INVALID_DOC
     */
    public static final String THEREFORE_ERR_INVALID_DOC = "THEREFORE_ERR_INVALID_DOC";

    /**
     * DOC_MGT_FLD_CD
     */
    public static final String COL_DOC_MGT_FLD_CD = "DOC_MGT_FLD_CD";

    /**
     * IDX_ATTRB_TRGT_COL_NM
     */
    public static final String COL_IDX_ATTRB_TRGT_COL_NM = "IDX_ATTRB_TRGT_COL_NM";

    /**
     * DOC_MGT_FLD_TP_CD
     */
    public static final String COL_DOC_MGT_FLD_TP_CD = "DOC_MGT_FLD_TP_CD";

    /**
     * DOC_MGT_FLD_NUM
     */
    public static final String COL_DOC_MGT_FLD_NUM = "DOC_MGT_FLD_NUM";

    //QC#23439
    /**
     * DOC_MGT_FLD_DESC_TXT
     */
    public static final String COL_DOC_MGT_FLD_DESC_TXT = "DOC_MGT_FLD_DESC_TXT";
    
    /**
     * CFS_LEASE_PKG_PO_HDR_PK
     */
    public static final String COL_CFS_LEASE_PKG_PO_HDR_PK = "CFS_LEASE_PKG_PO_HDR_PK";

    //QC#23598 START
    public static final String COL_AQU_NUM = "AQU_NUM";
    //QC#23598 END

    /**
     * CheckIn Comment : NWZC2240 Therefore Indexed Attribute Update API
     */
    public static final String CHECKIN_COMMNT = "NWZC2240 Therefore Indexed Attribute Update API";

    /**
     * ATTACH_MODE_CD : 01
     */
    public static final String ATTACH_MODE_CD = "01";

    /**
     * DETACH_MODE_CD : 02
     */
    public static final String DETACH_MODE_CD = "02";

    /**
     * ATT_DATA_TP_THEREFORE : TF
     */
    public static final String ATT_DATA_TP_THEREFORE = "TF";

    /**
     * Time Stamp Format
     */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Date length : 14
     */
    public static final int DATE_LENGTH = 17;

    // 2019/08/29 QC#52938 Add Start
    /** Date length : 8 */
    public static final int DATE_LENGTH_NO_TIME = 8;
    // 2019/08/29 QC#52938 Add End

    //QC#14927,15194 START
    /**
     * EZDsystemporperty key : S21.therfore.attachment.url
     */
    public static final String EZDSYS_KEY_THEREFORE_URL = "S21.therfore.attachment.url";

    //QC#23439
    /**
     * Scanned Date
     */
    public static final String SCANNED_DATE = "Scanned Date";

    /**
     * Scan Date
     */
    public static final String SCAN_DATE = "Scan Date";

    /**
     * Lease Document ID
     */
    public static final String LEASE_DOC_ID = "Lease Document ID";

    /** For search result field information */
    public static final String[] GET_SEARCH_RESULT_FLD_NM_LIST = {
        SCAN_DATE
        , SCANNED_DATE
        , LEASE_DOC_ID
    };

    /** For search condition field information */
    public static final String[] GET_SEARCH_CONDITION_FLD_NM_LIST = {
        "Line of BusinesNo"
        , "PriorityNo"
        , "Submitted By"
    };
    //QC#14927,15194 END

    // =================================================
    // Message Code
    // =================================================

    /**
     * It failed to update index attribute for Therefore.
     */
    public static final String NWAM0887E = "NWAM0887E";

    /**
     * Entered category can not be processed.(DOC_MGT_CATG)
     */
    public static final String NWAM0889E = "NWAM0889E";

    /**
     * It failed to get [DOC_MGT_IDX_ATTRB_DFN]
     */
    public static final String NWAM0890E = "NWAM0890E";

    /**
     * It failed to get [Business documents information]
     */
    public static final String NWAM0891E = "NWAM0891E";

    /**
     * "Global Company Code" is required.
     */
    public static final String NWZM0188E = "NWZM0188E";

    /**
     * It failed to update the [@]. PK [@]
     */
    public static final String NWZM1024E = "NWZM1024E";

    /**
     * "Document ID" is required.
     */
    public static final String NWZM1938E = "NWZM1938E";

    /**
     * "Parent Business Document Number" is required.
     */
    public static final String NWZM1981E = "NWZM1981E";

    /**
     * "Category Code"  is required.
     */
    public static final String NWZM2000E = "NWZM2000E";

    /**
     * "Mode Code" is required.
     */
    public static final String NWZM2001E = "NWZM2001E";

    /**
     * The document category [@] is not defined.
     */
    public static final String NWZM2003E = "NWZM2003E";

    /**
     * An error occurred in DOC_MGT_INTFC_TRX  registration.
     */
    public static final String NWZM2009E = "NWZM2009E";

    /**
     * Specified  ATT_DOC_TP_CD is not defined in DOC_MGT_CATG.
     */
    public static final String NWZM2013E = "NWZM2013E";

    /**
     * An error occurred in DOC_MGT_SCAN_TRX  registration.
     */
    public static final String NWZM2037E = "NWZM2037E";

    /**
     * An error occurred in DOC_MGT_ATT_RQST registration.
     */
    public static final String NWZM2259E = "NWZM2259E";

    /**
     * Therefore Doc ID entered is invalid.Please double-check the Doc ID.
     */
    public static final String NWZM2052E = "NWZM2052E";

    /**
     * Failed to update indexed attributes in Therefore due to connection problem. Please contact system administrator.
     */
    public static final String NWZM2053E = "NWZM2053E";
}
