/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0010;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NYEL0010.constant.NYEL0010Constant;

import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NYEL0010Scrn00_MyProcessMaintenance extends S21CommonHandler implements NYEL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;
        
        String bizId          = scrnMsg.bizAppId_K2.getValue();
        String processId      = scrnMsg.menuProcId_K2.getValue();
        String processGroupId = "";
        
        S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();
        
        s21Info.setBusinessId(bizId);
        s21Info.setProcessId(processId);            
        ctx.setAttribute("SelectedS21ProcessInfo", s21Info );
        ctx.setAttribute("businessProcessInfo", null );
        ctx.setAttribute("SelectedProcessGroupId", processGroupId );
	}

}
