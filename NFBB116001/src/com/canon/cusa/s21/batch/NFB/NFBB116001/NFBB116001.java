/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB116001;


import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CM_ACRL_WRITE_OFFTMsg;
import business.parts.DFBEZDItemValueSetter;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 * Check Payment Invoice
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   CSAI            Y.Miyauchi      Create          New
 * 09/28/2016   Hitachi         K.Kasai         Update          QC#14130
 * 10/04/2016   Hitachi         K.Kasai         Update          QC#14130
 * 01/12/2017   Fujitsu         T.Murai         Update          QC#16928
 * 2018/03/29   CITS            T.Kikuhara      Update          QC#20316
 * 2018/11/13   Hitachi         J.Kim           Update          QC#23037
 * 2022/04/06   Hitachi         A.Kohinata      Update          QC#57935
 * </pre>
 */
public class NFBB116001 extends S21BatchMain implements NFBB116001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** CM_ACRL_WRITE_OFF for Insert */
    private CM_ACRL_WRITE_OFFTMsg insertTMsg;
    /** CM_ACRL_WRITE_OFF for Insert (*Array) */
    private CM_ACRL_WRITE_OFFTMsg[] insertTMsgArray;
    /** Commit Count */
    private int commitCnt = 0;
    /** Total Commit Count */
    private int totCommitCnt = 0;
    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		S21InfoLogOutput.println("Main Method Start");

        /** Initialize S21BatchMain */
        new NFBB116001().executeBatch(NFBB116001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
	}

    @Override
    protected void initRoutine() {
        S21InfoLogOutput.println("Ini  Method Start");

        commitCnt = 0;
        totCommitCnt = 0;

        S21InfoLogOutput.println("Ini  Method End");
    }

    @Override
    protected void mainRoutine() {
        S21InfoLogOutput.println("Main Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        

        try {

             /** Create Stock In Accrual information*/
             createStockInAccrual();
             
             /** Create Approved Accrual information */
             createApprovedAccrual();

        } catch (SQLException ex) {
        	//throw new S21AbendException();
        	rollback();
        }
        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("TermRoutine Method Start");
        /** Normal End this process */
        setTermState(termCd, totCommitCnt, 0, totCommitCnt);
        S21InfoLogOutput.println("TermRoutine Method End");
    }

    /**
     * <pre>
     *  Create Stock In Accrual Process
     *  @throws SQLException
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private void createStockInAccrual() throws SQLException {

         S21InfoLogOutput.println("createStockInAccrual Method Start");

         /** set queryMap */
         Map<String, String> queryMap = new HashMap<String, String>();
         queryMap.put(GLBL_CMPY_CD_ARG, glblCmpyCd);

         /** getEnteredWriteOff */
         List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getEnteredWriteOff", queryMap);
         
         if (ssmResult.size() <= 0) {
             return;
         } else {
             commitCnt = ssmResult.size();
             insertTMsgArray = new CM_ACRL_WRITE_OFFTMsg[commitCnt];
             for ( int iCnt=0; iCnt<ssmResult.size(); iCnt++ ){
                 insertTMsg = new CM_ACRL_WRITE_OFFTMsg();
                 NFBCommonBusiness.setZeroIntoCmAcrlWriteOff(insertTMsg);

                 /** Get Stock In */
                 DFBEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                 DFBEZDItemValueSetter.setValue(insertTMsg.cmAcrlWriteOffPk, ZYPOracleSeqAccessor.getNumberBigDecimal(CM_ACRL_WRITE_OFF_SQ));
                 DFBEZDItemValueSetter.setValue(insertTMsg.drCrTpCd, DR_CR_TP_CD_DEBIT);
                 DFBEZDItemValueSetter.setValue(insertTMsg.acInvTaxAmt, BigDecimal.ZERO);
                 DFBEZDItemValueSetter.setValue(insertTMsg.acInvJrnlCostAmt, BigDecimal.ZERO);
                 
                 DFBEZDItemValueSetter.setValue(insertTMsg.apVndCd, (String) ssmResult.get(iCnt).get(VND_CD));
                 if (ssmResult.get(iCnt).get(PO_NUM) != null && ssmResult.get(iCnt).get(PO_NUM).toString() != ""){
                     DFBEZDItemValueSetter.setValue(insertTMsg.poNum, (String) ssmResult.get(iCnt).get(PO_NUM));
                 } else {
                     DFBEZDItemValueSetter.setValue(insertTMsg.poNum, (String) ssmResult.get(iCnt).get(VND_CD));
                 }
                 DFBEZDItemValueSetter.setValue(insertTMsg.mdseCd, (String) ssmResult.get(iCnt).get(MDSE_CD));
                 DFBEZDItemValueSetter.setValue(insertTMsg.delyOrdNum, (String)ssmResult.get(iCnt).get(DELY_ORD_NUM));
                 DFBEZDItemValueSetter.setValue(insertTMsg.poQty, (BigDecimal)ssmResult.get(iCnt).get(PO_QTY));
                 DFBEZDItemValueSetter.setValue(insertTMsg.invRcvQty, (BigDecimal)ssmResult.get(iCnt).get(RCV_QTY));
                 DFBEZDItemValueSetter.setValue(insertTMsg.thisMthFobCostAmt, (BigDecimal)ssmResult.get(iCnt).get(THIS_MTH_FOB_COST_AMT));
                 DFBEZDItemValueSetter.setValue(insertTMsg.stkInDt, (String)ssmResult.get(iCnt).get(INV_RCV_DT));
                 DFBEZDItemValueSetter.setValue(insertTMsg.wrtOffFlg, FLG_KEY_NO);
                 // START 2016/09/28 [QC#14130, ADD]
                 DFBEZDItemValueSetter.setValue(insertTMsg.entDealNetUnitPrcAmt, (BigDecimal) ssmResult.get(iCnt).get(ENT_DEAL_NET_UNIT_PRC_AMT));
                 // END 2016/09/28 [QC#14130, ADD]

                 /** Get & Set this account code */
                 DFBEZDItemValueSetter.setValue(insertTMsg.coaAcctCd, getAcrlAccountCode(ssmResult.get(iCnt).get(MDSE_CD).toString()));
                 /** Get & Set this COA */
                 insertTMsg = getCoaSegmentCode(insertTMsg.mdseCd.getValue(), insertTMsg);
                 // START 2018/11/13 J.Kim [QC#23037, ADD]
                 DFBEZDItemValueSetter.setValue(insertTMsg.poOrdDtlLineNum, (String) ssmResult.get(iCnt).get(PO_ORD_DTL_LINE_NUM));
                 // END 2018/11/13 J.Kim [QC#23037, ADD]

                 insertTMsgArray[iCnt] = insertTMsg;

                 // Insert to CM_ACRL_WRITE_OFF
                 EZDTBLAccessor.insert(insertTMsg);

                 if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode()) ) {
                     rollback();
                     throw new S21AbendException(ZZBM0074E);
                 }
             }

             commit();
             totCommitCnt = totCommitCnt + commitCnt;
         }

         S21InfoLogOutput.println("createStockInAccrual Method End");
    }

    /**
     * <pre>
     *  Create Approved Accrual Process
     *  @return
     *  @throws SQLException
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private void createApprovedAccrual() throws SQLException {
        S21InfoLogOutput.println("createApprovedAccrual Method Start");

        /** set queryMap */
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(GLBL_CMPY_CD_ARG, glblCmpyCd);
        // mod start 2022/04/06 QC#57935
        //queryMap.put(ACCT_INV_STS_CD_ARG, ACCT_INV_STS_CD_AUTH);
        String[] acctInvStsCdList = new String[] {ACCT_INV_STS.AUTHORIZED, ACCT_INV_STS.LINKED_TO_COST_CALCULATION, ACCT_INV_STS.PAID, ACCT_INV_STS.VOIDED };
        queryMap.put("acctInvStsCdList", acctInvStsCdList);
        // mod end 2022/04/06 QC#57935
        String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(NFBB1160_AP_INV_CATG_CD, glblCmpyCd);
        List<String> listApInvCatgCd = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(apInvCatgCd)) {
            listApInvCatgCd =asList(apInvCatgCd.split(","));
        } else {
            throw new S21AbendException(NFBM0028E);
        }
        queryMap.put(LIST_AP_INV_CATG_CD_ARG, listApInvCatgCd);
        String excludeMdseCd = ZYPCodeDataUtil.getVarCharConstValue(NFBB1160_EXCLUDE_MDSE_CD, glblCmpyCd);
        List<String> listExcludeMdseCd = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(excludeMdseCd)) {
            listExcludeMdseCd =asList(excludeMdseCd.split(","));
        } else {
            throw new S21AbendException(NFBM0028E);
        }
        queryMap.put(LIST_EXCLUDE_MDSE_CD_ARG, listExcludeMdseCd);

        /** getEnteredWriteOff */
        List<Map> ssmResult = (List) ssmBatchClient.queryObjectList("getApprovedWriteOff", queryMap);

        if (ssmResult.size() <= 0) {
            return;
        } else {
            commitCnt = ssmResult.size();
            insertTMsgArray = new CM_ACRL_WRITE_OFFTMsg[commitCnt];
            for ( int iCnt=0; iCnt<ssmResult.size(); iCnt++ ){
                insertTMsg = new CM_ACRL_WRITE_OFFTMsg();
                NFBCommonBusiness.setZeroIntoCmAcrlWriteOff(insertTMsg);

                /** Get Stock In */
                DFBEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                DFBEZDItemValueSetter.setValue(insertTMsg.cmAcrlWriteOffPk, ZYPOracleSeqAccessor.getNumberBigDecimal(CM_ACRL_WRITE_OFF_SQ));
                DFBEZDItemValueSetter.setValue(insertTMsg.apVndCd, (String) ssmResult.get(iCnt).get(AP_VND_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.apVndInvNum, (String) ssmResult.get(iCnt).get(AP_VND_INV_NUM));
                // START 2018/03/29 [QC#20316, ADD]
                DFBEZDItemValueSetter.setValue(insertTMsg.apVndInvLineNum, (String) ssmResult.get(iCnt).get(AP_VND_INV_LINE_NUM));
                // END 2018/03/29 [QC#20316, ADD]
                DFBEZDItemValueSetter.setValue(insertTMsg.apVndInvSqNum, (String) ssmResult.get(iCnt).get(AP_VND_INV_SQ_NUM));
                DFBEZDItemValueSetter.setValue(insertTMsg.locNum, (String) ssmResult.get(iCnt).get(LOC_NUM));
                DFBEZDItemValueSetter.setValue(insertTMsg.invDt, (String)  ssmResult.get(iCnt).get(INV_DT));
                if (ssmResult.get(iCnt).get(PO_NUM) != null && ssmResult.get(iCnt).get(PO_NUM).toString() != ""){
                    DFBEZDItemValueSetter.setValue(insertTMsg.poNum, (String) ssmResult.get(iCnt).get(PO_NUM));
                } else {
                    DFBEZDItemValueSetter.setValue(insertTMsg.poNum, ssmResult.get(iCnt).get(AP_VND_CD).toString() + "-" + ssmResult.get(iCnt).get(AP_VND_INV_NUM).toString() + "-" + ssmResult.get(iCnt).get(AP_VND_INV_SQ_NUM));
                }
                DFBEZDItemValueSetter.setValue(insertTMsg.mdseCd, (String) ssmResult.get(iCnt).get(MDSE_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.drCrTpCd, DR_CR_TP_CD_CREDIT);
                DFBEZDItemValueSetter.setValue(insertTMsg.delyOrdNum, (String) ssmResult.get(iCnt).get(DELY_ORD_NUM));
                DFBEZDItemValueSetter.setValue(insertTMsg.invQty, (BigDecimal) ssmResult.get(iCnt).get(INV_QTY));
                DFBEZDItemValueSetter.setValue(insertTMsg.poQty, (BigDecimal) ssmResult.get(iCnt).get(PO_QTY));
                DFBEZDItemValueSetter.setValue(insertTMsg.invRcvQty, (BigDecimal) ssmResult.get(iCnt).get(INV_RCV_QTY));
                DFBEZDItemValueSetter.setValue(insertTMsg.thisMthFobCostAmt, (BigDecimal) ssmResult.get(iCnt).get(THIS_MTH_FOB_COST_AMT));
                // START 2016/10/04 [QC#14130, ADD]
                DFBEZDItemValueSetter.setValue(insertTMsg.acInvJrnlCostAmt, (BigDecimal) ssmResult.get(iCnt).get(AC_INV_JRNL_COST_AMT));
                // END 2016/10/04 [QC#14130, ADD]
                DFBEZDItemValueSetter.setValue(insertTMsg.stkInDt, (String) ssmResult.get(iCnt).get(STK_IN_DT));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaCmpyCd, (String) ssmResult.get(iCnt).get(COA_CMPY_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaBrCd, (String) ssmResult.get(iCnt).get(COA_BR_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaCcCd, (String) ssmResult.get(iCnt).get(COA_CC_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaAcctCd, (String) ssmResult.get(iCnt).get(COA_ACCT_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaProdCd, (String) ssmResult.get(iCnt).get(COA_PROD_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaChCd, (String) ssmResult.get(iCnt).get(COA_CH_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaAfflCd, (String) ssmResult.get(iCnt).get(COA_AFFL_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaProjCd, (String) ssmResult.get(iCnt).get(COA_PROJ_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.coaExtnCd, (String) ssmResult.get(iCnt).get(COA_EXTN_CD));
                DFBEZDItemValueSetter.setValue(insertTMsg.wrtOffFlg, FLG_KEY_NO);
                // START 2016/09/28 [QC#14130, ADD]
                DFBEZDItemValueSetter.setValue(insertTMsg.entDealNetUnitPrcAmt, (BigDecimal) ssmResult.get(iCnt).get(ENT_DEAL_NET_UNIT_PRC_AMT));
                // END 2016/09/28 [QC#14130, ADD]
                // START 2018/11/13 J.Kim [QC#23037, ADD]
                DFBEZDItemValueSetter.setValue(insertTMsg.poOrdDtlLineNum, (String) ssmResult.get(iCnt).get(PO_ORD_DTL_LINE_NUM));
                // END 2018/11/13 J.Kim [QC#23037, ADD]

                insertTMsgArray[iCnt] = insertTMsg;

                // Insert to CM_ACRL_WRITE_OFF
                EZDTBLAccessor.insert(insertTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode()) ) {
                    rollback();
                    throw new S21AbendException(ZZBM0074E);
                }
            }

            commit();
            totCommitCnt = totCommitCnt + commitCnt;
        }

        S21InfoLogOutput.println("createApprovedAccrual Method End");
    }

    /**
     * <pre>
     *  Get Accrual COA Account Code
     *  @param mdse: String
     *  @return String
     *  @throws SQLException
     * </pre>
     */
    private String getAcrlAccountCode(String mdse) throws SQLException {
        S21InfoLogOutput.println("getAcrlAccountCode Method Start");

        /** Set queryMap */
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(GLBL_CMPY_CD_ARG, glblCmpyCd);
        queryMap.put(MDSE_CD_ARG, mdse);
        queryMap.put("acctTpCdAccrual", COA_PROJ_ACCT_TP.ACCRUAL); // ADD 2017/01/12 [QC#16928]

        S21InfoLogOutput.println("getAcrlAccountCode Method End");

        /** Get Accrual Coa Account Code */
        String strReturn = (String) ssmBatchClient.queryObject("getAcrlCoaAccountCode", queryMap);

        //Check Account Code by Merchandise
        if (strReturn == null || strReturn.isEmpty()) {
            return "";
        }
        
        return strReturn;
    }

    /**
     * Get COA Segment Code data
     * 
     * @param mdse: String
     * @param updateCoaCodeTMsg: CM_ACRL_WRITE_OFF
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    private CM_ACRL_WRITE_OFFTMsg getCoaSegmentCode(String mdse, CM_ACRL_WRITE_OFFTMsg updateCoaCodeTMsg) throws SQLException {
        S21InfoLogOutput.println("getCoaSegmentCode Method Start");

        /** Set queryMap */
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(GLBL_CMPY_CD_ARG, glblCmpyCd);
        queryMap.put(MDSE_CD_ARG, ARG_MDSE);
        
        /** Get Coa Segment Code */
        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getCoaSegmentCode", queryMap);
        
        if ( ssmResult == null || ssmResult.size() == 0) {
            return updateCoaCodeTMsg;
        }

        //Update insertTMsg with COA
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaCmpyCd, (String) ssmResult.get(0).get(CM_COA_CMPY_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaBrCd, (String) ssmResult.get(0).get(CM_COA_BR_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaCcCd, (String) ssmResult.get(0).get(CM_COA_CC_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaProdCd, (String) ssmResult.get(0).get(CM_COA_PROD_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaChCd, (String) ssmResult.get(0).get(CM_COA_CH_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaAfflCd, (String) ssmResult.get(0).get(CM_COA_AFFL_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaProjCd, (String) ssmResult.get(0).get(CM_COA_PROJ_CD));
        DFBEZDItemValueSetter.setValue(updateCoaCodeTMsg.coaExtnCd, (String) ssmResult.get(0).get(CM_COA_EXTN_CD));

        S21InfoLogOutput.println("getCoaSegmentCode Method End");

        return updateCoaCodeTMsg;
    }
}
