/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0120;

import java.util.HashMap;
import java.util.Map;


import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZIL0120Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZIL0120Query myInstance = new ZZIL0120Query();

    /**
     * Constructor.
     */
    private ZZIL0120Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0120Query
     */
    public static ZZIL0120Query getInstance() {
        return myInstance;
    }

    /**
     * get table list from INTERFACE_TRANSACTION table
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItrlIntfcIdsForMap(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg, String tblName, String configId){
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("tblName", tblName);
        ssmParam.put("configId", configId);
        return getSsmEZDClient().queryObjectList("getItrlIntfcIdsForMap", ssmParam);
        
    }
    
    /**
     * get master table name
     * @return
     */
    public S21SsmEZDResult getMasterTableNames(){
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getMasterTableNames", ssmParam);
    }
}
