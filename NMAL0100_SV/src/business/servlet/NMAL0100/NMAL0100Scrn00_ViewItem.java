package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.ERROR_CODE_E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0100Scrn00_ViewItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        BigDecimal radioValue = scrnMsg.xxRadioBtn.getValue();
        if (scrnMsg.A.getValidCount() == 0 || radioValue == null || radioValue.intValue() < 0) {
            //ZZM9000E=0,[@] field is mandatory.
            scrnMsg.xxRadioBtn.setErrorInfo(1, "ZZM9000E", new String[] {"Line selection" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
        scrnMsg.putErrorScreen();

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)) {
            int radioValue = scrnMsg.xxRadioBtn.getValueInt();
            if (scrnMsg.A.getValidCount() > radioValue && radioValue >= 0) {
                Object[] params = new Object[1];
                params[0] = scrnMsg.A.no(radioValue).mdseCd_A1;
                setArgForSubScreen(params);
            }
        }
    }

}
