/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1910.common;

import static business.servlet.NWAL1910.constant.NWAL1910Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1910.constant.NWAL1910Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1910.constant.NWAL1910Constant.DEFAULT_LINE_NUM;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import business.servlet.NWAL1910.NWAL1910BMsg;
import business.servlet.NWAL1910.NWAL1910_ABMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/14   Fujitsu         M.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1910CommonLogic {

    /**
     * pageItemClear
     * @param scrnMsg NWAL1910BMsg
     */
    public static void pageItemClear(NWAL1910BMsg scrnMsg) {
        // clear parameter displayed on screen
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * initCmnBtnSetting
     * @param handler S21CommonHandler
     */
    public static void initCmnBtnSetting(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * initSetProtection
     * @param scrnMsg NWAL1910BMsg
     */
    public static void initSetProtection(NWAL1910BMsg scrnMsg) {
        scrnMsg.ordSrcRefNum.setInputProtected(true);
        scrnMsg.dsOrdPosnNum.setInputProtected(true);
        scrnMsg.xxLineNum.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL1910_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxDtTm_A.setInputProtected(true);
            abMsg.xxSrUsrId_A.setInputProtected(true);
            abMsg.xxLineNum_A.setInputProtected(true);
            abMsg.mdseCd_A.setInputProtected(true);
            abMsg.mdseDescShortTxt_A.setInputProtected(true);
            abMsg.prcDtlGrpDescTxt_A.setInputProtected(true);
            abMsg.unitPrcAmt_A.setInputProtected(true);
        }
    }

    /**
     * setDispLineNum
     * @param scrnMsg NWAL1910BMsg
     */
    public static void setDispLineNum(NWAL1910BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, (String) DEFAULT_LINE_NUM);
        }
        StringBuilder dispLineNum = new StringBuilder();
        dispLineNum.append(scrnMsg.dsOrdPosnNum.getValue());
        dispLineNum.append(".");
        dispLineNum.append(scrnMsg.dsCpoLineNum.getValue());
        scrnMsg.xxLineNum.setValue(dispLineNum.toString());
    }

    /**
     * setAppFracDigit
     * @param scrnMsg NWAL1910BMsg
     */
    public static void setAppFracDigit(NWAL1910BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).unitPrcAmt_A.setAppFracDigit(2);
        }
    }

}
