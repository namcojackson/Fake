/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1870;

import java.io.Serializable;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1870Scrn00_CMN_keepOriginal
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1870Scrn00_CMN_keepOriginal extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //doNothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1870BMsg scrnMsg = (NWAL1870BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length >= 5) {
                scrnMsg.xxPopPrm_P0.setValue(FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[4], scrnMsg.xxPopPrm_P0);
            }
            setArgForSubScreen(params);
        }
    }
}
