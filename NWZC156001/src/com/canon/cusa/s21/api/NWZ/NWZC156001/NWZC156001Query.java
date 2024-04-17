/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC156001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NWZC156001PMsg;
import business.parts.NWZC156001_svcConfigRefPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.ORD_LINE_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Profitability Calculation API NWZC156001Query class
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/31   Fujitsu         M.Yamada        Update          QC#12112
 * 2017/01/24   Fujitsu         S.Yamamoto      Update          S21_NA#17003
 * 2017/10/10   Fujitsu         S.Yamamoto      Update          S21_NA#21664
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2018/05/21   Fujitsu         A.Kosai         Update          S21_NA#26150
 * </pre>
 */
public class NWZC156001Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWZC156001Query myInstance = new NWZC156001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    protected static NWZC156001Query getInstance() {
        return myInstance;
    }

    protected List<Map<String, Object>> getLatestPrftInfo(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // Mod Start 2017/10/20 QC#21773
        //ssmParam.put("creditAndRebillEntry", CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
        ssmParam.put("cpoSrcTpCdCredit", CPO_SRC_TP.CREDIT);
        ssmParam.put("cpoSrcTpCdRebill", CPO_SRC_TP.REBILL);
        // Mod End 2017/10/20 QC#21773
        ssmParam.put("cpoOrdNum", pMsg.trxHdrNum.getValue());

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getLatestPrftInfo", ssmParam);

        return mapList;
    }

    protected Map<String, String> getPrftRuleTp(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());// 2016/03/10 S21_NA#2939

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getPrftRuleTp", ssmParam);
        return map;
    }

    protected BigDecimal getNewVrsnNum(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", pMsg.trxHdrNum.getValue());

        return (BigDecimal) ssmBatchClient.queryObject("getNewVrsnNum", ssmParam);
    }

    protected String getDsOrdLineCatgCd(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("prtSvcLine", "PRFT_SVC_LINE");

        return (String) ssmBatchClient.queryObject("getDsOrdLineCatgCd", ssmParam);
    }

//    protected Map<String, BigDecimal> getLatestCost(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {
//    protected BigDecimal getMSRPAmt(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {
    protected BigDecimal getMSRPAmt(NWZC156001PMsg pMsg, String mdseCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // 2016/03/10 Add Start S21_NA#2939
//        if (scrPMsg.mdseCd.getValue().length() > 8) {
//            ssmParam.put("mdseCd", scrPMsg.mdseCd.getValue().substring(0, 8));
//        } else {
//            ssmParam.put("mdseCd", scrPMsg.mdseCd.getValue());
//        }
        // QC#7286 Modify
        // 2018/05/21 S21_NA#26150 Mod Start
//        if (mdseCd.length() > 8) {
//            ssmParam.put("mdseCd", mdseCd.substring(0, 8));
//        } else {
//            ssmParam.put("mdseCd", mdseCd);
//        }
        ssmParam.put("mdseCd", mdseCd);
        // 2018/05/21 S21_NA#26150 Mod End
        // 2016/03/10 Add End S21_NA#2939

//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);// 2016/03/10 S21_NA#2939
        ssmParam.put("prcListTpCd", PRC_LIST_TP.MSRP);
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);

//        Map<String, BigDecimal> map = (Map<String, BigDecimal>) ssmBatchClient.queryObject("getLatestCost", ssmParam);// 2016/03/10 S21_NA#2939
        BigDecimal msrpAmt = (BigDecimal) ssmBatchClient.queryObject("getMSRPAmt", ssmParam);// 2016/03/10 S21_NA#2939

        if (msrpAmt == null) {
            return BigDecimal.ZERO;
        }

        return msrpAmt;
    }

    public Integer getFinderFeeCnt(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.RETAIL_ORDER_TYPE_BY_DIVISION);
        ssmParam.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());// 2016/03/10 S21_NA#2939
        ssmParam.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());
        ssmParam.put("fndgMdseCd", scrPMsg.mdseCd.getValue());
        ssmParam.put("dsOrdLineCatgCd", scrPMsg.dsOrdLineCatgCd.getValue());

        return (Integer) ssmBatchClient.queryObject("getFinderFeeCnt", ssmParam);
    }

    public Integer getIstlCrCnt(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.RETAIL_ORDER_TYPE_BY_DIVISION);
        ssmParam.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());// 2016/03/10 S21_NA#2939
        ssmParam.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());
        ssmParam.put("istlMdseCd", scrPMsg.mdseCd.getValue());
        ssmParam.put("dsOrdLineCatgCd", scrPMsg.dsOrdLineCatgCd.getValue());

        return (Integer) ssmBatchClient.queryObject("getIstlCrCnt", ssmParam);
    }

    public Integer getSplyCnt(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.PRFT_SPLY_AMT_ITEMS.toString());
        ssmParam.put("mdseCd", scrPMsg.mdseCd.getValue());
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);// 2016/03/10 S21_NA#2939

        return (Integer) ssmBatchClient.queryObject("getSplyCnt", ssmParam);
    }

    public List<Map<String, Object>> getChngOrdInfo(NWZC156001PMsg pMsg, String ordProcNodePrflCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.CHANGE_ORDER_MODIFICATION);
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);// 2016/03/10 S21_NA#2939
        ssmParam.put("ordProcNodePrflCd", ordProcNodePrflCd);
        ssmParam.put("outBound", CONFIG_CATG.OUTBOUND);
        ssmParam.put("inBound", CONFIG_CATG.INBOUND);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getChngOrdInfo", ssmParam);

        return rsltList;
    }

    public BigDecimal getTotByotAmt(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("ordLineCtxTpCd", ORD_LINE_CTX_TP.LEASE_BUYOUT.toString());

        return (BigDecimal) ssmBatchClient.queryObject("getTotByotAmt", ssmParam);
    }

    public List<Map<String, Object>> getSvcOrdInfo(NWZC156001PMsg pMsg, String ordProcNodePrflCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("base", SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
        ssmParam.put("addlBase", SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);
        ssmParam.put("addlChrg", SVC_PRC_CATG.MAIN_UNIT_ADDITIONAL_CHARGE);
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.CHANGE_ORDER_MODIFICATION);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcOrdInfo", ssmParam);

        return rsltList;
    }

    // 01/24/2017 QC#17003 Del Start
//    public S21SsmEZDResult getAllMdseVTMsg(ALL_MDSE_VTMsg tMsg, String glblCmpyCd, String mdseCd) {
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("mdseCd", mdseCd);
//
//        return getSsmEZDClient().queryEZDMsg("getAllMdseVTMsg", ssmParam, tMsg);
//    }
    // 01/24/2017 QC#17003 Del End

    public List<String> getCoaMdseTpList(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("profServices", MDSE_TP_CTX_TP.PROF_SERVICES.toString());

        @SuppressWarnings("unchecked")
        List<String> rsltList = (List<String>) ssmBatchClient.queryObjectList("getCoaMdseTpList", ssmParam);

        return rsltList;
    }

    // QC#7707
    public List<Map<String, String>> getDiscAllocMethList(NWZC156001PMsg pMsg, String ordPrftRuleTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("ordPrftRuleTpCd", ordPrftRuleTpCd);
        String mdseTpCtxTpList[] = new String[] { //
        MDSE_TP_CTX_TP.PRFT_SRT_MDSE_TP.toString() //
                , MDSE_TP_CTX_TP.PRFT_CT_MDSE_TP.toString() //
                , MDSE_TP_CTX_TP.PRFT_MFLR_MDSE_TP.toString() //
                , MDSE_TP_CTX_TP.PRFT_MREV_MDSE_TP.toString() };
        ssmParam.put("mdseTpCtxTpList", mdseTpCtxTpList);

        @SuppressWarnings("unchecked")
        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDiscAllocMethList", ssmParam);

        return rsltList;
    }

//    public Integer getSvcCnt(NWZC156001PMsg pMsg, NWZC156001_svcConfigRefPMsg scrPMsg) { // QC#7810
    public Integer getSvcCnt(NWZC156001PMsg pMsg, String mdseCd) {
       Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.PRFT_SVC_AMT_ITEMS.toString());
        ssmParam.put("mdseCd", mdseCd);
//        ssmParam.put("mdseCd", scrPMsg.mdseCd.getValue());
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);// 2016/03/10 S21_NA#2939

        return (Integer) ssmBatchClient.queryObject("getSvcCnt", ssmParam);
    }

    // 2016/03/10 S21_NA#2939
    public Integer isRetailOrder(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.RETAIL_ORDER_TYPE_BY_DIVISION);
        ssmParam.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());

        return (Integer) ssmBatchClient.queryObject("isRetailOrder", ssmParam);
    }
    // S21_NA#11981 Add
    public Map<String, Object> getLatestPrftDtl(NWZC156001PMsg pMsg, String trxHdrNum, String trxLineNum, String trxLineSubNum, String ordPrftTrxCatgCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", trxHdrNum);
        ssmParam.put("cpoDtlLineNum", trxLineNum);
        ssmParam.put("cpoDtlLineSubNum", trxLineSubNum);
        ssmParam.put("ordPrftTrxCatgCd", ordPrftTrxCatgCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> map =  (Map<String, Object>) ssmBatchClient.queryObject("getLatestPrftDtl", ssmParam);
        return map;
    }

    public String getOrigMdseCd(NWZC156001DtlBean dtlBean, NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("cpoDtlLineNum", dtlBean.getTrxLineNum());
        ssmParam.put("cpoDtlLineSubNum", dtlBean.getTrxLineSubNum());

        String origMdseCd = (String) ssmBatchClient.queryObject("getOrigMdseCd", ssmParam);
        return origMdseCd;
    }

    public String getOrigMdseCd(NWZC156001_svcConfigRefPMsg scrPMsg, NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("cpoDtlLineNum", scrPMsg.trxLineNum.getValue());
        ssmParam.put("cpoDtlLineSubNum", scrPMsg.trxLineSubNum.getValue());

        String origMdseCd = (String) ssmBatchClient.queryObject("getOrigMdseCd", ssmParam);
        return origMdseCd;
    }

    // Add Start 2017/10/10 QC#21664
    public String getOrdLineSrcCatgCd(NWZC156001DtlBean dtlBean, NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("cpoDtlLineNum", dtlBean.getTrxLineNum());
        ssmParam.put("cpoDtlLineSubNum", dtlBean.getTrxLineSubNum());

        String origMdseCd = (String) ssmBatchClient.queryObject("getOrdLineSrcCatgCd", ssmParam);
        return origMdseCd;
    }
    // Add End 2017/10/10 QC#21664

}
