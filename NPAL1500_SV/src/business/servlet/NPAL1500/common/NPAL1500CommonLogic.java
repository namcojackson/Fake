/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1500.common;

import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0023E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PCT_100;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_ADD_CONFIG;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_ADD_LINE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_ADD_NEW_LINE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_APPLY;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_RQSTSHIPDT_APPLY;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_APPLY_ASL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_APPLY_DISC;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_APPRV_HIST;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_ATTACHMENTS;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CANCEL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CLOSE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_APPLY;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_APPROVE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_CLEAR;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_DETELE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_REJECT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_RESET;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_RETURN;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_SAVE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_SUBMIT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_COMMENT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_COPY;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DEST_WH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_ACCT_ACRL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_ACCT_CHRG;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_ACCT_VAR;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_DEST_RSWH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_LINE_PRICE_INFO;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_MDSE_INFO;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_MDSE_POPUP;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_SERIAL_POPUP;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_DETAIL_SRC_RSWH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_GET_ADDRESS;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_GET_SUPPLIER_NAME;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_GET_SUPPLIER_SITE_NAME;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_HISTORY;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_MOVE_TO_COMPONENT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_PRINT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_SELECT_ALL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_SHIP_TO_CUST;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_UN_SELECT_ALL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BUYER_SYSTEM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNC_APPROVE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNC_ID_SUBMIT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNC_ID_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_0;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_1;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_2;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_3;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_4;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_5;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_50;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_6;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_7;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM0049E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1326E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1329E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.SCREEN_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.SCR_NM_ACCOUNT_TYPE_CH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.SORT_IMG_ASC_PATH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.SORT_IMG_ATTR_PREFIX;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.SORT_IMG_DESC_PATH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_ADDL_HEADER;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_AP_INVOICE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TBL_ID_LEFT_B;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TBL_ID_A;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import parts.servletcommon.gui.EZDGUIImageAttribute;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.NPAL1500BMsg;
import business.servlet.NPAL1500.NPAL1500Bean;
import business.servlet.NPAL1500.NPAL1500_ABMsg;
import business.servlet.NPAL1500.NPAL1500_ABMsgArray;
import business.servlet.NPAL1500.NPAL1500_XBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.security.utils.S21StringUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   CITS            N Akaishi       Create          N/A
 * 2016/05/03   CSAI            K.Lee           Update          QC#7300
 * 09/07/2016   CITS            K.Ogino         Update          QC#11540
 * 2016/09/09   CITS            R.Shimamoto     Update          QC#13985
 * 09/12/2016   CITS            S.Endo          Update          N/A
 * 09/27/2016   CITS            S.Endo          Update          QC#11985
 * 09/28/2016   CITS            S.Endo          Update          QC#11985
 * 10/05/2016   CITS            S.Endo          Update          QC#12768
 * 10/12/2016   CITS            S.Endo          Update          QC#14285
 * 10/20/2016   CITS            S.Endo          Update          QC#15328/QC#15222/Refactoring
 * 11/15/2016   CITS            R.Shimamoto     Update          QC#15979
 * 12/12/2016   CITS            R.Shimamoto     Update          QC#15817
 * 06/26/2016   CITS            S.Endo          Update          QC#19378
 * 09/01/2017   CITS            R.Shimamoto     Update          QC#20439
 * 09/15/2017   CITS            R.Shimamoto     Update          QC#20735
 * 11/02/2017   Hitachi         Y.Takeno        Update          QC#21849(Sol#218)
 * 11/16/2017   CITS            S.Katsuma       Update          QC#22471
 * 11/28/2017   CITS            K.Ogino         Update          QC#22481
 * 12/04/2017   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 01/09/2018   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 01/25/2018   CITS            K.Ogino         Update          QC#23007
 * 2018/02/08   CITS            K.Ogino         Update          QC#21169
 * 2018/06/14   CITS            K.Kameoka       Update          QC#26474
 * 06/19/2018   CITS            T.Hakodate      Update          QC#24932
 * 06/15/2018   CITS            K.Kameoka       Update          QC#24996
 * 06/19/2018   CITS            K.Kameoka       Update          QC#21358
 * 06/20/2018   CITS            K.Kameoka       Update          QC#18420
 * 06/28/2018   CITS            K.Kameoka       Update          QC#26893
 * 07/03/2018   CITS            T.Tokutomi      Update          QC#23726
 * 07/06/2018   CITS            K.Kameoka       Update          QC#25024
 * 08/07/2018   CITS            K.Ogino         Update          QC#27024
 * 09/18/2018   CITS            K.Ogino         Update          QC#28311
 * 09/25/2018   CITS            K.Kameoka       Update          QC#28226
 * 10/01/2018   CITS            K.Kameoka       Update          QC#28463/28460/28146/27770
 * 10/11/2018   CITS            K.Kameoka       Update          QC#28726
 * 10/29/2018   Fujitsu         S.Ohki          Update          QC#28983
 * 01/31/2019   CITS            K.Ogino         Update          QC#30039
 * 02/13/2019   Fujitsu         T.Ogura         Update          QC#30267
 * 05/21/2019   CITS            K.Ogino         Update          QC#50139
 * 10/04/2019   CITS            R.Shimamoto     Update          QC#53392
 * 12/02/2019   Fujitsu         T.Ogura         Update          QC#54815
 * 12/12/2019   Fujitsu         R.Nakamura      Update          QC#55063
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 02/18/2020   Fujitsu         T.Ogura         Update          QC#55916
 * 09/15/2020   CITS            A.Marte         Update          QC#57698
 * 04/21/2021   CITS            J.Evangelista   Update          QC#57102
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2023/02/15   Hitachi         S.Dong          Update          QC#60966
 * 2023/04/04   Hitachi         S.Dong          Update          QC#61287
 * 2023/04/19   Hitachi         TZ.Win          Update          QC#61299
 *</pre>
 */
public final class NPAL1500CommonLogic {

    private NPAL1500CommonLogic() {

    }
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param funcList List<String>
     */
    public static final void ctrlScreenItem(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, List<String> funcList) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        // full protected
        protectAllItem(handler, scrnMsg);

        // check Role
        boolean inquiry = chkInquiryRole(funcList);

        // START 2021/04/21 J.Evangelista [QC#57102,ADD]
        // Set sort icons.
        NPAL1500CommonLogic.setTblColumnSortIMG(scrnMsg);
        // END 2021/04/21 J.Evangelista [QC#57102,ADD]

        // Set background color for Detail Tab
        NPAL1500CommonLogic.setTblBackColor(scrnMsg);

        // set Enable Item
        if (inquiry) {
            setItemEnableHasSubmitPermission(handler, scrnMsg);
            // for Inquiry Function
        } else {
            // Header Item
            scrnMsg.poNum.setInputProtected(false);
            // Header Button
            handler.setButtonEnabled(BTN_SEARCH[0], true);
            // set Common Enable Button
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
            // QC#28726 Add START
            if (funcList.contains(FUNC_ID_SEARCH)) {
                handler.setButtonEnabled(BTN_HISTORY, true);
            }
            // QC#28726 Add END

            // START 02/18/2020 T.Ogura [QC#55916,ADD]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            }
            // END   02/18/2020 T.Ogura [QC#55916,ADD]
        }
        //set Detail Item 
        if (scrnMsg.A.getValidCount() > 0) {
            setDetailItemEnableOnBuyBack(handler, scrnMsg);
        }

        // set Invoice Tab 
        if (scrnMsg.B.getValidCount() > 0) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).apVndInvNum_B1.setInputProtected(false);
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxCntDplyFlg_A2) &&  ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxCntDplyFlg_A2.getValue())) {
                scrnMsg.A.no(i).mdseCd_A1.clear();
            }
        }
    }

    /**
     * set detail item's column enable/disable whether PO_TP_CD is BUYBACK_PO(BB) or not.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void setDetailItemEnableOnBuyBack(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcConfigMstrPk_A1)) {
                //if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcConfigMstrPk_A1)) {
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_DETAIL_MDSE_POPUP, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, i, false);
                //}
            }
        }
    }


    /**
     * set item enable/disable when user has submit permission.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void setItemEnableHasSubmitPermission(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        if (PO_STS.SAVED.equals(scrnMsg.poStsCd_H.getValue())) {
            enableItemForSave(handler, scrnMsg);

        } else if (PO_STS.WAITING_FOR_APPROVAL.equals(scrnMsg.poStsCd_H.getValue())) {
            enableItemForWaiting(handler, scrnMsg);

        } else if (PO_STS.VALIDATED.equals(scrnMsg.poStsCd_H.getValue())) {
            enableItemForValidate(handler, scrnMsg);

        } else if (PO_STS.PO_CONFIRMED.equals(scrnMsg.poStsCd_H.getValue())) {
            // Same pattern with "Validate"
            enableItemForValidate(handler, scrnMsg);

        } else if (PO_STS.PO_ERROR.equals(scrnMsg.poStsCd_H.getValue())) {
            // Same pattern with "Validate"
            enableItemForValidate(handler, scrnMsg);

        } else if (PO_STS.RECEIVING.equals(scrnMsg.poStsCd_H.getValue())) {
            // Same pattern with "Validate"
            enableItemForValidate(handler, scrnMsg);

        } else if (PO_STS.CLOSED.equals(scrnMsg.poStsCd_H.getValue()) && PO_HDR_STS.OPEN.equals(scrnMsg.poHdrStsCd.getValue())) {
            enableItemForOpenedClose(handler, scrnMsg);

        } else if (PO_STS.CLOSED.equals(scrnMsg.poStsCd_H.getValue()) && PO_HDR_STS.CLOSED.equals(scrnMsg.poHdrStsCd.getValue())) {
            enableItemForClose(handler, scrnMsg);

        } else if (PO_STS.CANCELLED.equals(scrnMsg.poStsCd_H.getValue())) {
            enableItemForCancel(handler, scrnMsg);

        } else {
            // New
            enableItemForNew(handler, scrnMsg);
        }

        // common
        // START 2020/09/15 A.Marte [QC#57698, DEL]
        // Remove enabling of transmit method input if Print Button is not enabled
        // scrnMsg.trsmtMethTpCd.setInputProtected(false);
        // scrnMsg.sendPoFaxNum.setInputProtected(false);
        // scrnMsg.sendPoEmlAddr.setInputProtected(false);
        // scrnMsg.rptDestId.setInputProtected(false);
        // END 2020/09/15 A.Marte [QC#57698, DEL]
        scrnMsg.xxChkBox_LO.setInputProtected(false);

    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    public static final void protectAllItem(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {

        // scrnItem
        scrnMsg.setInputProtected(true);
        scrnMsg.A.setInputProtected(true);
        // START 2020/09/15 A.Marte [QC#57698, ADD]
        scrnMsg.trsmtMethTpCd.setInputProtected(true);
        // END 2020/09/15 A.Marte [QC#57698, ADD]
        // add start 2022/05/16 QC#57934
        scrnMsg.xxComnColOrdTxt.setInputProtected(false);
        // add end 2022/05/16 QC#57934

        // button activation
        // Header
        handler.setButtonEnabled(BTN_SEARCH[0], false);
        handler.setButtonEnabled(BTN_COPY[0], false);
        handler.setButtonEnabled(BTN_APPRV_HIST, false);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        handler.setButtonEnabled(BTN_DEST_WH, false);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_NAME, false);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_SITE_NAME, false);
        // START 2017/11/13 [QC#21849, ADD]
        handler.setButtonEnabled(BTN_SHIP_TO_CUST, false);
        // END   2017/11/13 [QC#21849, ADD]
        handler.setButtonEnabled(BTN_APPLY, false);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, false);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        // START 2023/04/19 TZ.Win [QC#61299, ADD]
        handler.setButtonEnabled(BTN_APPLY_ASL, false);
        // END 2023/04/19 TZ.Win [QC#61299, ADD]

        // Additional Header
        // START 2017/11/16 S.Katsuma [QC#22471,ADD]
//        handler.setButtonEnabled(BTN_COMMENT, false);
        handler.setButtonEnabled(BTN_COMMENT, true);
        // END 2017/11/16 S.Katsuma [QC#22471,ADD]
        handler.setButtonEnabled(BTN_GET_ADDRESS, false);

        // Detail
        handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        handler.setButtonEnabled(BTN_ADD_CONFIG, false);
        //QC#18420 Add Start
        handler.setButtonEnabled(BTN_APPLY_DISC, false);
        handler.setButtonEnabled(BTN_DETAIL_LINE_PRICE_INFO, false);
        //QC#18420 Add End

        handler.setButtonEnabled(BTN_DETAIL_MDSE_INFO, false);
        handler.setButtonEnabled(BTN_DETAIL_MDSE_POPUP, false);
        handler.setButtonEnabled(BTN_DETAIL_DEST_RSWH, false);
        handler.setButtonEnabled(BTN_DETAIL_SRC_RSWH, false);
        handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, false);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, false);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_ACRL, false);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_VAR, false);

        handler.setButtonEnabled(BTN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, false);
        handler.setButtonEnabled(BTN_HISTORY, false);
        handler.setButtonEnabled(BTN_CANCEL[0], false);
        handler.setButtonEnabled(BTN_CLOSE[0], false);
        handler.setButtonEnabled(BTN_PRINT[0], false);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    /**
     * <pre>
     * enableItemForNew
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForNew(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {

        // Header Item
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.dsPoTpCd.setInputProtected(false);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        handler.setButtonEnabled(BTN_APPLY, true);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.rqstShipDt.setInputProtected(false);
        handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, true);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.prchGrpCd.setInputProtected(false);
        scrnMsg.poOrdCmntTxt.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        scrnMsg.prntVndCd_L1.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.vndCd_L1.setInputProtected(false);
        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
            scrnMsg.srcRtlWhCd_SC.setInputProtected(false);
            scrnMsg.srcRtlWhCd_L1.setInputProtected(false);
        }
        // START 2019/02/13 T.Ogura [QC#30267,MOD]
//        if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
//            handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, true);
//        } else {
//            handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, false);
//        }
        handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, false);
        // END   2019/02/13 T.Ogura [QC#30267,MOD]
        scrnMsg.destRtlWhCd_DS.setInputProtected(false);
        scrnMsg.destRtlWhCd_L1.setInputProtected(false);
        // START 2017/11/08 [QC#21849, ADD]
        scrnMsg.shipToCustCd.setInputProtected(false);
        scrnMsg.shipToCustCd_L1.setInputProtected(false);
        scrnMsg.shipToLocNm.setInputProtected(false);
        // END   2017/11/08 [QC#21849, ADD]
        // QC#23007
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd_DS) //
                //QC#28460 Add Start
                && ZYPCommonFunc.hasValue(scrnMsg.poNum) //
                //QC#28460 Add End
                && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(scrnMsg.rtlWhCatgCd_DS.getValue()) //
                        || RTL_WH_CATG.CUSTOMER.equals(scrnMsg.rtlWhCatgCd_DS.getValue()) //
                        //QC#25024 Mod Start
//                        || MANUAL_DIRECT_SHIP_CUST_CD.equals(scrnMsg.destRtlWhCd_DS.getValue()))) {
                        || ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_DS.getValue()))) {
                        //QC#25024 Mod End

            scrnMsg.shipToCustCd.setInputProtected(true);
            scrnMsg.shipToCustCd_L1.setInputProtected(true);
            scrnMsg.shipToLocNm.setInputProtected(true);
            handler.setButtonEnabled(BTN_SHIP_TO_CUST, false);

        } else {

            scrnMsg.shipToCustCd.setInputProtected(false);
            scrnMsg.shipToCustCd_L1.setInputProtected(false);
            handler.setButtonEnabled(BTN_SHIP_TO_CUST, true);
        }

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        handler.setButtonEnabled(BTN_DEST_WH, true);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_NAME, true);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_SITE_NAME, true);
        // START 2023/04/19 TZ.Win [QC#61299, ADD]
        handler.setButtonEnabled(BTN_APPLY_ASL, true);
        // END 2023/04/19 TZ.Win [QC#61299, ADD]

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            // Header
            handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
            handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
            //QC#18420 Add Start
            scrnMsg.poDtlDiscPct.setInputProtected(false);
            handler.setButtonEnabled(BTN_APPLY_DISC, true);
            //QC#18420 Add Start

            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                scrnMsg.svcConfigMstrPk.setInputProtected(false);
                scrnMsg.svcConfigMstrPk_L1.setInputProtected(false);
                handler.setButtonEnabled(BTN_ADD_CONFIG, true);
                handler.setButtonEnabled(BTN_DETAIL_SRC_RSWH, true);
            }

            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }

            handler.setButtonEnabled(BTN_CANCEL[0], true);

        } else if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

            // ship to location
            scrnMsg.shipToAddlLocNm_ST.setInputProtected(false);
            scrnMsg.xxAllLineAddr_ST.setInputProtected(false);
            scrnMsg.shipToPostCd_ST.setInputProtected(false);
            scrnMsg.shipToCtyAddr_ST.setInputProtected(false);
            scrnMsg.shipToCntyNm_ST.setInputProtected(false);
            scrnMsg.shipToStCd_ST.setInputProtected(false);
            scrnMsg.shipToProvNm_ST.setInputProtected(false);
            scrnMsg.shipToCtryCd_ST.setInputProtected(false);
            // ship to location Anchor
            scrnMsg.shipToCtryCd_L1.setInputProtected(false);
            scrnMsg.xxLinkAncr_AL.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_ADDRESS, true);

            //QC#24996 Add Start
            scrnMsg.ctacPsnNm.setInputProtected(false);
            //QC#24996 Add End

            // freight info
            scrnMsg.frtCondCd.setInputProtected(false);
            scrnMsg.shpgSvcLvlCd.setInputProtected(false);
            scrnMsg.carrCd.setInputProtected(false);
            scrnMsg.carrAcctNum.setInputProtected(false);
            // freight info Anchor
            scrnMsg.carrCd_L1.setInputProtected(false);

            // Button
            handler.setButtonEnabled(BTN_COMMENT, true);

            // Header Details
            //QC#28226 Add Start
            scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt_L1.setInputProtected(false);
            //QC#28226 Add End
            scrnMsg.xxDsMultMsgDplyTxt_SI.setInputProtected(false);
            scrnMsg.xxDsMultMsgDplyTxt_RN.setInputProtected(false);
            scrnMsg.xxDsMultMsgDplyTxt_SN.setInputProtected(false);

            // QC#20735 Add.
            scrnMsg.poDtlIntfcErrMsgTxt_SL.setInputProtected(true);
            scrnMsg.poDtlIntfcErrMsgTxt_P0.setInputProtected(true);
            scrnMsg.poDtlIntfcErrMsgTxt_P1.setInputProtected(true);
            scrnMsg.xxDtTm_HP.setInputProtected(true);
        }

        // set Common Enable Button
        if (PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * enableItemForSave
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForSave(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {

        // Header Item
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        handler.setButtonEnabled(BTN_APPLY, true);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.rqstShipDt.setInputProtected(false);
        handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, true);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.prchGrpCd.setInputProtected(false);
        scrnMsg.poOrdCmntTxt.setInputProtected(false);

        //QC#25024 Add Start
        //for MD
        ctrlScreenItemForManualDrop(handler, scrnMsg);
        //QC#25024 Add End
        
        // START 2023/04/04 S.Dong [QC#61287,ADD]
        scrnMsg.destRtlWhCd_L1.setInputProtected(false);
        scrnMsg.destRtlWhCd_DS.setInputProtected(false);
        handler.setButtonEnabled(BTN_DEST_WH, true);
        scrnMsg.shipToCustCd.setInputProtected(false);
        scrnMsg.shipToCustCd_L1.setInputProtected(false);
        // END 2023/04/04 S.Dong [QC#61287,ADD]

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        // START 2023/04/19 TZ.Win [QC#61299, ADD]
        handler.setButtonEnabled(BTN_APPLY_ASL, true);
        // END 2023/04/19 TZ.Win [QC#61299, ADD]

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            
            // QC#24932 ADD START
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                // Header
                handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
                handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
                
                //QC#26893 Add Start
                scrnMsg.poDtlDiscPct.setInputProtected(false);
                handler.setButtonEnabled(BTN_APPLY_DISC, true);
                //QC#26893 Add End
            // QC#28311
            } else {
                handler.setButtonEnabled(BTN_CANCEL[0], true);
            }
            // QC#24932 ADD END
            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                scrnMsg.svcConfigMstrPk.setInputProtected(false);
                scrnMsg.svcConfigMstrPk_L1.setInputProtected(false);
                handler.setButtonEnabled(BTN_ADD_CONFIG, true);
                handler.setButtonEnabled(BTN_DETAIL_SRC_RSWH, true);
            }

            handler.setButtonEnabled(BTN_HISTORY, true);

            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }
            if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, true);
            }
            // QC#24932 ADD START
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                handler.setButtonEnabled(BTN_CANCEL[0], true);
            }
            // QC#24932 ADD END

        } else if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

            // ship to location
            scrnMsg.shipToAddlLocNm_ST.setInputProtected(false);
            scrnMsg.xxAllLineAddr_ST.setInputProtected(false);
            scrnMsg.shipToPostCd_ST.setInputProtected(false);
            scrnMsg.shipToCtyAddr_ST.setInputProtected(false);
            scrnMsg.shipToCntyNm_ST.setInputProtected(false);
            scrnMsg.shipToStCd_ST.setInputProtected(false);
            scrnMsg.shipToProvNm_ST.setInputProtected(false);
            scrnMsg.shipToCtryCd_ST.setInputProtected(false);
            // ship to location Anchor
            scrnMsg.shipToCtryCd_L1.setInputProtected(false);
            scrnMsg.xxLinkAncr_AL.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_ADDRESS, true);

            //QC#24996 Add Start
            scrnMsg.ctacPsnNm.setInputProtected(false);
            //QC#24996 Add End

            // freight info
            scrnMsg.frtCondCd.setInputProtected(false);
            scrnMsg.shpgSvcLvlCd.setInputProtected(false);
            scrnMsg.carrCd.setInputProtected(false);
            scrnMsg.carrAcctNum.setInputProtected(false);
            // freight info Anchor
            scrnMsg.carrCd_L1.setInputProtected(false);

            // Button
            handler.setButtonEnabled(BTN_COMMENT, true);

            // Header Details
            //QC#28226 Add Start
            scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt_L1.setInputProtected(false);
            //QC#28226 Add End
            scrnMsg.xxDsMultMsgDplyTxt_SI.setInputProtected(false);
            scrnMsg.xxDsMultMsgDplyTxt_RN.setInputProtected(false);
            scrnMsg.xxDsMultMsgDplyTxt_SN.setInputProtected(false);

            // QC#20735 Add.
            scrnMsg.poDtlIntfcErrMsgTxt_SL.setInputProtected(true);
            scrnMsg.poDtlIntfcErrMsgTxt_P0.setInputProtected(true);
            scrnMsg.poDtlIntfcErrMsgTxt_P1.setInputProtected(true);
            scrnMsg.xxDtTm_HP.setInputProtected(true);
        }
        // QC#24932 ADD START
        // set Common Enable Button
        if (PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            if (PO_APVL_STS.ENTERED.equals(scrnMsg.poApvlStsCd.getValue())) {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            }
            // QC#24932 ADD END
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * enableItemForWaiting
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForWaiting(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        // Header Item
        scrnMsg.poNum.setInputProtected(false);

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonEnabled(BTN_HISTORY, true);

            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }

        }

        // set Common Enable Button
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        } else {
        	// QC#20439
        	if (PO_APVL_STS.AWAITING_APPROVAL.equals(scrnMsg.poApvlStsCd.getValue())
        			&& BUYER_SYSTEM.equals(scrnMsg.poSubmtPsnCd.getValue())) {
        		handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        	} else {
        		handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        	}
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    /**
     * <pre>
     * enableItemForValidate
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForValidate(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        // Header Item
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.poOrdCmntTxt.setInputProtected(false);

        //QC#25024 Add Start
        //for MD
        ctrlScreenItemForManualDrop(handler, scrnMsg);
        //QC#25024 Add End

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        // START 2023/04/28 TZ.Win [QC#61299, ADD]
        if(!PO_APVL_STS.AWAITING_APPROVAL.equals(scrnMsg.poApvlStsCd.getValue())){
            handler.setButtonEnabled(BTN_APPLY_ASL, true);
        }
        // END 2023/04/28 TZ.Win [QC#61299, ADD]

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            
            // QC#24932 ADD START
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                // Header
                if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue()) || DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                    handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
                    handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
                } else {
                    handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
                    handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
                }
                // QC#26893 Add Start
//                scrnMsg.poDtlDiscPct.setInputProtected(false);
                // QC#26893 Add End
                //QC#18420 Add Start
                scrnMsg.poDtlDiscPct.setInputProtected(false);
                handler.setButtonEnabled(BTN_APPLY_DISC, true);
                //QC#18420 Add End
            }
            // QC#24932 ADD END

            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                // QC#25024 MOD Start
                scrnMsg.svcConfigMstrPk.setInputProtected(true);
                scrnMsg.svcConfigMstrPk_L1.setInputProtected(true);
                handler.setButtonEnabled(BTN_ADD_CONFIG, false);
//                scrnMsg.svcConfigMstrPk.setInputProtected(false);
//                scrnMsg.svcConfigMstrPk_L1.setInputProtected(false);
//                handler.setButtonEnabled(BTN_ADD_CONFIG, true);
                // QC#25024 MOD END
            }

            // Footer
            // START 2020/09/15 A.Marte [QC#57698, ADD]
            if(chkPrintHasPermission(scrnMsg)){
                scrnMsg.trsmtMethTpCd.setInputProtected(false);
                scrnMsg.sendPoEmlAddr.setInputProtected(false);
                scrnMsg.sendPoFaxNum.setInputProtected(false);
                scrnMsg.rptDestId.setInputProtected(false);
                
                handler.setButtonEnabled(BTN_PRINT[0], true);
            }
            // END 2020/09/15 A.Marte [QC#57698, ADD]

            handler.setButtonEnabled(BTN_HISTORY, true);

            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            // QC#24932 ADD START
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                if (scrnMsg.A.getValidCount() > 0) {
                    handler.setButtonEnabled(BTN_SELECT_ALL, true);
                    handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
                }
                if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                    handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, true);
                }
            }
            // QC#28311
            // QC#22481
            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                handler.setButtonEnabled(BTN_CLOSE[0], false);
                handler.setButtonEnabled(BTN_CANCEL[0], false);
                // QC#50139
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    if (PO_LINE_STS.CANCELLED.equals(scrnMsg.A.no(i).poLineStsCd_A1.getValue())) {
                        handler.setButtonEnabled(BTN_CANCEL[0], true);
                    }
                }
            } else {
                handler.setButtonEnabled(BTN_CLOSE[0], true);
                handler.setButtonEnabled(BTN_CANCEL[0], true);
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }

        } else if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);

            // Button
            handler.setButtonEnabled(BTN_COMMENT, true);

            // QC#28983 Add Start
            scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt_L1.setInputProtected(false);
            // QC#28983 Add End

            // QC#25024 Add Start
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            // QC#25024 Add End
                // Header Details
                scrnMsg.xxDsMultMsgDplyTxt_SI.setInputProtected(false);
                scrnMsg.xxDsMultMsgDplyTxt_RN.setInputProtected(false);
                scrnMsg.xxDsMultMsgDplyTxt_SN.setInputProtected(false);

                // QC#20735 Add.
                scrnMsg.poDtlIntfcErrMsgTxt_SL.setInputProtected(true);
                scrnMsg.poDtlIntfcErrMsgTxt_P0.setInputProtected(true);
                scrnMsg.poDtlIntfcErrMsgTxt_P1.setInputProtected(true);
                scrnMsg.xxDtTm_HP.setInputProtected(true);

                // QC#25024 Add Start
                scrnMsg.ctacPsnNm.setInputProtected(false);
                scrnMsg.frtCondCd.setInputProtected(false);
                scrnMsg.shpgSvcLvlCd.setInputProtected(false);
                scrnMsg.carrCd.setInputProtected(false);
                scrnMsg.carrAcctNum.setInputProtected(false);
                scrnMsg.carrCd_L1.setInputProtected(false);
                // QC#25024 Add End
            }
        }

        // control
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue()) //
                || DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())){ //
                //QC#28463 DEL Start
                //|| PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                //QC#28463 DEL Start
            // QC#30039
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
        	// QC#20439
        	if (PO_APVL_STS.AWAITING_APPROVAL.equals(scrnMsg.poApvlStsCd.getValue())
        			&& BUYER_SYSTEM.equals(scrnMsg.poSubmtPsnCd.getValue())) {
        		handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        	} else {
        		handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        	}
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * enableItemForOpenedClose
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForOpenedClose(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        // Header Item
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.poOrdCmntTxt.setInputProtected(false);

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        // START 2023/04/28 TZ.Win [QC#61299, ADD]
        if(!PO_APVL_STS.AWAITING_APPROVAL.equals(scrnMsg.poApvlStsCd.getValue())){
            handler.setButtonEnabled(BTN_APPLY_ASL, true);
        }
        // END 2023/04/28 TZ.Win [QC#61299, ADD]

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            // Footer
            // START 2020/09/15 A.Marte [QC#57698, ADD]
            if(chkPrintHasPermission(scrnMsg)){
                scrnMsg.trsmtMethTpCd.setInputProtected(false);
                scrnMsg.sendPoEmlAddr.setInputProtected(false);
                scrnMsg.sendPoFaxNum.setInputProtected(false);
                scrnMsg.rptDestId.setInputProtected(false);
                
                handler.setButtonEnabled(BTN_PRINT[0], true);
            }
            // END 2020/09/15 A.Marte [QC#57698, ADD]
            
            handler.setButtonEnabled(BTN_HISTORY, true);   
            
            //QC#18602 ADD Start
            //QC#28463 Mod Start
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            //QC#28463 Mod End
                if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue()) || DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                    handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
                } else {
                    handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
                }
            }
            handler.setButtonEnabled(BTN_CANCEL[0], true);
            //QC#18602 ADD END
            
            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }
            if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, true);
            }

            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue()) //
                    || DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                handler.setButtonEnabled(BTN_CLOSE[0], false);
                handler.setButtonEnabled(BTN_CANCEL[0], false);
            } else {
                handler.setButtonEnabled(BTN_CLOSE[0], true);
                handler.setButtonEnabled(BTN_CANCEL[0], true);
            }

        } else if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            // Button
            handler.setButtonEnabled(BTN_COMMENT, true);

            // QC#28983 Add Start
            scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt_L1.setInputProtected(false);
            // QC#28983 Add End

            //QC#28146 Mod Start
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            // QC#28146 Mod End
                // Header Details
                scrnMsg.xxDsMultMsgDplyTxt_SI.setInputProtected(false);
                scrnMsg.xxDsMultMsgDplyTxt_RN.setInputProtected(false);
                scrnMsg.xxDsMultMsgDplyTxt_SN.setInputProtected(false);

                // QC#20735 Add.
                scrnMsg.poDtlIntfcErrMsgTxt_SL.setInputProtected(true);
                scrnMsg.poDtlIntfcErrMsgTxt_P0.setInputProtected(true);
                scrnMsg.poDtlIntfcErrMsgTxt_P1.setInputProtected(true);
                scrnMsg.xxDtTm_HP.setInputProtected(true);

                // QC#25024 Add Start
                scrnMsg.ctacPsnNm.setInputProtected(false);
                scrnMsg.frtCondCd.setInputProtected(false);
                scrnMsg.shpgSvcLvlCd.setInputProtected(false);
                scrnMsg.carrCd.setInputProtected(false);
                scrnMsg.carrAcctNum.setInputProtected(false);
                scrnMsg.carrCd_L1.setInputProtected(false);
                // QC#25024 Add End
            }
        }

        // control
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue()) //
                || DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue()) ) {
        // QC#28463 Del Start
//                || PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
        // QC#28463 Del End
            // QC#30039
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    /**
     * <pre>
     * enableItemForClose
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForClose(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        // Header Item
        scrnMsg.poNum.setInputProtected(false);
        //QC#25024 ADD Start
        scrnMsg.poOrdCmntTxt.setInputProtected(false);
        //QC#25024 ADD Start

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            // Footer
            // START 2020/09/15 A.Marte [QC#57698, ADD]
            if(chkPrintHasPermission(scrnMsg)){
                scrnMsg.trsmtMethTpCd.setInputProtected(false);
                scrnMsg.sendPoEmlAddr.setInputProtected(false);
                scrnMsg.sendPoFaxNum.setInputProtected(false);
                scrnMsg.rptDestId.setInputProtected(false);
                
                handler.setButtonEnabled(BTN_PRINT[0], true);
            }
            // END 2020/09/15 A.Marte [QC#57698, ADD]
            
            handler.setButtonEnabled(BTN_HISTORY, true);

            //QC#18602 ADD Start
            //QC#28463 Mod Start
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            //QC#28463 Mod End
                if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue()) //
                        || DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                    handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
                } else {
                    handler.setButtonEnabled(BTN_ADD_NEW_LINE, true);
                }
            }
            handler.setButtonEnabled(BTN_CANCEL[0], true);
            //QC#18602 ADD END

            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }
            if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, true);
                handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
            }

        } else if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            // Button
            handler.setButtonEnabled(BTN_COMMENT, true);

            // QC#20735 Add.
            scrnMsg.poDtlIntfcErrMsgTxt_SL.setInputProtected(true);
            scrnMsg.poDtlIntfcErrMsgTxt_P0.setInputProtected(true);
            scrnMsg.poDtlIntfcErrMsgTxt_P1.setInputProtected(true);
            scrnMsg.xxDtTm_HP.setInputProtected(true);
        }

        // set Common Enable Button
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    /**
     * <pre>
     * enableItemForCancel
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static final void enableItemForCancel(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        // Header Item
        scrnMsg.poNum.setInputProtected(false);

        // Header Button
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_COPY[0], true);
        handler.setButtonEnabled(BTN_APPRV_HIST, true);
        //QC#25024 Add Start 
        // Mod Start 2019/12/12 QC#55063
//        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        // Mod End 2019/12/12 QC#55063
        // START 2019/12/02 T.Ogura [QC#54815,MOD]
//        handler.setButtonEnabled(BTN_COMMENT, false);
        handler.setButtonEnabled(BTN_COMMENT, true);
        // END   2019/12/02 T.Ogura [QC#54815,MOD]
        //QC#25024 Add End 

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            // Footer
            // START 2020/09/15 A.Marte [QC#57698, DEL]
            // Remove enabling TranmitMethod Input since Print Button is disabled
            // scrnMsg.trsmtMethTpCd.setInputProtected(false);
            // scrnMsg.sendPoEmlAddr.setInputProtected(false);
            // scrnMsg.sendPoFaxNum.setInputProtected(false);
            // scrnMsg.rptDestId.setInputProtected(false);
            // END 2020/09/15 A.Marte [QC#57698, DEL]

            handler.setButtonEnabled(BTN_HISTORY, true);

            // Detail Item
            enableDetailTabItem(handler, scrnMsg);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            }
            if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                handler.setButtonEnabled(BTN_MOVE_TO_COMPONENT, true);
            }

        }

        // set Common Enable Button
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    /**
     * enableDetailTabItem
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    private static void enableDetailTabItem(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        // QC#55313
        boolean isSubconPr = false;
        // cusor rule
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, NPAL1500Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);
 
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Set Focus rule
            ZYPGUIFocusRule fRule = new ZYPGUIFocusRule("chkBox" + i);
            if (i > 0) {
                fRule.setPrevId(NPAL1500Bean.poOrdDtlCmntTxt_A1 + "#" + (i - 1));
                tblFocusRule.addRule(fRule);
            }

            // Left Table focus to right table focus
            fRule = new ZYPGUIFocusRule("Get_MdseInfo#" + i);
            fRule.setNextId(NPAL1500Bean.entDealNetUnitPrcAmt_A1 + "#" + i);
            tblFocusRule.addRule(fRule);

            // Left Table focus from right table focus
            fRule = new ZYPGUIFocusRule(NPAL1500Bean.entDealNetUnitPrcAmt_A1 + "#" + i);
            fRule.setPrevId("Get_MdseInfo#" + i);
            tblFocusRule.addRule(fRule);

            if (i + 1 < scrnMsg.A.getValidCount()) {
                fRule = new ZYPGUIFocusRule(NPAL1500Bean.poOrdDtlCmntTxt_A1 + "#" + i);
                fRule.setNextId("chkBox" + (i + 1));
                tblFocusRule.addRule(fRule);
            }

            scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(true);

            if (PO_STS.SAVED.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())) {
                if (PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                    // QC#24936 ADD START
                    // QC#28463 MOD START
                    //enableDetailTabItemOnCloseCancel(handler, scrnMsg, i);
                    enableDetailTabItemOnElseDrop(handler, scrnMsg, i);
                    // QC#28463 MOD END
                    // QC#24936 ADD END
                } else {
                    enableDetailTabItemOnSaved(handler, scrnMsg, i);
                }
                //QC#14858(Sol#060) START 2017/12/05
                if (PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(true);
                }
                //QC#14858(Sol#060) END 2017/12/05
            } else if (PO_STS.VALIDATED.equals(scrnMsg.A.no(i).poStsCd_HD.getValue()) //
                    || PO_STS.PO_CONFIRMED.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())//
                    || PO_STS.PO_ERROR.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())//
                    || PO_STS.RECEIVING.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())) {
                if (PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                    // QC#24936 ADD START
                    // QC#28463 MOD START
                    //enableDetailTabItemOnCloseCancel(handler, scrnMsg, i);
                    enableDetailTabItemOnElseDrop(handler, scrnMsg, i);
                    // QC#28463 MOD END
                    // QC#24936 ADD END
                } else {
                    enableDetailTabItemOnProcessing(handler, scrnMsg, i);
                }
                //QC#14858(Sol#060) START 2017/12/05
                if (PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(false);
                }
                //QC#14858(Sol#060) END 2017/12/05
            } else if (PO_STS.WAITING_FOR_APPROVAL.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())) {
                enableDetailTabItemOnWaiting(handler, scrnMsg, i);
            } else if (PO_STS.CLOSED.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())//
                    && PO_LINE_STS.OPEN_FOR_INVOICE.equals(scrnMsg.A.no(i).poLineStsCd_A1.getValue())) {
                enableDetailTabItemOnOpenforInvoice(handler, scrnMsg, i);
            } else if (PO_STS.CLOSED.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())//
                    && PO_LINE_STS.CLOSED.equals(scrnMsg.A.no(i).poLineStsCd_A1.getValue())//
                    || PO_STS.CANCELLED.equals(scrnMsg.A.no(i).poStsCd_HD.getValue())) {
                enableDetailTabItemOnCloseCancel(handler, scrnMsg, i);
            } else {
                enableItemsOnElse(handler, scrnMsg, i);
            }

            // set fraction digit
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(2);
            //QC#18420 Add Start 
            scrnMsg.A.no(i).aslUnitPrcAmt_A1.setAppFracDigit(2);
            //QC#18420 Add End
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poBalQty.setAppFracDigit(0);
            scrnMsg.A.no(i).poCancQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).poDispQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).poInvQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).poRcvQty_A1.setAppFracDigit(0);
            // 2019/10/09 QC#53392 Add Start
            scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setAppFracDigit(2);
            // 2019/10/09 QC#53392 Add End
            // add start 2022/05/16 QC#57934
            scrnMsg.A.no(i).poRcvQty_WO.setAppFracDigit(0);
            scrnMsg.A.no(i).poInvQty_WO.setAppFracDigit(0);
            // add end 2022/05/16 QC#57934
            // QC#55313
            if (DS_PO_TP.SUBCONTRACT_PO.equals(scrnMsg.dsPoTpCd.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqNum_A1)) {
                scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
                isSubconPr = true;
            }
        }

        scrnMsg.xxTotAmt_AP.setAppFracDigit(2);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).acInvJrnlCostAmt_B1.setAppFracDigit(2);
        }
        // set fraction digit
        scrnMsg.xxTotAmt.setAppFracDigit(2);
        if (isSubconPr) {
            handler.setButtonEnabled(BTN_ADD_NEW_LINE, false);
        }
    }

    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=|Open|OpenForReceipt|Cancelled
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableItemsOnElse(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // New
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnElseParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnElseChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnElseRegular(handler, scrnMsg, i);
        }
    }

    /**
     * enable Items On (PO_STS=Closed/PO_LINE_STS=Closed)| PO_STS=Cancelled
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnCloseCancel(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // detail button
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnCloseCancelParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnCloseCancelChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnCloseCancelRegular(handler, scrnMsg, i);
        }
    }
    
    // QC#28463 MOD START
    /**
     * enable Items On PO_STS=Saved
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnElseDrop(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnSavedParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnSavedChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnSavedRegular(handler, scrnMsg, i);
        }
    }
    // QC#28463 MOD End

    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=OpenForInvoice
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenforInvoice(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // detail button
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnOpenforInvoiceParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnOpenforInvoiceChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnOpenforInvoiceRegular(handler, scrnMsg, i);
        }
    }

    /**
     * enable Items On PO_STS=WaitingForApproval
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnWaiting(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // detail button
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnWaitingParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnWaitingChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnWaitingRegular(handler, scrnMsg, i);
        }
    }

    /**
     * enable Items On PO_STS=Validated|PO Confirmed|PO Error|Receiving
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnProcessing(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemsnProcessingParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnProcessingChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnProcessingRegular(handler, scrnMsg, i);
        }
    }

    /**
     * enable Items On PO_STS=Saved
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnSaved(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (PO_MDSE_CMPSN_TP.PARENT.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnSavedParent(handler, scrnMsg, i);
        } else if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_HD.getValue())) {
            enableDetailTabItemOnSavedChild(handler, scrnMsg, i);
        } else {
            enableDetailTabItemOnSavedRegular(handler, scrnMsg, i);
        }
    }

    /**
     * enable SerializedItems Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabSerializedItemCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_IB.getValue())//
                && !PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            // QC#27024. Mod QC#24413
            if ((ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poRcvQty_A1) //
                    && BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).poRcvQty_A1.getValue()) < 0) //
                    || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNumFlg_A1) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).serNumFlg_A1.getValue()))) {
                scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(false);
            }
            handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, i, true);

            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                scrnMsg.A.no(i).serNumListTxt_A1.setIndispensable(true);
            } else {
                scrnMsg.A.no(i).serNumListTxt_A1.setIndispensable(false);
            }
        }
    }

    /**
     * enable Items On PO_STS=NEW | (PO_STS=Closed/PO_LINE_STS=|Open|OpenForReceipt|Cancelled) Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnElseCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        //QC#27770 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseDescShortTxt_A1)) {
//            scrnMsg.A.no(i).poLineTpCd_A1.setInputProtected(true);
//        } else {
        scrnMsg.A.no(i).poLineTpCd_A1.setInputProtected(false);
//        }
        //QC#27770 Mod End
        scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
        //QC#14858(Sol#060) START 2017/12/05
        scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
        scrnMsg.A.no(i).aslMdseCd_A1.setInputProtected(false);        
        //QC#14858(Sol#060) END 2017/12/05
        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
        // QC#18420 Add Start
        scrnMsg.A.no(i).poDtlDiscPct_A1.setInputProtected(false);
        handler.setButtonEnabled(BTN_DETAIL_LINE_PRICE_INFO, i, true);
        // QC#18420 Add End
        // QC#53392 2019/10/03 Add Start
        scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setInputProtected(false);
        // QC#53392 2019/10/03 Add End
        scrnMsg.A.no(i).poDispQty_A1.setInputProtected(false);
        scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(false);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).rqstShipDt_A1.setInputProtected(false);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_CH.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_AC.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_AC.setInputProtected(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_VA.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_VA.setInputProtected(false);
        }
        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(false);
            scrnMsg.stkStsCd_CD.setInputProtected(false);
            scrnMsg.stkStsDescTxt_NM.setInputProtected(false);

            // detail button
            handler.setButtonEnabled(BTN_DETAIL_SRC_RSWH, i, true);
        }
        scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);

        // detail button
        handler.setButtonEnabled(BTN_DETAIL_MDSE_INFO, i, true);
        handler.setButtonEnabled(BTN_DETAIL_MDSE_POPUP, i, true);
        handler.setButtonEnabled(BTN_DETAIL_DEST_RSWH, i, true);
        //QC#14858(Sol#060) START
        if(PO_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue()) //
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())){ 
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(false);
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, true);
        }else{
            scrnMsg.A.no(i).xxScrItem130Txt_CH.clear();
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(true);
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, false);
        }
        //QC#14858(Sol#060) END
        handler.setButtonEnabled(BTN_DETAIL_ACCT_ACRL, i, true);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_VAR, i, true);
    }

    /**
     * enable Items On PO_STS=NEW | (PO_STS=Closed/PO_LINE_STS=|Open|OpenForReceipt|Cancelled)/PO_MDSE_CMPSN_TP_CD=Regular | null
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnElseRegular(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // Reqular or null
        enableDetailTabItemOnElseCommon(handler, scrnMsg, i);
        enableDetailTabSerializedItemCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=NEW | (PO_STS=Closed/PO_LINE_STS=|Open|OpenForReceipt|Cancelled)/PO_MDSE_CMPSN_TP_CD=Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnElseChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // QC#15979 Mod Start.
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            enableDetailTabItemOnOpenChild(handler, scrnMsg, i);
//            enableDetailTabItemOnElseCommon(handler, scrnMsg, i);
            // QC#27024
            if ((ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poRcvQty_A1) //
                    && BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).poRcvQty_A1.getValue()) < 0) //
                    || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNumFlg_A1) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).serNumFlg_A1.getValue()))) {
                scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(true);
            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rcvSerTakeFlg_IB) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_IB.getValue())){
                scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(false);
                handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, i, true);
            } else {
                scrnMsg.A.no(i).serNumListTxt_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, i, false);
            }
//        } else {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//        }
         // QC#15979 Mod End.
        // Serialized Item
        enableDetailTabSerializedItemCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=NEW | (PO_STS=Closed/PO_LINE_STS=|Open|OpenForReceipt|Cancelled)/PO_MDSE_CMPSN_TP_CD=Parent
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnElseParent(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);
        } else {
            enableDetailTabItemOnElseCommon(handler, scrnMsg, i);
        }
        enableDetailTabSerializedItemCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On (PO_STS=Closed/PO_LINE_STS=Closed)| PO_STS=Cancelled Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnCloseCancelCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);

        // Serialized Item
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_IB.getValue())) {

            handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, i, true);
        }
    }

    /**
     * enable Items On (PO_STS=Closed/PO_LINE_STS=Closed)| PO_STS=Cancelled /PO_MDSE_CMPSN_TP_CD=Regular | null
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnCloseCancelRegular(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // Reqular or null
        enableDetailTabItemOnCloseCancelCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On (PO_STS=Closed/PO_LINE_STS=Closed)| PO_STS=Cancelled /PO_MDSE_CMPSN_TP_CD=Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnCloseCancelChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            enableDetailTabItemOnCloseCancelCommon(handler, scrnMsg, i);

        } else {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        }
    }

    /**
     * enable Items On (PO_STS=Closed/PO_LINE_STS=Closed)| PO_STS=Cancelled /PO_MDSE_CMPSN_TP_CD=Parent
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnCloseCancelParent(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        } else {
            enableDetailTabItemOnCloseCancelCommon(handler, scrnMsg, i);

        }
    }

    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=OpenForInvoice Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenforInvoiceCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);

        // QC#18420 Add Start
        // QC#28463 Del Start
        //if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
        // QC#28463 Del End
            //QC#18442(Sol#103) START
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
            //QC#18442(Sol#103) END
            scrnMsg.A.no(i).poDtlDiscPct_A1.setInputProtected(false);
            handler.setButtonEnabled(BTN_DETAIL_LINE_PRICE_INFO, i, true);
            // QC#53392 2019/10/03 Add Start
            scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setInputProtected(false);
            // QC#53392 2019/10/03 Add End
            // QC#25024 Add Start
            scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);
            // QC#25024 Add End
        //}
        // QC#18420 Add End

        // detail button
        //QC#14858(Sol#060) START
        if(PO_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue()) //
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())){ 
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, true);
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(false);
        }else{
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, false);
        }
        //QC#14858(Sol#060) END

        // Serialized Item
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_IB.getValue())) {

            handler.setButtonEnabled(BTN_DETAIL_SERIAL_POPUP, i, true);
        }
    }
    
    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=OpenForInvoice Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenforInvoiceForChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);
    }

    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=OpenForInvoice/PO_MDSE_CMPSN_TP_CD=Regular | null
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenforInvoiceRegular(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // Reqular or null
        enableDetailTabItemOnOpenforInvoiceCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=OpenForInvoice/PO_MDSE_CMPSN_TP_CD=Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenforInvoiceChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            enableDetailTabItemOnOpenforInvoiceForChild(handler, scrnMsg, i);
        } else {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        }
    }

    /**
     * enable Items On PO_STS=Closed/PO_LINE_STS=OpenForInvoice/PO_MDSE_CMPSN_TP_CD=Parent
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenforInvoiceParent(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // QC#25024 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//        } else {
        // QC#25024 Mod Start
            enableDetailTabItemOnOpenforInvoiceCommon(handler, scrnMsg, i);
        //}
    }

    /**
     * enable Items On PO_STS=WaitingForApproval Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnWaitingCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
    }

    /**
     * enable Items On PO_STS=WaitingForApproval /PO_MDSE_CMPSN_TP_CD=Regular | null
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnWaitingRegular(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // Reqular or null
        enableDetailTabItemOnWaitingCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=WaitingForApproval /PO_MDSE_CMPSN_TP_CD=Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnWaitingChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            enableDetailTabItemOnWaitingCommon(handler, scrnMsg, i);
        } else {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        }
    }

    /**
     * enable Items On PO_STS=WaitingForApproval /PO_MDSE_CMPSN_TP_CD=Parent
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnWaitingParent(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        } else {
            enableDetailTabItemOnWaitingCommon(handler, scrnMsg, i);
        }
    }

    /**
     * enable Items On PO_STS=Validated|PO Confirmed|PO Error|Receiving Common Logic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnProcessingCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
        scrnMsg.A.no(i).poDispQty_A1.setInputProtected(false);
        scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);
        // QC#25024 Add Start
        scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(false);
        // QC#25024 Add End
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).rqstShipDt_A1.setInputProtected(false);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(true);
        scrnMsg.A.no(i).xxScrItem130Txt_AC.setInputProtected(true);
        scrnMsg.A.no(i).xxScrItem130Txt_VA.setInputProtected(true);
        // QC#18420 Add Start
        scrnMsg.A.no(i).poDtlDiscPct_A1.setInputProtected(false);
        handler.setButtonEnabled(BTN_DETAIL_LINE_PRICE_INFO, i, true);
        // QC#18420 Add End
        // QC#53392 2019/10/03 Add Start
        scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setInputProtected(false);
        // QC#53392 2019/10/03 Add End
        // detail button
        //QC#14858(Sol#060) START
        scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(true);
        if(PO_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue()) //
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())){ 
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, true);
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(false);
        }else{
        handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, false);
        }
        //QC#14858(Sol#060) END

        // QC#25024 Add Start
        if (MDSE_ITEM_TP.TEXT_ITEM.equals((scrnMsg.A.no(i).mdseItemTpCd_A1.getValue()))) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).aslMdseCd_A1.setInputProtected(false);
        }
        // QC#25024 Add End

        handler.setButtonEnabled(BTN_DETAIL_ACCT_ACRL, i, true);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_VAR, i, true);

        enableDetailTabSerializedItemCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=Validated|PO Confirmed|PO Error|Receiving /PO_MDSE_CMPSN_TP_CD=Regular | null
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnProcessingRegular(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // Reqular or null
        enableDetailTabItemOnProcessingCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=Validated|PO Confirmed|PO Error|Receiving /PO_MDSE_CMPSN_TP_CD=Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnProcessingChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // QC#15979 Mod Start.
        enableDetailTabItemOnOpenChild(handler, scrnMsg, i);
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
//            enableDetailTabItemOnProcessingCommon(handler, scrnMsg, i);
//        } else {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//        }
        // QC#15979 Mod End.
    }

    /**
     * enable Items On PO_STS=Validated|PO Confirmed|PO Error|Receiving /PO_MDSE_CMPSN_TP_CD=Parent
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemsnProcessingParent(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        //QC#25024 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//        } else {
            enableDetailTabItemOnProcessingCommon(handler, scrnMsg, i);
//        }
        //QC#25024 Mod End
    }

    /**
     * enable Items On PO_STS=Saved CommonLogic
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnSavedCommon(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseDescShortTxt_A1)) {
            scrnMsg.A.no(i).poLineTpCd_A1.setInputProtected(true);
        } else {
            scrnMsg.A.no(i).poLineTpCd_A1.setInputProtected(false);
        }
        //QC#14858(Sol#060) START 2017/12/05
        scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
        scrnMsg.A.no(i).aslMdseCd_A1.setInputProtected(false);        
        //QC#14858(Sol#060) END 2017/12/05
        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
        // QC#18420 Add Start
        scrnMsg.A.no(i).poDtlDiscPct_A1.setInputProtected(false);
        handler.setButtonEnabled(BTN_DETAIL_LINE_PRICE_INFO, i, true);
        // QC#18420 Add End
        // QC#53392 2019/10/03 Add Start
        scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setInputProtected(false);
        // QC#53392 2019/10/03 Add End
        scrnMsg.A.no(i).poDispQty_A1.setInputProtected(false);
        scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(false);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).rqstShipDt_A1.setInputProtected(false);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(false);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_CH.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_AC.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_AC.setInputProtected(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_VA.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_VA.setInputProtected(false);
        }
        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
            scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(false);
            scrnMsg.stkStsCd_CD.setInputProtected(false);
            scrnMsg.stkStsDescTxt_NM.setInputProtected(false);

            // detail button
            handler.setButtonEnabled(BTN_DETAIL_SRC_RSWH, i, true);
        }
        scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);

        // detail button
        handler.setButtonEnabled(BTN_DETAIL_DEST_RSWH, i, true);
        //QC#14858(Sol#060) START 2017/12/05
        if(PO_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue()) //
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())){ 
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, true);
        }else{
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, false);
        }
        //QC#14858(Sol#060) END 2017/12/05
        handler.setButtonEnabled(BTN_DETAIL_ACCT_ACRL, i, true);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_VAR, i, true);
        
        //QC#28463 Add Start
        if (PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).aslMdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
            handler.setButtonEnabled(BTN_DETAIL_DEST_RSWH, i, false);
        }
        //QC#28463 Add End

        enableDetailTabSerializedItemCommon(handler, scrnMsg, i);
    }

    /** QC#15979 Add.
     * enable Items On CommonLogic Open Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnOpenChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        scrnMsg.A.no(i).poLineTpCd_A1.setInputProtected(true);
        scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
        // QC#18420 Add Start
        scrnMsg.A.no(i).poDtlDiscPct_A1.setInputProtected(true);
        handler.setButtonEnabled(BTN_DETAIL_LINE_PRICE_INFO, i, false);
        // QC#18420 Add End
        // QC#53392 2019/10/03 Add Start
        scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setInputProtected(true);
        // QC#53392 2019/10/03 Add End
        scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
        scrnMsg.A.no(i).rqstRcvDt_A1.setInputProtected(true);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).rqstShipDt_A1.setInputProtected(true);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.A.no(i).destRtlSwhCd_A1.setInputProtected(true);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_CH.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_CH.setInputProtected(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_AC.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_AC.setInputProtected(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_VA.getValue())) {
            scrnMsg.A.no(i).xxScrItem130Txt_VA.setInputProtected(false);
        }
        if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).stkStsCd_A1.setInputProtected(true);
            scrnMsg.stkStsCd_CD.setInputProtected(true);
            scrnMsg.stkStsDescTxt_NM.setInputProtected(true);

            // detail button
            handler.setButtonEnabled(BTN_DETAIL_SRC_RSWH, i, true);
        }
        scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(false);

        // detail button
        handler.setButtonEnabled(BTN_DETAIL_DEST_RSWH, i, false);
        //QC#14858(Sol#060) START
        scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(true);
        if(PO_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue()) //
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(scrnMsg.A.no(i).poLineTpCd_A1.getValue())){ 
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, true);
        }else{
            handler.setButtonEnabled(BTN_DETAIL_ACCT_CHRG, i, false);
        }
        //QC#14858(Sol#060) END
        handler.setButtonEnabled(BTN_DETAIL_ACCT_ACRL, i, true);
        handler.setButtonEnabled(BTN_DETAIL_ACCT_VAR, i, true);

        enableDetailTabSerializedItemCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=Saved/PO_MDSE_CMPSN_TP_CD=Regular |null
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnSavedRegular(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // Reqular or null
        enableDetailTabItemOnSavedCommon(handler, scrnMsg, i);
    }

    /**
     * enable Items On PO_STS=Saved/PO_MDSE_CMPSN_TP_CD=Child
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnSavedChild(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
        // QC#15979 Mod Start.
        enableDetailTabItemOnOpenChild(handler, scrnMsg, i);
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
//            enableDetailTabItemOnSavedCommon(handler, scrnMsg, i);
//        } else {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//        }
        // QC#15979 Mod End.
    }

    /**
     * enable Items On PO_STS=Saved/PO_MDSE_CMPSN_TP_CD=Parent
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     * @param i int
     */
    private static void enableDetailTabItemOnSavedParent(EZDCommonHandler handler, NPAL1500BMsg scrnMsg, int i) {
    // QC#28463 Del START
//        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poSendTs)) {
//            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//        } else {
            enableDetailTabItemOnSavedCommon(handler, scrnMsg, i);
//        }
    // QC#28463 Del End
    }

    /**
     * chkInquiryRole
     * @param functionList List<String>
     * @return true:Update false:Inquiry
     */
    public static boolean chkInquiryRole(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_ID_SUBMIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * #69 Move Error Tab.
     * @param scrnMsg NPAL1500BMsg
     */
    public static void moveErrorTab(NPAL1500BMsg scrnMsg) {
        if (checkErrorItemDetailTab(scrnMsg.A)) {
            scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        } else if (checkErrorItemAddlHeaderTab(scrnMsg)) {
            scrnMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
        }
    }

    /**
     * #69 check ErrorItemDetailTab.
     * @param NPAL1500_ABMsgArray
     */
    private static boolean checkErrorItemDetailTab(NPAL1500_ABMsgArray inParamItemArray) {
        boolean isError = false;
        for (int i = 0; i < inParamItemArray.getValidCount(); i++) {
            isError = checkErrorItem(inParamItemArray.no(i).xxChkBox_A1//
                    , inParamItemArray.no(i).poLineTpCd_A1//
                    , inParamItemArray.no(i).mdseCd_A1//
                    , inParamItemArray.no(i).entDealNetUnitPrcAmt_A1//
                    , inParamItemArray.no(i).poDispQty_A1//
                    //QC#14858(Sol#060) START
                    , inParamItemArray.no(i).poRcvQty_A1//
                    //QC#14858(Sol#060) END
                    , inParamItemArray.no(i).rqstRcvDt_A1//
                    // START 2023/02/15 S.Dong [QC#60966, ADD]
                    , inParamItemArray.no(i).rqstShipDt_A1//
                    // END 2023/02/15 S.Dong [QC#60966, ADD]
                    , inParamItemArray.no(i).destRtlSwhCd_A1//
                    , inParamItemArray.no(i).xxScrItem130Txt_AC);
            if (isError) {
                break;
            }
        }
        return isError;
    }

    /**
     * #69 check ErrorItemShipToTab.
     * @param NPAL1500BMsg
     */
    private static boolean checkErrorItemAddlHeaderTab(NPAL1500BMsg scrnMsg) {
        return checkErrorItem(scrnMsg.xxAllLineAddr_ST//
                , scrnMsg.shipToPostCd_ST//
                , scrnMsg.shipToCtyAddr_ST//
                , scrnMsg.shipToCtryCd_ST);
    }

    /**
     * #69
     * 
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
     * Set table's back color
     * @param scrnMsg NPAL1500BMsg
     */
    public static final void setTblBackColor(NPAL1500BMsg scrnMsg) {

        String tabName = scrnMsg.xxDplyTab.getValue();

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        if (TAB_DETAIL.equals(tabName)) {

            // mod start 2022/05/16 QC#57934
            //tblColor.setAlternateRowsBG(TBL_ID_LEFT_A, scrnMsg.A);
            //tblColor.setAlternateRowsBG(TBL_ID_RIGHT_A, scrnMsg.A);
            tblColor.setAlternateRowsBG(TBL_ID_A, scrnMsg.A);
            // mod end 2022/05/16 QC#57934
            tblColor.clearRowsBG(TBL_ID_LEFT_B, scrnMsg.B);

        } else if (TAB_ADDL_HEADER.equals(tabName)) {

            // mod start 2022/05/16 QC#57934
            //tblColor.clearRowsBG(TBL_ID_LEFT_A, scrnMsg.A);
            //tblColor.clearRowsBG(TBL_ID_RIGHT_A, scrnMsg.A);
            tblColor.clearRowsBG(TBL_ID_A, scrnMsg.A);
            // mod end 2022/05/16 QC#57934
            tblColor.clearRowsBG(TBL_ID_LEFT_B, scrnMsg.B);

        } else if (TAB_AP_INVOICE.equals(tabName)) {

            // mod start 2022/05/16 QC#57934
            //tblColor.clearRowsBG(TBL_ID_LEFT_A, scrnMsg.A);
            //tblColor.clearRowsBG(TBL_ID_RIGHT_A, scrnMsg.A);
            tblColor.clearRowsBG(TBL_ID_A, scrnMsg.A);
            // mod end 2022/05/16 QC#57934
            tblColor.setAlternateRowsBG(TBL_ID_LEFT_B, scrnMsg.B);

        }
    }

    /**
     * inputCheckForCancel
     * @param scrnMsg NPAL1500BMsg
     */
    public static void inputCheckForCancel(NPAL1500BMsg scrnMsg) {

        boolean chkFlg = false;
        boolean chkSubmittedFlg = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                chkFlg = true;
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poLineTpCd_A1)) {
                    chkSubmittedFlg = true;
                }
            }
        }

        if (!chkFlg) {
            scrnMsg.setMessageInfo(NPAM0049E);
        } else {
            checkAdditionalLineIsSaved(scrnMsg, chkSubmittedFlg);
        }
    }

    /**
     * check AdditionalLine is Saved. if Additional Line's PO_LINE_STS_CD is null,then set MessageInfo(NPAM1326E).
     * @param scrnMsg NPAL1500BMsg
     * @param chkSubmittedFlg boolean
     */
    private static void checkAdditionalLineIsSaved(NPAL1500BMsg scrnMsg, boolean chkSubmittedFlg) {
        if (chkSubmittedFlg && ZYPCommonFunc.hasValue(scrnMsg.poHdrStsCd) && !scrnMsg.poHdrStsCd.getValue().equals(PO_STS.SAVED)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poLineStsCd_A1)) {
                    scrnMsg.setMessageInfo(NPAM1326E);
                }
            }
        }
    }

    /**
     * <pre>
     * addCheckHeaderItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NPAL1500BMsg scrnMsg) {
        addCheckHeaderItem(scrnMsg);

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            addCheckDetailItem(scrnMsg);
        }
        if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            addCheckAddlHeaderItem(scrnMsg);
        }
    }

    /**
     * <pre>
     * addCheckHeaderItem for submit
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemForSubmit(NPAL1500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.dsPoTpCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.prchGrpCd);
        scrnMsg.addCheckItem(scrnMsg.poOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);

        if (TAB_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {
            addCheckDetailItem(scrnMsg);
        }
        if (TAB_ADDL_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            addCheckAddlHeaderItem(scrnMsg);
        }
    }

    /**
     * <pre>
     * addCheckHeaderItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckHeaderItem(NPAL1500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.dsPoTpCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.prchGrpCd);
        scrnMsg.addCheckItem(scrnMsg.poOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
        // START 2017/11/08 [QC#21849, ADD]
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        // END   2017/11/08 [QC#21849, ADD]
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
    }

    /**
     * <pre>
     * addCheckDetailItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckDetailItem(NPAL1500BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poLineTpCd_A1);
            // START 2017/12/04 [QC#14858, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poRcvQty_A1);
            // END 2017/12/04 [QC#14858, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poDispQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
            // START 2023/02/15 S.Dong [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstShipDt_A1);
            // END 2023/02/15 S.Dong [QC#60966, ADD]
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).destRtlSwhCd_A1);
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poOrdDtlCmntTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNumListTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).srcRtlSwhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_CH);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_AC);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_VA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).entPoDtlDealNetAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).stkStsCd_A1);
            // 2019/10/09 QC#53392 Add Start
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poDtlDiscPrcAmt_A1);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poDtlDiscPrcAmt_A1)) {
            	if(BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.getValue()) > 0){
                	scrnMsg.A.no(i).poDtlDiscPrcAmt_A1.setErrorInfo(1, NPAM0023E);
                }
            }
            // 2019/10/09 QC#53392 Add Start

            // QC#15817 Mod.
//            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem130Txt_AC) &&  (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_AC.getValue()))) {
//                scrnMsg.A.no(i).xxScrItem130Txt_AC.setErrorInfo(1, NPAM1329E, new String[] {"Accrual ACC", "Please click 'Acct' button." });
//            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem130Txt_CH) &&  (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_CH.getValue()))) {
                scrnMsg.A.no(i).xxScrItem130Txt_CH.setErrorInfo(1, NPAM1329E, new String[] {"Change ACC", "Please click 'Acct' button." });
            }
//            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem130Txt_VA) &&  (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).coaEnblFlg_VA.getValue()))) {
//                scrnMsg.A.no(i).xxScrItem130Txt_VA.setErrorInfo(1, NPAM1329E, new String[] {"Variance ACC", "Please click 'Acct' button." });
//            }
            
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poDtlDiscPct_A1)) {
                int valLen = String.valueOf(scrnMsg.A.no(i).poDtlDiscPct_A1.getValue()).length();    
                // Discount : over 100
                if (PCT_100.compareTo(scrnMsg.A.no(i).poDtlDiscPct_A1.getValue()) <= 0) {
                    scrnMsg.A.no(i).poDtlDiscPct_A1.setErrorInfo(1, NPAM0023E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).poDtlDiscPct_A1);
                // Discount : under 0
                }else if(BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).poDtlDiscPct_A1.getValue()) > 0){
                    scrnMsg.A.no(i).poDtlDiscPct_A1.setErrorInfo(1, NPAM0023E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).poDtlDiscPct_A1);
                // Discount : check disit
                }else if(valLen > 5){
                    scrnMsg.A.no(i).poDtlDiscPct_A1.setErrorInfo(1, NPAM0023E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).poDtlDiscPct_A1);
                }
            }
        }
    }

    /**
     * <pre>
     * addCheckHeaderItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckAddlHeaderItem(NPAL1500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.shipToAddlLocNm_ST);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_ST);
        scrnMsg.addCheckItem(scrnMsg.shipToPostCd_ST);
        scrnMsg.addCheckItem(scrnMsg.shipToCtyAddr_ST);
        scrnMsg.addCheckItem(scrnMsg.shipToCntyNm_ST);
        scrnMsg.addCheckItem(scrnMsg.shipToStCd_ST);
        scrnMsg.addCheckItem(scrnMsg.shipToProvNm_ST);
        scrnMsg.addCheckItem(scrnMsg.shipToCtryCd_ST);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.carrAcctNum);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_SI);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_RN);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt_SN);
        // START 2017/11/08 [QC#21849, DEL]
        // scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        // END   2017/11/08 [QC#21849, DEL]
        // QC#23726 Add.
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd);
    }

    /**
     * checkQtyEditStatus
     * @param scrnMsg NPAL1500BMsg
     * @param poStsCd String
     * @return boolean
     */
    public static boolean checkQtyEditStatus(NPAL1500BMsg scrnMsg, String poStsCd) {
        if (!ZYPCommonFunc.hasValue(poStsCd) || PO_STS.SAVED.equals(poStsCd)) {
            return true;
        } else if (PO_STS.VALIDATED.equals(poStsCd)) {
            return true;
        } else if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
            return true;
        } else if (PO_STS.SUBMITTED.equals(poStsCd)) {
            return true;
        } else if (PO_STS.RECEIVING.equals(poStsCd)) {
            return true;
        } else if (PO_STS.RECEIVING_COMPLETION.equals(poStsCd)) {
            return true;
        } else if (PO_STS.PO_CONFIRMED.equals(poStsCd)) {
            return true;
        } else if (PO_STS.PO_ERROR.equals(poStsCd)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return true if userId have update authority.
     * @param userId String
     * @return boolean
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_ID_SUBMIT })) {
            return true;
        }
        return false;
    }

    /**
     * Return true if userId have update authority.
     * @param userId String
     * @return boolean
     */
    public static final boolean hasApproveAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_APPROVE })) {
            return true;
        }
        return false;
    }

    /**
     * Add check item. Item are poQty_A and poOrdDtlCmntTxt_A.
     * @param scrnMsg NPAL1500BMsg
     * @param detailMsg NPAL1500_ABMsg
     */
    public static final void addChekItemDetail(NPAL1500BMsg scrnMsg, NPAL1500_ABMsg detailMsg) {
        scrnMsg.addCheckItem(detailMsg.vndInvtyLocCd_A1);
        // scrnMsg.addCheckItem(detailMsg.shipToCustCd_A);
        // scrnMsg.addCheckItem(detailMsg.poQty_A);
        scrnMsg.addCheckItem(detailMsg.poOrdDtlCmntTxt_A1);
        // scrnMsg.addCheckItem(detailMsg.thisMthFobCostAmt_A);
    }

    /**
     * Return true if value is greater then 0.
     * @param value BigDecimal
     * @param nullFlg In case of value is null, this value is return
     * @param containEqual boolean
     * @return boolean
     */
    public static final boolean isGreaterThanZero(BigDecimal value, boolean nullFlg, boolean containEqual) {

        if (!ZYPCommonFunc.hasValue(value)) {
            return nullFlg;
        }
        if (!containEqual && BigDecimal.ZERO.compareTo(value) < 0) {
            return true;
        } else if (containEqual && BigDecimal.ZERO.compareTo(value) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return true if value is 0.
     * @param value BigDecimal
     * @param nullFlg In case of value is null, this value is return
     * @return boolean
     */
    public static final boolean isZero(BigDecimal value, boolean nullFlg) {

        if (!ZYPCommonFunc.hasValue(value)) {
            return nullFlg;
        } else if (BigDecimal.ZERO.compareTo(value) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * Initial Common Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
    }

    /**
     * for cancel close
     * @param bizMsg NPAL1500CMsg
     */
    public static void inputSelectedCheckBox(NPAL1500CMsg bizMsg) {

        // Set
        boolean checkFlg = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                checkFlg = true;
            }
        }

        if (!checkFlg) {
            bizMsg.setMessageInfo("NPAM0049E");
        }

    }

    /**
     * Get Param NPAL1500 For PO Type
     * @param scrnMsg NPAL1500BMsg
     * @return Param NPAL1500 For PO Type
     */
    public static Object[] getParamNPAL1500ForPoType(NPAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "PO Type Search";
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DPT.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,DPT.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,DPT.DS_PO_TP_CD       AS DS_PO_TP_CD");
        sb.append("   ,DPT.DS_PO_TP_DESC_TXT AS DS_PO_TP_DESC_TXT");
        sb.append("FROM");
        sb.append("    DS_PO_TP DPT ");
        sb.append("WHERE");
        sb.append("    DPT.EZCANCELFLAG  = '0'");
        sb.append("    AND DPT.GLBL_CMPY_CD  = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DPT.DS_PO_TP_DPLY_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
        params[IDX_2] = sb.toString();

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Po Type Code";
        columnArray0[IDX_1] = "DS_PO_TP_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Po Type Name";
        columnArray1[IDX_1] = "DS_PO_TP_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_3] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_PO_TP_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_4] = sortConditionList;

        // ZYPTableUtil.clear(scrnMsg.Z);
        // params[IDX_5] = scrnMsg.Z;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     */
    public static void setInitParamForConfigPopup(NPAL1500BMsg scrnMsg) {

        // in
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        ZYPTableUtil.clear(scrnMsg.R);
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();

        // out
        scrnMsg.xxPopPrm_PO.clear();
        scrnMsg.xxPopPrm_PP.clear();
        scrnMsg.xxPopPrm_PQ.clear();
        scrnMsg.xxPopPrm_PR.clear();
        scrnMsg.svcConfigMstrPk_P0.clear();
        scrnMsg.xxPopPrm_PT.clear();
        ZYPTableUtil.clear(scrnMsg.S);

        // Location Parameters are set to subscreen delivery
        // information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, "02");
        if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.svcConfigMstrPk.getValue().toPlainString());
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(0).svcMachMstrStsCd_I, "CRAT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(1).svcMachMstrStsCd_I, "RMV");
        scrnMsg.R.setValidCount(IDX_2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, "03");
        if (ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd_SC)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, scrnMsg.srcRtlWhCd_SC);
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd_HD)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, scrnMsg.srcRtlSwhCd_HD);
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @return Object[]
     */
    public static Object[] setParamForConfigPopup(NPAL1500BMsg scrnMsg) {

        Object[] params = new Object[32];

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.R;
        params[10] = scrnMsg.xxPopPrm_P9;
        params[11] = scrnMsg.xxPopPrm_PA;
        params[12] = scrnMsg.xxPopPrm_PB;
        params[13] = scrnMsg.xxPopPrm_PC;
        params[14] = scrnMsg.xxPopPrm_PD;
        params[15] = scrnMsg.xxPopPrm_PE;
        params[16] = scrnMsg.xxPopPrm_PF;
        params[17] = scrnMsg.xxPopPrm_PG;
        params[18] = scrnMsg.xxPopPrm_PH;
        params[19] = scrnMsg.xxPopPrm_PI;
        params[20] = scrnMsg.xxPopPrm_PJ;
        params[21] = scrnMsg.xxPopPrm_PK;
        params[22] = scrnMsg.xxPopPrm_PL;
        params[23] = scrnMsg.xxPopPrm_PM;
        params[24] = scrnMsg.xxPopPrm_PN;
        params[25] = scrnMsg.xxPopPrm_PO;
        params[26] = scrnMsg.xxPopPrm_PP;
        params[27] = scrnMsg.xxPopPrm_PQ;
        params[28] = scrnMsg.xxPopPrm_PR;
        params[29] = scrnMsg.svcConfigMstrPk_P0;
        params[30] = scrnMsg.xxPopPrm_PT;
        params[31] = scrnMsg.S;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @return Object[]
     */
    public static Object[] setParamForNMAL6050Popup(NPAL1500BMsg scrnMsg) {

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup. Mod QC#27024
     * @param scrnMsg NPAL1500BMsg
     * @param idx int
     * @return Object[]
     */
    public static Object[] setParamForSeialNumEntryPopup(NPAL1500BMsg scrnMsg, NPAL1500CMsg bizMsg, int idx) {

        Object[] params = new Object[12];

        // P0(suffix) is no use
        params[0] = "T";

        // P1(header number)
        params[1] = scrnMsg.poNum;

        // P2(mdse code) is no use
        params[2] = null;

        // P3(mdse name) is no use
        params[3] = null;

        // P4(qty)
        params[4] = scrnMsg.A.no(idx).poDispQty_A1;

        // P5(mode) from line status
        if (scrnMsg.A.no(idx).poStsCd_HD.getValue().equals(PO_STS.SAVED) //
                || scrnMsg.A.no(idx).poStsCd_HD.getValue().equals(PO_STS.VALIDATED) //
                // QC#25024 Add Start
                || scrnMsg.A.no(idx).poStsCd_HD.getValue().equals(PO_STS.PO_CONFIRMED) //
                || scrnMsg.A.no(idx).poStsCd_HD.getValue().equals(PO_STS.PO_ERROR) //
                || scrnMsg.A.no(idx).poStsCd_HD.getValue().equals(PO_STS.RECEIVING) //
                // QC#25024 Add End
                || !ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).poStsCd_HD)) {
            params[5] = "1";
        } else {
            params[5] = "2";
        }

        // P6(transaction line number) is no use
        params[6] = null;

        // P7(transaction line sub number) is no use
        params[7] = null;

        // P8(serial number list)
        ZYPTableUtil.clear(scrnMsg.T);
        // Manager Code is set to subscreen delivery information.
        int serNumArrayLength = scrnMsg.A.no(idx).poDispQty_A1.getValueInt();
        String[] serNumTxt = scrnMsg.A.no(idx).serNumListTxt_A1.getValue().split(",");

        for (int i = 0; i < serNumArrayLength; i++) {
            String serNumber = "";
            if (serNumTxt.length > i) {
                serNumber = serNumTxt[i];
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxHdrNum_T, scrnMsg.poNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxDplyTrxNumTxt_T, scrnMsg.A.no(idx).poOrdDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).mdseCd_T, scrnMsg.A.no(idx).mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).serNum_T, serNumber);
            if (ZYPCommonFunc.hasValue(bizMsg.T.no(i).xxEdtModeFlg_T)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxEdtModeFlg_T, bizMsg.T.no(i).xxEdtModeFlg_T);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).xxEdtModeFlg_T, ZYPConstant.FLG_ON_Y);
            }
            // xxTrxRefPk is no use
            scrnMsg.T.no(i).xxTrxRefPk_T.clear();
            // xxRqstTpCd is no use
            scrnMsg.T.no(i).xxRqstTpCd_T.clear();
            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.A.no(idx).poLineTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).rtlSwhCd_T, scrnMsg.A.no(idx).srcRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).invtyLocCd_T, scrnMsg.srcRtlWhCd_SC.getValue().concat(scrnMsg.A.no(idx).srcRtlSwhCd_A1.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).rtlSwhCd_T, scrnMsg.A.no(idx).destRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).invtyLocCd_T, scrnMsg.destRtlWhCd_DS.getValue().concat(scrnMsg.A.no(idx).destRtlSwhCd_A1.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).svcConfigMstrPk_T, scrnMsg.A.no(idx).svcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(i).serNumTakeFlg_T, ZYPConstant.FLG_OFF_N);
        }
        scrnMsg.T.setValidCount(serNumArrayLength);

        params[8] = scrnMsg.T;

        // P9(retail warehouse code)
        // P10(retail warehouse name)
        // P11(inbound outbound code)
        if (scrnMsg.A.no(idx).poLineTpCd_A1.getValue().equals(DS_PO_TP.BUYBACK_PO)) {
            params[9] = scrnMsg.srcRtlWhCd_SC;
            params[10] = scrnMsg.rtlWhNm_SC;
            params[11] = INBD_OTBD.OUTBOUND;
        } else {
            params[9] = scrnMsg.destRtlWhCd_DS;
            params[10] = scrnMsg.rtlWhNm_DS;
            params[11] = INBD_OTBD.INBOUND;
        }

        return params;
    }

    /**
     * Set TextEntry Popup param
     * @param scrnMsg NPAL1500BMsg
     * @return PO Text Entry Popup Param (NPAL0170) Object[]
     */
    public static Object[] setParamForTextEntryPopup(NPAL1500BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.poNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, PO_MSG_TP.INTERNAL_PO_MESSAGE);

        Object[] params = new Object[7];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.Q;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @param idx Integer
     */
    public static void setInitParamForMdsePopup(NPAL1500BMsg scrnMsg, int idx) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        // Manager Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.A.no(idx).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, "10");
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @return Object[]
     */
    public static Object[] setParamForMdsePopup(NPAL1500BMsg scrnMsg) {

        Object[] params = new Object[10];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @param idx Integer
     */
    public static void setInitParamForAcctChrgPopup(NPAL1500BMsg scrnMsg, int idx) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        // QC#13985 2016/09/09 Add Start
        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblNm_P1.setValue(SCR_NM_ACCOUNT_TYPE_CH);
        // QC#13985 2016/09/09 Add End

        // Manager Code is set to subscreen delivery information.
        // QC#13985 2016/09/09 Mod Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, "NPAL1500" + "01" + scrnMsg.A.no(idx).poLineTpCd_A1.getValue());
        // QC#13985 2016/09/09 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.A.no(idx).splyCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.A.no(idx).splyCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(idx).splyCoaBrCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.A.no(idx).splyCoaCcCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(idx).splyCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(idx).splyCoaProdCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(idx).splyCoaChCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(idx).splyCoaProjCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(idx).splyCoaExtnCd);
    }
// QC#15817 Delete.
//    /**
//     * The method explanation: set initial parameter to call popup.
//     * @param scrnMsg NPAL1500BMsg
//     * @param idx Integer
//     */
//    public static void setInitParamForAcctAcrlPopup(NPAL1500BMsg scrnMsg, int idx) {
//
//        scrnMsg.xxPopPrm_P0.clear();
//        scrnMsg.xxPopPrm_P1.clear();
//        scrnMsg.xxPopPrm_P2.clear();
//        scrnMsg.xxPopPrm_P3.clear();
//        scrnMsg.xxPopPrm_P4.clear();
//        scrnMsg.xxPopPrm_P5.clear();
//        scrnMsg.xxPopPrm_P6.clear();
//        scrnMsg.xxPopPrm_P7.clear();
//        scrnMsg.xxPopPrm_P8.clear();
//        scrnMsg.xxPopPrm_P9.clear();
//
//        // QC#13985 2016/09/09 Add Start
//        scrnMsg.xxTblNm_P1.clear();
//        scrnMsg.xxTblNm_P1.setValue(SCR_NM_ACCOUNT_TYPE_AC);
//        // QC#13985 2016/09/09 Add End
//
//        // Manager Code is set to subscreen delivery information.
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, "NPAL1500" + "02" + scrnMsg.A.no(idx).poLineTpCd_A1.getValue());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.A.no(idx).coaCmpyCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.A.no(idx).coaAfflCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(idx).coaBrCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.A.no(idx).coaCcCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(idx).coaAcctCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(idx).coaProdCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(idx).coaChCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(idx).coaProjCd_AC);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(idx).coaExtnCd_AC);
//    }
//
//    /**
//     * The method explanation: set initial parameter to call popup.
//     * @param scrnMsg NPAL1500BMsg
//     * @param idx Integer
//     */
//    public static void setInitParamForAcctVarPopup(NPAL1500BMsg scrnMsg, int idx) {
//
//        scrnMsg.xxPopPrm_P0.clear();
//        scrnMsg.xxPopPrm_P1.clear();
//        scrnMsg.xxPopPrm_P2.clear();
//        scrnMsg.xxPopPrm_P3.clear();
//        scrnMsg.xxPopPrm_P4.clear();
//        scrnMsg.xxPopPrm_P5.clear();
//        scrnMsg.xxPopPrm_P6.clear();
//        scrnMsg.xxPopPrm_P7.clear();
//        scrnMsg.xxPopPrm_P8.clear();
//        scrnMsg.xxPopPrm_P9.clear();
//
//        // QC#13985 2016/09/09 Add Start
//        scrnMsg.xxTblNm_P1.clear();
//        scrnMsg.xxTblNm_P1.setValue(SCR_NM_ACCOUNT_TYPE_VA);
//        // QC#13985 2016/09/09 Add End
//
//        // Manager Code is set to subscreen delivery information.
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, "NPAL1500" + "03" + scrnMsg.A.no(idx).poLineTpCd_A1.getValue());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.A.no(idx).coaCmpyCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.A.no(idx).coaAfflCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(idx).coaBrCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.A.no(idx).coaCcCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(idx).coaAcctCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(idx).coaProdCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(idx).coaChCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(idx).coaProjCd_VA);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(idx).coaExtnCd_VA);
//    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @return Object[]
     */
    public static Object[] setParamForNMAL2550Popup(NPAL1500BMsg scrnMsg) {

        Object[] params = new Object[10];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;

        return params;
    }

    /**
     * Get Address Lookup Popup param
     * @param scrnMsg NPAL1500BMsg
     * @param glblCmpyCd String
     * @return Parameter[ Object[7] ]
     */
    public static Object[] getAddressPopupParam(NPAL1500BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        scrnMsg.P.clear();

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
        whereArray1[IDX_2] = scrnMsg.shipToCtyAddr_ST.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = scrnMsg.shipToStCd_ST.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = scrnMsg.shipToPostCd_ST.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        whereArray4[IDX_2] = scrnMsg.shipToCntyNm_ST.getValue();
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
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    // QC#13985 2016/09/09 Add Start
    /**
     * setAccountToPopUpNMAL2550
     * @param scrnMsg NPAL1500BMsg
     */
    public static void setAccountToPopUpNMAL2550(NPAL1500BMsg scrnMsg) {

        int eventRowIndex = scrnMsg.xxNum.getValueInt();
        String accountName = scrnMsg.xxTblNm_P1.getValue();

        StringBuilder chargeAccount = new StringBuilder();
        chargeAccount.append(scrnMsg.xxPopPrm_P1.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P3.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P4.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P5.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P6.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P7.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P2.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P8.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.xxPopPrm_P9.getValue());

        if (accountName.equals(SCR_NM_ACCOUNT_TYPE_CH)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaCmpyCd, scrnMsg.xxPopPrm_P1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaAfflCd, scrnMsg.xxPopPrm_P2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaBrCd, scrnMsg.xxPopPrm_P3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaCcCd, scrnMsg.xxPopPrm_P4);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaAcctCd, scrnMsg.xxPopPrm_P5);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaProdCd, scrnMsg.xxPopPrm_P6);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaChCd, scrnMsg.xxPopPrm_P7);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaProjCd, scrnMsg.xxPopPrm_P8);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).splyCoaExtnCd, scrnMsg.xxPopPrm_P9);

            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxScrItem130Txt_CH, chargeAccount.toString());

        }
        // QC#15817 Delete.
//        else if (accountName.equals(SCR_NM_ACCOUNT_TYPE_AC)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaCmpyCd_AC, scrnMsg.xxPopPrm_P1);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaAfflCd_AC, scrnMsg.xxPopPrm_P2);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaBrCd_AC, scrnMsg.xxPopPrm_P3);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaCcCd_AC, scrnMsg.xxPopPrm_P4);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaAcctCd_AC, scrnMsg.xxPopPrm_P5);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaProdCd_AC, scrnMsg.xxPopPrm_P6);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaChCd_AC, scrnMsg.xxPopPrm_P7);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaProjCd_AC, scrnMsg.xxPopPrm_P8);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaExtnCd_AC, scrnMsg.xxPopPrm_P9);
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxScrItem130Txt_AC, chargeAccount.toString());
//
//        } else if (accountName.equals(SCR_NM_ACCOUNT_TYPE_VA)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaCmpyCd_VA, scrnMsg.xxPopPrm_P1);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaAfflCd_VA, scrnMsg.xxPopPrm_P2);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaBrCd_VA, scrnMsg.xxPopPrm_P3);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaCcCd_VA, scrnMsg.xxPopPrm_P4);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaAcctCd_VA, scrnMsg.xxPopPrm_P5);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaProdCd_VA, scrnMsg.xxPopPrm_P6);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaChCd_VA, scrnMsg.xxPopPrm_P7);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaProjCd_VA, scrnMsg.xxPopPrm_P8);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaExtnCd_VA, scrnMsg.xxPopPrm_P9);
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxScrItem130Txt_VA, chargeAccount.toString());
//
//        }
    }
    // QC#13985 2016/09/09 Add End
    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPAL1500_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NPAL1500_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

// QC#21849 2017/11/02 Add Start
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @return Object[]
     */
    public static Object[] setParamForNMAL6760Popup(NPAL1500BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PG, scrnMsg.shipToCustCd);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, "02");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, "03");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PK, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PL, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PM, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PN, "-");

        int paramCount = 0;
        Object[] params = new Object[24];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;
        params[paramCount++] = scrnMsg.xxPopPrm_PB;
        params[paramCount++] = scrnMsg.xxPopPrm_PC;
        params[paramCount++] = scrnMsg.xxPopPrm_PD;
        params[paramCount++] = scrnMsg.xxPopPrm_PE;
        params[paramCount++] = scrnMsg.xxPopPrm_PF;
        params[paramCount++] = scrnMsg.xxPopPrm_PG;
        params[paramCount++] = scrnMsg.xxPopPrm_PH;
        params[paramCount++] = scrnMsg.xxPopPrm_PI;
        params[paramCount++] = scrnMsg.xxPopPrm_PJ;
        params[paramCount++] = scrnMsg.xxPopPrm_PK;
        params[paramCount++] = scrnMsg.xxPopPrm_PL;
        params[paramCount++] = scrnMsg.xxPopPrm_PM;
        params[paramCount++] = scrnMsg.xxPopPrm_PN;

        return params;
    }
// QC#21849 2017/11/02 Add End
    

    //QC#25024 Add Start
    //for MD
    /**
     * ctrlScreenItemForManualDrop
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1500BMsg
     */
    public static void ctrlScreenItemForManualDrop(EZDCommonHandler handler, NPAL1500BMsg scrnMsg) {
        //if (MANUAL_DIRECT_SHIP_CUST_CD.equals(scrnMsg.destRtlWhCd_DS.getValue())) {
        if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_DS.getValue())){
            scrnMsg.shipToCustCd.setInputProtected(true);
            scrnMsg.shipToLocNm.setInputProtected(false);
            handler.setButtonEnabled(BTN_SHIP_TO_CUST, true);
            
            //ADD Header
            // ship to location
            scrnMsg.shipToAddlLocNm_ST.setInputProtected(false);
            scrnMsg.xxAllLineAddr_ST.setInputProtected(false);
            scrnMsg.shipToPostCd_ST.setInputProtected(false);
            scrnMsg.shipToCtyAddr_ST.setInputProtected(false);
            scrnMsg.shipToCntyNm_ST.setInputProtected(false);
            scrnMsg.shipToStCd_ST.setInputProtected(false);
            scrnMsg.shipToProvNm_ST.setInputProtected(false);
            scrnMsg.shipToCtryCd_ST.setInputProtected(false);
            // ship to location Anchor
            scrnMsg.shipToCtryCd_L1.setInputProtected(false);
            scrnMsg.xxLinkAncr_AL.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_ADDRESS, true);
        }
    }
    //QC#25024 Add End

    // QC#28226 Add Start
    /**
     * Get Param NWAL1130 For Terms
     * @param scrnMsg NMAL6860BMsg
     * @return Param NWAL1130 For Payment Term
     */
    public static Object[] getParamNWAL1130ForPaymentTerm(NPAL1500BMsg scrnMsg) {

        scrnMsg.P.clear();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Payment Term Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  VPT.EZCANCELFLAG          AS EZCANCELFLAG ");
        sb.append(", VPT.GLBL_CMPY_CD          AS GLBL_CMPY_CD ");
        sb.append(", VPT.VND_PMT_TERM_CD       AS VND_PMT_TERM_CD ");
        sb.append(", VPT.VND_PMT_TERM_DESC_TXT AS VND_PMT_TERM_DESC_TXT ");
        sb.append("FROM ");
        sb.append("  VND_PMT_TERM  VPT ");
        sb.append("WHERE ");
        sb.append("    VPT.EZCANCELFLAG   = '0' ");
        sb.append("AND VPT.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Payment Term Code";
        whereArray0[1] = "UPPER(VND_PMT_TERM_CD)";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Payment Term Name";
        whereArray1[1] = "UPPER(VND_PMT_TERM_DESC_TXT)";
        whereArray1[2] = scrnMsg.vndPmtTermDescTxt.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Payment Term Code";
        columnArray0[1] = "VND_PMT_TERM_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Payment Term Name";
        columnArray1[1] = "VND_PMT_TERM_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "VND_PMT_TERM_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "VND_PMT_TERM_DESC_TXT";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;
        params[6] = scrnMsg.P;

        return params;
    }
    // QC#28226 Add End

    // START 2020/09/15 A.Marte [QC#57698, ADD]
    /**
     * chkPrintHasPermission
     * @param functionList List<String>
     * @return true:Has Print Permission false:No Print Permission
     */
    public static boolean chkPrintHasPermission(NPAL1500BMsg scrnMsg) {
        // Only enable Print Button if PO_APVL_STS is APPROVED or PRE-APPROVED
        if ((PO_APVL_STS.PRE_APPROVED.equals(scrnMsg.poApvlStsCd.getValue()) || PO_APVL_STS.APPROVED.equals(scrnMsg.poApvlStsCd.getValue()))) {
            return true;
        }
        return false;
    }
    // END 2020/09/15 A.Marte [QC#57698, ADD]

    // START 2020/04/21 J.Evangelista [QC#57102,ADD]
    /**
     * setTblColumnSortIMG
     * @param scrnMsg NPAL1500BMsg
     */
    public static void setTblColumnSortIMG(NPAL1500BMsg scrnMsg) {

        String tabName = scrnMsg.xxDplyTab.getValue();
        String sortTblNm = "";
        String sortItemNm = "";
        String sortOrderBy = "";

        boolean doControlSort = false;

        if (TAB_AP_INVOICE.equals(tabName)) {
            sortTblNm = scrnMsg.xxSortTblNm_B1.getValue();
            if (NPAL1500Bean.B.equals(sortTblNm) && 0 < scrnMsg.B.getValidCount()) {
                sortItemNm = scrnMsg.xxSortItemNm_B1.getValue();
                sortOrderBy = scrnMsg.xxSortOrdByTxt_B1.getValue();
                doControlSort = true;
            }
        }

        if (doControlSort) {
            controlSortIMG(scrnMsg, sortItemNm, sortOrderBy);
        }
    }

    /**
     * controlSortIMG
     * @param scrnMsg NPAL1500BMsg
     * @param sortItemNm String
     * @param sortOrderBy String
     */
    public static void controlSortIMG(NPAL1500BMsg scrnMsg, String sortItemNm, String sortOrderBy) {

        EZDGUIImageAttribute imgAttr = new EZDGUIImageAttribute(SCREEN_ID, S21StringUtil.concatStrings(SORT_IMG_ATTR_PREFIX, sortItemNm));

        if (S21SortKey.ASC.equals(sortOrderBy)) {
            imgAttr.setSrc(SORT_IMG_ASC_PATH);
        } else if (S21SortKey.DESC.equals(sortOrderBy)) {
            imgAttr.setSrc(SORT_IMG_DESC_PATH);
        }
        imgAttr.setVisibility(true);
        scrnMsg.addGUIAttribute(imgAttr);
    }
    // END 2020/04/21 J.Evangelista [QC#57102,ADD]
}
