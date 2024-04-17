package business.blap.NWAL2040.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/24   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public interface NWAL2040Constant {

    String TAB_SRC_CATG = "SrcCatg";

    String TAB_MAP_TMPL_QLFY = "MapTmpQlfy";

    // 2017/09/12 QC#19805 Add Start
    String TAB_MAP_TMPL_QLFY_RMA = "MapTmpQlfyRMA";
    // 2017/09/12 QC#19805 Add End

    /**
     * RETURN_CD_NORMAL -- 0000
     */
    String RETURN_CD_NORMAL = "0000";

    // START 2016/03/24 K.Kojima [UT#56,ADD]
    /** Fetch size for Download */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Download limit */
    public static final int DOWNLOAD_LIMIT_CNT = 65000;

    /** Message Id : NZZM0000E */
    public static final String NZZM0000E = "NZZM0000E";

    /** Message Id : NZZM0001W */
    public static final String NZZM0001W = "NZZM0001W";

    /** MDSE_ITEM_CLS_TP_CD is asterisk */
    public static final String MDSE_ITEM_CLS_TP_CD_AST = "*";

    /** Column Name : DEF_WH_MAP_TMPL_CD */
    public static final String DEF_WH_MAP_TMPL_CD = "DEF_WH_MAP_TMPL_CD";

    /** Column Name : MDSE_ITEM_CLS_TP_NM */
    public static final String MDSE_ITEM_CLS_TP_NM = "MDSE_ITEM_CLS_TP_NM";

    /** Column Name : THIRD_PTY_ITEM_FLG */
    public static final String THIRD_PTY_ITEM_FLG = "THIRD_PTY_ITEM_FLG";

    /** Column Name : FROM_POST_CD */
    public static final String FROM_POST_CD = "FROM_POST_CD";

    /** Column Name : TO_POST_CD */
    public static final String TO_POST_CD = "TO_POST_CD";

    /** Column Name : ON_HND_CHK_FLG */
    public static final String ON_HND_CHK_FLG = "ON_HND_CHK_FLG";

    /** Column Name : OTBD_PRIM_ON_HND_CHK_WH_CD */
    public static final String OTBD_PRIM_ON_HND_CHK_WH_CD = "OTBD_PRIM_ON_HND_CHK_WH_CD";

    /** Column Name : OTBD_PRIM_ON_HND_CHK_SWH_NM */
    public static final String OTBD_PRIM_ON_HND_CHK_SWH_NM = "OTBD_PRIM_ON_HND_CHK_SWH_NM";

    /** Column Name : OTBD_PRIM_ON_HND_LIN_SRC_NM */
    public static final String OTBD_PRIM_ON_HND_LIN_SRC_NM = "OTBD_PRIM_ON_HND_LIN_SRC_NM";

    /** Column Name : OTBD_SCD_ON_HND_CHK_WH_CD */
    public static final String OTBD_SCD_ON_HND_CHK_WH_CD = "OTBD_SCD_ON_HND_CHK_WH_CD";

    /** Column Name : OTBD_SCD_ON_HND_CHK_SWH_NM */
    public static final String OTBD_SCD_ON_HND_CHK_SWH_NM = "OTBD_SCD_ON_HND_CHK_SWH_NM";

    /** Column Name : OTBD_SCD_ON_HND_LINE_SRC_NM */
    public static final String OTBD_SCD_ON_HND_LINE_SRC_NM = "OTBD_SCD_ON_HND_LINE_SRC_NM";

    /** Column Name : OTBD_DEF_WH_CD */
    public static final String OTBD_DEF_WH_CD = "OTBD_DEF_WH_CD";

    /** Column Name : OTBD_DEF_SWH_NM */
    public static final String OTBD_DEF_SWH_NM = "OTBD_DEF_SWH_NM";

    /** Column Name : OTBD_DEF_LINE_SRC_NM */
    public static final String OTBD_DEF_LINE_SRC_NM = "OTBD_DEF_LINE_SRC_NM";

    // 2017/09/12 QC#19805 Add Start
    /** Column Name : THIRD_PTY_DSP_TP_NM */
    public static final String THIRD_PTY_DSP_TP_NM = "THIRD_PTY_DSP_TP_NM";

    /** Column Name : DROP_RTRN_VND_NM */
    public static final String DROP_RTRN_VND_NM = "DROP_RTRN_VND_NM";
    // 2017/09/12 QC#19805 Add End

    /** Column Name : RMA_DEF_WH_CD */
    public static final String RMA_DEF_WH_CD = "RMA_DEF_WH_CD";

    // 2017/09/12 QC#19805 Del Start
//    /** Column Name : RMA_DEF_SWH_NM */
//    public static final String RMA_DEF_SWH_NM = "RMA_DEF_SWH_NM";
//
//    /** Column Name : RMA_DEF_LINE_SRC_NM */
//    public static final String RMA_DEF_LINE_SRC_NM = "RMA_DEF_LINE_SRC_NM";
    // 2017/09/12 QC#19805 Del End
    // END 2016/03/24 K.Kojima [UT#56,ADD]

}
