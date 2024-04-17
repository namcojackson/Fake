/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2620;

import static business.blap.NFCL2620.constant.NFCL2620Constant.BUSINESS_ID;
import static business.blap.NFCL2620.constant.NFCL2620Constant.YYYYMMDD_PATTERN;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDSMsg;
import business.blap.NFCL2620.common.NFCL2620CommonLogic;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * AR Refund Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/08/08   Fujitsu         C.Tanaka        Update          QC#5521
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 * 2023/06/15   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2620BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFCL2620CMsg cMsg = (NFCL2620CMsg) arg0;
        NFCL2620SMsg sMsg = (NFCL2620SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL2620_INIT".equals(screenAplID)) {
                doProcess_NFCL2620_INIT(cMsg, sMsg);
                // QC#5521 ADD Start
                ZYPGUITableColumn.getColData(cMsg, sMsg);
                // QC#5521 ADD End
            } else if ("NFCL2620Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_CMN_Clear(cMsg, sMsg);
                // QC#5521 ADD Start
                ZYPGUITableColumn.getColData(cMsg, sMsg);
                // QC#5521 ADD End
            } else if ("NFCL2620Scrn00_GetCustomerNm".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_GetCustomerNm(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_Search(cMsg, sMsg);
            // QC#5521 ADD Start
            } else if ("NFCL2620Scrn00_Select_Search".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_Select_Search(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_TBLColumnSort(cMsg, sMsg);
            }
            // QC#5521 ADD End
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL2620_INIT(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {

        NFCL2620CommonLogic.clearMsg(cMsg, sMsg);

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        NFCL2620CommonLogic.createPullDown(cMsg);
        NFCL2620CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(0);

        // QC#5521 ADD Start
        NFCL2620CommonLogic.createPulldownListSaveOpt(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        // QC#5521 ADD End
    }

    private void doProcess_NFCL2620Scrn00_CMN_Clear(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {

        NFCL2620CommonLogic.clearMsg(cMsg, sMsg);

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        NFCL2620CommonLogic.createPullDown(cMsg);
        NFCL2620CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(0);

        // QC#5521 ADD Start
        NFCL2620CommonLogic.createPulldownListSaveOpt(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        // QC#5521 ADD End
    }

    private void doProcess_NFCL2620Scrn00_GetCustomerNm(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd_H)) {
            NFCL2620CommonLogic.searchAddressForBillToCustomerAccount(cMsg);
        } else {
            cMsg.dsAcctNm_H.clear();
        }
    }

    private void doProcess_NFCL2620Scrn00_PageJump(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {

        int rowIndex = NFCL2620CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NFCL2620CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NFCL2620Scrn00_PageNext(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL2620CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFCL2620CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFCL2620Scrn00_PagePrev(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL2620CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFCL2620CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }
    private void doProcess_NFCL2620Scrn00_Search(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {
        if (!NFCL2620CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }
        // START 2023/06/13 S.Fujita [QC#61486,MOD]
        boolean colReps = NFCL2620CommonLogic.isCollectionReps();
        // NFCL2620CommonLogic.getSearchData(cMsg, sMsg);
        NFCL2620CommonLogic.getSearchData(cMsg, sMsg, colReps);
        // START 2023/06/13 S.Fujita [QC#61486,MOD]

        NFCL2620CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // QC#5521 ADD Start
    private void doProcess_NFCL2620Scrn00_Select_Search(NFCL2620CMsg bizMsg, NFCL2620SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (!NFCL2620CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.arRfTpCd_H, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_H, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.arRfStsCd_H, pMsg.srchOptTxt_04);

        if (EZDCommonFunc.isNumberCheck(pMsg.srchOptTxt_01.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arRfRqstPk_H, new BigDecimal(pMsg.srchOptTxt_01.getValue()));
        } else {
            bizMsg.arRfRqstPk_H.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_05.getValue(), YYYYMMDD_PATTERN)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arRfCratDt_F, pMsg.srchOptTxt_05.getValue());
        } else {
            bizMsg.arRfCratDt_F.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_06.getValue(), YYYYMMDD_PATTERN)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arRfCratDt_T, pMsg.srchOptTxt_06.getValue());
        } else {
            bizMsg.arRfCratDt_T.clear();
        }
        // add start 2022/07/20 QC#60113
        ZYPEZDItemValueSetter.setValue(bizMsg.apPmtChkNum_H, pMsg.srchOptTxt_07);
        // add end 2022/07/20 QC#60113
        // add start 2022/08/01 QC#60113-1
        if (EZDCommonFunc.isNumberCheck(pMsg.srchOptTxt_08.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealRfAmt_H, new BigDecimal(pMsg.srchOptTxt_08.getValue()));
        } else {
            bizMsg.dealRfAmt_H.clear();
        }
        // add end 2022/08/01 QC#60113-1
    }

    private void doProcess_NFCL2620Scrn00_TBLColumnSort(NFCL2620CMsg bizMsg, NFCL2620SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NFCL2620CommonLogic.pagenation(bizMsg, glblMsg, 0);
        }
    }
    // QC#5521 ADD End
}
