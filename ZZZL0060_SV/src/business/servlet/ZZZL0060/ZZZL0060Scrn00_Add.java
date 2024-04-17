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
import business.servlet.ZZZL0060.constant.ZZZL0060Constant;
import business.servlet.ZZZL0060.common.ZZZL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0060Scrn00_Add extends S21CommonHandler implements ZZZL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.batProcJobId);
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;

        if (scrnMsg.batProcJobId.isInputProtected()) {
            return null;
        }
        ZZZL0060CMsg bizMsg = new ZZZL0060CMsg();
        bizMsg.setBusinessID("ZZZL0060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;

        if (cMsg != null) {
            ZZZL0060CMsg bizMsg = (ZZZL0060CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        
        int row = scrnMsg.A.getValidCount();
        if (row >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo("ZZZM9011E", new String[] { Integer.toString(scrnMsg.A.length())  });
            return;
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZZZL0060CommonLogic.isEmpty(scrnMsg.A.no(i).ezUpTime_A.getValue())) {
                scrnMsg.A.no(i).batProcJobId_A.setInputProtected(true);
                scrnMsg.A.no(i).batTblNm_A.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).batProcJobId_A.setInputProtected(true);
                scrnMsg.A.no(i).batTblNm_A.setInputProtected(true);
            }
        }
        
        scrnMsg.A.no(row).clear();
        scrnMsg.A.no(row).batProcJobId_A.setNameForMessage("Job ID");
        scrnMsg.A.no(row).batProcJobId_A.setInputProtected(true);
        scrnMsg.A.no(row).batTblNm_A.setInputProtected(false);
        scrnMsg.A.no(row).batProcJobId_A.setValue(scrnMsg.batProcJobId.getValue());
        scrnMsg.A.setValidCount(row + 1 );
        
        scrnMsg.batProcJobId.setInputProtected(true);
        
        ZZZL0060CommonLogic.addCommonButton(this);
	}
}
