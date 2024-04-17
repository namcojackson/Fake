/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0480.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/05/19   Hitachi         M.Gotou         Update          QC#8535
 *</pre>
 */
public class NSAL0480Constant {

    /** SCRN_ID : NSAL0480Scrn00 */
    public static final String SCRN_ID = "NSAL0480Scrn00";

    /** APL_ID : NSAL0480 */
    public static final String APL_ID = "NSAL0480";

    /** TBL_NM */
    public static enum TBL_NM {
        /** MDSE_ITEM_CLS_TP */
        MDSE_ITEM_CLS_TP
        /** SVC_SEG */
        , SVC_SEG
        //
        , /* */;
    }

    /** COL_NM */
    public static enum COL_NM {
        /** SVC_ISTL_RULE_NUM */
        SVC_ISTL_RULE_NUM
        /** SVC_ISTL_RULE_NM */
        , SVC_ISTL_RULE_NM
        /** SVC_SKILL_NUM */
        , SVC_SKILL_NUM
        /** SVC_SKILL_NM */
        , SVC_SKILL_NM
        /** MTR_GRP_PK */
        , MTR_GRP_PK
        // START 2016/05/19 M.Gotou [QC#8535, MOD]
        /** MTR_GRP_NM */
        , MTR_GRP_NM
        // END 2016/05/19 M.Gotou [QC#8535, MOD]

        /** T_MDL_NM */
        , T_MDL_NM
        /** MDL_DESC_TXT */
        , MDL_DESC_TXT
        /** MDL_GRP_NM */
        , MDL_GRP_NM
        /** SVC_SEG_CD */
        , SVC_SEG_CD
        /** SVC_SEG_DESC_TXT */
        , SVC_SEG_DESC_TXT
        /** XX_SCR_ITEM_10_TXT */
        , XX_SCR_ITEM_10_TXT
        /** XX_CRAT_DT */
        , XX_CRAT_DT

        /** RCLL_INTVL_DAYS_AOT */
        , RCLL_INTVL_DAYS_AOT
        /** RCLL_COPY_VOL_CNT */
        , RCLL_COPY_VOL_CNT
        /** RCLL_GLBL_INTVL_DAYS_AOT */
        , RCLL_GLBL_INTVL_DAYS_AOT
        /** RCLL_GLBL_COPY_VOL_CNT */
        , RCLL_GLBL_COPY_VOL_CNT
        /** RSP_TM_UP_MN_AOT */
        , RSP_TM_UP_MN_AOT
        /** XS_VISIT_CNT */
        , XS_VISIT_CNT
        /** PHONE_FIX_FLG */
        , PHONE_FIX_FLG
        /** PHONE_FIX_INTVL_DAYS_AOT */
        , PHONE_FIX_INTVL_DAYS_AOT
        /** AFTER_HOUR_ALLW_FLG */
        , AFTER_HOUR_ALLW_FLG
        /** COPY_VOL_DAYS_AOT */
        , COPY_VOL_DAYS_AOT
        /** MAX_COPY_PER_DAY_TOT_CNT */
        , MAX_COPY_PER_DAY_TOT_CNT
        /** MAX_COPY_PER_DAY_BLACK_CNT */
        , MAX_COPY_PER_DAY_BLACK_CNT
        /** MAX_COPY_TEST_CNT */
        , MAX_COPY_TEST_CNT
        /** MDL_SPEED_BLACK_RATE */
        , MDL_SPEED_BLACK_RATE
        /** MDL_SPEED_COLOR_RATE */
        , MDL_SPEED_COLOR_RATE
        /** DS_MDL_PAPER_SIZE_NM */
        , DS_MDL_PAPER_SIZE_NM

        /** SRCH_OPT_PK */
        , SRCH_OPT_PK
        /** SRCH_OPT_NM */
        , SRCH_OPT_NM
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        /** SVC_DEINS_RULE_NUM */
        , SVC_DEINS_RULE_NUM
        /** CUST_ISTL_FLG */
        , CUST_ISTL_FLG
        /** SVC_ISTL_REQ_FLG */
        , SVC_ISTL_REQ_FLG
        /** SITE_SRVY_REQ_FLG */
        , SITE_SRVY_REQ_FLG
        /** SVC_ISTL_RULE_NM Installation */
        , SVC_ISTL_RULE_NM_IN
        /** SVC_ISTL_RULE_NM De-Installation */
        , SVC_ISTL_RULE_NM_DE
        /** MACH_IN_FLD_INAC_MTH_AOT */
        , MACH_IN_FLD_INAC_MTH_AOT
        // 2015/10/05 CSA Y.Tsuchimoto Add End
        //
        , /* */;
    }

    /** QUERY_PRM_ID */
    public static enum QUERY_PRM_ID {
        /** glblCmpyCd */
        GLBL_CMPY_CD("glblCmpyCd")
        /** svcIstlRuleFlg */
        , SVC_ISTL_RULE_FLG("svcIstlRuleFlg")
        /** mdlActvFlgActive */
        , MDL_ACTV_FLG_ACTIVE("mdlActvFlgActive")
        /** mdlActvFlgInActive */
        , MDL_ACTV_FLG_IN_ACTIVE("mdlActvFlgInActive")
        /** mdlDescTxt */
        , MDL_DESC_TXT("mdlDescTxt")
        /** mdlActvFlg */
        , MDL_ACTV_FLG("mdlActvFlg")
        /** xxCratDt */
        , XX_CRAT_DT("xxCratDt")
        /** svcSegCd */
        , SVC_SEG_CD("svcSegCd")
        /** */
        , SVC_SKILL_NUM("svcSkillNum")
        /** svcIstlRuleNum */
        , SVC_ISTL_RULE_NUM("svcIstlRuleNum")
        /** svcDeistlRuleNum */
        , SVC_DEISTL_RULE_NUM("svcDeistlRuleNum")
        /** svcIstlReqFlgOn */
        , SVC_ISTL_REQ_FLG_ON("svcIstlReqFlgOn")
        /** svcIstlReqFlgOff */
        , SVC_ISTL_REQ_FLG_OFF("svcIstlReqFlgOff")
        /** t_mdlNm */
        , T_MDL_NM("t_mdlNm")
        /** mdlGrpNm */
        , MDL_GRP_NM("mdlGrpNm")
        /** mtrGrpPk */
        , MTR_GRP_PK("mtrGrpPk")
        /** t_ItemCd */
        , MDSE_CD("t_ItemCd")
        /** mdseCd */
        , SPLY_MDSE_CD("mdseCd")
        /** mdseItemClsTpCd */
        , MSE_ITEM_CLS_TP_CD("mdseItemClsTpCd")
        /** imgSplyOemCd */
        , IMG_SPLY_OEM_CD("imgSplyOemCd")
        /** userId */
        , SRCH_OPT_USR_ID("srchOptUsrId")
        /** srchOptAplId */
        , SRCH_OPT_APL_ID("srchOptAplId")
        /** len */
        , LEN("len")
        /** one */
        , ONE("one")

        /** rownum1 */
        , ROWNUM_1("rownum1")
        /** limitRowNum */
        , LIMIT_ROWNUM("limitRownum")
        //
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        /** custIstlFlgOn */
        , CUST_ISTL_FLG_ON("custIstlFlgOn")
        /** custIstlFlgOff */
        , CUST_ISTL_FLG_OFF("custIstlFlgOff")
        /** siteSrvyReqFlgOn */
        , SITE_SRVY_REQ_FLG_ON("siteSrvyReqFlgOn")
        /** siteSrvyReqFlgOff */
        , SITE_SRVY_REQ_FLG_OFF("siteSrvyReqFlgOff")
        // 2015/10/05 CSA Y.Tsuchimoto Add End
        , /* */;

        /** */
        private String queryPrm;

        QUERY_PRM_ID(String queryPrm) {
            this.queryPrm = queryPrm;
        }

        /**
         * @return getQueryPrm
         */
        public String getQueryPrm() {
            return queryPrm;
        }

    }

    /** Limit RowNumber */
    public static final int LIMIT_ROWNUM = 201;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "ModelSearch";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** MDL_ACTV_FLG */
    public enum MDL_ACTV_FLG {
        /** Y:Active */
        Y("Active")
        /** N:In-Active */
        , N("In-Active")
        //
        , /* */;

        /** */
        private String mdlActvFlg;

        MDL_ACTV_FLG(String mdlActvFlg) {
            this.mdlActvFlg = mdlActvFlg;
        }

        /**
         * @return getmdlActvFlg
         */
        public String getmdlActvFlgNm() {
            return mdlActvFlg;
        }

    }

    /** MSG_PRM */
    public enum MSG_PRM {
        /** NO_SUPPLY_CLASS */
        NO_SUPPLY_CLASS("MDSE_ITEM_CLS_TP is not set up")
        /** NO_MTR_GRP */
        , NO_MTR_GRP("MTR_GRP is not set up")
        //
        , /* */;

        /** */
        private String msgPrm;

        MSG_PRM(String msgPrm) {
            this.msgPrm = msgPrm;
        }

        /**
         * @return getMsgPrm
         */
        public String getMsgPrm() {
            return msgPrm;
        }

    }

    /** MSG_ID */
    public enum MSG_ID {

        /** [@] field is mandatory. */
        ZZM9000E
        /** The process [@] has been successfully completed. */
        , ZZZM9003I
        /** Specified "@" already exists. */
        , NSAM0059E
        /** No search results found. */
        , NSAM0194I
        /** [@] is not selected. */
        , NSAM0199E
        /** System Error : @ */
        , NSAM0219E
        /** No search results found. */
        , NZZM0000E
        /**
         * There are too many search results, there is data that
         * cannot be displayed.
         */
        , NZZM0001W
        //
        , /* */;

    }

    /** MSG_KIND */
    public enum MSG_KIND {
        /** error */
        ERROR("E")
        /** warning */
        , WARNING("W")
        /** information */
        , INFO("I")
        //
        , /* */;

        /** */
        private String msgKind;

        MSG_KIND(String msgKind) {
            this.msgKind = msgKind;
        }

        /**
         * @return getMsgKind
         */
        public String getMsgKind() {
            return msgKind;
        }
    }

}
