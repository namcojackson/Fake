package business.blap.ZZOL0050;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import business.blap.ZZOL0050.constant.ZZOL0050Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0050Query extends S21SsmEZDQuerySupport implements ZZOL0050Constant {

    /**
     * Singleton instance.
     */
    private static final ZZOL0050Query myInstance = new ZZOL0050Query();

    /**
     * Constructor.
     */
    private ZZOL0050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZOL0050Query
     */
    public static ZZOL0050Query getInstance() {
        return myInstance;
    }

    /**
     * get search result
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getList(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_UPLD_CSV_ID,  cMsg.upldCsvId.getValue());
        ssmParam.put(PM_UPLD_CSV_NM,  cMsg.upldCsvNm.getValue());

        EZDMsg.copy(cMsg, null, sMsg, null);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        
        return getSsmEZDClient().queryEZDMsgArray(QUERY_LIST, ssmParam, sMsg.A);
    }
    
}
