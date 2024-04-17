/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1130;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/11   CITS            K.Ogino         Update          QC#21229
 * 2018/07/20   CITS            T.hakodate      Update          QC#20962
 * 2018/12/21   CITS            T.Tokutomi      Update          QC#29214
 *</pre>
 */
public class NPAL1130Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1130Query INSTANCE = new NPAL1130Query();

    /**
     * Constructor.
     */
    public NPAL1130Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1130Query
     */
    public static NPAL1130Query getInstance() {
        return INSTANCE;
    }

    /**
     * get MRP Info
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMrpInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMrpInfo", ssmParam);
    }

    // QC#20962 ADD START
    /**
     * get WH Info
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlWhInfo", ssmParam);
    }

    // QC#20962 ADD END
    
    
    /**
     * Add QC#21229
     * getMdseCd
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMdseCd", ssmParam);
    }

    /**
     * getCompatibleItem
     * QC#29214 Add method.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCompatibleItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCompatibleItem", ssmParam);
    }
}
