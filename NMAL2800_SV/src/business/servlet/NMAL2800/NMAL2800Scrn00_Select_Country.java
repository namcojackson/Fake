/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/26   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_Select_Country extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        NMAL2800CMsg bizMsg = new NMAL2800CMsg();
        bizMsg.setBusinessID("NMAL2800");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}