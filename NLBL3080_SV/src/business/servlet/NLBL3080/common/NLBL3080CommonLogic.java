/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080.common;



import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3080.NLBL3080BMsg;
import business.servlet.NLBL3080.NLBL3080Bean;
import business.servlet.NLBL3080.NLBL3080_ABMsg;
import business.servlet.NLBL3080.NLBL3080_BBMsg;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2016/08/08   CITS            R.Shimamoto     Update          QC#10609
 * 2017/06/23   CITS            R.Shimamoto     Update          QC#19518
 * 2018/07/23   CITS            K.Ogino         Update          QC#27047
 * 2018/11/26   Fujitsu         S.Takami        Update          QC#27765
 *</pre>
 */
public class NLBL3080CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg DSAL0040BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3080BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(NLBL3080Constant.SCREEN_ID);
        initCommonButton(userProfileService, handler);
        initButton(userProfileService, handler);
        controlScreenFields(scrnMsg);

        if (0 < scrnMsg.A.getValidCount() || 0 < scrnMsg.B.getValidCount()) {

            controlButton(userProfileService, handler, scrnMsg);
        }

        if (0 < scrnMsg.A.getValidCount() && NLBL3080Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            setVisibilityOrder(scrnMsg, handler);
            setBgColorOrder(scrnMsg);
        }

        if (0 < scrnMsg.B.getValidCount() && NLBL3080Constant.TAB_ID_ORD_LINE.equals(scrnMsg.xxDplyTab.getValue())) {

            setVisibilityOrderLine(scrnMsg, handler);
            setBgColorOrderLine(scrnMsg);
        }
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_SAVE[0], NLBL3080Constant.BTN_CMN_SAVE[1], NLBL3080Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_SUBMIT[0], NLBL3080Constant.BTN_CMN_SUBMIT[1], NLBL3080Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_APPLY[0], NLBL3080Constant.BTN_CMN_APPLY[1], NLBL3080Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_APPROVE[0], NLBL3080Constant.BTN_CMN_APPROVE[1], NLBL3080Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_REJECT[0], NLBL3080Constant.BTN_CMN_REJECT[1], NLBL3080Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_DOWNLOAD[0], NLBL3080Constant.BTN_CMN_DOWNLOAD[1], NLBL3080Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_DETELE[0], NLBL3080Constant.BTN_CMN_DETELE[1], NLBL3080Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_CLEAR[0], NLBL3080Constant.BTN_CMN_CLEAR[1], NLBL3080Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_RESET[0], NLBL3080Constant.BTN_CMN_RESET[1], NLBL3080Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_CMN_RETURN[0], NLBL3080Constant.BTN_CMN_RETURN[1], NLBL3080Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Init screen buttons
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        handler.setButtonProperties(NLBL3080Constant.BTN_SEARCH[0], NLBL3080Constant.BTN_SEARCH[1], NLBL3080Constant.BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_SELECT_ALL[0], NLBL3080Constant.BTN_SELECT_ALL[1], NLBL3080Constant.BTN_SELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_UNSELECT_ALL[0], NLBL3080Constant.BTN_UNSELECT_ALL[1], NLBL3080Constant.BTN_UNSELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_EXPAND_ALL[0], NLBL3080Constant.BTN_EXPAND_ALL[1], NLBL3080Constant.BTN_EXPAND_ALL[2], 0, null);
        handler.setButtonProperties(NLBL3080Constant.BTN_COLLAPSE_ALL[0], NLBL3080Constant.BTN_COLLAPSE_ALL[1], NLBL3080Constant.BTN_COLLAPSE_ALL[2], 0, null);

        // Order Tab
        handler.setButtonProperties(NLBL3080Constant.BTN_COORD_WRK_BENCH[0], NLBL3080Constant.BTN_COORD_WRK_BENCH[1], NLBL3080Constant.BTN_COORD_WRK_BENCH[2], 1, null);

        // Order List Tab
        handler.setButtonProperties(NLBL3080Constant.BTN_ALLOC_CANCEL[0], NLBL3080Constant.BTN_ALLOC_CANCEL[1], NLBL3080Constant.BTN_ALLOC_CANCEL[2], 0, null);
    }

    /**
     * Control screen buttons
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3080BMsg
     */
    public static final void controlButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3080BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NLBL3080Constant.BUSINESS_ID);

        if (functionIds.contains(NLBL3080Constant.FUNC_ID_UPDATE)) {

            if (0 < scrnMsg.B.getValidCount()) {

                handler.setButtonProperties(NLBL3080Constant.BTN_CMN_SUBMIT[0], NLBL3080Constant.BTN_CMN_SUBMIT[1], NLBL3080Constant.BTN_CMN_SUBMIT[2], 1, null);
                handler.setButtonProperties(NLBL3080Constant.BTN_ALLOC_CANCEL[0], NLBL3080Constant.BTN_ALLOC_CANCEL[1], NLBL3080Constant.BTN_ALLOC_CANCEL[2], 1, null);
            }

        } else {

            handler.setButtonProperties(NLBL3080Constant.BTN_CMN_SUBMIT[0], NLBL3080Constant.BTN_CMN_SUBMIT[1], NLBL3080Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NLBL3080Constant.BTN_ALLOC_CANCEL[0], NLBL3080Constant.BTN_ALLOC_CANCEL[1], NLBL3080Constant.BTN_ALLOC_CANCEL[2], 0, null);
        }

        if (0 < scrnMsg.A.getValidCount() || 0 < scrnMsg.B.getValidCount()) {

            handler.setButtonProperties(NLBL3080Constant.BTN_SELECT_ALL[0], NLBL3080Constant.BTN_SELECT_ALL[1], NLBL3080Constant.BTN_SELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLBL3080Constant.BTN_UNSELECT_ALL[0], NLBL3080Constant.BTN_UNSELECT_ALL[1], NLBL3080Constant.BTN_UNSELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLBL3080Constant.BTN_EXPAND_ALL[0], NLBL3080Constant.BTN_EXPAND_ALL[1], NLBL3080Constant.BTN_EXPAND_ALL[2], 1, null);
            handler.setButtonProperties(NLBL3080Constant.BTN_COLLAPSE_ALL[0], NLBL3080Constant.BTN_COLLAPSE_ALL[1], NLBL3080Constant.BTN_COLLAPSE_ALL[2], 1, null);
        }
    }

    /**
     * Control screen fields
     * @param scrnMsg NLBL3080BMsg
     */
    public static final void controlScreenFields(NLBL3080BMsg scrnMsg) {

        // Protect Name Fields
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.tocNm.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NLBL3080_ABMsg scrnMsgALine = scrnMsg.A.no(i);
            scrnMsgALine.dsOrdCatgDescTxt_A1.setInputProtected(true);
            scrnMsgALine.dsOrdTpDescTxt_A1.setInputProtected(true);
            scrnMsgALine.t_MdlNm_A1.setInputProtected(true);
            scrnMsgALine.pickSvcConfigMstrPk_A1.setInputProtected(true);
            scrnMsgALine.shipToCustLocCd_A1.setInputProtected(true);
            scrnMsgALine.dsAcctNm_A1.setInputProtected(true);
            scrnMsgALine.xxAllLineAddr_A1.setInputProtected(true);
            scrnMsgALine.shipToCtyAddr_A1.setInputProtected(true);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsgALine.xxSmryLineFlg_A1.getValue())) {

                scrnMsgALine.xxChkBox_A2.setInputProtected(true);

            } else {

                scrnMsgALine.xxChkBox_A2.setInputProtected(false);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            NLBL3080_BBMsg scrnMsgBLine = scrnMsg.B.no(i);
            scrnMsgBLine.mdseCd_B1.setInputProtected(true);
            scrnMsgBLine.mdseDescShortTxt_B1.setInputProtected(true);
            scrnMsgBLine.setMdseCd_B1.setInputProtected(true);
            scrnMsgBLine.backOrdImpctTpDescTxt_B1.setInputProtected(true);
            scrnMsgBLine.ordQty_B1.setInputProtected(true);
            scrnMsgBLine.ordQty_B2.setInputProtected(true);
            scrnMsgBLine.invtyAvalQty_B1.setInputProtected(true);
            scrnMsgBLine.serNum_B1.setInputProtected(true);
            scrnMsgBLine.t_MdlNm_B1.setInputProtected(true);
            scrnMsgBLine.svcConfigMstrPk_B1.setInputProtected(true);
            scrnMsgBLine.pickSvcConfigMstrPk_B1.setInputProtected(true);
            scrnMsgBLine.rddDt_B1.setInputProtected(true);
            scrnMsgBLine.rtlWhNm_B1.setInputProtected(true);
            scrnMsgBLine.rtlSwhCd_B1.setInputProtected(true);
            scrnMsgBLine.dsOrdLineCatgDescTxt_B1.setInputProtected(true);
            scrnMsgBLine.ordLineStsNm_B1.setInputProtected(true);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsgBLine.xxSmryLineFlg_B1.getValue())) {

                scrnMsgBLine.xxChkBox_B2.setInputProtected(true);

            } else {

                scrnMsgBLine.xxChkBox_B2.setInputProtected(false);
            }
        }
    }

    /**
     * checkInputSearch
     * @param scrnMsg NLBL3080BMsg
     */
    public static void checkInputSearch(NLBL3080BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum) && !ZYPCommonFunc.hasValue(scrnMsg.t_MdlNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.tocCd) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd) && !ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd) && !ZYPCommonFunc.hasValue(scrnMsg.serNum) ) {

            scrnMsg.cpoOrdNum.setErrorInfo(1, "NLZM2276E");
            scrnMsg.serNum.setErrorInfo(1, "NLZM2276E");
            scrnMsg.t_MdlNm.setErrorInfo(1, "NLZM2276E");
            scrnMsg.svcConfigMstrPk.setErrorInfo(1, "NLZM2276E");
            scrnMsg.rtlWhCd.setErrorInfo(1, "NLZM2276E");
            scrnMsg.tocCd.setErrorInfo(1, "NLZM2276E");
            scrnMsg.mdseCd.setErrorInfo(1, "NLZM2276E");
            scrnMsg.shipToCustCd.setErrorInfo(1, "NLZM2276E");
            scrnMsg.rddDt_FR.setErrorInfo(1, "NLZM2276E");
            scrnMsg.rddDt_TO.setErrorInfo(1, "NLZM2276E");
            scrnMsg.xxOrdDt_FR.setErrorInfo(1, "NLZM2276E");
            scrnMsg.xxOrdDt_TO.setErrorInfo(1, "NLZM2276E");
            scrnMsg.dsOrdCatgCd.setErrorInfo(1, "NLZM2276E");
            scrnMsg.dsOrdTpCd.setErrorInfo(1, "NLZM2276E");

        }

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.tocCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxOrdDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxOrdDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_BO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_NS);
        // QC#27047
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_AL);


        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO)) {

            if (0 < scrnMsg.rddDt_FR.getValue().compareTo(scrnMsg.rddDt_TO.getValue())) {

                scrnMsg.rddDt_FR.setErrorInfo(1, "NLZM2277E", new String[]{"Request Date(Through)", "Request Date(From)"});
                scrnMsg.rddDt_TO.setErrorInfo(1, "NLZM2277E", new String[]{"Request Date(Through)", "Request Date(From)"});
                scrnMsg.setMessageInfo("ZZM9037E");
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_TO)) {

            if (0 < scrnMsg.xxOrdDt_FR.getValue().compareTo(scrnMsg.xxOrdDt_TO.getValue())) {

                scrnMsg.xxOrdDt_FR.setErrorInfo(1, "NLZM2277E", new String[]{"Order Date(Through)", "Order Date(From)"});
                scrnMsg.xxOrdDt_TO.setErrorInfo(1, "NLZM2277E", new String[]{"Order Date(Through)", "Order Date(From)"});
                scrnMsg.setMessageInfo("ZZM9037E");
            }
        }

    }

    /**
     * AddCheckItemHeader
     * @param scrnMsg NLBL3080BMsg
     */
    public static void addCheckItemHeader(NLBL3080BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.tocCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxOrdDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxOrdDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_BO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_NS);
        // QC#27047
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_AL);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowCurNum_A);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowCurNum_B);
    }

    /**
     * addCheckItemOrder
     * @param scrnMsg NLBL3080BMsg
     * @param checkFlg Boolean
     */
    public static void addCheckItemOrder(NLBL3080BMsg scrnMsg, Boolean checkFlg) {

    	if (checkFlg) {
    		S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_A,
                    scrnMsg.xxPageShowToNum_A, scrnMsg.xxPageShowOfNum_A, scrnMsg.xxPageShowCurNum_A, scrnMsg.xxPageShowTotNum_A);
    	}

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NLBL3080_ABMsg scrnMsgALine = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(scrnMsgALine.xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsgALine.xxChkBox_A2);
        }
    }

    /**
     * addCheckItemOrderLine
     * @param scrnMsg NLBL3080BMsg
     * @param checkFlg Boolean
     */
    public static void addCheckItemOrderLine(NLBL3080BMsg scrnMsg, Boolean checkFlg) {

    	if (checkFlg) {
    		S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.B, scrnMsg.xxPageShowFromNum_B,
                    scrnMsg.xxPageShowToNum_B, scrnMsg.xxPageShowOfNum_B, scrnMsg.xxPageShowCurNum_B, scrnMsg.xxPageShowTotNum_B);
    	}

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            NLBL3080_BBMsg scrnMsgBLine = scrnMsg.B.no(i);
            scrnMsg.addCheckItem(scrnMsgBLine.xxChkBox_B1);
            scrnMsg.addCheckItem(scrnMsgBLine.xxChkBox_B2);
            scrnMsg.addCheckItem(scrnMsgBLine.mdseCd_B1);
            scrnMsg.addCheckItem(scrnMsgBLine.ordQty_B1);
            scrnMsg.addCheckItem(scrnMsgBLine.invtyAvalQty_B1);
            scrnMsg.addCheckItem(scrnMsgBLine.serNum_B1);
        }
    }

    /**
     * setVisibilityOrder
     * @param scrnMsg NLBL3080BMsg
     * @param handler EZDCommonHandler
     */
    public static void setVisibilityOrder(NLBL3080BMsg scrnMsg, EZDCommonHandler handler) {

        String cpoOrdNum = "";

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // Clear
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "OnChange_ChkBoxCpoNum" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "Open_OrdEntry" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "dsOrdCatgDescTxt" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "dsOrdTpDescTxt" + i);

            // Set Visibility
            if (cpoOrdNum.equals(scrnMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                // [+][-]button
                EZDGUIAttribute trxHdrNumBtm = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
                trxHdrNumBtm.setVisibility(false);
                scrnMsg.addGUIAttribute(trxHdrNumBtm);

                // Checkbox
                EZDGUIAttribute chkBoxB1 = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "OnChange_ChkBoxCpoNum" + i);
                chkBoxB1.setVisibility(false);
                scrnMsg.addGUIAttribute(chkBoxB1);

                // Sales Order Number
                EZDGUIAttribute cpoOrdNumLink = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "Open_OrdEntry" + i);
                cpoOrdNumLink.setVisibility(false);
                scrnMsg.addGUIAttribute(cpoOrdNumLink);

                // Order Category
                EZDGUIAttribute dsOrdCatgDescTxt = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "dsOrdCatgDescTxt" + i);
                dsOrdCatgDescTxt.setVisibility(false);
                scrnMsg.addGUIAttribute(dsOrdCatgDescTxt);

                // Order Reason
                EZDGUIAttribute dsOrdTpDescTxt = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "dsOrdTpDescTxt" + i);
                dsOrdTpDescTxt.setVisibility(false);
                scrnMsg.addGUIAttribute(dsOrdTpDescTxt);
            }

            cpoOrdNum = scrnMsg.A.no(i).cpoOrdNum_A1.getValue();
        }
    }

    /**
     * setVisibilityOrderLine
     * @param scrnMsg NLBL3080BMsg
     * @param handler EZDCommonHandler
     */
    public static void setVisibilityOrderLine(NLBL3080BMsg scrnMsg, EZDCommonHandler handler) {

        String cpoOrdNum = "";
        String dsOrdPosnNum = "";

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            // Clear
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "OnChange_ChkBoxCpoNum" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "Open_OrdEntry" + i);
            scrnMsg.clearGUIAttribute(NLBL3080Constant.SCREEN_ID, "OpenWin_SupdInvtySearch" + i);

            // Set Visibility
            if (cpoOrdNum.equals(scrnMsg.B.no(i).cpoOrdNum_B1.getValue()) && dsOrdPosnNum.equals(scrnMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                // [+][-]button
                EZDGUIAttribute trxHdrNumBtm = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
                trxHdrNumBtm.setVisibility(false);
                scrnMsg.addGUIAttribute(trxHdrNumBtm);

                // Checkbox
                EZDGUIAttribute chkBoxB1 = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "OnChange_ChkBoxCpoNum" + i);
                chkBoxB1.setVisibility(false);
                scrnMsg.addGUIAttribute(chkBoxB1);

                // Sales Order Number
                EZDGUIAttribute trxHdrNumLink = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "Open_OrdEntry" + i);
                trxHdrNumLink.setVisibility(false);
                scrnMsg.addGUIAttribute(trxHdrNumLink);

                // Supersession Inventory
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxSupdFlg_B1.getValue())) {
                    EZDGUIAttribute supInvtyLink = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "OpenWin_SupdInvtySearch" + i);
                    supInvtyLink.setVisibility(false);
                    scrnMsg.addGUIAttribute(supInvtyLink);
                }
            }

            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxSupdFlg_B1.getValue())) {
                scrnMsg.B.no(i).xxAncrCtrlFlg_B1.setInputProtected(true);
                EZDGUIAttribute supInvtyLinkCtrl = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "OpenWin_SupdInvtySearch" + i);
                supInvtyLinkCtrl.setStyleAttribute("color", "black");
                supInvtyLinkCtrl.setStyleAttribute("text-decoration", "none");
                scrnMsg.addGUIAttribute(supInvtyLinkCtrl);
            } else {
                scrnMsg.B.no(i).xxAncrCtrlFlg_B1.setInputProtected(false);
            }

            cpoOrdNum = scrnMsg.B.no(i).cpoOrdNum_B1.getValue();
            dsOrdPosnNum = scrnMsg.B.no(i).dsOrdPosnNum_B1.getValue();
        }
    }

    /**
     * setBgColorOrder
     * @param scrnMsg NLBL3080BMsg
     */
    public static void setBgColorOrder(NLBL3080BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLBL3080Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLBL3080Bean.A, scrnMsg.A);
    }

    /**
     * setBgColorOrderLine
     * @param scrnMsg NLBL3080BMsg
     */
    public static void setBgColorOrderLine(NLBL3080BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLBL3080Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLBL3080Bean.B, scrnMsg.B);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).ordQty_B1) && BigDecimal.ZERO.compareTo(scrnMsg.B.no(i).ordQty_B1.getValue()) < 0) {

                // 2018/11/26 QC#27765 Add Start
                boolean isWarning = false;
                BigDecimal invtyAvalQty = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).invtyAvalQty_B1)) {
                    invtyAvalQty = scrnMsg.B.no(i).invtyAvalQty_B1.getValue();
                }
                if (invtyAvalQty.compareTo(scrnMsg.B.no(i).ordQty_B1.getValue()) < 0) {
                    isWarning = true;
                }
                // 2018/11/26 QC#27765 Add End
                // 2018/11/26 QC#27765 Mod Start
//                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).invtyAvalQty_B1) || BigDecimal.ZERO.compareTo(scrnMsg.B.no(i).invtyAvalQty_B1.getValue()) == 0) {
                if (isWarning) {
                    // 2018/11/26 QC#27765 Mod End

                    EZDGUIAttribute setColorAttribute = null;
                    setColorAttribute = new EZDGUIAttribute(NLBL3080Constant.SCREEN_ID, "id_leftB_row" + i);
                    setColorAttribute.setStyleAttribute("background-color", NLBL3080Constant.HTML_COLOR_YELLOW);
                    scrnMsg.addGUIAttribute(setColorAttribute);
                }
            }
        }
    }

    /**
     * 
     * isUpdateUser
     * 
     * @param userProfileService S21UserProfileService
     * @return true update user
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NLBL3080Constant.BUSINESS_ID);
        return functionIds.contains(NLBL3080Constant.FUNC_ID_UPDATE);
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLBL3080BMsg
     * @return Object[]
     */
    public static Object[] setInitParamForConfigPopup(NLBL3080BMsg scrnMsg) {

        scrnMsg.P.clear();
        scrnMsg.I.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(NLBL3080Constant.IDX_0).xxPopPrm, NLBL3080Constant.PARM_CONFIG_EXST_MODE_CD_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(NLBL3080Constant.IDX_0).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(NLBL3080Constant.IDX_1).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.REMOVED);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(NLBL3080Constant.IDX_10).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(NLBL3080Constant.IDX_11).xxPopPrm, NLBL3080Constant.PARM_MACH_ALLOC_MODE_CODE_03);


        Object[] params = new Object[NLBL3080Constant.IDX_32];
        params[NLBL3080Constant.IDX_0] = scrnMsg.P.no(NLBL3080Constant.IDX_0).xxPopPrm;
        params[NLBL3080Constant.IDX_1] = scrnMsg.svcConfigMstrPk;
        params[NLBL3080Constant.IDX_2] = scrnMsg.P.no(NLBL3080Constant.IDX_2).xxPopPrm;
        params[NLBL3080Constant.IDX_3] = scrnMsg.P.no(NLBL3080Constant.IDX_3).xxPopPrm;
        params[NLBL3080Constant.IDX_4] = scrnMsg.P.no(NLBL3080Constant.IDX_4).xxPopPrm;
        params[NLBL3080Constant.IDX_5] = scrnMsg.t_MdlNm;
        params[NLBL3080Constant.IDX_6] = scrnMsg.P.no(NLBL3080Constant.IDX_6).xxPopPrm;
        params[NLBL3080Constant.IDX_7] = scrnMsg.P.no(NLBL3080Constant.IDX_7).xxPopPrm;
        params[NLBL3080Constant.IDX_8] = scrnMsg.P.no(NLBL3080Constant.IDX_8).xxPopPrm;
        params[NLBL3080Constant.IDX_9] = scrnMsg.I;
        params[NLBL3080Constant.IDX_10] = scrnMsg.P.no(NLBL3080Constant.IDX_10).xxPopPrm;
        params[NLBL3080Constant.IDX_11] = scrnMsg.P.no(NLBL3080Constant.IDX_11).xxPopPrm;
        params[NLBL3080Constant.IDX_12] = scrnMsg.P.no(NLBL3080Constant.IDX_12).xxPopPrm;
        params[NLBL3080Constant.IDX_13] = scrnMsg.P.no(NLBL3080Constant.IDX_13).xxPopPrm;
        params[NLBL3080Constant.IDX_14] = scrnMsg.P.no(NLBL3080Constant.IDX_14).xxPopPrm;
        params[NLBL3080Constant.IDX_15] = scrnMsg.P.no(NLBL3080Constant.IDX_15).xxPopPrm;
        params[NLBL3080Constant.IDX_16] = scrnMsg.P.no(NLBL3080Constant.IDX_16).xxPopPrm;
        params[NLBL3080Constant.IDX_17] = scrnMsg.P.no(NLBL3080Constant.IDX_17).xxPopPrm;
        params[NLBL3080Constant.IDX_18] = scrnMsg.P.no(NLBL3080Constant.IDX_18).xxPopPrm;
        params[NLBL3080Constant.IDX_19] = scrnMsg.P.no(NLBL3080Constant.IDX_19).xxPopPrm;
        params[NLBL3080Constant.IDX_20] = scrnMsg.P.no(NLBL3080Constant.IDX_20).xxPopPrm;
        params[NLBL3080Constant.IDX_21] = scrnMsg.P.no(NLBL3080Constant.IDX_21).xxPopPrm;
        params[NLBL3080Constant.IDX_22] = scrnMsg.P.no(NLBL3080Constant.IDX_22).xxPopPrm;
        params[NLBL3080Constant.IDX_23] = scrnMsg.P.no(NLBL3080Constant.IDX_23).xxPopPrm;
        params[NLBL3080Constant.IDX_24] = scrnMsg.P.no(NLBL3080Constant.IDX_24).xxPopPrm;
        params[NLBL3080Constant.IDX_25] = scrnMsg.P.no(NLBL3080Constant.IDX_25).xxPopPrm;
        params[NLBL3080Constant.IDX_26] = scrnMsg.t_MdlNm;
        params[NLBL3080Constant.IDX_27] = scrnMsg.P.no(NLBL3080Constant.IDX_27).xxPopPrm;
        params[NLBL3080Constant.IDX_28] = scrnMsg.P.no(NLBL3080Constant.IDX_28).xxPopPrm;
        params[NLBL3080Constant.IDX_29] = scrnMsg.svcConfigMstrPk;
        params[NLBL3080Constant.IDX_30] = scrnMsg.P.no(NLBL3080Constant.IDX_30).xxPopPrm;
        params[NLBL3080Constant.IDX_31] = scrnMsg.P.no(NLBL3080Constant.IDX_31).xxPopPrm;


        return params;
    }
}
