/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0770.common;

import static business.blap.NSAL0770.constant.NSAL0770Constant.*;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import business.blap.NSAL0770.NSAL0770CMsg;
import business.blap.NSAL0770.NSAL0770Query;
import business.blap.NSAL0770.NSAL0770SMsg;

/**
 *<pre>
 * Change Status Audit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kishimoto     Create          N/A
 * 2016/02/19   Hitachi         K.Kishimoto     Update          QC#3275
 *</pre>
 */
public class NSAL0770CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0770CMsg
     * @param sMsg NSAL0770SMsg
     */
    public static void clearMsg(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {
        // TODO
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0770CMsg
     * @param sMsg NSAL0770SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg, int pageFrom) {

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

    /**
     * Get Search Data
     * @param cMsg NSAL0770CMsg
     * @param sMsg NSAL0770SMsg
     */
    public static void getSearchData(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0770Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > sMsg.A.length()
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                String tm = sMsg.A.no(i).xxDtTm.getValue();
                setValue(sMsg.A.no(i).xxDtTm, ZYPDateUtil.formatEzd17ToDisp(tm));
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

}
