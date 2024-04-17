/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/02   CUSA            Fujitsu         Create          N/A
 * 2013/08/06   Fujitsu         K.Kimura        Update          WDS#1458 Installment Invoice modification
 * 2013/08/13   Fujitsu         K.Kimura        Update          WDS#1554 Multi Exchange Rate modification
 * 2016/05/19   Hitachi         T.Aoyagi        Update          QC#8569
 *</pre>
 */
package business.servlet.NMAL6460;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6460.NMAL6460CMsg;
import business.servlet.NMAL6460.common.NMAL6460CommonLogic;
import business.servlet.NMAL6460.constant.NMAL6460Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6460_INIT extends S21INITCommonHandler implements NMAL6460Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;

		NMAL6460CMsg bizMsg = new NMAL6460CMsg();
		bizMsg.setBusinessID("NMAL6460");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
		NMAL6460CMsg bizMsg  = (NMAL6460CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).pmtTermCashDiscSortNum_A1);
        NMAL6460CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

	}

	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).pmtTermCashDiscSortNum_A1.setNameForMessage("Sort Num");
            scrnMsg.A.no(i).pmtTermCashDiscCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).pmtTermCashDiscNm_A1.setNameForMessage("Name");
            scrnMsg.A.no(i).pmtTermCashDiscDescTxt_A1.setNameForMessage("Description Text");
            scrnMsg.A.no(i).pmtTermCd_A1.setNameForMessage("Payment Term Code");
            scrnMsg.A.no(i).cashDiscTermCd_A1.setNameForMessage("Cash Discount Term");
            scrnMsg.A.no(i).ediCashDiscDueAot_A1.setNameForMessage("EDI Cash Discount Due AOT");
            scrnMsg.A.no(i).ediCashDiscPct_A1.setNameForMessage("EDI Cash Discount Percent");
            scrnMsg.A.no(i).pmtTermSendFaxFlg_A1.setNameForMessage("PT Send Fax Flag");
            scrnMsg.A.no(i).istlPmtTermFlg_A1.setNameForMessage("Instalment PT Flag");
            scrnMsg.A.no(i).actlExchRateTpNum_A1.setNameForMessage("Actual Exchange Rate Type Num");
            // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
            scrnMsg.A.no(i).pmtTermConslDueDay_A1.setNameForMessage("Day of Month");
            scrnMsg.A.no(i).pmtTermConslAddMthNum_A1.setNameForMessage("Month Ahead");
            // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
        }
    }
}
