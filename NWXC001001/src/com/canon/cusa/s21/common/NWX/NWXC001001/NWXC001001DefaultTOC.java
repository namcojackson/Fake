/**
 * <pre>
 * Get Default TOC Code
 * 
 * Date         Company          Name              Create/Update  Defect No
 * ------------------------------------------------------------------------
 * 07/02/2009   Fujitsu          M.Nakamura        Create         N/A
 * 10/14/2009   Fujitsu          M.Nakamura        Update         ---
 * 11/18/2009   Fujitsu          T.Kaneda          Update         use Cash
 * 10/01/2010   Fujitsu          K.Tajima          Update         Performance tuning
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001DefaultTOC implements RGTN_STS {

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001DefaultTOC.class);
    
    /**
     * get Default TOC Code
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   mdseCd          MDSE_CD
     * @param   sellToCustCd    SELL_TO_CUST_CD
     * @param   billToCustCd    BILL_TO_CUST_CD
     * @return  tocCd
     */
    public static String getDefaultTOC(String glblCmpyCd, String mdseCd, String sellToCustCd, String billToCustCd) {

        if (!hasValue(glblCmpyCd) || !hasValue(mdseCd) || !hasValue(sellToCustCd) || !hasValue(billToCustCd)) {
            return null;
        }

        // MDSE
        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return null;
        }
        
        // TOC_CD
        return getTocCd(glblCmpyCd, sellToCustCd, billToCustCd, mdseTMsg);
    }

    private static final MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        
        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        
        if (mdseTMsg == null) {
            return null;
            
        } else if(!asList(READY_FOR_ORDER_TAKING, TERMINATED).contains(mdseTMsg.rgtnStsCd.getValue())) {
            return null;
            
        } else {
            return mdseTMsg;
        }
    }
    
    @SuppressWarnings("unchecked")
    private static final String getTocCd(String glblCmpyCd, String sellToCustCd, String billToCustCd, MDSETMsg mdseTMsg) {
        
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",       glblCmpyCd);
        ssmParam.put("zerothProdCtrlCd", mdseTMsg.zerothProdCtrlCd.getValue());
        ssmParam.put("firstProdCtrlCd",  mdseTMsg.firstProdCtrlCd.getValue());
        ssmParam.put("scdProdCtrlCd",    mdseTMsg.scdProdCtrlCd.getValue());
        ssmParam.put("sellToCustCd",     sellToCustCd);
        ssmParam.put("billToCustCd",     billToCustCd);

        final List<String> tocCdList = ssmClient.queryObjectList("getTocCdList", ssmParam);
        
        if (isEmpty(tocCdList)) {
            return null;
        } else {
            return tocCdList.get(0);
        }
    }
    
    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }
    
}
