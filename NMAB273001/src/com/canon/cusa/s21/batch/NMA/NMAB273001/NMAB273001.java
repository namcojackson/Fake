/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB273001;

import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.INFO_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.NMAM0071E;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.ZYPM0181E;
import static com.canon.cusa.s21.batch.NMA.NMAB273001.constant.NMAB273001Constant.ZZZM9026E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.PRIN_CUSTTMsg;
import business.db.PRIN_CUSTTMsgArray;
import business.db.PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.UPLD_CSV_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Duns Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/15   Fujitsu         M.Ohno           Create          QC#57315
 * </pre>
 */

public class NMAB273001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }
        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.batchHelper = new S21RequestBatchHelper();
    }

    @Override
    protected void mainRoutine() {
        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            this.doProcess(request);
        }
    }

    private void doProcess(ART10FMsg fMsg) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Upload ID
        String upldCsvId = getUpldCsvId(fMsg);

        // Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);

            stmt = ssmLLClient.createPreparedStatement("getDunsUpldWrk", param, execParam);
            rs = stmt.executeQuery();

            BigDecimal rowNum;
            List<PTY_LOC_WRKTMsg> plwList = new ArrayList<PTY_LOC_WRKTMsg>();
            List<BILL_TO_CUSTTMsg> btcList = new ArrayList<BILL_TO_CUSTTMsg>();
            List<SHIP_TO_CUSTTMsg> stcList = new ArrayList<SHIP_TO_CUSTTMsg>();
            List<PRIN_CUSTTMsg> pcList = new ArrayList<PRIN_CUSTTMsg>();

            while (rs.next()) {
                String locNum = rs.getString("LOC_NUM");
                rowNum = rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

                // PTY_LOC_WRK
                PTY_LOC_WRKTMsg plwTMsg = new PTY_LOC_WRKTMsg();
                plwTMsg.setSQLID("012");
                plwTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                plwTMsg.setConditionValue("locNum01", locNum);

                PTY_LOC_WRKTMsgArray plwTMsgArray = (PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(plwTMsg);
                if (plwTMsgArray.getValidCount() < 1) {
                    this.errorCount++;
                    this.messenger.addMessageForRecord(rowNum, NMAM8121E, locNum);
                    continue;
                }

                String dunsNum = rs.getString("DUNS_NUM");
                String dsUltDunsNum = rs.getString("DS_ULT_DUNS_NUM");
                String hqDunsNum = rs.getString("HQ_DUNS_NUM");
                BigDecimal dsLocEmpNum = rs.getBigDecimal("DS_LOC_EMP_NUM");
                BigDecimal dsLocRevAmt = rs.getBigDecimal("DS_LOC_REV_AMT");
                String dsCustSicCd = rs.getString("DS_CUST_SIC_CD");
                String dsCustSicDescTxt = rs.getString("DS_CUST_SIC_DESC_TXT");
                BigDecimal ptyLocPk = plwTMsgArray.no(0).ptyLocPk.getValue();

                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).dunsNum, dunsNum);
                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).dsUltDunsNum, dsUltDunsNum);
                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).hqDunsNum, hqDunsNum);
                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).dsLocEmpNum, dsLocEmpNum);
                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).dsLocRevAmt, dsLocRevAmt);
                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).dsCustSicCd, dsCustSicCd);
                ZYPEZDItemValueSetter.setValue(plwTMsgArray.no(0).dsCustSicDescTxt, dsCustSicDescTxt);
                plwList.add(plwTMsgArray.no(0));
                this.normalCount++;

                // BILL_TO_CUST
                BILL_TO_CUSTTMsg btcTMsg = new BILL_TO_CUSTTMsg();
                btcTMsg.setSQLID("060");
                btcTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                btcTMsg.setConditionValue("locNum01", locNum);

                BILL_TO_CUSTTMsgArray btcTMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(btcTMsg);
                if (btcTMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(btcTMsgArray.no(0).dunsNum, dunsNum);
                    btcList.add(btcTMsgArray.no(0));
                }

                // SHIP_TO_CUST
                SHIP_TO_CUSTTMsg stcTMsg = new SHIP_TO_CUSTTMsg();
                stcTMsg.setSQLID("048");
                stcTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                stcTMsg.setConditionValue("locNum01", locNum);

                SHIP_TO_CUSTTMsgArray stcTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(stcTMsg);
                if (stcTMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(stcTMsgArray.no(0).dunsNum, dunsNum);
                    stcList.add(stcTMsgArray.no(0));
                }

                // PRIN_CUST
                PRIN_CUSTTMsg pcTMsg = new PRIN_CUSTTMsg();
                pcTMsg.setSQLID("003");
                pcTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                pcTMsg.setConditionValue("ptyLocPk01", ptyLocPk);

                PRIN_CUSTTMsgArray pcTMsgArray = (PRIN_CUSTTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(pcTMsg);
                if (pcTMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(pcTMsgArray.no(0).dunsNum, dunsNum);
                    ZYPEZDItemValueSetter.setValue(pcTMsgArray.no(0).dsUltDunsNum, dsUltDunsNum);
                    ZYPEZDItemValueSetter.setValue(pcTMsgArray.no(0).dsLocEmpNum, dsLocEmpNum);
                    ZYPEZDItemValueSetter.setValue(pcTMsgArray.no(0).dsLocRevAmt, dsLocRevAmt);
                    pcList.add(pcTMsgArray.no(0));
                }

                // update
                if (plwList.size() >= DEFAULT_FETCH_SIZE) {
                    S21FastTBLAccessor.update(plwList.toArray(new PTY_LOC_WRKTMsg[plwList.size()]));
                    if (btcList.size() > 0) {
                        S21FastTBLAccessor.update(btcList.toArray(new BILL_TO_CUSTTMsg[btcList.size()]));
                    }

                    if (stcList.size() > 0) {
                        S21FastTBLAccessor.update(stcList.toArray(new SHIP_TO_CUSTTMsg[stcList.size()]));
                    }

                    if (pcList.size() > 0) {
                        S21FastTBLAccessor.update(pcList.toArray(new PRIN_CUSTTMsg[pcList.size()]));
                    }
                    commit();

                    plwList.clear();
                    btcList.clear();
                    stcList.clear();
                    pcList.clear();
                }
            }

            // last update
            if (plwList.size() > 0) {
                S21FastTBLAccessor.update(plwList.toArray(new PTY_LOC_WRKTMsg[plwList.size()]));
            }

            if (btcList.size() > 0) {
                S21FastTBLAccessor.update(btcList.toArray(new BILL_TO_CUSTTMsg[btcList.size()]));
            }

            if (stcList.size() > 0) {
                S21FastTBLAccessor.update(stcList.toArray(new SHIP_TO_CUSTTMsg[stcList.size()]));
            }

            if (pcList.size() > 0) {
                S21FastTBLAccessor.update(pcList.toArray(new PRIN_CUSTTMsg[pcList.size()]));
            }

            // result set
            StringBuilder msgValue = new StringBuilder();
            msgValue.append(this.normalCount);
            msgValue.append(INFO_MSG);

            if (this.errorCount > 0) {
                msgValue.append(" ");
                msgValue.append(this.errorCount);
                msgValue.append(ERR_MSG);
                this.messenger.addMessageForFile(NYXM0002E, msgValue.toString());
                this.messenger.uploadMessage();
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, msgValue.toString());
                this.messenger.uploadMessage();
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                this.termCd = TERM_CD.NORMAL_END;
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);

        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB273001().executeBatch(NMAB273001.class.getSimpleName());

    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NMAM0071E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (S21StringUtil.isEmpty(uploadCsvId)) {
            String[] msg = {MSG_UPLD_CSV_ID };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * @param upldCsvReqPk upload CSV Request Pk
     * @return UPLD_CSV_RQSTTMsg
     */
    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {

        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }

        return outTMsg;
    }

}
