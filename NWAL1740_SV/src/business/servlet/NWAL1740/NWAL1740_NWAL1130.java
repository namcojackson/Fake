/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.BIZ_ID;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NWAL1740.NWAL1740CMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1740_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }
        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = new NWAL1740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int btnidx = getButtonSelectNumber();
        if (btnidx > -1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(btnidx).rtlWhNm_C, scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(btnidx).rtlSwhNm_C, scrnMsg.P.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.C.no(btnidx).actvFlg_C);
        }
    }
}
