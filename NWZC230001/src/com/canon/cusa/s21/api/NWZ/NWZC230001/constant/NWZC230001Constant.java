/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC230001.constant;

/**
 *<pre>
 * Therefore Master Document Maintenance API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/11   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */

public class NWZC230001Constant {

    /**
     * VAR_CHAR_CONST_NM : THEREFORE_CONN_AVAL_FLG
     */
    public static final String THEREFORE_CONN_AVAL_FLG = "THEREFORE_CONN_AVAL_FLG";

    /**
     * ATT_DATA_TP_THEREFORE : TF
     */
    public static final String ATT_DATA_TP_THEREFORE = "TF";

    /**
     * DOC_MGT_CATG_NUM
     */
    public static final String COL_DOC_MGT_CATG_NUM = "DOC_MGT_CATG_NUM";

    /**
     * DOC_MGT_FLD_TP_CD
     */
    public static final String COL_DOC_MGT_FLD_TP_CD = "DOC_MGT_FLD_TP_CD";

    /**
     * DOC_MGT_FLD_NUM
     */
    public static final String COL_DOC_MGT_FLD_NUM = "DOC_MGT_FLD_NUM";

    /**
     * DOC_MGT_FLD_DESC_TXT
     */
    public static final String COL_DOC_MGT_FLD_DESC_TXT = "DOC_MGT_FLD_DESC_TXT";

    /**
     * DOC_MGT_DOC_ID
     */
    public static final String COL_DOC_MGT_DOC_ID = "DOC_MGT_DOC_ID";

    /**
     * DOC_MGT_ORG_CD
     */
    public static final String COL_DOC_MGT_ORG_CD = "DOC_MGT_ORG_CD";

    /**
     * DOC_MGT_SCAN_BR_CD
     */
    public static final String COL_DOC_MGT_SCAN_BR_CD = "DOC_MGT_SCAN_BR_CD";


    /**
     * IDX_ATTRB_TRGT_COL_NM
     */
    public static final String COL_IDX_ATTRB_TRGT_COL_NM = "IDX_ATTRB_TRGT_COL_NM";

    /** For search condition field information */
    public static final String[] GET_SEARCH_CONDITION_FLD_NM_LIST = {
        "Line of Business"
        , "Branch GL Code"
    };

    /**
     * UPDATE MODE
     */
    public static final String UPDATE_MODE = "U";

    /**
     * DELETE MODE
     */
    public static final String DELETE_MODE = "D";

    /**
     * CheckIn Comment : NWZC2300 Therefore Master Document Maintenance API
     */
    public static final String CHECKIN_COMMNT = "NWZC2300 Therefore Master Document Maintenance API";

    /**
     * Date length : 14
     */
    public static final int DATE_LENGTH = 17;

    /**
     * EZDsystemporperty key : S21.therfore.attachment.url
     */
    public static final String EZDSYS_KEY_THEREFORE_URL = "S21.therfore.attachment.url";

    /**
     * Time Stamp Format
     */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    // =================================================
    // Message Code
    // =================================================

    /**
     * "Global Company Code" is required.
     */
    public static final String NWZM0188E = "NWZM0188E";

    /**
     * "Category Number" is required.
     */
    public static final String NWZM1939E = "NWZM1939E";

    /**
     * "Business Document Number" is required.
     */
    public static final String NWZM1941E = "NWZM1941E";

    /**
     * "Mode Code" is required.
     */
    public static final String NWZM2001E = "NWZM2001E";

    /**
     * "Parent Business Document Number" is required.
     */
    public static final String NWZM1981E = "NWZM1981E";

    /**
     * It failed to get [DOC_MGT_IDX_ATTRB_DFN].
     */
    public static final String NWZM2216E = "NWZM2216E";

    /**
     * It failed to get [Branch maintenance information].
     */
    public static final String NWZM2217E = "NWZM2217E";

    /**
     * It failed to get [Therefore Document ID].
     */
    public static final String NWZM2218E = "NWZM2218E";

}
