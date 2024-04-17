/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0850.common;

import static business.blap.NSAL0850.constant.NSAL0850Constant.MESSAGE_ID_LENGTH;
import static business.blap.NSAL0850.constant.NSAL0850Constant.NSAM0031E;
import static business.blap.NSAL0850.constant.NSAL0850Constant.PRC_ALLOC_INTFC;
import static business.blap.NSAL0850.constant.NSAL0850Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0850.NSAL0850CMsg;
import business.blap.NSAL0850.NSAL0850SMsg;
import business.blap.NSAL0850.NSAL0850_ACMsg;
import business.blap.NSAL0850.NSAL0850_ACMsgArray;
import business.blap.NSAL0850.NSAL0850_ASMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ALLOC_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Sales Credit for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#12077
 * 2016/08/26   Hitachi         T.Mizuki        Update          QC#12077
 * 2016/11/29   Hitachi         T.Tomita        Update          QC#12077
 *</pre>
 */

public class NSAL0850CommonLogic {
    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {

        // NSAL0850_ACMsg -> NSAL0850_ASMsg
        NSAL0850_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0850_ACMsg acMsg = (NSAL0850_ACMsg) acMsgArray.no(i);
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
    public static void pagenation(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg, int itemIndex) {

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
     * setPrcAllocIntfc
     * 
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @return tMsgList
     */
    public static List<PRC_ALLOC_INTFCTMsg> setPrcAllocIntfc(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg) {
        List<PRC_ALLOC_INTFCTMsg> tMsgList = new ArrayList<PRC_ALLOC_INTFCTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                PRC_ALLOC_INTFCTMsg tMsg = new PRC_ALLOC_INTFCTMsg();
                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(tMsg.prcAllocIntfcPk, sMsg.A.no(i).prcAllocIntfcPk_A);
                setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
                setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
                setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_A);
                setValue(tMsg.prcAllocIntfcTpCd, sMsg.A.no(i).prcAllocIntfcTpCd_A);
                setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
                setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
                setValue(tMsg.tocCd, sMsg.A.no(i).tocCd_A);
                setValue(tMsg.tocNm, sMsg.A.no(i).tocNm_A);
                setValue(tMsg.prcAllocRate, sMsg.A.no(i).prcAllocRate_A);
                setValue(tMsg.allocIntfcStsCd, sMsg.A.no(i).dsContrIntfcStsCd_A);
                tMsg.intfcErrMsgTxt.clear();
                setValue(tMsg.ezUpTime, sMsg.A.no(i).ezUpTime);
                setValue(tMsg.ezUpTimeZone, sMsg.A.no(i).ezUpTimeZone);
                tMsgList.add(tMsg);
            }
        }
        return tMsgList;
    }

    /**
     * updateValidationResult
     * @param cMsg NSAL0850CMsg
     * @param sMsg NSAL0850SMsg
     * @param rstMsgList List<PRC_ALLOC_INTFCTMsg>
     * @param validFlg validFlg
     */
    public static void updateValidationResult(NSAL0850CMsg cMsg, NSAL0850SMsg sMsg, List<PRC_ALLOC_INTFCTMsg> rstMsgList, boolean validFlg) {

        for (PRC_ALLOC_INTFCTMsg rstMsg : rstMsgList) {

            if (hasValue(rstMsg.intfcErrMsgTxt)) {
                setValue(rstMsg.allocIntfcStsCd, ALLOC_INTFC_STS.ERROR);
            } else {
                setValue(rstMsg.allocIntfcStsCd, ALLOC_INTFC_STS.NORMAL);
            }

            if (validFlg && hasValue(rstMsg.prcAllocIntfcPk)) {
                // for update
                PRC_ALLOC_INTFCTMsg tMsg = getPrcAllocInterface(cMsg.glblCmpyCd.getValue(), rstMsg.prcAllocIntfcPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                setValue(tMsg.allocIntfcStsCd, rstMsg.allocIntfcStsCd);
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {PRC_ALLOC_INTFC });
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
                        setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, rstMsg.allocIntfcStsCd);
                        setValue(sMsg.A.no(j).ezUpTime, rstMsg.ezUpTime);
                        setValue(sMsg.A.no(j).ezUpTimeZone, rstMsg.ezUpTimeZone);
                    }
                }
            }
        }
    }

    /**
     * get PRC_ALLOC_INTFC By Primary Key
     * @param glblCmpyCd String
     * @param prcAllocIntfcPk BigDecimal
     * @return PRC_ALLOC_INTFCTMsg
     */
    public static PRC_ALLOC_INTFCTMsg getPrcAllocInterface(String glblCmpyCd, BigDecimal prcAllocIntfcPk) {
        PRC_ALLOC_INTFCTMsg prmTMsg = new PRC_ALLOC_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prcAllocIntfcPk, prcAllocIntfcPk);
        return (PRC_ALLOC_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static boolean matchLine(NSAL0850_ASMsg asMsg, PRC_ALLOC_INTFCTMsg rstMsg) {

        if (!isMatchObject(asMsg.prcAllocIntfcPk_A, rstMsg.prcAllocIntfcPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrIntfcSrcTpCd_A, rstMsg.contrIntfcSrcTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrSrcRefNum_A, rstMsg.dsContrSrcRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrIntfcBatNum_A, rstMsg.dsContrIntfcBatNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.prcAllocIntfcTpCd_A, rstMsg.prcAllocIntfcTpCd)) {
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
        if (!isMatchObject(asMsg.tocCd_A, rstMsg.tocCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.tocNm_A, rstMsg.tocNm)) {
            return false;
        }
        if (!isMatchObject(asMsg.prcAllocRate_A, rstMsg.prcAllocRate)) {
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

 // START 2016/07/27 M.Gotou [QC#12077, MOD]
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
    // END 2016/07/27 M.Gotou [QC#12077, MOD]
    // add start 2016/11/29 CSA Defect#12077
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
    // add end 2016/11/29 CSA Defect#12077
}
