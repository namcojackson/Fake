/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6170.common;

import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import business.servlet.NMAL6170.NMAL6170BMsg;
import business.servlet.NMAL6170.constant.NMAL6170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Relationships Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NMAL6170CommonLogic extends NMAL6170Constant {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * setScrnCtrl
     * @param scrnMsg NMAL6170BMsg
     */
    public static void setScrnCtrl(NMAL6170BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.dsAcctRelnTpCd_H);

    }

    /**
     * setInputParam
     * @param scrnMsg NMAL6170BMsg
     * @param arg Object[]
     */
    public static void setInputParam(NMAL6170BMsg scrnMsg, Object[] arg) {

        scrnMsg.clear();
        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < LENGTH) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctRelnTpCd_H, getString(arg[INPUT_DS_ACCT_RELN_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_F, getString(arg[INPUT_DS_ACCT_NUM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_F, getString(arg[INPUT_DS_ACCT_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_FB, getString(arg[INPUT_XX_CHK_BOX_CB]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_FS, getString(arg[INPUT_XX_CHK_BOX_CS]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_FR, getString(arg[INPUT_XX_CHK_BOX_CR]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_F1, getDtString(arg[INPUT_EFF_FROM_DT_F]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_F2, getDtString(arg[INPUT_EFF_FROM_DT_T]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_F1, getDtString(arg[INPUT_EFF_THRU_DT_F]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_F2, getDtString(arg[INPUT_EFF_THRU_DT_T]));

    }

    /**
     * setOutputParam
     * @param scrnMsg NMAL6170BMsg
     * @param arg Object[]
     */
    public static void setOutputParam(NMAL6170BMsg scrnMsg, Object[] arg) {

        setValue(arg[INPUT_DS_ACCT_RELN_TP_CD], scrnMsg.dsAcctRelnTpCd_H.getValue());
        setValue(arg[INPUT_DS_ACCT_NUM], scrnMsg.dsAcctNum_F.getValue());
        setValue(arg[INPUT_DS_ACCT_NM], scrnMsg.dsAcctNm_F.getValue());
        setValue(arg[INPUT_XX_CHK_BOX_CB], scrnMsg.xxChkBox_FB.getValue());
        setValue(arg[INPUT_XX_CHK_BOX_CS], scrnMsg.xxChkBox_FS.getValue());
        setValue(arg[INPUT_XX_CHK_BOX_CR], scrnMsg.xxChkBox_FR.getValue());
        setDtValue(arg[INPUT_EFF_FROM_DT_F], scrnMsg.effFromDt_F1);
        setDtValue(arg[INPUT_EFF_FROM_DT_T], scrnMsg.effFromDt_F2);
        setDtValue(arg[INPUT_EFF_THRU_DT_F], scrnMsg.effThruDt_F1);
        setDtValue(arg[INPUT_EFF_THRU_DT_T], scrnMsg.effThruDt_F2);

    }

    /**
     * clearScrn
     * @param scrnMsg NMAL6170BMsg
     */
    public static void clearScrn(NMAL6170BMsg scrnMsg) {

        scrnMsg.dsAcctRelnTpCd_H.clear();
        scrnMsg.dsAcctNum_F.clear();
        scrnMsg.dsAcctNm_F.clear();
        scrnMsg.xxChkBox_FB.clear();
        scrnMsg.xxChkBox_FS.clear();
        scrnMsg.xxChkBox_FR.clear();
        scrnMsg.effFromDt_F1.clear();
        scrnMsg.effFromDt_F2.clear();
        scrnMsg.effThruDt_F1.clear();
        scrnMsg.effThruDt_F2.clear();
    }

    /**
     * addCheckItem
     * @param scrnMsg NMAL6170BMsg
     */
    public static void addCheckItem(NMAL6170BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dsAcctRelnPk_F);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_F);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_F);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_FB);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_FS);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_FR);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_F1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_F2);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_F1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_F2);
    }

    private static String getString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBStringItem) obj).getValue();
    }

    private static String getDtString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBDateItem) obj).getValue();
    }

    private static void setValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) obj, val);
    }

    private static void setDtValue(Object obj, EZDBDateItem val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBDateItem) obj, val);
    }
}
