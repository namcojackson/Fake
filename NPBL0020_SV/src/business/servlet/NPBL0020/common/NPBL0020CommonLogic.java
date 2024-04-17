/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020.common;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.NPBL0020BMsg;
import business.servlet.NPBL0020.NPBL0020_XBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 * 04/13/2016   CITS            K.Ogino         Update          QC#7037
 * 04/13/2016   CITS            K.Ogino         Update          QC#6875
 * 04/25/2016   CITS            K.Ogino         Update          QC#7055
 * 04/26/2016   CITS            K.Ogino         Update          QC#6877
 * 06/09/2016   CITS            K.Ogino         Update          QC#9712
 * 06/10/2016   CSAI            D.Fukaya        Update          QC#9048
 * 06/14/2016   CSAI            D.Fukaya        Update          QC#9044
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9297
 * 06/24/2016   CSAI            D.Fukaya        Update          QC#9292
 * 06/28/2016   CSAI            D.Fukaya        Update          QC#7735
 * 07/29/2016   CITS            K.Ogino         Update          QC#8288
 * 08/03/2016   CITS            K.Ogino         Update          QC#12517
 * 08/04/2016   CITS            K.Ogino         Update          QC#13006
 * 08/05/2016   CITS            K.Ogino         Update          QC#8003
 * 08/22/2016   CITS            K.Ogino         Update          QC#9058
 * 09/07/2016   CITS            K.Ogino         Update          QC#11540
 * 10/03/2016   CITS            Y.IWASAKI       Update          QC#14576
 * 12/20/2016   CITS            K.Ogino         Update          QC#15815
 * 01/10/2017   CITS            K.Ogino         Update          QC#16296
 * 01/13/2017   CITS            T.Kikuhara      Update          QC#17099
 * 02/08/2016   CITS            K.Ogino         Update          QC#17483
 * 02/27/2016   CITS            T.Wada          Update          QC#17362
 * 04/06/2016   CITS            K.Ogino         Update          QC#18215
 * 06/26/2017   CITS            K.Ogino         Update          QC#19076
 * 08/08/2017   CITS            Y.Iwasaki       Update          QC#20118
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 10/10/2017   CITS            K.Ogino         Update          QC#21682
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 * 11/15/2017   CITS            K.Ogino         Update          QC#22608
 * 02/27/2018   CITS            T.Tokutomi      Update          QC#22376, 22511
 * 03/27/2018   CITS            T.Wada          Update          QC#22517
 * 04/03/2018   CITS            S.Katsuma       Update          QC#23521,25063
 * 04/09/2018   CITS            T.Kikuhara      Update          QC#24994
 * 05/25/2018   CITS            S.Katsuma       Update          QC#25893
 * 06/11/2018   CITS            S.Katsuma       Update          QC#26193
 * 11/20/2018   CITS            Y.Iwasaki       Update          QC#29212
 * 01/15/2019   Fujitsu         T.Ogura         Update          QC#29774
 * 02/04/2019   CITS            M.Naito         Update          QC#30049
 * 02/13/2019   Fujitsu         T.Ogura         Update          QC#30267
 * 03/08/2019   CITS            K.Ogino         Update          QC#30618
 * 09/26/2019   Fujitsu         T.Ogura         Update          QC#52362
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 * 12/16/2022   Hitachi         T.Kuroda        Update          QC#60562
 *</pre>
 */
public class NPBL0020CommonLogic {

    /**
     * Check Input for Save and Submit
     * @param scrnMsg NPBL0020BMsg
     */
    public static void checkInputForSaveAndSubmit(NPBL0020BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL)) {
            scrnMsg.prchReqTpCd_SL.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_PRCH_REQ_TP_DESC_TXT });
        } else {
            // QC#19076
            if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                    ) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt)) {
                    scrnMsg.rqstRcvDt.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RQST_RCV_DT });
                } else {
                    if (ZYPDateUtil.isPastDate(scrnMsg.rqstRcvDt.getValue())) {
                        scrnMsg.rqstRcvDt.setErrorInfo(1, NPAM0079E, new String[] {DISPLAY_NM_RQST_RCV_DT });
                    }
                }
            }
            // QC#17099 add start
            if ((PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue())) && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)) {
                scrnMsg.srcRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD });
            }
            // QC#22511 Update.
            if (PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)) {
                scrnMsg.srcRtlSwhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD });
            }
            // if
            // ((PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())
            // && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd))) {
            // scrnMsg.srcRtlSwhCd.setErrorInfo(1, ZZZM9025E, new
            // String[] {DISPLAY_NM_SRC_RTL_SWH_CD });
            // }
            // QC#17099 add end
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnRsnCd_SL)) {
                scrnMsg.vndRtrnRsnCd_SL.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_VND_RTRN_RSN_DESC_TXT });
                scrnMsg.xxDplyTab.setValue(TAB_HEADER);
                // clear html attribute
                scrnMsg.clearAllGUIAttribute(SCRN_ID);
            }
        }

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NPAM1360E);
        }

        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        }

        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
        // QC#17099 add start
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        // QC#17099 add end
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        // QC#22608 Start
        if (!PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        }
        // QC#22608 End
        scrnMsg.addCheckItem(scrnMsg.vndRtrnRsnCd_SL);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
        scrnMsg.putErrorScreen();
    }

    /**
     * Check Input for Save and Submit
     * @param scrnMsg NPBL0020BMsg
     * @param bizMsg NPBL0020CMsg
     * @return false : input check error
     */
    public static boolean postInputCheckForSaveAndSubmit(NPBL0020BMsg scrnMsg, NPBL0020CMsg bizMsg) {

        boolean isError = false;
        boolean retError = true;
        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
            scrnMsg.addCheckItem(scrnMsg.carrNm);
            scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
            scrnMsg.addCheckItem(scrnMsg.vndCd);
            scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
            scrnMsg.addCheckItem(scrnMsg.vndRtrnRsnCd_SL);
            // Expense Order
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd_EO);
            scrnMsg.addCheckItem(scrnMsg.shipToLocNm_EO);
            scrnMsg.putErrorScreen();
            retError = false;
        } else {
            isError = checkErrorItem(scrnMsg.rtlWhNm_DW, scrnMsg.rtlWhNm_SW, scrnMsg.fullPsnNm, scrnMsg.carrNm, scrnMsg.shpgSvcLvlCd_SL, scrnMsg.vndCd, scrnMsg.dplyVndNm, scrnMsg.destRtlWhCd, scrnMsg.vndRtrnRsnCd_SL,
                    scrnMsg.shipToCustCd_EO, scrnMsg.shipToLocNm_EO);
            if (isError) {
                scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
                scrnMsg.addCheckItem(scrnMsg.rtlWhNm_SW);
                scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
                scrnMsg.addCheckItem(scrnMsg.carrNm);
                scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
                scrnMsg.addCheckItem(scrnMsg.vndCd);
                scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
                scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
                scrnMsg.addCheckItem(scrnMsg.vndRtrnRsnCd_SL);
                // Expense Order
                scrnMsg.addCheckItem(scrnMsg.shipToCustCd_EO);
                scrnMsg.addCheckItem(scrnMsg.shipToLocNm_EO);
                scrnMsg.putErrorScreen();
                retError = false;
            } else {
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    // QC#56867 Mod
                    isError = checkErrorItem(scrnMsg.A.no(i).prchReqLineTpCd_A1, scrnMsg.A.no(i).mdseCd_A1, scrnMsg.A.no(i).prchReqDispQty_A1, scrnMsg.A.no(i).rtlWhNm_A1, scrnMsg.A.no(i).srcRtlSwhCd_A1, scrnMsg.A.no(i).rtlWhNm_A2,
                            scrnMsg.A.no(i).destRtlSwhCd_A1, scrnMsg.A.no(i).fromStkStsCd_A1, scrnMsg.A.no(i).xxScrItem50Txt_A1, scrnMsg.A.no(i).xxLogDtlTxt_A1, scrnMsg.A.no(i).shipToLocNm_E1, scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1, scrnMsg.A.no(i).xxChkBox_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineTpCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqDispQty_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).srcRtlSwhCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A2);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).destRtlSwhCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).fromStkStsCd_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem50Txt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxLogDtlTxt_A1);
                    // Expense Order
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToLocNm_E1);
                    // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                    if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        isError = checkErrorItem(scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                    }
                    // END 2018/05/25 S.Katsuma [QC#25893,ADD]
                    // QC#56867 Add
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
                if (isError) {
                    scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
                    retError = false;
                }
            }
            scrnMsg.putErrorScreen();
        }
        return retError;
    }

    /**
     * Check Input for Add New Line And Add Config, Existing Config
     * @param scrnMsg NPBL0020BMsg
     * @param bizMsg NPBL0020CMsg
     * @return false : input check error
     */
    public static boolean postInputCheckForAddLineAndConfig(NPBL0020BMsg scrnMsg, NPBL0020CMsg bizMsg) {

        boolean isError = false;
        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhNm_SW);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_SS);
            scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_DS);
            scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
            scrnMsg.addCheckItem(scrnMsg.vndCd);
            scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
            // Expense Order
            scrnMsg.addCheckItem(scrnMsg.shipToLocNm_EO);
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd_EO);
            scrnMsg.putErrorScreen();
            return false;
        } else {
            isError = checkErrorItem(scrnMsg.rtlWhNm_SW, scrnMsg.rtlSwhNm_SS, scrnMsg.rtlWhNm_DW, scrnMsg.rtlWhNm_DW, scrnMsg.dplyVndNm, scrnMsg.shipToLocNm_EO, scrnMsg.srcRtlWhCd, scrnMsg.srcRtlSwhCd, scrnMsg.destRtlWhCd,
                    scrnMsg.destRtlSwhCd, scrnMsg.vndCd, scrnMsg.shipToCustCd_EO);
            if (isError) {
                scrnMsg.addCheckItem(scrnMsg.rtlWhNm_SW);
                scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_SS);
                scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
                scrnMsg.addCheckItem(scrnMsg.rtlSwhNm_DS);
                scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
                scrnMsg.addCheckItem(scrnMsg.vndCd);
                scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
                scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
                scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
                scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
                // Expense Order
                scrnMsg.addCheckItem(scrnMsg.shipToLocNm_EO);
                scrnMsg.addCheckItem(scrnMsg.shipToCustCd_EO);
                scrnMsg.putErrorScreen();
                if (isError) {
                    scrnMsg.xxDplyTab.setValue(TAB_HEADER);
                }
                return false;
            }
            return true;
        }
    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setCtrlScrnItemDispInit(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitNoSelectRequestType(handler, scrnMsg);
        } else if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitSubcontract(handler, scrnMsg);
            // QC#55313
            handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        } else if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitWhTransfer(handler, scrnMsg);
        } else if (PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitDisposal(handler, scrnMsg);
        } else if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitVendorReturn(handler, scrnMsg);
        } else if (PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitRefurbishing(handler, scrnMsg);
        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            setCtrlScrnItemDispInitExpenseOrder(handler, scrnMsg);
        }

    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(No Select Request Type)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitNoSelectRequestType(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setIndispensable(false);
        scrnMsg.srcRtlSwhCd.setIndispensable(false);
        scrnMsg.vndRtrnRsnCd_SL.setIndispensable(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_DW.setInputProtected(false);
        scrnMsg.destRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_DS.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.vndCd.setIndispensable(false);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(false);
        scrnMsg.xxLinkAncr_DW.setInputProtected(false);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SC.setInputProtected(false);
        scrnMsg.xxLinkAncr_SL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(false);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(false);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
            handler.setButtonEnabled(BTN_ITEM, i, true);
            handler.setButtonEnabled(BTN_ITEM_INFO, i, true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(false);
            handler.setButtonEnabled(BTN_SRC_WH, i, true);
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(false);
            handler.setButtonEnabled(BTN_DEST_WH, i, true);
            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
//            if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
//                handler.setButtonEnabled(BTN_ACCOUNT, i, false);
//            } else {
//                handler.setButtonEnabled(BTN_ACCOUNT, i, true);
//            }
            handler.setButtonEnabled(BTN_ACCOUNT, i, NPBL0020CommonLogic.isNeededAccount(scrnMsg.chrgAcctEdtblFlg.getValue()));
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
            // Expense Order
            scrnMsg.A.no(i).shipToCustCd_E1.setInputProtected(false);
            handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, true);
            // QC#17843
            handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, true);
            handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, true);
            // START 2018/05/25 S.Katsuma [QC#25893,ADD]
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
            // END 2018/05/25 S.Katsuma [QC#25893,ADD]
            // START 2018/06/11 S.Katsuma [QC#26193,ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shpgSerTakeFlg_A1.getValue())) {
                handler.setButtonEnabled(BTN_SERIAL, i, true);
                scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(false);
            } else {
                handler.setButtonEnabled(BTN_SERIAL, i, false);
                scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
            }
            // END 2018/06/11 S.Katsuma [QC#26193,ADD]
        }

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(Request Type Subcontract)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitSubcontract(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(true);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(true);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_DW.setInputProtected(false);
        // QC#22608 Start
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        // QC#22608 End
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Mandatory
        scrnMsg.srcRtlWhCd.setIndispensable(true);
        scrnMsg.destRtlWhCd.setIndispensable(true);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(false);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SC.setInputProtected(true);
        scrnMsg.xxLinkAncr_SL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(false);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, false);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        // QC#22608 Start
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        // QC#22608 End
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(Request Type WH Transfer)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitWhTransfer(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.rqstRcvDt.setIndispensable(true);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setIndispensable(false);
        scrnMsg.srcRtlSwhCd.setIndispensable(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_DW.setInputProtected(false);
        scrnMsg.destRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_DS.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(true);
        scrnMsg.shipToLocNm_EO.setInputProtected(true);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(false);
        scrnMsg.xxLinkAncr_DW.setInputProtected(false);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SC.setInputProtected(false);
        scrnMsg.xxLinkAncr_SL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(true);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, false);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, true);
        scrnMsg.svcConfigMstrPk.setInputProtected(false);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, true);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(Request Type Disposal)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitDisposal(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.rqstRcvDt.setIndispensable(true);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setIndispensable(false);
        scrnMsg.srcRtlSwhCd.setIndispensable(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_DW.setInputProtected(true);
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.vndCd.setIndispensable(false);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(true);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SC.setInputProtected(false);
        scrnMsg.xxLinkAncr_SL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(false);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(false);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, true);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(Request Type Vendor Return)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitVendorReturn(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setIndispensable(true);
        // QC# 22376 Modify. Source Sub Warehouse is not mandatory.
        scrnMsg.srcRtlSwhCd.setIndispensable(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_DW.setInputProtected(true);
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.vndCd.setIndispensable(true);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(true);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SC.setInputProtected(true);
        scrnMsg.xxLinkAncr_SL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(false);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(false);
        scrnMsg.vndRtrnRsnCd_SL.setIndispensable(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(Request Type Refurbishing)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitRefurbishing(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setIndispensable(true);
        scrnMsg.srcRtlSwhCd.setIndispensable(true);
        scrnMsg.vndCd.setIndispensable(true);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_DW.setInputProtected(true);
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(true);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SC.setInputProtected(true);
        scrnMsg.xxLinkAncr_SL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(false);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    // QC#22517 Add Start
    /**
     * setCtrAddrDisp
     * @param scrnMsg
     */
    private static void setCtrAddrDisp(NPBL0020BMsg scrnMsg) {

        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.shipToLocNm.setInputProtected(false);
            scrnMsg.shipToAddlLocNm.setInputProtected(false);
            scrnMsg.xxShipVndAddr.setInputProtected(false);
            scrnMsg.shipToPostCd.setInputProtected(false);
            scrnMsg.shipToCtyAddr.setInputProtected(false);
            scrnMsg.shipToCntyNm.setInputProtected(false);
            scrnMsg.shipToStCd.setInputProtected(false);
            scrnMsg.shipToProvNm.setInputProtected(false);
            scrnMsg.shipToCtryCd.setInputProtected(false);

            scrnMsg.xxShipVndAddr.setIndispensable(true);
            scrnMsg.shipToPostCd.setIndispensable(true);
            scrnMsg.shipToCtyAddr.setIndispensable(true);
            scrnMsg.shipToCtryCd.setIndispensable(true);

            scrnMsg.xxLinkAncr_AD.setInputProtected(false);
            scrnMsg.xxLinkAncr_CT.setInputProtected(false);

        } else {
            scrnMsg.shipToLocNm.setInputProtected(true);
            scrnMsg.shipToAddlLocNm.setInputProtected(true);
            scrnMsg.xxShipVndAddr.setInputProtected(true);
            scrnMsg.shipToPostCd.setInputProtected(true);
            scrnMsg.shipToCtyAddr.setInputProtected(true);
            scrnMsg.shipToCntyNm.setInputProtected(true);
            scrnMsg.shipToStCd.setInputProtected(true);
            scrnMsg.shipToProvNm.setInputProtected(true);
            scrnMsg.shipToCtryCd.setInputProtected(true);

            scrnMsg.xxLinkAncr_AD.setInputProtected(true);
            scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        }
        scrnMsg.ctryNm.setInputProtected(true);

    }
    // QC#22517 Add End
    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen(Request Type Expense Order)
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispInitExpenseOrder(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(false);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.rqstRcvDt.setIndispensable(true);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        scrnMsg.carrNm.setInputProtected(false);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_SW.setInputProtected(false);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setIndispensable(false);
        scrnMsg.srcRtlSwhCd.setIndispensable(false);
        scrnMsg.rtlSwhNm_SS.setInputProtected(false);
        scrnMsg.destRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_DW.setInputProtected(true);
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        // Expense Order
        scrnMsg.shipToCustCd_EO.setInputProtected(false);
        scrnMsg.shipToLocNm_EO.setInputProtected(false);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(true);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(false);
        scrnMsg.xxLinkAncr_SC.setInputProtected(false);
        scrnMsg.xxLinkAncr_SL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SS.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        // Expense Order
        scrnMsg.xxLinkAncr_OS.setInputProtected(false);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_COPY, false);
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, true);
        scrnMsg.svcConfigMstrPk.setInputProtected(false);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, true);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        // QC#22517
        setCtrAddrDisp(scrnMsg);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
    }

    /**
     * The method explanation: The display control of the screen item
     * after Search
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setCtrlScrnItemDispAfterSearch(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);

        // total cost
        scrnMsg.xxTotAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqSavedFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.openStsFlg.getValue())) {
            // After Save
            setCtrlScrnItemDispAfterSave(handler, scrnMsg);
        } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.prchReqSavedFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.openStsFlg.getValue())) {
            // After Submit
            setCtrlScrnItemDispAfterSubmit(handler, scrnMsg);
        } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.prchReqSavedFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.openStsFlg.getValue())) {
            // After Search/Close or HeaderCancel
            setCtrlScrnItemDispAfterCloseOrCancel(handler, scrnMsg);
        } else if (ZYPCommonFunc.hasValue(scrnMsg.prchReqStsCd) && PRCH_REQ_STS.CANCELLED.equals(scrnMsg.prchReqStsCd.getValue())) {
            setCtrlScrnItemDispAfterCloseOrCancel(handler, scrnMsg);
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);
            } else if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                setCtrlScrnItemDispInitSubcontract(handler, scrnMsg);
            } else if (ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL.getValue())) {
                setCtrlScrnItemDispInit(handler, scrnMsg);
            } else {
                setCtrlScrnItemDispInitNoSelectRequestType(handler, scrnMsg);
            }
        }

        // QC#55313
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        }
    }

    /**
     * The method explanation: The display control of the screen item
     * after Save
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispAfterSave(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(true);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        // QC#18215 MOD START
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.rqstRcvDt.setInputProtected(true);
        } else {
            scrnMsg.rqstRcvDt.setInputProtected(false);
            scrnMsg.rqstRcvDt.setIndispensable(true);    // 2019/01/15 T.Ogura [QC#29774,ADD]
        }
        // QC#18215 MOD END
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.fullPsnNm.setInputProtected(true);
            scrnMsg.xxLinkAncr_RL.setInputProtected(true);
        } else {
            scrnMsg.fullPsnNm.setInputProtected(false);
            scrnMsg.xxLinkAncr_RL.setInputProtected(false);
        }
        // START 2019/02/13 T.Ogura [QC#30267,MOD]
//        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        // END   2019/02/13 T.Ogura [QC#30267,MOD]
        // START 2019/02/04 M.Naito [QC#30049,MOD]
//        scrnMsg.carrNm.setInputProtected(true);
        scrnMsg.carrNm.setInputProtected(false);
        // END 2019/02/04 M.Naito [QC#30049,MOD]
        // START 2019/02/13 T.Ogura [QC#30267,MOD]
//        scrnMsg.ctacPsnNm.setInputProtected(true);
        scrnMsg.ctacPsnNm.setInputProtected(false);
        // END   2019/02/13 T.Ogura [QC#30267,MOD]
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.srcRtlWhCd.setInputProtected(true);
            scrnMsg.rtlWhNm_SW.setInputProtected(true);
            scrnMsg.xxLinkAncr_SW.setInputProtected(true);
            scrnMsg.xxLinkAncr_SC.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_SRC_WH_H, false);
            handler.setButtonEnabled(BTN_GET_SRC_SWH_H, false);
        } else {
            scrnMsg.srcRtlWhCd.setInputProtected(false);
            // START 2019/01/15 T.Ogura [QC#29774,ADD]
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                scrnMsg.srcRtlWhCd.setIndispensable(true);
            }
            // END   2019/01/15 T.Ogura [QC#29774,ADD]
            scrnMsg.rtlWhNm_SW.setInputProtected(false);
            scrnMsg.xxLinkAncr_SW.setInputProtected(false);
            scrnMsg.xxLinkAncr_SC.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_SRC_WH_H, true);
            handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        }
        // Expense Order
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.srcRtlWhCd.setInputProtected(true);
            scrnMsg.rtlWhNm_SW.setInputProtected(true);
            scrnMsg.shipToCustCd_EO.setInputProtected(true);
            scrnMsg.shipToLocNm_EO.setInputProtected(true);
            scrnMsg.xxLinkAncr_SW.setInputProtected(true);
            scrnMsg.xxLinkAncr_OS.setInputProtected(true);
            scrnMsg.xxLinkAncr_SC.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_SRC_WH_H, false);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, false);
           // QC#22517
            scrnMsg.xxLinkAncr_AD.setInputProtected(false);
            scrnMsg.xxLinkAncr_CT.setInputProtected(false);
        } else {
            scrnMsg.shipToCustCd_EO.setInputProtected(true);
            scrnMsg.shipToLocNm_EO.setInputProtected(true);
            scrnMsg.xxLinkAncr_OS.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, false);
           // QC#22517
            scrnMsg.xxLinkAncr_AD.setInputProtected(true);
            scrnMsg.xxLinkAncr_CT.setInputProtected(true);
        }
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.srcRtlSwhCd.setInputProtected(false);
            scrnMsg.rtlSwhNm_SS.setInputProtected(false);
            scrnMsg.xxLinkAncr_SS.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
            handler.setButtonEnabled(BTN_GET_SRC_SWH_H, true);
        } else {
            scrnMsg.srcRtlSwhCd.setInputProtected(true);
            scrnMsg.rtlSwhNm_SS.setInputProtected(true);
            scrnMsg.xxLinkAncr_SS.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_SRC_SWH_H, false);
            handler.setButtonEnabled(BTN_GET_SRC_SWH_H, false);
        }
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.destRtlWhCd.setInputProtected(false);
            scrnMsg.rtlWhNm_DW.setInputProtected(false);
            // QC#22608 Start
            scrnMsg.destRtlSwhCd.setInputProtected(true);
            scrnMsg.rtlSwhNm_DS.setInputProtected(true);
            scrnMsg.xxLinkAncr_DW.setInputProtected(false);
            scrnMsg.xxLinkAncr_DS.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
            handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
            // QC#22608 End
        } else {
            scrnMsg.destRtlWhCd.setInputProtected(true);
            scrnMsg.rtlWhNm_DW.setInputProtected(true);
            scrnMsg.destRtlSwhCd.setInputProtected(true);
            scrnMsg.rtlSwhNm_DS.setInputProtected(true);
            scrnMsg.xxLinkAncr_DW.setInputProtected(true);
            scrnMsg.xxLinkAncr_DS.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
            handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        }
        if (PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.vndCd.setInputProtected(false);
            // START 2019/01/15 T.Ogura [QC#29774,ADD]
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                scrnMsg.vndCd.setIndispensable(true);
            }
            // END   2019/01/15 T.Ogura [QC#29774,ADD]
            scrnMsg.dplyVndNm.setInputProtected(false);
            scrnMsg.xxLinkAncr_SL.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, true);
        } else {
            scrnMsg.vndCd.setInputProtected(true);
            scrnMsg.dplyVndNm.setInputProtected(true);
            scrnMsg.xxLinkAncr_SL.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, false);
        }
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        // START 2019/02/04 M.Naito [QC#30049,MOD]
//        scrnMsg.xxLinkAncr_CL.setInputProtected(true);
        scrnMsg.xxLinkAncr_CL.setInputProtected(false);
        // END 2019/02/04 M.Naito [QC#30049,MOD]
        scrnMsg.xxTotAmt.setInputProtected(true);

        if (PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {

            scrnMsg.shipToCustCd_EO.setInputProtected(false);
            scrnMsg.shipToLocNm_EO.setInputProtected(false);
            scrnMsg.xxLinkAncr_OS.setInputProtected(false);
            // QC#22517
            scrnMsg.xxLinkAncr_AD.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, true);
        }
        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.KITTING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            handler.setButtonEnabled(BTN_COPY, false);
        } else {
            handler.setButtonEnabled(BTN_COPY, true);
        }
        handler.setButtonEnabled(BTN_HEADER_CANCEL, true);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
        if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, true);
        } else {
            handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        }
        // Expense Order
        if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.svcConfigMstrPk.setInputProtected(false);
            handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, true);
        } else {
            scrnMsg.svcConfigMstrPk.setInputProtected(true);
            handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        }
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, true);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        // QC#22517
        setCtrAddrDisp(scrnMsg);

        // Additional Header
        // Vendor Return
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            scrnMsg.vndRtrnRsnCd_SL.setInputProtected(false);
        } else {
            scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_HEADER_CANCEL, true);
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
            if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            } else {
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            }
        } else {
            handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * after Submit
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispAfterSubmit(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(true);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(true);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(true);
        scrnMsg.carrNm.setInputProtected(true);
        scrnMsg.ctacPsnNm.setInputProtected(true);
        scrnMsg.prchReqCmntTxt.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_SW.setInputProtected(true);
        scrnMsg.srcRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_SS.setInputProtected(true);
        scrnMsg.destRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_DW.setInputProtected(true);
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        scrnMsg.shipToCustCd_EO.setInputProtected(true);
        scrnMsg.shipToLocNm_EO.setInputProtected(true);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(true);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(true);
        scrnMsg.xxLinkAncr_IL.setInputProtected(false);
        scrnMsg.xxLinkAncr_RL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SC.setInputProtected(true);
        scrnMsg.xxLinkAncr_SL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SS.setInputProtected(true);
        scrnMsg.xxLinkAncr_SW.setInputProtected(true);
        scrnMsg.xxLinkAncr_OS.setInputProtected(true);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);

        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.KITTING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            handler.setButtonEnabled(BTN_COPY, false);
        } else {
            handler.setButtonEnabled(BTN_COPY, true);
        }
        handler.setButtonEnabled(BTN_HEADER_CANCEL, true);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, true);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, false);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, false);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, false);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        if (PRCH_REQ_TP.KITTING.equals(scrnMsg.prchReqTpCd_SL.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqApvlFlg.getValue())) {
            handler.setButtonEnabled(BTN_KITTING, true);
        } else {
            handler.setButtonEnabled(BTN_KITTING, false);
        }
        handler.setButtonEnabled(BTN_IMPORT, true);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);

        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);

        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonEnabled(BTN_HEADER_CANCEL, true);
            handler.setButtonEnabled(BTN_LINE_CANCEL, true);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, true);
        } else {
            handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
            handler.setButtonEnabled(BTN_LINE_CANCEL, false);
            // QC#56867 Add
            handler.setButtonEnabled(BTN_LINE_CLOSE, false);
        }
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * after Close Or HeaderCancel
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     */
    private static void setCtrlScrnItemDispAfterCloseOrCancel(EZDCommonHandler handler, NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setInputProtected(false);
        scrnMsg.prchReqTpCd_SL.setInputProtected(true);
        scrnMsg.prchReqStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqApvlStsDescTxt.setInputProtected(true);
        scrnMsg.prchReqCratDt.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(true);
        scrnMsg.prchReqSrcTpDescTxt.setInputProtected(true);
        scrnMsg.trxRefNum.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setInputProtected(true);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_SL.setInputProtected(true);
        scrnMsg.carrNm.setInputProtected(true);
        scrnMsg.ctacPsnNm.setInputProtected(true);
        scrnMsg.prchReqCmntTxt.setInputProtected(true);
        scrnMsg.srcRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_SW.setInputProtected(true);
        scrnMsg.srcRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_SS.setInputProtected(true);
        scrnMsg.destRtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm_DW.setInputProtected(true);
        scrnMsg.destRtlSwhCd.setInputProtected(true);
        scrnMsg.rtlSwhNm_DS.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        scrnMsg.shipToCustCd_EO.setInputProtected(true);
        scrnMsg.shipToLocNm_EO.setInputProtected(true);

        // Header Link activation
        scrnMsg.xxLinkAncr_CL.setInputProtected(true);
        scrnMsg.xxLinkAncr_DS.setInputProtected(true);
        scrnMsg.xxLinkAncr_DW.setInputProtected(true);
        scrnMsg.xxLinkAncr_IL.setInputProtected(true);
        scrnMsg.xxLinkAncr_RL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SC.setInputProtected(true);
        scrnMsg.xxLinkAncr_SL.setInputProtected(true);
        scrnMsg.xxLinkAncr_SS.setInputProtected(true);
        scrnMsg.xxLinkAncr_SW.setInputProtected(true);
        scrnMsg.xxLinkAncr_OS.setInputProtected(true);
        // QC#22517
        scrnMsg.xxLinkAncr_AD.setInputProtected(true);
        scrnMsg.xxLinkAncr_CT.setInputProtected(true);

        // Header button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.KITTING.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            handler.setButtonEnabled(BTN_COPY, false);
        } else {
            handler.setButtonEnabled(BTN_COPY, true);
        }
        handler.setButtonEnabled(BTN_HEADER_CANCEL, false);
        handler.setButtonEnabled(BTN_HEADER_CLOSE, false);
        handler.setButtonEnabled(BTN_APPRVL_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        // QC#17843
        handler.setButtonEnabled(BTN_GET_SRC_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SRC_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_SWH_H, false);
        handler.setButtonEnabled(BTN_GET_DEST_WH_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_SPLY_H, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_H, false);

        // Detail
        // Detail Header
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG, false);
        scrnMsg.svcConfigMstrPk.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG, false);
        scrnMsg.xxNum.setInputProtected(true);

        // Detail Table
        setCtrlScrnItemDispDetailTable(handler, scrnMsg, false);

        // Detail Footer Control
        handler.setButtonEnabled(BTN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL, false);
        // QC#56867 Add
        handler.setButtonEnabled(BTN_LINE_CLOSE, false);
        handler.setButtonEnabled(BTN_ON_HAND_INV, true);
        handler.setButtonEnabled(BTN_KITTING, false);
        handler.setButtonEnabled(BTN_IMPORT, false);

        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Additional Header
        scrnMsg.shipToLocNm.setInputProtected(true);
        scrnMsg.shipToAddlLocNm.setInputProtected(true);
        scrnMsg.xxShipVndAddr.setInputProtected(true);
        scrnMsg.shipToPostCd.setInputProtected(true);
        scrnMsg.shipToCtyAddr.setInputProtected(true);
        scrnMsg.shipToCntyNm.setInputProtected(true);
        scrnMsg.shipToStCd.setInputProtected(true);
        scrnMsg.shipToProvNm.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);
        // QC#22517
        scrnMsg.shipToCtryCd.setInputProtected(true);

        // Additional Header
        // Vendor Return
        scrnMsg.vndRtrnRsnCd_SL.setInputProtected(true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * for Detail Table
     * @param handler EZDCommonHandler
     * @param scrnMsg NPBL0020BMsg
     * @param addLineFlg boolean
     */
    public static void setCtrlScrnItemDispDetailTable(EZDCommonHandler handler, NPBL0020BMsg scrnMsg, boolean addLineFlg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        if (!TAB_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);   
        }

        // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        boolean isNeededAccount = NPBL0020CommonLogic.isNeededAccount(scrnMsg.chrgAcctEdtblFlg.getValue());
        // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
//            boolean isReqTypeVendorReturn=PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue());
            // END 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
            boolean isConfig=ZYPCommonFunc.hasValue(scrnMsg.A.no(i).configTpCd_A1);
            boolean isConfigHeader=(isConfig&&BigDecimal.ZERO.equals(scrnMsg.A.no(i).prchReqLineSubNum_A1.getValue()));
            
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.prchReqSavedFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.openStsFlg.getValue())) {
                // Submit
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_ITEM, i, false);
                handler.setButtonEnabled(BTN_ITEM_INFO, i, false);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SRC_WH, i, false);
                scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_DEST_WH, i, false);
                scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, false);
                scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
                // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                if (!isNeededAccount || isConfigHeader) {
                    handler.setButtonEnabled(BTN_ACCOUNT, i, false);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT, i, true);
                }
                // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

                scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
                // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
                // END 2018/05/25 S.Katsuma [QC#25893,ADD]
                // QC#17843
                handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                // START 2018/06/11 S.Katsuma [QC#26193,ADD]
                handler.setButtonEnabled(BTN_SERIAL, i, false);
                // END 2018/06/11 S.Katsuma [QC#26193,ADD]

            } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.prchReqSavedFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.openStsFlg.getValue())) {
                // Close/HeaderCancel
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_ITEM, i, false);
                handler.setButtonEnabled(BTN_ITEM_INFO, i, false);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SRC_WH, i, false);
                scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_DEST_WH, i, false);
                scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, false);
                scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
                // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                if (!isNeededAccount || isConfigHeader) {
                    handler.setButtonEnabled(BTN_ACCOUNT, i, false);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT, i, true);
                }
                // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

                scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
                // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
                // END 2018/05/25 S.Katsuma [QC#25893,ADD]
                // QC#17843
                handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                // START 2018/06/11 S.Katsuma [QC#26193,ADD]
                handler.setButtonEnabled(BTN_SERIAL, i, false);
                // END 2018/06/11 S.Katsuma [QC#26193,ADD]
            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqRelDtTmTs_A1) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).openStsFlg_A2.getValue())) {
                // LineClose/LineCance
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_ITEM, i, false);
                handler.setButtonEnabled(BTN_ITEM_INFO, i, false);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SRC_WH, i, false);
                scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_DEST_WH, i, false);
                scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, false);
                scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
                // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                if (!isNeededAccount || isConfigHeader) {
                    handler.setButtonEnabled(BTN_ACCOUNT, i, false);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT, i, true);
                }
                // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

                scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
                // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
                // END 2018/05/25 S.Katsuma [QC#25893,ADD]
                // QC#17843
                handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                // START 2018/06/11 S.Katsuma [QC#26193,ADD]
                handler.setButtonEnabled(BTN_SERIAL, i, false);
                // END 2018/06/11 S.Katsuma [QC#26193,ADD]
            } else {
                if (!isConfig) {
                    // Not Config
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prchReqLineTpCd_A1.setIndispensable(true);
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).mdseCd_A1.setIndispensable(true);
                    handler.setButtonEnabled(BTN_ITEM, i, true);
                    handler.setButtonEnabled(BTN_ITEM_INFO, i, true);
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prchReqDispQty_A1.setIndispensable(true);
                    scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(false);
                        handler.setButtonEnabled(BTN_SRC_WH, i, true);
                        scrnMsg.A.no(i).rtlWhNm_A1.setIndispensable(true);
                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setIndispensable(true);
                        // QC#17843
                        handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, true);
                    } else {
                        scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                        handler.setButtonEnabled(BTN_SRC_WH, i, false);
                        scrnMsg.A.no(i).rtlWhNm_A1.setIndispensable(false);
                        handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                    }
                    // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                    // QC#21682 Start
                    if (PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(false);
                        handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, true);
                        scrnMsg.A.no(i).shipToLocNm_E1.setIndispensable(true);
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(true);
                    } else {
                        scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
                        handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, false);
                        scrnMsg.A.no(i).shipToLocNm_E1.setIndispensable(false);
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(false);
                    }
                    // QC#21682 End
//                    if (PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(true);
//                    }
//                    if (isReqTypeVendorReturn) {
//                        // QC#22511 Modify.
//                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setIndispensable(true);
//                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
//                        handler.setButtonEnabled(BTN_ACCOUNT, i, false);
//                    } else {
//                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setIndispensable(true);
//                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
//                        handler.setButtonEnabled(BTN_ACCOUNT, i, true);
//                    }
//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_A1.getValue())) {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(false);
//                    } else {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
//                    }

                    scrnMsg.A.no(i).srcRtlSwhCd_A1.setIndispensable(true);
                    scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(isNeededAccount);
                    scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(!isNeededAccount);
                    handler.setButtonEnabled(BTN_ACCOUNT, i, isNeededAccount);
                    // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(false);
                        handler.setButtonEnabled(BTN_DEST_WH, i, true);
                        handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, true);
                        scrnMsg.A.no(i).rtlWhNm_A2.setIndispensable(true);
                    } else {
                        scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                        handler.setButtonEnabled(BTN_DEST_WH, i, false);
                        handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                    }
                    scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                    if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setIndispensable(true);
                    }
                    // QC#8003
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        if (RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                        // QC#24994 MOD START
                        //} else if (RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A1.getValue()) || RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                        //    scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
                        //    handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, true);
                        } else {
                            //scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                            //handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
                            scrnMsg.A.no(i).destRtlSwhCd_A1.setIndispensable(true);
                            handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, true);
                        // QC#24994 MOD END
                        }
                    } else {
                        scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                    }
                    scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);

                    scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).fromStkStsCd_A1.setIndispensable(true);
                    // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                    if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
                    } else {
                        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
                    }
                    // END 2018/05/25 S.Katsuma [QC#25893,ADD]
                    // START 2018/06/11 S.Katsuma [QC#26193,ADD]
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shpgSerTakeFlg_A1.getValue())) {
                        handler.setButtonEnabled(BTN_SERIAL, i, true);
                        scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(false);
                    } else {
                        handler.setButtonEnabled(BTN_SERIAL, i, false);
                        scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                    }
                    // END 2018/06/11 S.Katsuma [QC#26193,ADD]

                } else if (isConfigHeader) {
                    // Config
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prchReqLineTpCd_A1.setIndispensable(true);
                    scrnMsg.A.no(i).mdseCd_A1.clear();
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_ITEM, i, false);
                    handler.setButtonEnabled(BTN_ITEM_INFO, i, false);
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqDispQty_A1.clear();
                    scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        // V0.5
                        if (CONFIG_TP.EXISTING.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                            scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(true);
                            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                            handler.setButtonEnabled(BTN_SRC_WH, i, false);
                            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                            scrnMsg.A.no(i).rtlWhNm_A1.setIndispensable(false);
                            // QC#17843
                            handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                        } else {
                            handler.setButtonEnabled(BTN_SRC_WH, i, true);
                            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                            scrnMsg.A.no(i).rtlWhNm_A1.setIndispensable(true);
                            // QC#17843
                            handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                        }
                    } else {
                        scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                        handler.setButtonEnabled(BTN_SRC_WH, i, false);
                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                        scrnMsg.A.no(i).rtlWhNm_A1.setIndispensable(false);
                        // QC#17843
                        handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                    }
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(false);
                        handler.setButtonEnabled(BTN_DEST_WH, i, true);
                        scrnMsg.A.no(i).rtlWhNm_A2.setIndispensable(true);
                    } else {
                        scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                        handler.setButtonEnabled(BTN_DEST_WH, i, false);
                        scrnMsg.A.no(i).rtlWhNm_A2.setIndispensable(false);
                    }
                    // QC#21682 Start
                    if (PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.REFURBISHING.equals(scrnMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(false);
                        handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, true);
                        scrnMsg.A.no(i).shipToLocNm_E1.setIndispensable(true);
                    } else {
                        scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
                        handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, false);
                        scrnMsg.A.no(i).shipToLocNm_E1.setIndispensable(false);
                    }
                    // QC#21682 End
                    scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                    scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);

                    scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).fromStkStsCd_A1.setIndispensable(true);

                    scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(false);
                    scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_ACCOUNT, i, false);
                    handler.setButtonEnabled(BTN_SERIAL, i, false);
                    // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                    scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
                    // END 2018/05/25 S.Katsuma [QC#25893,ADD]

                } else { // Config Item
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).configTpDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(true);
                    if (CONFIG_TP.EXISTING.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                        scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
                        scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(true);
                    } else {
                        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
                        scrnMsg.A.no(i).mdseCd_A1.setIndispensable(true);
                        scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(false);
                        scrnMsg.A.no(i).prchReqDispQty_A1.setIndispensable(true);
                        scrnMsg.A.no(i).fromStkStsCd_A1.setInputProtected(false);
                        scrnMsg.A.no(i).fromStkStsCd_A1.setIndispensable(true);
                    }
                    if (CONFIG_TP.EXISTING.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                        handler.setButtonEnabled(BTN_ITEM, i, false);
                        handler.setButtonEnabled(BTN_ITEM_INFO, i, false);
                    } else {
                        handler.setButtonEnabled(BTN_ITEM, i, true);
                        handler.setButtonEnabled(BTN_ITEM_INFO, i, true);
                    }
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
                    scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);

                    scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                    // START 2022/12/16 T.Kuroda [QC#60562,MOD]
                    //handler.setButtonEnabled(BTN_SRC_WH, i, false);
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue())
                        || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        handler.setButtonEnabled(BTN_SRC_WH, i, true);
                    } else {
                        handler.setButtonEnabled(BTN_SRC_WH, i, false);
                    }
                    // END 2022/12/16 T.Kuroda [QC#60562,MOD]

                    if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        if (CONFIG_TP.EXISTING.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                            // QC#17843
                            handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                        } else {
                            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
                            scrnMsg.A.no(i).srcRtlSwhCd_A1.setIndispensable(true);
                            // QC#17843
                            handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, true);
                        }
                    } else {
                        scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                        // QC#17843
                        handler.setButtonEnabled(BTN_OPEN_WIN_SRC_SWH, i, false);
                    }
                    scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                    handler.setButtonEnabled(BTN_DEST_WH, i, false);
                    // QC#17843
                    handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);

                    // QC#8003
                    if ((PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue())) && CONFIG_TP.NEW.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                        if (RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                        // QC#24994 MOD START
                        //} else if (RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A1.getValue()) || RTL_WH_CATG.SHOWROOM.equals(scrnMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                        //    scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
                        //    // QC#17843
                        //    handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, true);
                        } else {
                        //    scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                        //    // QC#17843
                        //    handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, false);
                            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
                            scrnMsg.A.no(i).destRtlSwhCd_A1.setIndispensable(true);
                            handler.setButtonEnabled(BTN_OPEN_WIN_DEST_SWH, i, true);
                        // QC#24994 MOD END
                        }
                    } else {
                        scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
                    }
                    scrnMsg.A.no(i).shipToLocNm_E1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_SHIP_TO_CUST, i, false);
                    scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchReqLineStsDescTxt_A1.setInputProtected(true);

                    // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_A1.getValue())) {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(false);
//                    } else {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(true);
//                    }
                    scrnMsg.A.no(i).xxScrItem50Txt_A1.setInputProtected(!isNeededAccount);
                    scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
//                    if (isReqTypeVendorReturn) {
//                        handler.setButtonEnabled(BTN_ACCOUNT, i, false);
//                    } else {
//                        handler.setButtonEnabled(BTN_ACCOUNT, i, true);
//                    }
                    handler.setButtonEnabled(BTN_ACCOUNT, i, isNeededAccount);
//                    if (PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(true);
//                    } else {
//                        scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(false);
//                    }
                    scrnMsg.A.no(i).xxScrItem50Txt_A1.setIndispensable(isNeededAccount);
                    // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                    // START 2018/05/25 S.Katsuma [QC#25893,ADD]
                    if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
                    } else {
                        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
                    }
                    // END 2018/05/25 S.Katsuma [QC#25893,ADD]
                    // START 2018/06/11 S.Katsuma [QC#26193,ADD]
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shpgSerTakeFlg_A1.getValue())) {
                        // QC#30618
                        if (CONFIG_TP.EXISTING.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                            handler.setButtonEnabled(BTN_SERIAL, i, false);
                            scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                        } else {
                            handler.setButtonEnabled(BTN_SERIAL, i, true);
                            scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(false);
                        }
                    } else {
                        handler.setButtonEnabled(BTN_SERIAL, i, false);
                        scrnMsg.A.no(i).xxLogDtlTxt_A1.setInputProtected(true);
                    }
                    // END 2018/06/11 S.Katsuma [QC#26193,ADD]

                }
            }
            // QC#55313
            if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
                scrnMsg.A.no(i).prchReqDispQty_A1.setInputProtected(true);
            }
        }
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setNameForMessage(NPBL0020BMsg scrnMsg) {

        // Header
        scrnMsg.prchReqNum.setNameForMessage(DISPLAY_NM_PRCH_REQ_NUM);
        scrnMsg.prchReqTpCd_SL.setNameForMessage(DISPLAY_NM_PRCH_REQ_TP_DESC_TXT);
        scrnMsg.prchReqStsDescTxt.setNameForMessage(DISPLAY_NM_PRCH_REQ_STS_DESC_TXT);
        scrnMsg.prchReqApvlStsDescTxt.setNameForMessage(DISPLAY_NM_PRCH_REQ_APVL_STS_DESC_TXT);
        scrnMsg.prchReqCratDt.setNameForMessage(DISPLAY_NM_PRCH_REQ_CRAT_DT);
        scrnMsg.rqstRcvDt.setNameForMessage(DISPLAY_NM_RQST_RCV_DT);
        scrnMsg.prchReqSrcTpDescTxt.setNameForMessage(DISPLAY_NM_PRCH_REQ_SRC_TP_DESC_TXT);
        scrnMsg.trxRefNum.setNameForMessage(DISPLAY_NM_TRX_REF_NUM);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.mrpPlnNm.setNameForMessage(DISPLAY_NM_MRP_PLN_NM);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.fullPsnNm.setNameForMessage(DISPLAY_NM_FULL_PSN_NM);
        scrnMsg.shpgSvcLvlCd_SL.setNameForMessage(DISPLAY_NM_SHPG_SVC_LVL_DESC_TXT);
        scrnMsg.carrNm.setNameForMessage(DISPLAY_NM_CARR_NM);
        scrnMsg.ctacPsnNm.setNameForMessage(DISPLAY_NM_CTAC_PSN_NM);
        scrnMsg.prchReqCmntTxt.setNameForMessage(DISPLAY_NM_PRCH_REQ_CMNT_TXT);
        scrnMsg.srcRtlWhCd.setNameForMessage(DISPLAY_NM_SRC_RTL_WH_CD);
        scrnMsg.rtlWhNm_SW.setNameForMessage(DISPLAY_NM_RTL_WH_NM_SW);
        scrnMsg.srcRtlSwhCd.setNameForMessage(DISPLAY_NM_SRC_RTL_SWH_CD);
        scrnMsg.rtlSwhNm_SS.setNameForMessage(DISPLAY_NM_RTL_SWH_NM_SS);
        scrnMsg.destRtlWhCd.setNameForMessage(DISPLAY_NM_DEST_RTL_WH_CD);
        scrnMsg.rtlWhNm_DW.setNameForMessage(DISPLAY_NM_RTL_WH_NM_DW);
        scrnMsg.destRtlSwhCd.setNameForMessage(DISPLAY_NM_DEST_RTL_SWH_CD);
        scrnMsg.rtlSwhNm_DS.setNameForMessage(DISPLAY_NM_RTL_SWH_NM_DS);
        scrnMsg.vndCd.setNameForMessage(DISPLAY_NM_VND_CD);
        scrnMsg.dplyVndNm.setNameForMessage(DISPLAY_NM_DPLY_VND_NM);
        scrnMsg.shipToCustCd_EO.setNameForMessage(DISPLAY_NM_SHIP_TO_CUST_CD);
        scrnMsg.shipToLocNm_EO.setNameForMessage(DISPLAY_NM_SHIP_TO_LOC_NM);

        // Detail Header Control
        scrnMsg.svcConfigMstrPk.setNameForMessage(DISPLAY_NM_SVC_CONFIG_MSTR_PK);

        // Detail Table
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prchReqLineTpCd_A1.setNameForMessage(DISPLAY_NM_PRCH_REQ_LINE_TP_DESC_TXT);
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage(DISPLAY_NM_MDSE_CD);
            scrnMsg.A.no(i).prchReqDispQty_A1.setNameForMessage(DISPLAY_NM_PRCH_REQ_DISP_QTY);
            scrnMsg.A.no(i).rtlWhNm_A1.setNameForMessage(DISPLAY_NM_RTL_WH_NM_LINE_SW);
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setNameForMessage(DISPLAY_NM_SRC_RTL_SWH_CD_LINE);
            scrnMsg.A.no(i).rtlWhNm_A2.setNameForMessage(DISPLAY_NM_RTL_WH_NM_LINE_DW);
            scrnMsg.A.no(i).destRtlSwhCd_A1.setNameForMessage(DISPLAY_NM_DEST_RTL_SWH_CD_LINE);
            scrnMsg.A.no(i).shipToCustCd_E1.setNameForMessage(DISPLAY_NM_SHIP_TO_CUST_CD);
        }

        // Additional Header
        scrnMsg.vndRtrnRsnCd_SL.setNameForMessage(DISPLAY_NM_VND_RTRN_RSN_DESC_TXT);

    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setParamForRequesterPopup(NPBL0020BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Requester Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("'" + glblCmpyCd + "'" + "AS GLBL_CMPY_CD");
        sb.append("    ,V.EZCANCELFLAG AS EZCANCELFLAG");
        sb.append("    ,V.USR_NM AS PSN_CD");
        sb.append("    ,V.FIRST_NM || (NVL2(V.MID_NM, ' '||V.MID_NM||' ', ' ')) || V.LAST_NM FULL_PSN_NM ");
        sb.append(" FROM");
        sb.append("    AUTH_PSN V");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "User Id";
        whereArray1[IDX_1] = "PSN_CD";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "User Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = scrnMsg.fullPsnNm.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "User Id";
        columnArray1[IDX_1] = "PSN_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "User Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PSN_CD";
        sortConditionArray1[IDX_1] = "ASC";

        sortList.add(sortConditionArray1);

        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[IDX_6] = scrnMsg.R;
        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setInitParamForCarrierPopup(NPBL0020BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_OTBD_CARR_V);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NPBL0020BMsg scrnMsg) {

        scrnMsg.carrCd.clear();

        Object[] params = new Object[IDX_11];
        params[IDX_0] = scrnMsg.xxTblNm_P1;
        params[IDX_1] = scrnMsg.xxTblCdColNm_P1;
        params[IDX_2] = scrnMsg.xxTblNmColNm_P1;
        params[IDX_3] = scrnMsg.xxTblSortColNm_P1;
        params[IDX_4] = scrnMsg.xxScrNm_P1;
        params[IDX_5] = scrnMsg.xxHdrCdLbNm_P1;
        params[IDX_6] = scrnMsg.xxHdrNmLbNm_P1;
        params[IDX_7] = scrnMsg.xxDtlCdLbNm_P1;
        params[IDX_8] = scrnMsg.xxDtlNmLbNm_P1;
        params[IDX_9] = scrnMsg.carrCd;
        params[IDX_10] = scrnMsg.carrNm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setInitParamForSrcWarehousePopup(NPBL0020BMsg scrnMsg) {

        scrnMsg.P.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setParamForSrcWarehousePopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        Object[] params = new Object[IDX_12];
        params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[IDX_2] = scrnMsg.xxLocRoleTpCdListTxt;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;

        if (eventRowIndex > -1) {

            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            params[IDX_6] = scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1;
//            params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
            params[IDX_7] = scrnMsg.A.no(eventRowIndex).rtlWhNm_A1;
            params[IDX_8] = scrnMsg.A.no(eventRowIndex).srcRtlSwhCd_A1;
//            params[IDX_9] = scrnMsg.A.no(eventRowIndex).rtlSwhNm_A1;
            params[IDX_9] = scrnMsg.P.no(IDX_9).xxPopPrm;
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

        } else {

            params[IDX_6] = scrnMsg.srcRtlWhCd;
            params[IDX_7] = scrnMsg.rtlWhNm_SW;
            params[IDX_8] = scrnMsg.srcRtlSwhCd;
            params[IDX_9] = scrnMsg.rtlSwhNm_SS;

        }

        params[IDX_10] = scrnMsg.P.no(IDX_10).xxPopPrm;
        params[IDX_11] = scrnMsg.P.no(IDX_11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     */
    public static void setInitParamForSrcWarehouseForLinePopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        scrnMsg.P.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setParamForSrcWarehouseForLinePopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        Object[] params = new Object[IDX_12];
        params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[IDX_2] = scrnMsg.xxLocRoleTpCdListTxt;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;
        // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        params[IDX_6] = scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1;
//        params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
        params[IDX_7] = scrnMsg.A.no(eventRowIndex).rtlWhNm_A1;
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).configTpCd_A1) || scrnMsg.A.no(eventRowIndex).prchReqLineSubNum_A1.getValueInt() > 0) {
            params[IDX_8] = scrnMsg.A.no(eventRowIndex).srcRtlSwhCd_A1;
//            params[IDX_9] = scrnMsg.A.no(eventRowIndex).rtlSwhNm_A1;
        } else {
            params[IDX_8] = scrnMsg.P.no(IDX_6).xxPopPrm;
//            params[IDX_9] = scrnMsg.P.no(IDX_7).xxPopPrm;
        }
        params[IDX_9] = scrnMsg.P.no(IDX_7).xxPopPrm;
        // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        params[IDX_10] = scrnMsg.P.no(IDX_10).xxPopPrm;
        params[IDX_11] = scrnMsg.P.no(IDX_11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setInitParamForDestWarehousePopup(NPBL0020BMsg scrnMsg) {

        scrnMsg.P.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        //QC#17362
        String selectedPrchReqTp = null;
        if(scrnMsg.prchReqTpCd_SL != null) {
            selectedPrchReqTp = scrnMsg.prchReqTpCd_SL.getValue();
        }
        if (ZYPCommonFunc.hasValue(selectedPrchReqTp) && PRCH_REQ_TP.SUBCONTRACT.equals(selectedPrchReqTp)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_4).xxPopPrm, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setParamForDestWarehousePopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        Object[] params = new Object[IDX_12];
        params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[IDX_2] = scrnMsg.xxLocRoleTpCdListTxt;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;

        if (eventRowIndex > -1) {

            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            params[IDX_6] = scrnMsg.A.no(eventRowIndex).destRtlWhCd_A1;
//            params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
            params[IDX_7] = scrnMsg.A.no(eventRowIndex).rtlWhNm_A2;
            params[IDX_8] = scrnMsg.A.no(eventRowIndex).destRtlSwhCd_A1;
//            params[IDX_9] = scrnMsg.A.no(eventRowIndex).rtlSwhNm_A2;
            params[IDX_9] = scrnMsg.P.no(IDX_9).xxPopPrm;
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

        } else {

            params[IDX_6] = scrnMsg.destRtlWhCd;
            params[IDX_7] = scrnMsg.rtlWhNm_DW;
            params[IDX_8] = scrnMsg.destRtlSwhCd;
            params[IDX_9] = scrnMsg.rtlSwhNm_DS;

        }

        params[IDX_10] = scrnMsg.P.no(IDX_10).xxPopPrm;
        params[IDX_11] = scrnMsg.P.no(IDX_11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     */
    public static void setInitParamForDestWarehouseForLinePopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        scrnMsg.P.clear();

        // Requester Name is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setParamForDestWarehouseForLinePopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        Object[] params = new Object[IDX_12];
        params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[IDX_2] = scrnMsg.xxLocRoleTpCdListTxt;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;
        // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        params[IDX_6] = scrnMsg.A.no(eventRowIndex).destRtlWhCd_A1;
//        params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
        params[IDX_7] = scrnMsg.A.no(eventRowIndex).rtlWhNm_A2;
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).configTpCd_A1) || scrnMsg.A.no(eventRowIndex).prchReqLineSubNum_A1.getValueInt() > 0) {
            params[IDX_8] = scrnMsg.A.no(eventRowIndex).destRtlSwhCd_A1;
//            params[IDX_9] = scrnMsg.A.no(eventRowIndex).rtlSwhNm_A2;
        } else {
            params[IDX_8] = scrnMsg.P.no(IDX_6).xxPopPrm;
//            params[IDX_9] = scrnMsg.P.no(IDX_7).xxPopPrm;
        }
        params[IDX_9] = scrnMsg.P.no(IDX_7).xxPopPrm;
        // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        params[IDX_10] = scrnMsg.P.no(IDX_10).xxPopPrm;
        params[IDX_11] = scrnMsg.P.no(IDX_11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     */
    public static void setInitParamForConfigPopup(NPBL0020BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.I);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_0).xxPopPrm, PARM_CONFIG_EXST_MODE_CD_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(IDX_0).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(IDX_1).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.REMOVED);
        scrnMsg.I.setValidCount(IDX_2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_10).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_11).xxPopPrm, PARM_MACH_ALLOC_MODE_CODE_03);
        if (ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.srcRtlWhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_14).xxPopPrm, scrnMsg.srcRtlWhCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_15).xxPopPrm, scrnMsg.srcRtlSwhCd);
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @return Object[]
     */
    public static Object[] setParamForConfigPopup(NPBL0020BMsg scrnMsg) {

        Object[] params = new Object[IDX_32];
        params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[IDX_1] = scrnMsg.svcConfigMstrPk;
        params[IDX_2] = scrnMsg.P.no(IDX_2).xxPopPrm;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;
        params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
        params[IDX_7] = scrnMsg.P.no(IDX_7).xxPopPrm;
        params[IDX_8] = scrnMsg.P.no(IDX_8).xxPopPrm;
        params[IDX_9] = scrnMsg.I;
        params[IDX_10] = scrnMsg.P.no(IDX_10).xxPopPrm;
        params[IDX_11] = scrnMsg.P.no(IDX_11).xxPopPrm;
        params[IDX_12] = scrnMsg.P.no(IDX_12).xxPopPrm;
        params[IDX_13] = scrnMsg.P.no(IDX_13).xxPopPrm;
        params[IDX_14] = scrnMsg.P.no(IDX_14).xxPopPrm;
        params[IDX_15] = scrnMsg.P.no(IDX_15).xxPopPrm;
        params[IDX_16] = scrnMsg.P.no(IDX_16).xxPopPrm;
        params[IDX_17] = scrnMsg.P.no(IDX_17).xxPopPrm;
        params[IDX_18] = scrnMsg.P.no(IDX_18).xxPopPrm;
        params[IDX_19] = scrnMsg.P.no(IDX_19).xxPopPrm;
        params[IDX_20] = scrnMsg.P.no(IDX_20).xxPopPrm;
        params[IDX_21] = scrnMsg.P.no(IDX_21).xxPopPrm;
        params[IDX_22] = scrnMsg.P.no(IDX_22).xxPopPrm;
        params[IDX_23] = scrnMsg.P.no(IDX_23).xxPopPrm;
        params[IDX_24] = scrnMsg.P.no(IDX_24).xxPopPrm;
        params[IDX_25] = scrnMsg.P.no(IDX_25).xxPopPrm;
        params[IDX_26] = scrnMsg.P.no(IDX_26).xxPopPrm;
        params[IDX_27] = scrnMsg.P.no(IDX_27).xxPopPrm;
        params[IDX_28] = scrnMsg.P.no(IDX_28).xxPopPrm;
        params[IDX_29] = scrnMsg.svcConfigMstrPk;
        params[IDX_30] = scrnMsg.P.no(IDX_30).xxPopPrm;
        params[IDX_31] = scrnMsg.P.no(IDX_31).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     */
    public static void setInitParamForSerialNumEntryPopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.T);

        String rtlWhCd = "";
        String rtlWhNm = "";
        String rtlSwhCd = "";

        // Source WH
        if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            rtlWhCd = scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1.getValue();
            rtlWhNm = scrnMsg.A.no(eventRowIndex).rtlWhNm_A1.getValue();

        } else {
            rtlWhCd = scrnMsg.srcRtlWhCd.getValue();
            rtlWhNm = scrnMsg.rtlWhNm_SW.getValue();
        }

        // Source SWH
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            rtlSwhCd = scrnMsg.srcRtlSwhCd.getValue();
        } else {
            rtlSwhCd = scrnMsg.A.no(eventRowIndex).srcRtlSwhCd_A1.getValue();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqSavedFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_5).xxPopPrm, SCR_EDT_TP_CD_ENTRY);
        } else if (CONFIG_TP.EXISTING.equals(scrnMsg.A.no(eventRowIndex).configTpCd_A1.getValue()) && scrnMsg.A.no(eventRowIndex).prchReqLineSubNum_A1.getValueInt() != 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_5).xxPopPrm, SCR_EDT_TP_CD_READ);
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqSavedFlg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_5).xxPopPrm, SCR_EDT_TP_CD_ENTRY);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_5).xxPopPrm, SCR_EDT_TP_CD_READ);
        }

        int i = 0;
        int prchReqDispQty = scrnMsg.A.no(eventRowIndex).prchReqDispQty_A1.getValueInt();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1)) {
            String[] serialNumber = scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
            for (; i < serialNumber.length; i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxHdrNum_T1, scrnMsg.prchReqNum_HD);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxDplyTrxNumTxt_T1, scrnMsg.A.no(eventRowIndex).xxScrItem20Txt_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).mdseCd_T1, scrnMsg.A.no(eventRowIndex).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).serNum_T1, serialNumber[i]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxEdtModeFlg_T1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).rtlSwhCd_T1, rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).invtyLocCd_T1, ZYPCommonFunc.concatString(rtlWhCd, "", rtlSwhCd));
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).svcConfigMstrPk_T1, scrnMsg.A.no(eventRowIndex).svcConfigMstrPk_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).serNumTakeFlg_T1, ZYPConstant.FLG_OFF_N);
                if (i == prchReqDispQty - 1) {
                    break;
                }
            }
            scrnMsg.T.setValidCount(i);
        }

        for (int c = i; c < scrnMsg.A.no(eventRowIndex).prchReqDispQty_A1.getValueInt(); c++, i++) {
            if (c > scrnMsg.T.length() - 1) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).xxHdrNum_T1, scrnMsg.prchReqNum_HD);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).xxDplyTrxNumTxt_T1, scrnMsg.A.no(eventRowIndex).xxScrItem20Txt_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).mdseCd_T1, scrnMsg.A.no(eventRowIndex).mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).xxEdtModeFlg_T1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).rtlSwhCd_T1, scrnMsg.A.no(eventRowIndex).srcRtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).invtyLocCd_T1, ZYPCommonFunc.concatString(rtlWhCd, "", rtlSwhCd));
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).svcConfigMstrPk_T1, scrnMsg.A.no(eventRowIndex).svcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(c).serNumTakeFlg_T1, ZYPConstant.FLG_OFF_N);
        }
        scrnMsg.T.setValidCount(i);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_9).xxPopPrm, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_10).xxPopPrm, rtlWhNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_11).xxPopPrm, INBD_OTBD.OUTBOUND);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setParamForSerialNumEntryPopup(NPBL0020BMsg scrnMsg, int eventRowIndex) {

        Object[] params = new Object[IDX_12];

        params[IDX_0] = PRAM_NLBL3000_SUFFIX;
        params[IDX_1] = scrnMsg.prchReqNum_HD;
        params[IDX_2] = scrnMsg.P.no(IDX_2).xxPopPrm;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.A.no(eventRowIndex).prchReqDispQty_A1;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm.getValue();
        params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
        params[IDX_7] = scrnMsg.P.no(IDX_7).xxPopPrm;
        params[IDX_8] = scrnMsg.T;
        params[IDX_9] = scrnMsg.P.no(IDX_9).xxPopPrm;
        params[IDX_10] = scrnMsg.P.no(IDX_10).xxPopPrm;
        params[IDX_11] = scrnMsg.P.no(IDX_11).xxPopPrm.getValue();

        return params;
    }

    /**
     * setSupplierSearchParam
     * @param scrnMsg NPBL0020BMsg
     * @return Ship From / To Location Parameter
     */
    public static Object[] setSupplierSearchParam(NPBL0020BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.R.clear();

        // Paramter Set
        Object[] param = new Object[IDX_7];
        param[IDX_0] = "";
        param[IDX_1] = WINDOW_TITLE_SUPPLIER_SEARCH;
        param[IDX_2] = SELECT_TABLE_NAME_FOR_SUPPLIER_SEARCH;
        param[IDX_3] = getSearchConditionForSupplier(scrnMsg);
        param[IDX_4] = getDisplayColumnForSupplier(scrnMsg);
        param[IDX_5] = getSortForSupplier(scrnMsg);
        param[IDX_6] = scrnMsg.R;

        return param;

    }

    /**
     * getSearchConditionForSupplier
     * @param scrnMsg NPBL0020BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForSupplier(NPBL0020BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE, WHERE_SQL_NM_FOR_SUPPLIER_SITE_CODE, scrnMsg.vndCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME, WHERE_SQL_NM_FOR_SUPPLIER_SITE_NAME, scrnMsg.dplyVndNm.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForSupplier
     * @param scrnMsg NPBL0020BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForSupplier(NPBL0020BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_SUPPLIER_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_CODE, COLUMN_WIDTH_FOR_SUPPLIER_CODE, ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE, ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);

        return colList;
    }

    /**
     * getSortForSupplier
     * @param scrnMsg NPBL0020BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForSupplier(NPBL0020BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_SUPPLIER_CODE, SORT_CONDITION_FOR_SUPPLIER_CODE };
        Object[] sortObj2 = {SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE, SORT_CONDITION_FOR_SUPPLIER_SITE_CODE };

        sortList.add(sortObj1);
        sortList.add(sortObj2);

        return sortList;
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_UPDATE);
    }

    /**
     * Function List
     * @return List<String> Function List
     */
    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList;
    }

    /**
     * <pre>
     * Check ErrorItem
     * </pre>
     * @param List EZDBItem
     */
    private static boolean checkErrorItem(EZDBItem... inParamEzdbItems) {
        boolean isError = false;
        for (EZDBItem item : inParamEzdbItems) {
            if (item.isError()) {
                isError = true;
                break;
            }
        }
        return isError;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPBL0020BMsg
     * @param index int
     * @return ShipToCustPopup Param (NMAL6760) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NPBL0020BMsg scrnMsg, int index) {

        ZYPTableUtil.clear(scrnMsg.P);

        int paramCount = 0;
        Object[] params = new Object[IDX_26];
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        // START 2019/09/26 T.Ogura [QC#52362,MOD]
//        if (index > -1) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, scrnMsg.A.no(index).shipToLocNm_E1);
//        } else {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, scrnMsg.shipToLocNm_EO);
//        }
        String shipToCustLocNm = "";
        if (index > -1) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).shipToLocNm_E1)) {
                shipToCustLocNm = scrnMsg.A.no(index).shipToLocNm_E1.getValue();
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm_EO)) {
                shipToCustLocNm = scrnMsg.shipToLocNm_EO.getValue();
            }
        }
        if (shipToCustLocNm.length() == 60) {
            shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, shipToCustLocNm);
        // END   2019/09/26 T.Ogura [QC#52362,MOD]
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, SHIP_TO_ONLY_CD);
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        if (index > -1) {
            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, scrnMsg.A.no(index).shipToCustCd_E1);
            scrnMsg.P.no(paramCount).xxPopPrm.clear();
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, scrnMsg.shipToCustCd_EO);
        }
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPBL0020_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NPBL0020_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPBL0020BMsg
     * @param selectIdx int
     */
    public static Object[] setParamForShipToAddr(NPBL0020BMsg scrnMsg, int selectIdx) {

        scrnMsg.P.clear();

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToCustCd_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToLocNm_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToAddlLocNm_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToFirstLineAddr_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToScdLineAddr_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToThirdLineAddr_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToFrthLineAddr_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToFirstRefCmntTxt_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToScdRefCmntTxt_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToCtyAddr_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToStCd_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToPostCd_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToCtryCd_A1);
        paramList.add(scrnMsg.A.no(selectIdx).shipToCntyNm_A1);
        paramList.add(scrnMsg.xxPopPrm_P3);
        paramList.add(scrnMsg.xxPopPrm_P2); // ReadOnlyFlg
        paramList.add(scrnMsg.xxPopPrm_P2); // no used
        paramList.add(scrnMsg.xxPopPrm_P2); // no used
        paramList.add(scrnMsg.xxPopPrm_P2); // no used
        paramList.add(scrnMsg.xxPopPrm_P2); // no used
        paramList.add(scrnMsg.A.no(selectIdx).shipToProvNm_A1);

        return paramList.toArray(new EZDBItem[0]);
    }

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * Set Supply Demand Screen param
     * @param scrnMsg NPBL0020BMsg
     * @param lineIndex int
     * @return Supply / Demand Screen Param(NPAL1130) Object[]
     */
    public static Object[] setParamForSupplyDemand(NPBL0020BMsg scrnMsg, int lineIndex) {

        scrnMsg.P.no(IDX_0).xxPopPrm.clear();
        scrnMsg.P.no(IDX_1).xxPopPrm.clear();
        scrnMsg.P.no(IDX_2).xxPopPrm.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_0).xxPopPrm, scrnMsg.A.no(lineIndex).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_1).xxPopPrm, scrnMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_2).xxPopPrm, scrnMsg.srcRtlSwhCd);

        int paramCount = 0;
        Object[] params = new Object[IDX_3];
        params[paramCount++] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(IDX_2).xxPopPrm;

        return params;
    }
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    public static Object[] getAddressPopupParam(NPBL0020BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        scrnMsg.R.clear();

        params[IDX_0] = "";
        params[IDX_1] = "Address Lookup Popup";
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");
        String sql = baseSql.toString();
        params[IDX_2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "City";
        whereArray1[IDX_1] = "CTY_ADDR";
        whereArray1[IDX_2] = scrnMsg.shipToCtyAddr.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = scrnMsg.shipToStCd.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = scrnMsg.shipToPostCd.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        whereArray4[IDX_2] = scrnMsg.shipToCntyNm.getValue();
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "City";
        columnArray1[1] = "CTY_ADDR";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[0] = "State";
        columnArray2[1] = "ST_CD";
        columnArray2[2] = BigDecimal.valueOf(5);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[0] = "Postal Code";
        columnArray3[1] = "POST_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[0] = "County";
        columnArray4[1] = "CNTY_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CTY_ADDR";
        sortConditionArray1[IDX_1] = "ASC";

        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "ST_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[IDX_2];
        sortConditionArray3[IDX_0] = "POST_CD";
        sortConditionArray3[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        Object[] sortConditionArray4 = new Object[IDX_2];
        sortConditionArray4[IDX_0] = "CNTY_NM";
        sortConditionArray4[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray4);

        params[IDX_5] = sortConditionList;
        params[IDX_6] = scrnMsg.R;

        return params;
    }

    // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
    /**
     * isNeededAccount
     * @param chrgAcctEdtblFlg
     * @return boolean
     */
    public static boolean isNeededAccount(String chrgAcctEdtblFlg) {
        return isFlgOn(chrgAcctEdtblFlg);
    }

    /**
     * isFlgOn
     * @param <T>
     * @param flg
     * @return boolean
     */
    private static <T> boolean isFlgOn(T obj) {
        boolean ret = false;

        if (obj != null) {
            if (obj instanceof String) {
                String flg = obj.toString();

                if (ZYPConstant.FLG_ON_Y.equals(flg)) {
                    ret = true;
                } else {
                    ret = false;
                }
            } else {
                ret = false;
            }
        } else {
            ret = false;
        }

        return ret;
    }
    // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
}
