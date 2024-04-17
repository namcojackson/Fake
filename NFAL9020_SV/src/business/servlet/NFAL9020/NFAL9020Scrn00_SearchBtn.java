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

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: NFAL9020Scrn00_SearchBtn
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9020Scrn00_SearchBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020Scrn00_SearchBtn extends S21CommonHandler implements NFAL9020Constant {

    /** Singleton instance. */
    NFAL9020CommonLogic common = new NFAL9020CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        // scrnMsg.addCheckItem(scrnMsg.ajeId);
        // scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
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

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).sysSrcNm_A.setInputProtected(true);
            scrnMsg.A.no(i).trxNm_A.setInputProtected(true);
            scrnMsg.A.no(i).trxRsnNm_A.setInputProtected(true);

            scrnMsg.A.no(i).ajePtrnIndTpCd_01.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnIndTpNm_01.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlCd_01.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlNm_01.setInputProtected(true);

            scrnMsg.A.no(i).ajePtrnIndTpCd_02.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnIndTpNm_02.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlCd_02.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlNm_02.setInputProtected(true);

            scrnMsg.A.no(i).ajePtrnIndTpCd_03.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnIndTpNm_03.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlCd_03.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlNm_03.setInputProtected(true);

            scrnMsg.A.no(i).jrnlCatgNm_A.setInputProtected(true);
        }
        NFAL9020CommonLogic.changeTableColorByRow(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ajeId);
    }

}
