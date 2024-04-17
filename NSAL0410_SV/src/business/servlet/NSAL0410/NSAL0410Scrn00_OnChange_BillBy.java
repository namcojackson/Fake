/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0410.common.NSAL0410CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 *</pre>
 */
public class NSAL0410Scrn00_OnChange_BillBy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // for Delete Event
        scrnMsg.xxPgFlg_DE.clear();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        String selectBillbyTp = scrnMsg.A.no(getButtonSelectNumber()).svcBillByTpCd_SE.getValue();
        NSAL0410CommonLogic.controlActvfieldByBillByTp(scrnMsg, selectBillbyTp, getButtonSelectNumber());

        // has error
        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    }
}
