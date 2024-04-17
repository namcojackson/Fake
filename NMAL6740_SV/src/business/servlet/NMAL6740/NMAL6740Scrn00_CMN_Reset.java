/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import static business.servlet.NMAL6740.constant.NMAL6740Constant.BIZ_ID;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6740.NMAL6740CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 *</pre>
 */
public class NMAL6740Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;

        NMAL6740CMsg bizMsg = new NMAL6740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
