/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         T.Kawamura      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0010;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZIL0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZIL0010Query myInstance = new ZZIL0010Query();

    /**
     * Constructor.
     */
    private ZZIL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0010Query
     */
    public static ZZIL0010Query getInstance() {
        return myInstance;
    }

    /**
     * 画面入力された条件を元に検索し、INTERFACE_TRANSACTIONテーブルから一覧表のデータを取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransList(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

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

        EZDMsg.copy(cMsg, null, sMsg, null);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        return getSsmEZDClient().queryEZDMsgArray("getTransList", ssmParam, sMsg.A);
    }

    /**
     * 検索する日付文字列に変換する
     * @param dateFrom
     * @param timeFrom
     * @return String date format()
     */
    private String convDate(String date, String time) {

        if (!"".equals(date) && !"".equals(time)) {
            return date + time + "0000000";
        }
        return null;
    }

}
