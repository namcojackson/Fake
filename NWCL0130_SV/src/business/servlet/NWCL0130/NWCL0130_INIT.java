package business.servlet.NWCL0130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0130.NWCL0130CMsg;
import business.servlet.NWCL0130.NWCL0130BMsg;
import business.servlet.NWCL0130.common.NWCL0130CommonLogic;
import business.servlet.NWCL0130.constant.NWCL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/04/2016   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWCL0130_INIT extends S21INITCommonHandler implements NWCL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		checkBusinessAppGranted( getContextUserInfo().getUserId(), BUSINESS_ID);
		
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;
 		NWCL0130CMsg bizMsg = new NWCL0130CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;
		NWCL0130CMsg bizMsg  = (NWCL0130CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NWCL0130CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.setFocusItem(scrnMsg.conslBillNum_H1);
		
	}

	@Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    	
        NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.conslBillNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Bill#"));
        scrnMsg.invNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Inv#"));
	}

}
