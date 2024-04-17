/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1190;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public final class NSAL1190Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1190Query INSTANCE = new NSAL1190Query();

    /**
     * Constructor.
     */
    private NSAL1190Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1190Query
     */
    public static NSAL1190Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1190CMsg
     * @param sMsg NSAL1190SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getMaxMtrLbSortNum
     * @param cMsg NSAL1190CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxMtrLbSortNum(NSAL1190CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        return getSsmEZDClient().queryObjectList("getMaxMtrLbSortNum", params);
    }

    /**
     * getSsmParam
     * @param cMsg NSAL1190CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL1190CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mtrLbNm", cMsg.mtrLbNm_H.getValue());
        params.put("mtrLbDescTxt", cMsg.mtrLbDescTxt_H.getValue());
        params.put("limitCnt", cnt);

        return params;
    }

}
