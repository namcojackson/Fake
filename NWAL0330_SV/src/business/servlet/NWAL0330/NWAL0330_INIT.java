/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/13   Fujitsu         S.Yamamoto      Create          N/A
 *</pre>
 */
package business.servlet.NWAL0330;


import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0330.NWAL0330CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NWAL0330_INIT extends S21INITCommonHandler {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0330BMsg scrnMsg = (NWAL0330BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        // 前画面からの情報取得
        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.billToCustCd.setValue(param0.getValue()); // Payer Code
        }
        
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "Payer Code[" + scrnMsg.billToCustCd.getValue() + "]", this);
        }
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWAL0330BMsg scrnMsg = (NWAL0330BMsg) bMsg;

		NWAL0330CMsg bizMsg = new NWAL0330CMsg();
		bizMsg.setBusinessID("NWAL0330");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0330BMsg scrnMsg = (NWAL0330BMsg) bMsg;
        NWAL0330CMsg bizMsg = (NWAL0330CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 【共通ボタン】
        setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);     // Clear
        setButtonProperties("btn10", "CMN_Close", "Close", 1, null);    // Close
	}

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
    }
}
