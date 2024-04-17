/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2019/05/27   Fujitsu         S.Takami        Update          QC#50541
 *</pre>
 */
public class NFCL3000Scrn00_TAB_SalesCredit extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CommonLogic.addCheckItem_Header(scrnMsg);
        NFCL3000CommonLogic.addCheckItem_TAB(scrnMsg, false);
        scrnMsg.putErrorScreen();

        if(!NFCL3000CommonLogic.check_TAB(scrnMsg)) {
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
        // START 2019/05/27 S.Takami [QC#50541,DEL]
//        scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
        // END 2019/05/27 S.Takami [QC#50541,DEL]
        NFCL3000CommonLogic.addCheckItem_TAB(scrnMsg, false);
        scrnMsg.putErrorScreen();

        // START 2019/05/27 S.Takami [QC#50541,ADD]
        scrnMsg.xxDplyTab.setValue(TAB_SalesCredit);
        // END 2019/05/27 S.Takami [QC#50541,ADD]
        // START 2016/06/03 S.Fujita [QC#9452,ADD]
        if(scrnMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            this.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 0, null);
        } else {
            this.setButtonProperties("Calc"     , "Calc"     , "Calc"    , 1, null);
        }
        // END  2016/06/03 S.Fujita [QC#9452,ADD]

        NFCL3000CommonLogic.setDownloadButton(this, scrnMsg);

        if(scrnMsg.B.getValidCount() > 0) {
            this.setButtonProperties("DeleteLine_B", "DeleteLine_B", "Delete Line" , 1, null);
        }
    }
}
