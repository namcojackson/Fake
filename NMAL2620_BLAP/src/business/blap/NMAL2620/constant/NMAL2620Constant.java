/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2620.constant;

/**
 *<pre>
 * Territory Mass Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public final class NMAL2620Constant {

    /**
     * Period1:30Days
     */
    public static final String PERIOD_VAL_1 = "30Days";

    /**
     * Period1:60Days
     */
    public static final String PERIOD_VAL_2 = "60Days";

    /**
     * Period3:90Days
     */
    public static final String PERIOD_VAL_3 = "90Days";

    /**
     * Period1:30
     */
    public static final String PERIOD_1 = "30";

    /**
     * Period2:60
     */
    public static final String PERIOD_2 = "60";

    /**
     * Period3:90
     */
    public static final String PERIOD_3 = "90";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * DS_MSG_TRX_NM
     */
    public static final String DS_MSG_TRX_NM = "DS_MTR_INTFC_PK";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL2620";

    /** LIMIT_DOWNLOAD:65000 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** SLS_FLG */
    public static final String SLS_FLG = "Y";

    /** xxChkBox_A */
    public static final String CHK_BOX_A = "xxChkBox_A";

    /** Service Mode : 01 */
    public static final String SERVICE_MODE_01 = "01";

    /** Service Mode : 02 */
    public static final String SERVICE_MODE_02 = "02";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** ON_LINE */
    public static final String ON_LINE = "01";

    /** SUBMITTED */
    public static final String SUBMITTED = "01";

    /** ZZZM9007E */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** NMAM0185E */
    public static final String NMAM0185E = "NMAM0185E";

    /** NMAM0192E */
    public static final String NMAM0192E = "NMAM0192E";

    /** Territory Resource Assignment(s) can be active only within the Territory Effective Dates. */
    public static final String NMAM8448E = "NMAM8448E";

    /** Resource is not within @ business area. Please select another resource. */
    public static final String NMAM8397E = "NMAM8397E";

    /** Resource is not within @ business area. Please select another resource. */
    public static final String NMAM0179E = "NMAM0179E";

    /** [@] cannot be entered [@] */
    public static final String NMAM8450E = "NMAM8450E";

    /** Too many search results.  Please narrow your search criteria and retry. */
    public static final String NZZM0007E = "NZZM0007E";

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** @ cannot be selected. */
    public static final String NMAM0183E = "NMAM0183E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] can not be obtained. */
    public static final String NMAM8489E = "NMAM8489E";

    /** The entered [@] does not exist. */
    public static final String NMAM0009E = "NMAM0009E";

    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";

    /** Move to resource already have relations with selected territory in same term.  */
    public static final String NMAM8511E = "NMAM8511E";

    /** Organization relationship can not be deleted when active child organizations exists. Please terminate from bottom tier first. */
    public static final String NMAM8361E = "NMAM8361E";

    /** Territory relationship can not be deleted after parent territory's relation expires. Please terminate before relationsihp of parent territory expires. */
    public static final String NMAM8637E = "NMAM8637E";

    /** CSV File Name Download */
    public static final String CSV_DOWNLOAD_FILE_NAME = "NMAL2620";

    /** CSV Header For Download */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {"<*Territory Name>(<Alphabet Numeric Character>(50))", "<*Current Resource #>(<Alphabet Numeric Character>(50))", "<Move Resource #>(<Alphabet Numeric Character>(50))"
        , "<*Move Effective From(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))", "<Move Effective Thru(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))"
        , "<*Update Mode>(<Alphabet Numeric Character>(50))", "<Mass update reason>(<Alphabet Numeric Character>(4000))" };
}
