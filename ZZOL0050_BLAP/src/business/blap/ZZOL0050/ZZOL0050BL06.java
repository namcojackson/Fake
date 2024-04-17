package business.blap.ZZOL0050;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0050.constant.ZZOL0050Constant;
import business.blap.ZZOL0050.ZZOL0050CMsg;
import business.blap.ZZOL0050.ZZOL0050SMsg;
import business.db.UPLD_CSV_HDRTMsg;
import business.db.UPLD_CSV_HDRTMsgArray;
import business.db.UPLD_CSV_MSTRTMsg;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsg;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsgArray;
import business.db.UPLD_CSV_RST_PROCTMsg;
import business.db.UPLD_CSV_RST_PROCTMsgArray;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0050BL06 extends S21BusinessHandler implements ZZOL0050Constant{

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0050Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_CMN_Delete((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * delete Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050Scrn00_CMN_Delete(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        String chkFlgVal = null;
        
        // get start row number of page
        int index = cMsg.xxPageShowFromNum.getValueInt() - 1;
        
        // copy data from SMsg onto CMsg
        for (int i = index; i < index + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {

                // set checkbox value
                chkFlgVal = cMsg.A.no(i - index).xxChkBox_A.getValue();
                sMsg.A.no(i).xxChkBox_A.setValue( chkFlgVal );
            }
        }
        
        UPLD_CSV_MSTRTMsg condMsg = new UPLD_CSV_MSTRTMsg();

        int delCnt = 0;
        int idx = 0;
        for (; idx < sMsg.A.getValidCount(); idx++) {

            chkFlgVal = sMsg.A.no(idx).xxChkBox_A.getValue();
            if (chkFlgVal.length() == 0) {
                continue;
            }
            delCnt++;

            condMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
            condMsg.upldCsvId.setValue(sMsg.A.no(idx).upldCsvId_A.getValue());

            // get search result
            UPLD_CSV_MSTRTMsg upldCsvMstrTMsg = (UPLD_CSV_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(condMsg);
            if (upldCsvMstrTMsg == null) {
                cMsg.setMessageInfo("ZZZM9004E");
                break;
            } else {
                // get the update date and time and update time zone
                String ezUpTime = sMsg.A.no(idx).ezUpTime_A.getValue();
                String ezUpTimeZone = sMsg.A.no(idx).ezUpTimeZone_A.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(upldCsvMstrTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(upldCsvMstrTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }
            }

            // logical remove recode
            EZDTBLAccessor.logicalRemove(upldCsvMstrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upldCsvMstrTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {upldCsvMstrTMsg.getReturnCode() });
                break;
            }
            
            if ( !delCsvHdr( cMsg, condMsg ) ) {
                break;
            }
            if ( !delCsvRstBizAppId( cMsg, condMsg ) ) {
                break;
            }
            if ( !delCsvRstProc( cMsg, condMsg ) ) {
                break;
            }
        }

        // information message
        if (idx == sMsg.A.getValidCount()) {
            if (delCnt > 0) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete" });
            } else {
                cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX" });
            }
        }
    }

    /**
     * Delete UPLD_CSV_HDR
     * @param cMsg ZZOL0050CMsg
     * @param tMsg UPLD_CSV_MSTRTMsg
     * @return boolean  true:Success false:failed
     */
    private boolean delCsvHdr(ZZOL0050CMsg cMsg, UPLD_CSV_MSTRTMsg tMsg) {
    
        UPLD_CSV_HDRTMsg cond = new UPLD_CSV_HDRTMsg();

        // Plural exclusion matter search
        cond.setSQLID("050");
        cond.setConditionValue("ezCancelFlag01", "0");
        cond.setConditionValue("glblCmpyCd01"  , tMsg.glblCmpyCd.getValue());
        cond.setConditionValue("upldCsvId01"   , tMsg.upldCsvId.getValue());
        UPLD_CSV_HDRTMsgArray array = (UPLD_CSV_HDRTMsgArray)EZDTBLAccessor.findByConditionForUpdate(cond);

        // search result
        if ( array.length() == 0 ) {
            cMsg.setMessageInfo("ZZZM9014E", new String[] { EZDTBLAccessor.RTNCD_NOT_FOUND });
            return false;
        }
        cond.clear();

        // set key parameter
        cond.glblCmpyCd.setValue( tMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  tMsg.upldCsvId.getValue() );

        // logical remove recode
        EZDTBLAccessor.logicalRemoveByPartialKey( cond );
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
            cMsg.setMessageInfo("ZZZM9014E", new String[] { cond.getReturnCode() });
            return false;
        }
        
        return true;
    }

    /**
     * Delete UPLD_CSV_RST_BIZ_APP_ID
     * @param cMsg ZZOL0050CMsg
     * @param tMsg UPLD_CSV_MSTRTMsg
     * @return boolean  true:Success false:failed
     */
    private boolean delCsvRstBizAppId(ZZOL0050CMsg cMsg, UPLD_CSV_MSTRTMsg tMsg) {

        UPLD_CSV_RST_BIZ_APP_IDTMsg cond = new UPLD_CSV_RST_BIZ_APP_IDTMsg();

        // Plural exclusion matter search
        cond.setSQLID("052");
        cond.setConditionValue("ezCancelFlag01", "0");
        cond.setConditionValue("glblCmpyCd01"  , tMsg.glblCmpyCd.getValue());
        cond.setConditionValue("upldCsvId01"   , tMsg.upldCsvId.getValue());
        UPLD_CSV_RST_BIZ_APP_IDTMsgArray array = (UPLD_CSV_RST_BIZ_APP_IDTMsgArray)EZDTBLAccessor.findByConditionForUpdate(cond);

        // search result
        if ( array.length() == 0 ) {
            return true;
        }
        cond.clear();

        // set key parameter
        cond.glblCmpyCd.setValue( tMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  tMsg.upldCsvId.getValue() );

        // logical remove recode
        EZDTBLAccessor.logicalRemoveByPartialKey( cond );
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
            cMsg.setMessageInfo("ZZZM9014E", new String[] { cond.getReturnCode() });
            return false;
        }
        
        return true;
    }

    /**
     * Delete UPLD_CSV_RST_PROC
     * @param cMsg ZZOL0050CMsg
     * @param tMsg UPLD_CSV_MSTRTMsg
     * @return boolean  true:Success false:failed
     */
    private boolean delCsvRstProc(ZZOL0050CMsg cMsg, UPLD_CSV_MSTRTMsg tMsg) {
        
        UPLD_CSV_RST_PROCTMsg cond = new UPLD_CSV_RST_PROCTMsg();

        // Plural exclusion matter search
        cond.setSQLID("052");
        cond.setConditionValue("ezCancelFlag01", "0");
        cond.setConditionValue("glblCmpyCd01"  , tMsg.glblCmpyCd.getValue());
        cond.setConditionValue("upldCsvId01"   , tMsg.upldCsvId.getValue());
        UPLD_CSV_RST_PROCTMsgArray array = (UPLD_CSV_RST_PROCTMsgArray)EZDTBLAccessor.findByConditionForUpdate(cond);

        // search result
        if ( array.length() == 0 ) {
            return true;
        }
        cond.clear();

        // set key parameter
        cond.glblCmpyCd.setValue( tMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  tMsg.upldCsvId.getValue() );

        // logical remove recode
        EZDTBLAccessor.logicalRemoveByPartialKey( cond );
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
            cMsg.setMessageInfo("ZZZM9014E", new String[] { cond.getReturnCode() });
            return false;
        }
        
        return true;
    }

}
