/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/25   Hitachi         A.Kohinata      Create          QC#17369
 *</pre>
 */
public class NSAL0440Scrn00_Clear_ContrCondVal extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;

        int selectNumber = getButtonSelectNumber();
        scrnMsg.A.no(selectNumber).svcTermCondDataValCd_PS.clear();
        scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_A.clear();
        scrnMsg.setFocusItem(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_A);
    }
}
