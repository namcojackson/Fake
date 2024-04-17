/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0840.common;

import static business.blap.NSAL0840.constant.NSAL0840Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0840.NSAL0840CMsg;
import business.blap.NSAL0840.NSAL0840SMsg;
import business.blap.NSAL0840.NSAL0840_ACMsg;
import business.blap.NSAL0840.NSAL0840_ACMsgArray;
import business.blap.NSAL0840.NSAL0840_ASMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADD_CHRG_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662,6707
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#12077
 * 2016/08/26   Hitachi         T.Mizuki        Update          QC#12077
 * 2016/11/28   Hitachi         T.Tomita        Update          QC#12077
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 *</pre>
 */
public class NSAL0840CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        // NSAL0840_ACMsg -> NSAL0840_ASMsg
        NSAL0840_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0840_ACMsg acMsg = (NSAL0840_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;
            EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(targetIdxNumA), null);
        }
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage rowsPerPage
     * @param page page
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * Paginate to item
     * @param cMsg NSAL0100CMsg
     * @param sMsg NSAL0100SMsg
     * @param itemIndex int
     */
    public static void pagenation(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * get Additional Charge Interface By Primary Key
     * @param glblCmpyCd glblCmpyCd
     * @param dsAddlChrgIntfcPk dsAddlChrgIntfcPk
     * @return DS_ADDL_CHRG_INTFCTMsg
     */
    public static DS_ADDL_CHRG_INTFCTMsg getDsAddlChrgIntfc(String glblCmpyCd, BigDecimal dsAddlChrgIntfcPk) {
        DS_ADDL_CHRG_INTFCTMsg prmTMsg = new DS_ADDL_CHRG_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAddlChrgIntfcPk, dsAddlChrgIntfcPk);
        return (DS_ADDL_CHRG_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * get Contract Interface By Primary Key
     * @param glblCmpyCd glblCmpyCd
     * @param dsContrIntfcPk dsContrIntfcPk
     * @return DS_CONTR_INTFCTMsg
     */
    public static DS_CONTR_INTFCTMsg getDsContrIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * setDsAddlChrgIntfc
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     * @return tMsgList
     */
    public static List<DS_ADDL_CHRG_INTFCTMsg> setDsAddlChrgIntfc(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        List<DS_ADDL_CHRG_INTFCTMsg> tMsgList = new ArrayList<DS_ADDL_CHRG_INTFCTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {

                DS_ADDL_CHRG_INTFCTMsg tMsg = new DS_ADDL_CHRG_INTFCTMsg();
                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(tMsg.dsAddlChrgIntfcPk, sMsg.A.no(i).dsAddlChrgIntfcPk_A);
                setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
                setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
                setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
                setValue(tMsg.addChrgIntfcStsCd, sMsg.A.no(i).dsContrIntfcStsCd_A);
                tMsg.intfcErrMsgTxt.clear();
                setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
                setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);

                setValue(tMsg.chrgLvlTpCd, sMsg.A.no(i).chrgLvlTpCd_A);
                setValue(tMsg.addlChrgTpCd, sMsg.A.no(i).addlChrgTpCd_A);
                setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A);
                setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A);
                setValue(tMsg.bllgCycleCd, sMsg.A.no(i).bllgCycleCd_A);
                setValue(tMsg.addlChrgFlatDealPrcAmt, sMsg.A.no(i).addlChrgFlatDealPrcAmt_A);
                setValue(tMsg.addlChrgAplcPct, sMsg.A.no(i).addlChrgAplcPct_A);
                setValue(tMsg.addlChrgInvTpCd, sMsg.A.no(i).addlChrgInvTpCd_A);
                setValue(tMsg.printDtlFlg, sMsg.A.no(i).printDtlFlg_A);

                setValue(tMsg.ezUpTime, sMsg.A.no(i).ezUpTime);
                setValue(tMsg.ezUpTimeZone, sMsg.A.no(i).ezUpTimeZone);
                tMsgList.add(tMsg);
            }
        }
        return tMsgList;
    }

    /**
     * updateValidationResult
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     * @param rstMsgList List<DS_ADDL_CHRG_INTFCTMsg>
     * @param validFlg validFlg
     */
    public static void updateValidationResult(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg, List<DS_ADDL_CHRG_INTFCTMsg> rstMsgList, boolean validFlg) {

        for (DS_ADDL_CHRG_INTFCTMsg rstMsg : rstMsgList) {

            if (hasValue(rstMsg.intfcErrMsgTxt)) {
                setValue(rstMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.ERROR);
            } else {
                setValue(rstMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.NORMAL);
            }

            if (validFlg && hasValue(rstMsg.dsAddlChrgIntfcPk)) {

                DS_ADDL_CHRG_INTFCTMsg tMsg = getDsAddlChrgIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsAddlChrgIntfcPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                setValue(tMsg.addChrgIntfcStsCd, rstMsg.addChrgIntfcStsCd);
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                } else {
                    tMsg.intfcErrMsgTxt.clear();
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {DS_ADDL_CHRG_INTFC });
                    return;
                }

                setValue(rstMsg.ezUpTime, tMsg.ezUpTime);
                setValue(rstMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
            }

            // 2016/04/07 START[QC#5662, MOD]
            if (validFlg && !hasValue(rstMsg.dsAddlChrgIntfcPk) && hasValue(rstMsg.ezUpTime) && hasValue(rstMsg.ezUpTimeZone)) {

                BigDecimal dsContrIntfcPk = getDsContrIntfcPk(sMsg, rstMsg);
                DS_CONTR_INTFCTMsg tMsg = NSAL0840CommonLogic.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), dsContrIntfcPk);
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                setValue(tMsg.dsContrIntfcPk, dsContrIntfcPk);
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.dsContrIntfcStsCd, rstMsg.addChrgIntfcStsCd);
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {DS_CONTR_INTFC });
                    return;
                }
                setValue(rstMsg.ezUpTime, tMsg.ezUpTime);
                setValue(rstMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
            }
            // 2016/04/07 END[QC#5662, MOD]

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(j).xxChkBox_A.getValue())) {
                    if (matchLine(sMsg.A.no(j), rstMsg)) {

                        if (hasValue(rstMsg.intfcErrMsgTxt)) {
                            String messageId = rstMsg.intfcErrMsgTxt.getValue().substring(0, MESSAGE_ID_LENGTH);
                            setValue(sMsg.A.no(j).xxMsgId_A, (String) messageId);
                        } else {
                            setValue(sMsg.A.no(j).xxMsgId_A, (String) null);
                        }
                        setValue(sMsg.A.no(j).intfcErrMsgTxt_A, rstMsg.intfcErrMsgTxt);
                        setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, rstMsg.addChrgIntfcStsCd);
                        setValue(sMsg.A.no(j).ezUpTime, rstMsg.ezUpTime);
                        setValue(sMsg.A.no(j).ezUpTimeZone, rstMsg.ezUpTimeZone);
                    }
                }
            }
        }
    }

    private static boolean matchLine(NSAL0840_ASMsg asMsg, DS_ADDL_CHRG_INTFCTMsg rstMsg) {

        if (!isMatchObject(asMsg.dsContrSrcRefNum_A, rstMsg.dsContrSrcRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrIntfcSrcTpCd_A, rstMsg.contrIntfcSrcTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsAddlChrgIntfcPk_A, rstMsg.dsAddlChrgIntfcPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.serNum_A, rstMsg.serNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcMachMstrPk_A, rstMsg.svcMachMstrPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.mdseCd_A, rstMsg.mdseCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.chrgLvlTpCd_A, rstMsg.chrgLvlTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgTpCd_A, rstMsg.addlChrgTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.effFromDt_A, rstMsg.effFromDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.effThruDt_A, rstMsg.effThruDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllgCycleCd_A, rstMsg.bllgCycleCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgFlatDealPrcAmt_A, rstMsg.addlChrgFlatDealPrcAmt)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgAplcPct_A, rstMsg.addlChrgAplcPct)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgInvTpCd_A, rstMsg.addlChrgInvTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.printDtlFlg_A, rstMsg.printDtlFlg)) {
            return false;
        }

        return true;
    }

    private static boolean isMatchObject(EZDSStringItem asMsgObj, EZDTStringItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchObject(EZDSBigDecimalItem asMsgObj, EZDTBigDecimalItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (asMsgObj.getValue().compareTo(rstMsgObj.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchObject(EZDSDateItem asMsgObj, EZDTDateItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    // 2016/04/07 [QC#5662, ADD]
    private static BigDecimal getDsContrIntfcPk(NSAL0840SMsg sMsg, DS_ADDL_CHRG_INTFCTMsg rstMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (matchLine(sMsg.A.no(i), rstMsg)) {
                return sMsg.A.no(i).dsContrIntfcPk_A.getValue();
            }
        }
        return null;
    }

    // START 2016/07/22 M.Gotou [QC#12077, MOD]
    /**
     * <pre>
     * get SVC_MACH_MSTR_PK
     * </pre>
     * @param glblCmpyCd String
     * @param serNum String
     */
    public static BigDecimal getIbId(String glblCmpyCd, String serNum) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum);
        SVC_MACH_MSTRTMsgArray tMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        // mod start 2016/08/26 CSA QC#12077
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
        // mod end 2016/08/26 CSA QC#12077
            return null;
        }
        return tMsgArray.no(0).svcMachMstrPk.getValue();
    }

    /**
     * <pre>
     * get SVC_MACH_MSTR_PK
     * </pre>
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     */
    public static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg svcMachMstr = new SVC_MACH_MSTRTMsg();
        setValue(svcMachMstr.glblCmpyCd, glblCmpyCd);
        setValue(svcMachMstr.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(svcMachMstr);
    }
    // END 2016/07/22 M.Gotou [QC#12077, MOD]
    // add start 2016/11/28 CSA Defect#12077
    /**
     * <pre>
     * get SVC_MACH_MSTR for Serial#
     * </pre>
     * @param glblCmpyCd String
     * @param serNum String
     */
    public static SVC_MACH_MSTRTMsg getSvcMachMstrForSerNum(String glblCmpyCd, String serNum) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum);
        SVC_MACH_MSTRTMsgArray tMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }
    // add end 2016/11/28 CSA Defect#12077

    // START 2017/05/26 Y.Osawa [QC#18560, ADD]
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
    // END   2017/05/26 Y.Osawa [QC#18560, ADD]
}
