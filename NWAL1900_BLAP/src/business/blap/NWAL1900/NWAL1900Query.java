/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1900;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1900Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/18   Fujitsu         H.Kumagai       Create          N/A
 *</pre>
 */
public class NWAL1900Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1900Query MY_INSTANCE = new NWAL1900Query();

    /**
     * Private constructor
     */
    private NWAL1900Query() {
        super();
    }

    /**
     * Get the NWAL1900Query instance.
     * @return NWAL1900Query instance
     */
    public static NWAL1900Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1900CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getPrcRuleInfo(String glblCmpyCd, NWAL1900CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        if(!PRC_RULE_ADJ_LVL.LINE.equals(bizMsg.xxModeCd.getValue())){
            params.put("prcRuleAdjLvlCd", bizMsg.xxModeCd.getValue());
        }
        params.put("prcRuleModTpCd", bizMsg.prcRuleModTpCd.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
        params.put("dsTrxRuleTpCd", bizMsg.prcGrpTrxTpCd_H1.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getPrcRuleInfo", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param String        prcRuleHdrPk
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getOrdPrcCalcBase(String glblCmpyCd, BigDecimal specCondPrcPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("specCondPrcPk", specCondPrcPk);

        return getSsmEZDClient().queryObject("getOrdPrcCalcBase", params);
    }

    public String getPrcPrcdActNm(String glblCmpyCd, BigDecimal prcRulePrcdPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcRulePrcdPk", prcRulePrcdPk);

        return (String) getSsmEZDClient().queryObject("getPrcPrcdActNm", params).getResultObject();
    }
    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param String        specCondPrcPk
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getPrcAdjDtl(String glblCmpyCd, String specCondPrcPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("specCondPrcPk", specCondPrcPk);

        return getSsmEZDClient().queryObjectList("getPrcAdjDtl", params);
    }
}
