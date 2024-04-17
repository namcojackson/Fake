/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0030;

import parts.common.*;
import parts.servletcommon.*;
import business.servlet.ZZBL0030.common.ZZBL0030CommonLogic;
import business.servlet.ZZBL0030.constant.ZZBL0030Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZBL0030Scrn00_CMN_Clear extends S21CommonHandler implements ZZBL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0030BMsg scrnMsg = (ZZBL0030BMsg) bMsg;

		scrnMsg.ezReqBusinessName.clear();
		scrnMsg.ezReqInputDate_EN.clear();
		scrnMsg.ezReqInputDate_ST.clear();
		scrnMsg.ezReqJobStartDate.clear();
		scrnMsg.ezReqJobEndDate.clear();
		
		scrnMsg.ezReqJobStatus.setValue(scrnMsg.ezReqJobStatus_DP.no(0).getValue());
		scrnMsg.ezReqInputTime_ST.setValue(scrnMsg.xxHrsMn_P1.no(0).getValue());
		scrnMsg.ezReqInputTime_EN.setValue(scrnMsg.xxHrsMn_P2.no(0).getValue());
		scrnMsg.ezReqJobStartTime.setValue(scrnMsg.xxHrsMn_P3.no(0).getValue());
		scrnMsg.ezReqJobEndTime.setValue(scrnMsg.xxHrsMn_P4.no(0).getValue());

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        // Color Settings
        S21TableColorController tblColor = new S21TableColorController("ZZBL0030Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        ZZBL0030CommonLogic.initCommonButton(this, scrnMsg);
	}
}
