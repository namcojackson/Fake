/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0020;

import parts.common.*;
import parts.servletcommon.*;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZBL0020Scrn00_CMN_Clear extends S21CommonHandler implements ZZBL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;
		
		scrnMsg.ezReqBusinessID.clear();
		scrnMsg.ezReqJobCtlNetID.clear();

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        // Color Settings
        S21TableColorController tblColor = new S21TableColorController("ZZBL0020Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
	}
}