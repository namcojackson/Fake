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
 * Class name: Screen Component ID : NFAL0050Scrn00_AjeIdLink
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_AjeIdLink extends S21CommonHandler implements NFAL0050Constant {

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

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        Object[] param = new Object[10];
        param[0] = scrnMsg.ajeId;
        // Passing a mock fields
        param[1] = scrnMsg.ajePtrnIndTpCd_01;
        param[2] = scrnMsg.ajePtrnActlCd_01;
        param[3] = scrnMsg.ajePtrnIndTpCd_02;
        param[4] = scrnMsg.ajePtrnActlCd_02;
        param[5] = scrnMsg.ajePtrnIndTpCd_03;
        param[6] = scrnMsg.ajePtrnActlCd_03;
        param[7] = scrnMsg.sysSrcNm;
        param[8] = scrnMsg.trxNm;
        param[9] = scrnMsg.trxRsnNm;

        setArgForSubScreen(param);
    }

}
