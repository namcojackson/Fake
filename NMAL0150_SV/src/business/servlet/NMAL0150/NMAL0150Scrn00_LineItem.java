package business.servlet.NMAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0150.common.NMAL0150CommonLogic;
import business.servlet.NMAL0150.constant.NMAL0150Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0150Scrn00_LineItem extends S21CommonHandler implements NMAL0150Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1.getValue());
		scrnMsg.P.no(7).xxPopPrm.setValue(scrnMsg.A.no(getButtonSelectNumber()).mdseDescShortTxt_A1.getValue());
		scrnMsg.P.no(28).xxPopPrm.setValue(String.valueOf(getButtonSelectNumber()));
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0150Scrn00_LineItem");
		Object[] params = NMAL0150CommonLogic.toArray_popup(scrnMsg.P, 11);
		setArgForSubScreen(params);

	}

}
