/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import static business.servlet.NMAL7420.constant.NMAL7420Constant.DISP_HRCH_ACCT_CD_BILL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7420.common.NMAL7420CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Fujitsu         K.Ishizuka         Create          N/A
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 *</pre>
 */
public class NMAL7420Scrn00_OpenWin_AcctNumCustSold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;
        setArgForSubScreen(NMAL7420CommonLogic.getPopParamFor6760(scrnMsg, //
                // Mod Start 2018/12/04 QC#29321
                //scrnMsg.prcRuleCondFromTxt_54, scrnMsg.dsAcctNm_54, DISP_HRCH_ACCT_CD_BILL));
                scrnMsg.xxPrcQlfyValSrchTxt_K1, scrnMsg.xxPrcQlfyValSrchTxt_K2, DISP_HRCH_ACCT_CD_BILL));
        // Mod End 2018/12/04 QC#29321

    }

}
