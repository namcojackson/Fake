/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0360.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0360.NSAL0360CMsg;
import business.blap.NSAL0360.NSAL0360Query;
import business.blap.NSAL0360.NSAL0360SMsg;
import business.blap.NSAL0360.NSAL0360_ACMsg;
import business.blap.NSAL0360.constant.NSAL0360Constant;
import business.db.BLLG_CYCLETMsg;
import business.db.BLLG_CYCLETMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitach          T.Tomita        Update          N/A
 * 2015/12/10   Hitachi         A.Kohinata      Update          QC1727
 * 2016/02/25   Hitachi         T.Aoyagi        Update          QC3422
 * 2016/03/01   Hitachi         T.Tomita        Update          QC#4131
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/08/08   Hitachi         K.Kishimoto     Update          QC#12879
 * 2017/12/12   Hitachi         U.Kim           Update          QC#18779
 *</pre>
 */
public class NSAL0360CommonLogic {

    /**
     * <pre>
     * createHeaderFooterPulldownList
     * </pre>
     * @param cMsg NSAL0360CMsg
     */
    // START 2015/12/10 [QC1727, MOD]
    public static void createHeaderFooterPulldownList(NSAL0360CMsg cMsg, String glblCmpyCd) {
        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, cMsg.bllgTmgTpCd_H1, cMsg.bllgTmgTpNm_H2);
        createSvcMemoRsnPullDown(cMsg, glblCmpyCd);
        // START 2016/08/08 K.Kishimoto [QC#12879, ADD]
        createContrCloDayPullDownList(cMsg.mtrDplyPerEndDay_MC, cMsg.xxEdtDescTxt_MC);
        // START 2017/12/12 U.Kim [QC#18779, MOD]
        //createContrBllgDayPullDownList(cMsg.mtrBllgDay_MB, cMsg.xxEdtDescTxt_MB);
        createContrBllgDayPullDownList(cMsg.mtrBllgDay_MB, cMsg.xxEdtDescTxt_MB, cMsg.dsContrDtlPk_H1, glblCmpyCd);
        // END 2017/12/12 U.Kim [QC#18779, MOD]
        // END 2016/08/08 K.Kishimoto [QC#12879, ADD]
    }
    // END 2015/12/10 [QC1727, MOD]

    // START 2015/12/10 [QC1727, ADD]
    private static void createSvcMemoRsnPullDown(NSAL0360CMsg cMsg, String glblCmpyCd) {
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
    // END 2015/12/10 [QC1727, ADD]

    /**
     * <pre>
     * createScheduleRowPulldownList
     * </pre>
     * @param acMsg NSAL0360_ACMsg
     */
    public static void createScheduleRowPulldownList(EZDMsg ezdCMsg, String tblNm) {

        try {
            ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE_UOM.class, (EZDCStringItemArray) ezdCMsg.getClass().getField(replSfx("bllgCycleUomCd_A1", tblNm)).get(ezdCMsg), (EZDCStringItemArray) ezdCMsg.getClass().getField(
                    replSfx("bllgCycleUomNm_A2", tblNm)).get(ezdCMsg));
            // START 2015/10/23 T.Tomita [N/A, MOD]
//            ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, (EZDCStringItemArray) ezdCMsg.getClass().getField(replSfx("bllgCycleCd_A1", tblNm)).get(ezdCMsg), (EZDCStringItemArray) ezdCMsg.getClass().getField(
//                    replSfx("bllgCycleNm_A2", tblNm)).get(ezdCMsg));
            // END 2015/10/23 T.Tomita [N/A, MOD]
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
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
    
    public static DS_CONTR_BLLG_SCHD_MTRTMsg findDsContrBllgSchdMtrForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdMtrPk) {
        DS_CONTR_BLLG_SCHD_MTRTMsg prmTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        return (DS_CONTR_BLLG_SCHD_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    public static DS_CONTR_BLLG_MTRTMsg findDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // START 2016/02/25 T.Aoyagi [QC3422, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsg
     */
    public static DS_CONTR_PRC_EFFTMsg findDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }
    // END 2016/02/25 T.Aoyagi [QC3422, ADD]
    public static String getNextSeqNum(NSAL0360_ACMsg lastAcMsg) {
        int lastSqNum = Integer.parseInt(lastAcMsg.dsContrBllgSchdSqNum_A1.getValue());
        String sqNum = Integer.toString(lastSqNum + 1);
        return sqNum;
    }

    public static String getNextSeqNum(EZDMsg lastEzdMsg, String tblNm) {
        EZDCStringItem sqNumItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(lastEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
        int lastSqNum = Integer.parseInt(sqNumItem.getValue());
        String sqNum = Integer.toString(lastSqNum + 1);
        return sqNum;
    }

    public static boolean isChanged(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
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

    public static boolean isChangedHeader(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        if (!sMsg.mtrBllgTmgCd_H1.getValue().equals(cMsg.mtrBllgTmgCd_H1.getValue())) {
            return true;
        }

        // START 2015/10/23 T.Tomita [N/A, DEL]
//        if (!sMsg.xsChrgTpCd_H3.getValue().equals(cMsg.xsChrgTpCd_H3.getValue())) {
//            return true;
//        }
        // END 2015/10/23 T.Tomita [N/A, DEL]

        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        if (!sMsg.xxRadioBtn_H1.getValue().equals(cMsg.xxRadioBtn_H1.getValue())) {
//            return true;
//        }
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]

        // START 2016/08/08 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0360Constant.RADIO_VALUE_CLOSING_DAY.compareTo(cMsg.xxRadioBtn_H1.getValue()) == 0) {
//            // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//            if (!sMsg.mtrDplyPerEndDay_H1.getValue().equals(cMsg.mtrDplyPerEndDay_H1.getValue())) {
//            // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
//                return true;
//            }
//        }
        if (!sMsg.mtrDplyPerEndDay_H1.getValue().equals(cMsg.mtrDplyPerEndDay_H1.getValue())) {
            return true;
        }
        // END 2016/08/08 K.Kishimoto [QC#12879, MOD]

        // START 2016/08/08 K.Kishimoto [QC#12879, DEL]
//        if (!sMsg.xxRadioBtn_H2.getValue().equals(cMsg.xxRadioBtn_H2.getValue())) {
//            return true;
//        }
        // END 2016/08/08 K.Kishimoto [QC#12879, DEL]

        // START 2016/08/08 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0360Constant.RADIO_VALUE_BLLG_DAY.compareTo(cMsg.xxRadioBtn_H2.getValue()) == 0) {
//            if (!sMsg.mtrBllgDay_H1.getValue().equals(cMsg.mtrBllgDay_H1.getValue())) {
//                return true;
//            }
//        }
        if (!sMsg.mtrBllgDay_H1.getValue().equals(cMsg.mtrBllgDay_H1.getValue())) {
            return true;
        }
        // END 2016/08/08 K.Kishimoto [QC#12879, MOD]

        return false;
    }

    public static boolean isChangedFooter(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        if (!sMsg.svcMemoRsnCd_F3.getValue().equals(cMsg.svcMemoRsnCd_F3.getValue())) {
            return true;
        }

        if (!sMsg.svcCmntTxt_F1.getValue().equals(cMsg.svcCmntTxt_F1.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isChangedSchedules(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            if (isChangedSchedules(cMsg, sMsg, tblNm)) {
                return true;
            }
        }
        return false;

    }

    public static boolean isChangedSchedules(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg, String tblNm) {

        EZDMsgArray cTblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
        EZDMsgArray sTblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(sMsg, tblNm);

        if (sTblArray.getValidCount() != cTblArray.getValidCount()) {
            return true;
        }

        int cnt = sTblArray.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg sEsdMsg = sTblArray.get(i);
            EZDMsg cEsdMsg = cTblArray.get(i);
            if (!equalBigDecimal(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "dsContrBllgSchdMtrPk_A1"), NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg_S(sEsdMsg, tblNm, "dsContrBllgSchdMtrPk_A1"))) {
                return true;
            }

            if (!equalBigDecimal(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "dsContrBllgSchdSmryPk_A1"), NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg_S(sEsdMsg, tblNm, "dsContrBllgSchdSmryPk_A1"))) {
                return true;
            }

            EZDSStringItem dsContrBllgSchdSqNum_S = NSAL0360CommonLogic.getStringValueFromEZDMsg_S(sEsdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            EZDCStringItem dsContrBllgSchdSqNum_C = NSAL0360CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            if (!dsContrBllgSchdSqNum_S.getValue().equals(dsContrBllgSchdSqNum_C.getValue())) {
                return true;
            }

            if (!equalBigDecimal(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "perSchdNum_A1"), NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg_S(sEsdMsg, tblNm, "perSchdNum_A1"))) {
                return true;
            }

            EZDSStringItem perBllgCycleCd_S = NSAL0360CommonLogic.getStringValueFromEZDMsg_S(sEsdMsg, tblNm, "perBllgCycleCd_A1");
            EZDCStringItem perBllgCycleCd_C = NSAL0360CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "perBllgCycleCd_A1");
            if (!perBllgCycleCd_S.getValue().equals(perBllgCycleCd_C.getValue())) {
                return true;
            }

            EZDSDateItem bllgSchdFromDt_S = NSAL0360CommonLogic.getDateValueFromEZDMsg_S(sEsdMsg, tblNm, "bllgSchdFromDt_A1");
            EZDCDateItem bllgSchdFromDt_C = NSAL0360CommonLogic.getDateValueFromEZDMsg(cEsdMsg, tblNm, "bllgSchdFromDt_A1");
            if (!bllgSchdFromDt_S.getValue().equals(bllgSchdFromDt_C.getValue())) {
                return true;
            }

            EZDSDateItem bllgSchdThruDt_S = NSAL0360CommonLogic.getDateValueFromEZDMsg_S(sEsdMsg, tblNm, "bllgSchdThruDt_A1");
            EZDCDateItem bllgSchdThruDt_C = NSAL0360CommonLogic.getDateValueFromEZDMsg(cEsdMsg, tblNm, "bllgSchdThruDt_A1");
            if (!bllgSchdThruDt_S.getValue().equals(bllgSchdThruDt_C.getValue())) {
                return true;
            }

            // START 2015/10/23 T.Tomita [N/A, DEL]
//            EZDSStringItem bllgMtrBllgCycleCd_S = NSAL0360CommonLogic.getStringValueFromEZDMsg_S(sEsdMsg, tblNm, "bllgCycleCd_A1");
//            EZDCStringItem bllgMtrBllgCycleCd_C = NSAL0360CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "bllgCycleCd_A1");
//
//            if (!bllgMtrBllgCycleCd_S.getValue().equals(bllgMtrBllgCycleCd_C.getValue())) {
//                return true;
//            }
//
//            if (!equalBigDecimal(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "xsMtrCopyQty_A1"), NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg_S(sEsdMsg, tblNm, "xsMtrCopyQty_A1"))) {
//                return true;
//            }
//
//            if (!equalBigDecimal(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "xsMtrAmtRate_A1"), NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg_S(sEsdMsg, tblNm, "xsMtrAmtRate_A1"))) {
//                return true;
//            }
            // END 2015/10/23 T.Tomita [N/A, MOD]
        }
        return false;
    }

    public static void setBllgSchdThruDt(EZDMsg ezdMsg, String tblNm, NSAL0360CMsg cMsg, String glblCmpyCd) {
        EZDCStringItem perBllgCycleCd = getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1");
        EZDCBigDecimalItem perSchdNum = getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1");
        EZDCDateItem bllgSchdFromDt = getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1");
        EZDCDateItem bllgSchdThruDt = getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1");

        BLLG_CYCLETMsgArray bllgCycleTMsgArray = getBLLG_CYCLETMsgArray(glblCmpyCd);
        int durationDays = NSAL0360CommonLogic.getDurationDays(bllgCycleTMsgArray, perBllgCycleCd.getValue());
        int addDays = durationDays * perSchdNum.getValue().intValue() - 1;
        String targetThruDt = ZYPDateUtil.addDays(bllgSchdFromDt.getValue(), addDays);
        ZYPEZDItemValueSetter.setValue(bllgSchdThruDt, targetThruDt);
    }

    public static EZDCStringItem getStringValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCStringItem result = null;
        try {
            result = (EZDCStringItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDCBigDecimalItem getBigDecimalValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCBigDecimalItem result = null;
        try {
            result = (EZDCBigDecimalItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDCDateItem getDateValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCDateItem result = null;
        try {
            result = (EZDCDateItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDCItem getItemValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDCItem result = null;
        try {
            result = (EZDCItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDMsgArray getTableArrayFromEZDMsg(NSAL0360CMsg cMsg, String tblNm) {
        EZDMsgArray result = null;
        try {
            result = (EZDMsgArray) cMsg.getClass().getField(tblNm).get(cMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDSStringItem getStringValueFromEZDMsg_S(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDSStringItem result = null;
        try {
            result = (EZDSStringItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDSBigDecimalItem getBigDecimalValueFromEZDMsg_S(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDSBigDecimalItem result = null;
        try {
            result = (EZDSBigDecimalItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDSDateItem getDateValueFromEZDMsg_S(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDSDateItem result = null;
        try {
            result = (EZDSDateItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDMsgArray getTableArrayFromEZDMsg(NSAL0360SMsg sMsg, String tblNm) {
        EZDMsgArray result = null;
        try {
            result = (EZDMsgArray) sMsg.getClass().getField(tblNm).get(sMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // START 2016/03/01 T.Tomita [QC#4131, DEL]
//    public static void calcRowAmout(EZDMsg ezdMsg, String tblNm, NSAL0360CMsg cMsg, String glblCmpyCd) {
//        // API
//        EZDCBigDecimalItem xsMtrCopyQtyAS = getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_AS");
//        BigDecimal amount = xsMtrCopyQtyAS.getValue();
//        if (amount == null) {
//            amount = new BigDecimal(100);
//        } else {
//            amount = amount.add(BigDecimal.TEN);
//        }
//        ZYPEZDItemValueSetter.setValue(xsMtrCopyQtyAS, amount);
//    }
    // END 2016/03/01 T.Tomita [QC#4131, DEL]

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
        SimpleDateFormat dateFormat = new SimpleDateFormat(NSAL0360Constant.YYYYMMDD);
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
        SimpleDateFormat dateFormat = new SimpleDateFormat(NSAL0360Constant.YYYYMMDD);
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

    // START 2016/03/01 T.Tomita [QC#4131, ADD]
    public static boolean equalBigDecimal(EZDCBigDecimalItem cMsgValue1, EZDCBigDecimalItem cMsgValue2) {
        if (ZYPCommonFunc.hasValue(cMsgValue1)) {
            if (ZYPCommonFunc.hasValue(cMsgValue2)) {
                if (cMsgValue1.getValue().compareTo(cMsgValue2.getValue()) == 0) {
                    return true;
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(cMsgValue2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalString(EZDCStringItem cMsgValue1, EZDCStringItem cMsgValue2) {
        if (ZYPCommonFunc.hasValue(cMsgValue1)) {
            if (ZYPCommonFunc.hasValue(cMsgValue2)) {
                if (cMsgValue1.getValue().equals(cMsgValue2.getValue())) {
                    return true;
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(cMsgValue2)) {
                return true;
            }
        }
        return false;
    }
    // END 2016/03/01 T.Tomita [QC#4131, ADD]

    public static String replSfx(String str, String tblNm) {
        return str.replaceAll("_.", "_" + tblNm);
    }

    // START 2016/08/08 K.Kishimoto [QC#12879, ADD]
    private static void createContrCloDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        createDayPullDownList(valueItemArray, dispItemArray);
    }

    // START 2017/12/12 U.Kim [QC#18779, MOD]
    //private static void createContrBllgDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
    //    createDayPullDownList(valueItemArray, dispItemArray);
    private static void createContrBllgDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, EZDCBigDecimalItem dsContrDtlPk, String glblCmpyCd) {
        createBllgDays(valueItemArray, dispItemArray, dsContrDtlPk, glblCmpyCd);
    // START 2017/12/12 U.Kim [QC#18779, MOD]
    }

    private static void createDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        valueItemArray.clear();
        dispItemArray.clear();
        for (int i = 0; i < 28; i++) {
            ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), i + "");
            ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), i + "");
        }
        ZYPEZDItemValueSetter.setValue(valueItemArray.no(28), NSAL0360Constant.MAX_DAY);
        ZYPEZDItemValueSetter.setValue(dispItemArray.no(28), NSAL0360Constant.LAST_DAY);
    }
    // END 2016/08/08 K.Kishimoto [QC#12879, ADD]

    // START 2017/12/11 U.Kim [QC#18779, ADD]
    private static void createBllgDays(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, EZDCBigDecimalItem dsContrDtlPk, String glblCmpyCd){
        valueItemArray.clear();
        dispItemArray.clear();
        int count = 0;
        S21SsmEZDResult res = NSAL0360Query.getInstance().getBllgDays(glblCmpyCd, dsContrDtlPk.getValue());
        List<BigDecimal> bllgDaysList = (List<BigDecimal>) res.getResultObject();
        if (bllgDaysList.size() == 0) {
            return;
        }
        for (BigDecimal days : bllgDaysList) {
            if(days.compareTo(BigDecimal.ZERO) == 0 || days.compareTo(BigDecimal.ZERO) == 1 && !(days.compareTo(new BigDecimal(NSAL0360Constant.MAX_DAY)) == 0)){
                ZYPEZDItemValueSetter.setValue(valueItemArray.no(count), days + "");
                ZYPEZDItemValueSetter.setValue(dispItemArray.no(count), days + "");
            }else if(days.compareTo(new BigDecimal(NSAL0360Constant.MAX_DAY)) == 0){
                ZYPEZDItemValueSetter.setValue(valueItemArray.no(count), days + "");
                ZYPEZDItemValueSetter.setValue(dispItemArray.no(count), NSAL0360Constant.LAST_DAY);
            }else{
                ZYPEZDItemValueSetter.setValue(valueItemArray.no(count), days + "");
                ZYPEZDItemValueSetter.setValue(dispItemArray.no(count), days + "");
            }
            count++;
        }
    }

    // END 2017/12/11 U.Kim [QC#18779, ADD]
    
}
