/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0820;

import static business.blap.NSAL0820.constant.NSAL0820Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ACTL_CNT_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/04/06   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#12141
 * 2016/08/02   Hitachi         Y.Takeno        Update          QC#12077
 * 2016/09/07   Hitachi         T.Tomita        Update          QC#11836
 *</pre>
 */
public final class NSAL0820Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0820Query INSTANCE = new NSAL0820Query();

    /**
     * Private constructor
     */
    private NSAL0820Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0820Query singleton instance
     */
    public static NSAL0820Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
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
        if (ZYPCommonFunc.hasValue(cMsg.dsContrNum_S2)) {
            params.put("dsContrNum", (String) cMsg.dsContrNum_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxErrFlg_S2) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxErrFlg_S2.getValue())) {
            List<String> xxErrFlgList = new ArrayList<String>();
            xxErrFlgList.add(DS_CONTR_PROC_STS.ERROR);
            xxErrFlgList.add(DS_CONTR_PROC_STS.REPROCESS);
            params.put("xxErrFlgList", xxErrFlgList);
        }
        if (cMsg.P.getValidCount() > 0) {
            List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
            for (int i = 0; i < cMsg.P.getValidCount(); i++) {
                dsContrIntfcPkList.add((BigDecimal) cMsg.P.no(i).dsContrIntfcPk.getValue());
            }
            params.put("dsContrIntfcPkList", dsContrIntfcPkList);
        }
        params.put("limitNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.A);
    }

    /**
     * getdsActlCntIntfcPk
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0820_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getdsActlCntIntfcPk(String glblCmpyCd, NSAL0820_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());
        params.put("mdseCd", sMsgLine.mdseCd_A.getValue());
        params.put("physMtrLbCd", sMsgLine.physMtrLbCd_A.getValue());
        return getSsmEZDClient().queryObject("getdsActlCntIntfc", params);
    }

    /**
     * getStatusUpdateTarget
     * @param glblCmpyCd String
     * @param dsActlCntIntfcTMsgList List<DS_ACTL_CNT_INTFCTMsg>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusUpdateTarget(String glblCmpyCd, List<DS_ACTL_CNT_INTFCTMsg> dsActlCntIntfcTMsgList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<BigDecimal> dsActlCntIntfcPkList = new ArrayList<BigDecimal>();
        for (DS_ACTL_CNT_INTFCTMsg tMsg : dsActlCntIntfcTMsgList) {
            if (ZYPCommonFunc.hasValue(tMsg.dsActlCntIntfcPk)) {
                dsActlCntIntfcPkList.add(tMsg.dsActlCntIntfcPk.getValue());
            }
        }
        ssmParam.put("dsActlCntIntfcPkList", dsActlCntIntfcPkList);
        ssmParam.put("intfcStsError", DS_CONTR_INTFC_STS.ERROR);
        ssmParam.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        ssmParam.put("reProcess", DS_CONTR_PROC_STS.REPROCESS);

        return getSsmEZDClient().queryObjectList("getStatusUpdateTarget", ssmParam);
    }

    // START 2016/07/27 M.Gotou [QC#12141, ADD]
    /**
     * countCpltData
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countCpltData(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrProcStsCd", DS_CONTR_PROC_STS.COMPLEATED);
        List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrIntfcPkList.add((BigDecimal) cMsg.P.no(i).dsContrIntfcPk.getValue());
        }
        params.put("dsContrIntfcPkList", dsContrIntfcPkList);

        return getSsmEZDClient().queryObject("countCpltData", params);
    }

    /**
     * getDsContrIntfcActDescTxt
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcActDescTxt(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrIntfcPkList.add((BigDecimal) cMsg.P.no(i).dsContrIntfcPk.getValue());
        }
        params.put("dsContrIntfcPkList", dsContrIntfcPkList);

        return getSsmEZDClient().queryObject("getDsContrIntfcActDescTxt", params);
    }
    // END 2016/07/27 M.Gotou [QC#12141, ADD]

    // START 2016/08/02 Y.Takeno [QC#12077, ADD]
    /**
     * getDsContrIntfcPk
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0820_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcPk(String glblCmpyCd, NSAL0820_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("svcMachMstrPk", sMsgLine.svcMachMstrPk_A.getValue());
        params.put("bllgMtrLbCd", sMsgLine.bllgMtrLbCd_A.getValue());
        return getSsmEZDClient().queryObject("getDsContrIntfcPk", params);
    }
    // END 2016/08/02 Y.Takeno [QC#12077, ADD]

    // START 2016/09/07 T.Tomita [QC#11836, ADD]
    /**
     * getMtrLbCd
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mtrLbDescTxt String
     * @param mtrLbTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrLbCd(String glblCmpyCd, String slsDt, String mtrLbDescTxt, String mtrLbTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mtrLbDescTxt", mtrLbDescTxt);
        params.put("mtrLbTpCd", mtrLbTpCd);
        params.put("slsDt", slsDt);
        params.put("endDt", END_DT);
        return getSsmEZDClient().queryObject("getMtrLbCd", params);
    }
    // END 2016/09/07 T.Tomita [QC#11836, ADD]
}
