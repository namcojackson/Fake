package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;

import java.math.BigDecimal;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140Scrn00_ViewRequest extends S21CommonHandler implements NMAL0140Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		BigDecimal radioValue = scrnMsg.xxRadioBtn_H1.getValue();
		if (scrnMsg.A.getValidCount() == 0 || radioValue == null || radioValue.intValue() < 0) {
			//ZZM9000E=0,[@] field is mandatory.
			scrnMsg.xxRadioBtn_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Line selection"});
		}
		scrnMsg.addCheckItem( scrnMsg.xxRadioBtn_H1 );
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

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		BigDecimal radioValue = scrnMsg.xxRadioBtn_H1.getValue();
		if (scrnMsg.A.getValidCount() > 0 && radioValue != null && radioValue.intValue() >= 0) {
			for (int i = 0; i < scrnMsg.A.getValidCount(); i ++) {
				if (i == radioValue.intValue()) {
			        Object[] params = new Object[1];
			        params[0] = scrnMsg.A.no(i).mdseCstUpdHistHdrPk_A1;
			        setArgForSubScreen(params);
			        break;
				}
			}
		}
	}

}
