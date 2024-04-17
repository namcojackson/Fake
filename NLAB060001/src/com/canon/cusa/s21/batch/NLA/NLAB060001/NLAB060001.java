/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLA.NLAB060001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NLAI2020_01TMsg;
import business.db.NLAI2020_02TMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_MSGTMsg;
import business.db.RWS_MSGTMsgArray;
import business.db.WMS_INBD_PO_DTLTMsg;
import business.db.WMS_INBD_PO_HDRTMsg;
import business.db.WMS_INBD_PO_VNDTMsg;

import com.canon.cusa.s21.batch.NLA.NLAB060001.constant.NLAB060001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * ASN (Advanced Ship Notification) to Choice.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            T.Hakodate      Create          N/A
 * 04/14/2016   CITS            Y.IWASAKI       Update          QC#6817
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19369
 * 07/03/2017   CITS            Y.Imazu         Update          QC#19720
 * 
 *</pre>
 */

public class NLAB060001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Termination code */
    private TERM_CD termCd;

    /** The number of success */
    private int successCount;

    /** The number of error */
    private int errorCount;

    /** Commit Count */
    private int commitCount = 0;

    /** WH Owner */
    private String whOwnrCd;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLAB060001().executeBatch(NLAB060001.class.getSimpleName());
    }

    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        successCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        this.whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLAB060001Constant.VAR_CHAR_CONST_NM_TARGET_ORDER, this.glblCmpyCd);
        //this.whOwnrCd = WH_OWNR.CHOICE;

        // Check on input parameter
        checkParameter();
    }

    protected void mainRoutine() {

        try {

            // Create RWS Interface data.
            createRwsInterface();
            commit();

        } catch(S21AbendException e) {

            rollback();
            throw e;

        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLAB060001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    protected void termRoutine() {

        // The number of total
        int totalCount = successCount + errorCount;
        S21InfoLogOutput.println(NLAB060001Constant.ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount) });

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);

    }

    /**
     * checkParameter
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            throw new S21AbendException(NLAB060001Constant.ZZM9000E, new String[] {NLAB060001Constant.PARAM_NM_GLBL_CMPY_CD });
        }

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {

            throw new S21AbendException(NLAB060001Constant.ZZM9000E, new String[] {NLAB060001Constant.PARAM_NM_INTERFACE_ID });
        }

        // Commit Count
        String str = getUserVariable1();

        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(NLAB060001Constant.ZZM9000E, new String[] {NLAB060001Constant.PARAM_NM_VAR_USER1 });
        }

        try {
            commitCount = Integer.parseInt(str);

        } catch (NumberFormatException e) {

            throw new S21AbendException(NLAB060001Constant.ZZM9004E, new String[] {"Commit Count(" + str + ")" });
        }
    }

    /**
     * createRwsInterface
     * @param trxId
     * @param execParam
     */
    private void createRwsInterface() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLAB060001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(NLAB060001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(NLAB060001Constant.COL_WMS_DROP_STS_CD, NLAB060001Constant.VAL_WMS_DROP_STS_CD_NOT_DROP);
            ssmParam.put(NLAB060001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
            ssmParam.put(NLAB060001Constant.COL_INBD_OTBD_CD, INBD_OTBD.INBOUND);
            /* 07/03/2017 CSAI Y.Imazu Add QC#19720 START */
            ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
            /* 07/03/2017 CSAI Y.Imazu Add QC#19720 END */

            prdStmt = ssmLLClient.createPreparedStatement("findRws", ssmParam, execParam);
            rs = prdStmt.executeQuery();

            int processedCount = 0;
            int unitId = 1;

            while (rs.next()) {

                ++processedCount;
                // ++unitId;

                Map<String, Object> rwsHdr = convertRwsHeaderToMap(rs);

                String glblCmpyCd = (String)rwsHdr.get(NLAB060001Constant.COL_GLBL_CMPY_CD);
                String rwsNum = (String)rwsHdr.get(NLAB060001Constant.COL_RWS_NUM);

                // RWS_MSG
                List<Map<String, Object>> rwsMsgList = getRwsMsgList(glblCmpyCd, rwsNum);

                // RWS_DTL
                List<Map<String, Object>> rwsDtlList = getRwsDetailList(glblCmpyCd, rwsNum);

                // Validate fetched data
                if (!validateIFData(rwsHdr, rwsDtlList)) {
                    rollback();
                    ++errorCount;
                    processedCount = 0;
                    continue;
                }

                // Get Transaction ID.(RWS)
                BigDecimal trxId = trxAccess.getNextTransactionId();

                String positionKey=NLXCMsgHelper.toListedString(NLAB060001Constant.COL_GLBL_CMPY_CD, NLAB060001Constant.COL_RWS_NUM);
                String position=NLXCMsgHelper.toListedString(this.glblCmpyCd, rwsNum);
                
                // Insert NLAI2020_01
                NLAI2020_01TMsg if01TMsg = createNLAI2020_01RWS(rwsHdr, trxId, unitId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    outputErr(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_NLAI2020_01, NLAB060001Constant.TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_NLAI2020_01, NLAB060001Constant.TBL_RWS_HDR, positionKey, position }));
                }

                // Insert NLAI2020_02
                List<NLAI2020_02TMsg> if02TMsgList=createNLAI2020_02RWS(rwsHdr, rwsDtlList, trxId, unitId);
                int count=S21FastTBLAccessor.insert(if02TMsgList.toArray(new NLAI2020_02TMsg[if02TMsgList.size()]));
                if (count!=if02TMsgList.size()) {
                    outputErr(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_NLAI2020_02, NLAB060001Constant.TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_NLAI2020_02, NLAB060001Constant.TBL_RWS_HDR, positionKey, position }));
                }

                WMS_INBD_PO_HDRTMsg poHdrTMsg = createWmsInbdPoHdr(rwsHdr, rwsMsgList);
                EZDTBLAccessor.insert(poHdrTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poHdrTMsg.getReturnCode())) {
                    outputErr(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_WMS_INBD_PO_HDR, NLAB060001Constant.TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_WMS_INBD_PO_HDR, NLAB060001Constant.TBL_RWS_HDR, positionKey, position }));
                }
                
                List<WMS_INBD_PO_DTLTMsg> poDtlTMsgList = createWmsInbdPoDtlList(poHdrTMsg, rwsHdr, rwsDtlList);
                count = S21FastTBLAccessor.insert(poDtlTMsgList.toArray(new WMS_INBD_PO_DTLTMsg[poDtlTMsgList.size()]));
                if (count != poDtlTMsgList.size()) {
                    outputErr(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_WMS_INBD_PO_DTL, NLAB060001Constant.TBL_RWS_HDR, positionKey, position});

                    throw new S21AbendException(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_WMS_INBD_PO_DTL, NLAB060001Constant.TBL_RWS_HDR, positionKey, position});
                }
                
                WMS_INBD_PO_VNDTMsg poVndTMsg = createWmsInbdPoVnd(poHdrTMsg, rwsHdr);
                EZDTBLAccessor.insert(poVndTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poVndTMsg.getReturnCode())) {
                    outputErr(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_WMS_INBD_PO_VND, NLAB060001Constant.TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLAB060001Constant.NLGM0045E, new String[] {NLAB060001Constant.TBL_WMS_INBD_PO_VND, NLAB060001Constant.TBL_RWS_HDR, positionKey, position }));
                }
                
                // Create Transaction table.
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);

                updateRwsHdr(rwsNum, NLAB060001Constant.VAL_WMS_DROP_STS_CD_DROP);

                ++successCount;

                if (processedCount >= commitCount) {
                    commit();
                    processedCount = 0;
                }

            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLAB060001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    private Map<String, Object> convertRwsHeaderToMap(ResultSet rs) throws SQLException {
        HashMap<String, Object> rwsHdr=new HashMap<String, Object>();
        
        rwsHdr.put(NLAB060001Constant.COL_RWS_REF_NUM, rs.getString(NLAB060001Constant.COL_RWS_REF_NUM));
        rwsHdr.put(NLAB060001Constant.COL_WH_IN_ETA_DT, rs.getString(NLAB060001Constant.COL_WH_IN_ETA_DT));
        rwsHdr.put(NLAB060001Constant.COL_TPL_LOC_NM, rs.getString(NLAB060001Constant.COL_TPL_LOC_NM));
        rwsHdr.put(NLAB060001Constant.COL_ORIG_ORD_QTY, rs.getBigDecimal(NLAB060001Constant.COL_ORIG_ORD_QTY));
        rwsHdr.put(NLAB060001Constant.COL_GLBL_CMPY_CD, rs.getString(NLAB060001Constant.COL_GLBL_CMPY_CD));
        rwsHdr.put(NLAB060001Constant.COL_WH_CD, rs.getString(NLAB060001Constant.COL_WH_CD));
        rwsHdr.put(NLAB060001Constant.COL_S80_CMPY_CD, rs.getString(NLAB060001Constant.COL_S80_CMPY_CD));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_CD, rs.getString(NLAB060001Constant.COL_FROM_LOC_CD));
        rwsHdr.put(NLAB060001Constant.COL_S80_ORD_TP_CD, rs.getString(NLAB060001Constant.COL_S80_ORD_TP_CD));
        rwsHdr.put(NLAB060001Constant.COL_S80_TRX_CD, rs.getString(NLAB060001Constant.COL_S80_TRX_CD));
        rwsHdr.put(NLAB060001Constant.COL_WH_IN_ETA_DT, rs.getString(NLAB060001Constant.COL_WH_IN_ETA_DT));
        rwsHdr.put(NLAB060001Constant.COL_IMPT_INV_VESL_NM, rs.getString(NLAB060001Constant.COL_IMPT_INV_VESL_NM));
        rwsHdr.put(NLAB060001Constant.COL_IMPT_INV_BOL_NUM, rs.getString(NLAB060001Constant.COL_IMPT_INV_BOL_NUM));
        rwsHdr.put(NLAB060001Constant.COL_WH_CD, rs.getString(NLAB060001Constant.COL_WH_CD));
        rwsHdr.put(NLAB060001Constant.COL_RWS_NUM, rs.getString(NLAB060001Constant.COL_RWS_NUM));
        rwsHdr.put(NLAB060001Constant.COL_SVC_CONFIG_MSTR_PK, rs.getString(NLAB060001Constant.COL_SVC_CONFIG_MSTR_PK));
        rwsHdr.put(NLAB060001Constant.COL_STAGE_ACT_CD, rs.getString(NLAB060001Constant.COL_STAGE_ACT_CD));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_NM_01, rs.getString(NLAB060001Constant.COL_FROM_LOC_NM_01));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_NM_02, rs.getString(NLAB060001Constant.COL_FROM_LOC_NM_02));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_ADDR_01, rs.getString(NLAB060001Constant.COL_FROM_LOC_ADDR_01));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_ADDR_02, rs.getString(NLAB060001Constant.COL_FROM_LOC_ADDR_02));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_ADDR_03, rs.getString(NLAB060001Constant.COL_FROM_LOC_ADDR_03));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_ADDR_04, rs.getString(NLAB060001Constant.COL_FROM_LOC_ADDR_04));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_CTY_ADDR, rs.getString(NLAB060001Constant.COL_FROM_LOC_CTY_ADDR));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_ST_CD, rs.getString(NLAB060001Constant.COL_FROM_LOC_ST_CD));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_POST_CD, rs.getString(NLAB060001Constant.COL_FROM_LOC_POST_CD));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_CTRY_CD, rs.getString(NLAB060001Constant.COL_FROM_LOC_CTRY_CD));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_PSN_NM, rs.getString(NLAB060001Constant.COL_FROM_LOC_PSN_NM));
        rwsHdr.put(NLAB060001Constant.COL_FROM_LOC_TEL_NUM, rs.getString(NLAB060001Constant.COL_FROM_LOC_TEL_NUM));
        
        return rwsHdr;
    }

    /**
     * @param rwsNum
     * @return
     */
    private List<Map<String, Object>> getRwsDetailList(String glblCmpyCd, String rwsNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NLAB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLAB060001Constant.COL_RWS_NUM, rwsNum);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 START */
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 END */

        List<Map<String, Object>> rwsDtlList = ssmBatchClient.queryObjectList("getRwsDetail", ssmParam);

        return rwsDtlList;
    }

    private List<Map<String, Object>> getRwsMsgList(String glblCmpyCd, String rwsNum) {

        ArrayList<Map<String, Object>> rwsMsgList=new ArrayList<Map<String, Object>>();

        RWS_MSGTMsg tMsg=new RWS_MSGTMsg();
        tMsg.setSQLID("001");
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, rwsNum);

        RWS_MSGTMsgArray tMsgArray=(RWS_MSGTMsgArray)EZDTBLAccessor.findByCondition(tMsg);

        for(int n=0; n<tMsgArray.getValidCount(); ++n) {
            tMsg=tMsgArray.no(n);

            HashMap<String, Object> rwsMsg=new HashMap<String, Object>();
            rwsMsg.put(NLAB060001Constant.COL_GLBL_CMPY_CD, tMsg.glblCmpyCd.getValue());
            rwsMsg.put(NLAB060001Constant.COL_RWS_NUM, tMsg.rwsNum.getValue());
            rwsMsg.put(NLAB060001Constant.COL_RWS_MSG_SQ_NUM, tMsg.rwsMsgSqNum.getValue());
            rwsMsg.put(NLAB060001Constant.COL_RWS_MSG_TXT, tMsg.rwsMsgTxt.getValue());
            rwsMsgList.add(rwsMsg);
        }

        return rwsMsgList;
    }

    private boolean validateIFData(Map<String, Object> rwsHdr, List<Map<String, Object>> rwsDtlList) {
        // MANDATORY CHECK
        String rwsNum = (String)rwsHdr.get(NLAB060001Constant.COL_RWS_NUM);

        if (!ZYPCommonFunc.hasValue((String)rwsHdr.get(NLAB060001Constant.COL_WH_CD))) {
            outputErr(NLAB060001Constant.NLGM0041E, new String[] {NLAB060001Constant.COL_WH_CD, NLAB060001Constant.TBL_RWS_HDR, rwsNum });
            return false;
        }
        if (!ZYPCommonFunc.hasValue((String)rwsHdr.get(NLAB060001Constant.COL_TPL_LOC_NM))) {
            outputErr(NLAB060001Constant.NLGM0041E, new String[] {NLAB060001Constant.COL_TPL_LOC_NM, NLAB060001Constant.TBL_TPL_LOC, rwsNum });
            return false;
        }

        return true;
    }

    /**
     * createNLAI2020_01RWS
     * @param rwsHeader
     * @param unitId
     * @param rwsTrxId
     */
    private NLAI2020_01TMsg createNLAI2020_01RWS(Map<String, Object> rwsHdr, BigDecimal trxId, int unitId) {

        NLAI2020_01TMsg tMsg = new NLAI2020_01TMsg();

        // BAT_PROC_DT
        String batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        batProcDt += ZYPDateUtil.getCurrentSystemTime(NLAB060001Constant.FMT_HHMMSSSSS);

        // WH_IN_ETA_DT
        String whInEtaDt = (String) rwsHdr.get(NLAB060001Constant.COL_WH_IN_ETA_DT);
        if (ZYPCommonFunc.hasValue(whInEtaDt)) {
            whInEtaDt = ZYPDateUtil.DateFormatter(whInEtaDt, NLAB060001Constant.FMT_YYYYMMDD, NLAB060001Constant.FMT_YYYYMMDDHHMMSSSSS);
        }

        // ORIG_ORD_QTY
        BigDecimal origOrdQty = (BigDecimal) rwsHdr.get(NLAB060001Constant.COL_ORIG_ORD_QTY);
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, new BigDecimal(unitId));
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, NLAB060001Constant.VAL_TPL_FROM_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, NLAB060001Constant.VAL_TPL_TO_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, batProcDt);
        ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, (String) rwsHdr.get(NLAB060001Constant.COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rqstShipDtTmTsTxt, whInEtaDt);
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, NLAB060001Constant.VAL_TPL_ACCT_TXT);
        ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, (String) rwsHdr.get(NLAB060001Constant.COL_TPL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, origOrdQty.toPlainString());
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLAB060001Constant.VAL_WMS_REC_ID_01);

        return tMsg;
    }

    /**
     * createNLAI2020_02RWS
     * @param rwsDtlList
     * @param unitId
     * @param trxId
     */
    private List<NLAI2020_02TMsg> createNLAI2020_02RWS(Map<String, Object> rwsHdr, List<Map<String, Object>> rwsDtlList, BigDecimal trxId, int unitId) {
        ArrayList<NLAI2020_02TMsg> tMsgList = new ArrayList<NLAI2020_02TMsg>();

        int seqNo = 1;

        for (Map<String, Object> rwsDtl : rwsDtlList) {

            NLAI2020_02TMsg tMsg = new NLAI2020_02TMsg();

            seqNo++;

            // RWS_QTY
            String qtyOrdTxt = null;
            BigDecimal rwsQty = (BigDecimal) rwsDtl.get(NLAB060001Constant.COL_RWS_QTY);
            if (rwsQty != null) {
                qtyOrdTxt = rwsQty.toPlainString();
            }

            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, new BigDecimal(unitId));
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, new BigDecimal(seqNo));
            ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, qtyOrdTxt);
            ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, (String) rwsDtl.get(NLAB060001Constant.COL_MDSE_CD));
            // ZYPEZDItemValueSetter.setValue(tMsg.tplVndItemCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLAB060001Constant.VAL_WMS_REC_ID_02);

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    private WMS_INBD_PO_HDRTMsg createWmsInbdPoHdr(Map<String, Object> rwsHdr, List<Map<String, Object>> rwsMsgList) {
        WMS_INBD_PO_HDRTMsg tMsg = new WMS_INBD_PO_HDRTMsg();

        // WMS_SQ_NUM
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
        // WH_IN_ETA_DT
        String whInEtaDt = (String)rwsHdr.get(NLAB060001Constant.COL_WH_IN_ETA_DT);
        if(ZYPCommonFunc.hasValue(whInEtaDt)) {
            whInEtaDt += "000000";
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String)rwsHdr.get(NLAB060001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String)rwsHdr.get(NLAB060001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLAB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLAB060001Constant.VAL_INTFC_REC_TP_ID_HDR);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String)rwsHdr.get(NLAB060001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPoId, (String)rwsHdr.get(NLAB060001Constant.COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrchTpCd, (String)rwsHdr.get(NLAB060001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxCd, (String)rwsHdr.get(NLAB060001Constant.COL_S80_TRX_CD));
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsPoStsCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.poFromDtTmTs, whInEtaDt);
        // ZYPEZDItemValueSetter.setValue(tMsg.poUserId, );
        ZYPEZDItemValueSetter.setValue(tMsg.printSwthCd, NLAB060001Constant.VAL_PRINT_SWTH_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsVeslNm, (String)rwsHdr.get(NLAB060001Constant.COL_IMPT_INV_VESL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBolNum, (String)rwsHdr.get(NLAB060001Constant.COL_IMPT_INV_BOL_NUM));

        EZDTStringItem[] fieldItems = new EZDTStringItem[] {tMsg.inbdPoMsgTxt_01, tMsg.inbdPoMsgTxt_02, tMsg.inbdPoMsgTxt_03, tMsg.inbdPoMsgTxt_04 };
        for (int n = 0; n < rwsMsgList.size() && n < fieldItems.length; ++n) {
            String text = (String) rwsMsgList.get(n).get(NLAB060001Constant.COL_RWS_MSG_TXT);
            if (ZYPCommonFunc.hasValue(text)) {
                ZYPEZDItemValueSetter.setValue(fieldItems[n], text);
            }
        }

        // ZYPEZDItemValueSetter.setValue(tMsg.wmsResrcTxt, );
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsCloDtTmTs, );
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsSerNum, );
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String)rwsHdr.get(NLAB060001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, (String)rwsHdr.get(NLAB060001Constant.COL_RWS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, (BigDecimal)rwsHdr.get(NLAB060001Constant.COL_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.stageActCd, (String)rwsHdr.get(NLAB060001Constant.COL_STAGE_ACT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stageRecStsCd, NLAB060001Constant.VAL_STAGE_REC_STS_CD);

        return tMsg;
    }

    private List<WMS_INBD_PO_DTLTMsg> createWmsInbdPoDtlList(WMS_INBD_PO_HDRTMsg hdrTMsg, Map<String, Object> rwsHdr, List<Map<String, Object>> rwsDtlList) {
        ArrayList<WMS_INBD_PO_DTLTMsg> tMsgList = new ArrayList<WMS_INBD_PO_DTLTMsg>();

        for(Map<String, Object> rwsDtl : rwsDtlList) {
            WMS_INBD_PO_DTLTMsg tMsg = new WMS_INBD_PO_DTLTMsg();

            // WH_IN_ETA_DT
            String whInEtaDt = (String)rwsDtl.get(NLAB060001Constant.COL_WH_IN_ETA_DT);
            if(ZYPCommonFunc.hasValue(whInEtaDt)) {
                whInEtaDt += "000000";
            }
            //WMS_LINE_NUM
            BigDecimal wmsLineNum = null;
            String rwsDtlLineNum = (String)rwsDtl.get(NLAB060001Constant.COL_RWS_DTL_LINE_NUM);
            if(ZYPCommonFunc.hasValue(rwsDtlLineNum)) {
                wmsLineNum = new BigDecimal(rwsDtlLineNum);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String)rwsHdr.get(NLAB060001Constant.COL_GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String)rwsHdr.get(NLAB060001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsLineNum, wmsLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLAB060001Constant.VAL_INTFC_TP_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLAB060001Constant.VAL_INTFC_REC_TP_ID_DTL);
            // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String)rwsHdr.get(NLAB060001Constant.COL_S80_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsPoId, (String)rwsHdr.get(NLAB060001Constant.COL_RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String)rwsDtl.get(NLAB060001Constant.COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.s80StkStsCd, (String)rwsDtl.get(NLAB060001Constant.COL_S80_STK_STS_CD));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsLineStsCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInvInd, NLAB060001Constant.VAL_WMS_INV_IND);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOpenQty, (BigDecimal)rwsDtl.get(NLAB060001Constant.COL_RWS_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsEstDtTmTs, whInEtaDt);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInvId, (String)rwsDtl.get(NLAB060001Constant.COL_IMPT_INV_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsDoId, (String)rwsDtl.get(NLAB060001Constant.COL_IMPT_INV_DO_NUM));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsDescTxt, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsResrcTxt, );
            ZYPEZDItemValueSetter.setValue(tMsg.cseFromNum, (BigDecimal)rwsDtl.get(NLAB060001Constant.COL_OUT_PACK_FROM_CSE_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.cseToNum, (BigDecimal)rwsDtl.get(NLAB060001Constant.COL_OUT_PACK_TO_CSE_NUM));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsBatNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsColloNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String)rwsHdr.get(NLAB060001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String)rwsDtl.get(NLAB060001Constant.COL_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.usrCdRefTxt, (String)rwsDtl.get(NLAB060001Constant.COL_THIRD_PTY_DSP_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.serApvlReqFlg, (String)rwsDtl.get(NLAB060001Constant.COL_SER_APVL_REQ_FLG));

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    private WMS_INBD_PO_VNDTMsg createWmsInbdPoVnd(WMS_INBD_PO_HDRTMsg hdrTMsg, Map<String, Object> rwsHdr) {
        WMS_INBD_PO_VNDTMsg tMsg = new WMS_INBD_PO_VNDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String)rwsHdr.get(NLAB060001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String)rwsHdr.get(NLAB060001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLAB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLAB060001Constant.VAL_INTFC_REC_TP_ID_VND);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String)rwsHdr.get(NLAB060001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPoId, (String)rwsHdr.get(NLAB060001Constant.COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsVndNm_01, adjustString((String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_NM_01), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsVndNm_02, adjustString((String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_NM_02), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.vndToCtacNm, (String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndToCtacNum, adjustString((String)rwsHdr.get(NLAB060001Constant.COL_FROM_LOC_TEL_NUM), 15));
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsResrcTxt, );

        return tMsg;
    }
    
    /**
     * updateRwsHdr
     * @param gCmpyCd
     * @param rwsNum
     */
    private void updateRwsHdr(String rwsNum, String wmsDropStsCd) {

        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, rwsNum);

        inMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        ZYPEZDItemValueSetter.setValue(inMsg.wmsDropStsCd, wmsDropStsCd);

        EZDTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append(inMsg.getTableName());

            rollback();

            outputErr(NLAB060001Constant.NPAM1003E, new String[] {sb.toString() });

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NLAB060001Constant.NPAM1003E, new String[] {sb.toString() }));

        }

    }

    /**
     * @param msgId
     * @param msgParam
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    private String adjustString(String val, int len) {
        if (val == null) {
            return null;
        }

        if (val.length() > len) {
            val = val.substring(0, len);
        }

        return val;
    }
}
