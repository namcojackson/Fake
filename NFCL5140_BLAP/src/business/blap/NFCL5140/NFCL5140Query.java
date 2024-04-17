/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2011   Fujitsu         K.Kimura        Create          N/A
 * 02/24/2011   Fujitsu         K.Kimura        Create          1670
 *</pre>
 */
package business.blap.NFCL5140;

import java.util.Map;

import business.db.AR_ADJ_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * NFCL5050Query.
 */
public final class NFCL5140Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL5140Query MY_INSTANCE = new NFCL5140Query();

    /**
     * Constructor
     */
    public NFCL5140Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL5140Query
     */
    public static NFCL5140Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findArAdjTpForCateAdj( Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("findArAdjTpForCateAdj", ssmParam);
    }

}
