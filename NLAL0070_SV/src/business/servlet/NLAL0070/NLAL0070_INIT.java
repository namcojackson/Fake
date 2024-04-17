/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 * 2013/06/03   Hitachi         T.Tomita        Update          QC1209
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.NLAL0070.NLAL0070CMsg;

import business.servlet.NLAL0070.common.NLAL0070CommonLogic;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NLAL0070_INIT extends S21INITCommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

		NLAL0070CMsg bizMsg = new NLAL0070CMsg();
		bizMsg.setBusinessID("NLAL0070");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
		
        String outGetGlobalCompanyCode = getGlobalCompanyCode();
        scrnMsg.glblCmpyCd_BK.setValue(outGetGlobalCompanyCode);
        scrnMsg.usrId_BK.setValue(getContextUserInfo().getUserId());
        
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
		NLAL0070CMsg bizMsg  = (NLAL0070CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab_BK.setValue(TB_SCHEDULE);
        scrnMsg.xxRadioBtn_A1.setValue(RADIO_MDSE);
        // 2013/06/03 QC1209 T.Tomita Add Start
        NLAL0070CommonLogic.scrnInputProtected(scrnMsg);
        // 2013/06/03 QC1209 T.Tomita Add End
        NLAL0070CommonLogic.cntrlDispScrnItem(this, scrnMsg);
	}
	
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        scrnMsg.invtyLocCd_P1.setNameForMessage("WH");
        scrnMsg.xxOrdDt_F1.setNameForMessage("WH ETA From");
        scrnMsg.xxOrdDt_T1.setNameForMessage("WH ETA To");
        scrnMsg.rwsRefNum_P1.setNameForMessage("Ref#");
        scrnMsg.mdseCd_P1.setNameForMessage("Item#");
        scrnMsg.schdTpCd_P1.setNameForMessage("Sche Type");
        scrnMsg.imptInvNum_P1.setNameForMessage("Invoice#");
        scrnMsg.carrCd_P1.setNameForMessage("Carrier");
        scrnMsg.asnNotRcvFlg_P1.setNameForMessage("ASN Not Received");
        scrnMsg.whInPrtyFlg_P1.setNameForMessage("Priority");
        scrnMsg.imptInvLclFlg_P1.setNameForMessage("LCL");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
        	
        	scrnMsg.A.no(i).whInPrtyFlg_A1.setNameForMessage("Priority");
        	scrnMsg.A.no(i).tempWhInEtaDt_A1.setNameForMessage("TRM ETA Memo");
        	scrnMsg.A.no(i).schdWhInEtaDt_A1.setNameForMessage("WH Schedule ETA");
        	scrnMsg.A.no(i).finalWhInEtaDt_A1.setNameForMessage("Final WH Schedule ETA");
        	scrnMsg.A.no(i).railAvalDt_A1.setNameForMessage("Rail Available Date");
        	scrnMsg.A.no(i).apptTmTxt_A1.setNameForMessage("Appointment Time");
        	scrnMsg.A.no(i).apptDrygCarrCd_A2.setNameForMessage("Appointment Drayage Carrier");
        	scrnMsg.A.no(i).whSchdCmntTxt_A1.setNameForMessage("Comment");

        }
    }

}
