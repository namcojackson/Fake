/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0830;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static business.blap.NSAL0830.constant.NSAL0830Constant.*;
import business.db.DS_XS_COPY_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5648
 * 2016/04/19   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12141
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11831
 *</pre>
 */
public final class NSAL0830Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0830Query INSTANCE = new NSAL0830Query();

    /**
     * Private constructor
     */
    private NSAL0830Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0830Query singleton instance
     */
    public static NSAL0830Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
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
        params.put("xxTpCdH", XX_TP_CD_H);
        params.put("xxTpCdD", XX_TP_CD_D);
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("limitNum", sMsg.A.length() + 1);
        // START 2016/03/22 K.Yamada [QC#5648, ADD]
        params.put("contrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.USAGE);
        // END 2016/03/22 K.Yamada [QC#5648, ADD]

        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.A);
    }

    /**
     * get DS_XS_COPY_INTFC_PK.
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0830_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsXsCopyIntfcPk(String glblCmpyCd, NSAL0830_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());
        params.put("mdseCd", sMsgLine.mdseCd_A.getValue());
        params.put("bllgMtrLbCd", sMsgLine.bllgMtrLbCd_A.getValue());
        return getSsmEZDClient().queryObject("getDsXsCopyIntfcPk", params);
    }

    // ADD start 2016/04/19 CSA Defect#6691
    /**
     * get DS_XS_COPY_INTFC Data.
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0830_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getXsCopyIntfcData(String glblCmpyCd, NSAL0830_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());
        params.put("mdseCd", sMsgLine.mdseCd_A.getValue());
        params.put("bllgMtrLbCd", sMsgLine.bllgMtrLbCd_A.getValue());
        params.put("xsMtrCopyQty", sMsgLine.xsMtrCopyQty_A.getValue());
        return getSsmEZDClient().queryObject("getXsCopyIntfcData", params);
    }
    // ADD end 2016/04/19 CSA Defect#6691

    /**
     * get DS_CONTR_INTFC_PK.
     * @param glblCmpyCd String
     * @param sMsgLine NSAL0830_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcPk(String glblCmpyCd, NSAL0830_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrIntfcBatNum", sMsgLine.dsContrIntfcBatNum_A.getValue());
        params.put("dsContrSrcRefNum", sMsgLine.dsContrSrcRefNum_A.getValue());
        params.put("contrIntfcSrcTpCd", sMsgLine.contrIntfcSrcTpCd_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());
        params.put("mdseCd", sMsgLine.mdseCd_A.getValue());
        params.put("bllgMtrLbCd", sMsgLine.bllgMtrLbCd_A.getValue());
        // START 2016/08/09 Y.Takeno [QC#11831, ADD]
        params.put("contrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.USAGE);
        // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        return getSsmEZDClient().queryObject("getDsContrIntfcPk", params);
    }

    // ADD  MOD start 2016/04/19 CSA Defect#6691
    /**
     * getStatusUpdateTarget
     * @param glblCmpyCd String
     * @param dsXsCopyIntfcTMsgList List<DS_XS_COPY_INTFCTMsg>
     * @param sMsg NSAL0830SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusUpdateTarget(String glblCmpyCd, List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList, NSAL0830SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<BigDecimal> dsXsCopyIntfcPkList = new ArrayList<BigDecimal>();
        for (DS_XS_COPY_INTFCTMsg tMsg : dsXsCopyIntfcTMsgList) {
            if (ZYPCommonFunc.hasValue(tMsg.dsXsCopyIntfcPk)) {
                dsXsCopyIntfcPkList.add(tMsg.dsXsCopyIntfcPk.getValue());
            }
        }
        List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue()) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).dsXsCopyIntfcPk_A) && ZYPCommonFunc.hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
                dsContrIntfcPkList.add(sMsg.A.no(i).dsContrIntfcPk_A.getValue());
            }
        }
        ssmParam.put("dsXsCopyIntfcPkList", dsXsCopyIntfcPkList);
        ssmParam.put("dsContrIntfcPkList", dsContrIntfcPkList);
        ssmParam.put("intfcStsError", DS_CONTR_INTFC_STS.ERROR);
        ssmParam.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        ssmParam.put("reProcess", DS_CONTR_PROC_STS.REPROCESS);

        return getSsmEZDClient().queryObjectList("getStatusUpdateTarget", ssmParam);
    }
    //  ADD end 2016/04/19 CSA Defect#6691

    // START 2016/07/28 M.Gotou [QC#12141, ADD]
    /**
     * countCpltData
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countCpltData(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
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
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcActDescTxt(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
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
