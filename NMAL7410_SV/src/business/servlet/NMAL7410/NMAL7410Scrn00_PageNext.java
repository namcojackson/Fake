/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7410;

import static business.servlet.NMAL7410.constant.NMAL7410Constant.BIZ_ID;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.FUNCTION_CD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7410.NMAL7410CMsg;
import business.servlet.NMAL7410.common.NMAL7410CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7410Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;
        NMAL7410CommonLogic.pagingAddCheck(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;

        NMAL7410CMsg bizMsg = new NMAL7410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;
        NMAL7410CMsg bizMsg = (NMAL7410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7410CommonLogic.setTblBackColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).crListTxt_A);
    }
}
