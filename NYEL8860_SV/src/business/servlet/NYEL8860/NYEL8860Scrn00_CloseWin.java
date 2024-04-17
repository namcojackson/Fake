/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8860;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NYEL8860.NYEL8860CMsg;
//import business.servlet.NYEL8860.constant.NYEL8860Constant;

import business.blap.NYEL8860.NYEL8860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8860Scrn00_CloseWin extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8860BMsg scrnMsg = (NYEL8860BMsg) bMsg;

        int selectedIndex = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(0).xxPopPrm_P1, scrnMsg.A.no(selectedIndex).xxGrpFlg_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(0).xxPopPrm_P2, scrnMsg.A.no(selectedIndex).wfUsrGrpNm_A.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(0).xxPopPrm_P3, scrnMsg.A.no(selectedIndex).xxDtlNm_A.getValue());

        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object retVal = ((Object[])arg)[4];

            if (retVal instanceof EZDMsgArray) {
                EZDMsg.copy(scrnMsg.R, null, (EZDMsgArray) retVal, null);
            }
        }
    }
}
