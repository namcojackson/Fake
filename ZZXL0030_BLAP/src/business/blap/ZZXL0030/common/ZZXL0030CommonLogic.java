package business.blap.ZZXL0030.common;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZXL0030.ZZXL0030CMsg;
import business.blap.ZZXL0030.ZZXL0030SMsg;
import business.blap.ZZXL0030.constant.ZZXL0030Constant;
import business.db.GLBL_CMPYTMsg;

public class ZZXL0030CommonLogic implements ZZXL0030Constant {

    static public void initCommonButtons(EZDCommonHandler handler) {
    	
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        
    }
    
   static public void changeSubmitButton(EZDCommonHandler handler, int show) {
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], show, null);
    }
   
   /**
    * Check A array of C & S, if it is same, return true, otherwise false; 
    * @param sMsg
    * @param cMsg
    * @return
    */
   public static boolean checkModifiedData(EZDCMsg cMsg, EZDSMsg sMsg, int checkFlag) {
   	
      	ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;  
	   	ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
	
	   	// No search is involved.
	   	if (sesMsg.A.getValidCount() == 0 ) {
	   		return true;
	   	}
	   	
	   	// Get each month's offset and # of days. 
	   	int offsetA =bizMsg.xxMthOfsNum_A.getValueInt();
	   	int lengthA = bizMsg.xxMthLg_A.getValueInt();
	   	int offsetB =bizMsg.xxMthOfsNum_B.getValueInt();
	   	int lengthB = bizMsg.xxMthLg_B.getValueInt();
	   	
	   	int lastdayA = offsetA+lengthA;   // First day offset of next month.
	   	int lastdayB = offsetB+lengthB;	  // Last day of next month.
	   	
	   	// int index =0;
	   	if (checkFlag == CHECK_THIS_MONTH || checkFlag == CHECK_BOTH_MONTHS ) {
		   	// Check this month's dirty bit.
		   	for(int i=offsetA;i < lastdayA; i++) {
		  			if (!sesMsg.A.no(i).xxChkBox_A.getValue().equals(bizMsg.A.no(i).xxChkBox_A.getValue())){
		       			return false;
		       	}
		   	}
		   	if (checkFlag == CHECK_THIS_MONTH ) {
		   		return true;
		   	}
	   	}
	   	
	   	if (checkFlag == CHECK_NEXT_MONTH || checkFlag == CHECK_BOTH_MONTHS ) {
		   	// Check next month's dirty bit.
		   	for(int i=offsetB;i < lastdayB; i++) {
		  		if (!sesMsg.B.no(i).xxChkBox_B.getValue().equals(bizMsg.B.no(i).xxChkBox_B.getValue())){
		       		return false;
		       	}
		   	}
	   	}
	   	// No dirty bit in two months at all.
	   	return true;
   }
   
   /**
    * Method name: checkExistGlbCmpCd
    * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
    * @param sGblCpyCd String
    * @param cMsg ZZXL0030CMsg
    * @return boolean true or false
    */
   public static boolean checkExistGlbCmpCd(ZZXL0030CMsg cMsg) {

       // Search GLBL_CMPY table by Primary Key
       GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

       tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
       tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

       if (tMsg == null) {
           return false;
       } else {
           return true;
       }
   }
}
