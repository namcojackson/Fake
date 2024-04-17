/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL6710;

import static business.blap.NMAL6710.constant.NMAL6710Constant.CHILDREN_MAX_LEVEL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_ALL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_ACCT;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_LEASE_ACCT;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_LEASE_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.MAX_DL_SIZE;
import static business.blap.NMAL6710.constant.NMAL6710Constant.MOST_PARENT_MAX_LEVEL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.STATUS_CD_ACTIVE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 02/17/2016   Fujitsu         T.Murai         Update          CSA #2943
 * 02/19/2016   SRAA            Y.Chen          Update          QC#3825
 * 2016/02/26   SRAA            Y.Chen          Update          QC#3290, QC#3395
 * 2016/04/12   SRAA            Y.Chen          Update          QC#4526, QC#5890
 * 2016/06/21   SRAA            Y.Chen          Update          QC#6189
 * 2016/09/01   SRAA            Y.Chen          Update          QC#13440
 * 2016/12/05   Fujitsu         C.Yokoi         Update          QC#14340
 * 2017/10/03   CITS            T.Tokuotmi      Update          QC#21196
 * 2017/11/13   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 *</pre>
 */
public final class NMAL6710Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6710Query myInstance = new NMAL6710Query();
    private static final String NLAL1100Constant = null;

    /**
     * Constructor.
     */
    private NMAL6710Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6710Query
     */
    public static NMAL6710Query getInstance() {
        return myInstance;
    }

    /**
     * In case of Quick Lookup
     * 
     * @param bizMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     * @param globalCompanyCode String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAccountList(NMAL6710CMsg bizMsg, NMAL6710SMsg sMsg, String globalCompanyCode) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", globalCompanyCode);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", sMsg.C.length() + 1);
        
        String searchMode = "";
// QC#6189
//        if (SEARCH_MODE_CD_HRCH.equals(bizMsg.xxAcctSrchModeCd_D1.getValue())) {
//            searchMode = SEARCH_MODE_CD_HRCH;
//
//        }
        ssmParam.put("searchMode", searchMode);

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
            // QC#5890
            String shipFromFullCmpyAddrString= appendPercentChar(bizMsg.xxAllLineAddr_01.getValue());
            ssmParam.put("shipFromFullCmpyAddr", shipFromFullCmpyAddrString);
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

        //Additional Search Filters
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctDunsNum_01)) {
            ssmParam.put("dsAcctDunsNum", bizMsg.dsAcctDunsNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsUltDunsNum_01)) {
            ssmParam.put("dsUltDunsNum", bizMsg.dsUltDunsNum_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsCustSicDescTxt_01)) {
            ssmParam.put("dsCustSicDescTxt", bizMsg.dsCustSicDescTxt_01.getValue());
        }

        // QC#3395
//        String ctacPsnNmFlg = "";
//        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_01)
//                || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_01)) {
//            ctacPsnNmFlg = ZYPConstant.FLG_ON_Y;
//        }
//        ssmParam.put("ctacPsnNmFlg", ctacPsnNmFlg);
        String ctacPsnFlg = "";
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnTelNum_01)) {
            ctacPsnFlg = ZYPConstant.FLG_ON_Y;
        }
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
        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(bizMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End

        // QC#3290
        if(ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_02.getValue())){
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_OFF_N);
        }
        if(ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_03.getValue())){
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("rgtnSts_Actv", RGTN_STS.READY_FOR_ORDER_TAKING);        
        
//        // Related Account
//        if (ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)) {
//            ssmParam.put("relnAcctFlg", ZYPConstant.FLG_ON_Y);
//
//            // Bill To's Only, Lease  Bill To's Only
//            if (DISP_RELN_ACCT_CD_BILL.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())
//                    || DISP_RELN_ACCT_CD_LEASE_BILL.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
//                ssmParam.put("dsAcctRelnBillToFlg", ZYPConstant.FLG_ON_Y);
//            }
//            // Ship To's Only
//            if (DISP_RELN_ACCT_CD_SHIP.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
//                ssmParam.put("dsAcctRelnShipToFlg", ZYPConstant.FLG_ON_Y);
//            }
//            // Lease  Accts Only, Lease  Bill To's Only
//            if (DISP_RELN_ACCT_CD_LEASE_ACCT.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())
//                    || DISP_RELN_ACCT_CD_LEASE_BILL.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
//                ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.LEASE_ACCOUNT);
//            }
//            // Display Account or Location
//            if (DISP_RELN_ACCT_CD_ACCT.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())
//                    || DISP_RELN_ACCT_CD_LEASE_ACCT.equals(bizMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
//                ssmParam.put("dispAcctFlg", ZYPConstant.FLG_ON_Y);
//            } else {
//                ssmParam.put("dispLocFlg", ZYPConstant.FLG_ON_Y);
//            }
//        }
//        // Related Address
//        if (ZYPCommonFunc.hasValue(bizMsg.shipFromFullCmpyAddr_RT)) {
//            ssmParam.put("shipFromFullCmpyAddr_RT", bizMsg.shipFromFullCmpyAddr_RT.getValue());
//        }
        
        // QC#6189
        if(ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)){
            ssmParam.put("dispRelnFlg", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("dsAcctRelnTp_PrntAcct", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("rgtnSts_Ready", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnSts_Pend", RGTN_STS.PENDING_COMPLETION);


        // 2016/11/30 CSA-QC#14340 Add Start
        if(!ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4) && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_RT) && 
                !ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_RT) && !ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_RT)){
            ssmParam.put("isDisplayRelated", ZYPConstant.FLG_OFF_N);
        }
        // 2016/11/30 CSA-QC#14340 Add End

        return getSsmEZDClient().queryObjectList("getDsAccountList", ssmParam);

    }

    /**
     * getRelatedAccountList
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     * @param orgAccountNum string
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedAccountList(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg, String orgAccountNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNum", sMsg.C.length());
        ssmParam.put("dsAcctNum", orgAccountNum);
        ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

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
                || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnBillToFlg", ZYPConstant.FLG_ON_Y);
        }
        // Ship To's Only
        if (DISP_RELN_ACCT_CD_SHIP.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnShipToFlg", ZYPConstant.FLG_ON_Y);
        }
        // Lease  Accts Only, Lease  Bill To's Only
        if (DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnTpCdLease", DS_ACCT_RELN_TP.LEASE_ACCOUNT);
        }
        // Display Account or Location
        if (DISP_RELN_ACCT_CD_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                // QC#13440
                // || DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
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
            // QC#5890
            String xxAllLineAddr_RT= appendPercentChar(cMsg.xxAllLineAddr_RT.getValue());
            ssmParam.put("xxAllLineAddr_RT", xxAllLineAddr_RT);
        }

        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End     

        // QC#3290
        if(ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_02.getValue())){
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_OFF_N);
        }
        if(ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_03.getValue())){
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("rgtnSts_Actv", RGTN_STS.READY_FOR_ORDER_TAKING);        
        
        // QC#5890
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
    /**
     * getMostParentNode
     * @param glblCmpyCd String
     * @param targetDsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMostParentNode(NMAL6710CMsg cMsg, String glblCmpyCd, String targetDsAcctNum) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("targetDsAcctNum", targetDsAcctNum);
            ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

            ssmParam.put("maxLevel", MOST_PARENT_MAX_LEVEL);
            ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put("primaryOnly", ZYPConstant.FLG_ON_Y);
            
            // 02/17/2016 CSA #2943 Add Start
            if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
                ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_ON_Y);
            } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
                ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_OFF_N);
            }
            // 02/17/2016 CSA #2943 Add End

            return getSsmEZDClient().queryObjectList("getMostParentNode", ssmParam);

    }
    /**
     * getTreeListParent
     * @param cMsg NMAL6710CMsg
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param targetDsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTreeListParent(NMAL6710CMsg cMsg, String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("parentDsAcctNum", parentDsAcctNum);
        ssmParam.put("targetDsAcctNum", targetDsAcctNum);
        ssmParam.put("rowNum", cMsg.A.length());
        ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

        String rgtnStsCd = "";
        String dsAcctActvCustFlg = "";
        if (STATUS_CD_ACTIVE.equals(cMsg.xxAcctSrchStsCd_D2.getValue())) {
            rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("rgtnStsCd", rgtnStsCd);
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);
        // QC#3825
        if (!ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)) {
            ssmParam.put("childrenMaxLevel", 1);
        } else {
            ssmParam.put("childrenMaxLevel", CHILDREN_MAX_LEVEL);
        }

        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End
        return getSsmEZDClient().queryObjectList("getTreeListParent", ssmParam);

    }
    /**
     * getTreeListLeaseReln
     * @param cMsg NMAL6710CMsg
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param targetDsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTreeListLeaseReln(NMAL6710CMsg cMsg, String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("parentDsAcctNum", parentDsAcctNum);
        ssmParam.put("targetDsAcctNum", targetDsAcctNum);
        ssmParam.put("rowNum", cMsg.A.length());

        String rgtnStsCd = "";
        String dsAcctActvCustFlg = "";
        if (STATUS_CD_ACTIVE.equals(cMsg.xxAcctSrchStsCd_D2.getValue())) {
            rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;

        }
        ssmParam.put("rgtnStsCd", rgtnStsCd);
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);

        // Lease  Accts Only, Lease  Bill To's Only
        if (DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            String[] dsAcctRelnTpList = new String[] {DS_ACCT_RELN_TP.LEASE_ACCOUNT};
            ssmParam.put("dsAcctRelnTpList", dsAcctRelnTpList);
        } else {
            // Mod Start 2017/11/13 QC#17322(Sol#174)
            //String[] dsAcctRelnTpList = new String[] {DS_ACCT_RELN_TP.RELATED_ACCOUNT, DS_ACCT_RELN_TP.LEASE_ACCOUNT};
            String[] dsAcctRelnTpList = new String[] {DS_ACCT_RELN_TP.RELATED_ACCOUNT, //
                    DS_ACCT_RELN_TP.LEASE_ACCOUNT, DS_ACCT_RELN_TP.MYCSA_ACCOUNT };
            // Mod End 2017/11/13 QC#17322(Sol#174)
            ssmParam.put("dsAcctRelnTpList", dsAcctRelnTpList);
        }

        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End

        return getSsmEZDClient().queryObjectList("getTreeListLeaseReln", ssmParam);

    }
    /**
     * getSsmParamTreeList
     * @param cMsg NMAL6710CMsg
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param targetDsAcctNum String
     * @return Map<String, Object>
     */
    public S21SsmEZDResult getTreeListLocation(NMAL6710CMsg cMsg, String glblCmpyCd, String dsAcctNum, String tagetDsAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("rowNum", cMsg.A.length());

        String rgtnStsCd = "";
        String dsAcctActvCustFlg = "";
        if (STATUS_CD_ACTIVE.equals(cMsg.xxAcctSrchStsCd_D2.getValue())) {
            rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;

        }
        ssmParam.put("rgtnStsCd", rgtnStsCd);
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);

        // Display Account or Location
        // 2016/12/05 CSA-QC#14340 Mod Start
        ssmParam.put("dispLocFlg", ZYPConstant.FLG_ON_Y);
        // if (DISP_RELN_ACCT_CD_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                // QC#13440
                // || DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                // QC#5890
                // || !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_01.getValue())
               // ) {
           // ssmParam.put("dispAcctFlg", ZYPConstant.FLG_ON_Y);
                    //} else {
            // ssmParam.put("dispLocFlg", ZYPConstant.FLG_ON_Y);
        //}
        // 2016/12/05 CSA-QC#14340 Mod End
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
            // QC#5890
            String xxAllLineAddr_RT= appendPercentChar(cMsg.xxAllLineAddr_RT.getValue());
            ssmParam.put("xxAllLineAddr_RT", xxAllLineAddr_RT);
        }

        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End
        
        // QC#3395
        String ctacPsnFlg = "";
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnFirstNm_01) || ZYPCommonFunc.hasValue(cMsg.ctacPsnLastNm_01) || ZYPCommonFunc.hasValue(cMsg.ctacPsnTelNum_01)) {
            ctacPsnFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("ctacPsnFlg", ctacPsnFlg);
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnFirstNm_01)) {
            ssmParam.put("ctacPsnFirstNm", cMsg.ctacPsnFirstNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnLastNm_01)) {
            ssmParam.put("ctacPsnLastNm", cMsg.ctacPsnLastNm_01.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnTelNum_01)) {
            ssmParam.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.PHONE_WORK);
            ssmParam.put("ctacPsnTelNum", cMsg.ctacPsnTelNum_01.getValue());
        }
        
        // QC#3290
        if(ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_02.getValue())){
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_OFF_N);
        }
        if(ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_03.getValue())){
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("rgtnSts_Actv", RGTN_STS.READY_FOR_ORDER_TAKING);
        
        // QC#3395
        if(tagetDsAcctNum.equals(dsAcctNum)){
            ssmParam.put("tagetDsAcctNumFlg", ZYPConstant.FLG_ON_Y);            
        }
        
        // QC#4526
        // As same as contact condition, below location search conditions only apply to main account, do not apply to related account's locations.
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
            String xxAllLineAddr_01 = appendPercentChar(cMsg.xxAllLineAddr_01.getValue());
            ssmParam.put("shipFromFullCmpyAddr", xxAllLineAddr_01);
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

        return getSsmEZDClient().queryObjectList("getTreeListLocation", ssmParam);

    }

    private String appendPercentChar(String value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return value;
        }
        if (value.endsWith(NMAL6710Constant.CHAR_PERCENT)) {
            return value;
        }
        return value + NMAL6710Constant.CHAR_PERCENT;
    }

    
    /**
     * In case of Quick Lookup
     * 
     * @param bizMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     * @param globalCompanyCode String
     * @return S21SsmEZDResult
     * @throws SQLException 
     */
    public PreparedStatement getDsAccountListForDownload(NMAL6710CMsg bizMsg, NMAL6710SMsg sMsg, String globalCompanyCode, ResultSet rs, PreparedStatement ps) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", globalCompanyCode);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", MAX_DL_SIZE + 1);
        
        String searchMode = "";
        ssmParam.put("searchMode", searchMode);

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
            // QC#5890
            String shipFromFullCmpyAddrString= appendPercentChar(bizMsg.xxAllLineAddr_01.getValue());
            ssmParam.put("shipFromFullCmpyAddr", shipFromFullCmpyAddrString);
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

        //Additional Search Filters
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
        if (ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_01) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnTelNum_01)) {
            ctacPsnFlg = ZYPConstant.FLG_ON_Y;
        }
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
        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(bizMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCdCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End

        // QC#3290
        if(ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_02.getValue())){
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacLocFlg", ZYPConstant.FLG_OFF_N);
        }
        if(ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_03.getValue())){
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("inacBillToShipToFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("rgtnSts_Actv", RGTN_STS.READY_FOR_ORDER_TAKING);        

        if(ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)){
            ssmParam.put("dispRelnFlg", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("dsAcctRelnTp_PrntAcct", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("rgtnSts_Ready", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnSts_Pend", RGTN_STS.PENDING_COMPLETION);

        if(!ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4) && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_RT) && 
                !ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_RT) && !ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_RT)){
            ssmParam.put("isDisplayRelated", ZYPConstant.FLG_OFF_N);
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_DL_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6710Query.getInstance().getClass());

        return ssmLLClient.createPreparedStatement("getDsAccountList", ssmParam, execParam);
    }
    /**
     * getRelatedAccountList
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     * @param orgAccountNum string
     * @return S21SsmEZDResult
     * @throws SQLException 
     */
    public PreparedStatement getRelatedAccountListForDownload(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg, String orgAccountNum, ResultSet rs, PreparedStatement ps) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNum", MAX_DL_SIZE + 1);
        ssmParam.put("dsAcctNum", orgAccountNum);
        ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

        String rgtnStsCd = "";
        String dsAcctActvCustFlg = "";
        if (STATUS_CD_ACTIVE.equals(cMsg.xxAcctSrchStsCd_D2.getValue())) {
            rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("rgtnStsCd", rgtnStsCd);
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);

        // Bill To's Only, Lease Bill To's Only
        if (DISP_RELN_ACCT_CD_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue()) || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnBillToFlg", ZYPConstant.FLG_ON_Y);
        }
        // Ship To's Only
        if (DISP_RELN_ACCT_CD_SHIP.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnShipToFlg", ZYPConstant.FLG_ON_Y);
        }
        // Lease Accts Only, Lease Bill To's Only
        if (DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue()) || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            ssmParam.put("dsAcctRelnTpCdLease", DS_ACCT_RELN_TP.LEASE_ACCOUNT);
        }
        // Display Account or Location
        if (DISP_RELN_ACCT_CD_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
        // QC#13440
        // ||
        // DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
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
            // QC#5890
            String xxAllLineAddr_RT = appendPercentChar(cMsg.xxAllLineAddr_RT.getValue());
            ssmParam.put("xxAllLineAddr_RT", xxAllLineAddr_RT);
        }

        // 02/17/2016 CSA #2943 Add Start
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_DP.getValue())) {
            ssmParam.put("dsAcctTpCust", ZYPConstant.FLG_OFF_N);
        }
        // 02/17/2016 CSA #2943 Add End

        // QC#3290
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

        // QC#5890
        // Origin account has its own filter conditions. Account level
        // filter has been applied in before this search, only apply
        // location level filter only.
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


        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_DL_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6710Query.getInstance().getClass());

        return ssmLLClient.createPreparedStatement("getRelatedAccountList", ssmParam, execParam);
    }
}
