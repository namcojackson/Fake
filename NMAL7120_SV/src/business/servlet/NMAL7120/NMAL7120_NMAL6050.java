package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import business.servlet.NMAL7120.NMAL7120BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL7120_NMAL6050 extends S21CommonHandler implements NMAL7120Constant {

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
			// COA Branch
			if ("NMAL7120Scrn00_LineCoaBrCd".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).origCoaBrCd_A1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).origCoaBrCd_A1);
			}
		}
	}

}
