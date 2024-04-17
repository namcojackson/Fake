package business.blap.ZZOL0051;

import java.util.HashMap;
import java.util.Map;

import business.blap.ZZOL0051.constant.ZZOL0051Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0051Query extends S21SsmEZDQuerySupport implements ZZOL0051Constant {

    /**
     * Singleton instance.
     */
    private static final ZZOL0051Query myInstance = new ZZOL0051Query();

    /**
     * Constructor.
     */
    private ZZOL0051Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZOL0051Query
     */
    public static ZZOL0051Query getInstance() {
        return myInstance;
    }

    /**
     * get CSV Master search result
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getCsvMstr(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_UPLD_CSV_ID,  cMsg.upldCsvId.getValue());

        sMsg.clear();
        
        return getSsmEZDClient().queryEZDMsg(QUERY_MSTR, ssmParam, sMsg);
    }

    /**
     * get CSV Header search result
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getCsvHeaderList(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_UPLD_CSV_ID,  cMsg.upldCsvId.getValue());

        sMsg.H.clear();
        sMsg.H.setValidCount(0);
        
        return getSsmEZDClient().queryEZDMsgArray(QUERY_HEADERLIST, ssmParam, sMsg.H);
    }

    /**
     * get Application ID Relation search result
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getCsvAppIdList(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_UPLD_CSV_ID,  cMsg.upldCsvId.getValue());

        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        
        return getSsmEZDClient().queryEZDMsgArray(QUERY_APPIDLIST, ssmParam, sMsg.B);
    }

    /**
     * get Process ID Relation search result
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getCsvProcIdList(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_UPLD_CSV_ID,  cMsg.upldCsvId.getValue());

        sMsg.P.clear();
        sMsg.P.setValidCount(0);
        
        return getSsmEZDClient().queryEZDMsgArray(QUERY_PROCIDLIST, ssmParam, sMsg.P);
    }

}
