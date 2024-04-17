/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0850;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.PRC_ALLOC_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Sales Credit for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5542
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#12077
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12141
 *</pre>
 */

public final class NSAL0850Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0850Query INSTANCE = new NSAL0850Query();

    /**
     * Private constructor
     */
    private NSAL0850Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0850Query singleton instance
     */
    public static NSAL0850Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {
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
        params.put("contrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.BASE);
        params.put("limitNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.A);
    }

    /**
     * getPrcAllocIntfcPk
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0850_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcAllocIntfcPk(String glblCmpyCd, NSAL0850_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());
        params.put("mdseCd", sMsgLine.mdseCd_A.getValue());
        // ADD start 2016/04/22 CSA Defect#6691
        params.put("prcAllocIntfcTpCd", sMsgLine.prcAllocIntfcTpCd_A.getValue());
        params.put("tocCd", sMsgLine.tocCd_A.getValue());
        // ADD end 2016/04/22 CSA Defect#6691

        return getSsmEZDClient().queryObject("getPrcAllocIntfcPk", params);
    }

    // START 2016/07/27 M.Gotou [QC#12077, ADD]
    /**
     * getDsContrIntfcPk
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0850_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcPk(String glblCmpyCd, NSAL0850_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("svcMachMstrPk", sMsgLine.svcMachMstrPk_A.getValue());
        return getSsmEZDClient().queryObject("getDsContrIntfcPk", params);
    }
    // END 2016/07/27 M.Gotou [QC#12077, ADD]

    
    // 2016/04/07 [QC#5662, ADD]
    /**
     * getStatusUpdateTarget
     * @param glblCmpyCd String
     * @param prcAllocIntfcTMsgList List<PRC_ALLOC_INTFCTMsg>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusUpdateTarget(String glblCmpyCd, List<PRC_ALLOC_INTFCTMsg> prcAllocIntfcTMsgList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<BigDecimal> prcAllocIntfcPkList = new ArrayList<BigDecimal>();
        for (PRC_ALLOC_INTFCTMsg tMsg : prcAllocIntfcTMsgList) {
            if (ZYPCommonFunc.hasValue(tMsg.prcAllocIntfcPk)) {
                prcAllocIntfcPkList.add(tMsg.prcAllocIntfcPk.getValue());
            }
        }
        ssmParam.put("prcAllocIntfcPkList", prcAllocIntfcPkList);
        ssmParam.put("intfcStsError", DS_CONTR_INTFC_STS.ERROR);
        ssmParam.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        ssmParam.put("reProcess", DS_CONTR_PROC_STS.REPROCESS);

        return getSsmEZDClient().queryObjectList("getStatusUpdateTarget", ssmParam);
    }

    // START 2016/07/28 M.Gotou [QC#12141, ADD]
    /**
     * countCpltData
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countCpltData(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {
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
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcActDescTxt(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrIntfcPkList.add((BigDecimal) cMsg.P.no(i).dsContrIntfcPk.getValue());
        }
        params.put("dsContrIntfcPkList", dsContrIntfcPkList);

        return getSsmEZDClient().queryObject("getDsContrIntfcActDescTxt", params);
    }
    // END 2016/07/28 M.Gotou [QC#12141, ADD]
}
