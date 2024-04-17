package business.blap.NFCL3040;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;

import business.blap.NFCL3040.common.NFCL3040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/13   Hitachi         J.Kim           Create          Initial
 *</pre>
 */
public class NFCL3040BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            NFCL3040CMsg bizMsg = (NFCL3040CMsg) cMsg;
            NFCL3040SMsg glblMsg = (NFCL3040SMsg) sMsg;

            if ("NFCL3040_INIT".equals(screenAplID)) {
                doProcess_NFCL3040_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");

            } else if ("NFCL3040Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFCL3040Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NFCL3040Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL3040Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NFCL3040Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL3040Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NFCL3040Scrn00_Click_Search".equals(screenAplID)) {
                doProcess_NFCL3040Scrn00_Click_Search(bizMsg, glblMsg);

            } else if ("NFCL3040_NFCL3020".equals(screenAplID)) {
                doProcess_NFCL3040_NFCL3020(bizMsg, glblMsg);

            } else if ("NFCL3040Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL3040Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NFCL3040Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL3040Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3040_INIT(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        bizMsg.dsBankAcctTpCd.setValue(DS_BANK_ACCT_TP.INTERNAL);

        bizMsg.arRcptSrcCd_H.clear();
        bizMsg.arRcptSrcCd_LC.clear();
        bizMsg.arRcptSrcDescTxt_LD.clear();
        bizMsg.arBatRcptNm_H.clear();
        bizMsg.arBatRcptStsCd_H.clear();
        bizMsg.arBatRcptStsCd_LC.clear();
        bizMsg.arBatRcptStsNm_LD.clear();
        bizMsg.arLockBoxFileNm_H.clear();
        bizMsg.arLockBoxCd_H.clear();
        bizMsg.arLockBoxCd_LC.clear();
        bizMsg.arLockBoxNm_LD.clear();
        bizMsg.arLockBoxBatNum_H.clear();

        bizMsg.cratDt_H1.clear();
        bizMsg.cratDt_H2.clear();
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        bizMsg.dsBankAcctNm_H.clear();
        bizMsg.dsBankAcctNum_H.clear();
        bizMsg.dsBankBrNm_H.clear();

        // Receipt Source
        ZYPCodeDataUtil.createPulldownList(AR_RCPT_SRC.class, bizMsg.arRcptSrcCd_LC, bizMsg.arRcptSrcDescTxt_LD);
        // Batch Status
        ZYPCodeDataUtil.createPulldownList(AR_BAT_RCPT_STS.class, bizMsg.arBatRcptStsCd_LC, bizMsg.arBatRcptStsNm_LD);
        // Lockbox
        ZYPCodeDataUtil.createPulldownList(AR_LOCK_BOX.class, bizMsg.arLockBoxCd_LC, bizMsg.arLockBoxNm_LD);

        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
    }

    private void doProcess_NFCL3040Scrn00_TBLColumnSort(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NFCL3040CommonLogic.pagenation(bizMsg, glblMsg, 0);
        }
    }

    private void doProcess_NFCL3040Scrn00_CMN_Reset(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {
        doProcess_NFCL3040_INIT(bizMsg, glblMsg);
    }

    private void doProcess_NFCL3040Scrn00_PageNext(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        EZDCMsgArray bizMsgAry = bizMsg.A;
        EZDSMsgArray shareMsgAry = glblMsg.A;

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() + bizMsgAry.length());

        NFCL3040CommonLogic.dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    private void doProcess_NFCL3040Scrn00_PagePrev(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        EZDCMsgArray bizMsgAry = bizMsg.A;
        EZDSMsgArray shareMsgAry = glblMsg.A;

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsgAry.length());

        NFCL3040CommonLogic.dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    private void doProcess_NFCL3040Scrn00_Click_Search(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        NFCL3040CommonLogic.searchBatchList(bizMsg, glblMsg);
    }

    private void doProcess_NFCL3040_NFCL3020(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        NFCL3040CommonLogic.searchBatchList(bizMsg, glblMsg);
    }

    private void doProcess_NFCL3040Scrn00_CMN_Download(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        NFCL3040CommonLogic.download(bizMsg, glblMsg);

    }
}
