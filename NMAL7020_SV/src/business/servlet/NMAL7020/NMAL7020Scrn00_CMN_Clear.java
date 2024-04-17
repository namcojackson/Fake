/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020;

import static business.servlet.NMAL7020.constant.NMAL7020Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7020.NMAL7020CMsg;
import business.servlet.NMAL7020.common.NMAL7020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7020Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CMsg bizMsg = new NMAL7020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CMsg bizMsg = (NMAL7020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7020CommonLogic.initCmnBtnProp(this, getUserProfileService());
    }
}
