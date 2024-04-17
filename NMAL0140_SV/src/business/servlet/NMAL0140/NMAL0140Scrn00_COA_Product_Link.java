package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.common.NMAL0140CommonLogic;
import business.servlet.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140Scrn00_COA_Product_Link extends S21CommonHandler implements NMAL0140Constant {

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
		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue("COA_PROD");
		scrnMsg.P.no(1).xxPopPrm.setValue("COA_PROD_CD");
		scrnMsg.P.no(2).xxPopPrm.setValue("COA_PROD_NM");
		scrnMsg.P.no(3).xxPopPrm.setValue("COA_PROD_CD");
		scrnMsg.P.no(4).xxPopPrm.setValue("Look Up COA Product Code");
		scrnMsg.P.no(5).xxPopPrm.setValue("COA Product");
		scrnMsg.P.no(6).xxPopPrm.setValue("COA Product Name");
		scrnMsg.P.no(7).xxPopPrm.setValue("COA Product");
		scrnMsg.P.no(8).xxPopPrm.setValue("COA Product Name");
		scrnMsg.P.no(9).xxPopPrm.clear();
		scrnMsg.P.no(10).xxPopPrm.clear();
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0140Scrn00_COA_Product_Link");
		Object[] params = NMAL0140CommonLogic.toArray_popup(scrnMsg.P, 11);
		setArgForSubScreen(params);

	}

}
