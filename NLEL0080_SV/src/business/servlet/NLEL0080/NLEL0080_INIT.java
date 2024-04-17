/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0080;

import static business.servlet.NLEL0080.constant.NLEL0080Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0080.NLEL0080CMsg;
import business.servlet.NLEL0080.common.NLEL0080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/05/20   Hitachi         T.Tsuchida      Update          QC#8096
 * 2016/09/20   Hitachi         J.Kim           Update          QC#10360
 *</pre>
 */
public class NLEL0080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;

        NLEL0080CMsg bizMsg = NLEL0080CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;
        NLEL0080CMsg bizMsg = (NLEL0080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrDply, ASSET_SRC_TP.LEASED);
        NLEL0080CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;

        scrnMsg.assetTpNm_S.setNameForMessage("Book Type");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setNameForMessage("#");
            scrnMsg.A.no(i).dsAssetMstrPk.setNameForMessage("Asset Num");
            scrnMsg.A.no(i).dsAssetDescTxt.setNameForMessage("Description");
            scrnMsg.A.no(i).totAssetQty.setNameForMessage("Unit");
            scrnMsg.A.no(i).depcMthNum.setNameForMessage("Life in Months");
            scrnMsg.A.no(i).serNum.setNameForMessage("Serial Number");
            scrnMsg.A.no(i).curValAmt.setNameForMessage("Asset Value");
            scrnMsg.A.no(i).prntDsAssetMstrPk.setNameForMessage("Parent");
            scrnMsg.A.no(i).xxScrItem81Txt.setNameForMessage("Expense Acct");
            scrnMsg.A.no(i).slsRepTocCd.setNameForMessage("Sales Rep");
            scrnMsg.A.no(i).cpoOrdNum.setNameForMessage("Order Num");
            scrnMsg.A.no(i).cpoDplyLineNum.setNameForMessage("Order Line");
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            //scrnMsg.A.no(i).assetLeaseNum.setNameForMessage("Lease Num");
            //scrnMsg.A.no(i).leaseStartDt.setNameForMessage("Lease Start");
            //scrnMsg.A.no(i).leaseEndDt.setNameForMessage("Lease End");
            scrnMsg.A.no(i).dsContrNum.setNameForMessage("Contract Number");
            scrnMsg.A.no(i).contrEffFromDt.setNameForMessage("Contract Start");
            scrnMsg.A.no(i).contrEffThruDt.setNameForMessage("Contract End");
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.A.no(i).sellToCustCd.setNameForMessage("Customer Code");
            scrnMsg.A.no(i).firstLineAddr.setNameForMessage("Address");
            scrnMsg.A.no(i).ctyAddr.setNameForMessage("City");
            scrnMsg.A.no(i).stCd.setNameForMessage("State");
            scrnMsg.A.no(i).postCd.setNameForMessage("Zip");
            // START 2016/09/15 J.Kim [QC#10360,ADD]
            scrnMsg.A.no(i).bllgInvNum.setNameForMessage("Billing Num");
            scrnMsg.A.no(i).lastBillDt.setNameForMessage("Last Bill");
            // START 2018/06/25 J.Kim [QC#24844,MOD]
            // scrnMsg.A.no(i).poOrdNum.setNameForMessage("PO Num");
            scrnMsg.A.no(i).custIssPoNum.setNameForMessage("PO Num");
            // END 2018/06/25 J.Kim [QC#24844,MOD]
            scrnMsg.A.no(i).invNum.setNameForMessage("Invoice Num");
            scrnMsg.A.no(i).invDt.setNameForMessage("Invoice Dt");
            scrnMsg.A.no(i).vndTpDescTxt.setNameForMessage("Vendor Name");
            // END 2016/09/15 J.Kim [QC#10360,ADD]
        }
    }
}
