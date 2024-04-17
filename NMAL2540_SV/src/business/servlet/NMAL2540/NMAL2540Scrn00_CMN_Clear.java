/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NMAL2540.NMAL2540CMsg;
// import business.servlet.NMAL2540.constant.NMAL2540Constant;

import business.blap.NMAL2540.NMAL2540CMsg;
import business.servlet.NMAL2540.common.NMAL2540CommonLogic;
import business.servlet.NMAL2540.constant.NMAL2540Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2540Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;

        NMAL2540CMsg bizMsg = new NMAL2540CMsg();
        bizMsg.setBusinessID(NMAL2540Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2540Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;
        NMAL2540CMsg bizMsg = (NMAL2540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.firstLineAddr_H1);

        NMAL2540CommonLogic.initCommonButton(this);

    }
}
