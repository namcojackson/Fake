/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC167001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.parts.NWZC167001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public final class NWZC167001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWZC167001Query MY_INSTANCE = new NWZC167001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    protected static NWZC167001Query getInstance() {
        return MY_INSTANCE;
    }

    protected Integer countPsnCd(String glblCmpyCd, String psnCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnCd", psnCd);

        return (Integer) ssmBatchClient.queryObject("countPsnCd", ssmParam);
    }

    protected String getDefaultCarrCd(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("carrSvcLvlCd", pMsg.carrSvcLvlCd.getValue());
        ssmParam.put("rownum", "1");

        return (String) ssmBatchClient.queryObject("getDefaultCarrCd", ssmParam);
    }

    protected String getDefaultCarrSvcLvl(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("frtCondCd", pMsg.frtCondCd.getValue());
        ssmParam.put("shpgSvcLvlCd", pMsg.shpgSvcLvlCd.getValue());
        ssmParam.put("rownum", "1");

        return (String) ssmBatchClient.queryObject("getDefaultCarrSvcLvl", ssmParam);
    }

    protected Integer countSubmitPoNo(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("custIssPoNum", pMsg.custIssPoNum.getValue());
        ssmParam.put("ordHdrSts", ORD_HDR_STS.CANCELLED);
        ssmParam.put("sellToCustCd", pMsg.sellToCustCd.getValue());

        return (Integer) ssmBatchClient.queryObject("countSubmitPoNo", ssmParam);
    }

    protected Integer countSavePoNo(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("custIssPoNum", pMsg.custIssPoNum.getValue());
        ssmParam.put("splyQuoteSts", SPLY_QUOTE_STS.SAVED);
        ssmParam.put("sellToCustCd", pMsg.sellToCustCd.getValue());

        return (Integer) ssmBatchClient.queryObject("countSavePoNo", ssmParam);
    }

    protected String getAttrbTxt(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        ssmParam.put("dsOrdCatg", pMsg.dsOrdCatgCd.getValue());
        ssmParam.put("dsOrdTp", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());

        String attrbTxt = (String) ssmBatchClient.queryObject("getAttrbTxt", ssmParam);

        if (!ZYPCommonFunc.hasValue(attrbTxt)) {
            attrbTxt = "";
        }

        return attrbTxt;
    }

    protected String getCashOrCcReqFlg(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", pMsg.billToCustCd.getValue());

        return (String) ssmBatchClient.queryObject("getCashOrCcReqFlg", ssmParam);
    }

    protected String getIntrlFlg(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("sellToCustAcctCd", pMsg.sellToCustCd.getValue());

        return (String) ssmBatchClient.queryObject("getIntrlFlg", ssmParam);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, String> getMdseSts(String glblCmpyCd, String mdseCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        return (Map<String, String>) ssmBatchClient.queryObject("getMdseSts", ssmParam);
    }

    protected Integer countCtryCd(NWZC167001PMsg pMsg, String ctryCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("ctryCd", ctryCd);
        ssmParam.put("sellToCustCd", pMsg.sellToCustCd.getValue());
        ssmParam.put("billToCustCd", pMsg.billToCustCd.getValue());

        return (Integer) ssmBatchClient.queryObject("countCtryCd", ssmParam);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, String> getOrdCatgAndTp(NWZC167001PMsg pMsg, String ctxTp) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("ctxTpCd", ctxTp);

        return (Map<String, String>) ssmBatchClient.queryObject("getOrdCatgAndTp", ssmParam);
    }

    protected BigDecimal getCostPct(NWZC167001PMsg pMsg, String rtlSwh) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rtlSwh", rtlSwh);
        ssmParam.put("slsDt", pMsg.slsDt.getValue());

        return (BigDecimal) ssmBatchClient.queryObject("getCostPct", ssmParam);
    }

    protected String getInternalBill(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", pMsg.billToCustCd.getValue());
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("retnTpCd", BIZ_RELN_TP.INTERNCUST);

        return (String) ssmBatchClient.queryObject("getInternalBill", ssmParam);
    }

    protected String getSaleableSell(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("sellToCust", pMsg.sellToCustCd.getValue());
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (String) ssmBatchClient.queryObject("getSaleableSell", ssmParam);
    }

    protected String getEmbargoFlag(NWZC167001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCust", pMsg.shipToCustCd.getValue());
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (String) ssmBatchClient.queryObject("getEmbargoFlag", ssmParam);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, String> getShipToInfo(NWZC167001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustCd", pMsg.shipToCustCd.getValue());
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", 1);

        return (Map<String, String>) ssmBatchClient.queryObject("getShipToInfo", ssmParam);
    }

    protected Integer cntAvalWarehouse(NWZC167001PMsg pMsg, String invtyLocCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("invtyLocCd", invtyLocCd);

        return (Integer) ssmBatchClient.queryObject("cntAvalWarehouse", ssmParam);
    }
}
