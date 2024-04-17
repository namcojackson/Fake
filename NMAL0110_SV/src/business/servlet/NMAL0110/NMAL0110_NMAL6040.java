package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import business.servlet.NMAL0110.NMAL0110BMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110_NMAL6040 extends S21CommonHandler implements NMAL0110Constant {

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
		
		if (!CMN_CLOSE.equals(getLastGuard())) {
			setValue(scrnMsg.zerothProdCtrlCd_H1, scrnMsg.P.no(14).xxPopPrm);
			setValue(scrnMsg.zerothProdCtrlNm_H1, scrnMsg.P.no(15).xxPopPrm);
			setValue(scrnMsg.firstProdCtrlCd_H1, scrnMsg.P.no(16).xxPopPrm);
			setValue(scrnMsg.firstProdCtrlNm_H1, scrnMsg.P.no(17).xxPopPrm);
			setValue(scrnMsg.scdProdCtrlCd_H1, scrnMsg.P.no(18).xxPopPrm);
			setValue(scrnMsg.scdProdCtrlNm_H1, scrnMsg.P.no(19).xxPopPrm);
			setValue(scrnMsg.thirdProdCtrlCd_H1, scrnMsg.P.no(20).xxPopPrm);
			setValue(scrnMsg.thirdProdCtrlNm_H1, scrnMsg.P.no(21).xxPopPrm);
			setValue(scrnMsg.frthProdCtrlCd_H1, scrnMsg.P.no(22).xxPopPrm);
			setValue(scrnMsg.frthProdCtrlNm_H1, scrnMsg.P.no(23).xxPopPrm);
		}
		scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlCd_H1);

	}
	
	private void setValue(EZDBStringItem target, EZDBStringItem result) {
		target.setValue(result.getValue());
	}
}
