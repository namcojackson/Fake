/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2680;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2680.constant.NMAL2680Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 * 2016/06/01   Fujitsu         C.Yokoi         Update          CSA-QC#9925
 *</pre>
 */
public final class NMAL2680Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2680Query INSTANCE = new NMAL2680Query();

    /**
     * Constructor.
     */
    private NMAL2680Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2680Query
     */
    public static NMAL2680Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param cMsg NMAL2680CMsg
     * @param sMsg NMAL2680SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyRule(String glblCmpyCd, NMAL2680CMsg cMsg, NMAL2680SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // 2016/06/01 CSA-QC#9925 Mod Start
        if (ZYPCommonFunc.hasValue(cMsg.orgCd_P1)) {
            ssmParam.put("orgCd", cMsg.orgCd_P1.getValue());
            ssmParam.put("territory", NMAL2680Constant.TERRITORY);
        } else {
            ssmParam.put("trtyRulePk", cMsg.trtyRulePk_H1.getValue());
        }
        // 2016/06/01 CSA-QC#9925 Mod End
        return getSsmEZDClient().queryObjectList("getTrtyRule", ssmParam);
    }

    /**
     * @param cMsg NMAL2680CMsg
     * @param sMsg NMAL2680SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustRule(String glblCmpyCd, NMAL2680CMsg cMsg, NMAL2680SMsg sMsg) {
        Map<String, Object> ssmParam = getTrytyRuleParam(glblCmpyCd, cMsg, sMsg, true);
        return getSsmEZDClient().queryEZDMsgArray("getCustRule", ssmParam, sMsg.A);
    }

    /**
     * @param cMsg NMAL2680CMsg
     * @param sMsg NMAL2680SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProsRule(String glblCmpyCd, NMAL2680CMsg cMsg, NMAL2680SMsg sMsg) {
        Map<String, Object> ssmParam = getTrytyRuleParam(glblCmpyCd, cMsg, sMsg, true);
        return getSsmEZDClient().queryEZDMsgArray("getProsRule", ssmParam, sMsg.A);
    }

    public Map<String, Object> getTrytyRuleParam(String glblCmpyCd, NMAL2680CMsg cMsg, NMAL2680SMsg sMsg, boolean setRowNumFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if (setRowNumFlg) {
            ssmParam.put("rowNum", sMsg.A.length());
        }
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // 2016/06/01 CSA-QC#9925 Mod Start
        if (ZYPCommonFunc.hasValue(cMsg.orgCd_P1)) {
            ssmParam.put("orgCd", cMsg.orgCd_P1.getValue());
        } else {
            ssmParam.put("trtyRulePk", cMsg.trtyRulePk_H1.getValue());
        }
        // 2016/06/01 CSA-QC#9925 Mod End
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H1)) {
            String value = cMsg.xxQueryFltrTxt_H1.getValue().toUpperCase();
            ssmParam.put("filter", "%" + value + "%");
        }
        return ssmParam;
    }

}
