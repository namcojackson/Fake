package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140_NWAL1130 extends S21CommonHandler implements NMAL0140Constant {

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
			if ("NMAL0140Scrn00_Sub_WH_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H1, scrnMsg.O.no(2).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, scrnMsg.O.no(3).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_H1);
			} else if ("NMAL0140Scrn00_WH_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.O.no(1).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H1, scrnMsg.O.no(2).xxComnScrColValTxt_O1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, scrnMsg.O.no(3).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.rtlSwhCd_H1);
			}
		}
		
	}

}
