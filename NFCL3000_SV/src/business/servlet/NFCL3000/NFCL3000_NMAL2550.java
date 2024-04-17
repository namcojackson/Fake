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
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Tanka         Create          N/A
 * 2016/09/16   Fujitsu         S.Yoshida       Update          QC#13491
 * 2019/07/11   Fujitsu         S.Takami        Update          QC#51334
 *</pre>
 */
public class NFCL3000_NMAL2550 extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        // START 2019/07/11 S.Takami [QC#51334,ADD]
        int idx = scrnMsg.xxCellIdx.getValueInt();
        boolean isProtected = scrnMsg.C.no(idx).xxScrItem61Txt_C1.isInputProtected();
        // END 2019/07/11 S.Takami [QC#51334,ADD]
        // START 2016/09/16 S.Yoshida [QC#13491,ADD]
        if (ACCT_DIST_SMRY.equals(scrnMsg.xxRadioBtn_C.getValue())
                // START 2022/12/22 Y.Mochida [QC56616, MOD]
                // || (ZYPConstant.FLG_ON_Y.equals(scrnMsg.fnlzInvFlg_H4.getValue()) && isProtected)) { // 2019/07/11 S.Takami [QC#51334,ADD isProtected]
                   || isProtected) {
                // END 2022/12/22 Y.Mochida [QC56616, MOD]
            return null;
        }
        // END   2016/09/16 S.Yoshida [QC#13491,ADD]

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

        // START 2016/09/16 S.Yoshida [QC#13491,ADD]
        if (bizMsg == null) {
            return;
        }
        // END   2016/09/16 S.Yoshida [QC#13491,ADD]

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (!CMN_CLOSE.equals(getLastGuard())) {
        }
        NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, false);
        scrnMsg.putErrorScreen();
    }
}
