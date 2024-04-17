package business.blap.NYEL0050.common;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NYEL0050.NYEL0050CMsg;
import business.db.GLBL_CMPYTMsg;

public class NYEL0050CommonLogic {
	
   /**
    * Method name: checkExistGlbCmpCd
    * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
    * @param sGblCpyCd String
    * @param cMsg ZZXL0030CMsg
    * @return boolean true or false
    */
   public static boolean checkExistGlbCmpCd(NYEL0050CMsg cMsg) {

       // Search GLBL_CMPY table by Primary Key
       GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

       tMsg.glblCmpyCd.setValue(cMsg.A.no(0).glblCmpyCd.getValue());
       tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

       if (tMsg == null) {
           return false;
       } else {
           return true;
       }
   }
  
}
