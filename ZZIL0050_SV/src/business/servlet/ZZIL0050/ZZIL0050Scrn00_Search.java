/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0050;

import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0050.ZZIL0050CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0050Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0050BMsg scrnMsg = (ZZIL0050BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.intfcId);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0050BMsg scrnMsg = (ZZIL0050BMsg) bMsg;

        ZZIL0050CMsg bizMsg = new ZZIL0050CMsg();
        bizMsg.setBusinessID("ZZIL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0050BMsg scrnMsg = (ZZIL0050BMsg) bMsg;
        ZZIL0050CMsg bizMsg = (ZZIL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }
}
