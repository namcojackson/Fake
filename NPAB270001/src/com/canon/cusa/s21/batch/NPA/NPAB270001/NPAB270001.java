package com.canon.cusa.s21.batch.NPA.NPAB270001;

import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_GROUP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_GROUP_KEY_CC;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_GROUP_KEY_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_GROUP_KEY_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_VAR_ERROR;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MAIL_VAR_UPDATE;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MSG_ID_NPAM1173E;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.MSG_ID_NPAM1288E;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.NPZM0254E;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.NPZM0255E;
import static com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.NPZM0309W;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDValidatorException;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ASL_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;
import business.parts.NPZC115001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC115001.NPZC115001;
import com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant;
import com.canon.cusa.s21.batch.NPA.NPAB270001.constant.NPAB270001Constant.Select;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB270001:CUSA Parts Cost Update IF for Venlo/Poing
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/11/07   CITS            K.Ogino         Create          QC#54157
 * 2019/12/20   CITS            K.Ogino         Update          QC#55168
 * 2020/01/16   CITS            K.Ogino         Update          QC#55470
 *</pre>
 */

public class NPAB270001 extends S21BatchMain {

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Batch Date */
    private String batchDate = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** total correct count */
    private int totalNormalCount = 0;

    /** total Execute count */
    private int totalCount = 0;

    /** total error count */
    private int totalErrorCount = 0;

    /** Termination code */
    private TERM_CD termCd = null;

    /** errorList */
    private List<Map<String, Object>> errorList = new ArrayList<Map<String, Object>>();

    /** ASL_HDR ASL_NM */
    private String addTargetAslNm = "";

    /** VND_NM */
    private String addTargetVndNm = "";

    /** ASL_HDR Info */
    private Map<String, Object> aslHdrMap = null;

    /** Line Separator */
    private String lineSep = System.getProperty("line.separator");

    /** Cost update Group Text List */
    private LinkedList<String> costUpdTextList = new LinkedList<String>();

    // QC#55168
    private List<String> updateAslVndCdList = new ArrayList<String>();

    /** assetErrorList */
    private List<Map<String, Object>> assetErrorList = new ArrayList<Map<String, Object>>();

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NPAB270001().executeBatch(NPAB270001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // set termCd nomal
        this.termCd = TERM_CD.NORMAL_END;

        // Initialization
        this.profileService = S21UserProfileServiceFactory.getInstance().getService();

        // ssmBatchClient
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // ssmLowLevelClient
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get the Global Company Code.
        this.globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(MSG_ID_NPAM1173E, new String[] {"Global Company Code" });
        }

        // Get salesDate.
        this.batchDate = ZYPDateUtil.getBatProcDate(this.globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(batchDate)) {
            throw new S21AbendException(MSG_ID_NPAM1173E, new String[] {"Batch Date" });
        }

        // Get ASL Name
        this.addTargetAslNm = ZYPCodeDataUtil.getVarCharConstValue("NPAB270001_TARGET_ASL_NM", this.globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(this.addTargetAslNm)) {
            this.addTargetAslNm = "CANON USA";
        }

        // Get ASL Name
        this.addTargetVndNm = ZYPCodeDataUtil.getVarCharConstValue("NPAB270001_TARGET_VND_NM", this.globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(this.addTargetAslNm)) {
            this.addTargetVndNm = "PARTS900335";
        }

        // set ASL_HDR_PK
        getAslHdrPk();
        if (this.aslHdrMap == null || this.aslHdrMap.isEmpty()) {
            throw new S21AbendException(MSG_ID_NPAM1288E, new String[] {"ASL_HDR", "ASL_NM", this.addTargetAslNm });
        }

        // QC#55168
        String constVal = ZYPCodeDataUtil.getVarCharConstValue("NPAB270001_UPDATE_VND_CD", this.globalCompanyCode);
        if (ZYPCommonFunc.hasValue(constVal)) {
            this.updateAslVndCdList = Arrays.asList(constVal.split(","));
        } else {
            this.updateAslVndCdList.add("0000900335");
        }
    }

    // QC#55168 Start
    protected void mainRoutine() {

        // Target CUSA_PRT_COST_V.CD_PR_SYSTEM_PARTS = '1'
        stdCostUpd();
        // Target CUSA_PRT_COST_V.CD_PR_SYSTEM_PARTS = '2'
        assetRecovCostUpdate();

    }

    private void stdCostUpd() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            Map<String, Object> ssmParamMap = new HashMap<String, Object>();
            ssmParamMap.put("glblCmpyCd", this.globalCompanyCode);
            ssmParamMap.put("targetDate", this.batchDate);
            ssmParamMap.put("aslEffFromDt", this.batchDate);
            ssmParamMap.put("aslEffThruDt", this.batchDate);
            // New Entry Parameter
            ssmParamMap.put("aslHdrPk", (BigDecimal) this.aslHdrMap.get(Select.ASL_HDR_PK.toString()));
            ssmParamMap.put("prntVndCd", (String) this.aslHdrMap.get(Select.PRNT_VND_CD.toString()));
            ssmParamMap.put("aslNm", this.addTargetAslNm);
            ssmParamMap.put("vndCd", (String) this.aslHdrMap.get(Select.VND_CD.toString()));
            // QC#55168
            ssmParamMap.put("partsCd", "1"); // Standard Cost
            ssmParamMap.put("updateVndCdList", updateAslVndCdList);
            // QC#55470
            ssmParamMap.put("aslStartDt", this.aslHdrMap.get(Select.ASL_START_DT.toString()));

            // Get Target Data
            ps = this.ssmLLClient.createPreparedStatement("getCostUpdateTarget", ssmParamMap, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {

                boolean ret = false;
                this.totalCount++;
                try {
                    // ASL
                    if (ZYPCommonFunc.hasValue(getBigDecimal(rs, Select.ASL_DTL_PK))) {
                        // Update Cost(ASL_DTL / MDSE)
                        ret = costUpdate(rs);
                    } else {
                        // Target Item Primary Flag List
                        List<ASL_DTLTMsg> aslPrimaryList = getPrimaryItem(getString(rs, Select.CD_PARTS));

                        // Create ASL
                        ret = aslEntry(rs);
                        // Update Cost(ASL_DTL / MDSE)
                        if (ret) {
                            ret = costUpdate(rs);
                        }

                        // Primary Flag Change
                        if (ret && aslPrimaryList != null && !aslPrimaryList.isEmpty()) {
                            for (ASL_DTLTMsg tMsg : aslPrimaryList) {

                                ASL_DTLTMsg aslDtlTMsg = new ASL_DTLTMsg();
                                ZYPEZDItemValueSetter.setValue(aslDtlTMsg.glblCmpyCd, this.globalCompanyCode);
                                ZYPEZDItemValueSetter.setValue(aslDtlTMsg.aslDtlPk, tMsg.aslDtlPk);

                                aslDtlTMsg = (ASL_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(aslDtlTMsg);

                                if (aslDtlTMsg == null) {
                                    continue;
                                }

                                ZYPEZDItemValueSetter.setValue(aslDtlTMsg.primSplyFlg, ZYPConstant.FLG_OFF_N);
                                EZDTBLAccessor.update(aslDtlTMsg);

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslDtlTMsg.getReturnCode())) {
                                    continue;
                                }

                            }
                        }
                    }

                } catch (EZDAbendException abendEx) {
                    // Digit Overflow
                    if (abendEx.getRootCause() instanceof EZDValidatorException) {
                        ret = false;
                        abendEx.printStackTrace();
                        Map<String, Object> infoMap = new HashMap<String, Object>();
                        infoMap.put(Select.ASL_NM.toString(), getString(rs, Select.ASL_NM));
                        infoMap.put(Select.CD_PARTS.toString(), getString(rs, Select.CD_PARTS));
                        infoMap.put(Select.PR_SALES_PARTS1_TR.toString(), getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
                        infoMap.put(Select.UNIT_PRC_AMT.toString(), getBigDecimal(rs, Select.UNIT_PRC_AMT));
                        infoMap.put(Select.THIS_MTH_TOT_STD_COST_AMT.toString(), getBigDecimal(rs, Select.THIS_MTH_TOT_STD_COST_AMT));
                        infoMap.put(ERR_MSG, abendEx.getCause().getLocalizedMessage());
                        errorList.add(infoMap);
                    } else {
                        throw abendEx;
                    }
                }

                if (ret) {
                    this.totalNormalCount++;
                    commit();
                } else {
                    this.totalErrorCount++;
                    rollback();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    // QC#55168
    private void assetRecovCostUpdate() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        String assetRecoveryCostTxt = "ARV UPDATE " + this.batchDate;

        try {

            Map<String, Object> ssmParamMap = new HashMap<String, Object>();
            ssmParamMap.put("glblCmpyCd", this.globalCompanyCode);
            ssmParamMap.put("targetDate", this.batchDate);
            ssmParamMap.put("aslEffFromDt", this.batchDate);
            ssmParamMap.put("aslEffThruDt", this.batchDate);
            ssmParamMap.put("partsCd", "2"); // Asset Recov Cost
            ssmParamMap.put("updateVndCdList", updateAslVndCdList);

            // Get Target Data
            ps = this.ssmLLClient.createPreparedStatement("getAssetRecovCostUpdateTarget", ssmParamMap, execParam);
            rs = ps.executeQuery();

            BigDecimal mdseCstUpdHistHdrPk = null;
            boolean isFirst = true;

            while (rs.next()) {

                boolean ret = true;
                this.totalCount++;
                try {

                    if (!costUpdTextList.contains(assetRecoveryCostTxt)) {
                        costUpdTextList.add(assetRecoveryCostTxt);
                    }

                    if (rs.isFirst() || isFirst) {
                        isFirst = false;
                        // ARV Pending Request
                        // MDSE Cost Update History Header
                        MDSE_CST_UPD_HIST_HDRTMsg mdseCstHdr = new MDSE_CST_UPD_HIST_HDRTMsg();
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.glblCmpyCd, this.globalCompanyCode);

                        // Get PK
                        mdseCstUpdHistHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal("MDSE_CST_UPD_HIST_HDR_SQ");
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdTpCd, MDSE_CST_UPD_TP.ARV_PENDING);
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdGrpTxt, assetRecoveryCostTxt);
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdDescTxt, assetRecoveryCostTxt);
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdRefTxt, "CUSA (Venlo/Poing)");
                        String bizAppId = EZDDBCICarrier.getUppgID().substring(0, 8);
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdCratPsnCd, bizAppId);
                        ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdCratDt, this.batchDate);

                        S21ApiTBLAccessor.create(mdseCstHdr);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseCstHdr.getReturnCode())) {
                            // Error
                            isFirst = true;
                            Map<String, Object> infoMap = new HashMap<String, Object>();
                            infoMap.put(Select.CD_PARTS.toString(), getString(rs, Select.CD_PARTS));
                            infoMap.put(Select.PR_SALES_PARTS1_TR.toString(), getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
                            infoMap.put(Select.ASSET_RECOV_COST_AMT.toString(), getBigDecimal(rs, Select.ASSET_RECOV_COST_AMT));
                            infoMap.put(ERR_MSG, S21MessageFunc.clspGetMessage(NPZM0254E));
                            assetErrorList.add(infoMap);
                            this.totalErrorCount++;
                            rollback();
                            continue;
                        }
                    }

                    // MDSE Cost Update History Detail
                    MDSE_CST_UPD_HIST_DTLTMsg mdseCstDtl = new MDSE_CST_UPD_HIST_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.glblCmpyCd, this.globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCd, getString(rs, Select.CD_PARTS));
                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.rqstTotStdCostAmt, getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));

                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.pkgUomCd, PKG_UOM.EACH);
                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCstUpdEffFromDt, this.batchDate);
                    ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCstUpdStsCd, MDSE_CST_UPD_STS.PENDING);
                    S21ApiTBLAccessor.create(mdseCstDtl);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseCstDtl.getReturnCode())) {
                        // Error
                        Map<String, Object> infoMap = new HashMap<String, Object>();
                        infoMap.put(Select.CD_PARTS.toString(), getString(rs, Select.CD_PARTS));
                        infoMap.put(Select.PR_SALES_PARTS1_TR.toString(), getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
                        infoMap.put(Select.ASSET_RECOV_COST_AMT.toString(), getBigDecimal(rs, Select.ASSET_RECOV_COST_AMT));
                        infoMap.put(ERR_MSG, S21MessageFunc.clspGetMessage(NPZM0255E));
                        assetErrorList.add(infoMap);
                        this.totalErrorCount++;
                        rollback();
                        continue;
                    }

                    ret = true;

                } catch (EZDAbendException abendEx) {
                    // Digit Overflow
                    if (abendEx.getRootCause() instanceof EZDValidatorException) {
                        ret = false;
                        abendEx.printStackTrace();
                        Map<String, Object> infoMap = new HashMap<String, Object>();
                        infoMap.put(Select.CD_PARTS.toString(), getString(rs, Select.CD_PARTS));
                        infoMap.put(Select.PR_SALES_PARTS1_TR.toString(), getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
                        infoMap.put(Select.ASSET_RECOV_COST_AMT.toString(), getBigDecimal(rs, Select.ASSET_RECOV_COST_AMT));
                        infoMap.put(ERR_MSG, abendEx.getCause().getLocalizedMessage());
                        assetErrorList.add(infoMap);
                    } else {
                        throw abendEx;
                    }
                }

                if (ret) {
                    this.totalNormalCount++;
                    commit();
                } else {
                    this.totalErrorCount++;
                    rollback();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {

        // Cost Update Mail
        if (!errorList.isEmpty() || !this.costUpdTextList.isEmpty() || !this.assetErrorList.isEmpty()) {
            sendMail();
        }

        setTermState(this.termCd, this.totalNormalCount, this.totalErrorCount, this.totalCount);
    }

    private void getAslHdrPk() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.globalCompanyCode);
        ssmParam.put("aslNm", this.addTargetAslNm);
        ssmParam.put("vndNm", this.addTargetVndNm);
        ssmParam.put("aslEffFromDt", this.batchDate);
        ssmParam.put("aslEffThruDt", this.batchDate);

        this.aslHdrMap = (Map<String, Object>) ssmBatchClient.queryObject("findAslHdrPk", ssmParam);
    }

    private boolean aslEntry(ResultSet rs) throws SQLException {

        NPZC115001PMsg pMsg = new NPZC115001PMsg();
        pMsg.xxAslDtlList.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.ENTRY_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.batchDate);
        ZYPEZDItemValueSetter.setValue(pMsg.aslHdrPk, getBigDecimal(rs, Select.ASL_HDR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, getString(rs, Select.PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.aslNm, getString(rs, Select.ASL_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.aslDescTxt, getString(rs, Select.ASL_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.aslStartDt, getString(rs, Select.ASL_START_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.aslEndDt, getString(rs, Select.ASL_END_DT));

        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCd, getString(rs, Select.CD_PARTS));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndUomCd, VND_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).splyItemNum, getString(rs, Select.CD_PARTS));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndCd, getString(rs, Select.VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).primSplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).unitPrcAmt, getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
        // QC#55470
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).effFromDt, this.batchDate);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).effThruDt, getString(rs, Select.ASL_END_DT));

        new NPZC115001().execute(pMsg, ONBATCH_TYPE.BATCH);

        return apiResult(pMsg, getInfoMap(rs, pMsg));
    }

    private boolean costUpdate(ResultSet rs) throws SQLException {

        NPZC115001PMsg pMsg = new NPZC115001PMsg();

        pMsg.xxAslDtlList.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.COST_UPDATE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.batchDate);
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, getString(rs, Select.PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.aslNm, getString(rs, Select.ASL_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).splyItemNum, getString(rs, Select.CD_PARTS));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndCd, getString(rs, Select.VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).unitPrcAmt, getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        String aslNm = pMsg.aslNm.getValue();
        if (ZYPCommonFunc.hasValue(aslNm) && aslNm.length() > 41) {
            aslNm = aslNm.substring(0, 41);
        }
        aslNm = aslNm + " " + this.batchDate;

        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdGrpTxt, aslNm);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdDescTxt, aslNm);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdRefTxt, "CUSA (Venlo/Poing)");

        if (!costUpdTextList.contains(aslNm)) {
            costUpdTextList.add(aslNm);
        }

        // Call PO Update API
        new NPZC115001().execute(pMsg, ONBATCH_TYPE.BATCH);

        return apiResult(pMsg, getInfoMap(rs, pMsg));
    }

    private static String getString(ResultSet rs, Select colNm) throws SQLException {
        return rs.getString(colNm.toString());
    }

    private static BigDecimal getBigDecimal(ResultSet rs, Select colNm) throws SQLException {
        return rs.getBigDecimal(colNm.toString());
    }

    private boolean apiResult(NPZC115001PMsg pMsg, Map<String, Object> infoMap) {

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                if (xxMsgId.endsWith("E")) {
                    infoMap.put(ERR_MSG, S21MessageFunc.clspGetMessage(xxMsgId));
                    errorList.add(infoMap);
                    return false;
                } else if (xxMsgId.endsWith("W") && !NPZM0309W.equals(xxMsgId)) {
                    infoMap.put(ERR_MSG, S21MessageFunc.clspGetMessage(xxMsgId));
                    errorList.add(infoMap);
                } else {
                    // Info message. no process.
                    continue;
                }
            }
        }

        // if (isUpdate) {
        // // no process.
        // // updateList.add(infoMap);
        // }

        return true;
    }

    private Map<String, Object> getInfoMap(ResultSet rs, NPZC115001PMsg pMsg) throws SQLException {

        Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put(Select.ASL_NM.toString(), getString(rs, Select.ASL_NM));
        infoMap.put(Select.CD_PARTS.toString(), getString(rs, Select.CD_PARTS));
        infoMap.put(Select.PR_SALES_PARTS1_TR.toString(), getBigDecimal(rs, Select.PR_SALES_PARTS1_TR));
        infoMap.put(Select.UNIT_PRC_AMT.toString(), getBigDecimal(rs, Select.UNIT_PRC_AMT));
        infoMap.put(Select.THIS_MTH_TOT_STD_COST_AMT.toString(), getBigDecimal(rs, Select.THIS_MTH_TOT_STD_COST_AMT));

        return infoMap;
    }

    private void sendMail() {

        // *********************************************************
        // Mail Address
        // *********************************************************
        // mail Init
        S21Mail mail = new S21Mail(this.globalCompanyCode);

        // Get Mail Address
        S21MailGroup groupFrom = new S21MailGroup(this.globalCompanyCode, MAIL_GROUP_ID);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);

        S21MailGroup groupTo = new S21MailGroup(this.globalCompanyCode, MAIL_GROUP_ID);
        groupTo.setMailKey1(MAIL_GROUP_KEY_TO);

        S21MailGroup groupCc = new S21MailGroup(this.globalCompanyCode, MAIL_GROUP_ID);
        groupCc.setMailKey1(MAIL_GROUP_KEY_CC);

        // Set address FROM
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        // Set address TO
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        mail.setToAddressList(toAddrList);

        // Set address CC
        List<S21MailAddress> ccAddrList = groupCc.getMailAddress();
        mail.setCcAddressList(ccAddrList);

        // S21MailTemplate
        S21MailTemplate template = new S21MailTemplate(this.globalCompanyCode, MAIL_TEMPLATE_ID);

        // *********************************************************
        // mail body
        // *********************************************************
        // template.setTemplateParameter(MAIL_VAR_UPDATE,
        // addMailMessage(this.updateList, false));
        StringBuilder sb = new StringBuilder();
        for (String grpTxt : this.costUpdTextList) {
            sb.append(grpTxt);
            sb.append(lineSep);
        }
        template.setTemplateParameter(MAIL_VAR_UPDATE, sb.toString());
        // QC#55168
        sb = addMailMessage(null, this.errorList, true);
        sb = addMailMessage(sb, this.assetErrorList, false);

        template.setTemplateParameter(MAIL_VAR_ERROR, sb.toString());

        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }

    // QC#55168
    private StringBuilder addMailMessage(StringBuilder sb, List<Map<String, Object>> mailList, boolean isAsl) {

        if (sb == null) {
            sb = new StringBuilder();
        }

        String preAslName = "";
        if (!isAsl && !assetErrorList.isEmpty()) {
            sb.append("MDSE Code (Old Asset recovery cost / New Asset recovery cost)");
        }

        for (Map<String, Object> msgMap : mailList) {

            if (isAsl) {
                String aslName = (String) msgMap.get(Select.ASL_NM.toString());
                if (!ZYPCommonFunc.hasValue(aslName)) {
                    aslName = this.addTargetAslNm;
                }

                if (!aslName.equals(preAslName)) {
                    sb.append(lineSep);
                    sb.append("ASL Name : " + aslName);
                    sb.append(lineSep);
                    sb.append("MDSE Code (Old Cost / New Cost) : Error Message");
                    sb.append(lineSep);
                }

                sb.append(msgMap.get(Select.CD_PARTS.toString()));
                sb.append(" (" + msgMap.get(Select.THIS_MTH_TOT_STD_COST_AMT.toString()) + " / " + msgMap.get(Select.PR_SALES_PARTS1_TR.toString()) + ")");
                sb.append(" : " + msgMap.get(ERR_MSG));
                sb.append(lineSep);

                preAslName = aslName;
            } else {
                sb.append(lineSep);
                sb.append(msgMap.get(Select.CD_PARTS.toString()));
                sb.append(" (" + msgMap.get(Select.ASSET_RECOV_COST_AMT.toString()) + " / " + msgMap.get(Select.PR_SALES_PARTS1_TR.toString()) + ")");
            }
        }

        return sb;
    }

    private List<ASL_DTLTMsg> getPrimaryItem(String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.globalCompanyCode);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);

        return (List<ASL_DTLTMsg>) ssmBatchClient.queryObjectList("getPrimSplyFlg", ssmParam);
    }
}
