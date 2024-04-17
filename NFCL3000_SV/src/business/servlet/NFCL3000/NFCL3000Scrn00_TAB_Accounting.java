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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/08/01   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/09/21   Fujitsu         S.Fujita        Update          QC#14481
 * 2018/08/20   Fujitsu         S.Ohki          Update          QC#24835
 * 2019/05/16   Fujitsu         S.Takami        Update          QC#50374
 *</pre>
 */
public class NFCL3000Scrn00_TAB_Accounting extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        if (!ZYPCommonFunc.hasValue(scrnMsg.invNum_H1.getValue()) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxPgFlg_H1.getValue())){
            scrnMsg.invNum_H1.setErrorInfo(1, "NFCM0860E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        NFCL3000CommonLogic.addCheckItem_Header(scrnMsg);
        NFCL3000CommonLogic.addCheckItem_TAB(scrnMsg, false);

        if(!NFCL3000CommonLogic.check_TAB_Submit(this, scrnMsg)) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        // START 2016/06/03 S.Fujita [QC#9452,ADD]
//        bizMsg.setFunctionCode("20");
        bizMsg.setFunctionCode("40");
        // END   2016/06/03 S.Fujita [QC#9452,ADD]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/06/03 S.Fujita [QC#9452,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg.getValue())){
            // Start 2018/08/20 S.Ohki [QC#24835,MOD]
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
                scrnMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
                scrnMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
            }
            // END 2018/08/20 S.Ohki [QC#24835,MOD]
            NFCL3000CommonLogic.initialize(this, scrnMsg, false);
        }

        scrnMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);

        // START 2019/05/16 S.Takami [QC#50374,ADD]
        NFCL3000CommonLogic.addCheckItem_Header(scrnMsg);
        // END 2019/05/16 S.Takami [QC#50374,ADD]
        NFCL3000CommonLogic.addCheckItem_TAB_Submit(scrnMsg, false);
        NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);
        scrnMsg.putErrorScreen();

        // If it is not error except Accounting TAB
        scrnMsg.xxDplyTab.setValue(TAB_Accounting);

        // START 2016/09/21 S.Fujita [QC#14481,ADD]
        if(!NFCL3000CommonLogic.checkAcctDistError(bizMsg)) {
            scrnMsg.setMessageInfo("NFCM0576E", new String[]{"Accounting Information"});
        }
        // END   2016/09/21 S.Fujita [QC#14481,ADD]

        NFCL3000CommonLogic.setDownloadButton(this, scrnMsg);
        // END   2016/06/03 S.Fujita [QC#9452,ADD]
    }
}
