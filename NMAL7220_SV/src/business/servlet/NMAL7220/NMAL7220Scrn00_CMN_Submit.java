/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7220;

import static business.servlet.NMAL7220.constant.NMAL7220Constant.BIZ_ID;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.SCRN_ID_00;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.NZZM0002I;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7220.NMAL7220CMsg;
import business.servlet.NMAL7220.common.NMAL7220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7220Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        NMAL7220CommonLogic.submitCheck(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;

        NMAL7220CMsg bizMsg = new NMAL7220CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        NMAL7220CMsg bizMsg = (NMAL7220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        scrnMsg.addCheckItem(scrnMsg.prcFmlaNm_H1);
        scrnMsg.addCheckItem(scrnMsg.A.no(0).prcCatgNm_A1);

        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.prcFmlaPk_H1);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
