/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6810.common;

import parts.common.EZDMsg;
import business.blap.NMAL6810.NMAL6810CMsg;
import business.blap.NMAL6810.NMAL6810Query;
import business.blap.NMAL6810.NMAL6810SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NMAL6810CommonLogic {

    /**
     * Search
     * @param cMsg NMAL6810CMsg
     * @param sMsg NMAL6810SMsg
     */
    public static void search(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {

		ZYPTableUtil.clear(sMsg.A);
		ZYPTableUtil.clear(cMsg.A);
        S21SsmEZDResult ssmResult = NMAL6810Query.getInstance().getMdseCratTmpl(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            pageNation(cMsg, sMsg, 0);
        } else {
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.setMessageInfo("NZZM0000E");
        }
    }

    /**
     * pageNation
     * @param cMsg NMAL6810CMsg
     * @param sMsg NMAL6810SMsg
     * @param pageFrom int
     */
    public static void pageNation(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg, int pageFrom) {
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
}
