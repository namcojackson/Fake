/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0100.NWCL0100CMsg;
import business.servlet.NWCL0100.common.NWCL0100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWCL0100_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0100_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;
        NWCL0100CMsg bizMsg = new NWCL0100CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;
        NWCL0100CMsg bizMsg = (NWCL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0100CommonLogic.initCmnBtnProp(this);
        NWCL0100CommonLogic.controlItem(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxBillToAcctCdSrchTxt_H0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        scrnMsg.srchOptPk_S0.setNameForMessage("Save Search Options");
        scrnMsg.srchOptNm_S0.setNameForMessage("Search Option Name");
        scrnMsg.xxBillToAcctCdSrchTxt_H0.setNameForMessage("Bill To Accunt");
        scrnMsg.xxChkBox_H0.setNameForMessage("Show Active Only");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).billToCustAcctCd_A0.setNameForMessage("Bill To Acct Cd");
            scrnMsg.A.no(i).effFromDt_A0.setNameForMessage("Valid From");
            scrnMsg.A.no(i).effThruDt_A0.setNameForMessage("Valid To");
            scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setNameForMessage("Rate");
            scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setNameForMessage("Monthly Quota");
            scrnMsg.A.no(i).autoDrCratFlg_A0.setNameForMessage("Auto Debit");
        }
    }

}
