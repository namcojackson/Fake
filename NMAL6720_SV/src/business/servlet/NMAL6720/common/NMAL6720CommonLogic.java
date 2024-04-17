/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720.common;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_ACCT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_CERT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_CONTACT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_MSG;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_REF;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_SHIPPING;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_TECH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ADD_TRAN;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_ATTACH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_BILL_TO;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_BILL_TO_TRX;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_CLEAR_REL_BILL_TO;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_ACCT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_CERT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_MSG;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_REF;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_SHIPPING;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_TECH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_DEL_TRAN;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_GET_ACCT_NM;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_GET_ADDR;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_OPEN_WIN_ACCT_SRCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_OPEN_WIN_CTAC_SEARCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_OPEN_WIN_SHOW_CTACDETAILS;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_OPEN_WIN_UPLD_CTAC;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_SEARCH_PRIM_TECH_NM;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_SEARCH_REQ_TECH_NM;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_SHIP_TO;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BTN_VLD_ADDR;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FIVE_DIGIT_POST_WITHOUT_CNTY_ERR;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_ACCOUNTS_INQUIRY;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_ACCOUNTS_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_ACCOUNTS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_CLASSIFICATIONS_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_CLASSIFICATIONS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_CLASSIFIFATIONS_INQUIRY;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_CONTACTS_INQUIRY;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_CONTACTS_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_CONTACTS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_DPL_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_DPL_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_INSTRUCTIONS_INQUIRY;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_INSTRUCTIONS_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_INSTRUCTIONS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_LOCATION_GENERAL_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_LOCATION_GENERAL_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_SHIPPING_INQUIRY;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_SHIPPING_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_SHIPPING_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_SRVATTRB_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_SRVATTRB_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_TRANSACTIONS_INQUIRY;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_TRANSACTIONS_PROS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_ID_TRANSACTIONS_UPDATE;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.ORIG_POST_WITHOUT_CNTY_ERR;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.ORIG_POST_WITH_CNTY_ERR;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.SCREEN_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_ACCOUNT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_CLASSIFICATIONS;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_CONTACTS;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_INSTRUCTIONS;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_SHIPPING;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_SRVATTRB;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_TRANSACTIONS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6720.NMAL6720BMsg;
import business.servlet.NMAL6720.NMAL6720Bean;
import business.servlet.NMAL6720.NMAL6720_ABMsg;
import business.servlet.NMAL6720.NMAL6720_BBMsg;
import business.servlet.NMAL6720.NMAL6720_DBMsg;
import business.servlet.NMAL6720.NMAL6720_EBMsg;
import business.servlet.NMAL6720.NMAL6720_FBMsg;
import business.servlet.NMAL6720.NMAL6720_GBMsg;
import business.servlet.NMAL6720.NMAL6720_IBMsg;
import business.servlet.NMAL6720.NMAL6720_PBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/04/06   Fujitsu         C.Yokoi         Update          QC#6633
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 * 2016/04/14   SRAA            Y.Chen          Update          QC#5749
 * 2016/04/29   SRAA            Y.Chen          Update          QC#6559
 * 2016/05/10   SRAA            Y.Chen          Update          QC#4505
 * 2016/05/18   Fujitsu         N.Sugiura       Update          Unit Test#193
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11875
 * 2016/05/10   SRAA            Y.Chen          Update          QC#12060
 * 2016/10/12   Fujitsu         C.Yokoi         Update          QC#4096
 * 2016/10/21   Fujitsu         C.Yokoi         Update          QC#14982
 * 2016/11/17   Fujitsu         R.Nakamura      Update          QC#16037
 * 2016/11/30   Fujitsu         R.Nakamura      Update          QC#16037
 * 2017/02/21   Fujitsu         R.Nakamura      Update          QC#17631
 * 2017/11/27   Fujitsu         A.Kosai         Update          QC#20828
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/02/19   Fujitsu         Hd.Sugawara     Update          QC#20291-1
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/05/16   Fujitsu         Hd.Sugawara     Update          QC#26041
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 * 2018/08/10   Fujitsu         W.Honda         Update          QC#26041-1
 * 2018/08/29   Fujitsu         W.Honda         Update          QC#27869
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/04/12   Fujitsu         R.Nakamura      Update          QC#31136
 * 2020/03/24   Fujitsu         M.Ohno          Update          QC#55673
 * 2020/07/22   CITS            K.Ogino         Create          QC#57316
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6720CommonLogic {
    /**
     * The initial state of the screen item is set.
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
        setBgColor(scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL6720BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {
        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE)) {
            // Mod Start 2018/02/19 QC#20291-1
            //if (isAccountsTabUpdate(scrnMsg, functionIds)) {
            // Mod Start 2018/05/16 QC#26041
            //if (isAccountsTabSubmit(scrnMsg, functionIds)) {
            if (isAccountsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
            // Mod End 2018/02/19 QC#20291-1
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
        } else if (TAB_CLASSIFICATIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE)) {
            // Mod Start 2018/02/19 QC#20291-1
            //if (isClassificationsTabUpdate(scrnMsg, functionIds)) {
            // Mod Start 2018/05/16 QC#26041
            //if (isClassificationsTabSubmit(scrnMsg, functionIds)) {
            if (isClassificationsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
            // Mod End 2018/02/19 QC#20291-1
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE)) {
            // Mod Start 2018/02/19 QC#20291-1
            //if (isContactsTabUpdate(scrnMsg, functionIds)) {
            // Mod Start 2018/05/16 QC#26041
            //if (isContactsTabSubmit(scrnMsg, functionIds)) {
            if (isContactsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
            // Mod End 2018/02/19 QC#20291-1
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE)) {
            // Mod Start 2018/02/19 QC#20291-1
            //if (isTransactionsTabUpdate(scrnMsg, functionIds)) {
            // Mod Start 2018/05/16 QC#26041
            //if (isTransactionsTabSubmit(scrnMsg, functionIds)) {
            if (isTransactionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
            // Mod End 2018/02/19 QC#20291-1
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE)) {
            // Mod Start 2018/02/19 QC#20291-1
            //if (isInstructionsTabUpdate(scrnMsg, functionIds)) {
            // Mod Start 2018/05/16 QC#26041
            //if (isInstructionsTabSubmit(scrnMsg, functionIds)) {
            if (isInstructionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
            // Mod End 2018/02/19 QC#20291-1
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
        } else if (TAB_SRVATTRB.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE)) {
            // Mod Start 2018/02/19 QC#20291-1
            //if (isSrvAttrbTabUpdate(scrnMsg, functionIds)) {
            // Mod Start 2018/05/16 QC#26041
            //if (isSrvAttrbTabSubmit(scrnMsg, functionIds)) {
            if (isSrvAttrbTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                // Mod End 2018/02/19 QC#20291-1
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/19 QC#20297(Sol#379) add start
            // Mod Start 2018/05/16 QC#26041
            //if (isShippingTabSubmit(scrnMsg, functionIds)) {
            if (isShippingTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            }
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    // Add Start 2018/02/19 QC#20291-1
    // Del Start 2018/05/16 QC#26041
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isAccountsTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_ACCOUNTS_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isClassificationsTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isContactsTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_CONTACTS_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isTransactionsTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_TRANSACTIONS_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isSrvAttrbTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_SRVATTRB_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isInstructionsTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_INSTRUCTIONS_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isShippingTabSubmit(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
//        // user update header fields and detail fields.
//        if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE)) {
//            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//                result = true;
//            } else if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                result = true;
//            }
//        } else if (functionIds.contains(FUNC_ID_SHIPPING_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
//            result = true;
//        }
//
//        return result;
//    }
    // Del End 2018/05/16 QC#26041
    // Add End 2018/02/19 QC#20291-1

    // Add Start 2018/01/22 QC#20291(Sol#348)
    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isLocationGeneralUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isDplUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_DPL_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_DPL_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_DPL_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isAccountsTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
                result = true;
        } else if (functionIds.contains(FUNC_ID_ACCOUNTS_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isClassificationsTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isContactsTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_CONTACTS_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isTransactionsTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_TRANSACTIONS_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isSrvAttrbTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_SRVATTRB_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isInstructionsTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_INSTRUCTIONS_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }
    // Add End 2018/01/22 QC#20291(Sol#348)

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg, String userId) {
        controlScreenHeaderFields(userProfileService, handler, scrnMsg);

        // Add Start 2016/11/30 QC#16037
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        boolean authAccountRead = functionIds.contains(FUNC_ID_ACCOUNTS_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authAccountEdit = functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE);
        boolean authAccountEdit = false;
        if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE) || //
                functionIds.contains(FUNC_ID_ACCOUNTS_PROS_UPDATE)) {
            authAccountEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authAccountRead || authAccountEdit) {
            scrnMsg.xxTabProt_AC.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_AC.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authClassRead = functionIds.contains(FUNC_ID_CLASSIFIFATIONS_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authClassEdit = functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE);
        boolean authClassEdit = false;
        if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE) || //
                functionIds.contains(FUNC_ID_CLASSIFICATIONS_PROS_UPDATE)) {
            authClassEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authClassRead || authClassEdit) {
            scrnMsg.xxTabProt_CL.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_CL.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authContactsRead = functionIds.contains(FUNC_ID_CONTACTS_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authContactsEdit = functionIds.contains(FUNC_ID_CONTACTS_UPDATE);
        boolean authContactsEdit = false;
        if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE) || //
                functionIds.contains(FUNC_ID_CONTACTS_PROS_UPDATE)) {
            authContactsEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authContactsRead || authContactsEdit) {
            scrnMsg.xxTabProt_CT.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_CT.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authTransactionsRead = functionIds.contains(FUNC_ID_TRANSACTIONS_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authTransactionsEdit = functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE);
        boolean authTransactionsEdit = false;
        if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE) || //
                functionIds.contains(FUNC_ID_TRANSACTIONS_PROS_UPDATE)) {
            authTransactionsEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authTransactionsRead || authTransactionsEdit) {
            scrnMsg.xxTabProt_TR.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_TR.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authInstractionsRead = functionIds.contains(FUNC_ID_INSTRUCTIONS_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authInstractionsEdit = functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE);
        boolean authInstractionsEdit = false;
        if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE) || //
                functionIds.contains(FUNC_ID_INSTRUCTIONS_PROS_UPDATE)) {
            authInstractionsEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authInstractionsRead || authInstractionsEdit) {
            scrnMsg.xxTabProt_IN.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_IN.setValue(ZYPConstant.FLG_OFF_N);
        }

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authSrvattrbEdit = functionIds.contains(FUNC_ID_SRVATTRB_UPDATE);
        boolean authSrvattrbEdit = false;
        if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE) || //
                functionIds.contains(FUNC_ID_SRVATTRB_PROS_UPDATE)) {
            authSrvattrbEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authSrvattrbEdit) {
            scrnMsg.xxTabProt_SA.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_SA.setValue(ZYPConstant.FLG_OFF_N);
        }
        // Add End 2016/11/30 QC#16037

        // 2018/02/19 QC#20297(Sol#379) add start
        boolean authShippingRead = functionIds.contains(FUNC_ID_SHIPPING_INQUIRY);
        boolean authShippingEdit = false;
        if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE) || //
                functionIds.contains(FUNC_ID_SHIPPING_PROS_UPDATE)) {
            authShippingEdit = true;
        }

        if (authShippingRead || authShippingEdit) {
            scrnMsg.xxTabProt_SH.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_SH.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 2018/02/19 QC#20297(Sol#379) add end

        if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabAccount(userProfileService, handler, scrnMsg);

        } else if (TAB_CLASSIFICATIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabClassifications(userProfileService, handler, scrnMsg);

        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabContacts(userProfileService, handler, scrnMsg);

        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabTransactions(userProfileService, handler, scrnMsg);

        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabInstructions(userProfileService, handler, scrnMsg);

        } else if (TAB_SRVATTRB.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabSrbAttrb(userProfileService, handler, scrnMsg);

        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/19 QC#20297(Sol#379) add start
            controlScreenTabShipping(userProfileService, handler, scrnMsg);
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    /**
     * controlScreenHeaderFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenHeaderFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (hasUpdateHeaderAuth(scrnMsg, functionIds)) {
        if (isLocationGeneralUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)

            scrnMsg.ctryCd_P1.setInputProtected(false);

            scrnMsg.firstLineAddr_H1.setInputProtected(false);
            scrnMsg.scdLineAddr_H1.setInputProtected(false);
            scrnMsg.thirdLineAddr_H1.setInputProtected(false);
            scrnMsg.frthLineAddr_H1.setInputProtected(false);

            scrnMsg.ctyAddr_H1.setInputProtected(false);
            scrnMsg.stCd_P1.setInputProtected(false);
            scrnMsg.postCd_H1.setInputProtected(false);
            scrnMsg.cntyNm_H1.setInputProtected(false);
            scrnMsg.provNm_H1.setInputProtected(false);
            // 2023/11/06 QC#61924 Add Start
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.actvFlg_H1.getValue())) {
                scrnMsg.xxChkBox_H1.setInputProtected(false);
            } else {
                scrnMsg.xxChkBox_H1.setInputProtected(true);
            }
            // 2023/11/06 QC#61924 Add End

            // QC#4505
            handler.setButtonEnabled(BTN_VLD_ADDR, true);
            handler.setButtonEnabled(BTN_GET_ADDR, true);

            scrnMsg.dsAcctRelnBillToFlg_BI.setInputProtected(false);
            scrnMsg.effFromDt_BI.setInputProtected(false);
            scrnMsg.effThruDt_BI.setInputProtected(false);
            scrnMsg.primUsgFlg_BI.setInputProtected(false);

            scrnMsg.dsAcctRelnShipToFlg_SH.setInputProtected(false);
            scrnMsg.effFromDt_SH.setInputProtected(false);
            scrnMsg.effThruDt_SH.setInputProtected(false);
            scrnMsg.primUsgFlg_SH.setInputProtected(false);
            scrnMsg.billToCustCd_SH.setInputProtected(false);

            scrnMsg.dsLocNm_H1.setInputProtected(false);
            scrnMsg.telNum_H1.setInputProtected(false);
            scrnMsg.prinCustFlg_PR.setInputProtected(false);
            scrnMsg.actvFlg_H1.setInputProtected(false);
            scrnMsg.lineBizTpCd_P1.setInputProtected(false);

        } else {
            protectedAllHeaderFields(scrnMsg, handler);
        }

        if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            scrnMsg.actvFlg_H1.setInputProtected(true);
        }

        // 2017/11/27 QC#20828 Mod Start
        //if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && !NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
        // Mod Start 2018/02/19 QC#20291-1
        //if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) &&
        //        !NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue()) &&
        //        !isMergeProsLocToCust(scrnMsg)) {
        if (isProtectProspectUsage(scrnMsg, functionIds)) {
            // Mod End 2018/02/19 QC#20291-1
        // 2017/11/27 QC#20828 Mod End
            scrnMsg.dsAcctRelnBillToFlg_BI.setInputProtected(true);
            scrnMsg.effFromDt_BI.setInputProtected(true);
            scrnMsg.effThruDt_BI.setInputProtected(true);
            scrnMsg.primUsgFlg_BI.setInputProtected(true);

            scrnMsg.dsAcctRelnShipToFlg_SH.setInputProtected(true);
            scrnMsg.effFromDt_SH.setInputProtected(true);
            scrnMsg.effThruDt_SH.setInputProtected(true);
            scrnMsg.billToCustCd_SH.setInputProtected(true);
            scrnMsg.primUsgFlg_SH.setInputProtected(true);

            handler.setButtonEnabled(BTN_BILL_TO, false);
            // 2016/10/21 CSA-QC#14982 Add Start
            handler.setButtonEnabled(BTN_CLEAR_REL_BILL_TO, false);
            // 2016/10/21 CSA-QC#14982 Add End
            scrnMsg.prinCustFlg_PR.setInputProtected(true);
        }

        // Add Start 2018/02/19 QC#20291-1
        // Del Start 2018/05/16 QC#26041
        //changeUsageAccessControllForCustomerUpdateUser(handler, scrnMsg, functionIds);
        // Del End 2018/05/16 QC#26041
        // Add End 2018/02/19 QC#20291-1

        // 2016/05/18 Unit Test#193 Add Start
        scrnMsg.embgoFlg_H1.setInputProtected(true);
        scrnMsg.xxDtTxt_H1.setInputProtected(true);
        scrnMsg.dplRsnTxt_H1.setInputProtected(true);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_DPL_UPDATE)) {
        if (isDplUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            scrnMsg.dplStsCd_P1.setInputProtected(false);
        } else {
            scrnMsg.dplStsCd_P1.setInputProtected(true);
        }
        // 2016/05/18 Unit Test#193 Add End

        // 2020/03/24 QC#55673 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_AL.getValue())) {
            scrnMsg.addlLocNm.setInputProtected(false);
        } else {
            scrnMsg.addlLocNm.setInputProtected(true);
        }
          // 2020/03/24 QC#55673 Add End
    }

    // Add Start 2018/02/19 QC#20291-1
    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static final boolean isProtectProspectUsage(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_PROS_UPDATE) && //
        //        DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        //    if (PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue()) && //
        //            isMergeProsLocToCust(scrnMsg)) {
        //        result = false;
        //    } else {
        //        result = true;
        //    }
        //}
        if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE)) {
            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
                result = false;
            } else {
                if (PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
                    result = false;
                } else {
                    result = true;
                }
            }
        } else {
            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
                result = true;
            } else {
                if (PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue()) && //
                        isMergeProsLocToCust(scrnMsg)) {
                    result = false;
                } else {
                    result = true;
                }
            }
        }
        // Mod End 2018/05/16 QC#26041

        return result;
    }

      // Del Start 2018/05/16 QC#26041
//    /**
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NMAL6720BMsg
//     * @param functionIds List<String>
//     */
//    public static final void changeUsageAccessControllForCustomerUpdateUser(EZDCommonHandler handler, NMAL6720BMsg scrnMsg, List<String> functionIds) {
//
//        if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE)) {
//            if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && //
//                    PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//                // CUSTOMER update user can update from prospect to
//                // customer.
//                scrnMsg.dsAcctRelnBillToFlg_BI.setInputProtected(false);
//                scrnMsg.effFromDt_BI.setInputProtected(false);
//                scrnMsg.effThruDt_BI.setInputProtected(false);
//                scrnMsg.primUsgFlg_BI.setInputProtected(false);
//
//                scrnMsg.dsAcctRelnShipToFlg_SH.setInputProtected(false);
//                scrnMsg.effFromDt_SH.setInputProtected(false);
//                scrnMsg.effThruDt_SH.setInputProtected(false);
//                scrnMsg.billToCustCd_SH.setInputProtected(false);
//                scrnMsg.primUsgFlg_SH.setInputProtected(false);
//
//                scrnMsg.prinCustFlg_PR.setInputProtected(false);
//
//                controlProtectShipTo(scrnMsg, handler);
//            }
//        }
//    }
    // Del End 2018/05/16 QC#26041
    // Add End 2018/02/19 QC#20291-1

    /**
     * controlProtectShipTo
     * @param scrnMsg NMAL6720BMsg
     * @param handler EZDCommonHandler
     */
    public static final void controlProtectShipTo(NMAL6720BMsg scrnMsg, EZDCommonHandler handler) {
        // 2016/10/21 CSA-QC#14982 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_SH)) {
            handler.setButtonEnabled(BTN_BILL_TO, true);
        } else {
            handler.setButtonEnabled(BTN_BILL_TO, false);
        }

        // 2018/07/11 Update Start QC#26422
//        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SH)) {
//            handler.setButtonEnabled(BTN_CLEAR_REL_BILL_TO, true);
//        } else {
//            handler.setButtonEnabled(BTN_CLEAR_REL_BILL_TO, false);
//        }
        // 2018/07/11 Update End QC#26422
        // 2016/10/21 CSA-QC#14982 Add End
    }

    /**
     * protectedAllHeaderFields
     * @param NMAL6720BMsg scrnMsg
     */
    private static final void protectedAllHeaderFields(NMAL6720BMsg scrnMsg, EZDCommonHandler handler) {

        scrnMsg.ctryCd_P1.setInputProtected(true);

        scrnMsg.firstLineAddr_H1.setInputProtected(true);
        scrnMsg.scdLineAddr_H1.setInputProtected(true);
        scrnMsg.thirdLineAddr_H1.setInputProtected(true);
        scrnMsg.frthLineAddr_H1.setInputProtected(true);

        scrnMsg.ctyAddr_H1.setInputProtected(true);
        scrnMsg.stCd_P1.setInputProtected(true);
        scrnMsg.postCd_H1.setInputProtected(true);
        scrnMsg.cntyNm_H1.setInputProtected(true);
        scrnMsg.provNm_H1.setInputProtected(true);
        // 2023/11/06 QC#61924 Add Start
        scrnMsg.xxChkBox_H1.setInputProtected(true);
        // 2023/11/06 QC#61924 Add End

        // QC#4505
        handler.setButtonEnabled(BTN_VLD_ADDR, false);
        handler.setButtonEnabled(BTN_GET_ADDR, false);

        scrnMsg.dsAcctRelnBillToFlg_BI.setInputProtected(true);
        scrnMsg.effFromDt_BI.setInputProtected(true);
        scrnMsg.effThruDt_BI.setInputProtected(true);
        scrnMsg.primUsgFlg_BI.setInputProtected(true);

        scrnMsg.dsAcctRelnShipToFlg_SH.setInputProtected(true);
        scrnMsg.effFromDt_SH.setInputProtected(true);
        scrnMsg.effThruDt_SH.setInputProtected(true);
        scrnMsg.billToCustCd_SH.setInputProtected(true);
        scrnMsg.primUsgFlg_SH.setInputProtected(true);

        scrnMsg.dsLocNm_H1.setInputProtected(true);
        scrnMsg.telNum_H1.setInputProtected(true);
        scrnMsg.prinCustFlg_PR.setInputProtected(true);
        scrnMsg.actvFlg_H1.setInputProtected(true);
        scrnMsg.lineBizTpCd_P1.setInputProtected(true);

        handler.setButtonEnabled(BTN_BILL_TO, false);
        // 2016/10/21 CSA-QC#14982 Add Start
        handler.setButtonEnabled(BTN_CLEAR_REL_BILL_TO, false);
        // 2016/10/21 CSA-QC#14982 Add End
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenTabAccount(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE)) {
        if (isAccountsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(scrnMsg.A.no(i).rgtnStsCd_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                }
                // Add Start 2017/02/21 QC#17631
                // Mod Start 2019/04/12 QC#31136
//                handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, true);
//                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(false);
//                handler.setButtonEnabled(BTN_GET_ACCT_NM, true);
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).acctLocPk_A1)) {
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, i, false);
                    scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_GET_ACCT_NM, i, false);
                } else {
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, i, true);
                    scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_GET_ACCT_NM, i, true);
                }
                // Mod End 2019/04/12 QC#31136
                // Add End 2017/02/21 QC#17631
                // 2017/10/10 QC#20355 Mod Start
                if (ZYPCommonFunc.hasValue(scrnMsg.prosToCustChngStsCd_H1)) {
                    scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
                }
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
                // 2017/10/10 QC#20355 Mod End
            }
            handler.setButtonEnabled(BTN_ADD_ACCT, true);
            handler.setButtonEnabled(BTN_DEL_ACCT, true);

            scrnMsg.xxTabProt_AC.setInputProtected(false);
            scrnMsg.xxTabProt_CL.setInputProtected(false);
            scrnMsg.xxTabProt_CT.setInputProtected(false);
            scrnMsg.xxTabProt_TR.setInputProtected(false);
            scrnMsg.xxTabProt_SA.setInputProtected(false);
            scrnMsg.xxTabProt_IN.setInputProtected(false);
        } else {
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                // Add Start 2017/02/21 QC#17631
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, false);
                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_GET_ACCT_NM, false);
                // Add End 2017/02/21 QC#17631
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
            }
            handler.setButtonEnabled(BTN_ADD_ACCT, false);
            handler.setButtonEnabled(BTN_DEL_ACCT, false);
        }
            // 2017/11/27 QC#20828 Mod Start
//        if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) && !NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {
//            for (int i = 0; i < scrnMsg.A.length(); i++) {
//                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
//                handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, false);
//                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
//                handler.setButtonEnabled(BTN_GET_ACCT_NM, false);
//                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
//                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
//            }
//            handler.setButtonEnabled(BTN_ADD_ACCT, false);
//            handler.setButtonEnabled(BTN_DEL_ACCT, false);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (isAccountsTabUpdate(scrnMsg, functionIds) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            // Mod End 2018/01/22 QC#20291(Sol#348)

            for (int i = 0; i < scrnMsg.A.length(); i++) {

                if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, i, false);
                    scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_GET_ACCT_NM, i, false);
                    scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);

                } else if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_SRCH, i, true);
                    scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_GET_ACCT_NM, i, true);
                    scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
                }
            }

            // 2018/08/10 QC#26041-1 Mod Start
//            handler.setButtonEnabled(BTN_ADD_ACCT, !NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue()));;
            handler.setButtonEnabled(BTN_ADD_ACCT, functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE));
            // 2018/08/10 QC#26041-1 Mod End
            handler.setButtonEnabled(BTN_DEL_ACCT, false);
            // 2017/11/27 QC#20828 Mod End
        }

    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenTabClassifications(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE)) {
        if (isClassificationsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            scrnMsg.dunsNum_H1.setInputProtected(false);
            scrnMsg.dsUltDunsNum_H1.setInputProtected(false);
            scrnMsg.dsLocEmpNum_H1.setInputProtected(false);
            scrnMsg.dsLocRevAmt_H1.setInputProtected(false);
            scrnMsg.dsCustSicCd_H1.setInputProtected(false);
            scrnMsg.dsCustSicDescTxt_H1.setInputProtected(false);
            scrnMsg.dsLastUpdDunsDt_H1.setInputProtected(true);
            // QC#5749
            scrnMsg.dunsTradeStyleNm_H1.setInputProtected(false);
            scrnMsg.dunsActvCd_H1.setInputProtected(false);
            scrnMsg.dunsLineAddr_H1.setInputProtected(false);
            scrnMsg.dunsBizNm_H1.setInputProtected(false);
            scrnMsg.dsLastRcvDunsTxt_H1.setInputProtected(true);
            scrnMsg.dsInsdCtyLimitFlg_H1.setInputProtected(false);
            scrnMsg.geoCd_H1.setInputProtected(false);
            // 2016/10/12 CSA-QC#4096 Add
            scrnMsg.geoCd_L1.setValue(ZYPConstant.FLG_ON_Y);
            // Add Start 2017/02/21 QC#17631
            scrnMsg.glnNum_H1.setInputProtected(false);
            scrnMsg.hqDunsNum_H1.setInputProtected(false);
            scrnMsg.dsPrntDunsNum_H1.setInputProtected(false);
            // Add End 2017/02/21 QC#17631

            for (int i = 0; i < scrnMsg.B.length(); i++) {
                // Add Start 2017/02/21 QC#17631
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
                // Add End 2017/02/21 QC#17631
                scrnMsg.B.no(i).dsXrefAcctTpCd_P1.setInputProtected(false);
                scrnMsg.B.no(i).dsXrefAcctNm_B1.setInputProtected(false);
                scrnMsg.B.no(i).dsXrefAcctCd_B1.setInputProtected(false);
            }
            handler.setButtonEnabled(BTN_ADD_REF, true);
            handler.setButtonEnabled(BTN_DEL_REF, true);
        } else {
            scrnMsg.dunsNum_H1.setInputProtected(true);
            scrnMsg.dsUltDunsNum_H1.setInputProtected(true);
            scrnMsg.dsLocEmpNum_H1.setInputProtected(true);
            scrnMsg.dsLocRevAmt_H1.setInputProtected(true);
            scrnMsg.dsCustSicCd_H1.setInputProtected(true);
            scrnMsg.dsCustSicDescTxt_H1.setInputProtected(true);
            scrnMsg.dsLastUpdDunsDt_H1.setInputProtected(true);
            // QC#5749
            scrnMsg.dunsTradeStyleNm_H1.setInputProtected(true);
            scrnMsg.dunsActvCd_H1.setInputProtected(true);
            scrnMsg.dunsLineAddr_H1.setInputProtected(true);
            scrnMsg.dunsBizNm_H1.setInputProtected(true);
            scrnMsg.dsLastRcvDunsTxt_H1.setInputProtected(true);
            scrnMsg.dsInsdCtyLimitFlg_H1.setInputProtected(true);
            scrnMsg.geoCd_H1.setInputProtected(true);
            // 2016/10/12 CSA-QC#4096 Add
            scrnMsg.geoCd_L1.clear();
            // Add Start 2017/02/21 QC#17631
            scrnMsg.glnNum_H1.setInputProtected(true);
            scrnMsg.hqDunsNum_H1.setInputProtected(true);
            scrnMsg.dsPrntDunsNum_H1.setInputProtected(true);
            // Add End 2017/02/21 QC#17631

            for (int i = 0; i < scrnMsg.B.length(); i++) {
                // Add Start 2017/02/21 QC#17631
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
                // Add End 2017/02/21 QC#17631
                scrnMsg.B.no(i).dsXrefAcctTpCd_P1.setInputProtected(true);
                scrnMsg.B.no(i).dsXrefAcctNm_B1.setInputProtected(true);
                scrnMsg.B.no(i).dsXrefAcctCd_B1.setInputProtected(true);
            }
            handler.setButtonEnabled(BTN_ADD_REF, false);
            handler.setButtonEnabled(BTN_DEL_REF, false);
        }
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenTabContacts(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE)) {
        if (isContactsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            for (int i = 0; i < scrnMsg.C.length(); i++) {
                // Add Start 2018/01/22 QC#20291(Sol#348)
                scrnMsg.C.no(i).ctacPsnPk_C1.setInputProtected(false);
                // Add End 2018/01/22 QC#20291(Sol#348)
                // Mod Start 2017/12/06 QC#21897
                //scrnMsg.C.no(i).ctacTpNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).ctacTpDescTxt_C1.setInputProtected(true);
                // Mod End 2017/12/06 QC#21897
                scrnMsg.C.no(i).ctacPsnFirstNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).ctacPsnLastNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPsnDeptNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPntValTxt_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPntValTxt_C2.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPsnExtnNum_C1.setInputProtected(true);
                scrnMsg.C.no(i).effFromDt_C1.setInputProtected(false);
                scrnMsg.C.no(i).effThruDt_C1.setInputProtected(false);
                // Mod Start 2018/08/29 QC#27869
                if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).dsCtacPsnRelnPk_C1)) {
                    scrnMsg.C.no(i).dsPrimLocFlg_C1.setInputProtected(false);
                } else {
                    scrnMsg.C.no(i).dsPrimLocFlg_C1.setInputProtected(true);
                }
                // Mod End 2018/08/29 QC#27869
            }
        } else {
            for (int i = 0; i < scrnMsg.C.length(); i++) {
                // Add Start 2018/01/22 QC#20291(Sol#348)
                scrnMsg.C.no(i).ctacPsnPk_C1.setInputProtected(true);
                // Add End 2018/01/22 QC#20291(Sol#348)
                // Mod Start 2017/12/06 QC#21897
                //scrnMsg.C.no(i).ctacTpNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).ctacTpDescTxt_C1.setInputProtected(true);
                // Mod End 2017/12/06 QC#21897
                scrnMsg.C.no(i).ctacPsnFirstNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).ctacPsnLastNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPsnDeptNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPntValTxt_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPntValTxt_C2.setInputProtected(true);
                scrnMsg.C.no(i).dsCtacPsnExtnNum_C1.setInputProtected(true);
                scrnMsg.C.no(i).effFromDt_C1.setInputProtected(true);
                scrnMsg.C.no(i).effThruDt_C1.setInputProtected(true);
                scrnMsg.C.no(i).dsPrimLocFlg_C1.setInputProtected(true);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.locNum_H1)) {
            handler.setButtonEnabled(BTN_OPEN_WIN_CTAC_SEARCH, true);
        } else {
            handler.setButtonEnabled(BTN_OPEN_WIN_CTAC_SEARCH, false);
        }

        // QC#6559
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (ZYPCommonFunc.hasValue(scrnMsg.locNum_H1) && (functionIds.contains(FUNC_ID_CONTACTS_UPDATE))) {
        if (ZYPCommonFunc.hasValue(scrnMsg.locNum_H1) && //
                isContactsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            handler.setButtonEnabled(BTN_ADD_CONTACT, true);
            handler.setButtonEnabled(BTN_OPEN_WIN_UPLD_CTAC, true);
        } else {
            handler.setButtonEnabled(BTN_ADD_CONTACT, false);
            handler.setButtonEnabled(BTN_OPEN_WIN_UPLD_CTAC, false);
        }

        // 2016/04/06 CSA-QC#6633 Add Start
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.locNum_H1)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && //
                ZYPCommonFunc.hasValue(scrnMsg.locNum_H1) && //
                isContactsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            handler.setButtonEnabled(BTN_OPEN_WIN_SHOW_CTACDETAILS, true);
        } else {
            handler.setButtonEnabled(BTN_OPEN_WIN_SHOW_CTACDETAILS, false);
        }
        // 2016/04/06 CSA-QC#6633 Add End
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenTabTransactions(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE)) {
        if (isTransactionsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            for (int i = 0; i < scrnMsg.D.length(); i++) {
                // Add Start 2017/02/22 QC#17631
                scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(false);
                // Add End 2017/02/22 QC#17631
                scrnMsg.D.no(i).dsTrxRuleTpCd_P1.setInputProtected(false);
                scrnMsg.D.no(i).dsPoReqFlg_D1.setInputProtected(false);
                scrnMsg.D.no(i).dsBlktPoNum_D1.setInputProtected(false);
                scrnMsg.D.no(i).dsPoExprDt_D1.setInputProtected(false);
                scrnMsg.D.no(i).dsDefBillToCd_D1.setInputProtected(false);
                scrnMsg.D.no(i).dsDefShipToCd_D1.setInputProtected(false);
                scrnMsg.D.no(i).dsCrCardReqFlg_D1.setInputProtected(false);
                scrnMsg.D.no(i).dsOvngtAllwFlg_D1.setInputProtected(false);
            }
            handler.setButtonEnabled(BTN_ADD_TRAN, true);
            handler.setButtonEnabled(BTN_DEL_TRAN, true);
            handler.setButtonEnabled(BTN_BILL_TO_TRX, true);
            handler.setButtonEnabled(BTN_SHIP_TO, true);

        } else {
            for (int i = 0; i < scrnMsg.D.length(); i++) {
                // Add Start 2017/02/22 QC#17631
                scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(true);
                // Add End 2017/02/22 QC#17631
                scrnMsg.D.no(i).dsTrxRuleTpCd_P1.setInputProtected(true);
                scrnMsg.D.no(i).dsPoReqFlg_D1.setInputProtected(true);
                scrnMsg.D.no(i).dsBlktPoNum_D1.setInputProtected(true);
                scrnMsg.D.no(i).dsPoExprDt_D1.setInputProtected(true);
                scrnMsg.D.no(i).dsDefBillToCd_D1.setInputProtected(true);
                scrnMsg.D.no(i).dsDefShipToCd_D1.setInputProtected(true);
                scrnMsg.D.no(i).dsCrCardReqFlg_D1.setInputProtected(true);
                scrnMsg.D.no(i).dsOvngtAllwFlg_D1.setInputProtected(true);
            }
            handler.setButtonEnabled(BTN_ADD_TRAN, false);
            handler.setButtonEnabled(BTN_DEL_TRAN, false);
            handler.setButtonEnabled(BTN_BILL_TO_TRX, false);
            handler.setButtonEnabled(BTN_SHIP_TO, false);

        }
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenTabInstructions(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE)) {
        if (isInstructionsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            for (int i = 0; i < scrnMsg.E.length(); i++) {
                // Add Start 2017/02/22 QC#17631
                scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(false);
                // Add End 2017/02/22 QC#17631
                scrnMsg.E.no(i).lineBizTpCd_P2.setInputProtected(false);
                scrnMsg.E.no(i).dsBizAreaCd_P1.setInputProtected(false);
                scrnMsg.E.no(i).dsCustMsgTpCd_P1.setInputProtected(false);
                scrnMsg.E.no(i).dsCustMsgTxt_E1.setInputProtected(false);
                scrnMsg.E.no(i).dsPrintOnInvFlg_E1.setInputProtected(false);
                scrnMsg.E.no(i).effThruDt_E1.setInputProtected(false);
                if (ZYPCommonFunc.hasValue(scrnMsg.E.no(i).dsCustSpclMsgPk_E1)) {
                    handler.setButtonEnabled(BTN_ATTACH, i, true);
                } else {
                    handler.setButtonEnabled(BTN_ATTACH, i, false);
                }
            }
            handler.setButtonEnabled(BTN_ADD_MSG, true);
            handler.setButtonEnabled(BTN_DEL_MSG, true);
        } else {
            for (int i = 0; i < scrnMsg.E.length(); i++) {
                // Add Start 2017/02/22 QC#17631
                scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(true);
                // Add End 2017/02/22 QC#17631
                scrnMsg.E.no(i).lineBizTpCd_P2.setInputProtected(true);
                scrnMsg.E.no(i).dsBizAreaCd_P1.setInputProtected(true);
                scrnMsg.E.no(i).dsCustMsgTpCd_P1.setInputProtected(true);
                scrnMsg.E.no(i).dsCustMsgTxt_E1.setInputProtected(true);
                scrnMsg.E.no(i).dsPrintOnInvFlg_E1.setInputProtected(true);
                scrnMsg.E.no(i).effThruDt_E1.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(scrnMsg.E.no(i).dsCustSpclMsgPk_E1)) {
                    handler.setButtonEnabled(BTN_ATTACH, i, true);
                } else {
                    handler.setButtonEnabled(BTN_ATTACH, i, false);
                }
            }
            handler.setButtonEnabled(BTN_ADD_MSG, false);
            handler.setButtonEnabled(BTN_DEL_MSG, false);
        }
    }

    private static final void controlScreenTabSrbAttrb(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE)) {
        if (isSrvAttrbTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            for (int i = 0; i < scrnMsg.F.length(); i++) {
                scrnMsg.F.no(i).xxChkBox_F1.setInputProtected(false);
                scrnMsg.F.no(i).svcAccsPmitNum_F1.setInputProtected(false);
                scrnMsg.F.no(i).svcAccsPmitDescTxt_F1.setInputProtected(true);
                scrnMsg.F.no(i).effFromDt_F1.setInputProtected(false);
                scrnMsg.F.no(i).effToDt_F1.setInputProtected(false);
            }
            for (int i = 0; i < scrnMsg.G.length(); i++) {
                scrnMsg.G.no(i).xxChkBox_G1.setInputProtected(false);
                scrnMsg.G.no(i).techNm_G1.setInputProtected(true);
                scrnMsg.G.no(i).nonPrfTechCd_G1.setInputProtected(false);
                scrnMsg.G.no(i).effThruDt_G1.setInputProtected(false);
            }
            scrnMsg.prfTechCd_SA.setInputProtected(false);
            scrnMsg.reqTechCd_SA.setInputProtected(false);
            scrnMsg.xxAllPsnNm_S1.setInputProtected(true);
            scrnMsg.xxAllPsnNm_S2.setInputProtected(true);
            handler.setButtonEnabled(BTN_ADD_CERT, true);
            handler.setButtonEnabled(BTN_DEL_CERT, true);
            handler.setButtonEnabled(BTN_ADD_TECH, true);
            handler.setButtonEnabled(BTN_DEL_TECH, true);
            handler.setButtonEnabled(BTN_SEARCH_PRIM_TECH_NM, true);
            handler.setButtonEnabled(BTN_SEARCH_REQ_TECH_NM, true);
        } else {
            for (int i = 0; i < scrnMsg.F.length(); i++) {
                scrnMsg.F.no(i).xxChkBox_F1.setInputProtected(true);
                scrnMsg.F.no(i).svcAccsPmitNum_F1.setInputProtected(true);
                scrnMsg.F.no(i).svcAccsPmitDescTxt_F1.setInputProtected(true);
                scrnMsg.F.no(i).effFromDt_F1.setInputProtected(true);
                scrnMsg.F.no(i).effToDt_F1.setInputProtected(true);
            }
            for (int i = 0; i < scrnMsg.G.length(); i++) {
                scrnMsg.G.no(i).xxChkBox_G1.setInputProtected(true);
                scrnMsg.G.no(i).techNm_G1.setInputProtected(true);
                scrnMsg.G.no(i).nonPrfTechCd_G1.setInputProtected(true);
                scrnMsg.G.no(i).effThruDt_G1.setInputProtected(true);
            }
            scrnMsg.prfTechCd_SA.setInputProtected(true);
            scrnMsg.reqTechCd_SA.setInputProtected(true);
            scrnMsg.xxAllPsnNm_S1.setInputProtected(true);
            scrnMsg.xxAllPsnNm_S2.setInputProtected(true);
            handler.setButtonEnabled(BTN_ADD_CERT, false);
            handler.setButtonEnabled(BTN_DEL_CERT, false);
            handler.setButtonEnabled(BTN_ADD_TECH, false);
            handler.setButtonEnabled(BTN_DEL_TECH, false);
            handler.setButtonEnabled(BTN_SEARCH_PRIM_TECH_NM, false);
            handler.setButtonEnabled(BTN_SEARCH_REQ_TECH_NM, false);
        }
    }

    /**
     * Check addCheckItem return UPDATE
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NMAL6720BMsg scrnMsg) {
        addHeaderCheckItem(scrnMsg);

        if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabAccountCheckItem(scrnMsg);
        } else if (TAB_CLASSIFICATIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabClassificationsCheckItem(scrnMsg);
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabContactsCheckItem(scrnMsg);
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabTransactionsCheckItem(scrnMsg);
        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabMsgNoteCheckItem(scrnMsg);
        } else if (TAB_SRVATTRB.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabSrvAttrbCheckItem(scrnMsg);
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/19 QC#20297(Sol#379) add start
            addTabShippingCheckItem(scrnMsg);
         // 2018/02/19 QC#20297(Sol#379) add start
        }
    }

    /**
     * addHeaderCheckItem
     * @param scrnMsg NMAL6720BMsg
     */
    public static void addHeaderCheckItem(NMAL6720BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.locNum_H1);

        scrnMsg.addCheckItem(scrnMsg.ctryCd_P1);

        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.thirdLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.frthLineAddr_H1);

        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_P1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.provNm_H1);
        // 2023/11/06 QC#61924 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        // 2023/11/06 QC#61924 Add Start

        scrnMsg.addCheckItem(scrnMsg.dsAcctRelnBillToFlg_BI);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_BI);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_BI);
        scrnMsg.addCheckItem(scrnMsg.primUsgFlg_BI);

        scrnMsg.addCheckItem(scrnMsg.dsAcctRelnShipToFlg_SH);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_SH);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_SH);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_SH);
        scrnMsg.addCheckItem(scrnMsg.primUsgFlg_SH);

        scrnMsg.addCheckItem(scrnMsg.dsLocNm_H1);
        // 2020/03/24 QC#55673 Add Start
        scrnMsg.addCheckItem(scrnMsg.addlLocNm);
        // 2020/03/24 QC#55673 Add End
        scrnMsg.addCheckItem(scrnMsg.telNum_H1);
        scrnMsg.addCheckItem(scrnMsg.actvFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_P1);

        scrnMsg.addCheckItem(scrnMsg.dplStsCd_P1);
        scrnMsg.addCheckItem(scrnMsg.embgoFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.xxDtTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.dplRsnTxt_H1);

    }

    /**
     * addDetailCheckItem
     * @param scrnMsg NMAL6720BMsg
     */
    public static void addTabAccountCheckItem(NMAL6720BMsg scrnMsg) {
        // QC#57316 Add Start
        scrnMsg.addCheckItem(scrnMsg.actvFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_BI);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_BI);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_SH);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_SH);
        // QC#57316 Add End
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNum_A1);
        }
    }

    private static void addTabClassificationsCheckItem(NMAL6720BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.glnNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dunsNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsUltDunsNum_H1);
        scrnMsg.addCheckItem(scrnMsg.hqDunsNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsPrntDunsNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsLocEmpNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsLocRevAmt_H1);
        scrnMsg.addCheckItem(scrnMsg.dsCustSicCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsCustSicDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.dsLastUpdDunsDt_H1);
        // QC#5749
        scrnMsg.addCheckItem(scrnMsg.dunsTradeStyleNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dunsActvCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dunsLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.dunsBizNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsLastRcvDunsTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.dsInsdCtyLimitFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.geoCd_H1);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsXrefAcctCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsXrefAcctNm_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsXrefAcctTpCd_P1);
        }
    }

    private static void addTabContactsCheckItem(NMAL6720BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).dsPrimLocFlg_C1);
        }

    }

    private static void addTabTransactionsCheckItem(NMAL6720BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsBlktPoNum_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsCrCardReqFlg_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsDefBillToCd_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsDefShipToCd_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsOvngtAllwFlg_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsPoExprDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsPoReqFlg_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsTrxRuleTpCd_P1);
        }

    }

    private static void addTabMsgNoteCheckItem(NMAL6720BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).dsCustMsgTxt_E1);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).dsBizAreaCd_P1);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).lineBizTpCd_P2);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).dsCustMsgTpCd_P1);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).dsPrintOnInvFlg_E1);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).effThruDt_E1);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).xxChkBox_E1);
        }
    }

    private static void addTabSrvAttrbCheckItem(NMAL6720BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.prfTechCd_SA);
        scrnMsg.addCheckItem(scrnMsg.reqTechCd_SA);

        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.F.no(i).xxChkBox_F1);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).effFromDt_F1);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).effToDt_F1);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).svcAccsPmitNum_F1);
        }

        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.G.no(i).xxChkBox_G1);
            scrnMsg.addCheckItem(scrnMsg.G.no(i).effThruDt_G1);
            scrnMsg.addCheckItem(scrnMsg.G.no(i).nonPrfTechCd_G1);
        }
    }

    /**
     * exceptMandatoryError
     * @param scrnMsg NMAL6720BMsg
     */
    public static void exceptMandatoryError(NMAL6720BMsg scrnMsg) {

        // Header
        if (scrnMsg.ctryCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6720Bean.ctryCd_P1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.ctryCd_P1.clearErrorInfo();
            }
        }
        if (scrnMsg.firstLineAddr_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6720Bean.firstLineAddr_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.firstLineAddr_H1.clearErrorInfo();
            }
        }
        if (scrnMsg.ctyAddr_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6720Bean.ctyAddr_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.ctyAddr_H1.clearErrorInfo();
            }
        }
        if (scrnMsg.stCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6720Bean.stCd_P1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.stCd_P1.clearErrorInfo();
            }
        }
        if (scrnMsg.postCd_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6720Bean.postCd_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.postCd_H1.clearErrorInfo();
            }
        }
        if (scrnMsg.lineBizTpCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6720Bean.lineBizTpCd_P1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.lineBizTpCd_P1.clearErrorInfo();
            }
        }

        if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NMAL6720_ABMsg abMsg = scrnMsg.A.no(i);
                if (abMsg.effFromDt_A1.isError()) {
                    EZDMessageInfo ezdMsgInfo = abMsg.getErrorInfo(NMAL6720Bean.effFromDt_A1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        abMsg.effFromDt_A1.clearErrorInfo();
                    }
                }
            }
        } else if (TAB_CLASSIFICATIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NMAL6720_BBMsg bbMsg = scrnMsg.B.no(i);
                if (bbMsg.dsXrefAcctTpCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = bbMsg.getErrorInfo(NMAL6720Bean.dsXrefAcctTpCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        bbMsg.dsXrefAcctTpCd_P1.clearErrorInfo();
                    }
                }
            }
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NMAL6720_DBMsg dbMsg = scrnMsg.D.no(i);
                if (dbMsg.dsTrxRuleTpCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = dbMsg.getErrorInfo(NMAL6720Bean.dsTrxRuleTpCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        dbMsg.dsTrxRuleTpCd_P1.clearErrorInfo();
                    }
                }
            }

        } else if (TAB_SRVATTRB.equals(scrnMsg.xxDplyTab.getValue())) {
            // Nothing
            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                NMAL6720_FBMsg fbMsg = scrnMsg.F.no(i);
                if (fbMsg.svcAccsPmitNum_F1.isError()) {
                    EZDMessageInfo ezdMsgInfo = fbMsg.getErrorInfo(NMAL6720Bean.svcAccsPmitNum_F1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        fbMsg.svcAccsPmitNum_F1.clearErrorInfo();
                    }
                }

                if (fbMsg.effFromDt_F1.isError()) {
                    EZDMessageInfo ezdMsgInfo = fbMsg.getErrorInfo(NMAL6720Bean.effFromDt_F1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        fbMsg.effFromDt_F1.clearErrorInfo();
                    }
                }
            }

            for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
                NMAL6720_GBMsg gbMsg = scrnMsg.G.no(i);
                if (gbMsg.nonPrfTechCd_G1.isError()) {
                    EZDMessageInfo ezdMsgInfo = gbMsg.getErrorInfo(NMAL6720Bean.nonPrfTechCd_G1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        gbMsg.nonPrfTechCd_G1.clearErrorInfo();
                    }
                }
            }

        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                NMAL6720_EBMsg ebMsg = scrnMsg.E.no(i);

                if (ebMsg.lineBizTpCd_P2.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.lineBizTpCd_P2);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.lineBizTpCd_P2.clearErrorInfo();
                    }
                }

                if (ebMsg.dsBizAreaCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.dsBizAreaCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.dsBizAreaCd_P1.clearErrorInfo();
                    }
                }

                if (ebMsg.dsCustMsgTpCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.dsCustMsgTpCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.dsCustMsgTpCd_P1.clearErrorInfo();
                    }
                }

                if (ebMsg.dsCustMsgTxt_E1.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.dsCustMsgTxt_E1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.dsCustMsgTxt_E1.clearErrorInfo();
                    }
                }
            }
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/19 QC#20297(Sol#379) add start

            for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
                NMAL6720_IBMsg ebMsg = scrnMsg.I.no(i);

                if (ebMsg.lineBizTpCd_P3.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.lineBizTpCd_P3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.lineBizTpCd_P3.clearErrorInfo();
                    }
                }

                if (ebMsg.dsBizAreaCd_P2.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.dsBizAreaCd_P2);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.dsBizAreaCd_P2.clearErrorInfo();
                    }
                }
                // 2018/12/13 QC#29135 Add Start
                if (ebMsg.effFromDt_I1.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6720Bean.effFromDt_I1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.effFromDt_I1.clearErrorInfo();
                    }
                }
                // 2018/12/13 QC#29135 Add End
            }
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    /**
     * chkMandatoryError
     * @param ezdMsgInfo EZDMessageInfo
     * @return boolean
     */
    public static boolean chkMandatoryError(EZDMessageInfo ezdMsgInfo) {

        if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
            return true;
        }
        return false;

    }

    // Del Start 2018/01/22 QC#20291(Sol#348)
//    private static boolean hasUpdateHeaderAuth(NMAL6720BMsg scrnMsg, List<String> functionIds) {
//
//        boolean result = false;
//        // Add Start 2016/11/17 QC#16037
//        if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE)) {
//            result = true;
//            // Add End 2016/11/17 QC#16037
//        } else if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
//            if (functionIds.contains(FUNC_ID_ACCOUNTS_UPDATE)) {
//                result = true;
//            }
//        } else if (TAB_CLASSIFICATIONS.equals(scrnMsg.xxDplyTab.getValue())) {
//            if (functionIds.contains(FUNC_ID_CLASSIFICATIONS_UPDATE)) {
//                result = true;
//            }
//        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
//            if (functionIds.contains(FUNC_ID_CONTACTS_UPDATE)) {
//                result = true;
//            }
//        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
//            if (functionIds.contains(FUNC_ID_TRANSACTIONS_UPDATE)) {
//                result = true;
//            }
//        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
//            if (functionIds.contains(FUNC_ID_INSTRUCTIONS_UPDATE)) {
//                result = true;
//            }
//        } else if (TAB_SRVATTRB.equals(scrnMsg.xxDplyTab.getValue())) {
//            if (functionIds.contains(FUNC_ID_SRVATTRB_UPDATE)) {
//                result = true;
//            }
//        }
//        return result;
//    }
    // Del End 2018/01/22 QC#20291(Sol#348)

    /**
     * Set array for ZYPL0310
     * @param p NMAL6720_PBMsgArray
     * @param size int
     * @return Object
     */
    public static Object[] toArray_popupForZYPL0310(NMAL6720_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_P.getValue();
        }
        return param;
    }

    /**
     * setBgColor
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColor(NMAL6720BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForAccount(scrnMsg);
        } else if (TAB_CLASSIFICATIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForAcctCrossRef(scrnMsg);
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForContact(scrnMsg);
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForTransaction(scrnMsg);
        } else if (TAB_SRVATTRB.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForCerReq(scrnMsg);
            setBgColorForDoNotSendTec(scrnMsg);
        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForInstruction(scrnMsg);
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/19 QC#20297(Sol#379) add start
            setBgColorForShipping(scrnMsg);
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    /**
     * setBgColorForAccount
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForAccount(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * setBgColorForAccount
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForAcctCrossRef(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("B", scrnMsg.B);
        if (scrnMsg.B.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
    }

    /**
     * setBgColorForContact
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForContact(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("C", scrnMsg.C);
        if (scrnMsg.C.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("C", scrnMsg.C);
        }
    }

    /**
     * setBgColorForTransaction
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForTransaction(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("D", scrnMsg.D);
        if (scrnMsg.D.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("D", scrnMsg.D);
        }
    }

    /**
     * setBgColorForCerReq
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForCerReq(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("F", scrnMsg.F);
        if (scrnMsg.F.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("F", scrnMsg.F);
        }
    }

    /**
     * setBgColorForDoNotSendTec
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForDoNotSendTec(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("G", scrnMsg.G);
        if (scrnMsg.G.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("G", scrnMsg.G);
        }
    }

    /**
     * setBgColorForInstruction
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForInstruction(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("E", scrnMsg.E);
        if (scrnMsg.E.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("E", scrnMsg.E);
        }
    }

    // QC#6011
    /**
     * @param p NMAL6720_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popup(NMAL6720_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_P;
        }
        return param;
    }

    // QC#4505
    /**
     * @param scrnMsg NMAL6720BMsg
     * @param glblCmpyCd String
     * @param eventName String
     * @return Object[]
     */
    public static Object[] prepareAddressLookupPopupParameter(NMAL6720BMsg scrnMsg, String glblCmpyCd, String eventName) {
        scrnMsg.xxScrEventNm_P.setValue(eventName);

        Object[] params = new Object[7];

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

        baseSql.append("AND C.GLBL_CMPY_CD = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG = '0' ");
        baseSql.append("AND C.CNTY_PK = R.CNTY_PK ");

        List<Object[]> whereList = new ArrayList<Object[]>();
        addWhereCondition(whereList, "City", "CTY_ADDR", scrnMsg.ctyAddr_H1.getValue(), "Y");
        addWhereCondition(whereList, "State", "ST_CD", scrnMsg.stCd_P1.getValue(), "Y");
        // Mod Start 2018/07/31 QC#27488
        String postCd = scrnMsg.postCd_H1.getValue();
        if (!ORIG_POST_WITH_CNTY_ERR.equals(scrnMsg.xxRsltCd.getValue())
                && !ORIG_POST_WITHOUT_CNTY_ERR.equals(scrnMsg.xxRsltCd.getValue())) {
            if (ZYPCommonFunc.hasValue(postCd) && postCd.length() >= 6) {
                postCd = postCd.substring(0, 5);
                postCd += "%";
            }
        }
//        if (ZYPCommonFunc.hasValue(postCd) && postCd.length() >= 6) {
//            postCd = postCd.substring(0, 5);
//            postCd += "%";
//        }
        addWhereCondition(whereList, "Postal Code", "POST_CD", postCd, "Y");
        if (!ORIG_POST_WITHOUT_CNTY_ERR.equals(scrnMsg.xxRsltCd.getValue())
                && !FIVE_DIGIT_POST_WITHOUT_CNTY_ERR.equals(scrnMsg.xxRsltCd.getValue())) {
            addWhereCondition(whereList, "County", "CNTY_NM", scrnMsg.cntyNm_H1.getValue(), "Y");
        } else {
            addWhereCondition(whereList, "County", "CNTY_NM", null, "Y");
        }
//        addWhereCondition(whereList, "County", "CNTY_NM", scrnMsg.cntyNm_H1.getValue(), "Y");
        // Mod End 2018/07/31 QC#27488

        List<Object[]> columnList = new ArrayList<Object[]>();
        addDisplayColumn(columnList, "City", "CTY_ADDR", BigDecimal.valueOf(25), "Y");
        addDisplayColumn(columnList, "State", "ST_CD", BigDecimal.valueOf(5), "Y");
        addDisplayColumn(columnList, "Postal Code", "POST_CD", BigDecimal.valueOf(10), "Y");
        addDisplayColumn(columnList, "County", "CNTY_NM", BigDecimal.valueOf(30), "Y");

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        addSortCondition(sortConditionList, "CTY_ADDR", "ASC");
        addSortCondition(sortConditionList, "ST_CD", "ASC");
        addSortCondition(sortConditionList, "POST_CD", "ASC");
        addSortCondition(sortConditionList, "CNTY_NM", "ASC");

        scrnMsg.L.clear();

        params[0] = "";
        params[1] = "Address Lookup Popup";
        params[2] = baseSql.toString();
        params[3] = whereList;
        params[4] = columnList;
        params[5] = sortConditionList;
        params[6] = scrnMsg.L;

        return params;
    }

    private static void addWhereCondition(List<Object[]> whereList, String labelName, String dbColumnName, String initValue, String likeConditionFlag) {
        Object[] whereArray = new Object[4];
        whereArray[0] = labelName;
        whereArray[1] = dbColumnName;
        whereArray[2] = initValue;
        whereArray[3] = likeConditionFlag;
        whereList.add(whereArray);
    }

    private static void addDisplayColumn(List<Object[]> columnList, String labelName, String dbColumnName, BigDecimal displaySize, String linkFlag) {
        Object[] columnArray = new Object[4];
        columnArray[0] = labelName;
        columnArray[1] = dbColumnName;
        columnArray[2] = displaySize;
        columnArray[3] = linkFlag;
        columnList.add(columnArray);
    }

    private static void addSortCondition(List<Object[]> sortConditionList, String dbColumnName, String orderBy) {
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = dbColumnName;
        sortConditionArray[1] = orderBy;
        sortConditionList.add(sortConditionArray);
    }

    /**
     * setDplModDt
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setDplModDt(NMAL6720BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.dplModDtTmTs_H1)) {
            scrnMsg.xxDtTxt_H1.setValue(ZYPDateUtil.convertFormat(scrnMsg.dplModDtTmTs_H1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.getJavaDateFormatString(), S21CalendarUtil.SEPARATOR_SLASH));

        }
    }

    // 2017/11/27 QC#20828 Add Start
    private static boolean isMergeProsLocToCust(NMAL6720BMsg scrnMsg) {

        if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            return false;
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                return true;
            }
        }

        return false;
    }
    // 2017/11/27 QC#20828 Add End

    // 2018/02/19 QC#20297(Sol#379) add start
    private static void addTabShippingCheckItem(NMAL6720BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.I.no(i).dsBizAreaCd_P2);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).lineBizTpCd_P3);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).frtCondCd_P1);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).shpgSvcLvlCd_P1);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).effThruDt_I1);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).xxChkBox_I1);
            // 2018/12/13 QC#29315 Add Start
            scrnMsg.addCheckItem(scrnMsg.I.no(i).xxChkBox_ID);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).vndCd_I3);
            // 2023/01/13 QC#60860 Add Start
            scrnMsg.addCheckItem(scrnMsg.I.no(i).carrNm_I3);
            // 2023/01/13 QC#60860 Add End
            scrnMsg.addCheckItem(scrnMsg.I.no(i).dsCarrAcctNum_I1);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).effFromDt_I1);
            // 2018/12/13 QC#29315 Add End
        }
    }

    /**
     * @param scrnMsg NMAL6720BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isShippingTabUpdate(NMAL6720BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and DS_ACCT_TP(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
        if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_SHIPPING_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6720BMsg
     * @param userId String
     */
    private static final void controlScreenTabShipping(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6720BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (isShippingTabUpdate(scrnMsg, functionIds)) {
            for (int i = 0; i < scrnMsg.I.length(); i++) {
                scrnMsg.I.no(i).xxChkBox_I1.setInputProtected(false);
                scrnMsg.I.no(i).lineBizTpCd_P3.setInputProtected(false);
                scrnMsg.I.no(i).dsBizAreaCd_P2.setInputProtected(false);
                scrnMsg.I.no(i).frtCondCd_P1.setInputProtected(false);
                scrnMsg.I.no(i).shpgSvcLvlCd_P1.setInputProtected(false);
                scrnMsg.I.no(i).effThruDt_I1.setInputProtected(false);
                // 2018/12/13 QC#29315 Add Start
                scrnMsg.I.no(i).xxChkBox_ID.setInputProtected(false);
                scrnMsg.I.no(i).vndCd_I3.setInputProtected(false);
                scrnMsg.I.no(i).dsCarrAcctNum_I1.setInputProtected(false);
                scrnMsg.I.no(i).effFromDt_I1.setInputProtected(false);
                // 2018/12/13 QC#29315 Add End
                if (ZYPCommonFunc.hasValue(scrnMsg.I.no(i).dsCustShpgDefPk_I1)) {
                    handler.setButtonEnabled(BTN_ATTACH, i, true);
                } else {
                    handler.setButtonEnabled(BTN_ATTACH, i, false);
                }
            }
            handler.setButtonEnabled(BTN_ADD_SHIPPING, true);
            handler.setButtonEnabled(BTN_DEL_SHIPPING, true);
        } else {
            for (int i = 0; i < scrnMsg.I.length(); i++) {
                scrnMsg.I.no(i).xxChkBox_I1.setInputProtected(true);
                scrnMsg.I.no(i).lineBizTpCd_P3.setInputProtected(true);
                scrnMsg.I.no(i).dsBizAreaCd_P2.setInputProtected(true);
                scrnMsg.I.no(i).frtCondCd_P1.setInputProtected(true);
                scrnMsg.I.no(i).shpgSvcLvlCd_P1.setInputProtected(true);
                scrnMsg.I.no(i).effThruDt_I1.setInputProtected(true);
                // 2018/12/13 QC#29315 Add Start
                scrnMsg.I.no(i).xxChkBox_ID.setInputProtected(true);
                scrnMsg.I.no(i).vndCd_I3.setInputProtected(true);
                scrnMsg.I.no(i).dsCarrAcctNum_I1.setInputProtected(true);
                scrnMsg.I.no(i).effFromDt_I1.setInputProtected(true);
                // 2018/12/13 QC#29315 Add End
                if (ZYPCommonFunc.hasValue(scrnMsg.I.no(i).dsCustShpgDefPk_I1)) {
                    handler.setButtonEnabled(BTN_ATTACH, i, true);
                } else {
                    handler.setButtonEnabled(BTN_ATTACH, i, false);
                }
            }
            handler.setButtonEnabled(BTN_ADD_SHIPPING, false);
            handler.setButtonEnabled(BTN_DEL_SHIPPING, false);
        }
    }
    
    /**
     * setBgColorForInstruction
     * @param scrnMsg NMAL6720BMsg
     */
    public static void setBgColorForShipping(NMAL6720BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("I", scrnMsg.I);
        if (scrnMsg.I.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("I", scrnMsg.I);
        }
    }
    // 2018/02/19 QC#20297(Sol#379) add end
}
