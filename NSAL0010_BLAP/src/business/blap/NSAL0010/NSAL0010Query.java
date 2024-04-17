/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0010;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSAL0010.constant.NSAL0010Constant;
import business.db.DS_CTAC_PNTTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         T.Tomita        Update          QC#1794
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/02/25   Hitachi         T.Tomita        Update          QC#2690
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4888
 * 2016/04/08   Hitachi         M.Gotou         Update          QC#4905
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6218
 * 2016/04/20   Hitachi         T.Tomita        Update          QC#4892
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#6672
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#7832
 * 2016/07/22   Hitachi         T.Tomita        Update          QC#11161
 * 2016/08/08   Hitachi         T.Tomita        Update          QC#7794
 * 08/10/2016   CSAI            Y.Imazu         Update          QC#12496
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/10/16   Hitachi         T.Tomita        Update          QC#21563
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#19932
 * 2018/06/05   Hitachi         T.Tomita        Update          QC#23428
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 * 2021/08/17   CITS            R.Cabral        Update          QC#59010
 * 2023/07/31   Hitachi         Y.nagasawa      Update          QC#61632
 * 2023/10/06   Hitachi         K.Ishizuka      Update          QC#54186
 * 2023/12/19   Hitachi         T.Fukuta        Update          CSA-QC#61841
 *</pre>
 */
public final class NSAL0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0010Query INSTANCE = new NSAL0010Query();

    /**
     * Constructor.
     */
    private NSAL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0010Query
     */
    public static NSAL0010Query getInstance() {
        return INSTANCE;
    }

    /**
     * get SvcMachMstr
     * @param bizMsg NSAL0010CMsg
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstr(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getSvcMachMstr", ssmParam, cMsg);
    }

    /**
     * get SvcMachMstr
     * @param bizMsg NSAL0010CMsg
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachConfig(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getMachConfig", ssmParam, aSMsgArray);
    }

    /**
     * get SvcMachMstr
     * @param bizMsg NSAL0010CMsg
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachCtacPsn(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_CSMsgArray cSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSvcMachCtacPsn", ssmParam, cSMsgArray);
    }

    public S21SsmEZDResult getSvcMachCtacPsnIncTerm(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_CSMsgArray cSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSvcMachCtacPsnIncTerm", ssmParam, cSMsgArray);
    }

    /**
     * get SvcMachMstr
     * @param bizMsg NSAL0010CMsg
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcNonPrfTech(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_ESMsgArray eSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSvcNonPrfTech", ssmParam, eSMsgArray);
    }

    /**
     * get Sales History
     * @param bizMsg NSAL0010CMsg
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSlsHistory(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_GSMsgArray gSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSlsHistory", ssmParam, gSMsgArray);
    }
    
    public S21SsmEZDResult getIBHistory(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_ISMsgArray iSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getIbHist", ssmParam, iSMsgArray);
    }
    
    public S21SsmEZDResult getContrSmry(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_JSMsgArray jSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrSmry", ssmParam, jSMsgArray);
    }
    
    public S21SsmEZDResult getSvcCallHist(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_KSMsgArray kSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSvcCallHist", ssmParam, kSMsgArray);
    }
    
    public S21SsmEZDResult getInvHist(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_LSMsgArray lSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getInvHist", ssmParam, lSMsgArray);
    }

    /**
     * get Solution
     * @param bizMsg NSAL0010CMsg
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSolution(NSAL0010CMsg bizMsg, Map<String, Object> ssmParam, NSAL0010_BSMsgArray bSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSolution", ssmParam, bSMsgArray);
    }

    /**
     * get CurrentVersion
     * @param ssmParam Map
     * @return BigDecimal
     */
    public BigDecimal getCurrentVersion(Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getCurrentVersion", ssmParam).getResultObject();
    }

    /**
     * <pre>
     * The existence check of POST Code
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param postCd postCd
     * @return true:exist / false:not exist
     */
    public boolean existsPost(String glblCmpyCd, String postCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("postCd", postCd);

        return (Integer) getSsmEZDClient().queryObject("cntPost", ssmParam).getResultObject() > 0;
    }

    /**
     * <pre>
     * get org nm
     * </pre>
     * @param orgLayerNum String
     * @param orgCd String
     * @return orgNm
     */
    public String getOrgNm(BigDecimal orgLayerNum, String orgCd) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgLayerNum", orgLayerNum);
        ssmParam.put("orgCd", orgCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("orgStruTp", ORG_STRU_TP.TERRITORY_STRUCTURE);

        return (String) getSsmEZDClient().queryObject("getOrgNm", ssmParam).getResultObject();
    }


    /**
     * getDsMdl
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsMdl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsMdl", ssmParam);
    }

    //QC1936
    /**
     * getMdlMdseRelnV
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlMdseRelnV(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMdlMdseRelnV", ssmParam);
    }

    /**
     * isRtrnAssetWh
     * @param glblCmpyCd String
     * @param whCd String
     * @return result
     */
    public boolean isRtrnAssetWh(String glblCmpyCd, String whCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("whCd", whCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
// START T.Nakamura [WDS#2957 MOD]
//        ssmParam.put("locRoleTpCd", LOC_ROLE_TP.RTRN_ASSET_WH);
        ssmParam.put("locRoleTpCd", LOC_ROLE_TP.RETURNED_ASSET_WAREHOUSE);
// E N D T.Nakamura [WDS#2957 MOD]

        S21SsmEZDResult res = getSsmEZDClient().queryObject("countRtrnAssetWh", ssmParam);
        if (res.isCodeNormal()) {
            int count = (Integer) res.getResultObject();
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * isActiveContract
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return result
     */
    public boolean isActiveContract(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("ACTV", DS_CONTR_DTL_STS.ACTIVE);
        ssmParam.put("W4FB", DS_CONTR_DTL_STS.WAITING_FOR_FINAL_BILL);
        S21SsmEZDResult res = getSsmEZDClient().queryObject("countActiveContract", ssmParam);
        if (res.isCodeNormal()) {
            int count = (Integer) res.getResultObject();
            if (count > 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * isDsAsstMstr
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param serNum String
     * @return boolean true/false
     */
    public boolean isDsAsstMstr(String glblCmpyCd, String mdseCd, String serNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("serNum", serNum);
        S21SsmEZDResult res = getSsmEZDClient().queryObject("countDsAsstMstr", ssmParam);
        if (res.isCodeNormal()) {
            int count = (Integer) res.getResultObject();
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    // START 2015/12/10 T.Tomita [QC1794, MOD]
    public Map<String, Object> getDsContrDtlCtrlSts(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getDsContrDtlCtrlSts", ssmParam);
        if (res.isCodeNormal()) {
            return (Map<String, Object>) res.getResultObject();
        }
        return null;
    }
    // END 2015/12/10 T.Tomita [QC1794, MOD]

    // START 2016/05/12 T.Tomita [QC#7832, MOD]
    public List<Map<String, Object>> getConfigInfo(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getConfigInfo", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return new ArrayList<Map<String, Object>>();
        }
        return (List<Map<String, Object>>) res.getResultObject();
    }
    // END 2016/05/12 T.Tomita [QC#7832, MOD]

    public String getOpenOrder(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("closed", ORD_LINE_STS.CLOSED);
        ssmParam.put("cancelled", ORD_LINE_STS.CANCELLED);
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getOpenOrder", ssmParam);
        if (res.isCodeNormal()) {
            return (String) res.getResultObject();
        }
        return null;
    }

    /* 08/10/2016 CSAI Y.Imazu Add QC#12496 START */
    /**
     * Get Install Required Flag for Revenue
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    public String getSvcIstlReqFlg(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getSvcIstlReqFlg", ssmParam);

        if (ssmResult.isCodeNormal()) {

            return (String) ssmResult.getResultObject();
        }

        return null;
    }
    /* 08/10/2016 CSAI Y.Imazu Add QC#12496 END */

    // START 2016/02/04 T.Tomita [QC#3312, MOD]
    /**
     * count SvcMachMstr
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal countSvcMachMstr(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("countSvcMachMstr", ssmParam);
        return (BigDecimal) res.getResultObject();
    }
    // END 2016/02/04 T.Tomita [QC#3312, MOD]

    // START 2016/02/25 T.Tomita [QC#2690, ADD]
    /**
     * getCtacPsnPk
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal getCtacPsnPk(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getCtacPsnPk", ssmParam);
        return (BigDecimal) res.getResultObject();
    }
    // END 2016/02/25 T.Tomita [QC#2690, ADD]

    // START 2016/03/28 M.Gotou [QC#4888, ADD]
    /**
     * count RtlSWH
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal countRtlSWH(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("countRtlSWH", ssmParam);
        return (BigDecimal) res.getResultObject();
    }
    // END 2016/03/28 M.Gotou [QC#4888, ADD]

    // START 2016/04/08 M.Gotou [QC#4905, ADD]
    public SVC_CONTR_BRTMsg getSvcContrBr(String glblCmpyCd, String svcContrBrCd) {
        SVC_CONTR_BRTMsg tMsg = new SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, svcContrBrCd);
        return (SVC_CONTR_BRTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    // START 2016/12/14 K.Kojima [QC#15653,MOD]
    // public TECH_MSTRTMsg getTechMstr(String glblCmpyCd, String techTocCd) {
    //     TECH_MSTRTMsg tMsg = new TECH_MSTRTMsg();
    //     ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
    //     ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, techTocCd);
    //     return (TECH_MSTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
    // }
    public String getTechMstr(String glblCmpyCd, String techTocCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("techTocCd", techTocCd);
        ssmParam.put("maxDt", NSAL0010Constant.MAX_DT_VAL);
        ssmParam.put("slsDt", slsDt);
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getTechMstr", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return null;
        }
        return (String) res.getResultObject();
    }
    // END 2016/12/14 K.Kojima [QC#15653,MOD]
    // END 2016/04/08 M.Gotou [QC#4905, ADD]

    // add start 2016/04/19 CSA Defect#6218
    public DS_CTAC_PNTTMsg getDsCtacPnt(String glblCmpyCd, BigDecimal dsCtacPntPk) {
        DS_CTAC_PNTTMsg tMsg = new DS_CTAC_PNTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntPk, dsCtacPntPk);
        return (DS_CTAC_PNTTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // add end 2016/04/19 CSA Defect#6218

    // add start 2016/04/20 CSA Defect#4892
    public List<Map<String, Object>> getSvcLineBiz(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getSvcLineBiz", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return new ArrayList<Map<String, Object>>();
        }
        return (List<Map<String, Object>>) res.getResultObject();
    }
    // add end 2016/04/20 CSA Defect#4892
    // add start 2016/04/25 CSA Defect#6672
    /**
     * getEffDtInfo
     * @param ssmParam Map<String, Object>
     * @return Map<String, Object>
     */
    public Map<String, Object> getEffDtInfo(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getEffDtInfo", ssmParam);
        return (Map<String, Object>) res.getResultObject();
    }
    // add end 2016/04/25 CSA Defect#6672

    // START 2016/07/22 T.Tomita [QC#11161, ADD]
    /**
     * countSvcBrPostCdFull
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal countSvcBrPostCdFull(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("countSvcBrPostCdFull", ssmParam);
        return (BigDecimal) res.getResultObject();
    }

    /**
     * countSvcBrPostCdShort
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal countSvcBrPostCdShort(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObject("countSvcBrPostCdShort", ssmParam);
        return (BigDecimal) res.getResultObject();
    }
    // END 2016/07/22 T.Tomita [QC#11161, ADD]

    // START 2016/08/08 T.Tomita [QC#7794, ADD]
    /**
     * getCtacInfo
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getCtacInfo(Map<String, Object> ssmParam) {
        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getCtacInfo", ssmParam);
        return (List<Map<String, Object>>) res.getResultObject();
    }
    // END 2016/08/08 T.Tomita [QC#7794, ADD]

    // Add Start 2017/10/16 QC#21563
    public List<Map<String, Object>> getMachSts(String glblCmpyCd, String constVal) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        String[] svcMachMstrStsCdList = new String[]{};
        if (ZYPCommonFunc.hasValue(constVal)) {
            svcMachMstrStsCdList = constVal.split(NSAL0010Constant.DELIMITER);
        }
        ssmParam.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getSvcMachMstrSts", ssmParam);
        return (List<Map<String, Object>>) res.getResultObject();
    }

    public List<Map<String, Object>> getMachUsgSts(String glblCmpyCd, String constVal) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        String[] svcMachUsgStsCdList = new String[]{};
        if (ZYPCommonFunc.hasValue(constVal)) {
            svcMachUsgStsCdList = constVal.split(NSAL0010Constant.DELIMITER);
        }
        ssmParam.put("svcMachUsgStsCdList", svcMachUsgStsCdList);
        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getSvcMachUsgSts", ssmParam);
        return (List<Map<String, Object>>) res.getResultObject();
    }
    // Add End 2017/10/16 QC#21563

    // START 2018/05/28 K.Kitachi [QC#19932, ADD]
    /**
     * getSvcMachMstrQty
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal getSvcMachMstrQty(Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getSvcMachMstrQty", ssmParam).getResultObject();
    }

    /**
     * getInvtyDtlDlyQty
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal getInvtyDtlDlyQty(Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getInvtyDtlDlyQty", ssmParam).getResultObject();
    }
    // END 2018/05/28 K.Kitachi [QC#19932, ADD]
    // Add Start 2018/06/05 QC#23428
    public BigDecimal cntExchOrd(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        List<String> ordHdrStsCdList = new ArrayList<String>();
        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET);
        ssmParam.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.IB_UPDATE);
        ssmParam.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);

        S21SsmEZDResult res = getSsmEZDClient().queryObject("cntExchOrd", ssmParam);
        if (!res.isCodeNormal()) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) res.getResultObject();
    }
    // Add End 2018/06/05 QC#23428

    // START 2018/08/23 K.Kitachi [QC#27773, ADD]
    /**
     * countMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return BigDecimal
     */
    public BigDecimal countMdse(String glblCmpyCd, String mdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        return (BigDecimal) getSsmEZDClient().queryObject("countMdse", param).getResultObject();
    }

    /**
     * getDsContrWty
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @param slsDt String
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsContrWty(String glblCmpyCd, BigDecimal svcConfigMstrPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("slsDt", slsDt);
        param.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
        param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsContrWty", param).getResultObject();
    }

    /**
     * getDsContrIntfc
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsContrIntfc(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        param.put("dsContrProcStsCd", DS_CONTR_PROC_STS.COMPLEATED);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsContrIntfc", param).getResultObject();
    }

    /**
     * countDsContrWtyForAcc
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @param svcMachMstrPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public BigDecimal countDsContrWtyForAcc(String glblCmpyCd, String dsContrNum, BigDecimal svcMachMstrPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrNum", dsContrNum);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("slsDt", slsDt);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
        param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (BigDecimal) getSsmEZDClient().queryObject("countDsContrWtyForAcc", param).getResultObject();
    }

    /**
     * countDsContrIntfcForAcc
     * @param glblCmpyCd String
     * @param dsContrSrcRefNum String
     * @param svcMachMstrPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countDsContrIntfcForAcc(String glblCmpyCd, String dsContrSrcRefNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrSrcRefNum", dsContrSrcRefNum);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("dsContrProcStsCd", DS_CONTR_PROC_STS.COMPLEATED);
        return (BigDecimal) getSsmEZDClient().queryObject("countDsContrIntfcForAcc", param).getResultObject();
    }
    // END 2018/08/23 K.Kitachi [QC#27773, ADD]

    // START 2021/08/17 R. Cabral [QC#59010, ADD]
    /**
     * checkTransitionFlags
     * @param glblCmpyCd String
     * @param beforeValue String
     * @param afterValue String
     * @return boolean
     */
    public boolean checkTransitionFlags(String glblCmpyCd, String beforeValue, String afterValue) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("allText", NSAL0010Constant.ALL_TEXT);
        param.put("beforeValue", beforeValue);
        param.put("afterValue", afterValue);
        param.put("trxTpUpdateMap", NSAL0010Constant.TRX_TP_UPDATE_MAP);

        int resultNum = (Integer) getSsmEZDClient().queryObject("checkTransitionFlags", param).getResultObject();
        if (resultNum <= 0) {
            return false;
        }
        return true;
    }
    // END   2021/08/17 R. Cabral [QC#59010, ADD]

    // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
    /**
     * getSwLicId
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    public String getSwLicId(String glblCmpyCd, BigDecimal svcMachMstrPk){
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);
        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstrTMsg);
        return svcMachMstrTMsg.swLicId.getValue();
    }

    /**
     * updateSwLicId
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param swLicId String
     * @return boolean
     */
    public void updateSwLicId(String glblCmpyCd, BigDecimal svcMachMstrPk, String swLicId){
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstrForUpdate(glblCmpyCd, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.swLicId, swLicId);
        S21FastTBLAccessor.update(svcMachMstrTMsg);
    }

    /**
     * findSvcMachMstrForUpdate
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg findSvcMachMstrForUpdate(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(svcMachMstrTMsg);
    }
    // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
    // START 2023/10/06 K.Ishizuka [QC#54186, ADD]
    public S21SsmEZDResult getSvcPrvdPty(NSAL0010CMsg bizMsg, boolean toBeIstlByFlg, String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        if (toBeIstlByFlg){
            params.put("toBeIstlByFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcPrvdByFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getSvcPrvdPty", params);

    }
    
    public S21SsmEZDResult getSvcPrvdPtyFromSetSLBCd(NSAL0010CMsg bizMsg,String svcPrvdPtyCd, String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcPrvdPtyCd", svcPrvdPtyCd);
        return getSsmEZDClient().queryObject("getSvcPrvdPty", params);

    }
    // END 2023/10/06 K.Ishizuka [QC#54186, ADD]

    // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
    /**
     * Count locStsMndFlg by machine master status code
     * @param glblCmpyCd global company code
     * @param svcMachMstrStsCd machine master status code
     * @return locStsMndFlg count
     */
    public int countLocStsMndFlgByStsCd(String glblCmpyCd, String svcMachMstrStsCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locStsMndFlg", ZYPConstant.FLG_ON_Y);
        params.put("svcMachMstrStsCd", svcMachMstrStsCd);

        Integer resultNum = (Integer) getSsmEZDClient().queryObject("countLocStsMndFlgByStsCd", params).getResultObject();
        return resultNum.intValue();
    }
    // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
}
