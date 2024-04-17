package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
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
public class NMAL0140_NMAL6050 extends S21CommonHandler implements NMAL0140Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NMAL0140Scrn00_COA_Product_Link".equals(event)) {
				scrnMsg.coaProdCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.coaProdNm_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);
			} else if ("NMAL0140Scrn00_WH_Link".equals(event)) {
				scrnMsg.rtlWhCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.rtlWhNm_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
			} else if ("NMAL0140Scrn00_Sub_WH_Link".equals(event)) {
				scrnMsg.rtlSwhCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
				scrnMsg.rtlSwhNm_H1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_H1);
			}
		}
		
	}

}
