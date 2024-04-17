/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7150;

import static business.servlet.NMAL7150.constant.NMAL7150Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7150.NMAL7150CMsg;
import business.servlet.NMAL7150.common.NMAL7150CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;

        NMAL7150CMsg bizMsg = new NMAL7150CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;
        NMAL7150CMsg bizMsg = (NMAL7150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7150CommonLogic.initControlScreen(this, scrnMsg);

    }
}
