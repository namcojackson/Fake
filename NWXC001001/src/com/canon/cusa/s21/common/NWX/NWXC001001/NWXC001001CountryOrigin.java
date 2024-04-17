/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/23/2009   Fujitsu         T.Tanaka        Create          N/A
 * 10/01/2010   FUjitsu         K.Tajima        Update          Performance tuning
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.Map;

import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001CountryOrigin implements RGTN_STS {

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001CountryOrigin.class);
    
    /**
     * chkCountryOrigin
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   mdseCd          MDSE_CD
     * @param   shipToCustCd    SHIP_TO_CUST_CD
     * @param   sellToCustCd    SELL_TO_CUST_CD
     * @param   billToCustCd    BILL_TO_CUST_CD
     * @return  boolean
     */
    public static boolean chkCountryOrigin(String glblCmpyCd, String mdseCd, String sellToCustCd, String billToCustCd) {

        if (!hasValue(glblCmpyCd) || !hasValue(mdseCd) || !hasValue(sellToCustCd) || !hasValue(billToCustCd)) {
            return false;
        }

        // MDSE
        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return false;
        }
        
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("origCtryCd",   mdseTMsg.origCtryCd.getValue());
        ssmParam.put("sellToCustCd", sellToCustCd);
        ssmParam.put("billToCustCd", billToCustCd);

        return (Integer) ssmClient.queryObject("CntCtryOrigRst", ssmParam) > 0;
    }
    
    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        
        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return null;
            
        } else if (!READY_FOR_ORDER_TAKING.equals(mdseTMsg.rgtnStsCd.getValue())){
            return null;
            
        } else {
            return mdseTMsg;
        }
    }
    

}
