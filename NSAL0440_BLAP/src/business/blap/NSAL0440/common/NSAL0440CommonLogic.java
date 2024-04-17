/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0440.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0440.NSAL0440CMsg;
import business.blap.NSAL0440.NSAL0440Query;
import business.blap.NSAL0440.NSAL0440SMsg;
import business.blap.NSAL0440.NSAL0440_ACMsg;
import business.blap.NSAL0440.NSAL0440_ACMsgArray;
import business.blap.NSAL0440.NSAL0440_BSMsg;
import business.db.SVC_TERM_COND_ATTRB_MAPTMsg;
import business.db.SVC_TERM_COND_DATA_SRCTMsg;
import business.db.SVC_TERM_COND_DATA_SRCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto       Create          N/A
 * 2016/03/28   Hitachi         T.Tomita        Update          QC#3018
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#5489
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */

public class NSAL0440CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        // NSAL0440_ACMsg -> NSAL0440_BSMsg
        NSAL0440_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0440_ACMsg acMsg = (NSAL0440_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;

            NSAL0440_BSMsg bsMsg = sMsg.B.no(targetIdxNumA);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxBtnFlg_A, acMsg.xxBtnFlg_A);
            ZYPEZDItemValueSetter.setValue(bsMsg.svcTermAttrbMapValCd_A, acMsg.svcTermAttrbMapValCd_A);
            ZYPEZDItemValueSetter.setValue(bsMsg.condValNum_A, acMsg.condValNum_A);
            ZYPEZDItemValueSetter.setValue(bsMsg.svcTermCondDataValCd_PS, acMsg.svcTermCondDataValCd_PS);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxTrxDt_A, acMsg.xxTrxDt_A);
            ZYPEZDItemValueSetter.setValue(bsMsg.svcTermAttrbMapValCd_M, acMsg.svcTermAttrbMapValCd_M);
            ZYPEZDItemValueSetter.setValue(bsMsg.condValNum_M, acMsg.condValNum_M);
            ZYPEZDItemValueSetter.setValue(bsMsg.svcTermCondDataValCd_MS, acMsg.svcTermCondDataValCd_MS);
            ZYPEZDItemValueSetter.setValue(bsMsg.xxTrxDt_M, acMsg.xxTrxDt_M);
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Set View Data processing is executed.
     * @param sMsg Global area information
     */
    public static void setViewData_A(NSAL0440SMsg sMsg) {
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        if (sMsg.B.getValidCount() == 0) {
            return;
        }
        int aIdx = 0;
        int bIdx = 0;

        for (; bIdx < sMsg.B.getValidCount(); bIdx++) {
            String dplyFlg = (String) sMsg.B.no(bIdx).xxDplyCtrlFlg_A.getValue();
            String btnFlg = (String) sMsg.B.no(bIdx).xxBtnFlg_A.getValue();
            BigDecimal svcTermCondCatgPk = sMsg.B.no(bIdx).svcTermCondCatgPk_A.getValue();
            BigDecimal svcTermCondAttrbPk = sMsg.B.no(bIdx).svcTermCondAttrbPk_A.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(dplyFlg)) {
                EZDMsg.copy(sMsg.B.no(bIdx), null, sMsg.A.no(aIdx), null);
                aIdx++;
                if (ZYPConstant.FLG_ON_Y.equals(btnFlg)) {
                    aIdx = setViewDetail_A(aIdx, svcTermCondCatgPk, svcTermCondAttrbPk, sMsg);
                }
            }
        }
        sMsg.A.setValidCount(aIdx);
    }

    private static int setViewDetail_A(int aIdx, BigDecimal svcTermCondCatgPk, BigDecimal svcTermCondAttrbPk, NSAL0440SMsg sMsg) {
        int rtrnIdx = aIdx;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (svcTermCondCatgPk.compareTo(sMsg.B.no(i).svcTermCondCatgPk_A.getValue()) == 0 && svcTermCondAttrbPk.compareTo(sMsg.B.no(i).svcTermCondAttrbPk_A.getValue()) == 0) {
                if (ZYPConstant.FLG_OFF_N.equals((String) sMsg.B.no(i).xxDplyCtrlFlg_A.getValue())) {
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.A.no(rtrnIdx), null);
                    rtrnIdx++;
                }
            }
        }
        return rtrnIdx;
    }

    /**
     * createDetailPulldown
     * @param cMsg Business Component Interface Message
     */
    public static void createDetailPulldown(NSAL0440CMsg cMsg) {
        // getDetailPulldown
        SVC_TERM_COND_DATA_SRCTMsgArray detailResult = getPulldownData(cMsg);
        if (detailResult == null) {
            return;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0440_ACMsg acMsg = cMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(acMsg.xxDplyCtrlFlg_A.getValue())) {
                if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(acMsg.svcTermDataTpCd_A.getValue())) {
                    SVC_TERM_COND_DATA_SRCTMsgArray pulldownArray = extractDataSrc(detailResult, acMsg.svcTermCondDataSrcCd_A.getValue());
                    Map<String, String> tMsgKeys = new HashMap<String, String>();
                    tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcTermCondDataValCd");
                    tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcTermCondDataDispTxt");
                    ZYPPulldownValueSetter.set(pulldownArray, tMsgKeys, acMsg.svcTermCondDataValCd_PC, acMsg.svcTermCondDataDispTxt_PN);
                }
                // START 2016/06/03 T.Tomita [QC#5489, ADD]
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(acMsg.svcTermDataTpCd_A.getValue())) {
                    SVC_TERM_COND_ATTRB_MAPTMsg tMsg = new SVC_TERM_COND_ATTRB_MAPTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.physMaintTrgtTblNm, acMsg.svcTermCondDataSrcCd_A);
                    tMsg = (SVC_TERM_COND_ATTRB_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
                    S21SsmEZDResult res = NSAL0440Query.getInstance().getPhysMaintTrgtTblPulldownList(tMsg);
                    if (res.isCodeNormal()) {
                        List<Map<String, Object>> mapList = (List<Map<String, Object>>) res.getResultObject();
                        int pulldownIdx = 0;
                        for (Map<String, Object> map : mapList) {
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_PC.no(pulldownIdx), (String) map.get("TARGET_CD"));
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataDispTxt_PN.no(pulldownIdx), (String) map.get("TARGET_NAME"));
                            pulldownIdx++;
                        }
                    }
                }
                // END 2016/06/03 T.Tomita [QC#5489, ADD]
                // add start 2018/06/25 QC#17369
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(acMsg.svcTermDataTpCd_A.getValue())) {
                    SVC_TERM_COND_ATTRB_MAPTMsg tMsg = new SVC_TERM_COND_ATTRB_MAPTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.physMaintTrgtTblNm, acMsg.svcTermCondDataSrcCd_A);
                    tMsg = (SVC_TERM_COND_ATTRB_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
                    if (tMsg == null) {
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(acMsg.physMaintTrgtTblNm_A, tMsg.physMaintTrgtTblNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.logicMaintTrgtTblNm_A, tMsg.logicMaintTrgtTblNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.physMaintTrgtColNm_A, tMsg.physMaintTrgtColNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.logicMaintTrgtColNm_A, tMsg.logicMaintTrgtColNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.physDplyTrgtColNm_A, tMsg.physDplyTrgtColNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.logicDplyTrgtColNm_A, tMsg.logicDplyTrgtColNm);
                    if (ZYPCommonFunc.hasValue(acMsg.svcTermCondDataValCd_PS)) {
                        Map<String, Object> lookupData = NSAL0440Query.getInstance().getLookupPopupData(tMsg, acMsg.svcTermCondDataValCd_PS.getValue());
                        if (lookupData != null) {
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_PS, (String) lookupData.get("TARGET_CD"));
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermAttrbMapValCd_A, (String) lookupData.get("TARGET_NAME"));
                        }
                    }
                }
                // add end 2018/06/25 QC#17369
            } else {
                if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(acMsg.svcTermDataTpCd_M.getValue())) {
                    SVC_TERM_COND_DATA_SRCTMsgArray detailArray = extractDataSrc(detailResult, acMsg.svcTermCondDataSrcCd_A.getValue());
                    Map<String, String> tMsgKeys = new HashMap<String, String>();
                    tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcTermCondDataValCd");
                    tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcTermCondDataDispTxt");
                    ZYPPulldownValueSetter.set(detailArray, tMsgKeys, acMsg.svcTermCondDataValCd_MC, acMsg.svcTermCondDataDispTxt_MN);
                }
                // START 2016/06/03 T.Tomita [QC#5489, ADD]
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(acMsg.svcTermDataTpCd_M.getValue())) {
                    SVC_TERM_COND_ATTRB_MAPTMsg tMsg = new SVC_TERM_COND_ATTRB_MAPTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.physMaintTrgtTblNm, acMsg.svcTermCondDataSrcCd_A);
                    tMsg = (SVC_TERM_COND_ATTRB_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
                    S21SsmEZDResult res = NSAL0440Query.getInstance().getPhysMaintTrgtTblPulldownList(tMsg);
                    if (res.isCodeNormal()) {
                        List<Map<String, Object>> mapList = (List<Map<String, Object>>) res.getResultObject();
                        int pulldownIdx = 0;
                        for (Map<String, Object> map : mapList) {
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_MC.no(pulldownIdx), (String) map.get("TARGET_CD"));
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataDispTxt_MN.no(pulldownIdx), (String) map.get("TARGET_NAME"));
                            pulldownIdx++;
                        }
                    }
                }
                // END 2016/06/03 T.Tomita [QC#5489, ADD]
                // add start 2018/06/25 QC#17369
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(acMsg.svcTermDataTpCd_M.getValue())) {
                    SVC_TERM_COND_ATTRB_MAPTMsg tMsg = new SVC_TERM_COND_ATTRB_MAPTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.physMaintTrgtTblNm, acMsg.svcTermCondDataSrcCd_A);
                    tMsg = (SVC_TERM_COND_ATTRB_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
                    if (tMsg == null) {
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(acMsg.physMaintTrgtTblNm_A, tMsg.physMaintTrgtTblNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.logicMaintTrgtTblNm_A, tMsg.logicMaintTrgtTblNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.physMaintTrgtColNm_A, tMsg.physMaintTrgtColNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.logicMaintTrgtColNm_A, tMsg.logicMaintTrgtColNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.physDplyTrgtColNm_A, tMsg.physDplyTrgtColNm);
                    ZYPEZDItemValueSetter.setValue(acMsg.logicDplyTrgtColNm_A, tMsg.logicDplyTrgtColNm);
                    if (ZYPCommonFunc.hasValue(acMsg.svcTermCondDataValCd_MS)) {
                        Map<String, Object> lookupData = NSAL0440Query.getInstance().getLookupPopupData(tMsg, acMsg.svcTermCondDataValCd_MS.getValue());
                        if (lookupData != null) {
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_MS, (String) lookupData.get("TARGET_CD"));
                            ZYPEZDItemValueSetter.setValue(acMsg.svcTermAttrbMapValCd_M, (String) lookupData.get("TARGET_NAME"));
                        }
                    }
                }
                // add end 2018/06/25 QC#17369
            }

        }
    }

    private static SVC_TERM_COND_DATA_SRCTMsgArray getPulldownData(NSAL0440CMsg cMsg) {
        SVC_TERM_COND_DATA_SRCTMsg inMsg = new SVC_TERM_COND_DATA_SRCTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", (String) cMsg.glblCmpyCd.getValue());
        return (SVC_TERM_COND_DATA_SRCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private static SVC_TERM_COND_DATA_SRCTMsgArray extractDataSrc(SVC_TERM_COND_DATA_SRCTMsgArray tMsgArray, String dataSrc) {
        SVC_TERM_COND_DATA_SRCTMsgArray rtnArray = new SVC_TERM_COND_DATA_SRCTMsgArray();
        rtnArray.setMsgList(new EZDTMsg[tMsgArray.length()]);
        int rtnId = 0;
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            if (dataSrc.equals(tMsgArray.no(i).svcTermCondDataSrcCd.getValue())) {
                EZDMsg.copy(tMsgArray.no(i), null, rtnArray.no(rtnId), null);
                rtnId++;
            }
        }
        rtnArray.setValidCount(rtnId);
        return rtnArray;

    }

    // add start 2016/03/28 CSA Defect#3018
    /**
     * is Protect Status
     * @param scrnMsg
     */
    public static boolean isProtectSts(NSAL0440_BSMsg detailMsg) {
        // Header Status
        String dsContrStsCd = detailMsg.dsContrStsCd_A.getValue();
        if (ZYPCommonFunc.hasValue(dsContrStsCd)) {
            if (DS_CONTR_STS.TERMINATED.equals(dsContrStsCd) || DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd)) {
                return true;
            }
        }

        // Detail Status
        String dsContrDtlStsCd = detailMsg.dsContrDtlStsCd_A.getValue();
        if (!ZYPCommonFunc.hasValue(dsContrDtlStsCd)) {
            return false;
        }
        if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrDtlStsCd)) {
            return true;
        }
        return false;
    }
    // add end 2016/03/28 CSA Defect#3018
}
