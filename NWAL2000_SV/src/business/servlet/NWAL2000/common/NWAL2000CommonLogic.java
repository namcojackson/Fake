/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2000.common;

import static business.servlet.NWAL2000.constant.NWAL2000Constant.BTN_CMN_CANCEL;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.BTN_CMN_CLS;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.BTN_CMN_OK;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.CONFIG_MODE;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.HEADER_MODE;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.LINE_MODE;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.NMZM0009E;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.NWAM0042E;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.NWZM0012E;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.NWZM0013E;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.NWZM1518E;

import java.math.BigDecimal;

import business.servlet.NWAL2000.NWAL2000BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2000CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2016/02/25   Fujitsu         S.Ohki          Update          QC#2111
 * 2016/04/19   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2018/06/15   Fujitsu         M.Yamada        Update          QC#25227
 *</pre>
 */
public class NWAL2000CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2000BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL2000BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
        setInputProtect(scrnMsg);
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2000BMsg
     */
    public static void protectCmnBtnProp(S21CommonHandler handler, NWAL2000BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
        handler.setButtonEnabled(BTN_CMN_OK, false);
        handler.setButtonEnabled(BTN_CMN_CANCEL, false);
    }

    /**
     * setInputProtect
     * @param scrnMsg NWAL2000BMsg
     */
    public static void setInputProtect(NWAL2000BMsg scrnMsg) {
        scrnMsg.cpoOrdNum.setInputProtected(true);
        scrnMsg.configCatgCd.setInputProtected(true);
        if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.xxLineNum_DI.setInputProtected(true);
            scrnMsg.xxPopPrm.setInputProtected(true);
        } else if (CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
//            scrnMsg.dsOrdPosnNum.setInputProtected(true);
        }
        scrnMsg.xxPopPrm_DI.setInputProtected(true);
    }

    /**
     * checkInputParam
     * @param scrnMsg NWAL2000BMsg
     * @return boolean
     */
    public static boolean checkInputParam(NWAL2000BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
            scrnMsg.setMessageInfo(NWZM0012E);
            return false;
        }
        if (!CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())
                && !LINE_MODE.equals(scrnMsg.xxModeCd.getValue())
                && !HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.setMessageInfo(NWZM0013E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.glblCmpyCd)) {
            scrnMsg.setMessageInfo(NMZM0009E);
            return false;
        }
        if (!HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) { // 2016/02/25 S21_NA#2111 Add
            if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd)) {
                scrnMsg.setMessageInfo(NWZM1518E);
                return false;
            }
            // QC#25227
//            if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)) {
//                scrnMsg.setMessageInfo(NWZM1408E);
//                return false;
//            }
        }
        // QC#25227 del
//        if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum)) {
//                scrnMsg.setMessageInfo(NWAM0115E);
//                return false;
//            }
//            if (!ZYPCommonFunc.hasValue(scrnMsg.ordQty)) {
//                scrnMsg.setMessageInfo(NWZM0046E);
//                return false;
//            }
//        }
        return true;
    }

    /**
     * checkQuantity
     * @param scrnMsg NWAL2000BMsg
     * @return boolean
     */
    public static boolean checkQuantity(NWAL2000BMsg scrnMsg) {
        // 2016/04/19 S21_NA#5394 Mod Start
//        if (CONFIG_CATG.OUTBOUND.equals(scrnMsg.configCatgCd.getValue())) {
//            if (scrnMsg.ordQty.getValue().compareTo(scrnMsg.cancQty.getValue()) < 0) {
//                scrnMsg.cancQty.setErrorInfo(1, NWAM0698E);
//                return false;
//            } else if (scrnMsg.cancQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
//                scrnMsg.cancQty.setErrorInfo(1, NWAM0042E);
//                return false;
//            }
//        } else {
//            if (scrnMsg.ordQty.getValue().compareTo(scrnMsg.cancQty.getValue()) > 0) {
//                scrnMsg.cancQty.setErrorInfo(1, NWAM0698E);
//                return false;
//            } else if (scrnMsg.cancQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
//                scrnMsg.cancQty.setErrorInfo(1, NWAM0042E);
//                return false;
//            }
//        }
        // QC#25227 upd
//        BigDecimal ordQty = scrnMsg.ordQty.getValue().abs();
//        BigDecimal cancQty = scrnMsg.cancQty.getValue().abs();
//        if (ordQty.compareTo(cancQty) < 0) {
//            scrnMsg.cancQty.setErrorInfo(1, NWAM0698E);
//            return false;
//        } else if (scrnMsg.cancQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
//            scrnMsg.cancQty.setErrorInfo(1, NWAM0042E);
//            return false;
//        }
//        // 2016/04/19 S21_NA#5394 Mod End
        if (scrnMsg.cancQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            scrnMsg.cancQty.setErrorInfo(1, NWAM0042E);
            return false;
        }
        // QC#25227 upd
        return true;
    }

    // QC#25227 del
//    //Add Start NA QC#2177
//    /**
//     * Set Display Line Number
//     * 
//     * @param scrnMsg NWAL2000BMsg
//     */
//    public static void setDispLineNum(NWAL2000BMsg scrnMsg) {
//
//        if (!LINE_MODE.equals(scrnMsg.xxModeCd.getValue())
//                || !ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum)) {
//            return;
//        }
//
//        StringBuilder dispLineNum = new StringBuilder();
//        dispLineNum.append(scrnMsg.dsOrdPosnNum.getValue());
//        dispLineNum.append(".");
//        dispLineNum.append(scrnMsg.dsCpoLineNum.getValue());
//        scrnMsg.xxLineNum_DI.setValue(dispLineNum.toString());
//    }
//    //Add End   NA QC#2177
}
