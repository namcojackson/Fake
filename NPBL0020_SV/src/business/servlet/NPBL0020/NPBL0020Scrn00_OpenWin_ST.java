/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPBL0020.NPBL0020CMsg;
//import business.servlet.NPBL0020.constant.NPBL0020Constant;

import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_ST extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        //NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        //bizMsg.setBusinessID("NPBL0020");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        //NPBL0020CMsg bizMsg  = (NPBL0020CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
    	NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, "OpenWin_ST");
        scrnMsg.xxScrEventNm.setValue("OpenWin_ST");
        Object[] params = NPBL0020CommonLogic.getAddressPopupParam(scrnMsg, getGlobalCompanyCode());
        setArgForSubScreen(params);

    }
}
