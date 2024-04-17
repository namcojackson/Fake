///*
// * Copyright(c)2015 Canon USA Inc. All rights reserved.
// */
//package business.servlet.NPAL1500.common;
//
//import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_ADDL_HEADER;
//import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
//import parts.servletcommon.EZDCommonHandler;
//import business.servlet.NPAL1500.NPAL1500BMsg;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
//
///**
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 1/28/2016   CITS            N Akaishi       Create          N/A
// *</pre>
// */
//public class NPAL1500CommonLogicForScrnFields {
//
//    /**
//     * Set Screen Protect
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    public static void setProtect(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        // All Protect Fields
//        setHdrItemProtect(scrnMsg);
//        setHdrBtnlItemProtect(handler, scrnMsg);
//        setAddlHdrItemProtect(scrnMsg);
//        setAddlHdrBtnlItemProtect(handler, scrnMsg);
//        setDtlItemProtect(scrnMsg);
//        setDtlBtnlItemProtect(handler, scrnMsg);
//        
//        // UnProtected By PO Header Status
//        setUnProtectByStatus(handler, scrnMsg);
//        
//        // Set Protect By Authority
//        setProtectByAuthority(handler, scrnMsg);
//    }
//
//    /**
//     * Set Screen Protect By Status
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setUnProtectByStatus(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//        // set Header Protect
//        setUnProtectByHdrSts(handler, scrnMsg);
//
//        // set Addl Header or Detail Protect
//        String dplyTab = scrnMsg.xxDplyTab.getValue();
//
//        if (TAB_ADDL_HEADER.equals(dplyTab)) {
//            setUnProtectByDtlTab(handler, scrnMsg);
//        } else if (TAB_DETAIL.equals(dplyTab)) {
//
//        }
//    }
//
//    /**
//     * Set Screen Protect By Authority
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectByAuthority(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * Set Screen Protect By Header Status
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setUnProtectByHdrSts(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//        final String hdrStsCd = scrnMsg.poHdrStsCd.getValue();
//        
//        if (ZYPCommonFunc.hasValue(hdrStsCd)) {
//            
//            if (PO_STS.SAVED.equals(hdrStsCd)) {
//                setUnProtectHdrStsForEntered(handler, scrnMsg);
//                
//            } else if (PO_STS.WAITING_FOR_APPROVAL.equals(hdrStsCd)) {
//
//            } else if (PO_STS.VALIDATED.equals(hdrStsCd)) {
//                setProtectHdrStsForValidated(handler, scrnMsg);
//            } else if (PO_STS.PO_CONFIRMED.equals(hdrStsCd)) {
//
//            } else if (PO_STS.RECEIVING.equals(hdrStsCd)) {
//
//            } else if (PO_STS.RECEIVING_COMPLETION.equals(hdrStsCd)) {
//
//            } else if (PO_STS.CLOSED.equals(hdrStsCd)) {
//
//            } else if (PO_STS.CANCELLED.equals(hdrStsCd)) {
//
//            } else if (PO_STS.PO_ERROR.equals(hdrStsCd)) {
//
//            } else if (PO_STS.SUBMITTED.equals(hdrStsCd)) {
//
//            }
//
//        } else { // New, PO Copy
//            setProtectHdrStsForUnregistered(handler, scrnMsg);
//        }
//    }
//
//    /**
//     * Set Screen Protect By Line Status For Line Tab
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setUnProtectByDtlTab(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : Unregistered]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForUnregistered(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        // Header
//        setHdrBtnProtectForUnregistered(handler, scrnMsg);
//        // Addl Header Tab
//        setAddlHdrBtnProtectForUnregistered(handler, scrnMsg);
//        // Detail Tab
//        setLineTabBtnProtectForUnregistered(handler, scrnMsg);
//    }
//
//    /**
//     * <pre>
//     * Screen unprotecting control [Header Status : Saved]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setUnProtectHdrStsForEntered(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen unprotecting control [Header Status : Validated]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForValidated(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        // Header
//        // Header Tab
//        // Addl Header Tab
//        // Detail Tab
//        setLineTabBtnProtectForValidationHold(handler, scrnMsg);
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : PO Confirmed]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForPoConfirmed(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : Receiving]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForReceiving(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : Receiving Confirmation]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForReceivingConfirmation(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : Closed]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForClosed(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : Cancelled]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForCancelled(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : PO Error]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForPoError(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Screen protecting control [Header Status : Submitted]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setProtectHdrStsForSubmitted(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Set Header Item Protect
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setHdrItemProtect(NPAL1500BMsg scrnMsg) {
//
//        scrnMsg.poNum.setInputProtected(false);
//        scrnMsg.dsPoTpCd.setInputProtected(true);
//
//        scrnMsg.rqstRcvDt.setInputProtected(true);
//        scrnMsg.rqstRcvTm.setInputProtected(true);
//
//        scrnMsg.prchGrpCd.setInputProtected(true);
//        scrnMsg.poOrdCmntTxt.setInputProtected(true);
//
//        scrnMsg.poHdrStsCd.setInputProtected(true);
//        scrnMsg.poHdrStsDescTxt.setInputProtected(true);
//        scrnMsg.poApvlStsCd.setInputProtected(true);
//        scrnMsg.poApvlStsDescTxt.setInputProtected(true);
//        scrnMsg.poApvlDt.setInputProtected(true);
//
//        scrnMsg.prntVndCd.setInputProtected(true);
//        scrnMsg.prntVndNm.setInputProtected(true);
//
//        scrnMsg.vndCd.setInputProtected(true);
//        scrnMsg.vndNm.setInputProtected(true);
//
//        // DS_PO_TP_CD=BB(Buyback) enable
//        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
//            scrnMsg.srcRtlWhCd_SC.setInputProtected(false);
//            scrnMsg.rtlWhNm_SC.setInputProtected(false);
//        } else {
//            scrnMsg.srcRtlWhCd_SC.setInputProtected(true);
//            scrnMsg.rtlWhNm_SC.setInputProtected(true);
//        }
//
//        scrnMsg.destRtlWhCd_DS.setInputProtected(true);
//        scrnMsg.rtlWhNm_DS.setInputProtected(true);
//
//        scrnMsg.shipToCustCd.setInputProtected(true);
//        scrnMsg.shipToLocNm.setInputProtected(true);
//    }
//
//    /**
//     * <pre>
//     * Set Header Item Protect
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setHdrItemUnProtectPrchGrpCd(NPAL1500BMsg scrnMsg) {
//        scrnMsg.prchGrpCd.setInputProtected(false);
//    }
//
//    /**
//     * <pre>
//     * Set Header Item Protect
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setHdrItemUnProtectPoOrdCmt(NPAL1500BMsg scrnMsg) {
//        scrnMsg.poOrdCmntTxt.setInputProtected(false);
//    }
//
//    private static void setHdrItemUnProtectRqstRcvDt(NPAL1500BMsg scrnMsg) {
//        scrnMsg.rqstRcvDt.setInputProtected(false);
//        scrnMsg.rqstRcvTm.setInputProtected(false);
//    }
//
//    /**
//     * <pre>
//     * Set Additional Header TAB Item Protect
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setAddlHdrItemProtect(NPAL1500BMsg scrnMsg) {
//        // Ship To Location
//        scrnMsg.shipToAddlLocNm_ST.setInputProtected(true);
//        scrnMsg.xxAllLineAddr_ST.setInputProtected(true);
//        scrnMsg.shipToPostCd_ST.setInputProtected(true);
//        scrnMsg.shipToCtyAddr_ST.setInputProtected(true);
//        scrnMsg.shipToCntyNm_ST.setInputProtected(true);
//        scrnMsg.shipToStCd_ST.setInputProtected(true);
//        scrnMsg.shipToProvNm_ST.setInputProtected(true);
//        scrnMsg.shipToCtryCd_ST.setInputProtected(true);
//        scrnMsg.ctryDescTxt_ST.setInputProtected(true);
//        scrnMsg.ctacPsnNm.setInputProtected(true);
//        // Bill To Location
//        scrnMsg.varCharConstVal_B1.setInputProtected(true);
//        scrnMsg.xxAllLineAddr_BT.setInputProtected(true);
//        scrnMsg.varCharConstVal_B2.setInputProtected(true);
//        scrnMsg.varCharConstVal_B3.setInputProtected(true);
//        scrnMsg.varCharConstVal_B4.setInputProtected(true);
//        scrnMsg.varCharConstVal_B5.setInputProtected(true);
//        scrnMsg.varCharConstVal_B6.setInputProtected(true);
//        scrnMsg.varCharConstVal_B7.setInputProtected(true);
//        scrnMsg.varCharConstVal_B8.setInputProtected(true);
//        scrnMsg.varCharConstVal_B9.setInputProtected(true);
//        scrnMsg.varCharConstVal_BA.setInputProtected(true);
//        scrnMsg.ctryDescTxt_BT.setInputProtected(true);
//        // CSA Return Address
//        scrnMsg.rtrnShipToRtlWhCd_RW.setInputProtected(true);
//        scrnMsg.rtlWhNm_RW.setInputProtected(true);
//        scrnMsg.addlLocNm_RW.setInputProtected(true);
//        scrnMsg.xxAllLineAddr_RW.setInputProtected(true);
//        scrnMsg.postCd_RW.setInputProtected(true);
//        scrnMsg.ctyAddr_RW.setInputProtected(true);
//        scrnMsg.cntyNm_RW.setInputProtected(true);
//        scrnMsg.ctyAddr_RW.setInputProtected(true);
//        scrnMsg.cntyNm_RW.setInputProtected(true);
//        scrnMsg.stCd_RW.setInputProtected(true);
//        scrnMsg.provNm_RW.setInputProtected(true);
//        scrnMsg.ctryCd_RW.setInputProtected(true);
//        scrnMsg.ctryDescTxt_RW.setInputProtected(true);
//        // Freight Information
//        // Freight Term
//        scrnMsg.frtCondCd.setInputProtected(true);
//        // Service Level
//        scrnMsg.shpgSvcLvlCd.setInputProtected(true);
//        // Carrier
//        scrnMsg.carrCd.setInputProtected(true);
//        scrnMsg.carrNm.setInputProtected(true);
//        // Carrier Account
//        scrnMsg.carrAcctNum.setInputProtected(true);
//        // Header Details
//        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
//        // Special Instructions
//        scrnMsg.poMsgPk_SI.setInputProtected(true);
//        // Receiver Note
//        scrnMsg.poMsgPk_RN.setInputProtected(true);
//        // Shipper Note
//        scrnMsg.poMsgPk_SN.setInputProtected(true);
//    }
//
//    /**
//     * <pre>
//     * Set Additional Header TAB Item UnProtect(Ship To Location)
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setAddlHdrItemUnProtectShipTo(NPAL1500BMsg scrnMsg) {
//        // Ship To Location
//        scrnMsg.shipToAddlLocNm_ST.setInputProtected(false);
//        scrnMsg.xxAllLineAddr_ST.setInputProtected(false);
//        scrnMsg.shipToPostCd_ST.setInputProtected(false);
//        scrnMsg.shipToCtyAddr_ST.setInputProtected(false);
//        scrnMsg.shipToCntyNm_ST.setInputProtected(false);
//        scrnMsg.shipToStCd_ST.setInputProtected(false);
//        scrnMsg.shipToProvNm_ST.setInputProtected(false);
//        scrnMsg.shipToCtryCd_ST.setInputProtected(false);
//        scrnMsg.ctryDescTxt_ST.setInputProtected(false);
//        scrnMsg.ctacPsnNm.setInputProtected(false);
//    }
//
//    /**
//     * <pre>
//     * Set Additional Header TAB Item UnProtect(Freight Information)
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setAddlHdrItemUnProtectHeFreightInfo(NPAL1500BMsg scrnMsg) {
//        // Freight Information
//        // Freight Term
//        scrnMsg.frtCondCd.setInputProtected(false);
//        // Service Level
//        scrnMsg.shpgSvcLvlCd.setInputProtected(false);
//        // Carrier
//        scrnMsg.carrCd.setInputProtected(false);
//        scrnMsg.carrNm.setInputProtected(false);
//        // Carrier Account
//        scrnMsg.carrAcctNum.setInputProtected(false);
//    }
//
//    /**
//     * <pre>
//     * Set Additional Header TAB Item UnProtect(Header Detail)
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setAddlHdrItemUnProtectHeaderDetail(NPAL1500BMsg scrnMsg) {
//        // Header Details
//        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
//        // Special Instructions
//        scrnMsg.poMsgPk_SI.setInputProtected(false);
//        // Receiver Note
//        scrnMsg.poMsgPk_RN.setInputProtected(false);
//        // Shipper Note
//        scrnMsg.poMsgPk_SN.setInputProtected(false);
//    }
//
//    /**
//     * <pre>
//     * Set Detail TAB Item Protect
//     * </pre>
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setDtlItemProtect(NPAL1500BMsg scrnMsg) {
//        // Config ID
//        scrnMsg.svcConfigMstrPk.setInputProtected(true);
//        // Detail List
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//            scrnMsg.A.no(i).poLineTpCd_A1.setInputProtected(true);
//            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
//            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
//            scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
//            scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
//            scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(true);
//            scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(true);
//            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
//            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
//            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
//            scrnMsg.A.no(i).poLineStsDescTxt_A1.setInputProtected(true);
//            scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(true);
//            scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(true);
//            scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(true);
//        }
//        // Detail Footer
//        scrnMsg.trsmtMethTpCd.setInputProtected(true);
//        if (ZYPCommonFunc.hasValue(scrnMsg.trsmtMethTpCd) && TRSMT_METH_TP.PRINTER.equals(scrnMsg.trsmtMethTpCd)) {
//            scrnMsg.rptDestId.setInputProtected(true);
//        }
//    }
//
//    /**
//     * <pre>
//     * Set header Button Item Protect
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setHdrBtnlItemProtect(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        handler.setButtonEnabled("Search", false);
//        handler.setButtonEnabled("rqstRcvDt", false);
//        handler.setButtonEnabled("Search", false);
//        handler.setButtonEnabled("Copy", false);
//        handler.setButtonEnabled("OpenWin_AppvlHistory", false);
//    }
//
//    /**
//     * <pre>
//     * Set Additional Header TAB Button Item Protect
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setAddlHdrBtnlItemProtect(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        // handler.setButtonEnabled("TextEntry", false);
//    }
//
//    /**
//     * <pre>
//     * Set Detail TAB Button Item Protect
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setDtlBtnlItemProtect(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        // Detail Header
//        // Config ID
//        handler.setButtonEnabled("ApplyConfig", false);
//        // Add New Line
//        handler.setButtonEnabled("Add_NewLine", false);
//        // Detail List
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            // Item#
//            handler.setButtonEnabled("OpenWin_Mdse", i, false);
//            // Calender
//            // handler.setButtonEnabled("OpenWin_Mdse", i, false);
//            // Dest SWH
//            handler.setButtonEnabled("OpenWin_DestSWH", i, false);
//            // Source SWH
//            handler.setButtonEnabled("OpenWin_SrcSWH", i, false);
//            // Serial# Search
//            handler.setButtonEnabled("OpenWin_SerEnt", i, false);
//            // Charge ACC Search
//            handler.setButtonEnabled("OpenWin_AccountChrg", i, false);
//            // Accrual ACC Search
//            handler.setButtonEnabled("OpenWin_AccountAcrl", i, false);
//            // Variance ACC Search
//            handler.setButtonEnabled("OpenWin_AccountVar", i, false);
//        }
//        // Detail Footer
//        handler.setButtonEnabled("SelectAll", false);
//        handler.setButtonEnabled("UnSelectAll", false);
//        handler.setButtonEnabled("MoveTo_Componet", false);
//        handler.setButtonEnabled("OpenWin_CUSAEventHistory", false);
//        handler.setButtonEnabled("Cancel", false);
//        handler.setButtonEnabled("Close", false);
//        handler.setButtonEnabled("Print", false);
//    }
//
//    /**
//     * <pre>
//     * Set Header Link Item Protect
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setLnkItemProtect(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//        // TODO Link Item's Protect
//        // scrnMsg.billToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
//        // Supplier
//        // Supplier Site
//        // Source WH
//        // Destionation WH
//        // Ship To Customer
//
//        // State
//        // Country
//        // Carrier
//
//        // Select from CONFIG
//
//        // 
//
//    }
//
//    /**
//     * <pre>
//     * Set Header Button Protect [Header Status : Unregistered]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setHdrBtnProtectForUnregistered(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//        
//    }
//
//    /**
//     * <pre>
//     * Set Header Button Protect [Header Status : Entered]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setHdrBtnProtectForSaved(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//    }
//
//    /**
//     * <pre>
//     * Set Header Button Protect [Header Status : Unregistered]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setAddlHdrBtnProtectForUnregistered(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//        // handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, false);
//    }
//
//    /**
//     * <pre>
//     * Set Line Tab Button Protect [Header Status : Unregistered]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setLineTabBtnProtectForUnregistered(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//        // handler.setButtonEnabled(BTN_METERENTRY_EVENT_NM, false);
//    }
//
//    /**
//     * <pre>
//     * Set Line Tab Button Protect [Header Status : ValidationHold]
//     * </pre>
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NPAL1500BMsg
//     */
//    private static void setLineTabBtnProtectForValidationHold(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
//
//        // NPAL1500CommonLogic.inactiveAddButton(handler);
//        // handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
//        // handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
//        // handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
//        // handler.setButtonEnabled(BTN_AUTO_ADD_SUPPLY_EVENT_NM,
//        // false);
//        // handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
//    }
//    
//    
//}
