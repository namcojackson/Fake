/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2016/02/09   Hitachi         T.Aoyagi        Update          QC4081
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2023/03/13   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */
public class NSAL0300_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/02/09 T.Aoyagi [QC4081, ADD]
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        if (!CMN_CLOSE.equals(getLastGuard())) {
            scrnMsg.pmtTermCashDiscCd.setValue(scrnMsg.xxPopPrm_10.getValue());
            scrnMsg.pmtTermCashDiscDescTxt.setValue(scrnMsg.xxPopPrm_11.getValue());
            scrnMsg.setFocusItem(scrnMsg.mdseDescShortTxt_SP);
            // Start 2023/03/13 R.Takau[QC#55645, ADD]
            if (PMT_TERM_CASH_DISC.CHECK_BY_PHONE.equals(scrnMsg.pmtTermCashDiscCd.getValue())){
                scrnMsg.crCardCustRefNum.clear();
                scrnMsg.xxScrItem10Txt.clear();
                scrnMsg.xxMthDt_CC.clear();
                scrnMsg.xxYrDt_CC.clear();
            } else {
                scrnMsg.bankRteNum.clear();
                scrnMsg.dsBankAcctNum.clear();
                scrnMsg.dsCustBankAcctRelnPk.clear();
                scrnMsg.ezUpTime_CP.clear();
                scrnMsg.ezUpTimeZone_CP.clear();
            }
            // End 2023/03/13 R.Takau[QC#55645, ADD]
        } else {
            scrnMsg.setFocusItem(scrnMsg.pmtTermCashDiscCd);
        }
        // END 2016/02/09 T.Aoyagi [QC4081, ADD]
    }

}
