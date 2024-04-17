/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/12/14   Hitachi         K.Kasai         Update          QC1896
 *</pre>
 */
public class NSAL0150Scrn00_OpenWin_Estimate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        // MOD START 2015/12/14 K.Kasai [QC1896]
//        Object[] prm = new Object[2];
        Object[] prm = new Object[3];
        // MOD END 2015/12/14 K.Kasai [QC1896]
        prm[0] = scrnMsg.svcMachMstrPk;
        prm[1] = scrnMsg.P;
        // ADD START 2015/12/14 K.Kasai [QC1896]
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(0).mtrReadDt_B)) {
            prm[2] = scrnMsg.B.no(0).mtrReadDt_B;
        }
        // ADD END 2015/12/14 K.Kasai [QC1896]

        setArgForSubScreen(prm);
    }
}
