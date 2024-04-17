package business.servlet.NMAL0160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0160.NMAL0160BMsg;
import business.servlet.NMAL0160.common.NMAL0160CommonLogic;
import business.servlet.NMAL0160.constant.NMAL0160Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0160Scrn00_CostUpdGrp_Link extends S21CommonHandler implements NMAL0160Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0160BMsg scrnMsg = (NMAL0160BMsg) bMsg;
		ZYPTableUtil.clear(scrnMsg.P);
		scrnMsg.P.no(0).xxPopPrm.setValue(scrnMsg.mdseCstUpdTpCd_H1.getValue());
		scrnMsg.P.no(1).xxPopPrm.setValue(scrnMsg.mdseCstUpdGrpTxt_H1.getValue());
		scrnMsg.P.no(2).xxPopPrm.setValue(MDSE_CST_UPD_STS.PENDING);
		scrnMsg.P.no(28).xxPopPrm.setValue(String.valueOf(getButtonSelectNumber()));
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0160Scrn00_CostUpdGrp_Link");
		Object[] params = NMAL0160CommonLogic.toArray_popup(scrnMsg.P, 11);
		setArgForSubScreen(params);
	}

}
