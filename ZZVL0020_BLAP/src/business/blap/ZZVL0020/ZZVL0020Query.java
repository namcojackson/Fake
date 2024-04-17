package business.blap.ZZVL0020;

import java.util.HashMap;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public final class ZZVL0020Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZVL0020Query MY_INSTANCE = new ZZVL0020Query();

    /**
     * Constructor.
     */
    private ZZVL0020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  ZZVL0000Query
     */
    public static ZZVL0020Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Method name: getRoleList
     * <dd>The method explanation: Get Role List
     * @param   param HashMap<String, Object>
     * @param   sMsg ZZVL0020SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getRoleList(HashMap<String, Object> param, ZZVL0020SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getRoleList", param, sMsg.C);
    }

    /**
     * Method name: getAppList
     * <dd>The method explanation: Get BusinessID from UP_TAB,MENU_PROC
     * <dd>Remarks:
     * @param   param HashMap
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getAppList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("findProcessInfoData", param);

    }

    /**
     * Method name: getSharedMyAppList
     * <dd>The method explanation: Get My BusinessID from UP_TAB,MY_PROC
     * <dd>Remarks:
     * @param   param HashMap
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getSharedMyAppList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("findSharedMyProc", param);

    }

    /**
     * Method name: getCanAppList
     * <dd>The method explanation: Get Accessable BusinessID from Security Info tables
     * <dd>Remarks:
     * @param   param HashMap
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getCanAppList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("findRoleBizApp", param);

    }

}
