/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8840;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole.USER_ROLE_TYPE;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8840Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 * 2018/09/26   Fujitsu         C.Ogaki         Update          N/A
 *</pre>
 */
public final class NYEL8840Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8840Query MY_INSTANCE = new NYEL8840Query();

    /**
     * Private constructor
     */
    private NYEL8840Query() {
        super();
    }

    /**
     * Get the NYEL8840Query instance.
     * @return NYEL8840Query instance
     */
    public static NYEL8840Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getProcDfnList
     * @param bizMsg
     * @return
     */
    public S21SsmEZDResult getProcDfnList(NYEL8840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("procDfnList", params);
    }

    /**
     * Delegate Info.
     * @param user
     * @return
     */
// MOD Start 2018/09/26
//     public S21SsmEZDResult getDelegateUser(NYEL8840CMsg bizMsg) {
    public S21SsmEZDResult getDelegateUser(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
// MOD End 2018/09/26

        String user = bizMsg.xxWfAsgCd_F.getValue();
        String wfBizAppId = bizMsg.wfBizAppId.getValue();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("fromWfUsrId", user);
        params.put("fromWfUsrTpCd", USER_ROLE_TYPE.USER.getCode());
        params.put("toDtHHMM", S21SystemDate.getFWCurrentSystemTime("yyyyMMddHHmm"));

        if (!"ALL".equals(wfBizAppId)) {
            params.put("wfBizAppId", wfBizAppId);
        }
        // Redmine#2106 Bug fix
// MOD Start 2018/09/26
//         params.put("maxNum", bizMsg.A.length() + 1);
        params.put("maxNum", glblMsg.A.length() + 1);
// MOD End 2018/09/26

        return getSsmEZDClient().queryObjectList("getDelegateUser", params);

    }

// ADD Start 2018/09/26
    /**
     * checkPeriodOverlap
     * @param bizMsg
     * @return
     */
    public S21SsmEZDResult checkPeriodOverlap(NYEL8840CMsg bizMsg) {

        String user = bizMsg.xxWfAsgCd_F.getValue();
        String wfBizAppId = bizMsg.wfBizAppId.getValue();
        String toUser = bizMsg.xxWfAsgCd.getValue();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("fromWfUsrId", user);
        params.put("fromWfUsrTpCd", USER_ROLE_TYPE.USER.getCode());
        params.put("toWfUsrId", toUser);
        params.put("toWfUsrTpCd", USER_ROLE_TYPE.USER.getCode());
        params.put("fromDtHHMM", bizMsg.effFromDt.getValue() + bizMsg.effFromHourMn_H.getValue() + bizMsg.effFromHourMn_M.getValue());
        params.put("toDtHHMM", bizMsg.effThruDt.getValue() + bizMsg.effThruHourMn_H.getValue() + bizMsg.effThruHourMn_M.getValue());
        if (!"ALL".equals(wfBizAppId)) {
            params.put("wfBizAppId", wfBizAppId);
        }

        return getSsmEZDClient().queryObjectList("checkPeriodOverlap", params);

    }
// ADD End 2018/09/26
}
