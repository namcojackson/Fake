/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZKL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.ZZKL0010.ZZKL0010CMsg;
//import business.servlet.ZZKL0010.constant.ZZKL0010Constant;

import business.servlet.ZZKL0010.common.ZZKL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZZKL0010Scrn01_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZKL0010BMsg scrnMsg = (ZZKL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZKL0010BMsg scrnMsg = (ZZKL0010BMsg) bMsg;

        //ZZKL0010CMsg bizMsg = new ZZKL0010CMsg();
        //bizMsg.setBusinessID("ZZKL0010");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //ZZKL0010BMsg scrnMsg = (ZZKL0010BMsg) bMsg;
        //ZZKL0010CMsg bizMsg  = (ZZKL0010CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZZKL0010CommonLogic.enableDownloadButton(this);
    }
}
