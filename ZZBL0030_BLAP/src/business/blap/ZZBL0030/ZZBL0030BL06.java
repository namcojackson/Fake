/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0030;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZBL0030.common.ZZBL0030CommonLogic;
import business.db.ART10TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZBL0030BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        String screenAplID = cMsg.getScreenAplID();
        
        try {
            
            if ("ZZBL0030Scrn00_CMN_Submit".equals(screenAplID)){
                doProcess_ZZBL0030Scrn00_Submit(cMsg, sMsg);
	        } else {                
	            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
	        }
        } catch(Exception e) {
//            bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        }
    }
    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZBL0030Scrn00_Submit(EZDCMsg cMsg, EZDSMsg sMsg ) {
        
        ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;
        
        // Copy changed part on screen to S Msg
        ZZBL0030CommonLogic.copyCMsgtoSMsg(cMsg, sMsg);   

        boolean selectFlg = false;

        // Update the changed to DB
        ART10TMsg art10Msg = new ART10TMsg();
        for(int i=0; i < sesMsg.A.getValidCount(); i ++){
            if (!sesMsg.A.no(i).ezReqJobStatus_A.getValue().equals(sesMsg.A.no(i).ezReqJobStatus_BK.getValue())) {
                
            	art10Msg.EZREQBusinessApplicationID.setValue(sesMsg.A.no(i).ezReqBusinessName_A.getValue());
            	art10Msg.EZREQRegisteredDepartmentCode.setValue(sesMsg.A.no(i).ezReqInputDeptCode_A.getValue());
            	art10Msg.EZREQRegisteredUserID.setValue(sesMsg.A.no(i).ezReqInputUserID_A.getValue());
            	art10Msg.EZREQRegisteredDate.setValue(sesMsg.A.no(i).ezReqInputDate_A.getValue());
            	art10Msg.EZREQRegisteredTime.setValue(sesMsg.A.no(i).ezReqInputTime_A.getValue());
            	art10Msg.EZREQUserKey.setValue(sesMsg.A.no(i).ezReqUserKeyItem_A.getValue());    
                art10Msg = (ART10TMsg)EZDTBLAccessor.findByKeyForUpdate(art10Msg);
                if (art10Msg == null ) {
                    cMsg.setMessageInfo("ZZZM9004E");
                        return; 
                }
                String timeBefore     = sesMsg.A.no(i).ezUpTime.getValue();
                String timeZoneBefore = sesMsg.A.no(i).ezUpTimeZone.getValue();
                String time     = art10Msg.EZUpdateDateTime.getValue();
                String timeZone = art10Msg.EZUpTimeZone.getValue();
                if(!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }            	

                art10Msg.EZREQProcessStatus.setValue(sesMsg.A.no(i).ezReqJobStatus_A.getValue());
        		EZDTBLAccessor.update(art10Msg);
                String sReturnCode = art10Msg.getReturnCode();
                if (!sReturnCode.equals("0000")){
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }

                selectFlg = true;
        	}
        }
        if (selectFlg == true) {
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"update"});
        }
        ZZBL0030CommonLogic.getAot10(sMsg, cMsg);
        // clear all dirty bits
        for(int i=0;i < sesMsg.A.getValidCount(); i++) {
	   		sesMsg.A.no(i).actvFlg_A.setValue("");
	   	}
    }
}