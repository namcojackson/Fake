/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */
package business.blap.ZZML0060;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0060.common.ZZML0060CommonLogic;
import business.db.GLBL_CMPYTMsg;
import business.db.ML_GRP_ADDRTMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0060CMsg bizMsg = (ZZML0060CMsg) cMsg;
        ZZML0060SMsg sharedMsg = (ZZML0060SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0060Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZML0060Scrn00_CMN_Delete(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZML0060Scrn01_CMN_Submit(bizMsg, sharedMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060Scrn00_CMN_Delete(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = cMsg.xxPageShowToNum.getValueInt();
        ZZML0060CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom, pagenationTo);

        //Delete
        int deleteCount = 0;
        int firstErrorRowIndex = -1;
        boolean unchecked = true;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!"Y".equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                continue;
            }

            unchecked = false;

            ML_GRP_ADDRTMsg targetTMsg = new ML_GRP_ADDRTMsg();
            targetTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
            targetTMsg.mlGrpId.setValue(sMsg.A.no(i).mlGrpId_A.getValue());

            ML_GRP_ADDRTMsg checkTMsg = (ML_GRP_ADDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(targetTMsg);

            if (checkTMsg == null) {
                if (firstErrorRowIndex == -1) { firstErrorRowIndex = i; }
                sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "ZZZM9004E", null);
                continue;
            } else if (!checkTMsg.ezUpTime.getValue().equals(sMsg.A.no(i).ezUpTime_A.getValue())
                    || !checkTMsg.ezUpTimeZone.getValue().equals(sMsg.A.no(i).ezUpTimeZone_A.getValue())) {
                if (firstErrorRowIndex == -1) { firstErrorRowIndex = i; }
                sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "ZZZM9004E", null);
                continue;
            }

            EZDTBLAccessor.logicalRemove(targetTMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                deleteCount++;
            } else {
                if (firstErrorRowIndex == -1) { firstErrorRowIndex = i; }
                sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "ZZZM9014E", new String[] {targetTMsg.getReturnCode()});
            }
        }

        if (unchecked) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
            return;
        } else if (firstErrorRowIndex > -1) {
            ZZML0060CommonLogic.refreshScrn00(cMsg, sMsg, firstErrorRowIndex);
            cMsg.setCommitSMsg(true);
            return;
        }

        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});

        //ReSearch
        cMsg.glblCmpyCd_S.setValue(cMsg.glblCmpyCd.getValue());
        ZZML0060CommonLogic.searchZZML0060Scrn00(cMsg, sMsg);
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060Scrn01_CMN_Submit(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        // existence check Global Company Code
        GLBL_CMPYTMsg chkMsg = new GLBL_CMPYTMsg();
        chkMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_01.getValue());
        GLBL_CMPYTMsg glblComtTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(chkMsg);
        if ( glblComtTMsg == null ) {
            cMsg.glblCmpyCd_01.setErrorInfo(1, "ZZZM9006E", new String[] { "Global Company CD" });
            return;
        }

        ML_GRP_ADDRTMsg targetTMsg = new ML_GRP_ADDRTMsg();
        EZDMsg.copy(cMsg, "01", targetTMsg, "");

        if ("Add".equals(cMsg.xxScrEventNm.getValue())) {

            EZDTBLAccessor.create(targetTMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Create"});
                ZZML0060CommonLogic.research(cMsg, sMsg);
            } else if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(targetTMsg.getReturnCode())) {
                cMsg.mlGrpId_01.setErrorInfo(1, "ZZZM9015E", null);
                cMsg.setMessageInfo("ZZZM9015E", new String[] {targetTMsg.getReturnCode()});
                return;
            } else {
                cMsg.setMessageInfo("ZZZM9012E", new String[] {targetTMsg.getReturnCode()});
                return;
            }
        } else if ("Edit".equals(cMsg.xxScrEventNm.getValue())) {

            ML_GRP_ADDRTMsg checkTMsg = (ML_GRP_ADDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(targetTMsg);

            if (checkTMsg == null) {
                cMsg.setMessageInfo("ZZZM9004E", null);
                return;
            } else if (!checkTMsg.ezUpTime.getValue().equals(cMsg.ezUpTime_01.getValue())
                    || !checkTMsg.ezUpTimeZone.getValue().equals(cMsg.ezUpTimeZone_01.getValue())) {
                cMsg.setMessageInfo("ZZZM9004E", null);
                return;
            }

            EZDTBLAccessor.update(targetTMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
            } else {
                cMsg.setMessageInfo("ZZZM9013E", new String[] {targetTMsg.getReturnCode()});
                return;
            }
            
            ZZML0060CommonLogic.research(cMsg, sMsg);
//            ZZML0060CommonLogic.refreshCurrentScrn01(cMsg, sMsg);

//            ZZML0060CommonLogic.refreshCurrentScrn00(cMsg, sMsg);
        }
    }
}
