package business.blap.NFCL3020;

import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3020.common.NFCL3020CommonLogic;
import business.blap.NFCL3020.constant.NFCL3020Constant;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NFCL3020F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 10/05/2015   Fujitsu         T.Tanaka        Update           DS_BANK,DS_BANK_BR,DS_BANK_ACCT
 * 02/10/2016   Fujitsu         T.Tanaka        Update          Def#3294
 * 09/05/2016   Fujitsu         C.Tanaka        Update          QC#5521
 * 2018/01/16   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/02/05   Fujitsu         H.Ikeda         Update          QC#22759(reOpen)
 * 2018/03/08   Fujitsu         H.Ikeda         Update          QC#24469
 * 2018/03/20   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/04/03   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 2018/05/09   Fujitsu         Y.Matsui        Update          QC#25856
 * 2018/07/23   Fujitsu         S.Ohki          Update          QC#25928
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3020BL02 extends S21BusinessHandler implements NFCL3020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3020_INIT".equals(screenAplID)) {
                doProcess_NFCL3020_INIT(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Reset(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_PageNext(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_PagePrev(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_Click_Add".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_Click_Add((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);

            } else if ("NFCL3020Scrn00_Click_Del".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_Click_Del((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);

            } else if ("NFCL3020Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Save(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_Select_Bank".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_Select_Bank(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_Select_BankBr".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_Select_BankBr(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_Select_ReceiptSource".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_Select_ReceiptSource(cMsg, sMsg);

            } else if ("NFCL3020_NWAL1130".equals(screenAplID)) {
                doProcess_NFCL3020_NWAL1130(cMsg, sMsg);

            } else if ("NFCL3020_NFCL3030".equals(screenAplID)) {
                doProcess_NFCL3020_NFCL3030(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_TAB_Receipts".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_TAB_Receipts(cMsg, sMsg);
             // QC#5521 ADD Start
                ZYPGUITableColumn.getColData((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg, "BHEAD");

            // START 2018/07/23 S.Ohki [QC#25928, DEL]
//            } else if ("NFCL3020Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
//                doProcess_NFCL3020Scrn00_OnChangeSavedSearchOption((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);
            // END   2018/07/23 S.Ohki [QC#25928, DEL]

            } else if ("NFCL3020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_TBLColumnSort((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);

            } else if ("NFCL3020Scrn00_CustomerName".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CustomerName(cMsg, sMsg);

            // START 2018/03/16 H.Ikeda [QC#21737,ADD]
            } else if ("NFCL3020Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Download(cMsg, sMsg);
            // END   2018/03/16 H.Ikeda [QC#21737,ADD]

            // QC#5521 ADD End
            } else {
                // QC#5521 MOD Start
                //throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
                return;
                // QC#5521 MOD End
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2018/03/16 H.Ikeda [QC#21737,ADD]
    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3020Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Download================================START", this);

        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), ".csv");

        NFCL3020F00FMsg fMsg = new NFCL3020F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        fMsg.addExclusionItem("xxChkBox_B");

        // write header
        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.B.no(i), null, fMsg, null);
            ZYPEZDItemValueSetter.setValue(fMsg.rcptNum_B,       globalMsg.B.no(i).rcptNum_B);
            ZYPEZDItemValueSetter.setValue(fMsg.rcptChkNum_B,    globalMsg.B.no(i).rcptChkNum_B);
            ZYPEZDItemValueSetter.setValue(fMsg.arRcptTrxTpNm_B, globalMsg.B.no(i).arRcptTrxTpNm_B);
            ZYPEZDItemValueSetter.setValue(fMsg.rcptDt_B,        globalMsg.B.no(i).rcptDt_B);
            ZYPEZDItemValueSetter.setValue(fMsg.funcRcptAmt_B,   globalMsg.B.no(i).funcRcptAmt_B);
            ZYPEZDItemValueSetter.setValue(fMsg.payerCustCd_B,   globalMsg.B.no(i).payerCustCd_B);
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_B,      globalMsg.B.no(i).dsAcctNm_B);
            ZYPEZDItemValueSetter.setValue(fMsg.locNum_B,        globalMsg.B.no(i).locNum_B);
            ZYPEZDItemValueSetter.setValue(fMsg.arRcptStsNm_B,   globalMsg.B.no(i).arRcptStsNm_B);
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxNum_B,      globalMsg.B.no(i).arTrxNum_B);
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm_B,     globalMsg.B.no(i).arTrxTpNm_B);
            ZYPEZDItemValueSetter.setValue(fMsg.funcApplyAmt_B,  globalMsg.B.no(i).funcApplyAmt_B);

            csvOutFile.write();
        }

        csvOutFile.close();
        
    }
    // END   2018/03/16 H.Ikeda [QC#21737,ADD]

    // START 2018/01/16 H.Ikeda [QC#22759, ADD]
    /**
     * doProcess_NFCL3020Scrn00_CustomerName
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3020Scrn00_CustomerName(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;

        if(!ZYPCommonFunc.hasValue(bizMsg.payerCustCd_BH.getValue())) {
            return;
        }
        NFCL3020CommonLogic.searchAddressForBillToCustomerAccount(bizMsg);
    }
    // END  2018/01/16 H.Ikeda [QC#22759, ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020_INIT================================START", this);

        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        String salesDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        bizMsg.dsBankAcctTpCd.setValue(DS_BANK_ACCT_TP.INTERNAL);

        NFCL3020CommonLogic.clearScreen(cMsg, sMsg);

        ZYPCodeDataUtil.createPulldownList(AR_LOCK_BOX.class, bizMsg.arLockBoxCd_LC, bizMsg.arLockBoxNm_LD);
        NFCL3020CommonLogic.createPulldownListArRcptTrxTp(bizMsg.glblCmpyCd.getValue(), bizMsg);
        // START 2018/04/03 H.Ikeda [QC#21737-1, MOD]
        String arRcptManCrftFlg = ZYPConstant.FLG_ON_Y;
        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)) {
            arRcptManCrftFlg = ZYPConstant.FLG_OFF_N;
        }
        NFCL3020CommonLogic.createPulldownListArRcptSrc(bizMsg.glblCmpyCd.getValue(), bizMsg, arRcptManCrftFlg);
        // END    2018/04/03 H.Ikeda [QC#21737-1, MOD]
        NFCL3020CommonLogic.createPulldownListArBatRcptSts(bizMsg.glblCmpyCd.getValue(), bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)) {
            getBatRcptList(bizMsg, globalMsg);
            getRcptList(bizMsg, globalMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.rcptDt_BH, salesDt);
            // START 2018/03/08 H.Ikeda [QC#24469, MOD]
         //   NFCL3020CommonLogic.reCalc(bizMsg, globalMsg);
            NFCL3020CommonLogic.reCalc(bizMsg, globalMsg, TYPE_INIT, BigDecimal.ZERO, BigDecimal.ZERO);
            // END   2018/03/08 H.Ikeda [QC#24469, MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptDt_H, salesDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptDt_BH, salesDt);
        }

        // QC#5521 ADD Start
        NFCL3020CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        // QC#5521 ADD End

        EZDDebugOutput.println(1, "doProcess_NFCL3020_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Clear================================START", this);

        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        bizMsg.arRcptSrcCd_H.clear();
        bizMsg.arBatRcptCnt_H.clear();
        bizMsg.arBatRcptNm_H.clear();
        bizMsg.arBatRcptAmt_H.clear();
        NFCL3020CommonLogic.clearScreen(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptDt_H, bizMsg.procDt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rcptDt_BH, bizMsg.procDt.getValue());

        // START 2018/01/16 H.Ikeda [QC#22759, MOD]
        bizMsg.payerCustCd_BH.clear();
        bizMsg.dsAcctNm_BH.clear();
        bizMsg.locNum_BH.clear();
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        bizMsg.invNum_BH.clear();
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.arRcptTrxTpCd_BH, "00");
        // END   2018/01/16 H.Ikeda [QC#22759, MOD]
        // START 2018/04/05 H.Ikeda [QC#21737-1, ADD]
        NFCL3020CommonLogic.createPulldownListArRcptSrc(bizMsg.glblCmpyCd.getValue(), bizMsg, ZYPConstant.FLG_ON_Y);
        // START 2018/04/05 H.Ikeda [QC#21737-1, ADD]
        
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Clear================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        doProcess_NFCL3020_INIT(bizMsg, globalMsg);
    }

    
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_CMN_Save(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Save================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        doProcess_NFCL3020_INIT(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Save================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Save================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        doProcess_NFCL3020_INIT(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Save================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_PageNext================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        NFCL3020CommonLogic.copyPage(bizMsg, bizMsg.B, globalMsg.B);
        NFCL3020CommonLogic.nextPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_PageNext================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        NFCL3020CommonLogic.copyPage(bizMsg, bizMsg.B, globalMsg.B);
        NFCL3020CommonLogic.prevPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_Click_Add(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Click_Add================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        NFCL3020CommonLogic.copyPage(bizMsg, bizMsg.B, globalMsg.B);

        String salesDt = ZYPDateUtil.getSalesDate();

        // Def3269
        if(!NFCL3020CommonLogic.checkReceiptDate(bizMsg)) {
            return;
        }
        
        if (bizMsg.rcptDt_BH.getValue().compareTo(salesDt) > 0) {
            
            bizMsg.rcptDt_BH.setErrorInfo(1, "NFCM0040E", new String[] {});
            return;
//        } else if (!ZYPCommonFunc.hasValue(bizMsg.arBatRcptDt_H) || bizMsg.rcptDt_BH.getValue().compareTo(bizMsg.arBatRcptDt_H.getValue()) > 0) {
//            bizMsg.rcptDt_BH.setErrorInfo(1, "NFCM0776E", new String[] {"Batch Date", "Receipt Date"});
//            return;
        }
        if (bizMsg.arBatRcptDt_H.getValue().compareTo(salesDt) > 0) {
            bizMsg.arBatRcptDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
            return;
        }

        
        
        int no = globalMsg.B.getValidCount();

        if (no == globalMsg.B.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(globalMsg.B.length()) });
            return;
        }

        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).xxChkBox_B, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).rcptChkNum_B, bizMsg.rcptChkNum_BH);
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).arRcptTrxTpCd_B, bizMsg.arRcptTrxTpCd_BH);
        String arRcptTrxTpNm = ZYPCodeDataUtil.getName(AR_RCPT_TRX_TP.class, getGlobalCompanyCode(), bizMsg.arRcptTrxTpCd_BH.getValue());
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).arRcptTrxTpNm_B, arRcptTrxTpNm);
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).rcptDt_B, bizMsg.rcptDt_BH);
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).funcRcptAmt_B, bizMsg.funcRcptAmt_BH);

        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).remDsBankAcctPk_B, bizMsg.remDsBankAcctPk_H);
        
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).arCashApplyStsCd_B, AR_CASH_APPLY_STS.UNIDENTIFIED);
        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).ajeCratCpltFlg_B, ZYPConstant.FLG_OFF_N);

        // START 2018/05/09 Y.Matsui [QC#25856, MOD]
//        // START 2018/01/16 H.Ikeda [QC#22759, ADD]
//        SELL_TO_CUSTTMsgArray outSellToCustTMsg = NFCL3020Query.findBillToAcctCust(bizMsg);
//        if (outSellToCustTMsg.getValidCount() != 0) {
//            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).payerCustCd_B, bizMsg.payerCustCd_BH);
//            if (!bizMsg.dsAcctNm_BH.getValue().equals(outSellToCustTMsg.no(0).dsAcctNm.getValue())) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_BH, outSellToCustTMsg.no(0).dsAcctNm.getValue());
//            }
//        } else {
//              // START 2018/02/05 H.Ikeda [QC#22759, DEL]
//    //        bizMsg.payerCustCd_BH.setErrorInfo(1, "NFCM0029E", new String[] {});
//    //        bizMsg.dsAcctNm_BH.clear();
//    //        return;
//              // END   2018/02/05 H.Ikeda [QC#22759, DEL]
//        }
//        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).payerCustCd_B, bizMsg.payerCustCd_BH);
//        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_BH)) {
//            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).dsAcctNm_B, bizMsg.dsAcctNm_BH);
//        } else {
//            if (outSellToCustTMsg.getValidCount() != 0) {
//                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).dsAcctNm_B, outSellToCustTMsg.no(0).dsAcctNm.getValue());
//            }
//        }
//        ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).locNum_B, bizMsg.locNum_BH);
//        // END   2018/01/16 H.Ikeda [QC#22759, ADD]

        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_BH) || ZYPCommonFunc.hasValue(bizMsg.locNum_BH)) {
            if (!NFCL3020CommonLogic.getCustomer(bizMsg)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).payerCustCd_B, bizMsg.payerCustCd_BH);
            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).dsAcctNm_B, bizMsg.dsAcctNm_BH);
            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).locNum_B, bizMsg.locNum_BH);
        }
        // END   2018/05/09 Y.Matsui [QC#25856, MOD]

        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.invNum_BH)) {
            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(no).arTrxNum_B, bizMsg.invNum_BH);
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
        globalMsg.B.setValidCount(no + 1);
        bizMsg.rcptChkNum_BH.clear();

        bizMsg.payerCustCd_BH.clear();
        bizMsg.dsAcctNm_BH.clear();
        bizMsg.locNum_BH.clear();
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        bizMsg.invNum_BH.clear();
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]

        // START 2018/01/16 H.Ikeda [QC#22759, MOD]
        //bizMsg.arRcptTrxTpCd_BH.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.arRcptTrxTpCd_BH, "00");
        // END   2018/01/16 H.Ikeda [QC#22759, MOD]

        // START 2018/03/08 H.Ikeda [QC#24469, MOD]
        NFCL3020CommonLogic.reCalc(bizMsg, globalMsg, TYPE_ADD, bizMsg.funcRcptAmt_BH.getValue(), BigDecimal.ONE);
        bizMsg.funcRcptAmt_BH.clear();
        //NFCL3020CommonLogic.reCalc(bizMsg, globalMsg);
        // START 2018/03/08 H.Ikeda [QC#24469, MOD]
        NFCL3020CommonLogic.lastPage(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Click_Add================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_Click_Del(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Click_Del================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        NFCL3020SMsg tmpMsg = new NFCL3020SMsg();
        EZDMsg.copy(globalMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(globalMsg.B);
        // START 2018/03/08 H.Ikeda [QC#24469, ADD]
        BigDecimal delAmt = BigDecimal.ZERO;
        int delCnt = 0;
        // END   2018/03/08 H.Ikeda [QC#24469, ADD]

        int j = 0;
        for (int i = 0; i < tmpMsg.B.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.B.no(i).xxChkBox_B.getValue())) {
                EZDMsg.copy(tmpMsg.B.no(i), null, globalMsg.B.no(j), null);
                j++;
            }
            // START 2018/03/08 H.Ikeda [QC#24469, ADD]
            else {
                delAmt = delAmt.add(tmpMsg.B.no(i).funcRcptAmt_B.getValue());
                delCnt++;
            }
            // END   2018/03/08 H.Ikeda [QC#24469, ADD]
        }
        globalMsg.B.setValidCount(j);
        // START 2018/03/08 H.Ikeda [QC#24469, MOD]
        //NFCL3020CommonLogic.reCalc(bizMsg, globalMsg);
        NFCL3020CommonLogic.reCalc(bizMsg, globalMsg, TYPE_DEL, delAmt, new BigDecimal(delCnt));
        // END   2018/03/08 H.Ikeda [QC#24469, MOD]
        BigDecimal lastPageFromNum = NFCL3020CommonLogic.getLastPageFromNum(bizMsg, globalMsg);
        if (bizMsg.xxPageShowFromNum_B.getValue().compareTo(lastPageFromNum) < 0) {
            NFCL3020CommonLogic.dispPage(bizMsg, bizMsg.B, globalMsg.B);
        } else {
            NFCL3020CommonLogic.lastPage(bizMsg, globalMsg);
        }
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Click_Del================================END", this);
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getBatRcptList(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("arCashApplySts_Void", AR_CASH_APPLY_STS.VOID);
        ssmMap.put("arCashApplySts_Applied", AR_CASH_APPLY_STS.APPLIED);
        ssmMap.put("arCashApplySts_UnApplied", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("arCashApplySts_Unidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
        ssmMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arAdjTrxTp_Adjustment", AR_ADJ_TRX_TP.ADJUSTMENT);
        ssmMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        // START 2018/04/04 H.Ikeda [QC#21737-1,DEL]
        //ssmMap.put("arAdjTrxTp_ArCashRefund", AR_ADJ_TP.A_OR_R_CASH_REFUND);
        // END   2018/04/04 H.Ikeda [QC#21737-1,DEL]
        // START 2018/04/04 H.Ikeda [QC#21737-1,ADD]
        ssmMap.put("arCashApplySts_Partial", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("arApplyTp_Refund", AR_APPLY_TP.REFUND);
        ssmMap.put("arAdjTrxTp_Refund", AR_ADJ_TRX_TP.REFUND);
        // END   2018/04/04 H.Ikeda [QC#21737-1,ADD]
        S21SsmEZDResult ssmResult = NFCL3020Query.getInstance().getBatRcptList(bizMsg, ssmMap);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo("NZZM0000E");
        }
        
        /* 2015/10/05 Set DS_BANK,DS_BANK_BR,DS_BANK_ACCT */
//        NFCL3020CommonLogic.createPulldownListBankBr(getGlobalCompanyCode(), bizMsg);
//        NFCL3020CommonLogic.createPulldownListBankAccount(getGlobalCompanyCode(), bizMsg);

    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getRcptList(NFCL3020CMsg bizMsg, NFCL3020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("arCashApplySts_Unidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
        ssmMap.put("Asterisk", ASTERISK);
        ssmMap.put("rowNum", String.valueOf(globalMsg.B.length()));
        S21SsmEZDResult ssmResult = NFCL3020Query.getInstance().getRcptList(globalMsg, ssmMap);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.B.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

                queryResCnt = globalMsg.B.length();
            }

            int i;
            for (i = 0; i < globalMsg.B.getValidCount(); i++) {

                if (i < bizMsg.B.length()) {

                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.B.setValidCount(i);

            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            bizMsg.B.setValidCount(0);
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(0);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_Select_Bank(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Select_Bank================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        bizMsg.bankRteNum_H.clear();
        bizMsg.remDsBankAcctPk_H.clear();
        NFCL3020CommonLogic.createPulldownListBankBr(getGlobalCompanyCode(), bizMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Select_Bank================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_Select_BankBr(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Select_BankBr================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        bizMsg.remDsBankAcctPk_H.clear();
        NFCL3020CommonLogic.createPulldownListBankAccount(getGlobalCompanyCode(), bizMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Select_BankBr================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_Select_ReceiptSource(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_Select_ReceiptSource================================START", this);
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_Select_ReceiptSource================================END", this);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3020_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3020_NFCL3030(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        doProcess_NFCL3020_INIT(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3020Scrn00_TAB_Receipts(EZDCMsg cMsg, EZDSMsg sMsg) {
    }

    // START 2018/07/23 S.Ohki [QC#25928, DEL]
    // QC#5521 ADD Start
//    private void doProcess_NFCL3020Scrn00_OnChangeSavedSearchOption(NFCL3020CMsg bizMsg, NFCL3020SMsg glblMsg) {
//        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H)) {
//            return;
//        }
//
//        NSZC033001PMsg pMsg = new NSZC033001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
//
//        if (!NFCL3020CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.arRcptSrcCd_H, pMsg.srchOptTxt_01);
//        ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptNm_H, pMsg.srchOptTxt_03);
//        ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptStsCd_H, pMsg.srchOptTxt_06);
//
//        if (isNumberCheck(pMsg.srchOptTxt_02.getValue())) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptCnt_H, new BigDecimal(pMsg.srchOptTxt_02.getValue()));
//        } else {
//            bizMsg.arBatRcptCnt_H.clear();
//        }
//        if (isNumberCheck(pMsg.srchOptTxt_04.getValue())) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptAmt_H, new BigDecimal(pMsg.srchOptTxt_04.getValue()));
//        } else {
//            bizMsg.arBatRcptAmt_H.clear();
//        }
//        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_05.getValue(), YYYYMMDD)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptDt_H, pMsg.srchOptTxt_05.getValue());
//        } else {
//            bizMsg.arBatRcptDt_H.clear();
//        }
//    }
    // END   2018/07/23 S.Ohki [QC#25928, DEL]

    private void doProcess_NFCL3020Scrn00_TBLColumnSort(NFCL3020CMsg bizMsg, NFCL3020SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("B".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.B, glblMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.B.getValidCount());

            bizMsg.xxPageShowFromNum_B.setValue(1);
            NFCL3020CommonLogic.firstPage(bizMsg, glblMsg);
        }
    }
    // QC#5521 ADD End
}
