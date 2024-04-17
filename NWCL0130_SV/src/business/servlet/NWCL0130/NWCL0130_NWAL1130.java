package business.servlet.NWCL0130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWCL0130.NWCL0130BMsg;
import business.servlet.NWCL0130.constant.NWCL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWCL0130_NWAL1130 extends S21CommonHandler implements NWCL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NWCL0130Scrn00_Bill_Num_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.conslBillNum_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.conslBillNum_H1);
			} else if ("NWCL0130Scrn00_Inv_Num_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.invNum_H1);
			}
		}
		
	}

}
