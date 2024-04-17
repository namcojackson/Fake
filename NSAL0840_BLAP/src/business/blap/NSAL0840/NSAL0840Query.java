/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0840;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSAL0840.NSAL0840_ASMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/14   Hitachi         Y.Tsuchimoto    Update          QC#4568
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12141
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 *</pre>
 */
public final class NSAL0840Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0840Query INSTANCE = new NSAL0840Query();

    /**
     * Private constructor
     */
    private NSAL0840Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0840Query singleton instance
     */
    public static NSAL0840Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
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
        // 2016/02/16 QC#4568 Y.Tsuchimoto Add Start
        params.put("base", CONTR_INTFC_DTL_TP.BASE);
        // 2016/02/16 QC#4568 Y.Tsuchimoto Add End

        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.A);
    }

    /**
     * getDsAddlChrgIntfc
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0840_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAddlChrgIntfc(String glblCmpyCd, NSAL0840_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());
        // ADD start 2016/04/22 CSA Defect#6691
        params.put("mdseCd", sMsgLine.mdseCd_A.getValue());
        params.put("chrgLvlTpCd", sMsgLine.chrgLvlTpCd_A.getValue());
        params.put("addlChrgTpCd", sMsgLine.addlChrgTpCd_A.getValue());
        // ADD end 2016/04/22 CSA Defect#6691

        return getSsmEZDClient().queryObject("getDsAddlChrgIntfc", params);
    }

 // START 2016/07/28 M.Gotou [QC#12077, ADD]
    /**
     * getDsContrIntfcPk
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0840_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcPk(String glblCmpyCd, NSAL0840_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("svcMachMstrPk", sMsgLine.svcMachMstrPk_A.getValue());
        return getSsmEZDClient().queryObject("getDsContrIntfcPk", params);
    }
    // END 2016/07/28 M.Gotou [QC#12077, ADD]

    // 2016/04/07 [QC#5662, ADD]
    /**
     * getStatusUpdateTarget
     * @param glblCmpyCd String
     * @param dsAddlChrgIntfcTMsgList List<DS_ADDL_CHRG_INTFCTMsg>
     * @param sMsg NSAL0840SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusUpdateTarget(String glblCmpyCd, List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList, NSAL0840SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<BigDecimal> dsAddlChrgIntfcPkList = new ArrayList<BigDecimal>();
        for (DS_ADDL_CHRG_INTFCTMsg tMsg : dsAddlChrgIntfcTMsgList) {
            if (ZYPCommonFunc.hasValue(tMsg.dsAddlChrgIntfcPk)) {
                dsAddlChrgIntfcPkList.add(tMsg.dsAddlChrgIntfcPk.getValue());
            }
        }
        List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue()) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).dsAddlChrgIntfcPk_A) && ZYPCommonFunc.hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
                dsContrIntfcPkList.add(sMsg.A.no(i).dsContrIntfcPk_A.getValue());
            }
        }
        ssmParam.put("dsAddlChrgIntfcPkList", dsAddlChrgIntfcPkList);
        ssmParam.put("dsContrIntfcPkList", dsContrIntfcPkList);
        ssmParam.put("intfcStsError", DS_CONTR_INTFC_STS.ERROR);
        ssmParam.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        ssmParam.put("reProcess", DS_CONTR_PROC_STS.REPROCESS);

        return getSsmEZDClient().queryObjectList("getStatusUpdateTarget", ssmParam);
    }

    // START 2016/07/28 M.Gotou [QC#12141, ADD]
    /**
     * countCpltData
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countCpltData(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
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
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcActDescTxt(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
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
