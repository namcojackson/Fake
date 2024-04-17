package business.blap.ZZIL0030;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZIL0030.common.ZZIL0030CommonLogic;
import business.db.MDB_INBD_INTFC_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZIL0030Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZIL0030Scrn00_CMN_Delete((ZZIL0030CMsg) cMsg, (ZZIL0030SMsg) sMsg);
            } else if ("ZZIL0030Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZIL0030Scrn01_CMN_Submit((ZZIL0030CMsg) cMsg, (ZZIL0030SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Method: Delete from MDB_INBD_INTFC_CONFIG <dd>The method
     * explanation: The business peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Data Area
     */
    private void doProcess_ZZIL0030Scrn00_CMN_Delete(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        ZZIL0030CMsg bizMsg = cMsg;
        ZZIL0030SMsg shareMsg = sMsg;

        // copy the bizMsg value to shareMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = bizMsg.xxPageShowToNum.getValueInt();
        int i = pagenationFrom;
        int j = pagenationTo;
        for (; i < j; i++) {
            if (i < shareMsg.A.getValidCount()) {
                shareMsg.A.no(i).xxChkBox_A.setValue(bizMsg.A.no(i - pagenationFrom).xxChkBox_A.getValue());
            } else {
                break;
            }
        }

        // checkbox check
        boolean checkboxAvaFlag = false;
        for (int k = 0; k < shareMsg.A.getValidCount(); k++) {
            if (!shareMsg.A.no(k).xxChkBox_A.isClear()) {
                checkboxAvaFlag = true;
                break;
            }
        }
        if (!checkboxAvaFlag) {
            bizMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX" });
            return;
        }

        // Delete
        delete(bizMsg, shareMsg);

        // Show the page where the first error occured
        if ("E".equals(bizMsg.getMessageKind())) {
            int errRowNo = 0;
            for (int k = 0; k < shareMsg.A.getValidCount(); k++) {
                if (shareMsg.A.no(k).xxChkBox_A.isError()) {
                    errRowNo = k;
                    break;
                }
            }

            // Find out which page does the error occured
            int maxRowNo = bizMsg.A.length();
            int pageNo = 1;
            while (errRowNo > maxRowNo - 1) {
                errRowNo = errRowNo - maxRowNo - 1;
                pageNo++;
            }

            // clear bizMsg
            bizMsg.A.clear();
            bizMsg.A.setValidCount(0);

            int postPageFrom = maxRowNo * (pageNo - 1);

            int k = postPageFrom;

            // copy data from SMsg to CMsg
            for (; k < postPageFrom + bizMsg.A.length(); k++) {
                if (k < shareMsg.A.getValidCount()) {
                    EZDMsg.copy(shareMsg.A.no(k), null, bizMsg.A.no(k - postPageFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(k - postPageFrom);

            // set value to pagenation items
            bizMsg.xxPageShowFromNum.setValue(postPageFrom + 1);
            bizMsg.xxPageShowToNum.setValue(postPageFrom + bizMsg.A.getValidCount());
            return;
        } else {
            // Search again with previous search condition.
            ZZIL0030CommonLogic.searchMaster(cMsg, sMsg);
            cMsg.setMessageInfo(null, null);
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete" });
        }

    }

    /**
     * Method: Submit MDB_INBD_INTFC_CONFIG <dd>The method
     * explanation: The business peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZIL0030Scrn01_CMN_Submit(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        ZZIL0030CommonLogic.submitIF(cMsg, sMsg, getGlobalCompanyCode());

        if (cMsg.getMessageKind().equals("E")) {
            return;
        }

        ZZIL0030CommonLogic.searchMaster(cMsg, sMsg);
        cMsg.setMessageInfo(null, null);
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Submit" });

    }

    /**
     * Method: Delete data from MDB_INBD_INTFC_CONFIG <dd>The method
     * explanation: The business peculiarity processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param shareMsg Share Message
     */
    private void delete(ZZIL0030CMsg bizMsg, ZZIL0030SMsg shareMsg) {

        for (int i = 0; i < shareMsg.A.getValidCount(); i++) {

            if (shareMsg.A.no(i).xxChkBox_A.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                MDB_INBD_INTFC_CONFIGTMsg tMsg = new MDB_INBD_INTFC_CONFIGTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.intfcId, shareMsg.A.no(i).intfcId_A.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.bizApiId, shareMsg.A.no(i).bizApiId_A.getValue());

                tMsg = (MDB_INBD_INTFC_CONFIGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                String currentTime = shareMsg.A.no(i).ezUpTime_A.getValue();
                String currentTimeZone = shareMsg.A.no(i).ezUpTimeZone_A.getValue();
                String newTime = tMsg.ezUpTime.getValue();
                String newTimeZone = tMsg.ezUpTimeZone.getValue();
                if (tMsg == null || !ZYPDateUtil.isSameTimeStamp(currentTime, currentTimeZone, newTime, newTimeZone)) {
                    shareMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "ZZZM9004E");
                    // Another user has already updated, the update
                    // processing could not be conducted.
                    bizMsg.setMessageInfo("ZZZM9004E");
                    bizMsg.setCommitSMsg(true);
                    continue;

                }

                EZDTBLAccessor.logicalRemove(tMsg);
                if (tMsg.getReturnCode().equals(RTNCD_NORMAL)) {
                    continue;
                } else {
                    bizMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
            }
        }
    }

}
