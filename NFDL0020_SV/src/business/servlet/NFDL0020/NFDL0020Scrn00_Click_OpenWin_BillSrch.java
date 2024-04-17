/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;


import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFDL0020Scrn00_Click_OpenWin_BillSrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        //NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        //bizMsg.setBusinessID("NFDL0020");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        scrnMsg.P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.dsAcctNum_H);
        scrnMsg.P.no(1).xxPopPrm.clear();
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(3).xxPopPrm.clear();
        scrnMsg.P.no(4).xxPopPrm.clear();
        scrnMsg.P.no(5).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.clear();
        scrnMsg.P.no(7).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.clear();
        scrnMsg.P.no(9).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.clear();
        scrnMsg.P.no(11).xxPopPrm.clear();
        scrnMsg.P.no(12).xxPopPrm.clear();
        scrnMsg.P.no(13).xxPopPrm.clear();
        scrnMsg.P.no(14).xxPopPrm.clear();
        scrnMsg.P.no(15).xxPopPrm.clear();
        scrnMsg.P.no(16).xxPopPrm.clear();

        //NMAL6760
        Object[] param = new Object[17];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;
        param[11] = scrnMsg.P.no(11).xxPopPrm;
        param[12] = scrnMsg.P.no(12).xxPopPrm;
        param[13] = scrnMsg.P.no(13).xxPopPrm;
        param[14] = scrnMsg.P.no(14).xxPopPrm;
        param[15] = scrnMsg.P.no(15).xxPopPrm;
        param[16] = scrnMsg.P.no(16).xxPopPrm;
        
        setArgForSubScreen(param);

    }
}
