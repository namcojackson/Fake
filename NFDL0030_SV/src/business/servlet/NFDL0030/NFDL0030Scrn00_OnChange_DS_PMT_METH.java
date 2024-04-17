/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/10   Hitachi         S.Nakatani      Create          QC#55645
 *
 *</pre>
 */
public class NFDL0030Scrn00_OnChange_DS_PMT_METH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        scrnMsg.crCardTpNm_H.clear();
        scrnMsg.crCardLastDigitNum_H.clear();
        scrnMsg.crCardExprYrMth_H.clear();
        scrnMsg.crCardHldNm_H.clear();
        scrnMsg.crCardCustRefNum_H.clear();
        scrnMsg.xxAllLineAddr_H.clear();
        scrnMsg.dtlNoteTxt_H.clear();
        scrnMsg.bankRteNum_H.clear();
        scrnMsg.dsBankAcctNum_H.clear();
    }
}
