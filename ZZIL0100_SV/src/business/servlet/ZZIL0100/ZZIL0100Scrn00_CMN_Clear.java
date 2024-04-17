/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0100.ZZIL0100CMsg;
import business.servlet.ZZIL0100.common.ZZIL0100CommonLogic;
import business.servlet.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0100Scrn00_CMN_Clear extends S21CommonHandler {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;

        ZZIL0100CMsg bizMsg = new ZZIL0100CMsg();
        bizMsg.setBusinessID(ZZIL0100Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZIL0100Constant.ReadCode);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;
        ZZIL0100CMsg bizMsg = (ZZIL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        S21SortColumnIMGController.clearIMG("ZZIL0100Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        ZZIL0100CommonLogic.setTableColor(scrnMsg, bizMsg);
        ZZIL0100CommonLogic.setListBox(scrnMsg);
        ZZIL0100CommonLogic.setInitButton(this);

        scrnMsg.xxFromDt_RF.clear();
        scrnMsg.xxFromDt_UF.clear();
        scrnMsg.xxToDt_RT.clear();
        scrnMsg.xxToDt_UT.clear();
        scrnMsg.xxHrs_R1.clear();
        scrnMsg.xxHrs_R2.clear();
        scrnMsg.xxHrs_U1.clear();
        scrnMsg.xxHrs_U2.clear();
        scrnMsg.transactionId.clear();
        scrnMsg.processedFlag_PS.clear();
        
        
        scrnMsg.setFocusItem(scrnMsg.itrlIntfcId);
	}

}