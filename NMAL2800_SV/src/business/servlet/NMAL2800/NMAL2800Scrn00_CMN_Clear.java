/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.servlet.NMAL2800.common.NMAL2800CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        NMAL2800CMsg bizMsg = new NMAL2800CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2800CommonLogic.initCmnBtnProp(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxTpCd_H1);
    }
}