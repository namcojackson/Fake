/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1290;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public final class NSAL1290Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1290Query INSTANCE = new NSAL1290Query();

    /**
     * Constructor.
     */
    private NSAL1290Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1290Query
     */
    public static NSAL1290Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1290CMsg
     * @param sMsg NSAL1290SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NSAL1290CMsg
     * @param sMsg NSAL1290SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("mtrLbNm", cMsg.mtrLbNm_H);
        params.put("mtrLbTpCd", MTR_LB_TP.REGULAR_METER);
        params.put("limitNum", cnt);

        return params;
    }

    /**
     * getBllgMtrMap
     * @param glblCmpyCd String
     * @param mtrLbCd String
     * @return Map<String, Object>
     */
    public S21SsmEZDResult getBllgMtrMap(String glblCmpyCd, String mtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mtrLbCd", mtrLbCd);
        return getSsmEZDClient().queryObjectList("getBllgMtrMap", ssmPrm);
    }

}
