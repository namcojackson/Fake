package business.servlet.ZZXL0030.common;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZXL0030.ZZXL0030CMsg;
import business.blap.ZZXL0030.ZZXL0030SMsg;
import business.servlet.ZZXL0030.ZZXL0030BMsg;
import business.servlet.ZZXL0030.constant.ZZXL0030Constant;

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
    * Disenable all invalid dates of month. 
    * @param bMsg
    */
   public static void disenableCheckBoxes(EZDBMsg bMsg) {
	   
	   ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
	   
	   	int offsetA =scrnMsg.xxMthOfsNum_A.getValueInt();
	   	int lengthA = scrnMsg.xxMthLg_A.getValueInt();
	   	int offsetB =scrnMsg.xxMthOfsNum_B.getValueInt();
	   	int lengthB = scrnMsg.xxMthLg_B.getValueInt();
	   	int lastdayA = offsetA+lengthA;
    	int lastdayB = offsetB+lengthB;
    	
	   	for(int i=0;i < CALENDAR_NUM; i++) {
	   		
	   		if (i >= offsetA && i < lastdayA ) {
	   			scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
	   		} else {
	   			scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
	   		}
	   		
     		if (i >= offsetB && i < lastdayB ) {
     			scrnMsg.B.no(i).xxChkBox_B.setInputProtected(false);
    		} else {
    			scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
    		}
       	}
   	}
   
   /**
    * Clear & disable all check boxes.  
    * @param bMsg
    */
   public static void disenableAllCheckBoxes(EZDBMsg bMsg) {

	   ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
	   
	   scrnMsg.A.clear();
	   scrnMsg.B.clear();
	   
	   for(int i=0;i < CALENDAR_NUM; i++) {
		   scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
		   scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
	   }
   }
   
   /**
    * Set Item name to display on message.
    * @param scrnMsg
    */
   public static void setDisplayNameForMessage(ZZXL0030BMsg scrnMsg) {
       
       scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
       scrnMsg.xxYrDt.setNameForMessage("Year");
   }
}
