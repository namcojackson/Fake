package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL7120_NMAL6760 extends S21CommonHandler implements NMAL7120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
		
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			//Account Customer Number
			if ("NMAL7120Scrn00_LineCustNum".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).dsAcctNum_A1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.A.no(row).dsAcctNm_A1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).dsAcctNum_A1);
			} else if ("NMAL7120Scrn00_Acct_Link".equals(event)) {
				scrnMsg.dsAcctNum_H1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.dsAcctNm_H1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
			}
		}
	}

}
