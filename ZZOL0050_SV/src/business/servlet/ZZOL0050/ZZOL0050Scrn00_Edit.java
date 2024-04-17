/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0050.ZZOL0050CMsg;
import business.servlet.ZZOL0050.constant.ZZOL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0050Scrn00_Edit extends S21CommonHandler implements ZZOL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;
//
//        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
//        scrnMsg.addCheckItem( scrnMsg.upldCsvId );
//        scrnMsg.addCheckItem( scrnMsg.upldCsvNm );
//        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;

        // selected row number
        int selected =  getButtonSelectNumber();

        Object[] params = new Object[2];
        params[0] = scrnMsg.glblCmpyCd_H.getValue();
        params[1] = scrnMsg.A.no(selected).upldCsvId_A.getValue();
        setArgForSubScreen(params);
	}

}
