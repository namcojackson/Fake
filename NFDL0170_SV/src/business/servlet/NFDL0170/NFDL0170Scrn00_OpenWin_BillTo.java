/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170;

import static business.servlet.NFDL0170.constant.NFDL0170Constant.NMAL6760_PRM_LENGTH;
import static business.servlet.NFDL0170.constant.NFDL0170Constant.RELN_TP_BILL_TO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 *</pre>
 */
public class NFDL0170Scrn00_OpenWin_BillTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;

        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();
        scrnMsg.xxPopPrm_PO.clear();

        scrnMsg.xxPopPrm_PD.setValue(RELN_TP_BILL_TO);

        Object[] params = new Object[NMAL6760_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.billToCustAcctCd_H;   // Bill to customer number
        params[i++] = scrnMsg.billToCustAcctNm_H;   // Bill to customer name
        params[i++] = scrnMsg.xxPopPrm_P3;          // no used
        params[i++] = scrnMsg.xxPopPrm_P4;          // used
        params[i++] = scrnMsg.xxPopPrm_P5;          // no used
        params[i++] = scrnMsg.xxPopPrm_P6;          // no used
        params[i++] = scrnMsg.xxPopPrm_P7;          // no used
        params[i++] = scrnMsg.xxPopPrm_P8;          // no used
        params[i++] = scrnMsg.xxPopPrm_P9;          // no used
        params[i++] = scrnMsg.xxPopPrm_PA;          // no used
        params[i++] = scrnMsg.xxPopPrm_PB;          // no used
        params[i++] = scrnMsg.xxPopPrm_PC;          // no used
        params[i++] = scrnMsg.xxPopPrm_PD;          // Display Hirarchey Account
        params[i++] = scrnMsg.xxPopPrm_PE;          // no used
        params[i++] = scrnMsg.xxPopPrm_PF;          // no used
        params[i++] = scrnMsg.xxPopPrm_PG;          // no used
        params[i++] = scrnMsg.xxPopPrm_PH;          // no used
        params[i++] = scrnMsg.xxPopPrm_PI;          // no used
        params[i++] = scrnMsg.xxPopPrm_PJ;          // no used
        params[i++] = scrnMsg.xxPopPrm_PK;          // no used
        params[i++] = scrnMsg.xxPopPrm_PL;          // no used
        params[i++] = scrnMsg.xxPopPrm_PM;          // no used
        params[i++] = scrnMsg.xxPopPrm_PN;          // no used
        params[i++] = scrnMsg.xxPopPrm_PO;          // no used
        setArgForSubScreen(params);
    }
}
