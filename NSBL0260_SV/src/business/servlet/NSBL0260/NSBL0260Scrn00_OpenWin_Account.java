/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.BILL_TO_ONLY;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PARAM_LENGTH_NMAL6760;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_CUST_HEADER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0260.common.NSBL0260CommonLogic;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSBL0260Scrn00_OpenWin_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        NSBL0260CommonLogic.clearPopupDataForScrnMsg(scrnMsg);
        setValue(scrnMsg.xxPopPrm_SE, SELECT_POPUP_CUST_HEADER);
        Object[] params = new Object[PARAM_LENGTH_NMAL6760];
        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setValue(scrnMsg.xxPopPrm_00, scrnMsg.dsAcctNum_H);
        setValue(scrnMsg.xxPopPrm_12, BILL_TO_ONLY);

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_00;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        params[i++] = scrnMsg.xxPopPrm_15;
        params[i++] = scrnMsg.xxPopPrm_16;
        params[i++] = scrnMsg.xxPopPrm_17;
        params[i++] = scrnMsg.xxPopPrm_18;
        params[i++] = scrnMsg.xxPopPrm_19;
        params[i++] = scrnMsg.xxPopPrm_20;
        params[i++] = scrnMsg.xxPopPrm_21;
        params[i++] = scrnMsg.xxPopPrm_22;
        params[i++] = scrnMsg.xxPopPrm_23;

        setArgForSubScreen(params);
    }
}
