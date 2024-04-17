/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700.common;

import static business.servlet.NMAL6700.constant.NMAL6700Constant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBStringItem;
import parts.common.EZDCMsgArray;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.NMAL6700BMsg;
import business.servlet.NMAL6700.NMAL6700Bean;
import business.servlet.NMAL6700.NMAL6700_CBMsg;
import business.servlet.NMAL6700.NMAL6700_DBMsg;
import business.servlet.NMAL6700.NMAL6700_EBMsg;
import business.servlet.NMAL6700.NMAL6700_FBMsg;
import business.servlet.NMAL6700.NMAL6700_GBMsg;
import business.servlet.NMAL6700.NMAL6700_IBMsg;
import business.servlet.NMAL6700.NMAL6700_JBMsg;
import business.servlet.NMAL6700.NMAL6700_KBMsg;
import business.servlet.NMAL6700.NMAL6700_MBMsg;
import business.servlet.NMAL6700.NMAL6700_NBMsg;
import business.servlet.NMAL6700.NMAL6700_PBMsgArray;
import business.servlet.NMAL6700.NMAL6700_SBMsg;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreator;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreatorIF;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewDataColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewNodeColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewRadioColumn;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/20   Fujitsu         R.Nakamura      Update          QC#17631
 * 2017/07/06   Hitachi         J.Kim           Update          QC#16966
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/01/30   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/02/14   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/05/16   Fujitsu         Hd.Sugawara     Update          QC#26041
 * 2018/07/13   Fujitsu         M.Ishii         Update          QC#26613
 * 2018/07/30   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/13   Fujitsu         Y.Matsui        Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2018/08/30   Fujitsu         W.Honda         Update          QC#27869
 * 2018/12/10   Fujitsu         M.Ishii         Update          QC#29315
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/01/20   Hitachi         S.Fujita        Update          QC#61011
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6700CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6700BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6700BMsg scrnMsg, String userId) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
        setBgColor(scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL6700BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6700BMsg scrnMsg) {
        // Add Start 2018/01/22 QC#20291(Sol#348)
        int submit = 0;
        int downLoad = 0;
        // Add End 2018/01/22 QC#20291(Sol#348)

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        // Del Start 2018/01/22 QC#20291(Sol#348)
        //handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        // Del End 2018/01/22 QC#20291(Sol#348)
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        // Del Start 2018/01/22 QC#20291(Sol#348)
        //handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], 0, null);
        // Del End 2018/01/22 QC#20291(Sol#348)
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (TAB_ADDRESSES.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_ADDR_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //    handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isAddressTabSubmit(scrnMsg, functionIds)) {
            if (isAddressTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
                downLoad = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isRelationshipsTabSubmit(scrnMsg, functionIds)) {
            if (isRelationshipsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_RELNSHIPS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isRelationshipsTabSubmit(scrnMsg, functionIds)) {
            if (isRelationshipsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_CONTACT_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isContactsTabSubmit(scrnMsg, functionIds)) {
            if (isContactsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_MARKETING.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_MARKETING_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isMarketingTabSubmit(scrnMsg, functionIds)) {
            if (isMarketingTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_TRANSACTION_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isTransactionsTabSubmit(scrnMsg, functionIds)) {
            if (isTransactionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_CR_CLT.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_COLLECTION_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isCollectionsTabSubmit(scrnMsg, functionIds)) {
            if (isCollectionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
            // Add Start 2018/08/21 QC#27222-2
            if (isDiChkItemUpdate(scrnMsg, functionIds)) {
                submit = 1;
            }
            // Add End 2018/08/21 QC#27222-2
        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_INV_BLLG_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isInvBllgTabSubmit(scrnMsg, functionIds)) {
            if (isInvBllgTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_BANK_ACCT.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_BANK_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isBankAcctTabSubmit(scrnMsg, functionIds)) {
            if (isBankAcctTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_MSG_NOTE.equals(scrnMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE)) {
            //    handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            //}
            // Mod Start 2018/05/16 QC#26041
            //if (isInstractionsTabSubmit(scrnMsg, functionIds)) {
            if (isInstractionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // Mod End 2018/01/22 QC#20291(Sol#348)
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/14 QC#20297(Sol#379) add start
            // Mod Start 2018/05/16 QC#26041
            //if (isShippingTabSubmit(scrnMsg, functionIds)) {
            if (isShippingTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/05/16 QC#26041
                submit = 1;
            }
            // 2018/02/14 QC#20297(Sol#379) add end

        // Add Start 2018/07/30 QC#27222
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            if (isTaxingTabUpdate(scrnMsg, functionIds)) {
                submit = 1;
            }
        // Add End 2018/07/30 QC#27222
        }

        // Add Start 2018/01/22 QC#20291(Sol#348)
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], submit, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], downLoad, null);
        // Add End 2018/01/22 QC#20291(Sol#348)
    }

    // Add Start 2018/01/22 QC#20291(Sol#348)
    // Del Start 2018/05/16 QC#26041
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isAddressTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_ADDR_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_ADDR_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isRelationshipsTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_RELATIONSHIP_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isContactsTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_CONTACT_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_CONTACT_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isMarketingTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_MARKETING_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_MARKETING_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isTransactionsTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_TRANSACTION_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_TRANSACTION_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isCollectionsTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_COLLECTION_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_COLLECTION_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isInvBllgTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_INV_BLLG_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_INV_BLLG_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isBankAcctTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_BANK_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_BANK_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isInstractionsTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_INSTRUCTION_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
//
//    /**
//     * @param scrnMsg NMAL6700BMsg
//     * @param functionIds List<String>
//     * @return boolean
//     */
//    public static boolean isShippingTabSubmit(NMAL6700BMsg scrnMsg, List<String> functionIds) {
//        boolean result = false;
//
//        if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE)) {
//            // User can submit CUSTOMER.
//            // User can submit PROSPECT, becouse if category is
//            // PORSPECT, user can change only category.
//            // (prospect -> categoty).
//            result = true;
//        } else if (functionIds.contains(FUNC_ID_SHIPPING_PROS_UPDATE) && //
//                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
//            // User can submit only PROSPECT.
//            result = true;
//        }
//
//        return result;
//    }
    // Del End 2018/05/16 QC#26041

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isAddressTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_ADDR_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_ADDR_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_ADDR_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isRelationshipsTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_RELATIONSHIP_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isContactsTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_CONTACT_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_CONTACT_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_CONTACT_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isMarketingTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_MARKETING_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_MARKETING_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_MARKETING_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isTransactionsTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_TRANSACTION_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_TRANSACTION_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_TRANSACTION_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isCollectionsTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_COLLECTION_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_COLLECTION_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_COLLECTION_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isInvBllgTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_INV_BLLG_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_INV_BLLG_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_INV_BLLG_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isBankAcctTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_BANK_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_BANK_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_BANK_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isInstractionsTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE) && //
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_INSTRUCTION_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }
    // Add End 2018/01/22 QC#20291(Sol#348)

    // 2018/02/14 QC#20297(Sol#379) add start
    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isShippingTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        // If match security and category(CUSTOMER/PROSPECT),
        // user update header fields and detail fields.
        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE) &&
        //        DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE)) {
            // Mod End 2018/05/16 QC#26041
            result = true;
        } else if (functionIds.contains(FUNC_ID_SHIPPING_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }
    // 2018/02/14 QC#20297(Sol#379) add end

    // Add Start 2018/05/16 QC#26041
    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isGeneralUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        if (functionIds.contains(FUNC_ID_ACCT_UPDATE)) {
            result = true;
        } else if (functionIds.contains(FUNC_ID_ACCT_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }
    // Add End 2018/05/16 QC#26041

    // Add Start 2018/07/30 QC#27222
    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isTaxingTabUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        if (functionIds.contains(FUNC_ID_TAXING_UPDATE)) {
            result = true;
        } else if (functionIds.contains(FUNC_ID_TAXING_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }
    // Add Start 2018/07/30 QC#27222

    // Add Start 2018/08/21 QC#27222-2
    /**
     * @param scrnMsg NMAL6700BMsg
     * @param functionIds List<String>
     * @return boolean
     */
    public static boolean isDiChkItemUpdate(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        boolean result = false;

        if (functionIds.contains(FUNC_ID_DI_CHECK_ITEM_UPDATE)) {
            result = true;
        } else if (functionIds.contains(FUNC_ID_DI_CHECK_ITEM_PROS_UPDATE) && //
                DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
            result = true;
        }

        return result;
    }
    // Add End 2018/08/21 QC#27222-2

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6700BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6700BMsg scrnMsg, String userId) {
        controlScreenHeaderFields(userProfileService, handler, scrnMsg, userId);
        controlScreenDetailFields(userProfileService, handler, scrnMsg, userId);

    }

    /**
     * controlScreenHeaderFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6700BMsg
     * @param userId String
     */
    private static final void controlScreenHeaderFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6700BMsg scrnMsg, String userId) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Add Start 2018/01/22 QC#20291(Sol#348)
        boolean updateFlag = false;
        // Del Start 2018/05/16 QC#26041
        //boolean onlyCategoryUpdate = false;
        // Del End 2018/05/16 QC#26041

        // Mod Start 2018/05/16 QC#26041
        //if (functionIds.contains(FUNC_ID_ACCT_UPDATE)) {
        //    updateFlag = true;
        //    if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        //        // If category is PORSPECT, user can change only
        //        // category(prospect -> categoty).
        //        onlyCategoryUpdate = true;
        //    } else {
        //        onlyCategoryUpdate = false;
        //    }
        //}
        //if (functionIds.contains(FUNC_ID_ACCT_PROS_UPDATE) && //
        //        DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        //    updateFlag = true;
        //    onlyCategoryUpdate = false;
        //}
        updateFlag = isGeneralUpdate(scrnMsg, functionIds);
        // Mod End 2018/05/16 QC#26041
        // Add End 2018/01/22 QC#20291(Sol#348)

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (functionIds.contains(FUNC_ID_ACCT_UPDATE)) {
            //enableAllHeaderFields(scrnMsg);
        if (updateFlag) {
            // Del Start 2018/05/16 QC#26041
            //if (onlyCategoryUpdate) {
            //    protectedAllHeaderFields(scrnMsg);
            //    handler.setButtonEnabled(BTN_GET_INTER_COMPANY_NM[0], false);
            //    handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], false);
            //    handler.setButtonEnabled(BTN_OPENWIN_UPLD_LOC[0], false);
            //
            //    // Change category protection.
            //    scrnMsg.dsAcctTpCd_H3.setInputProtected(false);
            //} else {
                // Del End 2018/05/16 QC#26041
                enableAllHeaderFields(scrnMsg, functionIds);
            // Mod End 2018/01/22 QC#20291(Sol#348)
            handler.setButtonEnabled(BTN_GET_INTER_COMPANY_NM[0], true);
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], true);
            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                handler.setButtonEnabled(BTN_OPENWIN_UPLD_LOC[0], true);
            } else {
                handler.setButtonEnabled(BTN_OPENWIN_UPLD_LOC[0], false);
            }

            // Add Start 2018/01/22 QC#20291(Sol#348)
            // Del Start 2018/05/16 QC#26041
            //}
            // Del End 2018/05/16 QC#26041
            // Add End 2018/01/22 QC#20291(Sol#348)
        } else {
            protectedAllHeaderFields(scrnMsg);
            handler.setButtonEnabled(BTN_GET_INTER_COMPANY_NM[0], false);
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], false);
            handler.setButtonEnabled(BTN_OPENWIN_UPLD_LOC[0], false);
        }
        scrnMsg.xxTabProt_01.setInputProtected(false);
        scrnMsg.xxTabProt_02.setInputProtected(false);
        scrnMsg.xxTabProt_03.setInputProtected(false);
        scrnMsg.xxTabProt_04.setInputProtected(false);
        scrnMsg.xxTabProt_05.setInputProtected(false);
        scrnMsg.xxTabProt_06.setInputProtected(false);
        scrnMsg.xxTabProt_07.setInputProtected(false);
        scrnMsg.xxTabProt_08.setInputProtected(false);
        scrnMsg.xxTabProt_09.setInputProtected(false);
        scrnMsg.xxTabProt_10.setInputProtected(false);
        scrnMsg.xxTabProt_11.setInputProtected(false);
        // Add Start 2018/07/30 QC#27222
        scrnMsg.xxTabProt_12.setInputProtected(false);
        // Add End 2018/07/30 QC#27222

        scrnMsg.dsLocRevAmt_M1.setAppFracDigit(2);
        scrnMsg.crLimitAmt_U1.setAppFracDigit(2);
    }

    // Mod Start 2018/01/22 QC#20291(Sol#348)
    //private static void enableAllHeaderFields(NMAL6700BMsg scrnMsg) {
    private static void enableAllHeaderFields(NMAL6700BMsg scrnMsg, List<String> functionIds) {
        // Mod End 2018/01/22 QC#20291(Sol#348)
        scrnMsg.dsAcctNm_H1.setInputProtected(false);

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        //    scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
        //    scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(false);
        //} else {
        //    scrnMsg.dsAcctTpCd_H3.setInputProtected(false);
        //    scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(true);
        //}
        // Mod Start 2018/05/16 QC#26041
        //if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
        //    if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue())) {
        //        scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
        //    } else {
        //        if (functionIds.contains(FUNC_ID_ACCT_UPDATE)) {
        //            scrnMsg.dsAcctTpCd_H3.setInputProtected(false);
        //        } else {
        //            scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
        //        }
        //    }
        //} else {
        //    // When user add new account, user is not able to select
        //    // category.
        //    // It is not available both category.
        //    scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
        //}
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && //
                DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H3.getValue()) && //
                S21StringUtil.isEquals(scrnMsg.dsAcctTpCd_H3.getValue(), scrnMsg.dsAcctTpCd_HD.getValue())) {
            scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
        } else {
            if (functionIds.contains(FUNC_ID_ACCT_UPDATE)) {
                scrnMsg.dsAcctTpCd_H3.setInputProtected(false);
            } else {
                scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
            }
        }
        // Mod End 2018/05/16 QC#26041
        // Mod End 2018/01/22 QC#20291(Sol#348)

        scrnMsg.xxChkBox_H1.setInputProtected(false);

        scrnMsg.dsAcctItrlFlg_H3.setInputProtected(false);
        scrnMsg.dsAcctClsCd_H3.setInputProtected(false);
        scrnMsg.coaAfflCd_H1.setInputProtected(false);
        scrnMsg.coaAfflNm_H1.setInputProtected(true);
        scrnMsg.coaChCd_H1.setInputProtected(false);
        scrnMsg.coaChNm_H1.setInputProtected(true);
        scrnMsg.dsAcctDlrCd_H3.setInputProtected(false);

        scrnMsg.effThruDt_H1.setInputProtected(true);
        scrnMsg.xxChkBox_H2.setInputProtected(true);

        scrnMsg.dsAcctLegalNm_H1.setInputProtected(false);
        scrnMsg.dbaNm_H1.setInputProtected(false);
        scrnMsg.dsAcctDunsNm_H1.setInputProtected(false);
        scrnMsg.dsAcctAltNm_H1.setInputProtected(false);
        scrnMsg.dsXtrnlRefTxt_H1.setInputProtected(true);
        scrnMsg.dsDataSrcTxt_H1.setInputProtected(true);

        if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_H1.getValue())) {
            scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(true);
            // 2023/11/06 QC#61924 Add Start
            scrnMsg.xxChkBox_H3.setInputProtected(false);
            // 2023/11/06 QC#61924 Add End
        } else {
            scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(false);
            // 2023/11/06 QC#61924 Add Start
            scrnMsg.xxChkBox_H3.setInputProtected(true);
            // 2023/11/06 QC#61924 Add End
        }
    }

    /**
     * protectedAllHeaderFields
     * @param NMAL6700BMsg scrnMsg
     */
    private static final void protectedAllHeaderFields(NMAL6700BMsg scrnMsg) {

        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.xxChkBox_H1.setInputProtected(true);
        scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(true);
        scrnMsg.dsAcctTpCd_H3.setInputProtected(true);
        scrnMsg.dsAcctItrlFlg_H3.setInputProtected(true);
        scrnMsg.dsAcctClsCd_H3.setInputProtected(true);
        scrnMsg.coaAfflCd_H1.setInputProtected(true);
        scrnMsg.coaAfflNm_H1.setInputProtected(true);
        scrnMsg.coaChCd_H1.setInputProtected(true);
        scrnMsg.coaChNm_H1.setInputProtected(true);
        scrnMsg.dsAcctDlrCd_H3.setInputProtected(true);
        // 2023/11/06 QC#61924 Add Start
        scrnMsg.xxChkBox_H3.setInputProtected(true);
        // 2023/11/06 QC#61924 Add End

        scrnMsg.effThruDt_H1.setInputProtected(true);
        scrnMsg.xxChkBox_H2.setInputProtected(true);

        scrnMsg.dsAcctLegalNm_H1.setInputProtected(true);
        scrnMsg.dbaNm_H1.setInputProtected(true);
        scrnMsg.dsAcctDunsNm_H1.setInputProtected(true);
        scrnMsg.dsAcctAltNm_H1.setInputProtected(true);
        scrnMsg.dsXtrnlRefTxt_H1.setInputProtected(true);
        scrnMsg.dsDataSrcTxt_H1.setInputProtected(true);

    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6700BMsg
     * @param userId String
     */
    private static final void controlScreenDetailFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6700BMsg scrnMsg, String userId) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        boolean authAddrRead = functionIds.contains(FUNC_ID_ADDR_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authAddrEdit = functionIds.contains(FUNC_ID_ADDR_UPDATE);
        boolean authAddrEdit = false;
        if (functionIds.contains(FUNC_ID_ADDR_UPDATE) || //
                functionIds.contains(FUNC_ID_ADDR_PROS_UPDATE)) {
            authAddrEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authAddrRead || authAddrEdit) {
            scrnMsg.xxTabProt_01.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_01.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authHrchRead = functionIds.contains(FUNC_ID_HIERARCHY_INQUIRY);
        if (authHrchRead) {
            scrnMsg.xxTabProt_02.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_02.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authRelnshipRead = functionIds.contains(FUNC_ID_RELATIONSHIP_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authRelnshipEdit = functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE);
        boolean authRelnshipEdit = false;
        if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE) || //
                functionIds.contains(FUNC_ID_RELATIONSHIP_PROS_UPDATE)) {
            authRelnshipEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authRelnshipRead  || authRelnshipEdit) {
            scrnMsg.xxTabProt_03.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_03.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authContactRead = functionIds.contains(FUNC_ID_CONTACT_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authContactEdit = functionIds.contains(FUNC_ID_CONTACT_UPDATE);
        boolean authContactEdit = false;
        if (functionIds.contains(FUNC_ID_CONTACT_UPDATE) || //
                functionIds.contains(FUNC_ID_CONTACT_PROS_UPDATE)) {
            authContactEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authContactRead  || authContactEdit) {
            scrnMsg.xxTabProt_04.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_04.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authMarketingRead = functionIds.contains(FUNC_ID_MARKETING_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authMarketingEdit = functionIds.contains(FUNC_ID_MARKETING_UPDATE);
        boolean authMarketingEdit = false;
        if (functionIds.contains(FUNC_ID_MARKETING_UPDATE) || //
                functionIds.contains(FUNC_ID_MARKETING_PROS_UPDATE)) {
            authMarketingEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authMarketingRead  || authMarketingEdit) {
            scrnMsg.xxTabProt_05.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_05.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authTransactionRead = functionIds.contains(FUNC_ID_TRANSACTION_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authTransactionEdit = functionIds.contains(FUNC_ID_TRANSACTION_UPDATE);
        boolean authTransactionEdit = false;
        if (functionIds.contains(FUNC_ID_TRANSACTION_UPDATE) || //
                functionIds.contains(FUNC_ID_TRANSACTION_PROS_UPDATE)) {
            authTransactionEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authTransactionRead  || authTransactionEdit) {
            scrnMsg.xxTabProt_06.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_06.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authCollectionRead = functionIds.contains(FUNC_ID_COLLECTION_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authCollectionEdit = functionIds.contains(FUNC_ID_COLLECTION_UPDATE);
        boolean authCollectionEdit = false;
        if (functionIds.contains(FUNC_ID_COLLECTION_UPDATE) || //
                functionIds.contains(FUNC_ID_COLLECTION_PROS_UPDATE)) {
            authCollectionEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authCollectionRead  || authCollectionEdit) {
            scrnMsg.xxTabProt_07.setValue(ZYPConstant.FLG_ON_Y);
        } else {
        	// Mod Start 2018/08/21 QC#27222-2
//            scrnMsg.xxTabProt_07.setValue(ZYPConstant.FLG_OFF_N);

            boolean authDiChkItemRead = functionIds.contains(FUNC_ID_DI_CHECK_ITEM_INQUIRY);
            boolean authDiChkItemEdit = false;
            if (functionIds.contains(FUNC_ID_DI_CHECK_ITEM_UPDATE) || //
                    functionIds.contains(FUNC_ID_DI_CHECK_ITEM_PROS_UPDATE)) {
            	authDiChkItemEdit = true;
            }
            if (authDiChkItemRead  || authDiChkItemEdit) {
                scrnMsg.xxTabProt_07.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.xxTabProt_07.setValue(ZYPConstant.FLG_OFF_N);
            }
            // Mod End 2018/08/21 QC#27222-2
        }

        boolean authIinvBllgRead = functionIds.contains(FUNC_ID_INV_BLLG_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authIinvBllgEdit = functionIds.contains(FUNC_ID_INV_BLLG_UPDATE);
        boolean authIinvBllgEdit = false;
        if (functionIds.contains(FUNC_ID_INV_BLLG_UPDATE) || //
                functionIds.contains(FUNC_ID_INV_BLLG_PROS_UPDATE)) {
            authIinvBllgEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authIinvBllgRead  || authIinvBllgEdit) {
            scrnMsg.xxTabProt_08.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_08.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authBankRead = functionIds.contains(FUNC_ID_BANK_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authBankEdit = functionIds.contains(FUNC_ID_BANK_UPDATE);
        boolean authBankEdit = false;
        if (functionIds.contains(FUNC_ID_BANK_UPDATE) || //
                functionIds.contains(FUNC_ID_BANK_PROS_UPDATE)) {
            authBankEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authBankRead  || authBankEdit) {
            scrnMsg.xxTabProt_09.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_09.setValue(ZYPConstant.FLG_OFF_N);
        }

        boolean authInstructionRead = functionIds.contains(FUNC_ID_INSTRUCTION_INQUIRY);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //boolean authInstructionEdit = functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE);
        boolean authInstructionEdit = false;
        if (functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE) || //
                functionIds.contains(FUNC_ID_INSTRUCTION_PROS_UPDATE)) {
            authInstructionEdit = true;
        }
        // Mod End 2018/01/22 QC#20291(Sol#348)

        if (authInstructionRead  || authInstructionEdit) {
            scrnMsg.xxTabProt_10.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_10.setValue(ZYPConstant.FLG_OFF_N);
        }

        // 2018/02/14 QC#20297(Sol#379) add start
        boolean authShippingRead = functionIds.contains(FUNC_ID_SHIPPING_INQUIRY);
        boolean authShippingEdit = false;
        if (functionIds.contains(FUNC_ID_SHIPPING_UPDATE) || //
                functionIds.contains(FUNC_ID_SHIPPING_PROS_UPDATE)) {
            authShippingEdit = true;
        }

        if (authShippingRead  || authShippingEdit) {
            scrnMsg.xxTabProt_11.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_11.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 2018/02/14 QC#20297(Sol#379) add end

        // Add Start 2018/07/30 QC#27222
        boolean authTaxingRead = functionIds.contains(FUNC_ID_TAXING_INQUIRY);
        boolean authTaxingEdit = false;
        if (functionIds.contains(FUNC_ID_TAXING_UPDATE) || //
                functionIds.contains(FUNC_ID_TAXING_PROS_UPDATE)) {
        	authTaxingEdit = true;
        }

        if (authTaxingRead || authTaxingEdit) {
            scrnMsg.xxTabProt_12.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxTabProt_12.setValue(ZYPConstant.FLG_OFF_N);
        }
        // Add End 2018/07/30 QC#27222

        if (TAB_ADDRESSES.equals(scrnMsg.xxDplyTab.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                // Mod Start 2018/01/22 QC#20291(Sol#348)
                //if (functionIds.contains(FUNC_ID_ADDR_UPDATE)) {
                if (isAddressTabUpdate(scrnMsg, functionIds)) {
                    // Mod End 2018/01/22 QC#20291(Sol#348)
                    if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_HD.getValue())
                            && (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_H1.getValue())
                            || scrnMsg.A.getValidCount() > 0)) {
                        handler.setButtonEnabled(BTN_ADD_LOCATION[0], false);
                    } else {
                        handler.setButtonEnabled(BTN_ADD_LOCATION[0], true);
                    }
                    handler.setButtonEnabled(BTN_DWND_LOC_TMPL[0], true);
                    scrnMsg.xxChkBox_AX.setInputProtected(false);
                } else {
                    handler.setButtonEnabled(BTN_ADD_LOCATION[0], false);
                    handler.setButtonEnabled(BTN_DWND_LOC_TMPL[0], false);
                    scrnMsg.xxChkBox_AX.setInputProtected(false);
                }

            } else {
                handler.setButtonEnabled(BTN_ADD_LOCATION[0], false);
                handler.setButtonEnabled(BTN_DWND_LOC_TMPL[0], false);
                scrnMsg.xxChkBox_AX.setInputProtected(true);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                handler.setButtonEnabled(BTN_OPENWIN_ADDR_SRCH[0], true);
            } else {
                handler.setButtonEnabled(BTN_OPENWIN_ADDR_SRCH[0], false);
            }

            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], 0, null);
            } else {
                handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], 1, null);
            }
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.A.no(i).xxChkBox_AB.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_AS.setInputProtected(true);
                // Mod Start 2018/01/22 QC#20291(Sol#348)
                //if (functionIds.contains(FUNC_ID_ADDR_UPDATE)
                //        && DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_HD.getValue())) {
                if (isAddressTabUpdate(scrnMsg, functionIds) //
                        && DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_HD.getValue())) {
                    // Mod End 2018/01/22 QC#20291(Sol#348)
                    scrnMsg.A.no(i).xxChkBox_AP.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).xxChkBox_AP.setInputProtected(true);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)) {
                    scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
                }
                scrnMsg.A.no(i).firstLineAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).scdLineAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).ctyAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).stCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).postCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxRelnDsAcctTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctStsNm_A1.setInputProtected(true);
            }
        } else if (TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                scrnMsg.xxChkBox_BI.setInputProtected(false);
                scrnMsg.xxChkBox_BH.setInputProtected(false);
                handler.setButtonEnabled(BTN_SHOW_ACCT_FROM_HRCH[0], true);
            } else {
                scrnMsg.xxChkBox_BI.setInputProtected(true);
                scrnMsg.xxChkBox_BH.setInputProtected(true);
                handler.setButtonEnabled(BTN_SHOW_ACCT_FROM_HRCH[0], false);
            }
        } else if (TAB_RELNSHIPS.equals(scrnMsg.xxDplyTab.getValue())) {
            // Add Start 2023/01/20 QC#61011
            if (scrnMsg.C.getValidCount() > 0) {
                handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], 1, null);
            } else {
                handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], 0, null);
            }
            // Add End 2023/01/20 QC#61011
            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_RELATIONSHIP_UPDATE)) {
            if (isRelationshipsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)
                if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                    for (int i = 0; i < scrnMsg.C.length(); i++) {
                        scrnMsg.C.no(i).dsAcctRelnTpCd_C3.setInputProtected(false);

                        scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(false);
                        scrnMsg.C.no(i).xxChkBox_CB.setInputProtected(false);
                        scrnMsg.C.no(i).xxChkBox_CS.setInputProtected(false);
                        scrnMsg.C.no(i).xxChkBox_CR.setInputProtected(false);
                        scrnMsg.C.no(i).effFromDt_C1.setInputProtected(false);
                        scrnMsg.C.no(i).effThruDt_C1.setInputProtected(false);
                        handler.setButtonEnabled(BTN_SHOW_ACCT[0], i, true);
                        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).dsAcctRelnPk_C1)) {
                            scrnMsg.C.no(i).dsAcctNum_C1.setInputProtected(true);
                            handler.setButtonEnabled(BTN_OPENWIN_ACCT_SRCH[0], i, false);
                            handler.setButtonEnabled(BTN_GET_DS_ACCT_NM[0], i, false);
                        } else {
                            scrnMsg.C.no(i).dsAcctNum_C1.setInputProtected(false);
                            handler.setButtonEnabled(BTN_OPENWIN_ACCT_SRCH[0], i, true);
                            handler.setButtonEnabled(BTN_GET_DS_ACCT_NM[0], i, true);
                        }
                    }
                    // Mod Start 2018/01/22 QC#20291(Sol#348)
                    //scrnMsg.xxChkBox_AX.setInputProtected(false);
                    scrnMsg.xxChkBox_CX.setInputProtected(false);
                    // Mod End 2018/01/22 QC#20291(Sol#348)
                    handler.setButtonEnabled(BTN_ADD_RELNSHIP[0], true);
                    handler.setButtonEnabled(BTN_DEL_RELNSHIP[0], true);
                    // START 2017/07/06 J.Kim [QC#16966,ADD]
                    handler.setButtonEnabled(BTN_FILTER_RELNSHIP[0], true);
                    // END 2017/07/06 J.Kim [QC#16966,ADD]
                } else {
                    // Mod Start 2018/01/22 QC#20291(Sol#348)
                    //scrnMsg.xxChkBox_AX.setInputProtected(true);
                    scrnMsg.xxChkBox_CX.setInputProtected(true);
                    // Mod End 2018/01/22 QC#20291(Sol#348)
                    handler.setButtonEnabled(BTN_ADD_RELNSHIP[0], false);
                    handler.setButtonEnabled(BTN_DEL_RELNSHIP[0], false);
                    // START 2017/07/06 J.Kim [QC#16966,ADD]
                    handler.setButtonEnabled(BTN_FILTER_RELNSHIP[0], false);
                    // END 2017/07/06 J.Kim [QC#16966,ADD]
                }

            } else {
                for (int i = 0; i < scrnMsg.C.length(); i++) {
                    scrnMsg.C.no(i).dsAcctRelnTpCd_C3.setInputProtected(true);
                    scrnMsg.C.no(i).dsAcctNum_C1.setInputProtected(true);
                    scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(true);
                    scrnMsg.C.no(i).xxChkBox_CB.setInputProtected(true);
                    scrnMsg.C.no(i).xxChkBox_CS.setInputProtected(true);
                    scrnMsg.C.no(i).xxChkBox_CR.setInputProtected(true);
                    scrnMsg.C.no(i).effFromDt_C1.setInputProtected(true);
                    scrnMsg.C.no(i).effThruDt_C1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_SHOW_ACCT[0], i, true);
                    handler.setButtonEnabled(BTN_OPENWIN_ACCT_SRCH[0], i, false);
                    handler.setButtonEnabled(BTN_GET_DS_ACCT_NM[0], i, false);
                }
                // Mod Start 2018/01/22 QC#20291(Sol#348)
                //scrnMsg.xxChkBox_AX.setInputProtected(true);
                scrnMsg.xxChkBox_CX.setInputProtected(false);
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonEnabled(BTN_ADD_RELNSHIP[0], false);
                handler.setButtonEnabled(BTN_DEL_RELNSHIP[0], false);
                // START 2017/07/06 J.Kim [QC#16966,ADD]
                // Mod Start 2018/01/22 QC#20291(Sol#348)
                //handler.setButtonEnabled(BTN_FILTER_RELNSHIP[0], false);
                handler.setButtonEnabled(BTN_FILTER_RELNSHIP[0], true);
                // Mod End 2018/01/22 QC#20291(Sol#348)
                // END 2017/07/06 J.Kim [QC#16966,ADD]
            }
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_CONTACT_UPDATE)) {
            if (isContactsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)
                if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                    scrnMsg.xxChkBox_DX.setInputProtected(false);
                    handler.setButtonEnabled(BTN_ADD_CONTACT[0], true);
                    for (int i = 0; i < scrnMsg.D.length(); i++) {
                        // Add Start 2018/01/22 QC#20291(Sol#348)
                        scrnMsg.D.no(i).ctacPsnPk_D1.setInputProtected(false);
                        // Add End 2018/01/22 QC#20291(Sol#348)
                        // Mod Start 2018/08/30 QC#27869
//                        scrnMsg.D.no(i).dsPrimLocFlg_D1.setInputProtected(false);
                        if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).dsCtacPsnRelnPk_D1)) {
                            scrnMsg.D.no(i).dsPrimLocFlg_D1.setInputProtected(false);
                        } else {
                            scrnMsg.D.no(i).dsPrimLocFlg_D1.setInputProtected(true);
                        }
                        // Mod End 2018/08/30 QC#27869
                    }
                } else {
                    scrnMsg.xxChkBox_DX.setInputProtected(true);
                    handler.setButtonEnabled(BTN_ADD_CONTACT[0], false);
                }

            } else {
                scrnMsg.xxChkBox_DX.setInputProtected(false);
                handler.setButtonEnabled(BTN_ADD_CONTACT[0], false);
                for (int i = 0; i < scrnMsg.D.length(); i++) {
                    // Add Start 2018/01/22 QC#20291(Sol#348)
                    scrnMsg.D.no(i).ctacPsnPk_D1.setInputProtected(true);
                    // Add End 2018/01/22 QC#20291(Sol#348)
                    scrnMsg.D.no(i).dsPrimLocFlg_D1.setInputProtected(true);
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                handler.setButtonEnabled(NMAL6700Constant.BTN_OPEN_WIN_CTAC_SEARCH, true);
            } else {
                handler.setButtonEnabled(NMAL6700Constant.BTN_OPEN_WIN_CTAC_SEARCH, false);
            }

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && functionIds.contains(FUNC_ID_CONTACT_UPDATE)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && isContactsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)
                handler.setButtonEnabled(NMAL6700Constant.BTN_OPEN_WIN_UPLD_CTAC, true);
            } else {
                handler.setButtonEnabled(NMAL6700Constant.BTN_OPEN_WIN_UPLD_CTAC, false);
            }

        } else if (TAB_MARKETING.equals(scrnMsg.xxDplyTab.getValue())) {

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_MARKETING_UPDATE)) {
            if (isMarketingTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)
                scrnMsg.dsAcctDunsNum_M1.setInputProtected(false);
                scrnMsg.dsUltDunsNum_M1.setInputProtected(false);
                scrnMsg.dsLocEmpNum_M1.setInputProtected(false);
                scrnMsg.dsLocRevAmt_M1.setInputProtected(false);
                scrnMsg.dsCustSicCd_M1.setInputProtected(false);
                scrnMsg.dsCustSicDescTxt_M1.setInputProtected(false);
                scrnMsg.dsLastUpdDunsDt_M1.setInputProtected(false);
                scrnMsg.dsAcctUrl_M1.setInputProtected(false);
                scrnMsg.xxScrItem7Txt_M1.setInputProtected(false);
                scrnMsg.xxScrItem7Txt_M2.setInputProtected(false);
                scrnMsg.xxScrItem7Txt_M3.setInputProtected(false);
                scrnMsg.xxScrItem7Txt_M4.setInputProtected(false);
                scrnMsg.xxScrItem7Txt_M5.setInputProtected(false);
                scrnMsg.xxScrItem7Txt_M6.setInputProtected(false);

                handler.setButtonEnabled(BTN_ADD_GROUP[0], true);
                handler.setButtonEnabled(BTN_DEL_GROUP[0], true);
                for (int i = 0; i < scrnMsg.E.length(); i++) {
                    scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_GP[0], true);
                    scrnMsg.E.no(i).dsBizAreaCd_E3.setInputProtected(false);
                    scrnMsg.E.no(i).dsAcctGrpCd_E3.setInputProtected(false);
                    scrnMsg.E.no(i).dsAcctGrpDescTxt_E3.setInputProtected(true);
                    scrnMsg.E.no(i).effFromDt_E1.setInputProtected(false);
                    scrnMsg.E.no(i).effThruDt_E1.setInputProtected(false);
                }

                handler.setButtonEnabled(BTN_ADD_SER_REQ[0], true);
                handler.setButtonEnabled(BTN_DEL_SER_REQ[0], true);
                for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
                    scrnMsg.N.no(i).xxChkBox_N1.setInputProtected(false);
                    scrnMsg.N.no(i).svcAccsPmitNum_N1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCS_PMIT, true);
                    scrnMsg.N.no(i).svcAccsPmitDescTxt_N1.setInputProtected(false);
                    scrnMsg.N.no(i).effFromDt_N1.setInputProtected(false);
                    scrnMsg.N.no(i).effToDt_N1.setInputProtected(false);
                }
            } else {
                scrnMsg.dsAcctDunsNum_M1.setInputProtected(true);
                scrnMsg.dsUltDunsNum_M1.setInputProtected(true);
                scrnMsg.dsLocEmpNum_M1.setInputProtected(true);
                scrnMsg.dsLocRevAmt_M1.setInputProtected(true);
                scrnMsg.dsCustSicCd_M1.setInputProtected(true);
                scrnMsg.dsCustSicDescTxt_M1.setInputProtected(true);
                scrnMsg.dsLastUpdDunsDt_M1.setInputProtected(true);
                scrnMsg.dsAcctUrl_M1.setInputProtected(true);
                scrnMsg.xxScrItem7Txt_M1.setInputProtected(true);
                scrnMsg.xxScrItem7Txt_M2.setInputProtected(true);
                scrnMsg.xxScrItem7Txt_M3.setInputProtected(true);
                scrnMsg.xxScrItem7Txt_M4.setInputProtected(true);
                scrnMsg.xxScrItem7Txt_M5.setInputProtected(true);
                scrnMsg.xxScrItem7Txt_M6.setInputProtected(true);

                handler.setButtonEnabled(BTN_ADD_GROUP[0], false);
                handler.setButtonEnabled(BTN_DEL_GROUP[0], false);
                for (int i = 0; i < scrnMsg.E.length(); i++) {
                    scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCT_GP[0], false);
                    scrnMsg.E.no(i).dsBizAreaCd_E3.setInputProtected(true);
                    scrnMsg.E.no(i).dsAcctGrpCd_E3.setInputProtected(true);
                    scrnMsg.E.no(i).dsAcctGrpDescTxt_E3.setInputProtected(true);
                    scrnMsg.E.no(i).effFromDt_E1.setInputProtected(true);
                    scrnMsg.E.no(i).effThruDt_E1.setInputProtected(true);
                }

                handler.setButtonEnabled(BTN_ADD_SER_REQ[0], false);
                handler.setButtonEnabled(BTN_DEL_SER_REQ[0], false);
                for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
                    scrnMsg.N.no(i).xxChkBox_N1.setInputProtected(true);
                    scrnMsg.N.no(i).svcAccsPmitNum_N1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_ACCS_PMIT, false);
                    scrnMsg.N.no(i).svcAccsPmitDescTxt_N1.setInputProtected(true);
                    scrnMsg.N.no(i).effFromDt_N1.setInputProtected(true);
                    scrnMsg.N.no(i).effToDt_N1.setInputProtected(true);
                }
            }
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.coaCcNm_F1.setInputProtected(true);

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_TRANSACTION_UPDATE)) {
            if (isTransactionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)

                handler.setButtonEnabled(BTN_ADD_TRANSACTION[0], true);
                handler.setButtonEnabled(BTN_DEL_TRANSACTION[0], true);
                for (int i = 0; i < scrnMsg.F.length(); i++) {
                    scrnMsg.F.no(i).xxChkBox_F1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPEN_WIN_BILL_TO[0], true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_SHIP_TO[0], true);
                    scrnMsg.F.no(i).dsOvngtAllwFlg_F1.setInputProtected(false);
                    scrnMsg.F.no(i).dsTrxRuleTpCd_F3.setInputProtected(false);
                    scrnMsg.F.no(i).dsPoReqFlg_F1.setInputProtected(false);
                    // 2022/11/25 Add Start
                    if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.F.no(i).dsPoReqFlg_F1.getValue())){
                        scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(true);
                    } else {
                        scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(false);
                    }
                    // 2022/11/25 Add End
                    scrnMsg.F.no(i).dsBlktPoNum_F1.setInputProtected(false);
                    scrnMsg.F.no(i).dsPoExprDt_F1.setInputProtected(false);
                    scrnMsg.F.no(i).dsDefBillToCd_F1.setInputProtected(false);
                    scrnMsg.F.no(i).dsDefShipToCd_F1.setInputProtected(false);
                    scrnMsg.F.no(i).custEffLvlCd_F3.setInputProtected(false);
                    scrnMsg.F.no(i).dsCrCardReqFlg_F1.setInputProtected(false);
                }

                handler.setButtonEnabled(BTN_ADD_SPECIAL_HANDLONG[0], true);
                handler.setButtonEnabled(BTN_DEL_SPECIAL_HANDLONG[0], true);
                for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
                    scrnMsg.S.no(i).xxChkBox_S1.setInputProtected(false);
                    scrnMsg.S.no(i).dsSpclHdlgTpCd_S3.setInputProtected(false);
                    scrnMsg.S.no(i).dsSpclHdlgTpValCd_S3.setInputProtected(false);
                    scrnMsg.S.no(i).effFromDt_S1.setInputProtected(false);
                    scrnMsg.S.no(i).effThruDt_S1.setInputProtected(false);
                    scrnMsg.S.no(i).custEffLvlCd_S3.setInputProtected(false);
                }

                handler.setButtonEnabled(BTN_OPEN_COA, true);
                scrnMsg.coaCcCd_F1.setInputProtected(false);
                handler.setButtonEnabled(BTN_GET_COA_CC_NM[0], true);
            } else {

                handler.setButtonEnabled(BTN_ADD_TRANSACTION[0], false);
                handler.setButtonEnabled(BTN_DEL_TRANSACTION[0], false);
                for (int i = 0; i < scrnMsg.F.length(); i++) {
                    scrnMsg.F.no(i).xxChkBox_F1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_BILL_TO[0], false);
                    handler.setButtonEnabled(BTN_OPEN_WIN_SHIP_TO[0], false);
                    scrnMsg.F.no(i).dsOvngtAllwFlg_F1.setInputProtected(true);
                    scrnMsg.F.no(i).dsTrxRuleTpCd_F3.setInputProtected(true);
                    scrnMsg.F.no(i).dsPoReqFlg_F1.setInputProtected(true);
                    // 2022/11/25 Add Start
                    scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(true);
                    // 2022/11/25 Add End
                    scrnMsg.F.no(i).dsBlktPoNum_F1.setInputProtected(true);
                    scrnMsg.F.no(i).dsPoExprDt_F1.setInputProtected(true);
                    scrnMsg.F.no(i).dsDefBillToCd_F1.setInputProtected(true);
                    scrnMsg.F.no(i).dsDefShipToCd_F1.setInputProtected(true);
                    scrnMsg.F.no(i).custEffLvlCd_F3.setInputProtected(true);
                    scrnMsg.F.no(i).dsCrCardReqFlg_F1.setInputProtected(true);
                }

                handler.setButtonEnabled(BTN_ADD_SPECIAL_HANDLONG[0], false);
                handler.setButtonEnabled(BTN_DEL_SPECIAL_HANDLONG[0], false);
                for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
                    scrnMsg.S.no(i).xxChkBox_S1.setInputProtected(true);
                    scrnMsg.S.no(i).dsSpclHdlgTpCd_S3.setInputProtected(true);
                    scrnMsg.S.no(i).dsSpclHdlgTpValCd_S3.setInputProtected(true);
                    scrnMsg.S.no(i).effFromDt_S1.setInputProtected(true);
                    scrnMsg.S.no(i).effThruDt_S1.setInputProtected(true);
                    scrnMsg.S.no(i).custEffLvlCd_S3.setInputProtected(true);
                }

                handler.setButtonEnabled(BTN_OPEN_COA, false);
                scrnMsg.coaCcCd_F1.setInputProtected(true);
                handler.setButtonEnabled(BTN_GET_COA_CC_NM[0], false);
            }
        } else if (TAB_CR_CLT.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.cltCustTpNm_U1.setInputProtected(true);
            // 2018/07/13 QC#26613 Mod Start
//            scrnMsg.cltPtfoNm_U1.setInputProtected(true);
            scrnMsg.cltPtfoDescTxt_U1.setInputProtected(true);
            // 2018/07/13 QC#26613 Mod End
            

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_COLLECTION_UPDATE)) {
            if (isCollectionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)

                handler.setButtonEnabled(BTN_OPEN_WIN_TEMP, true);
                scrnMsg.xxLinkAncr_U1.setInputProtected(false);
                scrnMsg.dsCustArTmplNm_U1.setInputProtected(false);
                handler.setButtonEnabled(BTN_ADD_TEMPLATE[0], true);

                scrnMsg.ccyCd_U3.setInputProtected(false);
                scrnMsg.custCrRtgCd_U3.setInputProtected(false);
                scrnMsg.crLimitAmt_U1.setInputProtected(false);
                scrnMsg.crChkReqTpCd_U3.setInputProtected(false);
                scrnMsg.crRiskClsCd_U3.setInputProtected(false);
                // START 2018/01/25 [QC#22095, ADD]
                scrnMsg.contrCrRiskClsCd_U3.setInputProtected(false);
                // END   2018/01/25 [QC#22095, ADD]
                scrnMsg.pmtTermCashDiscCd_U3.setInputProtected(false);
                scrnMsg.custCrRtgCd_U3.setInputProtected(false);
                scrnMsg.custCrRtgCd_U3.setInputProtected(false);
                scrnMsg.ovrdPmtTermFlg_U1.setInputProtected(false);
                scrnMsg.cashOrCcReqFlg_U1.setInputProtected(false);
                scrnMsg.custHardHldFlg_U1.setInputProtected(false);
                scrnMsg.arStmtFlg_U1.setInputProtected(false);
                // START 2018/01/25 [QC#22095, ADD]
                //scrnMsg.sendCrBalStmtFlg_U1.setInputProtected(false);
                // END   2018/01/25 [QC#22095, ADD]
                
                scrnMsg.arStmtIssCycleCd_U3.setInputProtected(false);
                // START 2018/01/16 [QC#21734, DEL]
                // scrnMsg.dunFlg_U1.setInputProtected(false);
                // END   2018/01/16 [QC#21734, DEL]
                scrnMsg.dsCltAcctStsCd_U3.setInputProtected(false);
                scrnMsg.lateFeeAmt_U1.setInputProtected(false);
                handler.setButtonEnabled(BTN_OPEN_WIN_CLT_CUST, true);
                scrnMsg.cltCustTpCd_U1.setInputProtected(false);
                handler.setButtonEnabled(BTN_GET_CLT_CUST[0], true);
                handler.setButtonEnabled(BTN_OPEN_WIN_CLT_PTFO, true);
                scrnMsg.cltPtfoCd_U1.setInputProtected(false);
                handler.setButtonEnabled(BTN_GET_CLT_PTFO[0], true);
                scrnMsg.lateFeeFlg_U1.setInputProtected(false);
                // START 2018/01/25 [QC#22095, ADD]
                //scrnMsg.sendCrBalStmtFlg_U1.setInputProtected(false);
                // END   2018/01/25 [QC#22095, ADD]
                // Del Start 2018/07/30 QC#27222
//                scrnMsg.dsCustTaxCd_U1.setInputProtected(false);
//                scrnMsg.dsCustTaxCalcCd_U3.setInputProtected(false);
//                scrnMsg.dsTaxExemFlg_U1.setInputProtected(false);
//                scrnMsg.dsExemExprDt_U1.setInputProtected(false);
//                scrnMsg.dsTaxGrpExemCd_U3.setInputProtected(false);
                // Del End 2018/07/30 QC#27222
                // Del Start 2018/08/21 QC#27222-2
//                scrnMsg.dsTaxPrntTpCd_U3.setInputProtected(false);
//                // Add Start 2018/08/13 QC#27222
//                scrnMsg.autoCashHrchCd_U3.setInputProtected(false);
//                // Add End 2018/08/13 QC#27222
                // Del End 2018/08/21 QC#27222-2
            } else {
                handler.setButtonEnabled(BTN_OPEN_WIN_TEMP, false);
                scrnMsg.xxLinkAncr_U1.setInputProtected(true);
                scrnMsg.dsCustArTmplNm_U1.setInputProtected(true);
                handler.setButtonEnabled(BTN_ADD_TEMPLATE[0], false);

                scrnMsg.ccyCd_U3.setInputProtected(true);
                scrnMsg.custCrRtgCd_U3.setInputProtected(true);
                scrnMsg.crLimitAmt_U1.setInputProtected(true);
                scrnMsg.crChkReqTpCd_U3.setInputProtected(true);
                scrnMsg.crRiskClsCd_U3.setInputProtected(true);
                // START 2018/01/25 [QC#22095, ADD]
                scrnMsg.contrCrRiskClsCd_U3.setInputProtected(true);
                // END   2018/01/25 [QC#22095, ADD]
                scrnMsg.pmtTermCashDiscCd_U3.setInputProtected(true);
                scrnMsg.custCrRtgCd_U3.setInputProtected(true);
                scrnMsg.custCrRtgCd_U3.setInputProtected(true);
                scrnMsg.ovrdPmtTermFlg_U1.setInputProtected(true);
                scrnMsg.cashOrCcReqFlg_U1.setInputProtected(true);
                scrnMsg.custHardHldFlg_U1.setInputProtected(true);

                scrnMsg.arStmtFlg_U1.setInputProtected(true);
                // START 2018/01/25 [QC#22095, ADD]
                //scrnMsg.sendCrBalStmtFlg_U1.setInputProtected(true);
                // END   2018/01/25 [QC#22095, ADD]
                scrnMsg.arStmtIssCycleCd_U3.setInputProtected(true);
                // START 2018/01/16 [QC#21734, DEL]
                // scrnMsg.dunFlg_U1.setInputProtected(true);
                // END   2018/01/16 [QC#21734, DEL]
                scrnMsg.dsCltAcctStsCd_U3.setInputProtected(true);
                scrnMsg.lateFeeAmt_U1.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPEN_WIN_CLT_CUST, false);
                scrnMsg.cltCustTpCd_U1.setInputProtected(true);
                handler.setButtonEnabled(BTN_GET_CLT_CUST[0], false);
                handler.setButtonEnabled(BTN_OPEN_WIN_CLT_PTFO, false);
                scrnMsg.cltPtfoCd_U1.setInputProtected(true);
                handler.setButtonEnabled(BTN_GET_CLT_PTFO[0], false);
                scrnMsg.lateFeeFlg_U1.setInputProtected(true);

                // Del Start 2018/07/30 QC#27222
//                scrnMsg.dsCustTaxCd_U1.setInputProtected(true);
//                scrnMsg.dsCustTaxCalcCd_U3.setInputProtected(true);
//                scrnMsg.dsTaxExemFlg_U1.setInputProtected(true);
//                scrnMsg.dsExemExprDt_U1.setInputProtected(true);
//                scrnMsg.dsTaxGrpExemCd_U3.setInputProtected(true);
                // Del End 2018/07/30 QC#27222
                // Del Start 2018/08/21 QC#27222-2
//                scrnMsg.dsTaxPrntTpCd_U3.setInputProtected(true);
//                // Add Start 2018/08/13 QC#27222
//                scrnMsg.autoCashHrchCd_U3.setInputProtected(true);
//                // Add End 2018/08/13 QC#27222
                // Del End 2018/08/21 QC#27222-2
            }

            // Add Start 2018/08/21 QC#27222-2
            if (isDiChkItemUpdate(scrnMsg, functionIds)) {
                scrnMsg.dsTaxExemFlg_U1.setInputProtected(false);
                scrnMsg.dsExemExprDt_U1.setInputProtected(false);
                scrnMsg.dsTaxPrntTpCd_U3.setInputProtected(false);
                scrnMsg.autoCashHrchCd_U3.setInputProtected(false);
            } else {
                scrnMsg.dsTaxExemFlg_U1.setInputProtected(true);
                scrnMsg.dsExemExprDt_U1.setInputProtected(true);
                scrnMsg.dsTaxPrntTpCd_U3.setInputProtected(true);
                scrnMsg.autoCashHrchCd_U3.setInputProtected(true);
            }
            // Add End 2018/08/21 QC#27222-2

        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_INV_BLLG_UPDATE)) {
            if (isInvBllgTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)
                scrnMsg.custEffLvlCd_V3.setInputProtected(false);

                if (scrnMsg.G.getValidCount() >= scrnMsg.G.length()) {
                    handler.setButtonEnabled(BTN_ADD_INV_RULE[0], false);
                } else {
                    handler.setButtonEnabled(BTN_ADD_INV_RULE[0], true);
                }
                if (scrnMsg.G.getValidCount() > 0) {
                    handler.setButtonEnabled(BTN_DEL_INV_RULE[0], true);
                } else {
                    handler.setButtonEnabled(BTN_DEL_INV_RULE[0], false);
                }

                for (int i = 0; i < scrnMsg.G.length(); i++) {
                    scrnMsg.G.no(i).custInvSrcCd_G3.setInputProtected(false);
                    scrnMsg.G.no(i).custBllgTpCd_G3.setInputProtected(false);
                    scrnMsg.G.no(i).custBllgVcleCd_G3.setInputProtected(false);
                    scrnMsg.G.no(i).custConslTermCd_G3.setInputProtected(false);
                    scrnMsg.G.no(i).xxGenlFldAreaTxt_G1.setInputProtected(false);
                    scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G1.setInputProtected(true);
                    scrnMsg.G.no(i).xxGenlFldAreaTxt_G2.setInputProtected(false);
                    scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G2.setInputProtected(true);
                    scrnMsg.G.no(i).custEmlMsgTxt_G1.setInputProtected(false);
                    scrnMsg.G.no(i).defInvGrpCd_G3.setInputProtected(false);
                    scrnMsg.G.no(i).xxChkBox_G1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPEN_WIN_RESRC[0], true);
                    scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G1.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPEN_WIN_CTAC_PSN[0], true);
                    scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G2.setInputProtected(false);
                    scrnMsg.G.no(i).invGrpNum_G1.setInputProtected(false);
                    scrnMsg.G.no(i).custEffLvlCd_G3.setInputProtected(false);
                }

                handler.setButtonEnabled(BTN_ADD_ATTRIBUTE[0], true);
                handler.setButtonEnabled(BTN_DEL_ATTRIBUTE[0], true);
                for (int i = 0; i < scrnMsg.K.length(); i++) {
                    scrnMsg.K.no(i).bllgAttrbNm_K1.setInputProtected(false);
                    scrnMsg.K.no(i).bllgAttrbValTxt_K1.setInputProtected(false);
                    scrnMsg.K.no(i).bllgAttrbEnblFlg_K1.setInputProtected(false);
                    scrnMsg.K.no(i).bllgAttrbReqFlg_K1.setInputProtected(false);
                    scrnMsg.K.no(i).xxChkBox_K1.setInputProtected(false);
                    scrnMsg.K.no(i).custEffLvlCd_K3.setInputProtected(false);
                }

                scrnMsg.defBaseCycleCd_V3.setInputProtected(false);
                scrnMsg.defBaseTpCd_V3.setInputProtected(false);
                scrnMsg.defUsgCycleCd_V3.setInputProtected(false);
                scrnMsg.defUsgTpCd_V3.setInputProtected(false);
                // START 2022/03/22 [QC#59683, MOD]
//                scrnMsg.dsBillTgtrFlg_V1.setInputProtected(false);
                scrnMsg.dsInvTgtrTpCd_V1.setInputProtected(false);
                // END   2022/03/22 [QC#59683, MOD]
            } else {
                scrnMsg.custEffLvlCd_V3.setInputProtected(true);

                handler.setButtonEnabled(BTN_ADD_INV_RULE[0], false);
                handler.setButtonEnabled(BTN_DEL_INV_RULE[0], false);
                for (int i = 0; i < scrnMsg.G.length(); i++) {
                    scrnMsg.G.no(i).custInvSrcCd_G3.setInputProtected(true);
                    scrnMsg.G.no(i).custBllgTpCd_G3.setInputProtected(true);
                    scrnMsg.G.no(i).custBllgVcleCd_G3.setInputProtected(true);
                    scrnMsg.G.no(i).custConslTermCd_G3.setInputProtected(true);
                    scrnMsg.G.no(i).xxGenlFldAreaTxt_G1.setInputProtected(true);
                    scrnMsg.G.no(i).xxGenlFldAreaTxt_G2.setInputProtected(true);
                    scrnMsg.G.no(i).custEmlMsgTxt_G1.setInputProtected(true);
                    scrnMsg.G.no(i).defInvGrpCd_G3.setInputProtected(true);
                    scrnMsg.G.no(i).xxChkBox_G1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_RESRC[0], false);
                    scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPEN_WIN_CTAC_PSN[0], false);
                    scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G2.setInputProtected(true);
                    scrnMsg.G.no(i).invGrpNum_G1.setInputProtected(true);
                    scrnMsg.G.no(i).custEffLvlCd_G3.setInputProtected(true);
                }

                handler.setButtonEnabled(BTN_ADD_ATTRIBUTE[0], false);
                handler.setButtonEnabled(BTN_DEL_ATTRIBUTE[0], false);
                for (int i = 0; i < scrnMsg.K.length(); i++) {
                    scrnMsg.K.no(i).bllgAttrbNm_K1.setInputProtected(true);
                    scrnMsg.K.no(i).bllgAttrbValTxt_K1.setInputProtected(true);
                    scrnMsg.K.no(i).bllgAttrbEnblFlg_K1.setInputProtected(true);
                    scrnMsg.K.no(i).bllgAttrbReqFlg_K1.setInputProtected(true);
                    scrnMsg.K.no(i).xxChkBox_K1.setInputProtected(true);
                    scrnMsg.K.no(i).custEffLvlCd_K3.setInputProtected(true);
                }

                scrnMsg.defBaseCycleCd_V3.setInputProtected(true);
                scrnMsg.defBaseTpCd_V3.setInputProtected(true);
                scrnMsg.defUsgCycleCd_V3.setInputProtected(true);
                scrnMsg.defUsgTpCd_V3.setInputProtected(true);
                // START 2022/03/22 [QC#59683, MOD]
//                scrnMsg.dsBillTgtrFlg_V1.setInputProtected(true);
                scrnMsg.dsInvTgtrTpCd_V1.setInputProtected(true);
                // END   2022/03/22 [QC#59683, MOD]
            }
        } else if (TAB_BANK_ACCT.equals(scrnMsg.xxDplyTab.getValue())) {

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_BANK_UPDATE)) {
            if (isBankAcctTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)
                // 2018/12/10 QC#29315 Del Start
//                handler.setButtonEnabled(BUTTON_NAME_INSERT_BANK_ACCT, true);
//                handler.setButtonEnabled(BUTTON_NAME_DELETE_BANK_ACCT, true);
//                for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
//                    scrnMsg.W.no(i).xxChkBox_W1.setInputProtected(false);
//                    scrnMsg.W.no(i).vndCd_W3.setInputProtected(false);
//                    scrnMsg.W.no(i).dsCarrAcctNum_W1.setInputProtected(false);
//                    scrnMsg.W.no(i).effFromDt_W1.setInputProtected(false);
//                    scrnMsg.W.no(i).effThruDt_W1.setInputProtected(false);
//                    scrnMsg.W.no(i).xxChkBox_WD.setInputProtected(false);
//                    scrnMsg.W.no(i).xxChkBox_WS.setInputProtected(false);
//                }
                // 2018/12/10 QC#29315 Del End
                for (int i = 0; i < scrnMsg.I.length(); i++) {
                    scrnMsg.I.no(i).effThruDt_I1.setInputProtected(false);
                }
            } else {
                // 2018/12/10 QC#29315 Del Start
//                handler.setButtonEnabled(BUTTON_NAME_INSERT_BANK_ACCT, false);
//                handler.setButtonEnabled(BUTTON_NAME_DELETE_BANK_ACCT, false);
//                for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
//                    scrnMsg.W.no(i).xxChkBox_W1.setInputProtected(true);
//                    scrnMsg.W.no(i).vndCd_W3.setInputProtected(true);
//                    scrnMsg.W.no(i).dsCarrAcctNum_W1.setInputProtected(true);
//                    scrnMsg.W.no(i).effFromDt_W1.setInputProtected(true);
//                    scrnMsg.W.no(i).effThruDt_W1.setInputProtected(true);
//                    scrnMsg.W.no(i).xxChkBox_WD.setInputProtected(true);
//                    scrnMsg.W.no(i).xxChkBox_WS.setInputProtected(true);
//                }
                // 2018/12/10 QC#29315 Del End
                for (int i = 0; i < scrnMsg.I.length(); i++) {
                    scrnMsg.I.no(i).effThruDt_I1.setInputProtected(true);
                }
            }
            // 2018/12/10 QC#29315 Mod End
        } else if (TAB_MSG_NOTE.equals(scrnMsg.xxDplyTab.getValue())) {

            // Mod Start 2018/01/22 QC#20291(Sol#348)
            //if (functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE)) {
            if (isInstractionsTabUpdate(scrnMsg, functionIds)) {
                // Mod End 2018/01/22 QC#20291(Sol#348)

                handler.setButtonEnabled(BTN_ADD_MSG_NOTE[0], true);
                handler.setButtonEnabled(BTN_DEL_MSG_NOTE[0], true);
                for (int i = 0; i < scrnMsg.J.length(); i++) {
                    scrnMsg.J.no(i).xxChkBox_J1.setInputProtected(false);
                    scrnMsg.J.no(i).lineBizTpCd_J3.setInputProtected(false);
                    scrnMsg.J.no(i).dsBizAreaCd_J3.setInputProtected(false);
                    scrnMsg.J.no(i).dsCustMsgTpCd_J3.setInputProtected(false);
                    scrnMsg.J.no(i).dsCustMsgTxt_J1.setInputProtected(false);
                    scrnMsg.J.no(i).dsPrintOnInvFlg_J1.setInputProtected(false);
                    scrnMsg.J.no(i).custEffLvlCd_J3.setInputProtected(false);
                    scrnMsg.J.no(i).effThruDt_J1.setInputProtected(false);
                    if (ZYPCommonFunc.hasValue(scrnMsg.J.no(i).dsCustSpclMsgPk_J1)) {
                        handler.setButtonEnabled(BTN_ATTACH, i, true);
                    } else {
                        handler.setButtonEnabled(BTN_ATTACH, i, false);
                    }
                }
            } else {

                handler.setButtonEnabled(BTN_ADD_MSG_NOTE[0], false);
                handler.setButtonEnabled(BTN_DEL_MSG_NOTE[0], false);
                for (int i = 0; i < scrnMsg.J.length(); i++) {
                    scrnMsg.J.no(i).xxChkBox_J1.setInputProtected(true);
                    scrnMsg.J.no(i).lineBizTpCd_J3.setInputProtected(true);
                    scrnMsg.J.no(i).dsBizAreaCd_J3.setInputProtected(true);
                    scrnMsg.J.no(i).dsCustMsgTpCd_J3.setInputProtected(true);
                    scrnMsg.J.no(i).dsCustMsgTxt_J1.setInputProtected(true);
                    scrnMsg.J.no(i).dsPrintOnInvFlg_J1.setInputProtected(true);
                    scrnMsg.J.no(i).custEffLvlCd_J3.setInputProtected(true);
                    scrnMsg.J.no(i).effThruDt_J1.setInputProtected(true);
                    if (ZYPCommonFunc.hasValue(scrnMsg.J.no(i).dsCustSpclMsgPk_J1)) {
                        handler.setButtonEnabled(BTN_ATTACH, i, true);
                    } else {
                        handler.setButtonEnabled(BTN_ATTACH, i, false);
                    }
                }
            }
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) {

            if (isShippingTabUpdate(scrnMsg, functionIds)) {

                handler.setButtonEnabled(BTN_ADD_SHIPPING[0], true);
                handler.setButtonEnabled(BTN_DEL_SHIPPING[0], true);
                for (int i = 0; i < scrnMsg.M.length(); i++) {
                    scrnMsg.M.no(i).xxChkBox_M1.setInputProtected(false);
                    scrnMsg.M.no(i).lineBizTpCd_M3.setInputProtected(false);
                    scrnMsg.M.no(i).dsBizAreaCd_M3.setInputProtected(false);
                    scrnMsg.M.no(i).frtCondCd_M3.setInputProtected(false);
                    scrnMsg.M.no(i).shpgSvcLvlCd_M3.setInputProtected(false);
                    scrnMsg.M.no(i).custEffLvlCd_M3.setInputProtected(false);
                    scrnMsg.M.no(i).effThruDt_M1.setInputProtected(false);
                    if (ZYPCommonFunc.hasValue(scrnMsg.M.no(i).dsCustShpgDefPk_M1)) {
                        handler.setButtonEnabled(BTN_ATTACH, i, true);
                    } else {
                        handler.setButtonEnabled(BTN_ATTACH, i, false);
                    }
                }
            } else {

                handler.setButtonEnabled(BTN_ADD_SHIPPING[0], false);
                handler.setButtonEnabled(BTN_DEL_SHIPPING[0], false);
                for (int i = 0; i < scrnMsg.M.length(); i++) {
                    scrnMsg.M.no(i).xxChkBox_M1.setInputProtected(true);
                    scrnMsg.M.no(i).lineBizTpCd_M3.setInputProtected(true);
                    scrnMsg.M.no(i).dsBizAreaCd_M3.setInputProtected(true);
                    scrnMsg.M.no(i).frtCondCd_M3.setInputProtected(true);
                    scrnMsg.M.no(i).shpgSvcLvlCd_M3.setInputProtected(true);
                    scrnMsg.M.no(i).custEffLvlCd_M3.setInputProtected(true);
                    scrnMsg.M.no(i).effThruDt_M1.setInputProtected(true);
                    if (ZYPCommonFunc.hasValue(scrnMsg.M.no(i).dsCustShpgDefPk_M1)) {
                        handler.setButtonEnabled(BTN_ATTACH, i, true);
                    } else {
                        handler.setButtonEnabled(BTN_ATTACH, i, false);
                    }
                }
            }
        // Add Start 2018/07/30 QC#27222
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            if (isTaxingTabUpdate(scrnMsg, functionIds)) {
                scrnMsg.dsTaxGrpExemCd_U3.setInputProtected(false);
            } else {
                scrnMsg.dsTaxGrpExemCd_U3.setInputProtected(true);
            }
        // Add End 2018/07/30 QC#27222
        }
    }

    /**
     * addCheckItemForAll
     * @param scrnMsg NMAL6700BMsg
     * @param glblCmpyCd String
     */
    public static void addCheckItem(NMAL6700BMsg scrnMsg, String glblCmpyCd) {

        addHeaderCheckItem(scrnMsg);

        if (TAB_ADDRESSES.equals(scrnMsg.xxDplyTab.getValue())) {
            addAddressCheckItem(scrnMsg);
        } else if (TAB_RELNSHIPS.equals(scrnMsg.xxDplyTab.getValue())) {
            addRelnshipsCheckItem(scrnMsg, glblCmpyCd);
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            addContactsCheckItem(scrnMsg);
        } else if (TAB_MARKETING.equals(scrnMsg.xxDplyTab.getValue())) {
            addMarketingCheckItem(scrnMsg, glblCmpyCd);
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            addTransactionCheckItem(scrnMsg, glblCmpyCd);
        } else if (TAB_CR_CLT.equals(scrnMsg.xxDplyTab.getValue())) {
            addCrCltCheckItem(scrnMsg);
        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            addInvBllgCheckItem(scrnMsg);
        } else if (TAB_BANK_ACCT.equals(scrnMsg.xxDplyTab.getValue())) {
            addBankAcctCheckItem(scrnMsg, glblCmpyCd);
        } else if (TAB_MSG_NOTE.equals(scrnMsg.xxDplyTab.getValue())) {
            addMsgNoteCheckItem(scrnMsg);
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/14 QC#20297(Sol#379) add start
            addShippingCheckItem(scrnMsg);
            // 2018/02/14 QC#20297(Sol#379) add end
        // Add Start 2018/07/30 QC#27222
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            addTaxingCheckItem(scrnMsg);
        // Add End 2018/07/30 QC#27222
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * checkPageJump
     * @param scrnMsg NMAL6700BMsg
     */
    public static void checkInputPageJump(NMAL6700BMsg scrnMsg) {
        if (TAB_ADDRESSES.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                scrnMsg
                , scrnMsg.A
                , scrnMsg.xxPageShowFromNum_A1
                , scrnMsg.xxPageShowToNum_A1
                , scrnMsg.xxPageShowOfNum_A1
                , scrnMsg.xxPageShowCurNum_A1
                , scrnMsg.xxPageShowTotNum_A1);
        } else if (TAB_RELNSHIPS.equals(scrnMsg.xxDplyTab.getValue())) {

            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                scrnMsg
                , scrnMsg.C
                , scrnMsg.xxPageShowFromNum_C1
                , scrnMsg.xxPageShowToNum_C1
                , scrnMsg.xxPageShowOfNum_C1
                , scrnMsg.xxPageShowCurNum_C1
                , scrnMsg.xxPageShowTotNum_C1);
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                    scrnMsg
                    , scrnMsg.D
                    , scrnMsg.xxPageShowFromNum_D1
                    , scrnMsg.xxPageShowToNum_D1
                    , scrnMsg.xxPageShowOfNum_D1
                    , scrnMsg.xxPageShowCurNum_D1
                    , scrnMsg.xxPageShowTotNum_D1);

        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                    scrnMsg
                    , scrnMsg.F
                    , scrnMsg.xxPageShowFromNum_F1
                    , scrnMsg.xxPageShowToNum_F1
                    , scrnMsg.xxPageShowOfNum_F1
                    , scrnMsg.xxPageShowCurNum_F1
                    , scrnMsg.xxPageShowTotNum_F1);

        } else if (TAB_BANK_ACCT.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                    scrnMsg
                    , scrnMsg.I
                    , scrnMsg.xxPageShowFromNum_I1
                    , scrnMsg.xxPageShowToNum_I1
                    , scrnMsg.xxPageShowOfNum_I1
                    , scrnMsg.xxPageShowCurNum_I1
                    , scrnMsg.xxPageShowTotNum_I1);

        } else if (TAB_MSG_NOTE.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                    scrnMsg
                    , scrnMsg.J
                    , scrnMsg.xxPageShowFromNum_J1
                    , scrnMsg.xxPageShowToNum_J1
                    , scrnMsg.xxPageShowOfNum_J1
                    , scrnMsg.xxPageShowCurNum_J1
                    , scrnMsg.xxPageShowTotNum_J1);
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/14 QC#20297(Sol#379) add start
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
                    scrnMsg
                    , scrnMsg.M
                    , scrnMsg.xxPageShowFromNum_M2
                    , scrnMsg.xxPageShowToNum_M2
                    , scrnMsg.xxPageShowOfNum_M2
                    , scrnMsg.xxPageShowCurNum_M2
                    , scrnMsg.xxPageShowTotNum_M2);
            // 2018/02/14 QC#20297(Sol#379) add end
        }
    }

    /**
     * clearMandatoryError
     * @param scrnMsg NMAL6700BMsg
     */
    public static void clearMandatoryError(NMAL6700BMsg scrnMsg) {

        // Header
        if (scrnMsg.dsAcctNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.dsAcctNm_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.dsAcctNm_H1.clearErrorInfo();
            }
        }
        if (scrnMsg.dsAcctTpCd_H3.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.dsAcctTpCd_H3);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.dsAcctTpCd_H3.clearErrorInfo();
            }
        }
        if (scrnMsg.dsAcctItrlFlg_H3.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.dsAcctItrlFlg_H3);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.dsAcctItrlFlg_H3.clearErrorInfo();
            }
        }
        if (scrnMsg.dsAcctClsCd_H3.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.dsAcctClsCd_H3);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.dsAcctClsCd_H3.clearErrorInfo();
            }
        }
        if (scrnMsg.coaChCd_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.coaChCd_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.coaChCd_H1.clearErrorInfo();
            }
        }
        if (scrnMsg.coaAfflCd_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.coaAfflCd_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.coaAfflCd_H1.clearErrorInfo();
            }
        }
        if (TAB_RELNSHIPS.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NMAL6700_CBMsg cbMsg = scrnMsg.C.no(i);
                if (cbMsg.dsAcctRelnTpCd_C3.isError()) {
                    EZDMessageInfo ezdMsgInfo = cbMsg.getErrorInfo(NMAL6700Bean.dsAcctRelnTpCd_C3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        cbMsg.dsAcctRelnTpCd_C3.clearErrorInfo();
                    }
                }

                if (cbMsg.dsAcctNum_C1.isError()) {
                    EZDMessageInfo ezdMsgInfo = cbMsg.getErrorInfo(NMAL6700Bean.dsAcctNum_C1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        cbMsg.dsAcctNum_C1.clearErrorInfo();
                    }
                }

                if (cbMsg.effFromDt_C1.isError()) {
                    EZDMessageInfo ezdMsgInfo = cbMsg.getErrorInfo(NMAL6700Bean.effFromDt_C1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        cbMsg.effFromDt_C1.clearErrorInfo();
                    }
                }
            }
        } else if (TAB_MARKETING.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                NMAL6700_EBMsg ebMsg = scrnMsg.E.no(i);
                if (ebMsg.dsBizAreaCd_E3.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6700Bean.dsBizAreaCd_E3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.dsBizAreaCd_E3.clearErrorInfo();
                    }
                }

                if (ebMsg.dsAcctGrpCd_E3.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6700Bean.dsAcctGrpCd_E3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.dsAcctGrpCd_E3.clearErrorInfo();
                    }
                }

                if (ebMsg.effFromDt_E1.isError()) {
                    EZDMessageInfo ezdMsgInfo = ebMsg.getErrorInfo(NMAL6700Bean.effFromDt_E1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        ebMsg.effFromDt_E1.clearErrorInfo();
                    }
                }
            }

        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                NMAL6700_FBMsg fbMsg = scrnMsg.F.no(i);
                if (fbMsg.dsTrxRuleTpCd_F3.isError()) {
                    EZDMessageInfo ezdMsgInfo = fbMsg.getErrorInfo(NMAL6700Bean.dsTrxRuleTpCd_F3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        fbMsg.dsTrxRuleTpCd_F3.clearErrorInfo();
                    }
                }
                if (fbMsg.custEffLvlCd_F3.isError()) {
                    EZDMessageInfo ezdMsgInfo = fbMsg.getErrorInfo(NMAL6700Bean.custEffLvlCd_F3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        fbMsg.custEffLvlCd_F3.clearErrorInfo();
                    }
                }
            }
            for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
                NMAL6700_SBMsg sbMsg = scrnMsg.S.no(i);
                if (sbMsg.dsSpclHdlgTpCd_S3.isError()) {
                    EZDMessageInfo ezdMsgInfo = sbMsg.getErrorInfo(NMAL6700Bean.dsSpclHdlgTpCd_S3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        sbMsg.dsSpclHdlgTpCd_S3.clearErrorInfo();
                    }
                }

                if (sbMsg.dsSpclHdlgTpValCd_S3.isError()) {
                    EZDMessageInfo ezdMsgInfo = sbMsg.getErrorInfo(NMAL6700Bean.dsSpclHdlgTpValCd_S3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        sbMsg.dsSpclHdlgTpValCd_S3.clearErrorInfo();
                    }
                }

                if (sbMsg.effFromDt_S1.isError()) {
                    EZDMessageInfo ezdMsgInfo = sbMsg.getErrorInfo(NMAL6700Bean.effFromDt_S1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        sbMsg.effFromDt_S1.clearErrorInfo();
                    }
                }

                if (sbMsg.custEffLvlCd_S3.isError()) {
                    EZDMessageInfo ezdMsgInfo = sbMsg.getErrorInfo(NMAL6700Bean.custEffLvlCd_S3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        sbMsg.custEffLvlCd_S3.clearErrorInfo();
                    }
                }
            }
        } else if (TAB_CR_CLT.equals(scrnMsg.xxDplyTab.getValue())) {

            if (scrnMsg.ccyCd_U3.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.ccyCd_U3);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.ccyCd_U3.clearErrorInfo();
                }
            }

            if (scrnMsg.crLimitAmt_U1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.crLimitAmt_U1);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.crLimitAmt_U1.clearErrorInfo();
                }
            }

            if (scrnMsg.crChkReqTpCd_U3.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.crChkReqTpCd_U3);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.crChkReqTpCd_U3.clearErrorInfo();
                }
            }

            if (scrnMsg.crRiskClsCd_U3.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.crRiskClsCd_U3);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.crRiskClsCd_U3.clearErrorInfo();
                }
            }

            // START 2018/01/25 [QC#22095, ADD]
            if (scrnMsg.contrCrRiskClsCd_U3.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.contrCrRiskClsCd_U3);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.contrCrRiskClsCd_U3.clearErrorInfo();
                }
            }
            // END   2018/01/25 [QC#22095, ADD]

            if (scrnMsg.pmtTermCashDiscCd_U3.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.pmtTermCashDiscCd_U3);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.pmtTermCashDiscCd_U3.clearErrorInfo();
                }
            }

            if (scrnMsg.cltPtfoCd_U1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6700Bean.cltPtfoCd_U1);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.cltPtfoCd_U1.clearErrorInfo();
                }
            }
        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
                NMAL6700_GBMsg gbMsg = scrnMsg.G.no(i);

                if (gbMsg.custInvSrcCd_G3.isError()) {
                    EZDMessageInfo ezdMsgInfo = gbMsg.getErrorInfo(NMAL6700Bean.custInvSrcCd_G3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        gbMsg.custInvSrcCd_G3.clearErrorInfo();
                    }
                }

                if (gbMsg.custBllgTpCd_G3.isError()) {
                    EZDMessageInfo ezdMsgInfo = gbMsg.getErrorInfo(NMAL6700Bean.custBllgTpCd_G3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        gbMsg.custBllgTpCd_G3.clearErrorInfo();
                    }
                }

                if (gbMsg.custBllgVcleCd_G3.isError()) {
                    EZDMessageInfo ezdMsgInfo = gbMsg.getErrorInfo(NMAL6700Bean.custBllgVcleCd_G3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        gbMsg.custBllgVcleCd_G3.clearErrorInfo();
                    }
                }

                if (gbMsg.custEffLvlCd_G3.isError()) {
                    EZDMessageInfo ezdMsgInfo = gbMsg.getErrorInfo(NMAL6700Bean.custEffLvlCd_G3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        gbMsg.custEffLvlCd_G3.clearErrorInfo();
                    }
                }
            }
            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                NMAL6700_KBMsg kbMsg = scrnMsg.K.no(i);

                if (kbMsg.bllgAttrbNm_K1.isError()) {
                    EZDMessageInfo ezdMsgInfo = kbMsg.getErrorInfo(NMAL6700Bean.bllgAttrbNm_K1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        kbMsg.bllgAttrbNm_K1.clearErrorInfo();
                    }
                }

                if (kbMsg.custEffLvlCd_K3.isError()) {
                    EZDMessageInfo ezdMsgInfo = kbMsg.getErrorInfo(NMAL6700Bean.custEffLvlCd_K3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        kbMsg.custEffLvlCd_K3.clearErrorInfo();
                    }
                }
            }
        // 2018/12/10 QC#29315 Del Start
//        } else if (TAB_BANK_ACCT.equals(scrnMsg.xxDplyTab.getValue())) {
//
//            for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
//                NMAL6700_WBMsg wbMsg = scrnMsg.W.no(i);
//
//                if (wbMsg.vndCd_W3.isError()) {
//                    EZDMessageInfo ezdMsgInfo = wbMsg.getErrorInfo(NMAL6700Bean.vndCd_W3);
//                    if (chkMandatoryError(ezdMsgInfo)) {
//                        wbMsg.vndCd_W3.clearErrorInfo();
//                    }
//                }
//            }
        // 2018/12/10 QC#29315 Del End
        } else if (TAB_MSG_NOTE.equals(scrnMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
                NMAL6700_JBMsg jbMsg = scrnMsg.J.no(i);
                if (jbMsg.lineBizTpCd_J3.isError()) {
                    EZDMessageInfo ezdMsgInfo = jbMsg.getErrorInfo(NMAL6700Bean.lineBizTpCd_J3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        jbMsg.lineBizTpCd_J3.clearErrorInfo();
                    }
                }

                if (jbMsg.dsBizAreaCd_J3.isError()) {
                    EZDMessageInfo ezdMsgInfo = jbMsg.getErrorInfo(NMAL6700Bean.dsBizAreaCd_J3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        jbMsg.dsBizAreaCd_J3.clearErrorInfo();
                    }
                }

                if (jbMsg.dsCustMsgTpCd_J3.isError()) {
                    EZDMessageInfo ezdMsgInfo = jbMsg.getErrorInfo(NMAL6700Bean.dsCustMsgTpCd_J3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        jbMsg.dsCustMsgTpCd_J3.clearErrorInfo();
                    }
                }

                if (jbMsg.dsCustMsgTxt_J1.isError()) {
                    EZDMessageInfo ezdMsgInfo = jbMsg.getErrorInfo(NMAL6700Bean.dsCustMsgTxt_J1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        jbMsg.dsCustMsgTxt_J1.clearErrorInfo();
                    }
                }

                if (jbMsg.custEffLvlCd_J3.isError()) {
                    EZDMessageInfo ezdMsgInfo = jbMsg.getErrorInfo(NMAL6700Bean.custEffLvlCd_J3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        jbMsg.custEffLvlCd_J3.clearErrorInfo();
                    }
                }
            }
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/14 QC#20297(Sol#379) add start

            for (int i = 0; i < scrnMsg.M.getValidCount(); i++) {
                NMAL6700_MBMsg mbMsg = scrnMsg.M.no(i);
                if (mbMsg.lineBizTpCd_M3.isError()) {
                    EZDMessageInfo ezdMsgInfo = mbMsg.getErrorInfo(NMAL6700Bean.lineBizTpCd_M3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        mbMsg.lineBizTpCd_M3.clearErrorInfo();
                    }
                }

                if (mbMsg.dsBizAreaCd_M3.isError()) {
                    EZDMessageInfo ezdMsgInfo = mbMsg.getErrorInfo(NMAL6700Bean.dsBizAreaCd_M3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        mbMsg.dsBizAreaCd_M3.clearErrorInfo();
                    }
                }

                if (mbMsg.custEffLvlCd_M3.isError()) {
                    EZDMessageInfo ezdMsgInfo = mbMsg.getErrorInfo(NMAL6700Bean.custEffLvlCd_M3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        mbMsg.custEffLvlCd_M3.clearErrorInfo();
                    }
                }
                // 2018/02/14 QC#20297(Sol#379) add end
                // 201/12/13 QC#29315 Add Start
                if (mbMsg.effFromDt_M1.isError()) {
                    EZDMessageInfo ezdMsgInfo = mbMsg.getErrorInfo(NMAL6700Bean.effFromDt_M1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        mbMsg.effFromDt_M1.clearErrorInfo();
                    }
                }
                // 201/12/13 QC#29315 Add End
            }
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

    /**
     * addHeaderCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addHeaderCheckItem(NMAL6700BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
        // 2023/11/06 QC#61924 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H3);
        // 2023/11/06 QC#61924 Add End
        scrnMsg.addCheckItem(scrnMsg.dsAcctInacRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.dsAcctTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.dsAcctItrlFlg_H3);
        scrnMsg.addCheckItem(scrnMsg.dsAcctClsCd_H3);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaChNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctDlrCd_H3);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        scrnMsg.addCheckItem(scrnMsg.dsAcctLegalNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dbaNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctDunsNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctAltNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsXtrnlRefTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.dsDataSrcTxt_H1);

    }

    /**
     * addAddressCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addAddressCheckItem(NMAL6700BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AP);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }
    }

    /**
     * addRelnshipsCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addRelnshipsCheckItem(NMAL6700BMsg scrnMsg, String glblCmpyCd) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NMAL6700_CBMsg acMsg = scrnMsg.C.no(i);
            scrnMsg.addCheckItem(acMsg.xxChkBox_C1);
            scrnMsg.addCheckItem(acMsg.dsAcctNum_C1);
            scrnMsg.addCheckItem(acMsg.dsAcctRelnTpCd_C3);
            scrnMsg.addCheckItem(acMsg.effFromDt_C1);
            scrnMsg.addCheckItem(acMsg.effThruDt_C1);
            scrnMsg.addCheckItem(acMsg.xxChkBox_CB);
            scrnMsg.addCheckItem(acMsg.xxChkBox_CS);
            scrnMsg.addCheckItem(acMsg.xxChkBox_CR);
        }
    }

    /**
     * addContactsCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addContactsCheckItem(NMAL6700BMsg scrnMsg) {
         for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
         NMAL6700_DBMsg dbMsg = scrnMsg.D.no(i);
         scrnMsg.addCheckItem(dbMsg.dsPrimLocFlg_D1);
         }
    }

    /**
     * addMarketingCheckItem
     * @param scrnMsg NMAL6700BMsg
     * @param glblCmpyCd String
     */
    private static void addMarketingCheckItem(NMAL6700BMsg scrnMsg, String glblCmpyCd) {
        scrnMsg.addCheckItem(scrnMsg.dsAcctDunsNum_M1);
        scrnMsg.addCheckItem(scrnMsg.dsUltDunsNum_M1);
        scrnMsg.addCheckItem(scrnMsg.dsLocEmpNum_M1);
        scrnMsg.addCheckItem(scrnMsg.dsLocRevAmt_M1);
        scrnMsg.addCheckItem(scrnMsg.dsCustSicCd_M1);
        scrnMsg.addCheckItem(scrnMsg.dsCustSicDescTxt_M1);
        scrnMsg.addCheckItem(scrnMsg.dsLastUpdDunsDt_M1);

        scrnMsg.addCheckItem(scrnMsg.dsAcctUrl_M1);

        // BUSINESS HOURS

         // Input check
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_M1);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_M2);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_M3);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_M4);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_M5);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_M6);

        NMAL6700CommonLogic.checkDate(scrnMsg);
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunFromTm_M1) && !ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunToTm_M1)) {
            scrnMsg.xxScrItem7Txt_M2.setErrorInfo(1, NMAM0849E, new String[]{"Time From", "Time To"});
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunToTm_M1) && !ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunFromTm_M1)) {
            scrnMsg.xxScrItem7Txt_M1.setErrorInfo(1, NMAM0849E, new String[]{"Time To", "Time From"});
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunFromTm_M1) && ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunToTm_M1)) {
            if (scrnMsg.bizHrsSunFromTm_M1.getValue().compareTo(scrnMsg.bizHrsSunToTm_M1.getValue()) > 0) {
                scrnMsg.xxScrItem7Txt_M1.setErrorInfo(1, NMAM8441E, new String[]{"Time To", "Time From"});
                scrnMsg.xxScrItem7Txt_M2.setErrorInfo(1, NMAM8441E, new String[]{"Time To", "Time From"});
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriFromTm_M1) && !ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriToTm_M1)) {
            scrnMsg.xxScrItem7Txt_M4.setErrorInfo(1, NMAM0849E, new String[]{"Time From", "Time To"});
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriToTm_M1) && !ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriFromTm_M1)) {
            scrnMsg.xxScrItem7Txt_M3.setErrorInfo(1, NMAM0849E, new String[]{"Time To", "Time From"});
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriFromTm_M1) && ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriToTm_M1)) {
            if (scrnMsg.bizHrsMonFriFromTm_M1.getValue().compareTo(scrnMsg.bizHrsMonFriToTm_M1.getValue()) > 0) {
                scrnMsg.xxScrItem7Txt_M3.setErrorInfo(1, NMAM8441E, new String[]{"Time To", "Time From"});
                scrnMsg.xxScrItem7Txt_M4.setErrorInfo(1, NMAM8441E, new String[]{"Time To", "Time From"});
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatFromTm_M1) && !ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatToTm_M1)) {
            scrnMsg.xxScrItem7Txt_M6.setErrorInfo(1, NMAM0849E, new String[]{"Time From", "Time To"});
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatToTm_M1) && !ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatFromTm_M1)) {
            scrnMsg.xxScrItem7Txt_M5.setErrorInfo(1, NMAM0849E, new String[]{"Time To", "Time From"});
        } else if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatFromTm_M1) && ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatToTm_M1)) {
            if (scrnMsg.bizHrsSatFromTm_M1.getValue().compareTo(scrnMsg.bizHrsSatToTm_M1.getValue()) > 0) {
                scrnMsg.xxScrItem7Txt_M5.setErrorInfo(1, NMAM8441E, new String[]{"Time To", "Time From"});
                scrnMsg.xxScrItem7Txt_M6.setErrorInfo(1, NMAM8441E, new String[]{"Time To", "Time From"});
            }
        }

        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            NMAL6700_EBMsg ebMsg = scrnMsg.E.no(i);
            scrnMsg.addCheckItem(ebMsg.dsBizAreaCd_E3);
            scrnMsg.addCheckItem(ebMsg.dsAcctGrpCd_E3);
            scrnMsg.addCheckItem(ebMsg.effFromDt_E1);
            scrnMsg.addCheckItem(ebMsg.effThruDt_E1);
            String effFromDt = ebMsg.effFromDt_E1.getValue();
            String effThruDt = "";
            if (ZYPCommonFunc.hasValue(ebMsg.effThruDt_E1)) {
                effThruDt = ebMsg.effThruDt_E1.getValue();
            } else {
                effThruDt = MAX_EFF_THRU_DT;
            }
            // Date Check
            if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                ebMsg.effFromDt_E1.setErrorInfo(1, NMAM8115E);
                ebMsg.effThruDt_E1.setErrorInfo(1, NMAM8115E);
                scrnMsg.addCheckItem(ebMsg.effFromDt_E1);
                scrnMsg.addCheckItem(ebMsg.effThruDt_E1);
            }

            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(ebMsg.dsAcctGrpAsgPk_E1)) {
                // Effective From Date
                if (ZYPDateUtil.compare(effFromDt, slsDt) < 0) {
                    ebMsg.effFromDt_E1.setErrorInfo(1, NMAM8250E);
                    scrnMsg.addCheckItem(ebMsg.effFromDt_E1);
                }
                // Effective Through Date
                slsDt = ZYPDateUtil.addDays(slsDt, -1);
                if (ZYPCommonFunc.hasValue(effThruDt)) {

                    if (ZYPDateUtil.compare(effThruDt, slsDt) < 0) {
                        ebMsg.effThruDt_E1.setErrorInfo(1, NMAM8251E);
                        scrnMsg.addCheckItem(ebMsg.effThruDt_E1);
                    }
                }
            }
        }

        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
            NMAL6700_NBMsg nbMsg = scrnMsg.N.no(i);
            scrnMsg.addCheckItem(nbMsg.svcAccsPmitNum_N1);
            scrnMsg.addCheckItem(nbMsg.svcAccsPmitDescTxt_N1);
            scrnMsg.addCheckItem(nbMsg.effFromDt_N1);
            scrnMsg.addCheckItem(nbMsg.effToDt_N1);
            String effFromDt = "";
            String effThruDt = "";
            if (ZYPCommonFunc.hasValue(nbMsg.effFromDt_N1)) {
                effFromDt = nbMsg.effFromDt_N1.getValue();
            }
            if (ZYPCommonFunc.hasValue(nbMsg.effToDt_N1)) {
                effThruDt = nbMsg.effToDt_N1.getValue();
            } else {
                effThruDt = MAX_EFF_THRU_DT;
            }
            // Date Check
            if (ZYPCommonFunc.hasValue(nbMsg.effFromDt_N1)) {
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                    nbMsg.effFromDt_N1.setErrorInfo(1, NMAM8115E);
                    nbMsg.effToDt_N1.setErrorInfo(1, NMAM8115E);
                    scrnMsg.addCheckItem(nbMsg.effFromDt_N1);
                    scrnMsg.addCheckItem(nbMsg.effToDt_N1);
                }
            }

            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(nbMsg.svcAccsPmitPk_N1)) {
                // Effective From Date
                if (ZYPCommonFunc.hasValue(nbMsg.effFromDt_N1)) {
                    if (ZYPDateUtil.compare(effFromDt, slsDt) < 0) {
                        nbMsg.effFromDt_N1.setErrorInfo(1, NMAM8250E);
                        scrnMsg.addCheckItem(nbMsg.effFromDt_N1);
                    }
                }
                // Effective Through Date
                slsDt = ZYPDateUtil.addDays(slsDt, -1);
                if (ZYPCommonFunc.hasValue(effThruDt)) {

                    if (ZYPDateUtil.compare(effThruDt, slsDt) < 0) {
                        nbMsg.effToDt_N1.setErrorInfo(1, NMAM8251E);
                        scrnMsg.addCheckItem(nbMsg.effToDt_N1);
                    }
                }
            }
        }
    }

    /** 
     * addTransactionCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addTransactionCheckItem(NMAL6700BMsg scrnMsg, String glblCmpyCd) {
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            NMAL6700_FBMsg fbMsg = scrnMsg.F.no(i);
            scrnMsg.addCheckItem(fbMsg.dsTrxRuleTpCd_F3);
            scrnMsg.addCheckItem(fbMsg.dsPoReqFlg_F1);
            // 2022/11/25 Add Start
            scrnMsg.addCheckItem(fbMsg.hardCopyReqFlg_F1);
            // 2022/11/25 Add End
            scrnMsg.addCheckItem(fbMsg.dsBlktPoNum_F1);
            scrnMsg.addCheckItem(fbMsg.dsPoExprDt_F1);
            scrnMsg.addCheckItem(fbMsg.dsDefBillToCd_F1);
            scrnMsg.addCheckItem(fbMsg.dsDefShipToCd_F1);
            scrnMsg.addCheckItem(fbMsg.custEffLvlCd_F3);
            scrnMsg.addCheckItem(fbMsg.dsCrCardReqFlg_F1);
            scrnMsg.addCheckItem(fbMsg.dsOvngtAllwFlg_F1);
        }
        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
            NMAL6700_SBMsg sbMsg = scrnMsg.S.no(i);
            scrnMsg.addCheckItem(sbMsg.dsSpclHdlgTpCd_S3);
            scrnMsg.addCheckItem(sbMsg.dsSpclHdlgTpValCd_S3);
            scrnMsg.addCheckItem(sbMsg.effFromDt_S1);
            scrnMsg.addCheckItem(sbMsg.effThruDt_S1);
            scrnMsg.addCheckItem(sbMsg.custEffLvlCd_S3);

            String effThruDt = "";
            if (ZYPCommonFunc.hasValue(sbMsg.effThruDt_S1)) {
                effThruDt = sbMsg.effThruDt_S1.getValue();
            } else {
                effThruDt = MAX_EFF_THRU_DT;
            }
            // Date Check
            if (ZYPDateUtil.compare(sbMsg.effFromDt_S1.getValue(), effThruDt) > 0) {
                sbMsg.effFromDt_S1.setErrorInfo(1, NMAM8115E);
                sbMsg.effThruDt_S1.setErrorInfo(1, NMAM8115E);
                scrnMsg.addCheckItem(sbMsg.effFromDt_S1);
                scrnMsg.addCheckItem(sbMsg.effThruDt_S1);
            }

            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(sbMsg.dsCustSpclHdlgPk_S1)) {
                // Effective From Date
                if (ZYPCommonFunc.hasValue(sbMsg.effFromDt_S1.getValue())) {
                    if (ZYPDateUtil.compare(sbMsg.effFromDt_S1.getValue(), slsDt) < 0) {
                        sbMsg.effFromDt_S1.setErrorInfo(1, NMAM8250E);
                        scrnMsg.addCheckItem(sbMsg.effFromDt_S1);
                    }
                }
                // Effective Through Date
                slsDt = ZYPDateUtil.addDays(slsDt, -1);
                if (ZYPCommonFunc.hasValue(effThruDt)) {

                    if (ZYPDateUtil.compare(effThruDt, slsDt) < 0) {
                        sbMsg.effThruDt_S1.setErrorInfo(1, NMAM8251E);
                        scrnMsg.addCheckItem(sbMsg.effThruDt_S1);
                    }
                }
            }
        }
        scrnMsg.addCheckItem(scrnMsg.coaCcCd_F1);
    }

    /**
     * addCrCltCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addCrCltCheckItem(NMAL6700BMsg scrnMsg) {

        // CREDIT
        scrnMsg.addCheckItem(scrnMsg.ccyCd_U3);
        scrnMsg.addCheckItem(scrnMsg.custCrRtgCd_U3);
        scrnMsg.addCheckItem(scrnMsg.crLimitAmt_U1);
        scrnMsg.addCheckItem(scrnMsg.crChkReqTpCd_U3);
        scrnMsg.addCheckItem(scrnMsg.crRiskClsCd_U3);
        // START 2018/01/25 [QC#22095, ADD]
        scrnMsg.addCheckItem(scrnMsg.contrCrRiskClsCd_U3);
        // END   2018/01/25 [QC#22095, ADD]
        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscCd_U3);
        scrnMsg.addCheckItem(scrnMsg.ovrdPmtTermFlg_U1);
        scrnMsg.addCheckItem(scrnMsg.cashOrCcReqFlg_U1);
        scrnMsg.addCheckItem(scrnMsg.custHardHldFlg_U1);

        // COLLECTIONS
        scrnMsg.addCheckItem(scrnMsg.arStmtFlg_U1);
        // START 2018/01/25 [QC#22095, ADD]
        //scrnMsg.addCheckItem(scrnMsg.sendCrBalStmtFlg_U1);
        // END   2018/01/25 [QC#22095, ADD]
        scrnMsg.addCheckItem(scrnMsg.arStmtIssCycleCd_U3);
        // START 2018/01/16 [QC#21734, DEL]
        // scrnMsg.addCheckItem(scrnMsg.dunFlg_U1);
        // END   2018/01/16 [QC#21734, DEL]
        scrnMsg.addCheckItem(scrnMsg.cltCustTpCd_U1);
        scrnMsg.addCheckItem(scrnMsg.cltCustTpNm_U1);
        scrnMsg.addCheckItem(scrnMsg.cltPtfoCd_U1);
        // 2018/07/13 QC#26613 Mod Start
//        scrnMsg.addCheckItem(scrnMsg.cltPtfoNm_U1);
        scrnMsg.addCheckItem(scrnMsg.cltPtfoDescTxt_U1);
        // 2018/07/13 QC#26613 Mod End
        scrnMsg.addCheckItem(scrnMsg.lateFeeAmt_U1);

        // TAXING
        // Del Start 2018/07/30 QC#27222
//        scrnMsg.addCheckItem(scrnMsg.dsCustTaxCd_U1);
//        scrnMsg.addCheckItem(scrnMsg.dsCustTaxCalcCd_U3);
//        scrnMsg.addCheckItem(scrnMsg.dsTaxPrntTpCd_U3);
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        scrnMsg.addCheckItem(scrnMsg.dsExemExprDt_U1);
        // Mod End 2018/08/21 QC#27222-2 Uncomment
//        scrnMsg.addCheckItem(scrnMsg.dsTaxGrpExemCd_U3);
        // Del End 2018/07/30 QC#27222
        scrnMsg.addCheckItem(scrnMsg.dsTaxPrntTpCd_U3);

    }

    /**
     * addInvBllgCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addInvBllgCheckItem(NMAL6700BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.defBaseTpCd_V3);
        scrnMsg.addCheckItem(scrnMsg.defBaseCycleCd_V3);
        scrnMsg.addCheckItem(scrnMsg.defUsgTpCd_V3);
        scrnMsg.addCheckItem(scrnMsg.defUsgCycleCd_V3);
        // START 2022/03/22 [QC#59683, MOD]
//        scrnMsg.addCheckItem(scrnMsg.dsBillTgtrFlg_V1);
        scrnMsg.addCheckItem(scrnMsg.dsInvTgtrTpCd_V1);
        // END   2022/03/22 [QC#59683, MOD]

        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            NMAL6700_GBMsg gbMsg = scrnMsg.G.no(i);
            scrnMsg.addCheckItem(gbMsg.custInvSrcCd_G3);
            scrnMsg.addCheckItem(gbMsg.custBllgTpCd_G3);
            scrnMsg.addCheckItem(gbMsg.custConslTermCd_G3);
            scrnMsg.addCheckItem(gbMsg.custBllgVcleCd_G3);
            scrnMsg.addCheckItem(gbMsg.xxGenlFldAreaTxt_G1);
            scrnMsg.addCheckItem(gbMsg.xxGenlFldAreaTxt_G2);
            scrnMsg.addCheckItem(gbMsg.custEmlMsgTxt_G1);
            scrnMsg.addCheckItem(gbMsg.defInvGrpCd_G3);
            scrnMsg.addCheckItem(gbMsg.invGrpNum_G1);
            scrnMsg.addCheckItem(gbMsg.custEffLvlCd_G3);
        }

        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            NMAL6700_KBMsg kbMsg = scrnMsg.K.no(i);
            scrnMsg.addCheckItem(kbMsg.bllgAttrbNm_K1);
            scrnMsg.addCheckItem(kbMsg.bllgAttrbValTxt_K1);
            scrnMsg.addCheckItem(kbMsg.bllgAttrbEnblFlg_K1);
            scrnMsg.addCheckItem(kbMsg.bllgAttrbReqFlg_K1);
            scrnMsg.addCheckItem(kbMsg.custEffLvlCd_K3);
        }

    }

    /**
     * addBankAcctCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addBankAcctCheckItem(NMAL6700BMsg scrnMsg, String glblCmpyCd) {

        for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
            NMAL6700_IBMsg ibMsg = scrnMsg.I.no(i);
            scrnMsg.addCheckItem(ibMsg.effThruDt_I1);
        }
        // 2018/12/10 QC#29315 Del Start
//        for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
//            NMAL6700_WBMsg wbMsg = scrnMsg.W.no(i);
//            scrnMsg.addCheckItem(wbMsg.vndCd_W3);
//            scrnMsg.addCheckItem(wbMsg.dsCarrAcctNum_W1);
//            scrnMsg.addCheckItem(wbMsg.effFromDt_W1);
//            scrnMsg.addCheckItem(wbMsg.effThruDt_W1);
//            scrnMsg.addCheckItem(wbMsg.xxChkBox_WD);
//            scrnMsg.addCheckItem(wbMsg.xxChkBox_WS);
//        }
        // 2018/12/10 QC#29315 Del End
    }

    /**
     * addMsgNoteCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addMsgNoteCheckItem(NMAL6700BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            NMAL6700_JBMsg jbMsg = scrnMsg.J.no(i);
            scrnMsg.addCheckItem(jbMsg.lineBizTpCd_J3);
            scrnMsg.addCheckItem(jbMsg.dsBizAreaCd_J3);
            scrnMsg.addCheckItem(jbMsg.dsCustMsgTpCd_J3);
            scrnMsg.addCheckItem(jbMsg.dsCustMsgTxt_J1);
            scrnMsg.addCheckItem(jbMsg.dsPrintOnInvFlg_J1);
            scrnMsg.addCheckItem(jbMsg.custEffLvlCd_J3);
            scrnMsg.addCheckItem(jbMsg.effThruDt_J1);
        }

    }

    // 2018/02/14 QC#20297(Sol#379) add start
    /**
     * addShippingCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addShippingCheckItem(NMAL6700BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.M.getValidCount(); i++) {
            NMAL6700_MBMsg mbMsg = scrnMsg.M.no(i);
            scrnMsg.addCheckItem(mbMsg.lineBizTpCd_M3);
            scrnMsg.addCheckItem(mbMsg.dsBizAreaCd_M3);
            scrnMsg.addCheckItem(mbMsg.frtCondCd_M3);
            scrnMsg.addCheckItem(mbMsg.shpgSvcLvlCd_M3);
            scrnMsg.addCheckItem(mbMsg.custEffLvlCd_M3);
            scrnMsg.addCheckItem(mbMsg.effThruDt_M1);
            // 2018/12/10 QC#29315 Add Start
            scrnMsg.addCheckItem(mbMsg.dsCarrAcctNum_M1);
            scrnMsg.addCheckItem(mbMsg.vndCd_M3);
            // 2023/01/13 QC#60860 Add Start
            scrnMsg.addCheckItem(mbMsg.carrNm_M3);
            // 2023/01/13 QC#60860 Add End
            scrnMsg.addCheckItem(mbMsg.effFromDt_M1);
            scrnMsg.addCheckItem(mbMsg.xxChkBox_MD);
            // 2018/12/10 QC#29315 Add End
        }

    }
    // 2018/02/14 QC#20297(Sol#379) add end

    // Add Start 2018/07/30 QC#27222
    /**
     * addTaxingCheckItem
     * @param scrnMsg NMAL6700BMsg
     */
    private static void addTaxingCheckItem(NMAL6700BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsTaxGrpExemCd_U3);
    }
    // Add End 2018/07/30 QC#27222

    /**
     * setHirarchyTreeView
     * @param scrnMsg NMAL6700BMsg
     * @param bizMsg NMAL6700CMsg
     */
    public static void setHirarchyTreeView(NMAL6700BMsg scrnMsg, NMAL6700CMsg bizMsg) {
        // ##### create TreeView column types
        // 1. "Node"
        S21TreeViewNodeColumn nodeCol = new S21TreeViewNodeColumn();
        nodeCol.setRootNodeKey("dsAcctNum_B1"); // Root
        nodeCol.addInnerNodeKey("dsAcctNum_B2");
        nodeCol.addInnerNodeKey("dsAcctNum_B3");
        nodeCol.addInnerNodeKey("dsAcctNum_B4");
        nodeCol.addInnerNodeKey("dsAcctNum_B5");
        nodeCol.addInnerNodeKey("dsAcctNum_B6");
        nodeCol.addInnerNodeKey("dsAcctNum_B7");
        nodeCol.addInnerNodeKey("dsAcctNum_B8");
        nodeCol.addInnerNodeKey("dsAcctNum_B9");
        nodeCol.addInnerNodeKey("dsAcctNum_BA");
        nodeCol.setLeafNodeKey("locNum_BM"); // Leaf
        // 2. "Radio"
        S21TreeViewRadioColumn radioCol = new S21TreeViewRadioColumn();

        S21TreeViewDataColumn dataCol01 = new S21TreeViewDataColumn();
        dataCol01.setSchemaKey("dsAcctNm_B");
        S21TreeViewDataColumn dataCol02 = new S21TreeViewDataColumn();
        dataCol02.setSchemaKey("xxAllLineAddr_B");
        S21TreeViewDataColumn dataCol03 = new S21TreeViewDataColumn();
        dataCol03.setSchemaKey("ctyAddr_B");
        S21TreeViewDataColumn dataCol04 = new S21TreeViewDataColumn();
        dataCol04.setSchemaKey("stCd_B");
        S21TreeViewDataColumn dataCol05 = new S21TreeViewDataColumn();
        dataCol05.setSchemaKey("postCd_B");
        S21TreeViewDataColumn dataCol06 = new S21TreeViewDataColumn();
        dataCol06.setSchemaKey("locNum_B");
        S21TreeViewDataColumn dataCol07 = new S21TreeViewDataColumn();
        dataCol07.setSchemaKey("xxExstFlg_B");
        S21TreeViewDataColumn dataCol08 = new S21TreeViewDataColumn();
        dataCol08.setSchemaKey("dsAcctTpNm_B");
        S21TreeViewDataColumn dataCol09 = new S21TreeViewDataColumn();
        dataCol09.setSchemaKey("dsAcctStsNm_B");
        S21TreeViewRadioColumn dataCol10 = new S21TreeViewRadioColumn();
        S21TreeViewDataColumn dataCol11 = new S21TreeViewDataColumn();
        dataCol11.setSchemaKey("dsAcctNum_B");

        // ##### create S21TreeViewCreatorIF
        S21TreeViewCreatorIF tViewCreatorIF = new S21TreeViewCreatorIF((EZDCMsgArray) bizMsg.getMsgData("B"), nodeCol);
        tViewCreatorIF.addColumnType(dataCol01);
        tViewCreatorIF.addColumnType(dataCol02);
        tViewCreatorIF.addColumnType(dataCol03);
        tViewCreatorIF.addColumnType(dataCol04);
        tViewCreatorIF.addColumnType(dataCol05);
        tViewCreatorIF.addColumnType(dataCol06);
        tViewCreatorIF.addColumnType(dataCol07);
        tViewCreatorIF.addColumnType(dataCol08);
        tViewCreatorIF.addColumnType(dataCol09);
        tViewCreatorIF.addColumnType(dataCol10);
        tViewCreatorIF.addColumnType(dataCol11);
        tViewCreatorIF.addColumnType(radioCol);
        // ##### create S21TreeView
        S21TreeView treeView = S21TreeViewCreator.create(tViewCreatorIF, "treeView");
        treeView.setExpandedNodeIcon();
        scrnMsg.setTreeView(treeView);
    }

    /**
     * Method name: getAllTreeNodeRows <dd>The method explanation: Get
     * all tree node rows
     * @param treeView S21TreeView
     * @param columnIndex column Index
     * @return returnList List(TreeNodeRow)
     */
    public static List<TreeNodeRow> getAllTreeNodeRows(S21TreeView treeView, int columnIndex) {
        List<TreeNodeRow> returnList = new ArrayList<TreeNodeRow>();
        getAllTreeNodeRows(returnList, (TreeNodeRow) treeView.getRoot(), columnIndex);
        return returnList;
    }

    /**
     * Method name: getAllTreeNodeRows <dd>The method explanation: Get
     * all tree node rows
     * @param returnList List
     * @param treeNodeRow TreeNodeRow
     * @param columnIndex column Index
     */
    private static void getAllTreeNodeRows(List<TreeNodeRow> returnList, TreeNodeRow treeNodeRow, int columnIndex) {
        returnList.add(treeNodeRow);
        for (int i = 0; i < treeNodeRow.getChildCount(); i++) {
            getAllTreeNodeRows(returnList, treeNodeRow.getChild(i), columnIndex);
        }
    }

    /**
     * clear params
     * @param scrnMsg NMAL6700BMsg
     */
    public static void clearParams(NMAL6700BMsg scrnMsg) {
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
        scrnMsg.ctacPsnPk_15.clear();
        scrnMsg.ctacPsnPk_16.clear();
        scrnMsg.ctacPsnPk_17.clear();

    }

    /**
     * setEventParam
     * @param scrnMsg NMAL6700BMsg
     * @param eventName String
     */
    public static void setEventParam(NMAL6700BMsg scrnMsg, String eventName) {
        clearParams(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PB, eventName);
    }
    /**
     * popup array.
     * @param p DWCL0070_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popup(NMAL6700_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    /**
     * popup array.
     * @param scrnMsg NMAL6700BMsg
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_OpenWinBillToShipTo(NMAL6700BMsg scrnMsg, int size) {
        Object[] param = new Object[size];
        scrnMsg.dsAcctNm_P.clear();

        for (int i = 0; i < size; i++) {
            if (i == 3) {
                param[3] = scrnMsg.dsAcctNm_P;
                continue;
            }
            param[i] = scrnMsg.P.no(i).xxPopPrm;
        }
        return param;
    }

    /**
     * 
     * setProtectedForGrpAsg
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void setProtectedForGrpAsg(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
         scrnMsg.setInputProtected(false);
        if (scrnMsg.E.getValidCount() > 0) {
            handler.setButtonEnabled(BUTTON_NAME_ADD_GRP_ASG, false);
        } else {
            handler.setButtonEnabled(BUTTON_NAME_ADD_GRP_ASG, true);
        }
    }

    /**
     * 
     * setProtectedForCertificationReq
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void setProtectedForCertificationReq(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
        scrnMsg.setInputProtected(false);
        if (scrnMsg.N.getValidCount() > 0) {
            handler.setButtonEnabled(BUTTON_NAME_ADD_CERTIFICATION_REQ, false);
        } else {
            handler.setButtonEnabled(BUTTON_NAME_ADD_CERTIFICATION_REQ, true);
        }
    }

    /**
     * 
     * checkAuth
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuth(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (!functionIds.contains(FUNC_ID_MARKETING_UPDATE)) {
        if (!isMarketingTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            // All Protected
            handler.setButtonEnabled(BUTTON_NAME_ADD_GRP_ASG, false);
            handler.setButtonEnabled(BUTTON_NAME_DELETE_GRP_ASG, false);
            handler.setButtonEnabled(BUTTON_NAME_ADD_CERTIFICATION_REQ, false);
            handler.setButtonEnabled(BUTTON_NAME_DELETE_CERTIFICATION_REQ, false);
        }
    }

    /**
     * setBgColorForAddressTab
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColor(NMAL6700BMsg scrnMsg) {
        if (TAB_ADDRESSES.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForLocation(scrnMsg);

        } else if (TAB_RELNSHIPS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForRelationship(scrnMsg);
        } else if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForContacts(scrnMsg);
        } else if (TAB_MARKETING.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForGrpAsg(scrnMsg);
            setBgColorForCertificationReq(scrnMsg);
        } else if (TAB_TRANSACTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForTransactionRule(scrnMsg);
            setBgColorForSpecialHandling(scrnMsg);
        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForInvRule(scrnMsg);
            setBgColorForAttribute(scrnMsg);
        } else if (TAB_BANK_ACCT.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForCarrierAccount(scrnMsg);
            // 2018/12/10 QC#29315 Del Start
//            setBgColorForBankAccount(scrnMsg);
            // 2018/12/10 QC#29315 Del End
        } else if (TAB_MSG_NOTE.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForInstruction(scrnMsg);
        } else if (TAB_SHIPPING.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/02/14 QC#20297(Sol#379) add start
            setBgColorForShipping(scrnMsg);
            // 2018/02/14 QC#20297(Sol#379) add end
        }

    }


    /**
     * setBgColorForLocation
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForLocation(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * setBgColorForRelationship
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForRelationship(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("C", scrnMsg.C);
        if (scrnMsg.C.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("C", scrnMsg.C);
        }
    }

    /**
     * setBgColorForContacts
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForContacts(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("D", scrnMsg.D);
        if (scrnMsg.D.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("D", scrnMsg.D);
        }
    }

    /**
     * setBgColorForGrpAsg
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForGrpAsg(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("E", scrnMsg.E);
        if (scrnMsg.E.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("E", scrnMsg.E);
        }
    }

    /**
     * setBgColorForTransactionRule
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForTransactionRule(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("F", scrnMsg.F);
        if (scrnMsg.F.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("F", scrnMsg.F);
        }
    }

    /**
     * setBgColorForInvRule
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForInvRule(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("G", scrnMsg.G);
        if (scrnMsg.G.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("G", scrnMsg.G);
        }
    }

    /**
     * setBgColorForCarrierAccount
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForCarrierAccount(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("I", scrnMsg.I);
        if (scrnMsg.I.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("I", scrnMsg.I);
        }
    }

    /**
     * setBgColorForInstruction
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForInstruction(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("J", scrnMsg.J);
        if (scrnMsg.J.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("J", scrnMsg.J);
        }
    }

    /**
     * setBgColorForAttribute
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForAttribute(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("K", scrnMsg.K);
        if (scrnMsg.K.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("K", scrnMsg.K);
        }
    }

    /**
     * setBgColorForCertificationReq
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForCertificationReq(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("N", scrnMsg.N);
        if (scrnMsg.E.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("N", scrnMsg.N);
        }
    }

    /**
     * setBgColorForSpecialHandling
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForSpecialHandling(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("S", scrnMsg.S);
        if (scrnMsg.S.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("S", scrnMsg.S);
        }
    }
    // 2018/12/10 QC#29315 Del Start
//    /**
//     * setBgColorForBankAccount
//     * @param scrnMsg NMAL6700BMsg
//     */
//    public static void setBgColorForBankAccount(NMAL6700BMsg scrnMsg) {
//        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
//        tblColor.clearRowsBG("W", scrnMsg.W);
//        if (scrnMsg.W.getValidCount() > 0) {
//            tblColor.setAlternateRowsBG("W", scrnMsg.W);
//        }
//    }
    // 2018/12/10 QC#29315 Del End

    // 2018/02/14 QC#20297(Sol#379) add start
    /**
     * setBgColorForShipping
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForShipping(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("M", scrnMsg.M);
        if (scrnMsg.M.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("M", scrnMsg.M);
        }
    }
    // 2018/02/14 QC#20297(Sol#379) add end

    /**
     * 
     * setProtectedForGrpAsg
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void setProtectedForMsgNote(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
         scrnMsg.setInputProtected(false);
        if (scrnMsg.J.getValidCount() > 0) {
            handler.setButtonEnabled(BUTTON_NAME_DELETE_MSG_NOTE, true);
        } else {
            handler.setButtonEnabled(BUTTON_NAME_DELETE_MSG_NOTE, false);
        }
    }

    /**
     * 
     * checkAuthForMsgNote
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuthForMsgNote(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (!functionIds.contains(FUNC_ID_INSTRUCTION_UPDATE)) {
        if (!isInstractionsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            // All Protected
            handler.setButtonEnabled(BUTTON_NAME_ADD_MSG_NOTE, false);
            handler.setButtonEnabled(BUTTON_NAME_DELETE_MSG_NOTE, false);
        }
    }

    // 2018/02/14 QC#20297(Sol#379) add start
    /**
     * 
     * checkAuthForMsgNote
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuthForShipping(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (!isShippingTabUpdate(scrnMsg, functionIds)) {
            // All Protected
            handler.setButtonEnabled(BUTTON_NAME_ADD_SHIPPING, false);
            handler.setButtonEnabled(BUTTON_NAME_DELETE_SHIPPING, false);
        }
    }
    // 2018/02/14 QC#20297(Sol#379) add end

    /**
     * 
     * setBgColorForMsgNote
     * 
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForMsgNote(NMAL6700BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("J", scrnMsg.J);
        if (scrnMsg.E.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("J", scrnMsg.J);
        }
    }

    /**
     * popup array.
     * @param p NMAL6700_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArrayPopup(NMAL6700_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    // 2018/12/10 QC#29315 Del Start
//    /**
//     * 
//     * setProtectedForGrpAsg
//     * 
//     * @param scrnMsg NMAL6700BMsg
//     * @param handler EZDCommonHandler
//     */
//    public static void setProtectedForBankAcct(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
//        scrnMsg.setInputProtected(false);
//        if (scrnMsg.W.getValidCount() > 0) {
//            handler.setButtonEnabled(BUTTON_NAME_DELETE_BANK_ACCT, true);
//        } else {
//            handler.setButtonEnabled(BUTTON_NAME_DELETE_BANK_ACCT, false);
//        }
//    }
    // 2018/12/10 QC#29315 Del End

    /**
     * 
     * checkAuthForBankAcct
     * 
     * @param scrnMsg NMAL6700BMsg
     * @param handler EZDCommonHandler
     */
    public static void checkAuthForBankAcct(NMAL6700BMsg scrnMsg, EZDCommonHandler handler) {
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (!functionIds.contains(FUNC_ID_BANK_UPDATE)) {
        if (!isBankAcctTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            // All Protected
            handler.setButtonEnabled(BUTTON_NAME_INSERT_BANK_ACCT, false);
            handler.setButtonEnabled(BUTTON_NAME_DELETE_BANK_ACCT, false);
        }
    }

    // 2018/12/10 QC#29315 Del Start
//    /**
//     * 
//     * setBgColorForBankAcct
//     * 
//     * @param scrnMsg NMAL6700BMsg
//     */
//    public static void setBgColorForBankAcct(NMAL6700BMsg scrnMsg) {
//        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
//        tblColor.clearRowsBG("W", scrnMsg.W);
//        if (scrnMsg.W.getValidCount() > 0) {
//            tblColor.setAlternateRowsBG("W", scrnMsg.W);
//        }
//    }
    // 2018/12/10 QC#29315 Del End

    /**
     * 
     * hasValue
     * 
     * @param scrnMsg NMAL6700BMsg
     */
    public static void changeFormatTime(NMAL6700BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunFromTm_M1)) {
            scrnMsg.xxScrItem7Txt_M1.setValue(getFormatTime(scrnMsg.bizHrsSunFromTm_M1.getValue()));
        } else {
            scrnMsg.xxScrItem7Txt_M1.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSunToTm_M1)) {
            scrnMsg.xxScrItem7Txt_M2.setValue(getFormatTime(scrnMsg.bizHrsSunToTm_M1.getValue()));
        } else {
            scrnMsg.xxScrItem7Txt_M2.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriFromTm_M1)) {
            scrnMsg.xxScrItem7Txt_M3.setValue(getFormatTime(scrnMsg.bizHrsMonFriFromTm_M1.getValue()));
        } else {
            scrnMsg.xxScrItem7Txt_M3.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsMonFriToTm_M1)) {
            scrnMsg.xxScrItem7Txt_M4.setValue(getFormatTime(scrnMsg.bizHrsMonFriToTm_M1.getValue()));
        } else {
            scrnMsg.xxScrItem7Txt_M4.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatFromTm_M1)) {
            scrnMsg.xxScrItem7Txt_M5.setValue(getFormatTime(scrnMsg.bizHrsSatFromTm_M1.getValue()));
        } else {
            scrnMsg.xxScrItem7Txt_M5.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.bizHrsSatToTm_M1)) {
            scrnMsg.xxScrItem7Txt_M6.setValue(getFormatTime(scrnMsg.bizHrsSatToTm_M1.getValue()));
        } else {
            scrnMsg.xxScrItem7Txt_M6.clear();
        }
    }

    /**
     * hasValue
     * @param scrnMsg NMAL6700BMsg
     */
    public static void checkDate(NMAL6700BMsg scrnMsg) {

        changeReplaceTime(scrnMsg, scrnMsg.xxScrItem7Txt_M1, scrnMsg.bizHrsSunFromTm_M1);
        changeReplaceTime(scrnMsg, scrnMsg.xxScrItem7Txt_M2, scrnMsg.bizHrsSunToTm_M1);
        changeReplaceTime(scrnMsg, scrnMsg.xxScrItem7Txt_M3, scrnMsg.bizHrsMonFriFromTm_M1);
        changeReplaceTime(scrnMsg, scrnMsg.xxScrItem7Txt_M4, scrnMsg.bizHrsMonFriToTm_M1);
        changeReplaceTime(scrnMsg, scrnMsg.xxScrItem7Txt_M5, scrnMsg.bizHrsSatFromTm_M1);
        changeReplaceTime(scrnMsg, scrnMsg.xxScrItem7Txt_M6, scrnMsg.bizHrsSatToTm_M1);
    }
    private static String getFormatTime(String time) {
        return time.substring(0, 2) + ':' + time.substring(2, 4);
    }
    private static void changeReplaceTime(NMAL6700BMsg scrnMsg, EZDBStringItem dispTime, EZDBStringItem time) {
        String regex = "([0-1][0-9]|[2][0-3])(:)([0-5][0-9])";
        Pattern p = Pattern.compile(regex);

        if (ZYPCommonFunc.hasValue(dispTime)) {
            Matcher m = p.matcher(dispTime.getValue());
            if (m.find()) {
                time.setValue(dispTime.getValue().replace(":", ""));
            } else {
                dispTime.setErrorInfo(1, "NMAM8356E");
                scrnMsg.addCheckItem(dispTime);
                scrnMsg.setMessageInfo("NMAM8356E");
            }

        } else {
            time.clear();
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6700BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceSearchPopup(NMAL6700BMsg scrnMsg) {
        Object[] params = new Object[10];
        clearParams(scrnMsg);
        ZYPTableUtil.clear(scrnMsg.Q);

        NMAL6700_GBMsg gBMsg = scrnMsg.G.no(scrnMsg.xxCellIdx.getValueInt());
        String psnCdList = gBMsg.xxGenlFldAreaTxt_G1.getValue();
        String[] psnCdArray = NMAL6700CommonLogic.splitByComma(psnCdList);
        EZDBStringItem[] inputs = new EZDBStringItem[scrnMsg.Q.length()];
        EZDBStringItem[] outputs = new EZDBStringItem[scrnMsg.Q.length()];
        for (int i = 0; i < scrnMsg.Q.length(); i++) {
            if (i < psnCdArray.length) {
                scrnMsg.Q.no(i).psnCd_Q1.setValue(psnCdArray[i]);
            }
            inputs[i] = scrnMsg.Q.no(i).psnCd_Q1;
            outputs[i] = scrnMsg.Q.no(i).psnCd_Q2;
        }

        scrnMsg.xxPopPrm_P6.setValue("M");
        scrnMsg.xxPopPrm_P7.setValue(ZYPConstant.CHKBOX_ON_Y);

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6; // mode
        params[7] = scrnMsg.xxPopPrm_P7; // has email flag 
        params[8] = inputs;
        params[9] = outputs;

        return params;
    }

    /**
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setAppFracDigit(NMAL6700BMsg scrnMsg) {
        scrnMsg.dsLocRevAmt_M1.setAppFracDigit(2);
        scrnMsg.crLimitAmt_U1.setAppFracDigit(2);
        scrnMsg.lateFeeAmt_U1.setAppFracDigit(2);
    }

    /**
     * @param str String
     * @return String[]
     */
    public static String[] splitByComma(String str) {
        String[] array = str.split(NMAL6700Constant.CHAR_COMMA);
        List<String> list = new ArrayList<String>();
        for (String s : array) {
            if (ZYPCommonFunc.hasValue(s)) {
                list.add(s);
            }
        }
        return list.toArray(new String[]{});
    }

    // START 2017/07/06 J.Kim [QC#16966,ADD]
    /**
     * Set Popup Argument for NWAL6170
     * @param scrnMsg NMAL6170BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setArgumentNWAL6170(NMAL6700BMsg scrnMsg) {

        Object[] param = new Object[LENGTH];

        param[INPUT_DS_ACCT_RELN_TP_CD] = scrnMsg.dsAcctRelnTpCd_H;
        param[INPUT_DS_ACCT_NUM] = scrnMsg.dsAcctNum_F;
        param[INPUT_DS_ACCT_NM] = scrnMsg.dsAcctNm_F;
        param[INPUT_XX_CHK_BOX_CB] = scrnMsg.xxChkBox_FB;
        param[INPUT_XX_CHK_BOX_CS] = scrnMsg.xxChkBox_FS;
        param[INPUT_XX_CHK_BOX_CR] = scrnMsg.xxChkBox_FR;
        param[INPUT_EFF_FROM_DT_F] = scrnMsg.effFromDt_F1;
        param[INPUT_EFF_FROM_DT_T] = scrnMsg.effFromDt_F2;
        param[INPUT_EFF_THRU_DT_F] = scrnMsg.effThruDt_F1;
        param[INPUT_EFF_THRU_DT_T] = scrnMsg.effThruDt_F2;

        return param;
    }
    // END 2017/07/06 J.Kim [QC#16966,ADD]
}
