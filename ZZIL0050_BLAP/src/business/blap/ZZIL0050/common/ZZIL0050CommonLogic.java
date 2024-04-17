package business.blap.ZZIL0050.common;

import java.math.BigDecimal;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.ZZIL0050.ZZIL0050CMsg;
import business.blap.ZZIL0050.ZZIL0050Query;
import business.blap.ZZIL0050.ZZIL0050SMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0050CommonLogic {

    /**
     * search Interface ID List from Target I/F Setting Master Table
     * @param cMsg ZZIL0050CMsg
     * @param sMsg ZZIL0050SMsg
     */
    public static void searchList(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZIL0050Query.getInstance().getIFList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            // Max Over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // Copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set pagenation parameter
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }
}
