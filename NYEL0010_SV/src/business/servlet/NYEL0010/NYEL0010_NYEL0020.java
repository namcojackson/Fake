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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL0010.NYEL0010CMsg;
import business.servlet.NYEL0010.common.NYEL0010CommonLogic;
import business.servlet.NYEL0010.constant.NYEL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NYEL0010_NYEL0020 extends S21CommonHandler implements NYEL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;
        NYEL0010CMsg bizMsg = new NYEL0010CMsg();
        bizMsg.setBusinessID("NYEL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;
        NYEL0010CMsg bizMsg  = (NYEL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NYEL0010CommonLogic.setCommonButton(this);
	}

}
