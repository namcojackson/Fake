/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0120;

import java.util.HashMap;

import business.blap.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0120Query extends S21SsmEZDQuerySupport implements ZZOL0120Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZOL0120Query myInstance = new ZZOL0120Query();

	/**
	 * Constructor.
	 */
    private ZZOL0120Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZOL0120Query
	 */
    public static ZZOL0120Query getInstance() {
        return myInstance;
    }

    /**
     * Method name: getProcGroupList
     * <dd>The method explanation: Get getProcGroupList from MENU_PROC_GRP
     * <dd>Remarks:
     * @param   param HashMap<String, String>
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getProcGroupList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getProcGroupList", param);

    }

    /**
     * Method name: getProcGroupMax
     * <dd>The method explanation: Get MAX(MENU_PROC_GRP_CD) from MENU_PROC_GRP
     * <dd>Remarks:
     * @param   HashMap<String, String> param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getProcGroupMax(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getProcGroupMax", param);

    }

    /**
     * Method name: getProcMax
     * <dd>The method explanation: Get MAX(MENU_PROC_ID) from MENU_PROC
     * <dd>Remarks:
     * @param   HashMap<String, String> param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getProcMax(HashMap<String, String> param) {

        return getSsmEZDClient().queryObject("getProcMax", param);

    }

    /**
     * Method name: getProcList
     * <dd>The method explanation: Get getProcList from MENU_PROC
     * <dd>Remarks:
     * @param   HashMap<String, String> param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getProcList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getProcList", param);

    }

    /**
     * Method name: getUpTabList
     * <dd>The method explanation: Get UpTabList from UP_TAB
     * <dd>Remarks:
     * @param   HashMap<String, String> param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getUpTabList(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getUpTabList", param);

    }

    /**
     * Method name: getUpTabMax
     * <dd>The method explanation: Get MAX(UP_TAB_CD) from UP_TAB
     * <dd>Remarks:
     * @param   HashMap<String, String> param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getUpTabMax(HashMap<String, String> param) {

        return getSsmEZDClient().queryObjectList("getUpTabMax", param);

    }

}
