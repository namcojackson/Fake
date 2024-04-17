/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1050;

import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_SAVE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_SEARCH;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   CITS            N Akaishi       Create          n/a
 *</pre>
 */
public final class NLCL1050Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL1050Query MY_INSTANCE = new NLCL1050Query();

    /**
     * Constructor.
     */
    private NLCL1050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL1050Query
     */
    public static NLCL1050Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * 
     * getAbcAnlsClsList
     * 
     * @param glblCmpyCd String
     * @param abcAnlsClsInfo String
     * @param evemtName String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAbcAnlsClsList(String glblCmpyCd, String abcAnlsClsInfo, String evemtName) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        if (evemtName.equals(EVENT_NM_NLCL1050_SEARCH)) {
            params.put("abcAnlsClsNm", abcAnlsClsInfo);
        } else if (evemtName.equals(EVENT_NM_NLCL1050_SAVE)) {
            params.put("abcAnlsClsNum", abcAnlsClsInfo);
        }

        return getSsmEZDClient().queryObjectList("getAbcAnlsClsListInfo", params);
    }

    /**
     * get AbcAnlsCls List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAbcAnlsClsList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAbcAnlsClsList", ssmParam);
    }

    /**
     * check AbcAnlsAsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkAbcAnlsAsg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("checkAbcAnlsAsg", ssmParam);
    }

    /**
     * check AbcAnlsRslt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkAbcAnlsRslt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("checkAbcAnlsRslt", ssmParam);
    }

}
