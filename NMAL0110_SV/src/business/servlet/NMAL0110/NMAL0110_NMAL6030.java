package business.servlet.NMAL0110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import business.servlet.NMAL0110.NMAL0110BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 * 07/03/2015   Fujitsu         C.Tanaka        Update
 *</pre>
 */
public class NMAL0110_NMAL6030 extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		NMAL0110CMsg bizMsg = new NMAL0110CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		NMAL0110CMsg bizMsg  = (NMAL0110CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
		
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			//Image Ware Item
			if ("NMAL0110Scrn00_Imageware_Item_Link".equals(event)) {
				scrnMsg.iwrMdseCd_H1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.mdseDescShortTxt_IW.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.iwrMdseCd_H1);
			//SUPD Item
			} else if ("NMAL0110Scrn00_LineItem_SUPD".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.C.no(row).supdToMdseCd_C1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.C.no(row).mdseDescShortTxt_C1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.C.no(row).supdToMdseCd_C1);
			// Relation Item
			} else if ("NMAL0110Scrn00_LineItem_RELN_MDSE".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.D.no(row).relnMdseCd_D1.setValue(scrnMsg.P.no(0).xxPopPrm.getValue());
				scrnMsg.D.no(row).mdseDescShortTxt_D1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
				scrnMsg.setFocusItem(scrnMsg.D.no(row).relnMdseCd_D1);
			}
		}
	}

}
