/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL3080.NLBL3080CMsg;
//import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * ## 2016/03/10 no use program. ##
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/10   CITS            T.Tokutomi      Update          QC#5242
 *</pre>
 */
public class NLBL3080_NMAL6780 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

        //NLBL3080CMsg bizMsg = new NLBL3080CMsg();
        //bizMsg.setBusinessID("NLBL3080");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        //NLBL3080CMsg bizMsg  = (NLBL3080CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
