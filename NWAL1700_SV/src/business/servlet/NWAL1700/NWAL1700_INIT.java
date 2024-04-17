/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.MODE_CREATE;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.MODE_EDIT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1700_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#12143
 * 2017/03/07   Fujitsu         W.Honda         Update          QC#16855
 * 2017/09/11   Fujitsu         T.Murai         Update          QC#16346(Sol#373)
 * 2017/12/14   Fujitsu         Mz.Takahashi    Update          QC#19804(Sol349)
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWAL1700_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 2) {
            if (ZYPCommonFunc.hasValue((EZDBStringItem) params[0])
                    && ZYPCommonFunc.hasValue((EZDBStringItem) params[1])) {
                scrnMsg.xxModeCd.setValue(MODE_EDIT);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, (EZDBStringItem) params[0]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, (EZDBStringItem) params[1]);
            } else {
                scrnMsg.xxModeCd.setValue(MODE_CREATE);
            }
        } else {
            scrnMsg.xxModeCd.setValue(MODE_CREATE);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = (NWAL1700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1700CommonLogic.initCmnBtnProp(this, getUserProfileService());

        if (MODE_CREATE.equals(bizMsg.xxModeCd.getValue())) {
            //init
//            NWAL1700CommonLogic.setInitScreenValue(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd);

//            int nextIdx = scrnMsg.A.getValidCount();
//            scrnMsg.A.setValidCount(nextIdx + 1);
//            scrnMsg.A.no(nextIdx).xxRowId_A.setValue(DB_FLAG_INSERT);
//            scrnMsg.A.no(nextIdx).effFromDt_A.setValue(ZYPDateUtil.getSalesDate());
//            scrnMsg.A.no(nextIdx).lineProcDfnSortNum_A.setValue(nextIdx + 1);

            if (NWAL1700CommonLogic.isFullUser(getUserProfileService()) //
                    || NWAL1700CommonLogic.isInsertUser(getUserProfileService())) { //
                NWAL1700CommonLogic.setInitScreenFullandInsert(scrnMsg);
            } else {
                NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
            }
        } else {
            if (NWAL1700CommonLogic.isFullUser(getUserProfileService())) {
                NWAL1700CommonLogic.setEditScreenFullandInsert(scrnMsg, FUNCTION_FULL, this);
            } else if (NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
                NWAL1700CommonLogic.setEditScreenFullandInsert(scrnMsg, FUNCTION_INSERT, this);
            } else {
                NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
            }
        }
        NWAL1700CommonLogic.setFocusRule(scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A, 1);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A, 1);
        tblColor.setAlternateRowsBG("B", scrnMsg.B, 1);
        tblColor.setAlternateRowsBG("C", scrnMsg.C, 1);
        tblColor.setAlternateRowsBG("D", scrnMsg.D, 1);
        tblColor.setAlternateRowsBG("E", scrnMsg.E, 1);
        tblColor.setAlternateRowsBG("F", scrnMsg.F, 1);
        tblColor.setAlternateRowsBG("G", scrnMsg.G, 1);
    }



    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        //Header
        scrnMsg.dsOrdCatgCd.setNameForMessage("Category");
        scrnMsg.dsOrdTpNm.setNameForMessage("Reason");
        scrnMsg.ordProcTpCd.setNameForMessage("Workflow");
        scrnMsg.dsOrdRsnGrpCd.setNameForMessage("Sub Reason");
        scrnMsg.dsOrdTpDescTxt.setNameForMessage("Description");
        scrnMsg.lineBizTpCd.setNameForMessage("Line of Business");
        scrnMsg.effFromDt.setNameForMessage("Start Date");
        scrnMsg.effThruDt.setNameForMessage("End Date");
        scrnMsg.actvFlg.setNameForMessage("Active");

        //Workflow Control
        scrnMsg.vldApvlNodeUsgFlg.setNameForMessage("Validation Approval");
        scrnMsg.vldApvlNodePrflCd.setNameForMessage("Validation Approval");

        scrnMsg.diChkNodeUsgFlg.setNameForMessage("DI Check Required");
        scrnMsg.diChkNodePrflCd.setNameForMessage("DI Check Required");

        scrnMsg.prftApvlNodeUsgFlg.setNameForMessage("Profitability Approval");
        scrnMsg.prftApvlNodePrflCd.setNameForMessage("Profitability Approval");

        scrnMsg.crApvlNodeUsgFlg.setNameForMessage("Credit Approval");
        scrnMsg.crApvlNodePrflCd.setNameForMessage("Credit Approval");

        scrnMsg.assetNodeUsgFlg.setNameForMessage("Fixed Asset");
        scrnMsg.assetNodePrflCd.setNameForMessage("Fixed Asset");

        scrnMsg.outOfWhNodeUsgFlg.setNameForMessage("Out of Warehouse");
        scrnMsg.outOfWhNodePrflCd.setNameForMessage("Out of Warehouse");

        scrnMsg.splyAbuseNodeUsgFlg.setNameForMessage("Supply Abuse");
        scrnMsg.splyAbuseNodePrflCd.setNameForMessage("Supply Abuse");

        // add start 2023/04/25 QC#61337
        scrnMsg.manPrcNodeUsgFlg.setNameForMessage("Manual Price Approval");
        scrnMsg.manPrcNodePrflCd.setNameForMessage("Manual Price Approval");
        // add end 2023/04/25 QC#61337

        //ProcessingRules
        //Accounting
        scrnMsg.dsInvTpCd.setNameForMessage("AR Transaction Type");
        // Mod Start 2016/08/04 QC#12143
//        scrnMsg.dsInvTpDescTxt.setNameForMessage("AR Transaction Type");
        scrnMsg.dsInvTpNm.setNameForMessage("AR Transaction Type");
        // Mod End 2016/08/04 QC#12143
        scrnMsg.autoCancOrdFlg.setNameForMessage("Auto Cancel Order");

        //Degaults
        scrnMsg.defPrcCatgCd.setNameForMessage("Price List");
        scrnMsg.prcCatgDescTxt_PR.setNameForMessage("Price List");
        scrnMsg.defMaintPrcCatgCd.setNameForMessage("Service Price List");
        scrnMsg.prcCatgDescTxt_SP.setNameForMessage("Service Price List");
        scrnMsg.revFcstCd.setNameForMessage("Forecasting Code");
        scrnMsg.frtCondCd.setNameForMessage("Freight Terms");
        scrnMsg.invPrintStyleCd.setNameForMessage("Invoice Print Style");
        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        scrnMsg.trtyGrpTpTxt.setNameForMessage("Territory Group");
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)
        // Add 2020/04/24 QC#56638 Start
        scrnMsg.baseLocTxt.setNameForMessage("Salesrep Defaulting");
        // Add 2020/04/24 QC#56638 End

        scrnMsg.defBillToCustAcctCd.setNameForMessage("Bill To Account#");
        scrnMsg.dsAcctNm.setNameForMessage("Bill To Account#");
        scrnMsg.defBillToCustCd.setNameForMessage("Bill To Location");
        scrnMsg.locDescTxt.setNameForMessage("Bill To Location");
        scrnMsg.defInstlTpCd.setNameForMessage("Install Type");
        scrnMsg.defShpgSvcLvlCd.setNameForMessage("Shipping Service Level");
        scrnMsg.defCarrSvcLvlCd.setNameForMessage("Carrier Service Level");
        scrnMsg.carrSvcLvlDescTxt.setNameForMessage("Carrier Service Level");
        scrnMsg.dropShipAvalFlg.setNameForMessage("Drop Ship Available");

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("");
            scrnMsg.A.no(i).lineProcDfnSortNum_A.setNameForMessage("#");
            scrnMsg.A.no(i).dsOrdLineCatgCd_A.setNameForMessage("Line Category Name");
            scrnMsg.A.no(i).ordProcTpCd_A.setNameForMessage("Line Workflow");
            scrnMsg.A.no(i).primLineCatgFlg_A.setNameForMessage("Primary");
            scrnMsg.A.no(i).rmaPrimLineCatgFlg_A.setNameForMessage("RMA Primary"); // Add 2017/09/11 QC#16346
            scrnMsg.A.no(i).ajeAcctBatCd_A.setNameForMessage("AJE Account Batch");
            scrnMsg.A.no(i).ajeAcctBatDescTxt_A.setNameForMessage("AJE Account Batch");
            scrnMsg.A.no(i).dsOrdLineDrctnNm_A.setNameForMessage("Transaction Type");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).revFcstCd_A.setNameForMessage("Forecasting Code");
            scrnMsg.A.no(i).actvFlg_A.setNameForMessage("Active");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcListTpDescTxt_B.setNameForMessage("Price List Type");
            scrnMsg.B.no(i).xxChkBox_B.setNameForMessage("");

        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).omXtrnlSysDescTxt_C.setNameForMessage("System Name");
            scrnMsg.C.no(i).xxChkBox_C.setNameForMessage("");

        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).bizAppId_D.setNameForMessage("Screen");
            scrnMsg.D.no(i).xxChkBox_D.setNameForMessage("");

        }

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).locRoleTpDescTxt_E.setNameForMessage("Location Role");
            scrnMsg.E.no(i).xxChkBox_E.setNameForMessage("");

        }

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).ordCatgCtxTpCd_F.setNameForMessage("Value Set Name");
            scrnMsg.F.no(i).dsOrdCatgDescTxt_F.setNameForMessage("Category");
            scrnMsg.F.no(i).dsOrdTpDescTxt_F.setNameForMessage("Reason");

        }

        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).ordLineCtxTpCd_G.setNameForMessage("Value Set Name");
            scrnMsg.G.no(i).dsOrdLineCatgDescTxt_G.setNameForMessage("Category");

        }
    }
}
