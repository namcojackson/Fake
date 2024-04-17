/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.BIZ_ID;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7270.NMAL7270CMsg;
import business.servlet.NMAL7270.common.NMAL7270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;



/**
 *<pre>
 * NMAL7270Scrn00_Add_TrxCond
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2017/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7270Scrn00_Add_TrxCond extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        NMAL7270CMsg bizMsg = new NMAL7270CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CMsg bizMsg  = (NMAL7270CMsg) cMsg;

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = scrnMsg.A.getValidCount() - 1;
        // Mod Start 2017/02/28 QC#16011
//        NMAL7270CommonLogic.condAttrbCtrl(this, idx, scrnMsg);
//        NMAL7270CommonLogic.setBtnProp(this, scrnMsg);
//        NMAL7270CommonLogic.setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.A.no(idx).effFromDt_A1, scrnMsg.slsDt.getValue());
        boolean protectFldFlg = NMAL7270CommonLogic.updateAuthority(getUserProfileService());
        NMAL7270CommonLogic.condAttrbCtrl(this, idx, scrnMsg, protectFldFlg);
        NMAL7270CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7270CommonLogic.setEffFromDtProtect(scrnMsg.prcRuleHdrPk_BK, scrnMsg.A.no(idx).effFromDt_A1, scrnMsg.slsDt.getValue(), protectFldFlg);
        // Mod End 2017/02/28 QC#16011

        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.setFocusItem(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1);
    }
}
