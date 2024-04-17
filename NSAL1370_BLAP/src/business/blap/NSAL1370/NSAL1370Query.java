/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1370;

import java.util.HashMap;
import java.util.Map;

import static business.blap.NSAL1370.constant.NSAL1370Constant.*;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NSAL1370Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public final class NSAL1370Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1370Query MY_INSTANCE = new NSAL1370Query();

    /**
     * Private constructor
     */
    private NSAL1370Query() {
        super();
    }

    /**
     * Get the NSAL1370Query instance.
     * @return NSAL1370Query instance
     */
    public static NSAL1370Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSampleList
     * @param bizMsg Business Msg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlDescTxt(NSAL1370CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", bizMsg.mdlId_P.getValue());

        return getSsmEZDClient().queryObjectList("getMdlNm", params);
    }

    /**
     * @param bizMsg Business Msg
     * @param ix ValidCount
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddTier(NSAL1370CMsg bizMsg, int ix) {
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
