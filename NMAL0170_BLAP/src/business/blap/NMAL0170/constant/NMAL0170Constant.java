/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0170.constant;

/**
 *<pre>
 * NMAL0170Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Arima          Create          N/A
 * 2015/12/11   Fujitsu         T.Arima          Update          QC#1882
 * 2016/02/24   CITS            S.Tanikawa       Update          QC#2669
 * 2017/01/23   Fujitsu         R.Nakamura       Update          QC#17195
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17259
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17463
 *</pre>
 */
public class NMAL0170Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    // Add Start 2017/01/23 QC#17195
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";
    // Add End 2017/01/23 QC#17195

    /** No search results found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";
    
    // ADD START S21_NA #17259
    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";
    // ADD END S21_NA #17259

    // ADD START 2015/12/11
    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** This data has been updated by another user. [ TableName = @ , key = @, value = @ ] */
    public static final String NMAM8175E = "NMAM8175E";
    
    // ADD START S21_NA #17463
    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";
    // ADD END S21_NA #17463
    
    // ADD END 2015/12/11

    // ADD START 2016/02/24 QC#2669
    /** Please select registered supersession staging data. Not Registered data cannot be transferred to the next screen. */
    public static final String NMAM8373E = "NMAM8373E";

    /** You can't register same code between Item Code and Supersede. */
    public static final String NMAM8375E = "NMAM8375E";

    /** This supersession item had already registered. */
    public static final String NMAM8376E = "NMAM8376E";

    /** This Item Code already registerd as Supersession */
    public static final String NMAM8377E = "NMAM8377E";

    /** Please avoid supersede circulation setting */
    public static final String NMAM8378E = "NMAM8378E";

    // ADD END 2016/02/24 QC#2669

    // --------------------------------
    // SQL Parameter
    // --------------------------------
    /** SQL Parameter : glblCmpyCd */
    public static final String SQL_PRM_GLBLCMPYCD = "glblCmpyCd";

    /** SQL Parameter : itemNumber */
    public static final String SQL_PRM_ITEM_NUMBER = "itemNumber";

    /** SQL Parameter : fromDt */
    public static final String SQL_PRM_FROM_DT = "fromDt";

    /** SQL Parameter : toDt */
    public static final String SQL_PRM_TO_DT = "toDt";

    /** SQL Parameter : itemClsTp */
    public static final String SQL_PRM_ITEMCLSTP = "itemClsTp";

    /** SQL Parameter : itemTp */
    public static final String SQL_PRM_ITEMTP = "itemTp";

    /** SQL Parameter : rowNum */
    public static final String SQL_PRM_ROWNUM = "rowNum";

    /** SQL Parameter : supdCd */
    public static final String SQL_PRM_SUPDCD = "supdCd";

    /** SQL Parameter : submitFlg */
    public static final String SQL_PRM_SUBMITFLG = "submitFlg";

    /** SQL ID : searchSupersedesStage */
    public static final String SQL_ID_SEARCH = "searchSupersedesStage";

    /** SQL ID : findSupdRelnStagePk */
    public static final String SQL_ID_FINDSUPDRELNSTAGEPK = "findSupdRelnStagePk";

    // ADD START 2016/02/23 QC#2669
    // --------------------------------
    // SQL Parameter
    // --------------------------------
    /** DB COLUMN : SUPD_FROM_MDSE_CD */
    public static final String COL_SUPD_FROM_MDSE_CD = "SUPD_FROM_MDSE_CD";
    // ADD END 2016/02/23 QC#2669
}
