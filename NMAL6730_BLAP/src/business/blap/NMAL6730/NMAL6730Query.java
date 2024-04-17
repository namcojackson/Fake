/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6730;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_RULE_RCPNT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/07/14   Fujitsu         R.Nakamura      Update          QC#8527
 *</pre>
 */
public final class NMAL6730Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6730Query INSTANCE = new NMAL6730Query();

    /**
     * Constructor.
     */
    private NMAL6730Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6730Query
     */
    public static NMAL6730Query getInstance() {
        return INSTANCE;
    }

    /**
     * getBillToCustInfoByBillToCustCd
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @param sMsg NMAL6730SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustInfoHeader(String glblCmpyCd, String billToCustCd, NMAL6730SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryEZDMsg("getBillToCustInfoHeader", ssmParam, sMsg);
    }

    /**
     * getBillToCustInfoByBillToCustCd
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @param sMsg NMAL6730SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFinancialInfo(String glblCmpyCd, String billToCustCd, NMAL6730SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryEZDMsg("getFinancialInfo", ssmParam, sMsg);
    }

    /**
     * getBillToCustInfoByBillToCustCd
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @param locNum String
     * @param sMsg NMAL6730SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceSourceList(String glblCmpyCd, String billToCustCd, String locNum, NMAL6730SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("locNum", locNum);
        return getSsmEZDClient().queryEZDMsgArray("getInvoiceSourceList", ssmParam, sMsg.A);
    }

    /**
     * getBillToCustInfoByBillToCustCd
     * @param glblCmpyCd String
     * @param billToCustPk BigDecimal
     * @param sMsg NMAL6730SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillingCycleInfo(String glblCmpyCd, BigDecimal billToCustPk, NMAL6730SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustPk", billToCustPk);
        return getSsmEZDClient().queryEZDMsg("getBillingCycleInfo", ssmParam, sMsg);
    }

    /**
     * getDsAcctRefAttrb
     * @param glblCmpyCd String
     * @param locNum String
     * @param sMsg NMAL6730SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctRefAttrb(String glblCmpyCd, String locNum, NMAL6730SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        return getSsmEZDClient().queryEZDMsgArray("getDsAcctRefAttrb", ssmParam, sMsg.K);
    }

    /**
     * getBillToCustInfoByBillToCustCd
     * @param cMsg NMAL6730CMsg
     * @param sMsg NMAL6730SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTemplate(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd_H1.getValue());
        param.put("dsCustArTmplNm", cMsg.dsCustArTmplNm_F1.getValue());
        return getSsmEZDClient().queryEZDMsg("getTemplate", param, sMsg);
    }
    /**
     * GetCltPtfoNm
     * @param cMsg NMAL6730CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltPtfoNm(NMAL6730CMsg cMsg) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("cltPtfoCd", cMsg.cltPtfoCd_F1.getValue());

        return getSsmEZDClient().queryObject("getCltPtfoNm", query);
    }
    /**
     * getCustConslTermCdList
     * @param cMsg NMAL6730CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustConslTermCdList(NMAL6730CMsg cMsg) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getCustConslTermCdList", query);
    }
    /**
     * countRemId
     * @param cMsg NMAL6730CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countRemId(NMAL6730CMsg cMsg) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", cMsg.glblCmpyCd_H1.getValue());
        query.put("remId", cMsg.remId_F1.getValue());

        return getSsmEZDClient().queryObject("countRemId", query);
    }
    
    // QC#7781
    /**
     * countContact
     * @param cMsg NMAL6730CMsg
     * @param acMsg NMAL6730_ACMsg
     * @param ctacPsnPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countContact(NMAL6730CMsg cMsg, NMAL6730_ACMsg acMsg, BigDecimal ctacPsnPk) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", cMsg.glblCmpyCd_H1.getValue());
        query.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
        // Add Start 2016/07/14 QC#8527
        query.put("locNum", cMsg.locNum_H1.getValue());
        // Add End 2016/07/14 QC#8527
        query.put("ctacPsnPk", ctacPsnPk);

        return getSsmEZDClient().queryObject("countContact", query);
    }
    
    // QC#7781
    /**
     * countResource
     * @param acMsg NMAL6730_ACMsg
     * @param psnCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countResource(NMAL6730_ACMsg acMsg, String psnCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("psnCd", psnCd);

        return getSsmEZDClient().queryObject("countResource", query);
    }
    
    /**
     * @param dsCustInvRulePk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceSourceListInternalReview(BigDecimal dsCustInvRulePk) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsCustInvRulePk", dsCustInvRulePk);
        query.put("invRuleRcpntTp_Internal", INV_RULE_RCPNT_TP.INTERNAL);

        return getSsmEZDClient().queryObjectList("getInvoiceSourceListInternalReview", query);
    }
    
    /**
     * @param dsCustInvRulePk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceSourceListExternalContact(BigDecimal dsCustInvRulePk) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsCustInvRulePk", dsCustInvRulePk);
        query.put("invRuleRcpntTp_External", INV_RULE_RCPNT_TP.EXTERNAL);

        return getSsmEZDClient().queryObjectList("getInvoiceSourceListExternalContact", query);
    }
}
