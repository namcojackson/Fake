/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1410;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12782
 * 08/16/2016   CSAI            Y.Imazu         Update          QC#13406
 * 08/26/2016   CITS            T.Wada          Update          QC#10915
 * 10/05/2016   CITS            T.Hakodate      Update          QC#13276
 * 10/24/2016   CITS            T.Wada          Update          QC#13875
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 *</pre>
 */
public final class NPAL1410Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1410Query MY_INSTANCE = new NPAL1410Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1410Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1410Query instance.
     * </pre>
     * @return NPAL1410Query instance
     */
    public static NPAL1410Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

    /**
     * searchParts
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchParts", ssmParam);
    }

    /**
     * searchTask
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTask(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchTask", ssmParam);
    }

    /**
     * searchSerial
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSerial(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchSerial", ssmParam);
    }

    /**
     * searchConfigration
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchConfigration(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchConfigration", ssmParam);
    }

    /**
     * searchModel
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchModel(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchModel", ssmParam);
    }

    /**
     * getTechName
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechName", ssmParam);
    }

    /**
     * getWhName
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWhName", ssmParam);
    }

    /**
     * getWhInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWhInfo", ssmParam);
    }

    /**
     * getPartsPk
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPartsPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPartsPk", ssmParam);
    }

    /**
     * getRtlSwhList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwhList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlSwhList", ssmParam);
    }

    /**
     * countLocator
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countLocator(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countLocator", ssmParam);
    }

    /**
     * getRemanRequestForCancel
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRemanRequestForCancel(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRemanRequestForCancel", ssmParam);
    }

    /**
     * getOpenSoForMach
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOpenSoForMach(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getOpenSoForMach", ssmParam);
    }

    /**
     * getOpenSoForMach
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmnfPrtUsgCostAmt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRmnfPrtUsgCostAmt", ssmParam);
    }

    /**
     * getOpenSoForMach
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmnTotLborCostAmt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRmnTotLborCostAmt", ssmParam);
    }

    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmnTotLborCostAmtBeforeComp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRmnTotLborCostAmtBeforeComp", ssmParam);
    }

    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAssetRecovCostAmt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAssetRecovCostAmt", ssmParam);
    }

    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmnfToCmptMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRmnfToCmptMdseCd", ssmParam);
    }

    /**
     * getItemInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getItemInfo", ssmParam);
    }

    /**
     * findSameRemanOrder
     * @param ssmParam Map<String, Object>
     * @return Integer
     */
    public S21SsmEZDResult findSameRemanOrder(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("findSameRemanOrder", ssmParam);
    }

    /**
     * getShippingPlan
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingPlan(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlan", ssmParam);
    }

    /**
     * getShippingPlan
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderType(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getOrderType", ssmParam);
    }

    /**
     * getShippingPlanForParts
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingPlanForParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlanForParts", ssmParam);
    }

    /**
     * getUnusedParts
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUnusedParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getUnusedParts", ssmParam);
    }

    /**
     * getRwsDtlLineNumList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsDtlLineNumList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRwsDtlLineNumList", ssmParam);
    }

    /**
     * getRemanParts
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRemanParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRemanParts", ssmParam);
    }

    /**
     * getRemanASL
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRemanASL(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRemanASL", ssmParam);
    }

    /**
     * getRemanASL
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getItemName", ssmParam);
    }

    /**
     * getShippingOrderForSubmit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingOrderForSubmit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingOrderForSubmit", ssmParam);
    }

    /**
     * getAllTask
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllTask(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getAllTask", ssmParam);
    }

    /**
     * getAllUsage
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllUsage(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getAllUsage", ssmParam);
    }

    /**
     * getPartsUsage
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPartsUsage(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPartsUsage", ssmParam);
    }

    /**
     * getShippingPlanForSubmit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingPlanForSubmit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlanForSubmit", ssmParam);
    }

    /**
     * getAftDeclPnt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAftDeclPnt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAftDeclPnt", ssmParam);
    }

    /**
     * searchPartsTab
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStarndardParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getStarndardParts", ssmParam);
    }

    /**
     * getSerial
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerial(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSerial", ssmParam);
    }

    /**
     * getWmsAdjInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsAdjInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWmsAdjInfo", ssmParam);
    }

    /**
     * getPartsItemInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPartsItemInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getPartsItemInfo", ssmParam);
    }

    // QC#15057
    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */

    public S21SsmEZDResult chkMdseIntangible(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkMdseIntangible", ssmParam);
    }
    // QC#13875
    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxPrtReqLineNum(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMaxPrtReqLineNum", ssmParam);
    }
    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isOpenedSoStdParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("isOpenedSoStdParts", ssmParam);
    }

    //QC#18675 ADD START
    /**
     * searchSoRws
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSoRws(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchSoRws", ssmParam);
    }

    /**
     * getSceOrdTp
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSceOrdTp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSceOrdTp", ssmParam);
    }
    //QC#18675 ADD END
    
    /**
     * getPriorityInventoryCd
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriorityInventoryCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getPriorityInventoryCd", ssmParam);
    }

    /**
     * Add QC#22836
     * Get Pulldown list of Line Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStockStatusPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getStockStatusPulldownList", ssmParam);
    }
}
