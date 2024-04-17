/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0600;

import static business.blap.NSAL0600.constant.NSAL0600Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.parts.NSZC047008PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Cascade Date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 * 2015/01/25   Hitachi         T.Tomita        Update          CSA QC#2720, 2721
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2023/08/28   Hitachi         S.Moriai        Update          QC#59846
 *</pre>
 */
public final class NSAL0600Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0600Query INSTANCE = new NSAL0600Query();

    /**
     * Constructor.
     */
    private NSAL0600Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0600Query
     */
    public static NSAL0600Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsContr
     * @param sMsg NSAL0600SMsg
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsContr(NSAL0600SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", sMsg.dsContrPk_P.getValue());

        Map<String, Object> rsltMap = (Map<String, Object>) getSsmEZDClient().queryObject("getDsContr", ssmParam).getResultObject();
        return rsltMap;
    }

    /**
     * getDsContrDtl
     * @param sMsg NSAL0600SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtl(NSAL0600SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", sMsg.dsContrPk_P.getValue());
        ssmParam.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        ssmParam.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("dsContrDtlTpAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmParam.put("lvl4", new BigDecimal(LVL_4));
        ssmParam.put("maxRow", sMsg.A.length());

        return getSsmEZDClient().queryEZDMsgArray("getDsContrDtl", ssmParam, sMsg.A);
    }

    /**
     * getInvDt
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return String
     */
    public String getInvDt(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        // START 2016/01/25 T.Tomita [QC#2720, ADD]
        ssmParam.put("invTpCdCr", INV_TP.CREDIT_MEMO);
        // END 2016/01/25 T.Tomita [QC#2720, ADD]
        return (String) getSsmEZDClient().queryObject("getInvDt", ssmParam).getResultObject();
    }

    /**
     * getDsContrPrcEffForBase
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param fromDt String
     * @param thruDt String
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsContrPrcEffForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String fromDt, String thruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("fromDt", fromDt);
        ssmParam.put("thruDt", thruDt);

        Map<String, Object> rsltMap = (Map<String, Object>) getSsmEZDClient().queryObject("getDsContrPrcEffForBase", ssmParam).getResultObject();
        return rsltMap;
    }

    /**
     * getDsContrBllgMtrPkList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDsContrBllgMtrPkList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);

        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrBllgMtrPkList", ssmParam).getResultObject();
        return rsltMapList;
    }

    /**
     * getDsContrPrcEffForUsage
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param fromDt String
     * @param thruDt String
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsContrPrcEffForUsage(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String fromDt, String thruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("fromDt", fromDt);
        ssmParam.put("thruDt", thruDt);

        Map<String, Object> rsltMap = (Map<String, Object>) getSsmEZDClient().queryObject("getDsContrPrcEffForUsage", ssmParam).getResultObject();
        return rsltMap;
    }

    /**
     * getDsContrPrcEffMtrTMsgArray
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_PRC_EFF_MTRTMsgArray
     */
    public DS_CONTR_PRC_EFF_MTRTMsgArray getDsContrPrcEffMtrTMsgArray(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        DS_CONTR_PRC_EFF_MTRTMsgArray outMsgArray = (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            new DS_CONTR_PRC_EFF_MTRTMsgArray();
        }
        return outMsgArray;
    }

    // Add Start 08/02/2016 <QC#7402>
    /**
     * getCurPePk
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public BigDecimal getCurPePk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("slsDt", slsDt);

        BigDecimal curPePk = (BigDecimal) getSsmEZDClient().queryObject("getCurPePk", ssmParam).getResultObject();
        return curPePk;
    }

    /**
     * getCurPePk
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsg
     */
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }
    // Add End   08/02/2016 <QC#7402>

    // START 2023/08/28 S.Moriai [QC#59846, ADD]
    /**
     * getDsContrPrcEff
     * @param pMsg NSZC047008PMsg
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDsContrPrcEff(NSZC047008PMsg pMsg, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
        ssmParam.put("contrEffFromDt", pMsg.contrEffFromDt.getValue());
        ssmParam.put("contrEffThruDt", pMsg.contrEffThruDt.getValue());
        ssmParam.put("baseChrgFlg", pMsg.baseChrgFlg.getValue());
        ssmParam.put("usgChrgFlg", pMsg.usgChrgFlg.getValue());
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrPrcEff", ssmParam).getResultObject();
    }

    /**
     * getDsContrDtlForUpdate
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtlForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tmsg);
    }

    /**
     * getDsContrPrcEff
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsgArray
     */
    public DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * getContrEffThruDt
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    public String getContrEffThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        return (String) getSsmEZDClient().queryObject("getContrEffThruDt", ssmParam).getResultObject();
    }

    /**
     * getContrXsCopy
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return CONTR_XS_COPYTMsgArray
     */
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

    /**
     * getPrcEffMtrForUpdate
     * @param prcEff DS_CONTR_PRC_EFFTMsg
     * @return DS_CONTR_PRC_EFF_MTRTMsgArray
     */
    public DS_CONTR_PRC_EFF_MTRTMsgArray getPrcEffMtrForUpdate(DS_CONTR_PRC_EFFTMsg prcEff) {
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", prcEff.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPrcEffPk01", prcEff.dsContrPrcEffPk.getValue());
        inMsg.setConditionValue("dsContrBllgMtrPk01", prcEff.dsContrBllgMtrPk.getValue());
        return (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }
    // END 2023/08/28 S.Moriai [QC#59846, ADD]
}
