/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2260;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Import Attribute Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public final class NWAL2260Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2260Query INSTANCE = new NWAL2260Query();

    /**
     * Constructor.
     */
    private NWAL2260Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2260Query
     */
    public static NWAL2260Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchDataForHeader
     * @param cMsg NWAL2260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchDataForHeader(NWAL2260CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getSearchDataForHeader", getSsmParamForHeader(cMsg), cMsg);
    }

    /**
     * getSearchDataForDetail
     * @param cMsg NWAL2260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchDataForDetail(NWAL2260CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getSearchDataForDetail", getSsmParamForDetail(cMsg), cMsg);
    }

    /**
     * getSsmParamForHeader
     * @param cMsg NWAL2260CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamForHeader(NWAL2260CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        if (hasValue(cMsg.dsImptOrdPk)) {
            params.put("dsImptOrdPk", cMsg.dsImptOrdPk.getValue());
        } else {
            params.put("ordSrcRefNum", cMsg.ordSrcRefNum.getValue());
            params.put("cpoSrcTpCd", cMsg.cpoSrcTpCd.getValue());
        }

        return params;
    }

    /**
     * getSsmParamForDetail
     * @param cMsg NWAL2260CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamForDetail(NWAL2260CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsImptordDtlPk", cMsg.dsImptOrdDtlPk.getValue());

        return params;
    }
}
