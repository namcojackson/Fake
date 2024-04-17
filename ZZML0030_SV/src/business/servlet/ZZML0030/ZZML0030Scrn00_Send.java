/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 * 2020/03/03   Fujitsu         K.takahama      Update          QC#56127
 *</pre>
 */
package business.servlet.ZZML0030;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZML0030.ZZML0030CMsg;
import business.servlet.ZZML0030.common.ZZML0030CommonLogic;
import business.servlet.ZZML0030.constant.ZZML0030Constant;

import com.canon.cusa.s21.framework.mail.MailDefinition;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0030Scrn00_Send extends S21CommonHandler implements ZZML0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.mlSendStsCd_H );
        scrnMsg.addCheckItem( scrnMsg.mlSubjTxt_H );
        scrnMsg.addCheckItem( scrnMsg.mlUsrAddr_H );
        // 03/03/2020 Mod QC#56127 Start
        scrnMsg.addCheckItem( scrnMsg.mlAddrTpCd_T );
        scrnMsg.addCheckItem( scrnMsg.mlUsrAddr_T );
        // 03/03/2020 Mod QC#56127 End
        scrnMsg.addCheckItem( scrnMsg.xxFromDt_H );
        scrnMsg.addCheckItem( scrnMsg.xxToDt_H );
        scrnMsg.addCheckItem( scrnMsg.xxHrs_F );
        scrnMsg.addCheckItem( scrnMsg.xxHrs_T );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;

		ZZML0030CMsg bizMsg = new ZZML0030CMsg();
		bizMsg.setBusinessID("ZZML0030");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // selected row number
        int selected =  getButtonSelectNumber();
        bizMsg.xxRowNum.setValue(selected);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;
		ZZML0030CMsg bizMsg  = (ZZML0030CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZML0030CommonLogic.setTableColor( scrnMsg );

        // button disable
        boolean btnSts = false;
        for ( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
            
            String mlSendSts = scrnMsg.A.no( i ).xxScrItem7Txt_A.getValue();
            if ( MailDefinition.ML_STS_WAIT.equals( mlSendSts ) ) {
                btnSts = false;
            } else {
                btnSts = true;
            }
            setButtonEnabled( "Send", i, btnSts );
        }

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.mlSendStsCd_H );
        scrnMsg.addCheckItem( scrnMsg.mlSubjTxt_H );
        scrnMsg.addCheckItem( scrnMsg.mlUsrAddr_H );
        // 03/03/2020 Mod QC#56127 Start
        scrnMsg.addCheckItem( scrnMsg.mlAddrTpCd_T );
        scrnMsg.addCheckItem( scrnMsg.mlUsrAddr_T );
        // 03/03/2020 Mod QC#56127 End
        scrnMsg.addCheckItem( scrnMsg.xxFromDt_H );
        scrnMsg.addCheckItem( scrnMsg.xxToDt_H );
        scrnMsg.addCheckItem( scrnMsg.xxHrs_F );
        scrnMsg.addCheckItem( scrnMsg.xxHrs_T );
        scrnMsg.putErrorScreen();

	}

}
