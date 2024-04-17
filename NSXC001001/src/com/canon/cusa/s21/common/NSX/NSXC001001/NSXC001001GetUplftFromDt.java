/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_CONTR_INTFC_DEF_RULETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_THRU_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Uplift From Date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/28/2018   Hitachi         T.Tomita        Create          QC#27102
 * </pre>
 */
public class NSXC001001GetUplftFromDt {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetUplftFromDt.class);
    
    /**
     * Get Uplift From Date
     * @param glblCmpyCd String
     * @param svcLineBizCd String
     * @param mdseCd String
     * @param rntlFlg String
     */
    public static String getUplftFromDt(String glblCmpyCd, String contrEffFromDt, String contrIntfcSrcTpCd, String dsContrClsCd, String svcLineBizCd) {
        // input check
        if (!hasValue(contrEffFromDt)) {
            return null;
        }

        if (!hasValue(glblCmpyCd) || !hasValue(contrIntfcSrcTpCd) || !hasValue(dsContrClsCd) || !hasValue(svcLineBizCd)) {
            return contrEffFromDt;
        }

        // get DS_CONTR_INTFC_DEF_RULE
        DS_CONTR_INTFC_DEF_RULETMsg defRule = getDefRule(glblCmpyCd, contrIntfcSrcTpCd, dsContrClsCd, svcLineBizCd);
        if (defRule == null || !STUB_PREP_THRU_TP.EXTEND_END_MONTH.equals(defRule.stubPrepThruTpCd.getValue())) {
            return contrEffFromDt;
        }

        if ("01".equals(contrEffFromDt.substring(6))) {
            return contrEffFromDt;
        }

        String uplftFromDt = contrEffFromDt.substring(0, 6) + "01";
        return addMonths(uplftFromDt);
    }

    private static DS_CONTR_INTFC_DEF_RULETMsg getDefRule(String glblCmpyCd, String contrIntfcSrcTpCd, String dsContrClsCd, String svcLineBizCd) {
        DS_CONTR_INTFC_DEF_RULETMsg inMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, contrIntfcSrcTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrClsCd, dsContrClsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcLineBizCd, svcLineBizCd);

        return (DS_CONTR_INTFC_DEF_RULETMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private static String addMonths(String targetDate) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("targetDate", targetDate);
        ssmParam.put("dateFormat", "YYYYMMDD");
        ssmParam.put("addMth", BigDecimal.ONE);
        return (String) SSM_CLIENT.queryObject("addMonths", ssmParam);
    }
}
