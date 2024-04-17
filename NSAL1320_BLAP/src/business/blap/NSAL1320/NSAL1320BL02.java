/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1320;

import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0630E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.ZZM8100I;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0695W;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import business.blap.NSAL1320.common.NSAL1320CommonLogic;
import business.db.SELL_TO_CUSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1320BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/20   Hitachi         K.Kojima        Update          QC#19053
 * 2017/08/15   Hitachi         Y.Takeno        Update          QC#20378
 * 2017/09/26   Hitachi         Y.Takeno        Update          QC#21154
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/02/01   Hitachi         K.Yamada        Update          QC#23667
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/07/18   Hitachi         K.Kitachi       Update          QC#26589
 * 2024/03/12   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NSAL1320BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

            if ("NSAL1320_INIT".equals(screenAplID)) {
                doProcess_NSAL1320_INIT(bizMsg);

            } else if ("NSAL1320Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_CMN_Reset(bizMsg);

            } else if ("NSAL1320Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_CMN_Save(bizMsg);

            } else if ("NSAL1320Scrn00_DelDetail".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_DelDetail(bizMsg);

            } else if ("NSAL1320Scrn00_DelMaintenanceShell".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_DelMaintenanceShell(bizMsg);

            } else if ("NSAL1320Scrn00_MassApply".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_MassApply();

            } else if ("NSAL1320Scrn00_SearchMaintenance".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_SearchMaintenance(bizMsg);

            } else if ("NSAL1320_NMAL6780".equals(screenAplID)) {
                doProcess_NSAL1320_NMAL6780(bizMsg);

            } else if ("NSAL1320_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL1320_NWAL1130(bizMsg);

            } else if ("NSAL1320_NSAL1350".equals(screenAplID)) {
                doProcess_NSAL1320_NSAL1350(bizMsg);

            } else if ("NSAL1320_NSAL1360".equals(screenAplID)) {
                doProcess_NSAL1320_NSAL1360(bizMsg);

            } else if ("NSAL1320_NSAL1330".equals(screenAplID)) {
                doProcess_NSAL1320_NSAL1330(bizMsg);

            } else if ("NSAL1320_NSAL0110".equals(screenAplID)) {
                doProcess_NSAL1320_NSAL0110(bizMsg);

            } else if ("NSAL1320Scrn00_OpenWin_SvcPricing".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OpenWin_SvcPricing(bizMsg);

            } else if ("NSAL1320Scrn00_OnClick_MntBillAsEquip".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnClick_MntBillAsEquip(bizMsg);

            } else if ("NSAL1320Scrn00_OnChange_ContractIndicator".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnChange_ContractIndicator(bizMsg);

            } else if ("NSAL1320Scrn00_OnChange_BilledBy".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnChange_BilledBy(bizMsg);

            } else if ("NSAL1320Scrn00_OnBlur_DeriveFromSvcPgmCd".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnBlur_DeriveFromSvcPgmCd(bizMsg);

            } else if ("NSAL1320Scrn00_OnBlur_DeriveFromSvcPgmNm".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnBlur_DeriveFromSvcPgmNm(bizMsg);

            } else if ("NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNm".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNm(bizMsg);

            } else if ("NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNum".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNum(bizMsg);

            } else if ("NSAL1320Scrn00_OnBlur_DeriveFromCustLocCd".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnBlur_DeriveFromCustLocCd(bizMsg);

            } else if ("NSAL1320Scrn00_OnBlur_DeriveFromAddToContract".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnBlur_DeriveFromAddToContract(bizMsg);

            // START 2017/08/15 [QC#20378, ADD]
            } else if ("NSAL1320Scrn00_OnChange_PlanType".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnChange_ContractIndicator(bizMsg);

            } else if ("NSAL1320Scrn00_OnChange_ContractType".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OnChange_ContractIndicator(bizMsg);

            } else if ("NSAL1320Scrn00_OpenWin_Customer".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OpenWin_Customer(bizMsg);
            // END   2017/08/15 [QC#20378, ADD]

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1320_NSAL0110(NSAL1320CMsg bizMsg) {

        // START 2017/10/24 [QC#21556, MOD]
        // NSAL1320CommonLogic.checkContr(bizMsg);
        if (!NSAL1320CommonLogic.checkContr(bizMsg)) {
            NSAL1320CommonLogic.setHeaderInfoFromContrNum(bizMsg);
            // START 2018/07/18 K.Kitachi [QC#26589, ADD]
            int ixA = bizMsg.xxCellIdx.getValueInt();
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);
            NSAL1320CommonLogic.deriveFromContrTp(getGlobalCompanyCode(), aBizMsg.svcPgmMdseCd, aBizMsg.prcSvcContrTpCd);
            // END 2018/07/18 K.Kitachi [QC#26589, ADD]
        }
        // END   2017/10/24 [QC#21556, MOD]
    }

    private void doProcess_NSAL1320Scrn00_OnBlur_DeriveFromAddToContract(NSAL1320CMsg bizMsg) {

        NSAL1320CommonLogic.getContrInfo(bizMsg);
        // START 2018/07/18 K.Kitachi [QC#26589, ADD]
        int ixA = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);
        NSAL1320CommonLogic.deriveFromContrTp(getGlobalCompanyCode(), aBizMsg.svcPgmMdseCd, aBizMsg.prcSvcContrTpCd);
        // END 2018/07/18 K.Kitachi [QC#26589, ADD]
    }

    private void doProcess_NSAL1320Scrn00_OnChange_BilledBy(NSAL1320CMsg bizMsg) {
        doProcess_NSAL1320Scrn00_OnClick_MntBillAsEquip(bizMsg);
    }

    private void doProcess_NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNm(NSAL1320CMsg bizMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem targetItem = bizMsg.A.no(selectIndex).dsAcctNm;

        // START 2017/08/15 [QC#20378, ADD]
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(selectIndex);
        if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.xxExstFlg_PR.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_CI, ZYPConstant.FLG_ON_Y);
        }
        // END   2017/08/15 [QC#20378, ADD]

        SELL_TO_CUSTTMsg dsAcctCustTMsg //
        = NSAL1320CommonLogic.getDsAcctCustInfo(bizMsg, true, targetItem, "Customer Name", getGlobalCompanyCode());
        if (dsAcctCustTMsg == null) {
            return;
        }

        NSAL1320CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg, getGlobalCompanyCode());
    }

    private void doProcess_NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNum(NSAL1320CMsg bizMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem targetItem = bizMsg.A.no(selectIndex).dsAcctNum;

        // START 2017/08/15 [QC#20378, ADD]
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(selectIndex);
        if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.xxExstFlg_PR.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_CI, ZYPConstant.FLG_ON_Y);
        }
        // END   2017/08/15 [QC#20378, ADD]

        SELL_TO_CUSTTMsg dsAcctCustTMsg //
        = NSAL1320CommonLogic.getDsAcctCustInfo(bizMsg, false, targetItem, "Customer Number", getGlobalCompanyCode());
        if (dsAcctCustTMsg == null) {
            return;
        }

        NSAL1320CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg, getGlobalCompanyCode());
    }

    private void doProcess_NSAL1320Scrn00_OnBlur_DeriveFromCustLocCd(NSAL1320CMsg bizMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();
        Map<String, String> billToInfo //
        = NSAL1320CommonLogic.getBillToInfo(bizMsg, bizMsg.A.no(selectIndex).soldToCustLocCd, "Location Code");
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNum, billToInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).soldToCustLocCd, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).xxGenlFldAreaTxt_BI, billToInfo.get("BILL_TO_ADDR"));
    }

    private void doProcess_NSAL1320Scrn00_OnBlur_DeriveFromSvcPgmNm(NSAL1320CMsg bizMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ix);
        NSAL1320CommonLogic.deriveFromShellItemNm(//
                getGlobalCompanyCode(), false, aBizMsg.svcPgmMdseCd, aBizMsg.mdseDescShortTxt, bizMsg, ZYPConstant.FLG_ON_Y);
        // START 2018/07/18 K.Kitachi [QC#26589, ADD]
        NSAL1320CommonLogic.deriveFromContrTp(getGlobalCompanyCode(), aBizMsg.svcPgmMdseCd, aBizMsg.prcSvcContrTpCd);
        // END 2018/07/18 K.Kitachi [QC#26589, ADD]
    }

    private void doProcess_NSAL1320Scrn00_OnBlur_DeriveFromSvcPgmCd(NSAL1320CMsg bizMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ix);
        NSAL1320CommonLogic.deriveFromShellItemCd(//
                getGlobalCompanyCode(), false, aBizMsg.svcPgmMdseCd, aBizMsg.mdseDescShortTxt, bizMsg, ZYPConstant.FLG_ON_Y);
        // START 2018/07/18 K.Kitachi [QC#26589, ADD]
        NSAL1320CommonLogic.deriveFromContrTp(getGlobalCompanyCode(), aBizMsg.svcPgmMdseCd, aBizMsg.prcSvcContrTpCd);
        // END 2018/07/18 K.Kitachi [QC#26589, ADD]
    }

    private void doProcess_NSAL1320Scrn00_OnChange_ContractIndicator(NSAL1320CMsg bizMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ix);

        if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.xxExstFlg_PR.getValue())) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_CI, ZYPConstant.FLG_ON_Y);
        }
    }

    private void doProcess_NSAL1320Scrn00_MassApply() {
        // no operation.

    }

    private void doProcess_NSAL1320_NSAL1360(NSAL1320CMsg bizMsg) {
        NSAL1320CommonLogic.setNSAL1360ReturnValues(bizMsg);
    }

    private void doProcess_NSAL1320Scrn00_OpenWin_SvcPricing(NSAL1320CMsg bizMsg) {
        // START 2024/03/12 M.Kuroi [QC#63638, MOD]
        // if (bizMsg.getMessageInfo() != null) {
        if ("E".equals(bizMsg.getMessageKind())) {
        // END 2024/03/12 M.Kuroi [QC#63638, MOD]
            return;
        }
        NSAL1320CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

    private void doProcess_NSAL1320Scrn00_OnClick_MntBillAsEquip(NSAL1320CMsg bizMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ix);
        if (ZYPConstant.CHKBOX_ON_Y.equals(aBizMsg.applyEquipBillToFlg.getValue())) {
            NSAL1320CommonLogic.setBillToInfoToCustomer(aBizMsg, bizMsg);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320Scrn00_CMN_Reset(NSAL1320CMsg bizMsg) {
        NSAL1320CommonLogic.resetBizMsgBeforeInit(bizMsg);
        doProcess_NSAL1320_INIT(bizMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320Scrn00_CMN_Save(NSAL1320CMsg bizMsg) {
        //
        NSAL1320CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);

        if (bizMsg.getMessageInfo() == null) {
            bizMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * DelDetail Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320Scrn00_DelDetail(NSAL1320CMsg bizMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        NSAL1320CommonLogic.delDetail(getGlobalCompanyCode(), bizMsg, selectRows);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320_INIT(NSAL1320CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, bizMsg.xxScrItem50Txt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.refCpoOrdNum, bizMsg.xxScrItem50Txt);

        NSAL1320CommonLogic.createPulldownList(getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(), bizMsg);
        NSAL1320CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

    /**
     * DelMaintenanceShell Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320Scrn00_DelMaintenanceShell(NSAL1320CMsg bizMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.A.no(0).xxChkBox_A.setErrorInfo(1, NSAM0630E, new String[] {"Maintenance Shell" });
            return;
        }
        NSAL1320CommonLogic.delMaintenanceShell(bizMsg, selectRows);
    }

    /**
     * SearchMaintenance Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320Scrn00_SearchMaintenance(NSAL1320CMsg bizMsg) {
        //
        NSAL1320CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

    /**
     * NMAL6780 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320_NMAL6780(NSAL1320CMsg bizMsg) {
        // no operation
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320_NWAL1130(NSAL1320CMsg bizMsg) {
        // no operation
    }

    /**
     * NWAL2040 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320_NSAL1350(NSAL1320CMsg bizMsg) {
        boolean isAddForDetail = bizMsg.xxScrEventNm.getValue().endsWith("ForDetail");

        if (isAddForDetail) {
            int ix = bizMsg.xxCellIdx.getValueInt();
            BigDecimal cpoSvcLineNum = bizMsg.A.no(ix).shellLineNum.getValue();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (cpoSvcLineNum.compareTo(bizMsg.B.no(i).shellLineNum_B.getValue()) == 0) {
                    NSAL1320CommonLogic.deriveCustIssPoNum(getGlobalCompanyCode(), bizMsg.B.no(i), bizMsg);
                    // START 2018/02/01 [QC#23667,MOD]
                    // START 2017/06/20 K.Kojima [QC#19053,ADD]
                    //if (ZYPCommonFunc.hasValue(bizMsg.A.no(ix).xxTotAmt_S)) {
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(ix).xxTotAmt_S) && ZYPCommonFunc.hasValue(bizMsg.A.no(ix).dsContrPk_A)) {
                    // END 2018/02/01 [QC#23667,MOD]
                        NSAL1320CommonLogic.setAmountForNewLine(getGlobalCompanyCode(), bizMsg.A.no(ix), bizMsg.B.no(i), bizMsg);
                    }
                    // END 2017/06/20 K.Kojima [QC#19053,ADD]
                }
            }
        } else {
            if (bizMsg.A.getValidCount() == 0) {
                return;
            }
            int ix = bizMsg.A.getValidCount() - 1;
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ix);

            NSAL1320CommonLogic.createBillByTpPulldown(aBizMsg, bizMsg);
            // START 2017/09/26 [QC#21154, ADD]
            NSAL1320CommonLogic.setSoldToCustomerInfo(aBizMsg, bizMsg);
            // END   2017/09/26 [QC#21154, ADD]
            // 2018/05/07 QC#22482 Add Start
            aBizMsg.manContrOvrdFlg.setValue(ZYPConstant.FLG_OFF_N);
            // 2018/05/07 QC#22482 Add End

            BigDecimal cpoSvcLineNum = aBizMsg.shellLineNum.getValue();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (cpoSvcLineNum.compareTo(bizMsg.B.no(i).shellLineNum_B.getValue()) == 0) {
                    NSAL1320CommonLogic.deriveCustIssPoNum(getGlobalCompanyCode(), bizMsg.B.no(i), bizMsg);
                }
            }
        }

    }

    /**
     * NSAL1330 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1320_NSAL1330(NSAL1320CMsg bizMsg) {
        NSAL1320CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

// START 2017/08/16 [QC#20378, ADD]
    private void doProcess_NSAL1320Scrn00_OpenWin_Customer(NSAL1320CMsg bizMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ix);

        if (!ZYPConstant.FLG_ON_Y.equals(aBizMsg.xxExstFlg_PR.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        if (!ZYPCommonFunc.hasValue(aBizMsg.dsAcctNm) || !ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            aBizMsg.dsAcctNm.setErrorInfo(2, NSAM0695W);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_CI, ZYPConstant.FLG_ON_Y);
    }
// END   2017/08/16 [QC#20378, ADD]
}
