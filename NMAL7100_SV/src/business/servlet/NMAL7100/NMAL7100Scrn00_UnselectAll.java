/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7100.NMAL7100CMsg;
//import business.servlet.NMAL7100.constant.NMAL7100Constant;

import business.blap.NMAL7100.NMAL7100CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL7100Scrn00_UnselectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}