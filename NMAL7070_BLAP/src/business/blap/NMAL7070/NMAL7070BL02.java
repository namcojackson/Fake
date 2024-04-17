/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7070;

import static business.blap.NMAL7070.constant.NMAL7070Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7070.constant.NMAL7070Constant.CSV_FILE_NM;
import static business.blap.NMAL7070.constant.NMAL7070Constant.CSV_HEADER;
import static business.blap.NMAL7070.constant.NMAL7070Constant.EVENT_SEARCH;
import static business.blap.NMAL7070.constant.NMAL7070Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL7070.constant.NMAL7070Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7070.constant.NMAL7070Constant.NZZM0000E;
import static business.blap.NMAL7070.constant.NMAL7070Constant.NZZM0001W;
import static business.blap.NMAL7070.constant.NMAL7070Constant.STS_ACTIVE;
import static business.blap.NMAL7070.constant.NMAL7070Constant.STS_DELETED;
import static business.blap.NMAL7070.constant.NMAL7070Constant.STS_INACTIVE;
import static business.blap.NMAL7070.constant.NMAL7070Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7070.common.NMAL7070CommonLogic;
import business.file.NMAL7070F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
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
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;
            NMAL7070SMsg glblMsg = (NMAL7070SMsg) sMsg;

            if ("NMAL7070_INIT".equals(screenAplID)) {
                doProcess_NMAL7070_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_Search(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_CMN_Download(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_OnChange_SavedSearchOption(bizMsg, glblMsg);
            } else if ("NMAL7070Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL7070Scrn00_TBLColumnSort(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NMAL7070_INIT(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_PLN_TP.class, bizMsg.splyAgmtPlnTpCd_P, bizMsg.splyAgmtPlnTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_DOC_TP.class, bizMsg.splyAgmtDocTpCd_P, bizMsg.splyAgmtDocTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SPLY_AGMT_PLN_STS.class, bizMsg.splyAgmtPlnStsCd_P, bizMsg.splyAgmtPlnStsDescTxt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnStsCd, SPLY_AGMT_PLN_STS.ACTIVE_ONLY);

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_P, bizMsg.lineBizTpDescTxt_P);
        NMAL7070CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
    }

    private void doProcess_NMAL7070Scrn00_Search(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        List<String> prcCatgCdList = null;
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNm) //
                || ZYPCommonFunc.hasValue(bizMsg.dsAcctNum) //
                || ZYPCommonFunc.hasValue(bizMsg.csmpNum) //
                || ZYPCommonFunc.hasValue(bizMsg.coaBrCd) //
                || ZYPCommonFunc.hasValue(bizMsg.lineBizTpCd)) {

            S21SsmEZDResult ssmResult = NMAL7070Query.getInstance().getPriceCategoryCode(bizMsg, glblMsg);
            if (ssmResult.isCodeNotFound()) {

                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();

                bizMsg.setMessageInfo(NZZM0000E);
                return;
            } else {
                prcCatgCdList = (List<String>) ssmResult.getResultObject();
            }
        }

        S21SsmEZDResult ssmResultSplyAgmtPln = NMAL7070Query.getInstance().getSupplyAgreementPlan(bizMsg, glblMsg, prcCatgCdList);

        if (ssmResultSplyAgmtPln.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResultSplyAgmtPln.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResultSplyAgmtPln.getQueryResultCount());
            }
            List<Map<?, ?>> resultList = (List<Map<?, ?>>) ssmResultSplyAgmtPln.getResultObject();
            NMAL7070CommonLogic.setSeachResult(resultList, glblMsg);
            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {

                NMAL7070_ASMsg glblLineMsg = glblMsg.A.no(j);

                if (j < bizMsg.A.length()) {

                    EZDMsg.copy(glblLineMsg, null, bizMsg.A.no(j), null);
                    bizMsg.A.setValidCount(j + 1);
                } else {
                    break;
                }
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());

            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);

        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7070Scrn00_PageJump(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL7070CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7070Scrn00_PageNext(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7070CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7070Scrn00_PagePrev(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7070CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
    }

    /**
     * CMN_Download Event
     * @param bizMsg NMAL7070CMsg
     * @param glblMsg NMAL7070SMsg
     */
    private void doProcess_NMAL7070Scrn00_CMN_Download(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {

        // search
        List<String> prcCatgCdList = null;
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNm) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNum) || ZYPCommonFunc.hasValue(bizMsg.csmpNum) || ZYPCommonFunc.hasValue(bizMsg.lineBizTpCd)) {
            S21SsmEZDResult ssmResult = NMAL7070Query.getInstance().getPriceCategoryCode(bizMsg, glblMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            } else {
                prcCatgCdList = (List<String>) ssmResult.getResultObject();
            }
        }

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7070Query.getInstance().getClass());

            // create csv file
            bizMsg.xxFileData_A.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENSION);

            Map<String, Object> queryParam = getSerchParam(bizMsg, glblMsg, prcCatgCdList);
            ps = ssmLLClient.createPreparedStatement("getSupplyAgreementPlan", queryParam, execParam);
            rs = ps.executeQuery();

            NMAL7070F00FMsg fMsg = new NMAL7070F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_A.getTempFilePath(), fMsg);
            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

            fMsg.addExclusionItem("xxRadioBtn_A");

            //write header
            csvOutFile.writeHeader(CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

            if (!rs.next()) {
                bizMsg.setMessageInfo(NZZM0000E, null);
                csvOutFile.close();
                return;
            }

            // write contents
            do {
                if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                    bizMsg.setMessageInfo(NZZM0001W, null);
                    break;
                }
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnPk_A, rs.getBigDecimal("SPLY_AGMT_PLN_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnNm_A, rs.getString("SPLY_AGMT_PLN_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnShortNm_A, rs.getString("SPLY_AGMT_PLN_SHORT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnDescTxt_A, rs.getString("SPLY_AGMT_PLN_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.annTermCapNum_A, rs.getBigDecimal("ANN_TERM_CAP_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnTpDescTxt_A, rs.getString("SPLY_AGMT_PLN_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtDocTpDescTxt_A, rs.getString("SPLY_AGMT_DOC_TP_NM"));
                String actvFlg = rs.getString("ACTV_FLG");
                String delFlg = rs.getString("DEL_FLG");
                String effFrom = rs.getString("EFF_FROM_DT");
                String effThru = rs.getString("EFF_THRU_DT");
                String slsDt = ZYPDateUtil.getSalesDate();
                if (effThru == null) {
                    effThru = "99991231";
                }

                if (ZYPConstant.FLG_ON_Y.equals(delFlg)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem8Txt_A, STS_DELETED);
                } else if (ZYPConstant.FLG_ON_Y.equals(actvFlg) //
                        && ZYPDateUtil.compare(effFrom, slsDt) <= 0 //
                        && ZYPDateUtil.compare(effThru, slsDt) >= 0) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem8Txt_A, STS_ACTIVE);
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem8Txt_A, STS_INACTIVE);
                }

                if (rs.getString("EFF_FROM_DT") != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FM, NMAL7070CommonLogic.formatDt(rs.getString("EFF_FROM_DT")));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FM, rs.getString("EFF_FROM_DT"));
                }

                if (rs.getString("EFF_THRU_DT") != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, NMAL7070CommonLogic.formatDt(rs.getString("EFF_THRU_DT")));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, rs.getString("EFF_THRU_DT"));
                }

                if (rs.getString("EZINTIME") != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CD, NMAL7070CommonLogic.formatDt(rs.getString("EZINTIME")));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CD, rs.getString("EZINTIME"));
                }

                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_CB, rs.getString("CREATED_BY"));

                if (rs.getString("EZUPTIME") != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_UD, NMAL7070CommonLogic.formatDt(rs.getString("EZUPTIME")));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_UD, rs.getString("EZUPTIME"));
                }

                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_UB, rs.getString("UPDATE_BY"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    /**
     * getSerchParam
     * @param bizMsg NMAL7070SMsg
     * @param glblMsg NMAL7070SMsg
     * @param prcCatgCdList List<String>
     * @return S21SsmEZDResult
     */
    private Map<String, Object> getSerchParam(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg, List<String> prcCatgCdList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("effFromDt", bizMsg.effFromDt.getValue());
        params.put("effThruDt", bizMsg.effThruDt.getValue());
        params.put("plnNm", bizMsg.splyAgmtPlnNm.getValue());
        params.put("plnShortNm", bizMsg.splyAgmtPlnShortNm.getValue());
        params.put("plnDescTxt", bizMsg.splyAgmtPlnDescTxt.getValue());
        params.put("plnTp", bizMsg.splyAgmtPlnTpCd.getValue());
        params.put("docTp", bizMsg.splyAgmtDocTpCd.getValue());
        params.put("plnSts", bizMsg.splyAgmtPlnStsCd.getValue());
        params.put("salesDt", ZYPDateUtil.getSalesDate());
        if (prcCatgCdList != null) {
            String[] prcCatgCd = (String[]) prcCatgCdList.toArray(new String[0]);
            params.put("prcCatgCd", prcCatgCd);
        } else {
            params.put("prcCatgCd", null);
        }
        params.put("rowNum", LIMIT_DL_ROWNUM);
        
        return params;
    }

    /**
     * OnChange SavedSearchOption.
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NMAL7070Scrn00_OnChange_SavedSearchOption(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            NMAL7070CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    private void doProcess_NMAL7070Scrn00_TBLColumnSort(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            if (bizMsg.A.getValidCount() > 0) {
                int i = 0;
                for (; i < glblMsg.A.getValidCount(); i++) {
                    if (i == bizMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                bizMsg.A.setValidCount(i);

                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            }
        }
    }

    private void doProcess_NMAL7070Scrn00_CMN_Clear(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        doProcess_NMAL7070_INIT(bizMsg, glblMsg);
    }

}
