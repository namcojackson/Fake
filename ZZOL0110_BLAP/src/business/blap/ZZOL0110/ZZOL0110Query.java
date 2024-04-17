/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0110;

import java.util.HashMap;

import business.blap.ZZOL0110.constant.ZZOL0110Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0110Query extends S21SsmEZDQuerySupport implements ZZOL0110Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZOL0110Query myInstance = new ZZOL0110Query();

	/**
	 * Constructor.
	 */
    private ZZOL0110Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZOL0110Query
	 */
    public static ZZOL0110Query getInstance() {
        return myInstance;
    }

    /**
     * Method name: getTabInfoListForProc
     * <dd>The method explanation: Get My BusinessID from UP_TAB,MENU_PROC
     * <dd>Remarks:
     * @param   HashMap<String, String> param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getTabInfoListForProc(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getTabInfoListForProc", param);

    }

}
