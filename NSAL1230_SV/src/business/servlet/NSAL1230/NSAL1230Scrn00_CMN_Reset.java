/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1230.NSAL1230CMsg;
import business.servlet.NSAL1230.common.NSAL1230CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1230Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg = new NSAL1230CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg  = (NSAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1230CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
