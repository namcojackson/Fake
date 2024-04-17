/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_HoldDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[1];
// START 2016/10/19 N.Arai [QC#13901, MOD]
//        params[0] = scrnMsg.A.no(selectIdx).svcTaskNum_A;
        params[0] = scrnMsg.A.no(selectIdx).fsrNum_A;
// END 2016/10/19 N.Arai [QC#13901, MOD]

        setArgForSubScreen(params);
    }
}
