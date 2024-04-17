/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6710Scrn00_CMN_Return extends S21CommonHandler implements NMAL6710Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL2030BMsg scrnMsg = (NMAL2030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL2030BMsg scrnMsg = (NMAL2030BMsg) bMsg;

        // NMAL2030CMsg bizMsg = new NMAL2030CMsg();
        // bizMsg.setBusinessID("NMAL2030");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NMAL2030BMsg scrnMsg = (NMAL2030BMsg) bMsg;
//        // NMAL2030CMsg bizMsg = (NMAL2030CMsg) cMsg;
//
//        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        NMAL2030CommonLogic.clearConfirmInfo(scrnMsg, CMN_RETURN);
//        if (scrnMsg.xxSetFlg_R0.isClear()) {
//            scrnMsg.setMessageInfo("ZZM8102I", new String[] {MSG_CONST_ADD_RETURN });
//            scrnMsg.xxSetFlg_R0.setValue(CONF_FLG);
//            throw new EZDFieldErrorException();
//        } else {
//            scrnMsg.xxSetFlg_R0.clear();
//        }
    }

}
