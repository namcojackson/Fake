/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_ServiceCallEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Delete later
        try {
            Class.forName("business.servlet.NSBL0020.NSBL0020_INIT");
        } catch (ClassNotFoundException e) {
            NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
            scrnMsg.setMessageInfo("NFAM0043E", new String[] {"[No setup. Please deploy NSBL0020.]" });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, getGlobalCompanyCode());

        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[2];
        params[0] = scrnMsg.xxPopPrm_P1;
        params[1] = scrnMsg.A.no(selectIdx).svcTaskNum_A;

        setArgForSubScreen(params);
    }
}
