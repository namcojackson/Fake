package business.blap.ZZOL0010;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZOL0010.common.ZZOL0010CommonLogic;
import business.db.AOM02TMsg;


import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZOL0010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0010Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0010_Search(cMsg, sMsg);

            } else if ("ZZOL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZOL0010_PageNext(cMsg, sMsg);

            } else if ("ZZOL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZOL0010_PagePrev(cMsg, sMsg);

            } else if ("ZZOL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0010Scrn00_TBLColumnSort(cMsg, sMsg);

            } else if ("ZZOL0010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZOL0010Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("ZZOL0010Scrn00_Edit".equals(screenAplID)) {
                doProcess_ZZOL0010Scrn00_Edit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method: Search AOM02
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0010_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        // Keep the search condition
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        bizMsg.ezBusinessID_SB.setValue(bizMsg.ezBusinessID_00.getValue());
        bizMsg.ezCompanyCode_SB.setValue(bizMsg.ezCompanyCode.getValue());
        bizMsg.ezOnlStopFlag_SB.setValue(bizMsg.ezOnlStopFlag_SV.getValue());
        bizMsg.ezAcctInfoMode_SB.setValue(bizMsg.ezAcctInfoMode_SV.getValue());
        bizMsg.ezDebugLevel_SB.setValue(bizMsg.ezDebugLevel_SV.getValue());
        
        ZZOL0010CommonLogic.searchAOM02(cMsg, sMsg);
    }

    /**
     * Method: Page Next
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0010_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        ZZOL0010SMsg shareMsg = (ZZOL0010SMsg) sMsg;

        // Store checkbox data from CMsg to SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        int pagenationPreFrom = pagenationFrom - bizMsg.A.length();
        int j = pagenationPreFrom;
        for (; j < i; j++) {
            if (j < shareMsg.A.getValidCount()) {
                shareMsg.A.no(j).xxChkBox_A1.setValue(bizMsg.A.no(j - pagenationPreFrom).xxChkBox_A1.getValue());
            } else {
                break;
            }
        }

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

    }

    /**
     * Method: Page Prev
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0010_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        ZZOL0010SMsg shareMsg = (ZZOL0010SMsg) sMsg;

        // Store checkbox data from CMsg to SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        int pagenationPreFrom = pagenationFrom + bizMsg.A.length();
        int j = pagenationPreFrom;
        int pagenationPreTo = pagenationPreFrom + bizMsg.A.length();
        int k = pagenationPreTo;

        for (; j < k; j++) {
            shareMsg.A.no(j).xxChkBox_A1.setValue(bizMsg.A.no(j - pagenationPreFrom).xxChkBox_A1.getValue());
        }

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

    }

    /**
     * Method: TBLColumnSort
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZOL0010Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        ZZOL0010SMsg shareMsg = (ZZOL0010SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();
        
        // Store checkbox data from CMsg to SMsg
//        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
//        int k = pagenationFrom;
//        int pagenationPreFrom = pagenationFrom - bizMsg.A.length();
//        int j = pagenationPreFrom;
//        for (; j < k; j++) {
//            if (j < shareMsg.A.getValidCount()) {
//                shareMsg.A.no(j).xxChkBox_A1.setValue(bizMsg.A.no(j - pagenationPreFrom).xxChkBox_A1.getValue());
//            } else {
//                break;
//            }
//        }
        
        // copy Check Box from CMsg onto SMsg
        int pagenation = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                shareMsg.A.no(pagenation + i).xxChkBox_A1.setValue(bizMsg.A.no(i).xxChkBox_A1.getValue());
            }
        }
        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(shareMsg.A, shareMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, shareMsg.A.getValidCount());

            // Copy SMsg -> CMsg
            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }

    }

    /**
     * Method: Clear
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZOL0010Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        ZZOL0010SMsg shareMsg = (ZZOL0010SMsg) sMsg;

        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.setValidCount(0);

        return;
    }

    /**
     * Method: Edit
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZOL0010Scrn00_Edit(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;

        AOM02TMsg tMsg = new AOM02TMsg();

        tMsg.EZBusinessApplicationID.setValue(bizMsg.ezBusinessID_01.getValue());
        tMsg.EZCompanyCode.setValue(bizMsg.ezCompanyCode_01.getValue());

        EZDTBLAccessor.findByKey(tMsg);

        if (tMsg.getReturnCode().equals(RTNCD_NOT_FOUND)) {
            // TODO
            bizMsg.setMessageInfo("ZZOL0002E");

        } else {
            bizMsg.ezBusinessID_01.setValue(tMsg.EZBusinessApplicationID.getValue());
            bizMsg.ezCompanyCode_01.setValue(tMsg.EZCompanyCode.getValue());

            String startHrs = tMsg.EZOnlineOperationStartTime.getValue().substring(0, 1);
            String startMn = tMsg.EZOnlineOperationStartTime.getValue().substring(2, 3);
            String endHrs = tMsg.EZOnlineOperationEndTime.getValue().substring(0, 1);
            String endMn = tMsg.EZOnlineOperationEndTime.getValue().substring(0, 1);
            bizMsg.xxHrs_SV.setValue(startHrs);
            bizMsg.xxMn_SV.setValue(startMn);
            bizMsg.xxHrs_EV.setValue(endHrs);
            bizMsg.xxMn_EV.setValue(endMn);

            bizMsg.ezOnlStopFlag_01.setValue(tMsg.EZOnlineBlockingFlag.getValue());
            bizMsg.ezAcctInfoMode_01.setValue(tMsg.EZBillingInformationOutputType.getValue());
            bizMsg.ezDebugLevel_01.setValue(tMsg.EZDebugLevel.getValue());
        }
        return;
    }
}
