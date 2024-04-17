/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550.common;

import static business.servlet.NWAL1550.constant.NWAL1550Constant.BACK_GROUND_COLOR_RED;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BACK_GROUND_COLOR_YELLOW;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_APL;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_APR;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_RST;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.BTN_EXEC_DI_CHK_EVENT_NM;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.COLUMN_NUM_INCORRECT_VALUE;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.DECIMAL_DIGITS_NUM_TWO;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.DECIMAL_DIGITS_NUM_ZERO;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.NWAM0634E;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.REF_AUTH;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.SCRN_ID_00;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.SHARP;

import java.math.BigDecimal;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import parts.servletcommon.gui.EZDGUITableAttribute;
import business.servlet.NWAL1550.NWAL1550BMsg;
import business.servlet.NWAL1550.NWAL1550Bean;
import business.servlet.NWAL1550.NWAL1550_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_DTL_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1550CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1550CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1550BMsg
     * @param scrnAMsgAry NWAL1550_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1550BMsg scrnMsg, NWAL1550_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1550BMsg
     * @param scrnAMsgAry NWAL1550_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1550BMsg scrnMsg, NWAL1550_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1550BMsg
     * @param scrnAMsgAry NWAL1550_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1550BMsg scrnMsg, NWAL1550_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Active control of the screen item.
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1550BMsg
     */
    public static void setControlFields(EZDCommonHandler handler, NWAL1550BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxOrdDplyFlg.getValue())) {

            // Order:Y DI_Info:N W/E:-
            if (!ZYPCommonFunc.hasValue(scrnMsg.diChkVrsnNum_SL)) {

                scrnMsg.diChkVrsnNum_SL.setInputProtected(true);
                handler.setButtonEnabled("ExecDIChk", true);
                handler.setButtonEnabled("RefreshDIChkRslt", false);
                handler.setButtonEnabled("SelectAll", false);
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);

                // Order:Y DI_Info:Y W/E:N
            } else if (BigDecimal.ZERO.compareTo(scrnMsg.xxNum_WH.getValue()) == 0 && BigDecimal.ZERO.compareTo(scrnMsg.xxNum_WL.getValue()) == 0 && BigDecimal.ZERO.compareTo(scrnMsg.xxNum_EH.getValue()) == 0
                    && BigDecimal.ZERO.compareTo(scrnMsg.xxNum_EL.getValue()) == 0) {

                scrnMsg.diChkVrsnNum_SL.setInputProtected(false);
                handler.setButtonEnabled("ExecDIChk", true);
                handler.setButtonEnabled("RefreshDIChkRslt", true);
                handler.setButtonEnabled("SelectAll", false);
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                }

                // Order:Y DI_Info:Y W/E:Y
            } else {

                scrnMsg.diChkVrsnNum_SL.setInputProtected(false);
                handler.setButtonEnabled("ExecDIChk", true);
                handler.setButtonEnabled("RefreshDIChkRslt", true);

                if (BigDecimal.ZERO.compareTo(scrnMsg.xxNum_WH.getValue()) == 0 && BigDecimal.ZERO.compareTo(scrnMsg.xxNum_WL.getValue()) == 0) {
                    handler.setButtonEnabled("SelectAll", false);
                    handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
                } else {
                    handler.setButtonEnabled("SelectAll", true);
                    handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
                }

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    if (DI_CHK_DTL_STS.WARNING.equals(scrnMsg.A.no(i).diChkDtlStsCd_A.getValue())) {
                        scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                    } else {
                        scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                    }
                }
            }
            // Order:N DI_Info:N W/E:-
        } else {
            scrnMsg.diChkVrsnNum_SL.setInputProtected(true);
            handler.setButtonEnabled("ExecDIChk", false);
            handler.setButtonEnabled("RefreshDIChkRslt", false);
            handler.setButtonEnabled("SelectAll", false);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
    }

    /**
     * Set Incorrect BackGround Color.
     * @param scrnMsg NWAL1550BMsg
     */
    public static void setIncorrectBackGroundColor(NWAL1550BMsg scrnMsg) {

        EZDGUITableAttribute tableAtr = new EZDGUITableAttribute(SCRN_ID_00, "A");

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (DI_CHK_DTL_STS.WARNING.equals(scrnMsg.A.no(i).diChkDtlStsCd_A.getValue())) {
                tableAtr.setCellStyle(i, COLUMN_NUM_INCORRECT_VALUE, "pWar");
                
                EZDGUIAttribute textAtr = new EZDGUIAttribute(SCRN_ID_00, NWAL1550Bean.diChkErrTxt_A + SHARP + i);
                textAtr.setStyleAttribute("background-color", BACK_GROUND_COLOR_YELLOW);
                scrnMsg.addGUIAttribute(textAtr);
                
            } else if (DI_CHK_DTL_STS.ERROR.equals(scrnMsg.A.no(i).diChkDtlStsCd_A.getValue())) {
                tableAtr.setCellStyle(i, COLUMN_NUM_INCORRECT_VALUE, "pErr");
                
                EZDGUIAttribute textAtr = new EZDGUIAttribute(SCRN_ID_00, NWAL1550Bean.diChkErrTxt_A + SHARP + i);
                textAtr.setStyleAttribute("background-color", BACK_GROUND_COLOR_RED);
                scrnMsg.addGUIAttribute(textAtr);
            }
        }
        scrnMsg.addGUIAttribute(tableAtr);
        
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg NWAL1550BMsg
     */
    public static void addCheckItemBizLayerErr(NWAL1550BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NWAM0634E, new String[] {"Accept Check Box" });
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
        }
    }

    public static void initControlFields(NWAL1550BMsg scrnMsg) {
        
        scrnMsg.lineDealNetAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.lineDealTaxAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.lineDealChrgAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.lineDealSubTotAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.cpoSvcDealNetAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.cpoSvcDealTaxAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.cpoSvcDealChrgAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.cpoSvcDealSubTotAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.rmaDealNetAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.rmaDealTaxAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.rmaDealChrgAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.rmaDealSubTotAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.ordTotDealNetAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.ordTotDealTaxAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.ordTotDealChrgAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.ordTotDealSubTotAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.invTotDealNetAmt.setAppFracDigit(DECIMAL_DIGITS_NUM_TWO);
        scrnMsg.invAmtSlsPct.setAppFracDigit(DECIMAL_DIGITS_NUM_ZERO);
    }

    // S21_NA#16035 Add Start
    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1550BMsg
     */
    public static void setProtectByAuthority(EZDCommonHandler handler, NWAL1550BMsg scrnMsg) {

        // Only Reference Authority
        if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(scrnMsg.Z.no(0).xxFuncId.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            handler.setButtonEnabled(BTN_EXEC_DI_CHK_EVENT_NM, false);
        }
    }
    // S21_NA#16035 Add End
}
