/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0730.common;

import static business.servlet.NSAL0730.constant.NSAL0730Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0730.NSAL0730BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2015/12/16   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 * 2023/04/28   Hitachi         K.Watanabe      Update          QC#61261
 *</pre>
 */
public class NSAL0730CommonLogic {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0730BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NSAL0730BMsg scrnMsg) {

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (functionList.contains("NSAL0730T020")) {
            initCommonButton(handler, scrnMsg);
        } else {
            initInactiveCommonButton(handler, scrnMsg);
        }
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0730BMsg
     */
    public static void initCommonButton(EZDCommonHandler handler, NSAL0730BMsg scrnMsg) {

        // common button
        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_ACTIVE, null);
        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
     // START 2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
     // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);

    }

    /**
     * Initialize the items and buttons on the screen.(InActive)
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0730BMsg
     */
    public static void initInactiveCommonButton(EZDCommonHandler handler, NSAL0730BMsg scrnMsg) {

        // common button
        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
     // START 2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
     // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);
    }

    private static void controlScreenFields(EZDCommonHandler handler, NSAL0730BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    private static void setRowColors(NSAL0730BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField(TBL_ID).get(scrnMsg);
            tblColor.setAlternateRowsBG(TBL_ID, table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Control ScreenDetail Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0730BMsg
     */
    public static void controlScreenDetailFields(EZDCommonHandler handler, NSAL0730BMsg scrnMsg) {

        boolean inactiveFlg1 = false;
        boolean inactiveFlg2 = false;
        boolean inactiveFlg3 = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            String lvlNum = scrnMsg.A.no(i).dsContrMachLvlNum.getValue();
            String flg1 = scrnMsg.A.no(i).xxExstFlg_D1.getValue();
            String flg2 = scrnMsg.A.no(i).xxExstFlg_D2.getValue();
            String flg3 = scrnMsg.A.no(i).xxExstFlg_D3.getValue();
            // START 2023/04/28 K.Watanabe [QC#61261, MOD]
            // String leaseCmpyFlg = scrnMsg.A.no(i).leaseCmpyFlg.getValue();

            // if (LVL_NUM_1.equals(lvlNum) && setInactive(lvlNum, leaseCmpyFlg, flg1, flg2, flg3)) {
            if (LVL_NUM_1.equals(lvlNum) && setInactive(lvlNum, flg1, flg2, flg3)) {
                inactiveFlg1 = true;
            // } else if (LVL_NUM_1.equals(lvlNum) && !setInactive(lvlNum, leaseCmpyFlg, flg1, flg2, flg3)) {
            } else if (LVL_NUM_1.equals(lvlNum) && !setInactive(lvlNum, flg1, flg2, flg3)) {
                inactiveFlg1 = false;
            }

            // if (LVL_NUM_2.equals(lvlNum) && setInactive(lvlNum, leaseCmpyFlg, flg1, flg2, flg3)) {
            if (LVL_NUM_2.equals(lvlNum) && setInactive(lvlNum, flg1, flg2, flg3)) {
                inactiveFlg2 = true;
            // } else if (LVL_NUM_2.equals(lvlNum) && !setInactive(lvlNum, leaseCmpyFlg, flg1, flg2, flg3)) {
            } else if (LVL_NUM_2.equals(lvlNum) && !setInactive(lvlNum, flg1, flg2, flg3)) {
                inactiveFlg2 = false;
            }

            // if (LVL_NUM_3.equals(lvlNum) && setInactive(lvlNum, leaseCmpyFlg, flg1, flg2, flg3)) {
            if (LVL_NUM_3.equals(lvlNum) && setInactive(lvlNum, flg1, flg2, flg3)) {
                inactiveFlg3 = true;
            // } else if (LVL_NUM_3.equals(lvlNum) && !setInactive(lvlNum, leaseCmpyFlg, flg1, flg2, flg3)) {
            } else if (LVL_NUM_3.equals(lvlNum) && !setInactive(lvlNum, flg1, flg2, flg3)) {
                inactiveFlg3 = false;
            }
            // END 2023/04/28 K.Watanabe [QC#61261, MOD]

            // Contract#
            scrnMsg.A.no(i).xxScrItem34Txt.setInputProtected(true);
            // Contract# Serial# Select
            if (inactiveFlg1 || inactiveFlg2) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFlgNm, ZYPConstant.FLG_ON_1);
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(false);
            }
            // Serial#
            scrnMsg.A.no(i).serNum.setInputProtected(true);
            // Base/Overage Select
            if (inactiveFlg1 || inactiveFlg2 || inactiveFlg3) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFlgNm, ZYPConstant.FLG_ON_1);
                scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(false);
            }

            // Base/Overage
            // mod start 2016/02/29 CSA Defect#2684
            scrnMsg.A.no(i).mtrLbDescTxt.setInputProtected(true);
            // mod end 2016/02/29 CSA Defect#2684
            // START 2019/01/10 K.Kitachi [QC#26928, DEL]
//            // PO#
//            scrnMsg.A.no(i).custPoNum_D1.setInputProtected(true);
//            // Exp Date
//            scrnMsg.A.no(i).poDt_D1.setInputProtected(true);
            // END 2019/01/10 K.Kitachi [QC#26928, DEL]

            if (inactiveFlg1 || inactiveFlg2 || inactiveFlg3) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxDsMsgEntryTxt, getRtnMsg(NSAM0065E));
                // START 2019/01/10 K.Kitachi [QC#26928, MOD]
                // PO#
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFlgNm, ZYPConstant.FLG_ON_1);
//                scrnMsg.A.no(i).custPoNum_D2.setInputProtected(true);
                scrnMsg.A.no(i).custPoNum_A.setInputProtected(true);
                // PO# Button
                handler.setButtonEnabled("OpenWin_PO", i, false);
                // From Date
                scrnMsg.A.no(i).poFromDt_A.setInputProtected(true);
                // Thru Date
//                scrnMsg.A.no(i).poDt_D2.setInputProtected(true);
                scrnMsg.A.no(i).poDt_A.setInputProtected(true);
                // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            } else {
                // START 2019/01/10 K.Kitachi [QC#26928, MOD]
                // PO#
//                scrnMsg.A.no(i).custPoNum_D2.setInputProtected(false);
                scrnMsg.A.no(i).custPoNum_A.setInputProtected(false);
                // PO# Button
                handler.setButtonEnabled("OpenWin_PO", i, true);
                // From Date
                scrnMsg.A.no(i).poFromDt_A.setInputProtected(false);
                // Thru Date
//                scrnMsg.A.no(i).poDt_D2.setInputProtected(false);
                scrnMsg.A.no(i).poDt_A.setInputProtected(false);
                // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            }
            // Leasing
            scrnMsg.A.no(i).xxChkBox_D3.setInputProtected(true);
            // Return Message
            scrnMsg.A.no(i).xxDsMsgEntryTxt.setInputProtected(true);
        }
    }

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // START 2023/04/28 K.Watanabe [QC#61261, MOD]
    // private static boolean setInactive(String lvlNum, String leaseCmpyFlg, String exstFlg1, String exstFlg2, String exstFlg3) {
    private static boolean setInactive(String lvlNum, String exstFlg1, String exstFlg2, String exstFlg3) {

        // if (LVL_NUM_1.equals(lvlNum) && (ZYPConstant.FLG_ON_1.equals(exstFlg1) || ZYPConstant.FLG_ON_Y.equals(leaseCmpyFlg))) {
        if (LVL_NUM_1.equals(lvlNum) && (ZYPConstant.FLG_ON_1.equals(exstFlg1))) {
            return true;
        // } else if (!LVL_NUM_1.equals(lvlNum) && (ZYPConstant.FLG_ON_1.equals(exstFlg2) || ZYPConstant.FLG_ON_Y.equals(leaseCmpyFlg))) {
        } else if (!LVL_NUM_1.equals(lvlNum) && (ZYPConstant.FLG_ON_1.equals(exstFlg2))) {
            return true;
        // } else if (!LVL_NUM_1.equals(lvlNum) && (ZYPConstant.FLG_ON_1.equals(exstFlg3) || ZYPConstant.FLG_ON_Y.equals(leaseCmpyFlg))) {
        } else if (!LVL_NUM_1.equals(lvlNum) && (ZYPConstant.FLG_ON_1.equals(exstFlg3))) {
            return true;
        }
        return false;
    }
    // END 2023/04/28 K.Watanabe [QC#61261, MOD]

    /**
     * setBgColor
     * @param scrnMsg NSAL0730BMsg
     */
    public static void setBgColor(NSAL0730BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A);
        }
    }

    /**
     * addCheckItemForAllHeader
     * @param scrnMsg NSAL0730BMsg
     */
    public static void addCheckItemForAll(NSAL0730BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.custPoNum);
        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        scrnMsg.addCheckItem(scrnMsg.poFromDt);
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
        scrnMsg.addCheckItem(scrnMsg.poDt);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).custPoNum_D2);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).poDt_D2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).custPoNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poDt_A);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        }
    }

    // START 2019/01/10 K.Kitachi [QC#26928, ADD]
    /**
     * selectAllContract
     * @param scrnMsg NSAL0730BMsg
     */
    public static void selectAllContract(NSAL0730BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String lvlNum = scrnMsg.A.no(i).dsContrMachLvlNum.getValue();
            String dplyCtrlFlg = scrnMsg.A.no(i).xxDplyCtrlFlg.getValue();
            if (LVL_NUM_1.equals(lvlNum) && ZYPConstant.FLG_ON_Y.equals(dplyCtrlFlg)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_D1, ZYPConstant.CHKBOX_ON_Y);
            }
            if (LVL_NUM_2.equals(lvlNum) && ZYPConstant.FLG_ON_Y.equals(dplyCtrlFlg)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_D1, ZYPConstant.CHKBOX_ON_Y);
            }
        }
    }

    /**
     * selectAllSerial
     * @param scrnMsg NSAL0730BMsg
     */
    public static void selectAllSerial(NSAL0730BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String lvlNum = scrnMsg.A.no(i).dsContrMachLvlNum.getValue();
            String dplyCtrlFlg = scrnMsg.A.no(i).xxDplyCtrlFlg.getValue();
            if (LVL_NUM_3.equals(lvlNum) && ZYPConstant.FLG_ON_Y.equals(dplyCtrlFlg)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_D2, ZYPConstant.CHKBOX_ON_Y);
            }
        }
    }
    // END 2019/01/10 K.Kitachi [QC#26928, ADD]
}
