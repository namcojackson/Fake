/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0240_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
		String event = scrnMsg.O.no(29).xxPopPrm.getValue();
		if ("NMAL0240Scrn00_OpenWin_BomItem".equals(event)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.O.no(0).xxPopPrm.getValue());
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
		} else if ("NMAL0240Scrn00_OpenWin_CmpsnItemA".equals(event)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1, scrnMsg.O.no(0).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).mdseDescShortTxt_A1, scrnMsg.O.no(7).xxPopPrm.getValue());
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1);
		} else if ("NMAL0240Scrn00_OpenWin_CmpsnItemB".equals(event)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(getButtonSelectNumber()).mdseCd_B1, scrnMsg.O.no(0).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(getButtonSelectNumber()).mdseDescShortTxt_B1, scrnMsg.O.no(7).xxPopPrm.getValue());
            scrnMsg.setFocusItem(scrnMsg.B.no(getButtonSelectNumber()).mdseCd_B1);
        }
    }
}
