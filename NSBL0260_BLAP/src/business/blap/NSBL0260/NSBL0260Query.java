/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0260;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSBL0260.common.NSBL0260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * EOL Exception Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/07/03   Hitachi         K.Kim           Update          QC#18164
 *</pre>
 */
public final class NSBL0260Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSBL0260Query INSTANCE = new NSBL0260Query();

    /**
     * Private constructor
     */
    private NSBL0260Query() {
        super();
    }

    /**
     * Get instance
     * @return NSBL0260Query singleton instance
     */
    public static NSBL0260Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsMdlEol
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @param searchCriteriaCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg, String searchCriteriaCd) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        Map<String, Object> params = NSBL0260CommonLogic.getSearchCriteriaMap(cMsg, sMsg, searchCriteriaCd, sMsg.A.length() + 1);
        return getSsmEZDClient().queryObjectList("getDsMdlEol", params);
    }

    /**
     * getCustomerInfo
     * @param glblCmpyCd String
     * @param serNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomerInfo(String glblCmpyCd, String serNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("serNum", serNum);
        return getSsmEZDClient().queryObjectList("getCustomerInfo", params);
    }

    // START 2017/07/03 K.Kim [QC#18164, ADD]
    /**
     * existMdlNmSvcMachMstr
     * @param glblCmpyCd String
     * @param mdlNm String
     * @param serNum String
     * @return boolean
     */
    public boolean existMdlNmSvcMachMstr(String glblCmpyCd, String mdlNm, String serNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlNm", mdlNm);
        params.put("serNum", serNum);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("existMdlNmSvcMachMstr", params);
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) rslt.getResultObject();
            if (cnt == BigDecimal.ZERO) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    // END 2017/07/03 K.Kim [QC#18164, ADD]
}
