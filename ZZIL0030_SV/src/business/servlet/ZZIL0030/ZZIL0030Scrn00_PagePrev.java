/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0030.ZZIL0030CMsg;
import business.servlet.ZZIL0030.common.ZZIL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowFromNum, BigDecimal.valueOf(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1));
        scrnMsg.xxPageShowToNum.clear();

        ZZIL0030CMsg bizMsg = new ZZIL0030CMsg();
        bizMsg.setBusinessID("ZZIL0030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;
        ZZIL0030CMsg bizMsg = (ZZIL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZIL0030CommonLogic.dspScrn00(scrnMsg, this);
    }
}
