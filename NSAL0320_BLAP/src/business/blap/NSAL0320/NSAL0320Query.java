/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0320;

import static business.blap.NSAL0320.constant.NSAL0320Constant.END_DT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_MTR_USEDTMsg;
import business.db.DS_CONTR_BLLG_MTR_USEDTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.MDL_NMTMsg;
import business.db.MTR_LBTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_TIER_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC#1811
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#2806
 * 2016/02/03   Hitachi         T.Kanasaka      Update          QC#4029
 * 2016/02/09   Hitachi         T.Tomita        Update          QC#4219
 * 2016/02/09   Hitachi         T.Tomita        Update          QC#2424
 * 2016/03/14   Hitachi         T.Kanasaka      Update          QC#5005
 * 2016/03/14   Hitachi         T.Tomita        Update          QC#4958
 * 2016/06/08   Hitachi         T.Kanasaka      Update          QC#5005
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12106
 * 2016/09/20   Hitachi         T.Tomita        Update          QC#6316
 * 2016/09/23   Hitachi         T.Tomita        Update          QC#12178
 * 2016/10/06   Hitachi         T.Tomita        Update          QC#12178
 * 2016/11/15   Hitachi         K.Kishimoto     Update          QC#15133
 * 2016/12/15   Hitachi         T.Mizuki        Update          QC#16399
 * 2017/08/07   Hitachi         T.Kanasaka      Update          QC#18193,18195
 * 2018/04/06   Hitachi         K.Kitachi       Update          QC#23628
 * 2018/07/09   Hitachi         K.Kitachi       Update          QC#26834
 * 2020/04/16   CITS            T.Wada          Update          QC#55922
 * 2020/09/24   Hitachi         K.Kitachi       Update          QC#57667
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/10/25   CITS            E.Sanchez       Update          QC#60661
 *</pre>
 */
public class NSAL0320Query extends S21SsmEZDQuerySupport {

    private static final NSAL0320Query INSTANCE = new NSAL0320Query();

    private NSAL0320Query() {
    }

    public static final NSAL0320Query getInstance() {
        return INSTANCE;
    }

    public S21SsmEZDResult gerDsContr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObject("getDsContr", ssmPrm);
    }

    // START 2016/02/09 T.Tomita[QC#4219, MOD]
    public S21SsmEZDResult getBllgMtr(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk, int rowNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("rowNum", rowNum);
        return getSsmEZDClient().queryObjectList("getBllgMtr", ssmPrm);
    }

    // END 2016/02/09 T.Tomita[QC#4219, MOD]

    // mod start 2016/10/07 CSA Defect#12178
    public S21SsmEZDResult getBllgMtrMap(String glblCmpyCd, String mdlMtrLbCd, String bllgMtrLbCd, String bllgMtrMapLvlNum, BigDecimal dsContrDtlPk, String slsDt, BigDecimal prcMtrPkgPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("bllgMtrMapLvlNum", bllgMtrMapLvlNum);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("maxDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
        return getSsmEZDClient().queryObjectList("getBllgMtrMap", ssmPrm);
    }

    // mod end 2016/10/07 CSA Defect#12178

    public CONTR_PHYS_BLLG_MTR_RELNTMsg getContrPhysBllgMtrReln(String glblCmpyCd, BigDecimal contrPhysBllgMtrRelnPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg prmTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public CONTR_XS_COPYTMsgArray getContrXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg prmTMsg = new CONTR_XS_COPYTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // START 2015/10/13 T.Tomita [N/A, ADD]

    /**
     * getSvcPkgMap
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkgMap(String glblCmpyCd, BigDecimal mdlId, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlId", mdlId);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObjectList("getSvcPkgMap", ssmPrm);
    }

    // START 2018/04/06 K.Kitachi [QC#23628, ADD]
    /**
     * getSvcPkgMapForFltAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkgMapForFltAgg(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObjectList("getSvcPkgMapForFltAgg", ssmPrm);
    }

    // END 2018/04/06 K.Kitachi [QC#23628, ADD]

    // mod start 2016/07/26 CSA Defect#12106
    /**
     * getBllgMtrLvlForNonFleet
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrLvlForNonFleet(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("maxDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObjectList("getBllgMtrLvlForNonFleet", ssmPrm);
    }

    // mod end 2016/07/26 CSA Defect#12106

    // mod start 2016/10/06 CSA Defect#12178
    /**
     * getBllgMtrLvlForFltAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrLvlForFltAgg(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("maxDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObjectList("getBllgMtrLvlForFltAgg", ssmPrm);
    }

    // mod end 2016/10/06 CSA Defect#12178

    // mod start 2016/05/19 CSA Defect#4958
    /**
     * getPhysMtrForFltAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPhysMtrForFltAgg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        return getSsmEZDClient().queryObjectList("getPhysMtrForFltAgg", ssmPrm);
    }

    // mod end 2016/05/19 CSA Defect#4958

    /**
     * getDefPhysMtrForFltAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param mdlMtrLbCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefPhysMtrForFltAgg(String glblCmpyCd, BigDecimal dsContrPk, String mdlMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        return getSsmEZDClient().queryObject("getDefPhysMtrForFltAgg", ssmPrm);
    }

    // mod start 2016/10/06 CSA Defect#12178
    /**
     * getBllgMtrForFltAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @param mdlMtrLbCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrForFltAgg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String mdlMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);

        return getSsmEZDClient().queryObjectList("getBllgMtrForFltAgg", ssmPrm);
    }

    // mod end 2016/10/06 CSA Defect#12178

    // START 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
    // mod start 2016/07/26 CSA Defect#12106
    // START 2015/12/11 T.Kanasaka [QC#1811, MOD]
    /**
     * getBllgCntrInfo
     * @param glblCmpyCd String
     * @param prcMtrPkgPk BigDecimal
     * @param mdlId BigDecimal
     * @param mdlMtrLbCd String
     * @param dsContrCatgCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgCntrInfo(String glblCmpyCd, BigDecimal prcMtrPkgPk, BigDecimal mdlId, String mdlMtrLbCd, String dsContrCatgCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        // mod start 2016/10/07 CSA Defect#12178
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        // String fltFlg = ZYPConstant.FLG_OFF_N;
        // String aggFlg = ZYPConstant.FLG_OFF_N;
        String regFlg = ZYPConstant.FLG_OFF_N;
        // if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
        // fltFlg = ZYPConstant.FLG_ON_Y;
        // } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
        // aggFlg = ZYPConstant.FLG_ON_Y;
        // } else if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
        // regFlg = ZYPConstant.FLG_ON_Y;
        // }
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            regFlg = ZYPConstant.FLG_ON_Y;
        }
        // ssmPrm.put("fltFlg", fltFlg);
        // ssmPrm.put("aggFlg", aggFlg);
        ssmPrm.put("regFlg", regFlg);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("maxDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        // mod end 2016/10/07 CSA Defect#12178
        return getSsmEZDClient().queryObjectList("getBllgCntrInfo", ssmPrm);
    }

    // END 2015/12/11 T.Kanasaka [QC#1811, MOD]
    // mod end 2016/07/26 CSA Defect#12106
    // END 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]

    // START 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
    // mod start 2016/07/26 CSA Defect#12106
    // mod start 2016/05/24 CSA Defect#4958
    // START 2016/01/07 T.Tomita [QC#2806, MOD]
    /**
     * getBllgMtrLb
     * @param glblCmpyCd String
     * @param bllgMtrLbCdArray String[]
     * @param bllgMtrMapLvlNum String
     * @param mdlMtrLbCd String
     * @param dsContrCatgCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrLb(String glblCmpyCd, String[] bllgMtrLbCdArray, String bllgMtrMapLvlNum, String mdlMtrLbCd, String dsContrCatgCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("bllgMtrLbCdArray", bllgMtrLbCdArray);
        ssmPrm.put("bllgMtrMapLvlNum", bllgMtrMapLvlNum);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        // ssmPrm.put("dsContrCatgCdReg", DS_CONTR_CATG.REGULAR);
        // ssmPrm.put("dsContrCatgCdFlt", DS_CONTR_CATG.FLEET);
        // ssmPrm.put("dsContrCatgCdAgg", DS_CONTR_CATG.AGGREGATE);
        // ssmPrm.put("dsContrCatgCd", dsContrCatgCd);
        String regFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            regFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmPrm.put("regFlg", regFlg);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("maxDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObjectList("getBllgMtrLb", ssmPrm);
    }

    // mod end 2016/07/26 CSA Defect#12106
    // END 2016/01/07 T.Tomita [QC#2806, MOD]
    // mod end 2016/05/24 CSA Defect#4958
    // END 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]

    /**
     * getMtrMultRate
     * @param glblCmpyCd String
     * @param prcMtrPkgPk BigDecimal
     * @param bllgMtrLbCd String
     * @param physMtrLbCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrMultRate(String glblCmpyCd, BigDecimal prcMtrPkgPk, String bllgMtrLbCd, String physMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("physMtrLbCd", physMtrLbCd);
        return getSsmEZDClient().queryObjectList("getMtrMultRate", ssmPrm);
    }

    /**
     * getBllgMtrSchd
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrSchd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getBllgMtrSchd", ssmPrm);
    }

    // START 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
    /**
     * getPrntBllgMtr
     * @param glblCmpyCd String
     * @param mdlMtrLbCd String
     * @param bllgMtrLbCd String
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getPrntBllgMtr(String glblCmpyCd, String
    // mdlMtrLbCd, String bllgMtrLbCd, String slsDt) {
    public S21SsmEZDResult getPrntBllgMtr(String glblCmpyCd, String mdlMtrLbCd, String bllgMtrLbCd, String slsDt, String dsContrCatgCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);
        String regFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            regFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmPrm.put("regFlg", regFlg);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getPrntBllgMtr", ssmPrm);
    }

    // END 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]

    /**
     * getDsContrDtlForFltAggMach
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param mdlMtrLbCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlForFltAggMach(String glblCmpyCd, BigDecimal dsContrPk, String mdlMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        return getSsmEZDClient().queryObjectList("getDsContrDtlForFltAggMach", ssmPrm);
    }

    /**
     * getPrcListSvcTier
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @param prcMtrPkgPk BigDecimal
     * @param bllgMtrLbCd String
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListSvcTier(String glblCmpyCd, BigDecimal mdlId, BigDecimal prcMtrPkgPk, String bllgMtrLbCd, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlId", mdlId);
        ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmPrm.put("prcSvcPlnTpCd", PRC_SVC_PLN_TP.FIXED);
        ssmPrm.put("prcSvcContrTpCd", PRC_SVC_CONTR_TP.TONER_INCLUSIVE);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("prcSvcTierTpCd", PRC_SVC_TIER_TP.BASE);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObjectList("getPrcListSvcTier", ssmPrm);
    }

    /**
     * getDsContrDtl
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsContrBllgMtrPk
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrBllgMtrPk(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        return getSsmEZDClient().queryObject("getDsContrBllgMtrPk", ssmPrm);
    }

    // START 2016/03/14 T.Kanasaka [QC5005, MOD]
    /**
     * getDsContrBllgMtrForUpdate
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return DS_CONTR_BLLG_MTRTMsgArray
     */
    public DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
        // DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        // END 2016/02/03 T.Kanasaka [QC#4029, MOD]
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getContrPhysBllgMtrRelnForUpdate
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @param svcPhysMtrPk BigDecimal
     * @return CONTR_PHYS_BLLG_MTR_RELNTMsgArray
     */
    public CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrRelnForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd, BigDecimal svcPhysMtrPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg prmTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        prmTMsg.setSQLID("005");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);
        prmTMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // mod start 2016/05/20 CSA Defect#4958
    /**
     * getContrPhysBllgMtrRelnForUpdate
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcPhysMtrPk BigDecimal
     * @return CONTR_PHYS_BLLG_MTR_RELNTMsgArray
     */
    public CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrRelnForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg prmTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        prmTMsg.setSQLID("006");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // mod end 2016/05/20 CSA Defect#4958
    // END 2016/03/14 T.Kanasaka [QC5005, MOD]

    // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
    /**
     * getScheduleApiParams
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getScheduleApiParams(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return getSsmEZDClient().queryObjectList("getScheduleApiParams", ssmPrm);
    }

    // END 2016/02/03 T.Kanasaka [QC#4029, MOD]
    // START 2016/02/09 T.Tomita [QC#2424, ADD]
    public MTR_LBTMsg getMtrLbTMsg(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mtrLbCd, mtrLbCd);

        return (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    // END 2016/02/09 T.Tomita [QC#2424, ADD]
    // END 2015/10/13 T.Tomita [N/A, ADD]

    // START 2016/03/14 T.Kanasaka [QC5005, ADD]
    /**
     * getUnBllbRelnForFltAgg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUnBllbRelnForFltAgg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        return getSsmEZDClient().queryObjectList("getUnBllbRelnForFltAgg", ssmPrm);
    }

    /**
     * getContrXsCopyForAgg
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrXsCopyForAgg(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        return getSsmEZDClient().queryObjectList("getContrXsCopyForAgg", ssmPrm);
    }

    // END 2016/03/14 T.Kanasaka [QC5005, ADD]
    // add start 2016/05/18 CSA Defect#4958
    /**
     * getMdlNm
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @return String
     */
    public String getMdlNm(String glblCmpyCd, BigDecimal mdlId) {
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.t_GlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.t_MdlId, mdlId);
        MDL_NMTMsg resultMsg = (MDL_NMTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (resultMsg == null) {
            return null;
        }
        return resultMsg.t_MdlNm.getValue();
    }

    // START 2016/09/20 T.Tomita [QC#6316, MOD]
    /**
     * getDefaultBllgMtrLbForNonFleet
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mdlMtrLbCd String
     * @param mtrGrpPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getDefaultBllgMtrLbForNonFleet(String glblCmpyCd, String slsDt, String mdlMtrLbCd, BigDecimal mtrGrpPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("maxDt", END_DT);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        ssmPrm.put("mtrGrpPk", mtrGrpPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDefaultBllgMtrLbForNonFleet", ssmPrm).getResultObject();
    }

    // START 2017/08/07 T.Kanasaka [QC#18193,18195,DEL]
    // /**
    // * getDefaultBllgMtrLbForFltAgg
    // * @param glblCmpyCd String
    // * @param slsDt String
    // * @param mdlMtrLbCd String
    // * @param dsContrCatgCd String
    // * @param mtrGrpPk BigDecimal
    // * @return Map<String, Object>
    // */
    // public Map<String, Object> getDefaultBllgMtrLbForFltAgg(String
    // glblCmpyCd, String slsDt, String mdlMtrLbCd, String
    // dsContrCatgCd, BigDecimal mtrGrpPk) {
    // Map<String, Object> ssmPrm = new HashMap<String, Object>();
    // ssmPrm.put("glblCmpyCd", glblCmpyCd);
    // ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
    // ssmPrm.put("slsDt", slsDt);
    // ssmPrm.put("maxDt", END_DT);
    // ssmPrm.put("dsContrCatgCd", dsContrCatgCd);
    // ssmPrm.put("dsContrCatgCdFlt", DS_CONTR_CATG.FLEET);
    // ssmPrm.put("dsContrCatgCdAgg", DS_CONTR_CATG.AGGREGATE);
    // ssmPrm.put("mtrGrpPk", mtrGrpPk);
    // return (Map<String, Object>)
    // getSsmEZDClient().queryObject("getDefaultBllgMtrLbForFltAgg",
    // ssmPrm).getResultObject();
    // }
    // END 2017/08/07 T.Kanasaka [QC#18193,18195,DEL]
    // END 2016/09/20 T.Tomita [QC#6316, MOD]

    // mod start 2016/07/26 CSA Defect#12106
    // START 2016/06/08 T.Kanasaka [QC#5005, MOD]
    /**
     * getBllgMtrLbForFltAgg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mdlMtrLbCd String
     * @param dsContrCatgCd String
     * @param bllgMtrLbCd String
     * @param dsContrDtlPk BigDecimal
     * @param prcMtrPkgPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getBllgMtrLbForFltAgg(String glblCmpyCd, String slsDt, String mdlMtrLbCd, String dsContrCatgCd, String bllgMtrLbCd, BigDecimal dsContrDtlPk, BigDecimal prcMtrPkgPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("maxDt", END_DT);
        // mod start 2016/10/06 CSA Defect#12178
        // START 2017/08/07 T.Kanasaka [QC#18193,18195,DEL]
        // String fltFlg = ZYPConstant.FLG_OFF_N;
        // String aggFlg = ZYPConstant.FLG_OFF_N;
        // if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
        // fltFlg = ZYPConstant.FLG_ON_Y;
        // } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
        // aggFlg = ZYPConstant.FLG_ON_Y;
        // }
        // ssmPrm.put("fltFlg", fltFlg);
        // ssmPrm.put("aggFlg", aggFlg);
        // END 2017/08/07 T.Kanasaka [QC#18193,18195,DEL]
        ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
        // mod end 2016/10/06 CSA Defect#12178
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgMtrLbForFltAgg", ssmPrm).getResultObject();
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, MOD]
    // mod END 2016/07/26 CSA Defect#12106

    /**
     * getDefSvcPkgForNonFleet
     * @param glblCmpyCd String
     * @param prcMtrPkgPk BigDecimal
     * @param mtrLbCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getDefSvcPkgForNonFleet(String glblCmpyCd, BigDecimal prcMtrPkgPk, String mtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmPrm.put("mtrLbCd", mtrLbCd);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDefSvcPkgForNonFleet", ssmPrm).getResultObject();
    }

    // START 2017/08/07 T.Kanasaka [QC#18193,18195,DEL]
    // // mod start 2016/09/23 CSA Defect#12178
    // /**
    // * getDefSvcPkgForFltAgg
    // * @param glblCmpyCd String
    // * @param slsDt String
    // * @param prcMtrPkgPk BigDecimal
    // * @param mtrLbCd String
    // * @param dsContrCatgCd String
    // * @return Map<String, Object>
    // */
    // public Map<String, Object> getDefSvcPkgForFltAgg(String
    // glblCmpyCd, String slsDt, BigDecimal prcMtrPkgPk, String
    // mtrLbCd, String dsContrCatgCd) {
    // Map<String, Object> ssmPrm = new HashMap<String, Object>();
    // ssmPrm.put("glblCmpyCd", glblCmpyCd);
    // ssmPrm.put("prcMtrPkgPk", prcMtrPkgPk);
    // ssmPrm.put("mtrLbCd", mtrLbCd);
    // ssmPrm.put("dsContrCatgCd", dsContrCatgCd);
    // ssmPrm.put("dsContrCatgCdFlt", DS_CONTR_CATG.FLEET);
    // ssmPrm.put("dsContrCatgCdAgg", DS_CONTR_CATG.AGGREGATE);
    // ssmPrm.put("slsDt", slsDt);
    // ssmPrm.put("maxDt", END_DT);
    // return (Map<String, Object>)
    // getSsmEZDClient().queryObject("getDefSvcPkgForFltAgg",
    // ssmPrm).getResultObject();
    // }
    // // mod end 2016/09/23 CSA Defect#12178
    // END 2017/08/07 T.Kanasaka [QC#18193,18195,DEL]

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param mdlMtrLbCd String
     * @return String
     */
    public String getBllgMtrLbCdForMach(String glblCmpyCd, BigDecimal dsContrDtlPk, String mdlMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        return (String) getSsmEZDClient().queryObject("getBllgMtrLbCdForMach", ssmPrm).getResultObject();
    }

    // add end 2016/05/18 CSA Defect#4958

    // START 2016/06/08 T.Kanasaka [QC#5005, ADD]
    /**
     * @param glblCmpyCd String
     * @param contrPhysBllgMtrRelnPk BigDecimal
     * @param bllgMtrLbCd String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getContrPhysBllgMtrRelnPkForMach(String glblCmpyCd, BigDecimal contrPhysBllgMtrRelnPk, String bllgMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("contrPhysBllgMtrRelnPk", contrPhysBllgMtrRelnPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getContrPhysBllgMtrRelnPkForMach", ssmPrm).getResultObject();
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, ADD]

    // Add Start 2016/11/15 <QC#15133>
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param mdlMtrLbCd String
     * @return String
     */
    public String getPrcVldFlg(String glblCmpyCd, BigDecimal mdlId) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlId", mdlId);
        return (String) getSsmEZDClient().queryObject("getPrcVldFlg", ssmPrm).getResultObject();
    }

    // Add End 2016/11/15 <QC#15133>
    // add start 2016/12/15 CSA QC#16399
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrBllgSchdPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getDsContrBllgSchdPk", ssmPrm);
    }

    // add end 2016/12/15 CSA QC#16399

    // START 2018/07/09 K.Kitachi [QC#26834, ADD]
    /**
     * getLineMtrNotExstMachMtrInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrCatgCd String
     * @return (List<Map<String, Object>>)
     */
    public List<Map<String, Object>> getLineMtrNotExstMachMtrInfo(String glblCmpyCd, BigDecimal dsContrPk, String dsContrCatgCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        if (dsContrCatgCd.equals(DS_CONTR_CATG.FLEET)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        }
        if (dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        }
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getLineMtrNotExstMachMtrInfo", ssmPrm).getResultObject();
    }

    /**
     * getLineMtrNotExstMachMtrBllgPk
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getLineMtrNotExstMachMtrBllgPk(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getLineMtrNotExstMachMtrBllgPk", ssmPrm).getResultObject();
    }

    /**
     * getDsContrBllgMtrUsedForUpdate
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_BLLG_MTR_USEDTMsgArray
     */
    public DS_CONTR_BLLG_MTR_USEDTMsgArray getDsContrBllgMtrUsedForUpdate(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTR_USEDTMsg prmTMsg = new DS_CONTR_BLLG_MTR_USEDTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTR_USEDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsContrPrcEffForUpdate
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsgArray
     */
    public DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        prmTMsg.setSQLID("003");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsContrPrcEffMtrForUpdate
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFF_MTRTMsgArray
     */
    public DS_CONTR_PRC_EFF_MTRTMsgArray getDsContrPrcEffMtrForUpdate(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFF_MTRTMsg prmTMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        prmTMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsContrBllgSchdSmryForUpdate
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_BLLG_SCHD_SMRYTMsgArray
     */
    public DS_CONTR_BLLG_SCHD_SMRYTMsgArray getDsContrBllgSchdSmryForUpdate(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_BLLG_SCHD_SMRYTMsg prmTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        return (DS_CONTR_BLLG_SCHD_SMRYTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsContrBllgSchdMtrForUpdate
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @return DS_CONTR_BLLG_SCHD_MTRTMsgArray
     */
    public DS_CONTR_BLLG_SCHD_MTRTMsgArray getDsContrBllgSchdMtrForUpdate(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrBllgSchdSmryPk) {
        DS_CONTR_BLLG_SCHD_MTRTMsg prmTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        prmTMsg.setConditionValue("dsContrBllgSchdSmryPk01", dsContrBllgSchdSmryPk);
        return (DS_CONTR_BLLG_SCHD_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsContrBllgSchdForUpdate
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param invFlg String
     * @return DS_CONTR_BLLG_SCHDTMsgArray
     */
    public DS_CONTR_BLLG_SCHDTMsgArray getDsContrBllgSchdForUpdate(String glblCmpyCd, BigDecimal dsContrPrcEffPk, String invFlg) {
        DS_CONTR_BLLG_SCHDTMsg prmTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        prmTMsg.setSQLID("007");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        prmTMsg.setConditionValue("invFlg01", invFlg);
        return (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getSvcContrBllg
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return SVC_CONTR_BLLGTMsg
     */
    public SVC_CONTR_BLLGTMsg getSvcContrBllg(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_BLLGTMsg prmTMsg = new SVC_CONTR_BLLGTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcContrBllgPk, svcContrBllgPk);
        return (SVC_CONTR_BLLGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * getSvcContrMtrBllgForUpdate
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return SVC_CONTR_MTR_BLLGTMsgArray
     */
    public SVC_CONTR_MTR_BLLGTMsgArray getSvcContrMtrBllgForUpdate(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_MTR_BLLGTMsg prmTMsg = new SVC_CONTR_MTR_BLLGTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_MTR_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getSvcContrXsMtrBllgForUpdate
     * @param glblCmpyCd String
     * @param svcContrMtrBllgPk BigDecimal
     * @return SVC_CONTR_XS_MTR_BLLGTMsgArray
     */
    public SVC_CONTR_XS_MTR_BLLGTMsgArray getSvcContrXsMtrBllgForUpdate(String glblCmpyCd, BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_XS_MTR_BLLGTMsg prmTMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_XS_MTR_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getSvcContrBllgAllocForUpdate
     * @param glblCmpyCd String
     * @param svcContrMtrBllgPk BigDecimal
     * @return SVC_CONTR_BLLG_ALLOCTMsgArray
     */
    public SVC_CONTR_BLLG_ALLOCTMsgArray getSvcContrBllgAllocForUpdate(String glblCmpyCd, BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg prmTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_BLLG_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getSvcContrAddlChrgBllgForUpdate
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return SVC_CONTR_ADDL_CHRG_BLLGTMsgArray
     */
    public SVC_CONTR_ADDL_CHRG_BLLGTMsgArray getSvcContrAddlChrgBllgForUpdate(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_ADDL_CHRG_BLLGTMsg prmTMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_ADDL_CHRG_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // END 2018/07/09 K.Kitachi [QC#26834, ADD]

    // START 2020/09/24 K.Kitachi [QC#57667, DEL]
    // // QC#55922 Add Start
    // /**
    // * getContrPhysBllgMtrRelnByContrDtlPk
    // */
    // public List<Map<String, Object>>
    // getContrPhysBllgMtrRelnByContrDtlPk(String glblCmpyCd,
    // BigDecimal dsContrDtlPk) {
    // Map<String, Object> ssmPrm = new HashMap<String, Object>();
    // ssmPrm.put("glblCmpyCd", glblCmpyCd);
    // ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
    // return (List<Map<String, Object>>)
    // getSsmEZDClient().queryObjectList("getContrPhysBllgMtrRelnByContrDtlPk",
    // ssmPrm).getResultObject();
    // }
    // // QC#55922 Add End
    // END 2020/09/24 K.Kitachi [QC#57667, DEL]

    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcPhysMtrReadGrpSq BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrSchdBySvcPhysMrReadGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        return getSsmEZDClient().queryObjectList("getBllgMtrSchdBySvcPhysMrReadGrpSq", ssmPrm);
    }
    // END 2022/06/22 E.Sanchez [QC#59804, ADD]

    // START 2022/10/25 E.Sanchez [QC#60661, ADD]
    /**
     * getDsContrBllgMtrPkByContrDtlPk
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDsContrBllgMtrPkByContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrBllgMtrPkByContrDtlPk", ssmPrm).getResultObject();
    }
    // END 2022/10/25 E.Sanchez [QC#60661, ADD]
}
