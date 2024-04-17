/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0820.common;

import static business.blap.NSAL0820.constant.NSAL0820Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACTL_CNT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0820.NSAL0820CMsg;
import business.blap.NSAL0820.NSAL0820SMsg;
import business.blap.NSAL0820.NSAL0820_ACMsg;
import business.blap.NSAL0820.NSAL0820_ACMsgArray;
import business.blap.NSAL0820.NSAL0820_ASMsg;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12077
 * 2016/09/05   Hitachi         T.Mizuki        Update          QC#12083
 * 2016/11/28   Hitachi         T.Tomita        Update          QC#12077
 *</pre>
 */
public class NSAL0820CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        // NSAL0820_ACMsg -> NSAL0820_ASMsg
        NSAL0820_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0820_ACMsg acMsg = (NSAL0820_ACMsg) acMsgArray.no(i);
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
    public static void pagenation(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg, int itemIndex) {

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
     * setDsActlCntIntfc
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     * @return tMsgList
     */
    public static List<DS_ACTL_CNT_INTFCTMsg> setDsActlCntIntfc(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        List<DS_ACTL_CNT_INTFCTMsg> tMsgList = new ArrayList<DS_ACTL_CNT_INTFCTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                DS_ACTL_CNT_INTFCTMsg tMsg = new DS_ACTL_CNT_INTFCTMsg();
                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(tMsg.dsActlCntIntfcPk, sMsg.A.no(i).dsActlCntIntfcPk_A);
                setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
                setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
                setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
                setValue(tMsg.actlCntIntfcStsCd, sMsg.A.no(i).dsContrIntfcStsCd_A);
                tMsg.intfcErrMsgTxt.clear();
                setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
                setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
                setValue(tMsg.physMtrLbCd, sMsg.A.no(i).physMtrLbCd_A);
                setValue(tMsg.bllblFlg, sMsg.A.no(i).bllblFlg_A);
                setValue(tMsg.contrMtrMultRate, sMsg.A.no(i).contrMtrMultRate_A);
                setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
                setValue(tMsg.intgMdseCd, sMsg.A.no(i).intgMdseCd_A);
                setValue(tMsg.ezUpTime, sMsg.A.no(i).ezUpTime);
                setValue(tMsg.ezUpTimeZone, sMsg.A.no(i).ezUpTimeZone);
                tMsgList.add(tMsg);
            }
        }
        return tMsgList;
    }

    /**
     * updateValidationResult
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     * @param rstMsgList List<DS_ACTL_CNT_INTFCTMsg>
     * @param validFlg validFlg
     */
    public static void updateValidationResult(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg, List<DS_ACTL_CNT_INTFCTMsg> rstMsgList, boolean validFlg) {

        for (DS_ACTL_CNT_INTFCTMsg rstMsg : rstMsgList) {

            if (hasValue(rstMsg.intfcErrMsgTxt)) {
                setValue(rstMsg.actlCntIntfcStsCd, ACTL_CNT_INTFC_STS.ERROR);
            } else {
                setValue(rstMsg.actlCntIntfcStsCd, ACTL_CNT_INTFC_STS.NORMAL);
            }

            if (validFlg && hasValue(rstMsg.dsActlCntIntfcPk)) {
                // for update
                DS_ACTL_CNT_INTFCTMsg tMsg = getActualCounterInterfac(cMsg.glblCmpyCd.getValue(), rstMsg.dsActlCntIntfcPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                setValue(tMsg.actlCntIntfcStsCd, rstMsg.actlCntIntfcStsCd);
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {DS_ACTL_CNT_INTFC });
                    return;
                }

                setValue(rstMsg.ezUpTime, tMsg.ezUpTime);
                setValue(rstMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
            }

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
                        setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, rstMsg.actlCntIntfcStsCd);
                        setValue(sMsg.A.no(j).ezUpTime, rstMsg.ezUpTime);
                        setValue(sMsg.A.no(j).ezUpTimeZone, rstMsg.ezUpTimeZone);
                    }
                }
            }
        }
    }

    /**
     * get ActualCounterInterfac By Primary Key
     * @param glblCmpyCd String
     * @param dsActlCntIntfcPk BigDecimal
     * @return DS_ACTL_CNT_INTFCTMsg
     */
    public static DS_ACTL_CNT_INTFCTMsg getActualCounterInterfac(String glblCmpyCd, BigDecimal dsActlCntIntfcPk) {
        DS_ACTL_CNT_INTFCTMsg prmTMsg = new DS_ACTL_CNT_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsActlCntIntfcPk, dsActlCntIntfcPk);
        return (DS_ACTL_CNT_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static boolean matchLine(NSAL0820_ASMsg asMsg, DS_ACTL_CNT_INTFCTMsg rstMsg) {
        if (!isMatchObject(asMsg.dsContrSrcRefNum_A, rstMsg.dsContrSrcRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrSrcRefNum_A, rstMsg.dsContrSrcRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrIntfcSrcTpCd_A, rstMsg.contrIntfcSrcTpCd)) {
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
        if (!isMatchObject(asMsg.physMtrLbCd_A, rstMsg.physMtrLbCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllblFlg_A, rstMsg.bllblFlg)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrMtrMultRate_A, rstMsg.contrMtrMultRate)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllgMtrLbCd_A, rstMsg.bllgMtrLbCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.intgMdseCd_A, rstMsg.intgMdseCd)) {
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
            // mod start 2016/03/21 CSA Defect#3218
            if (asMsgObj.getValue().compareTo(rstMsgObj.getValue()) != 0) {
                return false;
            }
            // mod end 2016/03/21 CSA Defect#3218
        }
        return true;
    }

    // START 2016/07/28 M.Gotou [QC#12077, ADD]
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
        // mod start 2016/09/05 CSA QC#12083
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
        // mod end 2016/08/26 CSA QC#12083
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
    // START 2016/07/28 M.Gotou [QC#12077, ADD]
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
