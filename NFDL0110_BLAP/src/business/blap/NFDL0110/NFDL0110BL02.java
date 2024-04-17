/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0110;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0110.common.NFDL0110CommonLogic;
import business.blap.NFDL0110.constant.NFDL0110Constant;
import business.file.NFDL0110F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   CSAI            K.Lee           Create          Initial
 * 2016/04/27   Fujitsu         S.Fujita        Update          QC#7218
 * 2017/01/18   Fujitsu         S.Fujita        Update          QC#16813
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2023/02/13   Hitachi         E.Watabe        Update          QC#61092
 *</pre>
 */
public class NFDL0110BL02 extends S21BusinessHandler implements NFDL0110Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0110_INIT".equals(screenAplID)) {
                doProcess_NFDL0110_INIT(cMsg, sMsg);
                // START 2016/01/18 S.Fujita [QC#16813,MOD]
//                ZYPGUITableColumn.getColData(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0110CMsg) cMsg, (NFDL0110SMsg) sMsg);
                // END   2016/01/18 S.Fujita [QC#16813,MOD]

            } else if ("NFDL0110Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0110Scrn00_CMN_Download(cMsg, sMsg);

            } else if ("NFDL0110Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0110Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("NFDL0110Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0110Scrn00_PageNext(cMsg, sMsg);

            } else if ("NFDL0110Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0110Scrn00_PagePrev(cMsg, sMsg);

            } else if ("NFDL0110Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFDL0110Scrn00_PageJump(cMsg, sMsg);

                // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            } else if ("NFDL0110Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0110Scrn00_TBLColumnSort(cMsg, sMsg);
                // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

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
    private void doProcess_NFDL0110_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0110_INIT================================START", this);

        NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
        NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;

        String dsAcctNm = NFDL0110CommonLogic.getDsAccountCustName(getGlobalCompanyCode(), bizMsg.billToCustAcctCd_H.getValue());
        if (!ZYPCommonFunc.hasValue(dsAcctNm)) {
            bizMsg.setMessageInfo("NFDM0031E", new String[]{"Customer Account Name"});
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, dsAcctNm);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_H)) {
            String billToCustNm = NFDL0110CommonLogic.getBillToCustName(getGlobalCompanyCode(), bizMsg.billToCustCd_H.getValue());
            if (!ZYPCommonFunc.hasValue(billToCustNm)) {
                bizMsg.setMessageInfo("NFDM0031E", new String[]{"Bill To Customer Name"});
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.locNm_H, billToCustNm);
            }
        }
        // START 2023/02/13 E.Watabe[QC#61092, ADD]
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId("EXTNE193");
        if (funcIdList == null || funcIdList.isEmpty() || !funcIdList.contains("EXTNE193T010")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2023/02/13 E.Watabe[QC#61092, ADD]

        String clSystemBizId = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CL_SYSTEM_BIZ_ID, getGlobalCompanyCode());
        String clSystemParamNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CL_SYSTEM_PARAM_NM, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnScrCondValTxt_P0, clSystemBizId);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnScrCondValTxt_P1, clSystemParamNm);

        getCustomerCareList(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0110_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0110Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_CMN_Clear================================START", this);

        doProcess_NFDL0110_INIT(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_CMN_Clear================================END", this);
    }


    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getCustomerCareList(NFDL0110CMsg bizMsg, NFDL0110SMsg globalMsg) {

        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("lineTp01", LINE_TP_01);
        ssmMap.put("lineTp02", LINE_TP_02);
        ssmMap.put("lineTp03", LINE_TP_03);
        ssmMap.put("slsDt", slsDt);
        ssmMap.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmMap.put("arTrxTpCdList", new String[]{AR_TRX_TP.INVOICE, AR_TRX_TP.DEBIT_MEMO, AR_TRX_TP.DEDUCTION});
        // START 2018/10/05 J.Kim [QC#28570,MOD]
        // ssmMap.put("arCashApplyStsCdList", new String[]{AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED});
        ssmMap.put("arCashApplyStsCdList", new String[]{AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED, AR_CASH_APPLY_STS.APPLIED});
        // END 2018/10/05 J.Kim [QC#28570,MOD]
        ssmMap.put("billToCustAcctCd", bizMsg.billToCustAcctCd_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        // START 2018/10/11 J.Kim [QC#28570,ADD]
        ssmMap.put("stsClose", CI_TICKET_STS_CLOSE);
        // END 2018/10/11 J.Kim [QC#28570,ADD]
        S21SsmEZDResult ssmResult = NFDL0110Query.getInstance().getCustomerCareList(ssmMap, globalMsg);

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
            NFDL0110CommonLogic.dispPage(bizMsg, globalMsg);

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
    private void doProcess_NFDL0110Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
        NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Customer Care"), ".csv");
        String csvOutTempPath = bizMsg.xxFileData.getTempFilePath();

        NFDL0110F00FMsg fMsg = new NFDL0110F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);
        csvOutFile.writeHeader(HEADER_NAME.clone());

        ZYPEZDItemValueSetter.setValue(fMsg.billToCustAcctCd_H, bizMsg.billToCustAcctCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_H, bizMsg.dsAcctNm_H);
        ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_H, bizMsg.billToCustCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.locNm_H, bizMsg.locNm_H);
        
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A, globalMsg.A.no(i).billToCustCd_A);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDplyTrxNumTxt_A, globalMsg.A.no(i).xxDplyTrxNumTxt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxNum_A, globalMsg.A.no(i).arTrxNum_A);
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_A1, globalMsg.A.no(i).xxScrItem130Txt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A, globalMsg.A.no(i).billToCustCd_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).bllgPerFromDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).bllgPerFromDt_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).bllgPerToDt_A)) {
            	ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).bllgPerToDt_A.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt_A, globalMsg.A.no(i).dealOrigGrsAmt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A, globalMsg.A.no(i).dealRmngBalGrsAmt_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).invDueDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A3, ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).invDueDt_A.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.pmtLateDaysAot_A, globalMsg.A.no(i).pmtLateDaysAot_A);
            ZYPEZDItemValueSetter.setValue(fMsg.custCareTktNum_A, globalMsg.A.no(i).custCareTktNum_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxCratDt_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A4, ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).xxCratDt_A.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_A2, globalMsg.A.no(i).xxScrItem130Txt_A2);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0110Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_PageNext================================START", this);

        NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
        NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;

        NFDL0110CommonLogic.nextPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0110Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_PageNext================================START", this);

        NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
        NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;

        NFDL0110CommonLogic.prevPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0110Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_PageNext================================START", this);

        NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
        NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;
        NFDL0110CommonLogic.jumpPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0110Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0110Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
        NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowToNum_A.clear();
        bizMsg.xxPageShowOfNum_A.clear();

        S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

        EZDMsg.copy(globalMsg.A, null, bizMsg.A, null);
        bizMsg.xxPageShowFromNum_A.setValue(1);
        bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum_A.setValue(globalMsg.A.getValidCount());
    }

}
