package business.blap.ZZOL0010;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZOL0010.common.ZZOL0010CommonLogic;
import business.blap.ZZOL0010.constant.ZZOL0010Constant;
import business.db.AOM02TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


public class ZZOL0010BL06 extends S21BusinessHandler implements ZZOL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0010Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0010Scrn00_CMN_Delete(cMsg, sMsg);
            } else if ("ZZOL0010Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZOL0010Scrn01_CMN_Submit(cMsg, sMsg);
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
     * Method: Delete from AOM02
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Data Area
     */
    private void doProcess_ZZOL0010Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        ZZOL0010SMsg shareMsg = (ZZOL0010SMsg) sMsg;

        // copy the bizMsg value to shareMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = bizMsg.xxPageShowToNum.getValueInt();
        int i = pagenationFrom;
        int j = pagenationTo;
        for (; i < j; i++) {
            if (i < shareMsg.A.getValidCount()) {
                shareMsg.A.no(i).xxChkBox_A1.setValue(bizMsg.A.no(i - pagenationFrom).xxChkBox_A1.getValue());
            } else {
                break;
            }
        }

        // checkbox check
        boolean checkboxAvaFlag = false;
        for (int k = 0; k < shareMsg.A.getValidCount(); k++) {
            if (!shareMsg.A.no(k).xxChkBox_A1.isClear()) {
                checkboxAvaFlag = true;
                break;
            }
        }
        if (!checkboxAvaFlag) {
            bizMsg.setMessageInfo("ZZZM9007E", new String[]{"CHECK BOX"});
            return;
        }

        // Delete
        delete(bizMsg, shareMsg);

        // Show the page where the first error occured
        if ("E".equals(bizMsg.getMessageKind())) {
            int errRowNo = 0;
            for (int k = 0; k < shareMsg.A.getValidCount(); k++) {
                if (shareMsg.A.no(k).xxChkBox_A1.isError()) {
                    errRowNo = k;
                    break;
                }
            }
            
            // Find out which page does the error occured
            int maxRowNo = bizMsg.A.length();
            int pageNo = 1;
            while (errRowNo > maxRowNo - 1 ) {
                errRowNo = errRowNo - maxRowNo -1;
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
            ZZOL0010CommonLogic.searchAOM02(cMsg, sMsg);
            cMsg.setMessageInfo(null,null);
            cMsg.setMessageInfo("ZZZM9003I", new String[]{"Delete"});
        }
        
    }

    /**
     * Method: Delete data from AOM02
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param shareMsg Share Message
     */
    private void delete(ZZOL0010CMsg bizMsg, ZZOL0010SMsg shareMsg) {

        for (int i = 0; i < shareMsg.A.getValidCount(); i++) {

            if (shareMsg.A.no(i).xxChkBox_A1.getValue().equals(CHECK_ON)) {
                AOM02TMsg tMsg = new AOM02TMsg();
                tMsg.EZBusinessApplicationID.setValue(shareMsg.A.no(i).ezBusinessID_A1.getValue());
                tMsg.EZCompanyCode.setValue(shareMsg.A.no(i).ezCompanyCode_A1.getValue());

                tMsg = (AOM02TMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

                if (tMsg == null 
                        || !ZYPDateUtil.isSameTimeStamp(
                             shareMsg.A.no(i).ezUpTime_A1.getValue(), shareMsg.A.no(i).ezUpTimeZone_A1.getValue(), 
                             tMsg.EZUpdateDateTime.getValue(), tMsg.EZUpTimeZone.getValue())) {

                    shareMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "ZZZM9004E");
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

    /**
     * Method: Submit AOM02
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZOL0010Scrn01_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg)cMsg;

        ZZOL0010CommonLogic.submitAOM02(cMsg, sMsg);
                
        if (bizMsg.getMessageKind().equals("E")) {
//            cMsg.setMessageInfo(null,null);
//            cMsg.setMessageInfo("ZZZM9003I", new String[]{"Submit"});
            return;
        }
        
        ZZOL0010CommonLogic.searchAOM02(cMsg, sMsg);
        cMsg.setMessageInfo(null,null);
        cMsg.setMessageInfo("ZZZM9003I", new String[]{"Submit"});
        
    }
}
