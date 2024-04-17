/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.BIZ_ID;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NZZM0002I;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7230.NMAL7230CMsg;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CMsg bizMsg = new NMAL7230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        NMAL7230CMsg bizMsg  = (NMAL7230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        NMAL7230CommonLogic.controlScreen(this, scrnMsg);
        NMAL7230CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
