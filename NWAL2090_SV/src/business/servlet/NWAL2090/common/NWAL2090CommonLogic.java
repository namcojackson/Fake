/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2090.common;

import static business.servlet.NWAL2090.constant.NWAL2090Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2090.constant.NWAL2090Constant.BTN_CMN_CLS;
import business.servlet.NWAL2090.NWAL2090BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2090CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2090CommonLogic {

    /**
     * Set Backup.
     * @param scrnMsg NWAL2090BMsg
     */
    public static void setBackup(NWAL2090BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, scrnMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.chngRsnTpCd_BK, scrnMsg.chngRsnTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_BK, scrnMsg.xxComnScrColValTxt);
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2000BMsg
     */
    public static void protectCmnBtnProp(S21CommonHandler handler, NWAL2090BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2090BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL2090BMsg scrnMsg) {
        scrnMsg.cpoOrdNum.setInputProtected(true);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

}
