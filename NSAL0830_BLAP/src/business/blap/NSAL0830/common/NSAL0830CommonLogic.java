/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0830.common;

import static business.blap.NSAL0830.constant.NSAL0830Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0830.NSAL0830CMsg;
import business.blap.NSAL0830.NSAL0830Query;
import business.blap.NSAL0830.NSAL0830SMsg;
import business.blap.NSAL0830.NSAL0830_ACMsg;
import business.blap.NSAL0830.NSAL0830_ACMsgArray;
import business.blap.NSAL0830.NSAL0830_ASMsg;
import business.blap.NSAL0830.NSAL0830_DSMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_COPY_INTFC_STS;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5648
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#5450
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#6411
 * 2016/04/19   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11853,11854,12077
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11831,11853
 * 2016/08/26   Hitachi         T.Mizuki        Update          QC#12077
 * 2016/11/28   Hitachi         T.Tomita        Update          QC#12077
 *</pre>
 */
public class NSAL0830CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        // NSAL0830_ACMsg -> NSAL0830_ASMsg
        NSAL0830_ACMsgArray acMsgArray = cMsg.A;
//        sMsg.A.setValidCount(acMsgArray.getValidCount());
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0830_ACMsg acMsg = (NSAL0830_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;
            // ADD start 2016/04/07 CSA Defect#6411
            int nextTargetIdxNumA = acMsg.xxRowNum_A.getValueInt();
            if (nextTargetIdxNumA < acMsgArray.getValidCount()) {
                if (checkSameBllgMtr(cMsg, targetIdxNumA, nextTargetIdxNumA)) {
                    copyPrntToChild(cMsg, targetIdxNumA, nextTargetIdxNumA);
                }
            }
            // // ADD end 2016/04/06 CSA Defect#6411
            EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(targetIdxNumA), null);
        }
    }

    // ADD start 2016/04/07 CSA Defect#6411
    /**
     * check same billing meter
     * @param cMsg NSAL0830CMsg
     * @param cur int
     * @param next int
     */
    private static boolean checkSameBllgMtr(NSAL0830CMsg cMsg, int cur, int next) {

        if (!hasValue(cMsg.A.no(cur).dsContrIntfcPk_A)) {
            return false;
        }
        if (!hasValue(cMsg.A.no(cur).dsContrIntfcBatNum_A)) {
            return false;
        }
        if (!hasValue(cMsg.A.no(cur).bllgMtrLbCd_A)) {
            return false;
        }
        if (!hasValue(cMsg.A.no(next).dsContrIntfcPk_A)) {
            return false;
        }
        if (!hasValue(cMsg.A.no(next).dsContrIntfcBatNum_A)) {
            return false;
        }
        if (!hasValue(cMsg.A.no(next).bllgMtrLbCd_A)) {
            return false;
        }
        if (cMsg.A.no(cur).dsContrIntfcPk_A.getValue().compareTo(cMsg.A.no(next).dsContrIntfcPk_A.getValue()) == 0
                && cMsg.A.no(cur).dsContrIntfcBatNum_A.getValue().equals(cMsg.A.no(next).dsContrIntfcBatNum_A.getValue())
                && cMsg.A.no(cur).bllgMtrLbCd_A.getValue().equals(cMsg.A.no(next).bllgMtrLbCd_A.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * copy parent info to child
     * @param cMsg NSAL0830CMsg
     * @param cur int
     * @param next int
     */
    public static void copyPrntToChild(NSAL0830CMsg cMsg, int cur, int next) {

        setValue(cMsg.A.no(next).xxChkBox_A, cMsg.A.no(cur).xxChkBox_A);
        setValue(cMsg.A.no(next).dsContrIntfcBatNum_A, cMsg.A.no(cur).dsContrIntfcBatNum_A);
        setValue(cMsg.A.no(next).dsContrSrcRefNum_A, cMsg.A.no(cur).dsContrSrcRefNum_A);
        setValue(cMsg.A.no(next).contrIntfcSrcTpCd_A, cMsg.A.no(cur).contrIntfcSrcTpCd_A);
        setValue(cMsg.A.no(next).dsContrNum_A, cMsg.A.no(cur).dsContrNum_A);
        setValue(cMsg.A.no(next).dsContrIntfcActCd_A, cMsg.A.no(cur).dsContrIntfcActCd_A);
        setValue(cMsg.A.no(next).dsContrIntfcActDescTxt_A, cMsg.A.no(cur).dsContrIntfcActDescTxt_A);
        setValue(cMsg.A.no(next).serNum_A, cMsg.A.no(cur).serNum_A);
        setValue(cMsg.A.no(next).svcMachMstrPk_A, cMsg.A.no(cur).svcMachMstrPk_A);
        setValue(cMsg.A.no(next).mdseCd_A, cMsg.A.no(cur).mdseCd_A);
        setValue(cMsg.A.no(next).bllgMtrLbCd_A, cMsg.A.no(cur).bllgMtrLbCd_A);
        setValue(cMsg.A.no(next).mtrLbDescTxt_A, cMsg.A.no(cur).mtrLbDescTxt_A);
        setValue(cMsg.A.no(next).xsMtrLvlNum_A, cMsg.A.no(cur).xsMtrLvlNum_A);
        setValue(cMsg.A.no(next).dsContrIntfcStsCd_A, cMsg.A.no(cur).dsContrIntfcStsCd_A);
        setValue(cMsg.A.no(next).intfcErrMsgTxt_A, cMsg.A.no(cur).intfcErrMsgTxt_A);
        setValue(cMsg.A.no(next).dsContrProcStsDescTxt_A, cMsg.A.no(cur).dsContrProcStsDescTxt_A);
        setValue(cMsg.A.no(next).dsContrProcStsCd_A, cMsg.A.no(cur).dsContrProcStsCd_A);
        setValue(cMsg.A.no(next).dsContrIntfcPk_A, cMsg.A.no(cur).dsContrIntfcPk_A);
        setValue(cMsg.A.no(next).xxMsgId_A, cMsg.A.no(cur).xxMsgId_A);

    }
    // ADD end 2016/04/06 CSA Defect#6411

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
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @param itemIndex int
     */
    public static void pagenation(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, int itemIndex) {

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
     * numbering xxRowNum.
     * @param sMsg NSAL0830SMsg
     */
    public static void numberingRowNum(NSAL0830SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
    }

    /**
     * get Excess Copy Interface By Primary Key
     * 
     * @param glblCmpyCd glblCmpyCd
     * @param dsXsCopyIntfcPk dsXsCopyIntfcPk
     * @return DS_XS_COPY_INTFCTMsg
     */
    public static DS_XS_COPY_INTFCTMsg getDsXsCopyIntfc(String glblCmpyCd, BigDecimal dsXsCopyIntfcPk) {
        DS_XS_COPY_INTFCTMsg prmTMsg = new DS_XS_COPY_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsXsCopyIntfcPk, dsXsCopyIntfcPk);
        return (DS_XS_COPY_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * get Contract Interface By Primary Key
     * 
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
     * setDsXsCopyIntfc
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @return tMsgList
     */
    public static List<DS_XS_COPY_INTFCTMsg> setDsXsCopyIntfc(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        List<DS_XS_COPY_INTFCTMsg> tMsgList = new ArrayList<DS_XS_COPY_INTFCTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {

                DS_XS_COPY_INTFCTMsg tMsg = new DS_XS_COPY_INTFCTMsg();
                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(tMsg.dsXsCopyIntfcPk, sMsg.A.no(i).dsXsCopyIntfcPk_A);
                setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
                setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
                setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
                setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
                setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);

                setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
                // START 2016/03/22 K.Yamada [QC#5648, MOD]
                if (hasValue(sMsg.A.no(i).mtrLbDescTxt_A)) {
                    int length = sMsg.A.no(i).mtrLbDescTxt_A.getValue().length();
                    if (length > LENGTH_30) {
                        length = LENGTH_30;
                    }
                    setValue(tMsg.bllgMtrLbNm, sMsg.A.no(i).mtrLbDescTxt_A.getValue().substring(length));
                }
                // END 2016/03/22 K.Yamada [QC#5648, MOD]
                setValue(tMsg.xsMtrCopyQty, sMsg.A.no(i).xsMtrCopyQty_A);
                setValue(tMsg.xsMtrAmtRate, sMsg.A.no(i).xsMtrAmtRate_A);
                setValue(tMsg.xsMtrLvlNum, sMsg.A.no(i).xsMtrLvlNum_A);

                setValue(tMsg.xsCopyIntfcStsCd, sMsg.A.no(i).dsContrIntfcStsCd_A);
                tMsg.intfcErrMsgTxt.clear();
                setValue(tMsg.ezUpTime, sMsg.A.no(i).ezUpTime);
                setValue(tMsg.ezUpTimeZone, sMsg.A.no(i).ezUpTimeZone);
                tMsgList.add(tMsg);
            }
        }
        return tMsgList;
    }

    //  ADD start 2016/04/19 CSA Defect#6691
    public static void setTargetDeleteList(NSAL0830SMsg sMsg, int rowIndex) {
        if (hasValue(sMsg.A.no(rowIndex).dsXsCopyIntfcPk_A)) {
            int idx = sMsg.D.getValidCount();
            setValue(sMsg.D.no(idx).dsContrIntfcPk_D, sMsg.A.no(rowIndex).dsContrIntfcPk_A);
            setValue(sMsg.D.no(idx).dsXsCopyIntfcPk_D, sMsg.A.no(rowIndex).dsXsCopyIntfcPk_A);
            EZDMsg.copy(sMsg.A.get(rowIndex), null, sMsg.D.get(idx), null);
            sMsg.D.setValidCount(idx + 1);
        }

        List<Integer> clearSmsgList = new ArrayList<Integer>();
        clearSmsgList.add(Integer.valueOf(rowIndex));
        ZYPTableUtil.deleteRows(sMsg.A, clearSmsgList);
    }

    /**
     * clear Target Delete List (Save)
     * 
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    public static void clearTargetDeleteList(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        for (int j = 0; j < sMsg.D.getValidCount(); j++) {
            if (hasValue(sMsg.D.no(j).dsXsCopyIntfcPk_D)) {
                deleteForDeleteList(sMsg.D.no(j), cMsg);
            }
        }
        ZYPTableUtil.clear(sMsg.D);
    }

    /**
     * clear Target Delete List (CMN_Delete, Validate data)
     * 
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    public static void clearSelectedTargetDeleteList(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        List<Integer> clearDmsgList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            if (targetRow(sMsg.D.no(i).dsContrIntfcPk_D.getValue(), sMsg.A.no(i).dsContrIntfcPk_A.getValue(), sMsg)) {
                deleteForDeleteList(sMsg.D.no(i), cMsg);
                clearDmsgList.add(Integer.valueOf(i));
            }
        }
        ZYPTableUtil.deleteRows(sMsg.D, clearDmsgList);
    }


    private static boolean targetRow(BigDecimal dsContrIntfcPk_D, BigDecimal dsContrIntfcPk_A, NSAL0830SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (dsContrIntfcPk_A.compareTo(dsContrIntfcPk_D) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void deleteForDeleteList(NSAL0830_DSMsg dsMsg, NSAL0830CMsg cMsg) {
        DS_XS_COPY_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsXsCopyIntfc(cMsg.glblCmpyCd.getValue(), dsMsg.dsXsCopyIntfcPk_D.getValue());

        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(dsMsg.ezUpTime.getValue(), dsMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        EZDTBLAccessor.logicalRemove(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {DS_XS_COPY_INTFC });
            return;
        }
    }

    public static void deleteForDeleteList(NSAL0830_ASMsg asMsg, NSAL0830CMsg cMsg) {
        DS_XS_COPY_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsXsCopyIntfc(cMsg.glblCmpyCd.getValue(), asMsg.dsXsCopyIntfcPk_A.getValue());

        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime.getValue(), asMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        EZDTBLAccessor.logicalRemove(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {DS_XS_COPY_INTFC });
            return;
        }
        setValue(asMsg.ezUpTime, tMsg.ezUpTime);
        setValue(asMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
    }
    //  ADD end 2016/04/19 CSA Defect#6691

    /**
     * updateValidationResult
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @param rstMsgList List<DS_ADDL_CHRG_INTFCTMsg>
     * @param validFlg validFlg
     */
    public static void updateValidationResult(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg, List<DS_XS_COPY_INTFCTMsg> rstMsgList, boolean validFlg) {

        for (DS_XS_COPY_INTFCTMsg rstMsg : rstMsgList) {

            if (hasValue(rstMsg.intfcErrMsgTxt)) {
                setValue(rstMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.ERROR);
            } else {
                setValue(rstMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.NORMAL);
            }

            if (validFlg && hasValue(rstMsg.dsXsCopyIntfcPk)) {

                DS_XS_COPY_INTFCTMsg tMsg = getDsXsCopyIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsXsCopyIntfcPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                setValue(tMsg.xsCopyIntfcStsCd, rstMsg.xsCopyIntfcStsCd);
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                } else {
                    tMsg.intfcErrMsgTxt.clear();
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {DS_XS_COPY_INTFC });
                    return;
                }

                setValue(rstMsg.ezUpTime, tMsg.ezUpTime);
                setValue(rstMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
            }

            // MOD start 2016/04/19 CSA Defect#6691
            if (validFlg && !hasValue(rstMsg.dsXsCopyIntfcPk) && hasValue(rstMsg.ezUpTime) && hasValue(rstMsg.ezUpTimeZone) && getXxTpCd(sMsg, rstMsg)) {

                BigDecimal dsContrIntfcPk = getDsContrIntfcPk(sMsg, rstMsg);
                DS_CONTR_INTFCTMsg tMsg = NSAL0830CommonLogic.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), dsContrIntfcPk);
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
                    setValue(tMsg.dsContrIntfcStsCd, rstMsg.xsCopyIntfcStsCd);
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
            // MOD end 2016/04/19 CSA Defect#6691

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(j).xxChkBox_A.getValue())) {
                    if (matchLine(sMsg.A.no(j), rstMsg)) {

                        if (hasValue(rstMsg.intfcErrMsgTxt)) {
                            String messageId = rstMsg.intfcErrMsgTxt.getValue().substring(0, MESSAGE_ID_LENGTH);
                            setValue(sMsg.A.no(j).xxMsgId_A, (String) messageId);
                        } else {
                            sMsg.A.no(j).xxMsgId_A.clear();
                        }
                        setValue(sMsg.A.no(j).intfcErrMsgTxt_A, rstMsg.intfcErrMsgTxt);
                        setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, rstMsg.xsCopyIntfcStsCd);

                        if (validFlg && hasValue(rstMsg.dsXsCopyIntfcPk)) {
                            setValue(sMsg.A.no(j).ezUpTime, rstMsg.ezUpTime);
                            setValue(sMsg.A.no(j).ezUpTimeZone, rstMsg.ezUpTimeZone);
                        }
                    }
                }
            }
        }
        setErrorMessageToHeader(sMsg);
    }

    // MOD start 2016/04/19 CSA Defect#6691
    /**
     * setErrorMessageToHeader
     * 
     * @param sMsg NSAL0830SMsg
     */
    public static void setErrorMessageToHeader(NSAL0830SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                BigDecimal pkHeader = sMsg.A.no(i).dsContrIntfcPk_A.getValue();
                // START 2016/03/23 [QC#5450, MOD]
//                sMsg.A.no(i).xxMsgId_A.clear();
//                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, XS_COPY_INTFC_STS.NORMAL);
//                sMsg.A.no(i).intfcErrMsgTxt_A.clear();
                // END   2016/03/23 [QC#5450, MOD]
                if (hasValue(sMsg.A.no(i).xxMsgId_A)) {
                    continue;
                } else {
                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        // MOD start 2016/04/07 CSA Defect#6411
                        BigDecimal pkDetail = sMsg.A.no(j).dsContrIntfcPk_A.getValue();
                        // MOD end 2016/04/07 CSA Defect#6411
                        if (hasValue(pkDetail) && ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(j).xxDplyCtrlFlg_A.getValue())) {
                            if (hasValue(sMsg.A.no(j).intfcErrMsgTxt_A) && pkDetail.compareTo(pkHeader) == 0) {
                                setValue(sMsg.A.no(i).xxMsgId_A, sMsg.A.no(j).xxMsgId_A);
                                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, sMsg.A.no(j).dsContrIntfcStsCd_A);
                                setValue(sMsg.A.no(i).intfcErrMsgTxt_A, sMsg.A.no(j).intfcErrMsgTxt_A);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    // MOD end 2016/04/19 CSA Defect#6691

    // MOD start 2016/04/19 CSA Defect#6691
    private static boolean matchLine(NSAL0830_ASMsg asMsg, DS_XS_COPY_INTFCTMsg rstMsg) {

        if (!isMatchObject(asMsg.dsContrSrcRefNum_A, rstMsg.dsContrSrcRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrIntfcSrcTpCd_A, rstMsg.contrIntfcSrcTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsXsCopyIntfcPk_A, rstMsg.dsXsCopyIntfcPk)) {
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
        if (!isMatchObject(asMsg.bllgMtrLbCd_A, rstMsg.bllgMtrLbCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.xsMtrCopyQty_A, rstMsg.xsMtrCopyQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.xsMtrAmtRate_A, rstMsg.xsMtrAmtRate)) {
            return false;
        }
        if (!isMatchObject(asMsg.xsMtrLvlNum_A, rstMsg.xsMtrLvlNum)) {
            return false;
        }

        return true;
    }
    // MOD end 2016/04/19 CSA Defect#6691

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

    // ADD start 2016/04/19 CSA Defect#6691
    private static BigDecimal getDsContrIntfcPk(NSAL0830SMsg sMsg, DS_XS_COPY_INTFCTMsg rstMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (matchLine(sMsg.A.no(i), rstMsg)) {
                return sMsg.A.no(i).dsContrIntfcPk_A.getValue();
            }
        }
        return null;
    }

    private static boolean getXxTpCd(NSAL0830SMsg sMsg, DS_XS_COPY_INTFCTMsg rstMsg) {
        boolean flg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (matchLine(sMsg.A.no(i), rstMsg)) {
                if (XX_TP_CD_H.equals(sMsg.A.no(i).xxTpCd_A.getValue())) {
                    flg = true;
                }
            }
        }
        return flg;
    }

    /**
     * <pre>
     * checkInput for table
     * </pre>
     * @param sMsg NSAL0830SMsg
     * @param i int
     * @param cMsg NSAL0830CMsg
     * @return count
     */
    public static int setErrorMsg(NSAL0830SMsg sMsg, int i, NSAL0830CMsg cMsg) {
        int count = 0;
        // START 2016/08/09 Y.Takeno [QC#11853 DEL]
//        if (!hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
//            // START 2016/07/22 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0554E));
//            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0554E);
//            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0554E);
//            // END 2016/07/22 M.Gotou [QC#11853,11854, MOD]
//            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
//            count++;
//            return count;
//        }
        // END 2016/08/09 Y.Takeno [QC#11853 DEL]

        if (!hasValue(sMsg.A.no(i).dsContrIntfcBatNum_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        }

        if (!hasValue(sMsg.A.no(i).dsContrSrcRefNum_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        }

        if (!hasValue(sMsg.A.no(i).contrIntfcSrcTpCd_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        }

        if (!hasValue(sMsg.A.no(i).bllgMtrLbCd_A)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {BLLG_MTR_LB_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {BLLG_MTR_LB_CD});
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0362E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return count;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        }

     // START 2016/07/22 M.Gotou [QC#12077, ADD]
        if (hasValue(sMsg.A.no(i).svcMachMstrPk_A)) {
            SVC_MACH_MSTRTMsg tMsg = getSvcMachMstr(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).svcMachMstrPk_A.getValue());
            if (tMsg != null) {
                if (hasValue(sMsg.A.no(i).serNum_A)) {
                    if (!tMsg.serNum.getValue().equals(sMsg.A.no(i).serNum_A.getValue())) {
                        setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0556E));
                        sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0556E);
                        setValue(sMsg.A.no(i).xxMsgId_A, NSAM0556E);
                        setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        count++;
                        // START 2016/08/09 Y.Takeno [QC#11831, ADD]
                        return count;
                        // END 2016/08/09 Y.Takeno [QC#11831, ADD]
                    }
                }
                if (hasValue(sMsg.A.no(i).mdseCd_A)) {
                    if (!tMsg.mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A.getValue())) {
                        setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0557E));
                        sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0557E);
                        setValue(sMsg.A.no(i).xxMsgId_A, NSAM0557E);
                        setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        count++;
                        // START 2016/08/09 Y.Takeno [QC#11831, ADD]
                        return count;
                        // END 2016/08/09 Y.Takeno [QC#11831, ADD]
                    }
                }
            } else {
                setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SVC_MACH_MSTR_PK}));
                sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SVC_MACH_MSTR_PK});
                setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                count++;
                // START 2016/08/09 Y.Takeno [QC#11831, ADD]
                return count;
                // END 2016/08/09 Y.Takeno [QC#11831, ADD]
            }
        } else {
            if (hasValue(sMsg.A.no(i).serNum_A)) {
                BigDecimal svcMachMstrPk = getIbId(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).serNum_A.getValue());
                if (!hasValue(svcMachMstrPk)) {
                    setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SER_NUM}));
                    sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SER_NUM});
                    setValue(sMsg.A.no(i).xxMsgId_A, NSAM0011E);
                    setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    count++;
                    // START 2016/08/09 Y.Takeno [QC#11831, ADD]
                    return count;
                    // END 2016/08/09 Y.Takeno [QC#11831, ADD]
                }
            }
        }
        // END 2016/07/22 M.Gotou [QC#12077, ADD]

        // START 2016/08/09 Y.Takeno [QC#11831, ADD]
        S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getDsContrIntfcPk(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i));
        Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
        if (rs == null) {
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0554E));
            sMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0554E);
            setValue(sMsg.A.no(i).xxMsgId_A, NSAM0554E);
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            count++;
            return count;
        }
        // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        return count;
    }

    // START 2016/07/22 M.Gotou [QC#12077, MOD]
    public static boolean checkParam(NSAL0830SMsg sMsg, NSAL0830CMsg cMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (setErrorMsg(sMsg, i, cMsg) > 0) {
                flg = false;
            }
        }
        return flg;
    }

    public static boolean checkSelectedParam(NSAL0830SMsg sMsg, NSAL0830CMsg cMsg) {
        boolean flg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (setErrorMsg(sMsg, i, cMsg) > 0) {
                    flg = false;
                }
            }
        }
        return flg;
    }

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
    // ADD end 2016/04/19 CSA Defect#6691
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
}
