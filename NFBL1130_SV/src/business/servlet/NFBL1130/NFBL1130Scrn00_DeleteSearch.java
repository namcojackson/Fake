/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import static business.servlet.NFBL1130.constant.NFBL1130Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL1130.NFBL1130CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/10   Hitachi         J.Kim           Create          QC#5521
 *</pre>
 */
public class NFBL1130Scrn00_DeleteSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptPk_S)) {
            scrnMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E, new String[] {"Saved Search Options" });
        }

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
        scrnMsg.setMessageInfo("NZZM0002I");
    }
}
