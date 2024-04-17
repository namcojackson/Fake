/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import static business.servlet.NFCL3000.constant.NFCL3000Constant.ACCT_DIST_EDIT;
import static business.servlet.NFCL3000.constant.NFCL3000Constant.ACCT_DIST_SMRY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         T.Tanaka        Create          N/A
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10254
 *</pre>
 */
public class NFCL3000Scrn00_OnChangeRadio_C extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        // START 2016/06/16 S.Fujita [QC#10254,ADD]
        for(int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(scrnMsg.C.no(i).invErrMsgTxt_C1.getValue())) {
                String errId = scrnMsg.C.no(i).invErrMsgTxt_C1.getValue().substring(0, 9);
                scrnMsg.C.no(i).xxScrItem61Txt_C1.setErrorInfo(1, errId, new String[]{"COA Segment Code"});
            }
        }
        // END   2016/06/16 S.Fujita [QC#10254,ADD]
        if (ACCT_DIST_EDIT.equals(scrnMsg.xxRadioBtn_CB.getValue()) && ACCT_DIST_SMRY.equals(scrnMsg.xxRadioBtn_C.getValue())) {
            if (!NFCL3000CommonLogic.checkItem_AcctDistOnChange(scrnMsg)) {
                scrnMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
                scrnMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
            }
        }
        NFCL3000CommonLogic.addCheckItem_AcctDistOnChange(scrnMsg);
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

        NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);

        NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, false);
        scrnMsg.putErrorScreen();
    }
}
