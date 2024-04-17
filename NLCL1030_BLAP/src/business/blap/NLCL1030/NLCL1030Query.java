/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1030;

import static business.blap.NLCL1030.constant.NLCL1030Constant.BIZ_APP_ID;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_SRCH_OPT_USR_ID;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Query
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public final class NLCL1030Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL1030Query MY_INSTANCE = new NLCL1030Query();

    /**
     * Constructor.
     */
    private NLCL1030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NLCL1030Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSrchOptnPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, getContextUserInfo().getUserId());

        return getSsmEZDClient().queryObjectList("getSrchOptnPulldownList", ssmParam);
    }

    /**
     * <p>
     * Searches ABC Assignment.
     * </p>
     * @param ssmParam Map<String, Object>
     * @param sMsg glblMsg
     * @return result
     */
    public S21SsmEZDResult searchABCAssignment(Map<String, Object> ssmParam, NLCL1030SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("searchABCAssignment", ssmParam, sMsg.A);
    }

    /**
     * Search ABC Assignment SWH
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAbcAssignmentSwh(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("searchAbcAssignmentSwh", queryParams);

    }
}
