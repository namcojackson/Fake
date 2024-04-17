package business.blap.NWAL1500;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          n/a
 * 2016/02/17   Fujitsu         Y.Taoka         Update          QC#1694
 * 2016/08/01   Fujitsu         T.Ishii         Update          QC#8043
 * 2018/04/12   Fujitsu         K.Ishizuka      Update          QC#23704
 * 2019/05/07   Fujitsu         R.Nakamura      Update          QC#50015
 *</pre>
 */

public final class NWAL1500QueryForInit extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForInit MY_INSTANCE = new NWAL1500QueryForInit();

    /**
     * Constructor.
     */
    private NWAL1500QueryForInit() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForInit getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * get code table recode: code and description text
     * </pre>
     * @param queryParam query condition glblCmpyCd, tblNm
     * @return S21SsmEZDResult query result object (List<Map<String, Object>))
     */
    public S21SsmEZDResult getCodeTableDataList(Map<String, String> queryParam) {
        return getSsmEZDClient().queryObjectList("getCodeTableDataList", queryParam);
    }

    /**
     * <pre>
     * get ORD_LINE_SRC data (All Column)
     * </pre>
     * @param queryParam query condition 
     *     glblCmpyCd: Mandatory
     *     ordLineSrcCatgCd: option
     *     srcPrntVndCd: option
     * @return S21SsmEZDResult query result object (List<Map<String, Object>))
     */
    public S21SsmEZDResult getOrdLineSrcDataList(Map<String, String> queryParam) {
        return getSsmEZDClient().queryObjectList("getOrdLineSrcDataList", queryParam);
    }

    /**
     * <pre>
     * get configuration type list
     * </pre>
     * @param queryParam glblCmpyCd
     * @return S21SsmEZDResult query result object (List<Map<String, Object>))
     */
    public S21SsmEZDResult getConfigTpList(Map<String, String> queryParam) {
        return getSsmEZDClient().queryObjectList("getConfigTpList", queryParam);
    }

    /**
     * <pre>
     * get OrdEntryAct list
     * </pre>
     * @param glblCmpyCd String
     * @param ordEntryActcatgNm String
     * @return S21SsmEZDResult query result object (List<Map<String, Object>))
     */
    public S21SsmEZDResult getOrdEntryActList(String glblCmpyCd, boolean isLogistics, String... ordEntryActcatgNm) { // S21_NA#8043
        Map<String, Object> queryParam = new HashMap<String, Object>(); // S21_NA#8043
        queryParam.put("glblCmpyCd", glblCmpyCd);
        if (ordEntryActcatgNm != null && ordEntryActcatgNm.length > 0) { // S21_NA#8043
            queryParam.put("ordEntryActcatgNm", ordEntryActcatgNm);
        }
        // Add Start 2019/05/07 QC#50015
        if (isLogistics) {
            queryParam.put("isLogistics", ZYPConstant.FLG_ON_Y);
        }
        // Add End 2019/05/07 QC#50015

        return getSsmEZDClient().queryObjectList("getOrdEntryActList", queryParam);
    }

    // 2018/04/12 S21_NA#23704 Add Start
    /**
     * <pre>
     * get Swh Code For Protected
     * </pre>
     * @param queryParam 
     * @return S21SsmEZDResult query result object (List<Map<String, Object>))
     */
    public S21SsmEZDResult getSwhCdForProtected(Map<String, String> queryParam) {
        return getSsmEZDClient().queryObjectList("getSwhCdForProtected", queryParam);
    }
    // 2018/04/12 S21_NA#23704 Add End
}
