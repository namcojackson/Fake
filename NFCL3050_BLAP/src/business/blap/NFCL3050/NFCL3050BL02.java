package business.blap.NFCL3050;

import static business.blap.NFCL3050.constant.NFCL3050Constant.COMP_TP_COMP;
import static business.blap.NFCL3050.constant.NFCL3050Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NFCL3050.constant.NFCL3050Constant.CSV_DOWNLOAD_HEADER_GENERAL;
import static business.blap.NFCL3050.constant.NFCL3050Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NFCL3050.constant.NFCL3050Constant.DIS_PAT_VISIBILITY_ERROR;
import static business.blap.NFCL3050.constant.NFCL3050Constant.INCLUDE_CLOSED_INVOICES;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3050.common.NFCL3050CommonLogic;
import business.file.NFCL3050F00FMsg;
import business.file.NFCL3050F01FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NFCL3050BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/09   Hitachi         T.Tsuchida      Update          QC#5104
 * 2016/03/22   CSAI            K.Uramori       Update          QC#5770
 * 2016/04/26   Fujitsu         S.Fujita        Update          QC#7586
 * 2016/06/24   Hitachi         K.Kojima        Update          QC#8026
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#11507
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#13478
 * 2017/09/27   Hitachi         T.Tsuchida      Update          QC#21373
 * 2018/07/30   CITS            K.Ogino         Update          QC#26680
 *</pre>
 */
public class NFCL3050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3050CMsg bizMsg = (NFCL3050CMsg) cMsg;
            NFCL3050SMsg glblMsg = (NFCL3050SMsg) sMsg;

            if ("NFCL3050_INIT".equals(screenAplID)) {
                doProcess_NFCL3050_INIT(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_PageJump".equals(screenAplID)) {
                    doProcess_NFCL3050Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_Search(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_PageNext".equals(screenAplID)) {
                    doProcess_NFCL3050Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NFCL3050Scrn00_PagePrev".equals(screenAplID)) {
                    doProcess_NFCL3050Scrn00_PagePrev(bizMsg, glblMsg);

            //---- start add 2016/03/22 QC#5770
            } else if ("NFCL3050Scrn00_btnSelectAll".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_btnSelectAll(bizMsg, glblMsg);
            } else if ("NFCL3050Scrn00_btnUnslctAll".equals(screenAplID)) {
                doProcess_NFCL3050Scrn00_btnUnslctAll(bizMsg, glblMsg);
            //---- end 2016/03/22
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050_INIT(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        //Setup select box 
        NFCL3050CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

        // START 2016/07/14 K.Kojima [QC#11507,DEL]
        // // create Pull Down for Invoice Class
        // ZYPCodeDataUtil.createPulldownList(INV_TP.class,
        // bizMsg.invTpCd_H, bizMsg.invTpNm_H);
        // // START 2016/06/24 K.Kojima [QC#8026,ADD]
        // ZYPCodeDataUtil.createPulldownList(SYS_SRC.class,
        // bizMsg.sysSrcCd_H, bizMsg.sysSrcDescTxt_H);
        // // END 2016/06/24 K.Kojima [QC#8026,ADD]
        // END 2016/07/14 K.Kojima [QC#11507,DEL]

        // START 2016/07/12 K.Kojima [QC#11049,MOD]
        // ZYPGUITableColumn.getColData(bizMsg, glblMsg);
        if (bizMsg.xxDplyTab.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "A");
        } else {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "B");
        }
        // END 2016/07/12 K.Kojima [QC#11049,MOD]

        // START 2016/07/14 K.Kojima [QC#11507,DEL]
        // // create Pull Down for Invoice Type
        // S21SsmEZDResult ssmResult =
        // NFCL3050Query.getInstance().getInvoiceTpList();
        // if (ssmResult.isCodeNotFound()) {
        // return;
        // }
        // @SuppressWarnings("unchecked")
        // List<DS_INV_TPTMsg> outMsg = (List<DS_INV_TPTMsg>)
        // ssmResult.getResultObject();
        //
        // for (int i = 0; i < outMsg.size(); i++) {
        // if (i >= bizMsg.dsInvTpCd_H.length()) {
        // break;
        // }
        // DS_INV_TPTMsg dsInvTpTMsg = (DS_INV_TPTMsg) outMsg.get(i);
        // ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpCd_H.no(i),
        // dsInvTpTMsg.dsInvTpCd);
        // ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpNm_H.no(i),
        // dsInvTpTMsg.dsInvTpNm);
        // }
        // END 2016/07/14 K.Kojima [QC#11507,DEL]

        // START 2016/07/14 K.Kojima [QC#11507,ADD]
        NFCL3050CommonLogic.createPulldownListInvoiceClass(bizMsg);
        NFCL3050CommonLogic.createPulldownListInvoiceType(bizMsg);
        NFCL3050CommonLogic.createPulldownListSoruce(bizMsg);
        // END 2016/07/14 K.Kojima [QC#11507,ADD]
        // START 2017/09/27 T.Tsuchida [QC#21373,ADD]
        String IncludeClosedInvoices = ZYPCodeDataUtil.getVarCharConstValue(INCLUDE_CLOSED_INVOICES, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CL, IncludeClosedInvoices);
        // END 2017/09/27 T.Tsuchida [QC#21373,ADD]
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_CMN_Clear(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.clear();
        glblMsg.A.setValidCount(0);

        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, xxDplyTab);
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        // START 2016/08/23 S.Fujita [QC#13478,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_H, COMP_TP_COMP);
        // END   2016/08/23 S.Fujita [QC#13478,MOD]

        doProcess_NFCL3050_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_CMN_Download(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), ".csv");

        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        if (bizMsg.xxDplyTab.getValue().equals(DIS_PAT_VISIBILITY_ERROR)) {
            downloadForAll(bizMsg, glblMsg);
        } else {
            downloadForGeneral(bizMsg, glblMsg);
        }
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        // START 2016/07/12 K.Kojima [QC#11049,DEL]
        // NFCL3050F00FMsg fMsg = new NFCL3050F00FMsg();
        // ZYPCSVOutFile csvOutFile = new
        // ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        // fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        //
        // fMsg.addExclusionItem("xxRadioBtn_A");
        // // START 2016/04/26 S.Fujita [QC#7586,MOD]
        // fMsg.addExclusionItem("xxChkBox_A");
        // // END 2016/04/26 S.Fujita [QC#7586,MOD]
        //
        // //write header
        // // csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg,
        // ZYPGUITableColumn.getColOrder(bizMsg));
        // csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER);
        //
        // //write contents
        // for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
        // EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
        // // START 2016/03/09 T.Tsuchida [QC#5104,ADD]
        // ZYPEZDItemValueSetter.setValue(fMsg.dealCltDsptAmt_A,
        // convertAmtFormat(glblMsg.A.no(i).dealCltDsptAmt_A.getValue()));
        // ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A,
        // convertAmtFormat(glblMsg.A.no(i).dealRmngBalGrsAmt_A.getValue()));
        // ZYPEZDItemValueSetter.setValue(fMsg.invTotDealNetAmt_A,
        // convertAmtFormat(glblMsg.A.no(i).invTotDealNetAmt_A.getValue()));
        // ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_I,
        // convertDateFormat(glblMsg.A.no(i).invDt_A.getValue()));
        // ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_I,
        // convertDateFormat(glblMsg.A.no(i).invDt_A.getValue()));
        // ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ID,
        // convertDateFormat(glblMsg.A.no(i).invDueDt_A.getValue()));
        // ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CD,
        // convertDateFormat(glblMsg.A.no(i).cltDsptDt_A.getValue()));
        // // END 2016/03/09 T.Tsuchida [QC#5104,ADD]
        // csvOutFile.write();
        // }
        //
        // csvOutFile.close();
        // END 2016/07/12 K.Kojima [QC#11049,DEL]
    }

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    private void downloadForGeneral(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        NFCL3050F01FMsg fMsg = new NFCL3050F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        fMsg.addExclusionItem("xxRadioBtn_A");
        fMsg.addExclusionItem("xxChkBox_A");

        // write header
        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER_GENERAL, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
            ZYPEZDItemValueSetter.setValue(fMsg.dealCltDsptAmt_A, convertAmtFormat(glblMsg.A.no(i).dealCltDsptAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A, convertAmtFormat(glblMsg.A.no(i).dealRmngBalGrsAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.invTotDealNetAmt_A, convertAmtFormat(glblMsg.A.no(i).invTotDealNetAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_I, convertDateFormat(glblMsg.A.no(i).invDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_I, convertDateFormat(glblMsg.A.no(i).invDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ID, convertDateFormat(glblMsg.A.no(i).invDueDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CD, convertDateFormat(glblMsg.A.no(i).cltDsptDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_GD, convertDateFormat(glblMsg.A.no(i).glDt_A.getValue()));
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void downloadForAll(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        NFCL3050F00FMsg fMsg = new NFCL3050F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        fMsg.addExclusionItem("xxRadioBtn_A");
        fMsg.addExclusionItem("xxChkBox_A");

        // write header
        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
            ZYPEZDItemValueSetter.setValue(fMsg.dealCltDsptAmt_A, convertAmtFormat(glblMsg.A.no(i).dealCltDsptAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A, convertAmtFormat(glblMsg.A.no(i).dealRmngBalGrsAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.invTotDealNetAmt_A, convertAmtFormat(glblMsg.A.no(i).invTotDealNetAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_I, convertDateFormat(glblMsg.A.no(i).invDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_I, convertDateFormat(glblMsg.A.no(i).invDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ID, convertDateFormat(glblMsg.A.no(i).invDueDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CD, convertDateFormat(glblMsg.A.no(i).cltDsptDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_GD, convertDateFormat(glblMsg.A.no(i).glDt_A.getValue()));
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    // START 2016/03/09 T.Tsuchida [QC#5104,MOD]
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
    private static BigDecimal convertAmtFormat(BigDecimal data) {
        if (hasValue(data)) {
            data = data.setScale(2);
        }
        return data;
    }
    // END 2016/03/09 T.Tsuchida [QC#5104,MOD]

    /**
     * SaveSearch Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NFCL3050Scrn00_SaveSearch(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        return;
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NFCL3050Scrn00_DeleteSearch(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        return;
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NFCL3050Scrn00_OnChangeSavedSearchOption(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NFCL3050CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NFCL3050Scrn00_PageJump(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        NFCL3050CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NFCL3050CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_PageNext(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        NFCL3050CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NFCL3050CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_PagePrev(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        NFCL3050CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NFCL3050CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_Search(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        // START 2018/10/24 J.Kim [QC#28869,ADD]
        NFCL3050CommonLogic.outputSearchLog(bizMsg);
        // END 2018/10/24 J.Kim [QC#28869,ADD]
        // QC#26680
        NFCL3050CommonLogic.search(getGlobalCompanyCode(), bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_TBLColumnSort(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NFCL3050CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    
    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    //---- start 2016/03/23 QC#5770 move to common logic
    /*
    private void search(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NFCL3050Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NFCL3050CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
    */
    //---- end 2016/03/23
    
    /**
     * Select All Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_btnSelectAll(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        
    }
    
    /**
     * Unselect All Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3050Scrn00_btnUnslctAll(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        
    }
}
