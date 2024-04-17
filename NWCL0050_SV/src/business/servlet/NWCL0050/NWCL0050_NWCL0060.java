/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2015/11/09   Fujitsu         H.Nagashima     Update          CSA
 *</pre>
 */
public class NWCL0050_NWCL0060 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//CSA MOD Start
//        return null;
        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.scrInvEmlTs)) {
            return null;
        }

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
//CSA MOD End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // do nothing
    }
}
