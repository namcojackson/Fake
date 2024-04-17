/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0010;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWWL0010Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public final class NWWL0010Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWWL0010Query MY_INSTANCE = new NWWL0010Query();

    /**
     * Private constructor
     */
    private NWWL0010Query() {
        super();
    }

    /**
     * Get the NWWL0010Query instance.
     * @return NWWL0010Query instance
     */
    public static NWWL0010Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Notification List
     * @param bizMsg NWWL0010CMsg
     * @param glblMsg NWWL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNotificationList(NWWL0010CMsg bizMsg, NWWL0010SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyHdrNm", bizMsg.ntfyHdrNm);
        params.put("ntfyHdrDescTxt", bizMsg.ntfyHdrDescTxt);
        params.put("ntfyBizAreaTpCd", bizMsg.ntfyBizAreaTpCd_SL);
        params.put("ntfySubAreaTpCd", bizMsg.ntfySubAreaTpCd_SL);
        params.put("ntfyHdrActvFlg", bizMsg.ntfyHdrActvFlg);
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getNotificationList", params, glblMsg.A);
    }
}
