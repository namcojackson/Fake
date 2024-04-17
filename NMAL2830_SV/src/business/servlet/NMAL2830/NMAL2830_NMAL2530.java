/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_CLOSE_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2830_NMAL2530
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830_NMAL2530 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        if (BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_ON, scrnMsg.xxPopPrm_02);
            scrnMsg.setFocusItem(scrnMsg.stCd);
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    }
}
