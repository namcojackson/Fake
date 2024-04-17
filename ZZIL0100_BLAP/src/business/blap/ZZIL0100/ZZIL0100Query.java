/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0100;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import business.blap.ZZIL0100.common.InternalTransactionTableAccessor;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZIL0100Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZIL0100Query myInstance = new ZZIL0100Query();

    /**
     * Constructor.
     */
    private ZZIL0100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0100Query
     */
    public static ZZIL0100Query getInstance() {
        return myInstance;
    }

    /**
     * get table list data from INTERFACE_TRANSACTION
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransList(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg, String tableId) {

        String registerDateFrom = convDate(cMsg.xxFromDt_RF.getValue(), cMsg.xxHrs_R1.getValue());
        String registerDateTo = convDate(cMsg.xxToDt_RT.getValue(), cMsg.xxHrs_R2.getValue());
        String updatedDateFrom = convDate(cMsg.xxFromDt_UF.getValue(), cMsg.xxHrs_U1.getValue());
        String updatedDateTo = convDate(cMsg.xxToDt_UT.getValue(), cMsg.xxHrs_U2.getValue());

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);

        ssmParam.put("registerDateFrom", registerDateFrom);
        ssmParam.put("registerDateTo", registerDateTo);
        ssmParam.put("updatedDateFrom", updatedDateFrom);
        ssmParam.put("updatedDateTo", updatedDateTo);
        ssmParam.put("tableId", tableId);
        ssmParam.put("configId", cMsg.itrlIntfcTrxConfigId_PS.getValue());
        ssmParam.put("maxSize", sMsg.A.length() + 1);
        
        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(sMsg.A);

        return getSsmEZDClient().queryEZDMsgArray("getTransList", ssmParam, sMsg.A);
    }

    /**
     * get pulldown list data for internal interface config
     * @return
     */
    public S21SsmEZDResult getConfigNames() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", super.getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getConfigNames", ssmParam);
    }
    
    /**
     * select for upadte Proccessed flag
     * @param accessor InternalTransactionTableAccessor
     * @param sMsg EZDSMsg
     * @param index String
     * @return ezuptime
     */
    public String selectForupdateItrlProcFlag(InternalTransactionTableAccessor accessor, ZZIL0100SMsg sMsg, int i) {
    	return accessor.selectForUpdateItrlProcFlg(sMsg, i);
    }
    	
    /**
     * update Proccessed flag
     * @param accessor InternalTransactionTableAccessor
     * @param sMsg EZDSMsg
     * @param index
     * @return update number
     */
    public int updateItrlProcFlag(InternalTransactionTableAccessor accessor, ZZIL0100SMsg sMsg, int i, String itrlProcFlg) {
     	return accessor.updateItrlProcFlg(sMsg, i, itrlProcFlg);
    }
    
    /**
     * exchange string to date string
     * @param dateFrom
     * @param timeFrom
     * @return String date format()
     */
    private String convDate(String date, String time) {
        if (ZYPCommonFunc.hasValue(date) && ZYPCommonFunc.hasValue(time)) {
            return date + time + "0000000";
        }
        return null;
    }
}
