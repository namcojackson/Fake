/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2670;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public final class NMAL2670Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2670Query INSTANCE = new NMAL2670Query();

    /**
     * Constructor.
     */
    private NMAL2670Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2670Query
     */
    public static NMAL2670Query getInstance() {
        return INSTANCE;
    }

    /**
     * getTrtyRule
     * @param glblCmpyCd String
     * @param cMsg NMAL2670CMsg
     * @param sMsg NMAL2670SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyRule(String glblCmpyCd, NMAL2670CMsg cMsg, NMAL2670SMsg sMsg) {

        Map<String, Object> ssmParam = getTrytyRuleParam(glblCmpyCd, cMsg, sMsg, true);
        return getSsmEZDClient().queryEZDMsgArray("getTrtyRule", ssmParam, sMsg.A);
    }

    public Map<String, Object> getTrytyRuleParam(String glblCmpyCd, NMAL2670CMsg cMsg, NMAL2670SMsg sMsg, boolean setRowNumFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if (setRowNumFlg) {
            ssmParam.put("rowNum", sMsg.A.length());
        }
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        ssmParam.put("orgCd", cMsg.orgCd_H1);
        ssmParam.put("maxDt", "99991231");
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H1)) {
            String value = cMsg.xxQueryFltrTxt_H1.getValue().toUpperCase();
            ssmParam.put("filter", "%" + value + "%");
        }
        return ssmParam;
    }

}
