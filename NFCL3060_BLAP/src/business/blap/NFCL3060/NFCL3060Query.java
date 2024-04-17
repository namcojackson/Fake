/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3060;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFCL3060Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/8     Fujitsu         S.Fujita        Create          N/A
 * 2018/8/20    CITS            K.Kameoka       Update          QC#25090
 *</pre>
 */
public final class NFCL3060Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL3060Query MY_INSTANCE = new NFCL3060Query();

    /**
     * Private constructor
     */
    private NFCL3060Query() {
        super();
    }

    /**k
     * Get the NFCL3060Query instance.
     * @return NFCL3060Query instance
     */
    public static NFCL3060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Payment Search
     * @param bizMsg NFCL3060CMsg
     * @param glblMsg NFCL3060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult paymentsearch(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("arTrxNum", bizMsg.arTrxNum.getValue());

        return getSsmEZDClient().queryEZDMsg("getPaymentTerm", params, glblMsg);
    }

    /**
     * Search
     * @param bizMsg NFCL3060CMsg
     * @param glblMsg NFCL3060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("arTrxNum", bizMsg.arTrxNum.getValue());

        params.put("rowNum", glblMsg.A.length() + 1);
        //QC#25090 Add Start
        params.put("istlPmtTermFlg", ZYPConstant.FLG_ON_Y);
        //QC#25090 Add End
        
        return getSsmEZDClient().queryEZDMsgArray("getInvList", params, glblMsg.A);
    }
}
