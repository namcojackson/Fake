/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7140.common.NMAL7140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/06   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NMAL7140Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7140BMsg scrnMsg = (NMAL7140BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoCd);
        scrnMsg.addCheckItem(scrnMsg.prcPrmoQlfyTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcQlfyValTxt);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        scrnMsg.addCheckItem(scrnMsg.prmoAmt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H2);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7140BMsg scrnMsg = (NMAL7140BMsg) bMsg;
        Object[] arg = (Object[]) getArgForSubScreen();

        NMAL7140CommonLogic.setOutputParam(scrnMsg, arg);
    }
}
