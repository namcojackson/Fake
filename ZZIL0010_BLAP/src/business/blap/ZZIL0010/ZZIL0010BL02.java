/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         T.Kawamura      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0010;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;

import business.blap.ZZIL0010.constant.ZZIL0010Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZIL0010BL02 extends S21BusinessHandler implements ZZIL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0010_INIT".equals(screenAplID)) {
                doProcess_ZZIL0010_INIT((ZZIL0010CMsg) cMsg);

            } else if ("ZZIL0010Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_Search((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

            } else if ("ZZIL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_PageNext((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

            } else if ("ZZIL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_PagePrev((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

            } else if ("ZZIL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_TBLColumnSort((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

            } else if ("ZZIL0010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_CMN_Clear((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

            } else if ("ZZIL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_Submit((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg EZDCMsg
     */
    private void doProcess_ZZIL0010_INIT(ZZIL0010CMsg cMsg) {

    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0010Scrn00_Search(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZIL0010Query.getInstance().getTransList(cMsg, sMsg);

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {
            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // １ページ分の検索結果の転記（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // 改ページ項目への値の設定
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            // 検索結果なし
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0010Scrn00_PageNext(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // search item error check
        if (chkSearchItem(cMsg, sMsg)) {
            copyData(cMsg, sMsg, pagenationFrom - cMsg.A.length(), true);
            return;
        }

        // check change status flag
        if (chkChangeFlg(cMsg, sMsg, pagenationFrom - cMsg.A.length(), true)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }

        // copy data from SMsg onto CMsg
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0010Scrn00_PagePrev(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // search item error check
        if (chkSearchItem(cMsg, sMsg)) {
            copyData(cMsg, sMsg, pagenationFrom + cMsg.A.length(), true);
            return;
        }

        // check change status flag
        if (chkChangeFlg(cMsg, sMsg, pagenationFrom + cMsg.A.length(), true)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }

        // copy data from SMsg onto CMsg
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0010Scrn00_TBLColumnSort(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        // search item error check
        if (chkSearchItem(cMsg, sMsg)) {
            copyData(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), false);
            return;
        }

        // check change status flag
        if (chkChangeFlg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), false)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            if ("xxDtTm_AR".equals(sortItemNm)) {
                sortItemNm = "ezInTime_A";
            } else if ("xxDtTm_AU".equals(sortItemNm)) {
                sortItemNm = "ezUpTime_A";
            }

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            if (cMsg.A.getValidCount() > 0) {

                // 転記（SMsg -> CMsg）
                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                // 改ページ項目への値の設定（１ページ目を表示）
                cMsg.xxPageShowFromNum.setValue(1);
                cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            }
        }
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0010Scrn00_CMN_Clear(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        cMsg.clear();
        cMsg.A.setValidCount(0);

        sMsg.clear();
        sMsg.A.setValidCount(0);

        doProcess_ZZIL0010_INIT(cMsg);
    }

    /**
     * research processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0010Scrn00_Submit(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        // search item error check
        if (chkSearchItem(cMsg, sMsg)) {
            copyData(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), false);
            return;
        }

        S21SsmEZDResult ssmResult = ZZIL0010Query.getInstance().getTransList(cMsg, sMsg);

        // flag clear
        cMsg.xxRsltFlg.clear();

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {
            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                queryResCnt = sMsg.A.length();
            }

            String sortTblNm = cMsg.xxSortTblNm.getValue();
            // Table:A
            if ("A".equals(sortTblNm)) {
                String sortItemNm = cMsg.xxSortItemNm.getValue();
                String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

                // execute column sort function
                S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
                S21SortKey sortKey = new S21SortKey();
                sortKey.add(sortItemNm, sortOrdBy);
                S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());
            }

            // get start row number of page
            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
            while (queryResCnt <= pagenationFrom) {
                pagenationFrom -= cMsg.A.length();
            }

            // 指定ページ分の検索結果の転記（SMsg -> CMsg）
            int cnt = 0;
            for (int i = pagenationFrom; i < sMsg.A.getValidCount(); i++) {
                if (cnt < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
                cnt++;
            }
            cMsg.A.setValidCount(cnt);

            // 改ページ項目への値の設定
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            // 検索結果なし
        } else {
            cMsg.A.clear();
            cMsg.A.setValidCount(0);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();

            cMsg.xxSortTblNm.clear();
            cMsg.xxSortItemNm.clear();
            cMsg.xxSortOrdByTxt.clear();
        }

    }

    /**
     * search item error check
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return boolean false:error true:no error
     */
    private boolean chkSearchItem(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        boolean errFlg = false;

        // compare EZDCMsg with EZDSMsg
        errFlg |= chkItem(cMsg.interfaceId, sMsg.interfaceId, "Interface ID");
        errFlg |= chkItem(cMsg.transactionId, sMsg.transactionId, "Transaction ID");
        errFlg |= chkItem(cMsg.processedFlag_PS, sMsg.processedFlag_PS, "Processed Flag");

        errFlg |= chkItem(cMsg.xxFromDt_RF, sMsg.xxFromDt_RF, "Register Date From");
        errFlg |= chkItem(cMsg.xxHrs_R1, sMsg.xxHrs_R1, "Register Time From");
        errFlg |= chkItem(cMsg.xxToDt_RT, sMsg.xxToDt_RT, "Register Date To");
        errFlg |= chkItem(cMsg.xxHrs_R2, sMsg.xxHrs_R2, "Register Time To");

        errFlg |= chkItem(cMsg.xxFromDt_UF, sMsg.xxFromDt_UF, "Updated Date From");
        errFlg |= chkItem(cMsg.xxHrs_U1, sMsg.xxHrs_U1, "Updated Time From");
        errFlg |= chkItem(cMsg.xxToDt_UT, sMsg.xxToDt_UT, "Updated Date To");
        errFlg |= chkItem(cMsg.xxHrs_U2, sMsg.xxHrs_U2, "Updated Time To");

        return errFlg;
    }

    /**
     * item comparison
     * @param cItem EZDCItem
     * @param sItem EZDSItem
     * @param msgCode message code
     * @return boolean false:different value true:constant value
     */
    private boolean chkItem(EZDCItem cItem, EZDSItem sItem, String msgCode) {
        String cStr = null;
        String sStr = null;

        if (cItem instanceof EZDCStringItem) {
            cStr = ((EZDCStringItem) cItem).getValue();
        } else if (cItem instanceof EZDCDateItem) {
            cStr = ((EZDCDateItem) cItem).getValue();
        } else if (cItem instanceof EZDCBigDecimalItem) {
            BigDecimal cDec = ((EZDCBigDecimalItem) cItem).getValue();
            if (cDec == null) {
                cStr = "";
            } else {
                cStr = cDec.toString();
            }
        }

        if (sItem instanceof EZDSStringItem) {
            sStr = ((EZDSStringItem) sItem).getValue();
        } else if (sItem instanceof EZDSDateItem) {
            sStr = ((EZDSDateItem) sItem).getValue();
        } else if (sItem instanceof EZDSBigDecimalItem) {
            BigDecimal sDec = ((EZDSBigDecimalItem) sItem).getValue();
            if (sDec == null) {
                sStr = "";
            } else {
                sStr = sDec.toString();
            }
        }

        if (!cStr.equals(sStr)) {
            cItem.setErrorInfo(1, "ZZZM9021E", new String[] {msgCode });
            return true;
        }
        return false;
    }

    /**
     * check change status flag
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom Page Number
     * @param boolean revFlg Revision value Flag
     */
    private boolean chkChangeFlg(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg, int pagenationFrom, boolean revFlg) {

        boolean retFlg = false;
        Map<Integer, String> flgMap = new HashMap<Integer, String>();

        // out of check and warning flag of check
        if (sMsg.A.getValidCount() <= 1 || PAGE_WARN.equals(cMsg.xxRsltFlg.getValue())) {
            cMsg.xxRsltFlg.clear();
            return retFlg;
        }

        // check change status
        String procFlg = null;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            procFlg = cMsg.A.no(i).processedFlag_AS.getValue();
            if (!"".equals(procFlg)) {
                flgMap.put(i, procFlg);
            }
        }

        // setting data
        if (flgMap.size() > 0) {
            cMsg.A.clear();

            if (!revFlg) {
                pagenationFrom--;
            }

            int num = 0;
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    num = i - pagenationFrom;

                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(num), null);
                    procFlg = flgMap.get(num);
                    if (procFlg != null) {
                        cMsg.A.no(num).processedFlag_AS.setValue(procFlg);
                    }
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());

            cMsg.xxSortTblNm.clear();
            cMsg.xxSortItemNm.clear();
            cMsg.xxSortOrdByTxt.clear();

            cMsg.xxRsltFlg.setValue(PAGE_WARN);

            retFlg = true;
        }

        return retFlg;
    }

    /**
     * data copy
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom Page Number
     * @param boolean revFlg Revision value Flag
     */
    private void copyData(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg, int pagenationFrom, boolean revFlg) {

        Map<Integer, String> flgMap = new HashMap<Integer, String>();

        // check change status
        String procFlg = null;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            procFlg = cMsg.A.no(i).processedFlag_AS.getValue();
            if (!"".equals(procFlg)) {
                flgMap.put(i, procFlg);
            }
        }

        cMsg.A.clear();

        if (!revFlg) {
            pagenationFrom--;
        }

        int num = 0;
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                num = i - pagenationFrom;

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(num), null);
                procFlg = flgMap.get(num);
                if (procFlg != null) {
                    cMsg.A.no(num).processedFlag_AS.setValue(procFlg);
                }
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());

        cMsg.xxSortTblNm.clear();
        cMsg.xxSortItemNm.clear();
        cMsg.xxSortOrdByTxt.clear();

    }

}
