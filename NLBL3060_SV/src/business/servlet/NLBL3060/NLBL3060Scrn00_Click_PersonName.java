/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL3060.NLBL3060CMsg;
//import business.servlet.NLBL3060.constant.NLBL3060Constant;

import business.blap.NLBL3060.NLBL3060CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 *</pre>
 */
public class NLBL3060Scrn00_Click_PersonName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        if (!ZYPCommonFunc.hasValue(scrnMsg.schdCoordPsnCd)) {
            scrnMsg.schdCoordPsnCd.setErrorInfo(1, "NLAM0173E", new String[]{"Person Code"});
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID("NLBL3060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg  = (NLBL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        scrnMsg.putErrorScreen();
    }
}
