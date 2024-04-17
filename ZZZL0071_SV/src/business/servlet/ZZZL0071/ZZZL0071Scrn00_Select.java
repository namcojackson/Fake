/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0071;


import java.io.Serializable;

import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZZL0071.ZZZL0071CMsg;

import business.servlet.ZZZL0070.ZZZL0070BMsg;
import business.servlet.ZZZL0071.constant.ZZZL0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0071Scrn00_Select extends S21CommonHandler implements ZZZL0071Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;
		ZZZL0071CMsg bizMsg = new ZZZL0071CMsg();
		bizMsg.setBusinessID("ZZZL0071");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
        if (bizMsg.xxPageShowFromNum.getValueInt() != 0) {
            bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - 1);
        }
 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0071CMsg bizMsg = (ZZZL0071CMsg) cMsg;

        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;
                if (params[1] instanceof ZZZL0070BMsg) {
                    ZZZL0070BMsg newMsg = (ZZZL0070BMsg) params[1];

                    int maxRec = newMsg.A.getValidCount();
                    for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                        if (maxRec == newMsg.A.length()) {
                            maxRec = newMsg.A.length() + 1;
                            break;
                        }

                        if (duplicateCheck(bizMsg.B.no(i).batProcJobId_B.getValue(), newMsg.A)) {
                            newMsg.A.no(maxRec).batProcJobId_A.setValue(bizMsg.B.no(i).batProcJobId_B.getValue());
                            newMsg.A.no(maxRec).batProcPgmId_A.setValue(bizMsg.B.no(i).batProcPgmId_B.getValue());
                            maxRec++;
                        }
                    }
                    newMsg.A.setValidCount(maxRec);
                }
            }
        }
        return;
	}
    
    public static boolean duplicateCheck(String event, EZDBMsgArray newMsg) {
        int cnt = newMsg.getValidCount();
        for (int i = 0; i < cnt; i++) {
            if (newMsg.get(i).getValueString("batProcJobId_A", -1).equals(event)) {
                return false;
            }
        }
        return true;
    } 

}
