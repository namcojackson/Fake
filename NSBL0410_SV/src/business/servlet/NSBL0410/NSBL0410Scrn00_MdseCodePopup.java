/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.constant.NSBL0410Constant.LENGTH_7;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSBL0410Scrn00_MdseCodePopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.mdseCd);
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();

        Object[] params = new Object[LENGTH_7];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_0;
        params[i++] = scrnMsg.xxPopPrm_1;
        params[i++] = scrnMsg.xxPopPrm_2;
        params[i++] = scrnMsg.xxPopPrm_3;
        params[i++] = scrnMsg.xxPopPrm_4;
        params[i++] = scrnMsg.xxPopPrm_5;
        params[i++] = scrnMsg.xxPopPrm_6;
        setArgForSubScreen(params);
    }
}
