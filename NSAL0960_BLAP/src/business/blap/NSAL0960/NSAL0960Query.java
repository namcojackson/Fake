/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0960;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0960.common.NSAL0960CommonLogic;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Validation List Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/06/16   Hitachi         M.Gotou         Update          QC#9698
 *</pre>
 */
public final class NSAL0960Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0960Query INSTANCE = new NSAL0960Query();

    /**
     * Private constructor
     */
    private NSAL0960Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0960Query singleton instance
     */
    public static NSAL0960Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsContrVldList
     * @param cMsg NSAL0960CMsg
     * @param dsContrVldListPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrVldList(NSAL0960CMsg cMsg, BigDecimal dsContrVldListPk) {
        setValue(cMsg.glblCmpyCd_H, getGlobalCompanyCode());
        Map<String, Object> params = NSAL0960CommonLogic.getSearchCriteriaMap(cMsg, dsContrVldListPk, cMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getDsContrVldList", params);
    }

    /**
     * getVldActList
     * @param cMsg NSAL0960CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVldActList(NSAL0960CMsg cMsg) {
        setValue(cMsg.glblCmpyCd_H, getGlobalCompanyCode());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_H.getValue());

        return getSsmEZDClient().queryObjectList("getVldActList", params);
    }

    // add start 2016/06/16 CSA Defect#9698
    /**
     * getDsContrVldListPeriod
     * @param cMsg NSAL0960CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrVldListPeriod(NSAL0960CMsg cMsg) {
        setValue(cMsg.glblCmpyCd_H, getGlobalCompanyCode());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_H.getValue());

        return getSsmEZDClient().queryObjectList("getDsContrVldListPeriod", params);
    }
    // add end 2016/06/16 CSA Defect#9698
}
