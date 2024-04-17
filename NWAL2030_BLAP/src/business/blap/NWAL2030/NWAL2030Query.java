/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2030;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Hold Set Up Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         K.Kojima        Create          N/A
 * 2016/03/28   Hitachi         K.Kojima        Update          CSA QC#5801
 *</pre>
 */
public final class NWAL2030Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2030Query INSTANCE = new NWAL2030Query();

    /**
     * Constructor.
     */
    private NWAL2030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2030Query
     */
    public static NWAL2030Query getInstance() {
        return INSTANCE;
    }

    /**
     * getPulldownListLevel
     * @param cMsg NWAL2030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownListLevel(NWAL2030CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("hldLvlCdArray", new String[] {HLD_LVL.CPO_LEVEL, HLD_LVL.CPO_DETAIL_LEVEL });
        params.put("rowNum", cMsg.L.length());
        return getSsmEZDClient().queryEZDMsgArray("getPulldownListLevel", params, cMsg.L);
    }

    /**
     * getPulldownListWorkflow
     * @param cMsg NWAL2030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownListWorkflow(NWAL2030CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rowNum", cMsg.W.length());
        return getSsmEZDClient().queryEZDMsgArray("getPulldownListWorkflow", params, cMsg.W);
    }

    /**
     * getSearchData
     * @param cMsg NWAL2030CMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NWAL2030CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.hldRsnCd.getValue())) {
            params.put("hldRsnCd", cMsg.hldRsnCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.hldRsnNm.getValue())) {
            params.put("hldRsnNm", cMsg.hldRsnNm.getValue());
        }
        params.put("rowNum", cnt);
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, cMsg.A);
    }

    /**
     * getMaxSortNum
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    public BigDecimal getMaxSortNum(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getMaxSortNum", params);
        if (rs.isCodeNotFound()) {
            return BigDecimal.ONE;
        }
        return (BigDecimal) rs.getResultObject();
    }

}
