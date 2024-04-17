package business.blap.NWCL0130;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWCL0130.common.NWCL0130CommonLogic;
import business.blap.NWCL0130.NWCL0130CMsg;
import business.blap.NWCL0130.NWCL0130SMsg;
import business.blap.NWCL0130.constant.NWCL0130Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWCL0130BL06 extends S21BusinessHandler implements NWCL0130Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
        NWCL0130CMsg bizMsg = (NWCL0130CMsg) cMsg;
        String scrnAplID = bizMsg.getScreenAplID();
        try {
    		if ("NWCL0130Scrn00_CMN_Submit".equals(scrnAplID)) {
            	NWCL0130Scrn00_CMN_Submit(bizMsg, (NWCL0130SMsg) sMsg);
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
    private void NWCL0130Scrn00_CMN_Submit(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg) {

        NWCL0130CommonLogic.copyFromCMsgToSMsg(bizMsg, sMsg, false);

    	// #####################
    	// Input Check
    	// #####################
    	// Header
        boolean hasError = checkInput_Header(bizMsg);
    	if (hasError) {
    		return;
    	}
    	//hasError = checkInput_Detail(bizMsg, sMsg);
    	//if (hasError) {
    	//	return;
    	//}
    	
    	// #####################
    	// Update
    	// #####################
    	// Detail
   		for (int i = 0; i < sMsg.B.getValidCount(); i++) {
   			boolean isFound = false;
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(j).conslBillRgnrPk_A1) && sMsg.B.no(i).conslBillRgnrPk_A1.getValue().equals(sMsg.A.no(j).conslBillRgnrPk_A1.getValue())) {
   		        	isFound = true;
                    //update.
	        		//NWCL0130CommonLogic.updateDetail(getGlobalCompanyCode(), bizMsg, j);
   		        }
   		    }
   		    if (isFound == false) {
   		    	if(!NWCL0130CommonLogic.deleteDetail(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
   		    	    return;
   		    	}
   		    }
   		}
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).conslBillRgnrPk_A1.getValue())) {
                if (!NWCL0130CommonLogic.insertDetail(getGlobalCompanyCode(), bizMsg, sMsg, i)) {
   		    	    return;
	            }
	        }
		}
        //Search
        NWCL0130CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());
        
        //NMAM8182I=0,[@] process ended normally.
		bizMsg.setMessageInfo("NMAM8182I", new String[]{"Special Billing Regeneration Request Update"});
   		
    }
    
    private boolean checkInput_Header(NWCL0130CMsg bizMsg) {
    	
        boolean errorFlg = false;
        
        if (errorFlg) {
        	return true;
        }
        
        return false;
    }

    private boolean checkInput_Detail(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg) {

        boolean validationFlg = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String key = NWCL0130CommonLogic.getDuplicatedKeyString(bizMsg.A.no(i));
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                String key2 = NWCL0130CommonLogic.getDuplicatedKeyString(bizMsg.A.no(j));
            	if (key.equals(key2) && i != j) {
            	    bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Line"});
            	    bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Line"});
                    validationFlg = true;
            	}
            }
        }
        
        return validationFlg;
    }
}
