/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC002001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDPMsgArray;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CMPSNTMsg;
import business.db.KIT_MAT_INVTYTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.WRK_ORDTMsg;
import business.db.WRK_ORD_DTLTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NPZC002001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Work Order Update API
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/22   Fujitsu         R.Mori          Create          N/A
 * 2009/10/06   Fujitsu         R.Mori          Update          N/A
 * 2009/12/22   Fujitsu         R.Mori          Update          2829
 * 2010/03/16   Fujitsu         R.Mori          Update          3641 RWS Create in Work Order Cancel
 * 2010/05/20   Fujitsu         R.Mori          Update          5649 Partial Cancel Status Cancelled -> Closed
 * 2010/05/20   Fujitsu         R.Mori          Update          5892 When Refurbish cancel, Auto Receive Original Item
 * 2010/05/20   Fujitsu         R.Mori          Update          5906 Update KitMatInvty
 * 2010/11/11   CSAI            D.Fukaya        Update          674  Work Order Cancel
 * 2010/12/30   CSAI            K.Masaki        Update               Delete adding kit material inventory
 * 2016/05/09   CITS            K.Masaki        Update          CSA Project
 * 2018/03/05   CITS            K.Ogino         Update          QC#24053
 *</pre>
 */
public class NPZC002001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    // Return Code of EZDTBLAccessor
    /** Normal end */
    private static final String RTNCD_NORMAL_END = "0000";

    // Message IDs
    /** null input parameter Global Company Code */
    public static final String MSG_ID_NPZM0001E = "NPZM0001E";

    /** null input parameter Work Order Number */
    public static final String MSG_ID_NPZM0002E = "NPZM0002E";

    /** null input parameter Work Order Status */
    public static final String MSG_ID_NPZM0003E = "NPZM0003E";

    /** null input parameter SO Number */
    public static final String MSG_ID_NPZM0028E = "NPZM0028E";

    /** null input parameter PO Number */
    public static final String MSG_ID_NPZM0030E = "NPZM0030E";

    /** null input parameter PO Detail Number */
    public static final String MSG_ID_NPZM0031E = "NPZM0031E";

    /** null input parameter Work Order Detail Number */
    public static final String MSG_ID_NPZM0032E = "NPZM0032E";

    /** null input parameter Sales Date */
    public static final String MSG_ID_NPZM0033E = "NPZM0033E";

    /** Work Order is not found */
    public static final String MSG_ID_NPZM0004E = "NPZM0004E";

    /** invalid input parameter Finished Goods Received Quantity */
    public static final String MSG_ID_NPZM0005E = "NPZM0005E";

    /**
     * more Finished Goods Received Quantity than Finished Goods
     * Balance Quantity
     */
    public static final String MSG_ID_NPZM0006E = "NPZM0006E";

    /** Work Order Status is not Hard Allocated */
    public static final String MSG_ID_NPZM0029E = "NPZM0029E";

    /** Work Order Status is not Shipped or Receiving */
    public static final String MSG_ID_NPZM0007E = "NPZM0007E";

    /** Work Order Status is not Receiving */
    public static final String MSG_ID_NPZM0008E = "NPZM0008E";

    /** Work Order Status is not Receiving Completion */
    public static final String MSG_ID_NPZM0009E = "NPZM0009E";

    /** Finished Goods Balance Quantity is ZERO */
    public static final String MSG_ID_NPZM0010E = "NPZM0010E";

    /** Work Order Status is not Shipped or SO Cancelled */
    public static final String MSG_ID_NPZM0011E = "NPZM0011E";

    /** Can not Cancelled because start Receiving */
    public static final String MSG_ID_NPZM0012E = "NPZM0012E";

    /**
     * Can not SO Cancelled because Work Order Status is not Validated
     */
    public static final String MSG_ID_NPZM0013E = "NPZM0013E";

    /** DB error */
    public static final String MSG_ID_NPZM0014E = "NPZM0014E";

    /** invalid input parameter Work Order Status */
    public static final String MSG_ID_NPZM0015E = "NPZM0015E";

    /** Can not Shipped because Work Order Status is not Validated */
    public static final String MSG_ID_NPZM0016E = "NPZM0016E";

    /** Can not Cancelled */
    public static final String MSG_ID_NPZM0017E = "NPZM0017E";

    /** It failed in the Update processing. */
    public static final String MSG_ID_NMZM0019E = "NMZM0019E";

    /** for Debug */
    private static final int CST_DEBUG_MSG_LVL = 10;

    // 11/11/2010 D.Fukaya modify start
    /** for Use RWS_REF_NUM */
    // private static final String CANCEL = "CANCEL";
    private static final String CANCEL = "-C";

    // 11/11/2010 D.Fukaya modify end

    /** WRK_ORD FIRST_LINE_NUM */
    private static final String WO_HDR_FIRST_LINE_NUM = "000";

    /** WRK_ORD_DTL FIRST_LINE_NUM */
    private static final String WO_DTL_FIRST_LINE_NUM = "001";

    private ONBATCH_TYPE onBatchType;

    /**
     * <pre>Constructor</pre>
     * @param none
     * @throws none
     */
    public NPZC002001() {
        super();

        // initialize SSM
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Work Order Update
     * call execute(NPZC002001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmMsg NPZC002001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NPZC002001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Work Order Update API
     * This API is update Work Order's status and quantity.
     * Switch method by parameter.
     * 
     * [Overview]
     *  0.Get DS Work Order Type(Kitting or Unkitting)
     *
     *  1.Shipped
     *    Update Work Order.
     *    It is excuted when parameter,Work Order Status Code="04"(Shipped). 
     *    Called by "Update SO Confirmation(NLZC2110)"
     *    
     *  2.Receiving
     *    Update Work Order, Work Order Detail.
     *    It is excuted when parameter,Work Order Status Code="05"(Receiving). 
     *    Called by "Update Put Away Confirmation(NLZC2080)"
     *  
     *  3.Receiving Completion or Closed
     *    Update Work Order.
     *    It is excuted when parameter,Work Order Status Code="06"(Receiving Completion) or "07"(Closed). 
     *    Called by "Update RWS Completion(NLZC2090)"
     * 
     *  4.Canceled
     *    Update Work Order, Work Order Detail.
     *    It is excuted when parameter,Work Order Status Code="08"(Cancelled). 
     *    Called by "Work Order Entry(NPAL0030)"
     * 
     *  5.SO Cancelled
     *    Update Work Order.
     *    It is excuted when parameter,Work Order Status Code="09"(SO Cancelled). 
     *    Called by "Update SO Confirmation(NLZC2110)"
     * 
     * </pre>
     * @param inpPrmPMsg NPZC002001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC002001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        this.onBatchType = onBatchType;
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);

        try {

            // Common In-parameter check
            if (!checkInputCommon(msgMap)) {
                // When error, exit process.
                return;
            }

            // get In-parameter
            // Work Order Status Code
            String wkOdStsCd = inpPrmPMsg.wrkOrdStsCd.getValue();

            // get Work Order Type
            WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();
            wrkOrdTMsg = getWorkOrder(msgMap, inpPrmPMsg, wrkOrdTMsg);

            if (wrkOrdTMsg == null) {
                this.addMsgId(msgMap, MSG_ID_NPZM0004E);
                return;
            }

            String dsWorkOrderType = wrkOrdTMsg.dsWrkOrdTpCd.getValue();

            // with process by In-parameter's work order status code
            if (WRK_ORD_STS.SHIPPED.equals(wkOdStsCd)) {
                // Shipped
                executeUpdateShipped(msgMap, onBatchType);

            } else if (WRK_ORD_STS.RECEIVING.equals(wkOdStsCd)) {
                // Receiving
                executeUpdateReceiving(msgMap, onBatchType);

            } else if (WRK_ORD_STS.RECEIVING_COMPLETION.equals(wkOdStsCd)) {
                // Receiving Completion or Closed
                executeUpdateReceivedCompletion(msgMap, onBatchType, dsWorkOrderType);

            } else if (WRK_ORD_STS.CANCELLED.equals(wkOdStsCd)) {
                // Cancelled
                executeUpdateFullCancelled(msgMap, onBatchType, dsWorkOrderType);

            } else if (WRK_ORD_STS.SO_CANCELLED.equals(wkOdStsCd)) {
                // SO Cancelled
                executeUpdateSOCancelled(msgMap, onBatchType, dsWorkOrderType);

            } else {
                // If other code, exit process.
                this.addMsgId(msgMap, MSG_ID_NPZM0015E);
                return;
            }

        } catch (EZDDBRecordLockedException e) {

            printDebugLog("The subjective Work Order record has been locked by another user.");
            throw e;

        } finally {

            // send Message List to EZ API parameter.
            msgMap.flush();

            printDebugLog("*****");
            printDebugLog("<ParameterInfomation Log :" + msgMap.toString() + ">");
            printDebugLog("*****");
        }
    }

    /**
     * <pre>
     *  Execute Shipped process.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType
     */
    private void executeUpdateShipped(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // In-parameter PMsg
        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // In-parameter check for this process
        // none

        WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();

        // get WorkOrder record by key
        wrkOrdTMsg = getActiveWorkOrder(msgMap, inpPrmPMsg, wrkOrdTMsg);
        if (wrkOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return;
        }

        // DB value check
        // When WorkOrderStatusCode get from DB is not 03(VALIDATED),
        // exit process.
        if (!WRK_ORD_STS.VALIDATED.equals(wrkOrdTMsg.wrkOrdStsCd.getValue())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0016E);
            return;
        }

        // set value for update
        wrkOrdTMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.SHIPPED);

        // update WorkOrder Table
        if (!update(msgMap, wrkOrdTMsg)) {
            // When occure DB access error, exit.
            return;
        }

        // ***** Update KitMatInvty (Defect#5906) *****
        if (isKit(wrkOrdTMsg)) {
            updateKitMatInvty(msgMap, wrkOrdTMsg, WRK_ORD_STS.SHIPPED);
        }
    }

    private boolean isKit(WRK_ORDTMsg wrkOrdTMsg) {
        if (WRK_ORD_TP.KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isRefurbish(WRK_ORDTMsg wrkOrdTMsg) {
        if (WRK_ORD_TP.REFURBISH.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isInternalKit(WRK_ORDTMsg wrkOrdTMsg) {
        if (WRK_ORD_TP.INTERNAL_KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *   Execute Receiving process.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType
     */
    private void executeUpdateReceiving(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // in parameter check for this process
        if (!checkInputReceiving(msgMap)) {
            // When error, exit
            return;
        }

        // get input parameter
        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();
        // Finished Goods Received Quantity
        BigDecimal inpFnshGoodsRcvQty = inpPrmPMsg.fnshGoodsRcvQty.getValue();

        // Update Work Order (Common Process KIT and REFURBISH)start--

        // get Work Order record for update
        WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();

        // Retrieve Work Order by key items.
        wrkOrdTMsg = getActiveWorkOrder(msgMap, inpPrmPMsg, wrkOrdTMsg);
        if (wrkOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return;
        }

        BigDecimal fnshGoodsRcvQty = wrkOrdTMsg.fnshGoodsRcvQty.getValue();
        BigDecimal fnshGoodsBalQty = wrkOrdTMsg.fnshGoodsBalQty.getValue();

        // DB value and input parameter compare check
        // When Finished Goods Balance Quantity < In-parameter
        // Finished Goods Received Quantity,
        if (fnshGoodsBalQty.compareTo(inpFnshGoodsRcvQty) < 0) {
            this.addMsgId(msgMap, MSG_ID_NPZM0006E);
            return;
        }

        // DB value check
        // WorkOrderStatus check
        String workOrdStsCd = wrkOrdTMsg.wrkOrdStsCd.getValue();
        if (WRK_ORD_STS.SHIPPED.equals(workOrdStsCd) && BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) == 0) {
            // If WorkOrderStatus = SHIPPED and Finished Goods
            // Received Quantity = 0,
            // It is first time Receiving.

        } else if (WRK_ORD_STS.RECEIVING.equals(workOrdStsCd) && BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) < 0) {
            // If WorkOrderStatus = RECEIVING and Finished Goods
            // Received Quantity > 0,
            // It is after second time Receiving

        } else {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0007E);
            return;
        }

        // *********** Update WorkOrder Header *************
        // set input value to Work Order Table
        // Work Order Status Code
        wrkOrdTMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.RECEIVING);

        fnshGoodsRcvQty = fnshGoodsRcvQty.add(inpFnshGoodsRcvQty);
        fnshGoodsBalQty = fnshGoodsBalQty.subtract(inpFnshGoodsRcvQty);

        wrkOrdTMsg.fnshGoodsRcvQty.setValue(fnshGoodsRcvQty);
        wrkOrdTMsg.fnshGoodsBalQty.setValue(fnshGoodsBalQty);

        // update WorkOrder Table
        if (!update(msgMap, wrkOrdTMsg)) {
            // When occure DB access error, exit.
            return;
        }

        // *********** Update WorkOrder Detail *************
        // get WorkOrderDetail
        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getWrkOrdDtlKeyList", inpPrmPMsg);

        if (ssmResList.isEmpty()) {
            // no record hit
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return;
        }

        for (Map map : ssmResList) {

            String dtlNum = (String) map.get("WRK_ORD_DTL_LINE_NUM");

            WRK_ORD_DTLTMsg wrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
            wrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
            wrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
            wrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue(dtlNum);

            wrkOrdDtlTMsg = getWorkOrderDetail(msgMap, wrkOrdDtlTMsg);
            if (wrkOrdDtlTMsg == null) {
                return;
            }

            if (isRefurbish(wrkOrdTMsg)) {
                // Case Refurbish
                if (!updateWrkOrdDtlForReceivingRefurbish(msgMap, wrkOrdDtlTMsg)) {
                    return;
                }

            } else if (isKit(wrkOrdTMsg)) {
                // Case Kit
                if (!updateWrkOrdDtlForReceivingKit(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg)) {
                    return;
                }
            }
        }

        // ***** Update KitMatInvty (Defect#5906) *****
        if (isKit(wrkOrdTMsg)) {
            updateKitMatInvty(msgMap, wrkOrdTMsg, WRK_ORD_STS.RECEIVING);
        }
    }

    /**
     * <pre>
     * Update Work Order Detail (Receiving REFURBISH)
     * </pre>
     * @param wrkOrdDtlTMsg Work Order TMsg
     * @return true/false
     */
    private boolean updateWrkOrdDtlForReceivingRefurbish(S21ApiMessageMap msgMap, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // set input value to WorkOrderDetail Table
        BigDecimal origGoodsBalQty = wrkOrdDtlTMsg.origGoodsBalQty.getValue();
        origGoodsBalQty = origGoodsBalQty.subtract(inpPrmPMsg.fnshGoodsRcvQty.getValue());
        // set value for update
        wrkOrdDtlTMsg.origGoodsBalQty.setValue(origGoodsBalQty);

        // Update Work Order Detail
        if (!update(msgMap, wrkOrdDtlTMsg)) {
            // When DB error,
            addMsgId(msgMap, MSG_ID_NPZM0014E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Update Work Order Detail(Receiving KIT)
     * </pre>
     * @param wrkOrdDtlTMsg Work Order TMsg
     * @return true/false
     */
    private boolean updateWrkOrdDtlForReceivingKit(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // get Composition Master
        CMPSNTMsg cmpsnTMsg = getCmpsnOfKit(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg);
        if (cmpsnTMsg == null) {
            return false;
        }

        // set input value to WorkOrderDetail Table
        BigDecimal childMdseQty = cmpsnTMsg.childMdseQty.getValue();
        BigDecimal origGoodsBalQty = wrkOrdDtlTMsg.origGoodsBalQty.getValue();
        origGoodsBalQty = origGoodsBalQty.subtract(inpPrmPMsg.fnshGoodsRcvQty.getValue().multiply(childMdseQty));
        // set value for update
        wrkOrdDtlTMsg.origGoodsBalQty.setValue(origGoodsBalQty);

        // Update Work Order Detail
        if (!update(msgMap, wrkOrdDtlTMsg)) {
            // When DB error,
            addMsgId(msgMap, MSG_ID_NPZM0014E);
            return false;
        }

        return true;
    }

    private CMPSNTMsg getCmpsnOfKit(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd01", wrkOrdTMsg.glblCmpyCd.getValue());
        queryParam.put("prntMdseCd01", wrkOrdTMsg.fnshGoodsMdseCd.getValue());
        queryParam.put("childMdseCd01", wrkOrdDtlTMsg.origGoodsMdseCd.getValue());
        queryParam.put("childKitMatCd01", wrkOrdDtlTMsg.kitMatCd.getValue());
        queryParam.put("KIT_MDSE", MDSE_CMPSN_TP.KIT_MDSE);
        queryParam.put("KIT_MATERIAL", MDSE_CMPSN_TP.KIT_MATERIAL);
        CMPSNTMsg cmpsnTMsg = (CMPSNTMsg) ssmBatchClient.queryObject("getCMPSN", queryParam);

        if (cmpsnTMsg == null) {
            addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }
        return cmpsnTMsg;
    }

    /**
     * <pre>
     * Update Work Order Detail(Receiving KIT(Internal Kit))
     * </pre>
     * @param wrkOrdDtlTMsg Work Order TMsg
     * @return true/false
     */
    private boolean updateWrkOrdDtlForReceivingInternalKit(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {

        // Work Order Closed.
        if (wrkOrdTMsg.fnshGoodsOrdQty.getValue().compareTo(wrkOrdTMsg.fnshGoodsRcvQty.getValue()) == 0) {
            wrkOrdDtlTMsg.origGoodsCancQty.setValue(BigDecimal.ZERO);
            wrkOrdDtlTMsg.origGoodsBalQty.setValue(BigDecimal.ZERO);
            // Work Order Cancel after shipped.
        } else if (BigDecimal.ZERO.compareTo(wrkOrdTMsg.fnshGoodsCancQty.getValue()) < 0) {
            wrkOrdDtlTMsg.origGoodsCancQty.setValue(wrkOrdDtlTMsg.origGoodsBalQty.getValue());
            wrkOrdDtlTMsg.origGoodsBalQty.setValue(BigDecimal.ZERO);
        }
        // Update Work Order Detail
        if (!update(msgMap, wrkOrdDtlTMsg)) {
            // When DB error,
            addMsgId(msgMap, MSG_ID_NPZM0014E);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     *  Execute ReceivingCompletion process.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType
     */
    private void executeUpdateReceivedCompletion(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, String dsWorkOrderType) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();

        // Retrieve Work Order by key items.
        wrkOrdTMsg = getActiveWorkOrder(msgMap, inpPrmPMsg, wrkOrdTMsg);
        if (wrkOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return;
        }

        // DB value check
        BigDecimal fnshGoodsRcvQty = null;
        if (!ZYPCommonFunc.hasValue(wrkOrdTMsg.fnshGoodsRcvQty)) {
            // impossible case
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return;
        } else {
            fnshGoodsRcvQty = inpPrmPMsg.fnshGoodsRcvQty.getValue();
        }

        String workOrdStsCd = wrkOrdTMsg.wrkOrdStsCd.getValue();
        if (WRK_ORD_STS.SHIPPED.equals(workOrdStsCd) && BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) <= 0) {
            // If WorkOrderStatus = SHIPPED and Finished Goods
            // Received Quantity = 0,
            // It is first time Receiving.

        } else if (WRK_ORD_STS.RECEIVING.equals(workOrdStsCd) && BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) < 0) {
            // If WorkOrderStatus = RECEIVING and Finished Goods
            // Received Quantity > 0,
            // It is after second time Receiving

        } else {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0008E);
            return;
        }

        // get DB value.
        // Finished Goods Balance Quantity
        BigDecimal fnshGoodsBalQty = BigDecimal.ZERO;
        if (!ZYPCommonFunc.hasValue(wrkOrdTMsg.fnshGoodsBalQty)) {
            // impossible case
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return;
        } else {
            // Finished Goods Balance Quantity
            // Work Order Closed.
            if (wrkOrdTMsg.fnshGoodsBalQty.getValue().compareTo(fnshGoodsRcvQty) == 0) {
                wrkOrdTMsg.fnshGoodsCancQty.setValue(BigDecimal.ZERO);
                wrkOrdTMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.CLOSED);
                // Work Order Cancel after shipped.
            } else if (BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) == 0) {
                wrkOrdTMsg.fnshGoodsCancQty.setValue(wrkOrdTMsg.fnshGoodsBalQty.getValue());
                wrkOrdTMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.CANCELLED);
            }
        }

        wrkOrdTMsg.fnshGoodsRcvQty.setValue(fnshGoodsRcvQty);
        wrkOrdTMsg.fnshGoodsBalQty.setValue(fnshGoodsBalQty);

        // update WorkOrder Table
        if (!update(msgMap, wrkOrdTMsg)) {
            // When occure DB access error, exit.
            return;
        }

        if (isInternalKit(wrkOrdTMsg)) {
            // *********** Update WorkOrder Detail *************
            // get WorkOrderDetail
            List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getWrkOrdDtlKeyList", inpPrmPMsg);

            if (ssmResList.isEmpty()) {
                // no record hit
                this.addMsgId(msgMap, MSG_ID_NPZM0014E);
                return;
            }

            for (Map map : ssmResList) {

                String dtlNum = (String) map.get("WRK_ORD_DTL_LINE_NUM");

                WRK_ORD_DTLTMsg wrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
                wrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                wrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
                wrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue(dtlNum);

                wrkOrdDtlTMsg = getWorkOrderDetail(msgMap, wrkOrdDtlTMsg);
                if (wrkOrdDtlTMsg == null) {
                    return;
                }

                if (!updateWrkOrdDtlForReceivingInternalKit(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg)) {
                    return;
                }
            }
        }

        if (!isAllReceived(wrkOrdTMsg)) {
            if (!cancelWODtls(msgMap, onBatchType, wrkOrdTMsg, dsWorkOrderType)) {
                return;
            }
        }
    }

    /**
     * If this WorkOrder has been Received full Qty, return true.
     * @param wrkOrdTMsg
     * @return
     */
    private boolean isAllReceived(WRK_ORDTMsg wrkOrdTMsg) {
        if (BigDecimal.ZERO.compareTo(wrkOrdTMsg.fnshGoodsCancQty.getValue()) < 0) {
            // If FinishedGoodsBalanceQuantity > 0
            return false;
        }

        return true;
    }

    /**
     * <pre>
     *  Execute Cancelled process.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType
     */
    private void executeUpdateFullCancelled(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, String dsWorkOrderType) {

        // in parameter check for this process
        if (!checkInputShippedCancelled(msgMap)) {
            // When error, exit
            return;
        }

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // Update Work Order start--

        WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();

        // Retrieve Work Order by key items.
        wrkOrdTMsg = getActiveWorkOrder(msgMap, inpPrmPMsg, wrkOrdTMsg);
        if (wrkOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return;
        }

        // get DB value.
        BigDecimal fnshGoodsRcvQty = wrkOrdTMsg.fnshGoodsRcvQty.getValue();

        // DB value check
        // 11/11/2010 D.Fukaya modify start
        if (isKit(wrkOrdTMsg)) {
            // Kitting
            if (BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) == 0 && (WRK_ORD_STS.CLOSED.equals(wrkOrdTMsg.wrkOrdStsCd.getValue()))) {
                // do nothing
            } else {
                this.addMsgId(msgMap, MSG_ID_NPZM0017E);
                return;
            }
        } else if (isRefurbish(wrkOrdTMsg)) {
            // Refurbish
            if (BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) == 0 && (WRK_ORD_STS.SHIPPED.equals(wrkOrdTMsg.wrkOrdStsCd.getValue()))) {
                // do nothing
            } else {
                this.addMsgId(msgMap, MSG_ID_NPZM0017E);
                return;
            }
        }
        // 11/11/2010 D.Fukaya modify end

        BigDecimal fnshGoodsBalQty = wrkOrdTMsg.fnshGoodsBalQty.getValue();

        if (!cancelWrkOrdHeader(msgMap, wrkOrdTMsg, fnshGoodsBalQty)) {
            return;
        }

        if (!cancelWODtls(msgMap, onBatchType, wrkOrdTMsg, dsWorkOrderType)) {
            return;
        }
    }

    private boolean cancelWODtls(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, WRK_ORDTMsg wrkOrdTMsg, String dsWorkOrderType) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // ************** Update WorkOrderDetail *******************
        // get WorkOrderDetail
        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getWrkOrdDtlKeyList", inpPrmPMsg);

        if (ssmResList.isEmpty()) {
            // no record hit
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return false;
        }

        // ======== This process executed when Kitting only =========
        if (isInternalKit(wrkOrdTMsg)) {
            if (WRK_ORD_STS.CANCELLED.equals(wrkOrdTMsg.wrkOrdStsCd.getValue())) {
                // ************** Execute PO Receiving API
                // *******************
                NLZC201001PMsg poRcvPMsg = new NLZC201001PMsg();
                if (!callPORcvApi(msgMap, wrkOrdTMsg, inpPrmPMsg, poRcvPMsg, ssmResList, dsWorkOrderType)) {
                    return false;
                }

                String poRcvNum = poRcvPMsg.poRcvNum.getValue();
                String soNum = getSONum(msgMap, wrkOrdTMsg, inpPrmPMsg);

                // ************** Execute RWS API *******************
                if (!callRwsApi(msgMap, inpPrmPMsg, poRcvNum, soNum)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean cancelWrkOrdHeader(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, BigDecimal fnshGoodsBalQty) {
        // set input value to Work Order Table
        wrkOrdTMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.CANCELLED);

        // 11/11/2010 D.Fukaya modify start
        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();
        String wkOdStsCd = inpPrmPMsg.wrkOrdStsCd.getValue();
        if ((isKit(wrkOrdTMsg) && WRK_ORD_STS.CANCELLED.equals(wkOdStsCd))) {
            // do nothing

        } else {
            wrkOrdTMsg.fnshGoodsCancQty.setValue(fnshGoodsBalQty);
            wrkOrdTMsg.fnshGoodsBalQty.setValue(BigDecimal.ZERO);
        }
        // 11/11/2010 D.Fukaya modify end

        // Update WorkOrder
        if (!update(msgMap, wrkOrdTMsg)) {
            // When occure DB access error, exit.
            return false;
        }
        return true;
    }

    /**
     * <pre>
     *  Execute SO Cancelled process.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType
     * @param dsWorkOrderType
     */
    private void executeUpdateSOCancelled(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, String dsWorkOrderType) {

        // In-parameter PMsg
        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // In-parameter check for this process
        // none

        // get update record from DB
        WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();

        // Retrieve Work Order by key items.
        wrkOrdTMsg = getActiveWorkOrder(msgMap, inpPrmPMsg, wrkOrdTMsg);
        if (wrkOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return;
        }

        // check value get from DB

        // If WorkOrderStatus is not 03(Validated), error.
        String workOrdStsCd = wrkOrdTMsg.wrkOrdStsCd.getValue();
        if (!WRK_ORD_STS.VALIDATED.equals(workOrdStsCd)) {
            this.addMsgId(msgMap, MSG_ID_NPZM0013E);
            return;
        }

        // set value for update
        wrkOrdTMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.SO_CANCELLED);

        // update WorkOrder Table
        if (!update(msgMap, wrkOrdTMsg)) {
            // When DB access error, exit.
            return;
        }

        // ---- add Defect 3641 ----
        // execute SO Cancel -> Cancel
        // get DB value.
        BigDecimal fnshGoodsRcvQty = wrkOrdTMsg.fnshGoodsRcvQty.getValue();
        BigDecimal fnshGoodsBalQty = wrkOrdTMsg.fnshGoodsBalQty.getValue();

        // DB value check
        if (BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) == 0 && (WRK_ORD_STS.SO_CANCELLED.equals(wrkOrdTMsg.wrkOrdStsCd.getValue()))) {
            // do nothing
        } else {
            this.addMsgId(msgMap, MSG_ID_NPZM0017E);
            return;
        }

        if (!cancelWrkOrdHeader(msgMap, wrkOrdTMsg, fnshGoodsBalQty)) {
            return;
        }

        // Update WorkOrderDetail start--
        // get WorkOrderDetail
        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getWrkOrdDtlKeyList", inpPrmPMsg);

        if (ssmResList.isEmpty()) {
            // no record hit
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return;
        }

        int dtlCnt = 0;
        for (Map map : ssmResList) {

            String dtlNum = (String) map.get("WRK_ORD_DTL_LINE_NUM");

            WRK_ORD_DTLTMsg wrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
            wrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
            wrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
            wrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue(dtlNum);

            wrkOrdDtlTMsg = getWorkOrderDetail(msgMap, wrkOrdDtlTMsg);
            if (wrkOrdDtlTMsg == null) {
                return;
            }

            // cancel process
            if (!cancelWrkOrdDtl(msgMap, wrkOrdDtlTMsg)) {
                return;
            }

            if (!isInvtyControlItem(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg, dsWorkOrderType)) {
                // If this item is not target of inventory control,
                continue;
            }

            if (!callAllocNonCpoApi(msgMap, onBatchType, wrkOrdTMsg, wrkOrdDtlTMsg, dsWorkOrderType)) {
                return;
            }

            dtlCnt++;
        }
    }

    private boolean cancelWrkOrdDtl(S21ApiMessageMap msgMap, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {
        // set input value to WorkOrderDetail Table
        wrkOrdDtlTMsg.origGoodsCancQty.setValue(wrkOrdDtlTMsg.origGoodsBalQty.getValue());
        wrkOrdDtlTMsg.origGoodsBalQty.setValue(BigDecimal.ZERO);

        // Update Work Order Detail
        if (!update(msgMap, wrkOrdDtlTMsg)) {
            // When DB error,
            addMsgId(msgMap, MSG_ID_NPZM0014E);
            return false;
        }
        return true;
    }

    private boolean updateKitMatInvty(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, String proc) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getWrkOrdDtlKeyList", inpPrmPMsg);

        for (Map map : ssmResList) {

            String dtlNum = (String) map.get("WRK_ORD_DTL_LINE_NUM");

            WRK_ORD_DTLTMsg wrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
            wrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
            wrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
            wrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue(dtlNum);

            wrkOrdDtlTMsg = getWorkOrderDetail(msgMap, wrkOrdDtlTMsg);
            if (wrkOrdDtlTMsg == null) {
                return false;
            }

            if (!ZYPCommonFunc.hasValue(wrkOrdDtlTMsg.kitMatCd)) {
                // execute process when only KitMaterial
                continue;
            }

            KIT_MAT_INVTYTMsg kitMatInvtyTMsg = getKitMatInvty(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg.kitMatCd.getValue());

            if (proc.equals(WRK_ORD_STS.SHIPPED)) {

                // 2010/12/30 Delete adding kit material inventory --
                // Start --
                if (kitMatInvtyTMsg != null) {
                    // Add KitMatInvty of Kit Vendor
                    // BigDecimal newKitInvtyQty =
                    // kitMatInvtyTMsg.kitInvtyQty.getValue().add(wrkOrdDtlTMsg.origGoodsOrdQty.getValue());
                    // ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitInvtyQty,
                    // newKitInvtyQty);
                    // if (!update(msgMap, kitMatInvtyTMsg)) {
                    // return false;
                    // }
                    // 2010/12/30 Delete adding kit material inventory
                    // -- End --
                } else {
                    // New Record Insert
                    if (!createKitMatInvty(wrkOrdTMsg, wrkOrdDtlTMsg)) {
                        return false;
                    }
                }

            } else if (proc.equals(WRK_ORD_STS.RECEIVING)) {

                if (kitMatInvtyTMsg == null) {
                    // This record should be created when this
                    // WorkOrder Shipped.
                    addMsgId(msgMap, MSG_ID_NPZM0014E);
                    return false;
                }

                // get Composition Master
                CMPSNTMsg cmpsnTMsg = getCmpsnOfKit(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg);
                if (cmpsnTMsg == null) {
                    return false;
                }

                BigDecimal kitMatRcvQty = inpPrmPMsg.fnshGoodsRcvQty.getValue().multiply(cmpsnTMsg.childMdseQty.getValue());

                // Subtract KitMatInvty of Kit Vendor
                BigDecimal newKitInvtyQty = kitMatInvtyTMsg.kitInvtyQty.getValue().subtract(kitMatRcvQty);
                ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitInvtyQty, newKitInvtyQty);
                if (!update(msgMap, kitMatInvtyTMsg)) {
                    return false;
                }

            } else if (proc.equals(WRK_ORD_STS.CANCELLED)) {

                if (kitMatInvtyTMsg == null) {
                    // This record should be created when this
                    // WorkOrder Shipped.
                    addMsgId(msgMap, MSG_ID_NPZM0014E);
                    return false;
                }

                // Subtract KitMatInvty of Kit Vendor
                BigDecimal newKitInvtyQty = kitMatInvtyTMsg.kitInvtyQty.getValue().subtract(wrkOrdDtlTMsg.origGoodsCancQty.getValue());
                ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitInvtyQty, newKitInvtyQty);
                if (!update(msgMap, kitMatInvtyTMsg)) {
                    return false;
                }

            }
        }

        return true;
    }

    private boolean createKitMatInvty(WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {
        KIT_MAT_INVTYTMsg kitMatInvtyTMsg = new KIT_MAT_INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.glblCmpyCd, wrkOrdTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitMatCd, wrkOrdDtlTMsg.kitMatCd);
        ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitVndCd, wrkOrdTMsg.vndCd);
        // 2010/12/30 Delete adding kit material inventory -- Start --
        ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitInvtyQty, new BigDecimal(0));
        // ZYPEZDItemValueSetter.setValue(kitMatInvtyTMsg.kitInvtyQty,
        // wrkOrdDtlTMsg.origGoodsOrdQty);
        // 2010/12/30 Delete adding kit material inventory -- End --
        EZDTBLAccessor.create(kitMatInvtyTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(kitMatInvtyTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return true/false
     */
    private boolean checkInputCommon(S21ApiMessageMap msgMap) {

        // IN-parameter PMsg
        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // input parameter null check
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd)) {
            this.addMsgId(msgMap, MSG_ID_NPZM0001E);
            return false;
        }

        // Work Order Number
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.wrkOrdNum)) {
            this.addMsgId(msgMap, MSG_ID_NPZM0002E);
            return false;
        }

        // Work Order Status Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.wrkOrdStsCd)) {
            this.addMsgId(msgMap, MSG_ID_NPZM0003E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check input parameter(Receiving)
     * </pre>
     * @param msgMap Message Map
     * @return true/false
     */
    private boolean checkInputReceiving(S21ApiMessageMap msgMap) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // Finished Goods Received Quantity
        BigDecimal fnshGoodsRcvQty = inpPrmPMsg.fnshGoodsRcvQty.getValue();

        // Finished Goods Received Quantity Null Check
        if (!ZYPCommonFunc.hasValue(fnshGoodsRcvQty)) {
            this.addMsgId(msgMap, MSG_ID_NPZM0005E);
            return false;
        }

        // When Finished Goods Received Quantity <= 0 , error
        if (BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) >= 0) {
            this.addMsgId(msgMap, MSG_ID_NPZM0005E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check input parameter(Cancelled)
     * </pre>
     * @param msgMap Message Map
     * @return true/false
     */
    private boolean checkInputShippedCancelled(S21ApiMessageMap msgMap) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.slsDt.getValue())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0033E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     *  Check values that is retrieved from DB when Cancelled process.
     * </pre>
     * @param msgMap Message Map
     * @param wrkOrdrStsCd Work Order Status Code
     * @param fnshGoodsRcvQty Finished Goods Received Quantity
     * @param fnshGoodsBalQty Finished Goods Balance Quantity
     * @return true/false
     */
    private boolean checkValueCancelled(S21ApiMessageMap msgMap, String wrkOrdrStsCd, BigDecimal fnshGoodsRcvQty, BigDecimal fnshGoodsBalQty) {

        if (BigDecimal.ZERO.compareTo(fnshGoodsBalQty) < 0 && WRK_ORD_STS.RECEIVING_COMPLETION.equals(wrkOrdrStsCd)) {
            // a. Case, Work Order partial cancel
        } else if (BigDecimal.ZERO.compareTo(fnshGoodsRcvQty) == 0 && (WRK_ORD_STS.SHIPPED.equals(wrkOrdrStsCd) || WRK_ORD_STS.SO_CANCELLED.equals(wrkOrdrStsCd))) {
            // b. Case, Work Order all cancel
        } else {
            // It is not in case a, neither b.
            this.addMsgId(msgMap, MSG_ID_NPZM0017E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check Inventory Control Item.
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param wrkOrdTMsg Work Order TMsg
     * @param wrkOrdDtlTMsg Work Order Detail TMsg
     * @return Result (true: tangible itemNormal, false: intangible
     * item)
     */
    private boolean isInvtyControlItem(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg, String dsWorkOrderType) {

        if (!ZYPCommonFunc.hasValue(wrkOrdDtlTMsg.origGoodsMdseCd)) {
            // This item is KitMaterial
            return false;
        }

        // Is this Inventory Control goods?
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", wrkOrdDtlTMsg.glblCmpyCd);

        if (WRK_ORD_TP.INTERNAL_KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue()) && DS_WRK_ORD_TP.UN_KIT.equals(dsWorkOrderType)) {
            queryParam.put("mdseCd", wrkOrdTMsg.fnshGoodsMdseCd);
        } else {
            queryParam.put("mdseCd", wrkOrdDtlTMsg.origGoodsMdseCd);
        }

        Integer ssmRes = (Integer) ssmBatchClient.queryObject("countInvtyCtrlMdse", queryParam);

        if (ssmRes.intValue() >= 1) {
            // It is inventory control item.
            return true;
        } else {
            // It isn't inventory control item.
            return false;
        }
    }

    /**
     * <pre>
     * Execute Allocation for non CPO API.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @param wrkOrdTMsg Work Order TMsg
     * @param wrkOrdDtlTMsg Work Order Detail TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean callAllocNonCpoApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg, String dsWorkOrderType) {

        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // Set parameter for Allocation for non CPO API.
        NWZC107001PMsg allocApiPMsg = new NWZC107001PMsg();
        allocApiPMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        allocApiPMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        allocApiPMsg.xxRqstTpCd.setValue(NWZC107001.REQ_TP_CANCEL);
        allocApiPMsg.trxHdrNum.setValue(wrkOrdDtlTMsg.wrkOrdNum.getValue());
        allocApiPMsg.trxLineNum.setValue(wrkOrdDtlTMsg.wrkOrdDtlLineNum.getValue());
        allocApiPMsg.trxLineSubNum.setValue(WO_DTL_FIRST_LINE_NUM);
        if (WRK_ORD_TP.KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            allocApiPMsg.trxSrcTpCd.setValue(TRX_SRC_TP.KITTING);
        } else if (WRK_ORD_TP.REFURBISH.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            allocApiPMsg.trxSrcTpCd.setValue(TRX_SRC_TP.REFURBISH);
        } else if (WRK_ORD_TP.INTERNAL_KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            if (DS_WRK_ORD_TP.KIT.equals(dsWorkOrderType)) {
                allocApiPMsg.trxSrcTpCd.setValue(TRX_SRC_TP.INTERNAL_KITTING);
            } else if (DS_WRK_ORD_TP.UN_KIT.equals(dsWorkOrderType)) {
                allocApiPMsg.trxSrcTpCd.setValue(TRX_SRC_TP.INTERNAL_UN_KITTING);
                allocApiPMsg.trxLineNum.setValue(WO_HDR_FIRST_LINE_NUM);
            }
        }

        // Execute Allocation for non CPO API.
        NWZC107001 allocApi = new NWZC107001();
        allocApi.execute(allocApiPMsg, onBatchType);
        return checkApiResult(allocApiPMsg.xxMsgIdList, msgMap);
    }

    /**
     * <pre>
     * Execute Inventory Update API.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @param wrkOrdDtlTMsg Work Order Detail TMsg
     * @param wrkOrdTpCd Work Order Type Code(KIT/REFURBISH)
     * @return Result (true:Normal, false:Error)
     */
    private boolean callInvtyUpdteApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) {

        // Input parameter PMsg
        NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();

        // get receiving WH code
        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        shpgOrdTMsg = getShpgOrder(msgMap, inpPrmPMsg, shpgOrdTMsg, wrkOrdDtlTMsg.soNum.getValue());
        if (shpgOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NMZM0019E);
            return false;
        }

        NLZC002001PMsg invStckOutPMsg = new NLZC002001PMsg();

        // set parameter of stock-out from vender case.
        invStckOutPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
        invStckOutPMsg.invtyLocCd.setValue(wrkOrdTMsg.vndCd.getValue());
        invStckOutPMsg.locStsCd.setValue(LOC_STS.WORK_IN_PROCESS_ORIGINAL);
        invStckOutPMsg.trxRsnCd.setValue(TRX_RSN.VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR);

        setParamForInvtyUpdateAPI(wrkOrdTMsg, wrkOrdDtlTMsg, inpPrmPMsg, shpgOrdTMsg, invStckOutPMsg);

        // Execute Inventory update API.
        NLZC002001 invtyApi = new NLZC002001();
        invtyApi.execute(invStckOutPMsg, onBatchType);
        if (!checkApiResult(invStckOutPMsg.xxMsgIdList, msgMap)) {
            return false;
        }

        NLZC002001PMsg invStckInPMsg = new NLZC002001PMsg();

        // set parameter of stock-in to WH case.
        invStckInPMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
        invStckInPMsg.invtyLocCd.setValue(shpgOrdTMsg.whCd.getValue());
        invStckInPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        invStckInPMsg.trxRsnCd.setValue(TRX_RSN.VENDOR_TRANSFER_STOCK_IN_FROM_VENDOR);

        setParamForInvtyUpdateAPI(wrkOrdTMsg, wrkOrdDtlTMsg, inpPrmPMsg, shpgOrdTMsg, invStckInPMsg);

        invtyApi.execute(invStckInPMsg, onBatchType);
        if (!checkApiResult(invStckInPMsg.xxMsgIdList, msgMap)) {
            return false;
        }

        return true;
    }

    private void setParamForInvtyUpdateAPI(WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg, NPZC002001PMsg inpPrmPMsg, SHPG_ORDTMsg shpgOrdTMsg, NLZC002001PMsg invtyApiPMsg) {
        invtyApiPMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        invtyApiPMsg.xxSysTp.setValue(NLZC002001.SYS_TP_PROCR);
        invtyApiPMsg.mdseCd.setValue(wrkOrdDtlTMsg.origGoodsMdseCd.getValue());
        invtyApiPMsg.stkStsCd.setValue(wrkOrdDtlTMsg.stkStsCd.getValue());
        invtyApiPMsg.xxRqstQty.setValue(wrkOrdDtlTMsg.origGoodsCancQty.getValue());
        invtyApiPMsg.trxCd.setValue(TRX.MOVEMENT);
        invtyApiPMsg.invtyTrxDt.setValue(ZYPDateUtil.getSalesDate());
        invtyApiPMsg.sysSrcCd.setValue(SYS_SRC.S21_PROCUREMENT);
        invtyApiPMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
        invtyApiPMsg.wrkOrdLineNum.setValue(wrkOrdDtlTMsg.wrkOrdDtlLineNum.getValue());
        invtyApiPMsg.shipFromLocCustCd.setValue(wrkOrdTMsg.vndCd.getValue());
        invtyApiPMsg.shipToLocCustCd.setValue(shpgOrdTMsg.whCd.getValue());
        invtyApiPMsg.vndCd.setValue(wrkOrdTMsg.vndCd.getValue());
        invtyApiPMsg.uomCd.setValue(PKG_UOM.EACH);
    }

    private boolean callPORcvApi(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, NPZC002001PMsg inpPrmPMsg, NLZC201001PMsg poRcvPMsg, List<Map> ssmResList, String dsWorkOrderType) {
        // get SO# from WO_DTL(All WO_DTL have same SO#)
        String soNum = getSONum(msgMap, wrkOrdTMsg, inpPrmPMsg);

        // get SO
        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        shpgOrdTMsg = getShpgOrder(msgMap, inpPrmPMsg, shpgOrdTMsg, soNum);
        if (shpgOrdTMsg == null) {
            this.addMsgId(msgMap, MSG_ID_NMZM0019E);
            return false;
        }

        // Create PO Receiving Header Parameter
        createPORcvHdrParam(wrkOrdTMsg, inpPrmPMsg, shpgOrdTMsg, poRcvPMsg);

        if (DS_WRK_ORD_TP.KIT.equals(dsWorkOrderType)) {

            int dtlCnt = 0;
            for (Map map : ssmResList) {

                String dtlNum = (String) map.get("WRK_ORD_DTL_LINE_NUM");

                WRK_ORD_DTLTMsg wrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
                wrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                wrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
                wrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue(dtlNum);

                wrkOrdDtlTMsg = getWorkOrderDetail(msgMap, wrkOrdDtlTMsg);
                if (wrkOrdDtlTMsg == null) {
                    return false;
                }

                if (!isInvtyControlItem(msgMap, wrkOrdTMsg, wrkOrdDtlTMsg, dsWorkOrderType)) {
                    // If this item is not target of inventory
                    // control,
                    continue;
                }

                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                shpgOrdDtlTMsg = getShpgOrderDetail(msgMap, inpPrmPMsg, shpgOrdDtlTMsg, wrkOrdDtlTMsg, dsWorkOrderType);
                if (shpgOrdDtlTMsg == null) {
                    this.addMsgId(msgMap, MSG_ID_NMZM0019E);
                    return false;
                }

                createPORcvDtlParam(wrkOrdTMsg, wrkOrdDtlTMsg, shpgOrdDtlTMsg, poRcvPMsg, dtlCnt);

                dtlCnt++;

                poRcvPMsg.OrdInfoLIst.setValidCount(dtlCnt);
            }

        } else if (DS_WRK_ORD_TP.UN_KIT.equals(dsWorkOrderType)) {

            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();

            WRK_ORD_DTLTMsg wrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
            wrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
            wrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
            wrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue("001");

            wrkOrdDtlTMsg = getWorkOrderDetail(msgMap, wrkOrdDtlTMsg);
            if (wrkOrdDtlTMsg == null) {
                return false;
            }

            shpgOrdDtlTMsg = getShpgOrderDetail(msgMap, inpPrmPMsg, shpgOrdDtlTMsg, wrkOrdDtlTMsg, dsWorkOrderType);

            if (shpgOrdDtlTMsg == null) {
                this.addMsgId(msgMap, MSG_ID_NMZM0019E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(0).mdseCd, wrkOrdTMsg.fnshGoodsMdseCd);
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(0).stkStsCd, shpgOrdDtlTMsg.fromStkStsCd);
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(0).poRcvQty, shpgOrdDtlTMsg.shipQty);
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(0).poRcvTrxLineNum, shpgOrdDtlTMsg.soSlpNum);
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(0).invtyLocCd, wrkOrdTMsg.rcvInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(0).shipFromInvtyLocCd, wrkOrdTMsg.invtyLocCd.getValue() + wrkOrdTMsg.cpltRtlSwhCd.getValue());
            poRcvPMsg.OrdInfoLIst.setValidCount(1);
        }

        NLZC201001 poRcvApi = new NLZC201001();
        poRcvApi.execute(poRcvPMsg, this.onBatchType);

        return checkApiResult(poRcvPMsg.xxMsgIdList, msgMap);
    }

    private boolean callRwsApi(S21ApiMessageMap msgMap, NPZC002001PMsg inpPrmPMsg, String poRcvNum, String soNum) {
        NLZC200001PMsg rwsPMsg = new NLZC200001PMsg();
        rwsPMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        rwsPMsg.sysSrcCd.setValue(SYS_SRC.S21_PROCUREMENT);
        rwsPMsg.inbdSrcTpCd.setValue(INBD_SRC_TP.PO_RECEIVING);
        rwsPMsg.poRcvNum.setValue(poRcvNum);

        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsPMsg, this.onBatchType);

        if (!checkApiResult(rwsPMsg.xxMsgIdList, msgMap)) {
            return false;
        }

        // Get SO Serial#
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd);
        queryParam.put("soNum", soNum);

        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getSoSerNum", queryParam);

        NLZC304001PMsg rwsSerPMsg = new NLZC304001PMsg();

        rwsSerPMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());

        int i = 0;
        for (Map map : ssmResList) {
            String rwsNum = (String) map.get("RWS_NUM");
            String rwsDtlLineNum = (String) map.get("RWS_DTL_LINE_NUM");
            String mdseCd = (String) map.get("MDSE_CD");
            String serNum = (String) map.get("SER_NUM");

            rwsSerPMsg.rwsNum.setValue(rwsNum);
            rwsSerPMsg.SerialList.no(i).rwsDtlLineNum.setValue(rwsDtlLineNum);
            rwsSerPMsg.SerialList.no(i).mdseCd.setValue(mdseCd);
            rwsSerPMsg.SerialList.no(i).serNum.setValue(serNum);
            rwsSerPMsg.SerialList.no(i).putAwayChkStsCd.setValue(ZYPConstant.FLG_OFF_0);
            rwsSerPMsg.SerialList.no(i).serNumSendFlg.setValue(ZYPConstant.FLG_OFF_N);
            i++;
        }

        rwsSerPMsg.SerialList.setValidCount(i);

        if (ssmResList.size() > 0) {
            NLZC304001 rwsSerApi = new NLZC304001();
            rwsSerApi.execute(rwsSerPMsg, this.onBatchType);

            if (!checkApiResult(rwsSerPMsg.xxMsgIdList, msgMap)) {
                return false;
            }
        }

        return true;

    }

    private void createPORcvDtlParam(WRK_ORDTMsg wrkOrdTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, NLZC201001PMsg pMsg, int dtlCnt) {
        // Detail
        ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(dtlCnt).mdseCd, shpgOrdDtlTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(dtlCnt).stkStsCd, shpgOrdDtlTMsg.fromStkStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(dtlCnt).poRcvQty, shpgOrdDtlTMsg.shipQty);
        ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(dtlCnt).poRcvTrxLineNum, shpgOrdDtlTMsg.soSlpNum);
        ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(dtlCnt).invtyLocCd, wrkOrdTMsg.invtyLocCd.getValue() + wrkOrdDtlTMsg.splyRtlSwhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(dtlCnt).shipFromInvtyLocCd, wrkOrdTMsg.rcvInvtyLocCd);
    }

    private void createPORcvHdrParam(WRK_ORDTMsg wrkOrdTMsg, NPZC002001PMsg inpPrmPMsg, SHPG_ORDTMsg shpgOrdTMsg, NLZC201001PMsg pMsg) {
        // Header
        pMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        pMsg.sysSrcCd.setValue(SYS_SRC.S21_PROCUREMENT);

        if (WRK_ORD_TP.INTERNAL_KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            pMsg.sceOrdTpCd.setValue(NLXSceConst.SCE_ORD_TP_CD_KC);
        }

        String rwsRefNum = wrkOrdTMsg.wrkOrdNum.getValue() + CANCEL;
        pMsg.rwsRefNum.setValue(rwsRefNum);

        if (WRK_ORD_TP.INTERNAL_KIT.equals(wrkOrdTMsg.wrkOrdTpCd.getValue())) {
            pMsg.poRcvFromLocTpCd.setValue(LOC_TP.WAREHOUSE);
            pMsg.poRcvEtaDt.setValue(wrkOrdTMsg.rqstRcvDt.getValue());
            pMsg.MsgInfoLIst.no(0).poRcvMsgTxt.setValue(wrkOrdTMsg.wrkOrdMsgTxt.getValue());
            // QC#24053
            pMsg.MsgInfoLIst.setValidCount(1);
        } else {
            pMsg.poRcvFromLocTpCd.setValue(LOC_TP.VENDOR);
            pMsg.poRcvEtaDt.setValue(ZYPDateUtil.getSalesDate());
        }
        if (DS_WRK_ORD_TP.KIT.equals(wrkOrdTMsg.dsWrkOrdTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocCd, wrkOrdTMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, wrkOrdTMsg.invtyLocCd);
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(wrkOrdTMsg.dsWrkOrdTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocCd, wrkOrdTMsg.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, wrkOrdTMsg.rtlWhCd);
        }
        pMsg.poRcvDrctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvTrxHdrNum, shpgOrdTMsg.soNum);
    }

    /**
     * <pre>
     * Check the result for API.
     * </pre>
     * @param msgIdList Result message id list for API.
     * @param msgMap Message Map
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkApiResult(EZDPMsgArray msgIdList, S21ApiMessageMap msgMap) {

        if (msgIdList.getValidCount() > 0) {
            NPZC002001PMsg inpPrmPMsg = (NPZC002001PMsg) msgMap.getPmsg();
            EZDMsg.copy(msgIdList, null, inpPrmPMsg.xxMsgIdList, null);
            inpPrmPMsg.xxMsgIdList.setValidCount(msgIdList.getValidCount());
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * get WorkOrder
     * </pre>
     * @param msgMap Message Map
     * @param inpPrmPMsg In-parameter PMsg
     * @param wrkOrdTMsg
     * @return Search result (When error:Null)
     * @throws EZDDBRecordLockedException
     */
    private WRK_ORDTMsg getActiveWorkOrder(S21ApiMessageMap msgMap, NPZC002001PMsg inpPrmPMsg, WRK_ORDTMsg wrkOrdTMsg) throws EZDDBRecordLockedException {

        // set key item
        wrkOrdTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue()); // GlobalCompanyCode
        wrkOrdTMsg.wrkOrdNum.setValue(inpPrmPMsg.wrkOrdNum.getValue()); // WorkOrderNumber

        // WorkOrder SELECT
        wrkOrdTMsg = (WRK_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(wrkOrdTMsg);

        // When no record hit,
        if (wrkOrdTMsg == null) {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return null;
        }

        // When DB error,
        if (!RTNCD_NORMAL_END.equals(wrkOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }

        // When WorkOrderStatusCode get from DB is Null or Closed or
        // Cancel,
        String workOrdStsCd = wrkOrdTMsg.wrkOrdStsCd.getValue();
        if (!ZYPCommonFunc.hasValue(workOrdStsCd)
        // 11/11/2010 D.Fukaya delete start
                // || WRK_ORD_STS.CLOSED.equals(workOrdStsCd)
                // 11/11/2010 D.Fukaya delete end
                || WRK_ORD_STS.CANCELLED.equals(workOrdStsCd)) {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return null;
        }

        return wrkOrdTMsg;
    }

    /**
     * <pre>
     * get Work Order detail by primary key
     * </pre>
     * @param msgMap Message Map
     * @param inpPrmPMsg In-parameter PMsg
     * @param wrkOrdDtlTMsg
     * @return Search result (When error:Null)
     * @throws EZDDBRecordLockedException
     */
    private WRK_ORD_DTLTMsg getWorkOrderDetail(S21ApiMessageMap msgMap, WRK_ORD_DTLTMsg wrkOrdDtlTMsg) throws EZDDBRecordLockedException {

        wrkOrdDtlTMsg = (WRK_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(wrkOrdDtlTMsg);

        // When no record hit,
        if (wrkOrdDtlTMsg == null) {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return null;
        }

        // When DB error,
        if (!RTNCD_NORMAL_END.equals(wrkOrdDtlTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }

        return wrkOrdDtlTMsg;
    }

    /**
     * <pre>
     * get Work Order by primary key
     * </pre>
     * @param msgMap Message Map
     * @param inpPrmPMsg In-parameter PMsg
     * @param wrkOrdTMsg
     * @return Search result (When error:Null)
     * @throws EZDDBRecordLockedException
     */
    private WRK_ORDTMsg getWorkOrder(S21ApiMessageMap msgMap, NPZC002001PMsg inpPrmPMsg, WRK_ORDTMsg wrkOrdTMsg) throws EZDDBRecordLockedException {

        // set key item
    	wrkOrdTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
    	wrkOrdTMsg.wrkOrdNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

    	wrkOrdTMsg = (WRK_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(wrkOrdTMsg);

        // When no record hit,
        if (wrkOrdTMsg == null) {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return null;
        }

        // When DB error,
        if (!RTNCD_NORMAL_END.equals(wrkOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }

        return wrkOrdTMsg;
    }

    /**
     * <pre>
     * get ShippngOrder by primary key
     * </pre>
     * @param msgMap Message Map
     * @param inpPrmPMsg In-parameter PMsg
     * @param wrkOrdTMsg
     * @return Search result (When error:Null)
     * @throws EZDDBRecordLockedException
     */
    private SHPG_ORDTMsg getShpgOrder(S21ApiMessageMap msgMap, NPZC002001PMsg inpPrmPMsg, SHPG_ORDTMsg shpgOrdTMsg, String soNum) throws EZDDBRecordLockedException {

        // set key item
        shpgOrdTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        shpgOrdTMsg.soNum.setValue(soNum);

        shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKey(shpgOrdTMsg);

        // When no record hit,
        if (shpgOrdTMsg == null) {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return null;
        }

        // When DB error,
        if (!RTNCD_NORMAL_END.equals(shpgOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }

        return shpgOrdTMsg;
    }

    private SHPG_ORD_DTLTMsg getShpgOrderDetail(S21ApiMessageMap msgMap, NPZC002001PMsg inpPrmPMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, WRK_ORD_DTLTMsg wrkOrdDtlTMsg, String dsWorkOrderType) {

        // set key item
        shpgOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        shpgOrdDtlTMsg.soNum.setValue(wrkOrdDtlTMsg.soNum.getValue());
        if (DS_WRK_ORD_TP.KIT.equals(dsWorkOrderType)) {
            shpgOrdDtlTMsg.soSlpNum.setValue(wrkOrdDtlTMsg.wrkOrdDtlLineNum.getValue());
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(dsWorkOrderType)) {
            shpgOrdDtlTMsg.soSlpNum.setValue("001");
        }

        shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlTMsg);

        // When no record hit,
        if (shpgOrdDtlTMsg == null) {
            // add error message
            this.addMsgId(msgMap, MSG_ID_NPZM0004E);
            return null;
        }

        // When DB error,
        if (!RTNCD_NORMAL_END.equals(shpgOrdDtlTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }

        return shpgOrdDtlTMsg;
    }

    private String getSONum(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, NPZC002001PMsg inpPrmPMsg) {
        WRK_ORD_DTLTMsg firstWrkOrdDtlTMsg = new WRK_ORD_DTLTMsg();
        firstWrkOrdDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        firstWrkOrdDtlTMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
        firstWrkOrdDtlTMsg.wrkOrdDtlLineNum.setValue(WO_DTL_FIRST_LINE_NUM);

        firstWrkOrdDtlTMsg = getWorkOrderDetail(msgMap, firstWrkOrdDtlTMsg);
        if (firstWrkOrdDtlTMsg == null) {
            return null;
        }

        return firstWrkOrdDtlTMsg.soNum.getValue();
    }

    private KIT_MAT_INVTYTMsg getKitMatInvty(S21ApiMessageMap msgMap, WRK_ORDTMsg wrkOrdTMsg, String kitMatCd) {
        KIT_MAT_INVTYTMsg inMsg = new KIT_MAT_INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, wrkOrdTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.kitMatCd, kitMatCd);
        ZYPEZDItemValueSetter.setValue(inMsg.kitVndCd, wrkOrdTMsg.vndCd);

        KIT_MAT_INVTYTMsg kitMatInvtyTMsg = (KIT_MAT_INVTYTMsg) S21ApiTBLAccessor.findByKey(inMsg);

        // When DB error,
        if (kitMatInvtyTMsg != null && !RTNCD_NORMAL_END.equals(kitMatInvtyTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return null;
        }

        return kitMatInvtyTMsg;
    }

    /**
     * <pre>
     * Update DB
     * </pre>
     * @param msgMap Message Map
     * @param workOrdTMsg
     * @return true/false
     */
    private boolean update(S21ApiMessageMap msgMap, EZDTMsg workOrdTMsg) {

        // execute DB update
        S21ApiTBLAccessor.update(workOrdTMsg);

        if (!RTNCD_NORMAL_END.equals(workOrdTMsg.getReturnCode())) {
            // DB Error message
            this.addMsgId(msgMap, MSG_ID_NPZM0014E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set message ID
     * </pre>
     * @param msgMap Message Manager
     * @param msgId String setting value for Message ID
     * @throws none
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        msgMap.addXxMsgId(msgId);

        printDebugLog("setMsgId:" + msgId);
    }

    /**
     * <pre>
     * Print Debug Log
     * </pre>
     * @param debugMsg
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }
}
