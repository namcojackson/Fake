/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0350;

import static business.servlet.NSBL0350.constant.NSBL0350Constant.POP_PARAM_NUM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSBL0350.common.NSBL0350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Group Level Report
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0350Scrn00_OpenWin_OrgLkupPop extends S21CommonHandler {

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
        NSBL0350BMsg scrnMsg = (NSBL0350BMsg) bMsg;
        NSBL0350CommonLogic.initParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, BIZ_AREA_ORG.SERVICE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgDescTxt_H);
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
