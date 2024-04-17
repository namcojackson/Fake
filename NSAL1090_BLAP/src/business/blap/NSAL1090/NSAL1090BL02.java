/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1090;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1090.common.NSAL1090CommonLogic;
import business.blap.NSAL1090.constant.NSAL1090Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2018/05/31   CITS            M.Naito         Update          QC#22908
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 * 2023/07/06   CITS            T.Aizawa        Update          QC#59538
 *</pre>
 */
public class NSAL1090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1090CMsg cMsg = (NSAL1090CMsg) arg0;
        NSAL1090SMsg sMsg = (NSAL1090SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1090_INIT".equals(screenAplID)) {
                doProcess_NSAL1090_INIT(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_AllCheck".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_AllCheck(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_DetailCheck".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_DetailCheck(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_SubmitForApproval".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_SubmitForApproval(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL1090Scrn00_ViewApprovers".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_ViewApprovers(cMsg, sMsg);
            } else if ("NSAL1090_NSAL1030".equals(screenAplID)) {
                doProcess_NSAL1090_NSAL1030(cMsg, sMsg);
            } else if ("NSAL1090_NSAL1100".equals(screenAplID)) {
                doProcess_NSAL1090_NSAL1100(cMsg, sMsg);
            } else if ("NSAL1090_NSAL1120".equals(screenAplID)) {
                doProcess_NSAL1090_NSAL1120(cMsg, sMsg);
            // START 2018/05/31 M.Naito [QC#22908, ADD]
            } else if ("NSAL1090Scrn00_OpenWin_Attach".equals(screenAplID)) {
                doProcess_NSAL1090Scrn00_OpenWin_Attach(cMsg, sMsg);
            // END 2018/05/31 M.Naito [QC#22908, ADD]
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1090_INIT(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL1090CommonLogic.createPullDown(cMsg);

        NSAL1090CommonLogic.searchCrRebilInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1090Scrn00_AllCheck(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxChkBox_A, cMsg.xxChkBox_AL.getValue());
        }

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1090Scrn00_CMN_Reset(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        NSAL1090CommonLogic.searchCrRebilInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1090Scrn00_DetailCheck(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        NSAL1090CommonLogic.setCheckBox(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1090Scrn00_PageNext(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1090CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1090Scrn00_PagePrev(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1090CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1090Scrn00_SubmitForApproval(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        // START 2021/01/12/ R.Shimamoto [QC#58177, ADD]
        if (NSAL1090CommonLogic.checkFuturePE(cMsg, sMsg)) {
            // Warning
            if (ZYPCommonFunc.hasValue(cMsg.xxWrnSkipFlg)) {
                cMsg.xxWrnSkipFlg.clear();
            } else {
                //NSAM0754W
                cMsg.setMessageInfo(NSAL1090Constant.NSAM0754W);
                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                return;
            }
        }
        // END 2021/01/12/ R.Shimamoto [QC#58177, ADD]

        // START 2023/07/06 t.aizawa [QC#59538,ADD]
        if (NSAL1090CommonLogic.hasInvoiceWithMoreThanOneBillingPeriod(cMsg, sMsg)) {
            return;
        }
        // END   2023/07/06 t.aizawa [QC#59538,ADD]

        NSAL1090CommonLogic.setApprovalListParam(cMsg, sMsg);
    }

    private void doProcess_NSAL1090Scrn00_TBLColumnSort(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            NSAL1090CommonLogic.setSeqNum(sMsg);
            NSAL1090CommonLogic.setSvcCrRebilDtlPkExistsFlg(sMsg);

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    private void doProcess_NSAL1090Scrn00_ViewApprovers(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1090CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        NSAL1090CommonLogic.setApprovalListParam(cMsg, sMsg);
    }

    private void doProcess_NSAL1090_NSAL1030(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        NSAL1090CommonLogic.searchCrRebilInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1090_NSAL1100(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        NSAL1090CommonLogic.searchCrRebilInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1090_NSAL1120(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        NSAL1090CommonLogic.searchCrRebilInfo(cMsg, sMsg);
    }

    // START 2018/05/31 M.Naito [QC#22908, ADD]
    private void doProcess_NSAL1090Scrn00_OpenWin_Attach(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        NSAL1090CommonLogic.searchCrRebilInfo(cMsg, sMsg);
    }
    // END 2018/05/31 M.Naito [QC#22908, ADD]
}
