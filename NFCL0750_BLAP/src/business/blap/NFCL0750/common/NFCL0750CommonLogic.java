/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0750.common;

import static business.blap.NFCL0750.constant.NFCL0750Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NFCL0750.NFCL0750CMsg;
import business.blap.NFCL0750.NFCL0750Query;
import business.blap.NFCL0750.NFCL0750SMsg;
import business.blap.NFCL0750.NFCL0750_ASMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.file.NFCL0750F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/10   Hitachi         T.Tsuchida      Update          QC#5025
 * 2016/07/06   Hitachi         K.Kojima        Update          QC#11025
 * 2016/07/12   Hitachi         T.Tsuchida      Update          QC#11753
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 * 2018/04/20   Hitachi         J.Kim           Update          QC#24885
 *</pre>
 */
public class NFCL0750CommonLogic {

    /**
     * Clear Message
     * @param cMsg NFCL0750CMsg
     * @param sMsg NFCL0750SMsg
     */
    public static void clearMsg(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NFCL0750CMsg
     */
    public static void createPullDown(NFCL0750CMsg cMsg) {
        createArAdjRsnPullDown(cMsg);
        createArAdjTpPullDown(cMsg);
        createArWrtOffRqstTpPullDown(cMsg);
        createProcStsPullDown(cMsg);
        // START 2018/04/19 J.Kim [QC#24885,ADD]
        createCltDispTpPullDown(cMsg);
        // END 2018/04/19 J.Kim [QC#24885,ADD]
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NFCL0750CMsg
     * @param String glblCmpyCd
     */
    public static void setInitParams(NFCL0750CMsg cMsg, String glblCmpyCd) {
        setValue(cMsg.glblCmpyCd, glblCmpyCd);
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        // START 2018/04/19 J.Kim [QC#24885,DEL]
        // setValue(cMsg.wrtOffRqstUsrId_H, cMsg.getUserID());
        // END 2018/04/19 J.Kim [QC#24885,DEL]
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NFCL0750CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NFCL0750CMsg cMsg) {
        boolean rtnVal = false;
        if (hasValue(cMsg.xxFromDt_H)
                && hasValue(cMsg.xxThruDt_H)
                && ZYPDateUtil.compare(cMsg.xxFromDt_H.getValue(), cMsg.xxThruDt_H.getValue()) > 0) {
            cMsg.xxFromDt_H.setErrorInfo(1, NFCM0023E);
            cMsg.xxThruDt_H.setErrorInfo(1, NFCM0023E);
            rtnVal = true;
        }
        if (!existDsAcctCustNum(cMsg)) {
            rtnVal = true;
        }
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        if (hasValue(cMsg.arWrtOffRqstTpDescTxt_SV)
                && AR_WRT_OFF_RQST_TP.SYSTEM.equals(cMsg.arWrtOffRqstTpDescTxt_SV.getValue())
                && (!hasValue(cMsg.xxFromDt_H) && !hasValue(cMsg.xxThruDt_H))) {
            cMsg.xxFromDt_H.setErrorInfo(1, ZZM9000E, new String[] {ADJ_DT});
            cMsg.xxThruDt_H.setErrorInfo(1, ZZM9000E, new String[] {ADJ_DT});
            rtnVal = true;
        }
        
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        return rtnVal;
    }

    /**
     * Get Search Data
     * @param cMsg NFCL0750CMsg
     * @param sMsg NFCL0750SMsg
     */
    public static void getSearchData(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        S21SsmEZDResult ssmResult = NFCL0750Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length());
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
            // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * Set Display Data
     * @param cMsg NFCL0750CMsg
     * @param sMsg NFCL0750SMsg
     */
    public static void setDisplayData(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        if (hasValue(cMsg.dsAcctNum_H)) {
            getDsAcctCustNm(cMsg);
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NFCL0750_ASMsg aSMsg = sMsg.A.no(i);
            setValue(aSMsg.xxRowNum_A, BigDecimal.valueOf(i));
            setValue(aSMsg.xxGenlFldAreaTxt_FB, getConvertData(aSMsg.lowRmngBalAmt_A, aSMsg.highRmngBalAmt_A));
            setValue(aSMsg.xxGenlFldAreaTxt_IN, getConvertData(aSMsg.lowInvNum_A, aSMsg.highInvNum_A));
            setValue(aSMsg.xxGenlFldAreaTxt_DT, getConvertData(aSMsg.lowInvDueDt_A, aSMsg.highInvDueDt_A));
            if (hasValue(aSMsg.arWrtOffRqstTpCd_A)
                    && AR_WRT_OFF_RQST_TP.SYSTEM.equals(aSMsg.arWrtOffRqstTpCd_A.getValue())) {
                setValue(aSMsg.xxGenlFldAreaTxt_CN, getConvertData(aSMsg.lowBillToCustCd_A, aSMsg.highBillToCustCd_A));
            } else {
                setValue(aSMsg.xxGenlFldAreaTxt_CN, getConvertData(aSMsg.lowDsAcctNum_A, aSMsg.highDsAcctNum_A));
            }
            S21StringUtil.concatStrings(aSMsg.lowDsAcctNum_A, DIV_STR, aSMsg.highDsAcctNum_A);
        }
    }

    /**
     * Get Customer Name
     * @param cMsg NFCL0750CMsg
     * @return boolean
     */
    public static void getDsAcctCustNm(NFCL0750CMsg cMsg) {
        if (!hasValue(cMsg.dsAcctNum_H)) {
            cMsg.dsAcctNm_H.clear();
        }
        SELL_TO_CUSTTMsg sellToCustTMsg = NFCL0750Query.getInstance().getSellToCust(cMsg);
        if (sellToCustTMsg != null) {
            setValue(cMsg.dsAcctNm_H, sellToCustTMsg.dsAcctNm);
        }
    }

    /**
     * Exist Customer Num
     * @param cMsg NFCL0750CMsg
     * @return boolean
     */
    public static boolean existDsAcctCustNum(NFCL0750CMsg cMsg) {
        if (hasValue(cMsg.dsAcctNum_H)
                && !NFCL0750Query.getInstance().existSellToCust(cMsg)) {
            cMsg.dsAcctNm_H.clear();
            cMsg.dsAcctNum_H.setErrorInfo(1, NFCM0763E);
            return false;
        }
        return true;
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NFCL0750CMsg
     * @param sMsg NFCL0750SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg, int pageFrom) {

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
//        cMsg.xxPageShowOfNum.setValue();
//        cMsg.xxPageShowCurNum.setValue();
//        cMsg.xxPageShowTotNum.setValue();
//        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
//        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
//        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
//        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
//        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }

    /**
     * writeCsvFile
     * @param cMsg NFCL0750CMsg
     * @param sMsg NFCL0750SMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        NFCL0750F00FMsg fMsg = new NFCL0750F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        // Set don't display column
        fMsg.addExclusionItem("xxRadioBtn_A");

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            //resultSet -> fMsg
            EZDMsg.copy(sMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * writeCsvFileHeader
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NFCL0750F00FMsg
     * @param cMsg NFCL0750CMsg
     */
    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NFCL0750F00FMsg fMsg, NFCL0750CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "dummy1")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "User ID")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Write Off Request#")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Request Type")
                // START 2016/07/06 K.Kojima [QC#11025,ADD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Option")
                // END 2016/07/06 K.Kojima [QC#11025,ADD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Reason")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Activity")
                // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Note")
                // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Remaining Balance")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Invoice#")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Due Date")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Customer Number")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Incl Cnsl Inv")
                // START 2018/02/28 J.Kim [QC#21143,MOD]
                // , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Proc Sts")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Wf Sts")
                // END 2018/02/28 J.Kim [QC#21721,MOD]
                // START 2018/03/30 J.Kim [QC#21721,MOD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Approver Name")
                // END 2018/03/30 J.Kim [QC#21143,MOD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Error Message")
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    private static void createArAdjRsnPullDown(NFCL0750CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(TBL_NM.AR_ADJ_RSN.toString(), cMsg.arAdjRsnCd_L, cMsg.arAdjRsnDescTxt_L);
        if (!hasValue(cMsg.arAdjRsnCd_L.no(0))) {
            cMsg.arAdjRsnDescTxt_SV.setErrorInfo(1, NFCM0041E);
        }
    }

    private static void createArAdjTpPullDown(NFCL0750CMsg cMsg) {
        AR_ADJ_TPTMsgArray tMsgAry = NFCL0750Query.getInstance().getArAdjTpList(cMsg);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "arAdjTpCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "arAdjTpDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.arAdjTpCd_L, cMsg.arAdjTpDescTxt_L);
        if (!hasValue(cMsg.arAdjTpCd_L.no(0))) {
            cMsg.arAdjTpDescTxt_SV.setErrorInfo(1, NFCM0041E);
        }
    }

    private static void createArWrtOffRqstTpPullDown(NFCL0750CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(TBL_NM.AR_WRT_OFF_RQST_TP.toString(), cMsg.arWrtOffRqstTpCd_L, cMsg.arWrtOffRqstTpDescTxt_L);
        if (!hasValue(cMsg.arWrtOffRqstTpCd_L.no(0))) {
            cMsg.arWrtOffRqstTpDescTxt_SV.setErrorInfo(1, NFCM0041E);
        }
    }

    private static void createProcStsPullDown(NFCL0750CMsg cMsg) {
        // START 2018/02/28 J.Kim [QC#21143,MOD]
        // ZYPCodeDataUtil.createPulldownList(TBL_NM.PROC_STS.toString(), cMsg.procStsCd_L, cMsg.procStsDescTxt_L);
        ZYPCodeDataUtil.createPulldownList(AR_DS_WF_STS.class, cMsg.arDsWfStsCd_L, cMsg.arDsWfStsDescTxt_L);
        // END 2018/02/28 J.Kim [QC#21143,MOD]
        if (!hasValue(cMsg.arDsWfStsCd_L.no(0))) {
            cMsg.arDsWfStsDescTxt_SV.setErrorInfo(1, NFCM0041E);
        }
    }

    private static String getConvertData(EZDSStringItem inData1, EZDSStringItem inData2) {
        String rtnVal = "";
        if (hasValue(inData1)
                && hasValue(inData2)) {
            rtnVal = S21StringUtil.concatStrings(inData1.getValue(), DIV_STR, inData2.getValue());
        }
        return rtnVal;
    }

    private static String getConvertData(EZDSBigDecimalItem inData1, EZDSBigDecimalItem inData2) {
        String rtnVal = "";
        if (hasValue(inData1)
                && hasValue(inData2)) {
            rtnVal = S21StringUtil.concatStrings(getNumberFormat(inData1.getValue()), DIV_STR, getNumberFormat(inData2.getValue()));
        }
        return rtnVal;
    }

    private static String getNumberFormat(BigDecimal data) {
        // START 2016/03/10 T.Tsuchida [QC#5025,MOD]
//        NumberFormat nfNum = NumberFormat.getNumberInstance();
//        if (hasValue(data)) {
//            return nfNum.format(data);
//        }
        if (hasValue(data)) {
            return ZYPFormatUtil.formatNumToSysDisp(data.setScale(2));
        }
        // END 2016/03/10 T.Tsuchida [QC#5025,MOD]
        return "";
    }

    private static String getConvertData(EZDSDateItem inData1, EZDSDateItem inData2) {
        String rtnVal = "";
        if (hasValue(inData1)
                && hasValue(inData2)) {
            // START 2016/03/10 T.Tsuchida [QC#11753,MOD]
//            rtnVal = S21StringUtil.concatStrings(
//                    ZYPDateUtil.convertFormat(inData1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, ZYPDateUtil.SEPARATOR_SLASH)
//                    , DIV_STR
//                    , ZYPDateUtil.convertFormat(inData2.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, ZYPDateUtil.SEPARATOR_SLASH));
            rtnVal = S21StringUtil.concatStrings(
                    ZYPDateUtil.formatEzd8ToDisp(inData1.getValue(), true)
                    , DIV_STR
                    , ZYPDateUtil.formatEzd8ToDisp(inData2.getValue(), true));
            // END 2016/03/10 T.Tsuchida [QC#11753,MOD]
        }
        return rtnVal;
    }

    // START 2018/04/19 J.Kim [QC#24885,ADD]
    private static void createCltDispTpPullDown(NFCL0750CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(CLT_DISP_TP.class, cMsg.cltDispTpCd_LC, cMsg.cltDispTpDescTxt_LD, ":");
    }
    // END 2018/04/19 J.Kim [QC#24885,ADD]
}
