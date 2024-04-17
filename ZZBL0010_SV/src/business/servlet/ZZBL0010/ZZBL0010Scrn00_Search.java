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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZBL0010.ZZBL0010CMsg;
import business.servlet.ZZBL0010.common.ZZBL0010CommonLogic;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * The Class ZZBL0010Scrn00_Search is a process class to serach users.
 * @author $ $
 * @version $ $
 */
public class ZZBL0010Scrn00_Search extends S21CommonHandler implements ZZBL0010Constant {

    /**
     * Check input.
     * @param ctx the ctx
     * @param bMsg the b msg
     * @see com.canon.cusa.s21.framework.online.servlet.S21CommonHandler#checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */	
	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		
		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
	              
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effToDt);
        
        scrnMsg.addCheckItem(scrnMsg.batProcLogPk);
        scrnMsg.addCheckItem(scrnMsg.batProcSubSysCd);
        scrnMsg.addCheckItem(scrnMsg.batProcJbntId);
        scrnMsg.addCheckItem(scrnMsg.batProcJobId);
        scrnMsg.addCheckItem(scrnMsg.batProcPgmId);

        scrnMsg.putErrorScreen();
        
		if (!scrnMsg.effFromDt.isClear() && !scrnMsg.effToDt.isClear()) {
			
			String fromDate = scrnMsg.effFromDt.getValue();
			String toDate= scrnMsg.effToDt.getValue();
			
	        // if effFromDt > effToDt, then error
	        if (ZYPDateUtil.compare(toDate,fromDate) == -1 ) {
	            // This message ID should be changed.
	            scrnMsg.effFromDt.setErrorInfo(1, "ZZSM4100E");
	            scrnMsg.addCheckItem(scrnMsg.effFromDt);
	        }
		}
	        scrnMsg.putErrorScreen();  
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;

		// set values to items of pagenation for next page pagenation
	    scrnMsg.A.clear();
	    scrnMsg.A.setValidCount( 0 );
	    scrnMsg.xxPageShowFromNum.clear();
	    scrnMsg.xxPageShowToNum.clear();
	    scrnMsg.xxPageShowOfNum.clear();
	    
		ZZBL0010CMsg bizMsg = new ZZBL0010CMsg();
		bizMsg.setBusinessID("ZZBL0010");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
		ZZBL0010CMsg bizMsg  = (ZZBL0010CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
		ZZBL0010CommonLogic.setTableColor( scrnMsg );
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();  
	}
}