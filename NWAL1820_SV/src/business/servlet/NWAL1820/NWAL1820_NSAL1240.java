/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1820_NSAL1240
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1820_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard()) && 0 < scrnMsg.O.getValidCount()) {
            String serNum = "";
            for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
                if (SVC_MACH_TP.MACHINE.equals(scrnMsg.O.no(i).svcMachTpCd_O.getValue())) {
                    serNum = scrnMsg.O.no(i).serNum_O.getValue();
                    break;
                }
            }
            if (!ZYPCommonFunc.hasValue(serNum)) {
                serNum = scrnMsg.O.no(0).serNum_O.getValue();
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, serNum);
            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.coaBrDescTxt);
            return;
        }
    }
}
