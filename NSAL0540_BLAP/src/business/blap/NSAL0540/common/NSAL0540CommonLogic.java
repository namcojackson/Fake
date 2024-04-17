/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0540.common;

import static business.blap.NSAL0540.constant.NSAL0540Constant.*;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCItem;
import parts.common.EZDMsg;
import parts.common.EZDSItem;
import business.blap.NSAL0540.NSAL0540CMsg;
import business.blap.NSAL0540.NSAL0540SMsg;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL0540CommonLogic {

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NSAL0540CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isError(NSAL0540CMsg cMsg) {
        // Message line
        if (ERROR.equals(cMsg.getMessageKind())) {
            return true;
        }

        // screen item
        List<EZDCItem> inputItem = getCMsgInputItem(cMsg);
        for (EZDCItem item : inputItem) {
            if (item.isError()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the sMsg has an error.
     * @param sMsg NSAL0540SMsg
     * @return boolean true: If sMsg has error. false: otherwise.
     */
    public static boolean isError(NSAL0540SMsg sMsg) {
        // line item
        List<EZDSItem> inputLineItem = getSMsgInputItem(sMsg);
        for (EZDSItem item : inputLineItem) {
            if (item.isError()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the cMsg or sMsg has an error.
     * @param cMsg NSAL0540CMsg
     * @param sMsg NSAL0540SMsg
     * @return boolean true: If cMsg or sMsg has error. false: otherwise.
     */
    public static boolean isError(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {
        return isError(cMsg) || isError(sMsg);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0540CMsg
     * @param sMsg NSAL0540SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    private static List<EZDCItem> getCMsgInputItem(NSAL0540CMsg cMsg) {
        List<EZDCItem> list = new ArrayList<EZDCItem>();
        list.add(cMsg.svcSlnNm);
        list.add(cMsg.svcCmntTxt);
        return list;
    }

    private static List<EZDSItem> getSMsgInputItem(NSAL0540SMsg sMsg) {
        List<EZDSItem> list = new ArrayList<EZDSItem>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            list.add(sMsg.A.no(i).xxChkBox_A0);
        }
        return list;
    }
}
