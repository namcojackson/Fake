/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL6460;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6460.NMAL6460CMsg;
import business.servlet.NMAL6460.common.NMAL6460CommonLogic;
import business.servlet.NMAL6460.constant.NMAL6460Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6460Scrn00_SearchPaymentTermName extends S21CommonHandler implements NMAL6460Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
        
        int idx = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).pmtTermCd_A1);
        if (!hasValue(scrnMsg.A.no(idx).pmtTermCd_A1)) {
            scrnMsg.A.no(idx).pmtTermCd_A1.setErrorInfo(1
                                                    , MESSAGE_ID.ZZM9000E.toString()
                                                    , new String[]{NMAL6460Bean.pmtTermCd_A1});
        }
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
		scrnMsg.xxListNum.setValue(getButtonSelectNumber());
		NMAL6460CMsg bizMsg = new NMAL6460CMsg();
		bizMsg.setBusinessID("NMAL6460");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
		NMAL6460CMsg bizMsg  = (NMAL6460CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6460CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        scrnMsg.addCheckItem(scrnMsg.A.no(getButtonSelectNumber()).pmtTermCd_A1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).cashDiscTermCd_A1);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

	}

}
