/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1700;

import static business.blap.NWAL1700.constant.NWAL1700Constant.APPLY_DS_ORD_TP_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.BILL_TO_ACCT_NUM;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DS_ORD_CATG_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DS_ORD_LINE_CATG_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DS_ORD_TP_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DS_ORD_TP_NM;
import static business.blap.NWAL1700.constant.NWAL1700Constant.EFF_FROM;
import static business.blap.NWAL1700.constant.NWAL1700Constant.EFF_THRU;
import static business.blap.NWAL1700.constant.NWAL1700Constant.EXIST_CK_ROW_NUM;
import static business.blap.NWAL1700.constant.NWAL1700Constant.GLOBAL_CMPY_CODE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.HLD_RSN_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.LEVEL_H;
import static business.blap.NWAL1700.constant.NWAL1700Constant.LOC_GRP_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.LOC_GRP_CD_CUSA;
import static business.blap.NWAL1700.constant.NWAL1700Constant.ORD_PROC_NODE_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.ORD_PROC_NODE_PRFL_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.ORD_PROC_TP;
import static business.blap.NWAL1700.constant.NWAL1700Constant.ORD_PROC_TP_LVL_CD;
import static business.blap.NWAL1700.constant.NWAL1700Constant.ROW_NUM;
import static business.blap.NWAL1700.constant.NWAL1700Constant.SALES_DATE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1700Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/05   Fujitsu         M.Suzuki        Create          QC#6567
 * 2017/03/07   Fujitsu         W.Honda         Update          QC#16855
 * 2017/12/14   Fujitsu         Mz.Takahashi    Update          QC#19804(Sol349)
 * 2018/11/08   Fujitsu         H.Kumagai       Update          QC#29127
 *</pre>
 */
public final class NWAL1700Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1700Query MY_INSTANCE = new NWAL1700Query();

    /**
     * Private constructor
     */
    private NWAL1700Query() {
        super();
    }

    /**
     * Get the NWAL1700Query instance.
     * @return NWAL1700Query instance
     */
    public static NWAL1700Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param level String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorkFlowPulldownList(String level) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        //2016/04/04 S21_NA#6536 MOD Start --------------
        ssmParam.put(ORD_PROC_TP_LVL_CD, level);
        //2016/04/04 S21_NA#6536 MOD Start --------------
        return getSsmEZDClient().queryObjectList("getWorkFlowPulldownList", ssmParam);
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSubReasonPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getSubReasonPulldownList", ssmParam);
    }

    /**
     * @param nodeCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorkFlowControlsPulldownList(String nodeCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ORD_PROC_NODE_CD, nodeCd);
        return getSsmEZDClient().queryObjectList("getWorkFlowControlsPulldownList", ssmParam);
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getForecastingPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getForecastingPulldownList", ssmParam);
    }

    // Del Start 2017/12/14 QC#19804(Sol349)
    // Add Start 2017/03/07 QC#16855
    /**
     * @return S21SsmEZDResult
     */
    /*
    public S21SsmEZDResult getTrtyGrpTpPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getTrtyGrpTpPulldownList", ssmParam);
    }
    */
    // Add End 2017/03/07 QC#16855
    // Del End 2017/12/14 QC#19804(Sol349)

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineCategoryPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getLineCategoryPulldownList", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSystemName(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ROW_NUM, bizMsg.C.length());
        return getSsmEZDClient().queryObjectList("getSystemName", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getScreenList(NWAL1700CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ROW_NUM, bizMsg.D.length());
        return getSsmEZDClient().queryObjectList("getScreenList", ssmParam);
    }

    /**
     * @param bizMsg bizMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeader(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        String slsDt = ZYPDateUtil.getSalesDate();
        ssmParam.put(SALES_DATE, slsDt);

        return getSsmEZDClient().queryObject("getHeader", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineCategory(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        ssmParam.put(ROW_NUM, bizMsg.A.length());
        return getSsmEZDClient().queryObjectList("getLineCategory", ssmParam);
    }

    /**
     * @param bizMsg bizMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceListType(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getPriceListType", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPublishToExternalSource(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getPublishToExternalSource", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderEntryScreen(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getOrderEntryScreen", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationRole(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        ssmParam.put(LOC_GRP_CD, LOC_GRP_CD_CUSA);
        return getSsmEZDClient().queryObjectList("getLocationRole", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCategoryValueSet(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        ssmParam.put(DS_ORD_CATG_CD, bizMsg.dsOrdCatgCd.getValue());
        ssmParam.put(ROW_NUM, bizMsg.F.length());
        return getSsmEZDClient().queryObjectList("getCategoryValueSet", ssmParam);
    }

    /**
     * @param glblMsg NWAL1700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineValueSet(NWAL1700SMsg glblMsg, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, glblMsg.dsOrdTpCd.getValue());
        // 2018/11/08 Update Start QC#29127
//        ssmParam.put(EFF_FROM, glblMsg.effFromDt.getValue());
//        ssmParam.put(EFF_THRU, glblMsg.effThruDt.getValue());
        ssmParam.put(EFF_FROM, slsDt);
        ssmParam.put(EFF_THRU, slsDt);
        // 2018/11/08 Update End   QC#29127
        ssmParam.put(ROW_NUM, glblMsg.G.length());
        return getSsmEZDClient().queryObjectList("getLineValueSet", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReasonName(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_NM, bizMsg.dsOrdTpNm.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        }

        ssmParam.put(ROW_NUM, EXIST_CK_ROW_NUM);
        return getSsmEZDClient().queryObject("getReasonName", ssmParam);
    }

    /**
     * @param ordProcTpCd String
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getOrderProcTp(NWAL1700CMsg bizMsg) {
    public S21SsmEZDResult getOrderProcTp(String ordProcTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        // 2016/04/04 S21_NA#6357 MOD Start
        // ssmParam.put(ORD_PROC_TP, bizMsg.ordProcTpCd);
        ssmParam.put(ORD_PROC_TP, ordProcTpCd);
        // 2016/04/04 S21_NA#6357 MOD End
        ssmParam.put(ORD_PROC_TP_LVL_CD, LEVEL_H);
        ssmParam.put(ROW_NUM, EXIST_CK_ROW_NUM);
        return getSsmEZDClient().queryObject("getOrderProcTp", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxOrdTpCd(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObject("getMaxOrdTpCd", ssmParam);
    }

    /**
     * @param hldRsn String
     * @param nodeCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldCtrl(String hldRsn, String nodeCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(HLD_RSN_CD, hldRsn);
        ssmParam.put(APPLY_DS_ORD_TP_CD, nodeCd);
        ssmParam.put(ROW_NUM, EXIST_CK_ROW_NUM);
        return getSsmEZDClient().queryObject("getHldCtrl", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRvwReq(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ORD_PROC_NODE_PRFL_CD, bizMsg.crApvlNodePrflCd.getValue());
        ssmParam.put(ROW_NUM, EXIST_CK_ROW_NUM);
        return getSsmEZDClient().queryObject("getRvwReq", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizAppId(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getBizAppId", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationRoleReln(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getLocationRoleReln", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceListReln(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getPriceListReln", ssmParam);
    }

    /**
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSystemReln(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_TP_CD, bizMsg.dsOrdTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getSystemReln", ssmParam);
    }

    /**
     * Get Bill To Account Name
     * @param billToAccountNum Bill To Account Number
     * @param salesDate Sales Date
     * @return String Bill To Account Name
     */
    public String getBillToAccountNm(String billToAccountNum, String salesDate) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(BILL_TO_ACCT_NUM, billToAccountNum);
        ssmParam.put(SALES_DATE, salesDate);
        ssmParam.put(ROW_NUM, EXIST_CK_ROW_NUM);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getBillToAccountNm", ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return "";
        }
        return (String) ssmResult.getResultObject();
    }

    /**
     * Get Transaction Type
     * @param lineCatgCd Line Category Code
     * @return Transaction Type
     */
    public Map<String, String> getTransactionType(String lineCatgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(DS_ORD_LINE_CATG_CD, lineCatgCd);
        ssmParam.put(ROW_NUM, EXIST_CK_ROW_NUM);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getTransactionType", ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (Map<String, String>) ssmResult.getResultObject();
    }

    /**
     * getMaxLineSortNum
     * @param bizMsg NWAL1700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxLineSortNum(NWAL1700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObject("getMaxLineSortNum", ssmParam);
    }

    //2017/12/14 Add Start QC#19804(Sol349)
    /**
     * isExitsTrtyGrp
     * @param bizMsg NWAL1700CMsg
     * @return Boolean
     */
    public Boolean isExitsTrtyGrp(String trtyGrpTpCd, String lineBizTpCd) {
        Boolean ret = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put("trtyGrpTpCd", trtyGrpTpCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExitsTrtyGrp", ssmParam);

        if (result.isCodeNormal()){
            if ((Integer)result.getResultObject() > 0) {
                ret = true;
            }
        }
        return ret;
    }
    //2017/12/14 Add End QC#19804(Sol349)
}