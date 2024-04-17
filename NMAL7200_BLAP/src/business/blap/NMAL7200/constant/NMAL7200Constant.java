/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7200.constant;

/**
 *<pre>
 * NMAL7200Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 * 2016/09/05   Fujitsu         R.Nakamura      Update          QC#8222
 *</pre>
 */
public class NMAL7200Constant {


    //--------------------------------
    // Message ID
    //--------------------------------
    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search result were found.   */
    public static final String NMAM0007I = "NMAM0007I";

    /** First Selected Row Index */
    public static final int FIRST_SELECTED_ROW_INDEX = 0;

    /** Screen Max Row Size */
    public static final int SCREEN_MAX_ROW_SIZE = 200;

    /** Select Max Row Size */
    public static final int SELECT_MAX_ROW_SIZE = 201;

    /** Global Compy Code  */
    public static final String GLOBAL_CMPY_CODE = "glblCmpyCd";

    /** Prc Grp PK */
    public static final String PRC_GRP_PK = "prcGrpPk";

    /** Prc GRP Nm */
    public static final String PRC_GRP_NM = "prcGrpNm";

    /** Prc GRP Desc Txt */
    public static final String PRC_GRP_DESC_TXT = "prcGrpDescTxt";

    /** Prc Rule Attrb Cd */
    public static final String PRC_RULE_ATTRB_CD1 = "prcRuleAttrbCd1";

    /** Prc Rule Attrb Cd */
    public static final String PRC_RULE_ATTRB_CD2 = "prcRuleAttrbCd2";

    /** Prc Rule Attrb Cd */
    public static final String PRC_RULE_ATTRB_CD3 = "prcRuleAttrbCd3";

    /** Prc Rule Attrb Cd */
    public static final String PRC_RULE_ATTRB_CD4 = "prcRuleAttrbCd4";

    /** Usage Type1 */
    public static final String USAGE_TYPE1 = "usageType1";

    /** Usage Type2 */
    public static final String USAGE_TYPE2 = "usageType2";

    /** Usage Type List */
    public static final String USAGE_TYPE_LIST = "Price List";

    /** Usage Type Rule */
    public static final String USAGE_TYPE_RULE = "Price Rule";

    /** Actv Flg1 */
    public static final String ACTV_FLG1 = "actvFlg1";

    /** Actv Flg2 */
    public static final String ACTV_FLG2 = "actvFlg2";

    /** Actv Flg3 */
    public static final String ACTV_FLG3 = "actvFlg3";

    /** Actv Flg4 */
    public static final String ACTV_FLG4 = "actvFlg4";

    /** Actv Flg Y */
    public static final String ACTV_FLG_Y = "Y";

    /** Actv Flg YES  */
    public static final String ACTV_FLG_YES = "YES";

    /** Actv Flg N */
    public static final String ACTV_FLG_N = "N";

    /** Actv Flg No  */
    public static final String ACTV_FLG_NO = "NO";

    /** RowNum */
    public static final String ROWNUM = "rowNum";

    /** AttrbList */
    public static final String ATTRBLIST = "attrbList";

    // Add Start 2016/09/05 QC#8222
    /** File */
    public static final String CSV = ".csv";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7200 Price Group Usage";

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {
        "Usage Type"
        , "Usage Value"
        , "Usage ID"
        , "Usage Active"};
    // Add End 2016/09/05 QC#8222

}
