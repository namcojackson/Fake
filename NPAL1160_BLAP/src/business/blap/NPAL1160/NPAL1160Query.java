/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1160;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8539
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 05/17/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public final class NPAL1160Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1160Query MY_INSTANCE = new NPAL1160Query();

    /**
     * Constructor.
     */
    private NPAL1160Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1160Query
     */
    public static NPAL1160Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of HierarchyType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHierarchyTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getHierarchyTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Position
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPositionPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPositionPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Planning Group
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPlanningGroupPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPlanningGroupPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Transaction
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransactionPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTransactionPulldownList", ssmParam);
    }

    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
    /**
     * Get Pulldown list of Request Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequestTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Request Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestType(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequestType", ssmParam);
    }
    // END 2023/08/29 M.Kikushima [QC#61590, ADD]

    /**
     * Get Pulldown list of Parts/MERCH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPartsMERCHPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPartsMERCHPulldownList", ssmParam);
    }

    /**
     * Search Team
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTeam(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchTeam", ssmParam, sMsg.A);
    }

    /**
     * Search Team PK By Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTeamPKByName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchTeamPKByName", ssmParam);
    }

    /**
     * Check Member By Team PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal checkMemberByTeamPK(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkMemberByTeamPK", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * Check Transaction By Team PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal checkTransactionByTeamPK(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkTransactionByTeamPK", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * Check Location By Team PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal checkLocationByTeamPK(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkLocationByTeamPK", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * overlap check For Member
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal overlapCheckForMember(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("overlapCheckForMember", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * overlap check For Transaction
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal overlapCheckForTransaction(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("overlapCheckForTransaction", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * overlap check For Location
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal overlapCheckForLocation(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("overlapCheckForLocation", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * overlap check For ApvlLimit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal overlapCheckForApvlLimit(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("overlapCheckForApvlLimit", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * Search Member
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchMember(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchMember", ssmParam, sMsg.B);
    }

    /**
     * Search Member PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchMemberForPK(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchMemberForPK", ssmParam);
    }

    /**
     * Search Member Upload
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchMemberForUpload(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchMemberForUpload", ssmParam);
    }

    /**
     * Search Transaction
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTransaction(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchTransaction", ssmParam, sMsg.C);
    }

    /**
     * Search Transaction PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTransactionForPK(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchTransactionForPK", ssmParam);
    }

    /**
     * Search Location
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchLocation(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchLocation", ssmParam, sMsg.D);
    }

    /**
     * Search Location PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchLocationForPK(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchLocationForPK", ssmParam);
    }

    /**
     * Search Approval Limit
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchApprovalLimit(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchApprovalLimit", ssmParam, sMsg.E);
    }

    /**
     * Search Tech Threshold
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTechThreshold(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchTechThreshold", ssmParam, sMsg.K);
    }

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Search Tech Approval Minimum
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTechApprovalMinimum(Map<String, Object> ssmParam, NPAL1160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchTechApprovalMinimum", ssmParam, sMsg.L);
    }
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * <pre>
     * execute SSM id="countAuthPsn" in [NPAL1160Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param psnCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countAuthPsn(String glblCmpyCd, String psnCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnCd", psnCd);

        return getSsmEZDClient().queryObject("countAuthPsn", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="countAuthPsn" in [NPAL1160Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param psnCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countWh(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("countWh", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getBeforeAndAfterPositionLimitAmt" in [NPAL1160Query.xml]
     * </pre>
     * 
     * Add QC#18689-Sol#303
     * @param glblCmpyCd String
     * @param apvlHrchTpCd String
     * @param prchGrpCd String
     * @param apvlHistSrcTpCd String
     * @param apvlTeamPosnTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBeforeAndAfterPositionLimitAmt(String glblCmpyCd, String apvlHrchTpCd, String prchGrpCd, String apvlHistSrcTpCd, String apvlTeamPosnTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("apvlHrchTpCd", apvlHrchTpCd);
        ssmParam.put("prchGrpCd", prchGrpCd);
        ssmParam.put("apvlHistSrcTpCd", apvlHistSrcTpCd);
        ssmParam.put("apvlTeamPosnTpCd", apvlTeamPosnTpCd);

        return getSsmEZDClient().queryObjectList("getBeforeAndAfterPositionLimitAmt", ssmParam);
    }
}
