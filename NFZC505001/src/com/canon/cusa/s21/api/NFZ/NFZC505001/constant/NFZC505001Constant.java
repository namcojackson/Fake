/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC505001.constant;

/**
 *<pre>
 * NFZC5050:Therefore Indexed Attribute Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/12   Fujitsu         W.Honda         Create          N/A
 * 2017/03/09   Fujitsu         T.Aoi           Update          QC#17544
 *</pre>
 */

public class NFZC505001Constant {

    /** APP_ID */
    public static final String APP_ID = "NFZC5050";

    /** DOC_MGT_FLD_CD */
    public static final String COL_DOC_MGT_FLD_CD = "DOC_MGT_FLD_CD";

    /** IDX_ATTRB_TRGT_COL_NM */
    public static final String COL_IDX_ATTRB_TRGT_COL_NM = "IDX_ATTRB_TRGT_COL_NM";

    // 2017/02/22 S21_NA#17544 Add Start
    /** DOC_MGT_FLD_DESC_TXT */
    public static final String COL_DOC_MGT_FLD_DESC_TXT = "DOC_MGT_FLD_DESC_TXT";
    // 2017/02/22 S21_NA#17544 Add End

    /** DOC_MGT_FLD_TP_CD */
    public static final String COL_DOC_MGT_FLD_TP_CD = "DOC_MGT_FLD_TP_CD";

    /** DOC_MGT_FLD_NUM */
    public static final String COL_DOC_MGT_FLD_NUM = "DOC_MGT_FLD_NUM";

    /** CheckIn Comment : NWZC2240 Therefore Indexed Attribute Update API */
    public static final String CHECKIN_COMMNT = "NWZC2240 Therefore Indexed Attribute Update API";

    // 2017/03/09 S21_NA#17544 Add Start
    /** Invoice Status */
    public static final String INV_STS = "Invoice Status";
    // 2017/03/09 S21_NA#17544 Add End

    /** ATTACH_MODE_CD : 01 */
    public static final String ATTACH_MODE_CD = "01";

    /** DETACH_MODE_CD : 02 */
    public static final String DETACH_MODE_CD = "01";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Date length : 14 */
    public static final int DATE_LENGTH = 17;

    /** parameter value : AP_VND_INV_SQ_NUM */
    public static final String PARAM_VALUE_AP_VND_INV_SQ_NUM = "00";

    // =================================================
    // Message Code
    // =================================================

    /** It failed to update index attribute for Therefore. */
    public static final String NFBM0239E = "NFBM0239E";

    /** Entered category can not be processed.(DOC_MGT_CATG) */
    public static final String NFBM0240E = "NFBM0240E";

    /** It failed to get [DOC_MGT_IDX_ATTRB_DFN] */
    public static final String NFBM0241E = "NFBM0241E";

    /** It failed to get [Business documents information] */
    public static final String NFBM0242E = "NFBM0242E";

    /** "Global Company Code" is required. */
    public static final String NFBM0243E = "NFBM0243E";

    /** "Document ID" is required. */
    public static final String NFBM0244E = "NFBM0244E";

    /** "Category Code"  is required. */
    public static final String NFBM0245E = "NFBM0245E";

    /** "Mode Code" is required. */
    public static final String NFBM0246E = "NFBM0246E";

    /** "Vendor Code" is required. */
    public static final String NFBM0247E = "NFBM0247E";

    /** "Vendor Invoice Number" is required. */
    public static final String NFBM0248E = "NFBM0248E";

    /** "Vendor Invoice Sequence Number" is required. */
    public static final String NFBM0249E = "NFBM0249E";

    /** The document category [@] is not defined. */
    public static final String NFBM0250E = "NFBM0250E";

    /** The document category is not defined. */
    public static final String NFBM0251E = "NFBM0251E";

    /** The document category is not supported for document attach. */
    public static final String NFBM0252E = "NFBM0252E";

    /** Therefore Doc ID entered is invalid.Please double-check the Doc ID. */
    public static final String NFZM0029E = "NFZM0029E";

    /** Failed to update indexed attributes in Therefore due to connection problem. Please contact system administrator. */
    public static final String NFZM0030E = "NFZM0030E";

    // 2017/03/09 S21_NA#17544 Add Start
    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";
    // 2017/03/09 S21_NA#17544 Add End

    /** VAR_CHAR_CONST : THEREFORE_CONN_AVAL_FLG */
    public static final String VAR_CHAR_CONST_KEY_THEREFORE_CONN_AVAL_FLG = "THEREFORE_CONN_AVAL_FLG";

    /** VAR_CHAR_CONST : THEREFORE_ERR_INVALID_DOC */
    public static final String VAR_CHAR_CONST_KEY_THEREFORE_ERR_INVALID_DOC = "THEREFORE_ERR_INVALID_DOC";
}
