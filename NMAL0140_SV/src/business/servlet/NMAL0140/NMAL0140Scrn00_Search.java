package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0140.NMAL0140CMsg;
import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.common.NMAL0140CommonLogic;
import business.servlet.NMAL0140.constant.NMAL0140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL0140Scrn00_Search extends S21CommonHandler implements NMAL0140Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		
		//QC#12585
		//WH and SWH is mandatory
		if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_H1)) {
			//NMAM8640E=0,@ is required, if @ is entered.
			scrnMsg.rtlWhCd_H1.setErrorInfo(1, "NMAM8640E", new String[]{"Sub Warehouse", "Warehouse"});
			scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
		}
		
		if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1) && ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_H1)) {
			//NMAM8640E=0,@ is required, if @ is entered.
			scrnMsg.rtlSwhCd_H1.setErrorInfo(1, "NMAM8640E", new String[]{"Warehouse", "Sub Warehouse"});
			scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
		}
		scrnMsg.putErrorScreen();
		
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		NMAL0140CommonLogic.setPage(scrnMsg, 1);
		NMAL0140CMsg bizMsg = new NMAL0140CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		NMAL0140CMsg bizMsg  = (NMAL0140CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0140CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		
	}

}
