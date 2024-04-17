package business.blap.NFCL0860;

import static business.blap.NFCL0860.constant.NFCL0860Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NFCL0860.constant.NFCL0860Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NFCL0860.constant.NFCL0860Constant.NFCM0041E;
import static business.blap.NFCL0860.constant.NFCL0860Constant.NZZM0001W;
import static business.blap.NFCL0860.constant.NFCL0860Constant.ZZZM9005W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL0860.common.NFCL0860CommonLogic;
import business.file.NFCL0860F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NFCL0860BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/14   Hitachi         T.Tsuchida      Update          QC#5431
 * 2021/11/11   CITS            G.Delgado       Update          QC#55788
 * 2022/01/08   CITS            K.Suzuki        Update          QC#55788-1
 * 2022/01/22   CITS            K.Suzuki        Update          QC#55788-2
 *</pre>
 */
public class NFCL0860BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL0860CMsg bizMsg = (NFCL0860CMsg) cMsg;
            NFCL0860SMsg glblMsg = (NFCL0860SMsg) sMsg;

            if ("NFCL0860_INIT".equals(screenAplID)) {
                doProcess_NFCL0860_INIT(bizMsg, glblMsg);

            } else if ("NFCL0860Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL0860Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NFCL0860Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL0860Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NFCL0860Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL0860Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NFCL0860Scrn00_Unapply".equals(screenAplID)) {
                doProcess_NFCL0860Scrn00_Unapply(bizMsg, glblMsg);

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
    private void doProcess_NFCL0860_INIT(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(bizMsg.invNum_H)) {
            bizMsg.setMessageInfo(NFCM0041E);
        } else {
            if (!searchHeader(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(ZZZM9005W);
                return;
            }
            // START 2022/01/08 K.Suzuki [QC#55788-1, ADD]
            searchReceiptFromOnAccount(bizMsg, glblMsg);
            // END   2022/01/08 K.Suzuki [QC#55788-1, ADD]
            if (!searchDetail(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(ZZZM9005W);
                return;
            }
        }
    }

    /**
     * UNAPPLY Event
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NFCL0860Scrn00_Unapply(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {
        // research
        doProcess_NFCL0860_INIT(bizMsg, glblMsg);
    }

    /**
     * Header search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     * @return boolean
     */
    private boolean searchHeader(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NFCL0860Query.getInstance().searchHeader(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_AB, glblMsg.billToCustAcctCd_AB);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_AB, glblMsg.billToCustCd_AB);
            ZYPEZDItemValueSetter.setValue(bizMsg.arTrxNum_AB, glblMsg.arTrxNum_AB);
            ZYPEZDItemValueSetter.setValue(bizMsg.arTrxBalPk_AB, glblMsg.arTrxBalPk_AB);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_AB, glblMsg.ezUpTime_AB);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_AB, glblMsg.ezUpTimeZone_AB);
            ZYPEZDItemValueSetter.setValue(bizMsg.dealCcyCd_AB, glblMsg.dealCcyCd_AB);
            // START 2021/11/11 G.Delgado [QC#55788, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.arTrxTpCd_AB, glblMsg.arTrxTpCd_AB);
            // END 2021/11/11 G.Delgado [QC#55788, ADD]
        }
        return true;
    }

    /**
     * Detail search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     * @return boolean
     */
    private boolean searchDetail(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        // START 2022/01/08 K.Suzuki [QC#55788-1, MOD]
        // START 2021/11/11 G.Delgado [QC#55788, MOD]
        // S21SsmEZDResult ssmResult = NFCL0860Query.getInstance().searchDetail(bizMsg, glblMsg);
        //S21SsmEZDResult ssmResult;
        //if (AR_TRX_TP.RECEIPT.equals(bizMsg.arTrxTpCd_AB.getValue())) {
        //    ssmResult = NFCL0860Query.getInstance().searchDetailForReceipt(bizMsg, glblMsg);
        //} else {
        //    ssmResult = NFCL0860Query.getInstance().searchDetail(bizMsg, glblMsg);
        //}
        // END 2021/11/11 G.Delgado [QC#55788, MOD]
        S21SsmEZDResult ssmResult = NFCL0860Query.getInstance().searchDetail(bizMsg, glblMsg);
        // END   2022/01/08 K.Suzuki [QC#55788-1, MOD]

        if (ssmResult.isCodeNotFound()) {
            return false;
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            NFCL0860CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
        return true;
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL0860Scrn00_CMN_Clear(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.clear();
        glblMsg.A.setValidCount(0);

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL0860Scrn00_CMN_Download(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), ".csv");

        NFCL0860F00FMsg fMsg = new NFCL0860F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        fMsg.addExclusionItem("xxChkBox_A");
        //write header
        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
        //write contents
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
            // START 2016/03/14 T.Tsuchida [QC#5431,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt_A, convertAmtFormat(glblMsg.A.no(i).dealOrigGrsAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.dealApplyAmt_A, convertAmtFormat(glblMsg.A.no(i).dealApplyAmt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AT, convertDateFormat(glblMsg.A.no(i).arTrxDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CA, convertDateFormat(glblMsg.A.no(i).cashAppDt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_GL, convertDateFormat(glblMsg.A.no(i).glDt_A.getValue()));
            // END 2016/03/14 T.Tsuchida [QC#5431,MOD]
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    // START 2016/03/14 T.Tsuchida [QC#5431,MOD]
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
    // END 2016/03/14 T.Tsuchida [QC#5431,MOD]

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL0860Scrn00_TBLColumnSort(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            NFCL0860CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    // START 2022/01/08 K.Suzuki [QC#55788-1, ADD]
    /**
     * searchReceiptFromOnAccount
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void searchReceiptFromOnAccount(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NFCL0860Query.getInstance().getReceiptNumFromOnAccount(bizMsg);
        if (ssmResult.isCodeNormal()) {
            // START 2022/01/22 K.Suzuki [QC#55788-2, MOD]
            //ZYPEZDItemValueSetter.setValue(bizMsg.invNum_H1, (String) ssmResult.getResultObject());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCustRefNum_H, (String) ssmResult.getResultObject());
            // END   2022/01/22 K.Suzuki [QC#55788-2, MOD]
        } else {
            // START 2022/01/22 K.Suzuki [QC#55788-2, MOD]
            //ZYPEZDItemValueSetter.setValue(bizMsg.invNum_H1, bizMsg.invNum_H);
            ZYPEZDItemValueSetter.setValue(bizMsg.arCustRefNum_H, bizMsg.invNum_H);
            // END   2022/01/22 K.Suzuki [QC#55788-2, MOD]
        }
    }
    // END   2022/01/08 K.Suzuki [QC#55788-1, ADD]
}
