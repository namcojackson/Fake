/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */
package business.blap.ZZML0010;

import parts.common.EZDCMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0010.common.ZZML0010CommonLogic;
import business.db.ML_SYS_CONFIGTMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0010BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0010CMsg bizMsg = (ZZML0010CMsg) cMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZML0010Scrn00_CMN_Submit(bizMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0010Scrn00_CMN_Submit(ZZML0010CMsg cMsg, EZDSMsg sMsg) {
        
        ML_SYS_CONFIGTMsg targetTMsg = new ML_SYS_CONFIGTMsg();
        EZDMsg.copy(cMsg, null, targetTMsg, null);
        ML_SYS_CONFIGTMsg checkTMsg = (ML_SYS_CONFIGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(targetTMsg);
        
        if (checkTMsg == null) {
            // In case of New record
            targetTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_S.getValue());
            targetTMsg.mlSysConfigRecId.setValue("1");
            // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            // targetTMsg.mlLocId.setValue(cMsg.langCd.getValue());
            // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            
            EZDTBLAccessor.create(targetTMsg);
            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Create"});
            } else {
                cMsg.setMessageInfo("ZZZM9012E", new String[] {targetTMsg.getReturnCode()});
                return;
            }
            // In case of update
        } else {
            if (cMsg.ezUpTime.getValue() == null || cMsg.ezUpTimeZone.getValue() == null) {
                cMsg.setMessageInfo("ZZZM9015E");
                return;
            }
            if (!checkTMsg.ezUpTime.getValue().equals(cMsg.ezUpTime.getValue())
                    || !checkTMsg.ezUpTimeZone.getValue().equals(cMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }
            // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            // targetTMsg.mlLocId.setValue(cMsg.langCd.getValue());
            // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            EZDTBLAccessor.update(targetTMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
            } else {
                cMsg.setMessageInfo("ZZZM9013E", new String[] {targetTMsg.getReturnCode()});
                return;
            }
        }
        ZZML0010CommonLogic.search(cMsg);
    }
}
