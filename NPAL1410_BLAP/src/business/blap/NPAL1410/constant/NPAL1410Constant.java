/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1410.constant;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12782
 * 08/16/2016   CSAI            Y.Imazu         Update          QC#13406
 * 10/21/2016   CITS            T.Hakodate      Update          QC#15057
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 * 01/16/2019   Fujitsu         T.Ogura         Update          QC#29893
 * 04/01/2019   CITS            K.Ogino         Update          QC#30851
 * 12/05/2019   Fujitsu         T.Ogura         Update          QC#54842
 *</pre>
 */
public class NPAL1410Constant {

    /** Business Application ID */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1410";

    /** default swh code */
    public static final String DEFAULT_SWH = "NEW";

    /** . */
    public static final String TIME_FORMAT = "HHmmss";

    /** . */
    public static final String EVENT_TYPE_CO = "CO";

    /** . */
    public static final String EVENT_TYPE_CC = "CC";

    /** . */
    public static final String EVENT_TYPE_P = "P";

    /** . */
    public static final String EVENT_TYPE_NPAL6800 = "NPAL6800";

    /** . */
    public static final String RMNF_ORD_NUM_SQ = "RMNF_ORD_NUM_SQ";

    /** . */
    public static final String RMNF_PRT_REQ_SQ = "RMNF_PRT_REQ_SQ";

    /** . */
    public static final String RWS_REF_NUM_RR = "-RR";

    /** . */
    public static final String RWS_REF_NUM_2 = "-2";

    // =================================================
    // e-mail
    // =================================================
    /** Mail Message Format */
    public static final String ML_FMT = "%-16s %-40s  %s";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPAL1410M001";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NPAL1410";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_NUM = "prNum";

    /** . */
    public static final String EMAIL_PARAM_WH_CD = "whCd";

    /** . */
    public static final String EMAIL_PARAM_DATE_REQ = "requestDt";

    /** . */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Protected Control Mode */
    public static final String MODE_INIT = "0";

    /** Protected Control Mode */
    public static final String MODE_EDIT = "1";

    /** Protected Control Mode */
    public static final String MODE_IN_PROCESS = "2";

    /** Protected Control Mode */
    public static final String MODE_READ_ONLY = "3";

    /** App Key for header */
    public static final String APP_KEY_ID_HEADER = "01";

    /** App Key for parts */
    public static final String APP_KEY_ID_PARTS = "02";

    /** . */
    public static final String REQUEST_TYPE_NEW_ALLOC = "1";

    /** . */
    public static final String TRX_LINE_SUB_NUM = "001";

    /** Tab Configration */
    public static final String TAB_CONF = "Config";

    /** Tab Parts */
    public static final String TAB_PARTS = "Parts";

    /** Tab Task */
    public static final String TAB_TASK = "Task";

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** No search results found. */
    public static final String NPAM0002E = "NPAM0002E";

    /** Valid [@] does not exist in master. */
    public static final String NPAM0224E = "NPAM0224E";

    /** Configration can't be specified, please enter the Item Number. */
    public static final String NPAM1492E = "NPAM1492E";

    /** Please Enter Serial# of Main Machine. */
    public static final String NPAM1493E = "NPAM1493E";

    /** [@] does not exist in Master. */
    public static final String NPAM0076E = "NPAM0076E";

    // QC#15057
    /** NAPM1584E:Intangible Item can't be entered. */
    public static final String NPAM1584E = "NPAM1584E";

    /**
     * The selected locator has been used for another open Reman
     * Order. #@
     */
    public static final String NPAM1533E = "NPAM1533E";

    /** Supplier Item Number cannot be obtained from ASL Table. */
    public static final String NPZM0268E = "NPZM0268E";

    /** If serialized item is selected, please enter Serial#, too. */
    public static final String NPAM1435E = "NPAM1435E";

    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";

    /** . */
    public static final String ZZM9000E = "ZZM9000E";

    /** This data has been updated by another user. */
    public static final String NPAM0006E = "NPAM0006E";

    /** Main Unit Serial is required. */
    public static final String NPAM1378E = "NPAM1378E";

    /**
     * Config has been changed for this serial, please click Get
     * Config button.
     */
    public static final String NPAM1546E = "NPAM1546E";

    /** Another Main Machine can't be added in the Config ID. */
    public static final String NPAM1547E = "NPAM1547E";

    /** The entered serial is already assigned for another config. */
    public static final String NPAM1548E = "NPAM1548E";

    /** It failed to register an email. */
    public static final String NPZM0161E = "NPZM0161E";

    /**
     * Error occurred due to a process unable to complete. Please
     * contact IT Dept.
     */
    public static final String NLAM1077E = "NLAM1077E";

    /**
     * There is parts request detail that has not been receipt at
     * Reman locator yet.
     */
    public static final String NPAM1541E = "NPAM1541E";

    /**
     * There is Shipping Order for Reman Machine that has been not
     * shipped yet.
     */
    public static final String NPAM1574E = "NPAM1574E";

    /**
     * By clicking the [@] button, the data will be deleted
     * permanently.
     */
    public static final String NPAM1577W = "NPAM1577W";

    //QC#18675 ADD START
    /**
     * Main Unit Stock Status must be "To be reman".
     */
    public static final String NPAM1604E = "NPAM1604E";
    //QC#18675 ADD END

    /** RTL_WH/RTL_SWH Split possible length. */
    public static final int LG_WH = 6;

    /** Start position of RTL_WH */
    public static final int POS_RTL_WH_ST = 0;

    /** End position of RTL_WH */
    public static final int POS_RTL_WH_ED = 3;

    /** End position of RTL_SWH */
    public static final int POS_RTL_SWH_ED = 6;

    /**
     * When the reman order finished, the selected data will be
     * deleted from config master.
     */
    public static final String NPAM1582W = "NPAM1582W";

    /**
     * Configration has not been determined Please Click Get Config
     * Button.
     */
    public static final String NAPM1583E = "NAPM1583E";

    /**
     * The combination of [@] and [@] is incorrect.
     */
    public static final String NPAM1586E = "NPAM1586E";

    /**
     * Starting line number of Parts
     */
    public static final int DEF_PARTS_LINE_NO = 101;

    /**
     * QC#22836
     * No search results found of [@].
     */
    public static final String NPAM0020E = "NPAM0020E";

    /** [Main Unit Serial] has been rewritten. Please execute [Get Config] again. */
    public static final String NPAM1640E = "NPAM1640E";

    // QC#30851
    /** Multiple records are found with the entered [@] value. Please select from a popup screen. */
    public static final String NPAM1589E = "NPAM1589E";

    /** NPAM1647E:IB Trackable Item can't be entered. */
    public static final String NPAM1647E = "NPAM1647E";    // 2019/12/05 T.Ogura [QC#54842,ADD]
}
