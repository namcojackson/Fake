/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0100;

import java.util.HashMap;

import business.blap.ZZOL0100.constant.ZZOL0100Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0100Query extends S21SsmEZDQuerySupport implements ZZOL0100Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZOL0100Query myInstance = new ZZOL0100Query();

	/**
	 * Constructor.
	 */
    private ZZOL0100Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZOL0100Query
	 */
    public static ZZOL0100Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getInformationList
     * <dd>The method explanation: Get getInformationList from MENU_INFO
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getInformationList(HashMap<String, String> param) {
               
    	return getSsmEZDClient().queryObjectList("getInformationList", param);

    }

    /**
     * Method name: getInformationPreviewList
     * <dd>The method explanation: Get getInformationPreviewList from MENU_INFO
     * <dd>Remarks:
     * @param   HashMap param
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getInformationPreviewList(HashMap<String, String> param) {
               
        return getSsmEZDClient().queryObjectList("getInformationPreviewList", param);

    }

}
