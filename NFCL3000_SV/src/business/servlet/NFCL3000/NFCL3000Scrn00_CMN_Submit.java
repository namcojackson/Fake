/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
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
 * 2016/01/20   Fujitsu         T.Tanaka        Create          N/A
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/09/21   Fujitsu         S.Fujita        Update          QC#14481
 * 2018/08/20   Fujitsu         S.Ohki          Update          QC#24835
 *</pre>
 */
public class NFCL3000Scrn00_CMN_Submit extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CommonLogic.addCheckItem_Header(scrnMsg);
        NFCL3000CommonLogic.addCheckItem_TAB_Submit(scrnMsg, true);
        scrnMsg.putErrorScreen();

        if(!NFCL3000CommonLogic.check_TAB_Submit(this, scrnMsg)) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3000CommonLogic.addCheckItem_Header(scrnMsg);
        NFCL3000CommonLogic.addCheckItem_TAB_Submit(scrnMsg, false);
        scrnMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
        scrnMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
        NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);

        scrnMsg.putErrorScreen();

        // START 2016/09/21 S.Fujita [QC#14481,MOD]
        if(NFCL3000CommonLogic.checkAcctDistError(bizMsg)) {
            // If it is not error
            // START 2018/08/20 S.Ohki [QC#24835,MOD]
//            scrnMsg.xxRadioBtn_C.setValue(ACCT_DIST_SMRY);
//            scrnMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_H3.getValue())) {
                scrnMsg.xxRadioBtn_C.setValue(ACCT_DIST_SMRY);
                scrnMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);
            }
            // END 2018/08/20 S.Ohki [QC#24835,MOD]
        } else {
            scrnMsg.setMessageInfo("NFCM0576E", new String[]{"Accounting Information"});
        }
        // END   2016/09/21 S.Fujita [QC#14481,MOD]

        if (!"E".equals(bizMsg.getMessageKind())) {
            NFCL3000CommonLogic.initialize(this, scrnMsg, false);
            NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);
        }
    }
}
