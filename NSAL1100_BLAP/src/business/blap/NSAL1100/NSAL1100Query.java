/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1100;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public final class NSAL1100Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1100Query INSTANCE = new NSAL1100Query();

    /**
     * Constructor.
     */
    private NSAL1100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1100Query
     */
    public static NSAL1100Query getInstance() {
        return INSTANCE;
    }

    /**
     * get HeaderInfo
     * @param cMsg NSAL1090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(NSAL1100CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcCrRebilPk", cMsg.svcCrRebilPk_P.getValue());

        return getSsmEZDClient().queryEZDMsg("getHeaderInfo", params, cMsg);
    }

    /**
     * getFullPsnNm
     * @param assigneeCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFullPsnNm(String assigneeCd, String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("psnCd", assigneeCd);

        return getSsmEZDClient().queryObject("getFullPsnNm", params);
    }
}
