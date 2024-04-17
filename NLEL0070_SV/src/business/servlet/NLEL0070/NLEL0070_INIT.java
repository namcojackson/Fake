/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0070.NLEL0070CMsg;
import business.servlet.NLEL0070.common.NLEL0070CommonLogic;
import business.servlet.NLEL0070.constant.NLEL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLEL0070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLEL0070Constant.BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;

        NLEL0070CMsg bizMsg = new NLEL0070CMsg();
        bizMsg.setBusinessID(NLEL0070Constant.BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
        NLEL0070CMsg bizMsg = (NLEL0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Protected.
        NLEL0070CommonLogic.protectedInitEv(scrnMsg, this);

        // Focus.
        scrnMsg.setFocusItem(scrnMsg.assetTpCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
        scrnMsg.assetTpCd_H1.setNameForMessage("Book Type");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective From Date");

        scrnMsg.assetTpCd_M1.setNameForMessage("Book Type");
        scrnMsg.effFromDt_M1.setNameForMessage("Effective From Date");
        scrnMsg.assetBookCtrlDescTxt_M1.setNameForMessage("Description");
        scrnMsg.defDepcMthNum_M1.setNameForMessage("Default Life (Month)");

        scrnMsg.assetCoaAcctCd_M1.setNameForMessage("Asset");
        scrnMsg.depcCoaAcctCd_M1.setNameForMessage("Depreciation Expence");
        scrnMsg.accumDepcCoaAcctCd_M1.setNameForMessage("Accumlated Depreciation");
        scrnMsg.depcAdjCoaAcctCd_M1.setNameForMessage("Depreciation Adjustment");
        scrnMsg.slsPrcdCoaAcctCd_M1.setNameForMessage("Sales Proceeds");
        scrnMsg.rmvCostCoaAcctCd_M1.setNameForMessage("Removal Cost");
        scrnMsg.gainLossCoaAcctCd_M1.setNameForMessage("Gain/Loss");
        scrnMsg.clingCoaAcctCd_M1.setNameForMessage("Cleaning Account");
        scrnMsg.adjCoaAcctCd_M1.setNameForMessage("Adjustment Account");
    }
}
