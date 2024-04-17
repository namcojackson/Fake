/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1660;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1660Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public final class NWAL1660Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1660Query MY_INSTANCE = new NWAL1660Query();

    /**
     * Private constructor
     */
    private NWAL1660Query() {
        super();
    }

    /**
     * Get the NWAL1660Query instance.
     * @return NWAL1660Query instance
     */
    public static NWAL1660Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param mdseCd        String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInPondWt(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getInPondWt", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1660CMsg
     * @param spcCondPrcPk  spcCondPrcPk
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getPrcRuleInfo(String glblCmpyCd, NWAL1660CMsg bizMsg, BigDecimal spcCondPrcPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("spcCondPrcPk", spcCondPrcPk);
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());
        params.put("modFlgFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObject("getPrcRuleInfo", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param mdseCd        mdseCd
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMdseNm(String glblCmpyCd, String mdseCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseNm", params);
    }
}
