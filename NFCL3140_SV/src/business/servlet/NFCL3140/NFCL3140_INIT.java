/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3140;

import static business.servlet.NFCL3140.constant.NFCL3140Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3140.NFCL3140CMsg;
import business.servlet.NFCL3140.common.NFCL3140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#12142
 * 2016/11/25   Hitachi         J.Kim           Update          QC#16240
 *</pre>
 */
public class NFCL3140_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = new NFCL3140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = (NFCL3140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3140CommonLogic.setupScreenItems(this, scrnMsg);
        NFCL3140CommonLogic.setupAnchor(scrnMsg);
        scrnMsg.invTpCd_SV.setInputProtected(false);
        scrnMsg.setFocusItem(scrnMsg.dsInvTpNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) arg0;
        scrnMsg.dsInvTpNm.setNameForMessage("Name");
        scrnMsg.dsInvTpDescTxt.setNameForMessage("Description");
        scrnMsg.invTpCd_SV.setNameForMessage("Class");
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        scrnMsg.dsInvTpNm_AC.setNameForMessage("Associated Credit Type");
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        scrnMsg.invSrcTxt.setNameForMessage("Invoice Source");
        scrnMsg.arCoaCmpyCd.setNameForMessage("Receivable - Company");
        scrnMsg.arCoaBrCd.setNameForMessage("Receivable - Branch");
        scrnMsg.arCoaCcCd.setNameForMessage("Receivable - Cost Center");
        scrnMsg.arCoaAcctCd.setNameForMessage("Receivable - Account");
        scrnMsg.arCoaProdCd.setNameForMessage("Receivable - Product");
        scrnMsg.arCoaChCd.setNameForMessage("Receivable - Channel");
        // START 2016/11/25 J.Kim [QC#16240,MOD]
        // scrnMsg.arCoaAfflCd.setNameForMessage("Receivable - Affiliate");
        scrnMsg.arCoaAfflCd.setNameForMessage("Receivable - Intercompany");
        // END 2016/11/25 J.Kim [QC#16240,MOD]
        scrnMsg.arCoaProjCd.setNameForMessage("Receivable - M'dise Type");
        scrnMsg.arCoaExtnCd.setNameForMessage("Receivable - Business Unit");
        scrnMsg.slsCoaCmpyCd.setNameForMessage("Revenue - Company");
        scrnMsg.slsCoaBrCd.setNameForMessage("Revenue - Branch");
        scrnMsg.slsCoaCcCd.setNameForMessage("Revenue - Cost Center");
        scrnMsg.slsCoaAcctCd.setNameForMessage("Revenue - Account");
        scrnMsg.slsCoaProdCd.setNameForMessage("Revenue - Product");
        scrnMsg.slsCoaChCd.setNameForMessage("Revenue - Channel");
        // START 2016/11/25 J.Kim [QC#16240,MOD]
        // scrnMsg.slsCoaAfflCd.setNameForMessage("Revenue - Affiliate");
        scrnMsg.slsCoaAfflCd.setNameForMessage("Revenue - Intercompany");
        // END 2016/11/25 J.Kim [QC#16240,MOD]
        scrnMsg.slsCoaProjCd.setNameForMessage("Revenue - M'dise Type");
        scrnMsg.slsCoaExtnCd.setNameForMessage("Revenue - Business Unit");
        scrnMsg.taxCoaCmpyCd.setNameForMessage("Tax - Company");
        scrnMsg.taxCoaBrCd.setNameForMessage("Tax - Branch");
        scrnMsg.taxCoaCcCd.setNameForMessage("Tax - Cost Center");
        scrnMsg.taxCoaAcctCd.setNameForMessage("Tax - Account");
        scrnMsg.taxCoaProdCd.setNameForMessage("Tax - Product");
        scrnMsg.taxCoaChCd.setNameForMessage("Tax - Channel");
        // START 2016/11/25 J.Kim [QC#16240,MOD]
        // scrnMsg.taxCoaAfflCd.setNameForMessage("Tax - Affiliate");
        scrnMsg.taxCoaAfflCd.setNameForMessage("Tax - Intercompany");
        // END 2016/11/25 J.Kim [QC#16240,MOD]
        scrnMsg.taxCoaProjCd.setNameForMessage("Tax - M'dise Type");
        scrnMsg.taxCoaExtnCd.setNameForMessage("Tax - Business Unit");
        scrnMsg.unEarnCoaCmpyCd.setNameForMessage("Unearned Revenue - Company");
        scrnMsg.unEarnCoaBrCd.setNameForMessage("Unearned Revenue - Branch");
        scrnMsg.unEarnCoaCcCd.setNameForMessage("Unearned Revenue - Cost Center");
        scrnMsg.unEarnCoaAcctCd.setNameForMessage("Unearned Revenue - Account");
        scrnMsg.unEarnCoaProdCd.setNameForMessage("Unearned Revenue - Product");
        scrnMsg.unEarnCoaChCd.setNameForMessage("Unearned Revenue - Channel");
        // START 2016/11/25 J.Kim [QC#16240,MOD]
        // scrnMsg.unEarnCoaAfflCd.setNameForMessage("Unearned Revenue - Affiliate");
        scrnMsg.unEarnCoaAfflCd.setNameForMessage("Unearned Revenue - Intercompany");
        // END 2016/11/25 J.Kim [QC#16240,MOD]
        scrnMsg.unEarnCoaProjCd.setNameForMessage("Unearned Revenue - M'dise Type");
        scrnMsg.unEarnCoaExtnCd.setNameForMessage("Unearned Revenue - Business Unit");
    }
}
