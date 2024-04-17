package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import business.servlet.NMAL0110.NMAL0110BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110_NMAL6760 extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
		
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			//Account Customer Number
			if ("NMAL0110Scrn00_LineItem_CUST".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.E.no(row).dsAcctNum_E1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.E.no(row).dsAcctNm_E1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.E.no(row).dsAcctNum_E1);
			}
		}
	}

}
