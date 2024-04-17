/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1200.constant;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 08/30/2016   CITS       S.Endo                Update          QC#13726
 * 03/01/2023   CITS       R.Kurahashi           Update          QC#61128
 *</pre>
 */
public class NPAL1200Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1200";

    /** . */
    public static final String INSRC_ZN_PLN_SQ = "INSRC_ZN_PLN_SQ";

    /** . */
    public static final int MODE_ERROR = -1;

    /** . */
    public static final int MODE_NORMAL = 1;

    /** . */
    public static final int MODE_EDIT = 2;

    /** . */
    public static final int MODE_DELETE = 3;

    /** . */
    public static final int MODE_ADD = 4;

    /** . */
    public static final int MODE_ADD_CANCEL = 5;

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name */
    public static final String NPAL1200_WH_CATG_CD_LIST = "NPAL1200_WH_CATG_CD_LIST";

    // =================================================
    // Message
    // =================================================
    /** . */
    public static final String NPAM0001W = "NPAM0001W";

    /** . */
    public static final String NPAM0089E = "NPAM0089E";

    /** . */
    public static final String ZZM9000E = "ZZM9000E";

    /** . */
    public static final String NPAM0224E = "NPAM0224E";

    /** . */
    public static final String NPAM1199E = "NPAM1199E";

    /** . */
    public static final String NPAM1349E = "NPAM1349E";

    /** . */
    public static final String NPAM1350E = "NPAM1350E";

    /** . */
    public static final String NPAM1237W = "NPAM1237W";

    /** . */
    public static final String NPAM1234E = "NPAM1234E";

    /** . */
    public static final String ZZM9015E = "ZZM9015E";

    /** . */
    public static final String NPAM0006E = "NPAM0006E";

    /** . */
    public static final String NPZM0216E = "NPZM0216E";

    /** . */
    public static final String NPZM0217E = "NPZM0217E";

    /** . */
    public static final String NPZM0218E = "NPZM0218E";

    /** . */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** .*/
    public static final String ZZPM0037W = "ZZPM0037W";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_FROM_SRC_ZN_CD = "srcZnCd_SF";

    /** . */
    public static final String DB_PARAM_TO_SRC_ZN_CD = "srcZnCd_ST";

    /** . */
    public static final String DB_PARAM_FROM_RTL_WH_CD = "rtlWhCd_HF";

    /** . */
    public static final String DB_PARAM_TO_RTL_WH_CD = "rtlWhCd_HT";

    /** . */
    public static final String DB_PARAM_MDSE_ITEM_CLS_TP_CD = "mdseItemClsTpCd_SH";

    /** . */
    public static final String DB_PARAM_INSRC_ENBL_FLG = "xxChkBox_H1";

    /** . */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /** . */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /** . */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";
    
    // QC#61128 Add Start
    /** . */
    public static final String DB_PARAM_PRCH_REQ_TP_CD = "prchReqTpCd_SF";
    
    /** . */
    public static final String DB_PARAM_S21_WH_FLG = "planTypeS21WhFlg";
    
    /** . */
    public static final String DB_PARAM_MNX_WH_FLG = "planTypeMnxWhFlg";
    
    /** . */
    public static final String DB_PARAM_WH_MNX_CD = "whMnxCd";
    
    /** . */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";
    // QC#61128 Add End

    // =================================================
    // DB Columns
    // =================================================
    /** . */
    public static final String SRC_ZN_CD = "SRC_ZN_CD";

    /** . */
    public static final String MDSE_ITEM_CLS_TP_CD = "MDSE_ITEM_CLS_TP_CD";

    /** . */
    public static final String MDSE_ITEM_CLS_TP_NM = "MDSE_ITEM_CLS_TP_NM";

    /** . */
    public static final String FROM_SRC_ZN_CD = "FROM_SRC_ZN_CD";

    /** . */
    public static final String FROM_RTL_WH_CD = "FROM_RTL_WH_CD";

    /** . */
    public static final String RTL_WH_NM_FROM = "RTL_WH_NM_FROM";

    /** . */
    public static final String TO_SRC_ZN_CD = "TO_SRC_ZN_CD";

    /** . */
    public static final String TO_RTL_WH_CD = "TO_RTL_WH_CD";

    /** . */
    public static final String RTL_WH_NM_TO = "RTL_WH_NM_TO";

    /** . */
    public static final String INSRC_ITEM_PRC_THRHD_AMT = "INSRC_ITEM_PRC_THRHD_AMT";

    /** . */
    public static final String INSRC_RNK_SORT_NUM = "INSRC_RNK_SORT_NUM";

    /** . */
    public static final String INSRC_ENBL_FLG = "INSRC_ENBL_FLG";

    /** . */
    public static final String RTL_WH_NM = "RTL_WH_NM";

    /** . */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";

    /** . */
    public static final String EZUPTIME = "EZUPTIME";

    /** . */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    /** . */
    public static final String INSRC_ZN_PLN_PK = "INSRC_ZN_PLN_PK";
    /** . */
    public static final String NMAM0182E = "NMAM0182E";
    
    // QC#61128 Add Start
    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";
    
    /** . */
    public static final String PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";
    
    /** . */
    public static final String WH_OWNR_CD = "WH_OWNR_CD";
    
    /** . */
    public static final String WH_OWNR_CD_FROM = "WH_OWNR_CD_FROM";
    
    /** . */
    public static final String WH_OWNR_CD_TO = "WH_OWNR_CD_TO";
    
    // =================================================
    // Plan Type Value
    // =================================================
    /** . */
    public static final String PLAN_TYPE_CD_S21_WH = "0";
    
    /** . */
    public static final String PLAN_TYPE_CD_MNX_WH = "1";
    
    /** . */
    public static final String PLAN_TYPE_NM_S21_WH = "S21 WH";
    
    /** . */
    public static final String PLAN_TYPE_NM_MNX_WH = "MNX WH";
    // QC#61128 Add End
}
