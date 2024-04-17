/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0450;

import java.util.HashMap;
import java.util.Map;

import static business.blap.NSBL0450.constant.NSBL0450Constant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public final class NSBL0450Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSBL0450Query INSTANCE = new NSBL0450Query();

    /**
     * Private constructor
     */
    private NSBL0450Query() {
        super();
    }

    /**
     * Get instance
     * @return NSBL0450Query singleton instance
     */
    public static NSBL0450Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("categoryY", CATEGORY_Y);
        params.put("categoryN", CATEGORY_N);
        params.put("maxMinute", MAX_MINUTE);
        params.put("minute", MINUTE);
        params.put("maxTime", MAX_TIME);
        params.put("timeFormatPre", TIME_FORMAT_PRE);
        params.put("timeFormat", TIME_FORMAT);
        params.put("pad00", PAD00);
        params.put("dtFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        params.put("dlFormat", DL_FORMAT);
        if (ZYPCommonFunc.hasValue(cMsg.svcSupplTaskNum_S2)) {
            params.put("svcSupplTaskNum", (String) cMsg.svcSupplTaskNum_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.svcSupplTaskTpCd_S2)) {
            params.put("svcSupplTaskTpCd", (String) cMsg.svcSupplTaskTpCd_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.cratDt_S2)) {
            params.put("cratDt", (String) cMsg.cratDt_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.techPsnNm_S2)) {
            params.put("techPsnNm", (String) cMsg.techPsnNm_S2.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mgrNm_S2)) {
            params.put("mgrNm", (String) cMsg.mgrNm_S2.getValue());
        }
        params.put("limitNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.A);
    }

}
