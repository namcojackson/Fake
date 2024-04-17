/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6150;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public final class NMAL6150Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6150Query INSTANCE = new NMAL6150Query();

    /**
     * Constructor.
     */
    private NMAL6150Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6150Query
     */
    public static NMAL6150Query getInstance() {
        return INSTANCE;
    }

    S21SsmEZDResult getStList(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getStList", ssmParam);
    }

}
