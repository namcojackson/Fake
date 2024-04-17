/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Shipping Hold API
 * 
 * When must stop shipment by an article or the combination of an article and the warehouse, 
 * carry out Hold.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/10   Fujitsu         H.Kato          Create          N/A
 * 2009/10/26   Fujitsu         S.Sugino        Update          N/A
 * 2009/11/19   Fujitsu         T.kaneda        Update          N/A
 * 2009/11/24   Fujitsu         K.Kato          Update          N/A
 * 2010/02/08   Fujitsu         A.Suda          Update          N/A
 * 05/07/2010   Fujitsu         K.Tajima        Update          2663 (performance tuning)
 * 11/30/2010   Fujitsu         K.Tajima        Update          645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC018001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

import business.db.HLD_RSNTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_WH_CONDTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * Shipping Hold API
 */
public class NWZC018001 extends S21ApiCommonBase implements HLD_RSN {

    public static final String NWZM0001E = "NWZM0001E";
    public static final String NWZM0250E = "NWZM0250E";
    public static final String NWZM0075E = "NWZM0075E";
    public static final String NWZM0036E = "NWZM0036E";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    private final S21SsmBatchClient ssmClient;
    private S21ApiMessageMap  msgMap;

    public NWZC018001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Shipping Hold API
     * 
     * @param validBean     NWXC005001ValidationBean
     * @param onBatchType   ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean validBean, final ONBATCH_TYPE onBatchType) {

        final NWXC005001PMsg pMsg        = validBean.getInputPMsg();
        final SHPG_PLNTMsg   shpgPlnTMsg = validBean.getSpTMsg();

        this.msgMap = new S21ApiMessageMap(pMsg);

        try {

            if (checkReqParams(pMsg)) {
                doProcess(pMsg, shpgPlnTMsg);
            }

        } finally {
            if (this.msgMap != null) {
                this.msgMap.flush();
            }
        }
    }

    /**
     * Shipping Hold API
     * 
     * @param validationBean    List<NWXC005001ValidationBean>
     * @param onBatchType       ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> validBeanList, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean validBean : validBeanList) {
            execute(validBean, onBatchType);
        }
    }

    private void addMsgId(String msgId) {
        if (this.msgMap != null) {
            this.msgMap.addXxMsgId(msgId);
        }
    }

    private boolean checkReqParams(NWXC005001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            addMsgId(NWZM0001E);
            return false;
        }

        if (!hasValue(pMsg.shpgPlnNum_I)) {
            addMsgId(NWZM0250E);
            return false;
        }

        return true;
    }

    private void doProcess(NWXC005001PMsg pMsg, SHPG_PLNTMsg shpgPlnTMsg) {

        // MDSE
        final MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), shpgPlnTMsg.mdseCd.getValue());
        if (mdseMsg == null) {
            addMsgId(NWZM0036E);
            return;
        }

        final String mdseCd = mdseMsg.mdseCd.getValue();

        // --------------------------------------------------
        // get MDSE_WH_COND
        // --------------------------------------------------
        String shpgOrdHldFlg       = mdseMsg.shpgOrdHldFlg.getValue();
        String shpgOrdHldFlgByWhCd = N;
        String invtyLocCd          = shpgPlnTMsg.invtyLocCd.getValue();
        if (N.equals(shpgOrdHldFlg)) {
            if (hasValue(invtyLocCd)) {
                //shpgOrdHldFlgByWhCd = getShpgOrdHldFlgByWhCd(pMsg.glblCmpyCd.getValue(), mdseCd, invtyLocCd);
            }
        }

        // --------------------------------------------------
        // check Shipping Hold
        // --------------------------------------------------
        boolean isHld = false;

        if (Y.equals(shpgOrdHldFlg) || Y.equals(shpgOrdHldFlgByWhCd)) {
            isHld = true;
        }

        if (isHld) {

            // --------------------------------------------------
            // Existence confirmation of the HOLD information
            // --------------------------------------------------
            if (isAlreadyHld(pMsg)) {
                return;
            }

            // HLD_RSN
            final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), SHIPPING_HOLD);

            if (Y.equals(hldRsnTMsg.exReqFlg.getValue())) {

                // --------------------------------------------------
                // get Exception information
                // --------------------------------------------------
                if (isException(pMsg, shpgPlnTMsg, mdseCd)) {
                    isHld = false;
                }
            }

            if (isHld) {
                setValue(pMsg.cpoOrdNum_O,        pMsg.cpoOrdNum_I);
                setValue(pMsg.cpoDtlLineNum_O,    pMsg.cpoDtlLineNum_I);
                setValue(pMsg.cpoDtlLineSubNum_O, pMsg.cpoDtlLineSubNum_I);
                setValue(pMsg.shpgPlnNum_O,       pMsg.shpgPlnNum_I);
                setValue(pMsg.hldRsnCd,           SHIPPING_HOLD);
            }
        }
    }

//    private String getShpgOrdHldFlgByWhCd(String glblCmpyCd, String mdseCd, String whCd) {
//
////        final Map<String, Object> ssmParam = new HashMap<String, Object>();
////        ssmParam.put("glblCmpyCd", glblCmpyCd);
////        ssmParam.put("mdseCd",     mdseCd);
////        ssmParam.put("whCd",       whCd);
////
////        return (String) ssmClient.queryObject("getShpgOrdHldFlgByWhCd", ssmParam);
//        
//        // [MDSE_WH_COND]
//        MDSE_WH_CONDTMsg mdseWhCondTMsg = new MDSE_WH_CONDTMsg();
//        setValue(mdseWhCondTMsg.glblCmpyCd, glblCmpyCd);
//        setValue(mdseWhCondTMsg.mdseCd,     mdseCd);
//        setValue(mdseWhCondTMsg.whCd,       whCd);
//        mdseWhCondTMsg = (MDSE_WH_CONDTMsg)findByKeyWithCache(mdseWhCondTMsg);
//        
//        if (mdseWhCondTMsg == null) {
//            return "";
//        } else {
//            return mdseWhCondTMsg.shpgOrdHldFlg.getValue();
//        }
//    }

    private boolean isAlreadyHld(NWXC005001PMsg pMsg) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("shpgPlnNum", pMsg.shpgPlnNum_I.getValue());
        ssmParam.put("hldRsnCd",   SHIPPING_HOLD);

        return (Integer) ssmClient.queryObject("getHldCnt", ssmParam) > 0;
    }

    private static boolean isException(NWXC005001PMsg pMsg, SHPG_PLNTMsg shpgPlnTMsg, String mdseCd) {

        String glblCmpyCd   = pMsg.glblCmpyCd.getValue();
        String shipToCustCd = shpgPlnTMsg.shipToCustCd.getValue();
        String billToCustCd = shpgPlnTMsg.billToCustCd.getValue();
        String sellToCustCd = shpgPlnTMsg.sellToCustCd.getValue();

        return NWXC001001ContractInfo.chkContractInfo(glblCmpyCd, mdseCd, shipToCustCd, sellToCustCd, billToCustCd, SHIPPING_HOLD, null);
    }
    
    private static EZDTMsg findByKeyWithCache(EZDTMsg tMsg) {
        
        return S21CacheTBLAccessor.findByKey(tMsg);
    }

}
