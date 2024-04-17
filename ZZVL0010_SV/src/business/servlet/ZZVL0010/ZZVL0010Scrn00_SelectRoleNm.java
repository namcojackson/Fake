/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0010;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0010Scrn00_SelectRoleNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;

        //ZZVL0010CMsg bizMsg = new ZZVL0010CMsg();
        //bizMsg.setBusinessID("ZZVL0010");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;
        //ZZVL0010CMsg bizMsg  = (ZZVL0010CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;

        //Get selected result index
        int selected =  getButtonSelectNumber();

        //Populating return values 
        Serializable arg = getArgForSubScreen();

        if (arg != null) {
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;
                EZDBStringItem templVal = (EZDBStringItem) params[1];
                templVal.setValue(scrnMsg.A.no(selected).roleNm_2.getValue());
            }
        }

    }
}
