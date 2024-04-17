package business.blap.ZZOL0600;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc; // 2013/04/30 ADD M.Sumida

public class ZZOL0600BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0600_INIT".equals(screenAplID)) {
                doProcess_ZZOL0600_INIT(cMsg, sMsg);
            } else if ("ZZOL0600Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0600Scrn00_Search(cMsg, sMsg);
            } else if ("ZZOL0600Scrn00_View".equals(screenAplID)) {
                doProcess_ZZOL0600Scrn00_View(cMsg, sMsg);
            } else if ("ZZOL0600Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZOL0600Scrn00_PageNext(cMsg, sMsg);

            } else if ("ZZOL0600Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZOL0600Scrn00_PagePrev(cMsg, sMsg);

            } else if ("ZZOL0600Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0600Scrn00_TBLColumnSort(cMsg, sMsg);

            } else if ("ZZOL0600Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZOL0600Scrn00_CMN_Clear(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Method: Search Abend Log Table
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0600Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        S21SsmEZDResult ssmResult = null;
        // Keep the search condition
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        ZZOL0600SMsg shareMsg = (ZZOL0600SMsg) sMsg;
        // initialize the table data
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.clear();
        shareMsg.A.setValidCount(0);

        S21InfoLogOutput.println("CHECK BOX VALUE :" + bizMsg.xxChkBox.getValue());

        if (ZZOL0600Constant.BIZAPL_CHKBOX_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
            ssmResult = ZZOL0600Query.getInstance().getAbendLogLimitedData(bizMsg, shareMsg);
        } else if (ZZOL0600Constant.BIZAPL_CHKBOX_OFF_N.equals(bizMsg.xxChkBox.getValue())) {
            ssmResult = ZZOL0600Query.getInstance().getAbendLogCompleteData(bizMsg, shareMsg);
        } else {
            ssmResult = ZZOL0600Query.getInstance().getAbendLogLimitedData(bizMsg, shareMsg);
        }

        if (ssmResult.isCodeNormal()) {
            // over the maximum length
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > shareMsg.A.length()) {

                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = shareMsg.A.length();
            }

            shareMsg.A.setValidCount(queryResCnt);

            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);

                if (ZZOL0600Constant.ONLINE_CHAR.equals(shareMsg.A.no(i).almsOnlBatFlg_TR.getValue())) {
                    bizMsg.A.no(i).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.ONLINE_FLG);
                } else if (ZZOL0600Constant.BATCH_CHAR.equals(shareMsg.A.no(i).almsOnlBatFlg_TR.getValue()) && shareMsg.A.no(i).almsBatProcId_TR.getValueInt() > 0) {
                    bizMsg.A.no(i).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.BATCH_FLG);
                }
                // Truncate the message to 50 chars logic
                if (shareMsg.A.no(i).almsMsgTxt_TR.getValue().length() > 50) {
                    String truncStr = shareMsg.A.no(i).almsMsgTxt_TR.getValue().substring(0, 50);
                    bizMsg.A.no(i).xxAbendMsgTxt_TR.setValue(truncStr);
                } else {
                    bizMsg.A.no(i).xxAbendMsgTxt_TR.setValue(shareMsg.A.no(i).almsMsgTxt_TR.getValue());
                }

            }
            bizMsg.A.setValidCount(i);

            // set value for Pagenenation
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

            // no search result
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Method: Initial Display of resutls.
     * <dd>The method explanation:display the intial logs if found.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0600_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        // Keep the search condition
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        // initialize the table data
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

    }

    /**
     * Method: Page Next
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Share Message
     */
    private void doProcess_ZZOL0600Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        ZZOL0600SMsg shareMsg = (ZZOL0600SMsg) sMsg;

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

    }

    /**
     * Method: Page Prev
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0600Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        ZZOL0600SMsg shareMsg = (ZZOL0600SMsg) sMsg;

        // Store checkbox data from CMsg to SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

    /**
     * Method: TBLColumnSort
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZOL0600Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        ZZOL0600SMsg shareMsg = (ZZOL0600SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(shareMsg.A, shareMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, shareMsg.A.getValidCount());

            // Copy SMsg -> CMsg
            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);
                // START 2013/08/21 M.Sumida Bug Fix
                if (ZZOL0600Constant.ONLINE_CHAR.equals(shareMsg.A.no(i).almsOnlBatFlg_TR.getValue())) {
                    bizMsg.A.no(i).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.ONLINE_FLG);
                } else if (ZZOL0600Constant.BATCH_CHAR.equals(shareMsg.A.no(i).almsOnlBatFlg_TR.getValue()) && shareMsg.A.no(i).almsBatProcId_TR.getValueInt() > 0) {
                    bizMsg.A.no(i).xxAlmsOnlBatFlgTxt_TR.setValue(ZZOL0600Constant.BATCH_FLG);
                }
                if (shareMsg.A.no(i).almsMsgTxt_TR.getValue().length() > 50) {
                    String truncStr = shareMsg.A.no(i).almsMsgTxt_TR.getValue().substring(0, 50);
                    bizMsg.A.no(i).xxAbendMsgTxt_TR.setValue(truncStr);
                } else {
                    bizMsg.A.no(i).xxAbendMsgTxt_TR.setValue(shareMsg.A.no(i).almsMsgTxt_TR.getValue());
                }
                // END   2013/08/21 M.Sumida Bug Fix
            }
            bizMsg.A.setValidCount(i);
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }

    /**
     * Method: Clear
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZOL0600Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        ZZOL0600SMsg shareMsg = (ZZOL0600SMsg) sMsg;
        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.setValidCount(0);
        return;
    }

    /**
     * Do process_ zzo l0600 scrn00_ view.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZOL0600Scrn00_View(EZDCMsg cMsg, EZDSMsg sMsg) {
        // Keep the search condition
        ZZOL0600CMsg bizMsg = (ZZOL0600CMsg) cMsg;
        StringBuilder msg_Details = new StringBuilder();
        BufferedReader reader = null;

        HashMap<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put(ZZOL0600Constant.glbl_cmpy_cd, bizMsg.glblCmpyCd_B.getValue());
        ssmParam.put(ZZOL0600Constant.alms_uuid, bizMsg.almsUnivsUniqId_B.getValue());

        S21SsmEZDResult ssmResult = ZZOL0600Query.getInstance().getClobData(ssmParam);

        if (ssmResult.isCodeNormal()) {

            // get search result
            Map map = (Map) ssmResult.getResultObject();
            BigDecimal chunkSz = (BigDecimal) map.get(ZZOL0600Constant.msg_size);
            if (chunkSz == null) {
                bizMsg.xxLogDtlTxt_B.clear();
            }

            Clob clob = (Clob) map.get(ZZOL0600Constant.alms_msg_details);

            // get clob data
            try {
                if (clob != null) {
                    reader = new BufferedReader(clob.getCharacterStream());

                    char[] cbuffer = new char[chunkSz.intValue()];
                    int length = -1;

                    while ((length = reader.read(cbuffer)) != -1) {
                        msg_Details.append(new String(cbuffer, 0, length));
                    }
                }
                // START 2013/04/30 Mod M.Sumida
                // if (msg_Details.toString().length() > 10000) {
                //     String truncStr = msg_Details.toString().substring(0, 10000);
                //     bizMsg.xxLogDtlTxt_B.setValue(truncStr);
                // } else {
                //     bizMsg.xxLogDtlTxt_B.setValue(msg_Details.toString());
                // }
                int digit  = bizMsg.getAttr("xxLogDtlTxt_B").getDigit()-1;
                if (msg_Details.toString().length() > digit / 2) {
                    bizMsg.xxLogDtlTxt_B.setValue(ZYPCommonFunc.subByteString(msg_Details.toString(), digit));
                } else {
                    bizMsg.xxLogDtlTxt_B.setValue(msg_Details.toString());
                }
                // END   2013/04/30 Mod M.Sumida

            } catch (Exception e) {
                String msg = "Failed to run the getClob Data Sql statement";
                throw new S21AbendException(e, msg);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    String msg = "Failed to run the getClob Data Sql statement";
                    throw new S21AbendException(e, msg);
                }
            }
        }

    }// end code normal

}
