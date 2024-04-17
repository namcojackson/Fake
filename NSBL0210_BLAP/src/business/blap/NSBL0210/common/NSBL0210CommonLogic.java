/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0210.common;

import static business.blap.NSBL0210.constant.NSBL0210Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0210.NSBL0210CMsg;
import business.blap.NSBL0210.NSBL0210Query;
import business.blap.NSBL0210.NSBL0210SMsg;
import business.blap.NSBL0210.NSBL0210_ACMsg;
import business.blap.NSBL0210.NSBL0210_ACMsgArray;
import business.blap.NSBL0210.NSBL0210_ASMsg;
import business.db.DS_MDL_GRPTMsg;
import business.db.DS_MDL_GRPTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.SVC_LBOR_CHRGTMsg;
import business.db.SVC_LBOR_CHRGTMsgArray;
import business.db.SVC_PRC_SHIFTTMsg;
import business.db.SVC_PRC_SHIFTTMsgArray;
import business.file.NSBL0210F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


/**
 * <pre>
 * Labor Charge Table Maintenance
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/23   Hitachi         Y.Takeno        Update          QC#8565
 * 2016/05/23   Hitachi         Y.Takeno        Update          QC#8567
 * 2016/06/06   Hitachi         Y.Takeno        Update          QC#5489
 *</pre>
 */
public class NSBL0210CommonLogic {

    public static void createSvcLineBizPulldownList(NSBL0210CMsg cMsg) {
        S21SsmEZDResult ssmSvcLineBizResult = NSBL0210Query.getInstance().getSvcLineBizList();
        if (ssmSvcLineBizResult.isCodeNotFound()) {
            return;
        }
        List<Map<String, Object>> svcLineBizList = (List<Map<String, Object>>) ssmSvcLineBizResult.getResultObject();
        if (svcLineBizList.size() == 0) {
            return;
        }

        cMsg.lineBizTpCd_L.clear();
        cMsg.lineBizTpDescTxt_L.clear();
        for (int i = 0; i < svcLineBizList.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) svcLineBizList.get(i);
            cMsg.lineBizTpCd_L.no(i).setValue((String) map.get("LINE_BIZ_TP_CD"));
            cMsg.lineBizTpDescTxt_L.no(i).setValue((String) map.get("LINE_BIZ_TP_DESC_TXT"));
        }
    }

    /**
     * Paginate to item
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @param itemIndex itemIndex
     */
    public static void pagenation(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg, int itemIndex) {

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
     * copy To SMsg for Current Page Info
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        // NSBL0210_ACMsg -> NSBL0210_ASMsg
        NSBL0210_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSBL0210_ACMsg acMsg = (NSBL0210_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum.getValueInt() - 1;

            NSBL0210_ASMsg asMsg = (NSBL0210_ASMsg) sMsg.A.get(targetIdxNumA);

            // copy CMsg to SMsg
            EZDMsg.copy(acMsg, null, asMsg, null);
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
     * getLastPageNum
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @return int
     */
    public static int getLastPageNum(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            return BigDecimal.ONE.intValue();
        }
        return BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP).intValue();
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NSBL0210CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NSBL0210CMsg cMsg) {
        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        } else if (cMsg.xxPageShowTotNum.getValueInt() < cMsg.xxPageShowCurNum.getValueInt()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
    }

    /**
     * getFirstErrorIndex
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @return error page number
     */
    public static final int getFirstErrorIndex(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        int errIndex = convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isError(sMsg, i)) {
                errIndex = i;
                break;
            }
        }
        return errIndex;
    }

    /**
     * isError
     * @param sMsg NSBL0210SMsg
     * @param index index
     * @return true/false
     */
    public static final boolean isError(NSBL0210SMsg sMsg, int index) {
        NSBL0210_ASMsg asMsg = sMsg.A.no(index);

        if (asMsg.mdlGrpNm.isError()) {
            return true;
        }

        if (asMsg.svcLineBizCd.isError()) {
            return true;
        }

        if (asMsg.svcPrcTrvlChrgFlg.isError()) {
            return true;
        }

        if (asMsg.svcPrcShiftNum.isError()) {
            return true;
        }

        if (asMsg.svcLborUnitAmt.isError()) {
            return true;
        }

        if (asMsg.svcMinChrgHrsAot.isError()) {
            return true;
        }

        if (asMsg.intgMdseCd.isError()) {
            return true;
        }

        return false;
    }

    /**
     * setEmptyRecord
     * 
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @param index index
     */
    public static void setEmptyRecord(String glblCmpyCd, NSBL0210CMsg cMsg, NSBL0210SMsg sMsg, int index) {
        NSBL0210_ASMsg asMsg = sMsg.A.no(index);
        ZYPEZDItemValueSetter.setValue(asMsg.xxRowNum, BigDecimal.valueOf(index + 1));
        sMsg.A.setValidCount(index + 1);
    }

    /**
     * reNumberingRowNum
     * @param sMsg NSBL0210SMsg
     */
    public static void reNumberingRowNum(NSBL0210SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum, BigDecimal.valueOf(i + 1));
        }
    }

    /**
     * setFMsgToSMsg
     * @param fMsg NSBL0210F00FMsg
     * @param sMsg NSBL0210SMsg
     * @param rowIndex rowIndex
     */
    public static void setFMsgToSMsg(NSBL0210F00FMsg fMsg, NSBL0210SMsg sMsg, int rowIndex) {
        NSBL0210_ASMsg asMsg = sMsg.A.no(rowIndex);

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxRowNum, BigDecimal.valueOf(rowIndex + 1));
        ZYPEZDItemValueSetter.setValue(asMsg.mdlGrpNm, fMsg.mdlGrpNm);
        ZYPEZDItemValueSetter.setValue(asMsg.mdlGrpDescTxt, fMsg.mdlGrpDescTxt);
        ZYPEZDItemValueSetter.setValue(asMsg.svcLineBizCd, fMsg.svcLineBizCd);
        ZYPEZDItemValueSetter.setValue(asMsg.svcPrcTrvlChrgFlg, fMsg.svcPrcTrvlChrgFlg);
        ZYPEZDItemValueSetter.setValue(asMsg.svcPrcShiftNum, fMsg.svcPrcShiftNum);
        ZYPEZDItemValueSetter.setValue(asMsg.svcPrcShiftDescTxt, fMsg.svcPrcShiftDescTxt);
        ZYPEZDItemValueSetter.setValue(asMsg.svcLborUnitAmt, fMsg.svcLborUnitAmt);
        ZYPEZDItemValueSetter.setValue(asMsg.svcMinChrgHrsAot, fMsg.svcMinChrgHrsAot);
        ZYPEZDItemValueSetter.setValue(asMsg.intgMdseCd, fMsg.intgMdseCd);
        ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt, fMsg.mdseDescShortTxt);
    }

    /**
     * validateDetailLines
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @return true/false
     */
    public static boolean validateDetailLines(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        boolean result = true;

        // master existence check
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0210_ASMsg asMsg = sMsg.A.no(i);

            if (!isExistsDsMdlGrp(cMsg.glblCmpyCd.getValue(), asMsg.mdlGrpNm.getValue())) {
                asMsg.mdlGrpNm.setErrorInfo(1, NSBM0151E, new String[] {ITEM_NM_MDL_GRP_NM, TBL_NM_SVC_DS_MDL_GRP });
                result = false;
            }
            // START 06/06/2016 [QC#5489, MOD]
            // if (!isExistsSvcPrcShift(cMsg.glblCmpyCd.getValue(), asMsg.svcPrcShiftNum.getValue())) {
            if (!isExistsSvcPrcShift(cMsg.glblCmpyCd.getValue(), asMsg.svcPrcShiftNum.getValue(), asMsg.svcLineBizCd.getValue())) {
            // END 06/06/2016 [QC#5489, MOD]
                asMsg.svcPrcShiftNum.setErrorInfo(1, NSBM0151E, new String[] {ITEM_NM_SHIFT_NUM, TBL_NM_SVC_PRC_SHIFT });
                result = false;
            }
            if (!isExistsMdse(cMsg.glblCmpyCd.getValue(), asMsg.intgMdseCd.getValue())) {
                asMsg.intgMdseCd.setErrorInfo(1, NSBM0151E, new String[] {ITEM_NM_DEF_INTG_MDSE_CD, TBL_NM_MDSE });
                result = false;
            }
        }

        if (!result) {
            return false;
        }

        // consistency check
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0210_ASMsg asMsg = sMsg.A.no(i);

            // Shift#, Line of Business
            // START 06/06/2016 [QC#5489, MOD]
            // SVC_PRC_SHIFTTMsg svcPrcShift = NSBL0210CommonLogic.findSvcPrcShift(cMsg.glblCmpyCd.getValue(), asMsg.svcPrcShiftNum.getValue());
            SVC_PRC_SHIFTTMsg svcPrcShift = NSBL0210CommonLogic.findSvcPrcShift(cMsg.glblCmpyCd.getValue(), asMsg.svcPrcShiftNum.getValue(), asMsg.svcLineBizCd.getValue());
            // END 06/06/2016 [QC#5489, MOD]
            if (!asMsg.svcLineBizCd.getValue().equals(svcPrcShift.svcLineBizCd.getValue())) {
                sMsg.A.no(i).svcPrcShiftNum.setErrorInfo(1, NSBM0157E , new String[] {asMsg.svcPrcShiftNum.getValue(), asMsg.svcLineBizCd.getValue() });
                sMsg.A.no(i).svcLineBizCd.setErrorInfo(1, NSBM0157E , new String[] {asMsg.svcPrcShiftNum.getValue(), asMsg.svcLineBizCd.getValue() });
                result = false;
            }

            // Model Group, Line of Business (duplicate in sMsg)
            // START 05/24/2016 [QC#8567, MOD]
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                NSBL0210_ASMsg asMsgTgt = sMsg.A.no(j);
                String recordKey = createRecordGroupKey(asMsg);
                String tgtRecordKey = createRecordGroupKey(asMsgTgt);
                if (recordKey.equals(tgtRecordKey)) {
                    sMsg.A.no(i).mdlGrpNm.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                    sMsg.A.no(i).svcLineBizCd.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                    sMsg.A.no(i).svcPrcShiftNum.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });

                    sMsg.A.no(j).mdlGrpNm.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                    sMsg.A.no(j).svcLineBizCd.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                    sMsg.A.no(j).svcPrcShiftNum.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });

                    result = false;
                }
            }
            // END   05/24/2016 [QC#8567, MOD]

            // Model Group, Line of Business, Shift# (duplicate in table)
            if (!ZYPCommonFunc.hasValue(asMsg.svcLborChrgPk)) {
                // START 06/06/2016 [QC#5489, MOD]
                // int count = countupSvcLborChrg(cMsg.glblCmpyCd.getValue(), asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftPk.getValue());
                int count = countupSvcLborChrg(cMsg.glblCmpyCd.getValue(), asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue());
                // END 06/06/2016 [QC#5489, MOD]
                if (count > 0) {
                    sMsg.A.no(i).mdlGrpNm.setErrorInfo(1, NSBM0158E, new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue(), TBL_NM_SVC_LBOR_CHRG });
                    sMsg.A.no(i).svcLineBizCd.setErrorInfo(1, NSBM0158E, new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue(), TBL_NM_SVC_LBOR_CHRG });
                    sMsg.A.no(i).svcPrcShiftNum.setErrorInfo(1, NSBM0158E, new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue(), TBL_NM_SVC_LBOR_CHRG });
                    result = false;
                }
            }

            // Intangible Item
            MDSETMsg msdeTMsg = findMdse(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).intgMdseCd.getValue());
            if (!MDSE_ITEM_BILL_TP.LABOR.equals(msdeTMsg.mdseItemBillTpCd.getValue()) || !MDSE_ITEM_TP.INTANGIBLE.equals(msdeTMsg.mdseItemTpCd.getValue())) {
                sMsg.A.no(i).intgMdseCd.setErrorInfo(1, NSBM0152E);
                result = false;
            }
        }

        if (!result) {
            return false;
        }

        return true;
    }

    /**
     * validateDetailLinesForUpload
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @return true/false
     */
    public static boolean validateDetailLinesForUpload(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        boolean result = true;

        // consistency check
        Map<String, String> recordKeyMap = new HashMap<String, String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0210_ASMsg asMsg = sMsg.A.no(i);

            // Model Group, Line of Business (duplicate in sMsg)
            String recordKey = createRecordGroupKey(sMsg.A.no(i));
            if (recordKeyMap.containsKey(recordKey)) {
                sMsg.A.no(i).mdlGrpNm.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                sMsg.A.no(i).svcLineBizCd.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                sMsg.A.no(i).svcPrcShiftNum.setErrorInfo(1, NSBM0156E , new String[] {asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue() });
                result = false;
            }
            recordKeyMap.put(recordKey, recordKey);
        }

        return result;
    }

    private static String createRecordGroupKey(NSBL0210_ASMsg asMsg) {
        StringBuilder builder = new StringBuilder();
        if (ZYPCommonFunc.hasValue(asMsg.mdlGrpNm)) {
            builder.append(asMsg.mdlGrpNm.getValue());
        }
        builder.append(DELIM);
        if (ZYPCommonFunc.hasValue(asMsg.svcLineBizCd)) {
            builder.append(asMsg.svcLineBizCd.getValue());
        }
        builder.append(DELIM);
        if (ZYPCommonFunc.hasValue(asMsg.svcPrcShiftNum)) {
            builder.append(asMsg.svcPrcShiftNum.getValue());
        }
        return builder.toString();
    }

    /**
     * completionSMsgAfterUpload
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     */
    public static void completionSMsgAfterUpload(String glblCmpyCd, NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0210_ASMsg asMsg = sMsg.A.no(i);

            // find DS_MDL_GRP by MDL_GRP_NM
            DS_MDL_GRPTMsg mdlGrpTMsg = findDsMdlGrp(glblCmpyCd, asMsg.mdlGrpNm.getValue());
            if (mdlGrpTMsg != null) {
                ZYPEZDItemValueSetter.setValue(asMsg.mdlGrpId, mdlGrpTMsg.mdlGrpId);
                ZYPEZDItemValueSetter.setValue(asMsg.mdlGrpDescTxt, mdlGrpTMsg.mdlGrpDescTxt);
            }

            // find SVC_PRC_SHIFT by SVC_PRC_SHIFT_NUM
            // START 06/06/2016 [QC#5489, MOD]
            // SVC_PRC_SHIFTTMsg svcPrcShiftTMsg = findSvcPrcShift(glblCmpyCd, asMsg.svcPrcShiftNum.getValue());
            SVC_PRC_SHIFTTMsg svcPrcShiftTMsg = findSvcPrcShift(glblCmpyCd, asMsg.svcPrcShiftNum.getValue(), asMsg.svcLineBizCd.getValue());
            // END 06/06/2016 [QC#5489, MOD]
            if (svcPrcShiftTMsg != null) {
                // START 06/06/2016 [QC#5489, DEL]
                // ZYPEZDItemValueSetter.setValue(asMsg.svcPrcShiftPk, svcPrcShiftTMsg.svcPrcShiftPk);
                // END 06/06/2016 [QC#5489, DEL]
                ZYPEZDItemValueSetter.setValue(asMsg.svcPrcShiftDescTxt, svcPrcShiftTMsg.svcPrcShiftDescTxt);
            }

            // find MDSE by MDSE_CD
            MDSETMsg mdseTMsg = findMdse(glblCmpyCd, asMsg.intgMdseCd.getValue());
            if (mdseTMsg != null) {
                ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt, mdseTMsg.mdseDescShortTxt);
            }

            // find SVC_LBOR_CHRG by MDL_GRP_NM, SVC_LINE_BIZ_CD, SVC_PRC_SHIFT_NUM
            // START 06/06/2016 [QC#5489, MOD]
            // SVC_LBOR_CHRGTMsg svcLborChrgTMsg = findSvcLborChrg(glblCmpyCd, asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftPk.getValue());
            SVC_LBOR_CHRGTMsg svcLborChrgTMsg = findSvcLborChrg(glblCmpyCd, asMsg.mdlGrpNm.getValue(), asMsg.svcLineBizCd.getValue(), asMsg.svcPrcShiftNum.getValue());
            // END 06/06/2016 [QC#5489, MOD]
            if (svcLborChrgTMsg != null) {
                ZYPEZDItemValueSetter.setValue(asMsg.svcLborChrgPk, svcLborChrgTMsg.svcLborChrgPk);
                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTime, svcLborChrgTMsg.ezUpTime);
                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTimeZone, svcLborChrgTMsg.ezUpTimeZone);
            }

        }
    }

// START 06/06/2016 [QC#5489, MOD]
    /**
     * isExistsSvcLborChrg
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @param svcLineBizCd svcLineBizCd
     * @param svcPrcShiftNum svcPrcShiftNum
     * @return true/false
     */
    public static boolean isExistsSvcLborChrg(String glblCmpyCd, String mdlGrpNm, String svcLineBizCd, String svcPrcShiftNum) {
        if (findSvcLborChrg(glblCmpyCd, mdlGrpNm, svcLineBizCd, svcPrcShiftNum) != null) {
            return true;
        }
        return false;
    }

    /**
     * findSvcLborChrg
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @param svcLineBizCd svcLineBizCd
     * @param svcPrcShiftNum svcPrcShiftNum
     * @return SVC_LBOR_CHRGTMsg
     */
    public static SVC_LBOR_CHRGTMsg findSvcLborChrg(String glblCmpyCd, String mdlGrpNm, String svcLineBizCd, String svcPrcShiftNum) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(mdlGrpNm) || !ZYPCommonFunc.hasValue(svcLineBizCd) || !ZYPCommonFunc.hasValue(svcPrcShiftNum)) {
            return null;
        }

        SVC_LBOR_CHRGTMsg inMsg = new SVC_LBOR_CHRGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpNm01", mdlGrpNm);
        inMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);
        inMsg.setConditionValue("svcPrcShiftNum01", svcPrcShiftNum);

        SVC_LBOR_CHRGTMsgArray tMsgArray = (SVC_LBOR_CHRGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (SVC_LBOR_CHRGTMsg) tMsgArray.no(0);
        }

        return null;
    }

    /**
     * countupSvcLborChrg
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @param svcLineBizCd svcLineBizCd
     * @param svcPrcShiftNum svcPrcShiftNum
     * @return int
     */
    public static int countupSvcLborChrg(String glblCmpyCd, String mdlGrpNm, String svcLineBizCd, String svcPrcShiftNum) {
        SVC_LBOR_CHRGTMsg inMsg = new SVC_LBOR_CHRGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpNm01", mdlGrpNm);
        inMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);
        inMsg.setConditionValue("svcPrcShiftNum01", svcPrcShiftNum);

        return EZDTBLAccessor.count(inMsg);
    }

    /**
     * isExistsSvcPrcShift
     * @param glblCmpyCd glblCmpyCd
     * @param svcPrcShiftNum svcPrcShiftNum
     * @param svcLineBizCd svcLineBizCd
     * @return true/false
     */
    public static boolean isExistsSvcPrcShift(String glblCmpyCd, String svcPrcShiftNum, String svcLineBizCd) {
        // START 06/06/2016 [QC#5489, MOD]
        // if (findSvcPrcShift(glblCmpyCd, svcPrcShiftNum) != null) {
        if (findSvcPrcShift(glblCmpyCd, svcPrcShiftNum, svcLineBizCd) != null) {
        // END06/06/2016 [QC#5489, MOD]
            return true;
        }
        return false;
    }

    /**
     * findSvcPrcShift
     * @param glblCmpyCd glblCmpyCd
     * @param svcPrcShiftNum svcPrcShiftNum
     * @param svcLineBizCd svcLineBizCd
     * @return SVC_PRC_SHIFTTMsg
     */
    public static SVC_PRC_SHIFTTMsg findSvcPrcShift(String glblCmpyCd, String svcPrcShiftNum, String svcLineBizCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(svcPrcShiftNum)) {
            return null;
        }

        SVC_PRC_SHIFTTMsg inMsg = new SVC_PRC_SHIFTTMsg();
        inMsg.setSQLID("012");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPrcShiftNum01", svcPrcShiftNum);
        inMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);

        SVC_PRC_SHIFTTMsgArray tMsgArray = (SVC_PRC_SHIFTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (SVC_PRC_SHIFTTMsg) tMsgArray.no(0);
        }

        return null;
    }

// END 06/06/2016 [QC#5489, MOD]

    /**
     * isExistsMdse
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return true/false
     */
    public static boolean isExistsMdse(String glblCmpyCd, String mdseCd) {
        if (findMdse(glblCmpyCd, mdseCd) != null) {
            return true;
        }
        return false;
    }

    /**
     * isExistsDsMdlGrp
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @return true/false
     */
    public static boolean isExistsDsMdlGrp(String glblCmpyCd, String mdlGrpNm) {
        if (findDsMdlGrp(glblCmpyCd, mdlGrpNm) != null) {
            return true;
        }
        return false;
    }

    /**
     * findDsMdlGrp
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @return DS_MDL_GRPTMsg
     */
    public static DS_MDL_GRPTMsg findDsMdlGrp(String glblCmpyCd, String mdlGrpNm) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(mdlGrpNm)) {
            return null;
        }

        DS_MDL_GRPTMsg inMsg = new DS_MDL_GRPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpNm01", mdlGrpNm);

        DS_MDL_GRPTMsgArray tMsgArray = (DS_MDL_GRPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (DS_MDL_GRPTMsg) tMsgArray.no(0);
        }

        return null;
    }

    /**
     * findMdse
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return MDSETMsg
     */
    public static MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setSQLID("502");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);

        MDSETMsgArray tMsgArray = (MDSETMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (MDSETMsg) tMsgArray.no(0);
        }

        return null;
    }

}
