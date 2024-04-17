/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0880.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import business.blap.NSAL0880.NSAL0880CMsg;
import business.blap.NSAL0880.NSAL0880Query;
import business.blap.NSAL0880.constant.NSAL0880Constant.MSG_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0880CommonLogic {

    /**
     * getSearchData
     * @param cMsg NSAL0880CMsg
     */
    public static void searchMtrIntfc(NSAL0880CMsg cMsg) {
        if (isError(cMsg)) {
            return;
        }
        S21SsmEZDResult ssmResult = NSAL0880Query.getInstance().getMtrIntfc(cMsg);
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.serNum, cMsg.A.no(0).serNum_A);
            // Result > 40
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), new String[]{Integer.toString(cMsg.A.length())});
                queryResCnt = cMsg.A.length();
            }
        } else {
            // No result
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.toString());
        }
    }

    private static boolean isError(NSAL0880CMsg cMsg) {
        if (!hasValue(cMsg.svcMachMstrPk)) {
            cMsg.setMessageInfo(MSG_ID.NSAM0362E.toString(), new String[] {"Service Machine Master PK" });
            return true;
        }
        if (!hasValue(cMsg.svcPhysMtrReadGrpSq)) {
            cMsg.setMessageInfo(MSG_ID.NSAM0362E.toString(), new String[] {"Service Physical Meter Read Group SQ" });
            return true;
        }
        return false;
    }
}
