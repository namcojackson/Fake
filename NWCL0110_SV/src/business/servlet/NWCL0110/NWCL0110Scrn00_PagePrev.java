package business.servlet.NWCL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0110.NWCL0110CMsg;
import business.servlet.NWCL0110.NWCL0110BMsg;
import business.servlet.NWCL0110.common.NWCL0110CommonLogic;
import business.servlet.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWCL0110Scrn00_PagePrev extends S21CommonHandler {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        NWCL0110CommonLogic.checkInput_Submit(ctx, scrnMsg);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;

		NWCL0110CMsg bizMsg = new NWCL0110CMsg();
		bizMsg.setBusinessID(NWCL0110Constant.BIZ_ID);
		bizMsg.setFunctionCode(NWCL0110Constant.FUNCTION_SEARCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
		NWCL0110CMsg bizMsg  = (NWCL0110CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0110CommonLogic.setTblBackColor(scrnMsg);
        NWCL0110CommonLogic.controlDtl(this, scrnMsg);
        // clear sort icons
        S21SortColumnIMGController.clearIMG(NWCL0110Constant.SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
		scrnMsg.setFocusItem(scrnMsg.xxTpCd);
	}

}
