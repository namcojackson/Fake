package business.blap.NFDL0100;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0100.common.NFDL0100CommonLogic;
import business.blap.NFDL0100.constant.NFDL0100Constant;
import business.file.NFDL0100F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2016/04/27   Fujitsu         S.Fujita        Update          QC#7218
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#11570
 * 2018/09/19   Hitachi         Y.Takeno        Update          QC#19578
 * 2019/07/26   Fujitsu         M.Ishii         Update          QC#51679
 * 2020/02/25   CITS            M.Furugoori     Update          QC#55489
 * 2022/08/03   CITS            D.Mamaril       Update          QC#60294
 * 2023/03/07   Hitachi         S.Fujita        Update          QC#61169
 * 2023/09/25   Hitachi         S.Fujita        Update          QC#61857
 *</pre>
 */
public class NFDL0100BL02 extends S21BusinessHandler implements NFDL0100Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0100Scrn00_CMN_ColSave".equals(screenAplID)) {

            } else if ("NFDL0100Scrn00_CMN_ColClear".equals(screenAplID)) {

            } else if ("NFDL0100_INIT".equals(screenAplID)) {
                doProcess_NFDL0100_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_Click_Btn_Refresh".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_Click_Btn_Refresh(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_Click_Btn_Email".equals(screenAplID)) {
            	doProcess_NFDL0100Scrn00_Click_Btn_Email(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_CMN_Download(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_PageNext(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_PagePrev(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_PageJump(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_Click_Btn_UnselectAll".equals(screenAplID)) {
            	doProcess_NFDL0100Scrn00_Click_Btn_UnselectAll(cMsg, sMsg);

            } else if ("NFDL0100Scrn00_Click_Btn_SelectAll".equals(screenAplID)) {
            	doProcess_NFDL0100Scrn00_Click_Btn_SelectAll(cMsg, sMsg);

            // START 2020/02/25 [QC#55489, ADD]
            } else if ("NFDL0100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0100Scrn00_TBLColumnSort(cMsg, sMsg);
            // END   2020/02/25 [QC#55489, ADD]

            } else if ("NFDL0100_NWCL0060".equals(screenAplID)) {

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100_INIT================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        String dsAcctNm = NFDL0100CommonLogic.getDsAccountCustName(getGlobalCompanyCode(), bizMsg.billToCustAcctCd_H.getValue());
        if (!ZYPCommonFunc.hasValue(dsAcctNm)) {
            bizMsg.setMessageInfo("NFDM0031E", new String[]{"Customer Account Name"});
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, dsAcctNm);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_H)) {
            String billToCustNm = NFDL0100CommonLogic.getBillToCustName(getGlobalCompanyCode(), bizMsg.billToCustCd_H.getValue());
            if (!ZYPCommonFunc.hasValue(billToCustNm)) {
                bizMsg.setMessageInfo("NFDM0031E", new String[]{"Bill To Customer Name"});
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.locNm_H, billToCustNm);
            }
        }

        getPrintInvoiceList(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0100_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_Click_Btn_Refresh(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_Click_Btn_Refresh================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        // START 2020/03/09 M.Furugoori [QC#55489, ADD]
        bizMsg.xxSortTblNm.clear();
        bizMsg.xxSortItemNm.clear();
        bizMsg.xxSortOrdByTxt.clear();
        // END 2020/03/09 M.Furugoori [QC#55489, ADD]

        doProcess_NFDL0100_INIT(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_Click_Btn_Refresh================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_CMN_Clear================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        bizMsg.clear();
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);

        doProcess_NFDL0100_INIT(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_CMN_Clear================================END", this);
    }


    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getPrintInvoiceList(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {

        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("docTp01", DOC_TP_01);
        ssmMap.put("docTp02", DOC_TP_02);
        ssmMap.put("docTp03", DOC_TP_03);
        ssmMap.put("docTp04", DOC_TP_04);
        ssmMap.put("arTrxTpInv", AR_TRX_TP.INVOICE);
        ssmMap.put("arTrxTpDem", AR_TRX_TP.DEBIT_MEMO);
        ssmMap.put("arTrxTpCrm", AR_TRX_TP.CREDIT_MEMO);
        // START 2023/03/07 S.Fujita [QC#61169, ADD]
        ssmMap.put("arTrxTpDed", AR_TRX_TP.DEDUCTION);
        // END 2023/03/07 S.Fujita [QC#61169, ADD]
        ssmMap.put("slsDt", slsDt);
        ssmMap.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        if (!bizMsg.xxChkBox_H1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            ssmMap.put("arCashApplyStsCdList", new String[]{AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED});
        }
        if (bizMsg.xxChkBox_H2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            // START 2023/03/07 S.Fujita [QC#61169, MOD]
            // ssmMap.put("arTrxTpCdList", new String[]{AR_TRX_TP.INVOICE, AR_TRX_TP.DEBIT_MEMO, AR_TRX_TP.CREDIT_MEMO});
            ssmMap.put("arTrxTpCdList", new String[]{AR_TRX_TP.INVOICE, AR_TRX_TP.DEBIT_MEMO, AR_TRX_TP.CREDIT_MEMO, AR_TRX_TP.DEDUCTION});
        } else {
            // ssmMap.put("arTrxTpCdList", new String[]{AR_TRX_TP.INVOICE, AR_TRX_TP.DEBIT_MEMO});
            ssmMap.put("arTrxTpCdList", new String[]{AR_TRX_TP.INVOICE, AR_TRX_TP.DEBIT_MEMO, AR_TRX_TP.DEDUCTION});
            // END 2023/03/07 S.Fujita [QC#61169, MOD]
        }
        ssmMap.put("cMsg", bizMsg);
        // 2019/07/26 QC#51679 Add Start
        ssmMap.put("ipcRecCdBill", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        // 2019/07/26 QC#51679 Add End
        S21SsmEZDResult ssmResult = NFDL0100Query.getInstance().getPrintInvoiceList(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

                queryResCnt = globalMsg.A.length();
            // START 2016/04/27 S.Fujita [QC#7218,MOD]
//            } else {
//
//                bizMsg.setMessageInfo("NZZM0002I");
            // END 2016/04/27 S.Fujita [QC#7218,MOD]
            }
            bizMsg.xxPageShowFromNum_A.setValue(1);
            NFDL0100CommonLogic.dispPage(bizMsg, globalMsg);

        } else {
            // START 2016/04/27 S.Fujita [QC#7218,MOD]
//            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.setMessageInfo("NFDM0034I");
            // END 2016/04/27 S.Fujita [QC#7218,MOD]
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(0);
        }
    }

    /**
     * CMN_Download
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NFDL0100Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Print_Invoice"), ".csv");
        String csvOutTempPath = bizMsg.xxFileData.getTempFilePath();

        NFDL0100F00FMsg fMsg = new NFDL0100F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);
        csvOutFile.writeHeader(HEADER_NAME.clone());

        ZYPEZDItemValueSetter.setValue(fMsg.billToCustAcctCd_H, bizMsg.billToCustAcctCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_H, bizMsg.dsAcctNm_H);
        ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_H, bizMsg.billToCustCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.locNm_H, bizMsg.locNm_H);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A, globalMsg.A.no(i).billToCustCd_A);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDplyTrxNumTxt_A, globalMsg.A.no(i).xxDplyTrxNumTxt_A);
            // START 2022/03/07 S.Fujita [QC#61169, MOD]
            // ZYPEZDItemValueSetter.setValue(fMsg.arTrxNum_A, globalMsg.A.no(i).arTrxNum_A);
            ZYPEZDItemValueSetter.setValue(fMsg.arCustRefNum_A, globalMsg.A.no(i).arCustRefNum_A);
            // END 2022/03/07 S.Fujita [QC#61169, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.grpInvNum_A, globalMsg.A.no(i).grpInvNum_A);
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_A, globalMsg.A.no(i).xxScrItem130Txt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt_A, globalMsg.A.no(i).dealOrigGrsAmt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A, globalMsg.A.no(i).dealRmngBalGrsAmt_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).arTrxDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).arTrxDt_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).invDueDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).invDueDt_A.getValue()));
            }
            // START 2022/08/03 D.Mamaril [QC#60294, MOD]
            //ZYPEZDItemValueSetter.setValue(fMsg.pmtLateDaysAot_A, globalMsg.A.no(i).pmtLateDaysAot_A);
            ZYPEZDItemValueSetter.setValue(fMsg.daysPastDueAot_A, globalMsg.A.no(i).daysPastDueAot_A);
            // START 2022/08/03 D.Mamaril [QC#60294, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum_A, globalMsg.A.no(i).custIssPoNum_A);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_PageNext================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        NFDL0100CommonLogic.copyPage(bizMsg, globalMsg);
        NFDL0100CommonLogic.nextPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_PageNext================================START", this);
        
        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        NFDL0100CommonLogic.copyPage(bizMsg, globalMsg);
        NFDL0100CommonLogic.prevPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_PageNext================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        NFDL0100CommonLogic.copyPage(bizMsg, globalMsg);
        NFDL0100CommonLogic.jumpPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_PageNext================================END", this);
    }

    /**
     * <pre>
     * OpenWin MailEntry Event
     * </pre>
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     */
    private void doProcess_NFDL0100Scrn00_Click_Btn_Email(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_OpenWin_MailEntry================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        NFDL0100CommonLogic.copyPage(bizMsg, globalMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (!NFDL0100CommonLogic.isAnyCheckBoxChecked(bizMsg, globalMsg, selectedRows)) {
            return;
        }

        if (!NFDL0100CommonLogic.checkSelectMaxCnt(getGlobalCompanyCode(), bizMsg, globalMsg, selectedRows)) {
            return;
        }

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            if (!NFDL0100CommonLogic.checkConsolidated(bizMsg, globalMsg, selectedRows, bizMsg.xxDplyCtrlFlg_EM)) {
                return;
            }
        }
        // START 2023/09/25 S.Fujita [QC#61857, ADD]
        Boolean chkCnslFlg = false;
        Boolean sendIndvdlFlg = false;
        // END 2023/09/25 S.Fujita [QC#61857, ADD]
        ZYPTableUtil.clear(bizMsg.B);
        int i = 0;
        // 2019/07/26 QC#51679 Add Start
        int j = 0;
        // 2019/07/26 QC#51679 Add End
        for (; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);
            // 2019/07/26 QC#51679 Mod Start
//            NFDL0100_BCMsg bCMsg = bizMsg.B.no(i);
            NFDL0100_BCMsg bCMsg = bizMsg.B.no(j);
            // 2019/07/26 QC#51679 Mod End
            // 2019/07/26 QC#51679 Add Start
            if (!NFDL0100CommonLogic.checkDupulicateInvBill(bizMsg, globalMsg, selectedRows, i)) {
                // START 2023/09/25 S.Fujita [QC#61857, MOD]
                // continue;
                if (!sendIndvdlFlg) {
                    continue;
                }
                // END 2023/09/25 S.Fujita [QC#61857, MOD]
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(checkedRowNum).eipRptRqstPk_A)) {
                continue;
            }
            // 2019/07/26 QC#51679 Add End
            ZYPEZDItemValueSetter.setValue(bCMsg.invEmlAddr_B, globalMsg.A.no(checkedRowNum).invEmlAddr_A.getValue());
            ZYPEZDItemValueSetter.setValue(bCMsg.xxFilePathTxt_B, NFDL0100CommonLogic.getFileName(globalMsg.A.no(checkedRowNum).invFileUrl_A.getValue()));
            ZYPEZDItemValueSetter.setValue(bCMsg.invFileUrl_B, globalMsg.A.no(checkedRowNum).invFileUrl_A.getValue());
            ZYPEZDItemValueSetter.setValue(bCMsg.eipRptRqstPk_B, globalMsg.A.no(checkedRowNum).eipRptRqstPk_A.getValue());
            // 2019/07/26 QC#51679 Mod Start
//            ZYPEZDItemValueSetter.setValue(bCMsg.invNum_B, globalMsg.A.no(checkedRowNum).arTrxNum_A.getValue());
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(checkedRowNum).grpInvNum_A)) {
                // START 2023/09/25 S.Fujita [QC#61857, MOD]
                if (!chkCnslFlg) {
                    sendIndvdlFlg = sendConsolidateOrIndividual(bizMsg);
                    chkCnslFlg = true;
                }
                if (sendIndvdlFlg) {
                    ZYPEZDItemValueSetter.setValue(bCMsg.arCustRefNum_B, globalMsg.A.no(checkedRowNum).arCustRefNum_A.getValue());
                    ZYPEZDItemValueSetter.setValue(bCMsg.eipRptRqstPk_B, globalMsg.A.no(checkedRowNum).eipRptRqstPk_AI.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(bCMsg.conslBillNum_B, globalMsg.A.no(checkedRowNum).grpInvNum_A.getValue());
                }
                // END 2023/09/25 S.Fujita [QC#61857, MOD]
            } else {
                // START 2022/03/07 S.Fujita [QC#61169, MOD]
                // ZYPEZDItemValueSetter.setValue(bCMsg.invNum_B, globalMsg.A.no(checkedRowNum).arTrxNum_A.getValue());
                ZYPEZDItemValueSetter.setValue(bCMsg.arCustRefNum_B, globalMsg.A.no(checkedRowNum).arCustRefNum_A.getValue());
                // END 2022/03/07 S.Fujita [QC#61169, MOD]
            }
            // 2019/07/26 QC#51679 Mod End

            // START 2018/09/19 [QC#19578, ADD]
            ZYPEZDItemValueSetter.setValue(bCMsg.billToDsAcctNm_B, globalMsg.A.no(checkedRowNum).billToDsAcctNm_A.getValue());
            ZYPEZDItemValueSetter.setValue(bCMsg.billToDsAcctNum_B, globalMsg.A.no(checkedRowNum).billToDsAcctNum_A.getValue());
            // END   2018/09/19 [QC#19578, ADD]
            // 2019/07/26 QC#51679 Add Start
            j++;
            // 2019/07/26 QC#51679 Add End
        }
        // 2019/07/26 QC#51679 Mod Start
//        bizMsg.B.setValidCount(i);
        bizMsg.B.setValidCount(j);
        // 2019/07/26 QC#51679 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSfxKeyTxt, "B");
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_OpenWin_MailEntry================================END", this);
    }

    // START 2023/09/25 S.Fujita [QC#61857, ADD]
    /**
     * @param cMsg Business Component Interface Message
     */
    private boolean sendConsolidateOrIndividual(EZDCMsg cMsg) {
        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
            return true;
        }
        return false;
    }
    // END 2023/09/25 S.Fujita [QC#61857, ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_Click_Btn_SelectAll(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_Click_Btn_SelectAll================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        NFDL0100CommonLogic.copyPage(bizMsg, globalMsg);

        // START 2016/08/01 K.Kojima [QC#11570,ADD]
        if (globalMsg.A.getValidCount() == 0) {
            return;
        }
        // END 2016/08/01 K.Kojima [QC#11570,ADD]

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }
        NFDL0100CommonLogic.dispPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_Click_Btn_SelectAll================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0100Scrn00_Click_Btn_UnselectAll(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_Click_Btn_UnselectAll================================START", this);

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        NFDL0100CommonLogic.copyPage(bizMsg, globalMsg);

        // START 2016/08/01 K.Kojima [QC#11570,ADD]
        if (globalMsg.A.getValidCount() == 0) {
            return;
        }
        // END 2016/08/01 K.Kojima [QC#11570,ADD]

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            globalMsg.A.no(i).xxChkBox_A.clear();
        }
        NFDL0100CommonLogic.dispPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0100Scrn00_Click_Btn_UnselectAll================================END", this);
    }

    // START 2020/02/25 M.Furugoori [QC#55489, ADD]
    /**
     * <pre>
     * table column sort
     * </pre>
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     */
    private void doProcess_NFDL0100Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            // Copy
            int i = 0;

            for (; i < globalMsg.A.getValidCount(); i++) {

                if (i == bizMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }

            bizMsg.A.setValidCount(i);

            // set Page
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
        }
    }
    // END 2020/02/25 M.Furugoori [QC#55489, ADD]
}
