package business.blap.ZZBL0010.common;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZBL0010.ZZBL0010CMsg;
import business.db.GLBL_CMPYTMsg;

public class ZZBL0010CommonLogic {
   
   /**
    * Method name: checkExistGlbCmpCd
    * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
    * @param sGblCpyCd String
    * @param cMsg ZZXL0030CMsg
    * @return boolean true or false
    */
   public static boolean checkExistGlbCmpCd(ZZBL0010CMsg cMsg) {

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
