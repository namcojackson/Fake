/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3050;

import static business.servlet.NFCL3050.constant.NFCL3050Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3050.NFCL3050CMsg;
import business.servlet.NFCL3050.common.NFCL3050CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   CSAI            K.Uramori       Create          N/A
 * 2016/05/19   Fujitsu         S.Fujita        Update          QC#7780
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 *</pre>
 */
public class NFCL3050Scrn00_btnRegen extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;

        NFCL3050CommonLogic.isSelected(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;

        NFCL3050CMsg bizMsg = new NFCL3050CMsg();
        bizMsg.setBusinessID("NFCL3050");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;
        NFCL3050CMsg bizMsg  = (NFCL3050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3050CommonLogic.controlScreen(this, getUserProfileService(), scrnMsg);
        // START 2016/07/12 K.Kojima [QC#11049,MOD]
        // NFCL3050CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A,
        // "A");
        NFCL3050CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A);
        // END 2016/07/12 K.Kojima [QC#11049,MOD]
        scrnMsg.xxRadioBtn.setValue(0);
        // Clear Sort Icon
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        // START 2016/05/19 S.Fujita [QC#7780,ADD]
        scrnMsg.A.setCheckParam(new String[] {NFCL3050Bean.xxChkBox_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        // END 2016/05/19 S.Fujita [QC#7780,ADD]
    }
}
