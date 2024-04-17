/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2170;

import static business.blap.NWAL2170.constant.NWAL2170Constant.ZZM8100I;

import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import business.blap.NWAL2170.common.NWAL2170CommonLogic;
import business.db.SELL_TO_CUSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2170BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2016/03/14   Fujitsu         M.Yamada        Update          QC#5087
 * 2017/05/30   Fujitsu         S.Ohki          Update          RS#8233
 *</pre>
 */
public class NWAL2170BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;

            if ("NWAL2170_INIT".equals(screenAplID)) {
                doProcess_NWAL2170_INIT(bizMsg);

            } else if ("NWAL2170Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_CMN_Reset(bizMsg);

            } else if ("NWAL2170Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_CMN_Save(bizMsg);

            } else if ("NWAL2170Scrn00_SearchMaintenance".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_SearchMaintenance(bizMsg);

            } else if ("NWAL2170_NMAL6780".equals(screenAplID)) {
                doProcess_NWAL2170_NMAL6780(bizMsg);

            } else if ("NWAL2170_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL2170_NWAL1130(bizMsg);

            } else if ("NWAL2170_NWAL2180".equals(screenAplID)) {
                doProcess_NWAL2170_NWAL2180(bizMsg);

            } else if ("NWAL2170Scrn00_OpenWin_SvcPricing".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_OpenWin_SvcPricing(bizMsg);

            } else if ("NWAL2170Scrn00_OnClick_MntBillAsEquip".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_OnClick_MntBillAsEquip(bizMsg);

            } else if ("NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNm".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNm(bizMsg);

            } else if ("NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNum".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNum(bizMsg);

            } else if ("NWAL2170Scrn00_OnBlur_DeriveFromCustLocCd".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_OnBlur_DeriveFromCustLocCd(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNm(NWAL2170CMsg bizMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem targetItem = bizMsg.A.no(selectIndex).dsAcctNm;

        SELL_TO_CUSTTMsg sellToCustTMsg //
        = NWAL2170CommonLogic.getSellToCustInfo(bizMsg, true, targetItem, "Customer Name", getGlobalCompanyCode());
        if (sellToCustTMsg == null) {
            return;
        }

        NWAL2170CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, sellToCustTMsg, getGlobalCompanyCode());
    }

    private void doProcess_NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNum(NWAL2170CMsg bizMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem targetItem = bizMsg.A.no(selectIndex).dsAcctNum;

        SELL_TO_CUSTTMsg sellToCustTMsg //
        = NWAL2170CommonLogic.getSellToCustInfo(bizMsg, false, targetItem, "Customer Number", getGlobalCompanyCode());
        if (sellToCustTMsg == null) {
            return;
        }

        NWAL2170CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, sellToCustTMsg, getGlobalCompanyCode());
    }

    private void doProcess_NWAL2170Scrn00_OnBlur_DeriveFromCustLocCd(NWAL2170CMsg bizMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();
        Map<String, String> billToInfo //
        = NWAL2170CommonLogic.getBillToInfo(bizMsg, bizMsg.A.no(selectIndex).soldToCustLocCd, "Location Code");
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNum, billToInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).soldToCustLocCd, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).xxBillToAcctNmSrchTxt, billToInfo.get("BILL_TO_ADDR"));
    }

    private void doProcess_NWAL2170Scrn00_OpenWin_SvcPricing(NWAL2170CMsg bizMsg) {
        if (bizMsg.getMessageInfo() != null) {
            return;
        }
        NWAL2170CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);

    }

    private void doProcess_NWAL2170Scrn00_OnClick_MntBillAsEquip(NWAL2170CMsg bizMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NWAL2170_ACMsg aBizMsg = bizMsg.A.no(ix);
        if (ZYPConstant.CHKBOX_ON_Y.equals(aBizMsg.applyEquipBillToFlg.getValue())) {
            NWAL2170CommonLogic.setBillToInfoToCustomer(aBizMsg, bizMsg);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170Scrn00_CMN_Reset(NWAL2170CMsg bizMsg) {
        NWAL2170CommonLogic.resetBizMsgBeforeInit(bizMsg);

        doProcess_NWAL2170_INIT(bizMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170Scrn00_CMN_Save(NWAL2170CMsg bizMsg) {
        //
        NWAL2170CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);

        if (bizMsg.getMessageInfo() == null) {
            bizMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170_INIT(NWAL2170CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, bizMsg.xxScrItem50Txt_P);
        if (NWAL2170CommonLogic.isImport(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefNum, bizMsg.xxScrItem50Txt);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.refCpoOrdNum, bizMsg.xxScrItem50Txt);
        }

        NWAL2170CommonLogic.createPulldownList(getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(), bizMsg);
        NWAL2170CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

    /**
     * SearchMaintenance Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170Scrn00_SearchMaintenance(NWAL2170CMsg bizMsg) {
        //
        NWAL2170CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

    /**
     * NMAL6780 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170_NMAL6780(NWAL2170CMsg bizMsg) {
        // no operation
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170_NWAL1130(NWAL2170CMsg bizMsg) {
        // no operation
    }

    /**
     * NWAL2180 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2170_NWAL2180(NWAL2170CMsg bizMsg) {
        NWAL2170CommonLogic.getInitData(getGlobalCompanyCode(), bizMsg);
    }

}
