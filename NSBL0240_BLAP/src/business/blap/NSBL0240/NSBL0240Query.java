/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0240;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public final class NSBL0240Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0240Query INSTANCE = new NSBL0240Query();

    /**
     * Constructor.
     */
    private NSBL0240Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0240Query
     */
    public static NSBL0240Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcLineBizList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcLineBizList() {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        return getSsmEZDClient().queryObjectList("getSvcLineBizList", ssmParam);
    }
}
