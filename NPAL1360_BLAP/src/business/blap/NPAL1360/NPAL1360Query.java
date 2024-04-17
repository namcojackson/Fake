/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1360;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : SQL Query
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 02/16/2017   CITS            R.Shimamoto     Update          QC#17439
 * 08/30/2017   CITS            T.Hakodate      Update          Sol#069(QC#18270)
 * 03/14/2018   CITS            K.Fukumura      Update          QC#22324
 * 07/10/2018   CITS            K.Ogino         Update          QC#26243
 *</pre>
 */

public final class NPAL1360Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NPAL1360Query MY_INSTANCE = new NPAL1360Query();

    /**
     * Constructor.
     */
    private NPAL1360Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NPAL1360Query
     */
    public static NPAL1360Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Kit Item Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getKitItemName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getKitItemName", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param cMsg NPAL1360CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1360CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, cMsg.A);
    }

    /**
     * Get Kit Item Effect Date
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getKitItemEffectDate(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getKitItemEffectDate", ssmParam);
    }

    /**
     * Get Work Order Serial
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorkOrderSerial(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWorkOrderSerial", ssmParam);
    }

    /**
     * Check Kit Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkKitItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkKitItem", ssmParam);
    }

    /**
     * Check Kit Item Effect Date
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkKitItemEffectDate(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getKitItemEffectDate", ssmParam);
    }

    /**
     * Get Component Item Info
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getComponentItemsInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getComponentItemsInfo", ssmParam);
    }

    /**
     * Get Available Inventory
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAvailableInventory(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getAvailableInventory", ssmParam);
    }

    /**
     * Get Unkit Component Item Information
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUnkitComponentItemInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getUnkitComponentItemInfo", ssmParam);
    }

    /**
     * Check Configuration
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkConfiguration(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkConfiguration", ssmParam);
    }

    /**
     * Check Service Machine Master
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkServiceMachineMaster(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkServiceMachineMaster", ssmParam);
    }

    /**
     * Get Shipping Plan
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingPlan(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlan", ssmParam);
    }

    
    // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) START
    /**
     * Get Shipping Plan Detail
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingPlanDetail(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlanDetail", ssmParam);
    }

    /**
     * getWrkRwsSerKit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrkRwsSerKit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWrkRwsSerKit", ssmParam);
    }

    /**
     * getWrkRwsSerUnKit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrkRwsSerUnKit(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWrkRwsSerUnKit", ssmParam);
    }

    /**
     * getWrkOrdDtl
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrkOrdDtl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWrkOrdDtl", ssmParam);
    }

    // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) END
    
    
    /**
     * Get RWS
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRws(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRws", ssmParam);
    }

    /**
     * Get Kit BOM
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getKitBom(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getKitBom", ssmParam);
    }

    // 2018/03/14 Add Start
    /**
     * Get Cmpsn Msg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCmpsnMsg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCmpsnMsg", ssmParam);
    }
    // 2018/03/14 Add End

    // QC#17439 Add.
    /**
     * check PR Close Detail
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkPrCloseDetail(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkPrCloseDetail", ssmParam);
    }

    /**
     * ADD QC#26243 getNonSerSvcMachMstrList
     * @param bizMsg NLCL0090CMsg
     * @param mdseCd String
     * @param invtyLocCd String
     * @param rownum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNonSerSvcMachMstrList(NPAL1360CMsg cMsg, String mdseCd, String invtyLocCd, int rownum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("invtyLocCd", invtyLocCd);
        params.put("locStsCd", LOC_STS.DC_STOCK);
        params.put("stkStsCd", STK_STS.GOOD);
        params.put("mdseCd", mdseCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("rownum", rownum);
        params.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);

        return getSsmEZDClient().queryObjectList("getNonSerSvcMachMstrList", params);
    }
}
