/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0860.common;

import static business.servlet.NFCL0860.constant.NFCL0860Constant.BIZ_ID;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_01_SAV_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_01_SAV_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_01_SAV_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_02_SUB_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_02_SUB_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_02_SUB_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_03_APL_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_03_APL_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_03_APL_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_04_APR_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_04_APR_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_04_APR_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_05_REJ_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_05_REJ_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_05_REJ_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_06_DWL_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_06_DWL_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_06_DWL_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_07_DEL_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_07_DEL_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_07_DEL_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_08_CLE_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_08_CLE_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_08_CLE_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_09_RST_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_09_RST_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_09_RST_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_10_RTR_GUARD;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_10_RTR_LABEL;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.BTN_10_RTR_NAME;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.FUNC_ID_UPDATE;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.SCRN_ID_00;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.ZZSM4300E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL0860.NFCL0860BMsg;
import business.servlet.NFCL0860.NFCL0860_ABMsg;
import business.servlet.NFCL0860.NFCL0860_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFCL0860CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/22    Fujitsu         S.Fujita        Create          N/A
 * 2016/03/14   Hitachi         T.Tsuchida      Update          QC#5431
 * 2016/05/13   CSAI            K.Uramori       Update          QC#7983
 * 2017/08/22   Hitachi         T.Tsuchida      Update          QC#19573
 * 2020/01/30   Fujitsu         H.Ikeda         Update          QC#55631
 * 2022/04/22   CITS            K.Suzuki        Update          QC#59333
 *</pre>
 */
public class NFCL0860CommonLogic {

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
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * setGuiAttr
     * @param handler S21CommonHandler
     * @param scrnMsg BMAL0040BMsg
     */
    public static void setGuiAttr(S21CommonHandler handler, NFCL0860BMsg scrnMsg) {

        //Details
        NFCL0860_ABMsgArray lineMsgArray = scrnMsg.A;
        boolean hasUpdateFunc = hasUpdateFuncId();
        boolean btnFlg = false;

        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                NFCL0860_ABMsg lineMsg = lineMsgArray.no(i);

                if (hasUpdateFunc && lineMsg.xxNum_A.getValue().compareTo(BigDecimal.valueOf(2)) == 0) {
                    //---- start 2016/05/16 Check Cancel Flag. Only non-cancel record can be unapplied.
                    if (ZYPConstant.FLG_OFF_N.equals(lineMsg.arScrCancFlg_A.getValue())) {
                        lineMsg.xxChkBox_A.setInputProtected(false);
                        btnFlg = true;
                    } else {
                        lineMsg.xxChkBox_A.setInputProtected(true);
                    }
                    // START 2022/04/22 K.Suzuki [QC#59333,ADD]
                } else if (hasUpdateFunc && lineMsg.xxNum_A.getValue().compareTo(BigDecimal.valueOf(3)) == 0
                        && ZYPCommonFunc.hasValue(lineMsg.arAdjTpCd_A)
                        && AR_ADJ_TP.OPEN_A_OR_R_REFUND.equals(lineMsg.arAdjTpCd_A.getValue())) {
                    // record can be unapplied.
                    if (ZYPConstant.FLG_OFF_N.equals(lineMsg.arScrCancFlg_A.getValue())) {
                        lineMsg.xxChkBox_A.setInputProtected(false);
                        btnFlg = true;
                    } else {
                        lineMsg.xxChkBox_A.setInputProtected(true);
                    }
                    // END   2022/04/22 K.Suzuki [QC#59333,ADD]
                } else {
                    lineMsg.xxChkBox_A.setInputProtected(true);
                }

                // START 2016/03/14 T.Tsuchida [QC#5431,MOD]
                //lineMsg.arAdjTpNm_A.setInputProtected(true);
                lineMsg.arAdjTpDescTxt_A.setInputProtected(true);
                // END 2016/03/14 T.Tsuchida [QC#5431,MOD]
                lineMsg.adjCmntTxt_A.setInputProtected(true);
                // START 2017/08/22 T.Tsuchida [QC#19573,ADD]
                lineMsg.arWrtOffNoteTxt_A.setInputProtected(true);
                // END 2017/08/22 T.Tsuchida [QC#19573,ADD]
                lineMsg.dealOrigGrsAmt_A.setInputProtected(true);
                lineMsg.dealApplyAmt_A.setInputProtected(true);
            }

            if (hasUpdateFunc && btnFlg) {
                handler.setButtonEnabled("Unapply", true);
                
            } else {
                handler.setButtonEnabled("Unapply", false);
            }
        } else {
            handler.setButtonEnabled("Unapply", false);
            handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        }
        //---- start add 2016/05/13 QC#7983
        initLink(handler, scrnMsg);
        //---- end 2016/05/13
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL0860BMsg
     * @param scrnAMsgAry NFCL0860_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NFCL0860BMsg scrnMsg, NFCL0860_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL0860BMsg
     * @param scrnAMsgAry NFCL0860_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFCL0860BMsg scrnMsg, NFCL0860_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NFCL0860BMsg
     * @param scrnAMsgAry NFCL0860_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFCL0860BMsg scrnMsg, NFCL0860_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * inputCheckHeader
     * @param scrnMsg NFCL0860BMsg
     */
    public static void clearScreen(NFCL0860BMsg scrnMsg) {

        NFCL0860_ABMsgArray lineMsgArray = scrnMsg.A;
        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A.clear();
            }
        }
    }

    /**
     * Initialize Link
     * If the field is null, make it invisible.
     * @param scrnMsg NFCL0860BMsg
     * @param handler EZ Common Handler
     */
    //---- start add 2016/05/13
    public static void initLink(EZDCommonHandler handler, NFCL0860BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if ( !AR_TRX_TP.ON_ACCOUNT.equals(scrnMsg.A.no(i).arTrxTpCd_B1.getValue()) && !AR_TRX_TP.DEDUCTION.equals(scrnMsg.A.no(i).arTrxTpCd_B1.getValue())) {

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxNum_A)) {
                    setItemVisibility(scrnMsg, "arTrxNum_A#" + i, true);
                } else {
                    setItemVisibility(scrnMsg, "arTrxNum_A#" + i, false);
                }
            }

        }
    }

    /**
     * Set Item Visibility
     * @param scrnMsg NFCL0860BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFCL0860BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute(SCRN_ID_00, itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }
    //---- end 2016/05/13

    // START 2020/01/30 H.Ikeda [QC#55631, ADD]
    /**
     * setCheckAll
     * 
     * @param handler S21CommonHandler
     * @param scrnMsg NFCL0860BMsg
     */
    public static void setCheckAll(S21CommonHandler handler, NFCL0860BMsg scrnMsg) {
        //Details
        NFCL0860_ABMsgArray lineMsgArray = scrnMsg.A;

        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                NFCL0860_ABMsg lineMsg = lineMsgArray.no(i);
                if (ZYPConstant.FLG_OFF_N.equals(lineMsg.arScrCancFlg_A.getValue())) {
                    lineMsg.xxChkBox_A.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            }
        }
    }
    // END   2020/01/30 H.Ikeda [QC#55631, ADD]
}
