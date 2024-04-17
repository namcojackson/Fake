package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_CLOSE;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0100.NMAL0100CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0100_NMAL6040 extends S21CommonHandler   {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		NMAL0100CMsg bizMsg = new NMAL0100CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
		NMAL0100CMsg bizMsg  = (NMAL0100CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		if (!CMN_CLOSE.equals(getLastGuard())) {
			ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlCd_H1, toUpperCase(scrnMsg.P.no(14).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlNm_H1, toUpperCase(scrnMsg.P.no(15).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlCd_H1, toUpperCase(scrnMsg.P.no(16).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlNm_H1, toUpperCase(scrnMsg.P.no(17).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlCd_H1, toUpperCase(scrnMsg.P.no(18).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlNm_H1, toUpperCase(scrnMsg.P.no(19).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlCd_H1, toUpperCase(scrnMsg.P.no(20).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlNm_H1, toUpperCase(scrnMsg.P.no(21).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlCd_H1, toUpperCase(scrnMsg.P.no(22).xxPopPrm.getValue()));
			ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlNm_H1, toUpperCase(scrnMsg.P.no(23).xxPopPrm.getValue()));
		}
		scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);

	}
	
	private String toUpperCase(String arg) {
		if (ZYPCommonFunc.hasValue(arg)) {
			return arg.toUpperCase();
		}
		return arg;
	}
	
}
