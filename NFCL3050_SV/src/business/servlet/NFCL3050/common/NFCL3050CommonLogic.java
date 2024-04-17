/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3050.common;

import static business.servlet.NFCL3050.constant.NFCL3050Constant.*;

import java.util.List;

import parts.common.EZDGUIAttribute;
import business.servlet.NFCL3050.NFCL3050BMsg;
import business.servlet.NFCL3050.NFCL3050_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFCL3050CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/14   Hitachi         T.Tsuchida      Update          QC#5423
 * 2016/03/22   CSAI            K.Uramori       Update          QC#5770
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#11507
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#13478
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#14791
 * 2018/02/22   Fujitsu         T.Murai         Update          QC#24241
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 * 2018/10/18   Fujitsu         T.Noguchi       Update          QC#28434
 * 2022/11/29   Hitachi         M.Nakajima      Update          QC#60742
 * 2024/03/05   Hitachi         TZ.Win          Update          QC#63665
 *</pre>
 */
public class NFCL3050CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    // START 2016/07/12 K.Kojima [QC#11049,DEL]
    // /**
    // * Table has an id attribute of the argument row background
    // color,
    // * change the argument groupRows every alternate line groups.
    // * @param scrnMsg NFCL3050BMsg
    // * @param scrnAMsgAry NFCL3050_ABMsgArray
    // * @param tblId HTML id attribute given to the Table
    // */
    // public static void setRowsBGWithClear(NFCL3050BMsg scrnMsg,
    // NFCL3050_ABMsgArray scrnAMsgAry, String tblId) {
    // setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    // }
    // START 2016/07/12 K.Kojima [QC#11049,DEL]

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3050BMsg
     * @param scrnAMsgAry NFCL3050_ABMsgArray
     */
    public static void setRowsBGWithClear(NFCL3050BMsg scrnMsg, NFCL3050_ABMsgArray scrnAMsgAry) {
        if (scrnMsg.xxDplyTab.getValue().equals(DIS_PAT_INVISIBILITY_ERROR)) {
            setRowsBGWithClear(scrnMsg, scrnAMsgAry, "B", 1);
        } else {
            setRowsBGWithClear(scrnMsg, scrnAMsgAry, "A", 1);
        }
    }

    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3050BMsg
     * @param scrnAMsgAry NFCL3050_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFCL3050BMsg scrnMsg, NFCL3050_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NFCL3050BMsg
     * @param scrnAMsgAry NFCL3050_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFCL3050BMsg scrnMsg, NFCL3050_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param userProfileService S21UserProfileService
     * @param scrnMsg NFCL3050BMsg
     */
    public static void controlScreen(S21CommonHandler handler, S21UserProfileService userProfileService, NFCL3050BMsg scrnMsg) {

        NFCL3050_ABMsgArray lineMsgArray = scrnMsg.A;

        if (lineMsgArray.getValidCount() > 0) {
            handler.setButtonEnabled("Activity", true);
            handler.setButtonEnabled("Details", true);
            handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
            // START 2016/03/14 T.Tsuchida [QC#5423,MOD]
            controlScreenDetailFields(lineMsgArray);
            // END 2016/03/14 T.Tsuchida [QC#5423,MOD]
            
            //---- start add 2016/03/22 QC#5770
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_ER.getValue())) {
                // START 2016/07/12 K.Kojima [QC#11049,MOD]
                // handler.setButtonEnabled("btnSelectAll", true);
                handler.setButtonEnabled("btnSlectAll", true);
                // END 2016/07/12 K.Kojima [QC#11049,MOD]
                handler.setButtonEnabled("btnUnslctAll", true);
                // START 2016/10/20 K.Kojima [QC#14791,MOD]
                // handler.setButtonEnabled("btnRegen", true);
                if (COMP_TP_COMP.equals(scrnMsg.xxRadioBtn_H.getValue())) {
                    handler.setButtonEnabled("btnRegen", false);
                } else {
                    handler.setButtonEnabled("btnRegen", true);
                }
                // END 2016/10/20 K.Kojima [QC#14791,MOD]
                
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    // START 2016/10/20 K.Kojima [QC#14791,MOD]
                    // scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).fnlzInvFlg_A.getValue())) {
                        scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                    } else {
                        scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                    }
                    // END 2016/10/20 K.Kojima [QC#14791,MOD]
                }
                
            } else {
                // START 2016/07/12 K.Kojima [QC#11049,MOD]
                // handler.setButtonEnabled("btnSelectAll", false);
                handler.setButtonEnabled("btnSlectAll", false);
                // END 2016/07/12 K.Kojima [QC#11049,MOD]
                handler.setButtonEnabled("btnUnslctAll", false);
                handler.setButtonEnabled("btnRegen", false);
                
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                }
            }
            
            //---- end 2016/03/22
            
            //---- start 2016/03/23
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // START 2016/07/14 K.Kojima [QC#11507,MOD]
                // scrnMsg.A.no(i).dsInvTpDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).dsInvTpNm_A.setInputProtected(true);
                // END 2016/07/14 K.Kojima [QC#11507,MOD]
                scrnMsg.A.no(i).invErrMsgTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).invldValTxt_A.setInputProtected(true);
                // START 2018/05/28 Y.Matsui [QC#26342,ADD]
                scrnMsg.A.no(i).custIssPoNum_A.setInputProtected(true);
                // END   2018/05/28 Y.Matsui [QC#26342,ADD]
            }
            //----end 2016/03/23
        } else {
            handler.setButtonEnabled("Activity", false);
            handler.setButtonEnabled("Details", false);
            handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);

            scrnMsg.setFocusItem(scrnMsg.billToCustAcctNm_H);
            
          //---- start add 2016/03/22 QC#5770
            // START 2016/07/12 K.Kojima [QC#11049,MOD]
            // handler.setButtonEnabled("btnSelectAll", false);
            handler.setButtonEnabled("btnSlectAll", false);
            // END 2016/07/12 K.Kojima [QC#11049,MOD]
            handler.setButtonEnabled("btnUnslctAll", false);
            handler.setButtonEnabled("btnRegen", false);
            //---- end 2016/03/22
        }
   }

    // START 2016/03/14 T.Tsuchida [QC#5423,MOD]
    /**
     * controlScreenDetailFields
     * @param scrnMsg NFCL3050BMsg
     */
    private static final void controlScreenDetailFields(NFCL3050_ABMsgArray lineMsgArray) {

        for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
            lineMsgArray.no(i).billToCustAcctNm_A.setInputProtected(true);
            lineMsgArray.no(i).billToCustAcctCd_A.setInputProtected(true);
            lineMsgArray.no(i).invNum_A.setInputProtected(true);
            lineMsgArray.no(i).invDt_A.setInputProtected(true);
            lineMsgArray.no(i).invDueDt_A.setInputProtected(true);
            lineMsgArray.no(i).invTotDealNetAmt_A.setInputProtected(true);
            lineMsgArray.no(i).dealRmngBalGrsAmt_A.setInputProtected(true);
            lineMsgArray.no(i).dsContrNum_A.setInputProtected(true);
            lineMsgArray.no(i).xxScrItem61Txt_B.setInputProtected(true);
            // START 2022/11/28 M.Nakajima [QC#60742,ADD]
            lineMsgArray.no(i).cltPsnNm_A.setInputProtected(true);
            // END 2022/11/28 M.Nakajima [QC#60742,ADD]
            // START 2016/07/14 K.Kojima [QC#11507,MOD]
            // lineMsgArray.no(i).dsInvTpDescTxt_A.setInputProtected(true);
            lineMsgArray.no(i).dsInvTpNm_A.setInputProtected(true);
            // END 2016/07/14 K.Kojima [QC#11507,MOD]
            // START 2016/07/14 K.Kojima [QC#11507,MOD]
            // lineMsgArray.no(i).invTpDescTxt_A.setInputProtected(true);
            lineMsgArray.no(i).invTpNm_A.setInputProtected(true);
            // END 2016/07/14 K.Kojima [QC#11507,MOD]
            lineMsgArray.no(i).invSrcTxt_A.setInputProtected(true);
            // START 2016/07/14 K.Kojima [QC#11507,MOD]
            // lineMsgArray.no(i).arCashApplyStsDescTxt_A.setInputProtected(true);
            lineMsgArray.no(i).arCashApplyStsNm_A.setInputProtected(true);
            // END 2016/07/14 K.Kojima [QC#11507,MOD]
            lineMsgArray.no(i).dealCltDsptAmt_A.setInputProtected(true);
            lineMsgArray.no(i).cltDsptDt_A.setInputProtected(true);
            lineMsgArray.no(i).invErrMsgTxt_A.setInputProtected(true);
            lineMsgArray.no(i).invldValTxt_A.setInputProtected(true);
        }
    }
    // END 2016/03/14 T.Tsuchida [QC#5423,MOD]

    /**
     * addCheckItemHeader
     * @param scrnMsg NFCL3050BMsg
     */
    public static void addCheckItemHeader(NFCL3050BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.billToCustAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm_H);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H);
        scrnMsg.addCheckItem(scrnMsg.invNum_FR);
        scrnMsg.addCheckItem(scrnMsg.invNum_TO);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_TO);
        scrnMsg.addCheckItem(scrnMsg.dueDt_FR);
        scrnMsg.addCheckItem(scrnMsg.dueDt_TO);
        scrnMsg.addCheckItem(scrnMsg.dealRmngBalGrsAmt_LO);
        scrnMsg.addCheckItem(scrnMsg.dealRmngBalGrsAmt_HI);
        scrnMsg.addCheckItem(scrnMsg.invTotDealNetAmt_LO);
        scrnMsg.addCheckItem(scrnMsg.invTotDealNetAmt_HI);
        scrnMsg.addCheckItem(scrnMsg.dsInvTpCd_L);
        scrnMsg.addCheckItem(scrnMsg.invTpCd_L);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_H);
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CL);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CN);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_ER);
        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        scrnMsg.addCheckItem(scrnMsg.glDt_FR);
        scrnMsg.addCheckItem(scrnMsg.glDt_TO);
        scrnMsg.addCheckItem(scrnMsg.srcSysDocNum);
        // START 2016/08/23 S.Fujita [QC#13478,MOD]
//        scrnMsg.addCheckItem(scrnMsg.xxChkBox_IC);
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_H);
        // END   2016/08/23 S.Fujita [QC#13478,MOD]
        // END 2016/07/12 K.Kojima [QC#11049,ADD]
        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        scrnMsg.addCheckItem(scrnMsg.custIncdtId);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_TO);
        scrnMsg.addCheckItem(scrnMsg.ezInUserID);
        // END 2024/03/05 TZ.Win [QC#63665, ADD]
    }

    /**
     * inputCheckHeader
     * @param scrnMsg NFCL3050BMsg
     */
    public static void inputCheckHeader(NFCL3050BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFromDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxToDt_TO)) {
            if (0 < scrnMsg.xxFromDt_FR.getValue().compareTo(scrnMsg.xxToDt_TO.getValue())) {
                // START 2016/03/14 T.Tsuchida [QC#5423,MOD]
                scrnMsg.xxFromDt_FR.setErrorInfo(1, "NLZM2277E", new String[]{"Invoice Date To", "Invoice Date From"});
                scrnMsg.xxToDt_TO.setErrorInfo(1, "NLZM2277E", new String[]{"Invoice Date To", "Invoice Date From"});
                // END 2016/03/14 T.Tsuchida [QC#5423,MOD]
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.dueDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.dueDt_TO)) {
            if (0 < scrnMsg.dueDt_FR.getValue().compareTo(scrnMsg.dueDt_TO.getValue())) {
                // START 2016/03/14 T.Tsuchida [QC#5423,MOD]
//                scrnMsg.dueDt_FR.setErrorInfo(1, "NLZM2277E", new String[]{"Due date to", "Due date From"});
//                scrnMsg.dueDt_TO.setErrorInfo(1, "NLZM2277E", new String[]{"Due date to", "Due date From"});
                // START 2016/07/12 K.Kojima [QC#11049,MOD]
                // scrnMsg.dueDt_FR.setErrorInfo(1, "NLZM2277E", new
                // String[]{"Due Date to", "Due Date From"});
                // scrnMsg.dueDt_TO.setErrorInfo(1, "NLZM2277E", new
                // String[]{"Due Date to", "Due Date From"});
                scrnMsg.dueDt_FR.setErrorInfo(1, "NLZM2277E", new String[] {"Due Date To", "Due Date From" });
                scrnMsg.dueDt_TO.setErrorInfo(1, "NLZM2277E", new String[] {"Due Date To", "Due Date From" });
                // END 2016/07/12 K.Kojima [QC#11049,MOD]
                // END 2016/03/14 T.Tsuchida [QC#5423,MOD]
            }
        }

        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.glDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.glDt_TO)) {
            if (0 < scrnMsg.glDt_FR.getValue().compareTo(scrnMsg.glDt_TO.getValue())) {
                scrnMsg.glDt_FR.setErrorInfo(1, "NLZM2277E", new String[] {"GL Date To", "GL Date From" });
                scrnMsg.glDt_TO.setErrorInfo(1, "NLZM2277E", new String[] {"GL Date To", "GL Date From" });
            }
        }
        // END 2016/07/12 K.Kojima [QC#11049,ADD]
        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.custIncdtId)) {
            if (!ZYPCommonFunc.isNumberCheck(scrnMsg.custIncdtId.getValue())) {
                scrnMsg.custIncdtId.setErrorInfo(1, "ZZZM9026E", new String[]{"CI Number"});
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_TO)) {
            if (0 < scrnMsg.xxCratDt_FR.getValue().compareTo(scrnMsg.xxCratDt_TO.getValue())) {
                scrnMsg.xxCratDt_FR.setErrorInfo(1, "NFCM0922E", new String[] {"Creation Date To", "Creation Date From" });
                scrnMsg.xxCratDt_TO.setErrorInfo(1, "NFCM0922E", new String[] {"Creation Date To", "Creation Date From" });
            }
        }
        // END 2024/03/05 TZ.Win [QC#63665, ADD]

        addCheckItemHeader(scrnMsg);
    }
    
    //---- start 2016/03/22 QC#5770
    public static void isSelected(NFCL3050BMsg scrnMsg) {
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                return;
            }
        }
        
        scrnMsg.A.no(0).xxChkBox_A.setErrorInfo(1, NFAM0075E);
        scrnMsg.addCheckItem(scrnMsg.A.no(0).xxChkBox_A);

        scrnMsg.putErrorScreen();
    }
    
    //---- end 2016/03/22

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    /**
     * @param scrnMsg NFCL3050BMsg
     */
    public static void setDisplayPattern(NFCL3050BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_T020) || funcList.contains(FUNC_T030)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, DIS_PAT_VISIBILITY_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, DIS_PAT_INVISIBILITY_ERROR);
        }
    }

    /**
     * @param scrnMsg NFCL3050BMsg
     * @return boolean
     */
    private static boolean isFunctionT020(NFCL3050BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_T020) && !funcList.contains(FUNC_T030)) {
            return true;
        }
        return false;
    }

    /**
     * @param scrnMsg NFCL3050BMsg
     */
    public static void initDisplayFunction(NFCL3050BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        if (scrnMsg.xxDplyTab.getValue().equals(DIS_PAT_INVISIBILITY_ERROR)) {
            setItemVisibility(scrnMsg, ID_BTN_SELECT_ALL, false);
            setItemVisibility(scrnMsg, ID_BTN_UNSELECT_ALL, false);
            setItemVisibility(scrnMsg, ID_BTN_REGENERATE_ACCT_DIST, false);
            setItemVisibility(scrnMsg, ID_INVOICE_ERROR, false);
        }
        if (isFunctionT020(scrnMsg)) {
            // MOD START 2018/02/22 S21_NA#24241
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_ER, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_ER, ZYPConstant.FLG_ON_Y);
            // MOD END 2018/02/22 S21_NA#24241
            scrnMsg.xxChkBox_ER.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg NFCL3050BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFCL3050BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute(SCRN_ID_00, itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }
    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    // 2018/10/18 QC#28434 Add Start
    /**
     * Set To Search Condition
     * @param scrnMsg NFCL3050BMsg
     */
    public static void setToSearchCondition(NFCL3050BMsg scrnMsg) {

        // Invoice Number
        if (ZYPCommonFunc.hasValue(scrnMsg.invNum_FR) && !ZYPCommonFunc.hasValue(scrnMsg.invNum_TO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_TO, scrnMsg.invNum_FR);
        }

        // Invoice Date
        if (ZYPCommonFunc.hasValue(scrnMsg.xxFromDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.xxToDt_TO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxToDt_TO, scrnMsg.xxFromDt_FR);
        }

        // Due Date
        if (ZYPCommonFunc.hasValue(scrnMsg.dueDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.dueDt_TO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dueDt_TO, scrnMsg.dueDt_FR);
        }

        // GL Date
        if (ZYPCommonFunc.hasValue(scrnMsg.glDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.glDt_TO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.glDt_TO, scrnMsg.glDt_FR);
        }
    }
    // 2018/10/18 QC#28434 Add End
}
