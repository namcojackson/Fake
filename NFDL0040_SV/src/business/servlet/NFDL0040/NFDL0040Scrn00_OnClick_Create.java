/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFDL0040Scrn00_OnClick_Create
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Fujitsu         C.Naito         Create          QC#6269-1
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#10279
 *</pre>
 */
public class NFDL0040Scrn00_OnClick_Create extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/08/09 K.Kojima [QC#10279,ADD]
        NFDL0040BMsg scrnMsg = (NFDL0040BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxBtnFlg) || !ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setMessageInfo("NFDM0035E");
        }
        // END 2016/08/09 K.Kojima [QC#10279,ADD]
    }
}
