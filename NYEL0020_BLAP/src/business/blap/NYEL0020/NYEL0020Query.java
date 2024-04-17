/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.NYEL0020;

import java.util.HashMap;

import business.blap.NYEL0020.constant.NYEL0020Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NYEL0020Query extends S21SsmEZDQuerySupport implements NYEL0020Constant {

	/**
	 * Singleton instance.
	 */
	private static final NYEL0020Query myInstance = new NYEL0020Query();

	/**
	 * Constructor.
	 */
    private NYEL0020Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	NYEL0020Query
	 */
    public static NYEL0020Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getAppList
     * <dd>The method explanation: Get BusinessID from UP_TAB,MENU_PROC
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getAppList(HashMap<String, String> param) {
               
    	return getSsmEZDClient().queryObjectList("findProcessInfoData", param);

    }

    /**
     * Method name: getAppListForProc
     * <dd>The method explanation: Get My BusinessID from UP_TAB,MY_PROC
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getMyAppList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("findMyProc", param);

    }
    
    /**
     * Method name: getCanAppList
     * <dd>The method explanation: Get Accessable BusinessID from Security Info tables
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getCanAppList(HashMap<String, String> param) {
               
        return getSsmEZDClient().queryObjectList("findMyBizApp", param);

    }

}
