/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.NYEL0010;

import java.util.HashMap;

import business.blap.NYEL0010.constant.NYEL0010Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NYEL0010Query extends S21SsmEZDQuerySupport implements NYEL0010Constant {

	/**
	 * Singleton instance.
	 */
	private static final NYEL0010Query myInstance = new NYEL0010Query();

	/**
	 * Constructor.
	 */
    private NYEL0010Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	NYEL0010Query
	 */
    public static NYEL0010Query getInstance() {
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

    /**
     * Method name: getMyProcessInfoList
     * <dd>The method explanation: Get My Process from UP_TAB,MY_PROC
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getMyProcessInfoList(HashMap<String, String> param) {
               
        return getSsmEZDClient().queryObjectList("getMyProcessInfoList", param);

    }

    // START 2016/12/26 C.Ogaki Add Function Shared My Process
    /**
     * Method name: getSharedMyProcessInfoList
     * <dd>The method explanation: Get Shared My Process from AUTH_PSN,USR_ROLE,ROLE,UP_TAB,MY_PROC
     * <dd>Remarks:
     * @param   param HashMap
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getSharedMyProcessInfoList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getSharedMyProcessInfoList", param);

    }
    // END   2016/12/26 C.Ogaki Add Function Shared My Process

    /**
     * Method name: getWfBizAppList
     * <dd>The method explanation: get WF BizAppList from UP_TAB,MENU_PROC,MENU_PROC_GRP
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getWfBizAppList(HashMap<String, String> param) {
               
        return getSsmEZDClient().queryObjectList("getWfBizAppList", param);

    }

    /**
     * Method name: getInformationList
     * <dd>The method explanation: Get Information List from MENU_INFO
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getInformationList(HashMap<String, String> param) {
              
        return getSsmEZDClient().queryObjectList("getInformationList", param);

    }

    /**
     * Method name: getCDMenuBizAppList
     * <dd>The method explanation: Get BizApp List from CD_MENU_TBL
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getCDMenuBizAppList(HashMap<String, String> param) {
              
        return getSsmEZDClient().queryObjectList("getCDMenuBizAppList", param);

    }
}
