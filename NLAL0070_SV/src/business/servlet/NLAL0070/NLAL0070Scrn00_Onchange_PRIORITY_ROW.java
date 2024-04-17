/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL0070.constant.NLAL0070Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_Onchange_PRIORITY_ROW extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		// do nothing
	}

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
        
        int selectedRowNum = getButtonSelectNumber();

        scrnMsg.A.no(selectedRowNum).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
    }

}
