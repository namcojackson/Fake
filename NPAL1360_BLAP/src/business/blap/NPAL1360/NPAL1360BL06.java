/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1360;

import static business.blap.NPAL1360.constant.NPAL1360Constant.COMPLETION_SWH;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_CANCEL;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_CMN_SUBMIT;
import static business.blap.NPAL1360.constant.NPAL1360Constant.KIT_ITEM;
import static business.blap.NPAL1360.constant.NPAL1360Constant.NPAM1524W;
import static business.blap.NPAL1360.constant.NPAL1360Constant.NPAM1527E;
import static business.blap.NPAL1360.constant.NPAL1360Constant.NPAM1529E;
import static business.blap.NPAL1360.constant.NPAL1360Constant.ORDER_QTY;
import static business.blap.NPAL1360.constant.NPAL1360Constant.WAREHOUSE;
import static business.blap.NPAL1360.constant.NPAL1360Constant.WORK_ORDER_TYPE;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1360.common.NPAL1360CommonLogic;
import business.db.DS_WRK_ORD_TPTMsg;
import business.db.WRK_ORDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Update
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/26/2017   CITS            Y.IWASAKI       Update          QC#17242
 * 08/30/2017   CITS            T.Hakodate      Update          Sol#069(QC#18270)
 *</pre>
 */

public class NPAL1360BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1360_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1360_Submit((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_CANCEL.equals(screenAplID)) {
                doProcess_NPAL1360_Cancel((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Submit(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum) && ZYPCommonFunc.hasValue(sMsg.wrkOrdNum)) {
            if (!cMsg.wrkOrdNum.getValue().equals(sMsg.wrkOrdNum.getValue())) {
                cMsg.wrkOrdNum.setErrorInfo(1, NPAM1527E);
                return;
            }
        }
        if (!cMsg.dsWrkOrdTpCd_SL.getValue().equals(sMsg.dsWrkOrdTpCd_SL.getValue())) {
            cMsg.dsWrkOrdTpCd_SL.setErrorInfo(1, NPAM1529E, new String[] {WORK_ORDER_TYPE});
            return;
        }
        if (!cMsg.rtlWhCd.getValue().equals(sMsg.rtlWhCd.getValue())) {
            cMsg.rtlWhCd.setErrorInfo(1, NPAM1529E, new String[] {WAREHOUSE});
            return;
        }
        if (!cMsg.cpltRtlSwhCd.getValue().equals(sMsg.cpltRtlSwhCd.getValue())) {
            cMsg.cpltRtlSwhCd.setErrorInfo(1, NPAM1529E, new String[] {COMPLETION_SWH});
            return;
        }
        if (!cMsg.fnshGoodsMdseCd.getValue().equals(sMsg.fnshGoodsMdseCd.getValue())) {
            cMsg.fnshGoodsMdseCd.setErrorInfo(1, NPAM1529E, new String[] {KIT_ITEM});
            return;
        }
        if (!cMsg.fnshGoodsOrdQty.getValue().equals(sMsg.fnshGoodsOrdQty.getValue())) {
            cMsg.fnshGoodsOrdQty.setErrorInfo(1, NPAM1529E, new String[] {ORDER_QTY});
            return;
        }

        // Get latest WRK_ORDs info
        WRK_ORDTMsg wrkOrdTMsg = null;
        if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum)) {
            // WRK_ORD
            wrkOrdTMsg = NPAL1360CommonLogic.getWrkOrd(cMsg);
        }
        // DS_WRK_ORD_TP
        DS_WRK_ORD_TPTMsg dsWrkOrdTpTMsg = NPAL1360CommonLogic.getDsWrkOrdTp(cMsg);

        // Close PR if screen comes from InvReq screen.
        boolean closePR = false;
        if (!ZYPCommonFunc.hasValue(cMsg.wrkOrdStsCd) && ZYPCommonFunc.hasValue(cMsg.prchReqNum_P1)) {
            closePR = true;
        }

        // Work around with QC#17242
        // Handling saved request with RPCH_REQ_NUM does not work correctly.
        if (wrkOrdTMsg != null && WRK_ORD_STS.SAVED.equals(cMsg.wrkOrdStsCd.getValue())) {
            // Load InvReq info from WRK_ORD into *_P1.
            if (ZYPCommonFunc.hasValue(wrkOrdTMsg.prchReqNum)) {
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, wrkOrdTMsg.prchReqNum);
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_P1, wrkOrdTMsg.prchReqNum);
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineNum_P1, wrkOrdTMsg.prchReqLineNum);
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineSubNum_P1, wrkOrdTMsg.prchReqLineSubNum);
            }
        }

        // Update Order Type - Kitting/Unkitting
        ZYPEZDItemValueSetter.setValue(cMsg.trxSrcTpCd, dsWrkOrdTpTMsg.trxSrcTpCd);

        if (!NPAL1360CommonLogic.checkKitItem(cMsg, sMsg)) {
            return;
        }

        if (!NPAL1360CommonLogic.checkWhSwH(cMsg, sMsg)) {
            return;
        }

        if (!NPAL1360CommonLogic.checkLineWhSwH(cMsg, sMsg)) {
            return;
        }

        if (!NPAL1360CommonLogic.checkSerial(cMsg)) {
            return;
        }

        if (!NPAL1360CommonLogic.checkConfiguration(cMsg)) {
            return;
        }

        String wrkOrdSavedFlg = ZYPConstant.FLG_OFF_N;
        if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            // Check Composition Master
            if (!NPAL1360CommonLogic.checkComposition(cMsg, sMsg)) {
                return;
            }
            // Calculate Allocation Quantity
            if (!NPAL1360CommonLogic.setAllocationQuantity(cMsg, sMsg)) {
                wrkOrdSavedFlg = ZYPConstant.FLG_ON_Y;
            }
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {

            NPAL1360CommonLogic.getKitItemAvailableInventory(cMsg);

            if (cMsg.fnshGoodsOrdQty.getValue().compareTo(cMsg.invtyAllocQty.getValue()) > 0) {
                cMsg.setMessageInfo(NPAM1524W, new String[] {KIT_ITEM});
                wrkOrdSavedFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        // Delete "Saved" Work Order
        if (WRK_ORD_STS.SAVED.equals(cMsg.wrkOrdStsCd.getValue())) {
            if (!NPAL1360CommonLogic.checkOtherProcessUpdate(cMsg, wrkOrdTMsg)) {
                return;
            }
            if (!NPAL1360CommonLogic.deleteWorkOrder(cMsg)) {
                return;
            }
        }

        // Insert Work Order
        if (!NPAL1360CommonLogic.insertWorkOder(cMsg)) {
            return;
        }

        // Close PR
        // PR should be closed only when coming from InvReq screen.
        if (closePR) {
            if (!NPAL1360CommonLogic.closePurchaseRequisition(cMsg)) {
                // Restore original value
                ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
                return;
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(wrkOrdSavedFlg)) {
            return;
        }

        // Order Items Allocation 
        if (!NPAL1360CommonLogic.callAllocationForNonCpoApi(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }

        wrkOrdSavedFlg = cMsg.xxYesNoCd.getValue();
        cMsg.xxYesNoCd.clear();

        if (ZYPConstant.FLG_ON_Y.equals(wrkOrdSavedFlg)) {
            return;
        }

        if (!NPAL1360CommonLogic.updateWorkOrderAfterAllocation(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }

        if (!NPAL1360CommonLogic.callShippingPlanUpdateApi(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }

        if (!NPAL1360CommonLogic.callShippingOrderApi(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }

        if (!NPAL1360CommonLogic.callMachineMasterUpdateApi(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }

        if (!NPAL1360CommonLogic.updateWorkOrderAfterSubmit(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }

        if (BigDecimal.ZERO.compareTo(cMsg.totOrdQty.getValue()) < 0) {
            if (!NPAL1360CommonLogic.createNewWorkOrder(cMsg)) {
                // Restore original value
                ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
                return;
            }
        }

        // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) START
        // Call RWS API NLZC200001
        if (!NPAL1360CommonLogic.createRws(cMsg)) {
            // Restore original value
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, sMsg.wrkOrdNum);
            return;
        }
        // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) END
    }

    /**
     * Cancel
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Cancel(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum) && ZYPCommonFunc.hasValue(sMsg.wrkOrdNum)) {
            if (!cMsg.wrkOrdNum.getValue().equals(sMsg.wrkOrdNum.getValue())) {
                cMsg.wrkOrdNum.setErrorInfo(1, NPAM1527E);
                return;
            }
        }

        if (!NPAL1360CommonLogic.cancel(cMsg, sMsg)) {
            return;
        }

    }

}
