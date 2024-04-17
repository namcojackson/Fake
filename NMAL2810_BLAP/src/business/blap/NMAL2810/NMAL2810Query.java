/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2810;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2810.constant.NMAL2810Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2810Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public final class NMAL2810Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2810Query MY_INSTANCE = new NMAL2810Query();

    /**
     * Private constructor
     */
    private NMAL2810Query() {
        super();
    }

    /**
     * Get the NMAL2810Query instance.
     * @return NMAL2810Query instance
     */
    public static NMAL2810Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getDetailLocationInfo
     * @param bizMsg NMAL2810CMsg
     * @param glblMsg NMAL2810SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailLocationInfo(NMAL2810CMsg bizMsg, NMAL2810SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("locNum", bizMsg.locNum_R.getValue());
        params.put("slsDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rgtnStsP01", RGTN_STS.PENDING_COMPLETION);
        params.put("rgtnStsP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("max_date", NMAL2810Constant.MAX_DATE);

        return getSsmEZDClient().queryEZDMsg("getDetailLocationInfo", params, bizMsg);
    }

    /**
     * getDetailProspectInfo
     * @param bizMsg NMAL2810CMsg
     * @param glblMsg NMAL2810SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailProspectInfo(NMAL2810CMsg bizMsg, NMAL2810SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prosNum", bizMsg.locNum_R.getValue());

        return getSsmEZDClient().queryEZDMsg("getDetailProspectInfo", params, bizMsg);
    }
}
