/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7050.constant.NMAL7050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;
        if (!NMAL7050Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt, scrnMsg.P.no(0).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt);
    }
}
