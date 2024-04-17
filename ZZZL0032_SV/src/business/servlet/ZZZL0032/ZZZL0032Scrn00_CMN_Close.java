/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0032;


import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0030.ZZZL0030_ABMsgArray;
import business.servlet.ZZZL0032.common.ZZZL0032CommonLogic;
import business.servlet.ZZZL0032.constant.ZZZL0032Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0032Scrn00_CMN_Close extends S21CommonHandler implements ZZZL0032Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;

		//ZZZL0032CMsg bizMsg = new ZZZL0032CMsg();
		//bizMsg.setBusinessID("ZZZL0032");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;
        
//        ZZZL0032CommonLogic.removeUnCheckRows(scrnMsg);

        //Populating return values 
        Serializable arg = getArgForSubScreen();
        
        if (arg != null) {
            if (arg instanceof Object[]) {
                if (arg instanceof Object[]) {
                    Object[] params = (Object[]) arg;
                    ZZZL0030_ABMsgArray ZZZL0030_A = (ZZZL0030_ABMsgArray)params[1];
                    int startRow = ZZZL0030_A.getValidCount();

                    for (int i=0 ; i < scrnMsg.A.getValidCount(); i++) {
                        if (startRow >= ZZZL0030_A.length()) {
                            break;
                        }
                        if (!scrnMsg.A.no(i).xxChkBox_02.isClear() && ZZZL0032CommonLogic.duplicateCheck(scrnMsg.A.no(i).scrAppId_A.getValue(), ZZZL0030_A)) {
                            ZZZL0030_A.no(startRow).bizId_A.setValue(scrnMsg.A.no(i).bizId_A.getValue());
                            ZZZL0030_A.no(startRow).scrAppId_A.setValue(scrnMsg.A.no(i).scrAppId_A.getValue());
                            startRow++;
                        }
                    }
                    ZZZL0030_A.setValidCount(startRow);
                }
            }
        }
	}
}
