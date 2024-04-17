/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC117001.constant;

/**
 * <pre>
 * NPZC117001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/29/2015   Hitachi         T.Harada        Create
 * 05/31/2016   Hitachi         T.Iwamoto       Update          QC#8686
 * 2016/10/24   Hitachi         K.Kojima        Update          QC#15483
 * 2017/10/05   CITS            M.Naito         Update          QC#20990
 * 2017/12/12   CITS            K.Ogino         Update          QC#21784
 * 2018/10/16   CITS            T.Wada          Update          QC#27440
 * 2019/12/09   CITS            M.Naito         Update          QC#53421
 * 2020/02/18   CITS            T.Wada          Update          QC#55702
 * 2023/01/17   Hitachi         E.Watabe        Update          QC#60924
 * 2023/08/07   Hitachi         T.Kuroda        Update          QC#61648
 * 2023/10/25   Hitachi         T.Kuroda        Update          QC#61494
 * </pre>
 */
public final class NPZC117001Constant {

    /** This Program ID */
    public static final String PROGRAM_ID = "NPZC1170";

    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** An input parameter, [@], has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** The data @ does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";

    /** For input parameter @, only @ or a larger value can be registered. */
     public static final String NPZM0271E = "NPZM0271E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert the CLICK_TECH_ORD_PRT. */
    public static final String NPZM0206E = "NPZM0206E";

    /** No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";

    // QC#55702 Add
    /** This item is no longer available. */
    public static final String NZZM0016E = "NZZM0016E";

    // START 2023/08/07 T.Kuroda [QC#61648, ADD]
    /** Ordering Parts doesn't exist in inventory. Please select the other Delivery Option if Premium Rush Order needs. */
    public static final String NPZM0318E = "NPZM0318E";
    // END 2023/08/07 T.Kuroda [QC#61648, ADD]

    /*****************************************************************
     * DS_COND_CONST
     ****************************************************************/
     /** DS_COND_CONST GroupId : CLICK_COMMON */
    public static final String GRP_ID_CLICK_COMMON = "CLICK_COMMON";

    /** DS_COND_CONST KEY : TIME_FORMAT */
    public static final String TIME_FORMAT = "TIME_FORMAT";

    /** DS_COND_CONST GroupId : NPZC1170 */
    public static final String GRP_ID_NPZC1170 = "NPZC1170";

    /** DS_COND_CONST KEY : ITEM_VALIDATION_FLG */
    public static final String ITEM_VALIDATION_FLG = "ITEM_VALIDATION_FLG";

    /** DS_COND_CONST KEY : REQ_STATUS */
    public static final String REQ_STATUS = "REQ_STATUS";

    /** REQ_TP : Premium Rush */
    public static final String REQ_TP_PRE_RUSH = "REQ_TP_PRE_RUSH";

    /** REQ_TP : Rush1 */
    public static final String REQ_TP_RUSH_1 = "REQ_TP_RUSH_1";

    /** REQ_TP : Rush2 */
    public static final String REQ_TP_RUSH_2 = "REQ_TP_RUSH_2";

    /** REQ_TP : Rush3 */
    public static final String REQ_TP_RUSH_3 = "REQ_TP_RUSH_3";

    // START 2019/12/09 M.Naito [QC#53421,MOD]
    /** REQ_TP : Rush4 */
    public static final String REQ_TP_RUSH_4 = "REQ_TP_RUSH_4";
    // END 2019/12/09 M.Naito [QC#53421,MOD]

    /** REQ_TP : Standard 2Day */
    public static final String REQ_TP_STANDARD_2 = "REQ_TP_STANDARD_2";

    /** REQ_TP : Standard 3Day */
    public static final String REQ_TP_STANDARD_3 = "REQ_TP_STANDARD_3";
    // add start 2017/10/05 CSA Defect#20990
    /** REQ_TP : Will Call */
    public static final String REQ_TP_STANDARD_4 = "REQ_TP_STANDARD_4";
    // add end 2017/10/05 CSA Defect#20990

    /** DS_COND_CONST KEY : DELIVER_TO */
    public static final String DELIVER_TO = "DELIVER_TO";

    // START 2023/10/25 T.Kuroda [QC#61494, ADD]
    /** DS_COND_CONST KEY : SHIPPING_CUT_OFF_START_TIME */
    public static final String SHIPPING_CUT_OFF_START_TIME = "SHIPPING_CUT_OFF_START_TIME";

    /** DS_COND_CONST KEY : SHIPPING_CUT_OFF_END_TIME */
    public static final String SHIPPING_CUT_OFF_END_TIME = "SHIPPING_CUT_OFF_END_TIME";
    // END 2023/10/25 T.Kuroda [QC#61494, ADD]

    /*****************************************************************
     * OTHER CONST
     ****************************************************************/
    /** MAP_KEY: ADD_DAYS */
    public static final String KEY_ADD_DAYS = "ADD_DAYS";

    /** MAP_KEY: KEY_TASK_CHK_FLG */
    public static final String KEY_TASK_CHK_FLG = "KEY_TASK_CHK_FLG";

    /** MAP_KEY: PRCH_REQ_TP_CD */
    public static final String KEY_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** MAP_KEY: SRC_WH */
    public static final String KEY_SRC_WH = "SRC_WH";

    /** MAP_KEY: SHPG_SVC_LVL */
    public static final String KEY_SHPG_SVC_LVL = "SHPG_SVC_LVL";
    // add start 2016/10/04 CSA Defect#14803
    /** MAP_KEY: RQST_DT_TXT */
    public static final String KEY_RQST_DT_TXT = "RQST_DT_TXT";
    // add end 2016/10/04 CSA Defect#14803

    // START 2016/10/24 K.Kojima [QC#15483,ADD]
    /** MAP_KEY: DEF_ADD_TIME */
    public static final String KEY_DEF_ADD_TIME = "DEF_ADD_TIME";

    // END 2016/10/24 K.Kojima [QC#15483,ADD]

    /** EMER_SRC */
    public static final String EMER_SRC = "EMER_SRC";

    /** DEF_SRC */
    public static final String DEF_SRC = "DEF_SRC";

    // START 2023/08/07 T.Kuroda [QC#61648, ADD]
    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0002";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPZC1170M001";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** Mail Param Today */
    public static final String MAIL_PARAM_TODAY = "today";

    /** Mail Param Date */
    public static final String MAIL_PARAM_TIME = "time";

    /** Mail Param Details Info */
    public static final String MAIL_PARAM_MESSAGE = "message";

    /** Mail Date Format */
    public static final String MAIL_DATE_FORMAT = "MM/dd/yyyy";

    /** Mail Time Format */
    public static final String MAIL_TIME_FORMAT = "HH:mm";

    /** Mail item code Format */
    public static final String MAIL_ITEM_CODE_FORMAT = "%-24s";

    /** Mail item description Format */
    public static final String MAIL_ITEM_DESCRIPTIONE_FORMAT = "%-29s";

    /** 1 space. */
    public static final String MAIL_SPACE = " ";

    /** Mail Line separator */
    public static final String MAIL_LINE_SEPARATOR = System.getProperty("line.separator");
    // END 2023/08/07 T.Kuroda [QC#61648, ADD]

    // START 2023/10/25 T.Kuroda [QC#61494, ADD]
    /** 1 day */
    public static final int ONE_DAY = 1;

    /** 2 days */
    public static final int TWO_DAYS = 2;

    /** 3 days */
    public static final int THREE_DAYS = 3;

    /** 4 days */
    public static final int FOUR_DAYS = 4;
    // END 2023/10/25 T.Kuroda [QC#61494, ADD]

    /*****************************************************************
     * NPZC1030_CONST(PR Update API)
     ****************************************************************/

    /** DEST_RTL_SWH_CD : Default */
    public static final String DEST_RTL_SWH_CD_G = "G";

    // QC#21784
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Destination Warehouse Code
     */
    public static final String DB_PARAM_DEST_WH_CD = "destWhCd";

    /**
     * DB Param Name: Procurement Type Code PROCR_TP_CD Supplier
     */
    public static final String DB_PARAM_PROCR_TP_CD_SUPPLIER = "procrTpCdSupplier";

    /**
     * DB Param Name: Tech SWH CD DB_PARAM_DEST_SWH_CD
     */
    public static final String DB_PARAM_DEST_SWH_CD = "destSwhCd";
    
    // QC#27440 Add
    /** SHIP TO : PRIMARY_PICKUP */
    public static final String PRIMARY_PICKUP = "PRIMARY PICKUP";

    // Start 2023/01/17 E.Watabe [QC#60924,ADD]
    public static final String NPZC1170_EXCL_ITEM_TP = "NPZC1170_EX_ITEM_TP";
    // End 2023/01/17 E.Watabe [QC#60924,ADD]
}
