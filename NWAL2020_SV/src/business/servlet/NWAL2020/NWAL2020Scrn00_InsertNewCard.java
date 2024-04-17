/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2020;

import static business.servlet.NWAL2020.constant.NWAL2020Constant.BIZ_ID;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.BTN_INSERT_NEW_CARDS;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.PAYMENTCH_FAILED;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2020.NWAL2020CMsg;
import business.servlet.NWAL2020.common.NWAL2020CommonLogic;
import business.servlet.NWAL2020.common.NWAL2020TceppsRequestResponce;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2020Scrn00_InsertNewCard
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020Scrn00_InsertNewCard extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;

        NWAL2020CommonLogic.validateCreditCard(scrnMsg);

        // tcepps ITG402550 start
        new NWAL2020TceppsRequestResponce().loadResponseParams(ctx, (NWAL2020BMsg) scrnMsg, getGlobalCompanyCode());
        if (scrnMsg.crCardCustRefNum_C.isClear()) {
            scrnMsg.setMessageInfo("ZZSM0001E", new String[] {PAYMENTCH_FAILED });
            throw new EZDFieldErrorException();
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(BTN_INSERT_NEW_CARDS);

        NWAL2020CMsg bizMsg = new NWAL2020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;
        NWAL2020CMsg bizMsg = (NWAL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
