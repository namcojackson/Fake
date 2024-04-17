/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0690;

import static business.blap.NSAL0690.constant.NSAL0690Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/11/26   Hitachi         T.Tsuchida      Update          QC#1225
 * 2015/12/24   Hitachi         T.Tsuchida      Update          QC#2429
 * 2015/12/24   Hitachi         T.Tsuchida      Update          QC#2436
 * 2017/02/13   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/09/28   Hitachi         K.Kojima        Update          QC#18376
 * 2017/10/02   Hitachi         M.Kidokoro      Update          QC#18290
 * 2018/05/22   Hitachi         K.Kitachi       Update          QC#26070
 * 2018/05/31   Hitachi         K.Kitachi       Update          QC#26210
 * 2018/06/15   Hitachi         K.Kojima        Update          QC#26702
 * 2018/06/18   Hitachi         U.Kim           Update          QC#24903
 * 2018/08/17   Hitachi         K.Kojima        Update          QC#27295
 * 2018/12/19   Fujitsu         W.Honda         Update          QC#29636
 * 2024/04/02   Hitachi         T.Nagae         Update          QC#63552
 *</pre>
 */
public final class NSAL0690Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0690Query INSTANCE = new NSAL0690Query();

    /**
     * Constructor.
     */
    private NSAL0690Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0690Query
     */
    public static NSAL0690Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aCMsgArray NSAL0690_ACMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrHdrInfo(Map<String, Object> ssmParam, NSAL0690_ACMsgArray aCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrHdrInfo", ssmParam, aCMsgArray);
    }

    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param bCMsgArray NSAL0690_BCMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlInfo(Map<String, Object> ssmParam, NSAL0690_BCMsgArray bCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrDtlInfo", ssmParam, bCMsgArray);
    }

    /**
     * exist Not Update Contract Detail
     * @param cMsg NSAL0690CMsg
     * @param bCMsg NSAL0690_BCMsg
     * @return boolean
     */
    public String getNewPeriodEnd(NSAL0690CMsg cMsg, NSAL0690_BCMsg bCMsg) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrDtlPk", bCMsg.dsContrPk_B1.getValue().toString());
        ssmParam.put("dsContrCtrlSts", DS_CONTR_CTRL_STS.ACTIVE);
        return (String) getSsmEZDClient().queryObject("getNewPeriodEnd", ssmParam).getResultObject();
    }

    /**
     * get Price Up Ratio
     * @param ssmParam Map<String, Object>
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getPrcUpRatio(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getPrcUpRatio", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return null;
        }
        return (Map<String, Object>) res.getResultObject();
    }

    /**
     * getDsContrBllgMtrList
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsgArray
     */
    public DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }

    /**
     * getDsContrDtl
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);

        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * getDsContrDtlByDsContrPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlTp String
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtlByDsContrPk(String glblCmpyCd, BigDecimal dsContrPk, String dsContrDtlTp) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_DTLTMsgArray tMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(tMsgArray.no(i).dsContrDtlTpCd)
                    && dsContrDtlTp.equals(tMsgArray.no(i).dsContrDtlTpCd.getValue())) {
                return (DS_CONTR_DTLTMsg) tMsgArray.no(i);
            }
        }
        return null;
    }

    /**
     * getContrXsCopyList
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return CONTR_XS_COPYTMsgArray
     */
    public CONTR_XS_COPYTMsgArray getContrXsCopyList(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg prmTMsg = new CONTR_XS_COPYTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }

    // START 2017/02/13 K.Kitachi [QC#17410, ADD]
    /**
     * countCfsDlr
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @param cfsDlrCd String
     * @return BigDecimal
     */
    public BigDecimal countCfsDlr(String glblCmpyCd, String billToCustCd, String cfsDlrCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("billToCustCd", billToCustCd);
        paramMap.put("cfsDlrCd", cfsDlrCd);
        return (BigDecimal) getSsmEZDClient().queryObject("countCfsDlr", paramMap).getResultObject();
    }

    /**
     * getRegAndAggBase
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getRegAndAggBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvl1", LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", BASE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getRegAndAggBase", paramMap).getResultObject();
    }

    /**
     * getRegAndAggUsg
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getRegAndAggUsg(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("dsContrMachLvl1", LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", OVERAGE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getRegAndAggUsg", paramMap).getResultObject();
    }

    /**
     * getFltBase
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getFltBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvl1", LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", BASE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getFltBase", paramMap).getResultObject();
    }

    /**
     * getFltUsg
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getFltUsg(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("dsContrMachLvl1", LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", OVERAGE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getFltUsg", paramMap).getResultObject();
    }
    // END 2017/02/13 K.Kitachi [QC#17410, ADD]

    // START 2017/09/28 K.Kojima [QC#18376,ADD]
    /**
     * getFleetLineNewAmount
     * @param glblCmpyCd
     * @param dsContrPk
     * @param basePrcDealAmt
     * @return
     */
    public BigDecimal getFleetLineNewAmount(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal basePrcDealAmt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("basePrcDealAmt", basePrcDealAmt);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        return (BigDecimal) getSsmEZDClient().queryObject("getFleetLineNewAmount", paramMap).getResultObject();
    }

    /**
     * getUplftMtrPrcUpRatio
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return BigDecimal
     */
    public BigDecimal getUplftMtrPrcUpRatio(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        BigDecimal uplftMtrPrcUpRatio = (BigDecimal) getSsmEZDClient().queryObject("getUplftMtrPrcUpRatio", param).getResultObject();
        if (uplftMtrPrcUpRatio == null) {
            uplftMtrPrcUpRatio = BigDecimal.ZERO;
        }
        return uplftMtrPrcUpRatio;
    }

    /**
     * getUplftMtrPrcUpRatioForLine
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return BigDecimal
     */
    public BigDecimal getUplftMtrPrcUpRatioForLine(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        BigDecimal uplftMtrPrcUpRatio = (BigDecimal) getSsmEZDClient().queryObject("getUplftMtrPrcUpRatioForLine", param).getResultObject();
        if (uplftMtrPrcUpRatio == null) {
            uplftMtrPrcUpRatio = BigDecimal.ZERO;
        }
        return uplftMtrPrcUpRatio;
    }
    // END 2017/09/28 K.Kojima [QC#18376,ADD]

    // START 2018/06/15 K.Kojima [QC#26702,DEL]
    // // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
    // /**
    //  * getPoDtListForBase
    //  * @param glblCmpyCd String
    //  * @param dsContrPk BigDecimal
    //  * @param dsContrDtlPk BigDecimal
    //  * @return List<String>
    //  */
    // public List<String> getPoDtListForBase(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    //     Map<String, Object> paramMap = new HashMap<String, Object>();
    //     paramMap.put("glblCmpyCd", glblCmpyCd);
    //     paramMap.put("dsContrPk", dsContrPk);
    //     paramMap.put("dsContrDtlPk", dsContrDtlPk);
    //     paramMap.put("dsContrMachLvlNum1", LVL_NUM_1);
    //     paramMap.put("dsContrMachLvlNum2", LVL_NUM_2);
    //     paramMap.put("dsContrMachLvlNum3", LVL_NUM_3);
    //     List<String> poDtList = (List<String>) getSsmEZDClient().queryObjectList("getPoDtListForBase", paramMap).getResultObject();
    //     return poDtList;
    // }
    // 
    // /**
    //  * getPoDtListForUsage
    //  * @param glblCmpyCd String
    //  * @param dsContrPk BigDecimal
    //  * @param dsContrDtlPk BigDecimal
    //  * @param dsContrBllgMtrPk BigDecimal
    //  * @return List<String>
    //  */
    // public List<String> getPoDtListForUsg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    //     Map<String, Object> paramMap = new HashMap<String, Object>();
    //     paramMap.put("glblCmpyCd", glblCmpyCd);
    //     paramMap.put("dsContrPk", dsContrPk);
    //     paramMap.put("dsContrDtlPk", dsContrDtlPk);
    //     paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
    //     paramMap.put("dsContrMachLvlNum1", LVL_NUM_1);
    //     paramMap.put("dsContrMachLvlNum2", LVL_NUM_2);
    //     paramMap.put("dsContrMachLvlNum3", LVL_NUM_3);
    //     List<String> poDtList = (List<String>) getSsmEZDClient().queryObjectList("getPoDtListForUsg", paramMap).getResultObject();
    //     return poDtList;
    // }
    // // END 2017/10/02 M.Kidokoro [QC#18290, ADD]
    // END 2018/06/15 K.Kojima [QC#26702,DEL]

    // START 2018/05/22 K.Kitachi [QC#26070, ADD]
    /**
     * getDsContrPrcEffMtrPkList
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return S21SsmEZDResult
     */
    // START 2012/12/01 R.Jin [QC#60880,MOD]
//    public List<BigDecimal> getDsContrPrcEffMtrPkList(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
    public S21SsmEZDResult getDsContrPrcEffMtrPkList(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
    // END 2012/12/01 R.Jin [QC#60880,MOD]
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        // START 2012/12/01 R.Jin [QC#60880,MOD]
//        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrPrcEffMtrPkList", paramMap).getResultObject();
        return getSsmEZDClient().queryObjectList("getDsContrPrcEffMtrPkList", paramMap);
        // END 2012/12/01 R.Jin [QC#60880,MOD]
    }

    /**
     * getDsContrPrcEffMtr
     * @param glblCmpyCd String
     * @param dsContrPrcEffMtrPk BigDecimal
     * @return DS_CONTR_PRC_EFF_MTRTMsg
     */
    public DS_CONTR_PRC_EFF_MTRTMsg getDsContrPrcEffMtr(String glblCmpyCd, BigDecimal dsContrPrcEffMtrPk) {
        DS_CONTR_PRC_EFF_MTRTMsg tMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcEffMtrPk, dsContrPrcEffMtrPk);
        return (DS_CONTR_PRC_EFF_MTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }
    // END 2018/05/22 K.Kitachi [QC#26070, ADD]

    // START 2018/06/15 K.Kojima [QC#26702,DEL]
    // // START 2018/05/31 K.Kitachi [QC#26210, ADD]
    // /**
    //  * getRnwHoldPoTrgt
    //  * @param glblCmpyCd String
    //  * @param dsContrPk BigDecimal
    //  * @return List<BigDecimal>
    //  */
    // public List<BigDecimal> getRnwHoldPoTrgt(String glblCmpyCd, BigDecimal dsContrPk) {
    //     Map<String, Object> paramMap = new HashMap<String, Object>();
    //     paramMap.put("glblCmpyCd", glblCmpyCd);
    //     paramMap.put("dsContrPk", dsContrPk);
    //     paramMap.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
    //     return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getRnwHoldPoTrgt", paramMap).getResultObject();
    // }
    // 
    // /**
    //  * getDsContrPrcEff
    //  * @param glblCmpyCd String
    //  * @param dsContrPrcEffPk BigDecimal
    //  * @return DS_CONTR_PRC_EFFTMsg
    //  */
    // public DS_CONTR_PRC_EFFTMsg getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
    //     DS_CONTR_PRC_EFFTMsg tMsg = new DS_CONTR_PRC_EFFTMsg();
    //     ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
    //     ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcEffPk, dsContrPrcEffPk);
    //     return (DS_CONTR_PRC_EFFTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
    // }
    // // END 2018/05/31 K.Kitachi [QC#26210, ADD]
    // END 2018/06/15 K.Kojima [QC#26702,DEL]

    // START 2018/06/18 U.Kim [QC#24903,ADD]
    /**
     * getDsContrPrcEff
     * @param curDsContrDtlPk
     * @return
     */
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEffForLastBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        inTMsg.setSQLID("006");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        DS_CONTR_PRC_EFFTMsgArray outTmsgArray = (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTmsgArray.getValidCount() == 0) {
            return null;
        }
        return outTmsgArray.no(0);
    }
    // END 2018/06/18 U.Kim [QC#24903,ADD]

    // START 2018/08/17 K.Kojima [QC#27295,ADD]
    /**
     * getRnwLtrCtrlInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getRnwLtrCtrlInfo(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getRnwLtrCtrlInfo", paramMap).getResultObject();
    }
    // END 2018/08/17 K.Kojima [QC#27295,ADD]

    // START 2018/12/19 W.Honda [QC#29636,ADD]
    /**
     * getMaxPrcUpRatioFromRnwEscl
     * @param dsContrTMsg
     * @param dsContrDtlTMsg
     * @param baseFlg
     * @return BigDecimal
     */
    public BigDecimal getMaxPrcUpRatioFromRnwEscl(DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, boolean baseFlg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", dsContrDtlTMsg.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrDtlTMsg.dsContrPk);
        if (!baseFlg && DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            paramMap.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        } else {
            paramMap.put("dsContrDtlPk", dsContrDtlTMsg.dsContrDtlPk);
        }
        paramMap.put("machLvlNumLv1", "1");
        paramMap.put("machLvlNumLv2", "2");
        paramMap.put("machLvlNumLv3", "3");
        if (baseFlg) {
            paramMap.put("baseFlg", ZYPConstant.FLG_ON_Y);
            paramMap.put("baseUsgNumBase", "BASE");
        } else {
            paramMap.put("usageFlg", ZYPConstant.FLG_ON_Y);
            paramMap.put("baseUsgNumUsg", "OVERAGE");
        }

        return (BigDecimal) getSsmEZDClient().queryObject("getMaxPrcUpRatioFromRnwEscl", paramMap).getResultObject();
    }
    // END 2018/12/19 W.Honda [QC#29636,ADD]

    // START 2024/04/02 T.Nagae [QC#63552, ADD]
    public List<Map<String, Object>> getRenewalLetterList(String glblCmpyCd, String slsDt, List<BigDecimal> dsContrDtlPkList){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("slsDt", slsDt);
        paramMap.put("dsContrDtlPkList", dsContrDtlPkList);
        
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRenewalLetterList", paramMap).getResultObject();
    }
    // END 2024/04/02 T.Nagae [QC#63552, ADD]

}
