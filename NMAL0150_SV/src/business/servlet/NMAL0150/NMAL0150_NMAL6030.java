package business.servlet.NMAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.constant.NMAL0150Constant;
import business.servlet.NMAL0150.NMAL0150BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0150_NMAL6030 extends S21CommonHandler implements NMAL0150Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
		NMAL0150CMsg bizMsg = new NMAL0150CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0150Scrn00_LineItem".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				bizMsg.A.no(row).mdseCd_A1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				bizMsg.A.no(row).mdseDescShortTxt_A1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
			}
		}
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
		NMAL0150CMsg bizMsg  = (NMAL0150CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0150Scrn00_LineItem".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).mdseCd_A1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.A.no(row).mdseDescShortTxt_A1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).mdseCd_A1);
			}
		}
	}

}
