/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0330.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDSBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0330.NSAL0330CMsg;
import business.blap.NSAL0330.NSAL0330Query;
import business.blap.NSAL0330.NSAL0330SMsg;
import business.blap.NSAL0330.NSAL0330_ACMsg;
import business.blap.NSAL0330.NSAL0330_ASMsg;
import business.blap.NSAL0330.constant.NSAL0330Constant;
import business.db.BLLG_CYCLETMsg;
import business.db.BLLG_CYCLETMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2015/12/08   Hitachi         T.Kanasaka      Update          QC#1463
 * 2016/02/18   Hitachi         T.Tomita        Update          QC#3892
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/08/05   Hitachi         K.Kishimoto     Update          QC#12879
 * 2016/08/09   Hitachi         K.Kishimoto     Update          QC#12310
 * 2017/08/03   Hitachi         E.Kameishi      Update          QC#18586
 * 2017/12/11   Hitachi         U.Kim           Update          QC#18779
 *</pre>
 */
public class NSAL0330CommonLogic {

    /**
     * <pre>
     * createHeaderFooterPulldownList
     * </pre>
     * @param cMsg NSAL0330CMsg
     * @param glblCmpyCd String
     */
    // START 2015/12/08 T.Kanasaka [QC#1463, MOD]
    public static void createHeaderFooterPulldownList(NSAL0330CMsg cMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, cMsg.bllgTmgTpCd_H1, cMsg.bllgTmgTpNm_H2);
        createSvcMemoRsnPullDown(cMsg, glblCmpyCd);
        // START 2016/08/05 K.Kishimoto [QC#12879, ADD]
        createContrCloDayPullDownList(cMsg.baseDplyPerEndDay_BC, cMsg.xxEdtDescTxt_BC);
        // START 2017/12/11 U.Kim [QC#18779, MOD]
        //createContrBllgDayPullDownList(cMsg.contrBllgDay_BB, cMsg.xxEdtDescTxt_BB);
        createContrBllgDayPullDownList(cMsg, glblCmpyCd);
        // END 2017/12/11 U.Kim [QC#18779, MOD]
        // END 2016/08/05 K.Kishimoto [QC#12879, ADD]
    }
    // END 2015/12/08 T.Kanasaka [QC#1463, MOD]

    // START 2015/12/08 T.Kanasaka [QC#1463, ADD]
    private static void createSvcMemoRsnPullDown(NSAL0330CMsg cMsg, String glblCmpyCd) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(glblCmpyCd, SVC_MEMO_TP.CHANGE_VIEW_PRICING);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_F1, cMsg.svcMemoRsnNm_F2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // END 2015/12/08 T.Kanasaka [QC#1463, ADD]

    /**
     * <pre>
     * createScheduleRowPulldownList
     * </pre>
     * @param acMsg NSAL0330_ACMsg
     */
    public static void createScheduleRowPulldownList(NSAL0330_ACMsg acMsg) {
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE_UOM.class, acMsg.bllgCycleUomCd_A1, acMsg.bllgCycleUomNm_A2);
        // START 2015/10/21 T.Tomita [N/A, DEL]
//        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, acMsg.bllgCycleCd_A1, acMsg.bllgCycleNm_A2);
        // END 2015/10/21 T.Tomita [N/A, DEL]
    }

    public static DS_CONTR_DTLTMsg findDsContrDtlForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    public static SVC_MEMOTMsg findSvcMemoForUpdate(String glblCmpyCd, BigDecimal svcMemoPk) {
        SVC_MEMOTMsg prmTMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMemoPk, svcMemoPk);
        return (SVC_MEMOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    public static DS_CONTR_BLLG_SCHD_SMRYTMsg findDsContrBllgSchdSmryForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_SCHD_SMRYTMsg prmTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    public static String getNextSeqNum(NSAL0330_ACMsg lastAcMsg) {
        int lastSqNum = Integer.parseInt(lastAcMsg.dsContrBllgSchdSqNum_A1.getValue());
        String sqNum = Integer.toString(lastSqNum + 1);
        return sqNum;
    }

    public static boolean isChanged(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        if (isChangedHeader(cMsg, sMsg)) {
            return true;
        }

        if (isChangedFooter(cMsg, sMsg)) {
            return true;
        }

        if (isChangedSchedules(cMsg, sMsg)) {
            return true;
        }
        return false;
    }

    public static boolean isChangedHeader(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {

        if (!sMsg.baseBllgTmgCd_H1.getValue().equals(cMsg.baseBllgTmgCd_H1.getValue())) {
            return true;
        }

        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        if (!sMsg.xxRadioBtn_H1.getValue().equals(cMsg.xxRadioBtn_H1.getValue())) {
//            return true;
//        }
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]

        // START 2016/08/05 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0330Constant.RADIO_VALUE_CLOSING_DAY.compareTo(cMsg.xxRadioBtn_H1.getValue()) == 0) {
//            // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//            if (!sMsg.baseDplyPerEndDay_H1.getValue().equals(cMsg.baseDplyPerEndDay_H1.getValue())) {
//            // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
//                return true;
//            }
//        }
        if (!sMsg.baseDplyPerEndDay_H1.getValue().equals(cMsg.baseDplyPerEndDay_H1.getValue())) {
            return true;
        }
        // END 2016/08/05 K.Kishimoto [QC#12879, MOD]

        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        if (!sMsg.xxRadioBtn_H2.getValue().equals(cMsg.xxRadioBtn_H2.getValue())) {
//            return true;
//        }
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]

        // START 2016/08/05 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0330Constant.RADIO_VALUE_BLLG_DAY.compareTo(cMsg.xxRadioBtn_H2.getValue()) == 0) {
//            if (!sMsg.contrBllgDay_H1.getValue().equals(cMsg.contrBllgDay_H1.getValue())) {
//                return true;
//            }
//        }
        if (!sMsg.contrBllgDay_H1.getValue().equals(cMsg.contrBllgDay_H1.getValue())) {
            return true;
        }
        // END 2016/08/05 K.Kishimoto [QC#12879, MOD]

        return false;
    }

    public static boolean isChangedFooter(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {

        if (!sMsg.svcMemoRsnCd_F3.getValue().equals(cMsg.svcMemoRsnCd_F3.getValue())) {
            return true;
        }

        if (!sMsg.svcCmntTxt_F1.getValue().equals(cMsg.svcCmntTxt_F1.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isChangedSchedules(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {

        if (sMsg.A.getValidCount() != cMsg.A.getValidCount()) {
            return true;
        }

        int cnt = sMsg.A.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NSAL0330_ASMsg asMsg = sMsg.A.no(i);
            NSAL0330_ACMsg acMsg = cMsg.A.no(i);
            if (!equalBigDecimal(acMsg.dsContrBllgSchdSmryPk_A1, asMsg.dsContrBllgSchdSmryPk_A1)) {
                return true;
            }

            if (!asMsg.dsContrBllgSchdSqNum_A1.getValue().equals(acMsg.dsContrBllgSchdSqNum_A1.getValue())) {
                return true;
            }

            if (!asMsg.perSchdNum_A1.getValue().equals(acMsg.perSchdNum_A1.getValue())) {
                return true;
            }

            if (!asMsg.perBllgCycleCd_A1.getValue().equals(acMsg.perBllgCycleCd_A1.getValue())) {
                return true;
            }

            if (!asMsg.bllgSchdFromDt_A1.getValue().equals(acMsg.bllgSchdFromDt_A1.getValue())) {
                return true;
            }

            if (!asMsg.bllgSchdThruDt_A1.getValue().equals(acMsg.bllgSchdThruDt_A1.getValue())) {
                return true;
            }

            // START 2015/10/21 T.Tomita [N/A, DEL]
//            if (!asMsg.bllgCycleCd_A3.getValue().equals(acMsg.bllgCycleCd_A3.getValue())) {
//                return true;
//            }
//
//            if (asMsg.basePrcDealAmt_A1.getValue().compareTo(acMsg.basePrcDealAmt_A1.getValue()) != 0) {
//                return true;
//            }
            // END 2015/10/21 T.Tomita [N/A, DEL]
        }
        return false;
    }

    public static void setBllgSchdThruDt(NSAL0330_ACMsg acMsg, NSAL0330CMsg cMsg, String glblCmpyCd) {
        BLLG_CYCLETMsgArray bllgCycleTMsgArray = getBLLG_CYCLETMsgArray(glblCmpyCd);
        int durationDays = NSAL0330CommonLogic.getDurationDays(bllgCycleTMsgArray, acMsg.perBllgCycleCd_A1.getValue());
        int addDays = durationDays * acMsg.perSchdNum_A1.getValue().intValue() - 1;
        String targetThruDt = ZYPDateUtil.addDays(acMsg.bllgSchdFromDt_A1.getValue(), (int) addDays);
        ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, targetThruDt);
    }

    public static void calcTermAmout(NSAL0330CMsg cMsg) {
        BigDecimal amout = BigDecimal.ZERO;
        int cnt = cMsg.A.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NSAL0330_ACMsg acMsg = cMsg.A.no(i);
            // add start 2015/12/04 CSA Defect#1450
            if (!ZYPCommonFunc.hasValue(acMsg.baseSubTotPrcDealAmt_A1)) {
                continue;
            }
            // add end 2015/12/04 CSA Defect#1450
            amout = amout.add(acMsg.baseSubTotPrcDealAmt_A1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(cMsg.invTotAmt_H1, amout);
    }

    public static void calcRowAmout(NSAL0330_ACMsg acMsg, NSAL0330CMsg cMsg, String glblCmpyCd) {
        BigDecimal amout = BigDecimal.ZERO;

        BLLG_CYCLETMsg uomBllgCycle = getBllgCycle(glblCmpyCd, acMsg.perBllgCycleCd_A1.getValue());
        // START 2015/10/21 T.Tomita [N/A, MOD]
        BLLG_CYCLETMsg bllgCycle = getBllgCycle(glblCmpyCd, acMsg.bllgCycleCd_A1.getValue());
        // END 2015/10/21 T.Tomita [N/A, MOD]

        BigDecimal basePrcDealAmt = BigDecimal.ZERO;
        // START 2015/10/21 T.Tomita [N/A, MOD]
        if (BLLG_CYCLE.CONTRACT_PERIOD.equals(acMsg.bllgCycleCd_A1.getValue())) {
            bllgCycle = getBllgCycle(glblCmpyCd, BLLG_CYCLE.DAILY);

            int distance = ZYPDateUtil.getDistance(cMsg.contrEffFromDt_H1.getValue(), cMsg.contrEffThruDt_H1.getValue(), ZYPDateUtil.CALENDAR_GENERAL) + 1;
            basePrcDealAmt = acMsg.basePrcDealAmt_A1.getValue().divide(BigDecimal.valueOf(distance), 4, RoundingMode.HALF_UP);

        } else {
            basePrcDealAmt = acMsg.basePrcDealAmt_A1.getValue();
        }
        // END 2015/10/21 T.Tomita [N/A, MOD]

        BLLG_CYCLETMsgArray bllgCycleTMsgArray = getBLLG_CYCLETMsgArray(glblCmpyCd);
        if (uomBllgCycle.bllgCycleSortNum.getValue().compareTo(bllgCycle.bllgCycleSortNum.getValue()) == 0) {
            amout = basePrcDealAmt.multiply(acMsg.perSchdNum_A1.getValue());

        } else if (uomBllgCycle.bllgCycleSortNum.getValue().compareTo(bllgCycle.bllgCycleSortNum.getValue()) < 0) {
            amout = basePrcDealAmt.multiply(acMsg.perSchdNum_A1.getValue());

            boolean targetFlag = false;
            int cnt = bllgCycleTMsgArray.length();
            for (int i = cnt - 1; i >= 0; i--) {
                BLLG_CYCLETMsg tMsg = bllgCycleTMsgArray.no(i);
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(tMsg.bllgCycleCd.getValue())) {
                    continue;
                }

                if (bllgCycle.bllgCycleCd.getValue().equals(tMsg.bllgCycleCd.getValue())) {
                    targetFlag = true;
                }
                if (uomBllgCycle.bllgCycleCd.getValue().equals(tMsg.bllgCycleCd.getValue())) {
                    break;
                }
                if (targetFlag) {
                    if (amout.compareTo(BigDecimal.ZERO) != 0) {
                        amout = amout.divide(tMsg.bllgCycleAot.getValue(), 4, RoundingMode.HALF_UP);
                    }
                }
            }
        } else {
            amout = basePrcDealAmt.multiply(acMsg.perSchdNum_A1.getValue());

            boolean targetFlag = false;
            int cnt = bllgCycleTMsgArray.length();
            for (int i = 0; i < cnt; i++) {
                BLLG_CYCLETMsg tMsg = bllgCycleTMsgArray.no(i);
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(tMsg.bllgCycleCd.getValue())) {
                    continue;
                }

                if (bllgCycle.bllgCycleCd.getValue().equals(tMsg.bllgCycleCd.getValue())) {
                    targetFlag = true;
                    continue;
                }
                if (targetFlag) {
                    if (amout.compareTo(BigDecimal.ZERO) != 0) {
                        amout = amout.multiply(tMsg.bllgCycleAot.getValue());
                    }
                }
                if (uomBllgCycle.bllgCycleCd.getValue().equals(tMsg.bllgCycleCd.getValue())) {
                    break;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(acMsg.baseSubTotPrcDealAmt_A1, amout);
    }

    public static BLLG_CYCLETMsgArray getBLLG_CYCLETMsgArray(String glblCmpyCd) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.ezCancelFlag.setValue("0");
        BLLG_CYCLETMsgArray bllgCycleTMsgArray = (BLLG_CYCLETMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
        return bllgCycleTMsgArray;
    }

    public static BLLG_CYCLETMsg getBllgCycle(String glblCmpyCd, String bllgCycleCd) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bllgCycleCd.setValue(bllgCycleCd);
        inMsg.ezCancelFlag.setValue("0");
        return (BLLG_CYCLETMsg) ZYPCodeDataUtil.findByKey(inMsg);
    }

    public static BLLG_CYCLETMsg getPrevBLLG_CYCLETMsg(BLLG_CYCLETMsgArray bllgCycleTMsgArray, BLLG_CYCLETMsg targetCycle) {
        BLLG_CYCLETMsg result = null;
        int cnt = bllgCycleTMsgArray.length();
        for (int i = 0; i < cnt; i++) {
            if (targetCycle.bllgCycleCd.getValue().equals(bllgCycleTMsgArray.no(i).bllgCycleCd.getValue())) {
                break;
            }
            result = bllgCycleTMsgArray.no(i);
        }
        return result;
    }

    public static int getDurationDays(BLLG_CYCLETMsgArray bllgCycleTMsgArray, String bllgCycleCd) {
        BigDecimal duration = BigDecimal.ZERO;
        int cnt = bllgCycleTMsgArray.length();
        for (int i = 0; i < cnt; i++) {
            if (BigDecimal.ZERO.compareTo(duration) == 0) {
                duration = bllgCycleTMsgArray.no(i).bllgCycleAot.getValue();
            } else {
                duration = duration.multiply(bllgCycleTMsgArray.no(i).bllgCycleAot.getValue());
            }
            if (bllgCycleCd.equals(bllgCycleTMsgArray.no(i).bllgCycleCd.getValue())) {
                break;
            }
        }
        return duration.intValue();
    }

    public static String getLastDate(String contrEffFromDt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate(contrEffFromDt));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return toYYYYMMDD(cal);
    }

    public static Date toDate(String yyyyMMdd) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(NSAL0330Constant.YYYYMMDD);
        Date result = null;
        try {
            result = dateFormat.parse(yyyyMMdd);
        } catch (ParseException e) {
        }
        return result;
    }

    public static String getEndDate(String yyyyMMdd, String cloDay) {
        int intFromDay = Integer.parseInt(yyyyMMdd.substring(6));
        int intCloDay = Integer.parseInt(cloDay);
        if (intFromDay == intCloDay) {
            return yyyyMMdd;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate(yyyyMMdd));
        cal.set(Calendar.DATE, intCloDay);

        if (intFromDay > intCloDay) {
            cal.add(Calendar.MONTH, 1);
        }
        return toYYYYMMDD(cal);
    }

    public static String toYYYYMMDD(Calendar cal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(NSAL0330Constant.YYYYMMDD);
        return dateFormat.format(cal.getTime());
    }

    public static boolean equalBigDecimal(EZDCBigDecimalItem cMsgValue, EZDSBigDecimalItem sMsgValue) {
        if (ZYPCommonFunc.hasValue(cMsgValue)) {
            if (ZYPCommonFunc.hasValue(sMsgValue)) {
                if (cMsgValue.getValue().compareTo(sMsgValue.getValue()) == 0) {
                    return true;
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(sMsgValue)) {
                return true;
            }
        }
        return false;
    }

    // START 2016/02/18 T.Tomita [QC#3892, ADD]
    /**
     * calcThruDate
     * @param acMsg NSAL0330_ACMsg
     * @param glblCmpyCd String
     */
    public static void calcThruDate(NSAL0330_ACMsg acMsg, String glblCmpyCd) {
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(glblCmpyCd);
        param.setContrEffFromDt(acMsg.bllgSchdFromDt_A1.getValue());
        param.setContrDurnNum(acMsg.perSchdNum_A1.getValue());
        param.setCycleUomCd(acMsg.perBllgCycleCd_A1.getValue());

        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcEndDt();
        // START 2017/08/03 E.Kameishi [QC#18586,MOD]
        if (!ZYPCommonFunc.hasValue(param.getContrEffThruDt())) {
            return;
        } else if (param.getContrEffThruDt().length() > NSAL0330Constant.MAX_NUM_DIGT_DATE) {
            acMsg.perSchdNum_A1.setErrorInfo(1, NSAL0330Constant.NSZM1054E);
            acMsg.perBllgCycleCd_A1.setErrorInfo(1, NSAL0330Constant.NSZM1054E);
            return;
        }
        // END 2017/08/03 E.Kameishi [QC#18586,MOD]
        ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, param.getContrEffThruDt());
    }

    /**
     * calcPerSchdUom
     * @param acMsg NSAL0330_ACMsg
     * @param glblCmpyCd String
     */
    public static void calcPerSchdUom(NSAL0330_ACMsg acMsg, String glblCmpyCd) {
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(glblCmpyCd);
        param.setContrEffFromDt(acMsg.bllgSchdFromDt_A1.getValue());
        param.setContrEffThruDt(acMsg.bllgSchdThruDt_A1.getValue());

        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcDuration();
        // Add Start 08/09/2016 <QC#12310>
        if (!ZYPCommonFunc.hasValue(param.getContrDurnNum())) {
            return;
        } else if (NSAL0330Constant.OVER_PER_SEQ.compareTo(param.getContrDurnNum().abs()) <= 0) {
            acMsg.bllgSchdFromDt_A1.setErrorInfo(1, NSAL0330Constant.NSZM1054E);
            acMsg.bllgSchdThruDt_A1.setErrorInfo(1, NSAL0330Constant.NSZM1054E);
            return;
        }
        // Add End   08/09/2016 <QC#12310>
        ZYPEZDItemValueSetter.setValue(acMsg.perSchdNum_A1, param.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(acMsg.perBllgCycleCd_A1, param.getCycleUomCd());
    }
    // END 2016/02/18 T.Tomita [QC#3892, ADD]

    // START 2016/08/05 K.Kishimoto [QC#12879, ADD]
    private static void createContrCloDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        createDayPullDownList(valueItemArray, dispItemArray);
    }

    // START 2017/12/11 U.Kim [QC#18779, MOD]
    //private static void createContrBllgDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
    //     createDayPullDownList(valueItemArray, dispItemArray);
    private static void createContrBllgDayPullDownList(NSAL0330CMsg cMsg, String glblCmpyCd) {
        createBllgDays(cMsg, glblCmpyCd);
    // END 2017/12/11 U.Kim [QC#18779, MOD]
    }

    private static void createDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        valueItemArray.clear();
        dispItemArray.clear();
        for (int i = 0; i < 28; i++) {
            ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), i + "");
            ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), i + "");
        }
        ZYPEZDItemValueSetter.setValue(valueItemArray.no(28), NSAL0330Constant.MAX_DAY);
        ZYPEZDItemValueSetter.setValue(dispItemArray.no(28), NSAL0330Constant.LAST_DAY);
    }
    // END 2016/08/05 K.Kishimoto [QC#12879, ADD]

    // START 2017/12/11 U.Kim [QC#18779, ADD]
    private static void createBllgDays(NSAL0330CMsg cMsg, String glblCmpyCd) {
        cMsg.contrBllgDay_BB.clear();
        cMsg.xxEdtDescTxt_BB.clear();
        S21SsmEZDResult res = NSAL0330Query.getInstance().getBllgDays(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue());
        if (!res.isCodeNormal()) {
            return;
        }
        List<String> bllgDaysList = (List<String>) res.getResultObject();
        for (int count = 0; count < bllgDaysList.size(); count++) {
            String bllgDaysAot = bllgDaysList.get(count);
            if (bllgDaysAot.equals(NSAL0330Constant.MAX_DAY)) {
                ZYPEZDItemValueSetter.setValue(cMsg.contrBllgDay_BB.no(count), bllgDaysAot);
                ZYPEZDItemValueSetter.setValue(cMsg.xxEdtDescTxt_BB.no(count), NSAL0330Constant.LAST_DAY);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.contrBllgDay_BB.no(count), bllgDaysAot);
                ZYPEZDItemValueSetter.setValue(cMsg.xxEdtDescTxt_BB.no(count), bllgDaysAot);
            }
        }
    }

    // END 2017/12/11 U.Kim [QC#18779, ADD]

}
