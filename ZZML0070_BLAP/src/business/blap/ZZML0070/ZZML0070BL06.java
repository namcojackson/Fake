/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */
package business.blap.ZZML0070;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0070.common.ZZML0070CommonLogic;
import business.db.ML_USR_ADDRTMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0070BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0070CMsg bizMsg = (ZZML0070CMsg) cMsg;
        ZZML0070SMsg sharedMsg = (ZZML0070SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0070Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZML0070Scrn00_CMN_Delete(bizMsg, sharedMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070Scrn00_CMN_Delete(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = cMsg.xxPageShowToNum.getValueInt();
        ZZML0070CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom, pagenationTo);

        //Delete
        int deleteCount = 0;
        int firstErrorRowIndex = -1;
        boolean unchecked = true;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!"Y".equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                continue;
            }

            unchecked = false;

            ML_USR_ADDRTMsg targetTMsg = new ML_USR_ADDRTMsg();
            targetTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
            targetTMsg.mlUsrAddrPk.setValue(sMsg.A.no(i).mlUsrAddrPk_A.getValue());

            ML_USR_ADDRTMsg checkTMsg = (ML_USR_ADDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(targetTMsg);

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
            ZZML0070CommonLogic.refreshScrn00(cMsg, sMsg, firstErrorRowIndex);
            cMsg.setCommitSMsg(true);
            return;
        }

        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});

        //ReSearch
        cMsg.glblCmpyCd_S.setValue(cMsg.glblCmpyCd.getValue());
        ZZML0070CommonLogic.searchZZML0070Scrn00(cMsg, sMsg);
    }
}
