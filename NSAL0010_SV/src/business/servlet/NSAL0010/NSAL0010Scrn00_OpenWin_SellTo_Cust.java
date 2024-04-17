/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0010.NSAL0010CMsg;
//import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_SellTo_Cust extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        scrnMsg.xxPopPrm_01.clear();
//        scrnMsg.xxPopPrm_02.clear();
//        scrnMsg.xxPopPrm_03.clear();
//        scrnMsg.xxPopPrm_04.clear();
//        scrnMsg.xxPopPrm_05.clear();
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, LOC_ROLE_TP.SELL_TO);
//
//        Object[] params = new Object[6];
//        params[0] = scrnMsg.sellToCustCd_H1;
//        params[1] = scrnMsg.xxPopPrm_01;
//        params[2] = scrnMsg.sellToCustLocNm_H1;
//        params[3] = scrnMsg.xxPopPrm_03;
//        params[4] = scrnMsg.xxPopPrm_04;
//        params[5] = scrnMsg.xxPopPrm_05;
//
//        setArgForSubScreen(params);
    }
}
