/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.NWWM0028E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020_NMAL6870
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020_NMAL6870 extends S21CommonHandler {

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

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        StringBuilder ntfyDistListTxt = new StringBuilder();
        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
            if (i != 0) {
                ntfyDistListTxt.append(",");
            }
            ntfyDistListTxt.append(scrnMsg.P.no(i).xxComnScrColValTxt_1.getValue());
        }

        if (ntfyDistListTxt.length() <= scrnMsg.getAttr(NWWL0020Bean.ntfyDistListNmListTxt).getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyDistListNmListTxt, ntfyDistListTxt.toString());
        } else {
            scrnMsg.setMessageInfo(NWWM0028E, new String[] {scrnMsg.ntfyDistListNmListTxt.getNameForMessage() });
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyDistListNmListTxt, ntfyDistListTxt.toString().substring(0, scrnMsg.getAttr(NWWL0020Bean.ntfyDistListNmListTxt).getDigit()));
        }

        scrnMsg.setFocusItem(scrnMsg.ntfyDistListNmListTxt);
    }
}
