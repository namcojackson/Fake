package business.blap.ZZIL0050;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

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
public final class ZZIL0050Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final ZZIL0050Query MY_INSTANCE = new ZZIL0050Query();

    /**
     * Constructor.
     */
    private ZZIL0050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static ZZIL0050Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get interface ID list from MDB_INBD_INTFC_CONFIG
     * @param cMsg ZZIL0050CMsg
     * @param sMsg ZZIL0050SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getIFList(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(sMsg.A);

        return getSsmEZDClient().queryEZDMsgArray("getIFList", ssmParam, sMsg.A);
    }

}
