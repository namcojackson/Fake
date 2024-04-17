/**
 * Copyright(c)2012 Canon USA Inc. All rights reserved.
 */
package business.servlet.NMAL2630.common;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2630.NMAL2630BMsg;
import business.servlet.NMAL2630.constant.NMAL2630Constant;


/**
 *<pre>
 * Resource Search NMAL2630CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2016/05/31   SRAA            Y.Chen          Update          QC#9182
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */

public class NMAL2630CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2630BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2630BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);

    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {

        handler.setButtonEnabled(NMAL2630Constant.BTN_SEARCH[0], true);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2630Constant.BTN_CMN_CLEAR[0], NMAL2630Constant.BTN_CMN_CLEAR[1], NMAL2630Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2630Constant.BTN_CMN_CLOSE[0], NMAL2630Constant.BTN_CMN_CLOSE[1], NMAL2630Constant.BTN_CMN_CLOSE[2], 1, null);

    }

    /**
     * Check input
     * @param scrnMsg DSBL2630BMsg
     */
    public static void checkInput(NMAL2630BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        // QC#9182
        scrnMsg.addCheckItem(scrnMsg.orgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_FR); // MOD S21_NA QC#16481
        scrnMsg.addCheckItem(scrnMsg.effFromDt_TO); // MOD S21_NA QC#16481
    }
    
    // ADD START S21_NA QC#16481
    /**
     * Check Consistency of Date Column
     * @param scrnMsg NMAL2630BMsg
     */
    public static void checkDate(NMAL2630BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.effFromDt_TO) && ZYPDateUtil.compare(scrnMsg.effFromDt_FR.getValue(), scrnMsg.effFromDt_TO.getValue()) > 0) {
            scrnMsg.effFromDt_FR.setErrorInfo(1, NMAL2630Constant.NMAM0185E);
            scrnMsg.effFromDt_TO.setErrorInfo(1, NMAL2630Constant.NMAM0185E);
        }
        
    }
    // ADD END S21_NA QC#16481
}
