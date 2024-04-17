/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0160.constant;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/08/10   Hitachi         K.Kojima        Update          QC#13129
 * 2017/01/05   Fujitsu         T.Murai         Update          QC#16295
 * 2017/09/06   Hitachi         T.Tsuchida      Update          QC#20970
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Failed to update [@]. */
    public static final String NFDM0004E = "NFDM0004E";

    /** Failed to insert [@]. */
    public static final String NFDM0013E = "NFDM0013E";

    /** Please select data from PopUp. */
    public static final String NFDM0023E = "NFDM0023E";

    /** You can not set this data's Portfolio Name to Portfolio Next Level. */
    public static final String NFDM0026E = "NFDM0026E";

    /** This data does not exist. */
    public static final String NFDM0027E = "NFDM0027E";

    /** Data set up Error. */
    public static final String NFDM0028E = "NFDM0028E";

    /** You can not set this Portfolio Name, because it is related to this data's Portfolio Name. */
    public static final String NFDM0029E = "NFDM0029E";

    /** Selected Collector Portfolio's status is inactive. Please select active Collector Portfolio. */
    public static final String NFDM0030E = "NFDM0030E";

    // START 2016/08/09 K.Kojima [QC#13129,ADD]
    /** Portfolio status cannot be changed to Inactive because active master exists.[@] */
    public static final String NFDM0036E = "NFDM0036E";

    /** Portfolio status cannot be changed to Inactive because it has already been set other portfolio next level. */
    public static final String NFDM0037E = "NFDM0037E";
    // END 2016/08/09 K.Kojima [QC#13129,ADD]

    // START 2018/08/03 T.Ogura [QC#25899,ADD]
    /** @ has a duplicate of data. */
    public static final String NFDM0019E = "NFDM0019E";

    /** There is no changed item. */
    public static final String NFDM0051E = "NFDM0051E";
    // END   2018/08/03 T.Ogura [QC#25899,ADD]

    // --------------------------------
    // 
    // --------------------------------
    /** cltPtfoStsFlg radioBtn Select 'Active' */
    public static final String SELECT_ACT = "A";

    /** cltPtfoStsFlg radioBtn Select 'Inactive' */
    public static final String SELECT_INACT = "I";

    /** cltPtfoStsFlg radioBtn Select 'All' */
    public static final String SELECT_ALL = "B";

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 61 */
    public static final int IDX_61 = 61;

    // START 2017/04/05 [QC#16295,ADD]
    /** Var Char Const : NFDL0160_AR_ADJ_TP_CD */
    public static final String CONST_NM_NFDL0160_AR_ADJ_TP_CD = "NFDL0160_AR_ADJ_TP_CD";
    // START 2017/04/05 [QC#16295,ADD]

    // START 2017/09/06 T.Tsuchida [QC#20970,ADD]
    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NFDL0160";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "Collector Portfolio";
    // END 2017/09/06 T.Tsuchida [QC#20970,ADD]
}
