/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0110;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.ZZIL0110.common.ZZIL0110CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZIL0110Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZIL0110Query myInstance = new ZZIL0110Query();

    /**
     * Constructor.
     */
    private ZZIL0110Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0110Query
     */
    public static ZZIL0110Query getInstance() {
        return myInstance;
    }

    /**
     * get Data from ITRL_MSTR_XX table
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItrlIntfcIds(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg, String mstrTable) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("mstrTable", mstrTable);
        ssmParam.put("itrlIntfcId", cMsg.itrlIntfcId.getValue());
        ssmParam.put("configId", cMsg.itrlIntfcTrxConfigId_PS.getValue());
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(sMsg.A);
        return getSsmEZDClient().queryEZDMsgArray("getItrlIntfcIdsFromMstr", ssmParam, sMsg.A);
    }
    
    /**
     * get Data from ITRL_MSTR_XX table
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItrlIntfcTbl(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg, String mstrTable, String itrlIntfcId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("mstrTable", mstrTable);
        ssmParam.put("itrlIntfcId", itrlIntfcId);
        ssmParam.put("configId", cMsg.itrlIntfcTrxConfigId_BS.getValue());
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(sMsg.B);
        return getSsmEZDClient().queryEZDMsgArray("getItrlIntfcTblsFromMstr", ssmParam, sMsg.B);
    }


    public S21SsmEZDResult getMyId(){
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getMyId", ssmParam);
    }
    
    /**
     * get config names for self environment
     * @return
     */
    public S21SsmEZDResult getConfigNamesForSelf() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
//        ssmParam.put("selfTblName", ZZIL0110Constant.selfMstrTbl + "%");
        ssmParam.put("selfTblName", ZZIL0110CommonLogic.getSelfPrefix());
        return getSsmEZDClient().queryObjectList("getConfigNamesForSelf", ssmParam);
    }
    
    /**
     * get config names for self environment
     * @return
     */
    public S21SsmEZDResult getConfigNames() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getConfigNames", ssmParam);
    }
}
