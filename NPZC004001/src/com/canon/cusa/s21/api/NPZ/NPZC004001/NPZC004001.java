/**
 * Copyright(c)2012 Canon USA Inc. All rights reserved.
 */
package com.canon.cusa.s21.api.NPZ.NPZC004001;

import static com.canon.cusa.s21.api.NPZ.NPZC004001.constant.NPZC004001Constant.KEY_NAME_PO_DETAIL_MSG;
import static com.canon.cusa.s21.api.NPZ.NPZC004001.constant.NPZC004001Constant.KEY_NAME_UPDATE_STATUS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC109001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.constant.NPZC004001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreatePOHistory;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * PO Status Update API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/07   Fujitsu         R.Mori          Create          N/A
 * 2013/07/26   CSAI            K.Lee           Update          QC1444
 * 2013/11/19   Hitachi         T.Aoyagi        Update          QC3116
 * 2015/09/29   CITS            H.Sugawara      Update          CSA Project
 * 2016/05/06   CSAI            K.Lee           Update          QC#5762
 * 2016/05/06   CSAI            K.Lee           Update          QC#6478
 * 2016/05/06   CSAI            K.Lee           Update          QC#5761
 * 2016/06/30   CSAI            K.Lee           Update          QC#11273
 * 2016/09/15   CITS            K.Ogino         Update          QC#10453
 * 2016/12/12   CITS            S.Endo          Update          QC#14453
 * 2016/12/16   CITS            S.Endo          Update          QC#14630
 * 2017/05/15   CITS            R.Shimamoto     Update          QC#18473
 * 2017/06/07   CITS            S.Endo          Update          QC#17952
 * 2017/06/23   CITS            Y.IWASAKI       Update          QC#19527
 * 2017/06/23   CITS            K.Ogino         Update          QC#20133
 * 2017/10/23   CITS            T.Tokutomi      Update          QC#21662
 * 2017/11/14   CITS            K.Ogino         Update          QC#22137
 * 2017/12/01   CITS            K.Ogino         Update          QC#22481
 * 2017/12/12   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 2018/01/09   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 2018/01/16   CITS            K.Ogino         Update          QC#22412
 * 2018/03/28   CITS            K.Ogino         Update          QC#19214
 * 2018/04/06   CITS            K.Ogino         Update          QC#24731
 * 2018/12/14   Hitachi         E.Kameishi      Update          QC#29590
 * 2019/02/21   Hitachi         E.Kameishi      Update          QC#30322
 * 2019/11/21   CITS            R.Shimamoto     Update          QC#54865
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 02/06/2020   Fujitsu         M.Ishii         Update          QC#55530
 * 06/19/2020   Fujitsu         T.Ogura         Update          QC#57165
 * 2022/04/28   Hitachi         A.Kohinata      Update          QC#57934
 * 2023/02/09   CITS            R.Azucena       Update          QC#60836
 * 2023/04/13   Hitachi         E.Watabe        Update          QC#61323
 * 2023/05/26   Hitachi         T.NEMA          Update          QC#61553
 * 2023/07/18   CITS            K.Ikeda         Update          QC#61627
 * 2023/08/21   Hitachi         TZ.Win          Update          QC#61752
 *</pre>
 */
public class NPZC004001 extends S21ApiCommonBase {

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient;

    /** Process Mode ID */
    String procMode = null;

    /** Onbatch type */
    ONBATCH_TYPE onBatchType = null;

    /**
     * <pre>Constructor</pre>
     * @param none
     * @throws none
     */
    public NPZC004001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * PO Status Update API
     * call execute(NPZC004001PMsg, ONBATCH_TYPE) method by each PMsg. 
     * </pre>
     * @param inpPrmMsg NPZC004001PMsg List
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NPZC004001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * PO StatusUpdate API
     * This API is update Purchase Order's status and quantity.
     * Switch method by parameter.
     * </pre>
     * 
     * @param inpPrmPMsg NPZC004001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC004001PMsg inpPrmPMsg,
            final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);
        this.onBatchType = onBatchType;

        try {
            // Common In-parameter check
            if (!checkInputCommon(msgMap)) {
                // When error, exit process.
                return;
            }

            // Init Process Mode ID
            initModeId(msgMap);

            // In-parameter check
            if (NPZC004001Constant.MODE_ID_RECEIVING.equals(this.procMode)) {
                // 1.Receiving
                if (!checkInputForReceiving(msgMap)) {
                    // When error, exit process.
                    return;
                }
            }

            if (NPZC004001Constant.MODE_ID_CANCELED.equals(this.procMode)) {
                // 3.Cancelled
                if (!checkInputForCancelled(msgMap)) {
                    // When error, exit process.
                    return;
                }
            }

            // execute main process
            executeUpdate(msgMap);

        } finally {

            // send Message List to EZ API parameter.
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private void initModeId(S21ApiMessageMap msgMap) {
        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        if (PO_STS.RECEIVING.equals(inpPrmPMsg.poStsCd.getValue())) {
            // a) Receiving
            this.procMode = NPZC004001Constant.MODE_ID_RECEIVING;
        } else if (PO_STS.RECEIVING_COMPLETION.equals(inpPrmPMsg.poStsCd.getValue())) {
            // b) Receiving Completion
            this.procMode = NPZC004001Constant.MODE_ID_RECEIVING_COMPLETION;
        } else if (PO_STS.CANCELLED.equals(inpPrmPMsg.poStsCd.getValue())) {
            // c) Canceled
            this.procMode = NPZC004001Constant.MODE_ID_CANCELED;
        } else if (PO_STS.CLOSED.equals(inpPrmPMsg.poStsCd.getValue())) {
            // d) CLOSED
            this.procMode = NPZC004001Constant.MODE_ID_CLOSED;
        } else if (PO_STS.PO_CONFIRMED.equals(inpPrmPMsg.poStsCd.getValue())) {
            // e) PO Confirmed
            this.procMode = NPZC004001Constant.MODE_ID_PO_CONFIRMED;
        } else if (PO_STS.PO_ERROR.equals(inpPrmPMsg.poStsCd.getValue())) {
            // f) Error
            this.procMode = NPZC004001Constant.MODE_ID_ERROR;
        } else if (ZYPCommonFunc.hasValue(inpPrmPMsg.poInvQty)) {
            // QC#21662 delete invoice negative quantity check.
            // g) Invoice
            this.procMode = NPZC004001Constant.MODE_ID_INVOICE;
        } else if (ZYPCommonFunc.hasValue(inpPrmPMsg.poApvlStsCd)) {
            // h) Approval
            this.procMode = NPZC004001Constant.MODE_ID_APPROVAL;
        // add start 2022/04/28 QC#57934
        } else if (ZYPConstant.FLG_ON_Y.equals(inpPrmPMsg.wrtOffFlg.getValue())) {
            // i) Accrual Write Off Close
            this.procMode = NPZC004001Constant.MODE_ID_ACRL_WRITE_OFF_CLOSE;
        // add end 2022/04/28 QC#57934
        }
    }

    /**
     * <pre>
     * common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputCommon(S21ApiMessageMap msgMap) {

        // IN-parameter PMsg
        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        // input parameter null check

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd)) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0001E);
            return false;
        }

        // Purchase Order Number
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdNum)) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0018E);
            return false;
        }

        return true;

    }

    /**
     * <pre>
     * input parameter check (Receiving)
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputForReceiving(S21ApiMessageMap msgMap) {

        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        // Merchandise Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.mdseCd)) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0020E);
            return false;
        }

        // Purchase Order Receiving Quantity
        BigDecimal inpPoRcvQty = inpPrmPMsg.poRcvQty.getValue();

        if (!ZYPCommonFunc.hasValue(inpPoRcvQty)) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0005E);
            return false;
        }

        // If Quantity <= 0 , check NG.
        if (BigDecimal.ZERO.compareTo(inpPoRcvQty) >= 0) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0005E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * input parameter check (Cancelled)
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputForCancelled(S21ApiMessageMap msgMap) {

        //QC#19527 MDSE_CD is optional.
        /*
        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        // Merchandise Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.mdseCd)) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0020E);
            return false;
        }
        */

        return true;
    }

    /**
     * <pre>
     * Update Purchase Order Detail (Main process)
     * 1. Retrieave Purchase Order Detail record by input parameter.
     * 2. Update Purchase Order Detail's Status and Quantity.
     * 　 Update item is decided by Purchase Order Status Code.
     * 3. If it is necessary, Close Purchase Order Status.
     * </pre>
     * @param msgMap Message Map
     */
    private void executeUpdate(S21ApiMessageMap msgMap) {

        // 1. Retrieve Purchase Order Detail record by input parameter.
        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        // get PurchaseOrderDetail
        List<Map<String, Object>> ssmResList = getPurchaseOrderDetail(inpPrmPMsg);

        if (ssmResList.isEmpty()) {
            // no record hit
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0021E);
            return;
        }

        // 2. Update Purchase Order Detail.
        // When check error, exit and no more update following Detail or Header.  
        boolean allPoDtlWaitingForApproval = true;

        // QC#18473 Add.
        boolean setParentUpdFlg = false;

        List<Map<String,Object>> poDetailMapList = new ArrayList<Map<String,Object>>();
        // QC#22412
        List<PO_DTLTMsg> invUpdList = new ArrayList<PO_DTLTMsg>();

        for (Map<String, Object> poDataMap : ssmResList) {
            boolean isUpdateStatus = false;

            if (poDataMap == null) {
                // When no record hit (impossible case)
                this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                return;
            }

            PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();

            // Set DB Value to TMsg
            // PO_DTL
            poDtlTMsg.glblCmpyCd.setValue((String) poDataMap.get("GLBL_CMPY_CD"));
            poDtlTMsg.poOrdNum.setValue((String) poDataMap.get("PO_ORD_NUM"));
            poDtlTMsg.poOrdDtlLineNum.setValue((String) poDataMap.get("PO_ORD_DTL_LINE_NUM"));
            poDtlTMsg = (PO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poDtlTMsg);
            if (poDtlTMsg == null) {
                this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                return;
            }
            if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(poDtlTMsg.getReturnCode())) {
                // When DB error
                this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                return;
            }

            poDtlTMsg.poRcvQty.setValue((BigDecimal) poDataMap.get("PO_RCV_QTY"));
            poDtlTMsg.poBalQty.setValue((BigDecimal) poDataMap.get("PO_BAL_QTY"));
            poDtlTMsg.poInvQty.setValue((BigDecimal) poDataMap.get("PO_INV_QTY"));
            poDtlTMsg.poInvBalQty.setValue((BigDecimal) poDataMap.get("PO_INV_BAL_QTY"));

            if (EZDDebugOutput.isDebug()) {
                printDebugLog("--- PO_DTL properties ---");
                printDebugLog("glblCmpyCd:" + poDtlTMsg.glblCmpyCd.getValue());
                printDebugLog("poOrdNum:" + poDtlTMsg.poOrdNum.getValue());
                printDebugLog("poOrdDtlLineNum:"
                        + poDtlTMsg.poOrdDtlLineNum.getValue());
                printDebugLog("mdseCd:" + poDtlTMsg.mdseCd.getValue());
                printDebugLog("poStsCd:" + poDtlTMsg.poStsCd.getValue());
            }

            // get DB value
            // Purchase Order Receiving Quantity
            BigDecimal poRcvQty = poDtlTMsg.poRcvQty.getValue();
            // Purchase Order Balance Quantity
            BigDecimal poBalQty = poDtlTMsg.poBalQty.getValue();
            // Receive PO Invoice Flag
            String rcvPoInvFlg = (String) poDataMap.get("RCV_PO_INV_FLG");

            // Switch process by In-parameter's PO Status Code
            if (NPZC004001Constant.MODE_ID_RECEIVING.equals(this.procMode)) {

                // DB value check
                if (!checkDBValueForReceiving(msgMap, poDtlTMsg)) {
                    // When error, exit process.
                    return;
                }

                // Set value for update

                // Purchase Order Status Code
                poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);

                // START 2023/02/09 R.Azucena [QC#60836, MOD]
                // START 2017/12/12 [QC#14858, ADD]
                // if (PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poDtlTMsg.poLineTpCd.getValue())) {
                    // if Expense with Receipt, Update Received Qty = In Parameter Receiving Quantity
                //     poRcvQty = inpPrmPMsg.poRcvQty.getValue();
                // }else{
                    // Receiving Quantity = Receiving Quantity + In Parameter Receiving Quantity
                //     poRcvQty = poRcvQty.add(inpPrmPMsg.poRcvQty.getValue());
                // }
                // END 2017/12/12 [QC#14858, ADD]
                // Receiving Quantity = Receiving Quantity + In Parameter Receiving Quantity
                poRcvQty = poRcvQty.add(inpPrmPMsg.poRcvQty.getValue());
                // END 2023/02/09 R.Azucena [QC#60836, MOD]
                poDtlTMsg.poRcvQty.setValue(poRcvQty);

                // START 2023/02/09 R.Azucena [QC#60836, MOD]
                // START 2017/12/12 [QC#14858, ADD] 
                // if (PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poDtlTMsg.poLineTpCd.getValue())) {
                    // if Expense with Receipt, Update Balance Qty = Order Qty - In Parameter Receiving Quantity
                //     BigDecimal poOrdQty = poDtlTMsg.poDispQty.getValue();
                //     poBalQty = poOrdQty.subtract(inpPrmPMsg.poRcvQty.getValue());
                // }else{
                    // Balance Quantity = Balance Quantity - In Parameter Receiving Quantity
                //     poBalQty = poBalQty.subtract(inpPrmPMsg.poRcvQty.getValue());
                // }
                // END 2017/12/12 [QC#14858, ADD]
                // Balance Quantity = Balance Quantity - In Parameter Receiving Quantity
                poBalQty = poBalQty.subtract(inpPrmPMsg.poRcvQty.getValue());
                // END 2023/02/09 R.Azucena [QC#60836, MOD]
                if (BigDecimal.ZERO.compareTo(poBalQty) > 0) {
                    poBalQty = BigDecimal.ZERO;
                }
                poDtlTMsg.poBalQty.setValue(poBalQty);

                setLineStatus(msgMap, poDtlTMsg,  rcvPoInvFlg);

                // Set value for update
                //if (!BigDecimal.ZERO.equals(poDtlTMsg.poRcvQty.getValue())) {
                if (BigDecimal.ZERO.equals(poDtlTMsg.poBalQty.getValue())) {
                    isUpdateStatus = true;
                }

            } else if (NPZC004001Constant.MODE_ID_RECEIVING_COMPLETION.equals(this.procMode)) {

                if (PO_STS.CLOSED.equals(poDtlTMsg.poStsCd.getValue())) {
                    return;
                }

                // DB value check
                if (!checkDBValueForReceivingCompletion(msgMap, poDtlTMsg)) {
                    // When error, exit process.
                    return;
                }

                setLineStatusReceiveComp(msgMap, poDtlTMsg, rcvPoInvFlg);

                // Set value for update
                //if (!BigDecimal.ZERO.equals(poDtlTMsg.poRcvQty.getValue())) {
                if (BigDecimal.ZERO.equals(poDtlTMsg.poBalQty.getValue())) {
                    isUpdateStatus = true;
                }

            } else if (NPZC004001Constant.MODE_ID_CANCELED.equals(this.procMode)) {

                // DB value check
                if (!checkDBValueForCancelled(msgMap, poDtlTMsg)) {
                    // When error, exit process.
                    return;
                }

                // set value for update

                // Purchase Order Status Code
                poDtlTMsg.poStsCd.setValue(PO_STS.CANCELLED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CANCELLED);

                setCancelVal(poDtlTMsg, poBalQty);
                isUpdateStatus = true;

            } else if (NPZC004001Constant.MODE_ID_CLOSED.equals(this.procMode)) {

                setCloseVal(poDtlTMsg, isUpdateStatus, rcvPoInvFlg);

            } else if (NPZC004001Constant.MODE_ID_PO_CONFIRMED.equals(this.procMode)) {

                poDtlTMsg.poStsCd.setValue(PO_STS.PO_CONFIRMED);
                isUpdateStatus = true;

            } else if (NPZC004001Constant.MODE_ID_ERROR.equals(this.procMode)) {

                poDtlTMsg.poStsCd.setValue(PO_STS.PO_ERROR);
                isUpdateStatus = true;

            } else if (NPZC004001Constant.MODE_ID_INVOICE.equals(this.procMode)) {

                // DB value check
                if (!checkDBValueForInvoice(msgMap, poDtlTMsg)) {
                    // When error, exit process.
                    return;
                }

                BigDecimal invQty = poDtlTMsg.poInvQty.getValue().add(inpPrmPMsg.poInvQty.getValue());
                BigDecimal invBalQty = poDtlTMsg.poInvBalQty.getValue().subtract(inpPrmPMsg.poInvQty.getValue());
                if (BigDecimal.ZERO.compareTo(invBalQty) > 0) {
                    invBalQty = BigDecimal.ZERO;
                }

                if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue())) {
                    // QC#18473 Mod No Set Item Only.
                    if (!PO_MDSE_CMPSN_TP.PARENT.equals(poDtlTMsg.poMdseCmpsnTpCd.getValue())) {
                        poRcvQty = poRcvQty.add(inpPrmPMsg.poInvQty.getValue());
                        poBalQty = poBalQty.subtract(inpPrmPMsg.poInvQty.getValue());
                        if (BigDecimal.ZERO.compareTo(poBalQty) > 0) {
                            poBalQty = BigDecimal.ZERO;
                        }
                        poDtlTMsg.poRcvQty.setValue(poRcvQty);
                        poDtlTMsg.poBalQty.setValue(poBalQty);
                        // QC#22137 Start
                        if(BigDecimal.ZERO.compareTo(poBalQty) == 0) {
                            poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                        } else {
                            poDtlTMsg.poStsCd.setValue(PO_STS.VALIDATED);
                        }
                    } else {
                        invQty = poDtlTMsg.poInvQty.getValue();
                        invBalQty = poDtlTMsg.poInvBalQty.getValue();
                    }
                    // QC#22137 End
                }
                poDtlTMsg.poInvQty.setValue(invQty);
                poDtlTMsg.poInvBalQty.setValue(invBalQty);

                setLineStatusAtInvoice(msgMap, poDtlTMsg, rcvPoInvFlg);

                // QC#22412
                invUpdList.add(poDtlTMsg);

            } else if (NPZC004001Constant.MODE_ID_APPROVAL.equals(this.procMode)) {

                if (PO_STS.WAITING_FOR_APPROVAL.equals(poDtlTMsg.poStsCd.getValue())
                        && (PO_APVL_STS.APPROVED.equals(inpPrmPMsg.poApvlStsCd.getValue())
                                || PO_APVL_STS.PRE_APPROVED.equals(inpPrmPMsg.poApvlStsCd.getValue()))) {

                    // START 2017/12/12 [QC#14858, ADD] 
                    //if (PO_LINE_TP.ASSET.equals(poDtlTMsg.poLineTpCd.getValue())) {
                    if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue())) {
                    // END   2017/12/12 [QC#14858, ADD] 
                        poDtlTMsg.poStsCd.setValue(PO_STS.VALIDATED);
                        poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);

                    } else {
                        poDtlTMsg.poStsCd.setValue(PO_STS.VALIDATED);
                        isUpdateStatus = true;
                    }

                } else if (PO_STS.WAITING_FOR_APPROVAL.equals(poDtlTMsg.poStsCd.getValue())
                        && PO_APVL_STS.REJECTED.equals(inpPrmPMsg.poApvlStsCd.getValue())) {
                    poDtlTMsg.poStsCd.setValue(PO_STS.SAVED);
                }

            // add start 2022/04/28 QC#57934
            } else if (NPZC004001Constant.MODE_ID_ACRL_WRITE_OFF_CLOSE.equals(this.procMode)) {

                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
            // add end 2022/04/28 QC#57934

            } else {
                // i) If other code, exit process.
                this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0027E);
                return;
            }

            // Update PO_DTL
            if (!update(msgMap, poDtlTMsg)) {
                // When occure DB access error, exit.
                return;
            }

            // QC#55313
            POTMsg poTMsg = new POTMsg();
            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, inpPrmPMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());

            poTMsg = (POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poTMsg);

            if (poTMsg == null) {
                // When no record hit (impossible case)
                this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                return;
            }

            if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(poTMsg.getReturnCode())) {
                // When DB error
                this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                return;
            }

            // mod start 2022/04/28 QC#57934
            //if (DS_PO_TP.SUBCONTRACT_PO.equals(poTMsg.dsPoTpCd.getValue()) //
            //        && poDtlTMsg != null //
            //        && (
            //                PO_LINE_STS.CANCELLED.equals(poDtlTMsg.poLineStsCd.getValue()) //
            //                ||
            //                PO_LINE_STS.CLOSED.equals(poDtlTMsg.poLineStsCd.getValue())
            //            )
            //    ) {
            //
            //    subconVndWhDrop(msgMap, poDtlTMsg);
            //
            //}
            if (!NPZC004001Constant.MODE_ID_ACRL_WRITE_OFF_CLOSE.equals(this.procMode)) {
                if (DS_PO_TP.SUBCONTRACT_PO.equals(poTMsg.dsPoTpCd.getValue()) //
                        && poDtlTMsg != null //
                        && (
                                PO_LINE_STS.CANCELLED.equals(poDtlTMsg.poLineStsCd.getValue()) //
                                || //
                                PO_LINE_STS.CLOSED.equals(poDtlTMsg.poLineStsCd.getValue())
                            )
                    ) {
                    subconVndWhDrop(msgMap, poDtlTMsg);
                }
            }
            // mod end 2022/04/28 QC#57934

            Map<String,Object> updatePoDtlMap = new HashMap<String, Object>();
            updatePoDtlMap.put(KEY_NAME_UPDATE_STATUS, isUpdateStatus);
            updatePoDtlMap.put(KEY_NAME_PO_DETAIL_MSG, poDtlTMsg);
            poDetailMapList.add(updatePoDtlMap);
            
            // QC#8424
            // String eventId = getEventId(inpPrmPMsg);
            //
            // // Register the history
            // if (ZYPCommonFunc.hasValue(eventId)) {
            // NPXC001001CreatePOHistory.createPOHistory(inpPrmPMsg.glblCmpyCd.getValue(),
            // eventId,
            // poDtlTMsg.poOrdNum.getValue(),
            // poDtlTMsg.poOrdDtlLineNum.getValue());
            // }

            // START 2023/08/21 TZ.Win [QC#61752, MOD] 
//            if (!PO_STS.WAITING_FOR_APPROVAL.equals((String) poDataMap.get("PO_STS_CD"))) { 
            if (!PO_STS.WAITING_FOR_APPROVAL.equals((String) poDataMap.get("PO_STS_CD")) &&                             
                !PO_STS.CANCELLED.equals((String) poDataMap.get("PO_STS_CD"))) {
            // END 2023/08/21 TZ.Win [QC#61752, MOD]

                allPoDtlWaitingForApproval = false;
            }

            // QC#18473 Add.
            if (PO_MDSE_CMPSN_TP.CHILD.equals((String) poDataMap.get("PO_MDSE_CMPSN_TP_CD"))) {
                setParentUpdFlg = true;
            }
        }

        // 3. If it is necessary, Close Purchase Order Status.
        updateHeader(msgMap, allPoDtlWaitingForApproval, setParentUpdFlg);

        for (Map<String, Object> poDetailMap : poDetailMapList) {
            //POYO Update API
            if ((Boolean) poDetailMap.get(KEY_NAME_UPDATE_STATUS)) {
                if (!updateInbdVis(msgMap, onBatchType, (PO_DTLTMsg) poDetailMap.get(KEY_NAME_PO_DETAIL_MSG))) {
                    return;
                }
            }
        }

        // QC#22412
        if (NPZC004001Constant.MODE_ID_INVOICE.equals(this.procMode) && !invUpdList.isEmpty()) {

            for (PO_DTLTMsg poDtlTMsg : invUpdList) {
                String eventId = getEventId(inpPrmPMsg);

                // Register the history
                if (ZYPCommonFunc.hasValue(eventId)) {
                    NPXC001001CreatePOHistory.createPOHistory(inpPrmPMsg.glblCmpyCd.getValue(), eventId, poDtlTMsg.poOrdNum.getValue(), poDtlTMsg.poOrdDtlLineNum.getValue());
                }
            }
        }
    }

    private List<Map<String, Object>> getPurchaseOrderDetail(NPZC004001PMsg inpPrmPMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        queryParam.put("poOrdNum", inpPrmPMsg.poOrdNum.getValue());

        if (ZYPCommonFunc.hasValue(inpPrmPMsg.poStsCd)
                && !NPZC004001Constant.MODE_ID_RECEIVING_COMPLETION.equals(this.procMode) 
                && !NPZC004001Constant.MODE_ID_CLOSED.equals(this.procMode)) {
            // Merchandise Code
            if (ZYPCommonFunc.hasValue(inpPrmPMsg.mdseCd)) {
                queryParam.put("mdseCd", inpPrmPMsg.mdseCd.getValue());
            }
            // Purchase Order Status Code NOT IN ("CLOSED", "CANCELLED")
            queryParam.put("poStsCds",  new String[] {PO_STS.CLOSED, PO_STS.CANCELLED});
        // START 2023/05/26 T.NEMA [QC#61553, MOD]
        // //START 2023/04/13 E.Watabe [QC#61323, ADD]
        //} else if (NPZC004001Constant.MODE_ID_APPROVAL.equals(this.procMode)) {
        //    // Purchase Order Status Code NOT IN ("CLOSED")
        //    queryParam.put("poStsCds", new String[] {PO_STS.CLOSED});
        // //END   2023/04/13 E.Watabe [QC#61323, ADD]          
        //} else {
        } else if (!NPZC004001Constant.MODE_ID_APPROVAL.equals(this.procMode)) {
        // END   2023/05/26 T.NEMA [QC#61553, MOD]
            // Purchase Order Status Code NOT IN ("CANCELLED")
            queryParam.put("poStsCds", new String[] {PO_STS.CANCELLED });
        }

        if (ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdDtlLineNum)
                && !NPZC004001Constant.MODE_ID_APPROVAL.equals(this.procMode)) {
            queryParam.put("poOrdDtlLineNum", inpPrmPMsg.poOrdDtlLineNum.getValue());
        }

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(
                "queryActivePODtlKeyList", queryParam);
    }

    private void setCancelVal(PO_DTLTMsg poDtlTMsg, BigDecimal poBalQty) {
        // Cancell Quantity = Balance Quantity
        poDtlTMsg.poCancQty.setValue(poBalQty);

        // Balance Quantity = 0
        poDtlTMsg.poBalQty.setValue(BigDecimal.ZERO);

        // Invoice Balance Quantity = 0
        poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);
    }

    /**
     * <pre>
     * check that That Order Quantity and Receiving Quantity are equal
     * 
     * </pre>
     * @param poDtlTMsg PO_DTLTMsg
     * @param rcvPoInvFlg String
     * @return boolean
     */
    private void setCloseVal(PO_DTLTMsg poDtlTMsg, boolean isUpdateStatus, String rcvPoInvFlg) {
        BigDecimal invQty = poDtlTMsg.poInvQty.getValue();
        BigDecimal invBalQty = poDtlTMsg.poInvBalQty.getValue();
        BigDecimal rcvQty = poDtlTMsg.poRcvQty.getValue();
        BigDecimal balQty = poDtlTMsg.poBalQty.getValue();
        // Receiving
        if (PO_STS.RECEIVING.equals(poDtlTMsg.poStsCd.getValue())) {

            if (rcvQty.compareTo(invQty) > 0 && ZYPConstant.FLG_ON_Y.equals(rcvPoInvFlg)) {
                // PO_RCV_QTY > PO_INV_QTY

                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);
                // PO_CANC_QTY = PO_BAL_QTY
                poDtlTMsg.poCancQty.setValue(balQty);
                // PO_BAL_QTY = 0
                poDtlTMsg.poBalQty.setValue(BigDecimal.ZERO);
                // PO_INV_BAL_QTY = (PO_QTY - PO_CANC_QTY) - PO_INV_QTY
                BigDecimal ordQty = poDtlTMsg.poQty.getValue().subtract(
                        poDtlTMsg.poCancQty.getValue());
                poDtlTMsg.poInvBalQty.setValue(ordQty.subtract(invQty));
                isUpdateStatus = true;

            } else if (rcvQty.compareTo(invQty) == 0 || ZYPConstant.FLG_OFF_N.equals(rcvPoInvFlg)) {
                // PO_RCV_QTY = PO_INV_QTY

                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
                // PO_CANC_QTY = PO_BAL_QTY
                poDtlTMsg.poCancQty.setValue(balQty);
                // PO_BAL_QTY = 0
                poDtlTMsg.poBalQty.setValue(BigDecimal.ZERO);
                // PO_INV_BAL_QTY = (PO_QTY - PO_CANC_QTY) - PO_INV_QTY
                BigDecimal ordQty = poDtlTMsg.poQty.getValue().subtract(
                        poDtlTMsg.poCancQty.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(rcvPoInvFlg)) {
                    poDtlTMsg.poInvBalQty.setValue(ordQty.subtract(invQty));
                } else if (ZYPConstant.FLG_OFF_N.equals(rcvPoInvFlg)){
                    poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);
                }
                isUpdateStatus = true;

            } else if (rcvQty.compareTo(invQty) < 0
                    && BigDecimal.ZERO.compareTo(invBalQty) < 0) {
                // PO_RCV_QTY < PO_INV_QTY AND PO_INV_BAL_QTY > 0

                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);
                // PO_CANC_QTY = PO_QTY - PO_INV_QTY
                poDtlTMsg.poCancQty.setValue(poDtlTMsg.poQty.getValue().subtract(invQty));
                // PO_BAL_QTY = (PO_QTY - PO_CANC_QTY) - PO_RCV_QTY
                BigDecimal ordQty = poDtlTMsg.poQty.getValue().subtract(
                        poDtlTMsg.poCancQty.getValue());
                poDtlTMsg.poBalQty.setValue(ordQty.subtract(rcvQty));
                // PO_INV_BAL_QTY = 0
                poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);

            } else if (rcvQty.compareTo(invQty) < 0
                    && BigDecimal.ZERO.compareTo(invBalQty) == 0) {
                // PO_RCV_QTY < PO_INV_QTY AND PO_INV_BAL_QTY = 0

                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
                // PO_CANC_QTY = PO_CANC_QTY + PO_BAL_QTY
                poDtlTMsg.poCancQty.setValue(poDtlTMsg.poCancQty.getValue().add(balQty));
                // PO_BAL_QTY = 0
                poDtlTMsg.poBalQty.setValue(BigDecimal.ZERO);
                // PO_INV_BAL_QTY = 0
                poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);
                isUpdateStatus = true;
            }
        } else {

            if (rcvQty.compareTo(invQty) == 0) {
                // PO_RCV_QTY = PO_INV_QTY

                poDtlTMsg.poStsCd.setValue(PO_STS.CANCELLED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CANCELLED);
                // PO_CANC_QTY = PO_BAL_QTY
                poDtlTMsg.poCancQty.setValue(balQty);
                // PO_BAL_QTY = 0
                poDtlTMsg.poBalQty.setValue(BigDecimal.ZERO);
                // PO_INV_BAL_QTY = 0
                poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);

            } else if (rcvQty.compareTo(invQty) < 0
                    && BigDecimal.ZERO.compareTo(invBalQty) < 0) {
                // PO_RCV_QTY < PO_INV_QTY AND PO_INV_BAL_QTY > 0

                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);
                // PO_CANC_QTY = PO_QTY - PO_INV_QTY
                poDtlTMsg.poCancQty.setValue(poDtlTMsg.poQty.getValue().subtract(invQty));
                // PO_BAL_QTY = PO_BAL_QTY - PO_CANC_QTY
                poDtlTMsg.poBalQty.setValue(balQty.subtract(poDtlTMsg.poCancQty.getValue()));
                // PO_INV_BAL_QTY = 0
                poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);

            } else if (rcvQty.compareTo(invQty) < 0
                    && BigDecimal.ZERO.compareTo(invBalQty) == 0) {
                // PO_RCV_QTY < PO_INV_QTY AND PO_INV_BAL_QTY = 0

                poDtlTMsg.poStsCd.setValue(PO_STS.CANCELLED);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
                // PO_CANC_QTY = PO_CANC_QTY + PO_BAL_QTY
                poDtlTMsg.poCancQty.setValue(poDtlTMsg.poCancQty.getValue().add(balQty));
                // PO_BAL_QTY = 0
                poDtlTMsg.poBalQty.setValue(BigDecimal.ZERO);
                // PO_INV_BAL_QTY = 0
                poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);
            }
        }
    }

    private boolean updateInbdVis(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, PO_DTLTMsg poDtlTMsg) {

        String mode = NPZC109001Constant.POYO_INSERT_MODE;
        if (PO_STS.CANCELLED.equals(poDtlTMsg.poStsCd.getValue()) || PO_STS.CLOSED.equals(poDtlTMsg.poStsCd.getValue())) {
            mode = NPZC109001Constant.POYO_DELETE_MODE;
        }

        NPZC109001PMsg pMsg = new NPZC109001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, poDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(0).poOrdNum, poDtlTMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(0).poOrdDtlLineNum, poDtlTMsg.poOrdDtlLineNum);
        pMsg.detailList.setValidCount(1);

        NPZC109001 dPZC109001 = new NPZC109001();
        dPZC109001.execute(pMsg, onBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int idx = 0; idx < pMsg.xxMsgIdList.getValidCount(); idx++) {
                this.addMsgId(msgMap, pMsg.xxMsgIdList.no(idx).xxMsgId.getValue());
            }
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Update Purchase Order
     * 
     * According to the result of Purchase Order Detail's update process,
     * if all Detail's Status becomes CLOSED or CANCELLED, close Header's Status.
     * </pre>
     * @param msgMap
     * @param allPoDtlWaitingForApproval
     */
    private void updateHeader(S21ApiMessageMap msgMap, boolean allPoDtlWaitingForApproval, Boolean setParentUpdFlg) {

        // QC#18473 Set Parent Status And RcvQty Update.
        if ((NPZC004001Constant.MODE_ID_RECEIVING.equals(this.procMode)
                || NPZC004001Constant.MODE_ID_RECEIVING_COMPLETION.equals(this.procMode)
                || NPZC004001Constant.MODE_ID_INVOICE.equals(this.procMode))
                && Boolean.TRUE.equals(setParentUpdFlg)) {

            checkParentStatusUpdate(msgMap);

        }
        //QC#17952 Set Parent Status And RcvQty Update When All children's status are closed.
        if (NPZC004001Constant.MODE_ID_CLOSED.equals(this.procMode) && Boolean.TRUE.equals(setParentUpdFlg)){
            checkParentStatusClose(msgMap);
        }
        //QC#17952 END

        // get PurchaseOrderDetail
        List<Map> ssmMapList = (List<Map>) ssmBatchClient.queryObjectList(
                "queryPoDtlKeyList", msgMap.getPmsg());

        if (ssmMapList.isEmpty()) {
            // no record hit (impossible case)
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
            return;
        }

        // get Header record
        POTMsg poTMsg = getPOTMsg(msgMap, ssmMapList);
        if (poTMsg == null) {
            return;
        }

        // Check all Detail's Status.
        boolean allCancelled = true;
        boolean allCloseOrCancelled = true;
        boolean allCloseOrCancelledForDS = true;
        // QC#22137 Start
        // START 2023/07/18 K.Ikeda [QC#61627, ADD]
        // boolean lineOpen = true;
        boolean lineOpen = false;
        // END 2023/07/18 K.Ikeda [QC#61627, ADD]
        // boolean allWaitingForApproval = true;
        boolean includesReceiving = false;
        boolean includesPOError = false;
        boolean includesPOConfirmed = false;

        for (Map ssmRes : ssmMapList) {

            String dtlPoStsCd = (String) ssmRes.get("PO_STS_CD");
            String dtlPoLineStsCd = (String) ssmRes.get("PO_LINE_STS_CD");

            if (!PO_STS.CANCELLED.equals(dtlPoStsCd)) {
                allCancelled = false;
            }

            if (!PO_STS.CLOSED.equals(dtlPoStsCd)
                    && !PO_STS.CANCELLED.equals(dtlPoStsCd)) {

                allCloseOrCancelled = false;
            }

            if (!PO_LINE_STS.CLOSED.equals(dtlPoLineStsCd)
                    && !PO_LINE_STS.CANCELLED.equals(dtlPoLineStsCd)) {

                allCloseOrCancelledForDS = false;
            }

            if (PO_LINE_STS.OPEN.equals(dtlPoLineStsCd) || PO_LINE_STS.OPEN_FOR_INVOICE.equals(dtlPoLineStsCd) || PO_LINE_STS.OPEN_FOR_RECEIPT.equals(dtlPoLineStsCd)) {
                lineOpen = true;
            }

            // if (!PO_STS.WAITING_FOR_APPROVAL.equals(dtlPoStsCd)) {
            // allWaitingForApproval = false;
            // }

            // START 06/19/2020 T.Ogura [QC#57165,MOD]
//            if (PO_STS.RECEIVING.equals(dtlPoStsCd)) {
            if (PO_STS.RECEIVING.equals(dtlPoStsCd) || PO_STS.CLOSED.equals(dtlPoStsCd)) {
            // END   06/19/2020 T.Ogura [QC#57165,MOD]
                includesReceiving = true;
                continue;
            }
            if (PO_STS.PO_ERROR.equals(dtlPoStsCd)) {
                includesPOError = true;
                continue;
            }

            if (PO_STS.PO_CONFIRMED.equals(dtlPoStsCd)) {
                includesPOConfirmed = true;
                continue;
            }
        }

        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        String oldPostsCd = poTMsg.poStsCd.getValue();
        // add start 2022/04/28 QC#57934
        boolean updateFlg = true;
        // add end 2022/04/28 QC#57934

        // update Purchase Order's StatusCode
        if (NPZC004001Constant.MODE_ID_APPROVAL.equals(this.procMode)) {

            // NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg)
            // msgMap.getPmsg();
            if (allPoDtlWaitingForApproval && (PO_APVL_STS.APPROVED.equals(inpPrmPMsg.poApvlStsCd.getValue()) || PO_APVL_STS.PRE_APPROVED.equals(inpPrmPMsg.poApvlStsCd.getValue()))) {

                poTMsg.poStsCd.setValue(PO_STS.VALIDATED);
                poTMsg.poApvlPsnCd.setValue(inpPrmPMsg.poApvlPsnCd.getValue());
                poTMsg.poApvlDt.setValue(inpPrmPMsg.poApvlDt.getValue());
                poTMsg.poApvlTs.setValue(inpPrmPMsg.poApvlTs.getValue());

            } else if (allPoDtlWaitingForApproval && PO_APVL_STS.REJECTED.equals(inpPrmPMsg.poApvlStsCd.getValue())) {

                poTMsg.poStsCd.setValue(PO_STS.SAVED);
            }
            // QC#14688
            poTMsg.poApvlStsCd.setValue(inpPrmPMsg.poApvlStsCd.getValue());
            //isUpdateDsPo = true;
            
            //QC#18602 ADD Start
            //if (PO_STS.CLOSED.equals(poTMsg.poStsCd.getValue()) && PO_HDR_STS.CLOSED.equals(poTMsg.poHdrStsCd.getValue()) && lineOpen) {
            if (PO_HDR_STS.CLOSED.equals(poTMsg.poHdrStsCd.getValue()) && lineOpen) {

                poTMsg.poHdrStsCd.setValue(PO_HDR_STS.OPEN);
            }
            //QC#18602 ADD End
 
        // add start 2022/04/28 QC#57934
        } else if (NPZC004001Constant.MODE_ID_ACRL_WRITE_OFF_CLOSE.equals(this.procMode)) {
            if (allCloseOrCancelled) {
                poTMsg.poStsCd.setValue(PO_STS.CLOSED);
                if (allCloseOrCancelledForDS) {
                    poTMsg.poHdrStsCd.setValue(PO_HDR_STS.CLOSED);
                }
            } else {
                updateFlg = false;
            }
        // add end 2022/04/28 QC#57934

        } else {

            if (allCancelled) {

                poTMsg.poStsCd.setValue(PO_STS.CANCELLED);
                poTMsg.poHdrStsCd.setValue(PO_HDR_STS.CANCELLED);
                //isUpdateDsPo = true;

            } else if (allCloseOrCancelled) {

                poTMsg.poStsCd.setValue(PO_STS.CLOSED);

                if (allCloseOrCancelledForDS) {
                    poTMsg.poHdrStsCd.setValue(PO_HDR_STS.CLOSED);
                    //isUpdateDsPo = true;
                }

            } else if (includesReceiving) {
                poTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                poTMsg.poHdrStsCd.setValue(PO_HDR_STS.OPEN);
            } else if (includesPOError) {
                poTMsg.poStsCd.setValue(PO_STS.PO_ERROR);
            } else if (includesPOConfirmed) {
                poTMsg.poStsCd.setValue(PO_STS.PO_CONFIRMED);
            } else if (lineOpen) {
                // QC#24731
                if (!NPZC004001Constant.MODE_ID_CANCELED.equals(this.procMode)) {
                    poTMsg.poStsCd.setValue(PO_STS.VALIDATED);
                    poTMsg.poHdrStsCd.setValue(PO_HDR_STS.OPEN);
                }
            } else {
                return;
            }
            // QC#22137 End
        }

        // mod start 2022/04/28 QC#57934
        //if (!update(msgMap, poTMsg)) {
        //    // When occure DB access error, exit.
        //    return;
        //}
        if (updateFlg) {
            if (!update(msgMap, poTMsg)) {
                // When occure DB access error, exit.
                return;
            }
        }
        // mod end 2022/04/28 QC#57934

        // QC#8424
        // Register the history
        if (!oldPostsCd.equals(poTMsg.poStsCd.getValue()) && !NPZC004001Constant.MODE_ID_INVOICE.equals(this.procMode)) {
            String eventId = getEventId(inpPrmPMsg);
            for (Map ssmRes : ssmMapList) {

                if (ZYPCommonFunc.hasValue(eventId)) {
                    NPXC001001CreatePOHistory.createPOHistory(inpPrmPMsg.glblCmpyCd.getValue(), eventId, (String) ssmRes.get("PO_ORD_NUM"), (String) ssmRes.get("PO_ORD_DTL_LINE_NUM"));
                }
            }
        // QC#19214
        // mod start 2022/04/28 QC#57934
        //} else if (NPZC004001Constant.MODE_ID_CANCELED.equals(this.procMode)) {
        } else if (NPZC004001Constant.MODE_ID_CANCELED.equals(this.procMode) || NPZC004001Constant.MODE_ID_ACRL_WRITE_OFF_CLOSE.equals(this.procMode)) {
        // mod end 2022/04/28 QC#57934
            String eventId = getEventId(inpPrmPMsg);
            if (ZYPCommonFunc.hasValue(eventId) && ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdDtlLineNum)) {
                NPXC001001CreatePOHistory.createPOHistory(inpPrmPMsg.glblCmpyCd.getValue(), eventId, inpPrmPMsg.poOrdNum.getValue(), inpPrmPMsg.poOrdDtlLineNum.getValue());
            }
        }

    }

    /**
     * QC#17952
     * checkParentStatusClose
     * @param msgMap S21ApiMessageMap
     */
    private void checkParentStatusClose(S21ApiMessageMap msgMap) {
        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        queryParam.put("poOrdNum", inpPrmPMsg.poOrdNum.getValue());
        queryParam.put("poOrdDtlLineNum", inpPrmPMsg.poOrdDtlLineNum.getValue());

        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(
                "checkSetParentLineInfoForClose", queryParam);

        boolean allChildrenCloseFlag = true;
        if (!ssmResList.isEmpty()) {
            for (Map<String, Object>  setLineInfo : ssmResList) {
                if (!ZYPCommonFunc.hasValue((String) setLineInfo.get("CHILD_PO_STS_CD"))
                        || !PO_STS.CLOSED.equals((String) setLineInfo.get("CHILD_PO_STS_CD"))) {
                        allChildrenCloseFlag = false;
                        break;
                }
            }
            if (allChildrenCloseFlag) {
                PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
                poDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                poDtlTMsg.poOrdNum.setValue(inpPrmPMsg.poOrdNum.getValue());
                poDtlTMsg.poOrdDtlLineNum.setValue((String) ssmResList.get(0).get("PARENT_DTL_LINE_NUM"));
                poDtlTMsg = (PO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poDtlTMsg);
                if (poDtlTMsg == null) {
                    this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                    return;
                }
                if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(poDtlTMsg.getReturnCode())) {
                    // When DB error
                    this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
                    return;
                }
                BigDecimal poQty = (BigDecimal) ssmResList.get(0).get("CHILD_PO_QTY");
                BigDecimal poRcvQty = (BigDecimal) ssmResList.get(0).get("CHILD_PO_RCV_QTY");
                int scale = 2;
                int intScale = 0;
                
                // PO_RCV_QTY
                ZYPEZDItemValueSetter.setValue(poDtlTMsg.poRcvQty, poDtlTMsg.poQty.getValue().multiply(poRcvQty.divide(poQty, scale, RoundingMode.HALF_UP)).setScale(intScale, RoundingMode.HALF_UP));
                // PO_CANC_QTY
                ZYPEZDItemValueSetter.setValue(poDtlTMsg.poCancQty, poDtlTMsg.poQty.getValue().subtract(poDtlTMsg.poRcvQty.getValue()));
                // PO_STS_CD
                ZYPEZDItemValueSetter.setValue(poDtlTMsg.poStsCd, (String) ssmResList.get(0).get("CHILD_PO_STS_CD"));
                //PO_LINE_STS_CD
                ZYPEZDItemValueSetter.setValue(poDtlTMsg.poLineStsCd, (String) ssmResList.get(0).get("CHILD_PO_LINE_STS_CD"));

                update(msgMap, poDtlTMsg);
            }
        }
        
    }

    /**
     * get Event ID
     * @param inpPrmPMsg Input Parameter PMsg
     * @return
     */
    private String getEventId(NPZC004001PMsg inpPrmPMsg) {

        String eventId = null;
        String inpPoStsCd = inpPrmPMsg.poStsCd.getValue();

        if (PO_STS.RECEIVING.equals(inpPoStsCd)) {
            eventId = NPZC004001Constant.EVENT_ID_RECEIVING;
        } else if (PO_STS.RECEIVING_COMPLETION.equals(inpPoStsCd)) {
            eventId = NPZC004001Constant.EVENT_ID_RECEIVED;
        } else if (PO_STS.CANCELLED.equals(inpPoStsCd)) {
            eventId = NPZC004001Constant.EVENT_ID_CANCELLED;
        } else if (PO_STS.CLOSED.equals(inpPoStsCd)) {
            eventId = NPZC004001Constant.EVENT_ID_CLOSED;
        } else if (PO_STS.PO_CONFIRMED.equals(inpPoStsCd)) {
            eventId = NPZC004001Constant.EVENT_ID_CONFIRMED;
        } else if (PO_STS.PO_ERROR.equals(inpPoStsCd)) {
            eventId = NPZC004001Constant.EVENT_ID_ERROR;
        } else if (PO_APVL_STS.APPROVED.equals(inpPrmPMsg.poApvlStsCd.getValue())
                || PO_APVL_STS.PRE_APPROVED.equals(inpPrmPMsg.poApvlStsCd.getValue())) {
            eventId = NPZC004001Constant.EVENT_ID_APPROVED;
        } else if (PO_APVL_STS.REJECTED.equals(inpPrmPMsg.poApvlStsCd.getValue())) {
            eventId = NPZC004001Constant.EVENT_ID_REJECTED;
        // QC#22412
        } else if (!ZYPCommonFunc.hasValue(inpPoStsCd) && ZYPCommonFunc.hasValue(inpPrmPMsg.poInvQty) && BigDecimal.ZERO.compareTo(inpPrmPMsg.poInvQty.getValue()) < 0) {
            eventId = NPZC004001Constant.EVENT_ID_INVOICE_RECEIVING;
        } else if (!ZYPCommonFunc.hasValue(inpPoStsCd) && ZYPCommonFunc.hasValue(inpPrmPMsg.poInvQty) && BigDecimal.ZERO.compareTo(inpPrmPMsg.poInvQty.getValue()) > 0) {
            eventId = NPZC004001Constant.EVENT_ID_INVOICE_CANCEL;
        // add start 2022/04/28 QC#57934
        } else if (ZYPConstant.FLG_ON_Y.equals(inpPrmPMsg.wrtOffFlg.getValue())) {
            eventId = NPZC004001Constant.EVENT_ID_CLOSED;
        // add end 2022/04/28 QC#57934
        }
        

        return eventId;
    }

    /**
     * get PO TMsg
     * @param msgMap Message Map
     * @param ssmMapList PO_DTL data
     * @return
     */
    private POTMsg getPOTMsg(S21ApiMessageMap msgMap, List<Map> ssmMapList) {
        Map firstRes = ssmMapList.get(0);
        POTMsg poTMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, (String) firstRes.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, (String) firstRes.get("PO_ORD_NUM"));

        poTMsg = (POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poTMsg);

        if (poTMsg == null) {
            // When no record hit (impossible case)
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
            return null;
        }

        if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(poTMsg.getReturnCode())) {
            // When DB error
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
            return null;
        }
        return poTMsg;
    }

    /**
     * <pre>
     *  Check DB value (Receiving)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @return check result(OK:true, NG:false)
     */
    private boolean checkDBValueForReceiving(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg) {

        // POStatus check
        if (!isNotReceivedOrReceiving(msgMap, poDtlTMsg)) {
            return false;
        }

        return true;
    }

    private boolean isNotReceivedOrReceiving(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg) {
        // Purchase Order Status Code
        String poStsCd = poDtlTMsg.poStsCd.getValue();
        // Purchase Order Receiving Quantity
        BigDecimal poRcvQty = poDtlTMsg.poRcvQty.getValue();

        if ((PO_STS.VALIDATED.equals(poStsCd) || PO_STS.PO_CONFIRMED.equals(poStsCd) || PO_STS.PO_ERROR.equals(poStsCd)) && BigDecimal.ZERO.compareTo(poRcvQty) == 0) {
            // If POStatus = VALIDATED and Purchase Order Receiving
            // Quantity = 0, it is first Receiving.
            return true;

        } else if ((PO_STS.RECEIVING.equals(poStsCd) || PO_STS.RECEIVING_COMPLETION.equals(poStsCd)) && BigDecimal.ZERO.compareTo(poRcvQty) < 0) {
            // If POStatus = RECEIVING and Purchase Order Receiving
            // Quantity > 0, it is after second time Receiving.
            return true;
        // QC#54865 2019/11/28 ADD START
        } else if ((PO_STS.VALIDATED.equals(poStsCd) || PO_STS.PO_CONFIRMED.equals(poStsCd) || PO_STS.PO_ERROR.equals(poStsCd))
                && PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue()) && BigDecimal.ZERO.compareTo(poRcvQty) < 0) {

            return true;
        // QC#54865 2019/11/28 ADD END
        // QC#55530 2020/02/06 ADD START    
        } else if ((PO_STS.VALIDATED.equals(poStsCd) || PO_STS.PO_CONFIRMED.equals(poStsCd) || PO_STS.PO_ERROR.equals(poStsCd))
                && PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue()) && BigDecimal.ZERO.compareTo(poRcvQty) > 0) {

            return true;
        // QC#55530 2020/02/06 ADD END
        } else {

            // add error message
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0022E);
            return false;
        }
    }

    /**
     * <pre>
     *  Check DB value (Receiving)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @return check result(OK:true, NG:false)
     */
    private boolean checkDBValueForReceivingCompletion(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg) {

        if (!isNotReceivedOrReceiving(msgMap, poDtlTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     *  Check DB value (Cancelled)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @return check result(OK:true, NG:false)
     */
    private boolean checkDBValueForCancelled(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg) {

        String poStsCd = poDtlTMsg.poStsCd.getValue();

        // POStatus check
        if (BigDecimal.ZERO.compareTo(poDtlTMsg.poBalQty.getValue()) < 0 && PO_STS.RECEIVING.equals(poStsCd)) {
            // Balance Quantity > 0 AND POStatusCode = "03"(Receiving)
            // (V3.1)
            // a. In case, partical cancelled
            return true;
        } else if (BigDecimal.ZERO.compareTo(poDtlTMsg.poRcvQty.getValue()) == 0 && !PO_STS.RECEIVING.equals(poStsCd) && !PO_STS.RECEIVING_COMPLETION.equals(poStsCd)) {
            // Receiving Quantity = 0 AND POStatusCode =
            // "02"(Validated)
            // b. In case, all cancelled
            return true;
        } else {
            // When neither a nor b is filled, check NG.
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0026E);
            return false;
        }
    }

    /**
     * <pre>
     *  Check DB value (Cancelled)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @param rcvPoInvFlg String
     * @return check result(OK:true, NG:false)
     */
    private void setLineStatus(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg, String rcvPoInvFlg) {
        // Set value for update
        if (!BigDecimal.ZERO.equals(poDtlTMsg.poRcvQty.getValue())) {
            if (poDtlTMsg.poQty.getValue().equals(poDtlTMsg.poRcvQty.getValue())) {
                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                if (ZYPConstant.FLG_ON_Y.equals(rcvPoInvFlg) && BigDecimal.ZERO.compareTo(poDtlTMsg.poInvBalQty.getValue()) < 0) {
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);
                } else if (ZYPConstant.FLG_OFF_N.equals(rcvPoInvFlg) || BigDecimal.ZERO.equals(poDtlTMsg.poInvBalQty.getValue())) {
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
                }
            } else {
                poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);
            }
        }
    }

    /**
     * <pre>
     *  Check DB value (Receive Completion)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @param rcvPoInvFlg String
     * @return check result(OK:true, NG:false)
     */
    private void setLineStatusReceiveComp(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg, String rcvPoInvFlg) {

        // Set value for update
        if (!BigDecimal.ZERO.equals(poDtlTMsg.poRcvQty.getValue())) {
            if (poDtlTMsg.poQty.getValue().equals(poDtlTMsg.poRcvQty.getValue())) {
                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                if (ZYPConstant.FLG_ON_Y.equals(rcvPoInvFlg) && BigDecimal.ZERO.compareTo(poDtlTMsg.poInvBalQty.getValue()) < 0) {
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);
                } else if (ZYPConstant.FLG_OFF_N.equals(rcvPoInvFlg) || BigDecimal.ZERO.equals(poDtlTMsg.poInvBalQty.getValue())) {
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
                    poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);
                }
            } else {
                poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);
            }
        }
    }

    /**
     * <pre>
     *  Check DB value (At Invoice)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @param rcvPoInvFlg String
     * @return check result(OK:true, NG:false)
     */
    private void setLineStatusAtInvoice(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg, String rcvPoInvFlg) {

        // Set value for update
        // QC#22481
        if (!BigDecimal.ZERO.equals(poDtlTMsg.poRcvQty.getValue())) {
            if (poDtlTMsg.poQty.getValue().equals(poDtlTMsg.poRcvQty.getValue().add(poDtlTMsg.poCancQty.getValue()))) {
                poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                if (ZYPConstant.FLG_ON_Y.equals(rcvPoInvFlg) && BigDecimal.ZERO.compareTo(poDtlTMsg.poInvBalQty.getValue()) < 0) {
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);
                    // START 2018/12/14 E.Kameishi[QC#29590,MOD]
                    //poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING_COMPLETION);
                    poDtlTMsg.poStsCd.setValue(PO_STS.CLOSED);
                    // END 2018/12/14 E.Kameishi[QC#29590,MOD]
                    // START 2019/02/21 E.Kameishi [QC#30322,ADD]
                    POTMsg poTMsg = new POTMsg();
                    ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, poDtlTMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poDtlTMsg.poOrdNum);

                    poTMsg = (POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poTMsg);
                    ZYPEZDItemValueSetter.setValue(poTMsg.poHdrStsCd, PO_HDR_STS.OPEN);
                    if (!update(msgMap, poTMsg)) {
                        // When occure DB access error, exit.
                        return;
                    }
                    // END 2019/02/21 E.Kameishi [QC#30322,ADD]
                } else if (BigDecimal.ZERO.equals(poDtlTMsg.poInvBalQty.getValue()) || ZYPConstant.FLG_OFF_N.equals(rcvPoInvFlg)) {
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.CLOSED);
                    poDtlTMsg.poInvBalQty.setValue(BigDecimal.ZERO);
                }
            } else {
                // QC#54865 2019/11/21 MOD START
//              poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
//              poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);
                if (!PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue()) ){
                    poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                    poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_RECEIPT);
                }
                // QC#54865 2019/11/21 MOD END
            }
        } else {
            // QC#22137 Start
            if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue()) && BigDecimal.ZERO.compareTo(poDtlTMsg.poRcvQty.getValue()) == 0) {
                poDtlTMsg.poStsCd.setValue(PO_STS.VALIDATED);
                // START 2018/12/14 E.Kameishi[QC#29590,MOD]
                //poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);
                // END 2018/12/14 E.Kameishi[QC#29590,MOD]
            } else if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlTMsg.poMatchTpCd.getValue()) && BigDecimal.ZERO.compareTo(poDtlTMsg.poRcvQty.getValue()) < 0) {
                poDtlTMsg.poStsCd.setValue(PO_STS.RECEIVING);
                poDtlTMsg.poLineStsCd.setValue(PO_LINE_STS.OPEN_FOR_INVOICE);
            }
            // QC#22137 End
        }
    }

    /**
     * <pre>
     *  Check DB value (Invoice)
     * </pre>
     * @param msgMap Message Map
     * @param poDtlTMsg PO_DTLTMsg
     * @return check result(OK:true, NG:false)
     */
    private boolean checkDBValueForInvoice(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg) {

        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        // PO Line Num
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdDtlLineNum)) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0175E);
            return false;
        }

        // Purchase Order Status Code
        String poStsCd = poDtlTMsg.poStsCd.getValue();

        if (PO_STS.CLOSED.equals(poStsCd)) {
            return true;
        } else if (isNotReceivedOrReceiving(msgMap, poDtlTMsg)) {
            return true;
        } else {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0022E);
            return false;
        }

    }

    /**
     * <pre>
     * Update DB
     * </pre>
     * @param msgMap Message Map
     * @param ezTMsg
     * @return update result(OK:true, NG:false)
     */
    private boolean update(S21ApiMessageMap msgMap, EZDTMsg ezTMsg) {

        // execute DB update
        S21ApiTBLAccessor.update(ezTMsg);

        if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(ezTMsg.getReturnCode())) {
            // DB Error message
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Add Message ID to MessageMap, and print debug log.
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
     * Print debug log.
     * </pre>
     * @param debugMsg
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(NPZC004001Constant.CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }
    
    /**QC#18473 Add
     * checkParentStatusUpdate
     * @param msgMap
     */
    private void checkParentStatusUpdate(S21ApiMessageMap msgMap) {

        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        queryParam.put("poOrdNum", inpPrmPMsg.poOrdNum.getValue());

        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(
                "checkSetParentLineInfo", queryParam);

        if (!ssmResList.isEmpty()) {
            for (Map<String, Object>  setLineInfo : ssmResList) {
                // SET Parent Line Update.
                setParentLineUpdate(msgMap, setLineInfo);
            }
        }

    }
    
    /**QC#18473 Add
     * setParentLineUpdate
     * @param msgMap
     * @param setLineInfo
     * @param inpPrmPMsg
     * @return
     */
    private boolean setParentLineUpdate(S21ApiMessageMap msgMap, Map<String, Object> setLineInfo) {

        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();

        // RCV_PO_INV_FLG
        String rcvPoInvFlg = (String) setLineInfo.get("RCV_PO_INV_FLG");

        // PO_DTL
        poDtlTMsg.glblCmpyCd.setValue((String) setLineInfo.get("GLBL_CMPY_CD"));
        poDtlTMsg.poOrdNum.setValue((String) setLineInfo.get("PO_ORD_NUM"));
        poDtlTMsg.poOrdDtlLineNum.setValue((String) setLineInfo.get("PARENT_DTL_LINE_NUM"));
        poDtlTMsg = (PO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poDtlTMsg);
        if (poDtlTMsg == null) {
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
            return false;
        }
        if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(poDtlTMsg.getReturnCode())) {
            // When DB error
            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
            return false;
        }

        BigDecimal poQty = poDtlTMsg.poQty.getValue();
        BigDecimal poRcvQty = (BigDecimal) setLineInfo.get("MIN_RCV_SET_QTY");
        // QC#20133 Add
        BigDecimal poInvQty = (BigDecimal) setLineInfo.get("MIN_INV_SET_QTY");

        // PO_RCV_QTY
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poRcvQty, poRcvQty);
        // PO_BAL_QTY
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poBalQty, poQty.subtract(poRcvQty));
        // QC#20133 Add
        // PO_INV_QTY
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poInvQty, poInvQty);
        // PO_INV_BAL_QTY
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poInvBalQty, poQty.subtract(poInvQty));
        // PO_STS_CD
        if (poRcvQty.compareTo(BigDecimal.ZERO) >= 1 && poRcvQty.compareTo(poQty) != 0) {
            // Receiving
            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poStsCd, PO_STS.RECEIVING);

        } else if (poRcvQty.compareTo(poQty) == 0) {
            // Closed
            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poStsCd, PO_STS.CLOSED);

        } else if (poRcvQty.compareTo(BigDecimal.ZERO) == 0) {
            // PO Confirmed
            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poStsCd, PO_STS.PO_CONFIRMED);
        }
        // QC#20133 Mod
//        poDtlTMsg.glblCmpyCd.setValue(poDtlTMsg.glblCmpyCd.getValue());
//        poDtlTMsg.poOrdNum.setValue(poDtlTMsg.poOrdNum.getValue());
//        poDtlTMsg.poOrdDtlLineNum.setValue(poDtlTMsg.poOrdDtlLineNum.getValue());
//        poDtlTMsg = (PO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poDtlTMsg);
//        if (poDtlTMsg == null) {
//            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
//            return false;
//        }
//        if (!NPZC004001Constant.RTNCD_NORMAL_END.equals(poDtlTMsg.getReturnCode())) {
//            // When DB error
//            this.addMsgId(msgMap, NPZC004001Constant.MSG_ID_NPZM0014E);
//            return false;
//        }

        // PO_LINE_STS_CD
        if (poRcvQty.compareTo(BigDecimal.ZERO) >= 1 && poRcvQty.compareTo(poQty) != 0) {
            // Open for Receipt
            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poLineStsCd, PO_LINE_STS.OPEN_FOR_RECEIPT);

        } else if (poRcvQty.compareTo(poQty) == 0) {
            // Open For Invoice
            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poLineStsCd, PO_LINE_STS.OPEN_FOR_INVOICE);

        } else if (poRcvQty.compareTo(BigDecimal.ZERO) == 0) {
            // Open
            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poLineStsCd, PO_LINE_STS.OPEN);
        }

        setLineStatusAtInvoice(msgMap, poDtlTMsg, rcvPoInvFlg);

        // Update PO_DTL
        if (!update(msgMap, poDtlTMsg)) {
            // When occure DB access error, exit.
            return false;
        }

        return true;
    }

    /**
     * QC#55313 Add
     * @param msgMap S21ApiMessageMap
     */
    private void subconVndWhDrop(S21ApiMessageMap msgMap, PO_DTLTMsg poDtlTMsg) {

        NPZC004001PMsg inpPrmPMsg = (NPZC004001PMsg) msgMap.getPmsg();

        String poOrdNum = inpPrmPMsg.poOrdNum.getValue();
        String poOrdDtlLineNum = inpPrmPMsg.poOrdDtlLineNum.getValue();
        String ccyCd = "";

        GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, inpPrmPMsg.glblCmpyCd.getValue(), inpPrmPMsg.glblCmpyCd.getValue());

        if (glblCmpyT == null) {

            ccyCd = poDtlTMsg.ccyCd.getValue();

        } else {

            ccyCd = glblCmpyT.stdCcyCd.getValue();
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        queryParam.put("poOrdNum", poOrdNum);
        queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        queryParam.put("subContractPO", DS_PO_TP.SUBCONTRACT_PO);
        queryParam.put("close", PO_LINE_STS.CLOSED);
        queryParam.put("cancel", PO_LINE_STS.CANCELLED);

        List<Map<String, Object>> remaindList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSubconRemaindList", queryParam);

        if (remaindList != null && !remaindList.isEmpty()) {
            // Call Inventory Update API (NLZC0020) for Original Item
            List<NLZC002001PMsg> pMsgStkOutList = getPmsgRpForStkOut(inpPrmPMsg, remaindList, ccyCd);

            if (pMsgStkOutList != null && !pMsgStkOutList.isEmpty()) {
                NLZC002001 stkOutApi = new NLZC002001();
                stkOutApi.execute(pMsgStkOutList, onBatchType);

                for (NLZC002001PMsg msg : pMsgStkOutList) {

                    if (S21ApiUtil.isXxMsgId(msg)) {

                        List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);

                        for (int i = 0; i < msgList.size(); i++) {

                            String messageId = msgList.get(i);

                            if (messageId.endsWith("E")) {

                                throw new S21AbendException(msgList.get(0));

                            } else if (messageId.endsWith("W")) {

                                msgMap.addXxMsgId(messageId);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * <li>Refurbish Vendor Transfer Stock-Out from Vendor</li>
     * </pre>
     * @param inpPrmPMsg NPZC004001PMsg
     * @param shipMdseRpInfoList List<Map<String, Object>>
     * @param ccyCd String
     * @return List<NLZC002001PMsg>
     */
    private List<NLZC002001PMsg> getPmsgRpForStkOut(NPZC004001PMsg inpPrmPMsg, final List<Map<String, Object>> remaindList, String ccyCd) {

        List<NLZC002001PMsg> pMsgList = new ArrayList<NLZC002001PMsg>();

        for (Map<String, Object> remaindMap : remaindList) {

            BigDecimal remQty = (BigDecimal) remaindMap.get("REM_QTY");

            if (BigDecimal.ZERO.compareTo(remQty) == 0) {
                continue;
            }
            
            // Refurb Vendor Transfer Stock-Out from Vendor
            NLZC002001PMsg outPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(outPMsg.glblCmpyCd, inpPrmPMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(outPMsg.trxCd, TRX.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(outPMsg.trxRsnCd, TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR);
            ZYPEZDItemValueSetter.setValue(outPMsg.mdseCd, (String) remaindMap.get("SHIP_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyLocCd, (String) remaindMap.get("TO_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
            ZYPEZDItemValueSetter.setValue(outPMsg.stkStsCd, (String) remaindMap.get("FROM_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.xxRqstQty, remQty);
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyTrxDt, ZYPDateUtil.getSalesDate());
            ZYPEZDItemValueSetter.setValue(outPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            ZYPEZDItemValueSetter.setValue(outPMsg.sysSrcCd, (String) remaindMap.get("SYS_SRC_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvNum, (String) remaindMap.get("TRX_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.poRcvLineNum, (String) remaindMap.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsNum, (String) remaindMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsRefNum, (String) remaindMap.get("RWS_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.rwsDtlLineNum, (String) remaindMap.get("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.soNum, (String) remaindMap.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.soSlpNum, (String) remaindMap.get("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.poOrdNum, (String) remaindMap.get("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.poOrdDtlLineNum, (String) remaindMap.get("PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdNum, (String) remaindMap.get("INVTY_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.invtyOrdLineNum, (String) remaindMap.get("INVTY_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(outPMsg.shipToCustCd, (String) remaindMap.get("TO_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.vndCd, (String) remaindMap.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(outPMsg.ccyCd, ccyCd);
            ZYPEZDItemValueSetter.setValue(outPMsg.uomCd, PKG_UOM.EACH);

            pMsgList.add(outPMsg);
        }

        return pMsgList;
    }
}
