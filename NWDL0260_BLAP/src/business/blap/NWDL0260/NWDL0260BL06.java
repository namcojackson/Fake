/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 * 12/15/2010   Fujitsu         A.Suda          Update          951
 *</pre>
 */
package business.blap.NWDL0260;

import static business.blap.NWDL0260.common.NWDL0260CommonLogic.debug;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.debugMethodEnd;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.debugMethodStart;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getBackuppedSearchCriteria;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getInTrnstInvtyWithLock;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getInTrnstLocStsCd;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getOnHandInvtyWithLock;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getOnHandLocStsCd;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getStkStsCd;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.isScreen00;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.nullToZero;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.toArray;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.getSalesDate;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateNoWait;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWDL0260.constant.NWDL0260Constant;
import business.db.HID_INVTYTMsg;
import business.db.INVTYTMsg;
import business.parts.NLZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NWDL0260BL06 extends S21BusinessHandler implements NWDL0260Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        NWDL0260CMsg myCMsg = (NWDL0260CMsg) cMsg;
        myCMsg.setCommitSMsg(true);

        try {

            String scrnAplId = cMsg.getScreenAplID();

            // Scrn00
            if (isScreen00(myCMsg)) {
                if (scrnAplId.endsWith("_Allocate")) {
                    doProcess_Scrn00_Allocate(myCMsg);
                }
                if (scrnAplId.endsWith("_Cancel")) {
                    doProcess_Scrn00_Cancel(myCMsg);
                }
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_Scrn00_Allocate(NWDL0260CMsg cMsg) {
        final String methodNm = "doProcess_Scrn00_Allocate";
        debugMethodStart(this, methodNm);

        try {

            getBackuppedSearchCriteria(cMsg);

            EZDCBigDecimalItem allocOnHandQtyItem  = cMsg.invtyAdvcOnHandQty_AL;
            EZDCBigDecimalItem allocInTrnstQtyItem = cMsg.invtyAdvcInTrnstQty_AL;
            
            debug(this, "++++++++++++++++++++++++++++++++++++++++++++++++++");
            debug(this, " - mdseCd          = ", cMsg.mdseCd.getValue());
            debug(this, " - firstProdCtrlCd = ", cMsg.firstProdCtrlCd.getValue());
            debug(this, " - invtyLocCd      = ", cMsg.invtyLocCd.getValue());
            debug(this, " - Qty(On Hand)    = ", allocOnHandQtyItem.getValue());
            debug(this, " - Qty(In Transit) = ", allocInTrnstQtyItem.getValue());
            debug(this, "++++++++++++++++++++++++++++++++++++++++++++++++++");

            
            // Inventory Allocation API (Allocation)
            final NLZC001001PMsg pMsg = new NLZC001001PMsg();
            setValue(pMsg.xxProcTpCd, NLZC001001.PROC_TP_ALLOC);
            setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(pMsg.mdseCd,     cMsg.mdseCd);
            setValue(pMsg.invtyLocCd, cMsg.invtyLocCd);
            setValue(pMsg.stkStsCd,   getStkStsCd());
            
            // --------------------------------------------------
            // allocate 'On Hand' Qty
            // --------------------------------------------------
            final BigDecimal allocOnHandQty = nullToZero(allocOnHandQtyItem.getValue());
            if (allocOnHandQty.compareTo(ZERO) > 0) {

                debug(this, "========== allocate 'On Hand' Qty - [START]");
                debug(this, " - allocOnHandQty           = ", allocOnHandQty);

                // lock [INVTY]
                final INVTYTMsg invty = getOnHandInvtyWithLock(cMsg);
                if (invty == null) {
                    cMsg.invtyAdvcOnHandQty_AV.clear();
                    cMsg.mdseCd.setErrorInfo(1, MsgId.NZZM0000E.name());
                    cMsg.invtyLocCd.setErrorInfo(1, MsgId.NZZM0000E.name());
                    return;
                }

                // allocation available Qty.
                final BigDecimal invtyAvalQty = nullToZero(invty.invtyAvalQty.getValue());
                debug(this, " - allocation available Qty = ", invtyAvalQty);
                
                if (allocOnHandQty.compareTo(invtyAvalQty) > 0) {
                    setValue(cMsg.invtyAdvcOnHandQty_AV, invtyAvalQty);
                    allocOnHandQtyItem.setErrorInfo(1, MsgId.ZZM9002E.name(), toArray("Allocation Qty (On Hand)"));
                    return;
                }
                
                // call [NLZC001001] : Inventory Allocation API (On Hand)
                setValue(pMsg.locStsCd,  getOnHandLocStsCd());
                setValue(pMsg.xxRqstQty, allocOnHandQty);
                final String apiErrMsgId = callInventoryAllocationAPI(pMsg);
                if (hasValue(apiErrMsgId)) {
                    cMsg.setMessageInfo(apiErrMsgId);
                    return;
                }
                
                debug(this, "========== allocate 'On Hand' Qty - [E N D]");
            }

            // --------------------------------------------------
            // allocate 'In Transit' Qty
            // --------------------------------------------------
            final BigDecimal allocInTrnstQty = nullToZero(allocInTrnstQtyItem.getValue());
            if (allocInTrnstQty.compareTo(ZERO) > 0) {

                debug(this, "========== allocate 'In Transit' Qty - [START]");
                debug(this, " - allocInTrnstQty          = ", allocInTrnstQty);

                // lock [INVTY]
                final INVTYTMsg invty = getInTrnstInvtyWithLock(cMsg);
                if (invty == null) {
                    cMsg.invtyAdvcInTrnstQty_AV.clear();
                    cMsg.mdseCd.setErrorInfo(1, MsgId.NZZM0000E.name());
                    cMsg.invtyLocCd.setErrorInfo(1, MsgId.NZZM0000E.name());
                    return;
                }

                // allocation available Qty.
                final BigDecimal invtyAvalQty = nullToZero(invty.invtyAvalQty.getValue());
                debug(this, " - allocation available Qty = ", invtyAvalQty);
                
                if (allocInTrnstQty.compareTo(invtyAvalQty) > 0) {
                    setValue(cMsg.invtyAdvcInTrnstQty_AV, invtyAvalQty);
                    allocInTrnstQtyItem.setErrorInfo(1, MsgId.ZZM9002E.name(), toArray("Allocation Qty (In Transit)"));
                    return;
                }

                // call [NLZC001001] : Inventory Allocation API (In Transit)
                setValue(pMsg.locStsCd,  getInTrnstLocStsCd());
                setValue(pMsg.xxRqstQty, allocInTrnstQty);
                final String apiErrMsgId = callInventoryAllocationAPI(pMsg);
                if (hasValue(apiErrMsgId)) {
                    cMsg.setMessageInfo(apiErrMsgId);
                    return;
                }
                
                debug(this, "========== allocate 'In Transit' Qty - [E N D]");
            }

            // --------------------------------------------------
            // insert [HID_INVTY]
            // --------------------------------------------------
            final HID_INVTYTMsg hidInvty = new HID_INVTYTMsg();
            setValue(hidInvty.glblCmpyCd,           getGlobalCompanyCode());
            setValue(hidInvty.hidInvtyPk,           getNextHidInvtyPk());
            setValue(hidInvty.hidTs,                createHidTs());
            setValue(hidInvty.hidUserId,            getContextUserInfo().getUserId());
            setValue(hidInvty.mdseCd,               cMsg.mdseCd);
            setValue(hidInvty.invtyLocCd,           cMsg.invtyLocCd);
            setValue(hidInvty.stkStsCd,             getStkStsCd());
            setValue(hidInvty.invtyAdvcOnHandQty,   allocOnHandQty);
            setValue(hidInvty.invtyAdvcInTrnstQty,  allocInTrnstQty);
            setValue(hidInvty.hidInvtyMemoTxt,      cMsg.hidInvtyMemoTxt);
            
            // ***** insert
            //EZDTBLAccessor.insert(hidInvty);
            S21FastTBLAccessor.insert(hidInvty);
            debug(this, "========== insert [HID_INVTY]. hidInvtyPk = ", hidInvty.hidInvtyPk.getValue());

            while (RTNCD_DUPLICATE.equals(hidInvty.getReturnCode())) {
                // ***** insert
                setValue(hidInvty.hidInvtyPk, getNextHidInvtyPk());
                //EZDTBLAccessor.insert(hidInvty);
                S21FastTBLAccessor.insert(hidInvty);
                debug(this, "========== insert [HID_INVTY]. hidInvtyPk = ", hidInvty.hidInvtyPk.getValue());
            }

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }
    
    private void doProcess_Scrn00_Cancel(NWDL0260CMsg cMsg) {
        final String methodNm = "doProcess_Scrn00_Cancel";
        debugMethodStart(this, methodNm);

        try {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                NWDL0260_ACMsg lineMsg = cMsg.A.no(i);

                // cancellation line
                if (Y.equals(lineMsg.xxChkBox_A1.getValue())) {

                    // lock [HID_INVTY]
                    final HID_INVTYTMsg hidInvty = lockHidInvty(lineMsg);
                    if (hidInvty == null) {
                        continue;
                    }

                    EZDCBigDecimalItem allocOnHandQtyItem  = lineMsg.invtyAdvcOnHandQty_A1;
                    EZDCBigDecimalItem allocInTrnstQtyItem = lineMsg.invtyAdvcInTrnstQty_A1;

                    debug(this, "++++++++++++++++++++++++++++++++++++++++++++++++++");
                    debug(this, "[",i,"] : hidInvtyPk = ", lineMsg.hidInvtyPk_A1.getValue());
                    debug(this, " - mdseCd          = ", lineMsg.mdseCd_A1.getValue());
                    debug(this, " - invtyLocCd      = ", lineMsg.invtyLocCd_A1.getValue());
                    debug(this, " - Qty(In Transit) = ", allocInTrnstQtyItem.getValue());
                    debug(this, " - Qty(On Hand)    = ", allocOnHandQtyItem.getValue());
                    debug(this, "++++++++++++++++++++++++++++++++++++++++++++++++++");
                    
                    
                    // Inventory Allocation API (Cancel Allocation)
                    final NLZC001001PMsg pMsg = new NLZC001001PMsg();
                    setValue(pMsg.xxProcTpCd, NLZC001001.PROC_TP_CANC_ALLOC);
                    setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                    setValue(pMsg.mdseCd,     lineMsg.mdseCd_A1);
                    setValue(pMsg.invtyLocCd, lineMsg.invtyLocCd_A1);
                    setValue(pMsg.stkStsCd,   lineMsg.stkStsCd_A1);

                    BigDecimal cancelInTrnstQty = ZERO;
                    BigDecimal cancelOnHandQty  = ZERO;

                    // --------------------------------------------------
                    // cancel 'In Transit' Qty
                    // --------------------------------------------------
                    cancelInTrnstQty = nullToZero(allocInTrnstQtyItem.getValue());
                    if (cancelInTrnstQty.compareTo(ZERO) > 0) {

                        debug(this, "========== cancel 'In Transit' Qty - [START]");
                        debug(this, " - cancelInTrnstQty     = ", cancelInTrnstQty);

                        // lock [INVTY]
                        final INVTYTMsg invty = getInTrnstInvtyWithLock(lineMsg);
                        
                        if (invty == null) {
                            debug(this, "***** In Transit INVTY is Null.");
                            
                            cancelOnHandQty  = cancelInTrnstQty;
                            cancelInTrnstQty = ZERO;
                            
                        } else {
                            
                            // cancel available Qty.
                            final BigDecimal invtyAllocQty = nullToZero(invty.invtyAllocQty.getValue());
                            debug(this, " - cancel available Qty = ", invtyAllocQty);
                            
                            final BigDecimal xxRqstQty;
                            if (cancelInTrnstQty.compareTo(invtyAllocQty) > 0) {
                                xxRqstQty        = invtyAllocQty;
                                cancelOnHandQty  = cancelInTrnstQty.subtract(invtyAllocQty);
                                cancelInTrnstQty = ZERO;
                            } else {
                                xxRqstQty        = cancelInTrnstQty;
                                cancelInTrnstQty = ZERO;
                            }
                            debug(this, " - xxRqstQty            = ", xxRqstQty);

                            // call [NLZC001001] : Inventory Allocation API (In Transit)
                            if (xxRqstQty.compareTo(ZERO) > 0) {
                                setValue(pMsg.locStsCd,  getInTrnstLocStsCd());
                                setValue(pMsg.xxRqstQty, xxRqstQty);
                                final String apiErrMsgId = callInventoryAllocationAPI(pMsg);
                                if (hasValue(apiErrMsgId)) {
                                    rollback();
                                    continue;
                                }
                            }
                        }
                        
                        debug(this, "========== cancel 'In Transit' Qty - [E N D]");
                    }

                    // --------------------------------------------------
                    // cancel 'On Hand' Qty
                    // --------------------------------------------------
                    cancelOnHandQty = cancelOnHandQty.add(nullToZero(allocOnHandQtyItem.getValue()));
                    if (cancelOnHandQty.compareTo(ZERO) > 0) {
                        
                        debug(this, "========== cancel 'On Hand' Qty - [START]");
                        debug(this, " - cancelOnHandQty      = ", cancelOnHandQty);

                        // lock [INVTY]
                        final INVTYTMsg invty = getOnHandInvtyWithLock(lineMsg);
                        
                        if (invty == null) {
                            debug(this, "***** On Hand INVTY is Null.");

                            // nothing to do.
                        
                        } else {
                            
                            // cancel available Qty.
                            final BigDecimal invtyAllocQty = nullToZero(invty.invtyAllocQty.getValue());
                            debug(this, " - cancel available Qty = ", invtyAllocQty);
                            
                            final BigDecimal xxRqstQty;
                            if (cancelOnHandQty.compareTo(invtyAllocQty) > 0) {
                                xxRqstQty = invtyAllocQty;
                            } else {
                                xxRqstQty = cancelOnHandQty;
                            }
                            debug(this, " - xxRqstQty            = ", xxRqstQty);
    
                            // call [NLZC001001] : Inventory Allocation API (On Hand)
                            if (xxRqstQty.compareTo(ZERO) > 0) {
                                setValue(pMsg.locStsCd,  getOnHandLocStsCd());
                                setValue(pMsg.xxRqstQty, xxRqstQty);
                                final String apiErrMsgId = callInventoryAllocationAPI(pMsg);
                                if (hasValue(apiErrMsgId)) {
                                    rollback();
                                    continue;
                                }
                            }
                            
                            cancelOnHandQty = cancelOnHandQty.subtract(xxRqstQty);
                        }
                        
                        debug(this, "========== cancel 'On Hand' Qty - [E N D]");
                    }

                    // --------------------------------------------------
                    // logicalRemove [HID_INVTY]
                    // --------------------------------------------------
                    logicalRemoveHidInvty(hidInvty, cancelInTrnstQty, cancelOnHandQty);
                    commit();
                }
            }

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private HID_INVTYTMsg lockHidInvty(NWDL0260_ACMsg lineMsg) {

        final HID_INVTYTMsg hidInvty = new HID_INVTYTMsg();
        setValue(hidInvty.glblCmpyCd, getGlobalCompanyCode());
        setValue(hidInvty.hidInvtyPk, lineMsg.hidInvtyPk_A1);

        return (HID_INVTYTMsg) findByKeyForUpdateNoWait(hidInvty);
    }

    private String callInventoryAllocationAPI(NLZC001001PMsg pMsg) {
        
        // --------------------------------------------------
        // [NLZC001001] : Inventory Allocation API
        // --------------------------------------------------
        debug(this, "call [NLZC001001] : Inventory Allocation API.");
        new NLZC001001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

        String apiErrMsgId = "";

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            String xxMsgId = pMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            if (hasValue(xxMsgId)) {
                if (xxMsgId.endsWith("E")) {
                    apiErrMsgId = xxMsgId;
                    debug(this, "[Error] xxMsgId = ", apiErrMsgId);
                }
            }
        }

        return apiErrMsgId;
    }

    private void logicalRemoveHidInvty(HID_INVTYTMsg hidInvty, BigDecimal remainderInTrnstQty, BigDecimal remainderOnHandQty) {
        final String methodNm = "logicalRemoveHidInvty";
        debugMethodStart(this, methodNm);
        
        try {
            
            // In Transit Qty (remainder)
            debug(this, " - In Transit Qty = ", hidInvty.invtyAdvcInTrnstQty.getValue(), " -> ", remainderInTrnstQty );
            setValue(hidInvty.invtyAdvcInTrnstQty, remainderInTrnstQty);

            // On Hand Qty (remainder)
            debug(this, " - On Hand    Qty = ", hidInvty.invtyAdvcOnHandQty.getValue(),  " -> ", remainderOnHandQty );
            setValue(hidInvty.invtyAdvcOnHandQty, remainderOnHandQty);

            EZDTBLAccessor.update(hidInvty);
            EZDTBLAccessor.logicalRemove(hidInvty);
            
        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private static void commit() {
        EZDConnectionMgr.getInstance().commit();
    }

    private static String createHidTs() {
        
        String slsDt = getSalesDate();
        
        return slsDt + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmsssss").substring(slsDt.length());
    }

    private static BigDecimal getNextHidInvtyPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal("HID_INVTY_SQ");
    }

    private static void rollback() {
        EZDConnectionMgr.getInstance().rollback();
    }

}
