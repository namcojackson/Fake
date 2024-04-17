/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZBL0010Scrn00_CMN_Clear extends S21CommonHandler implements ZZBL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
		
		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
		
		scrnMsg.batProcLogPk.clear();
		scrnMsg.glblCmpyCd.clear();
		scrnMsg.batProcJbntId.clear();
		scrnMsg.batProcJobId.clear();
		scrnMsg.batProcSubSysCd.clear();
		scrnMsg.batProcPgmId.clear();
		scrnMsg.batProcTrmCd.setValue(scrnMsg.batProcTrmCd_DP.no(0).getValue());
		scrnMsg.effFromDt.clear();
		scrnMsg.effToDt.clear();
		
        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        
        // Color Settings
        S21TableColorController tblColor = new S21TableColorController("ZZBL0010Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);        

        scrnMsg.setFocusItem( scrnMsg.batProcLogPk );
		
	}
}