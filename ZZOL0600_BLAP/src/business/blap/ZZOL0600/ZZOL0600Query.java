package business.blap.ZZOL0600;

import java.util.HashMap;
import java.util.Map;

import business.blap.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0600Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZOL0600Query myInstance = new ZZOL0600Query();

    /**
     * Constructor.
     */
    private ZZOL0600Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZOL0010Query
     */
    public static ZZOL0600Query getInstance() {
        return myInstance;
    }

    /**
     * Gets the abend log data.
     * @param cMsg the c msg
     * @param sMsg the s msg
     * @return the abend log data
     */
    public S21SsmEZDResult getAbendLogLimitedData(ZZOL0600CMsg cMsg, ZZOL0600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if (cMsg.almsOnlBatFlg_F2.getValue().equals(ZZOL0600Constant.ALL_CHAR))
            cMsg.almsOnlBatFlg_F2.clear();
        ssmParam.put("cMsg", cMsg);
        return getSsmEZDClient().queryEZDMsgArray("getAbendLogLimitedDetails", ssmParam, sMsg.A);

    }

    /**
     * Gets the initial abend log data.
     * @param cMsg the c msg
     * @param sMsg the s msg
     * @return the initial abend log data
     */
    public S21SsmEZDResult getAbendLogCompleteData(ZZOL0600CMsg cMsg, ZZOL0600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if (cMsg.almsOnlBatFlg_F2.getValue().equals(ZZOL0600Constant.ALL_CHAR))
            cMsg.almsOnlBatFlg_F2.clear();
        ssmParam.put("cMsg", cMsg);
        return getSsmEZDClient().queryEZDMsgArray("getAbendLogCompleteDetails", ssmParam, sMsg.A);
    }

    /**
     * Gets the job log path name.
     * @param param the param
     * @return the job log path name
     */
    public S21SsmEZDResult getJobLogPathName(HashMap<String, String> param) {
        return getSsmEZDClient().queryObjectList("getJobLogPathName", param);
    }

    /**
     * Gets the clob data.
     * @param param the param
     * @return the clob data
     */

    public S21SsmEZDResult getClobData(HashMap<String, String> param) {
        return getSsmEZDClient().queryObject("getClobData", param);
    }

}
