/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/27   Hitachi         K.Kojima        Create          N/A
 * 2016/07/13   Hitachi         K.Kojima        Update          QC#10781
 *</pre>
 */
public class NFDL0020Scrn00_Click_TransactionDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        Object[] params = new Object[1];
        // START 2016/07/13 K.Kojima [QC#10781,DEL]
        // params[0] = scrnMsg.A.no(selectIdx).arTrxNum_A;
        // setArgForSubScreen(params);
        // END 2016/07/13 K.Kojima [QC#10781,DEL]
        if (scrnMsg.A.no(selectIdx).arTrxTpCd_A.getValue().equals(AR_TRX_TP.INVOICE) || scrnMsg.A.no(selectIdx).arTrxTpCd_A.getValue().equals(AR_TRX_TP.CREDIT_MEMO)) {
            // START 2016/07/13 K.Kojima [QC#10781,ADD]
            if (scrnMsg.A.no(selectIdx).arTrxNum_A.getValue().indexOf("-") > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(selectIdx).arTrxNum_A.getValue().substring(0, scrnMsg.A.no(selectIdx).arTrxNum_A.getValue().indexOf("-")));
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(selectIdx).arTrxNum_A);
            }
            params[0] = scrnMsg.P.no(0).xxPopPrm;
            setArgForSubScreen(params);
            // END 2016/07/13 K.Kojima [QC#10781,ADD]
            this.setResult("NFCL3000");
        } else if (scrnMsg.A.no(selectIdx).arTrxTpCd_A.getValue().equals(AR_TRX_TP.RECEIPT)) {
            // START 2016/07/13 K.Kojima [QC#10781,ADD]
            params[0] = scrnMsg.A.no(selectIdx).arTrxNum_A;
            setArgForSubScreen(params);
            // END 2016/07/13 K.Kojima [QC#11049,ADD]
            this.setResult("NFCL3030");
        } else {
            this.setResult("NO");
        }
    }
}
