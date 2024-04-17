/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB234001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NLZC309001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.batch.NWA.NWAB234001.constant.NWAB234001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONV_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * NWAB234001:Conversion Order Completion Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2013/08/08   Fujitsu         A.Shinohara     Create          R-OM050
 * 2015/11/30   Fujitsu         H.Nagashima     Update          CSA
 * 2016/04/25   Fujitsu         H.Nagashima     Update          QC#7480
 * 2016/04/27   Fujitsu         H.Nagashima     Update          QC#7606
 * 2016/12/14   Fujitsu         H.Nagashima     Update          QC#16425
 * 2017/10/24   Fujitsu         H.Ikeda         Update          QC#21550
 * 2018/03/06   Fujitsu         A.Kosai         Update          QC#20153
 * 2019/05/09   Fujitsu         Hd.Sugawara     Update          QC#50075
 * 2019/07/01   Fujitsu         Hd.Sugawara     Update          QC#50984
 * 2019/12/26   Fujitsu         K.Kato          Update          QC#55207
 * 2020/01/21   Fujitsu         K.Kato          Update          QC#55207-1
 *</pre>
 */
public class NWAB234001 extends S21BatchMain {
    /* Input Parameters */
    /** Global Company Code */
    private String glblCmpyCd;
    /* Internal Variables */
    /** Sales Date */
    private String salesDate;
    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;
    /** total correct count */
    private int totalCorrectCount = 0;
    /** total error count */
    private int totalErrorCount = 0;
    /** total Execute count */
    private int totalExecuteCount = 0;

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    // Add Start 2019/05/09 QC#50075
    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // Add End 2019/05/09 QC#50075

    /** Message Map for Mail */
    private Map<String, List<String>> msgMap = new LinkedHashMap<String, List<String>>(NWAB234001Constant.SIZE_100);
    /** Mail Message Information */
    private StringBuilder mailMsgInfo = new StringBuilder();

    /** ssm cache */
    private S21LRUMap<String, Object> cacheMap = new S21LRUMap<String, Object>();

    @Override
    protected void initRoutine() {
        // Check GlobalCompanyCode
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NWAB234001Constant.NWZM0473E);
        }

        // Check Sales Date
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NWAB234001Constant.NWAM0446E);
        }

        // Initialization of SSM
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // Add Start 2019/05/09 QC#50075
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Add End 2019/05/09 QC#50075
    }

    @Override
    protected void mainRoutine() {

        execUpdateConvOrdInfo();
        if (this.totalErrorCount > 0) {
            setTermCd(TERM_CD.WARNING_END);
        }

        // Send Mail
        if (!msgMap.isEmpty()) {
            postMail();
        }

        commit();
    }

    @Override
    protected void termRoutine() {
        totalExecuteCount = totalCorrectCount + totalErrorCount;
        setTermState(termCd, totalCorrectCount, totalErrorCount, totalExecuteCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB234001().executeBatch(NWAB234001.class.getSimpleName());
    }

    /**
     * Set TERM_CD.
     * @param ptermCd TERM_CD
     */
    private void setTermCd(TERM_CD ptermCd) {
        if (TERM_CD.NORMAL_END.equals(termCd)
                || (TERM_CD.WARNING_END.equals(termCd)
                && TERM_CD.ABNORMAL_END.equals(ptermCd))) {
            termCd = ptermCd;
        }
    }

    /**
     * Execute Update Conversion Order.
     * @return Boolean
     */
    private Boolean execUpdateConvOrdInfo() {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // Set SQL Parameters
        paramMap.put(NWAB234001Constant.PARAM_GLBL_CMPY_CD,             glblCmpyCd);
        paramMap.put(NWAB234001Constant.PARAM_CONV_COMP_FLG,            ZYPConstant.FLG_OFF_N);
        paramMap.put(NWAB234001Constant.PARAM_ORD_LINE_STS_CD,          ORD_HDR_STS.CLOSED);

        List<String> lineCatgCdListSls = new ArrayList<String>();
        lineCatgCdListSls.addAll(getConvLineCatgList(CONV_PROC_TP.LOAN_TO_SALES));
        lineCatgCdListSls.addAll(getConvLineCatgList(CONV_PROC_TP.RENTAL_TO_SALES));
        paramMap.put(NWAB234001Constant.PARAM_ORD_LINE_CATG_CD_SLS,     lineCatgCdListSls);

        List<String> lineCatgCdListRtrn = new ArrayList<String>();
        lineCatgCdListRtrn.addAll(getConvLineCatgList(CONV_PROC_TP.LOAN_RETURN));
        paramMap.put(NWAB234001Constant.PARAM_ORD_LINE_CATG_CD_RTRN,    lineCatgCdListRtrn);
        paramMap.put(NWAB234001Constant.PARAM_SVC_MACH_MAINT_AVAL_FLG,  ZYPConstant.FLG_OFF_N);

        Boolean ssmRes = (Boolean) ssmBatchClient.queryObject(NWAB234001Constant.SQL_GET_SLS_AND_RTRN_CONV_ORD_INFO, paramMap, new UpdSlsAndRtrnConvOrdInfo());

        return ssmRes;
    }

        /**
         * UpdSlsAndRtrnConvOrdInfo
         */
        protected class UpdSlsAndRtrnConvOrdInfo extends S21SsmBooleanResultSetHandlerSupport {

            /** Shipping Number List for NWZC188001PMsg */
            private Map<String, ArrayList<String>> shpgNumListMap = new HashMap<String, ArrayList<String>>();

            @Override
            /**
             * doProcessQueryResult
             */
            protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

                Set<String> errCpoOrdNumSet = new HashSet<String>(NWAB234001Constant.SIZE_50);
                // Add Start 2019/05/09 QC#50075
                Set<BigDecimal> svcConfigMstrPkSet = new HashSet<BigDecimal>(NWAB234001Constant.SIZE_50);
                // Add End 2019/05/09 QC#50075

                // Has no result data
                if (!rs.next()) {
                    return true;
                }

                List<String> rentalToSalesLineCatgCdList = getConvLineCatgList(CONV_PROC_TP.RENTAL_TO_SALES);
                List<String> loanToSalesLineCatgCdList   = getConvLineCatgList(CONV_PROC_TP.LOAN_TO_SALES);
                List<String> loanReturnLineCatgCdList    = getConvLineCatgList(CONV_PROC_TP.LOAN_RETURN);
                String prevCpoOrdNum = null;
                String cpoOrdNum     = null;
                do {
                    prevCpoOrdNum = cpoOrdNum;
                    cpoOrdNum     = rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM);

                    if (ZYPCommonFunc.hasValue(prevCpoOrdNum) && !prevCpoOrdNum.equals(cpoOrdNum)) {

                        if (!errCpoOrdNumSet.contains(prevCpoOrdNum)) {
                            // Call NWZC188001 
                            if (!callStsUpdApi()) {
                                errCpoOrdNumSet.add(prevCpoOrdNum);
                            }
                        }
                        shpgNumListMap.clear();

                        // commit by order
                        updDsCpoAndExecCommitRollback(errCpoOrdNumSet, prevCpoOrdNum);
                    }

                    String dsOrdTpCd   = rs.getString(NWAB234001Constant.DB_DS_ORD_TP_CD);
                    String dsOrdLineCatgCd   = dsOrdTpCd + rs.getString(NWAB234001Constant.DB_DS_ORD_LINE_CATG_CD);
                    boolean isRentalToSales = rentalToSalesLineCatgCdList.contains(dsOrdLineCatgCd);
                    boolean isLoanToSales   = loanToSalesLineCatgCdList.contains(dsOrdLineCatgCd);
                    boolean isLoanReturn    = loanReturnLineCatgCdList.contains(dsOrdLineCatgCd);
                    boolean isSetCompornent = ZYPCommonFunc.hasValue(rs.getString(NWAB234001Constant.DB_SET_MDSE_CD));
                    boolean existMachMstr   = ZYPCommonFunc.hasValue(rs.getBigDecimal(NWAB234001Constant.DB_SVC_MACH_MSTR_PK));
                    // Add Start 2019/05/09 QC#50075
                    BigDecimal svcConfigMstrPk = rs.getBigDecimal(NWAB234001Constant.DB_SVC_CONFIG_MSTR_PK);
                    // Add End 2019/05/09 QC#50075

                    // ##### Execute Machine Master Update API(loan to sales, rental to sales) #####
                    if (existMachMstr && (isLoanToSales || isRentalToSales)) {
                        if (!execMachMstrUpdApi(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }
                    }

                    // Add Start 2019/05/09 QC#50075
                    if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && !svcConfigMstrPkSet.contains(svcConfigMstrPk) && //
                            (isLoanToSales || isRentalToSales)) {
                        svcConfigMstrPkSet.add(svcConfigMstrPk);

                        if (!execMachMstrUpdApiForSameConfig(rs, errCpoOrdNumSet)) {
                            continue;
                        }
                    }
                    // Add End 2019/05/09 QC#50075

                    // ##### Execute Asset Staging Entry API(rental to sales) #####
                    if (isRentalToSales) {
                        if (!execAssetStagingEntryApi(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }
                    }

                    // ##### Loan Order Update(loan to sales, loan return) #####
                    if (isLoanToSales || isLoanReturn) {

                        // ### Update original loan order ###
                        if (!updateOriginalLoanOrder(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }
                    }

                    // ##### update conversion order #####
                    if (isLoanToSales || isRentalToSales) {
                        // ### update ds cpo detail for conversion order ###
                        if (!updateDsCpoDtlForConvOrder(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }
                        //update set parent
                        if (isSetCompornent && !updateSetParentSalesConversionOrder(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }

                    } else if (isLoanReturn) {
                        // ### update ds cpo return detail for conversion order ###
                        if (!updateDsCpoRtrnDtlForConvOrder(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }
                        //update set parent for return
                        if (isSetCompornent && !updateSetParentReturnConversionOrder(rs)) {
                            if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                                errCpoOrdNumSet.add(cpoOrdNum);
                            }
                            continue;
                        }
                    }

                } while (rs.next());

                // transaction commit or rollback
                if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                    if (!callStsUpdApi()) {
                        errCpoOrdNumSet.add(cpoOrdNum);
                    }
                    shpgNumListMap.clear();
                }
                updDsCpoAndExecCommitRollback(errCpoOrdNumSet, cpoOrdNum);

                return true;
            }

            /**
             * Execute Machine Master Update API
             * @param rs ResultSet
             * @return boolean
             * @throws SQLException
             */
            private boolean execMachMstrUpdApi(ResultSet rs) throws SQLException {
                NSZC001001PMsg machMstrUpdApiPMsg = new NSZC001001PMsg();

                machMstrUpdApiPMsg.glblCmpyCd.setValue(glblCmpyCd);
                machMstrUpdApiPMsg.slsDt.setValue(salesDate);
                machMstrUpdApiPMsg.xxModeCd.setValue(ProcessMode.CONVERSION_ORDER.code);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcConfigMstrPk, rs.getBigDecimal(NWAB234001Constant.DB_SVC_CONFIG_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrPk, rs.getBigDecimal(NWAB234001Constant.DB_SVC_MACH_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.shipToCustCd, rs.getString(NWAB234001Constant.DB_SHIP_TO_CUST_LOC_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.locNm, rs.getString(NWAB234001Constant.DB_SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.addlLocNm, rs.getString(NWAB234001Constant.DB_SHIP_TO_ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.firstLineAddr, rs.getString(NWAB234001Constant.DB_SHIP_TO_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.scdLineAddr, rs.getString(NWAB234001Constant.DB_SHIP_TO_SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.thirdLineAddr, rs.getString(NWAB234001Constant.DB_SHIP_TO_THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.frthLineAddr, rs.getString(NWAB234001Constant.DB_SHIP_TO_FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ctyAddr, rs.getString(NWAB234001Constant.DB_SHIP_TO_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.stCd, rs.getString(NWAB234001Constant.DB_SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.provNm, rs.getString(NWAB234001Constant.DB_SHIP_TO_PROV_NM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.cntyNm, rs.getString(NWAB234001Constant.DB_SHIP_TO_CNTY_NM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.postCd, rs.getString(NWAB234001Constant.DB_SHIP_TO_POST_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ctryCd, rs.getString(NWAB234001Constant.DB_SHIP_TO_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.cpoOrdNum, rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.cpoDtlLineNum, rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.cpoDtlLineSubNum, rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.custIssPoNum, rs.getString(NWAB234001Constant.DB_CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.dsOrdTpCd, rs.getString(NWAB234001Constant.DB_DS_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.dsOrdRsnCd, rs.getString(NWAB234001Constant.DB_DS_ORD_RSN_CD));
                // 2018/03/06 S21_NA#20153 Del Start
//                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ownrAcctNum, rs.getString(NWAB234001Constant.DB_BILL_TO_CUST_ACCT_CD));
//                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ownrLocNum, rs.getString(NWAB234001Constant.DB_BILL_TO_CUST_LOC_CD));
                // 2018/03/06 S21_NA#20153 Del End
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.billToAcctNum, rs.getString(NWAB234001Constant.DB_BILL_TO_CUST_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.billToLocNum, rs.getString(NWAB234001Constant.DB_BILL_TO_CUST_LOC_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.curLocAcctNum, rs.getString(NWAB234001Constant.DB_SHIP_TO_CUST_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.curLocNum, rs.getString(NWAB234001Constant.DB_SHIP_TO_CUST_LOC_CD));
                // QC#21550 add Start
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachTrxTpCd, rs.getString(NWAB234001Constant.DB_DS_ORD_CATG_CD));
                // QC#21550 add End

                // 2018/03/06 S21_NA#20153 Add Start
                Map<String, Object> ibOwnerOverrideInfo = getIbOwnerOverrideInfo(
                        rs.getString(NWAB234001Constant.DB_DS_ORD_CATG_CD),
                        rs.getString(NWAB234001Constant.DB_DS_ORD_TP_CD),
                        rs.getString(NWAB234001Constant.DB_DS_ORD_RSN_CD));

                if (ibOwnerOverrideInfo.isEmpty()) {
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ownrAcctNum, rs.getString(NWAB234001Constant.DB_BILL_TO_CUST_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ownrLocNum, rs.getString(NWAB234001Constant.DB_BILL_TO_CUST_LOC_CD));
                } else {
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ownrAcctNum, (String) ibOwnerOverrideInfo.get("OWNR_ACCT_NUM"));
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.ownrLocNum, (String) ibOwnerOverrideInfo.get("OWNR_LOC_NUM"));
                }
                // 2018/03/06 S21_NA#20153 Add End

            new NSZC001001().execute(machMstrUpdApiPMsg, ONBATCH_TYPE.BATCH);
            return !isErrCallApi(NWAB234001Constant.API_MACH_MSTR_UPD, machMstrUpdApiPMsg, rs);
        }

        // Add Start 2019/05/09 QC#50075
        /**
         * Execute Machine Master Update API for same config
         * @param rs ResultSet
         * @return boolean
         * @throws SQLException
         */
        private boolean execMachMstrUpdApiForSameConfig(ResultSet rs, Set<String> errCpoOrdNumSet) throws SQLException {
            boolean result = true;

            String cpoOrdNum = (String) rs.getString("CPO_ORD_NUM");
            BigDecimal svcConfigMstrPk = (BigDecimal) rs.getBigDecimal("SVC_CONFIG_MSTR_PK");

            Map<String, Object> paramMap = new HashMap<String, Object>();

            // Set SQL Parameters
            paramMap.put(NWAB234001Constant.PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NWAB234001Constant.PARAM_CONV_COMP_FLG, ZYPConstant.FLG_OFF_N);
            paramMap.put(NWAB234001Constant.PARAM_ORD_LINE_STS_CD, ORD_HDR_STS.CLOSED);

            List<String> lineCatgCdListSls = new ArrayList<String>();
            lineCatgCdListSls.addAll(getConvLineCatgList(CONV_PROC_TP.LOAN_TO_SALES));
            lineCatgCdListSls.addAll(getConvLineCatgList(CONV_PROC_TP.RENTAL_TO_SALES));
            paramMap.put(NWAB234001Constant.PARAM_ORD_LINE_CATG_CD_SLS, lineCatgCdListSls);

            List<String> lineCatgCdListRtrn = new ArrayList<String>();
            lineCatgCdListRtrn.addAll(getConvLineCatgList(CONV_PROC_TP.LOAN_RETURN));
            paramMap.put(NWAB234001Constant.PARAM_ORD_LINE_CATG_CD_RTRN, lineCatgCdListRtrn);
            paramMap.put(NWAB234001Constant.PARAM_SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_OFF_N);

            paramMap.put("cpoOrdNum", cpoOrdNum);
            paramMap.put("svcConfigMstrPk", svcConfigMstrPk);
            paramMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

            S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
            ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            PreparedStatement stmt = null;
            ResultSet sameConfigRs = null;

            try {
                stmt = ssmLLClient.createPreparedStatement("getSalesAndReturnConvOrdInfoForSameConfig", paramMap, ssmParam);
                sameConfigRs = stmt.executeQuery();

                while (sameConfigRs.next()) {
                    if (!execMachMstrUpdApi(sameConfigRs)) {
                        result = false;

                        if (!errCpoOrdNumSet.contains(cpoOrdNum)) {
                            errCpoOrdNumSet.add(cpoOrdNum);
                        }
                    }
                }

                return result;
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, sameConfigRs);
            }
        }
        // Add End 2019/05/09 QC#50075

        /**
         * Execute Asset Staging Entry API
         * @param rs ResultSet
         * @return boolean
         * @throws SQLException
         */
        private boolean execAssetStagingEntryApi(ResultSet rs) throws SQLException {

            NLZC309001PMsg assetStagingApiPMsg = new NLZC309001PMsg();

            assetStagingApiPMsg.glblCmpyCd.setValue(glblCmpyCd);
            assetStagingApiPMsg.slsDt.setValue(salesDate);
            assetStagingApiPMsg.procModeCd.setValue(NLZC309001Constant.PROC_MODE_RENTAL_CONVERSION_EARLY_BUYOUT);
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.mdseCd, rs.getString(NWAB234001Constant.DB_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.toSvcConfigMstrPk, rs.getBigDecimal(NWAB234001Constant.DB_SVC_CONFIG_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.svcMachMstrPk, rs.getBigDecimal(NWAB234001Constant.DB_SVC_MACH_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.baseCmptFlg, rs.getString(NWAB234001Constant.DB_BASE_CMPT_FLG));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.cpoOrdNum, rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.cpoDtlLineNum, rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.cpoDtlLineSubNum, rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.dsOrdPosnNum, rs.getString(NWAB234001Constant.DB_DS_ORD_POSN_NUM));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.shipToCustAcctCd, rs.getString(NWAB234001Constant.DB_SHIP_TO_CUST_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.shipToCustCd, rs.getString(NWAB234001Constant.DB_SHIP_TO_CUST_LOC_CD));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.sellToCustCd, rs.getString(NWAB234001Constant.DB_SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.soldToCustLocCd, rs.getString(NWAB234001Constant.DB_SOLD_TO_CUST_LOC_CD));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.slsRepTocCd, rs.getString(NWAB234001Constant.DB_SLS_REP_OR_SLS_TEAM_TOC_CD));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.invNum, rs.getString(NWAB234001Constant.DB_INV_NUM));
            ZYPEZDItemValueSetter.setValue(assetStagingApiPMsg.invDt, rs.getString(NWAB234001Constant.DB_INV_DT));

            new NLZC309001().execute(assetStagingApiPMsg, ONBATCH_TYPE.BATCH);
            return !isErrCallApi(NWAB234001Constant.API_ASSET_STG, assetStagingApiPMsg, rs);
        }

        /**
         * Get Result of Execute API
         * @param apiNm String
         * @param ezdPMsg EZDPMsg
         * @param rs ResultSet
         * @return boolean
         * @throws SQLException
         */
        private boolean isErrCallApi(String apiNm, EZDPMsg ezdPMsg, ResultSet rs) throws SQLException {
            boolean errFlg = false;

            List<String> msgList = S21ApiUtil.getXxMsgIdList(ezdPMsg);
            if (!msgList.isEmpty()) {
                for (String xxMsgId : msgList) {
                    if (xxMsgId.endsWith(NWAB234001Constant.MESSAGE_KIND_ERROR)) {
                        if (!errFlg) {
                            errFlg = true;
                            String[] msgConvStr = new String[] {apiNm};
                            logOutputAndStkMsg(getKeyStr(rs), NWAB234001Constant.NWAM0189E, msgConvStr);
                        }
                        logOutputAndStkMsg(getKeyStr(rs), xxMsgId, null);
                    }
                }
            }

            return errFlg;
        }

        /**
         * Update Conversion Order and Transaction
         * @param errCpoOrdNumSet Set&lt;String&gt;
         * @param procCpoOrdNum String
         */
        private void updDsCpoAndExecCommitRollback(Set<String> errCpoOrdNumSet, String procCpoOrdNum) {

            if (!errCpoOrdNumSet.contains(procCpoOrdNum)) {

                commit();
                totalCorrectCount++;
            } else {
                String[] keyStr = new String[] {procCpoOrdNum};
                String[] msgConvStr = new String[] {procCpoOrdNum};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWAM0635E, msgConvStr);
                rollback();
                totalErrorCount++;
            }
        }

        /**
         * Get Key String for Message Map
         * @param rs ResultSet
         * @return String[]
         * @throws SQLException
         */
        private String[] getKeyStr(ResultSet rs) throws SQLException {
            String cpoOrdNum = rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM);
            String cpoDtlLineNum = rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_NUM);
            String cpoDtlLineSubNum = rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_SUB_NUM);

            if (!ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
                cpoDtlLineNum = rs.getString(NWAB234001Constant.DB_DS_CPO_RTRN_LINE_NUM);
            }
            if (!ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
                cpoDtlLineNum = rs.getString(NWAB234001Constant.DB_DS_CPO_RTRN_LINE_SUB_NUM);
            }

            return new String[] {cpoOrdNum, NWAB234001Constant.COMMA, cpoDtlLineNum, NWAB234001Constant.COMMA, cpoDtlLineSubNum};
        }

        /**
         * Output Log and Stock Error Message ID for Mail
         * @param keyStr String[]
         * @param xxMsgId String
         * @param msgConvStr String[]
         */
        private void logOutputAndStkMsg(String[] keyStr, String xxMsgId, String[] msgConvStr) {
            String key = S21StringUtil.concatStrings((Object[]) keyStr);

            List<String> msgList;
            if (msgMap.containsKey(key)) {
                msgList = msgMap.get(key);
            } else {
                msgList = new ArrayList<String>(NWAB234001Constant.SIZE_10);
            }

            String msg;
            if (msgConvStr == null) {
                msg = S21MessageFunc.clspGetMessage(xxMsgId);
            } else {
                msg = S21MessageFunc.clspGetMessage(xxMsgId, msgConvStr);
            }
            msgList.add(msg);

            msgMap.put(key, msgList);
            S21InfoLogOutput.println(xxMsgId, msgConvStr);
        }

        /**
         * update original Loan Order
         * @param rs ResultSet
         */
        private boolean updateOriginalLoanOrder(ResultSet rs) throws SQLException {

            String origCpoOrdNum        = rs.getString(NWAB234001Constant.DB_ORIG_CPO_ORD_NUM);
            String origCpoDtlLineNum    = rs.getString(NWAB234001Constant.DB_ORIG_CPO_DTL_LINE_NUM);
            String origCpoDtlLineSubNum = rs.getString(NWAB234001Constant.DB_ORIG_CPO_DTL_LINE_SUB_NUM);

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",           glblCmpyCd);
            ssmParam.put("origCpoOrdNum",        origCpoOrdNum);
            ssmParam.put("origCpoDtlLineNum",    origCpoDtlLineNum);
            ssmParam.put("origCpoDtlLineSubNum", origCpoDtlLineSubNum);

            BigDecimal loanBalQty = (BigDecimal) ssmBatchClient.queryObject("getLoanBalQty", ssmParam);

            if (!ZYPCommonFunc.hasValue(loanBalQty)) {
                String[] keyStr = new String[] {origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWAM0189E, null);
                return false;
            }

            //update Loan Ballance quantity
            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd,       glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum,        origCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum,    origCpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, origCpoDtlLineSubNum);
            loanBalQty = loanBalQty.subtract(rs.getBigDecimal(NWAB234001Constant.DB_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.loanBalQty,       loanBalQty);
            EZDTBLAccessor.updateSelectionField(cpoDtlTMsg, new String[]{"loanBalQty"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
                String[] keyStr = new String[] {origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum};
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_CPO, Arrays.toString(keyStr)};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            if (!BigDecimal.ZERO.equals(loanBalQty)) {
                return true;
            }

            // close original order detail
            if (!closeOriginalLoanDetail(origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum)) {
                return false;
            }

            // set ShpgNum for Shipping Status Update
            if (!setShpgNumber(origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum)) {
                return false;
            }

            // close original order detail for set parent
            if (ZYPCommonFunc.hasValue(rs.getString(NWAB234001Constant.DB_SET_MDSE_CD))) {
                if (!closeOriginalSetParent(origCpoOrdNum, origCpoDtlLineNum)) {
                    return false;
                }
            }
            // close original order
            if (!closeOriginalCpo(origCpoOrdNum)) {
                return false;
            }

            return true;
        }

        private boolean closeOriginalSetParent(String origCpoOrdNum, String origCpoDtlLineNum) throws SQLException {

            String origCpoDtlLineSubNum = "000";

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",           glblCmpyCd);
            ssmParam.put("origCpoOrdNum",        origCpoOrdNum);
            ssmParam.put("origCpoDtlLineNum",    origCpoDtlLineNum);
            ssmParam.put("origCpoDtlLineSubNum", origCpoDtlLineSubNum);
            ssmParam.put("ordLineStsClosed",     ORD_LINE_STS.CLOSED);
            ssmParam.put("ordLineStsCancelled",  ORD_LINE_STS.CANCELLED);

            Integer cnt = (Integer) ssmBatchClient.queryObject("countOpenCompornent", ssmParam);

            if (cnt == 0) {
                if (!closeOriginalLoanDetail(origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum)) {
                    return false;
                }
            }

            return true;
        }

        /**
         * close Original Loan Data
         * @param rs ResultSet
         */
        private boolean closeOriginalLoanDetail(String origCpoOrdNum, String origCpoDtlLineNum, String origCpoDtlLineSubNum) throws SQLException {

            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd,       glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum,        origCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum,    origCpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, origCpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.openFlg,          ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ordLineStsCd,     ORD_LINE_STS.CLOSED);

            EZDTBLAccessor.updateSelectionField(cpoDtlTMsg, new String[]{"openFlg", "ordLineStsCd"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
                String[] keyStr = new String[] {origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum};
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_CPO, Arrays.toString(keyStr)};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            // Add Start 2019/07/01 QC#50984
            printBizProcLog(NWAB234001Constant.EVENT_ID_CLOSE, origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum);
            // Add End 2019/07/01 QC#50984

            return true;
        }

        /**
         * close Original Cpo
         * @param origCpoOrdNum String
         */
        private boolean closeOriginalCpo(String origCpoOrdNum) throws SQLException {

            // close judge
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",           glblCmpyCd);
            ssmParam.put("origCpoOrdNum",        origCpoOrdNum);
            ssmParam.put("ordLineStsClosed",     ORD_LINE_STS.CLOSED);
            ssmParam.put("ordLineStsCancelled",  ORD_LINE_STS.CANCELLED);
            int lineCount = (Integer) ssmBatchClient.queryObject("countOpenDetailData", ssmParam);

            if (lineCount > 0) {
                return true;
            }

            //close CPO
            CPOTMsg cpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd,  glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum,   origCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg,     ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.CLOSED);
            EZDTBLAccessor.updateSelectionField(cpoTMsg, new String[]{"openFlg", "ordHdrStsCd"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
                String[] keyStr = new String[] {origCpoOrdNum};
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_CPO, origCpoOrdNum};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            // Add Start 2019/07/01 QC#50984
            printBizProcLog(NWAB234001Constant.EVENT_ID_CLOSE, origCpoOrdNum, null, null);
            // Add End 2019/07/01 QC#50984

            return true;
        }

        // Add Start 2019/07/01 QC#50984
        /**
         * Print S21BusinessProcessLogMsg
         * @param eventId String
         * @param ordNum String
         * @param lineNum String
         * @param lineSubNum String
         */
        private void printBizProcLog(String eventId, String ordNum, String lineNum, String lineSubNum) {
            S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();

            bizProcLogMsg.setSubSysId(NWAB234001Constant.SUB_SYS_ID);
            bizProcLogMsg.setProcId(NWAB234001Constant.PROCESS_ID);
            bizProcLogMsg.setDocTpCd(NWAB234001Constant.DOCUMENT_TYPE);
            bizProcLogMsg.setEventId(eventId);

            if (ZYPCommonFunc.hasValue(lineNum) && ZYPCommonFunc.hasValue(lineSubNum)) {
                bizProcLogMsg.setDocId(S21StringUtil.concatStrings(lineNum, ".", lineSubNum));
            }

            bizProcLogMsg.setPrntDocId(ordNum);

            S21BusinessProcessLog.print(bizProcLogMsg);
        }
        // Add End 2019/07/01 QC#50984

        /**
         * update cpo detail for Conversion Order
         * @param rs ResultSet
         */
        private boolean updateDsCpoDtlForConvOrder(ResultSet rs) throws SQLException {

            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd,       glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum,        rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum,    rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.convCompFlg,      ZYPConstant.FLG_ON_Y);

            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.convProcStsCd,    NWAB234001Constant.CONV_PROC_STS_CD_PROC_TRGT);

            EZDTBLAccessor.updateSelectionField(cpoDtlTMsg, new String[]{"convCompFlg", "convProcStsCd"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
                String[] keyStr = getKeyStr(rs);
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_CPO_DTL,  Arrays.toString(keyStr)};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            return true;
        }

        /**
         * update ds cpo return detail for Conversion Order
         * @param rs ResultSet
         */
        private boolean updateDsCpoRtrnDtlForConvOrder(ResultSet rs) throws SQLException {

            String cpoOrdNum           = rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM);
            String dsCpoRtrnLineNum    = rs.getString(NWAB234001Constant.DB_DS_CPO_RTRN_LINE_NUM);
            String dsCpoRtrnLineSubNum = rs.getString(NWAB234001Constant.DB_DS_CPO_RTRN_LINE_SUB_NUM);

            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd,          glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum,           cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum,    dsCpoRtrnLineNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, dsCpoRtrnLineSubNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.convCompFlg,         ZYPConstant.FLG_ON_Y);

            EZDTBLAccessor.updateSelectionField(dsCpoRtrnDtlTMsg, new String[]{"convCompFlg"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCpoRtrnDtlTMsg.getReturnCode())) {
                String[] keyStr = new String[] {cpoOrdNum, dsCpoRtrnLineNum, dsCpoRtrnLineSubNum};
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_DS_CPO_RTRN_DTL,  Arrays.toString(keyStr)};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            return true;
        }
//QC#9594 add Start
        private boolean updateSetParentSalesConversionOrder(ResultSet rs) throws SQLException {

            String cpoOrdNum            = rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM);
            String cpoDtlLineNum        = rs.getString(NWAB234001Constant.DB_CPO_DTL_LINE_NUM);
            String cpoDtlLineSubNum     = "000";

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",          glblCmpyCd);
            ssmParam.put("cpoOrdNum",           cpoOrdNum);
            ssmParam.put("cpoDtlLineNum",       cpoDtlLineNum);
            ssmParam.put("cpoDtlLineSubNum",    cpoDtlLineSubNum);
            ssmParam.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
            ssmParam.put("convCompFlgN",         ZYPConstant.FLG_OFF_N);

            Integer cnt = (Integer) ssmBatchClient.queryObject("countUnconpleteConversionCompornent", ssmParam);

            if (cnt > 0) {
                return true;
            }

            //update CPO_DTL
            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd,       glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum,        cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum,    cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.convCompFlg,      ZYPConstant.FLG_ON_Y);

            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.convProcStsCd,    NWAB234001Constant.CONV_PROC_STS_CD_PROC_TRGT);

            EZDTBLAccessor.updateSelectionField(cpoDtlTMsg, new String[]{"convCompFlg", "convProcStsCd"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
                String[] keyStr = new String[] {cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum};
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_CPO_DTL,  Arrays.toString(keyStr)};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            return true;
        }

        private boolean updateSetParentReturnConversionOrder(ResultSet rs) throws SQLException {

            String cpoOrdNum            = rs.getString(NWAB234001Constant.DB_CPO_ORD_NUM);
            String dsCpoRtrnLineNum     = rs.getString(NWAB234001Constant.DB_DS_CPO_RTRN_LINE_NUM);
            String dsCpoRtrnLineSubNum  = "000";

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",              glblCmpyCd);
            ssmParam.put("cpoOrdNum",               cpoOrdNum);
            ssmParam.put("dsCpoRtrnLineNum",        dsCpoRtrnLineNum);
            ssmParam.put("dsCpoRtrnLineSubNum",     dsCpoRtrnLineSubNum);
            ssmParam.put("rtrnLineStsCancelled",    RTRN_LINE_STS.CANCELLED);
            ssmParam.put("convCompFlgN",            ZYPConstant.FLG_OFF_N);

            Integer cnt = (Integer) ssmBatchClient.queryObject("countUnconpleteConversionCompornentForReturn", ssmParam);

            if (cnt > 0) {
                return true;
            }

            //update DS_CPO_RTRN_DTL
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd,          glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum,           cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum,    dsCpoRtrnLineNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, dsCpoRtrnLineSubNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.convCompFlg,         ZYPConstant.FLG_ON_Y);

            EZDTBLAccessor.updateSelectionField(dsCpoRtrnDtlTMsg, new String[]{"convCompFlg"});

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCpoRtrnDtlTMsg.getReturnCode())) {
                String[] keyStr = new String[] {cpoOrdNum, dsCpoRtrnLineNum, dsCpoRtrnLineSubNum};
                String[] msgConvStr = new String[] {NWAB234001Constant.TBL_DS_CPO_RTRN_DTL,  Arrays.toString(keyStr)};
                logOutputAndStkMsg(keyStr, NWAB234001Constant.NWZM1024E, msgConvStr);
                return false;
            }

            return true;
        }

        /**
         * close Original Loan Data
         * @param rs ResultSet
         */
        private boolean setShpgNumber(String origCpoOrdNum, String origCpoDtlLineNum, String origCpoDtlLineSubNum) throws SQLException {

            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            shpgPlnTMsg.setSQLID("001");
            shpgPlnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shpgPlnTMsg.setConditionValue("trxHdrNum01", origCpoOrdNum);
            shpgPlnTMsg.setConditionValue("trxLineNum01", origCpoDtlLineNum);
            shpgPlnTMsg.setConditionValue("trxLineSubNum01", origCpoDtlLineSubNum);

            SHPG_PLNTMsgArray shpgPlnTMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByCondition(shpgPlnTMsg);

            if (shpgPlnTMsgArray != null && shpgPlnTMsgArray.length() > 0) {

                ArrayList<String> shpgNumList;
                if (!shpgNumListMap.containsKey(origCpoOrdNum)) {
                    shpgNumList = new ArrayList<String>();
                } else {
                    shpgNumList = shpgNumListMap.get(origCpoOrdNum);
                }

                for (int i = 0; i < shpgPlnTMsgArray.length(); i++) {
                    SHPG_PLNTMsg tMsg = (SHPG_PLNTMsg) shpgPlnTMsgArray.get(i);
                    shpgNumList.add(tMsg.shpgPlnNum.getValue());

                    if (shpgNumList.size() == NWAB234001Constant.MAX_LIST_SIZE_1000) {
                        shpgNumListMap.put(origCpoOrdNum, shpgNumList);
                        // Call NWZC188001
                        if (!callStsUpdApi()) {
                            shpgNumListMap.clear();
                            return false;
                        }
                        shpgNumListMap.clear();
                    }
                }
                if (shpgNumList.size() != 0) {
                    shpgNumListMap.put(origCpoOrdNum, shpgNumList);
                }
            }
            return true;
        }

        /**
         * Call NWZC188001 Display Order Line Status Update API
         * @param cpoOrdNum String
         * @return boolean
         */
        private boolean callStsUpdApi() {

            if (shpgNumListMap.size() == 0) {
                return true;
            }

            
            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);

            Set<String> ordNumSet = shpgNumListMap.keySet();
            for (String cpoOrdNum : ordNumSet) {
                ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);
                ArrayList<String> shpgNumList = shpgNumListMap.get(cpoOrdNum);

                for (int i = 0; i < shpgNumList.size(); i++) {
                    NWZC188001_shpgPlnNumListPMsg shpgNumMsg = pMsg.shpgPlnNumList.no(i);
                    ZYPEZDItemValueSetter.setValue(shpgNumMsg.shpgPlnNum, shpgNumList.get(i));
                    pMsg.shpgPlnNumList.setValidCount(i + 1);
                }
                
            }

            new NWZC188001().execute(pMsg, ONBATCH_TYPE.BATCH);

            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }

            for (int i = 0; i < pMsg.shpgPlnNumList.getValidCount(); i++) {
                // Shipping Number List: Error check
                NWZC188001_shpgPlnNumListPMsg shpgListPmsg = pMsg.shpgPlnNumList.no(i);

                if (ZYPCommonFunc.hasValue(shpgListPmsg.xxMsgId)) {
                    S21InfoLogOutput.println(shpgListPmsg.xxMsgId.getValue());
                    return false;
                }
            }
            return true;
        }

    }
    /**
     * Send error Mail
     */
    private void postMail() {
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NWAB234001Constant.ML_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            String[] msgConvStr = new String[] {NWAB234001Constant.FROM, NWAB234001Constant.ML_GRP_ID_FROM, NWAB234001Constant.HYPHEN};
            throw new S21AbendException(NWAB234001Constant.NWAM0637E, msgConvStr);
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NWAB234001Constant.ML_GRP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            String[] msgConvStr = new String[] {NWAB234001Constant.TO, NWAB234001Constant.ML_GRP_ID_TO, NWAB234001Constant.HYPHEN};
            throw new S21AbendException(NWAB234001Constant.NWAM0637E, msgConvStr);
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, NWAB234001Constant.ML_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NWAB234001Constant.NWAM0319E, new String[] {NWAB234001Constant.ML_TMPL_ID});
        }
        cratMailMsg();
        String currentTime = ZYPDateUtil.getCurrentSystemTime(NWAB234001Constant.ML_DT_TM_FMT);
        template.setTemplateParameter(NWAB234001Constant.ML_TMPL_KEY_ID, NWAB234001Constant.BIZ_ID);
        template.setTemplateParameter(NWAB234001Constant.ML_TMPL_KEY_DT, currentTime);
        template.setTemplateParameter(NWAB234001Constant.ML_TMPL_KEY_MSG, mailMsgInfo.toString());

        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(addrFromList.get(0));
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(NWAB234001Constant.ML_LANG), NWAB234001Constant.ML_LANG_CD);
        mail.postMail();
    }

    /**
     * Create Error Mail Message
     */
    private void cratMailMsg() {
        String ttl;
        String msg;
        mailMsgInfo.append(NWAB234001Constant.LINE_FEED_CODE);
        for (Map.Entry<String, List<String>> entry : msgMap.entrySet()) {
            ttl = S21StringUtil.concatStrings(entry.getKey(), NWAB234001Constant.LINE_FEED_CODE);
            mailMsgInfo.append(ttl);
            for (String value : entry.getValue()) {
                msg = S21StringUtil.concatStrings(NWAB234001Constant.TAB, value, NWAB234001Constant.LINE_FEED_CODE);
                mailMsgInfo.append(msg);
            }
        }
    }

    /**
     * get Conversion Target Line Category List
     */
    private List<String> getConvLineCatgList(String convProcTpTxt) {

        String key = createCacheKey("geConvTargetKehy", convProcTpTxt);
        List<String> lineCatgCdList = (List<String>) cacheMap.get(key);

        if (lineCatgCdList == null) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",    glblCmpyCd);
            ssmParam.put("convProcTpTxt", convProcTpTxt);
            ssmParam.put("slsDt",         salesDate);
            // 2020/01/21 QC#55207-1 Del Start
            //// 2019/12/26 QC#55207 Add Start
            //ssmParam.put("dsOrdLineCatg", new String[] {DS_ORD_LINE_CATG.RENTAL_CONVERSION_BILL_ONLY});
            //// 2018/03/06 QC#55207 Add End
            // 2020/01/21 QC#55207-1 Del End
            lineCatgCdList = (List<String>) ssmBatchClient.queryObjectList("geConvTargetKehy", ssmParam);
            cacheMap.put(key, lineCatgCdList);
        }
        return lineCatgCdList;
    }

    // 2018/03/06 S21_NA#20153 Add Start
    private Map<String, Object> getIbOwnerOverrideInfo(String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {

        Map<String, Object> ibOwnerOverrideInfo = new HashMap<String, Object>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",     glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", "IB_OWNER_OVERRIDE");
        ssmParam.put("dsOrdCatgCd",    dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd",      dsOrdTpCd);
        ssmParam.put("dsOrdRsnCd",     dsOrdRsnCd);
        List<Map<String, Object>> ibOwnerOverrideInfoList =
            (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIbOwnerOverrideInfo", ssmParam);

        if (ibOwnerOverrideInfoList.size() > 0) {
            ibOwnerOverrideInfo = ibOwnerOverrideInfoList.get(0);
        }

        return ibOwnerOverrideInfo;
    }
    // 2018/03/06 S21_NA#20153 Add End
}
