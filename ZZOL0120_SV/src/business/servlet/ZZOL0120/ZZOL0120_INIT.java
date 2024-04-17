/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0120;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.servlet.ZZOL0120.common.ZZOL0120CommonLogic;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0120_INIT extends S21CommonHandler implements ZZOL0120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	  //security violation check of this screen.
      checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;

		ZZOL0120CMsg bizMsg = new ZZOL0120CMsg();
		bizMsg.setBusinessID("ZZOL0120");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
		ZZOL0120CMsg bizMsg  = (ZZOL0120CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZOL0120CommonLogic.dspScrn00(scrnMsg, this);

        scrnMsg.glblCmpyCd.setNameForMessage(GLOBAL_COMPANY_CODE);
        scrnMsg.menuProcGrpNm.setNameForMessage(PROCESS_GROUP_NAME);
        scrnMsg.menuProcGrpCd.setNameForMessage(GROUP_CD);
        scrnMsg.menuProcGrpSortNum.setNameForMessage(ORDER);
        scrnMsg.menuProcGrpDescTxt.setNameForMessage(DESCRIPTION);
        scrnMsg.wfAppNm.setNameForMessage(EAR_NAME);
        
        scrnMsg.menuProcNm_B1.setNameForMessage(PROCESS_NAME);
        scrnMsg.othSysUrl_B1.setNameForMessage(URL);

        scrnMsg.upTabNm_C1.setNameForMessage(UP_TAB_NAME);
        scrnMsg.upTabSortNum_C1.setNameForMessage(ORDER);
        scrnMsg.bizAppNm_C1.setNameForMessage(FUNCTION_LIST_NAME);
        scrnMsg.bizAppId_C1.setNameForMessage(APPRICATION_ID);
        
        scrnMsg.setFocusItem(scrnMsg.menuProcGrpNm);
        
        S21SortColumnIMGController.clearIMG(SCREEN_NAME_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
	}

}