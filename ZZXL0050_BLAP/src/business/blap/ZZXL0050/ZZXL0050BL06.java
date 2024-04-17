package business.blap.ZZXL0050;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZXL0050.ZZXL0050CMsg;
import business.blap.ZZXL0050.ZZXL0050SMsg;
import business.blap.ZZXL0050.constant.ZZXL0050Constant;
import business.db.DT_MGTTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZXL0050BL06 extends S21BusinessHandler implements ZZXL0050Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZXL0050Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_CMN_Delete((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn01_CMN_Submit((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

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
    private void doProcess_ZZXL0050Scrn00_CMN_Delete(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        String chkFlgVal = null;
        DT_MGTTMsg condMsg = new DT_MGTTMsg();

        int delCnt = 0;
        int idx = 0;
        for (; idx < sMsg.A.getValidCount(); idx++) {

            chkFlgVal = cMsg.A.no(idx).xxChkBox_A.getValue();
            if (chkFlgVal.length() == 0) {
                continue;
            }
            delCnt++;

            condMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
            condMsg.dtProcCd.setValue(sMsg.A.no(idx).dtProcCd_A.getValue());
            condMsg.dtMgtPgmId.setValue(sMsg.A.no(idx).dtMgtPgmId_A.getValue());

            // get search result
            DT_MGTTMsg dtMgtTMsg = (DT_MGTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(condMsg);
            if (dtMgtTMsg == null) {
                cMsg.setMessageInfo("ZZZM9004E");
                break;
            } else {
                // get the update date and time and update time zone
                String ezUpTime = sMsg.A.no(idx).ezUpTime_A.getValue();
                String ezUpTimeZone = sMsg.A.no(idx).ezUpTimeZone_A.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(dtMgtTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(dtMgtTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }
            }

            // logical remove recode
            EZDTBLAccessor.logicalRemove(dtMgtTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtMgtTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {dtMgtTMsg.getReturnCode() });
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
     * submit Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn01_CMN_Submit(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        boolean insFlg = false;
        boolean sameFlg = false;
        GLBL_CMPYTMsg chkMsg = new GLBL_CMPYTMsg();
        DT_MGTTMsg condMsg = new DT_MGTTMsg();
        DT_MGTTMsg dtMgtTMsg = null;

        // existence check Global Company Code
        chkMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        GLBL_CMPYTMsg glblComtTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(chkMsg);
        if ( glblComtTMsg == null ) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] { "Global Company CD" });
            return;
        }
        
        // set search parameter
        condMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());

        String dtProcCd     = cMsg.dtProcCd_01.getValue();
        String dtMgtPgmId   = cMsg.dtMgtPgmId_01.getValue();
        String sMdtProcCd   = sMsg.dtProcCd_01.getValue();
        String sMdtMgtPgmId = sMsg.dtMgtPgmId_01.getValue();
        
        // compare update value with an initial screen item value
        if ( dtProcCd.equals(sMdtProcCd) && dtMgtPgmId.equals(sMdtMgtPgmId) ) {
            sameFlg = true;
        } else if ( sMdtProcCd.length() > 0 && sMdtMgtPgmId.length() > 0 ) {
            
            condMsg.dtProcCd.setValue(sMdtProcCd);
            condMsg.dtMgtPgmId.setValue(sMdtMgtPgmId);

            dtMgtTMsg = (DT_MGTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(condMsg);
            if ( dtMgtTMsg == null ) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            } else {
                // get the update date and time and update time zone
                String ezUpTime = sMsg.ezUpTime_01.getValue();
                String ezUpTimeZone = sMsg.ezUpTimeZone_01.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(dtMgtTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(dtMgtTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
            }
            
            // logical remove recode
            EZDTBLAccessor.logicalRemove(condMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(condMsg.getReturnCode())) {
                
                cMsg.setMessageInfo("ZZZM9014E", new String[] {condMsg.getReturnCode() });
                return;
            }
        }
        
        condMsg.dtProcCd.setValue(dtProcCd);
        condMsg.dtMgtPgmId.setValue(dtMgtPgmId);

        // get search result
        dtMgtTMsg = (DT_MGTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(condMsg);
        if ( dtMgtTMsg == null && sameFlg ) {

            cMsg.setMessageInfo("ZZZM9004E");
            return;
        } else
        if ( dtMgtTMsg == null && !sameFlg ) {

            dtMgtTMsg = new DT_MGTTMsg();
            dtMgtTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            EZDMsg.copy(cMsg, "01", dtMgtTMsg, "");
            insFlg = true;
        } else
        if (  dtMgtTMsg != null && sameFlg ) {
            // get the update date and time and update time zone
            String ezUpTime = sMsg.ezUpTime_01.getValue();
            String ezUpTimeZone = sMsg.ezUpTimeZone_01.getValue();

            // check the update date and time and update time zone
            if (!ezUpTime.equals(dtMgtTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(dtMgtTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }
        }

        // set value
        dtMgtTMsg.mgtDt.setValue( cMsg.mgtDt_01.getValue() );
        
        // create/update recode
        String msgId = null;
        String msgParam = null;
        if ( insFlg ) {
            // create recode
            EZDTBLAccessor.create(dtMgtTMsg);
            msgId = "ZZZM9012E";
            msgParam = "Create";
        } else {
            // update recode
            EZDTBLAccessor.update(dtMgtTMsg);
            msgId = "ZZZM9013E";
            msgParam = "Update";
        }

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(dtMgtTMsg.getReturnCode())) {

            EZDMsg.copy(dtMgtTMsg, "", sMsg, "01");

            // set the update date and time and update time zone
            sMsg.ezUpTime_01.setValue(dtMgtTMsg.ezUpTime.getValue());
            sMsg.ezUpTimeZone_01.setValue(dtMgtTMsg.ezUpTimeZone.getValue());

            // information message
            cMsg.setMessageInfo("ZZZM9003I", new String[] { msgParam });
        } else {
            // error message
            cMsg.setMessageInfo(msgId, new String[] {dtMgtTMsg.getReturnCode() });
        }

    }

}
