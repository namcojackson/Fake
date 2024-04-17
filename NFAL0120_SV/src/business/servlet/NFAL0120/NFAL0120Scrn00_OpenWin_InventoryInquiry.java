/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFAL0120.NFAL0120CMsg;
//import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFAL0120Scrn00_OpenWin_InventoryInquiry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        //NFAL0120CMsg bizMsg = new NFAL0120CMsg();
        //bizMsg.setBusinessID("NFAL0120");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        //---- start 2016/03/30 Set only 3 parameters
        Object[] params = new Object[3];
        params[0] = scrnMsg.A.no(selectIdx).glDt_A;
        params[1] = scrnMsg.A.no(selectIdx).glDt_A;
        params[2] = scrnMsg.A.no(selectIdx).invtyTrxPk_A;
        //---- end 2016/03/30
        
        setArgForSubScreen(params);

    }
}
