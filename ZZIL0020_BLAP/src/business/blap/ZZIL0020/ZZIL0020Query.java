package business.blap.ZZIL0020;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsgArray;

import business.blap.ZZIL0020.constant.ZZIL0020Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class ZZIL0020Query extends S21SsmEZDQuerySupport implements ZZIL0020Constant {


    /**
     * Singleton instance.
     */
    private static final ZZIL0020Query myInstance = new ZZIL0020Query();

    /**
     * Constructor.
     */
    private ZZIL0020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0010Query
     */
    public static ZZIL0020Query getInstance() {
        return myInstance;
    }

    /**
     * get csv date
     * @param cMsg
     * @param sMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCsvData(ZZIL0020CMsg cMsg, EZDMsgArray array) {

        return getSsmEZDClient().queryEZDMsgArray(cMsg.xxTblNm.getValue(), null, array);
    }

    /**
     * get Interface Id
     * @param satementId
     * @param intfcId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getIntfcId(String satementId, String intfcId) {

        // Query Component(SSM) parameter setting
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("intfcId", intfcId);
        return getSsmEZDClient().queryObject(satementId, ssmParam);
    }

}
