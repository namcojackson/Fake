/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9020;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL9020.common.NFAL9020CommonLogic;
import business.servlet.NFAL9020.constant.NFAL9020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: NFAL9020Scrn00_SelectBtn
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9020Scrn00_SelectBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020Scrn00_SelectBtn extends S21CommonHandler implements NFAL9020Constant {

    /** Singleton instance. */
    NFAL9020CommonLogic common = new NFAL9020CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

        // NFAL9020CMsg bizMsg = new NFAL9020CMsg();
        // bizMsg.setBusinessID("NFAL9020");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {

            // Row Number Index
            int selectedIndex = getButtonSelectNumber();

            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    ((EZDBStringItem) param[0]).setValue(scrnMsg.A.no(selectedIndex).ajeId_A.getValue().toUpperCase());
                } else if (i == 1) {
                    ((EZDBStringItem) param[1]).setValue(scrnMsg.A.no(selectedIndex).ajePtrnIndTpCd_01.getValue().toUpperCase());
                } else if (i == 2) {
                    ((EZDBStringItem) param[2]).setValue(scrnMsg.A.no(selectedIndex).ajePtrnActlCd_01.getValue().toUpperCase());
                } else if (i == 3) {
                    ((EZDBStringItem) param[3]).setValue(scrnMsg.A.no(selectedIndex).ajePtrnIndTpCd_02.getValue().toUpperCase());
                } else if (i == 4) {
                    ((EZDBStringItem) param[4]).setValue(scrnMsg.A.no(selectedIndex).ajePtrnActlCd_02.getValue().toUpperCase());
                } else if (i == 5) {
                    ((EZDBStringItem) param[5]).setValue(scrnMsg.A.no(selectedIndex).ajePtrnIndTpCd_03.getValue().toUpperCase());
                } else if (i == 6) {
                    ((EZDBStringItem) param[6]).setValue(scrnMsg.A.no(selectedIndex).ajePtrnActlCd_03.getValue().toUpperCase());
                } else if (i == 7) {
                    ((EZDBStringItem) param[7]).setValue(scrnMsg.A.no(selectedIndex).sysSrcNm_A.getValue().toUpperCase());
                } else if (i == 8) {
                    ((EZDBStringItem) param[8]).setValue(scrnMsg.A.no(selectedIndex).trxNm_A.getValue().toUpperCase());
                } else if (i == 9) {
                    ((EZDBStringItem) param[9]).setValue(scrnMsg.A.no(selectedIndex).trxRsnNm_A.getValue().toUpperCase());
                }
            } // for
        } // if
    }

}
