/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NYEL0050;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.transitioncommon.EZDTransition;
import business.servlet.NYEL0050.constant.NYEL0050Constant;

import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfoConstant;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NYEL0050Scrn00_SelectBizId extends S21CommonHandler implements NYEL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 		
 		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NYEL0050BMsg scrnMsg = (NYEL0050BMsg) bMsg;
        S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();
        int index = getButtonSelectNumber();
        String bizAppId = "";
        String processId  = "";
        String processGroupId = "";
        
        bizAppId = scrnMsg.A.no(index).bizAppId.getValue();
        processId = S21BusinessProcessInfoConstant.CD_TBL_PROCESS;
        S21InfoLogOutput.println("selected bizApp Id is :"+bizAppId);
        s21Info.setBusinessId(bizAppId);
        s21Info.setProcessId(processId);            
        ctx.setAttribute("SelectedS21ProcessInfo", s21Info );
        ctx.setAttribute("businessProcessInfo", null );
        ctx.setAttribute("SelectedProcessGroupId", processGroupId );
        setNextTransition(EZDTransition.OVERRIDE, bizAppId);
	}

}
