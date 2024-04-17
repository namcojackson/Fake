/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0010;

import static business.blap.NWWL0010.constant.NWWL0010Constant.BIZ_ID;
import static business.blap.NWWL0010.constant.NWWL0010Constant.CSV_EXT;
import static business.blap.NWWL0010.constant.NWWL0010Constant.CSV_LIMIT_COUNT;
import static business.blap.NWWL0010.constant.NWWL0010Constant.FETCH_SIZE;
import static business.blap.NWWL0010.constant.NWWL0010Constant.NZZM0000E;
import static business.blap.NWWL0010.constant.NWWL0010Constant.NZZM0001W;
import static business.blap.NWWL0010.constant.NWWL0010Constant.NZZM0002I;
import static business.blap.NWWL0010.constant.NWWL0010Constant.ZZZM9001E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWWL0010.common.NWWL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * NWWL0010BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0010CMsg bizMsg = (NWWL0010CMsg) cMsg;
            NWWL0010SMsg glblMsg = (NWWL0010SMsg) sMsg;

            if ("NWWL0010Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWWL0010_INIT".equals(screenAplID)) {
                doProcess_NWWL0010_INIT(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_OnChange_BizArea".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_OnChange_BizArea(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_Search".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWWL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWWL0010Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_CMN_Download(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWWL0010Query.getInstance().getClass());
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID), CSV_EXT);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("ntfyHdrNm", bizMsg.ntfyHdrNm);
            params.put("ntfyHdrDescTxt", bizMsg.ntfyHdrDescTxt);
            params.put("ntfyBizAreaTpCd", bizMsg.ntfyBizAreaTpCd_SL);
            params.put("ntfySubAreaTpCd", bizMsg.ntfySubAreaTpCd_SL);
            params.put("ntfyHdrActvFlg", bizMsg.ntfyHdrActvFlg);
            params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            params.put("rowNum", String.valueOf(CSV_LIMIT_COUNT));

            stmtSelect = ssmLLClient.createPreparedStatement("getNotificationList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                bizMsg.setMessageInfo(ZZZM9001E, null);
                return;
            }

            NWWL0010CommonLogic.writeCsvFileContInfo(bizMsg, rs);

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
    private void doProcess_NWWL0010Scrn00_CMN_Clear(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {
        doProcess_NWWL0010_INIT(bizMsg, glblMsg);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010_INIT(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {
        NWWL0010CommonLogic.createPulldownList(bizMsg, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrActvFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * OnChange_BizArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_OnChange_BizArea(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {
        NWWL0010CommonLogic.createNtfySubAreaPulldownList(bizMsg, getGlobalCompanyCode());
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_PageJump(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWWL0010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_PageNext(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWWL0010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_PagePrev(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWWL0010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_Search(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {
        notificationListSearch(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0010Scrn00_TBLColumnSort(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NWWL0010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * Notification List Search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void notificationListSearch(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NWWL0010Query.getInstance().getNotificationList(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {

            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            } else {
                bizMsg.setMessageInfo(NZZM0002I);
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NWWL0010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
}
