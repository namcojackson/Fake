/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BIZ_ID;
//import static business.servlet.NMAL0230.constant.NMAL0230Constant.BOM_ITEM_POPUP;
//import static business.servlet.NMAL0230.constant.NMAL0230Constant.CMPSN_ITEM_POPUP;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0230.NMAL0230CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0230_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;

        NMAL0230CMsg bizMsg = new NMAL0230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;
		String event = scrnMsg.O.no(29).xxPopPrm.getValue();
		if ("NMAL0230Scrn00_OpenWin_BomItem".equals(event)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_BO, scrnMsg.O.no(0).xxPopPrm.getValue());
            scrnMsg.setFocusItem(scrnMsg.mdseCd_BO);
		} else if ("NMAL0230Scrn00_OpenWin_CmpsnItem".equals(event)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.childMdseCd_CO, scrnMsg.O.no(0).xxPopPrm.getValue());
            scrnMsg.setFocusItem(scrnMsg.childMdseCd_CO);
        }
    }
}
