/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1620;

import static business.blap.NWAL1620.constant.NWAL1620Constant.LINE_MODE;
import static business.blap.NWAL1620.constant.NWAL1620Constant.ROW_NUM_ONE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1620Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 *</pre>
 */
public final class NWAL1620Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1620Query MY_INSTANCE = new NWAL1620Query();

    /**
     * Private constructor
     */
    private NWAL1620Query() {
        super();
    }

    /**
     * Get the NWAL1620Query instance.
     * @return NWAL1620Query instance
     */
    public static NWAL1620Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getCntDsCpoDtl
     * @param bizMsg NWAL1620CMsg
     * @return Integer
     */
    public Integer getCntDsCpoDtl(NWAL1620CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        ssmParam.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum.getValue());
        if (LINE_MODE.equals(bizMsg.xxModeCd.getValue())) {
            ssmParam.put("lineNum", bizMsg.dsCpoLineNum.getValue());
        }
        ssmParam.put("rowNum", ROW_NUM_ONE);

        return (Integer) getSsmEZDClient().queryObject("getCntDsCpoDtl", ssmParam).getResultObject();
    }

    /**
     * getCntDsCpoRtrnDtl
     * @param bizMsg NWAL1620CMsg
     * @return Integer
     */
    public Integer getCntDsCpoRtrnDtl(NWAL1620CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        ssmParam.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum.getValue());
        if (LINE_MODE.equals(bizMsg.xxModeCd.getValue())) {
            ssmParam.put("lineNum", bizMsg.dsCpoLineNum.getValue());
        }
        ssmParam.put("rowNum", ROW_NUM_ONE);

        return (Integer) getSsmEZDClient().queryObject("getCntDsCpoRtrnDtl", ssmParam).getResultObject();
    }

    /**
     * getConfigCatg
     * @param bizMsg NWAL1620CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigCatg(NWAL1620CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getConfigCatg", ssmParam);
    }
    // mod start 2016/07/29 CSA S21_NA#12607
    /**
     * Check Credit Rebill Order
     * @param bizMsg NWAL1620CMsg
     * @return result S21SsmEZDResult
     */
    public S21SsmEZDResult cntCreditRebillOrder(NWAL1620CMsg bizMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.dsCpoLineNum.getValue())) {
            params.put("dsCpoLineNum", String.valueOf(bizMsg.dsCpoLineNum.getValue()));
        }
        params.put("crRebilCdCredit", CR_REBIL.CREDIT);
        params.put("crRebilCdRebill", CR_REBIL.REBILL);
        if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd.getValue())) {
            return getSsmEZDClient().queryObject("cntCreditRebillOrder", params);
        } else {
            return getSsmEZDClient().queryObject("cntCreditRebillOrderRMA", params);
        }
        
    }
    // mod end 2016/07/29 CSA S21_NA#12607
}
