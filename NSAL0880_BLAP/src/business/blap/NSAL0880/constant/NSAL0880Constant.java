/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0880.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0880Constant {

    /** QUERY_PRM_ID */
    public static enum QUERY_PRM_ID {
        /** glblCmpyCd */
        GLBL_CMPY_CD("glblCmpyCd")
        /** svcMachMstrPk */
        , SVC_MACH_MSTR_PK("svcMachMstrPk")
        /** svcPhysMtrReadGrpSq */
        , SVC_PHYS_MTR_READ_GRP_SQ("svcPhysMtrReadGrpSq")
        /** dsMsgTrxNm */
        , DS_MSG_TRX_NM("dsMsgTrxNm")
        /** rowNum */
        , ROW_NUM("rowNum");

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
        , NZZM0001W
        /** Input parameter "@" is a mandatory field. */
        , NSAM0362E;
    }

    /** SEARCH_LIMIT_CNT */
    public static final int SEARCH_LIMIT_CNT = 41;

    /** DS_MTR_INTFC_PK */
    public static final String DS_MTR_INTFC_PK = "DS_MTR_INTFC_PK";
}
