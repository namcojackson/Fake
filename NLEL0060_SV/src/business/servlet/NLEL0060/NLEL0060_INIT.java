/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.*;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NLEL0060_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#7113
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9757
 * 2016/06/30   Hitachi         J.Kim           Update          QC#11017
 * 2016/07/21   Hitachi         Y.Tsuchimoto    Update          QC#11019
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2017/11/01   Hitachi         J.Kim           Update          QC#16345
 * 2018/04/12   Hitachi         J.Kim           Update          QC#22807
 * 2018/08/17   CITS            Y.Iwasaki       Update          QC#24426
 * 2018/08/28   Fujitsu         S.Ohki          Update          QC#28000
 *</pre>
 */
public class NLEL0060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = new NLEL0060CMsg();

        // START 2016/06/14 T.Tsuchida [QC#9757,MOD]
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAssetMstrPk_P, param01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeInd_P, ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.xxModeInd_P.clear();
            scrnMsg.dsAssetMstrPk_P.clear();
        }
        // START 2016/06/14 T.Tsuchida [QC#9757,MOD]

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0060CommonLogic.initCmnBtnProp(this);
        NLEL0060CommonLogic.initFieldProp(this, scrnMsg);
        setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.assetTpCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        scrnMsg.srchOptPk.setNameForMessage(SRCH_OPT_PK);
        scrnMsg.srchOptNm.setNameForMessage(SRCH_OPT_NM);

        scrnMsg.dsAssetMstrPk_H1.setNameForMessage("Asset Num");
        scrnMsg.svcConfigMstrPk_H1.setNameForMessage("Config Num");
        // START 2016/06/30 J.Kim [QC#10864,ADD]
        scrnMsg.depcCoaAcctCd_F.setNameForMessage("Expense Account From");
        scrnMsg.depcCoaAcctCd_T.setNameForMessage("Expense Account To");
        // END 2016/06/30 J.Kim [QC#10864,ADD]
        // START 2018/04/12 J.Kim [QC#22807,ADD]
        scrnMsg.expCoaBrCd_F.setNameForMessage("Expense Branch From");
        scrnMsg.expCoaBrCd_T.setNameForMessage("Expense Branch To");
        scrnMsg.expCoaCcCd_F.setNameForMessage("Expense Cost Center From");
        scrnMsg.expCoaCcCd_T.setNameForMessage("Expense Cost Center To");
        scrnMsg.expCoaExtnCd_F.setNameForMessage("Expense Business Unit From");
        scrnMsg.expCoaExtnCd_T.setNameForMessage("Expense Business Unit To");
        // END 2018/04/12 J.Kim [QC#22807,ADD]
        scrnMsg.cpoOrdNum_H1.setNameForMessage("Order Num");
        scrnMsg.serNum_H1.setNameForMessage("Serial Num");
        scrnMsg.custIssPoNum_H1.setNameForMessage("PO Num");
        scrnMsg.assetTagNum_H1.setNameForMessage(ASSET_TAG_NUM);
        scrnMsg.dsAssetDescTxt_H1.setNameForMessage(DS_ASSET_DESC_TXT);
        scrnMsg.depcStartDt_H1.setNameForMessage(DEPC_START_DT_FROM);
        scrnMsg.depcStartDt_H2.setNameForMessage(DEPC_START_DT_TO);
        scrnMsg.mdseCd_H1.setNameForMessage(MDSE_CD);
        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // scrnMsg.sellToCustCd_H1.setNameForMessage(SELL_TO_CUST_CD);
        scrnMsg.shipToCustAcctCd_H1.setNameForMessage(SHIP_TO_CUST_ACCT_CD);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        scrnMsg.vndCd_H1.setNameForMessage(VND_CD);
        scrnMsg.rtnWhCd_H1.setNameForMessage(RTN_WH_CD);

        scrnMsg.acctYrMth_T1.setNameForMessage(ACCT_YR_MTH);

        scrnMsg.xxPageShowFromNum.setNameForMessage("Page");

        NLEL0060_ABMsg aBMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            aBMsg = scrnMsg.A.no(i);
            aBMsg.xxChkBox_A1.setNameForMessage(CHECK_BOX);
            aBMsg.dsAssetDescTxt_A1.setNameForMessage(DS_ASSET_DESC_TXT);
            aBMsg.depcMthNum_A1.setNameForMessage("Life in Months");
            aBMsg.totAssetQty_A1.setNameForMessage("Units");
            aBMsg.assetTagNum_A1.setNameForMessage(ASSET_TAG_NUM);
            aBMsg.dsAssetGrpInitBookAmt_A1.setNameForMessage("Asset Value");
            // START 2018/06/19 J.Kim [QC#24844,MOD]
            // aBMsg.assetLeaseNum_A1.setNameForMessage("Lease Num");
            // aBMsg.leaseStartDt_A1.setNameForMessage("Lease Start");
            // aBMsg.leaseEndDt_A1.setNameForMessage("Lease End");
            aBMsg.dsContrNum_A1.setNameForMessage("Contract Number");
            aBMsg.contrEffFromDt_A1.setNameForMessage("Contract Start");
            aBMsg.contrEffThruDt_A1.setNameForMessage("Contract End");
            // END 2018/06/19 J.Kim [QC#24844,MOD]
            aBMsg.depcStartDt_A1.setNameForMessage("Date in Service");
            aBMsg.serNum_A1.setNameForMessage("Serial Number");
            aBMsg.coaMdseTpCd_A1.setNameForMessage("Item Type");
            aBMsg.dsAcctNm_A1.setNameForMessage("Customer Name");
            aBMsg.cpoOrdNum_A1.setNameForMessage("Order Number");
            aBMsg.dplyLineNum_A1.setNameForMessage("Order Line");
            // START 2018/06/19 J.Kim [QC#24844,MOD]
            // aBMsg.poOrdNum_A1.setNameForMessage("PO Number");
            aBMsg.custIssPoNum_A1.setNameForMessage("PO Number");
            // END 2018/06/19 J.Kim [QC#24844,MOD]
            aBMsg.invNum_A1.setNameForMessage("Invoice Number");
            aBMsg.vndCd_A1.setNameForMessage("Vender Code");
            // START 2018/07/25 J.Kim [QC#24950,ADD]
            aBMsg.dtlCmntTxt_A1.setNameForMessage("Comment");
            // END 2018/07/125 J.Kim [QC#24950,ADD]
        }

        NLEL0060_BBMsg bBMsg;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            bBMsg = scrnMsg.B.no(i);
            bBMsg.xxChkBox_B1.setNameForMessage(CHECK_BOX);
            // START 2016/05/11 K.Kojima [QC#7113,ADD]
            bBMsg.prntDsAssetMstrPk_B1.setNameForMessage("Asset Num");
            bBMsg.assetTpDescTxt_B1.setNameForMessage("Book Type");
            // START 2016/11/01 J.Kim [QC#16345,MOD]
            // bBMsg.xxScrItem50Txt_B1.setNameForMessage("Asset Account");
            bBMsg.coaAcctCd_B.setNameForMessage("Asset Acc");
            bBMsg.coaBrCd_B.setNameForMessage("Asset Br");
            bBMsg.coaCcCd_B.setNameForMessage("Asset CC");
            bBMsg.coaExtnCd_B.setNameForMessage("Asset BU");
            // END 2016/11/01 J.Kim [QC#16345,MOD]
            bBMsg.xxScrItem50Txt_B2.setNameForMessage("Expense Account");
            bBMsg.xxScrItem10Txt_B1.setNameForMessage("Location");
            bBMsg.xxAllLineAddr_B1.setNameForMessage("Address Line");
            bBMsg.ctyAddr_B1.setNameForMessage("City");
            bBMsg.stCd_B1.setNameForMessage("State");
            bBMsg.postCd_B1.setNameForMessage("Zip Code");
            bBMsg.asgDtlCmntTxt_B1.setNameForMessage("Comment");
            // END 2016/05/11 K.Kojima [QC#7113,ADD]
            // START 2018/05/17 J.Kim [QC#25879,ADD]
            bBMsg.psnNum_B.setNameForMessage("Sales Rep"); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
            // END 2018/05/17 J.Kim [QC#25879,ADD]
        }

        NLEL0060_CBMsg cBMsg;
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            cBMsg = scrnMsg.C.no(i);
            cBMsg.xxChkBox_C1.setNameForMessage(CHECK_BOX);
            // START 2016/05/11 K.Kojima [QC#7113,ADD]
            cBMsg.dsAssetMstrPk_C1.setNameForMessage("Asset Num");
            cBMsg.assetTpDescTxt_C1.setNameForMessage("Book Type");
            cBMsg.assetStsDescTxt_C1.setNameForMessage("Status");
            cBMsg.dsAssetGrpInitBookAmt_C1.setNameForMessage("Asset Value");
            cBMsg.curValAmt_C1.setNameForMessage("NBV");
            // START 2016/06/30 J.Kim [QC#11017,MOD]
            // cBMsg.xxRteActlFrtAmt_C1.setNameForMessage("Depc Reserve");
            // cBMsg.firstDepcYrMth_C1.setNameForMessage("Depc YTD");
            // cBMsg.lastDepcYrMth_C1.setNameForMessage("Last Depre Date");
            cBMsg.xxRteActlFrtAmt_C1.setNameForMessage("Deprn Reserve");
            // START 2016/07/21 Y.Tsuchimoto [QC#11019,MOD]
            cBMsg.xxTotPrcAmt_C1.setNameForMessage("Deprn YTD");
            // END 2016/07/21 Y.Tsuchimoto [QC#11019,ADD]
            cBMsg.lastDepcYrMth_C1.setNameForMessage("Last Deprn Date");
            // END 2016/06/30 J.Kim [QC#11017,MOD]
            cBMsg.curValAmt_C2.setNameForMessage("Remaining Life");
            cBMsg.prntDsAssetMstrPk_C1.setNameForMessage("Parent Asset");
            cBMsg.finDtlCmntTxt_C1.setNameForMessage("Comment");
            // END 2016/05/11 K.Kojima [QC#7113,ADD]
        }
    }

    private void setAppFracDigit(NLEL0060BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).curValAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dsAssetGrpInitBookAmt_A1.setAppFracDigit(2);
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).dsAssetGrpInitBookAmt_C1.setAppFracDigit(2);
            scrnMsg.C.no(i).curValAmt_C1.setAppFracDigit(2);
            scrnMsg.C.no(i).xxRteActlFrtAmt_C1.setAppFracDigit(2);
            scrnMsg.C.no(i).curValAmt_C2.setAppFracDigit(0);
        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).depcValAmt_D1.setAppFracDigit(2);
        }

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).depcValAmt_E1.setAppFracDigit(2);
            scrnMsg.E.no(i).depcValAmt_E2.setAppFracDigit(2);
        }
    }
}
