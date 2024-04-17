/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2160;

import java.util.HashMap;
import java.util.Map;

import static business.blap.NWAL2160.constant.NWAL2160Constant.*;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2160Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public final class NWAL2160Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2160Query MY_INSTANCE = new NWAL2160Query();

    /**
     * Private constructor
     */
    private NWAL2160Query() {
        super();
    }

    /**
     * Get the NWAL2160Query instance.
     * @return NWAL2160Query instance
     */
    public static NWAL2160Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSampleList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlDescTxt(NWAL2160CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", bizMsg.mdlId_P.getValue());

        return getSsmEZDClient().queryObjectList("getMdlNm", params);
    }

    public S21SsmEZDResult getAddTier(NWAL2160CMsg bizMsg, int ix) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (ix == 0) {
            params.put("prcSvcTierTpCd", DUMMY_TIER_TP_CD);
        } else {
            params.put("prcSvcTierTpCd", bizMsg.A.no(ix - 1).prcSvcTierTpCd_A.getValue());
        }

        return getSsmEZDClient().queryObjectList("getAddTier", params);
    }

}
