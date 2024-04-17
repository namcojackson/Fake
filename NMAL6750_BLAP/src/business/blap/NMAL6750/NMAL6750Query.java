/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6750;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * PR Release  NPAL1030Query
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/05   Fujitsu         J.Sakamoto      Create          N/A
 * 2017/08/10   Fujitsu         H.Nagashima     Update          S21_NA#8598
 * 2018/07/31   Fujitsu         H.Tomimatsu     Update          S21_NA#26565
 *</pre>
 */

public final class NMAL6750Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6750Query MY_INSTANCE = new NMAL6750Query();

    /**
     * Constructor.
     */
    private NMAL6750Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1030Query
     */
    public static NMAL6750Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Get Resource List
     * </pre>
     * @param queryParam SSM Query Parameter
     * @param sMsg NMAL6750SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactPsnInfo(Map<String, Object> queryParam, NMAL6750SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsg("getContactPsnInfo", queryParam, sMsg);
    }

    /**
     * <pre>
     * Get Resource List
     * </pre>
     * @param queryParam SSM Query Parameter
     * @param sMsg NMAL6750SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedAccountInfo(Map<String, Object> queryParam, NMAL6750_ASMsg sMsg) {
        return getSsmEZDClient().queryEZDMsg("getRelatedAccountInfo", queryParam, sMsg);
    }

    /**
     * <pre>
     * Get Resource List
     * </pre>
     * @param queryParam SSM Query Parameter
     * @param sMsg NMAL6750SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedAccountList(Map<String, Object> queryParam, NMAL6750SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getRelatedAccountList", queryParam, sMsg.A);
    }

    /**
     * <pre>
     * Get Resource List
     * </pre>
     * @param queryParam SSM Query Parameter
     * @param sMsg NMAL6750SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactPointList(Map<String, Object> queryParam, NMAL6750SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getContactPointList", queryParam, sMsg.B);
    }

    /**
     * <pre>
     * Get Other Primary Contact
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOtherPrimCtac(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getOtherPrimCtac", queryParam);
    }

    /**
     * <pre>
     * Get Department list
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDeptList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDeptList", queryParam);
    }

    /**
     * search Account Name
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctNm(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getAcctNm", queryParam);
    }

    // QC#8598 add Start
    /**
     * get Contact Person Relation List
     * @param queryParam SSM Query Parameter
     * @param sMsg NMAL6750SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactRelationList(Map<String, Object> queryParam, NMAL6750SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getContactRelationList", queryParam, sMsg.R);
    }
    /**
     * get Auto Format Contact Type List
     * @param queryParam SSM Query Parameter
     * @param sMsg NMAL6750SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAutoFormatContactTypeList(Map<String, Object> queryParam, NMAL6750SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getAutoFormatContactTypeList", queryParam, sMsg.T);
    }
    /**
     * search Contact Person By name
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchContactByName(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("searchContactByName", queryParam);
    }
    /**
     * get Mandatory Contact Point Type List
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMandatoryContactPointTypeList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getMandatoryContactPointType", queryParam);
    }
    /**
     * get Contact Person Relation PK
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactRelationPK(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getContactRelationPK", queryParam);
    }
    // QC#8598 add End

    // Add START 2018/07/31 H.Tomimatsu S21_NA#26565
    /**
     * <pre>
     * is Exist Contact tRelation
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return boolean
     */
    public boolean isExistContactRelation(Map<String, Object> queryParam) {

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistContactRelation", queryParam);

        return 0 < (Integer) result.getResultObject();
    }
    // Add END 2018/07/31 H.Tomimatsu S21_NA#26565
}
