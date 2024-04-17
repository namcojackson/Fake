/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0220.common;

import static business.blap.NFAL0220.constant.NFAL0220Constant.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCSVInFile;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0220.NFAL0220CMsg;
import business.blap.NFAL0220.NFAL0220Query;
import business.blap.NFAL0220.NFAL0220SMsg;
import business.blap.NFAL0220.NFAL0220_ASMsg;

import business.db.JRNL_CATGTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.db.JRNL_ENTRYTMsgArray;
import business.db.JRNL_ENTRY_SRC_TPTMsg;
import business.db.JRNL_ENTRY_SRC_TPTMsgArray;
import business.db.MAN_JRNL_ENTRY_DTLTMsg;
import business.db.MAN_JRNL_ENTRY_HDRTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NFAL0220F00FMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC104001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NFA.NFZC104001.NFZC104001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.JRNL_ENTRY_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NFAL0220CommonLogic
 * Manual Journal Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/06/21   Hitachi         J.Kim           Update          QC#10325
 * 2016/07/06   Hitachi         J.Kim           Update          QC#10325
 * 2016/07/13   Hitachi         J.Kim           Update          QC#10324
 * 2016/07/13   Hitachi         J.Kim           Update          QC#11481
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#12766
 * 2016/08/07   Hitachi         J.Kim           Update          QC#12851
 * 2016/08/22   Hitachi         J.Kim           Update          QC#13537
 * 2016/09/14   Fujitsu         S.Yoshida       Update          QC#14463
 * 2016/09/23   Hitachi         K.Kojima        Update          QC#14463
 * 2016/10/20   Hitachi         J.Kim           Update          QC#13460
 *</pre>
 */
public class NFAL0220CommonLogic {

    /**
     * clearMsg
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     */
    public static void clearMsg(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(sMsg.E);
    }

    /**
     * checkSelect
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     * @return boolean
     */
    public static boolean checkSelect(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // START 2016/07/13 J.Kim [QC#10324,DEL]
        // sMsg.D.clear();
        // END 2016/07/13 J.Kim [QC#10324,DEL]
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NFAM0075E);
            return false;
        } else {
            int dsMsg = sMsg.D.getValidCount();
            for (Integer line : selectedRows) {
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(dsMsg++).manJrnlEntryDtlPk, sMsg.A.no(line).manJrnlEntryDtlPk_A);
            }
            sMsg.D.setValidCount(sMsg.D.getValidCount() + selectedRows.size());

            ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        }

        calculationTotal(cMsg, sMsg);
        return true;
    }

    /**
     * uploadFile
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     * @return boolean
     */
    public static boolean uploadFile(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        String path = cMsg.xxFileData.getTempFilePath();
        NFAL0220F00FMsg fMsg = new NFAL0220F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);

        try {

            if (mappedFile.read() == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return false;
            }

            int index = 0;
            int sValidCount = 0;
            int sLength = sMsg.A.length();
            int a0length = fMsg.getAttr("jrnlDealAmt_A0").getDigit();
            int a1length = fMsg.getAttr("jrnlDealAmt_A1").getDigit();
            // do basic check and load to screen(for all csv data)
            while ((mappedFile.read()) != 1) {

                sValidCount = sMsg.A.getValidCount();
                if (sValidCount == sLength) {
                    // error message 10000 Over
                    cMsg.setMessageInfo(NFAM0072E, new String[] {"Record", String.valueOf(sLength) });
                    return false;
                }

                index++;
                EZDSStringItem xxChkBox = sMsg.A.no(sValidCount).xxChkBox_A;
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).xxNum_A, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).xxScrItem61Txt_A, fMsg.xxScrItem61Txt.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).jrnlDealAmt_A1, checkLengthBigDecimalItem(xxChkBox, fMsg.jrnlDealAmt_A0.getValue(), a0length));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).jrnlDealAmt_A2, checkLengthBigDecimalItem(xxChkBox, fMsg.jrnlDealAmt_A1.getValue(), a1length));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).manJrnlLineTxt_A, fMsg.manJrnlLineTxt.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).dsAcctNum_A, fMsg.dsAcctNum.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).serNum_A, fMsg.serNum.getValue());
                // START 2016/08/09 J.Kim [QC#12851,MOD] 
                // ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).jrnlEntrySrcTpCd_LS, getJrnlEntrySrcTpCd(cMsg, sMsg, fMsg.jrnlEntrySrcTpNm.getValue(), sValidCount));
                if (ZYPCommonFunc.hasValue(fMsg.jrnlEntrySrcTpNm)) {
                    String jrnlEntrySrcTpCd = getJrnlEntrySrcTpCd(cMsg, fMsg.jrnlEntrySrcTpNm.getValue());
                    if (ZYPCommonFunc.hasValue(jrnlEntrySrcTpCd)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).jrnlEntrySrcTpCd_LS, jrnlEntrySrcTpCd);
                    } else {
                        sMsg.A.no(sValidCount).jrnlEntrySrcTpCd_LS.setErrorInfo(1, NFAM0043E, new String[] {"Source Type" });
                    }
                }
                // END 2016/08/09 J.Kim [QC#12851,MOD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sValidCount).jrnlEntrySrcValTxt_A, fMsg.jrnlEntrySrcValTxt.getValue());
                sMsg.A.setValidCount(sValidCount + 1);
                fMsg.clear();
            }
            if (mappedFile.getReadRecCount() <= 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            calculationTotal(cMsg, sMsg);
        } finally {
            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvFilePath);
        }
        return true;
    }

    private static BigDecimal checkLengthBigDecimalItem(EZDSStringItem xxChkBox_A, BigDecimal bdAmt, int length) {

        if (bdAmt != null && bdAmt.precision() > length) {
            xxChkBox_A.setErrorInfo(1, NFAM0169E);
            return null;
        }
        return bdAmt;
    }

    private static String getJrnlEntrySrcTpCd(NFAL0220CMsg cMsg, String jrnlEntrySrcTpNm) {

        String jrnlEntrySrcTpCd = null;
        if (jrnlEntrySrcTpNm != null && !jrnlEntrySrcTpNm.isEmpty()) {
            JRNL_ENTRY_SRC_TPTMsgArray jrnlEntrySrcTpTMsg = NFAL0220Query.getInstance().getJrnLEntrySrcTpFindByCondition(cMsg, jrnlEntrySrcTpNm);
            if (jrnlEntrySrcTpTMsg == null || jrnlEntrySrcTpTMsg.getValidCount() == 0) {
                // START 2016/08/09 J.Kim [QC#12851,MOD]
                // cMsg.xxFileData.setErrorInfo(1, NFAM0043E, new String[] {"Source Type" });
                return null;
                // END 2016/08/09 J.Kim [QC#12851,MOD]
            } else {
                jrnlEntrySrcTpCd = jrnlEntrySrcTpTMsg.no(0).jrnlEntrySrcTpCd.getValue();
            }
        }
        return jrnlEntrySrcTpCd;
    }

    // START 2016/07/05 J.Kim [QC#10325,ADD]
    private static boolean isCheckManJrnlEntryDtlPk(NFAL0220SMsg sMsg) {

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            NFAL0220_ASMsg asMsg = sMsg.A.no(index);
            if (ZYPCommonFunc.hasValue(asMsg.xxScrItem61Txt_A)) {
                return true;
            }
        }
        return false;
    }
    // END 2016/07/05 J.Kim [QC#10325,ADD]

    /**
     * isErrorSubmitCondition
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     * @return boolean
     */
    public static boolean isErrorSubmitCondition(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // START 2016/07/05 J.Kim [QC#10325,ADD]
        if (!isCheckManJrnlEntryDtlPk(sMsg)) {
            cMsg.setMessageInfo(NFAM0176E);
            return false;
        }
        // END 2016/07/05 J.Kim [QC#10325,ADD]

        if (EDIT.equals(cMsg.xxModeCd_N.getValue())) {
            if (!isSameManualJournalEntryHeaderInfo(cMsg)) {
                cMsg.manJrnlNm.setErrorInfo(1, NFAM0157E);
                return false;
            }

            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                NFAL0220_ASMsg asMsg = sMsg.A.no(index);
                if (!ZYPCommonFunc.hasValue(asMsg.manJrnlEntryDtlPk_A) || isSameRecord(cMsg.glblCmpyCd.getValue(), asMsg)) {
                    continue;
                }
                if (!isSameManualJournalEntryDetail(cMsg, asMsg)) {
                    asMsg.xxChkBox_A.setErrorInfo(1, NFAM0157E);
                    return false;
                }
            }
        }

        // total Debit,Credit
        calculationTotal(cMsg, sMsg);

        // Category
        if (!ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd)) {
            cMsg.jrnlCatgCd.setErrorInfo(1, ZZZM9025E, new String[] {"Category" });
            return false;
        }

        // Reversal Date
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.autoRvslFlg.getValue()) && !ZYPCommonFunc.hasValue(cMsg.rsvdRvslDt)) {
            cMsg.rsvdRvslDt.setErrorInfo(1, NFAM0167E);
            return false;
        }
        // START 2016/10/20 J.Kim [QC#13460,DEL]
        //// START 2016/07/05 J.Kim [QC#10325,ADD]
        // Auto Reversal
        //if (ZYPCommonFunc.hasValue(cMsg.rsvdRvslDt) && !ZYPConstant.FLG_ON_Y.equals(cMsg.autoRvslFlg.getValue())) {
        //    cMsg.autoRvslFlg.setErrorInfo(1, NFAM0178E);
        //    return false;
        //}
        //// END 2016/07/05 J.Kim [QC#10325,ADD]
        // END 2016/10/20 J.Kim [QC#13460,DEL]

        // Check Index Record
        String accountingDt = cMsg.glDt.getValue().substring(0, 6);
        String slsDt = cMsg.slsDt.getValue().substring(0, 6);
        if (!slsDt.equals(accountingDt)) {
            // START 2016/08/22 J.Kim [QC#13537,MOD]
            BigDecimal businessDate = ZYPCodeDataUtil.getNumConstValue("BUSINESS_DATE", cMsg.glblCmpyCd.getValue());
            if (!accountingDt.equals(editAccountingDt()) || !ZYPCommonFunc.hasValue(businessDate)) {
                cMsg.glDt.setErrorInfo(1, NFAM0168E, new String[] {"Accounting Date", "Sales Date" });
                return false;
            }

            String[] businessDt = ZYPDateUtil.getBusinessDays(cMsg.glblCmpyCd.getValue(), slsDt.substring(0, 4), slsDt.substring(4, 6));
            if (businessDt == null || businessDt.length == 0) {
                cMsg.glDt.setErrorInfo(1, NFAM0168E, new String[] {"Accounting Date", "Sales Date" });
                return false;
            }

            businessDate = businessDate.subtract(BigDecimal.ONE);
            String businessDay = ZYPDateUtil.addBusinessDay(cMsg.glblCmpyCd.getValue(), businessDt[0], businessDate.intValue());
            if (ZYPDateUtil.compare(cMsg.slsDt.getValue(), businessDay) > 0) {
                cMsg.glDt.setErrorInfo(1, NFAM0168E, new String[] {"Accounting Date", "Sales Date" });
                return false;
            }
            // END 2016/08/22 J.Kim [QC#13537,MOD]
        }

        // START 2016/08/22 J.Kim [QC#13537,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.glPerNm, convertGlPeriod(cMsg));
        // END 2016/08/22 J.Kim [QC#13537,ADD]
        String glDt = getYYYYMM(cMsg.glPerNm.getValue());
        if (glDt == null || glDt.length() != 6) {
            cMsg.glPerNm.setErrorInfo(1, NFAM0043E, new String[] {"GL Period" });
            return false;
        }

        if (glDt == null || !accountingDt.equals(glDt)) {
            cMsg.glPerNm.setErrorInfo(1, NFAM0043E, new String[] {"GL Period" });
            return false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.rsvdRvslDt)) {
            if (ZYPDateUtil.compare(cMsg.rsvdRvslDt.getValue(), cMsg.slsDt.getValue()) < 0) {
                cMsg.rsvdRvslDt.setErrorInfo(1, NFAM0165E, new String[] {"GL Period" });
                return false;
            }
        }

        int index = 0;
        ZYPTableUtil.clear(sMsg.E);
        List<String> dsAcctNumList = new ArrayList<String>();
        List<String> dsAcctNumErrorList = new ArrayList<String>();
        List<String> coaCombinationList = new ArrayList<String>();
        List<String> coaCombinationErrorList = new ArrayList<String>();
        Map<String, Object> coaCombinationMap = new HashMap<String, Object>();
        boolean errorFlg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NFAL0220_ASMsg asMsg = sMsg.A.no(i);
            if (!checkDefaultRecord(asMsg)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(asMsg.xxScrItem61Txt_A)) {
                asMsg.xxScrItem61Txt_A.setErrorInfo(1, ZZZM9025E, new String[] {"Accounting String" });
                setErrorInfo(sMsg, index++, i);
                continue;
            }

            if (!ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1) && !ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2)) {
                if (!ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1)) {
                    asMsg.jrnlDealAmt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Debit" });
                }
                if (!ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2)) {
                    asMsg.jrnlDealAmt_A2.setErrorInfo(1, ZZZM9025E, new String[] {"Credit" });
                }
                setErrorInfo(sMsg, index++, i);
                continue;
            }

            if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1) && ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2)) {
                // START 2016/07/05 J.Kim [QC#9989,MOD]
                // asMsg.jrnlDealAmt_A1.setErrorInfo(1, NFAM0158E, new
                // String[] {"Debit" });
                asMsg.jrnlDealAmt_A1.setErrorInfo(1, NFAM0166E, new String[] {"Debit" });
                asMsg.jrnlDealAmt_A2.setErrorInfo(1, NFAM0166E, new String[] {"Credit" });
                // END 2016/07/05 J.Kim [QC#9989,MOD]
                setErrorInfo(sMsg, index++, i);
                continue;
            }

            // START 2016/07/13 J.Kim [QC#11481,ADD]
            if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1) && BigDecimal.ZERO.compareTo(asMsg.jrnlDealAmt_A1.getValue()) == 0) {
                asMsg.jrnlDealAmt_A1.setErrorInfo(1, NFAM0179E, new String[] {"Debit" });
                setErrorInfo(sMsg, index++, i);
                continue;
            }
            if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2) && BigDecimal.ZERO.compareTo(asMsg.jrnlDealAmt_A2.getValue()) == 0) {
                asMsg.jrnlDealAmt_A2.setErrorInfo(1, NFAM0179E, new String[] {"Credit" });
                setErrorInfo(sMsg, index++, i);
                continue;
            }
            // END 2016/07/13 J.Kim [QC#11481,ADD]

            // Accounting String
            if (ZYPCommonFunc.hasValue(asMsg.xxScrItem61Txt_A)) {
                String accoutingString = asMsg.xxScrItem61Txt_A.getValue();
                String[] asStr = accoutingString.split(STR_DOT);
                if (asStr.length != 9) {
                    asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0159E);
                    setErrorInfo(sMsg, index++, i);
                    continue;
                }

                if (!checkLength(asStr, asMsg)) {
                    // START 2016/08/10 J.Kim [QC#12851,DEL]
                    // asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Accounting String" });
                    // END 2016/08/10 J.Kim [QC#12851,DEL]
                    setErrorInfo(sMsg, index++, i);
                    continue;
                }

                // START 2016/08/29 [QC#12851,MOD]
                if (!coaCombinationList.contains(accoutingString)) {
                    coaCombinationList.add(accoutingString);
                    // START 2016/09/23 K.Kojima [QC#14463,MOD]
                    // String msgId = callCOACombinationCheckAPI(cMsg,
                    // asMsg);
                    // if (msgId != null) {
                    String[] msgData = callCOACombinationCheckAPI(cMsg, asMsg);
                    if (msgData != null) {
                        // END 2016/09/23 K.Kojima [QC#14463,MOD]
                        // START 2016/08/09 J.Kim [QC#12851,MOD]
                        // asMsg.xxScrItem61Txt_A.setErrorInfo(1, msgId, new String[] {"COA Combination Check API" });
                        // START 2016/09/23 K.Kojima [QC#14463,MOD]
                        // asMsg.xxScrItem61Txt_A.setErrorInfo(1,
                        // msgId, new String[] {"This COA Combination"
                        // });
                        asMsg.xxScrItem61Txt_A.setErrorInfo(1, msgData[0], new String[] {msgData[1] });
                        // END 2016/09/23 K.Kojima [QC#14463,MOD]
                        // END 2016/08/09 J.Kim [QC#12851,MOD]
                        setErrorInfo(sMsg, index++, i);
                        coaCombinationErrorList.add(accoutingString);
                        // START 2016/09/23 K.Kojima [QC#14463,MOD]
                        // coaCombinationMap.put(accoutingString,
                        // msgId);
                        coaCombinationMap.put(accoutingString, msgData);
                        // END 2016/09/23 K.Kojima [QC#14463,MOD]
                        continue;
                    }
                } else {
                    if (coaCombinationErrorList.contains(accoutingString)) {
                        // START 2016/09/23 K.Kojima [QC#14463,MOD]
                        // asMsg.xxScrItem61Txt_A.setErrorInfo(1,
                        // (String)
                        // coaCombinationMap.get(accoutingString), new
                        // String[] {"This COA Combination" });
                        String[] msgData = (String[]) coaCombinationMap.get(accoutingString);
                        asMsg.xxScrItem61Txt_A.setErrorInfo(1, msgData[0], new String[] {msgData[1] });
                        // END 2016/09/23 K.Kojima [QC#14463,MOD]
                        setErrorInfo(sMsg, index++, i);
                        continue;
                    }
                }
                // END 2016/08/29 [QC#12851,MOD]
            }

            // Customer
            if (ZYPCommonFunc.hasValue(asMsg.dsAcctNum_A)) {
                String dsAcctNum = asMsg.dsAcctNum_A.getValue();
                // START 2016/08/29 [QC#12851,MOD]
                if (!dsAcctNumList.contains(dsAcctNum)) {
                    dsAcctNumList.add(dsAcctNum);
                    SELL_TO_CUSTTMsgArray sellToCustInfo = NFAL0220Query.getInstance().getSellToCustFindByCondition(cMsg, dsAcctNum);
                    if (sellToCustInfo == null || sellToCustInfo.getValidCount() == 0) {
                        asMsg.dsAcctNum_A.setErrorInfo(1, NFAM0043E, new String[] {"Customer#" });
                        dsAcctNumErrorList.add(dsAcctNum);
                        setErrorInfo(sMsg, index++, i);
                        continue;
                    }
                } else {
                    if(dsAcctNumErrorList.contains(dsAcctNum)) {
                        asMsg.dsAcctNum_A.setErrorInfo(1, NFAM0043E, new String[] {"Customer#" });
                        setErrorInfo(sMsg, index++, i);
                        continue;
                    }
                }
                // END 2016/08/29 [QC#12851,MOD]
            }

            // Source Value
            if (ZYPCommonFunc.hasValue(asMsg.jrnlEntrySrcValTxt_A) && !ZYPCommonFunc.hasValue(asMsg.jrnlEntrySrcTpCd_LS)) {
                asMsg.jrnlEntrySrcTpCd_LS.setErrorInfo(1, NFAM0052E, new String[] {"Source Type" });
                setErrorInfo(sMsg, index++, i);
            }
        }

        if (sMsg.E.getValidCount() > 0) {
            return false;
        }

        // Debit & Credit Amount
        if (cMsg.jrnlDealAmt_T1.getValue().compareTo(cMsg.jrnlDealAmt_T2.getValue()) != 0) {
            cMsg.jrnlDealAmt_T1.setErrorInfo(1, NFAM0160E);
            // START 2016/07/05 J.Kim [QC#10325,ADD]
            cMsg.jrnlDealAmt_T2.setErrorInfo(1, NFAM0160E);
            // END 2016/07/05 J.Kim [QC#10325,ADD]
            return false;
        }

        return errorFlg;
    }

    private static void setErrorInfo(NFAL0220SMsg sMsg, int count, int rowNum) {
        sMsg.E.no(count).xxErrNum.setValue(rowNum);
        sMsg.E.setValidCount(count + 1);
    }

    private static boolean checkLength(String[] asStr, NFAL0220_ASMsg asMsg) {

        // START 2016/08/10 J.Kim [QC#12851,MOD]
        // int coaCmpyCdLen = asMsg.getAttr("coaCmpyCd_A").getDigit();
        // int coaBrCdLen = asMsg.getAttr("coaBrCd_A").getDigit();
        // int coaCcCdLen = asMsg.getAttr("coaCcCd_A").getDigit();
        // int coaAcctCdLen = asMsg.getAttr("coaAcctCd_A").getDigit();
        // int coaProdCdLen = asMsg.getAttr("coaProdCd_A").getDigit();
        // int coaChCdLen = asMsg.getAttr("coaChCd_A").getDigit();
        // int coaAfflCdLen = asMsg.getAttr("coaAfflCd_A").getDigit();
        // int coaProjCdLen = asMsg.getAttr("coaProjCd_A").getDigit();
        // int coaExtnCdLen = asMsg.getAttr("coaExtnCd_A").getDigit();
        // // START 2016/08/02 K.Kojima [QC#12766,ADD]
        // // if (asStr[0].length() <= coaCmpyCdLen && asStr[1].length()
        // // <= coaAfflCdLen && asStr[2].length() <= coaBrCdLen &&
        // // asStr[3].length() <= coaCcCdLen && asStr[4].length() <=
        // // coaAcctCdLen && asStr[5].length() <= coaProdCdLen
        // // && asStr[6].length() <= coaChCdLen && asStr[7].length() <=
        // // coaProjCdLen && asStr[8].length() <= coaExtnCdLen) {
        // // return true;
        // // }
        // if (asStr[ARRAY_NUM_CMPY_CD].length() <= coaCmpyCdLen && asStr[ARRAY_NUM_BR_CD].length() <= coaBrCdLen && asStr[ARRAY_NUM_CC_CD].length() <= coaCcCdLen && asStr[ARRAY_NUM_ACCT_CD].length() <= coaAcctCdLen
        //        && asStr[ARRAY_NUM_PROD_CD].length() <= coaProdCdLen && asStr[ARRAY_NUM_CH_CD].length() <= coaChCdLen && asStr[ARRAY_NUM_AFFL_CD].length() <= coaAfflCdLen && asStr[ARRAY_NUM_PROJ_CD].length() <= coaProjCdLen
        //        && asStr[ARRAY_NUM_EXTN_CD].length() <= coaExtnCdLen) {
        //    return true;
        // }
        // // END 2016/08/02 K.Kojima [QC#12766,MOD]

        if (COA_LENGTH[0] != asStr[ARRAY_NUM_CMPY_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Company format." });
            return false;
        }
        if (COA_LENGTH[1] != asStr[ARRAY_NUM_BR_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Branch format." });
            return false;
        }
        if (COA_LENGTH[2] != asStr[ARRAY_NUM_CC_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Cost Center format." });
            return false;
        }
        if (COA_LENGTH[3] != asStr[ARRAY_NUM_ACCT_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Account format." });
            return false;
        }
        if (COA_LENGTH[4] != asStr[ARRAY_NUM_PROD_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Product format." });
            return false;
        }
        if (COA_LENGTH[5] != asStr[ARRAY_NUM_CH_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Channel format." });
            return false;
        }
        if (COA_LENGTH[6] != asStr[ARRAY_NUM_AFFL_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Intercompany format." });
            return false;
        }
        if (COA_LENGTH[7] != asStr[ARRAY_NUM_PROJ_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Merchandise format." });
            return false;
        }
        if (COA_LENGTH[8] != asStr[ARRAY_NUM_EXTN_CD].length()) {
            asMsg.xxScrItem61Txt_A.setErrorInfo(1, NFAM0043E, new String[] {"Business Unit format." });
            return false;
        }
        return true;
        // END 2016/08/10 J.Kim [QC#12851,MOD]
    }

    // START 2016/07/12 J.Kim [QC#10324,DEL]
    // private static boolean checkGLPeriod(NFAL0220CMsg cMsg) {
    //
    // String[] period = new String[] {"JAN", "FEB", "MAR", "APR",
    // "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
    // for (String dt : period) {
    // if (cMsg.glPerNm.getValue().startsWith(dt)) {
    // return true;
    // }
    // }
    // return false;
    // }
    // END 2016/07/12 J.Kim [QC#10324,DEL]

    /**
     * getYYYYMM
     * @param perNm String
     * @return String
     */
    public static String getYYYYMM(String perNm) {

        if (!ZYPCommonFunc.hasValue(perNm)) {
            return null;
        }

        if (perNm.length() != 6) {
            return null;
        }

        String monthNm = perNm.substring(0, 3);
        // When 21XX, it won't work...
        String strYYYY = "20".concat(perNm.substring(4, 6));
        String strMM = "";

        if ("JAN".equals(monthNm)) {
            strMM = "01";
        } else if ("FEB".equals(monthNm)) {
            strMM = "02";
        } else if ("MAR".equals(monthNm)) {
            strMM = "03";
        } else if ("APR".equals(monthNm)) {
            strMM = "04";
        } else if ("MAY".equals(monthNm)) {
            strMM = "05";
        } else if ("JUN".equals(monthNm)) {
            strMM = "06";
        } else if ("JUL".equals(monthNm)) {
            strMM = "07";
        } else if ("AUG".equals(monthNm)) {
            strMM = "08";
        } else if ("SEP".equals(monthNm)) {
            strMM = "09";
        } else if ("OCT".equals(monthNm)) {
            strMM = "10";
        } else if ("NOV".equals(monthNm)) {
            strMM = "11";
        } else if ("DEC".equals(monthNm)) {
            strMM = "12";
        // START 2016/07/21 J.Kim [QC#10324,MOD]
        } else {
            return null;
        }
        // END 2016/07/21 J.Kim [QC#10324,MOD]

        return strYYYY.concat(strMM);
    }

    // START 2016/09/23 K.Kojima [QC#14463,MOD]
    // private static String callCOACombinationCheckAPI(NFAL0220CMsg
    // cMsg, NFAL0220_ASMsg asMsg) {
    private static String[] callCOACombinationCheckAPI(NFAL0220CMsg cMsg, NFAL0220_ASMsg asMsg) {
        // END 2016/09/23 K.Kojima [QC#14463,MOD]

        // Accounting String
        editAccountingString(asMsg);

        // START 2016/09/23 K.Kojima [QC#14463,DEL]
        // String msgId = null;
        // END 2016/09/23 K.Kojima [QC#14463,DEL]
        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_V.getValue())) {
            ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, asMsg.coaCmpyCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, asMsg.coaBrCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, asMsg.coaCcCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, asMsg.coaAcctCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, asMsg.coaProdCd_A);
        // START 2016/09/14 S.Yoshida [QC#14463,ADD] 
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, asMsg.coaChCd_A);
        // END   2016/09/14 S.Yoshida [QC#14463,ADD] 
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, asMsg.coaAfflCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, asMsg.coaProjCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, asMsg.coaExtnCd_A);
        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, BIZ_ID);

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (!S21ApiUtil.getXxMsgIdList(apiMsg).isEmpty()) {
            for (int j = 0; j < apiMsg.xxMsgIdList.getValidCount(); j++) {
                // START 2016/09/23 K.Kojima [QC#14463,MOD]
                // msgId =
                // apiMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                String msgId = apiMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                String msgTxt = apiMsg.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue();
                // END 2016/09/23 K.Kojima [QC#14463,MOD]
                if (ZYPCommonFunc.hasValue(msgId)) {
                    // START 2016/09/23 K.Kojima [QC#14463,MOD]
                    // return msgId;
                    String[] msgData = new String[] {msgId, msgTxt };
                    return msgData;
                    // END 2016/09/23 K.Kojima [QC#14463,MOD]
                }
            }
        }
        // START 2016/09/23 K.Kojima [QC#14463,MOD]
        // return msgId;
        return null;
        // END 2016/09/23 K.Kojima [QC#14463,MOD]
    }

    private static boolean callJournalEntryCreationAPI(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        NFZC104001 api = new NFZC104001();
        NFZC104001PMsg apiMsg = new NFZC104001PMsg();
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        ZYPEZDItemValueSetter.setValue(apiMsg.procDt, cMsg.slsDt);
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(apiMsg).isEmpty()) {
            List<S21ApiMessage> xxMsgIdList = S21ApiUtil.getXxMsgList(apiMsg);
            S21ApiMessage msg = xxMsgIdList.get(0);
            cMsg.manJrnlCpltFlg.setErrorInfo(1, msg.getXxMsgid(), new String[] {getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()) });
            return false;
        }
        return true;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private static void calculationTotal(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        BigDecimal jrnlDealAmtTatal = BigDecimal.ZERO;
        BigDecimal jrnlFuncAmtTatal = BigDecimal.ZERO;
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            BigDecimal jrnlDealAmt = setDefault(sMsg.A.no(index).jrnlDealAmt_A1.getValue());
            BigDecimal jrnlFuncAmt = setDefault(sMsg.A.no(index).jrnlDealAmt_A2.getValue());
            jrnlDealAmtTatal = jrnlDealAmtTatal.add(jrnlDealAmt);
            jrnlFuncAmtTatal = jrnlFuncAmtTatal.add(jrnlFuncAmt);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.jrnlDealAmt_T1, jrnlDealAmtTatal);
        ZYPEZDItemValueSetter.setValue(cMsg.jrnlDealAmt_T2, jrnlFuncAmtTatal);
    }

    private static BigDecimal setDefault(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    private static boolean checkDefaultRecord(NFAL0220_ASMsg asMsg) {

        if (ZYPCommonFunc.hasValue(asMsg.xxScrItem61Txt_A) || ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1) || ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2) || ZYPCommonFunc.hasValue(asMsg.manJrnlLineTxt_A)
                || ZYPCommonFunc.hasValue(asMsg.dsAcctNum_A) || ZYPCommonFunc.hasValue(asMsg.serNum_A) || ZYPCommonFunc.hasValue(asMsg.jrnlEntrySrcTpCd_LS) || ZYPCommonFunc.hasValue(asMsg.jrnlEntrySrcValTxt_A)) {
            return true;
        }

        return false;
    }

    private static boolean isSameRecord(String glblCmpyCd, NFAL0220_ASMsg asMsg) {

        List<String> asMsgList = new ArrayList<String>();
        asMsgList.add(0, asMsg.coaCmpyCd_A.getValue());
        asMsgList.add(1, asMsg.coaBrCd_A.getValue());
        asMsgList.add(2, asMsg.coaCcCd_A.getValue());
        asMsgList.add(3, asMsg.coaAcctCd_A.getValue());
        asMsgList.add(4, asMsg.coaProdCd_A.getValue());
        asMsgList.add(5, asMsg.coaChCd_A.getValue());
        asMsgList.add(6, asMsg.coaAfflCd_A.getValue());
        asMsgList.add(7, asMsg.coaProjCd_A.getValue());
        asMsgList.add(8, asMsg.coaExtnCd_A.getValue());
        asMsgList.add(9, asMsg.manJrnlLineTxt_A.getValue());
        asMsgList.add(10, asMsg.dsAcctNum_A.getValue());
        asMsgList.add(11, asMsg.serNum_A.getValue());
        asMsgList.add(12, asMsg.jrnlEntrySrcTpCd_LS.getValue());
        asMsgList.add(13, asMsg.jrnlEntrySrcValTxt_A.getValue());
        BigDecimal jrnlDealAmt = setDefault(asMsg.jrnlDealAmt_A1.getValue());
        BigDecimal jrnlFuncAmt = setDefault(asMsg.jrnlDealAmt_A2.getValue());

        BigDecimal manJrnlEntryDtlPk = asMsg.manJrnlEntryDtlPk_A.getValue();
        MAN_JRNL_ENTRY_DTLTMsg manJrnlEntryDtlTMsg = NFAL0220Query.getInstance().manJrnlEntryDtlFindByKey(glblCmpyCd, manJrnlEntryDtlPk);
        if (manJrnlEntryDtlTMsg == null) {
            return true;
        }

        List<String> tMsgList = new ArrayList<String>();
        tMsgList.add(0, manJrnlEntryDtlTMsg.coaCmpyCd.getValue());
        tMsgList.add(1, manJrnlEntryDtlTMsg.coaBrCd.getValue());
        tMsgList.add(2, manJrnlEntryDtlTMsg.coaCcCd.getValue());
        tMsgList.add(3, manJrnlEntryDtlTMsg.coaAcctCd.getValue());
        tMsgList.add(4, manJrnlEntryDtlTMsg.coaProdCd.getValue());
        tMsgList.add(5, manJrnlEntryDtlTMsg.coaChCd.getValue());
        tMsgList.add(6, manJrnlEntryDtlTMsg.coaAfflCd.getValue());
        tMsgList.add(7, manJrnlEntryDtlTMsg.coaProjCd.getValue());
        tMsgList.add(8, manJrnlEntryDtlTMsg.coaExtnCd.getValue());
        tMsgList.add(9, manJrnlEntryDtlTMsg.manJrnlLineTxt.getValue());
        tMsgList.add(10, manJrnlEntryDtlTMsg.dsAcctNum.getValue());
        tMsgList.add(11, manJrnlEntryDtlTMsg.serNum.getValue());
        tMsgList.add(12, manJrnlEntryDtlTMsg.jrnlEntrySrcTpCd.getValue());
        tMsgList.add(13, manJrnlEntryDtlTMsg.jrnlEntrySrcValTxt.getValue());
        BigDecimal jrnlDealAmtTMsg = setDefault(manJrnlEntryDtlTMsg.jrnlDealAmt.getValue());
        BigDecimal jrnlFuncAmtTMsg = setDefault(manJrnlEntryDtlTMsg.jrnlFuncAmt.getValue());

        for (int i = 0; i < asMsgList.size(); i++) {
            if (!asMsgList.get(i).equals(tMsgList.get(i))) {
                return false;
            }
        }

        if (DEBIT.equals(asMsg.drCrTpCd_A.getValue()) && jrnlDealAmt.compareTo(jrnlDealAmtTMsg) != 0) {
            return false;
        }
        if (CREDIT.equals(asMsg.drCrTpCd_A.getValue()) && jrnlFuncAmt.compareTo(jrnlFuncAmtTMsg) != 0) {
            return false;
        }

        return true;
    }

    /**
     * copyJournal
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     */
    public static boolean copyJournal(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // START 2016/07/05 J.Kim [QC#10325,ADD]
        if (sMsg.A.getValidCount() == 0 || !isCheckManJrnlEntryDtlPk(sMsg)) {
            cMsg.setMessageInfo(NFAM0176E);
            return false;
        }
        // END 2016/07/05 J.Kim [QC#10325,ADD]

        cMsg.rsvdRvslDt.clear();
        int manJrnlNmLen = cMsg.getAttr("manJrnlNm").getDigit();
        String manJrnlNm = "Copy Of " + cMsg.manJrnlNm.getValue();
        if (manJrnlNm.length() > manJrnlNmLen) {
            manJrnlNm = manJrnlNm.substring(0, manJrnlNmLen);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlEntryHdrPk, getZYPOracleSeqManJrnlEntryHdrSq());
        ZYPEZDItemValueSetter.setValue(cMsg.glDt, cMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(cMsg.glPerNm, cMsg.glPerNm);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlNm, manJrnlNm);
        ZYPEZDItemValueSetter.setValue(cMsg.rvslCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.autoRvslFlg, ZYPConstant.FLG_OFF_N);

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            NFAL0220_ASMsg asMsg = sMsg.A.no(index);
            ZYPEZDItemValueSetter.setValue(asMsg.xxNum_A, BigDecimal.valueOf(index).add(BigDecimal.ONE));
            ZYPEZDItemValueSetter.setValue(asMsg.manJrnlEntryDtlPk_A, getZYPOracleSeqManJrnlEntryDtlSq());
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_R, NEW);
        ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_N, NEW);
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Copy Journal" });

        return true;
    }

    /**
     * reverse
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     * @return boolean
     */
    public static boolean reverse(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // START 2016/07/05 J.Kim [QC#10325,ADD]
        if (sMsg.A.getValidCount() == 0 || !isCheckManJrnlEntryDtlPk(sMsg)) {
            cMsg.setMessageInfo(NFAM0176E);
            return false;
        }
        // END 2016/07/05 J.Kim [QC#10325,ADD]

        if (!isSameManualJournalEntryHeaderInfo(cMsg)) {
            cMsg.manJrnlNm.setErrorInfo(1, NFAM0004E);
            return false;
        }

        // Save Old Primary Key
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlEntryHdrPk_B, cMsg.manJrnlEntryHdrPk);

        cMsg.rsvdRvslDt.clear();
        int manJrnlNmLen = cMsg.getAttr("manJrnlNm").getDigit();
        String manJrnlNm = "Reversal Of " + cMsg.manJrnlNm.getValue();
        if (manJrnlNm.length() > manJrnlNmLen) {
            manJrnlNm = manJrnlNm.substring(0, manJrnlNmLen);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlEntryHdrPk, getZYPOracleSeqManJrnlEntryHdrSq());
        ZYPEZDItemValueSetter.setValue(cMsg.glDt, cMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(cMsg.glPerNm, cMsg.glPerNm);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlNm, manJrnlNm);
        ZYPEZDItemValueSetter.setValue(cMsg.rvslCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.autoRvslFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlDescTxt, cMsg.manJrnlDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.jrnlDealAmt_T1, calJrnDelAmt(cMsg.jrnlDealAmt_T1));
        ZYPEZDItemValueSetter.setValue(cMsg.jrnlDealAmt_T2, calJrnDelAmt(cMsg.jrnlDealAmt_T2));

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            NFAL0220_ASMsg asMsg = sMsg.A.no(index);
            ZYPEZDItemValueSetter.setValue(asMsg.xxNum_A, BigDecimal.valueOf(index).add(BigDecimal.ONE));
            ZYPEZDItemValueSetter.setValue(asMsg.jrnlDealAmt_A1, calJrnDelAmt(asMsg.jrnlDealAmt_A1));
            ZYPEZDItemValueSetter.setValue(asMsg.jrnlDealAmt_A2, calJrnDelAmt(asMsg.jrnlDealAmt_A2));
            ZYPEZDItemValueSetter.setValue(asMsg.manJrnlEntryDtlPk_A, getZYPOracleSeqManJrnlEntryDtlSq());
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_R, NEW);
        ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_N, NEW);
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Reverse" });
        return true;
    }

    private static BigDecimal getZYPOracleSeqManJrnlEntryHdrSq() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MAN_JRNL_ENTRY_HDR_SQ);
    }

    private static BigDecimal getZYPOracleSeqManJrnlEntryDtlSq() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MAN_JRNL_ENTRY_DTL_SQ);
    }

    private static BigDecimal calJrnDelAmt(EZDCBigDecimalItem item) {
        return item.getValue().negate();
    }

    private static BigDecimal calJrnDelAmt(EZDSBigDecimalItem item) {
        if (ZYPCommonFunc.hasValue(item)) {
            return item.getValue().negate();
        }
        return null;
    }

    private static boolean updateReverse(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg mjehTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(mjehTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk_B);
        MAN_JRNL_ENTRY_HDRTMsg outMjehTMsg = (MAN_JRNL_ENTRY_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mjehTMsg);

        if (outMjehTMsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(outMjehTMsg.actlRvslDt, cMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.rvslCpltFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(outMjehTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMjehTMsg.getReturnCode())) {
            return false;
        }
        cMsg.manJrnlEntryHdrPk_B.clear();
        return true;
    }

    /**
     * Journal Category Validation
     * @param cMsg NFAL0220CMsg
     * @return boolean
     */
    public static boolean jrnlCatgSearch(NFAL0220CMsg cMsg) {

        JRNL_CATGTMsg tMsg = new JRNL_CATGTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCatgCd, cMsg.jrnlCatgCd);

        JRNL_CATGTMsg rslt = (JRNL_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (rslt == null) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.jrnlCatgDescTxt, rslt.jrnlCatgDescTxt);
            return true;
        }
    }

    /**
     * Create Pull Down
     * @param cMsg NFAL0220CMsg
     */
    public static void createPullDown(NFAL0220CMsg cMsg) {
        // Source Type
        ZYPCodeDataUtil.createPulldownList(JRNL_ENTRY_SRC_TP.class, cMsg.jrnlEntrySrcTpCd_LC, cMsg.jrnlEntrySrcTpDescTxt_LD);
    }

    /**
     * searchManJrnlEntry
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     */
    public static void searchManJrnlEntry(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        createPullDown(cMsg);

        if (!setManJrnlEntryHdrTMsg(cMsg)) {
            cMsg.setMessageInfo(ZZZM9005W);
            return;
        }

        S21SsmEZDResult ssmResult = NFAL0220Query.getInstance().searchManJrnlEntry(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                calculationTotal(cMsg, sMsg);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9005W);
            cMsg.A.setValidCount(0);
        }
    }

    private static boolean setManJrnlEntryHdrTMsg(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg manJrnlEntryHdrTMsg = NFAL0220Query.getInstance().manJrnlEntryHdrFindByKey(cMsg);
        if (manJrnlEntryHdrTMsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlNm, manJrnlEntryHdrTMsg.manJrnlNm);
        ZYPEZDItemValueSetter.setValue(cMsg.glSendCpltFlg, manJrnlEntryHdrTMsg.glSendCpltFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlDescTxt, manJrnlEntryHdrTMsg.manJrnlDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlCpltFlg, manJrnlEntryHdrTMsg.manJrnlCpltFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlCpltFlg_B, manJrnlEntryHdrTMsg.manJrnlCpltFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.jrnlCatgCd, manJrnlEntryHdrTMsg.jrnlCatgCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rsvdRvslDt, manJrnlEntryHdrTMsg.rsvdRvslDt);
        ZYPEZDItemValueSetter.setValue(cMsg.autoRvslFlg, manJrnlEntryHdrTMsg.autoRvslFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.rvslCpltFlg, manJrnlEntryHdrTMsg.rvslCpltFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.glPerNm, manJrnlEntryHdrTMsg.glPerNm);
        ZYPEZDItemValueSetter.setValue(cMsg.glDt, manJrnlEntryHdrTMsg.glDt);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_H1, manJrnlEntryHdrTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_H1, manJrnlEntryHdrTMsg.ezUpTimeZone);
        jrnlCatgSearch(cMsg);
        return true;
    }

    /**
     * setInitLine
     * @param sMsg NFAL0220SMsg
     */
    public static void setInitLine(NFAL0220SMsg sMsg) {

        sMsg.A.setValidCount(10);
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxNum_A, BigDecimal.valueOf(index).add(BigDecimal.ONE));
        }
    }

    /**
     * createManualJournalEntry
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     */
    public static void createManualJournalEntry(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        List<MAN_JRNL_ENTRY_DTLTMsg> insertDetail = new ArrayList<MAN_JRNL_ENTRY_DTLTMsg>();
        List<MAN_JRNL_ENTRY_DTLTMsg> updateDetail = new ArrayList<MAN_JRNL_ENTRY_DTLTMsg>();
        if (EDIT.equals(cMsg.xxModeCd_N.getValue())) {

            // Delete
            boolean deleteFlg = false;
            if (sMsg.D.getValidCount() > 0) {
                if (sMsg.A.getValidCount() == 0) {
                    if (!deleteManualJournalEntryHeader(cMsg)) {
                        return;
                    }
                    if (!deleteManualJournalEntryDetail(cMsg, sMsg)) {
                        return;
                    }
                    cMsg.setMessageInfo(ZZSM4116I, new String[] {"Journal Entry" });
                    cMsg.manJrnlEntryHdrPk.clear();
                    return;
                }

                if (!deleteManualJournalEntryDetail(cMsg, sMsg)) {
                    cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry Detail", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                    return;
                }
                deleteFlg = true;
                ZYPTableUtil.clear(sMsg.D);
            }

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.manJrnlCpltFlg_B.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.manJrnlCpltFlg.getValue())) {
                if (!deleteJrnlEntry(cMsg, sMsg)) {
                    cMsg.setMessageInfo(NFAM0164E, new String[] {"Journal Entry", "JRNL_ENTRY", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                    return;
                }
            }

            // Update Manual Journal Entry Header
            if (!updateManualJournalEntryHeader(cMsg)) {
                cMsg.setMessageInfo(NFAM0163E, new String[] {"Manual Journal Entry Header", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return;
            }

            // Update Manual Journal Entry Detail
            int lineNum = 0;
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                NFAL0220_ASMsg asMsg = sMsg.A.no(index);

                // START 2016/07/05 J.Kim [QC#10325,ADD]
                if (!checkDefaultRecord(asMsg)) {
                    continue;
                }
                // END 2016/07/05 J.Kim [QC#10325,ADD]

                lineNum++;
                if (ZYPCommonFunc.hasValue(asMsg.manJrnlEntryDtlPk_A)) {

                    if (!deleteFlg && isSameRecord(cMsg.glblCmpyCd.getValue(), asMsg)) {
                        continue;
                    }

                    // START 2016/08/29 [QC#12851,MOD]
                    //if (!updateManualJournalEntryDetail(cMsg, asMsg, lineNum)) {
                    //    asMsg.xxChkBox_A.setErrorInfo(1, NFAM0163E, new String[] {"Manual Journal Entry Detail", "MAN_JRNL_ENTRY_DTL_PK", String.valueOf(asMsg.manJrnlEntryDtlPk_A.getValue()) });
                    //    return;
                    //}
                    MAN_JRNL_ENTRY_DTLTMsg manJrnlEntryDtlTMsg = updateManualJournalEntryDetail(cMsg, asMsg, lineNum);
                    if (manJrnlEntryDtlTMsg == null) {
                        cMsg.setMessageInfo(NFAM0181E, new String[] {"Manual Journal Entry Detail" });
                        return;
                    }
                    updateDetail.add(manJrnlEntryDtlTMsg);
                    if (MAX_UPDATE_COUNT == updateDetail.size()) {
                        if (!update(updateDetail)) {
                            cMsg.setMessageInfo(NFAM0181E, new String[] {"Manual Journal Entry Detail" });
                            return;
                        }
                        updateDetail.clear();
                    }
                    // END 2016/08/29 [QC#12851,MOD]
                } else {
                    // START 2016/08/29 [QC#12851,MOD]
                    //if (!insertManualJournalEntryDetail(cMsg, asMsg, lineNum)) {
                    //    asMsg.xxChkBox_A.setErrorInfo(1, NFAM0162E, new String[] {"Manual Journal Entry Detail", "MAN_JRNL_ENTRY_DTL_PK", String.valueOf(asMsg.manJrnlEntryDtlPk_A.getValue()) });
                    //    return;
                    //}
                    insertDetail.add(insertManualJournalEntryDetail(cMsg, asMsg, lineNum));
                    if (MAX_UPDATE_COUNT == insertDetail.size()) {
                        if (!insert(insertDetail)) {
                            cMsg.setMessageInfo(NFAM0180E, new String[] {"Manual Journal Entry Detail" });
                            return;
                        }
                        insertDetail.clear();
                    }
                    // END 2016/08/29 [QC#12851,MOD]
                }
            }

            // START 2016/08/29 [QC#12851,MOD]
            // update Detail
            if (updateDetail.size() > 0) {
                if (!update(updateDetail)) {
                    cMsg.setMessageInfo(NFAM0181E, new String[] {"Manual Journal Entry Detail" });
                    return;
                }
                updateDetail.clear();
            }

            // Insert Detail
            if (insertDetail.size() > 0) {
                if (!insert(insertDetail)) {
                    cMsg.setMessageInfo(NFAM0180E, new String[] {"Manual Journal Entry Detail" });
                    return;
                }
                insertDetail.clear();
            }
            // END 2016/08/29 [QC#12851,MOD]
        } else {

            // Reverse
            if (ZYPCommonFunc.hasValue(cMsg.manJrnlEntryHdrPk_B)) {
                if (!updateReverse(cMsg)) {
                    cMsg.manJrnlNm.setErrorInfo(1, NFAM0163E, new String[] {"Manual Journal Entry Header", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                    return;
                }
            }

            if (!insertManualJournalEntryHeader(cMsg)) {
                cMsg.setMessageInfo(NFAM0162E, new String[] {"Manual Journal Entry Header", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return;
            }

            int lineNum = 0;
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                NFAL0220_ASMsg asMsg = sMsg.A.no(index);
                if (!checkDefaultRecord(asMsg)) {
                    continue;
                }
                lineNum++;
                // START 2016/08/29 [QC#12851,MOD]
                //if (!insertManualJournalEntryDetail(cMsg, asMsg, lineNum)) {
                //    asMsg.xxChkBox_A.setErrorInfo(1, NFAM0162E, new String[] {"Manual Journal Entry Detail", "MAN_JRNL_ENTRY_DTL_PK", String.valueOf(asMsg.manJrnlEntryDtlPk_A.getValue()) });
                //    return;
                //}
                insertDetail.add(insertManualJournalEntryDetail(cMsg, asMsg, lineNum));
                if (MAX_UPDATE_COUNT == insertDetail.size()) {
                    if (!insert(insertDetail)) {
                        cMsg.setMessageInfo(NFAM0180E, new String[] {"Manual Journal Entry Detail" });
                        return;
                    }
                    insertDetail.clear();
                }
                // END 2016/08/29 [QC#12851,MOD]
            }

            // START 2016/08/29 [QC#12851,MOD]
            if (insertDetail.size() > 0) {
                if (!insert(insertDetail)) {
                    cMsg.setMessageInfo(NFAM0180E, new String[] {"Manual Journal Entry Detail" });
                    return;
                }
                insertDetail.clear();
            }
            // END 2016/08/29 [QC#12851,MOD]
        }

        // Complete
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.manJrnlCpltFlg.getValue())) {
            // NFZC1040 (Journal Entry Creation API)
            if (!callJournalEntryCreationAPI(cMsg, sMsg)) {
                return;
            }
        }

        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Creating Journal Entry" });
    }

    private static boolean deleteJrnlEntry(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        BigDecimal manJrnlEntryHdrPk = cMsg.manJrnlEntryHdrPk.getValue();
        JRNL_ENTRYTMsgArray jrnlEntryTMsgArray = NFAL0220Query.getInstance().getJrnlEntryFindByCondition(cMsg, manJrnlEntryHdrPk);

        if (jrnlEntryTMsgArray == null) {
            return false;
        }

        List<JRNL_ENTRYTMsg> jrnlEntryList = new ArrayList<JRNL_ENTRYTMsg>();
        for (int index = 0; index < jrnlEntryTMsgArray.getValidCount(); index++) {
            JRNL_ENTRYTMsg jrnlEntryTMsg = new JRNL_ENTRYTMsg();
            ZYPEZDItemValueSetter.setValue(jrnlEntryTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(jrnlEntryTMsg.jrnlEntryPk, jrnlEntryTMsgArray.no(index).jrnlEntryPk);
            JRNL_ENTRYTMsg outTMsg = (JRNL_ENTRYTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(jrnlEntryTMsg);
            if (outTMsg == null) {
                return false;
            }
            jrnlEntryList.add(outTMsg);
        }

        JRNL_ENTRYTMsg[] inTMsgArray = new JRNL_ENTRYTMsg[jrnlEntryList.size()];
        int deleteCount = S21FastTBLAccessor.removePhysical(jrnlEntryList.toArray(inTMsgArray));
        if (deleteCount != inTMsgArray.length) {
            return false;
        }
        return true;
    }

    private static boolean deleteManualJournalEntryDetail(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        List<MAN_JRNL_ENTRY_DTLTMsg> mjehTMsgList = new ArrayList<MAN_JRNL_ENTRY_DTLTMsg>();
        for (int index = 0; index < sMsg.D.getValidCount(); index++) {
            // START 2016/08/05 J.Kim [QC#12851,ADD]
            if (!ZYPCommonFunc.hasValue(sMsg.D.no(index).manJrnlEntryDtlPk)) {
                continue;
            }
            // END 2016/08/05 J.Kim [QC#12851,ADD]
            MAN_JRNL_ENTRY_DTLTMsg inTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.manJrnlEntryDtlPk, sMsg.D.no(index).manJrnlEntryDtlPk);
            MAN_JRNL_ENTRY_DTLTMsg outMjehTMsg = (MAN_JRNL_ENTRY_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
            if (outMjehTMsg == null) {
                cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return false;
            }
            mjehTMsgList.add(outMjehTMsg);
        }

        if (mjehTMsgList.size() > 0) {
            MAN_JRNL_ENTRY_DTLTMsg[] inTMsgArray = new MAN_JRNL_ENTRY_DTLTMsg[mjehTMsgList.size()];
            int deleteCount = S21FastTBLAccessor.removePhysical(mjehTMsgList.toArray(inTMsgArray));
            if (deleteCount != inTMsgArray.length) {
                cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return false;
            }
        }
        return true;
    }

    /**
     * deleteManualJournalEntryHeader
     * @param cMsg NFAL0220CMsg
     * @return boolean
     */
    private static boolean deleteManualJournalEntryHeader(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg inHdrTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inHdrTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        MAN_JRNL_ENTRY_HDRTMsg outMjehTMsg = (MAN_JRNL_ENTRY_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inHdrTMsg);

        if (outMjehTMsg == null) {
            cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
            return false;
        }

        EZDTBLAccessor.remove(outMjehTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMjehTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
            return false;
        }
        return true;
    }

    // START 2016/08/05 J.Kim [QC#12851,ADD]
    private static boolean logicalRemoveManualJournalEntryHeader(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg inHdrTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inHdrTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        MAN_JRNL_ENTRY_HDRTMsg outMjehTMsg = (MAN_JRNL_ENTRY_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inHdrTMsg);

        if (outMjehTMsg == null) {
            cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
            return false;
        }

        EZDTBLAccessor.logicalRemove(outMjehTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMjehTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
            return false;
        }
        return true;
    }
    // END 2016/08/05 J.Kim [QC#12851,ADD]

    /**
     * deleteManualJournalEntry
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     * @return boolean
     */
    public static boolean deleteManualJournalEntry(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // START 2016/07/05 J.Kim [QC#10325,ADD]
        if (NEW.equals(cMsg.xxModeCd_N.getValue())) {
            cMsg.setMessageInfo(NFAM0177E);
            return false;
        }
        // END 2016/07/05 J.Kim [QC#10325,ADD]

        // START 2016/07/13 J.Kim [QC#11481,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.manJrnlCpltFlg.getValue())) {
            if (!deleteJrnlEntry(cMsg, sMsg)) {
                cMsg.setMessageInfo(NFAM0164E, new String[] {"Journal Entry", "JRNL_ENTRY", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return false;
            }
        }
        // END 2016/07/13 J.Kim [QC#11481,ADD]

        // START 2016/08/05 J.Kim [QC#12851,MOD]
        // if (!deleteManualJournalEntryHeader(cMsg)) {
        if (!logicalRemoveManualJournalEntryHeader(cMsg)) {
            // END 2016/08/05 J.Kim [QC#12851,MOD]
            return false;
        }

        // START 2016/08/05 J.Kim [QC#12851,DEL]
        // List<MAN_JRNL_ENTRY_DTLTMsg> mjedTMsgList = new ArrayList<MAN_JRNL_ENTRY_DTLTMsg>();
        // END 2016/08/05 J.Kim [QC#12851,DEL]
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            MAN_JRNL_ENTRY_DTLTMsg inTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.manJrnlEntryDtlPk, sMsg.A.no(index).manJrnlEntryDtlPk_A);
            MAN_JRNL_ENTRY_DTLTMsg outMjedTMsg = (MAN_JRNL_ENTRY_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
            if (outMjedTMsg == null) {
                cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return false;
            }

            // START 2016/08/05 J.Kim [QC#12851,MOD]
            // mjedTMsgList.add(outMjedTMsg);
            EZDTBLAccessor.logicalRemove(outMjedTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMjedTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NFAM0164E, new String[] {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK", String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
                return false;
            }
            // END 2016/08/05 J.Kim [QC#12851,MOD]
        }

        // START 2016/08/05 J.Kim [QC#12851,MOD]
        // MAN_JRNL_ENTRY_DTLTMsg[] inTMsgArray = new
        // MAN_JRNL_ENTRY_DTLTMsg[mjedTMsgList.size()];
        // int deleteCount =
        // S21FastTBLAccessor.removePhysical(mjedTMsgList.toArray(inTMsgArray));
        // if (deleteCount != inTMsgArray.length) {
        // cMsg.setMessageInfo(NFAM0164E, new String[]
        // {"Manual Journal Entry", "MAN_JRNL_ENTRY_HDR_PK",
        // String.valueOf(cMsg.manJrnlEntryHdrPk.getValue()) });
        // return false;
        // }
        // END 2016/08/05 J.Kim [QC#12851,MOD]
        return true;
    }

    private static boolean insertManualJournalEntryHeader(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg mjehTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
        BigDecimal manJrnlEntryHdrPk = getZYPOracleSeqManJrnlEntryHdrSq();
        ZYPEZDItemValueSetter.setValue(mjehTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.glPerNm, cMsg.glPerNm);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.glDt, cMsg.glDt);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.jrnlCatgCd, cMsg.jrnlCatgCd);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.manJrnlNm, cMsg.manJrnlNm);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.manJrnlDescTxt, cMsg.manJrnlDescTxt);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.rsvdRvslDt, cMsg.rsvdRvslDt);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.rvslCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.autoRvslFlg, setChkBox(cMsg.autoRvslFlg));
        ZYPEZDItemValueSetter.setValue(mjehTMsg.manJrnlCpltFlg, setChkBox(cMsg.manJrnlCpltFlg));
        ZYPEZDItemValueSetter.setValue(mjehTMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.insert(mjehTMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(mjehTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private static String setChkBox(EZDCStringItem obj) {

        if (!ZYPCommonFunc.hasValue(obj)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return obj.getValue();
    }

    private static boolean updateManualJournalEntryHeader(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg mjehTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(mjehTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mjehTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        MAN_JRNL_ENTRY_HDRTMsg outMjehTMsg = (MAN_JRNL_ENTRY_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mjehTMsg);

        if (outMjehTMsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(outMjehTMsg.glPerNm, cMsg.glPerNm);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.glDt, cMsg.glDt);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.jrnlCatgCd, cMsg.jrnlCatgCd);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.manJrnlNm, cMsg.manJrnlNm);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.manJrnlDescTxt, cMsg.manJrnlDescTxt);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.rsvdRvslDt, cMsg.rsvdRvslDt);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.rvslCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.autoRvslFlg, setChkBox(cMsg.autoRvslFlg));
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.manJrnlCpltFlg, setChkBox(cMsg.manJrnlCpltFlg));
        ZYPEZDItemValueSetter.setValue(outMjehTMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(outMjehTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMjehTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private static MAN_JRNL_ENTRY_DTLTMsg insertManualJournalEntryDetail(NFAL0220CMsg cMsg, NFAL0220_ASMsg asMsg, int num) {

        // Accounting String
        editAccountingString(asMsg);

        MAN_JRNL_ENTRY_DTLTMsg manJrnlEntryDtlTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
        BigDecimal manJrnlEntryDtlPk = getZYPOracleSeqManJrnlEntryDtlSq();
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.manJrnlEntryDtlPk, manJrnlEntryDtlPk);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.manJrnlLineNum, new BigDecimal(num));
        BigDecimal jrnlDealAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1)) {
            jrnlDealAmt = asMsg.jrnlDealAmt_A1.getValue();
            ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.drCrTpCd, DEBIT);
        } else if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2)) {
            jrnlDealAmt = asMsg.jrnlDealAmt_A2.getValue();
            ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.drCrTpCd, CREDIT);
        }
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaCmpyCd, asMsg.coaCmpyCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaBrCd, asMsg.coaBrCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaCcCd, asMsg.coaCcCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaAcctCd, asMsg.coaAcctCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaProdCd, asMsg.coaProdCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaChCd, asMsg.coaChCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaAfflCd, asMsg.coaAfflCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaProjCd, asMsg.coaProjCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.coaExtnCd, asMsg.coaExtnCd_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.jrnlDealAmt, jrnlDealAmt);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.jrnlFuncAmt, jrnlDealAmt);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.dealCcyCd, "USD");
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.manJrnlLineTxt, asMsg.manJrnlLineTxt_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.dsAcctNum, asMsg.dsAcctNum_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.serNum, asMsg.serNum_A);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.jrnlEntrySrcTpCd, asMsg.jrnlEntrySrcTpCd_LS);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.jrnlEntrySrcValTxt, asMsg.jrnlEntrySrcValTxt_A);
        ZYPEZDItemValueSetter.setValue(asMsg.manJrnlEntryDtlPk_A, manJrnlEntryDtlPk);
        return manJrnlEntryDtlTMsg;
    }

    private static boolean insert(List<MAN_JRNL_ENTRY_DTLTMsg> manJrnlEntryDtlTMsgList) {

        MAN_JRNL_ENTRY_DTLTMsg[] inTMsgArray = new MAN_JRNL_ENTRY_DTLTMsg[manJrnlEntryDtlTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(manJrnlEntryDtlTMsgList.toArray(inTMsgArray));
        if (insertCount != inTMsgArray.length) {
            return false;
        }
        return true;
    }

    private static boolean update(List<MAN_JRNL_ENTRY_DTLTMsg> manJrnlEntryDtlTMsgList) {

        MAN_JRNL_ENTRY_DTLTMsg[] inTMsgArray = new MAN_JRNL_ENTRY_DTLTMsg[manJrnlEntryDtlTMsgList.size()];
        int updateCount = S21FastTBLAccessor.update(manJrnlEntryDtlTMsgList.toArray(inTMsgArray));
        if (updateCount != inTMsgArray.length) {
            return false;
        }
        return true;
    }

    private static MAN_JRNL_ENTRY_DTLTMsg updateManualJournalEntryDetail(NFAL0220CMsg cMsg, NFAL0220_ASMsg asMsg, int num) {

        // Accounting String
        editAccountingString(asMsg);

        MAN_JRNL_ENTRY_DTLTMsg manJrnlEntryDtlTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.manJrnlEntryDtlPk, asMsg.manJrnlEntryDtlPk_A);
        MAN_JRNL_ENTRY_DTLTMsg outManJrnlEntryDtlTMsg = (MAN_JRNL_ENTRY_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(manJrnlEntryDtlTMsg);

        if (outManJrnlEntryDtlTMsg == null) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        // START 2016/08/05 J.Kim [QC#12851,MOD]
        // ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.manJrnlLineNum, asMsg.xxNum_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.manJrnlLineNum, new BigDecimal(num));
        // END 2016/08/05 J.Kim [QC#12851,MOD]
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaCmpyCd, asMsg.coaCmpyCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaBrCd, asMsg.coaBrCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaCcCd, asMsg.coaCcCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaAcctCd, asMsg.coaAcctCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaProdCd, asMsg.coaProdCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaChCd, asMsg.coaChCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaAfflCd, asMsg.coaAfflCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaProjCd, asMsg.coaProjCd_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.coaExtnCd, asMsg.coaExtnCd_A);
        BigDecimal jrnlDealAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A1)) {
            jrnlDealAmt = asMsg.jrnlDealAmt_A1.getValue();
            ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.drCrTpCd, DEBIT);
        } else if (ZYPCommonFunc.hasValue(asMsg.jrnlDealAmt_A2)) {
            jrnlDealAmt = asMsg.jrnlDealAmt_A2.getValue();
            ZYPEZDItemValueSetter.setValue(manJrnlEntryDtlTMsg.drCrTpCd, CREDIT);
        }
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.jrnlDealAmt, jrnlDealAmt);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.jrnlFuncAmt, jrnlDealAmt);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.dealCcyCd, "USD");
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.manJrnlLineTxt, asMsg.manJrnlLineTxt_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.dsAcctNum, asMsg.dsAcctNum_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.serNum, asMsg.serNum_A);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.jrnlEntrySrcTpCd, asMsg.jrnlEntrySrcTpCd_LS);
        ZYPEZDItemValueSetter.setValue(outManJrnlEntryDtlTMsg.jrnlEntrySrcValTxt, asMsg.jrnlEntrySrcValTxt_A);
        return manJrnlEntryDtlTMsg;
    }

    private static boolean isSameManualJournalEntryHeaderInfo(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg outJrnlHdrTMsg = NFAL0220Query.getInstance().manJrnlEntryHdrFindByKey(cMsg);
        if (outJrnlHdrTMsg == null) {
            return false;
        }

        String ezUpTime = cMsg.ezUpTime_H1.getValue();
        String ezUpTimeZone = cMsg.ezUpTimeZone_H1.getValue();
        String bfMsgEzUpTime = outJrnlHdrTMsg.ezUpTime.getValue();
        String bfsgEzUpTimeZone = outJrnlHdrTMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bfMsgEzUpTime, bfsgEzUpTimeZone, ezUpTime, ezUpTimeZone)) {
            return false;
        }
        return true;
    }

    private static boolean isSameManualJournalEntryDetail(NFAL0220CMsg cMsg, NFAL0220_ASMsg asMsg) {

        BigDecimal manJrnlEntryDtlPk = asMsg.manJrnlEntryDtlPk_A.getValue();
        MAN_JRNL_ENTRY_DTLTMsg outJrnlHdrTMsg = NFAL0220Query.getInstance().manJrnlEntryDtlFindByKey(cMsg.glblCmpyCd.getValue(), manJrnlEntryDtlPk);
        if (outJrnlHdrTMsg == null) {
            return false;
        }

        String ezUpTime = asMsg.ezUpTime_A.getValue();
        String ezUpTimeZone = asMsg.ezUpTimeZone_A.getValue();
        String bfMsgEzUpTime = outJrnlHdrTMsg.ezUpTime.getValue();
        String bfsgEzUpTimeZone = outJrnlHdrTMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bfMsgEzUpTime, bfsgEzUpTimeZone, ezUpTime, ezUpTimeZone)) {
            return false;
        }
        return true;
    }

    /**
     * @param writeCsvFileHeader
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NFAL0220F00FMsg
     * @param cMsg NFAL0220CMsg
     */
    public static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NFAL0220F00FMsg fMsg, NFAL0220CMsg cMsg) {
        final String[] csvHeader = new String[] {"Accounting String", "Debit", "Credit", "Line Description", "Customer#", "Serial#", "Source Type", "Source Value" };
        csvOutFile.writeHeader(csvHeader);
    }

    /**
     * Write csv file
     * @param cMsg NSBL0270CMsg
     * @param sMsg NFAL0220SMsg
     * @param fMsg NSBL0270F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    public static void writeCsvFileForSvcPrcShift(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg, NFAL0220F00FMsg fMsg, ZYPCSVOutFile csvOutFile) {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        // write contents
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NFAL0220_ASMsg asMsg = sMsg.A.no(i);
            if (i > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                break;
            }

            // resultset -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt, asMsg.xxScrItem61Txt_A.getValue());
            if (DEBIT.equals(asMsg.drCrTpCd_A.getValue())) {
                fMsg.jrnlDealAmt_A1.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlDealAmt_A0, asMsg.jrnlDealAmt_A1);
            }
            if (CREDIT.equals(asMsg.drCrTpCd_A.getValue())) {
                fMsg.jrnlDealAmt_A0.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlDealAmt_A1, asMsg.jrnlDealAmt_A2);
            }
            ZYPEZDItemValueSetter.setValue(fMsg.manJrnlLineTxt, asMsg.manJrnlLineTxt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, asMsg.dsAcctNum_A);
            ZYPEZDItemValueSetter.setValue(fMsg.serNum, asMsg.serNum_A);
            ZYPEZDItemValueSetter.setValue(fMsg.jrnlEntrySrcTpNm, getJrnLEntrySrcTpCd(cMsg, asMsg.jrnlEntrySrcTpCd_LS.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.jrnlEntrySrcValTxt, asMsg.jrnlEntrySrcValTxt_A);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    private static String getJrnLEntrySrcTpCd(NFAL0220CMsg cMsg, String jrnlEntrySrcTpCd) {

        String jrnlEntrySrcTpNm = "";
        JRNL_ENTRY_SRC_TPTMsg jestTMsg = NFAL0220Query.getInstance().getJrnLEntrySrcTpFindByKey(cMsg, jrnlEntrySrcTpCd);
        if (jestTMsg != null) {
            jrnlEntrySrcTpNm = jestTMsg.jrnlEntrySrcTpNm.getValue();
        }
        return jrnlEntrySrcTpNm;
    }

    /**
     * getRsvdRvslDt
     * @return String
     * @throws ParseException 
     */
    public static String getRsvdRvslDt() {

        String rsvdRvslDt = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df.parse(ZYPDateUtil.getSalesDate()));
            cal.add(Calendar.MONTH, 1);
            String dt = df.format(cal.getTime()).substring(0, 6);
            rsvdRvslDt = dt + STR_DATE;
        } catch (ParseException e) {
        }
        return rsvdRvslDt;
    }

    // START 2016/08/22 J.Kim [QC#13537,MOD]
    /**
     * editAccountingDt
     * @return String
     * @throws ParseException 
     */
    private static String editAccountingDt() {

        String accountingDt = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df.parse(ZYPDateUtil.getSalesDate()));
            cal.add(Calendar.MONTH, -1);
            accountingDt = df.format(cal.getTime()).substring(0, 6);
        } catch (ParseException e) {
        }
        return accountingDt;
    }
    // END 2016/08/22 J.Kim [QC#13537,MOD]

    // START 2016/07/13 J.Kim [QC#10325,DEL]
    // private static String
    // editAccountingString(MAN_JRNL_ENTRY_DTLTMsg inTMsg) {
    // StringBuffer sb = new StringBuffer();
    // sb.append(inTMsg.coaCmpyCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaBrCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaCcCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaAcctCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaProdCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaChCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaAfflCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaProjCd.getValue());
    // sb.append(DOT);
    // sb.append(inTMsg.coaExtnCd.getValue());
    // return sb.toString();
    // }
    // END 2016/07/13 J.Kim [QC#10325,DEL]

    private static void editAccountingString(NFAL0220_ASMsg asMsg) {

        String xxScrItem61Txt = asMsg.xxScrItem61Txt_A.getValue();
        String[] accStr = xxScrItem61Txt.split(STR_DOT);
        // START 2016/08/02 K.Kojima [QC#12766,ADD]
        // ZYPEZDItemValueSetter.setValue(asMsg.coaCmpyCd_A,
        // accStr[0]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaBrCd_A, accStr[2]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaCcCd_A, accStr[3]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaAcctCd_A,
        // accStr[4]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaProdCd_A,
        // accStr[5]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaChCd_A, accStr[6]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaAfflCd_A,
        // accStr[1]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaProjCd_A,
        // accStr[7]);
        // ZYPEZDItemValueSetter.setValue(asMsg.coaExtnCd_A,
        // accStr[8]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaCmpyCd_A, accStr[ARRAY_NUM_CMPY_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaBrCd_A, accStr[ARRAY_NUM_BR_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaCcCd_A, accStr[ARRAY_NUM_CC_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaAcctCd_A, accStr[ARRAY_NUM_ACCT_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaProdCd_A, accStr[ARRAY_NUM_PROD_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaChCd_A, accStr[ARRAY_NUM_CH_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaAfflCd_A, accStr[ARRAY_NUM_AFFL_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaProjCd_A, accStr[ARRAY_NUM_PROJ_CD]);
        ZYPEZDItemValueSetter.setValue(asMsg.coaExtnCd_A, accStr[ARRAY_NUM_EXTN_CD]);
        // END 2016/08/02 K.Kojima [QC#12766,MOD]
    }

    // START 2016/08/22 J.Kim [QC#13537,ADD]
    /**
     * convertGlPeriod
     * @param cMsg NFAL0220CMsg
     * @return String
     */
    public static String convertGlPeriod(NFAL0220CMsg cMsg) {

        String glPeriod = "";
        if (ZYPCommonFunc.hasValue(cMsg.glDt)) {

            String strYYYY = cMsg.glDt.getValue().substring(2, 4);
            String monthNm = cMsg.glDt.getValue().substring(4, 6);
            // When 21XX, it won't work...
            String strYYYYMM = "-".concat(strYYYY);
            String strMM = "";

            if ("01".equals(monthNm)) {
                strMM = "JAN";
            } else if ("02".equals(monthNm)) {
                strMM = "FEB";
            } else if ("03".equals(monthNm)) {
                strMM = "MAR";
            } else if ("04".equals(monthNm)) {
                strMM = "APR";
            } else if ("05".equals(monthNm)) {
                strMM = "MAY";
            } else if ("06".equals(monthNm)) {
                strMM = "JUN";
            } else if ("07".equals(monthNm)) {
                strMM = "JUL";
            } else if ("08".equals(monthNm)) {
                strMM = "AUG";
            } else if ("09".equals(monthNm)) {
                strMM = "SEP";
            } else if ("10".equals(monthNm)) {
                strMM = "OCT";
            } else if ("11".equals(monthNm)) {
                strMM = "NOV";
            } else if ("12".equals(monthNm)) {
                strMM = "DEC";
            } else {
                strYYYYMM = "";
            }

            glPeriod =  strMM.concat(strYYYYMM);
        }
        return glPeriod;
    }
    // END 2016/08/22 J.Kim [QC#13537,ADD]

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param sMsgCount int
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int sMsgCount) {

        int insertPage = (sMsgCount / rowsPerPage) * rowsPerPage;
        return insertPage;
    }

    /**
     * @param maxDispalyRows int
     * @param rowNum int
     * @return int
     */
    public static int getPageNum(int maxDispalyRows, int rowNum) {
        return ((rowNum - 1) / maxDispalyRows + 1);
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNum(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }
}
