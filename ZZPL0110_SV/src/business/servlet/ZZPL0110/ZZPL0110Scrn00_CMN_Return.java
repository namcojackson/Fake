/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
// import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
// import business.blap.ZZPL0110.ZZPL0110CMsg;
// import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/12   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        // ZZPL0110CMsg bizMsg = new ZZPL0110CMsg();
        // bizMsg.setBusinessID("ZZPL0110");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;
        // ZZPL0110CMsg bizMsg = (ZZPL0110CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
