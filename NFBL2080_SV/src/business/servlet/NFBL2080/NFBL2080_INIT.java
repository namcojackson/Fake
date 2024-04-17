/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2080.NFBL2080CMsg;
import business.servlet.NFBL2080.common.NFBL2080CommonLogic;
import business.servlet.NFBL2080.constant.NFBL2080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NFBL2080_INIT extends S21INITCommonHandler implements NFBL2080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFBL2080");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("20");
        scrnMsg.xxDplyTab.setValue(TAB.Header.name());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        NFBL2080CommonLogic.initialize(this, scrnMsg, SCRN_EVENT.NFBL2080_INIT.name());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2080CommonLogic.setHyoSettings(scrnMsg, this);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        // Search Conditions
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.vndCd.setNameForMessage("Supplier Site Code");
        scrnMsg.vndInvNum.setNameForMessage("Supplier INV#");
        scrnMsg.soNum.setNameForMessage("Supplier SO#");
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.xxCratDt_S1.setNameForMessage("EDI Rcv Date From");
        scrnMsg.xxCratDt_S2.setNameForMessage("EDI Rcv Date To");
        scrnMsg.poOrdNum.setNameForMessage("PO#");
        scrnMsg.ediPoOrdNum.setNameForMessage("EDI PO#");
        scrnMsg.batErrMsgTxt.setNameForMessage("Message");

        // Search Results
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).batErrMsgTxt_AP.setNameForMessage("Message");
        }

        // Header Tab
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.soNum_H1.setNameForMessage("Supplier SO#");
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.poOrdNum_H1.setNameForMessage("PO#");
        scrnMsg.ediPoOrdNum_H1.setNameForMessage("EDI PO#");
        scrnMsg.custIssPoNum_H1.setNameForMessage("Customer PO#");
        scrnMsg.batErrMsgTxt_HP.setNameForMessage("Message");

        // Detail Tab
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).shipQty_B1.setNameForMessage("INV QTY");
        }

    }
}
