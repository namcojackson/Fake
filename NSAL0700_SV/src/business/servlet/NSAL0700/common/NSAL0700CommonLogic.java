/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0700.common;

import static business.servlet.NSAL0700.constant.NSAL0700Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBMsgArray;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0700.NSAL0700BMsg;
import business.servlet.NSAL0700.NSAL0700_ABMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2015/12/11   Hitachi         T.Tsuchida      Update          QC#1611
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/02/26   Hitachi         K.Kasai         Update          QC#2684
 * 2016/07/08   Hitachi         M.Gotou         Update          QC#11598, QC#11599
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/09/04   Hitachi         K.Kojima        Update          QC#20816
 *</pre>
 */
public class NSAL0700CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0700BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0700BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0700BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0700BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
     // START 2017/02/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
     // END   2017/02/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0700BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0700BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0700BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0700BMsg scrnMsg) {
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
        scrnMsg.xxChkBox_H1.setInputProtected(false);
        scrnMsg.xxChkBox_H2.setInputProtected(false);
        scrnMsg.xxChkBox_H3.setInputProtected(false);
        // START 2016/07/08 M.Gotou [QC#11598, DEL]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, ZYPConstant.CHKBOX_ON_Y);
        // END 2016/07/08 M.Gotou [QC#11598, DEL]
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0700BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0700BMsg scrnMsg) {
        // mod start 2016/11/28 CSA QC#4210
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        // mod end 2016/11/28 CSA QC#4210
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            boolean isProtectedCheckBoxContract = isProtectedCheckBoxContract(scrnMsg.A.no(i));
            boolean isProtectedCheckBoxSerial = isProtectedCheckBoxSerial(scrnMsg.A.no(i), isProtectedCheckBoxContract);
            boolean isProtectedCheckBoxBaseOverage = isProtectedCheckBoxBaseOverage(scrnMsg.A.no(i), isProtectedCheckBoxContract, isProtectedCheckBoxSerial);
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(isProtectedCheckBoxContract);
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(isProtectedCheckBoxSerial);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A3.setInputProtected(isProtectedCheckBoxBaseOverage);
            // mod start 2016/02/26 CSA Defect#2684
            scrnMsg.A.no(i).mtrLbDescTxt_A1.setInputProtected(true);
            // mod end 2016/02/26 CSA Defect#2684
            scrnMsg.A.no(i).dsContrCtrlStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgCycleUomNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).nextBllgDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).basePrcDealAmt_A1.setAppFracDigit(2);
            isVisibility(scrnMsg, scrnMsg.A.no(i).xxRefCseNum_A1.getValueInt(), i, scrnMsg.A.no(i).dsContrCatgCd_A1.getValue());
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0700BMsg
     */
    private static void setRowColors(NSAL0700BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void isVisibility(NSAL0700BMsg scrnMsg, int lvlNum, int idx, String dsContrCatgCd) {
        if (lvlNum == LVL_NUM_10) {
            isVisibilityContract(scrnMsg, idx);
        } else if (lvlNum == LVL_NUM_20) {
            isVisibilitySerial(scrnMsg, idx);
        } else if (lvlNum == LVL_NUM_30) {
            isVisibilityBaseOverage(scrnMsg, idx);
        }

        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            isVisibilityFlt(scrnMsg, idx);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            isVisibilityAgg(scrnMsg, idx);
        }
    }

    private static void setVisibilityItem(NSAL0700BMsg scrnMsg, String itemId) {
        EZDGUIAttribute xxItem = new EZDGUIAttribute(SCREEN_ID, itemId);
        xxItem.setVisibility(false);
        scrnMsg.addGUIAttribute(xxItem);
    }

    private static void isVisibilityContract(NSAL0700BMsg scrnMsg, int idx) {
        setVisibilityItem(scrnMsg, "xxChkBox_A2#" + idx);
        setVisibilityItem(scrnMsg, "serNum_A1#" + idx);
        setVisibilityItem(scrnMsg, "xxChkBox_A3#" + idx);
        // mod start 2016/02/26 CSA Defect#2684
        setVisibilityItem(scrnMsg, "mtrLbDescTxt_A1#" + idx);
        // mod end 2016/02/26 CSA Defect#2684
        // START 2016/07/08 M.Gotou [QC#11599, ADD]
        scrnMsg.A.no(idx).xxChkBox_A2.setInputProtected(true);
        scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxChkBox_A3.setInputProtected(true);
        scrnMsg.A.no(idx).mtrLbDescTxt_A1.setInputProtected(true);
        // END 2016/07/08 M.Gotou [QC#11599, ADD]
    }

    private static void isVisibilitySerial(NSAL0700BMsg scrnMsg, int idx) {
        setVisibilityItem(scrnMsg, "xxChkBox_A1#" + idx);
        setVisibilityItem(scrnMsg, "xxScrItem34Txt_A1#" + idx);
        setVisibilityItem(scrnMsg, "xxChkBox_A3#" + idx);
        // mod start 2016/02/26 CSA Defect#2684
        setVisibilityItem(scrnMsg, "mtrLbDescTxt_A1#" + idx);
        // mod end 2016/02/26 CSA Defect#2684
        // START 2016/07/08 M.Gotou [QC#11599, ADD]
        scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxScrItem34Txt_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxChkBox_A3.setInputProtected(true);
        scrnMsg.A.no(idx).mtrLbDescTxt_A1.setInputProtected(true);
        // END 2016/07/08 M.Gotou [QC#11599, ADD]
    }

    private static void isVisibilityBaseOverage(NSAL0700BMsg scrnMsg, int idx) {
        setVisibilityItem(scrnMsg, "xxChkBox_A1#" + idx);
        setVisibilityItem(scrnMsg, "xxScrItem34Txt_A1#" + idx);
        setVisibilityItem(scrnMsg, "xxChkBox_A2#" + idx);
        setVisibilityItem(scrnMsg, "serNum_A1#" + idx);
        // START 2016/07/08 M.Gotou [QC#11599, ADD]
        scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxScrItem34Txt_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxChkBox_A2.setInputProtected(true);
        scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
        // END 2016/07/08 M.Gotou [QC#11599, ADD]
    }

    private static void isVisibilityFlt(NSAL0700BMsg scrnMsg, int idx) {
        setVisibilityItem(scrnMsg, "xxChkBox_A2#" + idx);
        // START 2016/07/08 M.Gotou [QC#11599, ADD]
        scrnMsg.A.no(idx).xxChkBox_A2.setInputProtected(true);
        // END 2016/07/08 M.Gotou [QC#11599, ADD]
    }

    private static void isVisibilityAgg(NSAL0700BMsg scrnMsg, int idx) {
        setVisibilityItem(scrnMsg, "xxChkBox_A2#" + idx);
        setVisibilityItem(scrnMsg, "xxChkBox_A3#" + idx);
        // START 2016/07/08 M.Gotou [QC#11599, ADD]
        scrnMsg.A.no(idx).xxChkBox_A2.setInputProtected(true);
        scrnMsg.A.no(idx).xxChkBox_A3.setInputProtected(true);
        // END 2016/07/08 M.Gotou [QC#11599, ADD]
    }

    private static boolean isProtectedCheckBoxContract(NSAL0700_ABMsg aBMsg) {
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (isProtectedCheckBox(aBMsg.dsContrCtrlStsCd_A1.getValue()) || ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A1.getValue())) {
        if (isProtectedCheckBox(aBMsg.dsContrCtrlStsCd_A1.getValue())) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            ZYPEZDItemValueSetter.setValue(aBMsg.xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
            return true;
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        } else if (ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBMsg.xxGenlFldAreaTxt_A1, BLLG_HOLD_MSG);
        // END 2017/09/04 K.Kojima [QC#20816,ADD]
        }
        return false;
    }

    private static boolean isProtectedCheckBoxSerial(NSAL0700_ABMsg aBMsg, boolean isProtectedCheckBoxContract) {
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (isProtectedCheckBox(aBMsg.dsContrCtrlStsCd_A1.getValue()) || ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A2.getValue()) || isProtectedCheckBoxContract) {
        if (isProtectedCheckBox(aBMsg.dsContrCtrlStsCd_A1.getValue()) || (ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A2.getValue()) && ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A1.getValue())) || isProtectedCheckBoxContract) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            ZYPEZDItemValueSetter.setValue(aBMsg.xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
            return true;
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        } else if (ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A2.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBMsg.xxGenlFldAreaTxt_A1, BLLG_HOLD_MSG);
        // END 2017/09/04 K.Kojima [QC#20816,ADD]
        }
        return false;
    }

    private static boolean isProtectedCheckBoxBaseOverage(NSAL0700_ABMsg aBMsg, boolean isProtectedCheckBoxContract, boolean isProtectedCheckBoxSerial) {
        // START 2017/09/04 K.Kojima [QC#20816,MOD]
        // if (isProtectedCheckBox(aBMsg.dsContrCtrlStsCd_A1.getValue()) || ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A3.getValue()) || isProtectedCheckBoxContract || isProtectedCheckBoxSerial) {
        if (isProtectedCheckBox(aBMsg.dsContrCtrlStsCd_A1.getValue()) || (ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A3.getValue()) && ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A2.getValue())) || isProtectedCheckBoxContract || isProtectedCheckBoxSerial) {
        // END 2017/09/04 K.Kojima [QC#20816,MOD]
            ZYPEZDItemValueSetter.setValue(aBMsg.xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
            return true;
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        } else if (ZYPConstant.FLG_ON_Y.equals(aBMsg.bllgHldFlg_A3.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBMsg.xxGenlFldAreaTxt_A1, BLLG_HOLD_MSG);
        // END 2017/09/04 K.Kojima [QC#20816,ADD]
        }
        return false;
    }

    private static boolean isProtectedCheckBox(String dsContrCtrlStsCd) {
        if (DS_CONTR_CTRL_STS.EXPIRED.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.CANCELLED.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.TERMINATED.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.TERMINATED_HOLD.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.EXPIRED_HOLD.equals(dsContrCtrlStsCd)) {
            return true;
        }
        return false;
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

    /**
     * addCheckItem
     * @param scrnMsg NSAL0700BMsg
     */
    public static void addCheckItem(NSAL0700BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
    }
}
