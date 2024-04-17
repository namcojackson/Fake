/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0040.ZZIL0040CMsg;
import business.servlet.ZZIL0040.common.ZZIL0040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZZIL0040Scrn00_View extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;

        // Get selected result index
        int selected = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.mdbInbdIntfcRqstPk_D, scrnMsg.A.no(selected).mdbInbdIntfcRqstPk_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcId_D, scrnMsg.A.no(selected).intfcId_A.getValue());

        ZZIL0040CMsg bizMsg = new ZZIL0040CMsg();
        bizMsg.setBusinessID("ZZIL0040");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;
        ZZIL0040CMsg bizMsg = (ZZIL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZIL0040CommonLogic.setDispDate01(scrnMsg, bizMsg);
        ZZIL0040CommonLogic.dspScrn01(scrnMsg, this);

    }
}
