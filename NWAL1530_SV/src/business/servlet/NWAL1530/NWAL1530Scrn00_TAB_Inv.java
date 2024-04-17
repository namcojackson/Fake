/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.BIZ_ID;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_INVOICE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1530.NWAL1530CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_TAB_Inv
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 * 2023/04/24   Hitachi         R.Takau         Update          QC#61382
 *</pre>
 */
public class NWAL1530Scrn00_TAB_Inv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        NWAL1530CMsg bizMsg = new NWAL1530CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        NWAL1530CMsg bizMsg = (NWAL1530CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set the error to tab
        if (ZYPCommonFunc.hasValue(bizMsg.errMsgCd_IV)) {
            scrnMsg.setMessageInfo(bizMsg.errMsgCd_IV.getValue());
        } else {
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                scrnMsg.E.no(i).invTotFuncNetAmt_E.setAppFracDigit(2);
                // START 2023.04/24 R.Takau [ADD, QC#61382]
                scrnMsg.E.no(i).dealRmngBalGrsAmt_E.setAppFracDigit(2);
                // END 2023.04/24 R.Takau [ADD, QC#61382]
            }
        }

        // Display TAB = Invoice
        scrnMsg.xxDplyTab.setValue(TAB_INVOICE);
    }
}
