/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130_NMAL6870
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130_NMAL6870 extends S21CommonHandler {

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

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (i == 0) {
                sb = sb.append(scrnMsg.R.no(i).xxComnScrColValTxt_1.getValue());
            } else {
                sb = sb.append(scrnMsg.addCharTxt.getValue()).append(scrnMsg.R.no(i).xxComnScrColValTxt_1.getValue());
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt()).xxRecNmTxt_D1, sb.toString());

        scrnMsg.setFocusItem(scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt()).xxRecNmTxt_D1);
        scrnMsg.xxCellIdx.clear();
    }
}
