/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1070;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1070 Min-Max Planning Inquiry
 * Function Name : The data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            Keiichi Masaki  Create          N/A
 * 02/09/2017   CITS            Y.IWASAKI       Update          QC#17478
 * 02/17/2017   CITS     Takeshi Tokutomi       Update          QC#17572
 * 10/05/2017   CITS            K.Ogino         Update          QC#21229
 * 03/13/2018   CITS            T.Wada          Update          Sol#118(QC#12110)
 * 2018/04/09   CITS            K.Ogino         Update          QC#21229
 * 2018/06/19   CITS            K.Ogino         Update          QC#25857
 * 2018/12/05   Hitachi         J.Kim           Update          QC#18224
 * 2019/07/08   CITS            T.Wada          Update          QC#50988 
 *</pre>
 */
public final class NPAL1070Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NPAL1070Query MY_INSTANCE = new NPAL1070Query();

    /**
     * Constructor.
     */
    private NPAL1070Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NPAL1070Query
     */
    public static NPAL1070Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Item Description
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getItemDescription", ssmParam);
    }

    /**
     * Get Manager Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getManagerName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getManagerName", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1070SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * check Item Master
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkItemMaster(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkItemMaster", ssmParam);
    }

    /**
     * check MRP_INFO
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMrpInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkMrpInfo", ssmParam);
    }

    /** QC#17572 02/17/2017 T.Tokutomi START **/
    /**
     * check MRP_INFO
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMrpInfoForDetail(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkMrpInfoForDetail", ssmParam);
    }
    /** QC#17572 02/17/2017 T.Tokutomi END **/

    /**
     * check MRP_INFO with ORD_TAKE_MDSE
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMrpInfoWithOrdTakeMdse(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkMrpInfoWithOrdTakeMdse", ssmParam);
    }

    /**
     * check Retail WH SWH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkRtlWhSwh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkRtlWhSwh", ssmParam);
    }

    /**
     * check DS Condition Const
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkDsCondConst(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkDsCondConst", ssmParam);
    }

    /**
     * get Copy To MRP_INFO_PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCopyToMrpInfoPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCopyToMrpInfoPk", ssmParam);
    }

    /**
     * getOrdTakeMdse
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdTakeMdse(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getOrdTakeMdse", ssmParam);
    }

    /**
     * countAttData
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countAttData(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countAttData", ssmParam);
    }

    //Sol#118(QC#12110) Add
    /**
     * checkItemMaster4Submit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkItemMaster4Submit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkItemMaster4Submit", ssmParam);
    }

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
     * Add QC#25857 check ASL
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countASLData(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countASLData", ssmParam);
    }

    // START 2018/12/05 J.Kim [QC#18224,ADD]
    /**
     * getRtlWhInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRtlWhInfo", ssmParam);
    }
    // END 2018/12/05 J.Kim [QC#18224,ADD]
    // QC#50988 Add Start
    /**
     * findMrpInfoByPlnName
     */
    public S21SsmEZDResult findMrpInfoByPlnName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findMrpInfoByPlnName", ssmParam);
    }
    // QC#50988 Add End
}
