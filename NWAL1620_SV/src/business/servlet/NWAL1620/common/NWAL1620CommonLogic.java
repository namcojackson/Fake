/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1620.common;

import static business.servlet.NWAL1620.constant.NWAL1620Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.CONFIG_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.COPY_FROM_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.COPY_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.HEADER_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.LINE_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NLBM1269E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NMZM0009E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWAM0664E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWZM0012E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWZM1205E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWZM1374E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWZM1409E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWZM1411E;
import business.servlet.NWAL1620.NWAL1620BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1620CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2017/09/20   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1620CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1620BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL1620BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1620BMsg
     */
    public static void protectCmnBtnProp(S21CommonHandler handler, NWAL1620BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
    }

    /**
     * Set Parameter Back up.
     * @param scrnMsg NWAL1620BMsg
     */
    public static void setBackup(NWAL1620BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, scrnMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_BK, scrnMsg.xxChkBox);
        ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd_BK, scrnMsg.configCatgCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_BK, scrnMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum_BK, scrnMsg.dsCpoLineNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxQty10Num_BK, scrnMsg.xxQty10Num);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_BR, scrnMsg.xxChkBox_R); // Add 2017/09/20 S21_NA#18859

    }

    /**
     * setInputProtect
     * @param scrnMsg NWAL1620BMsg
     */
    public static void setInputProtect(NWAL1620BMsg scrnMsg) {

        // Copy Mode
        if (COPY_MODE.equals(scrnMsg.xxModeCd_CP.getValue())) {
            setAllInputProtect(scrnMsg);
            return;
        }

        // Copy From : Header Mode
        if (HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.cpoOrdNum.setInputProtected(true);
            return;
        }

        // Copy From : Config and Line Mode
        scrnMsg.cpoOrdNum.setInputProtected(false);
        scrnMsg.configCatgCd.setInputProtected(true);
        scrnMsg.dsOrdPosnNum.setInputProtected(false);
        scrnMsg.xxQty10Num.setInputProtected(false);
        if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.xxLineNum_DI.setInputProtected(false);
        }
    }

    /**
     * setAllInputProtect
     * @param scrnMsg NWAL1620BMsg
     */
    private static void setAllInputProtect(NWAL1620BMsg scrnMsg) {

        // All Mode
        scrnMsg.cpoOrdNum.setInputProtected(true);
        if (HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            return;
        }

        // Config and Line Mode
        scrnMsg.configCatgCd.setInputProtected(true);
        scrnMsg.dsOrdPosnNum.setInputProtected(true);

        // Line Mode
        if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.xxLineNum_DI.setInputProtected(true);
        }
    }

    /**
     * addCheckInput
     * @param scrnMsg NWAL1620BMsg
     */
    public static void addCheckInput(NWAL1620BMsg scrnMsg) {

        if (!HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.xxQty10Num);
            if (COPY_FROM_MODE.equals(scrnMsg.xxModeCd_CP.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
                scrnMsg.addCheckItem(scrnMsg.configCatgCd);
                scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum);
                if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.xxLineNum_DI);
                }
            }
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * checkInputParam
     * @param scrnMsg NWAL1620BMsg
     * @return boolean
     */
    public static boolean checkInputParam(NWAL1620BMsg scrnMsg) {

        String copyMode = scrnMsg.xxModeCd_CP.getValue();
        String dtlMode = scrnMsg.xxModeCd.getValue();

        if (!ZYPCommonFunc.hasValue(copyMode) || !ZYPCommonFunc.hasValue(dtlMode)) {
            scrnMsg.setMessageInfo(NWZM0012E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.glblCmpyCd)) {
            scrnMsg.setMessageInfo(NMZM0009E);
            return false;
        }

        if (COPY_MODE.equals(copyMode) && HEADER_MODE.equals(dtlMode)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.setMessageInfo(NWZM1205E);
                return false;
            }
        }

        if (CONFIG_MODE.equals(dtlMode) || (COPY_MODE.equals(copyMode) && LINE_MODE.equals(dtlMode))) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd)) {
                scrnMsg.setMessageInfo(NWZM1409E);
                return false;
            }
        }

        if (COPY_MODE.equals(copyMode)) {
            if (CONFIG_MODE.equals(dtlMode) && LINE_MODE.equals(dtlMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)) {
                    scrnMsg.setMessageInfo(NWZM1374E);
                    return false;
                }
            }

            if (LINE_MODE.equals(dtlMode)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum)) {
                    scrnMsg.setMessageInfo(NWZM1411E);
                    return false;
                }
            }
        }

        return true;
    }

    //Add Start NA QC#2177
    /**
     * Set Display Line Number
     * 
     * @param scrnMsg NWAL1620BMsg
     */
    public static void setDispLineNum(NWAL1620BMsg scrnMsg) {

        if (!LINE_MODE.equals(scrnMsg.xxModeCd.getValue())
                || !ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum)) {
            return;
        }

        StringBuilder dispLineNum = new StringBuilder();
        dispLineNum.append(scrnMsg.dsOrdPosnNum.getValue());
        dispLineNum.append(".");
        dispLineNum.append(scrnMsg.dsCpoLineNum.getValue());
        scrnMsg.xxLineNum_DI.setValue(dispLineNum.toString());
    }

    /**
     * Parse Line Number
     * 
     * @param scrnMsg NWAL1620BMsg
     */
    public static void parseLineNum(NWAL1620BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxLineNum_DI)) {
            return;
        }

        String dispLineNum = scrnMsg.xxLineNum_DI.getValue();
        if (!dispLineNum.matches("\\d+\\.\\d+|\\d+")) {
            scrnMsg.xxLineNum_DI.setErrorInfo(1, NWAM0664E, new String[]{"\"0\" or \"0.0\""});
            scrnMsg.addCheckItem(scrnMsg.xxLineNum_DI);
            scrnMsg.putErrorScreen();
        }

        if (dispLineNum.indexOf(".") <= 0) {
            scrnMsg.dsCpoLineNum.setValue(Integer.parseInt(dispLineNum));
            return;
        }

        String[] dispLineNumAry = dispLineNum.split("\\.");

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)) {
            scrnMsg.dsOrdPosnNum.setValue(dispLineNumAry[0]);

        } else {

            if (!scrnMsg.dsOrdPosnNum.getValue().equals(dispLineNumAry[0])) {
                scrnMsg.xxLineNum_DI.setErrorInfo(1, NLBM1269E, new String[]{
                        scrnMsg.xxLineNum_DI.getNameForMessage(),
                        scrnMsg.dsOrdPosnNum.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.xxLineNum_DI);
                scrnMsg.putErrorScreen();
            }
        }

        scrnMsg.dsCpoLineNum.setValue(Integer.parseInt(dispLineNumAry[1]));

    }
    //Add End   NA QC#2177
}
