/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * SHIPPING_CONTRACT_RESTRICTION
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/31/2009   Fujitsu         K.Kato          Create          N/A
 * 11/18/2009   Fujitsu         N.Mitsuishi     Update          N/A
 * 11/24/2009   Fujitsu         N.Mitsuishi     Update          N/A
 * 02/08/2010   Fujitsu         T.Nagase        Update          N/A (Dealer profile)
 * 05/07/2010   Fujitsu         K.Tajima        Update          2663 (performance tuning)
 * 09/07/2010   Fujitsu         A.Suda		    Update          412(All2) 
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC010001;

import static com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo.chkContractInfo;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.HLDTMsg;
import business.db.HLD_RSNTMsg;
import business.db.MDSETMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * SHIPPING_CONTRACT_RESTRICTION
 */
public class NWZC010001 extends S21ApiCommonBase implements HLD_RSN {

    public static final String NWZM0001E = "NWZM0001E";
    public static final String NWZM0002E = "NWZM0002E";
    public static final String NWZM0003E = "NWZM0003E";
    public static final String NWZM0004E = "NWZM0004E";
    public static final String NWZM0077E = "NWZM0077E";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    private S21ApiMessageMap msgMap;
    
    private final LocalCache localCache = new LocalCache();

    public NWZC010001() {
        super();
    }

    /**
     * Shipping Contract Restriction Hold API
     * 
     * @param param         NWXC005001ValidationBean Interface
     * @param onBatchType   ONBATCH_TYPE
     */
    @SuppressWarnings("unchecked")
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final NWXC005001PMsg pMsg = param.getInputPMsg();
        this.msgMap = new S21ApiMessageMap(pMsg);

        try {

            // check request parameters.
            if (!checkReqParams(pMsg)) {
                return;
            }

            final CPOTMsg     cpoTMsg    = param.getCTMsg();
            final CPO_DTLTMsg cpoDtlTMsg = param.getCdTMsg();

            // MDSE
            final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), cpoDtlTMsg.mdseCd.getValue());
            if (mdseTMsg == null) {
                addMsgId(NWZM0077E);
                return;
            }

            if (Y.equals(mdseTMsg.soAuthReqFlg.getValue())) {

                // is already Hold?
                if (isAlreadyHld(pMsg, cpoDtlTMsg)) {
                    return;
                }

                // HLD_RSN
                final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), SHIPPING_CONTRACT_RESTRICTION);

                if (Y.equals(hldRsnTMsg.exReqFlg.getValue())) {
                    if (findCustExToOrdRstQty(pMsg, cpoTMsg, cpoDtlTMsg, mdseTMsg)) {
                        return;
                    }
                }

                setValue(pMsg.cpoOrdNum_O,        cpoDtlTMsg.cpoOrdNum.getValue());
                setValue(pMsg.cpoDtlLineNum_O,    cpoDtlTMsg.cpoDtlLineNum.getValue());
                setValue(pMsg.cpoDtlLineSubNum_O, cpoDtlTMsg.cpoDtlLineSubNum.getValue());
                setValue(pMsg.hldRsnCd,           SHIPPING_CONTRACT_RESTRICTION);
            }

        } finally {
            if (this.msgMap != null) {
                this.msgMap.flush();
            }
        }
    }

    /**
     * Shipping Contract Restriction Hold API
     * 
     * @param params        List<NWXC005001ValidationBean> Interface
     * @param onBatchType   ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    private void addMsgId(String msgId) {
        if (this.msgMap != null) {
            this.msgMap.addXxMsgId(msgId);
        }
    }

    private boolean checkReqParams(final NWXC005001PMsg param) {

        if (!hasValue(param.glblCmpyCd)) {
            addMsgId(NWZM0001E);
            return false;
        }

        if (!hasValue(param.cpoOrdNum_I)) {
            addMsgId(NWZM0002E);
            return false;
        }

        if (!hasValue(param.cpoDtlLineNum_I)) {
            addMsgId(NWZM0003E);
            return false;
        }

        if (!hasValue(param.cpoDtlLineSubNum_I)) {
            addMsgId(NWZM0004E);
            return false;
        }

        return true;
    }

    private boolean findCustExToOrdRstQty(NWXC005001PMsg pMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, MDSETMsg mdseTMsg) {

        String glblCmpyCd   = pMsg.glblCmpyCd.getValue();
        String mdseCd       = mdseTMsg.mdseCd.getValue();
        String shipToCustCd = cpoDtlTMsg.shipToCustCd.getValue();
        String sellToCustCd = cpoTMsg.sellToCustCd.getValue();
        String billToCustCd = cpoTMsg.billToCustCd.getValue();
        String hldRsn       = SHIPPING_CONTRACT_RESTRICTION;
        String slsDt        = pMsg.slsDt.getValue();

        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(mdseCd).append(",");
        cacheKeySb.append(shipToCustCd).append(",");
        cacheKeySb.append(sellToCustCd).append(",");
        cacheKeySb.append(billToCustCd).append(",");
        cacheKeySb.append(hldRsn).append(",");
        cacheKeySb.append(slsDt);
        
        final String cacheKey = cacheKeySb.toString();
        
        Boolean contractInfo = this.localCache.contractInfoCache.get(cacheKey);
        
        if (contractInfo == null) {

            contractInfo = chkContractInfo(glblCmpyCd, mdseCd, shipToCustCd, sellToCustCd, billToCustCd, hldRsn, null, slsDt);
            
            this.localCache.contractInfoCache.put(cacheKey, contractInfo);
        }
        
        return contractInfo;
    }

    private static boolean isAlreadyHld(NWXC005001PMsg pMsg, CPO_DTLTMsg cpoDtlTMsg) {

        final HLDTMsg reqTMsg = new HLDTMsg();
        reqTMsg.setSQLID("015");
        reqTMsg.setConditionValue("glblCmpyCd01",       pMsg.glblCmpyCd.getValue());
        reqTMsg.setConditionValue("cpoOrdNum01",        cpoDtlTMsg.cpoOrdNum.getValue());
        reqTMsg.setConditionValue("cpoDtlLineNum01",    cpoDtlTMsg.cpoDtlLineNum.getValue());
        reqTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlTMsg.cpoDtlLineSubNum.getValue());
        reqTMsg.setConditionValue("relFlg01",           N);
        reqTMsg.setConditionValue("hldRsnCd01",         SHIPPING_CONTRACT_RESTRICTION);

        return S21ApiTBLAccessor.count(reqTMsg) > 0;
    }

    /**
     * Local Data Cache.
     * 
     * @author K.Tajima
     */
    private static final class LocalCache {
        
        final S21LRUMap<String, Boolean> contractInfoCache = new S21LRUMap<String, Boolean>();
    }
    
}
