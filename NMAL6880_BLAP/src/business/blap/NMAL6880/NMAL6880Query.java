/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6880;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public final class NMAL6880Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6880Query MY_INSTANCE = new NMAL6880Query();

    /**
     * Constructor.
     */
    private NMAL6880Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6880Query
     */
    public static NMAL6880Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search Event
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findVendorShipToXref", ssmParam);
    }

    /**
     * Submit Event
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countVendorShipToXref(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countVendorShipToXref", ssmParam);
    }

    /**
     * >> Event
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRtlWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("findRtlWh", ssmParam);
    }

    /**
     * Submit Event
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRtlSwh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("findRtlSwh", ssmParam);
    }
}
