/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL9900;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/05/11   Hitachi         K.Yamada        Update          CSA QC#7471
 * 2016/06/15   Hitachi         T.Aoyagi        Update          QC#9685
 * 2017/07/12   Hitachi         M.Kidokoro      Update          QC#18659,19534
 * 2021/07/02   CITS            K.Ogino         Update          QC#58926
 *</pre>
 */
public final class NSAL9900Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL9900Query INSTANCE = new NSAL9900Query();

    /**
     * Constructor.
     */
    private NSAL9900Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL9900Query
     */
    public static NSAL9900Query getInstance() {
        return INSTANCE;
    }

    /**
     * getTblInfo
     * @param glblCmpyCd String
     * @param tblNm String
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getTblInfo(String glblCmpyCd, String tblNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("tblNm", tblNm);

        return (Map<String, Object>) getSsmEZDClient().queryObject("getTblInfo", params).getResultObject();
    }

    /**
     * getColInfo
     * @param glblCmpyCd String
     * @param tblNm String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getColInfo(String glblCmpyCd, String tblNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("tblNm", tblNm);

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getColInfo", params).getResultObject();
    }

    /**
     * getPullDownData
     * @param glblCmpyCd String
     * @param tblNm String
     * @param colCd String
     * @param colNm String
     * @param physMaintTrgtTblNm String
     * @param physMaintTrgtColNm String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    //public List<Map<String, Object>> getPullDownData(String glblCmpyCd, String tblNm, String colCd, String colNm) {
    public List<Map<String, Object>> getPullDownData(String glblCmpyCd, String tblNm, String colCd, String colNm, String physMaintTrgtTblNm, String physMaintTrgtColNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("tblNm", tblNm);
        params.put("colCd", colCd);
        params.put("colNm", colNm);
        // mod start 2016/05/11 CSA Defect#7471
        params.put("physMaintTrgtTblNm", physMaintTrgtTblNm);
        params.put("physMaintTrgtColNm", physMaintTrgtColNm);
        // mod end 2016/05/11 CSA Defect#7471

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getPullDownData", params).getResultObject();
    }

    /**
     * getSearchInfo
     * @param glblCmpyCd String
     * @param searchInfo String
     * @param tblNm String
     * @param whereInfo String
     * @param sortInfo String
     * @param searchLimit String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSearchInfo(String glblCmpyCd, String searchInfo, String tblNm, String whereInfo, String sortInfo, String searchLimit) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("searchInfo", searchInfo);
        params.put("tblNm", tblNm);
        params.put("whereInfo", whereInfo);
        params.put("sortInfo", sortInfo);
        params.put("searchLimit", searchLimit);

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSearchInfo", params).getResultObject();
    }

    /**
     * getRelationInfo
     * @param glblCmpyCd String
     * @param targetValue String
     * @param physMaintTrgtTblNm String
     * @param physMaintTrgtColNm String
     * @param physRelnTblNm String
     * @param physRelnColCd String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    //public List<Map<String, Object>> getRelationInfo(String glblCmpyCd, String targetValue, String physRelnTblNm, String physRelnColCd) {
    public List<Map<String, Object>> getRelationInfo(String glblCmpyCd, String targetValue, String physMaintTrgtTblNm, String physMaintTrgtColNm, String physRelnTblNm, String physRelnColCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("targetValue", targetValue);
        // mod start 2016/05/11 CSA Defect#7471
        if (!hasValue(physRelnTblNm) || !hasValue(physRelnColCd)) {
            params.put("hasPhysRelnInfo", ZYPConstant.FLG_OFF_N);
        } else {
            params.put("hasPhysRelnInfo", ZYPConstant.FLG_ON_Y);
        }
        params.put("physRelnTblNm", physRelnTblNm);
        params.put("physRelnColCd", physRelnColCd);
        params.put("physMaintTrgtTblNm", physMaintTrgtTblNm);
        params.put("physMaintTrgtColNm", physMaintTrgtColNm);
        // mod end 2016/05/11 CSA Defect#7471
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRelationInfo", params).getResultObject();
    }

    // START 2017/07/12 M.Kidokoro [QC#18659,19534, ADD]
    /**
     * getRelationInfo
     * @param glblCmpyCd String
     * @param targetValue String
     * @param physRelnTblNm String
     * @param physRelnColCd String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getRelationInfoForNumber(String glblCmpyCd, String targetValue, String physRelnTblNm, String physRelnColCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("targetValue", targetValue);
        params.put("physRelnTblNm", physRelnTblNm);
        params.put("physRelnColCd", physRelnColCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRelationInfoForNumber", params).getResultObject();
    }

    // END 2017/07/12 M.Kidokoro [QC#18659,19534, ADD]

    // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
    /**
     * @param glblCmpyCd String
     * @param tblNm String
     * @param whereInfo String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getRecordByUniqKey(String glblCmpyCd, String tblNm, String whereInfo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("tblNm", tblNm);
        params.put("whereInfo", whereInfo);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getRecordByUniqKey", params).getResultObject();
    }
    // END 2016/06/15 T.Aoyagi [QC#9685, ADD]

    // START 2021/07/02 [QC#58926, ADD]
    /**
     * getCondSqlTxt
     * @param glblCmpyCd String
     * @param tblNm String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public String getCondSqlTxt(String glblCmpyCd, String tblNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("tblNm", tblNm);

        return (String) getSsmEZDClient().queryObject("getCondSqlTxt", params).getResultObject();
    }
    // END 2021/07/02 [QC#58926, ADD]
}
