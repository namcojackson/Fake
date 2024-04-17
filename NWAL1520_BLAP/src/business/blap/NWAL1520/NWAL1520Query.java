/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1520;

import static business.blap.NWAL1520.constant.NWAL1520Constant.HLD_LVL_DESC_TXT_RMA_SUFFIX;
import static business.blap.NWAL1520.constant.NWAL1520Constant.NWAL1520_HDR_HLD_ONLY;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1520Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Fujitsu         A.Suda          Create          N/A
 * 2016/04/27   Fujitsu         M.Yamada        Update          S21_NA#7159
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#19916-2
 * 2017/08/21   Fujitsu         S.Takami        Update          S21_NA#19951
 * 2017/09/19   Fujitsu         H.Sugawara      Update          QC#19918
 * 2019/09/10   Fujitsu         R.Nakamura      Update          S21_NA#51929
 * 2019/09/20   Fujitsu         S.Kosaka        Update          QC#53184
 * 2022/08/03   Hitachi         H.Watanabe      Update          QC#60257
 *</pre>
 */
public final class NWAL1520Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1520Query MY_INSTANCE = new NWAL1520Query();

    /**
     * Private constructor
     */
    private NWAL1520Query() {
        super();
    }

    /**
     * Get the NWAL1520Query instance.
     * @return NWAL1520Query instance
     */
    public static NWAL1520Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getCategoryTypeList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCategoryTypeList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getCategoryTypeList", params);
    }

    /**
     * getcpoOrdNum
     * @param bizMsg NWAL1520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getcpoOrdNum(NWAL1520CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_A.getValue());
        return getSsmEZDClient().queryObjectList("getcpoOrdNum", params);
    }

    /**
     * getHldReasonList
     * @param hldRsnDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldReasonList(String hldRsnDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("hldReason", hldRsnDescTxt);
        return getSsmEZDClient().queryObjectList("getHldReasonList", params);

    }

    /**
     * getHldApplyReasonList
     * @param hldRsnDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldApplyReasonList(String hldApplyRsnDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("hldApplyReason", hldApplyRsnDescTxt);
        return getSsmEZDClient().queryObjectList("getHldApplyReasonList", params);
    }

    /**
     * getHldList
     * @param bizMsg NWAL1520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldList(NWAL1520CMsg bizMsg) {

        List<String> dsOrdPosnNumList = new ArrayList<String>();
        List<String> dsCpoLineNumList = new ArrayList<String>();

        // 2017/07/27 S21_NA#19916-2 Add Start
        // START 08/03/2022 [QC#60257, Del]
//        String hdrOnly = ZYPCodeDataUtil.getVarCharConstValue(NWAL1520_HDR_HLD_ONLY, getGlobalCompanyCode());
        // END 08/03/2022 [QC#60257, Del]
        boolean isHdrOnly = false;
        // START 08/03/2022 [QC#60257, Del]
//        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, hdrOnly)) {
//            isHdrOnly = true;
//        }
        // END 08/03/2022 [QC#60257, Del]
        // 2017/07/27 S21_NA#19916-2 Add End
        Map<String, Object> params = new HashMap<String, Object>();
        if (hasValue(bizMsg.condSqlTxt_V)) {
            String[] lineNumsList = bizMsg.condSqlTxt_V.getValue().split(",", 0);
            for (String lineNums : lineNumsList) {
                if (hasValue(lineNums)) {
                    String[] lineNumList = lineNums.split("\\.");
                    if (lineNumList.length > 0) {
                        dsOrdPosnNumList.add(lineNumList[0]);
                    }
                    if (lineNumList.length > 1) {
                        dsCpoLineNumList.add(lineNumList[1]);
                        params.put("hasLineNum", ZYPConstant.FLG_ON_Y);
                    }
                }
            }
        } else { // 2017/07/27 S21_NA#19916-2 Add Start
            if (isHdrOnly) {
                params.put("isHdrOnly", ZYPConstant.FLG_ON_Y);
            }
        } // 2017/07/27 S21_NA#19916-2 Add End

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_V.getValue());
        params.put("configCatgCd", bizMsg.configCatgCd_V.getValue());
        params.put("lineNums", bizMsg.condSqlTxt_V.getValue());
        params.put("dsOrdPosnNumList", dsOrdPosnNumList);
        params.put("dsCpoLineNumList", dsCpoLineNumList);
        params.put("hldReason", bizMsg.hldRsnCd_V.getValue());
        params.put("hldApplyReason", bizMsg.hldApplyRsnCd_V.getValue());
        params.put("xxRadioBtn", bizMsg.xxRadioBtn_V.getValue());
        params.put("hldUntilDt", bizMsg.hldUntilDt_V.getValue());
        params.put("rowNum", bizMsg.L.length() + 1);
        if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd_V.getValue())) {
            params.put("configCatgOutBound", CONFIG_CATG.OUTBOUND);
        }
        if (CONFIG_CATG.INBOUND.equals(bizMsg.configCatgCd_V.getValue())) {
            params.put("configCatgInBound", CONFIG_CATG.INBOUND);
            // 2017/08/21 S21_NA#19951 Del Start
//            params.put("trxSrcTpCdReturn", TRX_SRC_TP.WHOLE_SALES_RETURN);
            // 2017/08/21 S21_NA#19951 Del End
        }
        // 2017/08/21 S21_NA#19951 Add Start
        params.put("trxSrcTpCdReturn", TRX_SRC_TP.WHOLE_SALES_RETURN);
        // 2017/08/21 S21_NA#19951 Add End

        // Add Start 2017/09/19 QC#19918
        params.put("rmaSuffix", HLD_LVL_DESC_TXT_RMA_SUFFIX);
        // Add End 2017/09/19 QC#19918

        return getSsmEZDClient().queryObjectList("getHldList", params);
    }

    /**
     * getOrderInfo
     * @param bizMsg NWAL1520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderInfo(NWAL1520CMsg bizMsg) {

        List<String> dsOrdPosnNumList = new ArrayList<String>();
        List<String> dsCpoLineNumList = new ArrayList<String>();

        String[] lineNumsList = bizMsg.condSqlTxt_A.getValue().split(",", 0);
        for (String lineNums : lineNumsList) {
            if (hasValue(lineNums)) {
                String[] lineNumList = lineNums.split("\\.");
                dsOrdPosnNumList.add(lineNumList[0]);
                dsCpoLineNumList.add(lineNumList[1]);
            }
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_A.getValue());
        params.put("configCatgCd", bizMsg.configCatgCd_A.getValue());
        if (dsOrdPosnNumList.isEmpty()) {
            dsOrdPosnNumList = null;
        }
        if (dsCpoLineNumList.isEmpty()) {
            dsCpoLineNumList = null;
        }
        params.put("dsOrdPosnNum", dsOrdPosnNumList);
        params.put("dsCpoLineNum", dsCpoLineNumList);
        params.put("lineSubNum", "000");

        return getSsmEZDClient().queryObjectList("getOrderInfo", params);
    }

    // Add Start 2019/09/10 QC#51929
    /**
     * getcpoOrdNum
     * @param bizMsg NWAL1520CMsg
     * @return S21SsmEZDResult
     */
    public BigDecimal getRteStsRoutedCnt(NWAL1520CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.configCatgCd_A) //
                && S21StringUtil.isEquals(bizMsg.configCatgCd_A.getValue(), CONFIG_CATG.INBOUND)) {
            return BigDecimal.valueOf(0);
        }
        String[] lineNumsList = bizMsg.condSqlTxt_A.getValue().split(",", 0);
        List<Map<String, String>> lineParamList = new ArrayList<Map<String, String>>();
        for (String lineNums : lineNumsList) {
            if (hasValue(lineNums)) {
                String[] lineNumList = lineNums.split("\\.");
                Map<String, String> lineParams = new HashMap<String, String>();
                lineParams.put("dsOrdPosnNum", lineNumList[0]);
                lineParams.put("dsCpoLineNum", lineNumList[1]);
                lineParamList.add(lineParams);
            }
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_A.getValue());
        if (lineParamList.isEmpty()) {
            lineParamList = null;
        }
        params.put("lineParamList", lineParamList);
        params.put("ordLineStsShipped", ORD_LINE_STS.SHIPPED);
        params.put("ordLineStsClosed", ORD_LINE_STS.CLOSED);
        params.put("ordLineStsCanceled", ORD_LINE_STS.CANCELLED);
        params.put("rteStsRouted", RTE_STS.ROUTED);
        params.put("rteStsSOCreating", RTE_STS.SO_CREATING);
        params.put("lineSubNum", "000");
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getRteStsRoutedCnt", params);

        return (BigDecimal) result.getResultObject();

    }
    // Add End 2019/09/10 QC#51929

    // 2019/09/20 QC#53184 Add Start
    public List<Map<String, Object>> getShipPlanNumFromOrdNum(NWZC188001PMsg inMsg) {

        Map<String, Object> shpgOrdParam = new HashMap<String, Object>();
        shpgOrdParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        shpgOrdParam.put("cpoOrdNum", inMsg.cpoOrdNum.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getShipPlanNumFromOrdNum", shpgOrdParam);

        return (List<Map<String, Object>>) result.getResultObject();
    }

    public List<Map<String, Object>> getRmaDtlNumFromOrdNum(NWZC188001PMsg inMsg) {

        Map<String, Object> rmaOrdParam = new HashMap<String, Object>();
        rmaOrdParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        rmaOrdParam.put("cpoOrdNum", inMsg.cpoOrdNum.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getRmaDtlNumFromOrdNum", rmaOrdParam);

        return (List<Map<String, Object>>) result.getResultObject();
    }
    // 2019/09/20 QC#53184 Add End
}
