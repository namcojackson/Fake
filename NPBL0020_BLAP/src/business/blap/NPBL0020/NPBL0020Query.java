/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0020;

import static business.blap.NPBL0020.constant.NPBL0020Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DB_PARAM_SVC_MACH_MSTR_STS_CD_LIST;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DS_COND_CONST_GRP_ID;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DS_COND_CONST_VAL_TXT_01;
import static business.blap.NPBL0020.constant.NPBL0020Constant.DS_COND_CONST_VAL_TXT_02;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EXPENSE_ORDER;
import static business.blap.NPBL0020.constant.NPBL0020Constant.NPBB0010_DS_ORD_TP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS            Makoto Okigami  Create          N/A
 * 04/07/2016   CITS            K.Ogino         Update          N/A
 * 06/23/2016   CSAI            D.Fukaya        Update          QC#8000
 * 06/30/2016   CSAI            D.Fukaya        Update          QC#7735
 * 07/25/2016   CITS            K.Ogino         Update          QC#9300
 * 08/09/2016   CITS            K.Ogino         Update          QC#12172
 * 01/13/2017   CITS            R.Shimamoto     Update          QC#17098
 * 02/09/2017   CITS            K.Ogino         Update          QC#17483
 * 02/27/2018   CITS            K.Ogino         Update          QC#22518
 * 03/27/2018   CITS            T.Wada          Update          QC#22517 
 * 11/06/2018   CITS            M.Naito         Update          QC#28698
 * 05/17/2019   CITS            M.Naito         Update          QC#50076
 * 09/11/2019   CITS            K.Ogino         Update          QC#52809
 * 10/03/2019   CITS            M.Naito         Update          QC#52809
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 * 11/09/2021   CITS            R.Azucena       Update          QC#58586
 * 11/16/2022   CITS            R.Azucena       Update          QC#60808
 *</pre>
 */
public final class NPBL0020Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPBL0020Query MY_INSTANCE = new NPBL0020Query();

    /**
     * Constructor.
     */
    private NPBL0020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPBL0020Query
     */
    public static NPBL0020Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of Requisition Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequisitionTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequisitionTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Line Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLineTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Line Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStockStatusPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getStockStatusPulldownList", ssmParam);
    }

    /**
     * Get Line Status Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineStatusName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getLineStatusName", ssmParam);
    }

    /**
     * Get Config Type Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigTypeName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getConfigTypeName", ssmParam);
    }

    /**
     * Get Header Status Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderStatusName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getHeaderStatusName", ssmParam);
    }

    /**
     * Get Approval Status Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApprovalStatusName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getApprovalStatusName", ssmParam);
    }

    /**
     * Get Document Source Type Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDocumentSourceTypeName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDocumentSourceTypeName", ssmParam);
    }

    /**
     * Get Requisition Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequisitionType(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRequisitionType", ssmParam);
    }

    /**
     * Get Config Components
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigComponents(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getConfigComponents", ssmParam);
    }

    /**
     * Get Item Description
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getItemDescription", ssmParam);
    }

    /**
     * Get PO Order Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoOrderStatus(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPoOrderStatus", ssmParam);
    }

    /**
     * Get Ship To Address
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToAddress(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShipToAddress", ssmParam);
    }

    /**
     * Find Requester
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRequester(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findRequester", ssmParam);
    }

    /**
     * Find Ship To Supplier
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findShipToSupplier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findShipToSupplier", ssmParam);
    }

    /**
     * Find Ship To Supplier From Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findShipToSupplierFromName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findShipToSupplierFromName", ssmParam);
    }

    /**
     * Find Rtl WH From Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRtlWhFromName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findRtlWhFromName", ssmParam);
    }

    /**
     * Find Rtl WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRtlWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findRtlWh", ssmParam);
    }

    /**
     * Find Rtl SWH From Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRtlSwhFromName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findRtlSwhFromName", ssmParam);
    }

    /**
     * Check Inventory Account
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkInventoryAccount(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkInventoryAccount", ssmParam);
    }

    /**
     * Find Shipping Service Level Carrier Relation
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findShpgSvcLvlCarrierRelation(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findShpgSvcLvlCarrierRelation", ssmParam);
    }

    /**
     * Find Serial Warehouse Relation
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSerialWarehouseRelation(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findSerialWarehouseRelation", ssmParam);
    }

    /**
     * Find Carrier From Rtl WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCarrierFromRtlWH(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findCarrierFromRtlWH", ssmParam);
    }

    /**
     * Find Def Dply Coa Info
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDefDplyCoaInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findDefDplyCoaInfo", ssmParam);
    }

    /**
     * Find Account Branch From Rtl WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAccountBranchFromRtlWH(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findAccountBranchFromRtlWH", ssmParam);
    }

    /**
     * Find Account Acct From Rtl WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAccountAcctFromRtlWH(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findAccountAcctFromRtlWH", ssmParam);
    }

    /**
     * Find PO
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findPO(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findPO", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPBL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPBL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * <pre>
     * Check Existence For CPO data which is not canceled corresponding to PR#
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean (true : Exist / false : Not Exist)
     */
    public boolean checkExistenceForCPO(String glblCmpyCd, String prchReqNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkExistenceForCPO", ssmParam);
        return ((Integer) ssmResult.getResultObject()).intValue() > 0;
    }

    /**
     * <pre>
     * Check Existence For Inventory Order data which is not canceled corresponding to PR#
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean (true : Exist / false : Not Exist)
     */
    public boolean checkExistenceForInventoryOrder(String glblCmpyCd, String prchReqNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkExistenceForInventoryOrder", ssmParam);
        return ((Integer) ssmResult.getResultObject()).intValue() > 0;
    }

    /**
     * <pre>
     * Check Existence For Vendor Return data which is not canceled corresponding to PR#
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean (true : Exist / false : Not Exist)
     */
    public boolean checkExistenceForVendorReturn(String glblCmpyCd, String prchReqNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkExistenceForVendorReturn", ssmParam);
        return ((Integer) ssmResult.getResultObject()).intValue() > 0;
    }

    /**
     * <pre>
     * Check Existence For Work Order data which is not canceled corresponding to PR#
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean (true : Exist / false : Not Exist)
     */
// QC#56543 2020/05/01 Start
//   public boolean checkExistenceForWorkOrder(String glblCmpyCd, String prchReqNum) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("prchReqNum", prchReqNum);
//        ssmParam.put("wrkOrdSts_cancelled", WRK_ORD_STS.CANCELLED);
//        ssmParam.put("wrkOrdSts_soCancelled", WRK_ORD_STS.SO_CANCELLED);
//        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkExistenceForWorkOrder", ssmParam);
//        return ((Integer) ssmResult.getResultObject()).intValue() > 0;
//    }
    // QC#56543 2020/05/01 End

    /**
     * Get Config Item Line Info
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigItemLineInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getConfigItemLineInfo", ssmParam);
    }

    /**
     * Get PR Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrchReqSts(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("findPrchReqSts", ssmParam);
    }

    /**
     * Get Ship to Cust Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustName(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getShipToCustName", ssmParam);
    }

    /**
     * Get Ship to Cust Cd
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustCd(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShipToCustCd", ssmParam);
    }

    /**
     * Get CCY_CD
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCcyCd(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getCcyCd", ssmParam);
    }

    /**
     * getShipToCustInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustInfo(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getShipToCustInfo", ssmParam);
    }

    /**
     * getCoaGlCmbn4Seg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaGlCmbn4Seg(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getCoaGlCmbn4Seg", ssmParam);
    }

    /**
     * <pre>
     * Check Existence For CPO data which is not close corresponding to PR#
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean (true : close / false : not close)
     */
    public boolean checkExistenceForCPOClose(String glblCmpyCd, String prchReqNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("closeSts", ORD_HDR_STS.CLOSED);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkExistenceForCPOClose", ssmParam);

        if (ssmResult.isCodeNormal()) {
            return false;
        }
        return true;
    }

    /**
     * Check open SO data
     * @param glblCmpyCd String
     * @param prchReqNum String
     * @return boolean (true : open / false : not open)
     */
    public boolean checkOpenSO(String glblCmpyCd, String prchReqNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flagN", ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkOpenSO", ssmParam);
        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }


// QC#56543 2020/05/01 Start
//    /**
//     * Check open WO data
//     * @param glblCmpyCd String
//     * @param prchReqNum String
//     * @return boolean (true : open / false : not open)
//     */
//    public boolean checkOpenWorkOrder(String glblCmpyCd, String prchReqNum) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("prchReqNum", prchReqNum);
//        List<String> wrkOrdStsCdList = new ArrayList<String>();
//        wrkOrdStsCdList.add(WRK_ORD_STS.CLOSED);
//        wrkOrdStsCdList.add(WRK_ORD_STS.CANCELLED);
//        wrkOrdStsCdList.add(WRK_ORD_STS.SO_CANCELLED);
//        ssmParam.put("wrkOrdStsCdList", wrkOrdStsCdList);
//
//        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkOpenWorkOrder", ssmParam);
//        if (ssmResult.isCodeNormal()) {
//            return true;
//        }
//        return false;
//    }
// QC#56543 2020/05/01 End

    /**
     * Check Item Number
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkItemNumber(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkItemNumber", ssmParam);
    }

    /**
     * Execute DB Query
     * @param statementId String
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult Return to Single record
     */
    public S21SsmEZDResult executeQueryResultSingle(String statementId, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject(statementId, ssmParam);
    }

    /**
     * Execute DB Query
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult Return to M record
     */
    public S21SsmEZDResult executeQueryResultMulti(String statementId, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList(statementId, ssmParam);
    }
    /**
     * getDefaultVendorWh
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDefaultVendorWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDefaultVendorWh", ssmParam);
    }
    /**
     * checkVendorWh
     * @param glblCmpyCd
     * @param rtlWhCd
     * @param prntVndCd
     * @return
     */
    public boolean checkVendorWh(String glblCmpyCd, String rtlWhCd, String prntVndCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("prntVndCd", prntVndCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkVendorWh", ssmParam);
        if (result.isCodeNormal()) {

            Integer count = (Integer) result.getResultObject();
            if (count.intValue() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Get Pulldown Service Level
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgSvcLvlPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShpgSvcLvlPulldownList", ssmParam);
    }

    /**
     * Add QC#22518
     * getShipToCoaMap
     * @param glblCmpyCd
     * @param shipToCustCd
     * @return Map<String, Object>
     */
    public Map<String, Object> getShipToCoaMap(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getShipToCoaMap", ssmParam);

        if (result.isCodeNormal()) {

            return (Map<String, Object>) result.getResultObject();
        }

        return null;
    }
    /**
     * getAddrByPost
     * @param glblCmpyCd
     * @param postCd
     * @return
     */
    public S21SsmEZDResult getAddrByPost(String glblCmpyCd, String postCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("postCd", postCd);

        return getSsmEZDClient().queryObjectList("getAddrByPost", params);
    }

    // START 2018/11/06 M.Naito [QC#28698,ADD]
    /**
     * checkExistConfig
     * @param glblCmpyCd
     * @param mdseCd
     * @param serNum
     * @param curLocNum
     * @param stkStsCd
     * @return boolean
     */
    public boolean checkExistConfig(String glblCmpyCd, String mdseCd, String serNum, String curLocNum, String stkStsCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("curLocNum", curLocNum);
        ssmParam.put("stkStsCd", stkStsCd);
        List<String> svcMachMstrStsCdList = new ArrayList<String>(2);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD_LIST, svcMachMstrStsCdList);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkExistConfig", ssmParam);
        if (result.isCodeNormal()) {

            Integer count = (Integer) result.getResultObject();
            if (count.intValue() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    // END 2018/11/06 M.Naito [QC#28698,ADD]

    // START 2019/05/17 M.Naito [QC#50076,ADD]
    /**
     * Get WH Category Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhCatgCd(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getRtlWhCatgCd", ssmParam);
    }

    /**
     * checkDefSwhByWhCatg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkDefSwhByWhCatg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("checkDefSwhByWhCatg", ssmParam);
    }
    // END 2019/05/17 M.Naito [QC#50076,ADD]

    // QC#52809 Add
    public Map<String, Object> getOrdInfo(String glblCmpyCd, boolean isConfig) {

        Map<String, Object> ordInfoMap = null;

        // Get Order Information
        if (isConfig) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            param.put(DS_COND_CONST_GRP_ID, NPBB0010_DS_ORD_TP);
            param.put(DS_COND_CONST_VAL_TXT_01, ZYPConstant.FLG_ON_Y);
            param.put(DS_COND_CONST_VAL_TXT_02, EXPENSE_ORDER);

            ordInfoMap = (Map<String, Object>) getSsmEZDClient().queryObject("getOrdInfo", param).getResultObject();
            if (ordInfoMap == null) {
                return null;
            }
        } else {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            param.put(DS_COND_CONST_GRP_ID, NPBB0010_DS_ORD_TP);
            param.put(DS_COND_CONST_VAL_TXT_01, ZYPConstant.FLG_OFF_N);
            param.put(DS_COND_CONST_VAL_TXT_02, EXPENSE_ORDER);

            ordInfoMap = (Map<String, Object>) getSsmEZDClient().queryObject("getOrdInfo", param).getResultObject();
            if (ordInfoMap == null) {
                return null;
            }
        }

        return ordInfoMap;
    }
    // QC#52809 End

    // START 2019/10/03 M.Naito [QC#52809,ADD]
    /**
     * get Bill To Customer Info
     * @param glblCmpyCd String
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info
     */
    public S21SsmEZDResult getBillToCustInfo(String glblCmpyCd, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustInfo", params);
    }
    // END 2019/10/03 M.Naito [QC#52809,ADD]

    // QC#56867 Add Start
    public boolean isExistDsImptOrd(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum, List<String> imptStsList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("prchReqLineNum", prchReqLineNum);
        ssmParam.put("prchReqLineSubNum", prchReqLineSubNum);
        ssmParam.put("imptStsList", imptStsList);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("isExistDsImptOrd", ssmParam);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }

    public Map<String, Object> findCpoInfo(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("prchReqLineNum", prchReqLineNum);
        ssmParam.put("prchReqLineSubNum", prchReqLineSubNum);
        Map<String, Object> ordInfoMap = (Map<String, Object>)getSsmEZDClient().queryObject("findCpoDtl", ssmParam).getResultObject();

        return ordInfoMap;
    }

    public boolean checkOpenSODtl(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("prchReqLineNum", prchReqLineNum);
        ssmParam.put("prchReqLineSubNum", prchReqLineSubNum);
        ssmParam.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flagN", ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkOpenSO", ssmParam);
        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }
    // QC#56867 Add End

    // START 2021/11/09 R.Azucena[QC#58586, ADD]
    /**
     * Get available single quantity in SVC_MACH_MSTR 
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAvailableSingleQty(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAvailableSingleQty", ssmParam);
    }
    // END 2021/11/09 R.Azucena[QC#58586, ADD]

    // START 2022/11/16 R.Azucena[QC#60808, ADD]
    /**
     * getServiceExchangeItemCnt 
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getServiceExchangeItemCnt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getServiceExchangeItemCnt", ssmParam);
    }
    // END 2022/11/16 R.Azucena[QC#60808, ADD]
}
