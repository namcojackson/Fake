/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB723001;

import static com.canon.cusa.s21.batch.NMA.NMAB723001.constant.NMAB723001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ART10FMsg;
import business.db.CSMP_CONTRTMsg;
import business.db.COA_BRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * CSMP Customer Upload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   SRAA            K.Aratani       Update          N/A
 * 2017/09/08   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 * 2017/09/21   Fujitsu         K.Ishizuka      Update          QC#21333
 *</pre>
 */
public class NMAB723001 extends S21BatchMain {

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** S21RequestBatchHelper */
    private S21RequestBatchHelper reqbatch = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** Code Map (DS_ACCT_CUST) */
    private Map<String, Map<String, Object>> dsAcctCustMap = null;

    /** Code Map (PRC_CATG) */
    private Map<String, Map<String, Object>> prcCatgMap = null;

    /** Code Map (PRC_CONTR) */
    private Map<String, Map<String, Object>> prcContrMap = null;

    /** Insert List CSMP_CONTRTMsg */
    private List<CSMP_CONTRTMsg> insTMsgList = null;

    /** Update List CSMP_CONTRTMsg */
    private List<CSMP_CONTRTMsg> updTMsgList = null;
    
    /**
     * initRoutine
     */
    @Override
    protected void initRoutine() {

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }
        

        this.reqbatch = new S21RequestBatchHelper();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.dsAcctCustMap = new HashMap<String, Map<String, Object>>();
        this.prcCatgMap = new HashMap<String, Map<String, Object>>();
        this.prcContrMap = new HashMap<String, Map<String, Object>>();
    }

    /**
     * mainRoutine
     */
    @Override
    protected void mainRoutine() {
        if (!this.reqbatch.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg reqmsg : this.reqbatch.getRequestList()) {
            this.doProcess(reqmsg);
        }
    }

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg reqMsg) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String glblCmpyCd = reqMsg.EZInCompanyCode.getValue();
            String upldCsvRqstPk = reqMsg.EZREQCondition.getValue();
            String upldCsvId = reqMsg.EZREQUserKey.getValue();

            this.messenger = new ZYPCSVUploadMessenger(upldCsvId, new BigDecimal(upldCsvRqstPk));

            BigDecimal wrkCount = getCountWrkData(glblCmpyCd, upldCsvRqstPk);
            if (wrkCount.intValue() == 0) {
                this.messenger.addMessageForFile(NWAM0803E, null);
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", glblCmpyCd);
            stesParam.put("upldCsvRqstPk", new BigDecimal(upldCsvRqstPk));
            ps = this.ssmLLClient.createPreparedStatement("getWrkData", stesParam, execParam());
            rs = ps.executeQuery();

            this.insTMsgList = new ArrayList<CSMP_CONTRTMsg>();
            this.updTMsgList = new ArrayList<CSMP_CONTRTMsg>();

            int recordCount = 0;
            int errorCount = 0;
            int processCount = 0;
            while (rs.next()) {
                recordCount++;

                if (!checkMandatory(rs)) {
                	errorCount++;
                    continue;
                }

                if (!checkFormat(rs)) {
                	errorCount++;
                    continue;
                }

                if (!checkMaster(glblCmpyCd, upldCsvId, upldCsvRqstPk, rs)) {
                	errorCount++;
                    continue;
                }
                processCount++;
            }
            
        	//Update
            int insUpdCount = 0;
            for (int i = 0; i < this.updTMsgList.size(); i++) {
                EZDTBLAccessor.update(this.updTMsgList.get(i));
                insUpdCount++;
                if (insUpdCount == this.commitNumber) {
                    commit();
                    insUpdCount = 0;
                }
            }
        	
            for (int i = 0; i < this.insTMsgList.size(); i++) {
                EZDTBLAccessor.create(this.insTMsgList.get(i));
                insUpdCount++;
                if (insUpdCount == this.commitNumber) {
                    commit();
                    insUpdCount = 0;
                }
            }
            if (insUpdCount > 0) {
                commit();
                insUpdCount = 0;
            }
            
            this.errorCount += errorCount;
            this.normalCount += processCount;
            this.insTMsgList = new ArrayList<CSMP_CONTRTMsg>();
            this.updTMsgList = new ArrayList<CSMP_CONTRTMsg>();
            if (errorCount > 0) {
            	//${success records} record(s) successfully created. ${error and warning records} record(s) skipped/errored.
            	StringBuffer sb = new StringBuffer();
            	sb.append(processCount);
            	sb.append(" record(s) successfully created. ");
            	sb.append(errorCount);
            	sb.append(" record(s) skipped/errored.");
                //this.messenger.addMessageForFile(NWAM0804E, null);
                this.messenger.addMessageForFile(NYXM0002E, sb.toString());
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
            } else {
            	//${success records} record(s) successfully created.
            	StringBuffer sb = new StringBuffer();
            	sb.append(processCount);
            	sb.append(" record(s) successfully created. ");
                this.messenger.addMessageForFile(NYXM0001I, sb.toString());
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * termRoutine
     */
    @Override
    protected void termRoutine() {
        int requestCnt = this.reqbatch.getRecordCount();
        S21InfoLogOutput.printlnv(ZZBM0009I, this.reqbatch.getFilePath(), "read", requestCnt);
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB723001().executeBatch(NMAB723001.class.getSimpleName());
    }

    /**
     * checkMandatory
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatory(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkMandatoryColumn(rs, DS_ACCT_NUM, CSV_DS_ACCT_NUM)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, DLR_REF_NUM, CSV_DLR_REF_NUM)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, EFF_FROM_DT, CSV_EFF_FROM_DT)) {
            checkResult = false;
        }

        return checkResult;
    }

    /**
     * checkFormat
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkFormat(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkFormatFlag(rs, CSMP_CONTR_ACTV_FLG, CSV_CSMP_CONTR_ACTV_FLG)) {
            checkResult = false;
        }

        return checkResult;
    }

    /**
     * checkMaster
     * @param glblCmpyCd String
     * @param upldCsvId String
     * @param upldCsvRqstPk String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMaster(String glblCmpyCd, String upldCsvId, String upldCsvRqstPk, ResultSet rs) throws SQLException {
        boolean checkResult = true;

        BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);

    	//Account Number
        String dsAcctNum = rs.getString(DS_ACCT_NUM);
        String dsAcctNm = "";
        Map<String, Object> dsAcctCustInfo = getDsAcctCust(glblCmpyCd, dsAcctNum);
        if (dsAcctCustInfo == null) {
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_DS_ACCT_NUM);
            checkResult = false;
        } else {
        	dsAcctNum = (String) dsAcctCustInfo.get(DS_ACCT_NUM);
        	dsAcctNm = (String) dsAcctCustInfo.get(DS_ACCT_NM);
        }

        //CSMP Number 
        if (!csmpNumFormatCheck(rs.getString(CSMP_NUM))) {
        	//NMAM8075E=0,The specified format is incorrect. It must be @.
            uploadMesageForRecord(upldCsvRqstRowNum, NMAM8075E, "XXXXX-XXXXX");
            checkResult = false;
        }
        //CSA Number
        
    	//CSMP Level
        String prcCatgNm = rs.getString(PRC_CATG_NM);
        String prcCatgCd = "";
        if (prcCatgNm != null && !"".equals(prcCatgNm)) {
            Map<String, Object> prcCatgInfo = getPrcCatg(glblCmpyCd, prcCatgNm);
            if (prcCatgInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_PRC_CATG_NM);
                checkResult = false;
            } else {
                prcCatgCd = (String) prcCatgInfo.get(PRC_CATG_CD);
            }
        }
        //CSMP Level
        
        // S21_NA DEL START QC#20206(Sol#269)
        //Contract Number
        //      if (rs.getString(PRC_CONTR_NUM) != null && !"".equals(rs.getString(PRC_CONTR_NUM)) ) {
        //            String prcContrNum = rs.getString(PRC_CONTR_NUM);
        //            Map<String, Object> prcContrInfo = getPrcContract(glblCmpyCd, prcContrNum);
        //            if (prcContrInfo == null) {
        //                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_PRC_CONTR_NUM);
        //                checkResult = false;
        //            } else {
        //              prcContrNum = (String) prcContrInfo.get(PRC_CONTR_NUM);
        //            }
        //      }
        // S21_NA DEL END QC#20206(Sol#269)
        
        //Originating GL Branch Code
        String origCoaBrCd = rs.getString(ORIG_COA_BR_CD);
        if (ZYPCommonFunc.hasValue(origCoaBrCd)) {
            COA_BRTMsg coaBrTMsg = findByKeyCoaBr(glblCmpyCd, origCoaBrCd);
            if (coaBrTMsg == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, ORIG_COA_BR_CD);
                checkResult = false;
            }
        }

        //Origin Dealer Code
        //Effective Date
        //Expiration Date
        String effFromDt = rs.getString(EFF_FROM_DT);
        String effThruDt = rs.getString(EFF_THRU_DT);
        if (ZYPCommonFunc.hasValue(effThruDt)) {
            if (ZYPDateUtil.compare(effFromDt, effThruDt) >= 0) {
        		//NMAM8213E=0,Effective date is out of range. Please check the Effective date.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, EFF_FROM_DT + " and " + EFF_THRU_DT);
                checkResult = false;
    	    }
    	}
        
        //Rejection Date
        //Early Term Date
        //Renewed CSMP#
        //Markup Equipment
        //Markup Accessory
        //Notes
        //Active
        
        
        //Account Number, CSA Number, Effective From Date
        Map<String, Object> map = getDuplicatedUploadWork(glblCmpyCd, rs.getString(DS_ACCT_NUM), rs.getString(DLR_REF_NUM), rs.getString(EFF_FROM_DT), rs.getString(EFF_THRU_DT), rs.getBigDecimal("UPLD_CSV_RQST_PK"), rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        if (map != null && map.get("UPLD_CSV_RQST_PK") != null) {
        	uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
            checkResult = false;
        }
        
        //Account Number, CSA Number, Effective From Date
        map = getDuplicated(glblCmpyCd, rs.getString(DS_ACCT_NUM), rs.getString(DLR_REF_NUM), rs.getString(EFF_FROM_DT));
        if (map != null && map.get("CSMP_CONTR_PK") != null) {
        	uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
            checkResult = false;
        }
        
        if (checkResult) {
            //Account Number, CSA Number, Effective From Date
            map = getDevidedLine(glblCmpyCd, rs.getString(DS_ACCT_NUM), rs.getString(DLR_REF_NUM), rs.getString(EFF_FROM_DT));
            //Shift Mode
            if (map != null && map.get("CSMP_CONTR_PK") != null) {
	        	CSMP_CONTRTMsg tMsg = findByKeyCSMPContr(glblCmpyCd, (BigDecimal) map.get("CSMP_CONTR_PK"));
        		String endDt = ZYPDateUtil.addDays(rs.getString(EFF_FROM_DT), -1);
        		if (ZYPCommonFunc.hasValue(rs.getString(EFF_FROM_DT)) && tMsg.effFromDt.getValue().equals(rs.getString(EFF_FROM_DT))) {
        			endDt = rs.getString(EFF_FROM_DT);
        		}
	            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, endDt);
	            updTMsgList.add(tMsg);
	            
	        	CSMP_CONTRTMsg newTMsg = new CSMP_CONTRTMsg();
	            ZYPEZDItemValueSetter.setValue(newTMsg.glblCmpyCd, glblCmpyCd);
	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpContrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_CONTR_SQ));
	            ZYPEZDItemValueSetter.setValue(newTMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
	            ZYPEZDItemValueSetter.setValue(newTMsg.dsAcctNm, dsAcctNm);
	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpNum, rs.getString(CSMP_NUM));
	            ZYPEZDItemValueSetter.setValue(newTMsg.dlrRefNum, rs.getString(DLR_REF_NUM));
	            ZYPEZDItemValueSetter.setValue(newTMsg.prcCatgCd, prcCatgCd);
	            //ZYPEZDItemValueSetter.setValue(newTMsg.prcContrNum, rs.getString(PRC_CONTR_NUM)); // S21_NA DEL QC#20206(Sol#269)
	            ZYPEZDItemValueSetter.setValue(newTMsg.origCoaBrCd, rs.getString(ORIG_COA_BR_CD));
	            ZYPEZDItemValueSetter.setValue(newTMsg.rtlDlrCd, rs.getString(RTL_DLR_CD));
	            ZYPEZDItemValueSetter.setValue(newTMsg.effFromDt, rs.getString(EFF_FROM_DT));
	            ZYPEZDItemValueSetter.setValue(newTMsg.effThruDt, rs.getString(EFF_THRU_DT));
	            ZYPEZDItemValueSetter.setValue(newTMsg.cusaRejDt, rs.getString(CUSA_REJ_DT));
	            ZYPEZDItemValueSetter.setValue(newTMsg.erlTrmnDt, rs.getString(ERL_TRMN_DT));
	            ZYPEZDItemValueSetter.setValue(newTMsg.rnwCsmpNum, rs.getString(RNW_CSMP_NUM));
	            ZYPEZDItemValueSetter.setValue(newTMsg.uplftEquipRatio, rs.getBigDecimal(UPLFT_EQUIP_RATIO));
	            ZYPEZDItemValueSetter.setValue(newTMsg.uplftAsryRatio, rs.getBigDecimal(UPLFT_ASRY_RATIO));
	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpNumCmntTxt, rs.getString(CSMP_NUM_CMNT_TXT));
	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpContrActvFlg, ZYPConstant.FLG_ON_Y);
	            insTMsgList.add(newTMsg);
	        //Not Shift Mode
            } else {
            	//Update Mode
                //Account Number, CSA Number, Effective From Date
            	Map<String, Object> updMap = getCsmpContr(glblCmpyCd, rs.getString(DS_ACCT_NUM), rs.getString(DLR_REF_NUM), rs.getString(EFF_FROM_DT));
                if (updMap != null && updMap.get("CSMP_CONTR_PK") != null) {
    	        	CSMP_CONTRTMsg tMsg = findByKeyCSMPContr(glblCmpyCd, (BigDecimal) updMap.get("CSMP_CONTR_PK"));
    	        	if (tMsg != null) {
    	                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
    	                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, dsAcctNm);
    	                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum, rs.getString(CSMP_NUM));
    	                ZYPEZDItemValueSetter.setValue(tMsg.dlrRefNum, rs.getString(DLR_REF_NUM));
    	                ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCd);
    	                // ZYPEZDItemValueSetter.setValue(tMsg.prcContrNum, rs.getString(PRC_CONTR_NUM)); // S21_NA DEL QC#20206(Sol#269)
    	                ZYPEZDItemValueSetter.setValue(tMsg.origCoaBrCd, rs.getString(ORIG_COA_BR_CD));
    	                ZYPEZDItemValueSetter.setValue(tMsg.rtlDlrCd, rs.getString(RTL_DLR_CD));
    	                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, rs.getString(EFF_FROM_DT));
    	                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, rs.getString(EFF_THRU_DT));
    	                ZYPEZDItemValueSetter.setValue(tMsg.cusaRejDt, rs.getString(CUSA_REJ_DT));
    	                ZYPEZDItemValueSetter.setValue(tMsg.erlTrmnDt, rs.getString(ERL_TRMN_DT));
    	                ZYPEZDItemValueSetter.setValue(tMsg.rnwCsmpNum, rs.getString(RNW_CSMP_NUM));
    	                ZYPEZDItemValueSetter.setValue(tMsg.uplftEquipRatio, rs.getBigDecimal(UPLFT_EQUIP_RATIO));
    	                ZYPEZDItemValueSetter.setValue(tMsg.uplftAsryRatio, rs.getBigDecimal(UPLFT_ASRY_RATIO));
    	                ZYPEZDItemValueSetter.setValue(tMsg.csmpNumCmntTxt, rs.getString(CSMP_NUM_CMNT_TXT));
    	                ZYPEZDItemValueSetter.setValue(tMsg.csmpContrActvFlg, rs.getString(CSMP_CONTR_ACTV_FLG));
    		            updTMsgList.add(tMsg);
    	        	}
    	        //Insert Mode
                } else {
    	        	CSMP_CONTRTMsg newTMsg = new CSMP_CONTRTMsg();
    	            ZYPEZDItemValueSetter.setValue(newTMsg.glblCmpyCd, glblCmpyCd);
    	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpContrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_CONTR_SQ));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.dsAcctNm, dsAcctNm);
    	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpNum, rs.getString(CSMP_NUM));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.dlrRefNum, rs.getString(DLR_REF_NUM));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.prcCatgCd, prcCatgCd);
    	            //ZYPEZDItemValueSetter.setValue(newTMsg.prcContrNum, rs.getString(PRC_CONTR_NUM)); // S21_NA DEL QC#20206(Sol#269)
    	            ZYPEZDItemValueSetter.setValue(newTMsg.origCoaBrCd, rs.getString(ORIG_COA_BR_CD));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.rtlDlrCd, rs.getString(RTL_DLR_CD));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.effFromDt, rs.getString(EFF_FROM_DT));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.effThruDt, rs.getString(EFF_THRU_DT));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.cusaRejDt, rs.getString(CUSA_REJ_DT));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.erlTrmnDt, rs.getString(ERL_TRMN_DT));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.rnwCsmpNum, rs.getString(RNW_CSMP_NUM));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.uplftEquipRatio, rs.getBigDecimal(UPLFT_EQUIP_RATIO));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.uplftAsryRatio, rs.getBigDecimal(UPLFT_ASRY_RATIO));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpNumCmntTxt, rs.getString(CSMP_NUM_CMNT_TXT));
    	            ZYPEZDItemValueSetter.setValue(newTMsg.csmpContrActvFlg, ZYPConstant.FLG_ON_Y);
    	            insTMsgList.add(newTMsg);
                }
            }
        }

        return checkResult;
    }

    /**
     * checkMandatoryColumn
     * @param rs ResultSet
     * @param targetColumnName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatoryColumn(ResultSet rs, String targetColumnName, String csvColumnName) throws SQLException {
        String targetColumn = rs.getString(targetColumnName);
        if (targetColumn == null || targetColumn.length() == 0) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, ZZM9000E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkFormatFlag
     * @param rs ResultSet
     * @param targetColumnName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkFormatFlag(ResultSet rs, String targetColumnName, String csvColumnName) throws SQLException {
        String targetColumn = rs.getString(targetColumnName);
        if (!ZYPConstant.FLG_ON_Y.equals(targetColumn) && !ZYPConstant.FLG_OFF_N.equals(targetColumn)) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0807E, csvColumnName);
            return false;
        }
        return true;
    }


    /**
     * uploadMesageForRecord
     * @param upldCsvRqstRowNum BigDecimal
     * @param msgId String
     * @param msgStr String
     */
    private void uploadMesageForRecord(BigDecimal upldCsvRqstRowNum, String msgId, String msgStr) {
        this.messenger.addMessageForRecord(upldCsvRqstRowNum, msgId, msgStr);
    }

    /**
     * getCountWrkData
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @return BigDecimal
     */
    private BigDecimal getCountWrkData(String glblCmpyCd, String upldCsvRqstPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("upldCsvRqstPk", upldCsvRqstPk);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getCountWrkData", queryParam);
        return result;
    }

    /**
     * getDsAcctCust
     * @param glblCmpyCd String
     * @param mdseItemClsTpDescTxt String
     * @return Map<String, Object>
     */
    private Map<String, Object> getDsAcctCust(String glblCmpyCd, String dsAcctNum) {
        if (this.dsAcctCustMap.containsKey(dsAcctNum)) {
            return this.dsAcctCustMap.get(dsAcctNum);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("dsAcctNum", dsAcctNum);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsAcctCust", queryParam);
            if (result != null) {
                this.dsAcctCustMap.put(dsAcctNum, result);
            }
            return result;
        }
    }
    
    /**
     * getPrcCatg
     * @param glblCmpyCd String
     * @param prcCatgNm String
     * @return Map<String, Object>
     */
    private Map<String, Object> getPrcCatg(String glblCmpyCd, String prcCatgNm) {
        if (this.prcCatgMap.containsKey(prcCatgNm)) {
            return this.prcCatgMap.get(prcCatgNm);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("prcCatgNm", prcCatgNm);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrcCatg", queryParam);
            if (result != null) {
                this.prcCatgMap.put(prcCatgNm, result);
            }
            return result;
        }
    }
    
    // S21_NA DEL START QC#20206(Sol#269)
    //    /**
    //     * getPrcContract
    //     * @param glblCmpyCd String
    //     * @param prcContrNum String
    //     * @return Map<String, Object>
    //     */
    //    private Map<String, Object> getPrcContract(String glblCmpyCd, String prcContrNum) { 
    //        if (this.prcContrMap.containsKey(prcContrNum)) {
    //            return this.prcContrMap.get(prcContrNum);
    //        } else {
    //            Map<String, Object> queryParam = new HashMap<String, Object>();
    //            queryParam.put("glblCmpyCd", glblCmpyCd);
    //            queryParam.put("prcContrNum", prcContrNum);
    //            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrcContract", queryParam);
    //            if (result != null) {
    //                this.prcContrMap.put(prcContrNum, result);
    //            }
    //            return result;
    //        }
    //    }
    // S21_NA DEL END QC#20206(Sol#269)
    
    /**
     * findByKeyCoaBr
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return COA_BRTMsg
     */
    private static COA_BRTMsg findByKeyCoaBr(String glblCmpyCd, String coaBrCd) {
    	COA_BRTMsg inMsg = new COA_BRTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.coaBrCd.setValue(coaBrCd);
        return (COA_BRTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    
    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * CSMP# Format Check
     * @param checkTarget String
     * @return
     */
    private static boolean csmpNumFormatCheck(String checkTarget) {
        if (!ZYPCommonFunc.hasValue(checkTarget)) {
            return true;
        }
        if (checkTarget.length() != 11) {
            return false;
        }
        if (checkTarget.matches("([0-9]|[A-Z]){5}-([0-9]|[A-Z]){5}")) {
            return true;
        }
        return false;
    }

    private Map<String, Object> getDuplicated(String glblCmpyCd, String dsAcctNum, String dlrRefNum, String effFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("dlrRefNum", dlrRefNum);
        ssmParam.put("effFromDt", effFromDt);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDuplicated", ssmParam);
        return result;
    }
    
    private Map<String, Object> getDevidedLine(String glblCmpyCd, String dsAcctNum, String dlrRefNum, String effFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("dlrRefNum", dlrRefNum);
        ssmParam.put("effFromDt", effFromDt);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDevidedLine", ssmParam);
        return result;
    }
    
    private Map<String, Object> getCsmpContr(String glblCmpyCd, String dsAcctNum, String dlrRefNum, String effFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("dlrRefNum", dlrRefNum);
        ssmParam.put("effFromDt", effFromDt);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCsmpContr", ssmParam);
        return result;
    }
    
    private Map<String, Object> getDuplicatedUploadWork(String glblCmpyCd, String dsAcctNum, String dlrRefNum, String effFromDt, String effThruDt, BigDecimal upldCsvRqstPk, BigDecimal upldCsvRqstRowNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("dlrRefNum", dlrRefNum);
        ssmParam.put("effFromDt", effFromDt);
        if (ZYPCommonFunc.hasValue(effThruDt)) {
        	ssmParam.put("effThruDt", effThruDt);
        } else {
        	ssmParam.put("effThruDt", "99991231");
        }
        ssmParam.put("upldCsvRqstPk", upldCsvRqstPk);
        ssmParam.put("upldCsvRqstRowNum", upldCsvRqstRowNum);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDuplicatedUploadWork", ssmParam);
        return result;
    }
    /**
     * findByKeyCoaBr
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return COA_BRTMsg
     */
    private static CSMP_CONTRTMsg findByKeyCSMPContr(String glblCmpyCd, BigDecimal csmpContrPk) {
    	CSMP_CONTRTMsg inMsg = new CSMP_CONTRTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.csmpContrPk.setValue(csmpContrPk);
        return (CSMP_CONTRTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    
}
