/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB035001;

import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.GET_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.COA_PROD_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.COA_MDSE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.MKT_MDL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.FRTH_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.SLS_MAT_GRP_CD_01;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.SLS_MAT_GRP_CD_02;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.SLS_MAT_GRP_CD_03;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.MSG_DESC_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.BLK;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8463E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8707E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMZM0375E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NWBM0097E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8358E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8713E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8714E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8715E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8716E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8717E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8718E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8719E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8720E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8721E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.NMAM8722E;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.PARAM_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.UPLD_CSV_RQST_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.REV_ACCT_START_WITH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB035001.constant.NMAB035001Constant.ZYPM0181E;

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
import business.db.COA_PROJTMsg;
import business.db.ITEM_HDR_UPLD_WRKTMsg;
import business.db.ART10FMsg;
import business.db.COA_PRODTMsg;
import business.db.MDSETMsg;
import business.db.MKT_MDLTMsg;
import business.db.UPLD_CSV_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Header Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/09   Hitachi         S.Nakatani      Create          QC#60399
 *</pre>
 */

public class NMAB035001 extends S21BatchMain {

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

        new NMAB035001().executeBatch(NMAB035001.class.getSimpleName());
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
            ITEM_HDR_UPLD_WRKTMsg wrkTMsg = null;

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

                MDSETMsg mdseTMsg = new MDSETMsg();
                mdseTMsg = findMdse(getGlobalCompanyCode(), wrkTMsg.mdseCd.getValue());
                if (mdseTMsg == null) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8713E, wrkTMsg.mdseCd.getValue());
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                boolean allDataMatch = true;

                // PRODUCT CODE check
                if (ZYPCommonFunc.hasValue(wrkTMsg.coaProdCd) && !wrkTMsg.coaProdCd.getValue().equals(mdseTMsg.coaProdCd.getValue())) {
                    COA_PRODTMsg coaProdTmsg = findCoaProd(getGlobalCompanyCode(), wrkTMsg.coaProdCd.getValue());
                    if (coaProdTmsg == null) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8714E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                    if (ZYPCommonFunc.hasValue(mdseTMsg.coaProdCd) && ZYPCommonFunc.hasValue(mdseTMsg.revCoaAcctCd)) {
                        String revCoaAcctCd = mdseTMsg.revCoaAcctCd.getValue();
                        if (COA_PROD.ADMINISTRATION.equals(wrkTMsg.coaProdCd.getValue()) && revCoaAcctCd.startsWith(REV_ACCT_START_WITH_CD)) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8715E, null);
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.coaProdCd, wrkTMsg.coaProdCd);
                    allDataMatch = false;
                }

                // Merchandise Type check
                if (ZYPCommonFunc.hasValue(wrkTMsg.coaMdseTpCd) && !wrkTMsg.coaMdseTpCd.getValue().equals(mdseTMsg.coaMdseTpCd.getValue())) {
                    COA_PROJTMsg coaProjTmsg = findCoaProj(getGlobalCompanyCode(), wrkTMsg.coaMdseTpCd.getValue());
                    if (coaProjTmsg == null) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8716E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                    if (checkMdseTp(wrkTMsg, mdseTMsg)) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8707E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                    List<Map<String, Object>> list = getMdseTpValSetTMsg(getGlobalCompanyCode(), "SVC_ALLOC_TP_REQ_ITEM", mdseTMsg.mdseItemTpCd.getValue(), wrkTMsg.coaMdseTpCd.getValue());
                    if (list != null && list.size() > 0) {
                        if (!ZYPCommonFunc.hasValue(mdseTMsg.svcAllocTpCd)) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8722E, "Service Allocation Type");
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        }
                    }
                    Map<String, Object> mapIPCC = null;
                    List<Map<String, Object>> rsIntgProdCatgConvResultList = getIntgProdCatgConvPriorityList(wrkTMsg, mdseTMsg, getGlobalCompanyCode());
                    if ((rsIntgProdCatgConvResultList == null) || (rsIntgProdCatgConvResultList.isEmpty())) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8358E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                    mapIPCC = (Map<String, Object>) rsIntgProdCatgConvResultList.get(0);
                    if (mapIPCC == null) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8358E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.coaMdseTpCd, wrkTMsg.coaMdseTpCd);
                    allDataMatch = false;
                }

                // Marketing Model check
                if (ZYPCommonFunc.hasValue(wrkTMsg.mktMdlCd) && !wrkTMsg.mktMdlCd.getValue().equals(mdseTMsg.mktMdlCd.getValue())) {
                    if (BLK.equals(String.valueOf(wrkTMsg.mktMdlCd.getValue()).toUpperCase())) {
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.mktMdlCd, "");
                        allDataMatch = false;
                    } else {
                        MKT_MDLTMsg mktMdlTMsg = new MKT_MDLTMsg();
                        ZYPEZDItemValueSetter.setValue(mktMdlTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(mktMdlTMsg.mktMdlCd, wrkTMsg.mktMdlCd);
                        mktMdlTMsg = (MKT_MDLTMsg) EZDTBLAccessor.findByKey(mktMdlTMsg);
                        if (mktMdlTMsg == null) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8717E, null);
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        }
                        if (MDSE_ITEM_TP.MACHINE.equals(mdseTMsg.mdseItemTpCd.getValue()) || (MDSE_ITEM_TP.KIT.equals(mdseTMsg.mdseItemTpCd.getValue()) && COA_MDSE_TP.MACHINE.equals(wrkTMsg.coaMdseTpCd.getValue()))) {
                            if (!ZYPCommonFunc.hasValue(wrkTMsg.mktMdlCd)) {
                                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8722E, "Marketing Model");
                                setErrorMsg(fMsg);
                                currentError++;
                                continue;
                            }
                            if (!ZYPCommonFunc.hasValue(mdseTMsg.mktMdlSegCd)) {
                                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8722E, "Marketing Segment");
                                setErrorMsg(fMsg);
                                currentError++;
                                continue;
                            }
                        }
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.mktMdlCd, wrkTMsg.mktMdlCd);
                        allDataMatch = false;
                    }
                }

                // Product Level 5 check
                if (ZYPCommonFunc.hasValue(wrkTMsg.frthProdCtrlCd) && !wrkTMsg.frthProdCtrlCd.getValue().equals(mdseTMsg.frthProdCtrlCd.getValue())) {
                    Map<String, Object> map = getFrthProdHrch(wrkTMsg, getGlobalCompanyCode());
                    if (map == null) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8718E, (String) wrkTMsg.frthProdCtrlCd.getValue());
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    } else {
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.frthProdCtrlCd, wrkTMsg.frthProdCtrlCd);
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.frthProdCtrlNm, wrkTMsg.frthProdCtrlCd);
                        allDataMatch = false;
                    }
                }

                // Material Group 1 check
                if (ZYPCommonFunc.hasValue(wrkTMsg.slsMatGrpCd_01) && !wrkTMsg.slsMatGrpCd_01.getValue().equals(mdseTMsg.slsMatGrpCd_01.getValue())) {
                    if (BLK.equals(String.valueOf(wrkTMsg.slsMatGrpCd_01.getValue()).toUpperCase())) {
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_01, "");
                        allDataMatch = false;
                    } else {
                        Map<String, Object> map = getSlsMatGrp(getGlobalCompanyCode(), wrkTMsg.slsMatGrpCd_01.getValue(), "1");
                        if (map == null) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8719E, (String) wrkTMsg.slsMatGrpCd_01.getValue());
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        } else {
                            ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_01, wrkTMsg.slsMatGrpCd_01);
                            allDataMatch = false;
                        }
                    }
                }

                // Material Group 2 check
                if (ZYPCommonFunc.hasValue(wrkTMsg.slsMatGrpCd_02) && !wrkTMsg.slsMatGrpCd_02.getValue().equals(mdseTMsg.slsMatGrpCd_02.getValue())) {
                    if (BLK.equals(String.valueOf(wrkTMsg.slsMatGrpCd_02.getValue()).toUpperCase())) {
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_02, "");
                        allDataMatch = false;
                    } else {
                        Map<String, Object> map = getSlsMatGrp(getGlobalCompanyCode(), wrkTMsg.slsMatGrpCd_02.getValue(), "2");
                        if (map == null) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8720E, (String) wrkTMsg.slsMatGrpCd_02.getValue());
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        } else {
                            ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_02, wrkTMsg.slsMatGrpCd_02);
                            allDataMatch = false;
                        }
                    }
                }

                // Material Group 3 check
                if (ZYPCommonFunc.hasValue(wrkTMsg.slsMatGrpCd_03) && !wrkTMsg.slsMatGrpCd_03.getValue().equals(mdseTMsg.slsMatGrpCd_03.getValue())) {
                    if (BLK.equals(String.valueOf(wrkTMsg.slsMatGrpCd_03.getValue()).toUpperCase())) {
                        ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_03, "");
                        allDataMatch = false;
                    } else {
                        Map<String, Object> map = getSlsMatGrp(getGlobalCompanyCode(), wrkTMsg.slsMatGrpCd_03.getValue(), "3");
                        if (map == null) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8721E, (String) wrkTMsg.slsMatGrpCd_03.getValue());
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        } else {
                            ZYPEZDItemValueSetter.setValue(mdseTMsg.slsMatGrpCd_03, wrkTMsg.slsMatGrpCd_03);
                            allDataMatch = false;
                        }
                    }
                }

                // Update MDSE record.
                if (!allDataMatch) {
                    S21FastTBLAccessor.update(mdseTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTMsg.getReturnCode())) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMZM0375E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }

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

    /**
     * @return CTAC_INFO_WRKTMsg
     */
    private ITEM_HDR_UPLD_WRKTMsg transferWrk(ResultSet rs) throws SQLException {

        ITEM_HDR_UPLD_WRKTMsg wrkTMsg = new ITEM_HDR_UPLD_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wrkTMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstPk, rs.getBigDecimal(UPLD_CSV_RQST_PK));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstRowNum, rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.coaProdCd, rs.getString(COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.coaMdseTpCd, rs.getString(COA_MDSE_TP_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.mktMdlCd, rs.getString(MKT_MDL_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.frthProdCtrlCd, rs.getString(FRTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.slsMatGrpCd_01, rs.getString(SLS_MAT_GRP_CD_01));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.slsMatGrpCd_02, rs.getString(SLS_MAT_GRP_CD_02));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.slsMatGrpCd_03, rs.getString(SLS_MAT_GRP_CD_03));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstCmntTxt, rs.getString(UPLD_CSV_RQST_CMNT_TXT));

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

    private MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
    }

    private COA_PRODTMsg findCoaProd(String glblCmpyCd, String coaProd) {

        COA_PRODTMsg coaProdTMsg = new COA_PRODTMsg();
        ZYPEZDItemValueSetter.setValue(coaProdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaProdTMsg.coaProdCd, coaProd);
        return (COA_PRODTMsg) S21CacheTBLAccessor.findByKey(coaProdTMsg);

    }

    private COA_PROJTMsg findCoaProj(String glblCmpyCd, String coaMdseTpCd) {

        COA_PROJTMsg coaProjTMsg = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(coaProjTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaProjTMsg.coaProjCd, coaMdseTpCd);
        return (COA_PROJTMsg) S21CacheTBLAccessor.findByKey(coaProjTMsg);

    }

    private boolean checkMdseTp(ITEM_HDR_UPLD_WRKTMsg wrkTMsg, MDSETMsg mdseTMsg) {
        // Open transaction?
        int rsTran = getTransactionDataCPOAndPO(wrkTMsg.glblCmpyCd.getValue(), wrkTMsg.mdseCd.getValue());
        BigDecimal countInvty = getInventoryCount(wrkTMsg.mdseCd.getValue());
        Integer countTran = new Integer(0);
        if (ZYPCommonFunc.hasValue(String.valueOf(rsTran))) {
            countTran = rsTran;
        }
        if (countTran.compareTo(new Integer(0)) > 0 || countInvty.compareTo(BigDecimal.ZERO) > 0) {
            List<Map<String, String>> coaAcctAfMapList = getCoaAcct(wrkTMsg.coaMdseTpCd.getValue());
            List<Map<String, String>> coaAcctBfMapList = getCoaAcct(mdseTMsg.coaMdseTpCd.getValue());
            if (coaAcctAfMapList != null && coaAcctBfMapList != null && coaAcctAfMapList.size() == coaAcctBfMapList.size()) {
                for (int i = 0; i < coaAcctBfMapList.size(); i++) {
                    Map<String, String> coaAcctAfMap = coaAcctAfMapList.get(i);
                    Map<String, String> coaAcctBfMap = coaAcctBfMapList.get(i);
                    if (!(ZYPCommonFunc.hasValue(coaAcctBfMap.get("COA_PROJ_ACCT_TP_CD")) && ZYPCommonFunc.hasValue(coaAcctBfMap.get("COA_ACCT_CD")) && coaAcctBfMap.get("COA_PROJ_ACCT_TP_CD").equals(coaAcctAfMap.get("COA_PROJ_ACCT_TP_CD")) && coaAcctBfMap
                            .get("COA_ACCT_CD").equals(coaAcctAfMap.get("COA_ACCT_CD")))) {
                        return true;
                    }
                }
            } else {
                // Different size or not get
                return true;
            }
        }
        return false;
    }

    private List<Map<String, Object>> getMdseTpValSetTMsg(String globalCompanyCode, String mdseTpCtxTpCd, String mdseItemTpCd, String coaMdseTpCd) {

        if (!ZYPCommonFunc.hasValue(mdseItemTpCd) || !ZYPCommonFunc.hasValue(coaMdseTpCd)) {
            return null;
        }

        List<Map<String, Object>> list = getMdseTpValSet(globalCompanyCode, mdseTpCtxTpCd, mdseItemTpCd, coaMdseTpCd);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    private Map<String, Object> getFrthProdHrch(ITEM_HDR_UPLD_WRKTMsg wrkTMsg, String globalCompanyCode) {
        List<Map<String, Object>> list = getProdCtrl(wrkTMsg.frthProdCtrlCd.getValue(), MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4, globalCompanyCode);
        if (list != null && !list.isEmpty()) {
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    if (map != null && map.get("PROD_CTRL_CD") != null) {
                        return map;
                    }
                }
            }
        }
        return null;
    }

    private Map<String, Object> getSlsMatGrp(String globalCompanyCode, String slsMatGrpCd, String slsMatGrpSqNum) {
        Map<String, Object> list = getSlsMatGrpQuery(globalCompanyCode, slsMatGrpCd, slsMatGrpSqNum);
        if ((list != null) && (!list.isEmpty())) {
            return list;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, String>> getCoaAcct(String mdseTpCd) {
        Map<String, Object> ssmParamCheckOrderReturn = new HashMap<String, Object>();
        ssmParamCheckOrderReturn.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParamCheckOrderReturn.put("invtyAcctFlgY", ZYPConstant.FLG_ON_Y);
        ssmParamCheckOrderReturn.put("projAcctTpCrRibl", COA_PROJ_ACCT_TP.CR_RBL);
        ssmParamCheckOrderReturn.put("mdseTpCd", mdseTpCd);
        List<Map<String, String>> list = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getCoaAcct", ssmParamCheckOrderReturn);
        if ((list != null) && (!list.isEmpty())) {
            return list;
        } else {
            return null;
        }
    }

    private BigDecimal getInventoryCount(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("getInventoryCount", params);
        if (ZYPCommonFunc.hasValue(cnt)) {
            return BigDecimal.ZERO;
        }
        return cnt;
    }

    private Integer getTransactionDataCPOAndPO(String glblCmpyCd, String mdseCd) {
        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        if (mdseCd.length() > 8) {
            cond.put("mdseCd8Digit", mdseCd.substring(0, 8));
        }
        cond.put("ordLineStsCd", new String[] {ORD_LINE_STS.CLOSED, ORD_LINE_STS.CANCELLED });
        cond.put("rtrnLineStsCd", new String[] {RTRN_LINE_STS.CLOSED, RTRN_LINE_STS.CANCELLED });
        cond.put("poStsCd", new String[] {PO_STS.CLOSED, PO_STS.CANCELLED });
        return (Integer) this.ssmBatchClient.queryObject("getTransactionDataCPOAndPO", cond);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMdseTpValSet(String glblCmpyCd, String mdseTpCtxTpCd, String mdseItemTpCd, String coaMdseTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseTpCtxTpCd", mdseTpCtxTpCd);
        ssmParam.put("mdseItemTpCd", mdseItemTpCd);
        ssmParam.put("coaMdseTpCd", coaMdseTpCd);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMdseTpValSet", ssmParam);
        if ((list != null) && (!list.isEmpty())) {
            return list;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getIntgProdCatgConvPriorityList(ITEM_HDR_UPLD_WRKTMsg wrkTMsg, MDSETMsg mdseTMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(mdseTMsg.mdseItemTpCd)) {
            ssmParam.put("mdseItemTpCd", mdseTMsg.mdseItemTpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(wrkTMsg.coaMdseTpCd)) {
            ssmParam.put("coaMdseTpCd", wrkTMsg.coaMdseTpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(mdseTMsg.swLicCtrlTpCd)) {
            ssmParam.put("swLicCtrlTpCd", mdseTMsg.swLicCtrlTpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(mdseTMsg.swCatgCd)) {
            ssmParam.put("swCatgCd", mdseTMsg.swCatgCd.getValue());
        }
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getIntgProdCatgConvPriorityList", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSlsMatGrpQuery(String glblCmpyCd, String slsMatGrpCd, String slsMatGrpSqNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsMatGrpCd", slsMatGrpCd);
        ssmParam.put("slsMatGrpSqNum", slsMatGrpSqNum);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSlsMatGrp", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getProdCtrl(String prodCtrlCd, String mdseStruElmntTpCd, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prodCtrlCd", prodCtrlCd);
        ssmParam.put("mdseStruElmntTpCd", mdseStruElmntTpCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getProdCtrl", ssmParam);
    }
}
