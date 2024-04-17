/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0120.common;

import parts.common.EZDMsg;
import business.blap.NWCL0120.NWCL0120CMsg;
import business.blap.NWCL0120.NWCL0120SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWCL0120CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0120CommonLogic {

    /**
     * Update the global Message.
     * @param bizMsg NWCL0120CMsg
     * @param glblMsg NWCL0120SMsg
     */
    public static void updateGlblMsg(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.length(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * Set Page Next
     * @param cMsg NWCL0120CMsg
     * @param sMsg NWCL0120SMsg
     */
    public static void setPageNext(NWCL0120CMsg cMsg, NWCL0120SMsg sMsg) {

        NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt());
        bizMsg.xxPageShowToNum.clear();

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
     * Set Page Prev
     * @param cMsg NWCL0120CMsg
     * @param sMsg NWCL0120SMsg
     */
    public static void setPagePrev(NWCL0120CMsg cMsg, NWCL0120SMsg sMsg) {

        NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
        bizMsg.xxPageShowToNum.clear();

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
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Set Page Search
     * @param cMsg NWCL0120CMsg
     * @param sMsg NWCL0120SMsg
     * @param ssmResult S21SsmEZDResult
     */
    public static void setPageSearch(NWCL0120CMsg cMsg, NWCL0120SMsg sMsg) {

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
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

}
