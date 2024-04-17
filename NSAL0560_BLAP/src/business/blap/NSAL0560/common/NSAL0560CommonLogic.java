/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0560.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0560.constant.NSAL0560Constant.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.dbcommon.EZDTBLAccessor;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NSAL0560.NSAL0560CMsg;
import business.blap.NSAL0560.NSAL0560Query;
import business.blap.NSAL0560.NSAL0560_ACMsg;
import business.blap.NSAL0560.NSAL0560_ACMsgArray;
import business.blap.NSAL0560.NSAL0560_BCMsg;
import business.blap.NSAL0560.NSAL0560_CCMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047021PMsg;

/**
 *<pre>
 * Base Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC1427
 * 2015/12/07   Hitachi         T.Kanasaka      Update          QC#1472
 * 2015/12/07   Hitachi         T.Kanasaka      Update          QC#1432
 * 2015/12/10   Hitachi         T.Kanasaka      Update          QC#1815
 * 2015/12/10   Hitachi         T.Kanasaka      Update          QC#1439
 * 2016/01/06   Hitachi         K.Yamada        Update          CSA QC#2783
 * 2016/02/08   Hitachi         K.Kishimoto     Update          QC#3884, QC#3891, QC#3898
 * 2016/05/17   Hitachi         T.Tomita        Update          QC#3891
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#4961
 * 2016/08/09   Hitachi         K.Kishimoto     Update          QC#12310
 * 2017/05/23   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/08/21   Hitachi         K.Kitachi       Update          QC#20061
 * 2017/09/01   Hitachi         K.Yamada        Update          QC#20838
 * 2017/11/09   Hitachi         K.Kojima        Update          QC#22438
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 *</pre>
 */
public class NSAL0560CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0560CMsg
     */
    public static void createPullDown(NSAL0560CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_A1, cMsg.bllgCycleNm_A2);
        // START 2017/05/23 Y.Osawa [QC#18560, ADD]
        deletePulldownList(cMsg.bllgCycleCd_A1, cMsg.bllgCycleNm_A2, BLLG_CYCLE.DAILY);
        // END   2017/05/23 Y.Osawa [QC#18560, ADD]
        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
//        ZYPCodeDataUtil.createPulldownList(SVC_MEMO_RSN.class, cMsg.svcMemoRsnCd_A1, cMsg.svcMemoRsnNm_A2);
        createSvcMemoRsnPullDown(cMsg);
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
    }

    // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
    private static void createSvcMemoRsnPullDown(NSAL0560CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.CHANGE_PRICE_EFFECTIVITY);
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
    // END 2015/12/07 T.Kanasaka [QC#1472, MOD]

    public static BigDecimal getNextSeqNum(NSAL0560_ACMsg lastAcMsg) {
        return lastAcMsg.dsContrPrcEffSqNum_A1.getValue().add(BigDecimal.ONE);
    }

    // del start 2016/05/17 CSA Defect#3891
//    public static void setReasonComment(NSAL0560CMsg cMsg) {
//        int targetRow = cMsg.xxRadioBtn_A.getValueInt();
//        ZYPEZDItemValueSetter.setValue(cMsg.A.no(targetRow).svcMemoRsnCd_A3, cMsg.svcMemoRsnCd_H3);
//        ZYPEZDItemValueSetter.setValue(cMsg.A.no(targetRow).svcCmntTxt_A1, cMsg.svcCmntTxt_H1);
//        //Add Start 02/08/2016 <QC#3891>
//        if (hasValue(cMsg.svcMemoRsnCd_H3)) {
//            setValue(cMsg.A.no(targetRow).svcMemoRsnNm_A1, ZYPCodeDataUtil.getName(SVC_MEMO_RSN.class, cMsg.glblCmpyCd.getValue(), cMsg.svcMemoRsnCd_H3.getValue()));
//        } else {
//            cMsg.A.no(targetRow).svcMemoRsnNm_A1.clear();
//        }
//        //Add End   02/08/2016 <QC#3891>
//    }
    // del end 2016/05/17 CSA Defect#3891

    public static boolean existsDsContrPrcEff(NSAL0560CMsg cMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).dsContrPrcEffPk_A1)) {
                    cMsg.setMessageInfo(NSAM0135E, new String[] {"New Line" });
                    return false;
                }
            }
        }
        return true;
    }

    // Mod Start 02/08/2016 <QC#3884, QC#3891, QC#3898>
    public static boolean isErrorTopSchedule(NSAL0560CMsg cMsg) {
        boolean retFlg = false;
        int cancelCount = 0;
        // mandatory check
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPrcEffFromDt_A1)) {
                    cMsg.A.no(i).contrPrcEffFromDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Start Date" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPrcEffThruDt_A1)) {
                    cMsg.A.no(i).contrPrcEffThruDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"End Date" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).bllgCycleCd_A3)) {
                    cMsg.A.no(i).bllgCycleCd_A3.setErrorInfo(1, ZZZM9025E, new String[] {"Frequency" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).basePrcDealAmt_A1)) {
                    cMsg.A.no(i).basePrcDealAmt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Price/Period" });
                    retFlg = true;
                }
                // START 2017/08/21 K.Kitachi [QC#20061, ADD]
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).basePrcTermDealAmtRate_A1)) {
                    cMsg.A.no(i).basePrcTermDealAmtRate_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Term Amount" });
                    retFlg = true;
                }
                // END 2017/08/21 K.Kitachi [QC#20061, ADD]
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
        // logical check
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
        for (int i = 0; i < cMsg.A.getValidCount();) {
            int curLine = vldLine(cMsg.A, i);
            if (curLine == -1) {
                break;
            }
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
            String bllgSchdThruDt = NSAL0560Query.getInstance().getMaxBllgSchdThruDt(cMsg.glblCmpyCd.getValue(), cMsg.A.no(curLine).dsContrPrcEffPk_A1.getValue());
            if (ZYPCommonFunc.hasValue(bllgSchdThruDt) && ZYPDateUtil.compare(bllgSchdThruDt, cMsg.A.no(curLine).contrPrcEffThruDt_A1.getValue()) > 0) {
                cMsg.A.no(curLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0375E, new String[] {bllgSchdThruDt});
                retFlg = true;
            }
            int nxtLine = vldLine(cMsg.A, curLine + 1);
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
        String contrEffThruDt = NSAL0560Query.getInstance().getContrEffThruDt(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        if (!cMsg.A.no(endLine).contrPrcEffThruDt_A1.getValue().equals(contrEffThruDt)) {
            cMsg.A.no(endLine).contrPrcEffThruDt_A1.setErrorInfo(1, NSAM0336E);
            retFlg = true;
        }
        // Mod End 2017/11/21 QC#21724
        if (retFlg) {
            return retFlg;
        }

        // mod start 2016/05/17 CSA Defect#3891
        boolean editFlg = false;
        // reason / comment check
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (hasValueForTargetLine(cMsg.A.no(i)) && changeColumnForTargetLine(cMsg.A.no(i), cMsg.B.no(i))) {
                editFlg = true;
                break;
            }
        }
        // START 2016/05/20 T.Tomita [QC#4923, ADD]
        if (!editFlg) {
            return true;
        }
        // END 2016/05/20 T.Tomita [QC#4923, ADD]
        if (editFlg && (!ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_H3) || !ZYPCommonFunc.hasValue(cMsg.svcCmntTxt_H1))) {
            cMsg.svcMemoRsnCd_H3.setErrorInfo(1,NSAM0388E);
            cMsg.svcCmntTxt_H1.setErrorInfo(1,NSAM0388E);
            retFlg = true;
        }

        // mod end 2016/05/17 CSA Defect#3891
        return retFlg;
    }

    private static int vldLine(NSAL0560_ACMsgArray acMsg, int nextLine) {
        int maxLine = acMsg.getValidCount();
        if (nextLine > maxLine) {
            return -1;
        }
        for (int i = nextLine; i < maxLine; i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(acMsg.no(i).dsContrDtlStsCd_A1.getValue())) {
                return i;
            }
        }
        return -1;
    }
    // Mod End   02/08/2016 <QC#3884, QC#3891, QC#3898>

    // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
    public static NSZC047008PMsg createApi(NSAL0560CMsg bizMsg) {
        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "08");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, bizMsg.dsContrDtlPk_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
        DS_CONTR_DTLTMsg tmsg = NSAL0560Query.getInstance().getDsContrDtl(bizMsg.glblCmpyCd.getValue(), bizMsg.dsContrDtlPk_H1.getValue());
        if (tmsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, tmsg.contrCloDay);
            ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, tmsg.baseBllgTmgCd);
            ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, tmsg.contrBllgDay);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, bizMsg.contrEffFromDt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, getContrEffThruDt(bizMsg.glblCmpyCd.getValue(), bizMsg.dsContrDtlPk_H1.getValue(), bizMsg.contrEffThruDt_H1.getValue()));
        int pIdx = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            //Add Start 02/08/2016 <QC#3898>
            if (DS_CONTR_CTRL_STS.CANCELLED.equals(bizMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                continue;
            }
            //Add End 02/08/2016 <QC#3898>
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).dsContrPrcEffPk_BL, bizMsg.A.no(i).dsContrPrcEffPk_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).dsContrPrcEffSqNum_BL, bizMsg.A.no(i).dsContrPrcEffSqNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).effFromDt_BL, bizMsg.A.no(i).contrPrcEffFromDt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).effThruDt_BL, bizMsg.A.no(i).contrPrcEffThruDt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).baseBllgCycleCd_BL, bizMsg.A.no(i).bllgCycleCd_A3);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).basePrcDealAmt_BL, bizMsg.A.no(i).basePrcDealAmt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).basePrcTermDealAmtRate_BL, bizMsg.A.no(i).basePrcTermDealAmtRate_A1);
            // mod start 2016/01/06 CSA Defect#2783
            //ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).dsContrPrcEffStsCd_BL, bizMsg.A.no(i).dsContrDtlStsCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).dsContrPrcEffStsCd_BL, bizMsg.A.no(i).dsContrPrcEffStsCd_A2);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).qltyAsrnHldFlg_BL, bizMsg.A.no(i).qltyAsrnHldFlg_A2);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).mtrHldFlg_BL, bizMsg.A.no(i).mtrHldFlg_A2);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).contrHldFlg_BL, bizMsg.A.no(i).contrHldFlg_A2);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(pIdx).bllgHldFlg_BL, bizMsg.A.no(i).bllgHldFlg_A2);
            // mod end 2016/01/06 CSA Defect#2783
            pIdx = pIdx + 1;
        }
        pMsg.xxBaseLineList.setValidCount(pIdx);
        return pMsg;
    }

    public static void executeApi(NSZC047008PMsg pMsg) {
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
    }

    // mod start 2016/05/17 CSA Defect#3891
    public static void insertSvcMemo(NSAL0560CMsg cMsg) {
//        List<SVC_MEMOTMsg> svcMemoTMsgList = new ArrayList<SVC_MEMOTMsg>();
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            if (hasValueForTargetLine(cMsg.A.no(i)) && changeColumnForTargetLine(cMsg.A.no(i), cMsg.B.no(i))) {
//                SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
//                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.CHANGE_PRICE_EFFECTIVITY);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcCmntTxt, cMsg.A.no(i).svcCmntTxt_A1);
//                ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, NSAL0560Query.getInstance().getDsContrPk(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue()));
//                ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, cMsg.dsContrDtlPk_H1);
//                ZYPEZDItemValueSetter.setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
//                ZYPEZDItemValueSetter.setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoRsnCd, cMsg.A.no(i).svcMemoRsnCd_A3);
//                BigDecimal dsContrPrcEffPk = cMsg.A.no(i).dsContrPrcEffPk_A1.getValue();
//                if (ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
//                    ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTrxNum, dsContrPrcEffPk.toString());
//                }
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTrxNm, "DS_CONTR_PRD_EFF_PK");
//                svcMemoTMsgList.add(tmsg);
//            }
//        }
//        if (svcMemoTMsgList.size() > 0) {
//            SVC_MEMOTMsg[] inMsgArray;
//            inMsgArray = new SVC_MEMOTMsg[svcMemoTMsgList.size()];
//            S21FastTBLAccessor.insert(svcMemoTMsgList.toArray(inMsgArray));
//        }
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.CHANGE_PRICE_EFFECTIVITY);
        ZYPEZDItemValueSetter.setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, NSAL0560Query.getInstance().getDsContrPk(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, cMsg.dsContrDtlPk_H1);
        ZYPEZDItemValueSetter.setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        ZYPEZDItemValueSetter.setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        EZDTBLAccessor.insert(tmsg);
    }
    // mod end 2016/05/17 CSA Defect#3891
    // END 2015/12/10 T.Kanasaka [QC#1815, MOD]

    private static boolean hasValueForTargetLine(NSAL0560_ACMsg aCMsg) {
        // START 2017/08/21 K.Kitachi [QC#20061, MOD]
        if (hasValue(aCMsg.contrPrcEffFromDt_A1)
                && hasValue(aCMsg.contrPrcEffThruDt_A1)
                && hasValue(aCMsg.bllgCycleCd_A3)
                && hasValue(aCMsg.basePrcDealAmt_A1)
                && hasValue(aCMsg.basePrcTermDealAmtRate_A1)) {
            return true;
        }
        // END 2017/08/21 K.Kitachi [QC#20061, MOD]
        return false;
    }

    private static boolean changeColumnForTargetLine(NSAL0560_ACMsg aCMsg, NSAL0560_BCMsg bCMsg) {
        // START 2017/08/21 K.Kitachi [QC#20061, MOD]
        if (!aCMsg.contrPrcEffFromDt_A1.getValue().equals(bCMsg.contrPrcEffFromDt_B1.getValue())
                || !aCMsg.contrPrcEffThruDt_A1.getValue().equals(bCMsg.contrPrcEffThruDt_B1.getValue())
                || !aCMsg.bllgCycleCd_A3.getValue().equals(bCMsg.bllgCycleCd_B3.getValue())
                // mod start 2015/12/10 CSA Defect#1765
                //|| !aCMsg.basePrcDealAmt_A1.getValue().equals(bCMsg.basePrcDealAmt_B1.getValue())) {
                || aCMsg.basePrcDealAmt_A1.getValue().compareTo(bCMsg.basePrcDealAmt_B1.getValue()) != 0
                // mod end 2015/12/10 CSA Defect#1765
                || aCMsg.basePrcTermDealAmtRate_A1.getValue().compareTo(bCMsg.basePrcTermDealAmtRate_B1.getValue()) != 0) {
            return true;
        }
        // END 2017/08/21 K.Kitachi [QC#20061, MOD]
        return false;
    }

    public static final void copyAMsgToBMsg(NSAL0560CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            setValue(bizMsg.B.no(i).contrPrcEffFromDt_B1, bizMsg.A.no(i).contrPrcEffFromDt_A1);
            setValue(bizMsg.B.no(i).contrPrcEffThruDt_B1, bizMsg.A.no(i).contrPrcEffThruDt_A1);
            setValue(bizMsg.B.no(i).bllgCycleCd_B3, bizMsg.A.no(i).bllgCycleCd_A3);
            setValue(bizMsg.B.no(i).basePrcDealAmt_B1, bizMsg.A.no(i).basePrcDealAmt_A1);
            // del start 2016/05/17 CSA Defect#3891
//            setValue(bizMsg.B.no(i).svcCmntTxt_B1, bizMsg.A.no(i).svcCmntTxt_A1);
//            setValue(bizMsg.B.no(i).svcMemoRsnCd_B3, bizMsg.A.no(i).svcMemoRsnCd_A3);
            // del end 2016/05/17 CSA Defect#3891
            // START 2017/08/21 K.Kitachi [QC#20061, ADD]
            setValue(bizMsg.B.no(i).basePrcTermDealAmtRate_B1, bizMsg.A.no(i).basePrcTermDealAmtRate_A1);
            // END 2017/08/21 K.Kitachi [QC#20061, ADD]
        }
    }

    public static final void updateUnderAgg(NSAL0560CMsg bizMsg) {
        //get price effectivity info under the aggregate contract
        DS_CONTR_PRC_EFFTMsgArray aggDsContrPrcEff = NSAL0560Query.getDsContrPrcEff(bizMsg);

        for (int i = 0; i < aggDsContrPrcEff.getValidCount(); i++) {
            DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg = (DS_CONTR_PRC_EFFTMsg) aggDsContrPrcEff.get(i);
            Map<String, Object> queryMap = setMachPrcEffParam(bizMsg, aggPrcEffTMsg);

            boolean existInvocedData = NSAL0560Query.existInvoicedBllgSchdMach(bizMsg);
            S21SsmEZDResult ssmResult = NSAL0560Query.getInstance().getMachDsContrPrcEff(queryMap, bizMsg.C);
            // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
            NSZC047008PMsg inPMsg = new NSZC047008PMsg();
            // END 2015/12/10 T.Kanasaka [QC#1815, MOD]
            if (ssmResult.getQueryResultCount() > 0) {
                int maxPeSeqNo = 0;
                BigDecimal prevDsContrDtlPk = null;
                BigDecimal currDsContrDtlPk = null;
                //check if exists invoiced PE
                for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                    NSAL0560_CCMsg machPrcEff = bizMsg.C.no(j);
                    //get Max PE seq No
                    if (prevDsContrDtlPk == null && !machPrcEff.dsContrDtlPk_C.getValue().equals(prevDsContrDtlPk)) {
                        currDsContrDtlPk = machPrcEff.dsContrDtlPk_C.getValue();
                        maxPeSeqNo = getMaxPrcEffSeqNo(bizMsg, maxPeSeqNo, currDsContrDtlPk);
                    }
                    if (j == 0) {
                        inPMsg = createApiParamHdr(bizMsg, machPrcEff);
                    }
                    boolean splitPrcEff = false;
                    if (existInvocedData) {
                        if (machPrcEff.contrEffThruDt_C.getValue().compareTo(aggPrcEffTMsg.contrPrcEffThruDt.getValue()) > 0) {
                            splitPrcEff = true;
                        }
                        if (splitPrcEff) {
                            //set api param(pattern 1)
                            inPMsg = createApiParamDtl1(inPMsg, machPrcEff, aggPrcEffTMsg);
                        }
                    } else {
                        if (machPrcEff.contrEffThruDt_C.getValue().compareTo(aggPrcEffTMsg.contrPrcEffThruDt.getValue()) >= 0) {
                            splitPrcEff = true;
                        }
                        if (splitPrcEff) {
                            maxPeSeqNo++;
                            //set api param(pattern 3)
                            inPMsg = createApiParamDtl3(inPMsg, machPrcEff, aggPrcEffTMsg, maxPeSeqNo);
                        } else {
                            maxPeSeqNo++;
                            //set api param(pattern 4)
                            inPMsg = createApiParamDtl4(inPMsg, machPrcEff, aggPrcEffTMsg, maxPeSeqNo);
                        }
                    }
                    if (j == bizMsg.C.getValidCount() - 1 || !machPrcEff.dsContrDtlPk_C.getValue().equals(bizMsg.C.no(j + 1).dsContrDtlPk_C.getValue())) {
                        executeApi(inPMsg);
                    }
                    prevDsContrDtlPk = machPrcEff.dsContrDtlPk_C.getValue();
                }
            }
        }
    }

    private static int getMaxPrcEffSeqNo(NSAL0560CMsg bizMsg, int maxPeSeqNo, BigDecimal currDsContrDtlPk) {
        for (int k = 0; k < bizMsg.C.getValidCount(); k++) {
            NSAL0560_CCMsg machPrcEff1 = bizMsg.C.no(k);
            if (currDsContrDtlPk.equals(machPrcEff1.dsContrDtlPk_C)) {
                maxPeSeqNo = machPrcEff1.dsContrPrcEffSqNum_C.getValueInt();
            } else {
                break;
            }
        }
        return maxPeSeqNo;
    }

    private static Map<String, Object> setMachPrcEffParam(NSAL0560CMsg bizMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryMap.put("prntDsContrDtlPk", aggPrcEffTMsg.dsContrDtlPk.getValue());
        queryMap.put("baseChrgFlg", FLG_ON_Y);
        queryMap.put("fromDt", aggPrcEffTMsg.contrPrcEffThruDt.getValue());
        queryMap.put("thruDt", aggPrcEffTMsg.contrPrcEffFromDt.getValue());
        return queryMap;
    }

    // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
    public static NSZC047008PMsg createApiParamHdr(NSAL0560CMsg cMsg, NSAL0560_CCMsg cCMsg) {
        NSZC047008PMsg pMsg = new NSZC047008PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, "08");
        setValue(pMsg.dsContrDtlPk, cCMsg.dsContrDtlPk_C);
        setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.contrCloDay, cCMsg.contrCloDay_C);
        setValue(pMsg.baseBllgTmgCd, cCMsg.baseBllgTmgCd_C);
        setValue(pMsg.contrBllgDay, cCMsg.contrBllgDay_C);
        setValue(pMsg.contrEffFromDt, cCMsg.contrEffFromDt_C);
        setValue(pMsg.contrEffThruDt, getContrEffThruDt(cMsg.glblCmpyCd.getValue(), cCMsg.dsContrDtlPk_C.getValue(), cCMsg.contrEffThruDt_C.getValue()));
        return pMsg;
    }

    public static NSZC047008PMsg createApiParamDtl1(NSZC047008PMsg pMsg, NSAL0560_CCMsg cCMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg) {
        NSZC047008PMsg inPMsg = pMsg;
        int addRow = inPMsg.xxBaseLineList.getValidCount();
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffPk_BL, cCMsg.dsContrPrcEffPk_C);
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffSqNum_BL, cCMsg.dsContrPrcEffSqNum_C);
        setValue(pMsg.xxBaseLineList.no(addRow).effFromDt_BL, cCMsg.contrPrcEffFromDt_C);
        setValue(pMsg.xxBaseLineList.no(addRow).effThruDt_BL, aggPrcEffTMsg.contrPrcEffThruDt);
        setValue(pMsg.xxBaseLineList.no(addRow).baseBllgCycleCd_BL, cCMsg.bllgCycleCd_C);
        setValue(pMsg.xxBaseLineList.no(addRow).basePrcDealAmt_BL, cCMsg.basePrcDealAmt_C);
        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
        setValue(pMsg.xxBaseLineList.no(addRow).basePrcTermDealAmtRate_BL, getTermAmt(cCMsg, aggPrcEffTMsg.glblCmpyCd.getValue(), cCMsg.dsContrDtlPk_C.getValue()));
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffStsCd_BL, cCMsg.dsContrPrcEffStsCd_C);
        // add start 2016/01/06 CSA Defect#2783
        setValue(pMsg.xxBaseLineList.no(addRow).qltyAsrnHldFlg_BL, cCMsg.qltyAsrnHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).mtrHldFlg_BL, cCMsg.mtrHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).contrHldFlg_BL, cCMsg.contrHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).bllgHldFlg_BL, cCMsg.bllgHldFlg_C);
        // add end 2016/01/06 CSA Defect#2783
        return pMsg;
    }

    public static NSZC047008PMsg createApiParamDtl3(NSZC047008PMsg pMsg, NSAL0560_CCMsg cCMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg, int maxPeSeqNo) {
        NSZC047008PMsg inPMsg = pMsg;
        int addRow = inPMsg.xxBaseLineList.getValidCount();
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffSqNum_BL, BigDecimal.valueOf(maxPeSeqNo));
        setValue(pMsg.xxBaseLineList.no(addRow).effFromDt_BL, aggPrcEffTMsg.contrPrcEffFromDt);
        setValue(pMsg.xxBaseLineList.no(addRow).effThruDt_BL, aggPrcEffTMsg.contrPrcEffThruDt);
        setValue(pMsg.xxBaseLineList.no(addRow).baseBllgCycleCd_BL, aggPrcEffTMsg.bllgCycleCd);
        setValue(pMsg.xxBaseLineList.no(addRow).basePrcDealAmt_BL, cCMsg.basePrcDealAmt_C);
        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
        setValue(pMsg.xxBaseLineList.no(addRow).basePrcTermDealAmtRate_BL, getTermAmt(cCMsg, aggPrcEffTMsg.glblCmpyCd.getValue(), cCMsg.dsContrDtlPk_C.getValue()));
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffStsCd_BL, cCMsg.dsContrPrcEffStsCd_C);
        // add start 2016/01/06 CSA Defect#2783
        setValue(pMsg.xxBaseLineList.no(addRow).qltyAsrnHldFlg_BL, cCMsg.qltyAsrnHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).mtrHldFlg_BL, cCMsg.mtrHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).contrHldFlg_BL, cCMsg.contrHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).bllgHldFlg_BL, cCMsg.bllgHldFlg_C);
        // add end 2016/01/06 CSA Defect#2783
        return pMsg;
    }

    public static NSZC047008PMsg createApiParamDtl4(NSZC047008PMsg pMsg, NSAL0560_CCMsg cCMsg, DS_CONTR_PRC_EFFTMsg aggPrcEffTMsg, int maxPeSeqNo) {
        NSZC047008PMsg inPMsg = pMsg;
        int addRow = inPMsg.xxBaseLineList.getValidCount();
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffSqNum_BL, BigDecimal.valueOf(maxPeSeqNo));
        setValue(pMsg.xxBaseLineList.no(addRow).effFromDt_BL, aggPrcEffTMsg.contrPrcEffFromDt);
        setValue(pMsg.xxBaseLineList.no(addRow).effThruDt_BL, cCMsg.contrEffThruDt_C);
        setValue(pMsg.xxBaseLineList.no(addRow).baseBllgCycleCd_BL, aggPrcEffTMsg.bllgCycleCd);
        setValue(pMsg.xxBaseLineList.no(addRow).basePrcDealAmt_BL, cCMsg.basePrcDealAmt_C);
        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
        setValue(pMsg.xxBaseLineList.no(addRow).basePrcTermDealAmtRate_BL, getTermAmt(cCMsg, aggPrcEffTMsg.glblCmpyCd.getValue(), cCMsg.dsContrDtlPk_C.getValue()));
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
        setValue(pMsg.xxBaseLineList.no(addRow).dsContrPrcEffStsCd_BL, cCMsg.dsContrPrcEffStsCd_C);
        // add start 2016/01/06 CSA Defect#2783
        setValue(pMsg.xxBaseLineList.no(addRow).qltyAsrnHldFlg_BL, cCMsg.qltyAsrnHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).mtrHldFlg_BL, cCMsg.mtrHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).contrHldFlg_BL, cCMsg.contrHldFlg_C);
        setValue(pMsg.xxBaseLineList.no(addRow).bllgHldFlg_BL, cCMsg.bllgHldFlg_C);
        // add end 2016/01/06 CSA Defect#2783
        return pMsg;
    }
    // END 2015/12/10 T.Kanasaka [QC#1815, MOD]

    // START 2015/12/07 T.Kanasaka [QC#1472, ADD]
    // Mod Start 02/08/2016 <QC#4961>
    public static BigDecimal getTermAmt(NSAL0560CMsg cMsg, NSAL0560_ACMsg aCMsg, String glblCmpyCd, BigDecimal dsContrDtlPk) {
//        CalcSchdTermAndAmtBean inBean = new CalcSchdTermAndAmtBean();
//        inBean.setGlblCmpyCd(glblCmpyCd);
//        inBean.setBllgSchdFromDt(aCMsg.contrPrcEffFromDt_A1.getValue());
//        inBean.setBllgSchdThruDt(aCMsg.contrPrcEffThruDt_A1.getValue());
//        inBean.setBllgCycleCd(aCMsg.bllgCycleCd_A3.getValue());
//        inBean.setDsContrCloDay(getContrCloDay(glblCmpyCd, dsContrDtlPk));
//        inBean.setBasePrcDealAmt(aCMsg.basePrcDealAmt_A1.getValue());
//
//        inBean = NSXC003001CalcSchdTermAndAmt.calcSchdTermAndAmt(inBean);
//        List<CalcSchdTermAndAmtLineBean> outBeanList = inBean.getLine();
//
//        BigDecimal totTermAmt = BigDecimal.ZERO;
//        for (int i = 0; i < outBeanList.size(); i++) {
//            if (hasValue(outBeanList.get(i).getBasePrcDealAmt())) {
//                totTermAmt = totTermAmt.add(outBeanList.get(i).getBasePrcDealAmt());
//            }
//        }
        NSZC047021PMsg m21PMsg = new NSZC047021PMsg();
        setValue(m21PMsg.glblCmpyCd, glblCmpyCd);
        setValue(m21PMsg.xxModeCd, "21");
        setValue(m21PMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        setValue(m21PMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(m21PMsg.contrCloDay, cMsg.contrCloDay_H1);
        setValue(m21PMsg.baseBllgTmgCd, cMsg.baseBllgTmgCd_H1);
        // START 2017/11/09 K.Kojima [QC#22438,MOD]
        // setValue(m21PMsg.baseBllgCycleCd, cMsg.baseBllgCycleCd_H1);
        setValue(m21PMsg.baseBllgCycleCd, aCMsg.bllgCycleCd_A3);
        // END 2017/11/09 K.Kojima [QC#22438,MOD]
        setValue(m21PMsg.ccyCd, cMsg.ccyCd_H1);
        setValue(m21PMsg.effFromDt, aCMsg.contrPrcEffFromDt_A1);
        setValue(m21PMsg.effThruDt, aCMsg.contrPrcEffThruDt_A1);
        setValue(m21PMsg.basePrcDealAmt, aCMsg.basePrcDealAmt_A1);

        NSZC047001 api = new NSZC047001();
        api.execute(m21PMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        // Add Start 08/09/2016 <QC#12310>
        if (m21PMsg.xxMsgIdList.getValidCount() > 0) {
            aCMsg.contrPrcEffFromDt_A1.setErrorInfo(1, m21PMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            aCMsg.contrPrcEffThruDt_A1.setErrorInfo(1, m21PMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return BigDecimal.ZERO;
        }
        // Add End   08/09/2016 <QC#12310>
        BigDecimal totTermAmt = m21PMsg.basePrcTermDealAmtRate.getValue();
        // Add Start 09/01/2017 QC#20838
        int bllgCycleCnt = calcBllgCycleCntFromDuration(aCMsg, glblCmpyCd);
        if (bllgCycleCnt > 0) {
            totTermAmt = aCMsg.basePrcDealAmt_A1.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
        }
        // Add End 09/01/2017 QC#20838

        return totTermAmt;
    }
	// Mod End   02/08/2016 <QC#4961>

    // Add Start 09/01/2017 QC#20838
    private static int calcBllgCycleCntFromDuration(NSAL0560_ACMsg aCMsg, String glblCmpyCd) {
        // calculate duration
        DateFormat df = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date startDt;
        try {
            startDt = df.parse(aCMsg.contrPrcEffFromDt_A1.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = aCMsg.contrPrcEffThruDt_A1.getValue();
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        // get BLLG_CYCLE Info
        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, aCMsg.bllgCycleCd_A3);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }
    // Add End 09/01/2017 QC#20838

    public static BigDecimal getTermAmt(NSAL0560_CCMsg cCMsg, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        CalcSchdTermAndAmtBean inBean = new CalcSchdTermAndAmtBean();
        inBean.setGlblCmpyCd(glblCmpyCd);
        inBean.setBllgSchdFromDt(cCMsg.contrPrcEffFromDt_C.getValue());
        inBean.setBllgSchdThruDt(cCMsg.contrPrcEffThruDt_C.getValue());
        inBean.setBllgCycleCd(cCMsg.bllgCycleCd_C.getValue());
        inBean.setDsContrCloDay(getContrCloDay(glblCmpyCd, dsContrDtlPk));
        inBean.setBasePrcDealAmt(cCMsg.basePrcDealAmt_C.getValue());

        inBean = NSXC003001CalcSchdTermAndAmt.calcSchdTermAndAmt(inBean);
        List<CalcSchdTermAndAmtLineBean> outBeanList = inBean.getLine();

        BigDecimal totTermAmt = BigDecimal.ZERO;
        for (int i = 0; i < outBeanList.size(); i++) {
            if (hasValue(outBeanList.get(i).getBasePrcDealAmt())) {
                totTermAmt = totTermAmt.add(outBeanList.get(i).getBasePrcDealAmt());
            }
        }
        return totTermAmt;
    }

    private static String getContrCloDay(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
        DS_CONTR_DTLTMsg outTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.contrCloDay.getValue();
    }
    // END 2015/12/07 T.Kanasaka [QC#1472, ADD]

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
        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0560Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
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
