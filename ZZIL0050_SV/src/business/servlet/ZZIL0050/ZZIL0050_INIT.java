/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0050.ZZIL0050CMsg;
import business.servlet.ZZIL0050.common.ZZIL0050CommonLogic;
import business.servlet.ZZIL0050.constant.ZZIL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

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

        ZZIL0050CommonLogic.setCommonButtons(this);
        S21TableColorController tblColor = new S21TableColorController(ZZIL0050Constant.SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        scrnMsg.setFocusItem(scrnMsg.intfcId);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

    }
}
