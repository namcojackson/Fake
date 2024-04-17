/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6740;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 *</pre>
 */
public final class NMAL6740Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6740Query INSTANCE = new NMAL6740Query();

    /**
     * Constructor.
     */
    private NMAL6740Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6740Query
     */
    public static NMAL6740Query getInstance() {
        return INSTANCE;
    }

    /**
     * getShipToCustInfoByShipToCustCd
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustInfoByShipToCustCd(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rowNum", 1);
        return getSsmEZDClient().queryObjectList("getShipToCustInfoByShipToCustCd", ssmParam);
    }

}
