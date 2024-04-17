/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_BTN_FLG_FROM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7190.NMAL7190CMsg;
//import business.servlet.NMAL7190.constant.NMAL7190Constant;

import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/14   Fujitsu         M.Ohno          Create          QC#18789(L3)
 *</pre>
 */
public class NMAL7190_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        int index = getButtonSelectNumber();

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(index).prcGrpFromTxt_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1,scrnMsg.P.no(7).xxPopPrm);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(index).prcGrpThruTxt_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1,scrnMsg.P.no(7).xxPopPrm);
        }
    }
}
