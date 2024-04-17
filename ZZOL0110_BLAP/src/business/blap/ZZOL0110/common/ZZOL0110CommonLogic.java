/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/31/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0110.common;

import java.util.HashMap;

import parts.common.EZDTMsg;
import business.blap.ZZOL0110.ZZOL0110CMsg;
import business.blap.ZZOL0110.constant.ZZOL0110Constant;

import com.canon.cusa.s21.common.ZZX.ZZXC001001.ZZXC001002;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;


public class ZZOL0110CommonLogic implements ZZOL0110Constant {

    /**
     * Method name: getGlbCmpNm
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param sGblCpyCd String
     * @param cMsg ZZOL0110CMsg
     * @return boolean true or false
     */
    public static boolean getGlbCmpNm(String sGblCpyCd, ZZOL0110CMsg cMsg) {

        // Init
        cMsg.glblCmpyNm_X1.setValue("");
        
        String strSysFlg = ZZXC001002.SYS_FLG;

        // PARTS
        if (ZZXC001002.SYS_FLG_PARTS.contains(strSysFlg)) {
            return true;

        // S21
        } else {  
            // Search GLBL_CMPY table by Primary Key            
            try {
                HashMap<String,String> key = new HashMap<String,String>();
                key.put("GLBL_CMPY_CD", sGblCpyCd);
                EZDTMsg findByKey = ZYPCodeDataUtil.findByKey("GLBL_CMPY", key);
                String strMsg = findByKey.getValueString("glblCmpyNm", 0);

                if (strMsg.length() > 0) {
                    cMsg.glblCmpyNm_X1.setValue(strMsg);
                    return true;
                }
                return false;
               
            } catch (IllegalArgumentException e) {
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
