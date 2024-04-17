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
 *</pre>
 */
public class NMAL7120Scrn00_LineCoaBrCd extends S21CommonHandler implements NMAL7120Constant {

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
        scrnMsg.P.no(0).xxPopPrm.setValue("COA_BR");
        scrnMsg.P.no(1).xxPopPrm.setValue("COA_BR_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("COA_BR_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("COA_BR_CD");
        scrnMsg.P.no(4).xxPopPrm.setValue("Look Up COA Branch Code");
        scrnMsg.P.no(5).xxPopPrm.setValue("COA Beanch Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("COA Beanch Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("COA Beanch Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("COA Beanch Name");
        scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.A.no(getButtonSelectNumber()).origCoaBrCd_A1.getValue());
        scrnMsg.P.no(10).xxPopPrm.setValue("");
        scrnMsg.P.no(28).xxPopPrm.setValue(String.valueOf(getButtonSelectNumber()));
        scrnMsg.P.no(29).xxPopPrm.setValue("NMAL7120Scrn00_LineCoaBrCd");
        Object[] params = NMAL7120CommonLogic.toArray_popup(scrnMsg.P, 11);
        setArgForSubScreen(params);

	}

}
