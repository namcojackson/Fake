/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0020;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZBL0020.common.ZZBL0020CommonLogic;
import business.db.ARM10TMsg;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZBL0020BL06 extends S21BusinessHandler implements ZZBL0020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        String screenAplID = cMsg.getScreenAplID();
        try {
            if ("ZZBL0020Scrn00_CMN_Delete".equals(screenAplID)){
                doProcess_ZZBL0020Scrn00_Delete(cMsg, sMsg);
            } else if ("ZZBL0020Scrn01_CMN_Submit".equals(screenAplID)){
                doProcess_ZZBL0020Scrn01_Submit(cMsg, sMsg);
            }
        } catch(Exception e) {
//            bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        }
    }
    
    /**
     * Delete all marked records from DB
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZBL0020Scrn00_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
	  	ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
	  	ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
	  	
	   	// Copy check marks for deletion to S Msg
	  	ZZBL0020CommonLogic.copyCMsgToSMsg(cMsg, sMsg);   
		   
		ARM10TMsg arm10Msg = new ARM10TMsg();

        boolean selectFlg = false;

		// Set all deleted marked records actvFlg_A DELETED.
	  	for (int j=0; j < sesMsg.A.getValidCount(); j++) {
  			if ( sesMsg.A.no(j).xxYesNoCd_A.getValue().equals(DELETED)){
  				
  				// Mark for S MSG
  				sesMsg.A.no(j).actvFlg_A.setValue(DELETED);
  				
  				// Delete records from DB actually.
  				arm10Msg.EZREQBusinessApplicationID.setValue(sesMsg.A.no(j).ezReqBusinessID_A.getValue());
  				arm10Msg = (ARM10TMsg)EZDTBLAccessor.findByKeyForUpdate(arm10Msg);
                if (arm10Msg == null ) {
                    bizMsg.setMessageInfo("ZZZM9004E");
                        return; 
                }
                String timeBefore     = sesMsg.A.no(j).ezUpTime.getValue();
                String timeZoneBefore = sesMsg.A.no(j).ezUpTimeZone.getValue();
                String time     = arm10Msg.EZUpdateDateTime.getValue();
                String timeZone = arm10Msg.EZUpTimeZone.getValue();
                if(!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
        		if (arm10Msg != null ) {
        			EZDTBLAccessor.logicalRemove(arm10Msg);
                    String sReturnCode = arm10Msg.getReturnCode();
                    if (!sReturnCode.equals("0000")){
                        cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                        return;
                    }
                    selectFlg = true;
                    cMsg.setMessageInfo("ZZZM9003I", new String[] {"delete"});
                }
  			}

  		}
        if (selectFlg == false) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
            return;
        }
	  	
        ZZBL0020CommonLogic.getAom10(sesMsg, bizMsg);
        ZZBL0020CommonLogic.copySMsgToCMsg(sMsg, cMsg, 0);
        
   }
    
    
    /**
     * Save added/edited record to DB
     * @param cMsg
     * @param sMsg
     */
  	private void doProcess_ZZBL0020Scrn01_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
  		
  	
	  	ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
	  	ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
	  	
	  	// Append added data.
	  	if (bizMsg.actvFlg_B.getValue().equals(ADDED)) {
	  		// Check if this record exists or not. If it does, can not add.
	  		ARM10TMsg arm10Msg = new ARM10TMsg();
	  		ARM10TMsg arm10MsgDummy = new ARM10TMsg();
	  		arm10Msg.EZREQBusinessApplicationID.setValue(bizMsg.ezReqBusinessID_B.getValue());
	  		arm10MsgDummy = (ARM10TMsg)EZDTBLAccessor.findByKey(arm10Msg);
			if (arm10MsgDummy != null ) {
				bizMsg.setMessageInfo("ZZZM9015E");
					return;	
			}
  		
			// Save a record to DB
			arm10Msg.EZREQBusinessApplicationID.setValue(bizMsg.ezReqBusinessID_B.getValue());
			arm10Msg.EZREQBusinessApplicationName.setValue(bizMsg.ezReqBusinessName_B.getValue());
			arm10Msg.EZREQExecutionControlNetID.setValue(bizMsg.ezReqJobCtlNetID_B.getValue());
	    	arm10Msg.EZREQExecutionControlFlaginError.setValue(bizMsg.ezReqJobErrorCtlFlag_B.getValue());
	    	arm10Msg.EZREQJobConcurrency.setValue(bizMsg.ezReqJobConcurrency_B.getValue());
	    	arm10Msg.EZREQJobBlockingFlag.setValue(bizMsg.ezReqJobStopFlag_B.getValue());
	    	arm10Msg.EZREQExtractionCount.setValue(bizMsg.ezReqCountPerJob_B.getValue());   
	    	EZDTBLAccessor.create(arm10Msg);
            String sReturnCode = arm10Msg.getReturnCode();
            if (!sReturnCode.equals("0000")){
                cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                return;
            }
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"create"});
	  		
	  	} else if (bizMsg.actvFlg_B.getValue().equals(EDITED)) {
  		
	  		String ezReqBusinessID = bizMsg.ezReqBusinessID_B.getValue();
	  		for (int j=0; j < sesMsg.A.getValidCount(); j++) {
	  			ZZBL0020_ASMsg currentRow = sesMsg.A.no(j);
	  			if (currentRow.ezReqBusinessID_A.getValue().equals(ezReqBusinessID)) {
	  				
	  				ARM10TMsg arm10Msg = new ARM10TMsg();
	  		  		arm10Msg.EZREQBusinessApplicationID.setValue(ezReqBusinessID);
	  		  		arm10Msg = (ARM10TMsg)EZDTBLAccessor.findByKeyForUpdate(arm10Msg);
                    if (arm10Msg == null ) {
                        bizMsg.setMessageInfo("ZZZM9004E");
                            return; 
                    }
                    String timeBefore     = sesMsg.A.no(j).ezUpTime.getValue();
                    String timeZoneBefore = sesMsg.A.no(j).ezUpTimeZone.getValue();
                    String time     = arm10Msg.EZUpdateDateTime.getValue();
                    String timeZone = arm10Msg.EZUpTimeZone.getValue();
                    if(!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                        cMsg.setMessageInfo("ZZZM9004E");
                        return;
                    }

		    		arm10Msg.EZREQBusinessApplicationName.setValue(bizMsg.ezReqBusinessName_B.getValue());
                    arm10Msg.EZREQExecutionControlFlaginError.setValue(bizMsg.ezReqJobErrorCtlFlag_B.getValue());
		        	arm10Msg.EZREQJobConcurrency.setValue(bizMsg.ezReqJobConcurrency_B.getValue());
		        	arm10Msg.EZREQJobBlockingFlag.setValue(bizMsg.ezReqJobStopFlag_B.getValue());
		        	arm10Msg.EZREQExtractionCount.setValue(bizMsg.ezReqCountPerJob_B.getValue());   
		        	EZDTBLAccessor.update(arm10Msg);
                    String sReturnCode = arm10Msg.getReturnCode();
                    if (!sReturnCode.equals("0000")){
                        cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                        return;
                    }
                    cMsg.setMessageInfo("ZZZM9003I", new String[] {"update"});
	  			}
	  		}
	  	}
        ZZBL0020CommonLogic.getAom10(sesMsg, bizMsg);
        ZZBL0020CommonLogic.copySMsgToCMsg(sMsg, cMsg, 0);
    }
}