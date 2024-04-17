/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZTL0000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZTL0000.ZZTL0000CMsg;
import business.servlet.ZZTL0000.common.ZZTL0000CommonLogic;
import business.servlet.ZZTL0000.constant.ZZTL0000Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2014/09/24   CUSA            Y.Suda          Create          N/A
 *</pre>
 */
public class ZZTL0000_INIT extends S21CommonHandlerEx implements ZZTL0000Constant {

    /** BussinessID */
    private static String bussinessId = "ZZTL0000";

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;
        
        // security violation check of this screen.
        //this.checkBusinessAppGranted(getContextUserInfo().getUserId(), bussinessId);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZTL0000BMsg scrnMsg = (ZZTL0000BMsg) bMsg;

        ZZTL0000CMsg bizMsg = new ZZTL0000CMsg();
        bizMsg.setBusinessID(bussinessId);
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

        ZZTL0000CommonLogic.dspScrn00(scrnMsg, this);
    }

}
