/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB034001;

import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.CNT_MDSE;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.TAX_EXEM_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.GET_TAX_EXEM_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.GET_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.MSG_DESC_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NMAM8709E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NMAM8710E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NMZM0374E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NMZM0375E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NMAM8463E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NWBM0097E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.PARAM_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.PARAM_TAX_EXEM_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.PARAM_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.UPLD_CSV_RQST_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB034001.constant.NMAB034001Constant.ZYPM0181E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.MDSETMsg;
import business.db.TAX_UPLD_WRKTMsg;
import business.db.UPLD_CSV_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Taxing Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/13   CITS            M.Furugoori     Create          QC#55448
 * 2020/12/09   CITS            J.Evangelista   Update          QC#57947
 *</pre>
 */

public class NMAB034001 extends S21BatchMain {

    /** UserProfile */
    private S21UserProfileService profile = null;

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** ProcCount */
    private int procCount = 0;

    /** ErrCount */
    private int errCount = 0;

    /**
     */
    @Override
    protected void initRoutine() {

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        batchHelper = new S21RequestBatchHelper();

    }

    /**
     */
    @Override
    protected void mainRoutine() {

        glblCmpyCd = getGlblCmpyCd();
        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            this.doProcess(request);
        }
    }

    /**
     */
    @Override
    protected void termRoutine() {

        setTermState(this.termCd, procCount, errCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB034001().executeBatch(NMAB034001.class.getSimpleName());
    }

    /**
     * @param fMsg ART10FMsg
     */
    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement ciWrk = null;
        ResultSet rsWrk = null;

        // Upload ID
        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

        // Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            inWrkMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
            inWrkMap.put(PARAM_UPLD_CSV_RQST_PK, upldCSVRqstTMsg.upldCsvRqstPk);

            ciWrk = ssmLLClient.createPreparedStatement(GET_WRK, inWrkMap, execParam);
            rsWrk = ciWrk.executeQuery();
            TAX_UPLD_WRKTMsg wrkTMsg = null;

            int currentError = 0;
            int currentNomal = 0;
            boolean warnFlg = false;
            while (rsWrk.next()) {
                wrkTMsg = transferWrk(rsWrk);
                if (!ZYPCommonFunc.hasValue(wrkTMsg.mdseCd)) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, MSG_DESC_MDSE_CD);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                // checkItemMaster
                Map<String, Object> inMdseMap = new HashMap<String, Object>();
                inMdseMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                inMdseMap.put(PARAM_MDSE_CD, wrkTMsg.mdseCd.getValue());
                Integer cntMdse = (Integer) this.ssmBatchClient.queryObject(CNT_MDSE, inMdseMap, execParam);
                if (cntMdse < 1) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8709E, wrkTMsg.mdseCd.getValue());
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }
                // checkCodeTable
                String taxExemTpCd = null;
                if (ZYPCommonFunc.hasValue(wrkTMsg.taxExemTpDescTxt)) {
                    taxExemTpCd = getTaxExemTp(wrkTMsg.taxExemTpDescTxt.getValue());
                    if (!ZYPCommonFunc.hasValue(taxExemTpCd)) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8710E, wrkTMsg.taxExemTpDescTxt.getValue());
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }

                MDSETMsg mdseTmsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTmsg.mdseCd, wrkTMsg.mdseCd.getValue());

                mdseTmsg = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdate(mdseTmsg);
                if (mdseTmsg == null) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMZM0374E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                // START 2020/12/09 J.Evangelista [QC#57947,ADD]
                String prevTaxExemTpCd = mdseTmsg.taxExemTpCd.getValue();
                // END   2020/12/09 J.Evangelista [QC#57947,ADD]
                if (ZYPCommonFunc.hasValue(wrkTMsg.taxExemTpDescTxt)) {
                    ZYPEZDItemValueSetter.setValue(mdseTmsg.taxExemTpCd, taxExemTpCd);
                }
                else {
                    mdseTmsg.taxExemTpCd.clear();
                }

                // START 2020/12/09 J.Evangelista [QC#57947,ADD]
                // Update MDSE record only when taxExemTpCd is changed.
                if (!prevTaxExemTpCd.equals(mdseTmsg.taxExemTpCd.getValue())) {
                // END   2020/12/09 J.Evangelista [QC#57947,ADD]
                    S21FastTBLAccessor.update(mdseTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTmsg.getReturnCode())) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMZM0375E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                // START 2020/12/09 J.Evangelista [QC#57947,ADD]
                }
                // END   2020/12/09 J.Evangelista [QC#57947,ADD]

                commit();
                currentNomal++;
            }
            this.procCount = this.procCount + currentNomal;
            this.errCount = this.errCount + currentError;
            if (currentError == 0) {
                batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                if (warnFlg) {
                    this.messenger.addMessageForFile(NMAM8463E, null);
                } else {
                    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(currentNomal, 0, currentError));
                }
                this.messenger.uploadMessage();
            } else {
                termCd = TERM_CD.WARNING_END;
                batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(currentNomal, 0, currentError));
                this.messenger.uploadMessage();
            }
            commit();
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ciWrk, rsWrk);
        }
    }

    private void setErrorMsg(ART10FMsg fMsg) {
        rollback();
        this.messenger.uploadMessage();
    }

    private String getTaxExemTp(String searchKey) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(PARAM_TAX_EXEM_TP_DESC_TXT, searchKey);

        List<String> resultList = (List<String>) this.ssmBatchClient.queryObjectList(GET_TAX_EXEM_TP, paramMap);

        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    /**
     * @return CTAC_INFO_WRKTMsg
     */
    private TAX_UPLD_WRKTMsg transferWrk(ResultSet rs) throws SQLException {

        TAX_UPLD_WRKTMsg wrkTMsg = new TAX_UPLD_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wrkTMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.taxExemTpDescTxt, rs.getString(TAX_EXEM_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstCmntTxt, rs.getString(UPLD_CSV_RQST_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstPk, rs.getBigDecimal(UPLD_CSV_RQST_PK));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstRowNum, rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));

        return wrkTMsg;
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

        if (!ZYPCommonFunc.hasValue(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NWBM0097E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!ZYPCommonFunc.hasValue(uploadCsvId)) {
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

    /**
     * @return GlobalCompanyCode
     */
    private String getGlblCmpyCd() {

        String cmpyCd = this.profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(cmpyCd)) {
            String[] msg = {GLOBAL_COMPANY_CODE };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return cmpyCd;
    }

    private static String createResultMessageArg(int insCount, int updCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insCount > 0) {
            builder.append(String.format(RESULT_MSG_INS, insCount));
        }
        if (updCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, updCount));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }
}
