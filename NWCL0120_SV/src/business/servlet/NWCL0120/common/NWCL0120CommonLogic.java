/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0120.common;

import static business.servlet.NWCL0120.constant.NWCL0120Constant.*;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BIZ_ID;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_APL;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_APR;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_CLR;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_DEL;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_DWL;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_RJT;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_RST;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_RTN;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_SAV;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BTN_CMN_SUB;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.COLUMN_NUM_INVOICE_DATE;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.COLUMN_NUM_INVOICE_NUM;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.MONTH_GET_DAY_MAX_DAY;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.MONTH_GET_DAY_MIN_DAY;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.SCRN_ID_00;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.SHARP;

import java.util.Calendar;
import java.util.List;

import parts.servletcommon.gui.EZDGUITableAttribute;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;
import business.servlet.NWCL0120.NWCL0120BMsg;
import business.servlet.NWCL0120.NWCL0120Bean;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWCL0120CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0120CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        controlCmnBtn(handler);
    }

    /**
     * Set Search Invoice Date.
     * @param scrnMsg NWCL0120BMsg
     */
    public static void setDataClear(NWCL0120BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.clearGUIAttribute(SCRN_ID_00, NWCL0120Bean.invDt_A0 + SHARP + i);
            scrnMsg.clearGUIAttribute(SCRN_ID_00, NWCL0120Bean.invNum_A0 + SHARP + i);
            scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(false);
        }
    }

    /**
     * Set Search Invoice Date.
     * @param scrnMsg NWCL0120BMsg
     */
    public static void setErrDataBackGroundColor(NWCL0120BMsg scrnMsg) {

        EZDGUITableAttribute tableAtr = new EZDGUITableAttribute(SCRN_ID_00, "A");
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).invErrMsgTxt_A0)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A0, ZYPConstant.FLG_ON_Y);
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(true);

                tableAtr.setCellStyle(i, COLUMN_NUM_INVOICE_DATE, BACK_GROUND_COLOR_ERR);
                tableAtr.setCellStyle(i, COLUMN_NUM_INVOICE_NUM, BACK_GROUND_COLOR_ERR);
            }
            scrnMsg.A.no(i).dsAcctNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).invTotDealNetAmt_A0.setInputProtected(true);
            scrnMsg.A.no(i).invErrMsgTxt_A0.setInputProtected(true);
        }
        scrnMsg.addGUIAttribute(tableAtr);
    }

    /**
     * Set Search Invoice Date.
     * @param scrnMsg NWCL0120BMsg
     * @param glblCmpyCd String
     */
    public static void setSearchInvoiceDate(NWCL0120BMsg scrnMsg, String glblCmpyCd) {

        String slsDate = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, slsDate);

        String toDate = getStartOrEndOfMonth(slsDate, -1, MONTH_GET_DAY_MAX_DAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.invDt_TO, toDate);

        String fmDate = getStartOrEndOfMonth(slsDate, -1, MONTH_GET_DAY_MIN_DAY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.invDt_FM, fmDate);
    }

    /**
     * Get Start Or End Of Month.
     * @param date String
     * @param amount int
     * @param getDayKey String
     * @return String
     */
    public static String getStartOrEndOfMonth(String date, int amount, String getDayKey) {

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "yyyy")), Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "MM")) - 1, Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "dd")));

        cal.add(Calendar.MONTH, amount);

        if (MONTH_GET_DAY_MAX_DAY.equals(getDayKey)) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DATE));
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DATE));
        }

        String yyyy = String.valueOf(cal.get(Calendar.YEAR));
        String mm = String.format("%tm", cal);
        String dd = String.format("%td", cal);

        return yyyy + mm + dd;
    }

    /**
     * Control Common Button
     * @param handler EZDCommonHandler
     */
    private static void controlCmnBtn(S21CommonHandler handler) {

        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
    }

    /**
     * Has Regist Function Id
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Reprocess Print Request(" + BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * Set App Frac Digit
     * </pre>
     * @param scrnMsg NWCL0120BMsg
     */
    public static void setAppFracDigit(NWCL0120BMsg scrnMsg, String glblCmpyCd) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).invTotDealNetAmt_A0.setAppFracDigit(getFuncCcyScale(glblCmpyCd));
        }
    }

    /**
     * <pre>
     * Get FuncCcyScale
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private static int getFuncCcyScale(String glblCmpyCd) {

        int funcCcyScale = 0;

        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(glblCmpyCd);

        glblTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg == null) {
            return funcCcyScale;
        }

        String funcCcyCd = glblTMsg.stdCcyCd.getValue();

        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyTMsg.ccyCd.setValue(funcCcyCd);

        ccyTMsg = (CCYTMsg) S21CodeTableAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null || ccyTMsg.aftDeclPntDigitNum.getValue() == null) {
            return funcCcyScale;
        }

        funcCcyScale = ccyTMsg.aftDeclPntDigitNum.getValue().intValue();

        return funcCcyScale;
    }
}
