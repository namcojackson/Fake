package business.blap.ZZVL0000;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * ZZVL0000Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public final class ZZVL0000Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZVL0000Query MY_INSTANCE = new ZZVL0000Query();

    /**
     * Constructor.
     */
    private ZZVL0000Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  ZZVL0000Query
     */
    public static ZZVL0000Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPreferredViewList
     * @param   cMsg ZZVL0000CMsg
     * @param   sMsg ZZVL0000SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getPreferredViewList(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getPreferredViewList", ssmParam, sMsg.A);
    }

    /**
     * getPreferredViewListForAdd
     * @param   cMsg ZZVL0000CMsg
     * @param   sMsg ZZVL0000SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getPreferredViewListForAdd(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.B.length() + 1);
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getPreferredViewListForAdd", ssmParam, sMsg.B);
    }

    /**
     * getPreferredViewCount
     * @param   glblCmpyCd String
     * @param   roleNm String
     * @param   scrAppId String
     * @param   scrTblNm String
     * @param   newTblColDefNm String
     * @return  int
     */
    public int getPreferredViewCount(String glblCmpyCd, String roleNm, String scrAppId, String scrTblNm, String newTblColDefNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("roleNm", roleNm);
        ssmParam.put("scrAppId", scrAppId);
        ssmParam.put("scrTblNm", scrTblNm);
        ssmParam.put("scrTblColDefNm", newTblColDefNm);
        return (Integer) getSsmEZDClient().queryObject("countPreferredView", ssmParam).getResultObject();
    }
}
