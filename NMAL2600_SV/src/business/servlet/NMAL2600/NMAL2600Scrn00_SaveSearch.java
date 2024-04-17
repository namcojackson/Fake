/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

import static business.servlet.NMAL2600.constant.NMAL2600Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2600.NMAL2600CMsg;
import business.servlet.NMAL2600.common.NMAL2600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2600Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptNm_S)){
            scrnMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E, new String[]{"Search Option Name"});
            scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
            scrnMsg.putErrorScreen();
        }

        NMAL2600CommonLogic.addCheckItems(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        NMAL2600CMsg bizMsg = new NMAL2600CMsg();
        bizMsg.setBusinessID("NMAL2600");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;
        NMAL2600CMsg bizMsg  = (NMAL2600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }
}
