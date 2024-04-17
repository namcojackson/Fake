/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6040.common.NMAL6040CommonLogic;
import business.servlet.NMAL6040.constant.NMAL6040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 *</pre>
 */
public class NMAL6040Scrn00_Select_ProductLineGroupCD extends S21CommonHandler implements NMAL6040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6040BMsg scrnMsg = (NMAL6040BMsg) bMsg;
        int index = getButtonSelectNumber();
        Object[] arg = (Object[]) getArgForSubScreen();
        NMAL6040CommonLogic.returnSelectData(scrnMsg, index, SELECT_PROD_LINE_GRP, arg);

    }

}
