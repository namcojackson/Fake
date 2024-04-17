/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2190;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL2190.NWAL2190CMsg;
//import business.servlet.NWAL2190.constant.NWAL2190Constant;

import business.blap.NWAL2190.NWAL2190CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Supply Agreement Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/02   Fujitsu         S.Iidaka        Create          N/A
 *</pre>
 */
public class NWAL2190Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NWAL2190BMsg scrnMsg = (NWAL2190BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2190BMsg scrnMsg = (NWAL2190BMsg) bMsg;

        NWAL2190CMsg bizMsg = new NWAL2190CMsg();
        bizMsg.setBusinessID("NWAL2190");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2190BMsg scrnMsg = (NWAL2190BMsg) bMsg;
        NWAL2190CMsg bizMsg  = (NWAL2190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
