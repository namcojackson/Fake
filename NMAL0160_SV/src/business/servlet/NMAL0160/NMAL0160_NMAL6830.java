package business.servlet.NMAL0160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0160.constant.NMAL0160Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0160_NMAL6830 extends S21CommonHandler implements NMAL0160Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0160BMsg scrnMsg = (NMAL0160BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0160Scrn00_CostUpdGrp_Link".equals(event)) {
				scrnMsg.mdseCstUpdTpCd_H1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.mdseCstUpdGrpTxt_H1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.mdseCstUpdGrpTxt_H1);
			}
		}
	}

}
