/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0270;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Service Pricing Shift Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public final class NSBL0270Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0270Query INSTANCE = new NSBL0270Query();

    /**
     * Constructor.
     */
    private NSBL0270Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0270Query
     */
    public static NSBL0270Query getInstance() {
        return INSTANCE;
    }

    /**
     * get SvcLineBiz
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcLineBiz(String glblCmpyCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        return getSsmEZDClient().queryObjectList("getSvcLineBiz", ssmPrm);
    }

}
