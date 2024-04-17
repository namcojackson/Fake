/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/07/2015   Fujitsu         T.Tanaka        Create          N/A
 * 2019/09/21   Fujitsu         S.Takami        Update          QC#53627 (without any comments)
 * </pre>
 */
package business.blap.NFCL5050;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL5050.constant.NFCL5050Constant;
//import business.blap.ZZIL0100.ZZIL0100CMsg;
//import business.blap.ZZIL0100.ZZIL0100SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * NFCL5050BL02.
 */
public class NFCL5050BL06 extends S21BusinessHandler implements NFCL5050Constant {
    
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            
            if ("NFCL5050Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData( cMsg, sMsg);
            } else if ("NFCL5050Scrn00_CMN_ColClear".equals(screenAplID)){
                ZYPGUITableColumn.clearColData( cMsg,  sMsg);
            }


        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
}
