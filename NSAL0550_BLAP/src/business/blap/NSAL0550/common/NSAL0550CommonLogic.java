/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0550.common;

import static business.blap.NSAL0550.constant.NSAL0550Constant.*;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import business.blap.NSAL0550.NSAL0550CMsg;
import business.blap.NSAL0550.NSAL0550SMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Takeno        Create          N/A
 * 2017/01/18   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0550CommonLogic {

    /**
     * copy SMsg to CMsg.
     * @param handler S21BusinessHandler
     * @param cMsg NSAL0550CMsg
     * @param sMsg NSAL0550SMsg
     * @param ssmResult S21SsmEZDResult
     */
    public static void copySMsgToCMsg(S21BusinessHandler handler, NSAL0550CMsg cMsg, NSAL0550SMsg sMsg, S21SsmEZDResult ssmResult) {

        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
            queryResCnt = sMsg.A.length();
        }

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
        cMsg.xxPageShowOfNum.setValue(queryResCnt);
    }

    /**
     * set CMsg.
     * 
     * @param handler S21BusinessHandler
     * @param cMsg NSAL0550CMsg
     * @param sMsg NSAL0550SMsg
     */
    public static void setCMsgNoResult(S21BusinessHandler handler, NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        cMsg.setMessageInfo(NZZM0000E);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        // START 2017/01/18 K.Ochiai [QC#16331, ADD]
        cMsg.setCommitSMsg(true);
        // END 2017/01/18 K.Ochiai [QC#16331, ADD]
    }

    /**
     * jump page.
     * @param handler S21BusinessHandler
     * @param cMsg NSAL0550CMsg
     * @param sMsg NSAL0550SMsg
     */
    public static void doPagenationJump(S21BusinessHandler handler, NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
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
     * view next page.
     * @param handler S21BusinessHandler
     * @param cMsg NSAL0550CMsg
     * @param sMsg NSAL0550SMsg
     */
    public static void doPagenationNext(S21BusinessHandler handler, NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
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
     * view prev page.
     * @param handler S21BusinessHandler
     * @param cMsg NSAL0550CMsg
     * @param sMsg NSAL0550SMsg
     */
    public static void doPagenationPrev(S21BusinessHandler handler, NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
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
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }
}
