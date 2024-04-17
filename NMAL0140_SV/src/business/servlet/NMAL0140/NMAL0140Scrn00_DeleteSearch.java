/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0140;

import static business.servlet.NMAL0140.constant.NMAL0140Constant.BUSINESS_ID;
import static business.servlet.NMAL0140.constant.NMAL0140Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0140.NMAL0140CMsg;
import business.servlet.NMAL0140.common.NMAL0140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0140Scrn00_DeleteSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRAA         K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0140Scrn00_DeleteSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
        NMAL0140CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;

        NMAL0140CMsg bizMsg = new NMAL0140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
        NMAL0140CMsg bizMsg = (NMAL0140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }
}
