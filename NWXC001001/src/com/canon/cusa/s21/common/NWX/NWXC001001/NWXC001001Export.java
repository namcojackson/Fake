/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * They are common parts in OM concerning Export.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2009   Fujitsu         S.Sugino        Create          N/A
 * 12/08/2009   Fujitsu         S.Sugino        Update          N/A
 * 12/18/2009   Fujitsu         S.Sugino        Update          N/A
 * 10/01/2010   Fujitsu         K.Tajima        Update          Performance tuning
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.Map;

import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001Export {

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001Export.class);
    
    /**
     * <pre>
     * When the country of Global Company and the country of the shipment destination are different, it is assumed Export.
     * </pre>
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   ctryCd          CTRY_CD
     * @return  true:export / false: not export
     */
    public static boolean isExportForCtry(String glblCmpyCd, String ctryCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);

        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        final String glblCmpyCtryCd;
        if (glblCmpyTMsg != null) {
            glblCmpyCtryCd = glblCmpyTMsg.ctryCd.getValue();
        } else {
            glblCmpyCtryCd = "";
        }
        
        return !glblCmpyCtryCd.equals(ctryCd);
    }
    
    /**
     * <pre>
     * When the country of Global Company and the country of the sell destination are different, it is assumed Export.
     * </pre>
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   sellToCustCd    SELL_TO_CUST_CD
     * @return  true:export / false: not export
     */
    public static boolean isExportForSellTo(String glblCmpyCd, String sellToCustCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("sellToCustCd", sellToCustCd);

        final String ctryCd = (String) ssmClient.queryObject("getSellToCtryCd", ssmParam);

        return isExportForCtry(glblCmpyCd, ctryCd);
    }

    /**
     * <pre>
     * When the country of Global Company and the country of the shipment destination are different, it is assumed Export.
     * </pre>
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   shipToCustCd    SHIP_TO_CUST_CD
     * @return  true:export / false: not export
     */
    public static boolean isExportForShipTo(String glblCmpyCd, String shipToCustCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);

        final String ctryCd = (String) ssmClient.queryObject("getShipToCtryCd", ssmParam);

        return isExportForCtry(glblCmpyCd, ctryCd);
    }

}
