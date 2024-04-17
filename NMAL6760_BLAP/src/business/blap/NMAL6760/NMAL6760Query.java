/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6760;

import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_CD_ALL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_ACCT;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_LEASE_ACCT;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_LEASE_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.STATUS_CD_ACTIVE;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL6760.constant.NMAL6760Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL6760Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/30   Fujitsu         C.Yokoi         Update          CSA-QC#14340
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19574
 * 2017/11/13   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 *</pre>
 */
public final class NMAL6760Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL6760Query MY_INSTANCE = new NMAL6760Query();

    /**
     * Private constructor
     */
    private NMAL6760Query() {
        super();
    }

    /**
     * Get the NMAL6760Query instance.
     * @return NMAL6760Query instance
     */
    public static NMAL6760Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * In case of Quick Lookup
     * @param bizMsg NMAL6760CMsg
     * @param sMsg NMAL6760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAccountList(NMAL6760CMsg bizMsg, NMAL6760SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", sMsg.C.length() + 1);

        String dsAcctActvCustFlg = "";
        if (STATUS_CD_ACTIVE.equals(bizMsg.xxAcctSrchStsCd_D2.getValue())) {
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;

        }
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);

        // Search Filters
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_01)) {
            ssmParam.put("dsAcctNm", bizMsg.dsAcctNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_01)) {
            String shipFromFullCmpyAddr = appendPercentChar(bizMsg.xxAllLineAddr_01.getValue());
            ssmParam.put("shipFromFullCmpyAddr", shipFromFullCmpyAddr);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctyAddr_01)) {
            ssmParam.put("ctyAddr", bizMsg.ctyAddr_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.stCd_DP)) {
            ssmParam.put("stCd", bizMsg.stCd_DP.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.postCd_01)) {
            ssmParam.put("postCd", bizMsg.postCd_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_01)) {
            ssmParam.put("dsAcctNum", bizMsg.dsAcctNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.locNum_01)) {
            ssmParam.put("locNum", bizMsg.locNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dbaNm_01)) {
            ssmParam.put("dbaNm", bizMsg.dbaNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctLegalNm_01)) {
            ssmParam.put("dsAcctLegalNm", bizMsg.dsAcctLegalNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctGrpCd_DP)) {
            ssmParam.put("dsAcctGrpCd", bizMsg.dsAcctGrpCd_DP.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctClsCd_DP)) {
            ssmParam.put("dsAcctClsCd", bizMsg.dsAcctClsCd_DP.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsLocNm_01)) {
            ssmParam.put("locNm", bizMsg.dsLocNm_01.getValue());
        }

        // Additional Search Filters
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctDunsNum_01)) {
            ssmParam.put("dsAcctDunsNum", bizMsg.dsAcctDunsNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsUltDunsNum_01)) {
            ssmParam.put("dsUltDunsNum", bizMsg.dsUltDunsNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsCustSicDescTxt_01)) {
            ssmParam.put("dsCustSicDescTxt", bizMsg.dsCustSicDescTxt_01.getValue());
        }
        String ctacPsnFlg = "";
        // START 2017/08/07 T.Tsuchida [QC#19574,MOD]
        //if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnTelNum_01)) {
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnTelNum_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnEmlAddr_01)) {
            ctacPsnFlg = ZYPConstant.FLG_ON_Y;
        }
        // END 2017/08/07 T.Tsuchida [QC#19574,MOD]
        ssmParam.put("ctacPsnFlg", ctacPsnFlg);
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_01)) {
            ssmParam.put("ctacPsnFirstNm", bizMsg.ctacPsnFirstNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_01)) {
            ssmParam.put("ctacPsnLastNm", bizMsg.ctacPsnLastNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnTelNum_01)) {
            ssmParam.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.PHONE_WORK);
            ssmParam.put("ctacPsnTelNum", bizMsg.ctacPsnTelNum_01.getValue());
        }
        // START 2017/08/07 T.Tsuchida [QC#19574,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnEmlAddr_01)) {
            ssmParam.put("dsCtacPntTpCdIsEml", DS_CTAC_PNT_TP.EMAIL_WORK);
            ssmParam.put("ctacPsnEmlAddr", bizMsg.ctacPsnEmlAddr_01.getValue());
        }
        // END 2017/08/07 T.Tsuchida [QC#19574,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctItrlFlg_C1)) {
            ssmParam.put("dsAcctItrlFlg", bizMsg.dsAcctItrlFlg_C1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsXtrnlRefTxt_01)) {
            ssmParam.put("dsXtrnlRefTxt", bizMsg.dsXtrnlRefTxt_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctClsCd_DP)) {
            ssmParam.put("dsAcctClsCd", bizMsg.dsAcctClsCd_DP.getValue());
        }
        String dsXrefAcctFlg = "";
        if (ZYPCommonFunc.hasValue(bizMsg.dsXrefAcctCd_01) || ZYPCommonFunc.hasValue(bizMsg.dsXrefAcctTpCd_DP)) {
            dsXrefAcctFlg = ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_01)) {
            ssmParam.put("billToCustCd", bizMsg.billToCustCd_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd_01)) {
            ssmParam.put("shipToCustCd", bizMsg.shipToCustCd_01.getValue());
        }
        ssmParam.put("dsXrefAcctFlg", dsXrefAcctFlg);
        if (ZYPCommonFunc.hasValue(bizMsg.dsXrefAcctCd_01)) {
            ssmParam.put("dsXrefAcctCd", bizMsg.dsXrefAcctCd_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsXrefAcctTpCd_DP)) {
            ssmParam.put("dsXrefAcctTpCd", bizMsg.dsXrefAcctTpCd_DP.getValue());
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_01.getValue())) {
            ssmParam.put("xxChkBox_01", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("xxChkBox_01", ZYPConstant.FLG_OFF_N);
        }
        if (DISP_HRCH_ACCT_CD_ALL.equals(bizMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dispBillToFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("dispShipToFlg", ZYPConstant.FLG_OFF_N);
        } else if (DISP_HRCH_ACCT_CD_BILL.equals(bizMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dispBillToFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("dispShipToFlg", ZYPConstant.FLG_OFF_N);

        } else if (DISP_HRCH_ACCT_CD_SHIP.equals(bizMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dispBillToFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("dispShipToFlg", ZYPConstant.FLG_ON_Y);
        }
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(bizMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_02.getValue())) {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_03.getValue())) {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("rgtnSts_Actv", RGTN_STS.READY_FOR_ORDER_TAKING);
        if (ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)) {
            ssmParam.put("dispRelnFlg", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("dsAcctRelnTp_PrntAcct", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("rgtnSts_Ready", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnSts_Pend", RGTN_STS.PENDING_COMPLETION);

        if (!ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4) && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_RT)
                && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_RT) && !ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_RT)) {
            ssmParam.put("isDisplayRelated", ZYPConstant.FLG_OFF_N);
        }

        return getSsmEZDClient().queryObjectList("getDsAccountList", ssmParam);

    }
    /**
     * getRelatedAccountList
     * @param cMsg NMAL6760CMsg
     * @param sMsg NMAL6760SMsg
     * @param orgAccountNum string
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedAccountList(NMAL6760CMsg cMsg, NMAL6760SMsg sMsg, String orgAccountNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNum", sMsg.C.length());
        ssmParam.put("dsAcctNum", orgAccountNum);
        ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        // Add Start 2017/11/13 QC#17322(Sol#174)
        ssmParam.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // Add End 2017/11/13 QC#17322(Sol#174)

        String rgtnStsCd = "";
        String dsAcctActvCustFlg = "";
        if (STATUS_CD_ACTIVE.equals(cMsg.xxAcctSrchStsCd_D2.getValue())) {
            rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("rgtnStsCd", rgtnStsCd);
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);

        // Bill To's Only, Lease  Bill To's Only
        if (DISP_RELN_ACCT_CD_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_HRCH_ACCT_CD_BILL.equals(cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dsAcctRelnBillToFlg", ZYPConstant.FLG_ON_Y);
        }
        // Ship To's Only
        if (DISP_RELN_ACCT_CD_SHIP.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_HRCH_ACCT_CD_SHIP.equals(cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dsAcctRelnShipToFlg", ZYPConstant.FLG_ON_Y);
        }
        // Lease  Accts Only, Lease  Bill To's Only
        if (DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnTpCdLease", DS_ACCT_RELN_TP.LEASE_ACCOUNT);
        }
        // Display Account or Location
        if (DISP_RELN_ACCT_CD_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                ) {
            ssmParam.put("dispAcctFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("dispLocFlg", ZYPConstant.FLG_ON_Y);
        }
        // Related Account Name
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_RT)) {
            ssmParam.put("dsAcctNm_RT", cMsg.dsAcctNm_RT.getValue());
        }
        // Related Account Number
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_RT)) {
            ssmParam.put("dsAcctNum_RT", cMsg.dsAcctNum_RT.getValue());
        }
        // Related Address
        if (ZYPCommonFunc.hasValue(cMsg.xxAllLineAddr_RT)) {
            String xxAllLineAddrRt = appendPercentChar(cMsg.xxAllLineAddr_RT.getValue());
            ssmParam.put("xxAllLineAddr_RT", xxAllLineAddrRt);
        }

        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_OFF_N);
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_02.getValue())) {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_03.getValue())) {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("rgtnSts_Actv", RGTN_STS.READY_FOR_ORDER_TAKING);

        // Origin account has its own filter conditions. Account level filter has been applied in before this search, only apply location level filter only.  
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_01.getValue())) {
            ssmParam.put("xxChkBox_01", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("xxChkBox_01", ZYPConstant.FLG_OFF_N);
        }
        if (DISP_HRCH_ACCT_CD_ALL.equals(cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dispBillToFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("dispShipToFlg", ZYPConstant.FLG_OFF_N);
        } else if (DISP_HRCH_ACCT_CD_BILL.equals(cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dispBillToFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("dispShipToFlg", ZYPConstant.FLG_OFF_N);

        } else if (DISP_HRCH_ACCT_CD_SHIP.equals(cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            ssmParam.put("dispBillToFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("dispShipToFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxAllLineAddr_01)) {
            String shipFromFullCmpyAddr = appendPercentChar(cMsg.xxAllLineAddr_01.getValue());
            ssmParam.put("shipFromFullCmpyAddr", shipFromFullCmpyAddr);
        }
        if (ZYPCommonFunc.hasValue(cMsg.ctyAddr_01)) {
            ssmParam.put("ctyAddr", cMsg.ctyAddr_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.stCd_DP)) {
            ssmParam.put("stCd", cMsg.stCd_DP.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.postCd_01)) {
            ssmParam.put("postCd", cMsg.postCd_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.locNum_01)) {
            ssmParam.put("locNum", cMsg.locNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsLocNm_01)) {
            ssmParam.put("locNm", cMsg.dsLocNm_01.getValue());
        }
        String dsXrefAcctFlg = "";
        if (ZYPCommonFunc.hasValue(cMsg.dsXrefAcctCd_01) || ZYPCommonFunc.hasValue(cMsg.dsXrefAcctTpCd_DP)) {
            dsXrefAcctFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("dsXrefAcctFlg", dsXrefAcctFlg);
        if (ZYPCommonFunc.hasValue(cMsg.dsXrefAcctCd_01)) {
            ssmParam.put("dsXrefAcctCd", cMsg.dsXrefAcctCd_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsXrefAcctTpCd_DP)) {
            ssmParam.put("dsXrefAcctTpCd", cMsg.dsXrefAcctTpCd_DP.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustCd_01)) {
            ssmParam.put("billToCustCd", cMsg.billToCustCd_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_01)) {
            ssmParam.put("shipToCustCd", cMsg.shipToCustCd_01.getValue());
        }

        return getSsmEZDClient().queryObjectList("getRelatedAccountList", ssmParam);
    }

    private String appendPercentChar(String value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return value;
        }
        if (value.endsWith(NMAL6760Constant.CHAR_PERCENT)) {
            return value;
        }
        return value + NMAL6760Constant.CHAR_PERCENT;
    }
}
