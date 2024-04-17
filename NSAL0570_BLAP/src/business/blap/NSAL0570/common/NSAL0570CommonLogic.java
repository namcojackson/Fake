/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0570.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0570.constant.NSAL0570Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface;

import business.blap.NSAL0570.NSAL0570CMsg;
import business.blap.NSAL0570.NSAL0570Query;
import business.blap.NSAL0570.NSAL0570_ACMsg;
import business.blap.NSAL0570.NSAL0570_ACMsgArray;
import business.blap.NSAL0570.NSAL0570_CCMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC047008PMsg;

/**
 *<pre>
 * Overage Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         T.Kanasaka      Update          QC#1815
 * 2015/12/11   Hitachi         A.Kohinata      Update          QC1820
 * 2016/01/06   Hitachi         T.Tomita        Update          QC2783
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#3844,QC#3845,QC#3846
 * 2016/04/06   Hitachi         K.Kishimoto     Update          QC#6585
 * 2016/04/11   Hitachi         K.Kishimoto     Update          QC#6728
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#4923
 * 2017/02/21   Hitachi         K.Kishimoto     Update          QC#17646
 * 2017/05/23   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 * 2020/03/12   Hitachi         K.Kitachi       Update          QC#55662
 *</pre>
 */
public class NSAL0570CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0570CMsg
     */
    // START 2015/12/11 [QC1820, MOD]
    public static void createPullDown(NSAL0570CMsg cMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_A1, cMsg.bllgCycleNm_A2);
        // START 2017/05/23 Y.Osawa [QC#18560, ADD]
        deletePulldownList(cMsg.bllgCycleCd_A1, cMsg.bllgCycleNm_A2, BLLG_CYCLE.DAILY);
        // END   2017/05/23 Y.Osawa [QC#18560, ADD]
        createSvcMemoRsnPullDown(cMsg, glblCmpyCd);
    }
    // START 2015/12/11 [QC1820, MOD]

    // START 2015/12/11 [QC1820, ADD]
    private static void createSvcMemoRsnPullDown(NSAL0570CMsg cMsg, String glblCmpyCd) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(glblCmpyCd, SVC_MEMO_TP.CHANGE_PRICE_EFFECTIVITY);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_A1, cMsg.svcMemoRsnNm_A2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // END 2015/12/11 [QC1820, ADD]

    public static BigDecimal getNextSeqNum(NSAL0570_ACMsg lastAcMsg) {
        return lastAcMsg.dsContrPrcEffSqNum_A1.getValue().add(BigDecimal.ONE);
    }

    // START 2016/05/20 T.Tomita [QC#4923, DEL]
//    public static void setReasonComment(NSAL0570CMsg cMsg) {
//        int targetRow = cMsg.xxRadioBtn_A.getValueInt();
//        setValue(cMsg.A.no(targetRow).svcMemoRsnCd_A3, cMsg.svcMemoRsnCd_H3);
//        setValue(cMsg.A.no(targetRow).svcCmntTxt_A1, cMsg.svcCmntTxt_H1);
//        //Add Start 02/08/2016 <QC#3846>
//        if (hasValue(cMsg.svcMemoRsnCd_H3)) {
//            setValue(cMsg.A.no(targetRow).svcMemoRsnNm_A1, ZYPCodeDataUtil.getName(SVC_MEMO_RSN.class, cMsg.glblCmpyCd.getValue(), cMsg.svcMemoRsnCd_H3.getValue()));
//        } else {
//            cMsg.A.no(targetRow).svcMemoRsnNm_A1.clear();
//        }
//        for (int i = targetRow + 1; i < cMsg.A.getValidCount(); i++) {
//            if (cMsg.A.no(i).dsContrPrcEffSqNum_A1.getValue().compareTo(cMsg.A.no(targetRow).dsContrPrcEffSqNum_A1.getValue()) != 0) {
//                break;
//            }
//            setValue(cMsg.A.no(i).svcMemoRsnCd_A3, cMsg.A.no(targetRow).svcMemoRsnCd_A3);
//            setValue(cMsg.A.no(i).svcCmntTxt_A1, cMsg.A.no(targetRow).svcCmntTxt_A1);
//            setValue(cMsg.A.no(i).svcMemoRsnNm_A1, cMsg.A.no(targetRow).svcMemoRsnNm_A1);
//            
//        }
//        //Add End   02/08/2016 <QC#3846>
//    }
    // END 2016/05/20 T.Tomita [QC#4923, DEL]

    //Mod Start 02/21/2016 <QC#17646>
    //Mod Start 02/16/2016 <QC#3844,QC#3845,QC#3846>
    public static boolean isErrorTopSchedule(NSAL0570CMsg cMsg) {
        boolean retFlg = false;
        int cancelCount = 0;
        // mandatory check
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                if (!hasValue(cMsg.A.no(i).contrPrcEffFromDt_A1)) {
                    cMsg.A.no(i).contrPrcEffFromDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Start Date" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).contrPrcEffThruDt_A1)) {
                    cMsg.A.no(i).contrPrcEffThruDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"End Date" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).bllgCycleCd_A3)) {
                    cMsg.A.no(i).bllgCycleCd_A3.setErrorInfo(1, ZZZM9025E, new String[] {"Frequency" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).xsMtrCopyQty_A1)) {
                    cMsg.A.no(i).xsMtrCopyQty_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Allowance" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).xsMtrAmtRate_A1)) {
                    cMsg.A.no(i).xsMtrAmtRate_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Price" });
                    retFlg = true;
                }
            } else {
                cancelCount++;
            }
        }
        // all cancel check
        if (cMsg.A.getValidCount() == cancelCount) {
            cMsg.setMessageInfo(NSAM0370E);
            retFlg = true;
            return retFlg;
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                if (ZYPDateUtil.compare(cMsg.A.no(i).contrPrcEffFromDt_A1.getValue(), cMsg.A.no(i).contrPrcEffThruDt_A1.getValue()) > 0) {
                    cMsg.A.no(i).contrPrcEffFromDt_A1.setErrorInfo(1, NSAM0062E);
                    cMsg.A.no(i).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0062E);
                    cMsg.setMessageInfo(NSAM0062E);
                    retFlg = true;
                }
            }
        }
        if (retFlg) {
            return retFlg;
        }
        int startLine = cMsg.A.length();
        int endLine = -1;
        BigDecimal peSq = BigDecimal.ZERO;
        for (int i = 0; i < cMsg.A.getValidCount();) {
            int curLine = vldLine(cMsg.A, i, peSq);
            if (curLine == -1) {
                break;
            }
            peSq = cMsg.A.no(curLine).dsContrPrcEffSqNum_A1.getValue();
            if (startLine > curLine) {
                startLine = curLine;
            }
            endLine = curLine;
            if (ZYPDateUtil.compare(cMsg.A.no(curLine).contrPrcEffFromDt_A1.getValue(), cMsg.contrEffFromDt_H1.getValue()) < 0) {
                cMsg.A.no(curLine).contrPrcEffFromDt_A1.setErrorInfo(1, NSAM0372E);
                retFlg = true;
            }
            if (ZYPDateUtil.compare(cMsg.A.no(curLine).contrPrcEffThruDt_A1.getValue(), cMsg.contrEffThruDt_H1.getValue()) > 0) {
                cMsg.A.no(curLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0372E);
                retFlg = true;
            }
            String bllgSchdThruDt = NSAL0570Query.getInstance().getMaxBllgSchdThruDt(cMsg.glblCmpyCd.getValue(), cMsg.A.no(curLine).dsContrPrcEffPk_A1.getValue());
            if (ZYPCommonFunc.hasValue(bllgSchdThruDt) && ZYPDateUtil.compare(bllgSchdThruDt, cMsg.A.no(curLine).contrPrcEffThruDt_A1.getValue()) > 0) {
                cMsg.A.no(curLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0375E, new String[] {bllgSchdThruDt});
                retFlg = true;
            }
            int nxtLine = vldLine(cMsg.A, curLine + 1, peSq);
            if (nxtLine == -1) {
                break;
            }
            if (ZYPDateUtil.compare(cMsg.A.no(nxtLine).contrPrcEffFromDt_A1.getValue(), ZYPDateUtil.addDays(cMsg.A.no(curLine).contrPrcEffThruDt_A1.getValue(), 1)) > 0) {
                cMsg.A.no(nxtLine).contrPrcEffFromDt_A1.setErrorInfo(1, NSAM0336E);
                cMsg.A.no(curLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0336E);
                retFlg = true;
            }
            if (ZYPDateUtil.compare(cMsg.A.no(nxtLine).contrPrcEffFromDt_A1.getValue(), ZYPDateUtil.addDays(cMsg.A.no(curLine).contrPrcEffThruDt_A1.getValue(), 1)) < 0) {
                cMsg.A.no(nxtLine).contrPrcEffFromDt_A1.setErrorInfo(1, NSAM0371E);
                cMsg.A.no(curLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0371E);
                retFlg = true;
            }
            if (ZYPDateUtil.compare(cMsg.A.no(nxtLine).contrPrcEffFromDt_A1.getValue(), cMsg.A.no(curLine).contrPrcEffFromDt_A1.getValue()) < 0) {
                cMsg.A.no(nxtLine).contrPrcEffFromDt_A1.setErrorInfo(1, NSAM0373E, new String[] {cMsg.A.no(curLine).contrPrcEffFromDt_A1.getValue()});
                retFlg = true;
            }
            i = nxtLine;
        }
        if (!cMsg.A.no(startLine).contrPrcEffFromDt_A1.getValue().equals(cMsg.contrEffFromDt_H1.getValue())) {
            cMsg.A.no(startLine).contrPrcEffFromDt_A1.setErrorInfo(1, NSAM0336E);
            retFlg = true;
        }
        // Mod Start 2017/11/21 QC#21724
        String contrEffThruDt = NSAL0570Query.getInstance().getContrEffThruDt(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        if (!cMsg.A.no(endLine).contrPrcEffThruDt_A1.getValue().equals(contrEffThruDt)) {
            cMsg.A.no(endLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0336E);
            retFlg = true;
        }
        // Mod End 2017/11/21 QC#21724
        if (retFlg) {
            return retFlg;
        }
        // START 2016/05/20 T.Tomita [QC#4923, MOD]
        boolean editFlg = false;
        // reason / comment check
        if (cMsg.A.getValidCount() == cMsg.B.getValidCount()) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (!isSame(cMsg.A.no(i).contrPrcEffFromDt_A1, cMsg.B.no(i).contrPrcEffFromDt_B1)
                        || !isSame(cMsg.A.no(i).contrPrcEffThruDt_A1, cMsg.B.no(i).contrPrcEffThruDt_B1)
                        || !isSame(cMsg.A.no(i).bllgCycleCd_A3, cMsg.B.no(i).bllgCycleCd_B3)
                        || !isSame(cMsg.A.no(i).xsMtrAmtRate_A1, cMsg.B.no(i).xsMtrAmtRate_B1)
                        || !isSame(cMsg.A.no(i).xsMtrCopyQty_A1, cMsg.B.no(i).xsMtrCopyQty_B1)
                        || !isSame(cMsg.A.no(i).dsContrDtlStsCd_A1, cMsg.B.no(i).dsContrDtlStsCd_B1)
                    ) {
                    editFlg = true;
                    break;
                }
            }
        } else {
            editFlg = true;
        }
        if (!editFlg) {
            return true;
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            boolean isErr = false;
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                BigDecimal curAllowace = BigDecimal.valueOf(-1);
                String fromDt = cMsg.A.no(i).contrPrcEffFromDt_A1.getValue();
                for (int j = i; j < cMsg.A.getValidCount(); j++) {
                    if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(j).dsContrDtlStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(cMsg.A.no(j).contrPrcEffFromDt_A1) && fromDt.equals(cMsg.A.no(j).contrPrcEffFromDt_A1.getValue())) {
                        if (ZYPCommonFunc.hasValue(cMsg.A.no(j).xsMtrCopyQty_A1)) {
                            if (curAllowace.compareTo(cMsg.A.no(j).xsMtrCopyQty_A1.getValue()) >= 0) {
                                cMsg.A.no(j).xsMtrCopyQty_A1.setErrorInfo(1, NSAM0623E);
                                isErr = false;
                            }
                            curAllowace = cMsg.A.no(j).xsMtrCopyQty_A1.getValue();
                        }
                    } else {
                        i = j -1;
                        break;
                    }
                }
            }
            if (isErr == true) {
                return true;
            }
        }
        if (!ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_H3) || !ZYPCommonFunc.hasValue(cMsg.svcCmntTxt_H1)) {
            cMsg.svcMemoRsnCd_H3.setErrorInfo(1, NSAM0388E);
            cMsg.svcCmntTxt_H1.setErrorInfo(1, NSAM0388E);
            retFlg = true;
        }
        // END 2016/05/20 T.Tomita [QC#4923, MOD]
        return retFlg;
    }
    //Mod End  02/21/2016 <QC#17646>

    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    private boolean checkAllowanceSeq(NSAL0570_ACMsgArray aCMsgArray, int stIdx, String fromDt) {
        if (!ZYPCommonFunc.hasValue(fromDt)) {
            return true;
        }

        boolean ret = true;
        BigDecimal curAllowace = BigDecimal.valueOf(-1);
        for (int j = stIdx; j < aCMsgArray.getValidCount(); j++) {
            if (ZYPCommonFunc.hasValue(aCMsgArray.no(j).contrPrcEffFromDt_A1) && fromDt.equals(aCMsgArray.no(j).contrPrcEffFromDt_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(aCMsgArray.no(j).xsMtrCopyQty_A1)) {
                    if (curAllowace.compareTo(aCMsgArray.no(j).xsMtrCopyQty_A1.getValue()) >= 0) {
                        aCMsgArray.no(j).xsMtrCopyQty_A1.setErrorInfo(1, NSAM0623E);
                        ret = false;
                    }
                    curAllowace = aCMsgArray.no(j).xsMtrCopyQty_A1.getValue();
                }
            } else {
                break;
            }
        }

        return ret;
    }
    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]

    private static int vldLine(NSAL0570_ACMsgArray acMsg, int nextLine, BigDecimal peSq) {
        int maxLine = acMsg.getValidCount();
        if (nextLine > maxLine) {
            return -1;
        }
        for (int i = nextLine; i < maxLine; i++) {
            if (peSq.compareTo(acMsg.no(i).dsContrPrcEffSqNum_A1.getValue()) == 0) {
                continue;
            }
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(acMsg.no(i).dsContrDtlStsCd_A1.getValue())) {
                return i;
            }
        }
        return -1;
    }

    // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
    //Mod Start 04/06/2016 <QC#6585>
    public static NSZC047008PMsg createApi(NSAL0570CMsg bizMsg) {
        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, "08");
        setValue(pMsg.slsDt, bizMsg.slsDt);
        setValue(pMsg.dsContrDtlPk, bizMsg.dsContrDtlPk_H1);
        setValue(pMsg.baseChrgFlg, FLG_OFF_N);
        setValue(pMsg.usgChrgFlg, FLG_ON_Y);
        setValue(pMsg.mtrCloDay, bizMsg.mtrCloDay_A);
        setValue(pMsg.mtrBllgTmgCd, bizMsg.mtrBllgTmgCd_A);
        setValue(pMsg.mtrBllgDay, bizMsg.mtrBllgDay_A);
        setValue(pMsg.contrEffFromDt, bizMsg.contrEffFromDt_H1);
        // Mod Start 2017/11/21 QC#21724
        setValue(pMsg.contrEffThruDt, getContrEffThruDt(bizMsg.glblCmpyCd.getValue(), bizMsg.dsContrDtlPk_H1.getValue(), bizMsg.contrEffThruDt_H1.getValue()));
        // Mod End 2017/11/21 QC#21724
        int pIdx = 0;
        BigDecimal prePeSqNum = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (DS_CONTR_CTRL_STS.CANCELLED.equals(bizMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                continue;
            }
            setValue(pMsg.xxMtrLineList.no(pIdx).dsContrPrcEffPk_ML, bizMsg.A.no(i).dsContrPrcEffPk_A1);
            BigDecimal curPeSqNum = bizMsg.A.no(i).dsContrPrcEffSqNum_A1.getValue();
            setValue(pMsg.xxMtrLineList.no(pIdx).dsContrPrcEffSqNum_ML, curPeSqNum);
            setValue(pMsg.xxMtrLineList.no(pIdx).effFromDt_ML, bizMsg.A.no(i).contrPrcEffFromDt_A1);
            setValue(pMsg.xxMtrLineList.no(pIdx).effThruDt_ML, bizMsg.A.no(i).contrPrcEffThruDt_A1);
            setValue(pMsg.xxMtrLineList.no(pIdx).mtrBllgCycleCd_ML, bizMsg.A.no(i).bllgCycleCd_A3);
            setValue(pMsg.xxMtrLineList.no(pIdx).dsContrBllgMtrPk_ML, bizMsg.dsContrBllgMtrPk_H1);
            setValue(pMsg.xxMtrLineList.no(pIdx).contrXsCopyPk_ML, bizMsg.A.no(i).contrXsCopyPk_A1);
            setValue(pMsg.xxMtrLineList.no(pIdx).xsMtrCopyQty_ML, bizMsg.A.no(i).xsMtrCopyQty_A1);
            setValue(pMsg.xxMtrLineList.no(pIdx).xsMtrAmtRate_ML, bizMsg.A.no(i).xsMtrAmtRate_A1);
            if (!hasValue(prePeSqNum) || prePeSqNum.compareTo(curPeSqNum) != 0) {
                setValue(pMsg.xxMtrLineList.no(pIdx).xsMtrFirstFlg_ML, FLG_ON_Y);
            } else {
                setValue(pMsg.xxMtrLineList.no(pIdx).xsMtrFirstFlg_ML, FLG_OFF_N);
            }
            prePeSqNum = curPeSqNum;
            // START 2016/01/06 [QC2783, ADD]
            setValue(pMsg.xxMtrLineList.no(pIdx).dsContrPrcEffStsCd_ML, bizMsg.A.no(i).dsContrPrcEffStsCd_A2);
            setValue(pMsg.xxMtrLineList.no(pIdx).qltyAsrnHldFlg_ML, bizMsg.A.no(i).qltyAsrnHldFlg_A2);
            setValue(pMsg.xxMtrLineList.no(pIdx).mtrHldFlg_ML, bizMsg.A.no(i).mtrHldFlg_A2);
            setValue(pMsg.xxMtrLineList.no(pIdx).contrHldFlg_ML, bizMsg.A.no(i).contrHldFlg_A2);
            setValue(pMsg.xxMtrLineList.no(pIdx).bllgHldFlg_ML, bizMsg.A.no(i).bllgHldFlg_A2);
            // END 2016/01/06 [QC2783, ADD]
            pIdx = pIdx + 1;
        }
        pMsg.xxMtrLineList.setValidCount(pIdx);
        return pMsg;
    }
    //Mod End   04/06/2016 <QC#6585>
    //Mod End   02/16/2016 <QC#3844,QC#3845,QC#3846>

    public static void executeApi(NSZC047008PMsg pMsg) {
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
    }

    //Mod Start 02/16/2016 <QC#3844,QC#3845,QC#3846>
    // START 2016/05/20 T.Tomita [QC#4923, MOD]
    public static void insertSvcMemo(NSAL0570CMsg cMsg) {
//        List<SVC_MEMOTMsg> svcMemoTMsgList = new ArrayList<SVC_MEMOTMsg>();
//        BigDecimal peSq = BigDecimal.ZERO;
//        for (int i = 0; i < cMsg.A.getValidCount();i++) {
//            int curLine = vldLine(cMsg.A, i, peSq);
//            if (curLine == -1) {
//                break;
//            }
//            peSq = cMsg.A.no(i).dsContrPrcEffSqNum_A1.getValue();
//            if (hasValueForTargetLine(cMsg.A.no(curLine)) && changeColumnForTargetLine(cMsg, curLine, peSq)) {
//                SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
//                setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
//                setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
//                setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
//                setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.CHANGE_PRICE_EFFECTIVITY);
//                setValue(tmsg.svcCmntTxt, cMsg.A.no(i).svcCmntTxt_A1);
//                setValue(tmsg.dsContrPk, cMsg.dsContrPk_H1);
//                setValue(tmsg.dsContrDtlPk, cMsg.dsContrDtlPk_H1);
//                setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
//                setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//                setValue(tmsg.svcMemoRsnCd, cMsg.A.no(i).svcMemoRsnCd_A3);
//                BigDecimal dsContrPrcEffPk = cMsg.A.no(i).dsContrPrcEffPk_A1.getValue();
//                if (hasValue(dsContrPrcEffPk)) {
//                    setValue(tmsg.svcMemoTrxNum, dsContrPrcEffPk.toString());
//                }
//                setValue(tmsg.svcMemoTrxNm, "DS_CONTR_PRD_EFF_PK");
//                svcMemoTMsgList.add(tmsg);
//            }
//            i = curLine;
//        }
//        if (svcMemoTMsgList.size() > 0) {
//            SVC_MEMOTMsg[] inMsgArray;
//            inMsgArray = new SVC_MEMOTMsg[svcMemoTMsgList.size()];
//            S21FastTBLAccessor.insert(svcMemoTMsgList.toArray(inMsgArray));
//        }
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.CHANGE_PRICE_EFFECTIVITY);
        setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(tmsg.dsContrPk, cMsg.dsContrPk_H1);
        setValue(tmsg.dsContrDtlPk, cMsg.dsContrDtlPk_H1);
        setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        EZDTBLAccessor.insert(tmsg);
    }
    // END 2016/05/20 T.Tomita [QC#4923, MOD]
    //Mod End   02/16/2016 <QC#3844,QC#3845,QC#3846>
    // END 2015/12/10 T.Kanasaka [QC#1815, MOD]

    // START 2016/06/02 T.Tomita [QC#4923, DEL]
//    private static boolean hasValueForTargetLine(NSAL0570_ACMsg aCMsg) {
//        if (hasValue(aCMsg.contrPrcEffFromDt_A1)
//                && hasValue(aCMsg.contrPrcEffThruDt_A1)
//                && hasValue(aCMsg.bllgCycleCd_A3)
//            ) {
//            return true;
//        }
//        return false;
//    }
//
//    //Mod Start 02/16/2016 <QC#3846>
//    private static boolean changeColumnForTargetLine(NSAL0570CMsg cMsg, int aLine, BigDecimal peSq) {
//        boolean ret = false;
//        if (!hasValue(peSq)) {
//            return ret;
//        }
//        int bLine = -1;
//        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//            if (peSq.compareTo(cMsg.B.no(i).dsContrPrcEffSqNum_B1.getValue()) == 0) {
//                bLine = i;
//                break;
//            }
//        }
//        if (bLine == -1) {
//            return true;
//        }
//        for (int aIdx = aLine; aIdx < cMsg.A.getValidCount(); aIdx++) {
//            if (bLine >= cMsg.B.getValidCount()) {
//                if (peSq.compareTo(cMsg.A.no(aIdx).dsContrPrcEffSqNum_A1.getValue()) != 0) {
//                    ret = true;
//                }
//                break;
//            }
//            if (peSq.compareTo(cMsg.A.no(aIdx).dsContrPrcEffSqNum_A1.getValue()) != 0) {
//                if (peSq.compareTo(cMsg.B.no(bLine).dsContrPrcEffSqNum_B1.getValue()) == 0) {
//                    ret = true;
//                }
//                break;
//            }
//            if (!cMsg.A.no(aIdx).contrPrcEffFromDt_A1.getValue().equals(cMsg.B.no(bLine).contrPrcEffFromDt_B1.getValue())
//                    || !cMsg.A.no(aIdx).contrPrcEffThruDt_A1.getValue().equals(cMsg.B.no(bLine).contrPrcEffThruDt_B1.getValue())
//                    || !cMsg.A.no(aIdx).bllgCycleCd_A3.getValue().equals(cMsg.B.no(bLine).bllgCycleCd_B3.getValue())
//                    || cMsg.A.no(aIdx).xsMtrAmtRate_A1.getValue().compareTo(cMsg.B.no(bLine).xsMtrAmtRate_B1.getValue()) != 0
//                    || cMsg.A.no(aIdx).xsMtrCopyQty_A1.getValue().compareTo(cMsg.B.no(bLine).xsMtrCopyQty_B1.getValue()) != 0
//                ) {
//                ret = true;
//                break;
//            }
//            aLine = aIdx;
//            bLine = bLine + 1;
//        }
//        if (aLine < cMsg.A.getValidCount() && bLine < cMsg.B.getValidCount()) {
//            if (peSq.compareTo(cMsg.B.no(bLine).dsContrPrcEffSqNum_B1.getValue()) == 0) {
//                ret = true;
//            }
//        }
//        return ret;
//    }
//    //Mod End   02/16/2016 <QC#3846>
//
//    public static final void copyAMsgToBMsg(NSAL0570CMsg bizMsg) {
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            setValue(bizMsg.B.no(i).contrPrcEffFromDt_B1, bizMsg.A.no(i).contrPrcEffFromDt_A1);
//            setValue(bizMsg.B.no(i).contrPrcEffThruDt_B1, bizMsg.A.no(i).contrPrcEffThruDt_A1);
//            setValue(bizMsg.B.no(i).bllgCycleCd_B3, bizMsg.A.no(i).bllgCycleCd_A3);
//            // START 2016/05/20 T.Tomita [QC#4923, DEL]
////            setValue(bizMsg.B.no(i).svcCmntTxt_B1, bizMsg.A.no(i).svcCmntTxt_A1);
////            setValue(bizMsg.B.no(i).svcMemoRsnCd_B3, bizMsg.A.no(i).svcMemoRsnCd_A3);
//            // END 2016/05/20 T.Tomita [QC#4923, DEL]
//        }
//    }
    // END 2016/06/02 T.Tomita [QC#4923, DEL]

    public static void updateUnderAgg(NSAL0570CMsg bizMsg) {
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        BigDecimal prntDsContrDtlPk = bizMsg.dsContrDtlPk_H1.getValue();
        BigDecimal dsContrBllgMtrPk = bizMsg.dsContrBllgMtrPk_H1.getValue();
        List<Map<String, Object>> aggLinePeList = NSAL0570Query.getInstance().getAggLinePe(glblCmpyCd, prntDsContrDtlPk, dsContrBllgMtrPk);
        if (aggLinePeList == null || aggLinePeList.size() == 0) {
            return;
        }
        List<Map<String, Object>> aggMachLineContrDtlList = NSAL0570Query.getInstance().getAggMachLineContrDtl(glblCmpyCd, prntDsContrDtlPk, bizMsg.mtrLbCd_H1.getValue());
        if (aggMachLineContrDtlList == null || aggMachLineContrDtlList.size() == 0) {
            return;
        }
        for (Map<String, Object>aggMachLineContrDtl : aggMachLineContrDtlList) {
            BigDecimal machPeSq = null;
            BigDecimal machDtlPk = (BigDecimal) aggMachLineContrDtl.get("DS_CONTR_DTL_PK");
            BigDecimal machBllgMtrPk = NSAL0570Query.getInstance().getMachBllgMtrPk(glblCmpyCd, machDtlPk, bizMsg.mtrLbCd_H1.getValue());
            if (machBllgMtrPk == null) {
                continue;
            }
            int apiIdx = 0;
            NSZC047008PMsg inPMsg = new NSZC047008PMsg();
            inPMsg = createApiParamHdr(bizMsg, aggMachLineContrDtl);
            for (Map<String, Object>aggLinePe : aggLinePeList) {
                String machDtlFromDt = (String) aggMachLineContrDtl.get("CONTR_EFF_FROM_DT");
                String machDtlThruDt = (String) aggMachLineContrDtl.get("PE_EFF_THRU_DT");
                String aggPeFromDt = (String) aggLinePe.get("CONTR_PRC_EFF_FROM_DT");
                String aggPeThruDt = (String) aggLinePe.get("CONTR_PRC_EFF_THRU_DT");
                if (aggPeThruDt.compareTo(machDtlFromDt) < 0) {
                    continue;
                }
                if (aggPeFromDt.compareTo(machDtlThruDt) > 0) {
                    continue;
                }
                Map<String, Object> targetMachPe = getTargetMachPe(glblCmpyCd, machDtlPk, machBllgMtrPk, aggPeFromDt, aggPeThruDt);
                if (targetMachPe == null) {
                    continue;
                }
                BigDecimal aggPePk = (BigDecimal) aggLinePe.get("DS_CONTR_PRC_EFF_PK");
                List<Map<String, Object>> aggPeMtrList = NSAL0570Query.getInstance().getPeMtr(glblCmpyCd, aggPePk, dsContrBllgMtrPk);
                if (aggPeMtrList == null || aggPeMtrList.size() == 0) {
                    continue;
                }
                BigDecimal machPePk = (BigDecimal) targetMachPe.get("DS_CONTR_PRC_EFF_PK");
                List<Map<String, Object>> machPeMtrList = NSAL0570Query.getInstance().getPeMtr(glblCmpyCd, machPePk, machBllgMtrPk);
                if (machPeMtrList == null || machPeMtrList.size() == 0) {
                    continue;
                }
                if (machPeSq == null) {
                    machPeSq = (BigDecimal) targetMachPe.get("DS_CONTR_PRC_EFF_SQ_NUM");
                } else {
                    machPeSq = machPeSq.add(BigDecimal.ONE);
                }
                String fromDt = aggPeFromDt;
                if (fromDt.compareTo(machDtlFromDt) < 0) {
                    fromDt = machDtlFromDt;
                }
                String thruDt = aggPeThruDt;
                if (thruDt.compareTo(machDtlThruDt) > 0) {
                    thruDt = machDtlThruDt;
                }
                int curMachPeMtrIdx = 0;
                for (Map<String, Object> aggPeMtr : aggPeMtrList) {
                    if (machPeMtrList.size() < curMachPeMtrIdx + 1) {
                        break;
                    }
                    Map<String, Object> machPeMtr = machPeMtrList.get(curMachPeMtrIdx);
                    inPMsg.xxMtrLineList.no(apiIdx).clear();
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).dsContrPrcEffSqNum_ML, machPeSq);
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).effFromDt_ML, fromDt);
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).effThruDt_ML, thruDt);
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).mtrBllgCycleCd_ML, (String) aggLinePe.get("BLLG_CYCLE_CD"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).dsContrBllgMtrPk_ML, machBllgMtrPk);
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).contrXsCopyPk_ML, (BigDecimal) machPeMtr.get("CONTR_XS_COPY_PK"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).xsMtrCopyQty_ML, (BigDecimal) machPeMtr.get("XS_MTR_COPY_QTY"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).xsMtrAmtRate_ML, (BigDecimal) aggPeMtr.get("XS_MTR_AMT_RATE"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).xsMtrFirstFlg_ML, (String) machPeMtr.get("XS_MTR_FIRST_FLG"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).dsContrPrcEffStsCd_ML, (String) targetMachPe.get("DS_CONTR_PRC_EFF_STS_CD"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).qltyAsrnHldFlg_ML, (String) targetMachPe.get("QLTY_ASRN_HLD_FLG"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).mtrHldFlg_ML, (String) targetMachPe.get("MTR_HLD_FLG"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).contrHldFlg_ML, (String) targetMachPe.get("CONTR_HLD_FLG"));
                    setValue(inPMsg.xxMtrLineList.no(apiIdx).bllgHldFlg_ML, (String) targetMachPe.get("BLLG_HLD_FLG"));
                    apiIdx++;
                    curMachPeMtrIdx++;
                    inPMsg.xxMtrLineList.setValidCount(apiIdx);
                }
            }
            executeApi(inPMsg);
            createXsCopy(bizMsg, machDtlPk, machBllgMtrPk);
        }
    }

    private static Map<String, Object> getTargetMachPe(String glblCmpyCd, BigDecimal dsContrDtlPk,  BigDecimal dsContrBllgMtrPk, String fromDt, String tartgetThruDt) {
        Map<String, Object> machPe = NSAL0570Query.getInstance().getMachPeIncl(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, fromDt);
        if (machPe != null && machPe.size() != 0) {
            return machPe;
        }
        Map<String, Object> machPeNext = NSAL0570Query.getInstance().getMachPeNext(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, fromDt, tartgetThruDt);
        if (machPeNext == null) {
            return null;
        }
        machPe = NSAL0570Query.getInstance().getMachPeIncl(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, (String) machPeNext.get("CONTR_PRC_EFF_FROM_DT"));
        if (machPe != null) {
            return machPe;
        }
        return null;
    }

    // START 2016/05/20 T.Tomita [QC#4923, DEL]
//    private static int getMaxPrcEffSeqNo(NSAL0570CMsg bizMsg, int maxPeSeqNo, BigDecimal currDsContrDtlPk) {
//        for (int k = 0; k < bizMsg.C.getValidCount(); k++) {
//            NSAL0570_CCMsg machPrcEff1 = bizMsg.C.no(k);
//            if (currDsContrDtlPk.equals(machPrcEff1.dsContrDtlPk_C.getValue())) {
//                maxPeSeqNo = machPrcEff1.dsContrPrcEffSqNum_C.getValueInt();
//            } else {
//                break;
//            }
//        }
//        return maxPeSeqNo;
//    }
//
//    private static Map<String, Object> setMachPrcEffParam(NSAL0570CMsg bizMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg) {
//        Map<String, Object> queryMap = new HashMap<String, Object>();
//        queryMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryMap.put("prntDsContrDtlPk", aggPrcEffTMsg.dsContrDtlPk.getValue());
//        queryMap.put("useChrgFlg", FLG_ON_Y);
//        queryMap.put("fromDt", aggPrcEffTMsg.contrPrcEffThruDt.getValue());
//        queryMap.put("thruDt", aggPrcEffTMsg.contrPrcEffFromDt.getValue());
//        return queryMap;
//    }
    // END 2016/05/20 T.Tomita [QC#4923, DEL]

    // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
    public static NSZC047008PMsg createApiParamHdr(NSAL0570CMsg cMsg, Map<String, Object> aggMachLineContrDtl) {
        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, "08");
        setValue(pMsg.slsDt, cMsg.slsDt);
        setValue(pMsg.dsContrDtlPk, (BigDecimal) aggMachLineContrDtl.get("DS_CONTR_DTL_PK"));
        setValue(pMsg.baseChrgFlg, FLG_OFF_N);
        setValue(pMsg.usgChrgFlg, FLG_ON_Y);
        setValue(pMsg.mtrCloDay, cMsg.mtrCloDay_A);
        setValue(pMsg.mtrBllgTmgCd, cMsg.mtrBllgTmgCd_A);
        setValue(pMsg.mtrBllgDay, cMsg.mtrBllgDay_A);
        setValue(pMsg.contrEffFromDt, (String) aggMachLineContrDtl.get("CONTR_EFF_FROM_DT"));
        // Mod Start 2017/11/21 QC#21724
        BigDecimal dsContrDtlPk = (BigDecimal) aggMachLineContrDtl.get("DS_CONTR_DTL_PK");
        String contrEffThruDt = (String) aggMachLineContrDtl.get("CONTR_EFF_THRU_DT");
        setValue(pMsg.contrEffThruDt, getContrEffThruDt(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, contrEffThruDt));
        // Mod End 2017/11/21 QC#21724
        return pMsg;
    }

    public static NSZC047008PMsg createApiParamDtl1(NSZC047008PMsg pMsg, NSAL0570_CCMsg cCMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg) {
        NSZC047008PMsg inPMsg = pMsg;
        int addRow = inPMsg.xxMtrLineList.getValidCount();
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffPk_ML, cCMsg.dsContrPrcEffPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffSqNum_ML, cCMsg.dsContrPrcEffSqNum_C);
        setValue(pMsg.xxMtrLineList.no(addRow).effFromDt_ML, cCMsg.contrPrcEffFromDt_C);
        setValue(pMsg.xxMtrLineList.no(addRow).effThruDt_ML, aggPrcEffTMsg.contrPrcEffThruDt);
        setValue(pMsg.xxMtrLineList.no(addRow).mtrBllgCycleCd_ML, cCMsg.bllgCycleCd_C);
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrBllgMtrPk_ML, cCMsg.dsContrBllgMtrPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).contrXsCopyPk_ML, cCMsg.contrXsCopyPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrCopyQty_ML, cCMsg.xsMtrCopyQty_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrAmtRate_ML, cCMsg.xsMtrAmtRate_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrFirstFlg_ML, cCMsg.xsMtrFirstFlg_C);
        // START 2016/01/06 [QC2783, ADD]
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffStsCd_ML, cCMsg.dsContrPrcEffStsCd_C);
        setValue(pMsg.xxMtrLineList.no(addRow).qltyAsrnHldFlg_ML, cCMsg.qltyAsrnHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).mtrHldFlg_ML, cCMsg.mtrHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).contrHldFlg_ML, cCMsg.contrHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).bllgHldFlg_ML, cCMsg.bllgHldFlg_C);
        // END 2016/01/06 [QC2783, ADD]
        return pMsg;
    }

    public static NSZC047008PMsg createApiParamDtl3(NSZC047008PMsg pMsg, NSAL0570_CCMsg cCMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg, int maxPeSeqNo, NSAL0570CMsg cMsg) {
        NSZC047008PMsg inPMsg = pMsg;
        int addRow = inPMsg.xxMtrLineList.getValidCount();
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffSqNum_ML, BigDecimal.valueOf(maxPeSeqNo));
        setValue(pMsg.xxMtrLineList.no(addRow).effFromDt_ML, aggPrcEffTMsg.contrPrcEffFromDt);
        setValue(pMsg.xxMtrLineList.no(addRow).effThruDt_ML, aggPrcEffTMsg.contrPrcEffThruDt);
        setValue(pMsg.xxMtrLineList.no(addRow).mtrBllgCycleCd_ML, aggPrcEffTMsg.bllgCycleCd);
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrBllgMtrPk_ML, cCMsg.dsContrBllgMtrPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).contrXsCopyPk_ML, cCMsg.contrXsCopyPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrCopyQty_ML, cCMsg.xsMtrCopyQty_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrAmtRate_ML, NSAL0570Query.getxsMtrAmtRate(cMsg, aggPrcEffTMsg));
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrFirstFlg_ML, cCMsg.xsMtrFirstFlg_C);
        // START 2016/01/06 [QC2783, ADD]
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffStsCd_ML, cCMsg.dsContrPrcEffStsCd_C);
        setValue(pMsg.xxMtrLineList.no(addRow).qltyAsrnHldFlg_ML, cCMsg.qltyAsrnHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).mtrHldFlg_ML, cCMsg.mtrHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).contrHldFlg_ML, cCMsg.contrHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).bllgHldFlg_ML, cCMsg.bllgHldFlg_C);
        // END 2016/01/06 [QC2783, ADD]
        return pMsg;
    }

    public static NSZC047008PMsg createApiParamDtl4(NSZC047008PMsg pMsg, NSAL0570_CCMsg cCMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg, int maxPeSeqNo, NSAL0570CMsg cMsg) {
        NSZC047008PMsg inPMsg = pMsg;
        int addRow = inPMsg.xxMtrLineList.getValidCount();
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffSqNum_ML, BigDecimal.valueOf(maxPeSeqNo));
        setValue(pMsg.xxMtrLineList.no(addRow).effFromDt_ML, aggPrcEffTMsg.contrPrcEffFromDt);
        setValue(pMsg.xxMtrLineList.no(addRow).effThruDt_ML, cCMsg.contrEffThruDt_C);
        setValue(pMsg.xxMtrLineList.no(addRow).mtrBllgCycleCd_ML, aggPrcEffTMsg.bllgCycleCd);
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrBllgMtrPk_ML, cCMsg.dsContrBllgMtrPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).contrXsCopyPk_ML, cCMsg.contrXsCopyPk_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrCopyQty_ML, cCMsg.xsMtrCopyQty_C);
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrAmtRate_ML, NSAL0570Query.getxsMtrAmtRate(cMsg, aggPrcEffTMsg));
        setValue(pMsg.xxMtrLineList.no(addRow).xsMtrFirstFlg_ML, cCMsg.xsMtrFirstFlg_C);
        // START 2016/01/06 [QC2783, ADD]
        setValue(pMsg.xxMtrLineList.no(addRow).dsContrPrcEffStsCd_ML, cCMsg.dsContrPrcEffStsCd_C);
        setValue(pMsg.xxMtrLineList.no(addRow).qltyAsrnHldFlg_ML, cCMsg.qltyAsrnHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).mtrHldFlg_ML, cCMsg.mtrHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).contrHldFlg_ML, cCMsg.contrHldFlg_C);
        setValue(pMsg.xxMtrLineList.no(addRow).bllgHldFlg_ML, cCMsg.bllgHldFlg_C);
        // END 2016/01/06 [QC2783, ADD]
        return pMsg;
    }
    // END 2015/12/10 T.Kanasaka [QC#1815, MOD]
    //Add Start 04/06/2016 <QC#6585>
    public static final void copyPeBlock(NSAL0570CMsg bizMsg) {
        BigDecimal prePeSqNum = null;
        NSAL0570_ACMsg preLine = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BigDecimal curPeSqNum = bizMsg.A.no(i).dsContrPrcEffSqNum_A1.getValue();
            if (!hasValue(prePeSqNum) || prePeSqNum.compareTo(curPeSqNum) != 0) {
                preLine = bizMsg.A.no(i);
                prePeSqNum = curPeSqNum;
            } else {
                setValue(bizMsg.A.no(i).contrPrcEffFromDt_A1, preLine.contrPrcEffFromDt_A1);
                setValue(bizMsg.A.no(i).contrPrcEffThruDt_A1, preLine.contrPrcEffThruDt_A1);
                setValue(bizMsg.A.no(i).bllgCycleCd_A3, preLine.bllgCycleCd_A3);
                setValue(bizMsg.A.no(i).bllgMinCnt_A1, preLine.bllgMinCnt_A1);
                setValue(bizMsg.A.no(i).bllgMinAmtRate_A1, preLine.bllgMinAmtRate_A1);
                setValue(bizMsg.A.no(i).bllgRollOverRatio_A1, preLine.bllgRollOverRatio_A1);
                setValue(bizMsg.A.no(i).bllgFreeCopyCnt_A1, preLine.bllgFreeCopyCnt_A1);
                setValue(bizMsg.A.no(i).dsContrDtlStsCd_A1, preLine.dsContrDtlStsCd_A1);
                setValue(bizMsg.A.no(i).dsContrDtlStsNm_A1, preLine.dsContrDtlStsNm_A1);
                setValue(bizMsg.A.no(i).cratDt_A1, preLine.cratDt_A1);
                // START 2016/05/20 T.Tomita [QC#4923, DEL]
//                setValue(bizMsg.A.no(i).svcMemoRsnNm_A1, preLine.svcMemoRsnNm_A1);
//                setValue(bizMsg.A.no(i).svcMemoRsnCd_A3, preLine.svcMemoRsnCd_A3);
//                setValue(bizMsg.A.no(i).svcCmntTxt_A1, preLine.svcCmntTxt_A1);
                // END 2016/05/20 T.Tomita [QC#4923, DEL]
                setValue(bizMsg.A.no(i).dsContrPrcEffPk_A1, preLine.dsContrPrcEffPk_A1);
                setValue(bizMsg.A.no(i).dsContrPrcEffStsCd_A2, preLine.dsContrPrcEffStsCd_A2);
                setValue(bizMsg.A.no(i).qltyAsrnHldFlg_A2, preLine.qltyAsrnHldFlg_A2);
                setValue(bizMsg.A.no(i).mtrHldFlg_A2, preLine.mtrHldFlg_A2);
                setValue(bizMsg.A.no(i).contrHldFlg_A2, preLine.contrHldFlg_A2);
                setValue(bizMsg.A.no(i).bllgHldFlg_A2, preLine.bllgHldFlg_A2);
                setValue(bizMsg.A.no(i).ezUpTime_A1, preLine.ezUpTime_A1);
                setValue(bizMsg.A.no(i).ezUpTimeZone_A1, preLine.ezUpTimeZone_A1);
                // START 2020/03/12 K.Kitachi [QC#55662, DEL]
//                setValue(bizMsg.A.no(i).contrXsCopyPk_A1, preLine.contrXsCopyPk_A1);
                // END 2020/03/12 K.Kitachi [QC#55662, DEL]
            }
        }
    }
    //Add End   04/06/2016 <QC#6585>
    // Add Start 02/16/2016 <QC#2063>
    public static boolean createXsCopy(NSAL0570CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFFTMsgArray prcEffArray = NSAL0570Query.getDsContrPrcEff(cMsg, dsContrDtlPk, dsContrBllgMtrPk);
        if (prcEffArray.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Price Effectivity" });
            return false;
        }
        boolean updFlg = false;
        String slsDt = cMsg.slsDt.getValue();
        String targetDt = slsDt;
        DS_CONTR_PRC_EFFTMsg prcEff = null;
        // Mod Start 2017/11/21 QC#21724
        String contrEffThruDt = NSAL0570Query.getInstance().getContrEffThruDt(cMsg.glblCmpyCd.getValue(), dsContrDtlPk);
        if (slsDt.compareTo(cMsg.contrEffFromDt_H1.getValue()) < 0) {
            targetDt = cMsg.contrEffFromDt_H1.getValue();
        } else if (slsDt.compareTo(contrEffThruDt) > 0) {
            targetDt = contrEffThruDt;
        }
        // Mod End 2017/11/21 QC#21724
        for (int i = 0; i < prcEffArray.getValidCount(); i++) {
            if (targetDt.compareTo(prcEffArray.no(i).contrPrcEffFromDt.getValue()) >= 0 && targetDt.compareTo(prcEffArray.no(i).contrPrcEffThruDt.getValue()) <= 0) {
                prcEff = prcEffArray.no(i);
            }
        }
        if (prcEff == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Price Effectivity" });
            return false;
        }
        
        CONTR_XS_COPYTMsgArray xsCopyArray = NSAL0570Query.getInstance().getContrXsCopy(cMsg.glblCmpyCd.getValue(), dsContrBllgMtrPk);
        // START 2020/03/12 K.Kitachi [QC#55662, MOD]
//        DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrArray = NSAL0570Query.getPrcEffMtr(prcEff);
        DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrArray = NSAL0570Query.getPrcEffMtrForUpdate(prcEff);
        // END 2020/03/12 K.Kitachi [QC#55662, MOD]
        if (xsCopyArray.getValidCount() != prcEffMtrArray.getValidCount()) {
            updFlg = true;
        } else {
            for (int i = 0; i < prcEffMtrArray.getValidCount(); i++) {
                if (xsCopyArray.no(i).xsMtrAmtRate.getValue().compareTo(prcEffMtrArray.no(i).xsMtrAmtRate.getValue()) != 0
                        || xsCopyArray.no(i).xsMtrCopyQty.getValue().compareTo(prcEffMtrArray.no(i).xsMtrCopyQty.getValue()) != 0
                        || !xsCopyArray.no(i).xsMtrFirstFlg.getValue().equals(prcEffMtrArray.no(i).xsMtrFirstFlg.getValue())) {
                    updFlg = true;
                    break;
                }
                // START 2020/03/12 K.Kitachi [QC#55662, ADD]
                if (!hasValue(prcEffMtrArray.no(i).contrXsCopyPk)) {
                    ZYPEZDItemValueSetter.setValue(prcEffMtrArray.no(i).contrXsCopyPk, xsCopyArray.no(i).contrXsCopyPk);
                    EZDTBLAccessor.update(prcEffMtrArray.no(i));
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEffMtrArray.no(i).getReturnCode())) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective Meter" });
                    }
                }
                // END 2020/03/12 K.Kitachi [QC#55662, ADD]
            }
        }
        if (!updFlg) {
            return true;
        }
        for (int i = 0; i < xsCopyArray.getValidCount(); i++) {
            EZDTBLAccessor.remove(xsCopyArray.no(i));
        }
        for (int i = 0; i < prcEffMtrArray.getValidCount(); i++) {
            CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, prcEffMtrArray.no(i).glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.contrXsCopyPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrPk, prcEffMtrArray.no(i).dsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(inMsg.xsMtrCopyQty, prcEffMtrArray.no(i).xsMtrCopyQty);
            ZYPEZDItemValueSetter.setValue(inMsg.xsMtrAmtRate, prcEffMtrArray.no(i).xsMtrAmtRate);
            ZYPEZDItemValueSetter.setValue(inMsg.xsMtrFirstFlg, prcEffMtrArray.no(i).xsMtrFirstFlg);
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Excess Copy" });
            }
            // START 2020/03/12 K.Kitachi [QC#55662, ADD]
            ZYPEZDItemValueSetter.setValue(prcEffMtrArray.no(i).contrXsCopyPk, inMsg.contrXsCopyPk);
            EZDTBLAccessor.update(prcEffMtrArray.no(i));
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEffMtrArray.no(i).getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective Meter" });
            }
            // END 2020/03/12 K.Kitachi [QC#55662, ADD]
        }
        return true;
    }

    // Add End   02/16/2016 <QC#2063>

    // START 2016/06/02 T.Tomita [QC#4923, ADD]
    private static boolean isSame(EZDCStringItem val1, EZDCStringItem val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && !hasValue(val2)) {
            return false;
        }
        if (!hasValue(val1) && hasValue(val2)) {
            return false;
        }
        if (val1.getValue().equals(val2.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isSame(EZDCBigDecimalItem val1, EZDCBigDecimalItem val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && !hasValue(val2)) {
            return false;
        }
        if (!hasValue(val1) && hasValue(val2)) {
            return false;
        }
        if (val1.getValue().compareTo(val2.getValue()) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isSame(EZDCDateItem val1, EZDCDateItem val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && !hasValue(val2)) {
            return false;
        }
        if (!hasValue(val1) && hasValue(val2)) {
            return false;
        }
        if (val1.getValue().equals(val2.getValue())) {
            return true;
        }
        return false;
    }
    // END 2016/06/02 T.Tomita [QC#4923, ADD]

    // START 2017/05/23 Y.Osawa [QC#18560, ADD]
    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END   2017/05/23 Y.Osawa [QC#18560, ADD]
    // Add Start 2017/11/21 QC#21724
    private static String getContrEffThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk, String contrEffThruDt) {
        if (!hasValue(glblCmpyCd) || !hasValue(dsContrDtlPk)) {
            return contrEffThruDt;
        }
        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0570Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return contrEffThruDt;
        }

        if (!hasValue(dsContrDtlTMsg.contrCloDt)) {
            return contrEffThruDt;
        }
        return dsContrDtlTMsg.contrCloDt.getValue();
    }
    // Add End 2017/11/21 QC#21724
}
