/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_DeleteSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2020Scrn00_DeleteSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        NLAL2020CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        NLAL2020CMsg bizMsg = new NLAL2020CMsg();
        bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }
}
