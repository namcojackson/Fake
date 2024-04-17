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
public class NMAL0110Scrn00_COG_Link extends S21CommonHandler implements NMAL0110Constant {

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
		scrnMsg.xxRsltFlg_H1.clear();
		scrnMsg.xxRsltFlg_H2.clear();
		scrnMsg.xxRsltFlg_H3.clear();

		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue("COA_ACCT");
		scrnMsg.P.no(1).xxPopPrm.setValue("COA_ACCT_CD");
		scrnMsg.P.no(2).xxPopPrm.setValue("COA_ACCT_NM");
		scrnMsg.P.no(3).xxPopPrm.setValue("COA_ACCT_CD");
		scrnMsg.P.no(4).xxPopPrm.setValue("Look Up Account Code");
		scrnMsg.P.no(5).xxPopPrm.setValue("Account Code");
		scrnMsg.P.no(6).xxPopPrm.setValue("Account Name");
		scrnMsg.P.no(7).xxPopPrm.setValue("Account Code");
		scrnMsg.P.no(8).xxPopPrm.setValue("Account Name");
		scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.cogsCoaAcctCd_H1.getValue());
		scrnMsg.P.no(10).xxPopPrm.setValue(scrnMsg.coaAcctNm_CG.getValue());
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0110Scrn00_COG_Link");
		Object[] params = NMAL0110CommonLogic.toArray_popup(scrnMsg.P, 11);
		setArgForSubScreen(params);

	}

}
