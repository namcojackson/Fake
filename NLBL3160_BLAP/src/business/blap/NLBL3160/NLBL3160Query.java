/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3160;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2018/02/06   CITS            K.Ogino         Update          QC#23875
 * 2018/05/29   CITS            S.Katsuma       Update          QC#25232
 *</pre>
 */
public final class NLBL3160Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3160Query MYINSTANCE = new NLBL3160Query();

    /**
     * Constructor.
     */
    private NLBL3160Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3160Query
     */
    public static NLBL3160Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * getSavedSearchOptionList
     * @param glblCmpyCd String
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String glblCmpyCd, String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("srchOptAplId", NLBL3160Constant.BUSINESS_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getPsnNm
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNm(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObject("getPsnNm", queryParams);
    }

    /**
     * getCustomer
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomer(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObject("getCustomer", queryParams);
    }

    /**
     * getBranch
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBranch(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObject("getBranch", queryParams);
    }

    /**
     * getCustomer
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUserAndCoordRel(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getUserAndCoordRel", queryParams);
    }

    /**
     * getScheduledOrders
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getScheduledOrders(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getScheduledOrders", queryParams);
    }

    /**
     * getOrdersToSchedule
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdersToSchedule(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getOrdersToSchedule", queryParams);
    }

    /**
     * getOrdersNotAvalToSchedule
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdersNotAvalToSchedule(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getOrdersNotAvalToSchedule", queryParams);
    }

    /**
     * QC#23875
     * countWarehousePermission
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPermissionWhList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPermissionWhList", ssmParam);
    }

    // START 2018/05/29 S.Katsuma [QC#25232,ADD]
    /**
     * getSvcMachTpCdList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachTpCdList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSvcMachTpCd", ssmParam);
    }

    /**
     * getSvcExchgCatgList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcExchgCatgList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSvcExchgCatgList", ssmParam);
    }
    // END 2018/05/29 S.Katsuma [QC#25232,ADD]
}
