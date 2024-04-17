/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */

package business.servlet.ZZSL1110.common;

import business.servlet.ZZSL1110.ZZSL1110BMsg;
import business.servlet.ZZSL1110.constant.ZZSL1110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * The Class ZZSL1110Common.
 * @author $Author: Satheesh Sangaraju $
 * @version $Revision: 1.2 $ $Date: 2009/07/09 22:16:11 $
 */
public class ZZSL1110Common implements ZZSL1110Constant {

	
	/**
     * Sets the common buttons.
     * @param handler the new common buttons
     */
	public static final void setCommonButtons(S21CommonHandler handler) {
		handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1],
				BTN_CMN_CLEAR[2], 1, null);
		handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1],
				BTN_CMN_CLOSE[2], 1, null);
	}
	
    /**
     * Control buttons display.
     * @param objS21CommonHandler the obj s21 common handler
     * @param scrnMsg the scrn msg
     */
    public static void ControlButtonsDisplay(S21CommonHandler objS21CommonHandler, ZZSL1110BMsg scrnMsg) {

        if (scrnMsg.xxCurPg.getValueInt() == 0) {
            objS21CommonHandler.setButtonEnabled(BTN_PREV[1], false);
        } else {
            objS21CommonHandler.setButtonEnabled(BTN_PREV[1], true);
        }
        // to disable next button
        if (scrnMsg.A.getValidCount() < 200) {
            objS21CommonHandler.setButtonEnabled(BTN_NEXT[1], false);
        } else {
            objS21CommonHandler.setButtonEnabled(BTN_NEXT[1], true);
        }
    }
    
}
