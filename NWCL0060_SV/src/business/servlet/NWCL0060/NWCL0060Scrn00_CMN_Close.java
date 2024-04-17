/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0060;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NWCL0060Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;

        if (!scrnMsg.xxMlCmntTxt_H1.isInputProtected()) {
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                scrnMsg.setMessageInfo("NWCM0095I");
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//CSA ADD Start
        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;
//        NWCL0060CMsg bizMsg = (NWCL0060CMsg) cMsg;

//        if (!FLG_ON_Y.equals(scrnMsg.xxScrDply.getValue())) { // ReadOnlyFlag

//            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            scrnMsg.addCheckItem(scrnMsg.scrInvEmlTs);
            scrnMsg.addCheckItem(scrnMsg.scrInvEmlAddr);
            scrnMsg.addCheckItem(scrnMsg.scrInvEmlSubjTxt);
            scrnMsg.addCheckItem(scrnMsg.scrInvEmlCmntTxt);
            scrnMsg.putErrorScreen();

//            Serializable arg = (Object[]) getArgForSubScreen();
            Object[] arg = (Object[]) getArgForSubScreen();

            if (arg instanceof Object[]) {

                Object[] params = (Object[]) arg;
//                EZDBStringItem param0 = (EZDBStringItem) params[0];
//                EZDBStringItem param1 = (EZDBStringItem) params[1];
                EZDBStringItem param2 = (EZDBStringItem) params[2];
                EZDBStringItem param3 = (EZDBStringItem) params[3];
                EZDBStringItem param4 = (EZDBStringItem) params[4];
                EZDBStringItem param5 = (EZDBStringItem) params[5];
                param2.setValue(scrnMsg.scrInvEmlTs.getValue());
                param3.setValue(scrnMsg.scrInvEmlAddr.getValue());
                param4.setValue(scrnMsg.scrInvEmlSubjTxt.getValue());
                param5.setValue(scrnMsg.scrInvEmlCmntTxt.getValue());
            }
//        }
//CSA ADD Start

    }
}
