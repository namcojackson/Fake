/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0110.constant.ZZOL0110Constant;

import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0110Scrn00_SelectAppL extends S21CommonHandler implements ZZOL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        ZZOL0110BMsg scrnMsg = (ZZOL0110BMsg) bMsg;
        S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();
        
        int index = getButtonSelectNumber();
        String bizId = "";
        String processId = "";
        bizId = scrnMsg.A.no(index).bizAppId_A1.getValue();
        processId = scrnMsg.A.no(index).wfAppNm_A1.getValue();


        s21Info.setBusinessId(bizId);
        s21Info.setProcessId(processId);            
        ctx.setAttribute("SelectedS21ProcessInfo", s21Info );
        ctx.setAttribute("businessProcessInfo", null );
        setJumpTransition(bizId);

	}

}
