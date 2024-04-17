/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1050.NSAL1050CMsg;
import business.servlet.NSAL1050.common.NSAL1050CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   CUSA            T.Mizuki         Create          N/A
 *</pre>
 */
public class NSAL1050Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermDataTpCd_1V);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbSortNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermCondAttrbNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effToDt_A);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;

        NSAL1050CMsg bizMsg = new NSAL1050CMsg();
        bizMsg.setBusinessID("NSAL1050");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        NSAL1050CMsg bizMsg = (NSAL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbSortNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermAttrbDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermCondSrcDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcTermDataTpCd_1V);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effToDt_A);
        }
        scrnMsg.putErrorScreen();
        NSAL1050CommonLogic.controlScreenList(scrnMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
