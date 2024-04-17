/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0350;

import static business.servlet.NSBL0350.constant.NSBL0350Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSBL0350.common.NSBL0350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/** 
 *<pre>
 * Group Level Report
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 *</pre>
 */
public class NSBL0350Scrn00_MoveWin_SubGrpRpt extends S21CommonHandler {

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

        if (!hasValue(scrnMsg.xxFromDt_H)) {
            scrnMsg.xxFromDt_H.setErrorInfo(1, ZZM9000E, new String[]{AS_OF});
            scrnMsg.addCheckItem(scrnMsg.xxFromDt_H);
            scrnMsg.putErrorScreen();
            return;
        }

        NSBL0350CommonLogic.initParam(scrnMsg);
        int colNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.A.no(colNum).orgCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFromDt_P, scrnMsg.xxFromDt_H);
        Object[] params = new Object[POP_PARAM_NUM_2];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_0;
        params[index++] = scrnMsg.xxFromDt_P;
        setArgForSubScreen(params);
        // START 2016/03/22 K.Yamada [QC#5735, ADD]
        openMultiModeScreen(TAB_NAME_NSBL0360);
        // END 2016/03/22 K.Yamada [QC#5735, ADD]
    }
}
