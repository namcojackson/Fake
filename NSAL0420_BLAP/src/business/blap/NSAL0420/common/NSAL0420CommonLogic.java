/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0420.common;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import business.blap.NSAL0420.NSAL0420CMsg;
import business.blap.NSAL0420.NSAL0420Query;
import business.blap.NSAL0420.NSAL0420SMsg;
import business.blap.NSAL0420.constant.NSAL0420Constant.MSG_ID;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0420CommonLogic {

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);

    }

    /**
     * getSearchData
     * @param cMsg NSAL0420CMsg
     * @param sMsg NSAL0420SMsg
     */
    public static void getSearchData(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0420Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), new String[]{Integer.toString(sMsg.A.length())});
                queryResCnt = sMsg.A.length();
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            // No result
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.toString());
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * copySMsgTocMsg
     * @param cMsg NSAL0420CMsg
     * @param sMsg NSAL0420SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(ixS++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }
}
