/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0760.common;

import static business.blap.NFCL0760.constant.NFCL0760Constant.BUSINESS_ID;
import static business.blap.NFCL0760.constant.NFCL0760Constant.NFCM0522E;
import static business.blap.NFCL0760.constant.NFCL0760Constant.NZZM0000E;
import static business.blap.NFCL0760.constant.NFCL0760Constant.NZZM0001W;
import static business.blap.NFCL0760.constant.NFCL0760Constant.NZZM0002I;
import static business.blap.NFCL0760.constant.NFCL0760Constant.WRT_OFF_OPT_TP_IS_GEN_ONLY;
import static business.blap.NFCL0760.constant.NFCL0760Constant.WRT_OFF_RQST_GRP_CD_IS_NONE;
import static business.blap.NFCL0760.constant.NFCL0760Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NFCL0760.NFCL0760CMsg;
import business.blap.NFCL0760.NFCL0760Query;
import business.blap.NFCL0760.NFCL0760SMsg;
import business.blap.NFCL0760.NFCL0760_ASMsg;
import business.file.NFCL0760F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/10   Hitachi         T.Tsuchida      Update          QC#5026
 * 2016/03/28   Fujitsu         S.Yoshida       Update          QC#5970
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 * 2018/05/31   Fujitsu         S.Ohki          Update          QC#24747
 * 2018/08/02   Hitachi         E.Kameishi      Update          QC#27541
 *</pre>
 */
public class NFCL0760CommonLogic {

    /**
     * Clear Message
     * @param cMsg NFCL0760CMsg
     * @param sMsg NFCL0760SMsg
     */
    public static void clearMsg(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NFCL0750CMsg
     * @param glblCmpyCd String
     */
    public static void setInitParams(NFCL0760CMsg cMsg, String glblCmpyCd) {
        setValue(cMsg.glblCmpyCd, glblCmpyCd);
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        if (WRT_OFF_RQST_GRP_CD_IS_NONE.equals(cMsg.wrtOffRqstGrpCd_H.getValue())) {
            cMsg.wrtOffRqstGrpCd_H.clear();
        }
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NFCL0760CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NFCL0760CMsg cMsg) {
        // START 2018/05/31 S.Ohki [QC#24747,MOD]
//        if (!hasValue(cMsg.arWrtOffRqstPk_H)
//                || !hasValue(cMsg.wrtOffRqstGrpCd_H)
//                || !hasValue(cMsg.wrtOffRqstUsrId_H)
//                || !hasValue(cMsg.arWrtOffRqstTpCd_H)) {
        if (!hasValue(cMsg.wrtOffRqstGrpCd_H)
                || !hasValue(cMsg.wrtOffRqstUsrId_H)
                || !hasValue(cMsg.arWrtOffRqstTpCd_H)) {
        // END 2018/05/31 S.Ohki [QC#24747,MOD]
            cMsg.setMessageInfo(NFCM0522E);
            return true;
        }
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NFCL0760CMsg
     * @param sMsg NFCL0760SMsg
     */
    public static void getSearchData(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {
        S21SsmEZDResult ssmResult = NFCL0760Query.getInstance().getHeaderData(cMsg);
        if (!ssmResult.isCodeNormal()) {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        ssmResult = NFCL0760Query.getInstance().getDetailData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * Set Data
     * @param cMsg NFCL0760CMsg
     * @param sMsg NFCL0760SMsg
     */
    public static void setData(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {
        BigDecimal totAmt = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NFCL0760_ASMsg asMsg = sMsg.A.no(i);
            if (WRT_OFF_OPT_TP_IS_GEN_ONLY.equals(cMsg.wrtOffOptTpCd_H.getValue())) {
                if (hasValue(asMsg.dealRmngBalGrsAmt_A)) {
                    totAmt = totAmt.add(asMsg.dealRmngBalGrsAmt_A.getValue());
                }
            } else {
                // START 2018/08/02 E.Kameishi [QC#27541,MOD]
                if (AR_DS_WF_STS.PENDING.equals(asMsg.arDsWfStsCd_A.getValue())) {
                    if (hasValue(asMsg.dealApplyAdjRsvdAmt_A)) {
                        totAmt = totAmt.add(asMsg.dealApplyAdjRsvdAmt_A.getValue());
                    }
                } else if (AR_DS_WF_STS.APPROVED.equals(asMsg.arDsWfStsCd_A.getValue())) {
                    if (hasValue(asMsg.dealArAdjAmt_A)) {
                        totAmt = totAmt.add(asMsg.dealArAdjAmt_A.getValue());
                    }
                }
                // END 2018/08/02 E.Kameishi [QC#27541,MOD]
            }
        }
        setValue(cMsg.xxTotAmt_H, totAmt);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NFCL0760CMsg
     * @param sMsg NFCL0760SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg, int pagenationFrom) {

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
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg, int pageFrom) {

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
     * writeCsvFile
     * @param cMsg NFCL0760CMsg
     * @param sMsg NFCL0760SMsg
     */
    public static void writeCsvFile(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {

        NFCL0760F00FMsg fMsg = new NFCL0760F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        writeCsvFileDetail(csvOutFile, fMsg, sMsg);

        csvOutFile.close();
    }

    // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
    /**
     * writeCsvFileHeader
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NFCL0760F00FMsg
     * @param cMsg NFCL0760CMsg
     */
    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NFCL0760F00FMsg fMsg, NFCL0760CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "User ID")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Write Off Request#")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Total Amount")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Reason")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Activity")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Note")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Remaining Amount (From)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Remaining Amount (To)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Invoice# (From)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Invoice# (To)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Due Date (From)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Due Date (To)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Customer Number (From)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Customer Number (To)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Invoice Class")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Include Consolidated Invoice")
        };
        csvOutFile.writeHeader(csvHeader);

        setValue(fMsg.xxComnScrColValTxt_1, cMsg.wrtOffRqstUsrId_H);
        setValue(fMsg.xxComnScrColValTxt_2, cMsg.wrtOffRqstGrpCd_H);
        setValue(fMsg.xxComnScrColValTxt_3, convertAmtFormat(cMsg.xxTotAmt_H.getValue()));
        setValue(fMsg.xxComnScrColValTxt_4, cMsg.arAdjRsnDescTxt_H);
        setValue(fMsg.xxComnScrColValTxt_5, cMsg.arAdjTpDescTxt_H);
        setValue(fMsg.xxComnScrColValTxt_6, cMsg.arWrtOffNoteTxt_H);
        setValue(fMsg.xxComnScrColValTxt_7, convertAmtFormat(cMsg.lowRmngBalAmt_H.getValue()));
        setValue(fMsg.xxComnScrColValTxt_8, convertAmtFormat(cMsg.highRmngBalAmt_H.getValue()));
        setValue(fMsg.xxComnScrColValTxt_9, cMsg.lowInvNum_H);
        setValue(fMsg.xxComnScrColValTxt_10, cMsg.highInvNum_H);
        setValue(fMsg.xxComnScrColValTxt_11, convertDateFormat(cMsg.lowInvDueDt_H.getValue()));
        setValue(fMsg.xxComnScrColValTxt_12, convertDateFormat(cMsg.highInvDueDt_H.getValue()));
        setValue(fMsg.xxComnScrColValTxt_13, cMsg.lowDsAcctNum_H);
        setValue(fMsg.xxComnScrColValTxt_14, cMsg.highDsAcctNum_H);
        setValue(fMsg.xxComnScrColValTxt_15, cMsg.invTpDescTxt_H);
        csvOutFile.write();

        fMsg.clear();
        csvOutFile.write();
    }

    /**
     * writeCsvFileDetail
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NFCL0760F00FMsg
     * @param sMsg NFCL0760SMsg
     */
    private static void writeCsvFileDetail(ZYPCSVOutFile csvOutFile, NFCL0760F00FMsg fMsg, NFCL0760SMsg sMsg) {
        setValue(fMsg.xxComnScrColValTxt_1, "Adj / Req Date");
        setValue(fMsg.xxComnScrColValTxt_2, "Cust Num");
        setValue(fMsg.xxComnScrColValTxt_3, "Customer Name");
        setValue(fMsg.xxComnScrColValTxt_4, "Invoice#");
        setValue(fMsg.xxComnScrColValTxt_5, "Inv Cls");
        setValue(fMsg.xxComnScrColValTxt_6, "Inv Type");
        setValue(fMsg.xxComnScrColValTxt_7, "Invoice Amount");
        setValue(fMsg.xxComnScrColValTxt_8, "Due Date");
        setValue(fMsg.xxComnScrColValTxt_9, "WO Pdg AMT");
        setValue(fMsg.xxComnScrColValTxt_10, "Rmng Bal");
        setValue(fMsg.xxComnScrColValTxt_11, "Adj Num");
        setValue(fMsg.xxComnScrColValTxt_12, "Adj Amt");
        // START 2018/02/28 J.Kim [QC#21143,MOD]
        // setValue(fMsg.xxComnScrColValTxt_13, "Proc Sts");
        setValue(fMsg.xxComnScrColValTxt_13, "Wf Sts");
        // END 2018/02/28 J.Kim [QC#21143,MOD]
        setValue(fMsg.xxComnScrColValTxt_14, "Error Message");
        fMsg.xxComnScrColValTxt_15.clear();
        csvOutFile.write();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NFCL0760_ASMsg asMsg = sMsg.A.no(i);
            setValue(fMsg.xxComnScrColValTxt_1, convertDateFormat(asMsg.adjDt_A.getValue()));
            setValue(fMsg.xxComnScrColValTxt_2, asMsg.billToCustAcctCd_A);
            setValue(fMsg.xxComnScrColValTxt_3, asMsg.dsAcctNm_A);
            setValue(fMsg.xxComnScrColValTxt_4, asMsg.arTrxNum_A);
            setValue(fMsg.xxComnScrColValTxt_5, asMsg.invTpDescTxt_A);
            setValue(fMsg.xxComnScrColValTxt_6, asMsg.dsInvTpDescTxt_A);
            setValue(fMsg.xxComnScrColValTxt_7, convertAmtFormat(asMsg.dealOrigGrsAmt_A.getValue()));
            setValue(fMsg.xxComnScrColValTxt_8, convertDateFormat(asMsg.invDueDt_A.getValue()));
            setValue(fMsg.xxComnScrColValTxt_9, convertAmtFormat(asMsg.dealApplyAdjRsvdAmt_A.getValue()));
            setValue(fMsg.xxComnScrColValTxt_10, convertAmtFormat(asMsg.dealRmngBalGrsAmt_A.getValue()));
            setValue(fMsg.xxComnScrColValTxt_11, asMsg.arAdjNum_A);
            setValue(fMsg.xxComnScrColValTxt_12, convertAmtFormat(asMsg.dealArAdjAmt_A.getValue()));
            // START 2018/02/28 J.Kim [QC#21143,MOD]
            // setValue(fMsg.xxComnScrColValTxt_13, asMsg.procStsDescTxt_A);
            setValue(fMsg.xxComnScrColValTxt_13, asMsg.arDsWfStsDescTxt_A);
            // END 2018/02/28 J.Kim [QC#21143,MOD]
            setValue(fMsg.xxComnScrColValTxt_14, asMsg.wrtOffErrMsgTxt_A);
            fMsg.xxComnScrColValTxt_15.clear();
            csvOutFile.write();
        }
    }
    // END 2017/08/21 T.Tsuchida [QC#19573,MOD]

    /**
     * convertDateFormat
     * @param date String
     * @return String
     */
    private static String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    /**
     * convertAmtFormat
     * @param data BigDecimal
     * @return String
     */
    private static String convertAmtFormat(BigDecimal data) {
        String rtnVal = "";
        // START 2016/03/10 T.Tsuchida [QC#5026,MOD]
//        if (hasValue(data)) {
//            DecimalFormat df = new DecimalFormat("#.00");
//            rtnVal = df.format(data);
//        }
        if (hasValue(data)) {
            rtnVal = ZYPFormatUtil.formatNumToSysDisp(data.setScale(2));
        }
        // START 2016/03/10 T.Tsuchida [QC#5026,MOD]
        return rtnVal;
    }
}
