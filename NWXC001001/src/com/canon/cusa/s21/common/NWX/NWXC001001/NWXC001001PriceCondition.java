/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/14/2009   Fujitsu         K.Kimura        Create          N/A
 * 10/01/2010   Fujitsu         K.Tajima        Update          Performance tuning
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001PriceCondition {

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001PriceCondition.class);

    /**
     * get PRC_COND_CD.
     * 
     * @param   glblCmpyCd      GLBL_CMPY_CD
     * @param   mdseCd          MDSE_CD
     * @param   sellToCustCd    SELL_TO_CUST_CD
     * @param   billToCustCd    BILL_TO_CUST_CD
     * @param   shipToCustCd    SHIP_TO_CUST_CD
     * 
     * @return  PRC_COND_CD
     */
    public static String getPriceCondition(String glblCmpyCd, String mdseCd, String sellToCustCd, String billToCustCd, String shipToCustCd) {

        if (!hasValue(glblCmpyCd) || !hasValue(mdseCd) || !hasValue(sellToCustCd) || !hasValue(billToCustCd) || !hasValue(shipToCustCd)) {
            return null;
        }

        // MDSE
        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return null;
        }

        // PRC_COND_CD
        return getPrcCondCd(glblCmpyCd, sellToCustCd, billToCustCd, shipToCustCd, mdseTMsg);
    }

    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return null;

        } else if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseTMsg.rgtnStsCd.getValue())) {
            return null;

        } else {
            return mdseTMsg;
        }
    }

    @SuppressWarnings("unchecked")
    private static String getPrcCondCd(String glblCmpyCd, String sellToCustCd, String billToCustCd, String shipToCustCd, MDSETMsg mdseTMsg) {

//        final Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd",       glblCmpyCd);
//        ssmParam.put("zerothProdCtrlCd", mdseTMsg.zerothProdCtrlCd.getValue());
//        ssmParam.put("firstProdCtrlCd",  mdseTMsg.firstProdCtrlCd.getValue());
//        ssmParam.put("sellToCustCd",     sellToCustCd);
//        ssmParam.put("billToCustCd",     billToCustCd);
//        ssmParam.put("shipToCustCd",     shipToCustCd);
//
//        final List<String> prcCondCdList = ssmClient.queryObjectList("getPrcCondCd", ssmParam);
//        
//        if (isEmpty(prcCondCdList)) {
            return "";
//        } else {
//            return prcCondCdList.get(0);
//        }
    }

    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

}
