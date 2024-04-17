/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL9010.common.NFAL9010CommonLogic;
import business.servlet.NFAL9010.constant.NFAL9010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL9010Scrn00_CMN_Clear
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9010Scrn00_CMN_Clear extends S21CommonHandler implements NFAL9010Constant {

    /**
     * Method name: checkInput
     * <dd>The method explanation: Check Input Data.
     * <dd>Remarks:
     * @param ctx Screen Application Context Class
     * @param bMsg Object which input value from screen is stored
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9010BMsg scrnMsg = (NFAL9010BMsg)bMsg;

    }

    /**
     * Method name: setRequestData
     * <dd>The method explanation: Call business logic.
     * <dd>Remarks:
     * @param ctx Screen Application Context Class
     * @param bMsg Object which input value from screen is stored
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9010BMsg scrnMsg = (NFAL9010BMsg)bMsg;
        // NFAL9010CMsg bizMsg = new NFAL9010CMsg();
        // bizMsg.setBusinessID( "NFAL9010" );
        // bizMsg.setFunctionCode( "20" );
        // EZDMsg.copy( scrnMsg, null, bizMsg, null );
        // return bizMsg;

        return null;
    }

    /**
     * Method name: doProcess
     * <dd>The method explanation: Display and edit for next page
     * contents.
     * <dd>Remarks:
     * @param ctx Screen Application Context
     * @param bMsg Object which input value from screen is stored
     * @param cMsg Object which input value from business is stored
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        // Clear Display Item.
        NFAL9010CommonLogic.clearDisplayItem(scrnMsg);

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.setValue(0);
        scrnMsg.xxPageShowToNum.setValue(0);
        scrnMsg.xxPageShowOfNum.setValue(0);

        NFAL9010CommonLogic.setDisplayName(scrnMsg);
        NFAL9010CommonLogic.changeTableColorByRow(ctx, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.coaAcctCd);
    }

}
