package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7120.common.NMAL7120CommonLogic;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 09/23/2016   Hitachi         Y.Takeno        Update          QC#13083
 *</pre>
 */
public class NMAL7120Scrn00_Acct_Link extends S21CommonHandler implements NMAL7120Constant {

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
		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue(scrnMsg.dsAcctNum_H1.getValue());
		scrnMsg.P.no(1).xxPopPrm.setValue(scrnMsg.dsAcctNm_H1.getValue());
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
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL7120Scrn00_Acct_Link");
// 09/23/2016 QC#13083 Mod Start
//		Object[] params = NMAL7120CommonLogic.toArray_popup(scrnMsg.P, 15);
        Object[] params = NMAL7120CommonLogic.createArgumentNMAL6760(scrnMsg.P, 15);
// 09/23/2016 QC#13083 Mod End
		setArgForSubScreen(params);

	}

}
