/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0470;

import static business.blap.NSAL0470.constant.NSAL0470Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_CONTR_STS_VTMsgArray;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Complete Contract
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Yamada        Create          N/A
 * 2016/02/16   Hitachi         T.Aoyagi        Update          QC3840
 * 2016/04/21   Hitachi         A.Kohinata      Update          QC1088
 * 2017/08/04   Hitachi         K.Kasai         Update          QC18882
 * 2017/10/24   Hitachi         U.Kim           Update          QC21865
 * 2018/04/04   Hitachi         K.Kitachi       Update          QC#21074
 * 2018/05/10   Hitachi         U.Kim           Update          QC#25482
 * 2018/05/11   CITS            T.Wada          Update          QC#24989
 * 2018/05/24   Hitachi         T.Tomita        Update          QC#24989
 * 2019/01/15   Hitachi         K.Kitachi       Update          QC#30080
 *</pre>
 */
public final class NSAL0470Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSAL0470Query MY_INSTANCE = new NSAL0470Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL0470Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL0470Query instance.
     * </pre>
     * @return NSAL0470Query instance
     */
    public static NSAL0470Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * findContrVldRslt
     * @param cMsg NSAL0470CMsg
     * @param sMsg NSAL0470SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findContrVldRslt(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
        Map<String, Object> queryParam = createFindCondMap(cMsg);
        queryParam.put("rowNum", sMsg.B.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("findContrVldRslt", queryParam, sMsg.B);
    }

    /**
     * createFindCondMap
     * @param cMsg NSAL0470CMsg cMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> createFindCondMap(NSAL0470CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrNum", cMsg.dsContrNum.getValue());
        queryParam.put("dsContrVldStsCd", getVldStsList(cMsg));
        // START 2018/04/04 K.Kitachi [QC#21074, ADD]
        Set<String> vldErrWarnStsSet = new LinkedHashSet<String>();
        vldErrWarnStsSet.add(DS_CONTR_VLD_STS.ERROR);
        vldErrWarnStsSet.add(DS_CONTR_VLD_STS.WARNING);
        queryParam.put("dsContrVldStsCdIsErrWarn", vldErrWarnStsSet);
        // END 2018/04/04 K.Kitachi [QC#21074, ADD]
        queryParam.put("dsContrVldPk", cMsg.dsContrVldPk_H3.getValue());
        queryParam.put("serNum", cMsg.serNum.getValue());
        return queryParam;
    }

    private Set<String> getVldStsList(NSAL0470CMsg cMsg) {
        Set<String> vldStsSet = new LinkedHashSet<String>();

        if (hasValue(cMsg.xxChkBox_H1)) {
            vldStsSet.add(DS_CONTR_VLD_STS.SUCCESS);
        }
        if (hasValue(cMsg.xxChkBox_H2)) {
            vldStsSet.add(DS_CONTR_VLD_STS.ERROR);
        }
        if (hasValue(cMsg.xxChkBox_H3)) {
            vldStsSet.add(DS_CONTR_VLD_STS.WARNING);
        }
        if (hasValue(cMsg.xxChkBox_H4)) {
            vldStsSet.add(DS_CONTR_VLD_STS.NOT_VALIDATED);
        }
        return vldStsSet;
    }

    /**
     * countErrResult
     * @param cMsg NSAL0470CMsg
     * @param sMsg NSAL0470SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countErrResult(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrNum", cMsg.dsContrNum.getValue());
        queryParam.put("error", DS_CONTR_VLD_STS.ERROR);
        queryParam.put("notValidated", DS_CONTR_VLD_STS.NOT_VALIDATED);

        return getSsmEZDClient().queryObjectList("countErrResult", queryParam);
    }

    /**
     * S21SsmEZDResult
     * @param cMsg NSAL0470CMsg
     * @return NSAL0470CMsg
     */
    public S21SsmEZDResult getContrVld(NSAL0470CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getContrVld", queryParam);
    }

    /**
     * getUpdDsContrDtl
     * @param cMsg NSAL0470CMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdDsContrDtl(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("draft", DS_CONTR_CTRL_STS.DRAFT);
        queryParam.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        queryParam.put("signed", DS_CONTR_CTRL_STS.SINGED);
        //END 2017/07/26 K.Kasai [QC#18882,ADD]

        // 2018/05/11 QC#24989 Add Start
        // Del Start 2018/05/24 QC#24989
//        queryParam.put("instl", SVC_MACH_MSTR_STS.INSTALLED);
//        queryParam.put("w4r", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        // Del End 2018/05/24 QC#24989
        queryParam.put("fleet", DS_CONTR_DTL_TP.FLEET);
        queryParam.put("agg", DS_CONTR_DTL_TP.AGGREGATE);
        // 2018/05/11 QC#24989 Add End

        return getSsmEZDClient().queryObjectList("getUpdDsContrDtl", queryParam);
    }

    /**
     * getUpdDsContrBllgMtr
     * @param cMsg NSAL0470CMsg
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdDsContrBllgMtr(NSAL0470CMsg cMsg, BigDecimal dsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrDtlPk", dsContrDtlPk);
        queryParam.put("draft", DS_CONTR_CTRL_STS.DRAFT);
        queryParam.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        queryParam.put("signed", DS_CONTR_CTRL_STS.SINGED);
        //END 2017/07/26 K.Kasai [QC#18882,ADD]

        return getSsmEZDClient().queryObjectList("getUpdDsContrBllgMtr", queryParam);
    }

    /**
     * getUpdDsContrPrcEffBase
     * @param cMsg NSAL0470CMsg
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdDsContrPrcEffBase(NSAL0470CMsg cMsg, BigDecimal dsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrDtlPk", dsContrDtlPk);
        queryParam.put("draft", DS_CONTR_CTRL_STS.DRAFT);
        queryParam.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        queryParam.put("signed", DS_CONTR_CTRL_STS.SINGED);
        //END 2017/07/26 K.Kasai [QC#18882,ADD]

        return getSsmEZDClient().queryObjectList("getUpdDsContrPrcEffBase", queryParam);
    }

    /**
     * getUpdDsContrPrcEffUsg
     * @param cMsg NSAL0470CMsg
     * @param dsContrBllgMtrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdDsContrPrcEffUsg(NSAL0470CMsg cMsg, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        queryParam.put("draft", DS_CONTR_CTRL_STS.DRAFT);
        queryParam.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        queryParam.put("qaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        queryParam.put("signed", DS_CONTR_CTRL_STS.SINGED);
        //END 2017/07/26 K.Kasai [QC#18882,ADD]

        return getSsmEZDClient().queryObjectList("getUpdDsContrPrcEffUsg", queryParam);
    }

    /**
     * findErrContrVldRslt
     * @param cMsg NSAL0470CMsg
     * @param sMsg NSAL0470SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findErrContrVldRslt(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrNum", cMsg.dsContrNum.getValue());

        Set<String> vldStsSet = new LinkedHashSet<String>();
        vldStsSet.add(DS_CONTR_VLD_STS.ERROR);
        vldStsSet.add(DS_CONTR_VLD_STS.NOT_VALIDATED);

        queryParam.put("dsContrVldStsCd", vldStsSet);
        queryParam.put("rowNum", sMsg.B.length());

        return getSsmEZDClient().queryObjectList("findErrContrVldRslt", queryParam);
    }

    /**
     * getContrVldRsltForRemove
     * @param cMsg NSAL0470CMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrVldRsltForRemove(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrPk", dsContrPk);

        return getSsmEZDClient().queryObjectList("getContrVldRsltForRemove", queryParam);
    }

    /**
     * getDoNotOverrideVld
     * @param cMsg NSAL0470CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDoNotOverrideVld(NSAL0470CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrNum", cMsg.dsContrNum.getValue());
        queryParam.put("error", DS_CONTR_VLD_STS.ERROR);
        queryParam.put("notValidated", DS_CONTR_VLD_STS.NOT_VALIDATED);

        return getSsmEZDClient().queryObjectList("getDoNotOverrideVld", queryParam);
    }

    // START 2016/02/16 T.Aoyagi [QC3840, ADD]
    /**
     * @param dsContrPk BigDecimal
     * @return String
     */
    public String getDsContrStsCd(BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg inMsg = new DS_CONTR_STS_VTMsg();
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("dsContrPk01", dsContrPk);
        inMsg.setSQLID("002");
        DS_CONTR_STS_VTMsgArray dsContrStsVTMsgArray = (DS_CONTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (dsContrStsVTMsgArray.getValidCount() > 0) {
            return dsContrStsVTMsgArray.no(0).dsContrCtrlStsCd.getValue();
        }
        return "";
    }
    // END 2016/02/16 T.Aoyagi [QC3840, ADD]

    // START 2016/04/21 [QC1088, ADD]
    /**
     * getDsContr
     * @param cMsg NSAL0470CMsg
     * @return Map<String, Object>
     */
    public S21SsmEZDResult getDsContr(NSAL0470CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrNum", cMsg.dsContrNum.getValue());

        return getSsmEZDClient().queryObject("getDsContr", queryParam);
    }

    /**
     * getContrRepCnt
     * @param cMsg NSAL0470CMsg
     * @return BigDecimal
     */
    public BigDecimal getContrRepCnt(NSAL0470CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String svcOrgFuncRoleTpCdRep = ZYPCodeDataUtil.getVarCharConstValue(SVC_ORG_FUNC_ROLE_TP_REP, glblCmpyCd);
        String svcOrgFuncRoleTpCdSupv = ZYPCodeDataUtil.getVarCharConstValue(SVC_ORG_FUNC_ROLE_TP_SUPERVR, glblCmpyCd);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        queryParam.put("maxDate", MAX_DATE);
        queryParam.put("psnCd", cMsg.contrAdminPsnCd.getValue());
        queryParam.put("svcOrgFuncRoleTpCdRep", svcOrgFuncRoleTpCdRep);
        queryParam.put("svcOrgFuncRoleTpCdSupv", svcOrgFuncRoleTpCdSupv);

        return (BigDecimal) getSsmEZDClient().queryObject("getContrRepCnt", queryParam).getResultObject();
    }

    /**
     * getDsContrForWf
     * @param cMsg NSAL0470CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrForWf(NSAL0470CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        queryParam.put("maxDate", MAX_DATE);
        queryParam.put("dsContrNum", cMsg.dsContrNum.getValue());

        return getSsmEZDClient().queryObject("getDsContrForWf", queryParam);
    }

    /**
     * getContrVldRsltForWf
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrVldRsltForWf(BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("error", DS_CONTR_VLD_STS.ERROR);
        queryParam.put("notValidated", DS_CONTR_VLD_STS.NOT_VALIDATED);

        return getSsmEZDClient().queryObjectList("getContrVldRsltForWf", queryParam);
    }
    // END 2016/04/21 [QC1088, ADD]

    //START 2017/10/24 U.Kim [QC21865, ADD]
    /**
     * getOrdSchInfo
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdSchInfo(BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("machine", SVC_MACH_TP.MACHINE);
        queryParam.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        queryParam.put("dsContrCatgCdWty", DS_CONTR_CATG.WARRANTY);
        return getSsmEZDClient().queryObjectList("getOrdSchInfo", queryParam);
    }
    //END 2017/10/24 U.Kim [QC21865, ADD]
    // START 2018/05/10 U.Kim [QC#25482, ADD]
    
    public S21SsmEZDResult getSvcContrBllg(BigDecimal dsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrDtlPk", dsContrDtlPk);
        queryParam.put("invTpCd", INV_TP.CREDIT_MEMO);
        return getSsmEZDClient().queryObjectList("getSvcContrBllg", queryParam);
    }

    public SVC_CONTR_BASE_BLLGTMsgArray getSvcContrBaseBllgForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_BASE_BLLGTMsg inMsg = new SVC_CONTR_BASE_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_BASE_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_MTR_BLLGTMsgArray getSvcContrMtrBllgForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_MTR_BLLGTMsg inMsg = new SVC_CONTR_MTR_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_MTR_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_XS_MTR_BLLGTMsgArray getSvcContrXsMtrBllgForUpdateNoWait(BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_XS_MTR_BLLGTMsg inMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_XS_MTR_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_ADDL_CHRG_BLLGTMsgArray getSvcContrAddlChrgBllgForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_ADDL_CHRG_BLLGTMsg inMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_ADDL_CHRG_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdForUpdateNoWait(BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }
    // START 2018/05/10 U.Kim [QC#25482, ADD]

    // START 2019/01/25 K.Kitachi [QC#30080, ADD]
    /**
     * getDsContrRnwEscl
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrRnwEscl(BigDecimal dsContrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("contrUplftTpCd", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        queryParam.put("dsContrMachLvlNum", LVL_NUM_1);
        return getSsmEZDClient().queryObjectList("getDsContrRnwEscl", queryParam);
    }

    /**
     * getDsContrRnwEsclForUpdateNoWait
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public DS_CONTR_RNW_ESCLTMsg getDsContrRnwEsclForUpdateNoWait(BigDecimal dsContrRnwEsclPk) {
        DS_CONTR_RNW_ESCLTMsg inMsg = new DS_CONTR_RNW_ESCLTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
        return (DS_CONTR_RNW_ESCLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }
    // END 2019/01/25 K.Kitachi [QC#30080, ADD]
}

