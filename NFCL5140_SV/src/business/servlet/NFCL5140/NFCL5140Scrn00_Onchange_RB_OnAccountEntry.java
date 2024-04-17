/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 09/29/2010   Fujitsu         I.Kondo         Update          DefID:8207 No.410
 * 10/01/2010   Fujitsu         I.Kondo         Update          DefID:8180 No.421
 * 2018/03/28   Fujitsu         H.Ikeda         Update          QC#21738
 * 2022/11/14   Hitachi			R.Takau			Update			QC#57252
 * </pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_Onchange_RB_OnAccountEntry class.
 */
public class NFCL5140Scrn00_Onchange_RB_OnAccountEntry extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
        // START 2018/03/28 H.Ikeda [QC#21738,MOD]
        //NFCL5140CommonLogic.clearHeaderInfo(scrnMsg);
        scrnMsg.arAdjTpCd_P1.clear();
        // END   2018/03/28 H.Ikeda [QC#21738,MOD]
        scrnMsg.xxInpAmtNum.setInputProtected(false);
        scrnMsg.arCustRefNum.setInputProtected(false);
        //scrnMsg.tocCd.setInputProtected(false);
        //scrnMsg.tocCd_LN.setInputProtected(false);
        //scrnMsg.coaProdCd.setInputProtected(false);
        //scrnMsg.coaProdCd_LN.setInputProtected(false);
        scrnMsg.arAdjTpCd_P1.setInputProtected(true);
        //scrnMsg.xxInvCmntTxt.setInputProtected(false);
        // END   2018/03/28 H.Ikeda [QC#21738,MOD]
        
        // START 2022/11/11 R.Takau [QC#57252,ADD]
    	scrnMsg.xxCmntTxt.setInputProtected(true);
		setButtonEnabled(BTN_A, false);
		scrnMsg.xxCmntTxt.clear();
		// END 2022/11/14 R.Takau [QC#57252,ADD]
		
        this.setButtonProperties("Insert_Record", "Insert_Record", "Insert", 1, null);

        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxModeInd);

    }

}
