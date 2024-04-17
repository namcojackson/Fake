package business.blap.ZZIL0040;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public final class ZZIL0040Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final ZZIL0040Query MY_INSTANCE = new ZZIL0040Query();

    /**
     * Constructor.
     */
    private ZZIL0040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static ZZIL0040Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get request data List
     * @param cMsg ZZIL0040CMsg
     * @param sMsg ZZIL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestList(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        String startDateFrom = convDate(cMsg.xxFromDt_SF.getValue(), cMsg.xxHrs_S1.getValue());
        String startDateTo = convDate(cMsg.xxToDt_ST.getValue(), cMsg.xxHrs_S2.getValue());
        String endDateFrom = convDate(cMsg.xxFromDt_EF.getValue(), cMsg.xxHrs_E1.getValue());
        String endDateTo = convDate(cMsg.xxToDt_ET.getValue(), cMsg.xxHrs_E2.getValue());

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);

        ssmParam.put("startDateFrom", startDateFrom);
        ssmParam.put("startDateTo", startDateTo);
        ssmParam.put("endDateFrom", endDateFrom);
        ssmParam.put("endDateTo", endDateTo);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        if (!cMsg.intfcRqstStsTxt_HS.getValue().equals("ALL")) {
            ssmParam.put("intfcRqstStsTxt", cMsg.intfcRqstStsTxt_HS.getValue());
        }
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(sMsg.A);

        return getSsmEZDClient().queryEZDMsgArray("getRequestList", ssmParam, sMsg.A);
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
