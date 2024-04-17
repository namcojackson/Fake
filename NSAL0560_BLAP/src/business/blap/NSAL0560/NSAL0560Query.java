/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0560;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

/**
 *<pre>
 * Base Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2016/02/01   Hitachi         T.Tomita        Update          CSA QC#2063
 * 2016/02/08   Hitachi         K.Kishimoto     Update          QC#3884, QC#3891, QC#3898
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 * 2018/05/11   Hitachi         K.Kim           Update          QC#25897
 * 2019/11/25   Hitachi         K.Kitachi       Update          QC#54703
 *</pre>
 */
public final class NSAL0560Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0560Query INSTANCE = new NSAL0560Query();

    /**
     * Constructor.
     */
    private NSAL0560Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0560Query
     */
    public static NSAL0560Query getInstance() {
        return INSTANCE;
    }

    /**
     * get getContrDtl
     * @param ssmParam Map<String, Object>
     * @param cMsg NSAL0560CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtl(Map<String, Object> ssmParam, NSAL0560CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getContrDtl", ssmParam, cMsg);
    }

    /**
     * get getContrPrcEff
     * @param ssmParam Map<String, Object>
     * @param aCMsgArray NSAL0560_ACMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrPrcEff(Map<String, Object> ssmParam, NSAL0560_ACMsgArray aCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrPrcEff", ssmParam, aCMsgArray);
    }

    public boolean existsDsContrBllgSchd(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk.toString());
        ssmParam.put("invFlg", ZYPConstant.FLG_ON_Y);
        return (Integer) getSsmEZDClient().queryObject("cntDsContrBllgSchd", ssmParam).getResultObject() > 0;
    }

    public boolean existsDsContrDtlAggMach(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return (Integer) getSsmEZDClient().queryObject("cntDsContrDtlAggMach", ssmParam).getResultObject() > 0;
    }

    public boolean existsDsContrDtlAggLine(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return (Integer) getSsmEZDClient().queryObject("cntDsContrDtlAggLine", ssmParam).getResultObject() > 0;
    }

    public String getMinBllgSchdFromDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        if (!ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            return null;
        }
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk.toString());
        ssmParam.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getMinBllgSchdFromDt", ssmParam).getResultObject();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getDsContrCtrlSts(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsContrCtrlSts", ssmParam).getResultObject();
    }

    public DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    // START 2016/02/01 T.Tomita [QC#2063, ADD]
    public DS_CONTR_DTLTMsg getDsContrDtlForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tmsg);
    }
    // END 2016/02/01 T.Tomita [QC#2063, ADD]

    /**
     * get getInvoicedBllgSchd
     * @param cMsg NSAL0560CMsg
     * @param aCMsg NSAL0560_ACMsg
     * @return DS_CONTR_BLLG_SCHDTMsgArray
     */
    public static DS_CONTR_BLLG_SCHDTMsgArray getInvoicedBllgSchd(NSAL0560CMsg cMsg, NSAL0560_ACMsg aCMsg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPrcEffPk01", aCMsg.dsContrPrcEffPk_A1.getValue());
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        return (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // Mod Start   02/08/2016 <QC#3884, QC#3891, QC#3898>
    public String getMaxBllgSchdThruDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        if (!ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            return null;
        }
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getMaxBllgSchdThruDt", ssmParam).getResultObject();
    }
    // Mod End   02/08/2016 <QC#3884, QC#3891, QC#3898>

    public BigDecimal getDsContrPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        ssmParam.put("rowNum", "1");
        return (BigDecimal) getSsmEZDClient().queryObject("getDsContrPk", ssmParam).getResultObject();
    }

    public static DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEff(NSAL0560CMsg cMsg) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
	    //Mod Start 02/08/2016 <QC#3884, QC#3891, QC#3898>
        inMsg.setSQLID("006");
    	//Mod End   02/08/2016 <QC#3884, QC#3891, QC#3898>
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk_H1.getValue());
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    public S21SsmEZDResult getMachDsContrPrcEff(Map<String, Object> ssmParam, NSAL0560_CCMsgArray cCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getMachDsContrPrcEff", ssmParam, cCMsgArray);
    }

    public static boolean existInvoicedBllgSchdMach(NSAL0560CMsg cMsg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("010");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk_H1.getValue());
        inMsg.setConditionValue("baseChrgFlg01", FLG_ON_Y);
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray outMsg = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    // Add Start 2017/11/21 QC#21724
    public String getContrEffThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        return (String) getSsmEZDClient().queryObject("getContrEffThruDt", ssmParam).getResultObject();
    }
    // Add End 2017/11/21 QC#21724

    // START 2018/05/11 K.Kim [QC#25897, ADD]
    public BigDecimal getAggLinePk(String glblCmpyCd, BigDecimal dsContrPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) getSsmEZDClient().queryObject("getAggLinePk", ssmParam).getResultObject();
    }
    // END 2018/05/11 K.Kim [QC#25897, ADD]

    // START 2019/11/25 K.Kitachi [QC#54703, ADD]
    public boolean existUnbilledRebill(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("countUnbilledRebill", ssmParam).getResultObject();
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }
    // END 2019/11/25 K.Kitachi [QC#54703, ADD]
}
