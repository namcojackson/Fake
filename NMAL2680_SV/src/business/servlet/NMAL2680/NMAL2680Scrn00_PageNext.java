/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2680;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2680.NMAL2680CMsg;
import business.servlet.NMAL2680.common.NMAL2680CommonLogic;
import business.servlet.NMAL2680.constant.NMAL2680Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2680Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2680BMsg scrnMsg = (NMAL2680BMsg) bMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        NMAL2680CMsg bizMsg = new NMAL2680CMsg();
        bizMsg.setBusinessID(NMAL2680Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2680Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2680BMsg scrnMsg = (NMAL2680BMsg) bMsg;
        NMAL2680CMsg bizMsg = (NMAL2680CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2680CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
