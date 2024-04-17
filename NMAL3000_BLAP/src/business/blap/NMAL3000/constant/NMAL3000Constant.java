/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3000.constant;

/**
 *<pre>
 * NMAL3000Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL3000Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NMAL3000";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL3000Scrn00";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The entered [@] does not exist in master */
    public static final String NMAM8121E = "NMAM8121E";

    /** Invalid combination.  Sales & Service */
    public static final String NMAM0070E = "NMAM0070E";

    /**  past date cannot be entered into the "Date Effective From */
    public static final String NMAM0185E = "NMAM0185E";

    /** Dealer Authorization Detail is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** Could not get the Account related Information */
    public static final String NMAM8203W = "NMAM8203W";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] is not selected.*/
    public static final String NMAM0014E = "NMAM0014E";

    /** 「@ cannot be added because it is exceeding the maximum number of row [@]」 */
    public static final String NMAM8187E = "NMAM8187E";

    /** It failed to register [@].*/
    public static final String NMAM0176E = "NMAM0176E";

    /** Please select item(s) */
    public static final String NMAM8054E = "NMAM8054E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Table Name Ds Dlr Auth Info */
    public static final String  TABLE_NAME_DS_DLR_AUTH_INFO = "DS_DLR_AUTH_INFO";

    /** GlblCmpy Code */
    public static final String GLBCMPY_CD = "glblCmpyCd";

    /** Account Num */
    public static final String ACCOUNT_NUM = "accountNum";

    /** Model */
    public static final String MODEL = "model";

    /** Dealer Code */
    public static final String DEALER_CODE = "dealerCd";

    /** Effective Date From */
    public static final String EFF_DATE_FROM = "effDateFrom";

    /** Effective Date TO */
    public static final String EFF_DATE_TO = "effDateTo";

    /** Sales */
    public static final String SALES = "sales";

    /** Service */
    public static final String SERVICE = "service";

    /** Record Not Found */
    public static final int RECORD_NOTFOUND = 0;

    /** GlblCmpy Code */
    public static final String GLBCMPY_CD01 = "glblCmpyCd01";
    
    /** Sell To Cust Code */
    public static final String SELL_TO_CUST_CD01 = "sellToCustCd01";

    /** Insert Flg */
    public static final String INSERT_FLG = "I";

    /** RowNum */
    public static final String ROWNUM = "rowNum";

    /** Check Box */
    public static final String CHK_A = "xxChkBox_A";

    /** Csv FileName */
    public static final String CSV_FILE_NAME =  "NMAL3000 Dealer Authorization Maintenance";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /**Max Row */
    public static final int  MAX_ROW = 5000;

    /** yyyymmdd Length */
    public static final int  YYYYMMDD_LENGTH = 8;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

}
