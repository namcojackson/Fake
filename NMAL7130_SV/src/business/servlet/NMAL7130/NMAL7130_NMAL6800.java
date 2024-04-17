/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130_NMAL6800
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        scrnMsg.setFocusItem(scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_D1);
        scrnMsg.xxCellIdx.clear();
    }
}