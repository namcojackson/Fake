/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6800;

//import static business.servlet.NMAL6800.constant.NMAL6800Constant.BIZ_ID;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.BUSINESS_ID;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.FUNC_CD_UPDT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6800.NMAL6800CMsg;
//import business.servlet.NMAL6800.constant.NMAL6800Constant;

import business.blap.NMAL6800.NMAL6800CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6800Scrn00_CMN_ColClear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;

        NMAL6800CMsg bizMsg = new NMAL6800CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
        NMAL6800CMsg bizMsg = (NMAL6800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
