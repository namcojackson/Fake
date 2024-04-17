/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */

package business.servlet.ZZSL1110;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZSL1110.constant.ZZSL1110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * The Class ZZSL1110Scrn00_Select.
 * @author $Author: Satheesh Sangaraju $
 * @version $Revision: 1.3 $ $Date: 2009/07/09 22:16:11 $
 */
public class ZZSL1110Scrn00_Select extends S21CommonHandler implements ZZSL1110Constant {

    /**
     * Check input.
     * @param ctx the ctx
     * @param bMsg the b msg
     * @see com.canon.cusa.s21.framework.online.servlet.S21CommonHandler#checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZSL1110BMsg scrnMsg = (ZZSL1110BMsg) bMsg;

    }

    /**
     * Sets the request data.
     * @param ctx the ctx
     * @param bMsg the b msg
     * @return the EZDC msg
     * @see com.canon.cusa.s21.framework.online.servlet.S21CommonHandler#setRequestData(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZSL1110BMsg scrnMsg = (ZZSL1110BMsg) bMsg;

        // ZZSL1110CMsg bizMsg = new ZZSL1110CMsg();
        // bizMsg.setBusinessID("ZZSL1110");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    /**
     * Do process.
     * @param ctx the ctx
     * @param bMsg the b msg
     * @param cMsg the c msg
     * @see com.canon.cusa.s21.framework.online.servlet.S21CommonHandler#doProcess(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg, parts.common.EZDCMsg)
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         ZZSL1110BMsg scrnMsg = (ZZSL1110BMsg) bMsg;
        // ZZSL1110CMsg bizMsg = (ZZSL1110CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
//       Get selected result index
        int selected =  getButtonSelectNumber();

        //Populating return values 
        Serializable arg = getArgForSubScreen();
        
        if (arg != null) {
            if (arg instanceof Object[]) {
                if (arg instanceof Object[]) {
                    Object[] params = (Object[]) arg;
                    EZDBStringItem templVal = (EZDBStringItem) params[1];
                    templVal.setValue(scrnMsg.A.no(selected).glblCmpyCd_A.getValue());
                }
            }
        }
    }

}