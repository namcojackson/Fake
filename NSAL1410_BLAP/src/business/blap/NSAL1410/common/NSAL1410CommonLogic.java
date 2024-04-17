/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1410.common;

import static business.blap.NSAL1410.constant.NSAL1410Constant.*;

import parts.common.EZDMsg;
import business.blap.NSAL1410.NSAL1410CMsg;
import business.blap.NSAL1410.NSAL1410Query;
import business.blap.NSAL1410.NSAL1410SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Contract Branch Rep Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410CommonLogic {

    /**
     * Get Search Data
     * @param cMsg NSAL1410CMsg
     * @param sMsg NSAL1410SMsg
     */
    public static void getSearchData(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL1410Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * copyCurrentPageToSMsg
     * @param cMsg NSAL1410CMsg
     * @param sMsg NSAL1410SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        int pageFromIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = pageFromIndex; i < pageFromIndex + cMsg.A.length(); i++) {
            if (i < pageFromIndex + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(i - pageFromIndex), null, sMsg.A.get(i), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation
     * @param cMsg NSAL1410CMsg
     * @param sMsg NSAL1410SMsg
     * @param pageFromIndex int
     */
    public static void pagenation(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg, int pageFromIndex) {
        int i = pageFromIndex;
        for (; i < pageFromIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFromIndex), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFromIndex);
        cMsg.xxPageShowFromNum.setValue(pageFromIndex + 1);
        cMsg.xxPageShowToNum.setValue(pageFromIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
}
