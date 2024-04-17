/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2620.NMAL2620CMsg;
//import business.servlet.NMAL2620.constant.NMAL2620Constant;

import business.blap.NMAL2620.NMAL2620CMsg;
import business.servlet.NMAL2620.constant.NMAL2620Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2620Scrn00_GetPersonNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        scrnMsg.psnNum_D.clear();
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxPsnNm_D)) {
            scrnMsg.setMessageInfo(NMAL2620Constant.NMAM8368E, new String[] {"Move Territory To"});
            throw new EZDFieldErrorException();
        }

        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        NMAL2620CMsg bizMsg = new NMAL2620CMsg();

        bizMsg.setBusinessID("NMAL2620");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        NMAL2620CMsg bizMsg  = (NMAL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
