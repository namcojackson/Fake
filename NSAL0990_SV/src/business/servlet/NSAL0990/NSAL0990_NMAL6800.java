/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 *</pre>
 */
public class NSAL0990_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
     // START 2016/09/21 N.Arai [QC#11616, MOD]
     // setValue(scrnMsg.mdseNm, scrnMsg.xxPopPrm_P1.getValue());
        setValue(scrnMsg.mdseDescShortTxt, scrnMsg.xxPopPrm_P1.getValue());
     // END 2016/09/21 N.Arai [QC#11616, MOD]
        scrnMsg.setFocusItem(scrnMsg.mdseCd);

    }
}
