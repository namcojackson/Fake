/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0060;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0060.ZZZL0060CMsg;
import business.servlet.ZZZL0060.common.ZZZL0060CommonLogic;
import business.servlet.ZZZL0060.constant.ZZZL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0060Scrn00_CMN_Submit extends S21CommonHandler implements ZZZL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.batProcJobId);
        for (int i=0; i< scrnMsg.A.getValidCount(); i++) {
           
            scrnMsg.addCheckItem(scrnMsg.A.no(i).batProcJobId_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).batTblNm_A);
            if(ZZZL0060CommonLogic.isEmpty(scrnMsg.A.no(i).batTblNm_A.getValue())) {
                scrnMsg.A.no(i).batTblNm_A.setErrorInfo(1, "ZZZM9025E", new String[]{"Batch TBL Name"});
            }
        }
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;

		ZZZL0060CMsg bizMsg = new ZZZL0060CMsg();
		bizMsg.setBusinessID("ZZZL0060");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
		ZZZL0060CMsg bizMsg  = (ZZZL0060CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if ("E".equals(bizMsg.getMessageKind())) { 
            for (int i=0; i< scrnMsg.A.getValidCount(); i++) {               
                scrnMsg.addCheckItem(scrnMsg.A.no(i).batProcJobId_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).batTblNm_A);
            }
            scrnMsg.putErrorScreen();
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).batProcJobId_A.setInputProtected(true);
                scrnMsg.A.no(i).batTblNm_A.setInputProtected(true);
            }
            scrnMsg.batProcJobId.setInputProtected(false);
            ZZZL0060CommonLogic.searchCommonButton(this);
        }
	}

}
