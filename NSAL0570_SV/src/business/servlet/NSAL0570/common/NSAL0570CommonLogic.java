/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570.common;

import static business.servlet.NSAL0570.constant.NSAL0570Constant.*;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0570.NSAL0570BMsg;
import business.servlet.NSAL0570.NSAL0570_ABMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         K.Kasai         Create          N/A
 * 2015/12/04   Hitachi         K.Yamada        Update          CSA QC#1427
 * 2015/12/09   Hitachi         K.Yamada        Update          CSA QC#1662
 * 2016/04/11   Hitachi         K.Kishimoto     Update          QC#6728
 * 2016/04/27   Hitachi         T.Iwamoto       Update          QC#1759
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#4923
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/09/20   Hitachi         N.Arai          Update          QC#11616
 * 2016/11/22   Hitachi         T.Mizuki        Update          QC#16116
 * 2017/02/21   Hitachi         K.Kishimoto     Update          QC#17646
 *</pre>
 */
public class NSAL0570CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0570BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
        // ADD start 2016/04/27 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/27 CSA Defect#1759
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0570BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        // START 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2016/07/28 O.Okuma [QC#12430, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // mod start 2016/11/22 CSA QC#16116
        if (scrnMsg.xxModeCd_H1.getValue().equals(REF_MODE) || !hasUpdateFuncId()) {
        // mod end 2016/11/22 CSA QC#16116
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0570BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {
        // mod start 2016/11/22 CSA QC#16116
        if (scrnMsg.xxModeCd_H1.getValue().equals(REF_MODE) || !hasUpdateFuncId()) {
        // mod end 2016/11/22 CSA QC#16116
            controlScreenHeaderFieldsRefMode(handler, scrnMsg);
            controlScreenDetailFieldsRefMode(handler, scrnMsg);
        } else if (scrnMsg.xxModeCd_H1.getValue().equals(EDIT_MODE)) {
            controlScreenHeaderFieldsEditMode(handler, scrnMsg);
            controlScreenDetailFieldsEditMode(handler, scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFieldsEditMode
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0570BMsg
     */
    private static final void controlScreenHeaderFieldsEditMode(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {

        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        scrnMsg.t_MdlNm_H1.setInputProtected(true);
     // START 2016/09/20 N.Arai [QC#11616, MOD]
     // scrnMsg.mdseNm_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
     // END 2016/09/20 N.Arai [QC#11616, MOD]
        scrnMsg.xxFirstScdCtyStAddr_H1.setInputProtected(true);
        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
        scrnMsg.baseBllgLastBllgDt_H1.setInputProtected(true);
        scrnMsg.mtrLbNm_H1.setInputProtected(true);

        handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_DELETE_ROW[0], true);
            handler.setButtonEnabled(BTN_SCHEDULES[0], true);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], true);
        } else {
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_SCHEDULES[0], false);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], false);
        }

        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
    }

    /**
     * controlScreenDetailFieldsEditMode
     * @param scrnMsg NSAL0570BMsg
     */
    private static final void controlScreenDetailFieldsEditMode(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {
    	// Mod Start 02/08/2016 <QC#7402>
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setInputProtected(true);
            String stsCd = scrnMsg.A.no(i).dsContrDtlStsCd_A1.getValue();
            String xxExstFlg_X1 = scrnMsg.A.no(i).xxExstFlg_X1.getValue();
            String xxExstFlg_X2 = scrnMsg.A.no(i).xxExstFlg_X2.getValue();
            String xxExstFlg_X3 = scrnMsg.A.no(i).xxExstFlg_X3.getValue();
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1)) {
                scrnMsg.A.no(i).contrPrcEffFromDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).contrPrcEffFromDt_A1.setInputProtected(false);
            }
            if (isProtectDsContrCtrlStsCd(stsCd)) {
                scrnMsg.A.no(i).contrPrcEffThruDt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).contrPrcEffThruDt_A1.setInputProtected(false);
            }
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X2)) {
                scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(false);
            }
            scrnMsg.A.no(i).bllgMinCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgMinAmtRate_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgRollOverRatio_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgFreeCopyCnt_A1.setInputProtected(true);
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X3)) {
                scrnMsg.A.no(i).xsMtrCopyQty_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xsMtrCopyQty_A1.setInputProtected(false);
            }
            if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X2)) {
                scrnMsg.A.no(i).xsMtrAmtRate_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xsMtrAmtRate_A1.setInputProtected(false);
            }
            scrnMsg.A.no(i).dsContrDtlStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cratDt_A1.setInputProtected(true);
            // START 2016/05/20 T.Tomita [QC#4923, DEL]
//            scrnMsg.A.no(i).svcMemoRsnNm_A1.setInputProtected(true);
            // END 2016/05/20 T.Tomita [QC#4923, DEL]

            scrnMsg.A.no(i).bllgMinAmtRate_A1.setAppFracDigit(2);
            // mod start 2015/12/09 CSA Defect#1662
            scrnMsg.A.no(i).xsMtrAmtRate_A1.setAppFracDigit(6);
            // mod end 2015/12/09 CSA Defect#1662

            if (DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.dsContrDtlTpCd_A.getValue())) {
                handler.setButtonEnabled(BTN_INSERT_PRC_ALLOW_ROW[0], i, false);
                handler.setButtonEnabled(BTN_DELETE_PRC_ALLOW_ROW[0], i, false);
                // Add Start 02/21/2017 <QC#17646>
                scrnMsg.A.no(i).xsMtrCopyQty_A1.setInputProtected(true);
                // Add End   02/21/2017 <QC#17646>
            } else {
                if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1)) {
                    handler.setButtonEnabled(BTN_INSERT_PRC_ALLOW_ROW[0], i, false);
                } else {
                    handler.setButtonEnabled(BTN_INSERT_PRC_ALLOW_ROW[0], i, true);
                }
                if (isProtectDsContrCtrlStsCd(stsCd) || ZYPConstant.FLG_ON_Y.equals(xxExstFlg_X1) || isOnlySqNum(scrnMsg, i)) {
                    handler.setButtonEnabled(BTN_DELETE_PRC_ALLOW_ROW[0], i, false);
                } else {
                    handler.setButtonEnabled(BTN_DELETE_PRC_ALLOW_ROW[0], i, true);
                }
            }
        }
    	// Mod End   02/08/2016 <QC#7402>
    }

    /**
     * controlScreenHeaderFieldsRefMode
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0570BMsg
     */
    private static final void controlScreenHeaderFieldsRefMode(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {

        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        scrnMsg.t_MdlNm_H1.setInputProtected(true);
     // START 2016/09/20 N.Arai [QC#11616, MOD]
     // scrnMsg.mdseNm_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
     // END 2016/09/20 N.Arai [QC#11616, MOD]
        scrnMsg.xxFirstScdCtyStAddr_H1.setInputProtected(true);
        scrnMsg.contrEffFromDt_H1.setInputProtected(true);
        scrnMsg.contrEffThruDt_H1.setInputProtected(true);
        scrnMsg.baseBllgLastBllgDt_H1.setInputProtected(true);
        scrnMsg.mtrLbNm_H1.setInputProtected(true);

        handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
        handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_SCHEDULES[0], true);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], true);
        } else {
            handler.setButtonEnabled(BTN_SCHEDULES[0], false);
            handler.setButtonEnabled(BTN_SKIP_MONTH[0], false);
        }

        scrnMsg.svcMemoRsnCd_H3.setInputProtected(true);
        scrnMsg.svcCmntTxt_H1.setInputProtected(true);
    }

    /**
     * controlScreenDetailFieldsRefMode
     * @param scrnMsg NSAL0570BMsg
     */
    private static final void controlScreenDetailFieldsRefMode(EZDCommonHandler handler, NSAL0570BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrPrcEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrPrcEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgCycleCd_A3.setInputProtected(true);
            scrnMsg.A.no(i).bllgMinCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgMinAmtRate_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgRollOverRatio_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgFreeCopyCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xsMtrCopyQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).xsMtrAmtRate_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrDtlStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).cratDt_A1.setInputProtected(true);
            // START 2016/05/20 T.Tomita [QC#4923, DEL]
//            scrnMsg.A.no(i).svcMemoRsnNm_A1.setInputProtected(true);
            // END 2016/05/20 T.Tomita [QC#4923, DEL]

            // mod start 2015/12/09 CSA Defect#1662
            scrnMsg.A.no(i).xsMtrAmtRate_A1.setAppFracDigit(6);
            // mod end 2015/12/09 CSA Defect#1662

            handler.setButtonEnabled(BTN_INSERT_PRC_ALLOW_ROW[0], i, false);
            handler.setButtonEnabled(BTN_DELETE_PRC_ALLOW_ROW[0], i, false);
        }
    }

    private static boolean isProtectDsContrCtrlStsCd(String dsContrCtrlStsCd) {
        // mod start 2015/12/04 CSA Defect#1427
        if (dsContrCtrlStsCd.equals(DS_CONTR_CTRL_STS.DRAFT) || dsContrCtrlStsCd.equals(DS_CONTR_CTRL_STS.ENTERED) || dsContrCtrlStsCd.equals(DS_CONTR_CTRL_STS.QA_HOLD)) {
            return false;
        }
        // mod end 2015/12/04 CSA Defect#1427
        return false;
    }

    // ADD start 2016/04/27 CSA Defect#1759
    /**
     * protectedAllHeaderFields
     * @param NSAL0570BMsg scrnMsg
     */
    private static void setRowColors(NSAL0570BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/27 CSA Defect#1759

    /**
     * addCheckItem
     * @param scrnMsg NSAL0570BMsg
     */
    public static void addCheckItem(NSAL0570BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0570_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.contrPrcEffFromDt_A1);
            scrnMsg.addCheckItem(abMsg.contrPrcEffThruDt_A1);
            scrnMsg.addCheckItem(abMsg.bllgCycleCd_A3);
            scrnMsg.addCheckItem(abMsg.xsMtrCopyQty_A1);
            scrnMsg.addCheckItem(abMsg.xsMtrAmtRate_A1);
        }
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
    }

    /**
     * <pre>
     * copyAMsgToBMsg
     * </pre>
     * @param scrnMsg NSAL0570BMsg
     */
    public static final void copyAMsgToBMsg(NSAL0570BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setValue(scrnMsg.B.no(i).dsContrPrcEffSqNum_B1, scrnMsg.A.no(i).dsContrPrcEffSqNum_A1);
            setValue(scrnMsg.B.no(i).contrPrcEffFromDt_B1, scrnMsg.A.no(i).contrPrcEffFromDt_A1);
            setValue(scrnMsg.B.no(i).contrPrcEffThruDt_B1, scrnMsg.A.no(i).contrPrcEffThruDt_A1);
            setValue(scrnMsg.B.no(i).bllgCycleCd_B3, scrnMsg.A.no(i).bllgCycleCd_A3);
            setValue(scrnMsg.B.no(i).xsMtrCopyQty_B1, scrnMsg.A.no(i).xsMtrCopyQty_A1);
            setValue(scrnMsg.B.no(i).xsMtrAmtRate_B1, scrnMsg.A.no(i).xsMtrAmtRate_A1);
            // START 2016/05/20 T.Tomita [QC#4923, DEL]
//            setValue(scrnMsg.B.no(i).svcCmntTxt_B1, scrnMsg.A.no(i).svcCmntTxt_A1);
//            setValue(scrnMsg.B.no(i).svcMemoRsnCd_B3, scrnMsg.A.no(i).svcMemoRsnCd_A3);
            // END 2016/05/20 T.Tomita [QC#4923, DEL]
            // START 2016/06/02 T.Tomita [QC#4923, ADD]
            setValue(scrnMsg.B.no(i).dsContrDtlStsCd_B1, scrnMsg.A.no(i).dsContrDtlStsCd_A1);
            // END 2016/06/02 T.Tomita [QC#4923, ADD]
        }
        scrnMsg.B.setValidCount(scrnMsg.A.getValidCount());
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isOnlySqNum(NSAL0570BMsg scrnMsg, int idx) {

        BigDecimal curSqNum = scrnMsg.A.no(idx).dsContrPrcEffSqNum_A1.getValue();
        BigDecimal preSqNum = INVLD_DS_CONTR_PRC_EFF_SQ_NUM;
        BigDecimal nxtSqNum = INVLD_DS_CONTR_PRC_EFF_SQ_NUM;

        if (idx > 0) {
            preSqNum = scrnMsg.A.no(idx - 1).dsContrPrcEffSqNum_A1.getValue();
        }
        if (idx + 1 != scrnMsg.A.getValidCount()) {
            nxtSqNum = scrnMsg.A.no(idx + 1).dsContrPrcEffSqNum_A1.getValue();
        }
        if (isEqualNum(preSqNum, curSqNum) || isEqualNum(curSqNum, nxtSqNum)) {
            return false;
        }
        return true;
    }
    // mod start 2016/11/22 CSA QC#16116
    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Renewal Rules contract/machine level(" + BUSINESS_ID + "). UserID is -> "
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }
    // mod end 2016/11/22 CSA QC#16116
}
