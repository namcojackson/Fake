/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0030.ZZIL0030CMsg;

import business.servlet.ZZIL0030.common.ZZIL0030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030Scrn01_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.intfcId_2);
        scrnMsg.addCheckItem(scrnMsg.bizApiId_2);
        scrnMsg.addCheckItem(scrnMsg.intfcIdDescTxt_2);
        scrnMsg.addCheckItem(scrnMsg.trgtBatJobNm_2);
        scrnMsg.addCheckItem(scrnMsg.trgtBatJobDescTxt_2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;

        ZZIL0030CMsg bizMsg = new ZZIL0030CMsg();
        bizMsg.setBusinessID("ZZIL0030");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0030BMsg scrnMsg = (ZZIL0030BMsg) bMsg;
        ZZIL0030CMsg bizMsg = (ZZIL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.addCheckItem(scrnMsg.intfcId_2);
        scrnMsg.putErrorScreen();

        ZZIL0030CommonLogic.dspScrn00(scrnMsg, this);

    }
}
