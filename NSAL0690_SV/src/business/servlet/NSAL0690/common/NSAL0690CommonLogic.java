/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0690.common;

import static business.servlet.NSAL0690.constant.NSAL0690Constant.*;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0690.NSAL0690BMsg;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/11/26   Hitachi         T.Tsuchida      Update          QC#1225
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1611
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1577
 * 2015/12/23   Hitachi         T.Tsuchida      Update          QC#2390
 * 2016/02/23   Hitachi         K.Kasai         Update          QC#4132
 * 2017/01/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/10/27   Hitachi         K.Kojima        Update          QC#21742
 * 2017/11/13   Hitachi         M.Kidokoro      Update          QC#21444
 * 2018/05/30   CITS            M.Naito         Update          QC#22887
 *</pre>
 */
public class NSAL0690CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0690BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0690BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0690BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0690BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        // START 2017/01/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2017/01/13 K.Ochiai [QC#16331, MOD]

        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0690BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0690BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0690BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0690BMsg scrnMsg) {
        scrnMsg.xxNum_H1.setInputProtected(false);
        scrnMsg.bllgCycleUomCd_H3.setInputProtected(false);
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0690BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0690BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxNum_A1.setInputProtected(false);
            scrnMsg.A.no(i).bllgCycleUomCd_A3.setInputProtected(false);
            scrnMsg.A.no(i).xxChkBox_AL.setInputProtected(false);
            // START 2017/10/27 K.Kojima [QC#21742,MOD]
            // if (protectContrLine(scrnMsg.A.no(i).dsContrCtrlStsCd_A1.getValue())) {
            if (protectContrLine(scrnMsg.A.no(i).contrRnwAvalFlg_A1.getValue())) {
            // END 2017/10/27 K.Kojima [QC#21742,MOD]
                scrnMsg.A.no(i).xxNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).bllgCycleUomCd_A3.setInputProtected(true);
                // add start 2016/02/23 CSA Defect#4132
                scrnMsg.A.no(i).xxChkBox_AL.setInputProtected(true);
                // add end 2016/02/23 CSA Defect#4132
            }
            scrnMsg.A.no(i).basePrcDealAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).newBaseDealAmt_A1.setInputProtected(true);
            // START 2015/11/26 T.Tsuchida [QC#1225,ADD]
            scrnMsg.A.no(i).basePrcDealAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).newBaseDealAmt_A1.setAppFracDigit(2);
            // END 2015/11/26 T.Tsuchida [QC#1225,ADD]
        }
        for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
            scrnMsg.B.no(j).xxChkBox_B1.setInputProtected(false);
            scrnMsg.B.no(j).xxThruDt_B1.setInputProtected(false);
            // START 2017/10/27 K.Kojima [QC#21742,MOD]
            // if (protectContrLine(scrnMsg.B.no(j).dsContrCtrlStsCd_BH.getValue())
            //         || protectContrLine(scrnMsg.B.no(j).dsContrCtrlStsCd_BD.getValue())) {
            if (protectContrLine(scrnMsg.B.no(j).contrRnwAvalFlg_BH.getValue())
                    || protectContrLine(scrnMsg.B.no(j).contrRnwAvalFlg_BD.getValue())) {
            // END 2017/10/27 K.Kojima [QC#21742,MOD]
                scrnMsg.B.no(j).xxChkBox_B1.setInputProtected(true);
                scrnMsg.B.no(j).xxThruDt_B1.setInputProtected(true);
            }
            // START 2017/11/13 M.Kidokoro [QC#21444, ADD]
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.B.no(j).dsContrCatgCd_B1.getValue())
                    && !protectContrLine(scrnMsg.B.no(j).contrRnwAvalFlg_BH.getValue())
                    && !protectContrLine(scrnMsg.B.no(j).contrRnwAvalFlg_BD.getValue())){
                scrnMsg.B.no(j).xxChkBox_B1.setInputProtected(true);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(j).xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                scrnMsg.B.no(j).xxThruDt_B1.setInputProtected(true);
            }
            // END 2017/11/13 M.Kidokoro [QC#21444, ADD]
            scrnMsg.B.no(j).mdseCd_B1.setInputProtected(true);
            scrnMsg.B.no(j).serNum_B1.setInputProtected(true);
            scrnMsg.B.no(j).t_MdlNm_B1.setInputProtected(true);
            scrnMsg.B.no(j).xxScrItem8Txt_B1.setInputProtected(true);
            scrnMsg.B.no(j).dsContrCtrlStsNm_B1.setInputProtected(true);
            scrnMsg.B.no(j).contrEffFromDt_B1.setInputProtected(true);
            scrnMsg.B.no(j).xxDiscRatio_B1.setInputProtected(true);
            scrnMsg.B.no(j).contrEffThruDt_B1.setInputProtected(true);
            scrnMsg.B.no(j).basePrcDealAmt_B1.setInputProtected(true);
            scrnMsg.B.no(j).newBaseDealAmt_B1.setInputProtected(true);
            scrnMsg.B.no(j).xxGenlFldAreaTxt_B1.setInputProtected(true);
            // START 2015/11/26 T.Tsuchida [QC#1225,ADD]
            // START 2018/05/30 M.Naito [QC#22887, MOD]
//            scrnMsg.B.no(j).xxDiscRatio_B1.setAppFracDigit(0);
            scrnMsg.B.no(j).xxDiscRatio_B1.setAppFracDigit(2);
            // START 2018/05/30 M.Naito [QC#22887, MOD]
            scrnMsg.B.no(j).basePrcDealAmt_B1.setAppFracDigit(2);
            scrnMsg.B.no(j).newBaseDealAmt_B1.setAppFracDigit(2);
            // END 2015/11/26 T.Tsuchida [QC#1225,ADD]
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0690BMsg
     */
    private static void setRowColors(NSAL0690BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // START 2017/10/27 K.Kojima [QC#21742,MOD]
    // /**
    //  * @param contrCtrlStsCd String
    //  */
    // private static boolean protectContrLine(String contrCtrlStsCd) {
    //     if (!DS_CONTR_CTRL_STS.ACTIVE.equals(contrCtrlStsCd)
    //             && !DS_CONTR_CTRL_STS.SINGED.equals(contrCtrlStsCd)
    //             && !DS_CONTR_CTRL_STS.RENEWAL_HOLD.equals(contrCtrlStsCd)) {
    //         return true;
    //     }
    //     return false;
    // }
    /**
     * @param contrRnwAvalFlg String
     */
    private static boolean protectContrLine(String contrRnwAvalFlg) {
        if (!ZYPCommonFunc.hasValue(contrRnwAvalFlg) || ZYPConstant.FLG_OFF_N.equals(contrRnwAvalFlg)) {
            return true;
        }
        return false;
    }
    // END 2017/10/27 K.Kojima [QC#21742,MOD]

    /**
     * addCheckItem
     * @param scrnMsg NSAL0690BMsg
     */
    public static void addCheckItemForApply(NSAL0690BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.xxNum_H1);
        scrnMsg.addCheckItem(scrnMsg.bllgCycleUomCd_H3);
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL0690BMsg
     */
    public static void addCheckItemForRegister(NSAL0690BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_B1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxThruDt_B1);
            }
        }
    }
}
