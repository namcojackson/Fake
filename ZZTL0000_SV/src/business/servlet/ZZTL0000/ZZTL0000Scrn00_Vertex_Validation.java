/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZTL0000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZTL0000.ZZTL0000CMsg;
//import business.servlet.ZZTL0000.constant.ZZTL0000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/09/07   Fujitsu         C.Ogaki         Update          ---
 *</pre>
 */
public class ZZTL0000Scrn00_Vertex_Validation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;
        //this.checkBusinessAppGranted(getContextUserInfo().getUserId(), bussinessId);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;

        ZZTL0000CMsg bizMsg = new ZZTL0000CMsg();
        bizMsg.setBusinessID("ZZTL0000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;
        ZZTL0000CMsg bizMsg  = (ZZTL0000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
