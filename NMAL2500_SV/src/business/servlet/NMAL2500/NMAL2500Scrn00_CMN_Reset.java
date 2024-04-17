/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2500.NMAL2500CMsg;
import business.servlet.NMAL2500.common.NMAL2500CommonLogic;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2500Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        NMAL2500CMsg bizMsg = new NMAL2500CMsg();
        bizMsg.setBusinessID(NMAL2500Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2500Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2500CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL2500Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_P1);

    }
}
