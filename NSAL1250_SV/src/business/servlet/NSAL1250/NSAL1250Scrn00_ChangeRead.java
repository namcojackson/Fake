/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1250;

import static business.servlet.NSAL1250.constant.NSAL1250Constant.BUSINESS_ID;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.FUNCTION_SEARCH;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1250.NSAL1250CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/09/14   Hitachi         N.Arai          Update          QC#10729
 *</pre>
 */
public class NSAL1250Scrn00_ChangeRead extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1250BMsg scrnMsg = (NSAL1250BMsg) bMsg;

        NSAL1250CMsg bizMsg = new NSAL1250CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1250BMsg scrnMsg = (NSAL1250BMsg) bMsg;
        NSAL1250CMsg bizMsg = (NSAL1250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        Object[] params = new Object[1];
        // START 09/14/2016 N.Arai [QC#10729, MOD]
        //params[0] = scrnMsg.svcMachMstrPk;
        params[0] = (EZDBBigDecimalItem)scrnMsg.svcContrBllgPk;
        // END 09/14/2016 N.Arai [QC#10729, MOD]
        setArgForSubScreen(params);
    }
}
