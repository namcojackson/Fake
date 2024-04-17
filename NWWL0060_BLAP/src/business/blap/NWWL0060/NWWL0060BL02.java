/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0060;

import static business.blap.NWWL0060.constant.NWWL0060Constant.CRLF;
import static business.blap.NWWL0060.constant.NWWL0060Constant.NZZM0000E;
import static business.blap.NWWL0060.constant.NWWL0060Constant.QUOTE;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWWL0060.common.NWWL0060CommonLogic;
import business.db.NTFY_DIST_QLFYTMsg;
import business.db.NTFY_DIST_QLFYTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_DIST_QLFY;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWWL0060BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0060CMsg bizMsg = (NWWL0060CMsg) cMsg;
            NWWL0060SMsg glblMsg = (NWWL0060SMsg) sMsg;

            if ("NWWL0060_INIT".equals(screenAplID)) {
                doProcess_NWWL0060_INIT(bizMsg, glblMsg);

            } else if ("NWWL0060Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWWL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWWL0060Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWWL0060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWWL0060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWWL0060Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWWL0060Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NWWL0060_NWWL0020".equals(screenAplID)) {
                doProcess_NWWL0060_NWWL0020(bizMsg, glblMsg);

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
    private void doProcess_NWWL0060_INIT(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        EZDMsg.copy(bizMsg, null, glblMsg, null);

        // create sql
        NTFY_DIST_QLFYTMsg inTMsg = new NTFY_DIST_QLFYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.ntfyDistQlfyCd, NTFY_DIST_QLFY.EMP_ID);
        inTMsg = (NTFY_DIST_QLFYTMsg) S21CodeTableAccessor.findByKey(inTMsg);

        String tblNm = inTMsg.ntfyDistQlfyTblNm.getValue();
        String psnColNm = inTMsg.ntfyDistQlfyColNm.getValue();

        NTFY_DIST_QLFYTMsg confitionTMsg = new NTFY_DIST_QLFYTMsg();
        ZYPEZDItemValueSetter.setValue(confitionTMsg.glblCmpyCd, getGlobalCompanyCode());

        NTFY_DIST_QLFYTMsgArray qlfyMsgArray = (NTFY_DIST_QLFYTMsgArray) S21CodeTableAccessor.findByCondition(confitionTMsg);
        StringBuilder sqlTxt = new StringBuilder();
        sqlTxt.append("(").append(CRLF);

        for (int i = 0; i < qlfyMsgArray.length(); i++) {
            NTFY_DIST_QLFYTMsg qlfyMsg = (NTFY_DIST_QLFYTMsg) qlfyMsgArray.get(i);

            if (i != 0) {
                sqlTxt.append("OR ");
            }

            sqlTxt.append("(");

            sqlTxt.append("NDLA.NTFY_DIST_QLFY_CD = ").append(QUOTE).append(replaceInput(qlfyMsg.ntfyDistQlfyCd.getValue())).append(QUOTE).append(CRLF);
            sqlTxt.append("AND P.").append(replaceInput(qlfyMsg.ntfyDistQlfyColNm.getValue())).append(" = NDLA.NTFY_DIST_LIST_ASG_VAL_TXT").append(CRLF);
            sqlTxt.append(")").append(CRLF);

        }

        sqlTxt.append(")").append(CRLF);

        S21SsmEZDResult ssmResult = NWWL0060Query.getInstance().getNtfySbscr(bizMsg, glblMsg, tblNm, psnColNm, sqlTxt.toString());
        List<Map<String, Object>> ntfySbscrList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            NWWL0060CommonLogic.setNtfySbscr(bizMsg, glblMsg, ntfySbscrList);
        }

    }

    private String replaceInput(String inputData) {
        String outputData = inputData;

        outputData = outputData.replace("&", "&amp;");
        outputData = outputData.replace("\"", "&quot;");
        outputData = outputData.replace("<", "&lt;");
        outputData = outputData.replace(">", "&gt;");
        outputData = outputData.replace("'", "&#39;");

        return outputData;
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_CMN_Reset(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {
        doProcess_NWWL0060_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_CMN_Submit(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {
        doProcess_NWWL0060_INIT(bizMsg, glblMsg);
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_PageJump(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {

        NWWL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWWL0060CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_PageNext(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {

        NWWL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWWL0060CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_PagePrev(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {

        NWWL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWWL0060CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060Scrn00_TBLColumnSort(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {
        NWWL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

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
            NWWL0060CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * NWWL0020 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0060_NWWL0020(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg) {
        //
    }

}
