/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import static business.servlet.NFAL0200.constant.NFAL0200Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0200.NFAL0200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/21   Hitachi         J.Kim           Create          QC#13514
 *</pre>
 */
public class NFAL0200Scrn00_DeleteSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptPk_S)) {
            scrnMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E, new String[] {"Saved Search Options" });
        }

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        NFAL0200CMsg bizMsg = new NFAL0200CMsg();
        bizMsg.setBusinessID("NFAL0200");
        bizMsg.setFunctionCode("40");
        EZDSMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();

        scrnMsg.setMessageInfo(NZZM0002I);

    }
}
