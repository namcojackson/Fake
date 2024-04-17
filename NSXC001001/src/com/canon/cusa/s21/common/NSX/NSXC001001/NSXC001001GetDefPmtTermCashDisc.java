/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 * <pre>
 * Get Default Payment Term Cash Discount
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/26/2013   WDS Team        K.Aratani       Create          N/A
 *</pre>
 */
public class NSXC001001GetDefPmtTermCashDisc {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetDefPmtTermCashDisc.class);

    /**
     * private constructor.
     */
    public NSXC001001GetDefPmtTermCashDisc() {
    }

    /**
     * <pre>
     * get Default Payment Term Cash Discount By Sell, Bill Customer Code
     * </pre>
     * @param glblCmpyCd String
     * @param SellToCustCd String
     */
    public static String getDefPmtTermCashDiscBySellBill(String glblCmpyCd, String sellToCustCd, String billToCustCd) {

        if (sellToCustCd == null || "".equals(sellToCustCd)) {
            return null;
        }
        
        String paramBillToCustCd = "";
        if (billToCustCd != null && !"".equals(billToCustCd)) {
            paramBillToCustCd = billToCustCd;
        }
        
        Map<String, Object> prmMap = new HashMap<String, Object>();

        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("sellToCustCd", sellToCustCd);
        prmMap.put("billToCustCd", paramBillToCustCd);
        
        List<Map<String, Object>> defPmtTermCashDiscList = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getDefPmtTermCashDiscBySellBill", prmMap);
        if (defPmtTermCashDiscList == null || defPmtTermCashDiscList.size() == 0 || defPmtTermCashDiscList.get(0) == null) {
            return null;
        }
        
        return (String) defPmtTermCashDiscList.get(0).get("PMT_TERM_CASH_DISC_CD");
    }

    /**
     * <pre>
     * get Default Payment Term Cash Discount By Sell, Bill Customer, MDSE Code
     * </pre>
     * @param glblCmpyCd String
     * @param SellToCustCd String
     */
    public static String getDefPmtTermCashDiscBySellBillMDSE(String glblCmpyCd, String sellToCustCd, String billToCustCd, String mdseCd) {

        if (sellToCustCd == null || "".equals(sellToCustCd)) {
            return null;
        }
        
        String paramBillToCustCd = "";
        if (billToCustCd != null && !"".equals(billToCustCd)) {
            paramBillToCustCd = billToCustCd;
        }
        
        if (mdseCd == null || "".equals(mdseCd)) {
            return null;
        }
        
        final MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseMsg == null || !(RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseMsg.rgtnStsCd.getValue()) || RGTN_STS.TERMINATED.equals(mdseMsg.rgtnStsCd.getValue()))) {
            return null;
        }
        
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("sellToCustCd", sellToCustCd);
        prmMap.put("billToCustCd", paramBillToCustCd);
        prmMap.put("mdseCd", mdseCd);
        if (ZYPCommonFunc.hasValue(mdseMsg.zerothProdCtrlCd)) {
            prmMap.put("zerothProdCtrlCd", mdseMsg.zerothProdCtrlCd.getValue());
            prmMap.put("OR0", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.firstProdCtrlCd)) {
            prmMap.put("firstProdCtrlCd", mdseMsg.firstProdCtrlCd.getValue());
            prmMap.put("OR1", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.scdProdCtrlCd)) {
            prmMap.put("scdProdCtrlCd", mdseMsg.scdProdCtrlCd.getValue());
            prmMap.put("OR2", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.thirdProdCtrlCd)) {
            prmMap.put("thirdProdCtrlCd", mdseMsg.thirdProdCtrlCd.getValue());
            prmMap.put("OR3", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.frthProdCtrlCd)) {
            prmMap.put("frthProdCtrlCd", mdseMsg.frthProdCtrlCd.getValue());
            prmMap.put("OR4", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.fifthProdCtrlCd)) {
            prmMap.put("fifthProdCtrlCd", mdseMsg.fifthProdCtrlCd.getValue());
            prmMap.put("OR5", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.sixthProdCtrlCd)) {
            prmMap.put("sixthProdCtrlCd", mdseMsg.sixthProdCtrlCd.getValue());
            prmMap.put("OR6", "Y");
        }
        if (ZYPCommonFunc.hasValue(mdseMsg.svnthProdCtrlCd)) {
            prmMap.put("svnthProdCtrlCd", mdseMsg.svnthProdCtrlCd.getValue());
            prmMap.put("OR7", "Y");
        }
        
        List<Map<String, Object>> defPmtTermCashDiscList = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getDefPmtTermCashDiscBySellBillMDSE", prmMap);
        if (defPmtTermCashDiscList == null || defPmtTermCashDiscList.size() == 0 || defPmtTermCashDiscList.get(0) == null) {
            return null;
        }
        
        return (String) defPmtTermCashDiscList.get(0).get("PMT_TERM_CASH_DISC_CD");
    }

}
