/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6890;

import static business.blap.NMAL6890.constant.NMAL6890Constant.BUSINESS_ID;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_SRCH_OPT_USR_ID;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL6890 Warehouse Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public final class NMAL6890Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6890Query MY_INSTANCE = new NMAL6890Query();

    /**
     * Constructor.
     */
    private NMAL6890Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL6890Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSrchOptnPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BUSINESS_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, getContextUserInfo().getUserId());

        return getSsmEZDClient().queryObjectList("getSrchOptnPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhTypePulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWhTypePulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyAcctPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getInvtyAcctPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSrcTypePulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getSrcTypePulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPlnItmInsrcPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getPlnItmInsrcPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyOwnrPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getInvtyOwnrPulldownList", ssmParam);
    }

    /**
     * <p>
     * Searches Suppliers.
     * </p>
     * @param ssmParam Map<String, Object>
     * @param sMsg glblMsg
     * @return result
     */
    public S21SsmEZDResult searchWarehouses(Map<String, Object> ssmParam, NMAL6890SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("searchWarehouses", ssmParam, sMsg.A);
    }
}
