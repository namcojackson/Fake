/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3140.NFCL3140CMsg;
import business.servlet.NFCL3140.common.NFCL3140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#12142
 * 
 *</pre>
 */
public class NFCL3140_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = new NFCL3140CMsg();
        bizMsg.setBusinessID("NFCL3140");
        bizMsg.setFunctionCode("20");

        String xxScrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_DsInvoiceType".equals(xxScrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsInvTpCd, scrnMsg.P.no(0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.dsInvTpNm);
            } else if ("OpenWin_AssociatedCreditType".equals(xxScrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsInvTpCd_AC, scrnMsg.P.no(0).xxComnScrColValTxt);
                // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsInvTpNm_AC, scrnMsg.P.no(1).xxComnScrColValTxt);
                // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
            }
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = (NFCL3140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3140CommonLogic.setupAnchor(scrnMsg);

        String xxScrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_DsInvoiceType".equals(xxScrEventNm)) {
                scrnMsg.setFocusItem(scrnMsg.dsInvTpNm);
            } else if ("OpenWin_AssociatedCreditType".equals(xxScrEventNm)) {
                // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                scrnMsg.setFocusItem(scrnMsg.dsInvTpNm_AC);
                // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
            }
        }
    }

}
