/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0700;

import static business.blap.NSAL0700.constant.NSAL0700Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSAL0700.common.NSAL0700CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract On Billing Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 * 2018/08/26   Hitachi         K.Kim           Update          QC#22987
 * 2019/07/18   Hitachi         A.Kohinata      Update          QC#51706
 *</pre>
 */
public final class NSAL0700Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0700Query INSTANCE = new NSAL0700Query();

    /**
     * Constructor.
     */
    private NSAL0700Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0700Query
     */
    public static NSAL0700Query getInstance() {
        return INSTANCE;
    }

    // mod start 2016/11/28 CSA QC#4210
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0700_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0700_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }
    // mod end 2016/11/28 CSA QC#4210
    /**
     * get DS_CONTR_PRC_EFF_PK
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @param usgChrgFlg String
     * @param bllgHldFlg
     * @return S21SsmEZDResult
     */
    // START 2017/09/04 K.Kojima [QC#20816,MOD]
    // public S21SsmEZDResult getDsContrPrcEffPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String usgChrgFlg) {
    public S21SsmEZDResult getDsContrPrcEffPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String usgChrgFlg, String bllgHldFlg) {
    // END 2017/09/04 K.Kojima [QC#20816,MOD]
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk.toString());
        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk.toString());
        }
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // ssmParam.put("bllgHldFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("bllgHldFlg", NSAL0700CommonLogic.changeBllgHldFLg(bllgHldFlg));
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
        List<String> dsContrCtrlStsList = new ArrayList<String>();
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.CANCELLED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.TERMINATED);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsList);
        if (ZYPCommonFunc.hasValue(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", baseChrgFlg);
        }
        if (ZYPCommonFunc.hasValue(usgChrgFlg)) {
            ssmParam.put("usgChrgFlg", usgChrgFlg);
        }
        return getSsmEZDClient().queryObjectList("getDsContrPrcEffPk", ssmParam);
    }

    // START 2017/09/04 K.Kojima [QC#20816,ADD]
    /**
     * getDsContrDtlPkList
     * @param glblCmpyCd
     * @param dsContrPk
     * @param bllgHldFlg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlPkList(String glblCmpyCd, BigDecimal dsContrPk, String bllgHldFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("bllgHldFlg", NSAL0700CommonLogic.changeBllgHldFLg(bllgHldFlg));
        List<String> dsContrCtrlStsList = new ArrayList<String>();
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.CANCELLED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.TERMINATED);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsList);
        return getSsmEZDClient().queryObjectList("getDsContrDtlPkList", ssmParam);
    }

    /**
     * getDsContrBllgMtrPkList
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @param bllgHldFlg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrBllgMtrPkList(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgHldFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("bllgHldFlg", NSAL0700CommonLogic.changeBllgHldFLg(bllgHldFlg));
        List<String> dsContrCtrlStsList = new ArrayList<String>();
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.CANCELLED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.TERMINATED);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsList);
        return getSsmEZDClient().queryObjectList("getDsContrBllgMtrPkList", ssmParam);
    }
    // END 2017/09/04 K.Kojima [QC#20816,ADD]
    // START 2018/08/26 [QC#22987, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedUsgChrg", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedCrRebil(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedCrRebil", param).getResultObject();
    }
    // END 2018/08/26 [QC#22987, ADD]

    // add start 2019/07/18 QC#51706
    /**
     * getSvcMemoForManBllgHld
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getSvcMemoForManBllgHld(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG_FOR_MAN_BLLG_HLD);
        param.put("svcMemoTpCd", SVC_MEMO_TP_FOR_MAN_BLLG_HLD);
        param.put("svcMemoRsnCd", SVC_MEMO_RSN_FOR_MAN_BLLG_HLD);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcMemoForManBllgHld", param).getResultObject();
    }
    // add end 2019/07/18 QC#51706
}
