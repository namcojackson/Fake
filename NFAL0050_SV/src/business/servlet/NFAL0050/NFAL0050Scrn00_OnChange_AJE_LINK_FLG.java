/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0050Scrn00_OnChange_AJE_LINK_FLG
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_OnChange_AJE_LINK_FLG extends S21CommonHandler implements NFAL0050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        // NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        // bizMsg.setBusinessID("NFAL0050");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        // NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }

}
