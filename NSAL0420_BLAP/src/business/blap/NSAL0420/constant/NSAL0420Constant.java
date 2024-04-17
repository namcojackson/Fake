/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0420.constant;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 * 2019/02/12   Hitachi         S.Kitamura      Update          QC#30264
 *</pre>
 */
public class NSAL0420Constant {

    /** TBL_NM */
    public static enum TBL_NM {
        /** DS_CONTR_STS */
        DS_CONTR_STS;
    }

    /** QUERY_PRM_ID */
    public static enum QUERY_PRM_ID {
        /** glblCmpyCd */
        GLBL_CMPY_CD("glblCmpyCd")
        /** slsDt */
        , SLS_DT("slsDt")
        /** svcRgNm */
        , SVC_RG_NM("svcRgNm")
        /** svcLineBizCd */
        , LINE_BIZ_TP_DESC_TXT("lineBizTpDescTxt")
        /** svcContrBrCd */
        , SVC_CONTR_BR_CD("svcContrBrCd")
        /** svcContrBrDescTxt */
        , SVC_CONTR_BR_DESC_TXT("svcContrBrDescTxt")
        /** xxGenlFldAreaTxt */
        , XX_GENL_FLD_AREA_TXT("xxGenlFldAreaTxt")
        /** searchLimitCnt */
        , SEARCH_LIMIT_CNT("searchLimitCnt")
        /** thruDtLimit */
        , THRU_DT_LIMIT("thruDtLimit")
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // START 2017/01/24 N.Arai [QC#17228, MOD]
        // /** psnTpCd */
        //, PSN_TP_CD("psnTpCd")
        // END 2017/01/24 N.Arai [QC#17228, MOD]
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        /** firstOrgCd */
        , FIRST_ORG_CD("firstOrgCd")
        /** sysSrcCd */
        , SYS_SRC_CD("sysSrcCd");

        /** queryPrm */
        private String queryPrm;

        /**
         * Get Query Parameter
         * @return getQueryPrm
         */
        QUERY_PRM_ID(String queryPrm) {
            this.queryPrm = queryPrm;
        }

        /**
         * Set Query Parameter
         * @return getQueryPrm
         */
        public String getQueryPrm() {
            return queryPrm;
        }

    }

    /** MSG_ID */
    public enum MSG_ID {

        /** No search results found. */
        NZZM0000E
        /** There are too many search results, there is data that cannot be displayed. */
        , NZZM0001W;
    }

    /** SEARCH_LIMIT_CNT */
    public static final int SEARCH_LIMIT_CNT = 201;

    /** THRU_DT_LIMIT */
    public static final String THRU_DT_LIMIT = "29991231";

    /** CONTR_BR_FIRST_ORG_CD */
    public static final String CONTR_BR_FIRST_ORG_CD = "CONTR_BR_FIRST_ORG_CD";
}
