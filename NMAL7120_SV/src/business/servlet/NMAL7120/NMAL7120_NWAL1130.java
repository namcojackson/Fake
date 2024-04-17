package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7120.NMAL7120BMsg;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL7120_NWAL1130 extends S21CommonHandler implements NMAL7120Constant {

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
			if ("NMAL7120Scrn00_LinePrcCatgCd".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).prcCatgNm_A1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcCatgNm_A1);
			} else if ("NMAL7120Scrn00_LinePrcContrNum".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).prcContrNum_A1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcContrNum_A1);
			} else if ("NMAL7120Scrn00_Prc_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.prcCatgNm_H1);
			} else if ("NMAL7120Scrn00_Contr_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum_H1, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.prcContrNum_H1);
			} else if ("NMAL7120Scrn00_Contr_Mass_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum_MU, scrnMsg.O.no(0).xxComnScrColValTxt_O1);
				scrnMsg.setFocusItem(scrnMsg.prcContrNum_MU);
			}
		}
		
	}

}
