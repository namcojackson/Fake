/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0520;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0520Query extends S21SsmEZDQuerySupport {

    private static final NSAL0520Query INSTANCE = new NSAL0520Query();

    private NSAL0520Query() {
    }

    public static NSAL0520Query getInstance() {
        return INSTANCE;
    }

    public S21SsmEZDResult getConfigHist(String glblCmpyCd, BigDecimal svcMachMstrPk, int rowNum) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        prm.put("rowNum", rowNum);
        return getSsmEZDClient().queryObjectList("getConfigHist", prm);
    }

}
