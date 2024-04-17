/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0510.constant;

/**
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2017/11/16   Hitachi         K.Kojima        Update          QC#21886
 *</pre>
 */
public class NSAL0510Constant {

    /** APL_ID : NSAL0510 */
    public static final String APL_ID = "NSAL0510";

    /** TBL_NM */
    public static enum TBL_NM {
        /** DS_CONTR_STS */
        DS_CONTR_STS;
    }

    /** COL_NM */
    public static enum COL_NM {

        /** DS_CONTR_DTL_PK */
        DS_CONTR_DTL_PK
        /** DS_SUB_CONTR_PK */
        , DS_SUB_CONTR_PK
        /** VND_CD */
        , VND_CD
        /** LOC_NM */
        , LOC_NM
        /** DS_ACCT_NUM */
        , DS_ACCT_NUM
        /** DS_ACCT_NM */
        , DS_ACCT_NM
        /** DS_CONTR_NUM */
        , DS_CONTR_NUM
        /** DS_CONTR_STS_NM */
        , DS_CONTR_STS_NM
        /** DLR_FLEET_NUM */
        , DLR_FLEET_NUM
        /** SER_NUM */
        , SER_NUM
        /** T_MDL_NM */
        , T_MDL_NM
        /** EFF_FROM_DT */
        , EFF_FROM_DT
        /** EFF_THRU_DT */
        , EFF_THRU_DT;
    }

    /** QUERY_PRM_ID */
    public static enum QUERY_PRM_ID {
        /** glblCmpyCd */
        GLBL_CMPY_CD("glblCmpyCd")
        /** dlrFleetNum */
        , DLR_FLEET_NUM("dlrFleetNum")
        /** mdlActvFlgActive */
        , MDL_ACTV_FLG_ACTIVE("mdlActvFlgActive")
        /** mdlActvFlgInActive */
        , MDL_ACTV_FLG_IN_ACTIVE("mdlActvFlgInActive")
        /** mdlActvFlg */
        , MDL_ACTV_FLG("mdlActvFlg")
        /** slsDt */
        , SLS_DT("slsDt")
        /** vndCd */
        , VND_CD("vndCd")
        /** dsAcctNum */
        , DS_ACCT_NUM("dsAcctNum")
        /** dsContrNum */
        , DS_CONTR_NUM("dsContrNum")
        /** dsContrSts */
        , DS_CONTR_CTRL_STS("dsContrCtrlSts")
        // START 2017/11/16 K.Kojima [QC#21886,ADD]
        /** dsContrCtrlStsCdList */
        , DS_CONTR_CTRL_STS_LIST("dsContrCtrlStsList")
        // END 2017/11/16 K.Kojima [QC#21886,ADD]
        /** serNum */
        , SER_NUM("serNum")
        /** t_mdlNm */
        , T_MDL_NM("t_MdlNm")
        /** mdlActvFlg */
        , CONTR_TRMN_FLG("contrTrmnFlgIsY")
        /** limitRowNum */
        , LIMIT_ROWNUM("limitRownum")
        /** vndTpCdIsSvcDealer */
        , VND_TP_CD_IS_SVC_DEALER("vndTpCdIsSvcDealer");

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

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** MDL_ACTV_FLG */
    public enum MDL_ACTV_FLG {
        /** Y:Active */
        Y("Active")
        /** N:In-Active */
        , N("In-Active");

        /** mdlActvFlg */
        private String mdlActvFlg;

        /**
         * Set Model Active Flag
         * @param mdlActvFlg Model Active Flag
         */
        MDL_ACTV_FLG(String mdlActvFlg) {
            this.mdlActvFlg = mdlActvFlg;
        }

        /**
         * Get Model Active Flag
         * @return getmdlActvFlg
         */
        public String getmdlActvFlgNm() {
            return mdlActvFlg;
        }

    }

    /** MSG_ID */
    public enum MSG_ID {

        /** No search results found. */
        NZZM0000E
        /** There are too many search results, there is data that cannot be displayed. */
        , NZZM0001W;
    }
}
