package com.canon.cusa.s21.api.NWZ.NWZC135001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import business.db.ATP_INFOTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CMPSNTMsg;
import business.db.CMPSNTMsgArray;
import business.db.DIST_DTLTMsg;
import business.db.MDSETMsg;
//import business.db.MDSE_WH_CONDTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRNT_CMPY_VNDTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_FROM_LOC_LIST_VTMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SPLY_INFOTMsg;
import business.db.STTMsg;
import business.parts.NWZC002001PMsg;
import business.parts.NWZC135001PMsg;
import business.parts.NWZC135001_OrderListPMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001DefaultTOC;
import com.canon.cusa.s21.common.NWX.NWXC002005.NWXC002005AllocAvailSegData;
import com.canon.cusa.s21.common.NWX.NWXC002005.NWXC002005AllocAvailSegDataArray;
import com.canon.cusa.s21.common.NWX.NWXC002005.NWXC002005AllocAvailSegParam;
import com.canon.cusa.s21.common.NWX.NWXC002005.NWXC002005AllocationAvailableSegment;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21DateManagement;

/**
 * <pre>
 * ATP Inquriy By Item API
 * Caluculate Available to Promise(ATP) from 
 * information before it receives an order.
 * </pre>
 * 
 * <pre>
 * ATP information is calculated from information before it receives an order. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2009   Fujitsu         K.Kimura        Create          N/A
 * 12/29/2009   Fujitsu         T.Nagase        Update          2602
 * 02/05/2010   Fujitsu         K.Kato          Update          3517
 * 03/02/2010   Fujitsu         R.Watanabe      Update          4076
 * 05/03/2010   Fujitsu         K.Tajima        Update          5989
 * 02/10/2011   Fujitsu         A.Suda          Update          5989
 * 02/14/2011   Fujitsu         A.Suda          Update          1484 
 * 08/29/2011   CSAI            T.Gotoda        Update          ITG#359518
 * 04/05/2013   Fujitsu         F.Saito         Update          WDS#R-OM005-001
 * 08/30/2013   Fujitsu         K.Fujita        Update          QC1489
 * 08/03/2017   Fujitsu         T.Murai         Update          S21_NA#5872(L3#001)
 * </pre>
 */

public class NWZC135001 extends S21ApiCommonBase {

    /** It is message ID set when the required parameter "Global Company Code" is a unsetting. */
    public static final String NWZM0188E = "NWZM0188E";

    /** It is message ID set when the required parameter "Batch Process Date" is a unsetting. */
    public static final String NWZM0301E = "NWZM0301E";

    /** It is message ID set when the required parameter "Sequence" is a unsetting. */
    public static final String NWDM0001E = "NWDM0001E";

    /** It is message ID set when the required parameter "Inventory Locatio Code" is a unsetting. */
    public static final String NWZM0615E = "NWZM0615E";

    /** It is message ID set when the required parameter "Merchandise Code" is a unsetting. */
    public static final String NWZM0021E = "NWZM0021E";

    /** It is message ID set when the required parameter "Quantity" is a unsetting. */
    public static final String NWZM0611E = "NWZM0611E";

    /** It is message ID set when the required parameter "RSD" or "RDD" is a unsetting. */
    public static final String NWZM0616E = "NWZM0616E";

    /** It is message ID set when the required parameter "Bill to Customer Code" is a unsetting. */
    public static final String NWZM0617E = "NWZM0617E";

    /** It is message ID set when the required parameter "Sell to Customer Code" is a unsetting. */
    public static final String NWZM0618E = "NWZM0618E";

    /** It is message ID set when the required parameter "Ship to Customer Code" is a unsetting. */
    public static final String NWZM0619E = "NWZM0619E";

    /** It is message ID set when the required parameter "TOC Code" is a unsetting. */
    public static final String NWZM0620E = "NWZM0620E";

    /** It is message ID set when warehouse information that relates to "Inventory Locatio Code" doesn't exist */
    public static final String NWZM0621E = "NWZM0621E";

    /** It is message ID set when merchandise information that relates to "Merchandise Code" doesn't exist */
    public static final String NWZM0622E = "NWZM0622E";

    /** It is message ID set when customer information that relates to "Bill To Customer Code" doesn't exist */
    public static final String NWZM0623E = "NWZM0623E";

    /** It is message ID set when customer information that relates to "Sell To Customer Code" doesn't exist */
    public static final String NWZM0624E = "NWZM0624E";

    /** It is message ID set when customer information that relates to "Ship To Customer Code" doesn't exist */
    public static final String NWZM0625E = "NWZM0625E";

    /** It is message ID set when "Ship To State Code" doesn't exist in the table of "ST" */
    public static final String NWZM0088E = "NWZM0088E";

    /** It is message ID set when "To Zip Code" doesn't exist in the table of "POST" */
    public static final String NWZM0085E = "NWZM0085E";

    /** It is message ID set when "Merchandise Code" of off the subject is set */
    public static final String NWZM0627E = "NWZM0627E";

    /** It is message ID set when distribution plan doesn't exist */
    public static final String NWZM0628E = "NWZM0628E";

    /** Set Value */
    private static final String TBD      = "TBD";
    private static final String TBD_DATE = "99991231";
    private static final String STOCK    = "STOCK";

    /** Global Company Code */
    private String glblCmpyCd     = "";

    /** Bat Date */
    private String batDt          = "";

    /** MDSE Attribute */
    private String invtyDistTpCd  = "";
    private String showEtaFlg     = "";
    // DELETE START 2013/04/12 WDS#R-OM005-001
//    private BigDecimal etaDaysAot = ZERO;
    // DELETE END   2013/04/12 WDS#R-OM005-001
    private String mdseTpCd       = "";
    private String invtyLocTpCd   = "";
    private String invtyCtrlFlg   = "";
    private String ordTakeFlg     = "N";
    private String shipToStCd   = "";
    private String toZipCd   = "";

//    private String invtyHardAllocTp     = ""; // Del S21_NA#5872

    /** It is an instance of S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre> 
     * It is a Constructor.
     * {@link #ssmBatchClient} is initialized.
     * <pre> 
     */
    public NWZC135001() {
       super();
       ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * ATP Inquriy By Item main
     * </pre>
     * 
     * @param param NWZC135001PMsg Interfsce
     * @param onBatchType division of online or batch
     */
    public void execute(final NWZC135001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("--- START ---");
            writeDebugLog("InputData=" + param.toString());
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap, onBatchType);

        msgMap.flush();

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("OrderList=" + param.toString());
            writeDebugLog("--- END ---");
        }
    }

    /**
     * <pre>
     * ATP Inquriy By Item main List Type
     * </pre>
     * 
     * @param params NWZC135001PMsg List Interfsce
     * @param onBatchType division of online or batch
     */
    public void execute(final List<NWZC135001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NWZC135001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        writeDebugLog("[doProcess] start");

        NWZC135001PMsg inMsg = (NWZC135001PMsg) msgMap.getPmsg();
        NWZC135001_OrderListPMsgArray outMsg = inMsg.OrderList;

        //Parameter check
        if (!this.isParameterCheck(msgMap)) {

            return;
        }

        NWZC135001PMsg inMsgTemp = new NWZC135001PMsg();
        NWZC135001_OrderListPMsgArray outMsgTemp = inMsgTemp.OrderList;

        //Parameter Check(List)
        if (!this.isListParameterCheck(msgMap, outMsgTemp)) {
            if (!(outMsgTemp.getValidCount() == 0)) {
                //Set Order List
                this.setOrderList(outMsg, outMsgTemp);
            }
            return;
        }

        //Vendor Check
        if (LOC_TP.VENDOR.equals(this.invtyLocTpCd)) {
            //Set Order List(Vendor Drop)
            this.setOrderListVendor(msgMap, outMsg);
            return;
        }

        //Distribution Check
        if (DIST_TP.NONE.equals(this.invtyDistTpCd)) {

            //Set Item Check
            if (MDSE_TP.SET.equals(this.mdseTpCd)) {
                //Get Set Item Component
                CMPSNTMsgArray cmpsnMsgArray = this.getSetItemComponent(msgMap);

                if (cmpsnMsgArray.getValidCount() == 0) {
                    return;
                } else {
                    //Set Order List(Set Item)
                    this.setOrderListSetItem(msgMap, outMsgTemp, cmpsnMsgArray);
                }

            } else {
                //Set Order List(Not Distribution)
                this.setOrderListNotDist(inMsg, outMsgTemp, msgMap);
            }

        } else {

            //Set Order List(Distribution)
            this.setOrderListDist(inMsg, outMsgTemp, msgMap);
        }

        if (!(outMsgTemp.getValidCount() == 0)) {
            //Merge Order List
            this.mergeOrderList(outMsgTemp);

            //Set Order List
            this.setOrderList(outMsg, outMsgTemp);
        }

        writeDebugLog("[doProcess] end");
    }

    private boolean isParameterCheck(S21ApiMessageMap msgMap) {
        writeDebugLog("[isParameterCheck] start");

        NWZC135001PMsg inMsg = (NWZC135001PMsg) msgMap.getPmsg();

        if (!hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        } else {
            this.glblCmpyCd = inMsg.glblCmpyCd.getValue();
        }

        if (!hasValue(inMsg.batDt)) {
            msgMap.addXxMsgId(NWZM0301E);
            return false;
        } else {
            this.batDt = inMsg.batDt.getValue();
        }

        if (!hasValue(inMsg.startSeqNum)) {
            //TBD
            msgMap.addXxMsgId(NWDM0001E);
            return false;
        }

        if (!hasValue(inMsg.invtyLocCd)) {
            msgMap.addXxMsgId(NWZM0615E);
            return false;
        }

        if (!hasValue(inMsg.mdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return false;
        }

        if (!hasValue(inMsg.ordQty)) {
            msgMap.addXxMsgId(NWZM0611E);
            return false;
        }

        try {
            BigDecimal ordQty = inMsg.ordQty.getValue();

            if (ZERO.compareTo(ordQty) >= 0) {
                msgMap.addXxMsgId(NWZM0611E);
                return false;
            }

        } catch (NumberFormatException e) {
            msgMap.addXxMsgId(NWZM0611E);
            return false;
        }

        if (!hasValue(inMsg.rsdDt) && !hasValue(inMsg.rddDt)
                || hasValue(inMsg.rsdDt) && hasValue(inMsg.rddDt)) {
            msgMap.addXxMsgId(NWZM0616E);
            return false;
        }

        if (!hasValue(inMsg.billToCustCd)) {
            msgMap.addXxMsgId(NWZM0617E);
            return false;
        }

        if (!hasValue(inMsg.sellToCustCd)) {
            msgMap.addXxMsgId(NWZM0618E);
            return false;
        }

        if (!hasValue(inMsg.shipToCustCd)) {
            msgMap.addXxMsgId(NWZM0619E);
            return false;
        }

        if (!hasValue(inMsg.tocCd)) {
            String defaultTocCd = NWXC001001DefaultTOC.getDefaultTOC(this.glblCmpyCd, inMsg.mdseCd.getValue(), inMsg.sellToCustCd.getValue(), inMsg.billToCustCd.getValue());
            setValue(inMsg.tocCd, defaultTocCd);

            if (!hasValue(inMsg.tocCd)) {
                msgMap.addXxMsgId(NWZM0620E);
                return false;
            }
        }

        writeDebugLog("[isParameterCheck] end");
        return true;

    }

    private boolean isListParameterCheck(S21ApiMessageMap msgMap, NWZC135001_OrderListPMsgArray outMsgTemp) {
        writeDebugLog("[isListParameterCheck] start");

        NWZC135001PMsg inMsg = (NWZC135001PMsg) msgMap.getPmsg();

        //Exsist Check
        if (!this.isInvtyLocCdCheck(inMsg.invtyLocCd.getValue())) {
            msgMap.addXxMsgId(NWZM0621E);
            return false;
        }

        if (!this.isMdseCdCheck(inMsg.mdseCd.getValue())) {
            msgMap.addXxMsgId(NWZM0622E);
            return false;
        }

        if (!this.isBillToCustCdCheck(inMsg.billToCustCd.getValue())) {
            msgMap.addXxMsgId(NWZM0623E);
            return false;
        }

        if (!this.isSellToCustCdCheck(inMsg.sellToCustCd.getValue())) {
            msgMap.addXxMsgId(NWZM0624E);
            return false;
        }

        if (!this.isShipToCustCdCheck(inMsg.shipToCustCd.getValue())) {
            msgMap.addXxMsgId(NWZM0625E);
            return false;
        }

        if (hasValue(inMsg.shipToStCd)) {
            if (!this.isShipToStCdCheck(inMsg.shipToStCd.getValue())) {
                msgMap.addXxMsgId(NWZM0088E);
                return false;
            }
        } else {
            setValue(inMsg.shipToStCd, this.shipToStCd);
        }

        // UPDATE START 2013/08/30 QC1489
//        if (hasValue(inMsg.toZipCd)) {
//            if (!this.isToZipCdCheck(inMsg.toZipCd.getValue())) {
//                msgMap.addXxMsgId(NWZM0085E);
//                return false;
//            }
//        } else {
//            setValue(inMsg.toZipCd, this.toZipCd);
//        }
//      if (hasValue(inMsg.toZipCd)) {
        if (!hasValue(inMsg.toZipCd)) {
          setValue(inMsg.toZipCd, this.toZipCd);
        }
        // UPDATE END   2013/08/30 QC1489

        // ITG#359518
        // Look into MDSE_WH_COND table.
//        setInvtyHardAllocTpCd(inMsg); // Del S21_NA#5872

        // Mod Start S21_NA#5872
//        if (!MDSE_TP.SET.equals(this.mdseTpCd) && HARD_ALLOC_TP.MANUAL.equals(this.invtyHardAllocTp)) {
        if (!MDSE_TP.SET.equals(this.mdseTpCd)) {
        // Mod End S21_NA#5872
            if (!LOC_TP.VENDOR.equals(this.invtyLocTpCd)) {
                this.setOrderListTBD(inMsg, outMsgTemp, inMsg.ordQty.getValue());
                return false;
            }
        }

        if (FLG_OFF_N.equals(this.showEtaFlg)) {
            this.setOrderListTBD(inMsg, outMsgTemp, inMsg.ordQty.getValue());
            //msgMap.addXxMsgId(NWZM0627E);
            return false;
        }

        //Set Value(Shipping Service Level Code,Freight Charge To Code,Freight Charge Metod Code)
        if (!hasValue(inMsg.shpgSvcLvlCd)) {
            inMsg.shpgSvcLvlCd.setValue(SHPG_SVC_LVL.GROUND_SERVICE);
        }

        if (!hasValue(inMsg.frtChrgToCd)) {
            inMsg.frtChrgToCd.setValue(FRT_CHRG_TO.CANON);
        }

        if (!hasValue(inMsg.frtChrgMethCd)) {
            inMsg.frtChrgMethCd.setValue(FRT_CHRG_METH.N_OR_A);
        }

        writeDebugLog("[isListParameterCheck] end");
        return true;
    }

    private Boolean isMdseCdCheck(String mdseCd) {
        writeDebugLog("[isMdseCdCheck] start");

        MDSETMsg inMsg = new MDSETMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.mdseCd.setValue(mdseCd);

        MDSETMsg outMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {

                ORD_TAKE_MDSETMsg inOrdTakeMsg = new ORD_TAKE_MDSETMsg();
                inOrdTakeMsg.glblCmpyCd.setValue(glblCmpyCd);
                inOrdTakeMsg.ordTakeMdseCd.setValue(substring(mdseCd, 8));

                ORD_TAKE_MDSETMsg outOrdTakeMsg = (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(inOrdTakeMsg);

                if (outOrdTakeMsg == null) {
                    return Boolean.FALSE;
                }

                MDSETMsg inOrdMsg = new MDSETMsg();
                inOrdMsg.glblCmpyCd.setValue(glblCmpyCd);
                inOrdMsg.mdseCd.setValue(outOrdTakeMsg.mdseCd.getValue());

                outMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inOrdMsg);

                if (outMsg == null) {
                    return Boolean.FALSE;
                } else {
                    this.ordTakeFlg = "Y";
                }
        } else {

            this.ordTakeFlg = "N";
        }

        this.invtyDistTpCd = outMsg.invtyDistTpCd.getValue();
        this.showEtaFlg    = outMsg.showEtaFlg.getValue();
        this.mdseTpCd      = outMsg.mdseTpCd.getValue();
        this.invtyCtrlFlg  = outMsg.invtyCtrlFlg.getValue();
//        this.invtyHardAllocTp = outMsg.invtyHardAllocTpCd.getValue(); // Del S21_NA#5872

        // DELETE START 2013/04/12 WDS#R-OM005-001
//        if (hasValue(outMsg.etaDaysAot)) {
//            this.etaDaysAot    = outMsg.etaDaysAot.getValue();
//        } else {
//            this.etaDaysAot    = ZERO;
//        }
        // DELETE END   2013/04/12 WDS#R-OM005-001

        if (!LOC_TP.VENDOR.equals(this.invtyLocTpCd)
                && !MDSE_TP.SET.equals(this.mdseTpCd)
                && FLG_OFF_N.equals(this.invtyCtrlFlg)) {

            return Boolean.FALSE;
        }

        writeDebugLog("[isMdseCdCheck] end");
        return Boolean.TRUE;
    }

    private Boolean isInvtyLocCdCheck(String invtyLocCd) {
        writeDebugLog("[isInvtyLocCdCheck] start");

        SHIP_FROM_LOC_LIST_VTMsg inMsg = new SHIP_FROM_LOC_LIST_VTMsg();

        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invtyLocCd01", invtyLocCd);

        SHIP_FROM_LOC_LIST_VTMsgArray outMsg = (SHIP_FROM_LOC_LIST_VTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (outMsg.getValidCount() == 0) {
            return Boolean.FALSE;
        }

        this.invtyLocTpCd = outMsg.no(0).invtyLocTpCd.getValue();

        writeDebugLog("[isInvtyLocCdCheck] end");
        return Boolean.TRUE;
    }

    private Boolean isBillToCustCdCheck(String billToCustCd) {
        writeDebugLog("[isBillToCustCdCheck] start");

        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();

        inMsg.setSQLID("047");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("billToCustCd01", billToCustCd);

        BILL_TO_CUSTTMsgArray outMsg = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (outMsg.getValidCount() == 0) {
            return Boolean.FALSE;
        }

        writeDebugLog("[isBillToCustCdCheck] end");
        return Boolean.TRUE;
    }

    private Boolean isSellToCustCdCheck(String sellToCustCd) {
        writeDebugLog("[isSellToCustCdCheck] start");

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();

        inMsg.setSQLID("033");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", sellToCustCd);

        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (outMsg.getValidCount() == 0) {
            return Boolean.FALSE;
        }

        writeDebugLog("[isSellToCustCdCheck] end");
        return Boolean.TRUE;
    }

    private Boolean isShipToCustCdCheck(String shipToCustCd) {
        writeDebugLog("[isShipToCustCdCheck] start");

        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();

        inMsg.setSQLID("030");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);

        SHIP_TO_CUSTTMsgArray outMsg = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (outMsg.getValidCount() == 0) {
            return Boolean.FALSE;
        } else {
            this.shipToStCd = outMsg.no(0).stCd.getValue();
            this.toZipCd = outMsg.no(0).postCd.getValue();
        }

        writeDebugLog("[isShipToCustCdCheck] end");
        return Boolean.TRUE;
    }

    private Boolean isShipToStCdCheck(String shipToStCd) {
        writeDebugLog("[isShipToStCdCheck] start");

        STTMsg inMsg = new STTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.stCd.setValue(shipToStCd);

        STTMsg outMsg = (STTMsg) S21ApiTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return Boolean.FALSE;
        }

        writeDebugLog("[isShipToStCdCheck] end");
        return Boolean.TRUE;
    }

    // DELETE START 2013/08/30 QC1489
//    private Boolean isToZipCdCheck(String toZipCd) {
//        writeDebugLog("[isToZipCdCheck] start");
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("postCd", toZipCd);
//
//        if ((Integer) ssmBatchClient.queryObject("cntPost", queryParam) == 0) {
//            return Boolean.FALSE;
//        }
//
//        writeDebugLog("[isToZipCdCheck] end");
//        return Boolean.TRUE;
//    }
    // DELETE END 2013/08/30 QC1489
    
    // Del Start S21_NA#5872
//    private void setInvtyHardAllocTpCd(NWZC135001PMsg inMsg) {
//
//        final MDSE_WH_CONDTMsg mdseWhCondMsg = getMdseWhCondMsg(inMsg.mdseCd.getValue(), inMsg.invtyLocCd.getValue());
//
//        if (mdseWhCondMsg != null) {
//
//            String whHardAllocTpCd = mdseWhCondMsg.invtyHardAllocTpCd.getValue();
//            if (hasValue(whHardAllocTpCd)) {
//                this.invtyHardAllocTp = whHardAllocTpCd;
//            }
//        }
//    }
//
//    private MDSE_WH_CONDTMsg getMdseWhCondMsg(String mdseCd, String whCd) {
//
//        final MDSE_WH_CONDTMsg mdseWhCondMsg = new MDSE_WH_CONDTMsg();
//
//        setValue(mdseWhCondMsg.glblCmpyCd, glblCmpyCd);
//        setValue(mdseWhCondMsg.mdseCd,     mdseCd);
//        setValue(mdseWhCondMsg.whCd,       whCd);
//
//        return (MDSE_WH_CONDTMsg) S21ApiTBLAccessor.findByKey(mdseWhCondMsg);
//    }
    // Del End S21_NA#5872

    // DELETE START 2013/04/12 WDS#R-OM005-001
//    private String calcPsdDt(NWZC135001PMsg inMsg) {
//
//        String rsdDt      = inMsg.rsdDt.getValue();
//        String psdDt      = "";
//        int etaDaysAotInt = 0;
//
//        if (hasValue(this.etaDaysAot)) {
//
//            etaDaysAotInt = this.etaDaysAot.intValue();
//        }
//
//        psdDt = ZYPDateUtil.addDays(this.batDt, etaDaysAotInt);
//
//        if (hasValue(rsdDt)) {
//            if (psdDt.compareTo(rsdDt) < 0) {
//                psdDt = rsdDt;
//            }
//        }
//        return psdDt;
//    }
    // DELETE END   2013/04/12 WDS#R-OM005-001

    private void setOrderListVendor(S21ApiMessageMap msgMap, NWZC135001_OrderListPMsgArray outMsg) {
        writeDebugLog("[setOrderListVendor] start");

        NWZC135001PMsg inMsg = (NWZC135001PMsg) msgMap.getPmsg();

        //Caluculate PSD Date
        String psdDt = this.calcPsdDt(inMsg);

        //Set Order List
        int j = outMsg.getValidCount();

        setValue(outMsg.no(j).endSeqNum, inMsg.startSeqNum.getValue());
        setValue(outMsg.no(j).mdseCd,   inMsg.mdseCd.getValue());
        setValue(outMsg.no(j).psdDtTxt, psdDt);
        setValue(outMsg.no(j).pddDtTxt, TBD);
        setValue(outMsg.no(j).allocQty, inMsg.ordQty.getValue());
        outMsg.setValidCount(j + 1);

        writeDebugLog("[setOrderListVendor] end");
    }

    private CMPSNTMsgArray getSetItemComponent(S21ApiMessageMap msgMap) {
        writeDebugLog("[getSetItemComponent] start");

        NWZC135001PMsg inMsg = (NWZC135001PMsg) msgMap.getPmsg();

        CMPSNTMsg paramMsg = new CMPSNTMsg();

        paramMsg.setSQLID("010");
        paramMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        paramMsg.setConditionValue("prntMdseCd01", inMsg.mdseCd.getValue());
        paramMsg.setConditionValue("effFromDt01", this.batDt);
        paramMsg.setConditionValue("effThruDt01", this.batDt);

        CMPSNTMsgArray outMsg = (CMPSNTMsgArray) S21ApiTBLAccessor.findByCondition(paramMsg);

        writeDebugLog("[getSetItemComponent] end");
        return outMsg;
    }

    private void setOrderListNotDist(NWZC135001PMsg inMsg, NWZC135001_OrderListPMsgArray outMsgTemp, S21ApiMessageMap msgMap) {
        writeDebugLog("[setOrderListNotDist] start");

        //Get Supply Information
        List<SPLY_INFOTMsg> splyInfoMsgList = this.getSplyInfo(inMsg);

        BigDecimal ordQty = inMsg.ordQty.getValue();

        for (int i = 0; i < splyInfoMsgList.size(); i++) {
            SPLY_INFOTMsg splyInfoMsg = (SPLY_INFOTMsg) splyInfoMsgList.get(i);
            BigDecimal avalQty = splyInfoMsg.avalQty.getValue();
            BigDecimal allocQty = ZERO;
            String etaDt = splyInfoMsg.etaOrEtdDt.getValue();

            if (avalQty.equals(ZERO)) {
                continue;
            }

            String salesDate = S21DateManagement.getSalesDate(this.glblCmpyCd);

            if (ZYPDateUtil.compare(etaDt, salesDate) < 0) {

                etaDt = salesDate;
            }

            // LeadTime Calculate
            NWZC002001PMsg leadTimePMsg = this.callLeadtimeCalcAPI(inMsg, etaDt);
            if (S21ApiUtil.isXxMsgId(leadTimePMsg)) {
                List<String> errlist = S21ApiUtil.getXxMsgIdList(leadTimePMsg);

                for (int j = 0; j < errlist.size(); j++) {

                    msgMap.addXxMsgId(errlist.get(j));
                }
                return;
            }

            String psdDt = leadTimePMsg.xxPsdDt.getValue();
            String pddDt = leadTimePMsg.xxPddDt.getValue();

            if (avalQty.compareTo(ordQty) > 0) {
                allocQty = ordQty;
            } else {
                allocQty = avalQty;
            }

            this.setOrderListNotDistDtl(inMsg, outMsgTemp, splyInfoMsg, etaDt, psdDt, pddDt, allocQty);

            ordQty = ordQty.subtract(allocQty);

            if (ordQty.equals(ZERO)) {
                break;
            }
        }

        //Set Order List(TBD)
        if (ZERO.compareTo(ordQty) < 0) {
            this.setOrderListTBD(inMsg, outMsgTemp, ordQty);
        }

        writeDebugLog("[setOrderListNotDist] end");
    }

    private void setOrderListNotDistDtl(NWZC135001PMsg inMsg, NWZC135001_OrderListPMsgArray outMsgTemp, SPLY_INFOTMsg splyInfoMsg, String etaDt, String psdDt, String pddDt, BigDecimal allocQty) {
        writeDebugLog("[setOrderListNotDistDtl] start");

        int i = outMsgTemp.getValidCount();

        setValue(outMsgTemp.no(i).endSeqNum,      inMsg.startSeqNum.getValue());
        setValue(outMsgTemp.no(i).mdseCd,         splyInfoMsg.mdseCd.getValue());
        setValue(outMsgTemp.no(i).inbdVisEventCd, splyInfoMsg.inbdVisEventCd.getValue());
        setValue(outMsgTemp.no(i).xxEtaDtTxt,     etaDt);
        setValue(outMsgTemp.no(i).psdDtTxt,       psdDt);
        setValue(outMsgTemp.no(i).pddDtTxt,       pddDt);
        setValue(outMsgTemp.no(i).vndInvNum,      splyInfoMsg.imptInvNum.getValue());
        setValue(outMsgTemp.no(i).vndInvDoNum,    splyInfoMsg.vndInvDoNum.getValue());
        setValue(outMsgTemp.no(i).imptCntnrNum,   splyInfoMsg.imptCntnrNum.getValue());
        setValue(outMsgTemp.no(i).allocQty,       allocQty);

        outMsgTemp.setValidCount(i + 1);

        writeDebugLog("[setOrderListNotDistDtl] end");
    }

    @SuppressWarnings("unchecked")
    private List<SPLY_INFOTMsg> getSplyInfo(NWZC135001PMsg inMsg) {
        writeDebugLog("[getSplyInfo] start");

        // EDIT START 2013/04/05 WDS#R-OM005-001
//        Map<String, String> splyInfoMap = new HashMap<String, String>();
        Map<String, Object> splyInfoMap = new HashMap<String, Object>();
        // EDIT END   2013/04/05 WDS#R-OM005-001
        splyInfoMap.put("glblCmpyCd", this.glblCmpyCd);
        splyInfoMap.put("mdseCd", inMsg.mdseCd.getValue());
        splyInfoMap.put("invtyLocCd", inMsg.invtyLocCd.getValue());
        splyInfoMap.put("stkStsCd", STK_STS.GOOD);
        splyInfoMap.put("ordTakeFlg", this.ordTakeFlg);
        splyInfoMap.put("stock", STOCK);

        // ADD START 2013/04/05 WDS#R-OM005-001
        List<String> whSysTpCd = new ArrayList<String>();
        whSysTpCd.add(WH_SYS_TP.WMS);
        whSysTpCd.add(WH_SYS_TP.S21_SYSTEM);
        splyInfoMap.put("whSysTpList",     whSysTpCd);
        // ADD END   2013/04/05 WDS#R-OM005-001

        List<SPLY_INFOTMsg> splyInfoMsgList = ssmBatchClient.queryObjectList("getSplyInfo", splyInfoMap);

        writeDebugLog("[getSplyInfo] end");
        return splyInfoMsgList;
    }

    private NWZC002001PMsg callLeadtimeCalcAPI(NWZC135001PMsg inMsg, String etaDt) {
        writeDebugLog("[callLeadtimeCalcAPI] start");

        NWZC002001PMsg paramMsg = new NWZC002001PMsg();

        setValue(paramMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(paramMsg.mdseCd, inMsg.mdseCd.getValue());
        setValue(paramMsg.ordQty, inMsg.ordQty.getValue());
        setValue(paramMsg.shpgSvcLvlCd, inMsg.shpgSvcLvlCd.getValue());
        setValue(paramMsg.frtChrgMethCd, inMsg.frtChrgMethCd.getValue());
        setValue(paramMsg.frtChrgToCd, inMsg.frtChrgToCd.getValue());

        if (hasValue(inMsg.rddDt.getValue())) {
            if (etaDt.compareTo(inMsg.rddDt.getValue()) > 0) {
                setValue(paramMsg.xxRsdDt, etaDt);
            } else {
                setValue(paramMsg.xxRddDt, inMsg.rddDt.getValue());
            }
        }

        if (hasValue(inMsg.rsdDt.getValue())) {
            if (etaDt.compareTo(inMsg.rsdDt.getValue()) > 0) {
                setValue(paramMsg.xxRsdDt, etaDt);
            } else {
                setValue(paramMsg.xxRsdDt, inMsg.rsdDt.getValue());
            }
        }

        setValue(paramMsg.invtyLocCd, inMsg.invtyLocCd.getValue());
        setValue(paramMsg.shipToPostCd, inMsg.toZipCd.getValue());
        setValue(paramMsg.shipToCustCd, inMsg.shipToCustCd.getValue());
        setValue(paramMsg.sellToCustCd, inMsg.sellToCustCd.getValue());
        setValue(paramMsg.shipToStCd, inMsg.shipToStCd.getValue());
        setValue(paramMsg.uomCd, PKG_UOM.EACH);
        setValue(paramMsg.slsDt, this.batDt);

        NWZC002001 api = new NWZC002001();
        api.execute(paramMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.isXxMsgId(paramMsg)) {
            if (etaDt.compareTo(paramMsg.xxPsdDt.getValue()) > 0) {
                NWZC002001PMsg paramMsgOth = new NWZC002001PMsg();

                setValue(paramMsgOth.glblCmpyCd, this.glblCmpyCd);
                setValue(paramMsgOth.mdseCd, inMsg.mdseCd.getValue());
                setValue(paramMsgOth.ordQty, inMsg.ordQty.getValue());
                setValue(paramMsgOth.shpgSvcLvlCd, inMsg.shpgSvcLvlCd.getValue());
                setValue(paramMsgOth.frtChrgMethCd, inMsg.frtChrgMethCd.getValue());
                setValue(paramMsgOth.frtChrgToCd, inMsg.frtChrgToCd.getValue());
                setValue(paramMsgOth.xxRsdDt, etaDt);
                setValue(paramMsgOth.invtyLocCd, inMsg.invtyLocCd.getValue());
                setValue(paramMsgOth.shipToPostCd, inMsg.toZipCd.getValue());
                setValue(paramMsgOth.shipToCustCd, inMsg.shipToCustCd.getValue());
                setValue(paramMsgOth.sellToCustCd, inMsg.sellToCustCd.getValue());
                setValue(paramMsgOth.shipToStCd, inMsg.shipToStCd.getValue());
                setValue(paramMsgOth.uomCd, PKG_UOM.EACH);
                setValue(paramMsgOth.slsDt, this.batDt);

                NWZC002001 apiOth = new NWZC002001();
                apiOth.execute(paramMsgOth, ONBATCH_TYPE.BATCH);

                return paramMsgOth;
            }
        }

        writeDebugLog("[callLeadtimeCalcAPI] end");
        return paramMsg;
    }

    @SuppressWarnings("unchecked")
    private void setOrderListDist(NWZC135001PMsg inMsg, NWZC135001_OrderListPMsgArray outMsgTemp, S21ApiMessageMap msgMap) {
        writeDebugLog("[setOrderListDist] start");

        BigDecimal ordQty = inMsg.ordQty.getValue();

        //Get Distribution Structure Segment
        NWXC002005AllocationAvailableSegment    allocAvalSeg = new NWXC002005AllocationAvailableSegment();
        NWXC002005AllocAvailSegParam            param = new NWXC002005AllocAvailSegParam();

        param.setGlblCmpyCd(this.glblCmpyCd);
        param.setMdseCd(inMsg.mdseCd.getValue());
        param.setInvtyLocCd(inMsg.invtyLocCd.getValue());
        param.setTocCd(inMsg.tocCd.getValue());
        param.setBillToCustCd(inMsg.billToCustCd.getValue());
        param.setSellToCustCd(inMsg.sellToCustCd.getValue());
        param.setWhSysTpCd(WH_SYS_TP.WMS);
        param.setTrxSrcTpCd(TRX_SRC_TP.WHOLE_SALES);
        NWXC002005AllocAvailSegDataArray resultSegArray = allocAvalSeg.get(param);
        if (NWXC002005AllocAvailSegDataArray.NORMAL_RTN.equals(resultSegArray.getResultCode()) != true) {
            this.setOrderListTBD(inMsg, outMsgTemp, ordQty);
            return;
        }

        //LeadTime Calculate
        NWZC002001PMsg leadTimePMsg = this.callLeadtimeCalcAPI(inMsg, "20090710");
        if (S21ApiUtil.isXxMsgId(leadTimePMsg)) {
            List<String> errlist = S21ApiUtil.getXxMsgIdList(leadTimePMsg);

            for (int m = 0; m < errlist.size(); m++) {

                msgMap.addXxMsgId(errlist.get(m));
            }
            return;
        }

        String psdDt      = leadTimePMsg.xxPsdDt.getValue();
        String pddDt      = leadTimePMsg.xxPddDt.getValue();

        List<BigDecimal> atpInfoPkList    = new ArrayList<BigDecimal>();
        List<DIST_DTLTMsg> distDtlMsgList = new ArrayList<DIST_DTLTMsg>();

        for (int i = 0; i < resultSegArray.size(); i++) {
            NWXC002005AllocAvailSegData segData = resultSegArray.getData(i);

            Map<String, Object> distDtlMap = new HashMap<String, Object>();
            distDtlMap.put("glblCmpyCd", this.glblCmpyCd);
            distDtlMap.put("mdseCd", inMsg.mdseCd.getValue());
            distDtlMap.put("invtyLocCd", inMsg.invtyLocCd.getValue());
            distDtlMap.put("distPlnStsCdSubmit", DIST_PLN_STS.SUBMITTED);
            distDtlMap.put("distPlnNum", segData.getDistPlnNum());
            distDtlMap.put("distStruSegPk", segData.getDistStruSegPk());
            distDtlMap.put("psdDt", psdDt);

            List<DIST_DTLTMsg> distDtlMsgListSeg = ssmBatchClient.queryObjectList("getDistAvalQty", distDtlMap);

            this.mergeDistDtlMsgList(distDtlMsgList, distDtlMsgListSeg);
        }

        dist:
        for (int j = 0; j < distDtlMsgList.size(); j++) {
            DIST_DTLTMsg distDtlMsg = distDtlMsgList.get(j);

            Map<String, Object> atpInfoMap = new HashMap<String, Object>();
            atpInfoMap.put("glblCmpyCd", this.glblCmpyCd);
            atpInfoMap.put("distPk", distDtlMsg.distPk.getValue());
            atpInfoMap.put("distTmFrameNum", distDtlMsg.distTmFrameNum.getValue());
            atpInfoMap.put("invtyLocCd", inMsg.invtyLocCd.getValue());
            atpInfoMap.put("ordTakeFlg", this.ordTakeFlg);
            atpInfoMap.put("mdseCd", inMsg.mdseCd.getValue());
            atpInfoMap.put("mdseTpCd", this.mdseTpCd);
            atpInfoMap.put("tbdDt", TBD_DATE);
            atpInfoMap.put("stock", STOCK);

            List<ATP_INFOTMsg> atpInfoMsgList = ssmBatchClient.queryObjectList("getAtpInfo", atpInfoMap);

            BigDecimal distQty = distDtlMsg.distAvalQty.getValue();

            atp:
            for (int k = 0; k < atpInfoMsgList.size(); k++) {
                ATP_INFOTMsg atpInfoMsg = atpInfoMsgList.get(k);
                BigDecimal atpQty = atpInfoMsg.allocQty.getValue();
                BigDecimal allocQty = ZERO;

                for (int m = 0; m < atpInfoPkList.size(); m++) {
                    if (atpInfoPkList.get(m).equals(atpInfoMsg.atpInfoPk.getValue())) {
                            continue atp;
                    }
                }

                if (distQty.compareTo(atpQty) > 0) {
                    allocQty = atpQty;
                } else if ((distQty.compareTo(atpQty) < 0)
                    && (ZERO.compareTo(distQty) < 0)) {
                    allocQty = distQty;
                } else if (distQty.equals(atpQty)) {
                    allocQty = distQty;
                } else {
                    continue dist;
                }

                if (ordQty.compareTo(allocQty) < 0) {
                    allocQty = ordQty;
                }

                atpInfoPkList.add(atpInfoMsg.atpInfoPk.getValue());

                //LeadTime Calculate
                leadTimePMsg = this.callLeadtimeCalcAPI(inMsg, atpInfoMsgList.get(k).whEtaDt.getValue());
                if (S21ApiUtil.isXxMsgId(leadTimePMsg)) {
                    List<String> errlist = S21ApiUtil.getXxMsgIdList(leadTimePMsg);

                    for (int m = 0; m < errlist.size(); m++) {

                        msgMap.addXxMsgId(errlist.get(m));
                    }
                    return;
                }

                psdDt      = leadTimePMsg.xxPsdDt.getValue();
                pddDt      = leadTimePMsg.xxPddDt.getValue();

                int l = outMsgTemp.getValidCount();
                setValue(outMsgTemp.no(l).endSeqNum,      inMsg.startSeqNum.getValue());
                setValue(outMsgTemp.no(l).mdseCd,         atpInfoMsg.mdseCd.getValue());
                setValue(outMsgTemp.no(l).inbdVisEventCd, atpInfoMsg.inbdVisEventCd.getValue());
                setValue(outMsgTemp.no(l).xxEtaDtTxt,     atpInfoMsg.whEtaDt.getValue());
                setValue(outMsgTemp.no(l).psdDtTxt,       psdDt);
                setValue(outMsgTemp.no(l).pddDtTxt,       pddDt);
                setValue(outMsgTemp.no(l).vndInvNum,      atpInfoMsg.imptInvNum.getValue());
                setValue(outMsgTemp.no(l).vndInvDoNum,    atpInfoMsg.vndInvDoNum.getValue());
                setValue(outMsgTemp.no(l).imptCntnrNum,   atpInfoMsg.imptCntnrNum.getValue());
                setValue(outMsgTemp.no(l).allocQty,       allocQty);
                outMsgTemp.setValidCount(l + 1);

                ordQty = ordQty.subtract(allocQty);
                distQty = distQty.subtract(allocQty);

                if (ZERO.equals(ordQty)) {
                    break dist;
                }

                if (ZERO.equals(distQty)) {
                    break atp;
                }

            }
        }

        //Set Order List(TBD)
        if (ZERO.compareTo(ordQty) < 0) {
            this.setOrderListTBD(inMsg, outMsgTemp, ordQty);
        }

        writeDebugLog("[setOrderListDist] end");
    }

    private void setOrderListSetItem(S21ApiMessageMap msgMap, NWZC135001_OrderListPMsgArray outMsgTemp, CMPSNTMsgArray cmpsnMsgArray) {
        writeDebugLog("[setOrderListSetItem] start");

        NWZC135001PMsg inMsg = (NWZC135001PMsg) msgMap.getPmsg();

        NWZC135001PMsg inMsgTemp = new NWZC135001PMsg();
        NWZC135001_OrderListPMsgArray outSetMsg = inMsgTemp.OrderList;
        List<CmpsnInfo> reqCmpsnMdseList = new ArrayList<CmpsnInfo>();

        for (int j = 0; j < cmpsnMsgArray.getValidCount(); j++) {
            CMPSNTMsg cmpsnMsg = (CMPSNTMsg) cmpsnMsgArray.get(j);
            String cmpsnMdseCd = "";


            if (hasValue(cmpsnMsg.childMdseCd.getValue())) {
                cmpsnMdseCd = cmpsnMsg.childMdseCd.getValue();
            } else if (hasValue(cmpsnMsg.childOrdTakeMdseCd.getValue())) {
                cmpsnMdseCd = cmpsnMsg.childOrdTakeMdseCd.getValue();
            } else {
                continue;
            }

            //Get Set Item Mdse Info
            if (!isMdseCdCheck(cmpsnMdseCd)) {
                continue;
            }

            if (FLG_OFF_N.equals(this.invtyCtrlFlg)) {

                continue;
            }

            NWZC135001PMsg  inSetMsg  = new NWZC135001PMsg();

            BigDecimal cmpsnQty = cmpsnMsg.childMdseQty.getValue().multiply(inMsg.ordQty.getValue());

            EZDMsg.copy(inMsg, null, inSetMsg, null);
            inSetMsg.mdseCd.setValue(cmpsnMdseCd);
            inSetMsg.ordQty.setValue(cmpsnQty);

            // Del Start S21_NA#5872
//            if (HARD_ALLOC_TP.MANUAL.equals(this.invtyHardAllocTp)) {
//                this.setOrderListTBD(inSetMsg, outSetMsg, cmpsnQty);
//
//            } else 
            // Del End S21_NA#5872
            if (DIST_TP.NONE.equals(this.invtyDistTpCd)) {
                //Set Order List(Not Distribution)
                this.setOrderListNotDist(inSetMsg, outSetMsg, msgMap);
            } else {
                //Set Order List(Distribution)
                this.setOrderListDist(inSetMsg, outSetMsg, msgMap);
            }



            CmpsnInfo reqCmpsnMdse = new CmpsnInfo();

            reqCmpsnMdse.setMdseCd(cmpsnMdseCd);
            reqCmpsnMdse.setOrdQty(cmpsnQty);
            reqCmpsnMdse.setChildQty(cmpsnMsg.childMdseQty.getValue());

            reqCmpsnMdseList.add(reqCmpsnMdse);
        }

        this.setOrderListSetItemAjust(inMsg, outSetMsg, outMsgTemp, reqCmpsnMdseList);

        writeDebugLog("[setOrderListSetItem] end");
    }

    private void setOrderListSetItemAjust(NWZC135001PMsg inMsg, NWZC135001_OrderListPMsgArray outSetMsgTemp, NWZC135001_OrderListPMsgArray outMsgTemp, List<CmpsnInfo> reqCmpsnMdseList) {
        writeDebugLog("[setOrderListSetItemAjust] start");

        NWZC135001PMsg inSetMsg = new NWZC135001PMsg();
        NWZC135001_OrderListPMsgArray outSetMsg = inSetMsg.OrderList;

        //Merge Order List
        this.mergeOrderList(outSetMsgTemp);

        //Merge Order List(Order Take)
        this.mergeOrderListOrderTake(outSetMsgTemp, reqCmpsnMdseList);

        //Set Order List
        this.setOrderList(outSetMsg, outSetMsgTemp);

        //Sort Order List
        this.sortOrderList(outSetMsg);

        BigDecimal ordQty      = inMsg.ordQty.getValue();
        BigDecimal setAllocQty = ZERO;

        //Order List Ajust
        for (int i = 0; i < outSetMsg.getValidCount(); i++) {
            NWZC135001PMsg inCalcMsg = new NWZC135001PMsg();
            NWZC135001_OrderListPMsgArray calcMsg = inCalcMsg.OrderList;

            String etaDt = outSetMsg.no(i).xxEtaDtTxt.getValue();
            String psdDt = outSetMsg.no(i).psdDtTxt.getValue();
            String pddDt = outSetMsg.no(i).pddDtTxt.getValue();

            if (TBD.equals(outSetMsg.no(i).xxEtaDtTxt.getValue())
                    || !hasValue(outSetMsg.no(i).endSeqNum.getValue())) {
                continue;
            }

            int m = calcMsg.getValidCount();
            setValue(calcMsg.no(m).mdseCd,     outSetMsg.no(i).mdseCd.getValue());
            setValue(calcMsg.no(m).allocQty,   outSetMsg.no(i).allocQty.getValue());
            setValue(calcMsg.no(m).xxEtaDtTxt, etaDt);
            setValue(calcMsg.no(m).psdDtTxt,   psdDt);
            setValue(calcMsg.no(m).pddDtTxt,   pddDt);
            calcMsg.setValidCount(m + 1);
            outSetMsg.no(i).clear();

            for (int j = 0; j < outSetMsg.getValidCount(); j++) {
                if (!hasValue(outSetMsg.no(j).endSeqNum.getValue())) {
                    continue;
                }

                if (etaDt.equals(outSetMsg.no(j).xxEtaDtTxt.getValue())) {

                    int k = calcMsg.getValidCount();
                    setValue(calcMsg.no(k).mdseCd,     outSetMsg.no(j).mdseCd.getValue());
                    setValue(calcMsg.no(k).allocQty,   outSetMsg.no(j).allocQty.getValue());
                    setValue(calcMsg.no(k).xxEtaDtTxt, etaDt);
                    setValue(calcMsg.no(k).psdDtTxt,   psdDt);
                    setValue(calcMsg.no(k).pddDtTxt,   pddDt);
                    calcMsg.setValidCount(k + 1);

                    outSetMsg.no(j).clear();
                }
            }

            if (!this.isCmpsnFull(calcMsg, reqCmpsnMdseList)) {
                this.transferAllocQty(calcMsg, outSetMsg, reqCmpsnMdseList, ZERO);
                continue;
            }

            setAllocQty = this.getAllocQty(calcMsg, inMsg, reqCmpsnMdseList);
            this.transferAllocQty(calcMsg, outSetMsg, reqCmpsnMdseList, setAllocQty);
            if (setAllocQty.compareTo(ordQty) > 0) {
                setAllocQty = ordQty;
            }

            int l = outMsgTemp.getValidCount();
            setValue(outMsgTemp.no(l).endSeqNum,  inMsg.startSeqNum.getValue());
            setValue(outMsgTemp.no(l).mdseCd,     inMsg.mdseCd.getValue());
            setValue(outMsgTemp.no(l).xxEtaDtTxt, etaDt);
            setValue(outMsgTemp.no(l).psdDtTxt,   psdDt);
            setValue(outMsgTemp.no(l).pddDtTxt,   pddDt);
            setValue(outMsgTemp.no(l).allocQty,   setAllocQty);
            outMsgTemp.setValidCount(l + 1);

            ordQty = ordQty.subtract(setAllocQty);

            if (ZERO.equals(ordQty)) {
                break;
            }
        }

        //Set Order List(TBD)
        if (ZERO.compareTo(ordQty) < 0) {
            this.setOrderListTBD(inMsg, outMsgTemp, ordQty);
        }

        writeDebugLog("[setOrderListSetItemAjust] end");
    }

    private Boolean isCmpsnFull(NWZC135001_OrderListPMsgArray calcMsg, List<CmpsnInfo> reqCmpsnMdseList) {
        writeDebugLog("[isCompsnFull] start");

        Boolean ret = Boolean.TRUE;

        req:
        for (int i = 0; i < reqCmpsnMdseList.size(); i++) {

            reqCmpsnMdseList.get(i).setAllocQty(ZERO);

            for (int j = 0; j < calcMsg.getValidCount(); j++) {
                if (reqCmpsnMdseList.get(i).getMdseCd().equals(calcMsg.no(j).mdseCd.getValue())) {
                    reqCmpsnMdseList.get(i).setAllocQty(calcMsg.no(j).allocQty.getValue());
                    continue req;
                }
            }

            if (ZERO.compareTo(reqCmpsnMdseList.get(i).getTransQty()) < 0) {
                int k = calcMsg.getValidCount();
                setValue(calcMsg.no(k).mdseCd,     reqCmpsnMdseList.get(i).getMdseCd());
                setValue(calcMsg.no(k).allocQty,   ZERO);
                calcMsg.setValidCount(k + 1);

                continue;
            }
            ret = Boolean.FALSE;
        }

        writeDebugLog("[isCompsnFull] end");
        return ret;
    }

    private BigDecimal getAllocQty(NWZC135001_OrderListPMsgArray calcMsg, NWZC135001PMsg inMsg, List<CmpsnInfo> reqCmpsnMdseList) {
        writeDebugLog("[getAllocQty] start");

        BigDecimal allocQty       = ZERO;
        BigDecimal childQty       = ZERO;
        BigDecimal transQty       = ZERO;
        BigDecimal allocQtyTotal  = ZERO;
        BigDecimal SetAllocQty    = ZERO;
        BigDecimal minSetAllocQty = ZERO;

        for (int i = 0; i < reqCmpsnMdseList.size(); i++) {
            CmpsnInfo reqCmpsnMdse = reqCmpsnMdseList.get(i);

            allocQty      = reqCmpsnMdse.getAllocQty();
            childQty      = reqCmpsnMdse.getChildQty();
            transQty      = reqCmpsnMdse.getTransQty();
            allocQtyTotal = allocQty.add(transQty);

            SetAllocQty = allocQtyTotal.divide(childQty, 0, BigDecimal.ROUND_DOWN);

            if (minSetAllocQty.compareTo(SetAllocQty) > 0
                    || i == 0) {
                minSetAllocQty = SetAllocQty;
            }
        }

        writeDebugLog("[getAllocQty] end");
        return minSetAllocQty;
    }

    private void transferAllocQty(NWZC135001_OrderListPMsgArray calcMsg, NWZC135001_OrderListPMsgArray outSetMsg, List<CmpsnInfo> reqCmpsnMdseList, BigDecimal setAllocQty) {
        writeDebugLog("[transferAllocQty] start");

        for (int i = 0; i < calcMsg.getValidCount(); i++) {
            String mdseCd = calcMsg.no(i).mdseCd.getValue();

            for (int j = 0; j < reqCmpsnMdseList.size(); j++) {
                if (mdseCd.equals(reqCmpsnMdseList.get(j).getMdseCd())) {

                    BigDecimal transQtyBefore = reqCmpsnMdseList.get(j).getTransQty();
                    BigDecimal allocQty       = transQtyBefore.add(calcMsg.no(i).allocQty.getValue());
                    BigDecimal childQty       = reqCmpsnMdseList.get(j).getChildQty();
                    BigDecimal transQty       = allocQty.subtract(setAllocQty.multiply(childQty));

                    reqCmpsnMdseList.get(j).setTransQty(transQty);

                    break;
                }
            }
        }




        writeDebugLog("[transferAllocQty] end");
    }

    private void setOrderListTBD(NWZC135001PMsg inMsg, NWZC135001_OrderListPMsgArray outMsgTemp, BigDecimal ordQty) {
        writeDebugLog("[setOrderListTBD] start");

        int i = outMsgTemp.getValidCount();
        setValue(outMsgTemp.no(i).endSeqNum,  inMsg.startSeqNum.getValue());
        setValue(outMsgTemp.no(i).mdseCd,     inMsg.mdseCd.getValue());
        setValue(outMsgTemp.no(i).xxEtaDtTxt, TBD);
        setValue(outMsgTemp.no(i).psdDtTxt,   TBD);
        setValue(outMsgTemp.no(i).pddDtTxt,   TBD);
        setValue(outMsgTemp.no(i).allocQty,   ordQty);
        outMsgTemp.setValidCount(i + 1);

        writeDebugLog("[setOrderListTBD] end");
    }

    private void mergeDistDtlMsgList(List<DIST_DTLTMsg> distDtlMsgList, List<DIST_DTLTMsg> distDtlMsgListSeg) {
        writeDebugLog("[mergeDistDtlMsgList] start");

        BigDecimal distPk;
        String     distTmFrameNum;
        BigDecimal distAvalQty;
        BigDecimal distPkAdd;
        String     distTmFrameNumAdd;
        BigDecimal distAvalQtyAdd;

        main:
        for (int i = 0; i < distDtlMsgListSeg.size(); i++) {
            distPkAdd         = distDtlMsgListSeg.get(i).distPk.getValue();
            distTmFrameNumAdd = distDtlMsgListSeg.get(i).distTmFrameNum.getValue();
            distAvalQtyAdd    = distDtlMsgListSeg.get(i).distAvalQty.getValue();

            for (int j = 0; j < distDtlMsgList.size(); j++) {
                distPk         = distDtlMsgList.get(j).distPk.getValue();
                distTmFrameNum = distDtlMsgList.get(j).distTmFrameNum.getValue();
                distAvalQty    = distDtlMsgList.get(j).distAvalQty.getValue();

                if (distPk.equals(distPkAdd)
                        && distTmFrameNum.equals(distTmFrameNumAdd)) {
                    setValue(distDtlMsgList.get(j).distAvalQty, distAvalQty.add(distAvalQtyAdd));
                    continue main;
                }
            }

            distDtlMsgList.add(distDtlMsgListSeg.get(i));
        }

        writeDebugLog("[mergeDistDtlMsgList] end");
    }

    private void mergeOrderList(NWZC135001_OrderListPMsgArray outMsgTemp) {
        writeDebugLog("[mergeOrderList] start");

        for (int i = 0; i < outMsgTemp.getValidCount(); i++) {

            for (int j = 0; j < outMsgTemp.getValidCount(); j++) {
               if ((j == i)
                       || (!hasValue(outMsgTemp.no(j).endSeqNum))) {
                   continue;
               }

               if (outMsgTemp.no(i).endSeqNum.getValue().equals(outMsgTemp.no(j).endSeqNum.getValue())
                       && outMsgTemp.no(i).mdseCd.getValue().equals(outMsgTemp.no(j).mdseCd.getValue())
                       && outMsgTemp.no(i).inbdVisEventCd.getValue().equals(outMsgTemp.no(j).inbdVisEventCd.getValue())
                       && outMsgTemp.no(i).xxEtaDtTxt.getValue().equals(outMsgTemp.no(j).xxEtaDtTxt.getValue())
                       && outMsgTemp.no(i).psdDtTxt.getValue().equals(outMsgTemp.no(j).psdDtTxt.getValue())
                       && outMsgTemp.no(i).pddDtTxt.getValue().equals(outMsgTemp.no(j).pddDtTxt.getValue())
                       && outMsgTemp.no(i).vndInvNum.getValue().equals(outMsgTemp.no(j).vndInvNum.getValue())
                       && outMsgTemp.no(i).vndInvDoNum.getValue().equals(outMsgTemp.no(j).vndInvDoNum.getValue())
                       && outMsgTemp.no(i).imptCntnrNum.getValue().equals(outMsgTemp.no(j).imptCntnrNum.getValue())) {

                   setValue(outMsgTemp.no(i).allocQty, outMsgTemp.no(i).allocQty.getValue().add(outMsgTemp.no(j).allocQty.getValue()));
                   outMsgTemp.no(j).clear();
               }
            }
        }

        writeDebugLog("[mergeOrderList] end");
    }

    private void mergeOrderListOrderTake(NWZC135001_OrderListPMsgArray outMsgTemp, List<CmpsnInfo> reqCmpsnMdseList) {
        writeDebugLog("[mergeOrderListOrderTake] start");

        for (int i = 0; i < reqCmpsnMdseList.size(); i++) {
            String mdseCd = reqCmpsnMdseList.get(i).getMdseCd();

            if (mdseCd.length() == 8) {

                for (int j = 0; j < outMsgTemp.getValidCount(); j++) {
                    if (!hasValue(outMsgTemp.no(j).endSeqNum)) {
                        continue;
                    }

                    String mdseCdComp1        = outMsgTemp.no(j).mdseCd.getValue();
                    String ordTakeMdseCdComp1 = substring(mdseCdComp1, 8);
                    BigDecimal allocQtyComp1  = outMsgTemp.no(j).allocQty.getValue();

                    if (mdseCd.equals(ordTakeMdseCdComp1)) {

                        setValue(outMsgTemp.no(j).mdseCd, ordTakeMdseCdComp1);

                        for (int k = 0; k < outMsgTemp.getValidCount(); k++) {
                            if ((k == j)
                                    || (!hasValue(outMsgTemp.no(k).endSeqNum))) {
                                continue;
                            }

                            String mdseCdComp2        = outMsgTemp.no(k).mdseCd.getValue();
                            String ordTakeMdseCdComp2 = substring(mdseCdComp2, 8);
                            BigDecimal allocQtyComp2  = outMsgTemp.no(k).allocQty.getValue();

                            if (ordTakeMdseCdComp1.equals(ordTakeMdseCdComp2)
                                    && outMsgTemp.no(j).xxEtaDtTxt.getValue().equals(outMsgTemp.no(k).xxEtaDtTxt.getValue())) {

                                setValue(outMsgTemp.no(j).allocQty, allocQtyComp1.add(allocQtyComp2));
                                outMsgTemp.no(k).clear();
                            }
                        }
                    }
                }
            }
        }

        writeDebugLog("[mergeOrderListOrderTake] end");
    }

    private void setOrderList(NWZC135001_OrderListPMsgArray outMsg, NWZC135001_OrderListPMsgArray outMsgTemp) {
        writeDebugLog("[setOrderList] start");

        for (int i = 0; i < outMsgTemp.getValidCount(); i++) {
            if (!hasValue(outMsgTemp.no(i).endSeqNum)) {
                continue;
            }

            int j = outMsg.getValidCount();
            setValue(outMsg.no(j).endSeqNum,      outMsgTemp.no(i).endSeqNum);
            setValue(outMsg.no(j).mdseCd,         outMsgTemp.no(i).mdseCd);
            setValue(outMsg.no(j).inbdVisEventCd, outMsgTemp.no(i).inbdVisEventCd);
            setValue(outMsg.no(j).xxEtaDtTxt,     outMsgTemp.no(i).xxEtaDtTxt);
            setValue(outMsg.no(j).psdDtTxt,       outMsgTemp.no(i).psdDtTxt);
            setValue(outMsg.no(j).pddDtTxt,       outMsgTemp.no(i).pddDtTxt);
            setValue(outMsg.no(j).vndInvNum,      outMsgTemp.no(i).vndInvNum);
            setValue(outMsg.no(j).vndInvDoNum,    outMsgTemp.no(i).vndInvDoNum);
            setValue(outMsg.no(j).imptCntnrNum,   outMsgTemp.no(i).imptCntnrNum);
            setValue(outMsg.no(j).allocQty,       outMsgTemp.no(i).allocQty);
            outMsg.setValidCount(j + 1);
        }

        writeDebugLog("[setOrderList] end");
    }

    private void sortOrderList(NWZC135001_OrderListPMsgArray outMsg) {
        writeDebugLog("[sortOrderList] start");

        NWZC135001PMsg inSetMsg = new NWZC135001PMsg();
        NWZC135001_OrderListPMsgArray outMsgTemp = inSetMsg.OrderList;

        for (int i = 0; i < outMsg.getValidCount(); i++) {
            String minEtaDt  = "";

            for (int j = 0; j < outMsg.getValidCount(); j++) {
                if (!hasValue(outMsg.no(j).endSeqNum)) {
                    continue;
                }

                if (!hasValue(minEtaDt)) {
                    minEtaDt =  outMsg.no(j).xxEtaDtTxt.getValue();
                } else {
                    String etaDt  = outMsg.no(j).xxEtaDtTxt.getValue();
                    if (minEtaDt.compareToIgnoreCase(etaDt) > 0) {
                        minEtaDt  = etaDt;
                    }
                }
            }

            for (int k = 0; k < outMsg.getValidCount(); k++) {
                if (!hasValue(outMsg.no(k).endSeqNum)) {
                    continue;
                }

                String etaDt  = outMsg.no(k).xxEtaDtTxt.getValue();
                if (minEtaDt.equals(etaDt)) {

                    int l = outMsgTemp.getValidCount();
                    setValue(outMsgTemp.no(l).endSeqNum,      outMsg.no(k).endSeqNum);
                    setValue(outMsgTemp.no(l).mdseCd,         outMsg.no(k).mdseCd);
                    setValue(outMsgTemp.no(l).inbdVisEventCd, outMsg.no(k).inbdVisEventCd);
                    setValue(outMsgTemp.no(l).xxEtaDtTxt,     outMsg.no(k).xxEtaDtTxt);
                    setValue(outMsgTemp.no(l).psdDtTxt,       outMsg.no(k).psdDtTxt);
                    setValue(outMsgTemp.no(l).pddDtTxt,       outMsg.no(k).pddDtTxt);
                    setValue(outMsgTemp.no(l).vndInvNum,      outMsg.no(k).vndInvNum);
                    setValue(outMsgTemp.no(l).vndInvDoNum,    outMsg.no(k).vndInvDoNum);
                    setValue(outMsgTemp.no(l).imptCntnrNum,   outMsg.no(k).imptCntnrNum);
                    setValue(outMsgTemp.no(l).allocQty,       outMsg.no(k).allocQty);
                    outMsgTemp.setValidCount(l + 1);

                    outMsg.no(k).clear();
                }
            }
        }

        outMsg.clear();
        EZDMsg.copy(outMsgTemp, null, outMsg, null);

        writeDebugLog("[sortOrderList] end");
    }

    private void writeDebugLog(String str) {
        EZDDebugOutput.println(1, "[ATP Inquiry By Item API] " + str, this);
    }

    private static String substring(String str, int endIndex) {
        String retStr = str;
        if (retStr != null) {
            if (retStr.length() > endIndex) {
                retStr = retStr.substring(0, endIndex);
            }
        }
        return retStr;
    }

    // ADD START 2013/04/12 WDS#R-OM005-001
    /**
     * Calculation Vendor Drop PSD Date
     * @param inMsg Parameter Class
     * @return PSD Date
     */
    private String calcPsdDt(NWZC135001PMsg inMsg) {
        // Is CUSA Check
        PRNT_CMPY_VNDTMsg prntCmpyVnd = new PRNT_CMPY_VNDTMsg();
        prntCmpyVnd.glblCmpyCd.setValue(glblCmpyCd);
        prntCmpyVnd.prntCmpyVndCd.setValue(inMsg.invtyLocCd.getValue());

        // CUSA has PRNT_CMPY_VND Record
        if (S21ApiTBLAccessor.findByKey(prntCmpyVnd) != null) {
            return TBD;
        }

        if (ZYPCommonFunc.hasValue(inMsg.rddDt)) {
            // RDD Setting
            return inMsg.rddDt.getValue();
        } else {
            // RSD Setting
            return batDt;
        }
    }
    // ADD END   2013/04/12 WDS#R-OM005-001
}
