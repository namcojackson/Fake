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
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0030Scrn00_Search extends S21CommonHandler implements ZZML0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
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

        // get Mail Send Date From & Mail Send Time From
        String updatedDateFrom = scrnMsg.xxFromDt_H.getValue();
        String updatedTimeFrom = scrnMsg.xxHrs_F.getValue();
        
        // get Mail Send Date To & Mail Send Time To
        String updatedDateTo = scrnMsg.xxToDt_H.getValue();
        String updatedTimeTo = scrnMsg.xxHrs_T.getValue();

        // correlative check
        if ( updatedDateFrom.length() > 0 && updatedDateTo.length() > 0 ) {
            if ( updatedTimeFrom.length() == 0 ) {
                updatedTimeFrom = "00";
            }
            if ( updatedTimeTo.length() == 0 ) {
                updatedTimeTo = "00";
            }
            if ( ( updatedDateFrom ).compareTo( updatedDateTo ) > 0 ) {
                scrnMsg.xxFromDt_H.setErrorInfo( 1, "ZZZM9010E", new String[] { "Mail Send Date(From)", "Mail Send Date(To)" } );
            } else if (( updatedDateFrom ).compareTo( updatedDateTo ) == 0) {
                if ( updatedTimeFrom.compareTo( updatedTimeTo ) > 0 ) {
                    scrnMsg.xxHrs_F.setErrorInfo( 1, "ZZZM9010E", new String[] { "Mail Send Time(From)", "Mail Send Time(To)" } );
                }
            }
        } else {
            if ( updatedTimeFrom.length() > 0 && updatedTimeTo.length() > 0 ) {
                if ( updatedTimeFrom.compareTo( updatedTimeTo ) > 0 ) {
                    scrnMsg.xxHrs_F.setErrorInfo( 1, "ZZZM9010E", new String[] { "Mail Send Time(From)", "Mail Send Time(To)" } );
                }
            }
        }

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount( 0 );
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        if ( scrnMsg.xxFromDt_H.isError() || scrnMsg.xxHrs_F.isError() ) {
            return null;
        }
        
		ZZML0030CMsg bizMsg = new ZZML0030CMsg();
		bizMsg.setBusinessID("ZZML0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;
		ZZML0030CMsg bizMsg  = (ZZML0030CMsg) cMsg;

        if ( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        ZZML0030CommonLogic.setTableColor( scrnMsg );
        S21SortColumnIMGController.clearIMG(screenId, scrnMsg, scrnMsg.A.no(0).getBaseContents());

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
