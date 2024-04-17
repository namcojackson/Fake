/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2530.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2530.NMAL2530BMsg;
import business.servlet.NMAL2530.constant.NMAL2530Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public class NMAL2530CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2530BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2530BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        controlScreenFields(handler, scrnMsg);

    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {

        handler.setButtonEnabled(NMAL2530Constant.BTN_SEARCH[0], true);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2530Constant.BTN_CMN_CLEAR[0], NMAL2530Constant.BTN_CMN_CLEAR[1], NMAL2530Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2530Constant.BTN_CMN_CLOSE[0], NMAL2530Constant.BTN_CMN_CLOSE[1], NMAL2530Constant.BTN_CMN_CLOSE[2], 1, null);

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2530BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2530BMsg scrnMsg) {
//        setIniOrgStatDate(scrnMsg);

        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2530BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2530BMsg scrnMsg) {

    }
    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2530BMsg
     */
    private static void setIniOrgStatDate(NMAL2530BMsg scrnMsg) {

        // Get Sales Date
        final String slsDt = ZYPDateUtil.getSalesDate();

        // Set Start Date
        scrnMsg.effFromDt_FR.setValue(slsDt);
    }
    
    // ADD START S21_NA QC#16481
    /**
     * Check Consistency of Date Column
     * @param scrnMsg NMAL2530BMsg
     */
    public static void checkDate(NMAL2530BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.effFromDt_TO) && ZYPDateUtil.compare(scrnMsg.effFromDt_FR.getValue(), scrnMsg.effFromDt_TO.getValue()) > 0) {
            scrnMsg.effFromDt_FR.setErrorInfo(1, NMAL2530Constant.NMAM0185E);
            scrnMsg.effFromDt_TO.setErrorInfo(1, NMAL2530Constant.NMAM0185E);
        }
        
    }
    // ADD END S21_NA QC#16481
}
