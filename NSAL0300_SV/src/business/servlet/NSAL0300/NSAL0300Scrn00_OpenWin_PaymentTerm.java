/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2016/02/09   Hitachi         T.Aoyagi        Update          QC4081
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_PaymentTerm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxPopPrm_01.setValue("PMT_TERM_CASH_DISC");
        scrnMsg.xxPopPrm_02.setValue("PMT_TERM_CASH_DISC_CD");
        scrnMsg.xxPopPrm_03.setValue("PMT_TERM_CASH_DISC_NM");
        scrnMsg.xxPopPrm_04.setValue("PMT_TERM_CASH_DISC_NM");
        scrnMsg.xxPopPrm_05.setValue("Payment Term Search");
        scrnMsg.xxPopPrm_06.setValue("Payment Term Code");
        scrnMsg.xxPopPrm_07.setValue("Payment Term Name");
        scrnMsg.xxPopPrm_08.setValue("Payment Term Code");
        scrnMsg.xxPopPrm_09.setValue("Payment Term Name");
        // START 2016/02/09 T.Aoyagi [QC4081, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_10, scrnMsg.pmtTermCashDiscCd);
        // END 2016/02/09 T.Aoyagi [QC4081, ADD]
        Object[] prm = new Object[11];
        prm[0] = scrnMsg.xxPopPrm_01; // 1.TBL_NM
        prm[1] = scrnMsg.xxPopPrm_02; // 2.TBL_CD_COLUMN_CD
        prm[2] = scrnMsg.xxPopPrm_03; // 3.TBL_CD_COLUMN_NM
        prm[3] = scrnMsg.xxPopPrm_04; // 4.TBL_SORT_NUM_COLUMN_NM
        prm[4] = scrnMsg.xxPopPrm_05; // 5.SCR_NM
        prm[5] = scrnMsg.xxPopPrm_06; // 6.HDR_CD_LABEL
        prm[6] = scrnMsg.xxPopPrm_07; // 7.HDR_NM_LABEL
        prm[7] = scrnMsg.xxPopPrm_08; // 8.DTL_CD_LABEL
        prm[8] = scrnMsg.xxPopPrm_09; // 9.DTL_NM_LABEL
        // START 2016/02/09 T.Aoyagi [QC4081, MOD]
//        prm[9] = scrnMsg.pmtTermCashDiscCd; // 10.CONDITION_CD
        prm[9] = scrnMsg.xxPopPrm_10; // 10.CONDITION_CD
        // END 2016/02/09 T.Aoyagi [QC4081, MOD]
        prm[10] = scrnMsg.xxPopPrm_11; // 11.CONDITION_NM
        setArgForSubScreen(prm);
    }
}
