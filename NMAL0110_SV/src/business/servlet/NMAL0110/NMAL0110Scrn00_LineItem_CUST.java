package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0110Scrn00_LineItem_CUST extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag

		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue(scrnMsg.E.no(getButtonSelectNumber()).dsAcctNum_E1.getValue());
		scrnMsg.P.no(1).xxPopPrm.setValue(scrnMsg.E.no(getButtonSelectNumber()).dsAcctNm_E1.getValue());
		scrnMsg.P.no(2).xxPopPrm.setValue("");
		scrnMsg.P.no(3).xxPopPrm.setValue("");
		scrnMsg.P.no(4).xxPopPrm.setValue("");
		scrnMsg.P.no(5).xxPopPrm.setValue("");
		scrnMsg.P.no(6).xxPopPrm.setValue("");
		scrnMsg.P.no(7).xxPopPrm.setValue("");
		scrnMsg.P.no(8).xxPopPrm.setValue("");
		scrnMsg.P.no(9).xxPopPrm.setValue("");
		scrnMsg.P.no(10).xxPopPrm.setValue("");
		scrnMsg.P.no(11).xxPopPrm.setValue("");
		scrnMsg.P.no(12).xxPopPrm.setValue("");
		scrnMsg.P.no(13).xxPopPrm.setValue("");
		scrnMsg.P.no(14).xxPopPrm.setValue("");
		scrnMsg.P.no(28).xxPopPrm.setValue(String.valueOf(getButtonSelectNumber()));
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0110Scrn00_LineItem_CUST");
		Object[] params = NMAL0110CommonLogic.toArray_popup(scrnMsg.P, 15);
		setArgForSubScreen(params);

	}

}
