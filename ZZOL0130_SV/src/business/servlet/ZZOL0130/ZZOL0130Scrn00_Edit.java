/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0130;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0130.common.ZZOL0130CommonLogic;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0130Scrn00_Edit extends S21CommonHandler implements ZZOL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

		//ZZOL0130CMsg bizMsg = new ZZOL0130CMsg();
		//bizMsg.setBusinessID("ZZOL0130");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
		
		int index = getButtonSelectNumber();

        scrnMsg.delTblConfigPk.setValue(scrnMsg.A.no(index).delTblConfigPk_A1.getValue());
		scrnMsg.delTblId.setValue(scrnMsg.A.no(index).delTblId_A1.getValue());
		
		String specEffCancCd = convEzCancFlagSpecNameToCd(scrnMsg.A.no(index).xxSpecEffCancNm_A1.getValue());
		scrnMsg.specEffCancCd_L.setValue(specEffCancCd);
		scrnMsg.specEffMthAot.setValue(scrnMsg.A.no(index).specEffMthAot_A1.getValue());
		scrnMsg.delTblCmntTxt.setValue(scrnMsg.A.no(index).delTblCmntTxt_A1.getValue());
		scrnMsg.delExecDt.setValue(scrnMsg.A.no(index).delExecDt_A1.getValue());
		scrnMsg.ezUpTime.setValue(scrnMsg.A.no(index).ezUpTime_A1.getValue());
        scrnMsg.ezUpTimeZone.setValue(scrnMsg.A.no(index).ezUpTimeZone_A1.getValue());
        
        ZZOL0130CommonLogic.dspScrn(this, scrnMsg);
        
	}

	private String convEzCancFlagSpecNameToCd(String xxEzCancFlgSpecName){

		if("Cancel".equals(xxEzCancFlgSpecName)){
			return "1";
		}else if("Not Cancel".equals(xxEzCancFlgSpecName)){
			return "0";
		}else{
			return "9";
		}
		
	}
	
}
