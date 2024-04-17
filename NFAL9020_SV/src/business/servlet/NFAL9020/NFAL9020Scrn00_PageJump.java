/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL9020.NFAL9020CMsg;
import business.servlet.NFAL9020.common.NFAL9020CommonLogic;
import business.servlet.NFAL9020.constant.NFAL9020Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: NFAL9020Scrn00_PageJump
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9020Scrn00_PageNext
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020Scrn00_PageJump extends S21CommonHandler implements NFAL9020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

        NFAL9020CMsg bizMsg = new NFAL9020CMsg();
        bizMsg.setBusinessID("NFAL9020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFAL9020CommonLogic.changeTableColorByRow(scrnMsg);
    }

}
