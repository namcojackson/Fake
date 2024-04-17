/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 11/11/2022	Hitachi			R.Takau			Update			QC#57252
 * </pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_Onchange_PD_AdjTpCd class.
 */
public class NFCL5140Scrn00_Onchange_PD_AdjTpCd extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        if ("".equals(scrnMsg.arAdjTpCd_P1.getValue())) {
            //scrnMsg.tocCd.clear();
            //scrnMsg.coaProdCd.clear();
            // START 2022/11/11 R.Takau [QC#57252,ADD]
        	scrnMsg.xxCmntTxt.setInputProtected(true);
        	setButtonEnabled(BTN_A, false);
        	// END 2022/11/11 R.Takau [QC#57252,ADD]
            return null;
        // START 2022/11/11 R.Takau [QC#57252,ADD]
        } else if (SLC_OTHER.equals(scrnMsg.arAdjTpCd_P1.getValue())){
        	scrnMsg.xxCmntTxt.setInputProtected(false);
        	setButtonEnabled(BTN_A, true);
        } else {
        	scrnMsg.xxCmntTxt.setInputProtected(true);
        	setButtonEnabled(BTN_A, false);
        	scrnMsg.xxCmntTxt.clear();
        }
        // END 2022/11/11 R.Takau [QC#57252,ADD]
        NFCL5140CMsg bizMsg = new NFCL5140CMsg();
        bizMsg.setBusinessID("NFCL5140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        if (null == cMsg) {
            //scrnMsg.tocCd.setInputProtected(false);
            //scrnMsg.coaProdCd.setInputProtected(false);
            scrnMsg.setFocusItem(scrnMsg.arAdjTpCd_P1);
            return;
        }

        NFCL5140CMsg bizMsg = (NFCL5140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /*if (ZYPConstant.FLG_ON_Y.equals(bizMsg.arAdjTocEntryFlg.getValue())) {
            scrnMsg.tocCd.setInputProtected(false);
            scrnMsg.tocCd_LN.setInputProtected(false);
            scrnMsg.coaProdCd.setInputProtected(false);
            scrnMsg.coaProdCd_LN.setInputProtected(false);
        } else {
            scrnMsg.tocCd.setInputProtected(true);
            scrnMsg.tocCd_LN.setInputProtected(true);
            scrnMsg.coaProdCd.setInputProtected(true);
            scrnMsg.coaProdCd_LN.setInputProtected(true);
        }*/

        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.arAdjTpCd_P1);

    }
}
