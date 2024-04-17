/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0120Scrn00_OnChange_COA_CH_CD
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120Scrn00_OnChange_COA_CH_CD extends S21CommonHandler implements NFAL0120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        // NFAL0120CMsg bizMsg = new NFAL0120CMsg();
        // bizMsg.setBusinessID("NFAL0120");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        // NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }

}
