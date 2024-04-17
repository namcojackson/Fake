/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1020.common;

import static business.servlet.NSAL1020.constant.NSAL1020Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;

import business.blap.NSAL1020.NSAL1020CMsg;
import business.servlet.NSAL1020.NSAL1020BMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2018/07/12   Hitachi         K.Kim           Update          QC#27009
 *</pre>
 */
public class NSAL1020CommonLogic {

    /**
     * initialize
     * @param handler EZDCommonHandler
     */
    public static void initialize(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * screenItemControl
     * @param scrnMsg NSAL1020BMsg
     */
    public static void screenItemControl(S21CommonHandler handler, NSAL1020BMsg scrnMsg, NSAL1020CMsg bizMsg) {

        setButtonEnabled(handler, bizMsg);
        setTableBGColor(scrnMsg);
    }

    private static void setButtonEnabled(S21CommonHandler handler, NSAL1020CMsg bizMsg) {

        if ("E".equals(bizMsg.getMessageKind())) {
            handler.setButtonEnabled(BTN_SEARCH, false);
        }
    }

    private static void setTableBGColor(NSAL1020BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1020BMsg
     */
    public static void commonAddCheckItem(NSAL1020BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.serNum_H);
    }

    /**
     * set input parameter
     * @param scrnMsg NSAL1020BMsg
     * @param arg Serializable
     */
    public static void setInputParam(NSAL1020BMsg scrnMsg, Serializable arg) {

        scrnMsg.ownrAcctNum_PH.clear();
        scrnMsg.shipToCustCd_PH.clear();
        scrnMsg.lineBizTpCd_PH.clear();
        // START 2018/07/12 K.Kim [QC#27009, ADD]
        scrnMsg.billToAcctNum_PH.clear();
        // END 2018/07/12 K.Kim [QC#27009, ADD]
        ZYPTableUtil.clear(scrnMsg.P);

        if (!(arg instanceof Object[])) {
            return;
        }

        Object[] params = (Object[]) arg;
        if (params.length < PARAM_LENGTH_NSAL1020) {
            return;
        }

        int idx = 0;
        if (params[idx] instanceof EZDBStringItem) {
            setValue(scrnMsg.ownrAcctNum_PH, (EZDBStringItem) params[idx]);
        }
        idx++;
        if (params[idx] instanceof EZDBStringItem) {
            setValue(scrnMsg.shipToCustCd_PH, (EZDBStringItem) params[idx]);
        }
        idx++;
        if (params[idx] instanceof EZDBStringItem) {
            setValue(scrnMsg.lineBizTpCd_PH, (EZDBStringItem) params[idx]);
        }
        idx++;
        // START 2018/07/12 K.Kim [QC#27009, ADD]
        if (params[idx] instanceof EZDBStringItem) {
            setValue(scrnMsg.billToAcctNum_PH, (EZDBStringItem) params[idx]);
        }
        idx++;
        // END 2018/07/12 K.Kim [QC#27009, ADD]
        if (params[idx] instanceof EZDBMsgArray) {
            EZDBMsgArray params4 = (EZDBMsgArray) params[idx];
            EZDMsg.copy(params4, null, scrnMsg.P, null);
        }
    }

    /**
     * set output parameter
     * @param scrnMsg NSAL1020BMsg
     * @param arg Object[]
     * @param rowNum int
     * @return Object[]
     */
    public static Object[] setOutputParam(NSAL1020BMsg scrnMsg, Serializable arg, int rowNum) {

        Object[] params = (Object[]) arg;
        if (params.length < PARAM_LENGTH_NSAL1020) {
            return params;
        }

        int idx = OUT_PARAM_START_INDEX;
        if (params[idx] instanceof EZDBBigDecimalItem) {
            setValue((EZDBBigDecimalItem) params[idx], scrnMsg.A.no(rowNum).svcMachMstrPk_D);
        }
        idx++;
        if (params[idx] instanceof EZDBBigDecimalItem) {
            setValue((EZDBBigDecimalItem) params[idx], scrnMsg.A.no(rowNum).dsContrDtlPk_D);
        }

        return params;
    }
}
