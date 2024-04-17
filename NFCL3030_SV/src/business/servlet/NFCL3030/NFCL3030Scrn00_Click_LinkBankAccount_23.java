/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3030.common.NFCL3030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         T.Tanaka        Create          N/A
 * 2016/08/23   Hitachi         T.Tsuchida      Update          QC#13570
 *</pre>
 */
public class NFCL3030Scrn00_Click_LinkBankAccount_23 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue("Click_LinkBankAccount_23");

        scrnMsg.dsBankAcctTpCd.setValue(DS_BANK_ACCT_TP.EXTERNAL);

        // START 2016/08/23 T.Tsuchida [QC#13570,MOD]
        Object[] params = NFCL3030CommonLogic.getParamNWAL1130ForBankAcct(scrnMsg);
        // END 2016/08/23 T.Tsuchida [QC#13570,MOD]

        setArgForSubScreen(params);

    }
}
