/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7050.NMAL7050CMsg;
//import business.servlet.NMAL7050.constant.NMAL7050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050Scrn00_LINK_SVC_MDL_MSTR extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;

        int index = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(index).mtrLbDescTxt_A1.getValue().toString());
        scrnMsg.P.setValidCount(1);

        Object[] params = new Object[1];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
