/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0640;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS         Makoto Okigami     Create          N/A
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 05/08/2019   CITS            T.Tokutomi      Update          QC#50029
 *</pre>
 */
public final class NLCL0640Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0640Query MY_INSTANCE = new NLCL0640Query();

    /**
     * Constructor.
     */
    private NLCL0640Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0640Query
     */
    public static NLCL0640Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Count Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountStatus(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCountStatus", ssmParam);
    }

    /**
     * Get PI Controls from PI Numer
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPIControlsFromPINumer(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPIControlsFromPINumer", ssmParam);
    }

    /**
     * Get TechLocInfo from Tech Location
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechLocInfoFromTechLocation(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechLocInfoFromTechLocation", ssmParam);
    }

    /**
     * Get TechLocInfo from PI Count Header
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechLocInfoFromPICountHeader(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechLocInfoFromPICountHeader", ssmParam);
    }

    /**
     * Count Serial
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSerial(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("countSerial", ssmParam);
    }

    /**
     * Count Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("countItem", ssmParam);
    }

    /**
     * Get Serial Number Range
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerialNumRange(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSerialNumRange", ssmParam);
    }

    /**
     * Get Merchandise Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMerchandiseName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMerchandiseName", ssmParam);
    }

    /**
     * Get PI Control
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPIControl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPIControl", ssmParam);
    }

    /**
     * Get PI Count Header
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPICountHeader(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPICountHeader", ssmParam);
    }

    /**
     * Get PI Count Detail
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPICountDetail(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPICountDetail", ssmParam);
    }

    /**
     * Get Counting Qty
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountingQty(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCountingQty", ssmParam);
    }

    /**
     * Get Inventory Qty
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInventoryQty(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInventoryQty", ssmParam);
    }

    /**
     * Get Machine Master Qty
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachineMasterQty(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMachineMasterQty", ssmParam);
    }

    /**
     * Get Inventory Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInventoryItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInventoryItem", ssmParam);
    }

    /**
     * Get Machine Master Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachineMasterItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMachineMasterItem", ssmParam);
    }

    /**
     * Count Variance Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countVarianceItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("countVarianceItem", ssmParam);
    }

    /**
     * Get ReCount Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReCountItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getReCountItem", ssmParam);
    }

    /**
     * Get Additional Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdditionalItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getAdditionalItem", ssmParam);
    }

    /**
     * Get ReCount WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReCountWH(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getReCountWH", ssmParam);
    }

    // START 2018/12/03 T.Ogura [QC#27978,DEL]
//    /**
//     * Get Physical Inventory Status Name
//     * @param ssmParam Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getPhysicalInventoryStatusName(Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryObjectList("getPhysicalInventoryStatusName", ssmParam);
//    }
    // END   2018/12/03 T.Ogura [QC#27978,DEL]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Get ReCounting Not Input Counting Detail
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReCountNotInputCntDtl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getReCountNotInputCntDtl", ssmParam);
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    /**
     * adjustmentPiCount
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult adjustmentPiCount(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("adjustmentPiCount", ssmParam);
    }

    /**
     * summaryPICountHeader
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult summaryPICountHeader(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("summaryPICountHeader", ssmParam);
    }

    /**
     * summaryPIControl
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult summaryPIControl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("summaryPIControl", ssmParam);
    }
}
