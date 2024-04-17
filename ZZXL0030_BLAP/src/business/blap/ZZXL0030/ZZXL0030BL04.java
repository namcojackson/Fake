package business.blap.ZZXL0030;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZXL0030.common.ZZXL0030CommonLogic;
import business.blap.ZZXL0030.constant.ZZXL0030Constant;
import business.db.CALTMsg;
import parts.dbcommon.EZDTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZXL0030BL04 extends S21BusinessHandler implements ZZXL0030Constant {

	private DecimalFormat dfMonth = new DecimalFormat("00");
	
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg; 
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
    	
    	String screenAplID = cMsg.getScreenAplID();
        
        try {
            
            if ("ZZXL0030Scrn00_CMN_Submit".equals(screenAplID)){
            	
                // Check Global Copmany Code is exist or not.
                if (!ZZXL0030CommonLogic.checkExistGlbCmpCd(bizMsg)) {
                    bizMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code"});
                    return;
                }
            	if (!sesMsg.calTpCd.getValue().equals(bizMsg.calTpCd.getValue())) {
            		// calTpCd in search is different from current seletion on screen.
            		bizMsg.setMessageInfo(CHANGETYPE_MSG);
            		bizMsg.xxLastBtnNm.setValue(SUBMIT_BUTTON);
            		return;
            	}
            	
                doProcess_ZZXL0030Scrn00_Submit(cMsg, sMsg, true);
                doProcess_ZZXL0030Scrn00_Submit(cMsg, sMsg, false);
                bizMsg.xxLastBtnNm.clear();

	        } else {                
	            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
	        }
        } catch(Exception e) {
//            bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        }
    }
    
    /**
     * Store all data of specific month from screen to DB. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param arrayA boolean T: array A(this month) F: array B (next month)
     */
    private void doProcess_ZZXL0030Scrn00_Submit(EZDCMsg cMsg, EZDSMsg sMsg, boolean arrayA) {
        
    	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg; 
    	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
    	
        // Update the changed to DB
    	CALTMsg calTMsg = new CALTMsg();
    	
    	// Only for S21
		calTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
		
		calTMsg.calTpCd.setValue(bizMsg.calTpCd.getValue());
		calTMsg.dtAttrbCd.setValue(ATTRIB_CD);
		
    	int nYear;
    	int nMonth;
      	if (arrayA) { 	// This month's offset & length
      	   	nYear = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(0,4));
        	nMonth = (int)Integer.parseInt(bizMsg.xxYrMth_01.getValue().substring(4,6));
      	} else {		// Next month's offset & length
     	   	nYear = (int)Integer.parseInt(bizMsg.xxYrMth_02.getValue().substring(0,4));
        	nMonth = (int)Integer.parseInt(bizMsg.xxYrMth_02.getValue().substring(4,6));
      	}
 
      	GregorianCalendar calSelectMonth = new GregorianCalendar(nYear, nMonth-1, 1);
    	
    	int offset =calSelectMonth.get(Calendar.DAY_OF_WEEK)-1; 
    	int length = calSelectMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
    	int lastday = offset+length;
    	
    	String checkMark;
		int dayNum=1;
    	for (int i=offset; i < lastday; i++, dayNum++) {
    		
    		if (i != offset) {
    			calSelectMonth.add(Calendar.DAY_OF_YEAR, 1);
    		}
    		
  			if (arrayA) {   // This month
  				sesMsg.A.no(i).xxChkBox_A.setValue(bizMsg.A.no(i).xxChkBox_A.getValue());
   				checkMark = bizMsg.A.no(i).xxChkBox_A.getValue().equals("Y")? "1" : "0";
			} else {		// Next month
				sesMsg.B.no(i).xxChkBox_B.setValue(bizMsg.B.no(i).xxChkBox_B.getValue());
				checkMark = bizMsg.B.no(i).xxChkBox_B.getValue().equals("Y")? "1" : "0";
			}
  			
  			// Claculate the current date and set it to calDt.
    		String dateString = nYear+dfMonth.format(nMonth)+dfMonth.format(dayNum);
    		calTMsg.calDt.setValue(dateString);

    		// Search one day information from DB.
    		calTMsg = (CALTMsg)EZDTBLAccessor.findByKeyForUpdate(calTMsg);
    		if (calTMsg == null ) { // No data on DB.
    			
    			// Insertion
    			calTMsg = new CALTMsg();
    			
    			// Only for S21
    			calTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
    			
    			calTMsg.calTpCd.setValue(bizMsg.calTpCd.getValue());
    			calTMsg.dtAttrbCd.setValue(ATTRIB_CD);
    			calTMsg.calDt.setValue(dateString);
    			calTMsg.dtAttrbValCd.setValue(checkMark);
    			
    			EZDTBLAccessor.insert(calTMsg);
    			
    		} else {
    			
    			// Update
    			if (!calTMsg.dtAttrbValCd.getValue().equals(checkMark)) {
    				calTMsg.dtAttrbValCd.setValue(checkMark);
           			EZDTBLAccessor.update(calTMsg);
    			}
    		}
    	}
    }
}