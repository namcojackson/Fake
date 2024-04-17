/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0570;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;

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
 * Overage Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/02/16   Hitachi         K.Kishimoto     Update          QC#2063
 * 2015/04/11   Hitachi         K.Kishimoto     Update          QC#6728
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 * 2019/11/25   Hitachi         K.Kitachi       Update          QC#54703
 * 2020/03/12   Hitachi         K.Kitachi       Update          QC#55662
 *</pre>
 */
public final class NSAL0570Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0570Query INSTANCE = new NSAL0570Query();

    /**
     * Constructor.
     */
    private NSAL0570Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0570Query
     */
    public static NSAL0570Query getInstance() {
        return INSTANCE;
    }

    /**
     * get getContrDtl
     * @param ssmParam Map<String, Object>
     * @param cMsg NSAL0570CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtl(Map<String, Object> ssmParam, NSAL0570CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getContrDtl", ssmParam, cMsg);
    }

    /**
     * get getContrPrcEff
     * @param ssmParam Map<String, Object>
     * @param aCMsgArray NSAL0570_ACMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrPrcEff(Map<String, Object> ssmParam, NSAL0570_ACMsgArray aCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrPrcEff", ssmParam, aCMsgArray);
    }

    public S21SsmEZDResult getPrntPrcEffMtr(Map<String, Object> ssmParam, NSAL0570_DCMsgArray dCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getPrntPrcEffMtr", ssmParam, dCMsgArray);
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

    public boolean existAggregateContr(NSAL0570CMsg cMsg) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk_H1);
        ssmParam.put("dsContrDtlTp", DS_CONTR_DTL_TP.AGGREGATE);
        S21SsmEZDResult out = getSsmEZDClient().queryObjectList("existAggrContr", ssmParam);
        return out.getQueryResultCount() > 0;
    }

    public String getMinBllgSchdFromDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        if (!ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("011");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setConditionValue("usgChrgFlg01", FLG_ON_Y);
        inMsg.setConditionValue("invFlg01", FLG_OFF_N);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray outTMsg = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outTMsg.getValidCount() > 0) {
            return outTMsg.no(0).bllgSchdFromDt.getValue();
        }
        return null;
    }

    public CONTR_XS_COPYTMsgArray getContrXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return null;
        }
        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        CONTR_XS_COPYTMsgArray outTMsg = (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return outTMsg;
    }

    public DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    /**
     * get getInvoicedBllgSchd
     * @param cMsg NSAL0570CMsg
     * @param aCMsg NSAL0570_ACMsg
     * @return DS_CONTR_BLLG_SCHDTMsgArray
     */
    public static DS_CONTR_BLLG_SCHDTMsgArray getInvoicedBllgSchd(NSAL0570CMsg cMsg, NSAL0570_ACMsg aCMsg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPrcEffPk01", aCMsg.dsContrPrcEffPk_A1.getValue());
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        return (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    public String getMaxBllgSchdThruDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        if (!ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            return null;
        }
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("usgChrgFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getMaxBllgSchdThruDt", ssmParam).getResultObject();
    }

    //Mod Start 02/16/2016 <QC#2063>
    public static DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEff(NSAL0570CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    //Mod End   02/16/2016 <QC#2063>

    //Add Start 02/16/2016 <QC#2063>
    // START 2020/03/12 K.Kitachi [QC#55662, MOD]
//    public static DS_CONTR_PRC_EFF_MTRTMsgArray getPrcEffMtr(DS_CONTR_PRC_EFFTMsg prcEff) {
    public static DS_CONTR_PRC_EFF_MTRTMsgArray getPrcEffMtrForUpdate(DS_CONTR_PRC_EFFTMsg prcEff) {
    // END 2020/03/12 K.Kitachi [QC#55662, MOD]
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", prcEff.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPrcEffPk01", prcEff.dsContrPrcEffPk.getValue());
        inMsg.setConditionValue("dsContrBllgMtrPk01", prcEff.dsContrBllgMtrPk.getValue());
        // START 2020/03/12 K.Kitachi [QC#55662, MOD]
//        return (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
        // END 2020/03/12 K.Kitachi [QC#55662, MOD]
    }
    //Add End   02/16/2016 <QC#2063>


    public static BigDecimal getxsMtrAmtRate(NSAL0570CMsg cMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg) {
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPrcEffPk01", aggPrcEffTMsg.dsContrPrcEffPk.getValue());
        inMsg.setConditionValue("dsContrBllgMtrPk01", cMsg.dsContrBllgMtrPk_H1.getValue());
        DS_CONTR_PRC_EFF_MTRTMsgArray outMsg = (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.length() > 0) {
            return outMsg.no(0).xsMtrAmtRate.getValue();
        }
        return null;
    }

    public S21SsmEZDResult getMachDsContrPrcEff(Map<String, Object> ssmParam, NSAL0570_CCMsgArray cCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getMachDsContrPrcEff", ssmParam, cCMsgArray);
    }

    public static boolean existInvoicedBllgSchdMach(NSAL0570CMsg cMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("011");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPrcEffPk01", aggPrcEffTMsg.dsContrPrcEffPk.getValue());
        inMsg.setConditionValue("usgChrgFlg01", FLG_ON_Y);
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray outMsg = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.length() > 0) {
            return true;
        }
        return false;
    }

    // Add Start 04/11/2016 <QC#6728>
    public List<Map<String, Object>> getAggLinePe(String glblCmpyCd, BigDecimal prntDsContrDtlPk,  BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrDtlPk", prntDsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAggLinePe", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getAggMachLineContrDtl(String glblCmpyCd, BigDecimal prntDsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrDtlPk", prntDsContrDtlPk);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAggMachLineContrDtl", ssmParam).getResultObject();
    }

    public BigDecimal getMachBllgMtrPk(String glblCmpyCd, BigDecimal dsContrDtlPk,  String bllgMtrLbCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        return (BigDecimal) getSsmEZDClient().queryObject("getMachBllgMtrPk", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getPeMtr(String glblCmpyCd, BigDecimal dsContrPrcEffPk,  BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getPeMtr", ssmParam).getResultObject();
    }

    public Map<String, Object> getMachPeIncl(String glblCmpyCd, BigDecimal dsContrDtlPk,  BigDecimal dsContrBllgMtrPk, String fromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("fromDt", fromDt);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getMachPeIncl", ssmParam).getResultObject();
    }

    public Map<String, Object> getMachPeNext(String glblCmpyCd, BigDecimal dsContrDtlPk,  BigDecimal dsContrBllgMtrPk, String fromDt, String tartgetThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("fromDt", fromDt);
        ssmParam.put("tartgetThruDt", tartgetThruDt);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getMachPeNext", ssmParam).getResultObject();
    }
    // Add End   04/11/2016 <QC#6728>
    // Add Start 2017/11/21 QC#21724
    public String getContrEffThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        return (String) getSsmEZDClient().queryObject("getContrEffThruDt", ssmParam).getResultObject();
    }
    // Add End 2017/11/21 QC#21724

    // START 2019/11/25 K.Kitachi [QC#54703, ADD]
    public boolean existUnbilledRebill(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("countUnbilledRebill", ssmParam).getResultObject();
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }
    // END 2019/11/25 K.Kitachi [QC#54703, ADD]
}
