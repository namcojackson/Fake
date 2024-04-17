/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0100;

import static business.blap.NWCL0100.constant.NWCL0100Constant.BIZ_ID;
import static business.blap.NWCL0100.constant.NWCL0100Constant.CSV_EXT;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_CURRENT;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_FUTURE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_NEW;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_PAST;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_SALESDATE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.FETCH_SIZE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.LINK_NAME_BILL_TO_ACCT;
import static business.blap.NWCL0100.constant.NWCL0100Constant.LINK_NAME_LIST_BILL_TO_ACCT_CD;
import static business.blap.NWCL0100.constant.NWCL0100Constant.LINK_NAME_LIST_ACCT_GRP;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0003I;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0124E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0125E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0127E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0130W;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0134E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NZZM0001W;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_SALES_DATE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_VALID_TO;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZZM9001E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWCL0100.common.NWCL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWCL0100BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2017/11/14   Fujitsu         Mz.Takahashi    Update          Sol#265(QC#18788)
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWCL0100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0100CMsg bizMsg = (NWCL0100CMsg) cMsg;
            NWCL0100SMsg glblMsg = (NWCL0100SMsg) sMsg;

            if ("NWCL0100_INIT".equals(screenAplID)) {
                doProcess_NWCL0100_INIT(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_CMN_Download(bizMsg, glblMsg);

            // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//            } else if ("NWCL0100Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWCL0100Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWCL0100Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_CMN_Clear(bizMsg, glblMsg);
            // END 2022/04/18 J.Evangelista [QC#59934,MOD]

            } else if ("NWCL0100Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_DeleteLine(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_InsertLine".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_InsertLine(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_Search".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_Search(bizMsg, glblMsg);

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
    private void doProcess_NWCL0100_INIT(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        glblMsg.clear();
        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        glblMsg.B.clear();
        glblMsg.B.setValidCount(0);

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.billToAcctNum_LK, LINK_NAME_BILL_TO_ACCT);

        // Setup Select Box
        NWCL0100CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_TBLColumnSort(NWCL0100CMsg cMsg, NWCL0100SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // （SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_CMN_Download(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWCL0100Query.getInstance().getClass());
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID), CSV_EXT);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("rowNum", glblMsg.A.length() + 1);
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("prcGrpTrgttpCd", PRC_GRP_TRGT_TP.CUSTOMER);
            ssmParam.put("prcGrpOpCd", PRC_GRP_OP.EQ);
            ssmParam.put("prcGrpTrgtAttrbCd", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
            ssmParam.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
            ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
            // 2017/11/14 Sol#265(QC#18788) Add Start
            ssmParam.put("prcGrpTrxTpCd", PRC_GRP_TRX_TP.SUPPLIES);
            // 2017/11/14 Sol#265(QC#18788) Add End

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H0.getValue())) {
                ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            }

            List<String> billToCustCdList = new ArrayList<String>();
            String[] billToCdList = bizMsg.xxBillToAcctCdSrchTxt_H0.getValue().split(",", 0);
            for (int i = 0; i < billToCdList.length; i++) {
                if (ZYPCommonFunc.hasValue(billToCdList[i])) {
                    billToCustCdList.add(billToCdList[i]);
                }
            }

            if (billToCustCdList.size() > 0) {
                ssmParam.put("billToCustCdList", billToCustCdList);
            }

            stmtSelect = ssmLLClient.createPreparedStatement("getContractList", ssmParam, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                bizMsg.setMessageInfo(ZZZM9001E, null);
                return;
            }

            NWCL0100CommonLogic.writeCsvFile(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//    private void doProcess_NWCL0100Scrn00_CMN_Reset(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
    private void doProcess_NWCL0100Scrn00_CMN_Clear(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
    // END 2022/04/18 J.Evangelista [QC#59934,MOD]

        doProcess_NWCL0100_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_CMN_Submit(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * DeleteLine Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_DeleteLine(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        NWCL0100CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        bizMsg.setCommitSMsg(true);

        final List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A0", ZYPConstant.FLG_ON_Y);

        if (selectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NWCM0127E);
            return;
        }

        // Confirm Invoice Print Control Update
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            bizMsg.setMessageInfo(NWCM0130W);
            return;
        }

        for (int idxA : selectedRows) {
            BigDecimal splyPgmContrPk = glblMsg.A.no(idxA).splyPgmContrPk_A0.getValue();
            if (!ZYPCommonFunc.hasValue(splyPgmContrPk)) {
                continue;
            }

            // Delete Check
            String frmDt = glblMsg.A.no(idxA).effFromDt_A0.getValue();
            String toDt = glblMsg.A.no(idxA).effThruDt_A0.getValue();
            String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

            if (ZYPCommonFunc.hasValue(toDt) && slsDt.compareTo(toDt) > 0) {

                glblMsg.A.no(idxA).effThruDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_TO, TEXT_SALES_DATE });
            }

            if (slsDt.compareTo(frmDt) >= 0 && (!ZYPCommonFunc.hasValue(toDt) || slsDt.compareTo(toDt) <= 0)) {

                glblMsg.A.no(idxA).effThruDt_A0.setErrorInfo(1, NWCM0125E);
                glblMsg.A.no(idxA).effFromDt_A0.setErrorInfo(1, NWCM0125E);
            }

            for (int idxB = 0; idxB < glblMsg.B.getValidCount(); idxB++) {
                if (splyPgmContrPk.compareTo(glblMsg.B.no(idxB).splyPgmContrPk_B0.getValue()) == 0) {
                    glblMsg.B.no(idxB).xxPgFlg_B0.setValue(ZYPConstant.FLG_ON_Y);
                    break;
                }
            }
        }

        if (!NWCL0100CommonLogic.showFirstErrorPage(bizMsg, glblMsg)) {
            return;
        }

        ZYPTableUtil.deleteRows(glblMsg.A, selectedRows);

        // copy data from glblMsg onto bizMsg
        int idx = glblMsg.A.getValidCount() - 1;
        int errPageShowFrom = (int) Math.ceil((double) (idx + 1) / bizMsg.A.length());
        bizMsg.xxPageShowFromNum.setValue((errPageShowFrom - 1) * bizMsg.A.length() + 1);
        NWCL0100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_DeleteSearch(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        return;
    }

    /**
     * InsertLine Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_InsertLine(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        NWCL0100CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        int idx = glblMsg.A.getValidCount();
        if (idx < glblMsg.A.length()) {
            glblMsg.A.setValidCount(idx + 1);
        } else {
            bizMsg.setMessageInfo(NWCM0134E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).xxLinkAncr_A0, LINK_NAME_LIST_BILL_TO_ACCT_CD);
        // 2017/11/14 Sol#265(QC#18788) Add Start
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).xxLinkProt_A0, LINK_NAME_LIST_ACCT_GRP);
        // 2017/11/14 Sol#265(QC#18788) Add Start
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dplyCtrlCd_A0, DISP_CTRL_CD_NEW);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).effFromDt_A0, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        int errPageShowFrom = (int) Math.ceil((double) (idx + 1) / bizMsg.A.length());
        bizMsg.xxPageShowFromNum.setValue((errPageShowFrom - 1) * bizMsg.A.length() + 1);
        NWCL0100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_OnChangeSavedSearchOption(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S0)) {
            NWCL0100CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_PageNext(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        NWCL0100CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWCL0100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_PagePrev(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        NWCL0100CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWCL0100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_SaveSearch(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        return;
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_Search(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        bizMsg.xxWrnSkipFlg.clear();
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        glblMsg.B.clear();
        glblMsg.B.setValidCount(0);

        S21SsmEZDResult ssmResult = NWCL0100Query.getInstance().getContractList(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NWCM0003I);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                String fromDt = glblMsg.A.no(i).effFromDt_A0.getValue();
                String toDt = glblMsg.A.no(i).effThruDt_A0.getValue();
                String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

                if (ZYPCommonFunc.hasValue(toDt) && slsDt.compareTo(toDt) > 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dplyCtrlCd_A0, DISP_CTRL_CD_PAST);
                } else if (slsDt.compareTo(fromDt) > 0 && (!ZYPCommonFunc.hasValue(toDt) || slsDt.compareTo(toDt) <= 0)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dplyCtrlCd_A0, DISP_CTRL_CD_CURRENT);
                } else if (slsDt.compareTo(fromDt) == 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dplyCtrlCd_A0, DISP_CTRL_CD_SALESDATE);
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dplyCtrlCd_A0, DISP_CTRL_CD_FUTURE);
                }
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NWCL0100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }

        EZDMsg.copy(glblMsg.A, "A0", glblMsg.B, "B0");
    }

}
