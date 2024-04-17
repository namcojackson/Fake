/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1420;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 * 1/29/2018   CITS       T.Wada               Update          QC#23072
 *</pre>
 */
public final class NPAL1420Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1420Query MY_INSTANCE = new NPAL1420Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1420Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1420Query instance.
     * </pre>
     * @return NPAL1420Query instance
     */
    public static NPAL1420Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchNew(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchNew", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchEdit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchEdit", ssmParam);
    }

    /**
     * GetTechnician
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechnician(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechnician", ssmParam);
    }

    /**
     * GetTechnician
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvAvalQty1(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInvAvalQty1", ssmParam);
    }

    /**
     * GetTechnician
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvAvalQty2(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInvAvalQty2", ssmParam);
    }
    /**
     * getPartsUsage
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getPartsUsage(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPartsUsage", ssmParam);
    }
    /**
     * getShippingPlanForSubmit
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getShippingPlanForSubmit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlanForSubmit", ssmParam);
    }
    /**
     * getShippingOrderForSubmit
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getShippingOrderForSubmit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingOrderForSubmit", ssmParam);
    }
    /**
     * getUnusedParts
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getUnusedParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getUnusedParts", ssmParam);
    }
    /**
     * getShippingPlanForParts
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getShippingPlanForParts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlanForParts", ssmParam);
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
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmnfToCmptMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRmnfToCmptMdseCd", ssmParam);
    }
    /**
     * @param ssmParam ssmParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmnTotLborCostAmtBeforeComp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRmnTotLborCostAmtBeforeComp", ssmParam);
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
     * getShippingPlan
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingPlan(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlan", ssmParam);
    }
    /**
     * getWmsAdjInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsAdjInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getWmsAdjInfo", ssmParam);
    }
    /**
     * getMaxRwsRefNum
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getMaxRwsRefNum(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMaxRwsRefNum", ssmParam);
    }
    /**
     * getUsgPartsForStsUpd
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getUsgPartsForStsUpd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getUsgPartsForStsUpd", ssmParam);
    }
}
