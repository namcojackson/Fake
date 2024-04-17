/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         T.Tanaka        Create          N/A
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/07/08   Fujitsu         S.Fujita        Update          QC#9992
 * 2019/05/27   Fujitsu         S.Takami        Update          QC#50541
 *</pre>
 */
public class NFCL3000Scrn00_AddLine_A extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CommonLogic.addCheckItem_AddLine(scrnMsg);

        // START 2016/07/08 S.Fujita [QC#9992,MOD]
//        if(!scrnMsg.invDt_H1.getValue().substring(0, 6).equals(scrnMsg.acctDt.getValue().substring(0, 6))) {
//            scrnMsg.invDt_H1.setErrorInfo(1, "NFCM0045E");
//        }
        if(!NFCL3000CommonLogic.check_InvDt(scrnMsg)) {
            scrnMsg.invDt_H1.setErrorInfo(1, "NFCM0847E", null);
        }
        // END   2016/07/08 S.Fujita [QC#9992,MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if(scrnMsg.A.getValidCount() > 0) {
            this.setButtonProperties("DeleteLine_A", "DeleteLine_A", "Delete Line" , 1, null);
            this.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 1, null);

            scrnMsg.xxTabProt_B.setInputProtected(false);
            // START 2016/06/03 S.Fujita [QC#9452,ADD]
            scrnMsg.xxTabProt_C.setInputProtected(false);
            // END   2016/06/03 S.Fujita [QC#9452,ADD]
            scrnMsg.xxTabProt_D.setInputProtected(false);

            // START 2019/05/27 S.Takami [QC#50541,MOD]
//            NFCL3000CommonLogic.setProtectLineInput(scrnMsg);
            scrnMsg.xxCellIdx.setValue(scrnMsg.A.getValidCount() - 1);
            NFCL3000CommonLogic.taxAdjustmentProtect(scrnMsg);
            NFCL3000CommonLogic.setQtyIndispensable(scrnMsg);
            scrnMsg.xxCellIdx.setValue(-1);
            // END 2019/05/27 S.Takami [QC#50541,MOD]

        } else {
            this.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
        }

        NFCL3000CommonLogic.setDownloadButton(this, scrnMsg);

        NFCL3000CommonLogic.addCheckItem_AddLine(scrnMsg);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).invBolLineNum_A1);
    }
}
