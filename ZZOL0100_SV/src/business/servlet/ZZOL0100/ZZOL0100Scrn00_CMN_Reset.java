/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0100.ZZOL0100CMsg;
import business.servlet.ZZOL0100.common.ZZOL0100CommonLogic;
import business.servlet.ZZOL0100.constant.ZZOL0100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0100Scrn00_CMN_Reset extends S21CommonHandler implements ZZOL0100Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount( 0 );
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();

        ZZOL0100CMsg bizMsg = new ZZOL0100CMsg();
        bizMsg.setBusinessID("ZZOL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;
        ZZOL0100CMsg bizMsg  = (ZZOL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZOL0100CommonLogic.dspScrn(scrnMsg, this);

        scrnMsg.glblCmpyCd.setNameForMessage(GLOBAL_COMPANY_CODE);
        scrnMsg.menuInfoTxt.setNameForMessage(INFORMATION_TEXT);
        scrnMsg.menuInfoSortNum.setNameForMessage(ORDER);
        scrnMsg.menuEffFromDt.setNameForMessage(EFFECTIVE_DATE_FROM);
        scrnMsg.menuEffThruDt.setNameForMessage(EFFECTIVE_DATE_To);
        scrnMsg.menuEffFromDt_P0.setNameForMessage(STANDARD_DATE);
        
        scrnMsg.menuInfoTxt_P1.setInputProtected(true);
        
        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);
        
        S21SortColumnIMGController.clearIMG(SCREEN_NAME, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);
	}

}
