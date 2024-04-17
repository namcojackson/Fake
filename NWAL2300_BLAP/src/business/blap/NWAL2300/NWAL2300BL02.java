/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2300;


import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2300.common.NWAL2300CommonLogic;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2300BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL2300BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2300CMsg bizMsg = (NWAL2300CMsg) cMsg;

            if ("NWAL2300_INIT".equals(screenAplID)) {
                doProcess_NWAL2300_INIT(bizMsg);

            } else if ("NWAL2300Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2300Scrn00_CMN_Clear(bizMsg);

            // START 2022/04/18 J.Evangelista [QC#59934,DEL]
//            } else if ("NWAL2300Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL2300Scrn00_CMN_Reset(bizMsg);
            // END 2022/04/18 J.Evangelista [QC#59934,DEL]

            } else if ("NWAL2300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2300Scrn00_CMN_Submit(bizMsg);

            } else if ("NWAL2300Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL2300Scrn00_Search(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2300_INIT(NWAL2300CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NWAL2300CommonLogic.initField(bizMsg, getUserProfileService());
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_H1, bizMsg.cpoOrdNum_BK);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H1)) {
            // search
            if (NWAL2300CommonLogic.errChkSearch(bizMsg)) {
                return;
            }
            search(bizMsg);
        }

        // Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        glblTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ccyMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            ccyMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
            ccyMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                bizMsg.aftDeclPntDigitNum.setValue(ccyMsg.aftDeclPntDigitNum.getValueInt());
            }
        }
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2300Scrn00_CMN_Clear(NWAL2300CMsg bizMsg) {
        NWAL2300CommonLogic.initField(bizMsg, getUserProfileService());
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    // START 2022/04/18 J.Evangelista [QC#59934,DEL]
//    private void doProcess_NWAL2300Scrn00_CMN_Reset(NWAL2300CMsg bizMsg) {
//        doProcess_NWAL2300_INIT(bizMsg);
//    }
    // END 2022/04/18 J.Evangelista [QC#59934,DEL]

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2300Scrn00_CMN_Submit(NWAL2300CMsg bizMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_H1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_H2.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_H3.getValue())) {
            // search
            search(bizMsg);
            bizMsg.setMessageInfo(null); // Clear Waring Message
        }
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2300Scrn00_Search(NWAL2300CMsg bizMsg) {
        // Clear value
        String cpoOrdNum = bizMsg.cpoOrdNum_H1.getValue();
        NWAL2300CommonLogic.initField(bizMsg, getUserProfileService());
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_H1, cpoOrdNum);

        // search
        if (NWAL2300CommonLogic.errChkSearch(bizMsg)) {
            return;
        }
        search(bizMsg);
    }

    /**
     * Search
     * @param bizMsg Business Msg
     */
    private void search(NWAL2300CMsg bizMsg) {

        // credit&Rebill information search
        if (!NWAL2300CommonLogic.creditRebillInformationSearch(bizMsg)) {
            return;
        }
        // Optional order detail search
        NWAL2300CommonLogic.optionalOrderDetailSearch(bizMsg);
        // Original Order Profitability Details Search
        NWAL2300CommonLogic.originalOrderProfitabilityDetailsSearch(bizMsg);
        // invoice Summary Search
        NWAL2300CommonLogic.invoiceSummarySearch(bizMsg);
        // invoice Line Search
        NWAL2300CommonLogic.invoiceLineSearch(bizMsg);
        // set Reason Code
        NWAL2300CommonLogic.setReasonCode(bizMsg);
        // Set Payment Term Cash Discount Header
        NWAL2300CommonLogic.setPmtTermCashDiscCdHder(bizMsg);
        // QC#22106 2018/03/30 Add Start
        NWAL2300CommonLogic.invoiceLineReNumbering(bizMsg);
        // QC#22106 2018/03/30 Add End
    }
}
