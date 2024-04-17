/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3000.NFCL3000CMsg;
//import business.servlet.NFCL3000.constant.NFCL3000Constant;

import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2019/05/27   Fujitsu         S.Takami        Update          QC#50541
 *</pre>
 */
public class NFCL3000Scrn00_DeleteLine_A extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        if(scrnMsg.A.getValidCount() < 1) {
            return;
        }

        int chkCount = 0;
        for( int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                chkCount++;
            }
        }
        if(chkCount < 1) {
            scrnMsg.setMessageInfo("NFCM0058E", null);
            throw new EZDFieldErrorException();
        }
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

        if(scrnMsg.A.getValidCount() < 1) {
            this.setButtonProperties("DeleteLine_A", "DeleteLine_A", "Delete Line" , 0, null);

            // TAB Protect
            scrnMsg.xxTabProt_A.setInputProtected(false);
            scrnMsg.xxTabProt_B.setInputProtected(true);
            scrnMsg.xxTabProt_C.setInputProtected(true);
            scrnMsg.xxTabProt_D.setInputProtected(true);
            scrnMsg.xxTabProt_E.setInputProtected(false);
        } else { // START 2019/05/27 S.Takami [QC#50541,ADD]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.xxCellIdx.setValue(i);
                NFCL3000CommonLogic.taxAdjustmentProtect(scrnMsg);
            }
            scrnMsg.xxCellIdx.setValue(-1);
            // END 2019/05/27 S.Takami [QC#50541,ADD]
        }
    }
}
