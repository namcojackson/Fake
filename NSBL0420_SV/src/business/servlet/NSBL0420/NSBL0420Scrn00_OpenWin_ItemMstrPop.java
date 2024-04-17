/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0420;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSBL0420.NSBL0420CMsg;
//import business.servlet.NSBL0420.constant.NSBL0420Constant;

import business.servlet.NSBL0420.common.NSBL0420CommonLogic;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.POP_PARAM_NUM;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0420Scrn00_OpenWin_ItemMstrPop extends S21CommonHandler {

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
        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;
        NSBL0420CommonLogic.initParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.mdseCd_TF);
        Object[] params = new Object[POP_PARAM_NUM];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_0;
        params[index++] = scrnMsg.xxPopPrm_1;
        params[index++] = scrnMsg.xxPopPrm_2;
        params[index++] = scrnMsg.xxPopPrm_3;
        params[index++] = scrnMsg.xxPopPrm_4;
        params[index++] = scrnMsg.xxPopPrm_5;
        params[index++] = scrnMsg.xxPopPrm_6;
        setArgForSubScreen(params);
    }
}
