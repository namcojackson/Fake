/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NMAL6850.NMAL6850CMsg;
// import business.servlet.NMAL6850.constant.NMAL6850Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6850 Supplier Search
 * Function Name : The business process for Open Window Supplier.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/26   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NMAL6850Scrn00_OpenWin_Supplier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;

        // parameter for sub screen.
        Object[] params = new Object[3];
        params[0] = scrnMsg.A.no(getButtonSelectNumber()).prntVndPk_A;
        params[1] = scrnMsg.A.no(getButtonSelectNumber()).dsAcctNum_A;
        params[2] = scrnMsg.A.no(getButtonSelectNumber()).billToCustCd_A;

        super.setArgForSubScreen(params);
    }
}
