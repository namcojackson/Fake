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

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.workflow.ezd.servlet.S21EZDWfParameter;

public class NYEL0010Scrn00_JumpToMyProcess extends S21CommonHandler implements NYEL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		// Added on 4/13/10
		// flush the current workflow parameter
		// Once a page transition "menu > global worklist > biz app page" happens,
		// S21EZDWfParameter object is created and the latest work item information
		// will be kept. This causes unexpected application behaviour, so
		// This attribute must flush everytime a transition from menu to biz app happens.
		S21EZDWfParameter ezdWfParameter = new S21EZDWfParameter();
		ctx.setAttribute( "S21EZDWfParameter", null );
		return null;

    }

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

	}

}
