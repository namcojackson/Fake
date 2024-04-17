/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL9900.constant;

/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/03/09   Hitachi         T.Aoyagi        Update          QC#5213
 * 2016/05/10   Hitachi         A.Kohinata      Update          QC#5389
 * 2016/06/15   Hitachi         T.Aoyagi        Update          QC#9685
 * 2020/04/07   Hitachi         K.Sakurai       Update          QC#56253
 *</pre>
 */
public class NSAL9900Constant {

    /** Screen Id */
    public static final String SCREEN_ID = "NSAL9900Scrn00";

    /** Under Bar */
    public static final String STR_UNDER_BAR = "_";

    /** String Zero */
    public static final String STR_ZERO = "0";

    /** Padding number */
    public static final int PAD_NUM = 2;

    /** STR_XX_STRING */
    public static final String STR_XX_STRING = "xxString";

    /** STR_XX_NUMBER */
    public static final String STR_XX_NUMBER = "xxNumber";

    /** STR_XX_DATE */
    public static final String STR_XX_DATE = "xxDate";

    /** STR_XX_YEARMONTH */
    public static final String STR_XX_YEARMONTH = "xxYearmonth";

    /** STR_XX_YEAR */
    public static final String STR_XX_YEAR = "xxYear";

    /** STR_XX_TIME */
    public static final String STR_XX_TIME = "xxTime";

    /** STR_XX_TS */
    public static final String STR_XX_TS = "xxTs";

    /** STR_XX_LIST_CD */
    public static final String STR_XX_LIST_CD = "xxListCd";

    /** STR_XX_LIST_NM */
    public static final String STR_XX_LIST_NM = "xxListNm";

    /** STR_XX_ROW_NUM */
    public static final String STR_XX_ROW_NUM = "xxRowNum";

    /** STR_XX_STRING_PK */
    public static final String STR_XX_STRING_PK = "xxStringPk";

    /** STR_XX_NUMBER_PK */
    public static final String STR_XX_NUMBER_PK = "xxNumberPk";

    /** STR_XX_DATE_PK */
    public static final String STR_XX_DATE_PK = "xxDatePk";

    /** STR_XX_YEARMONTH_PK */
    public static final String STR_XX_YEARMONTH_PK = "xxYearmonthPk";

    /** STR_XX_YEAR_PK */
    public static final String STR_XX_YEAR_PK = "xxYearPk";

    /** STR_XX_TIME_PK */
    public static final String STR_XX_TIME_PK = "xxTimePk";

    /** STR_XX_TS_PK */
    public static final String STR_XX_TS_PK = "xxTsPk";

    /** STR_XX_LIST_CD_PK */
    public static final String STR_XX_LIST_CD_PK = "xxListCdPk";

    /** STR_XX_LIST_NM_PK */
    public static final String STR_XX_LIST_NM_PK = "xxListNmPk";

    /** STR_XX_ROW_NUM_PK */
    public static final String STR_XX_ROW_NUM_PK = "xxRowNumPk";

    /** Column Type:String */
    public static final String COL_TP_STRING = "String";

    /** Column Type:Number */
    public static final String COL_TP_NUMBER = "Number";

    /** Column Type:Date */
    public static final String COL_TP_DATE = "Date";

    /** Column Type:YearMonth */
    public static final String COL_TP_YEARMONTH = "YearMonth";

    /** Column Type:Year */
    public static final String COL_TP_YEAR = "Year";

    /** Column Type:Time */
    public static final String COL_TP_TIME = "Time";

    /** Column Type:Ts */
    public static final String COL_TP_TS = "Ts";

    /** Display Control:CheckBox */
    public static final String DPLY_CTRL_CHECK_BOX = "CheckBox";

    /** Display Control:Pulldown */
    public static final String DPLY_CTRL_PULL_DOWN = "PullDown";

    /** Display Control:Popup */
    public static final String DPLY_CTRL_POP_UP = "Popup";

    /** Search Condition Suffix */
    public static final String SRCH_COND_SFX = "A";

    /** Width 8 */
    public static final int WIDTH_8 = 8;

    /** Calendar width */
    public static final int CALENDAR_WIDTH = 31;

    /** Pulldown width */
    public static final int PULLDOWN_WIDTH = 27;

    /** Popup width */
    public static final int POPUP_WIDTH = 50;

    // START 2016/03/09 T.Aoyagi [QC#5213, ADD]
    /** Checkbox width */
    public static final int CHECKBOX_WIDTH = 16;
    // END 2016/03/09 T.Aoyagi [QC#5213, ADD]

    /** TMsg Package */
    public static final String TMSG_PACKAGE = "business.db.";

    /** TMsg Suffix */
    public static final String TMSG_SFX = "TMsg";

    /** Get Relation Column Name SQL */
    public static final String GET_RELN_COL_NM_SQL = "(SELECT B.$physRelnColNm$ FROM $physRelnTblNm$ B WHERE B.GLBL_CMPY_CD = A.GLBL_CMPY_CD AND B.$physRelnColCd$ = A.$physMaintTrgtColNm$ AND B.EZCANCELFLAG = '0') AS $aliasColNm$";

    /** Table Alias */
    public static final String TBL_ALIAS = "A";

    /** Where And */
    public static final String WHERE_AND = " AND ";

    /** Where Equal */
    public static final String WHERE_EQUAL = " = ";

    /** Where Like */
    public static final String WHERE_LIKE = " LIKE ";

    // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
    /** Where Is null */
    public static final String WHERE_IS_NULL = " IS NULL ";
    // END 2016/06/15 T.Aoyagi [QC#9685, ADD]

    /** Period Dev*/
    public static final String PERIOD_DEV = ".";

    /** Conma Dev */
    public static final String COMMA_DEV = ",";

    /** Single Quotation Dev */
    public static final String SINGLE_QUOTE_DEV = "'";

    /** Space Dev */
    public static final String SPACE_DEV = " ";

    /** PK_SFX */
    public static final String PK_SFX = "_PK";

    /** SQ_SFX */
    public static final String SQ_SFX = "_SQ";

    /** PK Column Count */
    public static final int PK_COL_CNT = 10;

    /** Upload Error Code */
    public static final int UPLOAD_ERR_CD = 1000;

    /** Index 4 */
    public static final int IDX_4 = 4;

    /** Index 6 */
    public static final int IDX_6 = 6;

    /** Index 8 */
    public static final int IDX_8 = 8;

    /** Index 17 */
    public static final int IDX_17 = 17;

    /** MSG_ID_NSAM0020E : No target data exists. */
    public static final String MSG_ID_NSAM0020E = "NSAM0020E";

    // add start 2016/05/10 QC#5389
    /** [@] is duplicated. */
    public static final String MSG_ID_NSAM0035E = "NSAM0035E";
    // add end 2016/05/10 QC#5389

    /** MSG_ID_NSAM0206E : The format of [@] is incorrect. */
    public static final String MSG_ID_NSAM0206E = "NSAM0206E";

    /** MSG_ID_NSAM0214E : The number for this process exceeds the maximum numbers for display and can not process. */
    public static final String MSG_ID_NSAM0214E = "NSAM0214E";

    /** MSG_ID_NSAM0219E : System Error : @ */
    public static final String MSG_ID_NSAM0219E = "NSAM0219E";

    // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
    /** This data is duplicate the other one. */
    public static final String MSG_ID_NSAM0537E = "NSAM0537E";
    // END 2016/06/15 T.Aoyagi [QC#9685, ADD]

    /** MSG_ID_NZZM0000E : No search results found. */
    public static final String MSG_ID_NZZM0000E = "NZZM0000E";

    /** MSG_ID_NZZM0002I : The process has been successfully completed. */
    public static final String MSG_ID_NZZM0002I = "NZZM0002I";

    /** MSG_ID_NZZM0003E : This data has been updated by another user. */
    public static final String MSG_ID_NZZM0003E = "NZZM0003E";

    /** MSG_ID_NZZM0009E : Please select only one Check Box. */
    public static final String MSG_ID_NZZM0009E = "NZZM0009E";

    /** MSG_ID_NZZM0011E : Please check at least 1 checkbox. */
    public static final String MSG_ID_NZZM0011E = "NZZM0011E";

    // START 2020/04/06 K.Sakurai [QC#63253, MOD]
    /** MSG_ID_NZZM0013E : The number of Detail rows reached to the maximum. */
    public static final String MSG_ID_NZZM0013E = "NZZM0013E";
    // END 2020/04/06 K.Sakurai [QC#63253, MOD]

    /** MSG_ID_ZYEM0004E : The Upload File is empty or only has a header line, therefore it could not be processed. Please confirm the content of the  Upload file. */
    public static final String MSG_ID_ZYEM0004E = "ZYEM0004E";

    /** MSG_ID_ZZM9001E : [@] field has too many digits entered. */
    public static final String MSG_ID_ZZM9001E = "ZZM9001E";

    /** MSG_ID_ZZZM9002W : Too many search results.  Please narrow your search criteria and retry */
    public static final String MSG_ID_ZZZM9002W = "ZZZM9002W";

    /** MSG_ID_ZZZM9006E : [@] is not found. */
    public static final String MSG_ID_ZZZM9006E = "ZZZM9006E";

    /** MSG_ID_ZZZM9007E : The field of [@] is not input. */
    public static final String MSG_ID_ZZZM9007E = "ZZZM9007E";

    /** MSG_ID_ZZZM9012E : Data insert failure.(ReturnCode = [@]) */
    public static final String MSG_ID_ZZZM9012E = "ZZZM9012E";

    /** MSG_ID_ZZZM9013E : Data update failure.(ReturnCode = [@]) */
    public static final String MSG_ID_ZZZM9013E = "ZZZM9013E";

    /** MSG_ID_ZZZM9014E : Data delete failure.(ReturnCode = [@]) */
    public static final String MSG_ID_ZZZM9014E = "ZZZM9014E";
}
