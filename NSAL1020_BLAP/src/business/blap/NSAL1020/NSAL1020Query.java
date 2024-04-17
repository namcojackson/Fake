/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1020;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26981
 * 2019/01/15   Hitachi         A.Kohinata      Update          QC#29917
 * 2024/04/11   Hitachi         T.Kawasue       Update          QC#63717
 *</pre>
 */
public final class NSAL1020Query extends S21SsmEZDQuerySupport {

    /** instance */
    private static final NSAL1020Query INSTANCE = new NSAL1020Query();

    /**
     * Private constructor
     */
    private NSAL1020Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL1020Query singleton instance
     */
    public static NSAL1020Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get search data
     * @param ssmParam Map<String, Object>
     * @param aCMsgArray NSAL1020_ACMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(Map<String, Object> ssmParam, NSAL1020_ACMsgArray aCMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", ssmParam, aCMsgArray);
    }

    /**
     * The existence check of CPO
     * @param ssmParam Map<String, Object>
     * @return true:exist / false:not exist
     */
    // del start 2019/01/15 QC#29917
    //public boolean existsCpo(Map<String, Object> ssmParam) {
    //    int cnt = (Integer) getSsmEZDClient().queryObject("cntCpo", ssmParam).getResultObject();
    //    return cnt > 0;
    //}
    // del end 2019/01/15 QC#29917

    // START 2018/07/19 K.Kitachi [QC#26981, ADD]
    /**
     * getFltMachPk
     * @param ssmParam Map<String, Object>
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getFltMachPk(Map<String, Object> ssmParam) {
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getFltMachPk", ssmParam).getResultObject();
    }
    // END 2018/07/19 K.Kitachi [QC#26981, ADD]

    // START 2024/04/11 T.Kawasue [QC#63717, ADD]
    /**
     * getSplyReadExclCustCnt
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getSplyReadExclCustCnt(String glblCmpyCd, String slsDt, BigDecimal svcMachMstrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", slsDt);
        params.put("svcMachMstrPk", svcMachMstrPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getSplyReadExclCustCnt", params).getResultObject();
    }

    /**
     * convLineBizTpToSvcLineBiz
     * @param glblCmpyCd String
     * @param lineBizTpCd String
     * @return String
     */
    public String convLineBizTpToSvcLineBiz(String glblCmpyCd, String lineBizTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineBizTpCd", lineBizTpCd);
        return (String) getSsmEZDClient().queryObject("convLineBizTpToSvcLineBiz", params).getResultObject();
    }
    // END 2024/04/11 T.Kawasue [QC#63717, ADD]
}
