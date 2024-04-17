/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZBL0030.ZZBL0030CMsg;
import business.servlet.ZZBL0030.common.ZZBL0030CommonLogic;
import business.servlet.ZZBL0030.constant.ZZBL0030Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * 
 * @author Q02870
 *
 */
public class ZZBL0030Scrn00_Search extends S21CommonHandler implements ZZBL0030Constant {

   /**
     * Check input.
     * @param ctx the ctx
     * @param bMsg the b msg
     * @see com.canon.cusa.s21.framework.online.servlet.S21CommonHandler#checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */	
	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZBL0030BMsg scrnMsg = (ZZBL0030BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.ezReqBusinessName);
        scrnMsg.addCheckItem(scrnMsg.ezReqInputDate_ST);
        scrnMsg.addCheckItem(scrnMsg.ezReqInputDate_EN);
        scrnMsg.addCheckItem(scrnMsg.ezReqJobStartDate);
	    scrnMsg.addCheckItem(scrnMsg.ezReqJobEndDate);
	    scrnMsg.putErrorScreen();

	    // Check Register Date consistancy.
		if (!scrnMsg.ezReqInputDate_EN.isClear() && !scrnMsg.ezReqInputDate_ST.isClear()) {
			
	        // if effFromDt > effToDt, then error
	        if (compareDateTime(scrnMsg.ezReqInputDate_EN.getValue(), scrnMsg.ezReqInputTime_EN.getValue(),
					            scrnMsg.ezReqInputDate_ST.getValue(), scrnMsg.ezReqInputTime_ST.getValue()) == -1 ) {
	            // This message ID should be changed.
	            scrnMsg.ezReqInputDate_ST.setErrorInfo(1, "ZZSM0410E");
	        }
		}
		
	    // Check Job Start/End Date consistancy.
		if (!scrnMsg.ezReqJobStartDate.isClear() && !scrnMsg.ezReqJobEndDate.isClear()) {
			
	        // if effFromDt > effToDt, then error
			if (compareDateTime(scrnMsg.ezReqJobEndDate.getValue(), scrnMsg.ezReqJobEndTime.getValue(),
		            		    scrnMsg.ezReqJobStartDate.getValue(), scrnMsg.ezReqJobStartTime.getValue()) == -1 ) {
	            // This message ID should be changed.
	            scrnMsg.ezReqJobStartDate.setErrorInfo(1, "ZZSM0410E");
	        }
		}

		scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0030BMsg scrnMsg = (ZZBL0030BMsg) bMsg;
	    
		ZZBL0030CMsg bizMsg = new ZZBL0030CMsg();
		bizMsg.setBusinessID("ZZBL0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0030BMsg scrnMsg = (ZZBL0030BMsg) bMsg;
		ZZBL0030CMsg bizMsg  = (ZZBL0030CMsg) cMsg;
		
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		ZZBL0030CommonLogic.setTableColor( scrnMsg );

        S21SortColumnIMGController.clearIMG("ZZBL0030Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
		
        ZZBL0030CommonLogic.initCommonButton(this, scrnMsg);
	}
	
	
	private int compareDateTime(String yyyyMMdd1, String HHmmss1, String yyyyMMdd2, String HHmmss2) {
		
		int cmpResult = EZDCommonFunc.cmpDate( yyyyMMdd1, yyyyMMdd2 );
		switch( cmpResult ) {
			case 0: // Equal
				return EZDCommonFunc.cmpTime( HHmmss1, HHmmss2 );
			default:
				return cmpResult;
		}
	}

}