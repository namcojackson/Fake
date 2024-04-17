/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0110;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZIL0110.common.ZZIL0110CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.blap.ZZIL0110.common.InternalInterfaceMasterTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0110BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZIL0110Scrn01_Add".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_Add((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn01_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_CMN_Delete((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * add processing
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZIL0110Scrn01_Add(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        // Add
        String glblCmpyCd = getGlobalCompanyCode();
        
        InternalInterfaceMasterTableAccessor accessor = new InternalInterfaceMasterTableAccessor(cMsg.itrlIntfcTrxConfigId_BS.getValue());
        if(accessor.makeMasterRecord(cMsg.itrlIntfcId_B1.getValue(), cMsg.itrlIntfcTrxTblId_B1.getValue(), cMsg.itrlIntfcTrxConfigId_BS.getValue())){
            
        }
        
        ZZIL0110CommonLogic.setIntfctblidToMsg(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * delete processing
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZIL0110Scrn01_CMN_Delete(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(cMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);
        InternalInterfaceMasterTableAccessor accessor = new InternalInterfaceMasterTableAccessor(cMsg.itrlIntfcTrxConfigId_BS.getValue());
        // Delete checked rows
        for (int i = 0; i < selectRows.size(); i++) {
            accessor.cancelMasterRecord(cMsg.itrlIntfcId_B1.getValue(), cMsg.B.no(selectRows.get(i)).itrlIntfcTblId_B.getValue(), cMsg.itrlIntfcTrxConfigId_BS.getValue());
        }

        // Reset List
        ZZIL0110CommonLogic.setIntfctblidToMsg(cMsg, sMsg, glblCmpyCd);
    }
}
