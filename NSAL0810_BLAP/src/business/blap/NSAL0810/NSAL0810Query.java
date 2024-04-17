/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0810;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Ds Contract Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/01/28   Hitachi         Y.Tsuchimoto    Update          QC#3862
 * 2016/03/29   Hitachi         T.Iwamoto       Update          QC#4006
 * 2016/04/01   Hitachi         T.Iwamoto       Update          QC#6335
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 * 2017/09/15   Hitachi         A.Kohinata      Update          QC#19775
 * 2017/11/06   Hitachi         M.Kidokoro      Update          QC#18612
 *</pre>
 */
public final class NSAL0810Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0810Query INSTANCE = new NSAL0810Query();

    /**
     * Private constructor
     */
    private NSAL0810Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0810Query singleton instance
     */
    public static NSAL0810Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(cMsg.contrIntfcSrcTpCd_S2)) {
            params.put("contrIntfcSrcTpCd", (String) cMsg.contrIntfcSrcTpCd_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrSrcRefNum_S2)) {
            params.put("dsContrSrcRefNum", (String) cMsg.dsContrSrcRefNum_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrIntfcBatNum_S2)) {
            params.put("dsContrIntfcBatNum", (String) cMsg.dsContrIntfcBatNum_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrIntfcDt_F2)) {
            params.put("dsContrIntfcDtFrom", (String) cMsg.dsContrIntfcDt_F2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrIntfcDt_T2)) {
            params.put("dsContrIntfcDtThru", (String) cMsg.dsContrIntfcDt_T2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxErrFlg_S2) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxErrFlg_S2.getValue())) {
            params.put("errorFlg", cMsg.xxErrFlg_S2.getValue());
            params.put("error", DS_CONTR_PROC_STS.ERROR);
            params.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
            // START 2017/11/06 M.Kidokoro [QC#18612, ADD]
            params.put("contrIntfcStsError", DS_CONTR_INTFC_STS.ERROR);
            // END 2017/11/06 M.Kidokoro [QC#18612, ADD]
        } else {
            params.put("errorFlg", null);
        }

        params.put("limitNum", sMsg.A.length() + 1);

        //return getSsmEZDClient().queryEZDMsgArray("getDsContrIntfcList", params, sMsg.A);
        return getSsmEZDClient().queryObjectList("getDsContrIntfcList", params);
    }

    /**
     * getSvcLineBizList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcLineBizList() {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        return getSsmEZDClient().queryObjectList("getSvcLineBizList", ssmParam);
    }

    // START 2016/05/25 T.Tomita [QC#8898, DEL]
//    /**
//     * getMtrLbList
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMtrLbList() {
//        String glblCmpyCd = getGlobalCompanyCode();
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("billingMeter", MTR_LB_TP.BILLING_METER);
//        return getSsmEZDClient().queryObjectList("getMtrLbList", ssmParam);
//    }
    // END 2016/05/25 T.Tomita [QC#8898, DEL]

    /**
     * getDsContrIntfcDefRule
     * @param glblCmpyCd String
     * @param asMsg NSAL0810_ASMsg
     * @return S21SsmEZDResult
     */
    // mod start 2017/09/15 QC#19775
    //public S21SsmEZDResult getDsContrIntfcDefRule(String glblCmpyCd, String contrIntfcSrcTp) {
    public S21SsmEZDResult getDsContrIntfcDefRule(String glblCmpyCd, NSAL0810_ASMsg asMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("contrIntfcSrcTp", asMsg.contrIntfcSrcTpCd_AS.getValue());
        ssmParam.put("dsContrClsCd", asMsg.dsContrClsCd_AS.getValue());
        ssmParam.put("svcLineBizCd", asMsg.svcLineBizCd_AS.getValue());
        return getSsmEZDClient().queryObject("getDsContrIntfcDefRule", ssmParam);
    }
    // mod end 2017/09/15 QC#19775

    /**
     * getStartMtrCnt
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStartMtrCnt(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        return getSsmEZDClient().queryObject("getStartMtrCnt", ssmParam);
    }

    /**
     * getSameDsContrDtlList
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSameDsContrDtlList(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        ssmParam.put("active", DS_CONTR_DTL_STS.ACTIVE);
        return getSsmEZDClient().queryObjectList("getSameDsContrDtlList", ssmParam);
    }
    // DELETE start 2016/04/01 CSA Defect#6335
//    /**
//     * getMaxDsContrIntfcBatNum
//     * @param glblCmpyCd String
//     * @param salesDate String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMaxDsContrIntfcBatNum(String glblCmpyCd, String salesDate) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("salesDate", salesDate);
//        return getSsmEZDClient().queryObject("getMaxDsContrIntfcBatNum", ssmParam);
//    }
    // DELETE end 2016/04/01 CSA Defect#6335

    // ADD start 2016/04/01 CSA Defect#6335
    /**
     * @param glblCmpyCd String
     * @param salesDate String
     * @return BigDecimal
     */
    public BigDecimal getMaxPlusOneSequence(String glblCmpyCd, String salesDate) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("salesDate", salesDate);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        int maxDsContrIntfcSqNum = (Integer)  ssmBatchClient.queryObject("getMaxPlusOneSequence", param);
        BigDecimal rtnSqNum = null;
        if (maxDsContrIntfcSqNum > 0) {
            rtnSqNum = new BigDecimal(maxDsContrIntfcSqNum);
            rtnSqNum = rtnSqNum.add(BigDecimal.ONE);
        } else {
            rtnSqNum = BigDecimal.ONE;
        }
        return rtnSqNum;
    }
    // ADD end 2016/04/01 CSA Defect#6335

    /**
     * getDsActlCntrIntfc
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsActlCntrIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        return getSsmEZDClient().queryObjectList("getDsActlCntrIntfc", ssmParam);
    }

    /**
     * getDsXsCopyIntfc
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsXsCopyIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        return getSsmEZDClient().queryObjectList("getDsXsCopyIntfc", ssmParam);
    }

    /**
     * getDsAddlChrgIntfc
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAddlChrgIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        return getSsmEZDClient().queryObjectList("getDsAddlChrgIntfc", ssmParam);
    }

    /**
     * getPrcAllocIntfc
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcAllocIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        return getSsmEZDClient().queryObjectList("getPrcAllocIntfc", ssmParam);
    }

    /**
     * getCtacPsnPk
     * @param glblCmpyCd String
     * @param fullName String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnPk(String glblCmpyCd, String fullName) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("fullName", fullName);
        return getSsmEZDClient().queryObjectList("getCtacPsnPk", ssmParam);
    }

    /**
     * getDsContrIntfcForUpload
     * @param glblCmpyCd String
     * @param asMsg NSAL0810_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcForUpload(String glblCmpyCd, NSAL0810_ASMsg asMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("contrIntfcSrcTpCd", asMsg.contrIntfcSrcTpCd_AS.getValue());
        ssmParam.put("dsContrSrcRefNum", asMsg.dsContrSrcRefNum_A);
        ssmParam.put("dsContrIntfcBatNum", asMsg.dsContrIntfcBatNum_A);
        ssmParam.put("serNum", asMsg.serNum_A);
        ssmParam.put("mdseCd", asMsg.mdseCd_A);
        ssmParam.put("contrIntfcDtlTpCd", asMsg.contrIntfcDtlTpCd_AS);
        // START 2016/05/25 T.Tomita [QC#8898, MOD]
//        ssmParam.put("bllgMtrLbCd", asMsg.bllgMtrLbCd_AS);
        ssmParam.put("bllgMtrLbCd", asMsg.bllgMtrLbCd_A);
        // END 2016/05/25 T.Tomita [QC#8898, MOD]

        return getSsmEZDClient().queryObject("getDsContrIntfcForUpload", ssmParam);
    }

    /**
     * getCpoOrdTpCd
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCpoOrdTpCd(String glblCmpyCd,  BigDecimal dsContrIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrIntfcPk", dsContrIntfcPk);
        ssmParam.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.ORDER);
        // 2016/01/28 QC#3862 Y.Tsuchimoto Mod Start
        //return getSsmEZDClient().queryObjectList("getCpoOrdTpCd", ssmParam);
        return getSsmEZDClient().queryObject("getCpoOrdTpCd", ssmParam);
        // 2016/01/28 QC#3862 Y.Tsuchimoto Mod End
    }

    /**
     * getStatusUpdateTarget
     * @param glblCmpyCd String
     * @param dsCntIntfcPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusUpdateTarget(String glblCmpyCd, List<BigDecimal> dsCntIntfcPkList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCntIntfcPkList", dsCntIntfcPkList);
        ssmParam.put("intfcStsError", DS_CONTR_INTFC_STS.ERROR);
        ssmParam.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        ssmParam.put("reProcess", DS_CONTR_PROC_STS.REPROCESS);

        return getSsmEZDClient().queryObjectList("getStatusUpdateTarget", ssmParam);
    }

    // Add Start 2016/09/26 <QC#14428>
    /**
     * getAddlChrgTpV
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getAddlChrgTpV() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAddlChrgTpV", ssmParam).getResultObject();
    }
    // Add End   2016/09/26 <QC#14428>
}
