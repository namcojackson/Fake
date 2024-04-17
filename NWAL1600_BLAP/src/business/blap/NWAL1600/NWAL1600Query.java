/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1600;

import static business.blap.NWAL1600.constant.NWAL1600Constant.ROW_NUM;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1600Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/27   Fujitsu         T.Murai         Update          S21_NA#4618
 * 2017/11/10   Fujitsu         T.Aoi           Update          S21_NA#20146
 *</pre>
 */
public final class NWAL1600Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1600Query MY_INSTANCE = new NWAL1600Query();

    /**
     * Private constructor
     */
    private NWAL1600Query() {
        super();
    }

    /**
     * Get the NWAL1600Query instance.
     * @return NWAL1600Query instance
     */
    public static NWAL1600Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSlsRepDetail
     * @param glblCmpyCd String
     * @param slsRepCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSlsRepDetail(String glblCmpyCd, String slsRepCd) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("slsRepTocCd", slsRepCd);

        return getSsmEZDClient().queryObjectList("getSlsRepDetail", ssmMap);
    }

    /**
     * getCntSalesSlsrep
     * @param glblCmpyCd String
     * @param slsRepCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntSalesSlsrep(String glblCmpyCd, String slsRepCd) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("slsRepTocCd", slsRepCd);
        ssmMap.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmMap.put("orgStruTp", "BOS");
        ssmMap.put("rowNum", ROW_NUM);

        return getSsmEZDClient().queryObject("isSalesSlsrep", ssmMap);
    }

    /**
     * getSalesRepList
     * @param ssmParam Map<String, Object>
     * @return SalesRepList
     */
    public S21SsmEZDResult getSalesRepList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getSalesRepList", ssmParam);
    }

    // 2017/11/10 S21_NA#20146 Add Start
    /**
     * getSalesRepListForNonQuota
     * @param ssmParam Map<String, Object>
     * @return SalesRepList
     */
    public S21SsmEZDResult getSalesRepListForNonQuot(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getSalesRepListForNonQuot", ssmParam);
    }
    // 2017/11/10 S21_NA20146 Add End

    // S21_NA#4618 Add Start
    /**
     * getQuotaRoleList
     * @param glblCmpyCd String
     * @return quoteRoleList
     */
    public S21SsmEZDResult getQuotaRoleList(String glblCmpyCd) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("quotaFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getRoleList", ssmMap);
    }

    /**
     * getQuotaRoleList
     * @param glblCmpyCd String
     * @return nonQuoteRoleList
     */
    public S21SsmEZDResult getNonQuotaRoleList(String glblCmpyCd) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        //ssmMap.put("nonQuotaFlg", ZYPConstant.FLG_ON_Y); // S21_NA DEL QC#20426(Sol#092)

        return getSsmEZDClient().queryObjectList("getRoleList", ssmMap);
    }
    // S21_NA#4618 Add End
    
}
