/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0400.common;

import static business.blap.NSAL0400.constant.NSAL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0400.NSAL0400CMsg;
import business.blap.NSAL0400.NSAL0400Query;
import business.blap.NSAL0400.NSAL0400_ACMsg;
import business.blap.NSAL0400.NSAL0400_BCMsg;
import business.blap.NSAL0400.constant.NSAL0400Constant.MSG_PRM;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcCreditAmtForTerminateBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcCreditAmtForTerminate;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 * 2016/01/17   Hitachi         K.Yamada        Update          CSA Modify
 * 2016/02/02   Hitachi         K.Kishimoto     Update          QC#4003
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3691
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 03/31/2016   Hitachi         K.Kishimoto     Update          QC#6343
 * 2016/08/01   Hitachi         A.Kohinata      Update          QC#2853
 * 2017/06/06   Hitachi         T.Mizuki        Update          QC#18267
 * 2017/07/03   Hitachi         A.Kohinata      Update          QC#18349
 * 2017/10/04   Hitachi         K.Kim           Update          QC#21557
 * 2017/11/20   Hitachi         K.Yamada        Update          QC#22654
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 * 2017/11/27   Hitachi         T.Tomita        Update          QC#21724
 * 2018/02/22   CITS            M.Naito         Update          QC#23179
 * 2018/05/28   Hitachi         U.Kim           Update          QC#25933
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/05/24   Hitachi         K.Kishimoto     Update          QC#59684
 * 2022/09/15   CITS            E.Sanchez       Update          QC#59775
 * 2022/11/02   CITS            L.Mandanas      Update          QC#60652
 * 2023/07/14   CITS            R.Jin           Update          QC#61685
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 *</pre>
 */
public class NSAL0400CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * getDtlData
     * @param bizMsg NSAL0400CMsg
     */
    public static void getDtlData(NSAL0400CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NSAL0400Query.getInstance().getDtlData(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
        }

        int contrHdrCnt = 0;
        BigDecimal currContrPk = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL0400_ACMsg dtlMsg = bizMsg.A.no(i);
            String contrDtlCtrlSts = dtlMsg.dsContrCtrlStsCd_AD.getValue();
            if (!DS_CONTR_CTRL_STS.ACTIVE.equals(contrDtlCtrlSts)
                    && !DS_CONTR_CTRL_STS.SINGED.equals(contrDtlCtrlSts)
                         // START 2017/10/04 K.Kim [QC#21557, ADD]
                         && !FLG_ON_Y.equals(dtlMsg.contrTrmnAvalFlg_AD.getValue())) {
                         // END 2017/10/04 K.Kim [QC#21557, ADD]
                String msgTxt = S21MessageFunc.clspGetMessage(NSAM0414E);
                // START 2022/09/15 E.Sanchez [QC#59775, MOD]
                // setValue(dtlMsg.xxGenlFldAreaTxt_AD, msgTxt.substring(NSAM0414E.length()));
                setValue(dtlMsg.xxGenlFldAreaTxt_AD, msgTxt.substring(NSAM0414E.length() + 1));
                // END 2022/09/15 E.Sanchez [QC#59775, MOD]
            // START 2022/09/15 E.Sanchez [QC#59775, ADD]
            } else if (hasValue(dtlMsg.contrCloDt_DB)) {
                String msgTxt = S21MessageFunc.clspGetMessage(NSAM0764E);
                setValue(dtlMsg.xxGenlFldAreaTxt_AD, msgTxt.substring(NSAM0764E.length() + 1));
            // END 2022/09/15 E.Sanchez [QC#59775, ADD]
            }

            //copy contract header level info in A to B for screen control.
            if (!hasValue(currContrPk) || currContrPk.compareTo(dtlMsg.dsContrPk_AH.getValue()) != 0) {
                currContrPk = dtlMsg.dsContrPk_AH.getValue();
                EZDMsg.copy(dtlMsg, "AH", bizMsg.B.no(contrHdrCnt), "B");
                contrHdrCnt++;
            }
        }
        bizMsg.B.setValidCount(contrHdrCnt);

    }

    /**
     * setContrHdrInfoToDtl
     * @param bizMsg NSAL0400CMsg
     */
    public static void setContrHdrInfoToDtl(NSAL0400CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL0400_ACMsg dtlMsg = bizMsg.A.no(i);
            if (DS_CONTR_CATG.FLEET.equals(dtlMsg.dsContrCatgCd_AH.getValue())) {
                continue;
            }
            BigDecimal dsContrPk = dtlMsg.dsContrPk_AH.getValue();

            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                NSAL0400_BCMsg hdrMsg = bizMsg.B.no(j);
                if (dsContrPk.compareTo(hdrMsg.dsContrPk_B.getValue()) == 0) {
                    EZDMsg.copy(hdrMsg, "B", dtlMsg, "AH");
                    break;
                }
            }
        }
    }

    /**
     * hasErrDtlOfReview
     * @param cMsg NSAL0400CMsg
     * @param targetList List<Integer>
     * @return boolean
     */
    public static boolean hasErrDtlOfReview(NSAL0400CMsg cMsg, List<Integer> targetList) {

        boolean hasError = false;
        for (int rowIndex : targetList) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(rowIndex);

            //copy header info to detail.
            copyHdrToDtl(acMsg);

            if (hasErrDtlOfReview(acMsg)) {
                hasError = true;
            }
        }
        return hasError;
    }

    private static boolean hasErrDtlOfReview(NSAL0400_ACMsg acMsg) {
        // Contract Close Date
        if (!hasValue(acMsg.contrCloDt_AD)) {
            acMsg.contrCloDt_AD.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.TERMINATION_DATE.getMsgPrm()) });
            return true;
        }
        // START 2023/07/14 R.Jin [QC#61685, MOD]
        // START 2022/02/04 K.Kitachi [QC#59684, MOD]
        if (ZYPDateUtil.compare(acMsg.contrCloDt_AD.getValue(), acMsg.contrEffFromDt_AD.getValue()) < 0
                || ZYPDateUtil.compare(acMsg.contrEffThruDt_AD.getValue(), acMsg.contrCloDt_AD.getValue()) < 0) {
//        String fromDt = ZYPDateUtil.addDays(acMsg.contrEffFromDt_AD.getValue(), -1);
//        if (ZYPDateUtil.compare(acMsg.contrCloDt_AD.getValue(), fromDt) < 0
//                || ZYPDateUtil.compare(acMsg.contrEffThruDt_AD.getValue(), acMsg.contrCloDt_AD.getValue()) < 0) {
        // END 2022/02/04 K.Kitachi [QC#59684, MOD]
        // END 2023/07/14 R.Jin [QC#61685, MOD]
            acMsg.contrCloDt_AD.setErrorInfo(1, NSAM0306E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.START_DATE.getMsgPrm()) //
                            , converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.END_DATE.getMsgPrm()) });
            return true;
        }
        // START 2022/05/24 K.Kishimoto [QC#59684, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue())) {
            if (hasValue(acMsg.contrCloDt_AD) && !acMsg.contrCloDt_AD.getValue().equals(acMsg.contrEffFromDt_AD.getValue())) {
                acMsg.contrTrmnFlg_AD.setErrorInfo(1, NSAM0759E);
                acMsg.contrCloDt_AD.setErrorInfo(1, NSAM0759E);
                return true;
            }
        }
        // END   2022/05/24 K.Kishimoto [QC#59684, ADD]
        return false;
    }

    private static void copyHdrToDtl(NSAL0400_ACMsg acMsg) {
        if (!hasValue(acMsg.contrCloDt_AD)) {
            setValue(acMsg.contrCloDt_AD, acMsg.contrCloDt_AH);
        }
        if (FLG_ON_Y.equals(acMsg.supprCrFlg_AH.getValue())
                && (!hasValue(acMsg.supprCrFlg_AD.getValue()) || FLG_OFF_N.equals(acMsg.supprCrFlg_AD.getValue()))) {
            setValue(acMsg.supprCrFlg_AD, acMsg.supprCrFlg_AH);
        }
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        if (FLG_ON_Y.equals(acMsg.contrTrmnFlg_AH.getValue())) {
            if (!FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue())) {
                setValue(acMsg.contrTrmnFlg_AD, acMsg.contrTrmnFlg_AH);
            }
            if (!hasValue(acMsg.contrCloDt_AH.getValue()) && !hasValue(acMsg.contrCloDt_AD.getValue())) {
                setValue(acMsg.contrCloDt_AD, acMsg.contrEffFromDt_AD);
            }
        }
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        // START 2022/11/02 L.Mandanas [QC#60652, ADD]
        if (!FLG_ON_Y.equals(acMsg.supprCrFlg_AH.getValue()) && FLG_ON_Y.equals(acMsg.supprCrFlg_AD.getValue())) {
            acMsg.supprCrFlg_AD.clear();
        }
        // END 2022/11/02 L.Mandanas [QC#60652, ADD]
    }

    /**
     * calcCreditAmt
     * @param acMsg NSAL0400_ACMsg
     */
    // mod start 2017/07/03 QC#18349
    public static void calcCreditAmt(NSAL0400CMsg cMsg, NSAL0400_ACMsg acMsg) {
//        NSAL0400Query query = NSAL0400Query.getInstance();
//        S21SsmEZDResult ssmResult = null;

        if (DS_CONTR_CATG.FLEET.equals(acMsg.dsContrCatgCd_AH.getValue())) {
            //In the fleet contract, it is not calculated credit amount for each machine.
            return;
        }
//        ssmResult = query.getInvoicedBllgSchd(acMsg.dsContrDtlPk_AD.getValue(), acMsg.contrCloDt_AD.getValue());
//        if (ssmResult.isCodeNotFound()) {
//            acMsg.trmnTotAmt_AD.clear();
//            return;
//        }
//
//        ssmResult = query.chkProratedSchd(acMsg.dsContrDtlPk_AD.getValue(), acMsg.contrCloDt_AD.getValue());
//        if (ssmResult.isCodeNormal()) {
//            List<BigDecimal> basePrcAmtList = (List<BigDecimal>) ssmResult.getResultObject();
//            calcCreditAmtNotProrate(acMsg, basePrcAmtList);
//        } else {
//            calcCreditAmtProrate(acMsg);
//        }

        CalcCreditAmtForTerminateBean inBean = new CalcCreditAmtForTerminateBean();
        inBean.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        inBean.setDsContrDtlPk(acMsg.dsContrDtlPk_AD.getValue());
        // START 2018/02/22 M.Naito [QC23179, MOD]
        // Mod Start 2017/11/27 QC#21724
//        inBean.setTrmnDt(addCloDt(acMsg.contrCloDt_AD.getValue(), -1));
        // Mod End 2017/11/27 QC#21724
        inBean.setTrmnDt(acMsg.contrCloDt_AD.getValue());
        // END 2018/02/22 M.Naito [QC23179, MOD]
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        if (FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue())) {
            inBean.setManTrmnTpCd(MAN_TRMN_TP.ALL_PERIOD);
        }
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        NSXC003001CalcCreditAmtForTerminate.calcCreditAmtForTerminate(inBean);
        setValue(acMsg.trmnTotAmt_AD, inBean.getCreditAmt());
    }
    // mod end 2017/07/03 QC#18349

    /**
     * @param cMsg NSAL0400CMsg
     */
    public static void calcFleetCreditAmt(NSAL0400CMsg cMsg) {
        NSAL0400Query query = NSAL0400Query.getInstance();
        S21SsmEZDResult ssmResult = null;

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0400_BCMsg bcMsg = cMsg.B.no(i);
            if (!DS_CONTR_CATG.FLEET.equals(bcMsg.dsContrCatgCd_B.getValue())) {
                continue;
            }
            if (!isAllTermination(cMsg, bcMsg.dsContrPk_B.getValue())) {
                bcMsg.trmnTotAmt_B.clear();
                continue;
            }

            String lastCloDt = getLastCloDt(cMsg, bcMsg.dsContrPk_B.getValue());
            DS_CONTR_DTLTMsg fleetLine = getFleetLine(cMsg, bcMsg.dsContrPk_B.getValue());

            // mod start 2017/11/20 QC#22654
            /*ssmResult = query.getInvoicedBllgSchd(fleetLine.dsContrDtlPk.getValue(), lastCloDt);
            if (ssmResult.isCodeNotFound()) {
                bcMsg.trmnTotAmt_B.clear();
                return;
            }

            ssmResult = query.chkProratedSchd(fleetLine.dsContrDtlPk.getValue(), lastCloDt);
            if (ssmResult.isCodeNormal()) {
                List<BigDecimal> basePrcAmtList = (List<BigDecimal>) ssmResult.getResultObject();
                calcFleetCreditAmtNotProrate(bcMsg, basePrcAmtList);
            } else {
                calcFleetCreditAmtProrate(bcMsg, fleetLine.dsContrDtlPk.getValue(), lastCloDt);
            }*/
            CalcCreditAmtForTerminateBean inBean = new CalcCreditAmtForTerminateBean();
            inBean.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
            inBean.setDsContrDtlPk(fleetLine.dsContrDtlPk.getValue());
            // START 2018/02/22 M.Naito [QC23179, MOD]
            // Mod Start 2017/11/27 QC#21724
//            inBean.setTrmnDt(addCloDt(lastCloDt, -1));
            // Mod End 2017/11/27 QC#21724
            inBean.setTrmnDt(lastCloDt);
            // END 2018/02/22 M.Naito [QC23179, MOD]
            NSXC003001CalcCreditAmtForTerminate.calcCreditAmtForTerminate(inBean);
            setValue(bcMsg.trmnTotAmt_B, inBean.getCreditAmt());
            // mod end 2017/11/20 QC#22654
        }
    }

    /**
     * @param cMsg NSAL0400CMsg
     * @param dsContrPk BigDecimal
     * @return boolean
     */
    public static boolean isAllTermination(NSAL0400CMsg cMsg, BigDecimal dsContrPk) {
        boolean isAllTermination = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(i);
            if (dsContrPk.compareTo(acMsg.dsContrPk_AH.getValue()) != 0) {
                continue;
            }
            if (!hasValue(acMsg.contrCloDt_AD)) {
                isAllTermination = false;
                break;
            }
        }
        return isAllTermination;
    }

    // START 2016/02/15 T.Aoyagi [QC3691, MOD]
    /**
     * @param cMsg NSAL0400CMsg
     * @param dsContrPk BigDecimal
     * @return String
     */
//    private static String getLastCloDt(NSAL0400CMsg cMsg, BigDecimal dsContrPk) {
    public static String getLastCloDt(NSAL0400CMsg cMsg, BigDecimal dsContrPk) {
    // END 2016/02/15 T.Aoyagi [QC3691, MOD]
        String lastCloDt = "";
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(i);
            if (dsContrPk.compareTo(acMsg.dsContrPk_AH.getValue()) != 0) {
                continue;
            }
            if (lastCloDt.compareTo(acMsg.contrCloDt_AD.getValue()) < 0) {
                lastCloDt = acMsg.contrCloDt_AD.getValue();
            }
        }
        return lastCloDt;
    }

    /**
     * @param cMsg NSAL0400CMsg
     * @param dsContrPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getFleetLine(NSAL0400CMsg cMsg, BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsContrPk01", dsContrPk);
        tMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);
        tMsg.setSQLID("006");

        DS_CONTR_DTLTMsgArray tMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }
    // START 2016/02/15 T.Aoyagi [QC3691, ADD]
    /**
     * @param cMsg NSAL0400CMsg
     * @param dsContrPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getAggLine(NSAL0400CMsg cMsg, BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsContrPk01", dsContrPk);
        tMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        tMsg.setSQLID("006");

        DS_CONTR_DTLTMsgArray tMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }
    // END 2016/02/15 T.Aoyagi [QC3691, ADD]
    private static void calcCreditAmtNotProrate(NSAL0400_ACMsg acMsg, List<BigDecimal> basePrcAmtList) {
        BigDecimal totCreditAmt = BigDecimal.ZERO;
        for (BigDecimal basePrcAmt : basePrcAmtList) {
            totCreditAmt = totCreditAmt.add(basePrcAmt);
        }
        setValue(acMsg.trmnTotAmt_AD, totCreditAmt);
    }

    private static void calcFleetCreditAmtNotProrate(NSAL0400_BCMsg bcMsg, List<BigDecimal> basePrcAmtList) {
        BigDecimal totCreditAmt = BigDecimal.ZERO;
        for (BigDecimal basePrcAmt : basePrcAmtList) {
            totCreditAmt = totCreditAmt.add(basePrcAmt);
        }
        setValue(bcMsg.trmnTotAmt_B, totCreditAmt);
    }

    private static void calcCreditAmtProrate(NSAL0400_ACMsg acMsg) {
        BigDecimal totCreditAmt = calcCreditAmtProrate(acMsg.dsContrDtlPk_AD.getValue(), acMsg.contrCloDt_AD.getValue());
        setValue(acMsg.trmnTotAmt_AD, totCreditAmt);
    }

    private static void calcFleetCreditAmtProrate(NSAL0400_BCMsg bcMsg, BigDecimal fleetDsContrDtlPk, String lastCloDt) {
        BigDecimal totCreditAmt = calcCreditAmtProrate(fleetDsContrDtlPk, lastCloDt);
        setValue(bcMsg.trmnTotAmt_B, totCreditAmt);
    }

    private static BigDecimal calcCreditAmtProrate(BigDecimal dsContrDtlPk, String contrCloDt) {
        NSAL0400Query query = NSAL0400Query.getInstance();
        S21SsmEZDResult ssmResult = null;

        ssmResult = query.getPrrtBllgSchd(dsContrDtlPk, contrCloDt);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        List<Map<String, Object>> prrtSchdList = (List<Map<String, Object>>) ssmResult.getResultObject();

        BigDecimal totCreditAmt = BigDecimal.ZERO;
        for (Map<String, Object> prrtSchd : prrtSchdList) {
            BigDecimal basePrcAmt = (BigDecimal) prrtSchd.get("BASE_PRC_DEAL_AMT");
            String schdFromDt = (String) prrtSchd.get("BLLG_SCHD_FROM_DT");
            BigDecimal prrtDivRate = (BigDecimal) prrtSchd.get("PRRT_DIV_RATE");

            BigDecimal divRate = DAY_OF_YEAR.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP);
            int distance = ZYPDateUtil.getDistance(schdFromDt, contrCloDt
                    , ZYPDateUtil.CALENDAR_GENERAL) + 1;
            BigDecimal creditAmt = basePrcAmt.multiply(BigDecimal.valueOf(distance)).divide(
                    divRate, 2, BigDecimal.ROUND_HALF_UP);
            totCreditAmt = totCreditAmt.add(creditAmt);
        }

        ssmResult = query.getNotPrrtSchd(dsContrDtlPk, contrCloDt);
        if (ssmResult.isCodeNormal()) {
            List<BigDecimal> basePrcAmtList = (List<BigDecimal>) ssmResult.getResultObject();
            for (BigDecimal basePrcAmt : basePrcAmtList) {
                totCreditAmt = totCreditAmt.add(basePrcAmt);
            }
        }
        return totCreditAmt;
    }

    /**
     * hasErrDtlOfSubmit
     * @param cMsg NSAL0400CMsg
     * @param targetList List<Integer>
     * @return boolean
     */
    public static boolean hasErrDtlOfSubmit(NSAL0400CMsg cMsg, final List<Integer> targetList) {

        boolean hasError = false;
        for (int rowIndex : targetList) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(rowIndex);

            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (hasErrDtlOfReview(acMsg)) {
                hasError = true;
            } else if (ZYPConstant.FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue()) && !acMsg.contrEffFromDt_AD.getValue().equals(acMsg.contrCloDt_AD.getValue())) {
                acMsg.contrTrmnFlg_AD.setErrorInfo(1, NSAM0759E);
                acMsg.contrCloDt_AD.setErrorInfo(1, NSAM0759E);
                hasError = true;
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
            // Add 03/31/2016 Start <QC#6343>
            if (hasValue(acMsg.trmnOvrdTotAmt_AD) && !hasValue(acMsg.trmnTotAmt_AD)) {
                acMsg.trmnOvrdTotAmt_AD.setErrorInfo(1, NSAM0450E);
                hasError = true;
                continue;
            }
            // Add 03/31/2016 End   <QC#6343>
            if (hasValue(acMsg.trmnOvrdTotAmt_AD) && hasValue(acMsg.trmnTotAmt_AD)) {
                if (BigDecimal.ZERO.compareTo(acMsg.trmnOvrdTotAmt_AD.getValue()) > 0
                        || acMsg.trmnOvrdTotAmt_AD.getValue().compareTo(acMsg.trmnTotAmt_AD.getValue()) > 0) {
                    acMsg.trmnOvrdTotAmt_AD.setErrorInfo(1, NSAM0194E
                            , new String[] {"0", converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.AMOUNT.getMsgPrm()) });
                    hasError = true;
                    continue;
                }
            }
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (hasError) {
                continue;
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]

            DS_CONTR_DTLTMsg tMsgKey = new DS_CONTR_DTLTMsg();
            setValue(tMsgKey.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsgKey.dsContrDtlPk, acMsg.dsContrDtlPk_AD);
            DS_CONTR_DTLTMsg tMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsgKey);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0001E //
                        , new String[] {MSG_PRM.DS_CONTR_DTL.getMsgPrm(), tMsgKey.dsContrDtlPk.getValue().toString() });
            }

            if (!ZYPDateUtil.isSameTimeStamp(//
                    acMsg.ezUpTime_AD.getValue(), acMsg.ezUpTimeZone_AD.getValue() //
                    , tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                acMsg.xxChkBox_AD.setErrorInfo(1, NZZM0003E);
                hasError = true;
                continue;
            }
            // START 2022/11/02 L.Mandanas [QC#60652, ADD]
            if (!checkCustomer(acMsg)) {
                hasError = true;
            }
            // END 2022/11/02 L.Mandanas [QC#60652, ADD]
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0400_BCMsg bcMsg = cMsg.B.no(i);
            if (!DS_CONTR_CATG.FLEET.equals(bcMsg.dsContrCatgCd_B.getValue())) {
                continue;
            }

            boolean isAllTerminate = true;
            BigDecimal dsContrPk = bcMsg.dsContrPk_B.getValue();
            // START 2016/02/19 T.Aoyagi [QC3694, ADD]
            List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
            // START 2016/02/19 T.Aoyagi [QC3694, ADD]
            for (int j = 0; j < cMsg.A.getValidCount(); j++) {
                NSAL0400_ACMsg acMsg = cMsg.A.no(j);

                if (dsContrPk.compareTo(acMsg.dsContrPk_AH.getValue()) != 0) {
                    continue;
                }
                if (!FLG_ON_Y.equals(acMsg.xxChkBox_AD.getValue())) {
                    isAllTerminate = false;
                    break;
                }
                // START 2016/02/19 T.Aoyagi [QC3694, ADD]
                dsContrDtlPkList.add(acMsg.dsContrDtlPk_AD.getValue());
                // END 2016/02/19 T.Aoyagi [QC3694, ADD]
            }
            // START 2016/02/19 T.Aoyagi [QC3694, ADD]
            S21SsmEZDResult ssmResult = NSAL0400Query.getInstance().getNonDispFleetMachCnt(dsContrPk, dsContrDtlPkList);
            int machCnt = (Integer) ssmResult.getResultObject();
            if (machCnt > 0) {
                isAllTerminate = false;
            }
            // END 2016/02/19 T.Aoyagi [QC3694, ADD]
            // START 2016/02/02 K.Kishimoto [QC#4003, MOD]
            if (!isAllTerminate && hasValue(bcMsg.trmnOvrdTotAmt_B)) {
                bcMsg.trmnOvrdTotAmt_B.setErrorInfo(1, NSAM0415E);
                hasError = true;
                continue;
            }
            // END 2016/02/02 K.Kishimoto [QC#4003, MOD]
            if (isAllTerminate && !hasValue(bcMsg.contrCloDt_B)) {
                bcMsg.contrCloDt_B.setErrorInfo(1, ZZM9000E
                        , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.TERMINATION_DATE.getMsgPrm()) });
                hasError = true;
                continue;
            }
        }
        return hasError;
    }

    // add start 2016/08/01 CSA Defect#2853
    public static boolean hasWarningDtlOfSubmit(NSAL0400CMsg cMsg, final List<Integer> targetList) {

        boolean hasWarning = false;
        BigDecimal limitDt = ZYPCodeDataUtil.getNumConstValue(NSAL0400_CONTR_CLO_LIMIT_DT, cMsg.glblCmpyCd.getValue());
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        boolean existAllPerTrmn = false;
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        for (int rowIndex : targetList) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(rowIndex);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue())) {
                existAllPerTrmn = true;
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]

            if (ZYPDateUtil.compare(cMsg.slsDt.getValue(), acMsg.contrCloDt_AD.getValue()) < 0) {
                continue;
            }

            int recCnt = NSAL0400Query.getInstance().getMtrReadCnt(acMsg.dsContrDtlPk_AD.getValue(), acMsg.contrCloDt_AD.getValue(), limitDt);
            if (recCnt == 0) {
                // Add Start 2017/06/06 <QC#18267>
                if (existBillingConuter(cMsg, acMsg.dsContrDtlPk_AD.getValue())) {
                    if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
                        acMsg.xxChkBox_AD.setErrorInfo(2, NSAM0560W, new String[] {limitDt.toString() });
                        cMsg.setMessageInfo(NSAM0560W, new String[] {limitDt.toString() });
                        hasWarning = true;
                    }
                }
                // Add End 2017/06/06 <QC#18267>
            // START 2024/03/26 K.Watanabe [QC#63549, ADD]
            } else if (!existAllPerTrmn && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
                int terminationContractCount = NSAL0400Query.getInstance().getTerminationContract(cMsg.glblCmpyCd.getValue(), acMsg.dsContrDtlPk_AD.getValue(), acMsg.contrCloDt_AD.getValue(), limitDt);
                if (terminationContractCount == 0) {
                    acMsg.xxChkBox_AD.setErrorInfo(2, NSAM0788W, new String[] {limitDt.toString() });
                    cMsg.setMessageInfo(NSAM0788W, new String[] {limitDt.toString() });
                    hasWarning = true;
                }
            // END   2024/03/26 K.Watanabe [QC#63549, ADD]
            }
        }

        if (hasWarning) {
            setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        } else if (existAllPerTrmn && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg_W.getValue())) {
            setValue(cMsg.xxRsltFlg_W, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NSAM0760W);
            hasWarning = true;
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        } else {
            cMsg.xxRsltFlg.clear();
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            cMsg.xxRsltFlg_W.clear();
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }
        return hasWarning;
    }
    // add end 2016/08/01 CSA Defect#2853
    // Add Start 2017/06/06 <QC#18267>
    /**
     * @param cMsg NSAL0400CMsg
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    private static boolean existBillingConuter(NSAL0400CMsg cMsg, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg tMsg = new DS_CONTR_BLLG_MTRTMsg();
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        tMsg.setSQLID("001");

        DS_CONTR_BLLG_MTRTMsgArray tMsgArray = (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }
    // Add End 2017/06/06 <QC#18267>
    // START 2018/02/22 M.Naito [QC23179, DEL]
    // Add Start 2017/11/21 QC#21724
//    public static String addCloDt(String contrCloDt, int days) {
//        String rtnDt = contrCloDt;
//        if (!hasValue(rtnDt)) {
//            return rtnDt;
//        }
//        return ZYPDateUtil.addDays(rtnDt, days);
//    }
    // Add End 2017/11/21 QC#21724
    // START 2018/02/22 M.Naito [QC23179, DEL]
    // START 2018/05/28 U.Kim [QC#25933, ADD]
    public static boolean checkAccessoryEndDate(NSAL0400CMsg cMsg, List<Integer> targetList) {
        NSAL0400Query query = NSAL0400Query.getInstance();
        boolean result = true;
        for (int rowNum : targetList) {
            List<Map<String, Object>> accInfoList = query.getAccessoryInfo(cMsg.A.no(rowNum).dsContrDtlPk_AD.getValue());
            if (accInfoList == null || accInfoList.size() == 0) {
                continue;
            }
            for (Map<String, Object> accInfo : accInfoList) {
                BigDecimal dsContrDtlPkAcc = (BigDecimal) accInfo.get("DS_CONTR_DTL_PK");
                String endDate = (String) accInfo.get("END_DT");
                boolean dispExistsFlg = false;
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    NSAL0400_ACMsg acMsg = cMsg.A.no(i);
                    if (!acMsg.dsContrDtlPk_AD.getValue().equals(dsContrDtlPkAcc)) {
                        continue;
                    }
                    if (!acMsg.xxChkBox_AD.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                        break;
                    }
                    dispExistsFlg = true;
                    if (cMsg.A.no(rowNum).contrCloDt_AD.getValue().compareTo(acMsg.contrCloDt_AD.getValue()) < 0) {
                        cMsg.A.no(rowNum).contrCloDt_AD.setErrorInfo(1, NSZM1334E);
                        result = false;
                    }
                }
                if (!dispExistsFlg) {
                    if (cMsg.A.no(rowNum).contrCloDt_AD.getValue().compareTo(endDate) < 0) {
                        cMsg.A.no(rowNum).contrCloDt_AD.setErrorInfo(1, NSZM1334E);
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    public static boolean checkMachineEndDate(NSAL0400CMsg cMsg, List<Integer> targetList) {
        NSAL0400Query query = NSAL0400Query.getInstance();
        boolean result = true;
        for (int rowNum : targetList) {
            Map<String, Object> machInfo = query.getMachineInfo(cMsg.A.no(rowNum).dsContrDtlPk_AD.getValue());
            if (machInfo == null) {
                continue;
            }
            BigDecimal dsContrDtlPkMach = (BigDecimal) machInfo.get("DS_CONTR_DTL_PK");
            String endDate = (String) machInfo.get("END_DT");
            boolean dispExistsFlg = false;
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NSAL0400_ACMsg acMsg = cMsg.A.no(i);
                if (!acMsg.dsContrDtlPk_AD.getValue().equals(dsContrDtlPkMach)) {
                    continue;
                }
                if (!acMsg.xxChkBox_AD.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    break;
                }
                dispExistsFlg = true;
                if (cMsg.A.no(rowNum).contrCloDt_AD.getValue().compareTo(acMsg.contrCloDt_AD.getValue()) > 0) {
                    cMsg.A.no(rowNum).contrCloDt_AD.setErrorInfo(1, NSZM1334E);
                    result = false;
                }
            }
            if (!dispExistsFlg) {
                if (cMsg.A.no(rowNum).contrCloDt_AD.getValue().compareTo(endDate) > 0) {
                    cMsg.A.no(rowNum).contrCloDt_AD.setErrorInfo(1, NSZM1334E);
                    result = false;
                }
            }
        }
        return result;
    }
    // END 2018/05/28 U.Kim [QC#25933, ADD]

    // START 2022/11/02 L.Mandanas [QC#60652, ADD]
    /**
     * checkCustomer
     * @param acMsg NSAL0400_ACMsg
     * @return boolean
     */
    private static boolean checkCustomer(NSAL0400_ACMsg acMsg) {
        NSAL0400Query query = NSAL0400Query.getInstance();

        if (FLG_ON_Y.equals(acMsg.supprCrFlg_AD.getValue())) {
            return true;
        }
        if (!hasValue(acMsg.trmnTotAmt_AD) && !hasValue(acMsg.trmnOvrdTotAmt_AD)) {
            return true;
        }
        if (hasValue(acMsg.trmnOvrdTotAmt_AD)) {
            if (acMsg.trmnOvrdTotAmt_AD.getValue().compareTo(BigDecimal.ZERO) == 0) {
                return true;
            }
        } else {
            if (acMsg.trmnTotAmt_AD.getValue().compareTo(BigDecimal.ZERO) == 0) {
                return true;
            }
        }

        Map<String, Object> resultMap = query.getBillToAndShipTo(acMsg.dsContrDtlPk_AD.getValue());
        if (resultMap == null) {
            return true;
        }
        String billToCustCd = (String) resultMap.get("BILL_TO_CUST_CD");
        String shipToCustCd = (String) resultMap.get("SHIP_TO_CUST_CD");

        List<String> invalidSellToCustList = query.getInvalidSellToCust(billToCustCd, shipToCustCd);
        if (invalidSellToCustList.size() > 0) {
            String msgPrm = invalidSellToCustList.get(0);
            for (int i = 1; i < invalidSellToCustList.size(); i++) {
                msgPrm = msgPrm + DELIMITER + invalidSellToCustList.get(i);
            }
            if (hasValue(msgPrm) && msgPrm.length() > LEN_100) {
                msgPrm = msgPrm.substring(0, LEN_100);
            }
            acMsg.xxChkBox_AD.setErrorInfo(1, NSAM0769E, new String[] {msgPrm });
            return false;
        }

        String invalidBillToCust = query.getInvalidBillToCust(billToCustCd);
        if (hasValue(invalidBillToCust)) {
            acMsg.xxChkBox_AD.setErrorInfo(1, NSAM0770E, new String[] {invalidBillToCust });
            return false;
        }

        String invalidShipToCust = query.getInvalidShipToCust(shipToCustCd);
        if (hasValue(invalidShipToCust)) {
            acMsg.xxChkBox_AD.setErrorInfo(1, NSAM0771E, new String[] {invalidShipToCust });
            return false;
        }
        return true;
    }
    // END 2022/11/02 L.Mandanas [QC#60652, ADD]
}
