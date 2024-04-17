/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0120;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;

import business.blap.NFAL0120.common.NFAL0120CommonLogic;
import business.blap.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Class name: NFAL0120Query
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0090Query
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public final class NFAL0120Query extends S21SsmEZDQuerySupport implements NFAL0120Constant {

    private int rowNum;
    
    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();
    
    /**
     * Singleton instance.
     */
    private static final NFAL0120Query INSTANCE = new NFAL0120Query();

    /**
     * Constructor.
     */
    private NFAL0120Query() {
        super();
        rowNum = MAX_ROW_CNT + 1;
    }

    /**
     * Singleton instance getter.
     * @return NFAL0120Query
     */
    public static NFAL0120Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFAL0120Query.xml id="getResultArReclass"
     * @param bizMsg NFAL0120CMsg
     * @param globalMsg NFAL0120SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultArReclass(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);
        ssmParam.put("rowNum", rowNum);

        return getSsmEZDClient().queryEZDMsgArray("getResultArReclass", ssmParam, globalMsg.A);
    }

    /**
     * NFAL0120Query.xml id="getResult"
     * @param bizMsg NFAL0120CMsg
     * @param globalMsg NFAL0120SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResult(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);
        ssmParam.put("rowNum", rowNum);

        List<EZDCItem> crdrCondValList = new ArrayList<EZDCItem>();

        /*
        crdrCondValList.add(bizMsg.coaCmpyCd);
        crdrCondValList.add(bizMsg.coaBrCd_3);
        crdrCondValList.add(bizMsg.coaCcCd);
        crdrCondValList.add(bizMsg.coaAcctCd);
        crdrCondValList.add(bizMsg.drCoaProdCd);
        crdrCondValList.add(bizMsg.coaChCd_3);
        crdrCondValList.add(bizMsg.coaAfflCd);
        crdrCondValList.add(bizMsg.coaProjCd);
        crdrCondValList.add(bizMsg.coaExtnCd);
        */
        // START 2017/12/04 [QC#12525, DEL]
        // crdrCondValList.add(bizMsg.coaAcctCd_FM);
        // crdrCondValList.add(bizMsg.coaAcctCd_TO);
        // END  2017/12/04 [QC#12525, DEL]
        // START 2017/12/04 [QC#12525, ADD]
        crdrCondValList.add(bizMsg.coaCmpyCd);
        crdrCondValList.add(bizMsg.coaBrCd);
        crdrCondValList.add(bizMsg.coaCcCd);
        crdrCondValList.add(bizMsg.coaAcctCd);
        crdrCondValList.add(bizMsg.coaProdCd);
        crdrCondValList.add(bizMsg.coaChCd);
        crdrCondValList.add(bizMsg.coaAfflCd);
        crdrCondValList.add(bizMsg.coaProjCd);
        crdrCondValList.add(bizMsg.coaExtnCd);
        // END   2017/12/04 [QC#12525, ADD]
        crdrCondValList.add(bizMsg.jrnlDealDrAmt_FM);
        crdrCondValList.add(bizMsg.jrnlDealDrAmt_TO);
        
        ssmParam.put("hasCrDrCondtion", NO);

    	for (EZDCItem ezdcItem : crdrCondValList) {
    		if (hasValue(ezdcItem)) {
    			ssmParam.put("hasCrDrCondtion", YES);
    			break;
    		}
    	}

        return getSsmEZDClient().queryEZDMsgArray("getResult", ssmParam, globalMsg.A);
        
    }

    /**
     * getResultParam
     * @param bizMsg NFAL0120CMsg
     * @param globalMsg NFAL0120SMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getResultParam(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);
        ssmParam.put("rowNum", rowNum);

        List<EZDCItem> crdrCondValList = new ArrayList<EZDCItem>();
        // START 2017/12/04 [QC#12525, DEL]
        // crdrCondValList.add(bizMsg.coaAcctCd_FM);
        // crdrCondValList.add(bizMsg.coaAcctCd_TO);
        // END   2017/12/04 [QC#12525, DEL]
        // START 2017/12/04 [QC#12525, ADD]
        crdrCondValList.add(bizMsg.coaCmpyCd);
        crdrCondValList.add(bizMsg.coaBrCd);
        crdrCondValList.add(bizMsg.coaCcCd);
        crdrCondValList.add(bizMsg.coaAcctCd);
        crdrCondValList.add(bizMsg.coaProdCd);
        crdrCondValList.add(bizMsg.coaChCd);
        crdrCondValList.add(bizMsg.coaAfflCd);
        crdrCondValList.add(bizMsg.coaProjCd);
        crdrCondValList.add(bizMsg.coaExtnCd);
        // END   2017/12/04 [QC#12525, ADD]
        crdrCondValList.add(bizMsg.jrnlDealDrAmt_FM);
        crdrCondValList.add(bizMsg.jrnlDealDrAmt_TO);

        ssmParam.put("hasCrDrCondtion", NO);

        for (EZDCItem ezdcItem : crdrCondValList) {
            if (hasValue(ezdcItem)) {
                ssmParam.put("hasCrDrCondtion", YES);
                break;
            }
        }

        return ssmParam;
    }

    /**
     * NFAL0120Query.xml id="getResultDrForReport"
     * @param bizMsg NFAL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultDrForReport(NFAL0120CMsg bizMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);

        return getSsmEZDClient().queryObjectList("getResultDrForReport", ssmParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="getResultCrForReport"
     * @param bizMsg NFAL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultCrForReport(NFAL0120CMsg bizMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);

        return getSsmEZDClient().queryObjectList("getResultCrForReport", ssmParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="countResultDr"
     * @param bizMsg NFAL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countResultDr(NFAL0120CMsg bizMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);

        return getSsmEZDClient().queryObjectList("countResultDr", ssmParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="countResultCr"
     * @param bizMsg NFAL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countResultCr(NFAL0120CMsg bizMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);

        return getSsmEZDClient().queryObjectList("countResultCr", ssmParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="getResultDrCr"
     * @param bizMsg NFAL0120CMsg
     * @param globalMsg NFAL0120SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultDrCr(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);
        ssmParam.put("rowNum", rowNum);

        return getSsmEZDClient().queryObjectList("getResultDrCr", ssmParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="getDebitTotal"
     * @param bizMsg NFAL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDebitTotal(NFAL0120CMsg bizMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);

        return getSsmEZDClient().queryObjectList("getDebitTotal", ssmParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="getCreditTotal"
     * @param bizMsg NFAL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditTotal(NFAL0120CMsg bizMsg, boolean isArchived) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParams(bizMsg, ssmParam, isArchived);

        return getSsmEZDClient().queryObjectList("getCreditTotal", ssmParam, -1, -1);
    }

    private void setParams(NFAL0120CMsg bizMsg, Map<String, Object> ssmParam, boolean isArchived) {

        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        ssmParam.put("glDt_FR", bizMsg.glDt_FR.getValue());
        ssmParam.put("glDt_TO", bizMsg.glDt_TO.getValue());

        ssmParam.put("sysSrcCd", bizMsg.sysSrcCd_3.getValue());
        
        //---- start add 2016/05/31
        String strYYYYMM = NFAL0120CommonLogic.getYYYYMM(bizMsg.glPerNm.getValue());
        if (hasValue(strYYYYMM)) {
            strYYYYMM = strYYYYMM + "%";
        }
        ssmParam.put("yyyymm", strYYYYMM);
        ssmParam.put("divider", DIVIDER);
        //---- end 2016/05/31
        
        /*ssmParam.put("trxCd", bizMsg.trxCd_3.getValue());
        ssmParam.put("trxRsnCd", bizMsg.trxRsnCd_3.getValue());

        // ssmParam.put("ajeId", bizMsg.ajeId.getValue());
        ssmParam.put("coaCmpyCd", bizMsg.coaCmpyCd.getValue());
        ssmParam.put("coaBrCd", bizMsg.coaBrCd_3.getValue());
        ssmParam.put("coaCcCd", bizMsg.coaCcCd.getValue());
        ssmParam.put("coaAcctCd", bizMsg.coaAcctCd.getValue());
        ssmParam.put("drCoaProdCd", bizMsg.drCoaProdCd.getValue());
        ssmParam.put("coaChCd", bizMsg.coaChCd_3.getValue());
        ssmParam.put("coaAfflCd", bizMsg.coaAfflCd.getValue());
        ssmParam.put("coaProjCd", bizMsg.coaProjCd.getValue());
        ssmParam.put("coaExtnCd", bizMsg.coaExtnCd.getValue());
        */

        /*
        ssmParam.put("billToCustCd", bizMsg.billToCustCd.getValue() + "%");
        ssmParam.put("vndCd", bizMsg.vndCd.getValue() + "%");
        ssmParam.put("ajeInvNum", bizMsg.ajeInvNum.getValue() + "%");
        ssmParam.put("prmoPk", bizMsg.prmoPk.getValue() + "%");
        ssmParam.put("ajeItemCd", bizMsg.ajeItemCd.getValue() + "%");

        ssmParam.put("coaProdCd", bizMsg.coaProdCd.getValue());
        ssmParam.put("tocCd", bizMsg.tocCd.getValue() + "%");
        ssmParam.put("soNum", bizMsg.soNum.getValue() + "%");
        */
        
        // CSA modification add
        if (bizMsg.xxQueryFltrTxt.getValue() != null && !"".equals(bizMsg.xxQueryFltrTxt.getValue())) {

            // START 2016/08/29 S.Fujita [QC#4105,MOD]
            //---- start 2016/08/10 Reference text should match ignoring upper/lower cases. DB field and search string are changed to both upper.
//          ssmParam.put("anyWord", "%" + bizMsg.xxQueryFltrTxt.getValue().toUpperCase() + "%");
            //---- end 2016/08/10
            // START 2016/12/21 E.Kameishi [QC#16730, MOD]
            if (ZYPCommonFunc.hasValue(bizMsg.srchOptTxt)) {
                ssmParam.put("anyWord", bizMsg.xxQueryFltrTxt.getValue().toUpperCase());
            } else {
                ssmParam.put("psnNum", bizMsg.xxQueryFltrTxt.getValue().toUpperCase());
                ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
                ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
                S21SsmEZDResult toc = NFAL0120Query.getInstance().getTocCd(ssmParam);
                if (toc.isCodeNormal()) {
                    Map<String, Object> map = (Map<String, Object>) toc.getResultObject();
                    ssmParam.put("tocCd", (String) map.get("TOC_CD"));
                } else {
                    ssmParam.put("anyWord", "%" + bizMsg.xxQueryFltrTxt.getValue().toUpperCase() + "%");
                }
                // START 2016/10/26 E.Kameishi [QC#14177,ADD]
                //if (ZYPCommonFunc.hasValue(bizMsg.srchOptTxt)) {
                //    ssmParam.put("anyWord", bizMsg.xxQueryFltrTxt.getValue().toUpperCase());
                //} else {
                //    ssmParam.put("anyWord", "%" + bizMsg.xxQueryFltrTxt.getValue().toUpperCase() + "%");
                //}
                // END 2016/10/26 E.Kameishi [QC#14177,ADD]
                // END 2016/12/21 E.Kameishi [QC#16730, MOD]
            }
            // END   2016/08/29 S.Fujita [QC#4105,MOD]
        }

        // START 2017/12/04 [QC#12525, ADD]
        if (bizMsg.xxQueryFltrTxt_AT.getValue() != null && !"".equals(bizMsg.xxQueryFltrTxt_AT.getValue())
                && hasValue(bizMsg.ajeTrxTpCd_3)) {
            String srchColNm = getSrchColNm(bizMsg.ajeTrxTpCd_3.getValue());
            ssmParam.put("srchColNm", srchColNm);
        }
        // END   2017/12/04 [QC#12525, ADD]

        ssmParam.put("suppressReclass", bizMsg.eventId.getValue());
        ssmParam.put("trxCdReclass", TRX_CD_RECLASS);

        ssmParam.put("arReclass", bizMsg.eventId.getValue());

        if (hasValue(bizMsg.glDt_FR)) {
            ssmParam.put("glDtYYYYMM", bizMsg.glDt_FR.getValue().substring(0, 6) + "%");
        }
        ssmParam.put("trxCdArRcls", TRX_CD_AR_RECLASS);
        ssmParam.put("trxRsnCdArRcls", TRX_RSN_CD_AR_RECLASS);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < TRX_RSN_CD_RECLASS.length; i++) {
            String trxRsnCd = TRX_RSN_CD_RECLASS[i];
            list.add(trxRsnCd);
        }
        ssmParam.put("trxRsnCdList", list);
        
        // START    2019/12/19 [QC#53730, DEL]
        // check if archived
        //if (common.isArchived(bizMsg)) {
        //if (isArchived) {
        //    ssmParam.put("archived", YES);
        //} else {
        //    ssmParam.put("archived", NO);
        //}
        // END     2019/12/19 [QC#53730, DEL]
    }

    /**
     * NFAL0120Query.xml id="checkBillToCustCd"
     * @param queryMap Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkBillToCustCd(Map<String, String> queryMap) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("billToCustCd", queryMap.get("billToCustCd"));

        return getSsmEZDClient().queryObjectList("checkBillToCustCd", queryParam, -1, -1);
    }

    /**
     * NFAL0120Query.xml id="checkVndCd"
     * @param queryMap Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkVndCd(Map<String, String> queryMap) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("vndCd", queryMap.get("vndCd"));

        return getSsmEZDClient().queryObjectList("checkVndCd", queryParam, -1, -1);
    }
    
    /**
     * NFAL0120Query.xml id="getLastMonth"
     * @return String
     */
    public String getLastMonth() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("lastMonth", common.getLastMonth());
        
        S21SsmEZDResult rtn = getSsmEZDClient().queryObject("getLastMonth", ssmParam);
        return (String) rtn.getResultObject();
    }

    /**
     * NFAL0120Query.xml id="chkIsArhived"
     * @return boolean
     */
    public String chkIsArhived() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        
        S21SsmEZDResult rtn = getSsmEZDClient().queryObject("isArchived", ssmParam);
        Integer rtnI = (Integer) rtn.getResultObject();
        
        if (rtnI.intValue() == 0) {
            return YES;
        }
        return NO;
    }
    
    //---- start add 2016/05/31
    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", "NFAL0120");
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
    //---- end 2016/05/31

    // START 2016/08/29 S.Fujita [QC#4105,ADD]
    /**
     * getTocCd
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getTocCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getTocCd", ssmParam);
    }
   // END   2016/08/29 S.Fujita [QC#4105,ADD]

    // START 2017/12/04 [QC#12525,ADD]
    public String getSrchColNm(String ajeTrxTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ajeTrxTpCd", ajeTrxTpCd);
        return (String) getSsmEZDClient().queryObject("getSrchColNm", params).getResultObject();
    }
    // END   2017/12/04 [QC#12525,ADD]
}
