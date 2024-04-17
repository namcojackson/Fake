package business.blap.ZZML0040;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0040.constant.ZZML0040Constant;
import business.db.ML_TMPLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZML0040BL06 extends S21BusinessHandler implements ZZML0040Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZML0040Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_CMN_Delete((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZML0040Scrn01_CMN_Submit((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

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
    private void doProcess_ZZML0040Scrn00_CMN_Delete(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        String chkFlgVal = null;
        
        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // get start row number of page
        int index = pagenationFrom - 1;
        
        // copy data from SMsg onto CMsg
        for (int i = index; i < index + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {

                // set checkbox value
                chkFlgVal = cMsg.A.no(i - index).xxChkBox_A.getValue();
                sMsg.A.no(i).xxChkBox_A.setValue( chkFlgVal );
            }
        }
        
        ML_TMPLTMsg tMsg = new ML_TMPLTMsg();
        String ezUpTime = null;
        String ezUpTimeZone = null;
        
        int cnt = 0;
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            chkFlgVal = sMsg.A.no(i).xxChkBox_A.getValue();
            if ( chkFlgVal.length() == 0 ) {
                continue;
            }
            cnt++;
            
            // set search parameter
            tMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd.getValue());
            tMsg.mlTmplId.setValue(sMsg.A.no(i).mlTmplId_A.getValue());
            tMsg.mlLocId.setValue(sMsg.A.no(i).mlLocId_A.getValue());

            // get search result
            ML_TMPLTMsg mlTmplTMsg = (ML_TMPLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
            if ( mlTmplTMsg == null ) {
                cMsg.setMessageInfo("ZZZM9004E");
                break;
            } else {
                // get the update date and time and update time zone
                ezUpTime = sMsg.A.no(i).ezUpTime_A.getValue();
                ezUpTimeZone = sMsg.A.no(i).ezUpTimeZone_A.getValue();

                // check the update date and time and update time zone
                if ( !ezUpTime.equals(mlTmplTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(mlTmplTMsg.ezUpTimeZone.getValue()) ) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }
            }

            // logical remove recode
            EZDTBLAccessor.logicalRemove(mlTmplTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mlTmplTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {mlTmplTMsg.getReturnCode() });
                break;
            }
        }
        
        // information message
        if ( i == sMsg.A.getValidCount() ) {
            if ( cnt > 0 ) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] { "Delete" });
            } else {
                cMsg.setMessageInfo("ZZZM9007E", new String[] { "CHECK BOX" });
            }
        }

    }

    /**
     * submit Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn01_CMN_Submit(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        boolean insFlg = false;
        String msgId = null;
        String msgParam = null;
        
        // get the update date and time and update time zone
        String ezUpTime = sMsg.ezUpTime_01.getValue();
        String ezUpTimeZone = sMsg.ezUpTimeZone_01.getValue();

        // set search parameter
        ML_TMPLTMsg tMsg = new ML_TMPLTMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_01.getValue());
        tMsg.mlTmplId.setValue(cMsg.mlTmplId_01.getValue());
        tMsg.mlLocId.setValue(cMsg.mlLocId_01.getValue());

        // get search result
        ML_TMPLTMsg mlTmplTMsg = (ML_TMPLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if ( mlTmplTMsg != null ) {
            // check the update date and time and update time zone
            if (ezUpTime != null && !ezUpTime.equals("")){
                if(!ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, mlTmplTMsg.ezUpTime.getValue(), mlTmplTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
            }
        } else {
            // new making
            mlTmplTMsg = new ML_TMPLTMsg();
            EZDMsg.copy(tMsg, null, mlTmplTMsg, null);
            insFlg = true;
        }

        // set value
        mlTmplTMsg.mlSubjTmplTxt.setValue(cMsg.mlSubjTmplTxt_01.getValue());

        // create/update recode
        if ( insFlg ) {
            // create recode
            EZDTBLAccessor.create(mlTmplTMsg);
            msgId = "ZZZM9012E";
            msgParam = "Create";
        } else {
            // update recode
            EZDTBLAccessor.update(mlTmplTMsg);
            msgId = "ZZZM9013E";
            msgParam = "Update";
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mlTmplTMsg.getReturnCode())) {
            cMsg.setMessageInfo(msgId, new String[] {mlTmplTMsg.getReturnCode() });
            return;
        }
        // information message
        cMsg.setMessageInfo("ZZZM9003I", new String[] { msgParam });

        // update ML_BODY_TMPL_TXT
        int result = ZZML0040Query.getInstance().updMlBody(cMsg);
        if ( result != 1) {
            cMsg.setMessageInfo(msgId, new String[] {Integer.toString(result) });
            return;
        }

        // set the update date and time and update time zone
        sMsg.ezUpTime_01.setValue(mlTmplTMsg.ezUpTime.getValue());
        sMsg.ezUpTimeZone_01.setValue(mlTmplTMsg.ezUpTimeZone.getValue());

    }
    
}
