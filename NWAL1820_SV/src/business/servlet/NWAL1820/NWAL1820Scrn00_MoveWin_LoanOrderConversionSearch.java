/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1820Scrn00_MoveWin_LoanOrderConversionSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1820Scrn00_MoveWin_LoanOrderConversionSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;
        int index = getButtonSelectNumber();

        // set param
        Object[] params = new Object[18];

        params[0] = scrnMsg.A.no(index).cpoOrdNum_A;
//        params[1] = scrnMsg.A.no(index).dsOrdCatgDescTxt_A; // Code
        params[2] = scrnMsg.A.no(index).dsOrdCatgDescTxt_A;
//        params[3] = scrnMsg.A.no(index).dsOrdTpDescTxt_A; // Code
        params[4] = scrnMsg.A.no(index).dsOrdTpDescTxt_A;
//        params[5] = scrnMsg.A.no(index).dsOrdRsnDescTxt_A; // Code
        params[6] = scrnMsg.A.no(index).dsOrdRsnDescTxt_A;
        params[7] = scrnMsg.A.no(index).xxCpoOrdDt_A;
//        params[8] = scrnMsg.A.no(index).ordHdrStsNm_A; // Code
        params[9] = scrnMsg.A.no(index).ordHdrStsNm_A;
        params[10] = scrnMsg.A.no(index).aquNum_A;
        params[11] = scrnMsg.A.no(index).loanPerDaysAot_A;
//        params[12] = scrnMsg.A.no(index).slsRepTocNm_A; // Code
        params[13] = scrnMsg.A.no(index).slsRepTocNm_A;
        params[14] = scrnMsg.A.no(index).coaBrCd_A;
        params[15] = scrnMsg.A.no(index).coaBrDescTxt_A;
        params[16] = scrnMsg.A.no(index).shipToCustAcctCd_A;
        params[17] = scrnMsg.A.no(index).shipToCustAcctNm_A;
        setArgForSubScreen(params);
    }
}
