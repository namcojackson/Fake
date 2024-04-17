package business.blap.ZZXL0050;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import business.blap.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZXL0050Query extends S21SsmEZDQuerySupport implements ZZXL0050Constant {

    /**
     * Singleton instance.
     */
    private static final ZZXL0050Query myInstance = new ZZXL0050Query();

    /**
     * Constructor.
     */
    private ZZXL0050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0030Query
     */
    public static ZZXL0050Query getInstance() {
        return myInstance;
    }

    /**
     * 画面入力された条件を元に検索し、一覧表のデータを取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getList(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_DT_PROC_CD, cMsg.dtProcCd.getValue());
        ssmParam.put(PM_DT_MGT_PGM_ID, cMsg.dtMgtPgmId.getValue());

        EZDMsg.copy(cMsg, null, sMsg, null);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        
        return getSsmEZDClient().queryEZDMsgArray(QUERY_LIST, ssmParam, sMsg.A);
    }
    
}
