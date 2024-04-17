/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0300_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_PO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_H, scrnMsg.svcConfigMstrPk_PO);
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        scrnMsg.setFocusItem(scrnMsg.svcConfigMstrPk_H);
    }
}
