/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0040;

import static business.blap.NWWL0040.constant.NWWL0040Constant.BIZ_AREA_TP_TBL_NM;
import static business.blap.NWWL0040.constant.NWWL0040Constant.BIZ_ID;
import static business.blap.NWWL0040.constant.NWWL0040Constant.CSV_EXT;
import static business.blap.NWWL0040.constant.NWWL0040Constant.CSV_LIMIT_COUNT;
import static business.blap.NWWL0040.constant.NWWL0040Constant.FETCH_SIZE;
import static business.blap.NWWL0040.constant.NWWL0040Constant.MAX_DT;
import static business.blap.NWWL0040.constant.NWWL0040Constant.NZZM0000E;
import static business.blap.NWWL0040.constant.NWWL0040Constant.NZZM0001W;
import static business.blap.NWWL0040.constant.NWWL0040Constant.SUB_AREA_TP_TBL_NM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWWL0040.common.NWWL0040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * NWWL0040BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0040CMsg bizMsg = (NWWL0040CMsg) cMsg;
            NWWL0040SMsg glblMsg = (NWWL0040SMsg) sMsg;

            if ("NWWL0040_INIT".equals(screenAplID)) {
                doProcess_NWWL0040_INIT(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWWL0040_INIT(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_OnChange_DistBizArea".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_OnChange_DistBizArea(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_OnChange_NotifBizArea".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_OnChange_NotifBizArea(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_Search".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWWL0040Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWWL0040Scrn00_TBLColumnSort(bizMsg, glblMsg);

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
    private void doProcess_NWWL0040_INIT(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(BIZ_AREA_TP_TBL_NM, bizMsg.ntfyBizAreaTpCd_P, bizMsg.ntfyBizAreaTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SUB_AREA_TP_TBL_NM, bizMsg.ntfySubAreaTpCd_DP, bizMsg.ntfySubAreaTpDescTxt_DP);
        ZYPCodeDataUtil.createPulldownList(SUB_AREA_TP_TBL_NM, bizMsg.ntfySubAreaTpCd_NP, bizMsg.ntfySubAreaTpDescTxt_NP);

        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListActvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrActvFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_CMN_Download(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        List<BigDecimal> hdrPkList = null;
        if (ZYPCommonFunc.hasValue(bizMsg.ntfyHdrNm) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfyHdrDescTxt) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_N) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfySubAreaTpCd_N) //
                || ZYPCommonFunc.hasValue(bizMsg.effFromDt_N) //
                || ZYPCommonFunc.hasValue(bizMsg.effThruDt_N) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfyHdrActvFlg) //
        ) {
            S21SsmEZDResult hdrSsmResult = NWWL0040Query.getInstance().getNtfyHdr(bizMsg);
            if (hdrSsmResult.isCodeNotFound()) {

                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();

                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
            hdrPkList = (List<BigDecimal>) hdrSsmResult.getResultObject();
        }

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWWL0040Query.getInstance().getClass());
            bizMsg.xxFileData_A.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID), CSV_EXT);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            if (hdrPkList != null && hdrPkList.size() > 0) {
                BigDecimal[] hdrPk = (BigDecimal[]) hdrPkList.toArray(new BigDecimal[0]);
                params.put("hdrPk", hdrPk);
            } else {
                params.put("hdrPk", null);
            }

            params.put("ntfyDistListNm", bizMsg.ntfyDistListNm.getValue());
            params.put("ntfyDistListDescTxt", bizMsg.ntfyDistListDescTxt.getValue());
            params.put("ntfyBizAreaTpCd", bizMsg.ntfyBizAreaTpCd_D.getValue());
            params.put("ntfySubAreaTpCd", bizMsg.ntfySubAreaTpCd_D.getValue());
            params.put("effFromDt", bizMsg.effFromDt_D.getValue());
            params.put("effThruDt", bizMsg.effThruDt_D.getValue());
            params.put("ntfyHdActvFlg", bizMsg.ntfyDistListActvFlg.getValue());
            params.put("rowNum", CSV_LIMIT_COUNT);
            params.put("maxDt", MAX_DT);
            params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

            stmtSelect = ssmLLClient.createPreparedStatement("searchDistList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                bizMsg.setMessageInfo(NZZM0000E, null);
                return;
            }

            NWWL0040CommonLogic.writeCsvFileContInfo(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * OnChange_DistBizArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_OnChange_DistBizArea(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {
        NWWL0040CommonLogic.createNtfySubAreaPulldownListForDist(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * OnChange_NotifBizArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_OnChange_NotifBizArea(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {
        NWWL0040CommonLogic.createNtfySubAreaPulldownListForNtfy(bizMsg, this.getGlobalCompanyCode());
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_PageJump(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWWL0040CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_PageNext(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWWL0040CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_PagePrev(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWWL0040CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_Search(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0040Scrn00_TBLColumnSort(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {
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

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NWWL0040CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        List<BigDecimal> hdrPkList = null;
        if (ZYPCommonFunc.hasValue(bizMsg.ntfyHdrNm) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfyHdrDescTxt) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_N) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfySubAreaTpCd_N) //
                || ZYPCommonFunc.hasValue(bizMsg.effFromDt_N) //
                || ZYPCommonFunc.hasValue(bizMsg.effThruDt_N) //
                || ZYPCommonFunc.hasValue(bizMsg.ntfyHdrActvFlg) //
        ) {
            S21SsmEZDResult hdrSsmResult = NWWL0040Query.getInstance().getNtfyHdr(bizMsg);
            if (hdrSsmResult.isCodeNotFound()) {

                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();

                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
            hdrPkList = (List<BigDecimal>) hdrSsmResult.getResultObject();
        }

        S21SsmEZDResult ssmResult = NWWL0040Query.getInstance().getDistList(bizMsg, glblMsg, hdrPkList);
        List<Map<String, String>> distListList = (List<Map<String, String>>) ssmResult.getResultObject();

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

            NWWL0040CommonLogic.setSeachResult(glblMsg, distListList);

            bizMsg.xxPageShowFromNum.setValue(1);
            NWWL0040CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

}
