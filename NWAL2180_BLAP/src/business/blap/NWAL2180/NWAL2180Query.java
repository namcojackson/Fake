/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2180;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2180Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         T.Murai         Create          N/A
 * 2016/04/21   Fujitsu         M.Yamada        UPdate          QC#6660
 * 2016/05/30   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/07/20   Fujitsu         M.Yamada        Update          QC#10378
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 * 2016/08/24   Fujitsu         M.Yamada        Update          QC#13057
 * 2016/09/07   Fujitsu         M.Yamada        Update          QC#13256
 * 2016/09/14   Fujitsu         M.Yamada        Update          QC#14513
 * 2016/09/16   Fujitsu         M.Yamada        Update          QC#10375
 * 2016/09/14   Fujitsu         M.Yamada        Update          QC#14869
 * 2016/10/04   Fujitsu         M.Yamada        Update          QC#14940
 * 2016/12/20   Fujitsu         S.Iidaka        Update          QC#16141
 * 2017/03/15   Fujitsu         M.Yamada        Update          QC#17971
 * 2017/03/22   Fujitsu         M.Yamada        Update          QC#17592
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 * 2018/12/07   Fujitsu         K.Ishizuka      Update          QC#29484
 *</pre>
 */
public final class NWAL2180Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2180Query MY_INSTANCE = new NWAL2180Query();

    /**
     * Private constructor
     */
    private NWAL2180Query() {
        super();
    }

    /**
     * Get the NWAL2180Query instance.
     * @return NWAL2180Query instance
     */
    public static NWAL2180Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @param target target area J/B
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdateAcsry(NWAL2180CMsg bizMsg, int index, String glblCmpyCd, String target) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        if ("B".equals(target)) {
            NWAL2180_BCMsg bBizMsg = bizMsg.B.no(index);
            params.put("lineNum", bBizMsg.cpoDtlLineNum_B.getValue());
            params.put("lineSubNum", bBizMsg.cpoDtlLineSubNum_B.getValue());
            params.put("dsImptSvcAddlBasePk", bBizMsg.cpoSvcAddlBasePrcPk_B.getValue());
        } else {
            NWAL2180_JCMsg jBizMsg = bizMsg.J.no(index);
            params.put("lineNum", jBizMsg.cpoDtlLineNum_J.getValue());
            params.put("lineSubNum", jBizMsg.cpoDtlLineSubNum_J.getValue());
            params.put("dsImptSvcAddlBasePk", jBizMsg.cpoSvcAddlBasePrcPk_J.getValue());
        }
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        params.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);

        String stmtId = "getImptAcsry";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListTp(String prcCatgCd, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", prcCatgCd);

        return getSsmEZDClient().queryObject("getPrcListTp", params);
    }

    /**
     * Get Update Additional Charge
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdateAddChrg(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("lineNum", bizMsg.C.no(index).cpoDtlLineNum_C.getValue());
        params.put("lineSubNum", bizMsg.C.no(index).cpoDtlLineSubNum_C.getValue());
        params.put("dsImptSvcAddlChrgPk", bizMsg.C.no(index).cpoSvcAddlChrgPrcPk_C.getValue());

        String stmtId = "getImptAddChrg";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * Get SvcPkg21
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg21(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(index).prcCatgCd_A.getValue());
        params.put("modelId", bizMsg.A.no(index).mdlId_A.getValue());
        params.put("modelNm", bizMsg.A.no(index).t_MdlNm_A.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg21", params);
    }

    /**
     * getSvcPkg21
     * @param bizMsg NWAL2180CMsg
     * @param rBizMsg NWAL2180_RCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg21(NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("modelId", rBizMsg.mdlId_R.getValue());
        params.put("modelNm", rBizMsg.t_MdlNm_R.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg21", params);
    }

    /**
     * Get SvcPkg21 Fleet
     * @param bizMsg NWAL2180CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg21FL(NWAL2180CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(0).prcCatgCd_A.getValue());
        params.put("modelId", mdlList);
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg21FL", params);
    }

    /**
     * Get SvcPkg07
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg07(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(index).prcCatgCd_A.getValue());
        params.put("modelId", bizMsg.A.no(index).mdlId_A.getValue());
        params.put("modelNm", bizMsg.A.no(index).t_MdlNm_A.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg07", params);
    }

    /**
     * getSvcPkg07
     * @param bizMsg NWAL2180CMsg
     * @param rBizMsg NWAL2180_RCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg07(NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("modelId", rBizMsg.mdlId_R.getValue());
        params.put("modelNm", rBizMsg.t_MdlNm_R.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg07", params);
    }

    /**
     * Get SvcPkg07 Fleet
     * @param bizMsg NWAL2180CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg07FL(NWAL2180CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(0).prcCatgCd_A.getValue());
        params.put("modelId", mdlList);
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg07FL", params);
    }

    /**
     * Get SvcPkg
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(index).prcCatgCd_A.getValue());
        params.put("modelId", bizMsg.A.no(index).mdlId_A.getValue());
        params.put("modelNm", bizMsg.A.no(index).t_MdlNm_A.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg", params);
    }

    /**
     * getSvcPkg
     * @param bizMsg NWAL2180CMsg
     * @param rBizMsg NWAL2180_RCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg(NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("modelId", rBizMsg.mdlId_R.getValue());
        params.put("modelNm", rBizMsg.t_MdlNm_R.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkg", params);
    }

    /**
     * Get SvcPkg FLeet
     * @param bizMsg NWAL2180CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkgFL(NWAL2180CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(0).prcCatgCd_A.getValue());
        params.put("modelId", mdlList);
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getSvcPkgFL", params);
    }

    /**
     * Get PrcListStru
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListStruForAupplyAgreement(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.R.no(index).prcCatgCd_R.getValue());

        return getSsmEZDClient().queryObjectList("getPrcListStru", params);
    }

    /**
     * Get TieredPrcListBox
     * @param aBizMsg NWAL2180_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierdByCatg(NWAL2180_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());

        return getSsmEZDClient().queryObjectList("getTierdByCatg", params);
    }

    /**
     * Get HeaderInfo
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrRsnNm(NWAL2180CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rsnCd", bizMsg.manContrOvrdRsnCd.getValue());

        return getSsmEZDClient().queryObjectList("getContrRsnNm", params);
    }

    /**
     * Get HeaderInfo
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrNum(NWAL2180CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());

        return getSsmEZDClient().queryObjectList("getDsContrNum", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPricingPkList(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("pkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue());

        return getSsmEZDClient().queryObjectList("getSvcPricingPkList", params);
    }

    /**
     * Get BLLG_CYCLE_MTH_AOT
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getbllgCycle(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bllgCycleCd", bizMsg.baseBllgCycleCd.getValue());
        params.put("effFromDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getbllgCycle", params);
    }

    /**
     * Get SvcPricingInfo
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseNm(String mdseCd, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseNm", params);
    }

    /**
     * Get SvcPricingInfo
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcCatgNm(String prcCatgCd, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", prcCatgCd);

        return getSsmEZDClient().queryObject("getPrcCatgNm", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlNm(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlId", bizMsg.A.no(index).mdlId_A.getValue());

        return getSsmEZDClient().queryObjectList("getMdlNm", params);
    }

    /**
     * Get mtrLb
     * @param mtrLbCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrNm(String mtrLbCd, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mtrLbCd", mtrLbCd);

        return getSsmEZDClient().queryObjectList("getMtrNm", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierInfo_BandSearch(NWAL2180CMsg bizMsg) {
        NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(bizMsg.xxNum_Z.getValueInt());
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(bizMsg.xxNum_A.getValueInt());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", zBizMsg.prcCatgCd_Z.getValue());
        params.put("modelId", zBizMsg.mdlId_Z.getValue().toString());
        params.put("modelNm", zBizMsg.t_MdlNm_Z.getValue());
        params.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue().toString());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrBLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        params.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_Z.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo_BandSearch", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierInfo_BandSearchConfig(NWAL2180CMsg bizMsg) {
        NWAL2180_UCMsg uBizMsg = bizMsg.U.no(bizMsg.xxNum_Z.getValueInt());
        NWAL2180_RCMsg rBizMsg = bizMsg.R.no(bizMsg.xxNum_A.getValueInt());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", uBizMsg.prcCatgCd_U.getValue());
        params.put("modelId", uBizMsg.mdlId_U.getValue().toString());
        params.put("modelNm", uBizMsg.t_MdlNm_U.getValue());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue().toString());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrBLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        params.put("prcListBandDescTxt", uBizMsg.prcListBandDescTxt_U.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo_BandSearch", params);
    }

    /**
     * Get SvcPricingInfo
     * @param glblMsg NWAL2180SMsg
     * @param zGlblMsg NWAL2180_ZSMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierInfo(NWAL2180SMsg glblMsg, NWAL2180_ZSMsg zGlblMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(zGlblMsg.prcCatgCd_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(zGlblMsg.mdlId_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(zGlblMsg.t_MdlNm_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(zGlblMsg.prcMtrPkgPk_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(glblMsg.prcSvcPlnTpCd)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(glblMsg.prcSvcContrTpCd)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(zGlblMsg.bllgMtrLbCd_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(glblMsg.prcBaseDt)) {
            return null;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("priceList", zGlblMsg.prcCatgCd_Z.getValue());
        params.put("modelId", zGlblMsg.mdlId_Z.getValue().toString());
        params.put("modelNm", zGlblMsg.t_MdlNm_Z.getValue());
        params.put("prcMtrPkgPk", zGlblMsg.prcMtrPkgPk_Z.getValue().toString());
        params.put("prcSvcPlnTpCd", glblMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", glblMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrLbCd", zGlblMsg.bllgMtrLbCd_Z.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("prcBaseDt", glblMsg.prcBaseDt.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcConfName(NWAL2180CMsg bizMsg, int index) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("coveredItem", bizMsg.B.no(index).mdseCd_B.getValue());
        params.put("prcList", bizMsg.B.no(index).addlBasePrcCatgCd_B.getValue());
        params.put("itemCd", PRC_QLFY_TP.ITEM_CODE);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getPrcConfName", params);
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2180CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMtrLb(NWAL2180CMsg bizMsg) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", bizMsg.A.no(index).mdlId_A.getValue().toString());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getMtrLb", params);
    }

    public S21SsmEZDResult getMtrLbConfig(NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue().toString());
        params.put("mdlId", rBizMsg.mdlId_R.getValue().toString());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getMtrLb", params);
    }

    /**
     * <pre>
     * @param bizMsg       NWAL2180SMsg
     * @param mdlIdList     List<BigDecimal>
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult isInMtrDB(NWAL2180CMsg bizMsg, List<BigDecimal> mdlIdList) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", mdlIdList);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("isInMtrDB", params);
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2180CMsg
     * @param mdlIdList     List<BigDecimal>
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMtrLbFL(NWAL2180CMsg bizMsg, List<BigDecimal> mdlIdList) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", mdlIdList);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getMtrLbFL", params);
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2180CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult cntRentalOrder(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ordCatgCtxTpCd", "RENTAL_ORDER");
        params.put("xxScrItem50Txt", bizMsg.xxScrItem50Txt.getValue());

        String stmtId = "cntImptRentalOrder";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2180CMsg
     * @param zBizMsg       NWAL2180_ZCMsg
     * @param mdlList       List<BigDecimal>
     * @param glblCmpyCd    glblCmpyCd
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getTierInfoForFleet(NWAL2180CMsg bizMsg, NWAL2180_ZCMsg zBizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(zBizMsg.prcCatgCd_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(zBizMsg.prcMtrPkgPk_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.prcSvcPlnTpCd)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.prcSvcContrTpCd)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(zBizMsg.bllgMtrLbCd_Z)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.prcBaseDt)) {
            return null;
        }
        if (mdlList.size() == 0) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("priceList", zBizMsg.prcCatgCd_Z.getValue());
        params.put("mdlId", mdlList);
        params.put("prcMtrPkgPk", zBizMsg.prcMtrPkgPk_Z.getValue().toString());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfoForFleet", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2180CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInitDataFromCpoSvc(String glblCmpyCd, NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("refCpoOrdNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("ordSrcRefNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("cpoSvcLineNum", bizMsg.dsImptSvcLineNum.getValue());
        params.put("dsImptSvcLineNum", bizMsg.dsImptSvcLineNum.getValue());

        String stmtId = "getImptInitDataFromDsImpt";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2180CMsg
     * @param aBizMsg       NWAL2180_ACMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getAnnualPrc(String glblCmpyCd, NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("annual", PRC_RATE_TP.ANNUAL);
        params.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        params.put("mdlId", aBizMsg.mdlId_A.getValue().toString());
        params.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("termMthNum", aBizMsg.shpgIntvlMthNum_A.getValue().toString());

        return getSsmEZDClient().queryObjectList("getAnnualPrc", params);
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2180CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBllgCycleList(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getBllgCycleList", params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCpoSvcDtl(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());
        params.put("dsImptSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());

        String stmtId = "getImptCpoSvcDtl";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPrcHdrForFleet(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());
        params.put("cpoOrdNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);

        String stmtId = "getImptSvcPrcHdrForFleet";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcUsgPrc(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());
        params.put("dsImptSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());

        String stmtId = "getImptSvcUsgPrc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcAddlBasePrc(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());

        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("svcPrcCatgAubc", SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);

        String stmtId = "getImptSvcAddlBasePrc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcAddlChrgPrc(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());

        params.put("shpgStsArrived", SHPG_STS.ARRIVED);

        String stmtId = "getImptSvcAddlChrgPrc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcConfigRef(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());

        return getSsmEZDClient().queryObjectList("getSvcConfigRef", params);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultMdlSvcPrc(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getDefaultMdlSvcPrc", params);
    }

    /**
     * getPrcRateTpCd
     * @param bizMsg NWAL2180CMsg
     * @param aBizMsg NWAL2180_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcRateTpCd(NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", ZYPDateUtil.getSalesDate());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.shpgIntvlMthNum.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getPrcRateTpCd", mapParam);
    }

    public S21SsmEZDResult getBandBasePrc(NWAL2180CMsg bizMsg, int ixZ) {
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(bizMsg.xxNum_A.getValueInt());
        NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);

        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcListStruTpService", PRC_LIST_STRU_TP.SERVICE);
        mapParam.put("prcListStruTpServiceTiers", PRC_LIST_STRU_TP.SERVICE_TIERS);
        mapParam.put("prcListStruTpSupplyProgram", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);

        mapParam.put("mdlId", zBizMsg.mdlId_Z.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());
        mapParam.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_Z.getValue());

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());

        return getSsmEZDClient().queryObject("getBandBasePrc", mapParam);
    }

    public S21SsmEZDResult getBandBasePrcConfig(NWAL2180CMsg bizMsg, int ixU) {
        NWAL2180_RCMsg rBizMsg = bizMsg.R.no(bizMsg.xxNum_A.getValueInt());
        NWAL2180_UCMsg zBizMsg = bizMsg.U.no(ixU);

        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcListStruTpService", PRC_LIST_STRU_TP.SERVICE);
        mapParam.put("prcListStruTpServiceTiers", PRC_LIST_STRU_TP.SERVICE_TIERS);
        mapParam.put("prcListStruTpSupplyProgram", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);

        mapParam.put("mdlId", zBizMsg.mdlId_U.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());
        mapParam.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_U.getValue());

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());

        return getSsmEZDClient().queryObject("getBandBasePrc", mapParam);
    }

    public S21SsmEZDResult getDefaultMdlSvcPrcByQlfy(NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        params.put("mdlId", aBizMsg.mdlId_A.getValue());

        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());

        params.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        params.put("prcListStruTpService", PRC_LIST_STRU_TP.SERVICE);
        params.put("prcListStruTpServiceTiers", PRC_LIST_STRU_TP.SERVICE_TIERS);
        params.put("prcListStruTpSupplyProgram", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);

        params.put("prcCtxTpServicePricing", PRC_CTX_TP.SERVICE_PRICING);

        return getSsmEZDClient().queryObjectList("getDefaultMdlSvcPrcByQlfy", params);
    }

    public S21SsmEZDResult getPrcListStruTp(String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);

        return getSsmEZDClient().queryObject("getPrcListStruTp", params);
    }

    /**
     * getDefaultBandForService
     * @param bizMsg NWAL2180CMsg
     * @param aBizMsg NWAL2180_ACMsg
     * @param zBizMsg NWAL2180_ZCMsg
     * @param prcListBandDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultBandForService(//
            NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg, NWAL2180_ZCMsg zBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        mapParam.put("cpc", PRC_RATE_TP.CPC);
        mapParam.put("annual", PRC_RATE_TP.ANNUAL);

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForService", mapParam);
    }

    /**
     * getDefaultBandForService
     * @param bizMsg NWAL2180CMsg
     * @param rBizMsg NWAL2180_RCMsg
     * @param uBizMsg NWAL2180_UCMsg
     * @param prcListBandDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultBandForService(//
            NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg, NWAL2180_UCMsg uBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        mapParam.put("cpc", PRC_RATE_TP.CPC);
        mapParam.put("annual", PRC_RATE_TP.ANNUAL);

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForService", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForServiceTiers(//
            NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg, NWAL2180_ZCMsg zBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForServiceTiers", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForServiceTiers(//
            NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg, NWAL2180_UCMsg uBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForServiceTiers", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForSupplyProgram(//
            NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg, NWAL2180_ZCMsg zBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForSupplyProgram", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForSupplyProgram(//
            NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg, NWAL2180_UCMsg uBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.shpgIntvlMthNum.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForSupplyProgram", mapParam);
    }

    public S21SsmEZDResult getContractIndicatorList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getContractIndicatorList", params);
    }

    public S21SsmEZDResult getCountPrcListBandFromDescTxt(String prcListBandDescTxt) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getCountPrcListBandFromDescTxt", params);
    }

    public S21SsmEZDResult getSplyAgmtPlnNm(NWAL2180_ACMsg aBizMsg, NWAL2180_ZCMsg zBizMsg, NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", aBizMsg.mdlId_A);
        params.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A);
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd);
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd);
        params.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z);
        params.put("prcCatgCd", aBizMsg.prcCatgCd_A);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("termMth", bizMsg.shpgIntvlMthNum);
        params.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_Z);

        return getSsmEZDClient().queryObjectList("getSplyAgmtPlnNm", params);
    }

    public S21SsmEZDResult getSplyAgmtPlnNm(//
            NWAL2180_RCMsg rBizMsg, NWAL2180_UCMsg uBizMsg, NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", rBizMsg.mdlId_R);
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R);
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd);
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd);
        params.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U);
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("termMth", bizMsg.shpgIntvlMthNum);
        params.put("prcListBandDescTxt", uBizMsg.prcListBandDescTxt_U);

        return getSsmEZDClient().queryObjectList("getSplyAgmtPlnNm", params);
    }

    public S21SsmEZDResult getSvcPrcHdrByConfig(NWAL2180CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcDtlPk", bizMsg.cpoSvcDtlPk.getValue());
        params.put("cpoOrdNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("configCatgOutBound", CONFIG_CATG.OUTBOUND);
        params.put("configTpCd", CONFIG_TP.ADD_TO_CONFIG); // 2018/12/07 S21_NA#29484 Add

        String stmtId = "getImptSvcPrcHdrByConfig";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    public S21SsmEZDResult getAnnualPrc(String glblCmpyCd, NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("annual", PRC_RATE_TP.ANNUAL);
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("mdlId", rBizMsg.mdlId_R.getValue().toString());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("termMthNum", rBizMsg.shpgIntvlMthNum_R.getValue().toString());

        return getSsmEZDClient().queryObjectList("getAnnualPrc", params);
    }

    public S21SsmEZDResult getTierdByCatg(NWAL2180_RCMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", aBizMsg.prcCatgCd_R.getValue());

        return getSsmEZDClient().queryObjectList("getTierdByCatg", params);
    }

    public S21SsmEZDResult getPrcRateTpCd(NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", ZYPDateUtil.getSalesDate());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.shpgIntvlMthNum.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getPrcRateTpCd", mapParam);
    }

    public S21SsmEZDResult getBasePrcForNonMeter(NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.shpgIntvlMthNum.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getBasePrcForNonMeter", mapParam);
    }

}
