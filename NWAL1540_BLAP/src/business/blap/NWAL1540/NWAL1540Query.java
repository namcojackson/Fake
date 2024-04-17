/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1540;

import java.util.HashMap;
import java.util.Map;

import business.parts.NWZC156001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1540Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 * 2016/03/10   Fujitsu         S.Yamamoto      Update          S21_NA#2939
 * 2016/03/30   Fujitsu         S.Yamamoto      Update          S21_NA#6292
 * 2016/04/19   Fujitsu         S.Yamamoto      Update          S21_NA#6974
 * 2017/10/11   Fujitsu         R.Nakamura      Update          S21_NA#21664
 *</pre>
 */
public final class NWAL1540Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1540Query MY_INSTANCE = new NWAL1540Query();

    /**
     * Private constructor
     */
    private NWAL1540Query() {
        super();
    }

    /**
     * Get the NWAL1540Query instance.
     * @return NWAL1540Query instance
     */
    public static NWAL1540Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWAL1540CMsg
     * @param glblMsg NWAL1540SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDetailInfo(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg, int limitRownum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());
        params.put("ordPrftVrsnNum", bizMsg.ordPrftVrsnNum.getValue());
        params.put("limitRownum", limitRownum);

        return getSsmEZDClient().queryEZDMsgArray("getSearchDetailInfoToSMsg", params, glblMsg.A);
    }

    public S21SsmEZDResult getVrsnNumList(NWAL1540CMsg bizMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());

        return getSsmEZDClient().queryObjectList("getVrsnNumList", params);
    }

    public S21SsmEZDResult searchHeaderInfo(NWAL1540CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());
//        if (ZYPCommonFunc.hasValue(bizMsg.ordPrftVrsnNum) //
//                && BigDecimal.ZERO.compareTo(bizMsg.ordPrftVrsnNum.getValue()) != 0) {
//            params.put("ordPrftVrsnNum", bizMsg.ordPrftVrsnNum.getValue());
//        }
        params.put("ordPrftVrsnNum", bizMsg.ordPrftVrsnNum.getValue());

        return getSsmEZDClient().queryEZDMsg("getOrdPrftHeaderInfo", params, bizMsg);
    }

    public S21SsmEZDResult getPrftCalcInfoForOrder(NWAL1540CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());
        params.put("outBound", CONFIG_CATG.OUTBOUND);
        params.put("inBound", CONFIG_CATG.INBOUND);
        params.put("ordPrftTrxCatgOutBound", ORD_PRFT_TRX_CATG.OUTBOUND);
        params.put("ordPrftTrxCatgInBound", ORD_PRFT_TRX_CATG.INBOUND);
//        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);// 2016/03/10 S21_NA#2939
        params.put("slsDt", ZYPDateUtil.getSalesDate());// 2016/03/10 S21_NA#6292

        return getSsmEZDClient().queryObjectList("getPrftCalcInfoForOrder", params);
    }

    public S21SsmEZDResult getPrftCalcInfoForQuote(NWAL1540CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());
        params.put("outBound", CONFIG_CATG.OUTBOUND);
        params.put("inBound", CONFIG_CATG.INBOUND);
        params.put("configCatgCdOutBound", CONFIG_CATG.OUTBOUND);
        params.put("ordPrftTrxCatgOutBound", ORD_PRFT_TRX_CATG.OUTBOUND);
//        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);// 2016/03/10 S21_NA#2939
        params.put("slsDt", ZYPDateUtil.getSalesDate());// 2016/03/10 S21_NA#6292
        params.put("cancelSts", SPLY_QUOTE_STS.CANCELLED);// 2016/04/19 S21_NA#6974

        return getSsmEZDClient().queryObjectList("getPrftCalcInfoForQuote", params);
    }

    // [Template] sample logic. result data receive to Map.
    //    /**
    //     * getSampleList
    //     * @return S21SsmEZDResult
    //     */
    //    public S21SsmEZDResult getSampleList() {
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("glblCmpyCd", getGlobalCompanyCode());
    //
    //        return getSsmEZDClient().queryObjectList("getSampleList", params);
    //    }

    // [Template] sample method for search. result data receive to sMsg.
    //    /**
    //     * getSampleToSMsg
    //     * @param bizMsg NWAL1540CMsg
    //     * @param glblMsg NWAL1540SMsg
    //     * @return S21SsmEZDResult
    //     */
    //    public S21SsmEZDResult getSampleToSMsg(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("glblCmpyCd", getGlobalCompanyCode());
    //        params.put("limitRownum", glblMsg.A.length());
    //
    //        return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg", params, glblMsg.A);
    //
    //    }

    // 2016/03/10 S21_NA#2939
    public S21SsmEZDResult getMaxVerGrossProfit(NWAL1540CMsg bizMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());

        return getSsmEZDClient().queryObject("getMaxVerGrossProfit", params);
    }

    // 2016/07/25 QC#11636
    public S21SsmEZDResult getCreditRebillCd(NWAL1540CMsg bizMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trxHdrNum", bizMsg.trxHdrNum.getValue());

        return getSsmEZDClient().queryObject("getCreditRebillCd", params);
    }

    // Add Start 2017/10/11 QC#21664
    public S21SsmEZDResult getPrftRuleTp(NWZC156001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());// 2016/03/10 S21_NA#2939

        return getSsmEZDClient().queryObject("getPrftRuleTp", ssmParam);
    }
    // Add End 2017/10/11 QC#21664
}
