/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1200;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 08/22/2016   CITS       S.Endo                Update          QC#12612
 * 03/01/2023   CITS       R.Kurahashi           Update          QC#61128
 *</pre>
 */
public final class NPAL1200Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1200Query MY_INSTANCE = new NPAL1200Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1200Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1200Query instance.
     * </pre>
     * @return NPAL1200Query instance
     */
    public static NPAL1200Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of SearchOption
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchOptionPulldownList(Map<String, Object> ssmParam) {
        ssmParam.put("srchOptUsrId", getContextUserInfo().getUserId());
        return getSsmEZDClient().queryObjectList("getSearchOptionPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Zone List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getZoneList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getZoneList", ssmParam);
    }

    /**
     * Get Pulldown list of ItemClass List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemClassList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getItemClassList", ssmParam);
    }

    /**
     * search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

    /**
     * get Rtl WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlWh", ssmParam);
    }

    /**
     * Check Insrc zone.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkZone(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkZone", ssmParam);
    }
    
    // QC#61128 Add Start
    /**
     * Get Pulldown list of Tech Request List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechRequestList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechRequestList", ssmParam);
    }
    
    /**
     * Check Insrc zone For Prch Req Tp.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkZoneForPrchReqTp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkZoneForPrchReqTp", ssmParam);
    }
    // QC#61128 Add End
}
