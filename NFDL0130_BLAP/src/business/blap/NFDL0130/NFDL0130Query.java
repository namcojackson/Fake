/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0130;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public final class NFDL0130Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFDL0130Query INSTANCE = new NFDL0130Query();

    /**
     * Constructor.
     */
    private NFDL0130Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0130Query
     */
    public static NFDL0130Query getInstance() {
        return INSTANCE;
    }

    /**
     * getPulldownList
     * @param cMsg NFDL0130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownList(NFDL0130CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rowNum", cMsg.N.length());
        return getSsmEZDClient().queryEZDMsgArray("getPulldownList", params, cMsg.N);
    }

    /**
     * getCountCltStrgyRelnCustTp
     * @param glblCmpyCd String
     * @param cltWrkItemCd String
     * @return String
     */
    public String getCltStrgyRelnWrkItem(String glblCmpyCd, String cltWrkItemCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltWrkItemCd", cltWrkItemCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCltStrgyRelnWrkItem", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

}
