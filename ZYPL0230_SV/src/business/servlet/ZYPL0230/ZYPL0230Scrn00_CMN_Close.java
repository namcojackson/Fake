package business.servlet.ZYPL0230;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0230.constant.ZYPL0230Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author Administrator
 */
public class ZYPL0230Scrn00_CMN_Close extends S21CommonHandler implements ZYPL0230Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // none
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // none
    }

}
