/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2620;

import static business.servlet.NFCL2620.constant.NFCL2620Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2620.NFCL2620CMsg;
import business.servlet.NFCL2620.common.NFCL2620CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4759
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 *</pre>
 */
public class NFCL2620_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;

        NFCL2620CMsg bizMsg = NFCL2620CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;
        NFCL2620CMsg bizMsg = (NFCL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2620CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;

        scrnMsg.arRfRqstPk_H.setNameForMessage("Refund Number");
        scrnMsg.arRfTpCd_H.setNameForMessage("Refund Type");
        scrnMsg.billToCustAcctCd_H.setNameForMessage("Customer");
        scrnMsg.arRfStsCd_H.setNameForMessage("Refund Status");
        scrnMsg.arRfCratDt_F.setNameForMessage("Refund Creation Date From");
        scrnMsg.arRfCratDt_T.setNameForMessage("Refund Creation Date To");
        // add start 2022/07/20 QC#60113
        scrnMsg.apPmtChkNum_H.setNameForMessage("Check#");
        // add end 2022/07/20 QC#60113
        // add start 2022/08/01 QC#60113-1
        scrnMsg.dealRfAmt_H.setNameForMessage("Refund Amount");
        // add end 2022/08/01 QC#60113-1
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
    }
}
