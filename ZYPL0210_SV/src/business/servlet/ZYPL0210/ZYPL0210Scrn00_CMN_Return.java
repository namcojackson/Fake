package business.servlet.ZYPL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author Administrator
 */
public class ZYPL0210Scrn00_CMN_Return extends S21CommonHandler implements ZYPL0210Constant {

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
