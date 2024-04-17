/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0030;

import static business.blap.NWWL0030.constant.NWWL0030Constant.NZZM0000E;
import static business.blap.NWWL0030.constant.NWWL0030Constant.NZZM0001W;
import static business.blap.NWWL0030.constant.NWWL0030Constant.BIZ_AREA_TP_TBL_NM;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWWL0030.common.NWWL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWWL0030BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0030CMsg bizMsg = (NWWL0030CMsg) cMsg;
            NWWL0030SMsg glblMsg = (NWWL0030SMsg) sMsg;

            if ("NWWL0030_INIT".equals(screenAplID)) {
                doProcess_NWWL0030_INIT(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_OnChange_BizArea".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_OnChange_BizArea(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_Search".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_ViewActDtl".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_ViewActDtl(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_ViewActRslt".equals(screenAplID)) {
                doProcess_NWWL0030Scrn00_ViewActRslt(bizMsg, glblMsg);

            } else if ("NWWL0030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWWL0030_INIT(bizMsg, glblMsg);

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
    private void doProcess_NWWL0030_INIT(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(BIZ_AREA_TP_TBL_NM, bizMsg.ntfyBizAreaTpCd_P, bizMsg.ntfyBizAreaTpDescTxt_P);
        NWWL0030CommonLogic.createNtfySubAreaPulldownList(bizMsg, getGlobalCompanyCode());

    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_CMN_Reset(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        doProcess_NWWL0030_INIT(bizMsg, glblMsg);

    }

    /**
     * OnChange_BizArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_OnChange_BizArea(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        NWWL0030CommonLogic.createNtfySubAreaPulldownList(bizMsg, getGlobalCompanyCode());
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_PageJump(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWWL0030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_PageNext(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWWL0030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_PagePrev(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWWL0030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_Search(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_TBLColumnSort(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
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
            NWWL0030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else if ("B".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.B, bizMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.B.getValidCount());
        }
    }

    /**
     * ViewActDtl Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_ViewActDtl(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        NWWL0030CommonLogic.clearActDtl(bizMsg);

        int selNum = Integer.parseInt(bizMsg.xxSelNum.getValue());

        S21SsmEZDResult ssmResult = NWWL0030Query.getInstance().getNtfyActDtl(bizMsg.B.no(selNum));
        Map<String, Object> ntfyActDtl = (Map<String, Object>) ssmResult.getResultObject();

        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        try {
            NWWL0030CommonLogic.setNtfyActDtl(bizMsg, ntfyActDtl);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        }
    }

    /**
     * ViewActRslt Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0030Scrn00_ViewActRslt(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        NWWL0030CommonLogic.clearActDtl(bizMsg);

        int selNum = Integer.parseInt(bizMsg.xxSelNum.getValue());

        S21SsmEZDResult ssmResult = NWWL0030Query.getInstance().getActRslt(bizMsg.A.no(selNum), glblMsg);
        List<Map<String, String>> actRsltList = (List<Map<String, String>>) ssmResult.getResultObject();

        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NZZM0000E);
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.B.setValidCount(glblMsg.B.length());

            } else {
                glblMsg.B.setValidCount(ssmResult.getQueryResultCount());
            }

            NWWL0030CommonLogic.setActRslt(bizMsg, glblMsg, actRsltList);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWWL0030CMsg bizMsg, NWWL0030SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);

        NWWL0030CommonLogic.clearActDtl(bizMsg);

        S21SsmEZDResult ssmResult = NWWL0030Query.getInstance().getNtfyHist(bizMsg, glblMsg);
        List<Map<String, String>> ntfyHistList = (List<Map<String, String>>) ssmResult.getResultObject();

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

            NWWL0030CommonLogic.setNtfyHist(bizMsg, glblMsg, ntfyHistList);

            bizMsg.xxPageShowFromNum.setValue(1);
            NWWL0030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

}
