package business.blap.ZZVL0010;

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
public final class ZZVL0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZVL0010Query MY_INSTANCE = new ZZVL0010Query();

    /**
     * Constructor.
     */
    private ZZVL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  ZZVL0010Query
     */
    public static ZZVL0010Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getRoleList
     * @param   cMsg ZZVL0010CMsg
     * @param   sMsg ZZVL0010SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getRoleList(ZZVL0010CMsg cMsg, ZZVL0010SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getRoleList", ssmParam, sMsg.A);
    }

}
