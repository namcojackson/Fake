/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB108001;

import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_EFF_FROM_DT_DEFAULT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_IDX_1;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_INTFC_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_PRE_INTFC_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_RGTN_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.BIND_VND_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.DSCTN_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.EFF_FROM_DT_DEFALUT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.EZINTIME;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.IDX_1;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.INTFC_CCY_CD_DEFALUT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.INTFC_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.INTFC_PRT_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.INTFC_PRT_SALE_DT_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.KEY_INTFC_CCY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.MDSE_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.NPAM1342E;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.PRT_MAIN_PROD_CD_DEFALUT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.PRT_MAIN_PROD_CTRL_LAYER_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.PRT_SIZE_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.P_CODE_PROD_LINE_VENDOR;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.STMT_GET_MDSE_DATA_FIND_BY_INTFC_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.STMT_GET_MDSE_INFO;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.STMT_GET_PAST_DATA;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.STMT_GET_PRE_INTFC_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.TBL_PRT_MSTR_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.THIS_MTH_TOT_STD_COST_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.VALUE_CINC_PROD_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.VALUE_PRT_ASSET_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.VND_INCR_ORD_QTY;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.VND_MIN_ORD_QTY;
import static com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant.ZZM9000E;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import business.db.PRT_MSTR_WRKTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.batch.NMA.NMAB108001.constant.NMAB108001Constant;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * S-Cube Parts Master Info to CINC (WWABF301/311) (Work Create)<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/13/2013   Hitachi         K.Kasai         Created         Created
 * 06/23/2016   CITS            N.Akaishi       Update          V1.0
 * 03/28/2019   CITS            T.Tokutomi      Update          QC#30963
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * </pre>
 */
public class NMAB108001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String batDt;

    /** Commit Number */
    private int commitCount;

    /** total commit count */
    private int totalCommitCount;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** INTFC_CCY_CD */
    private String intfcCcyCd = null;

    /** Part Main Production Control Number */
    private BigDecimal prtMainProdCtrlNum = null;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE});
        }

        // Get PROD_CTRL_NUM
        prtMainProdCtrlNum = ZYPCodeDataUtil.getNumConstValue(PRT_MAIN_PROD_CTRL_LAYER_NUM, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(prtMainProdCtrlNum)) {
            prtMainProdCtrlNum = PRT_MAIN_PROD_CD_DEFALUT;
        }

        // Get the Batch Mode
        this.commitCount = getCommitCount();
        //get Batch Process Date
        this.batDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {
            this.commitCount = MAX_COMMIT_NUMBER;
        }
        this.totalCommitCount = 0;

        // Get INTFC_CCY_CD
        this.intfcCcyCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_INTFC_CCY_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.intfcCcyCd)) {
            this.intfcCcyCd = INTFC_CCY_CD_DEFALUT;
        }
    }

    /**
     * Main process
     */
    @Override
    protected void mainRoutine() {

        //delete PRT_MSTR_WRK data(For rerun)
        deletePartsDataOfBatDt(this.batDt);

        //insert PartsData into PRT_MSTR_WRK
        insertPartsData();

        //delete PRT_MSTR_WRK data(Past Date)
        deletePartsDataOfPastDt();

    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     *  main process
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NMAB108001().executeBatch(NMAB108001.class.getSimpleName());
    }

    /**
     * deletePartsDataOfBatDt
     * @param procDt String 
     */
    private void deletePartsDataOfBatDt(String procDt) {

        List<PRT_MSTR_WRKTMsg> inTMsgList = new ArrayList<PRT_MSTR_WRKTMsg>();

        //get partsData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> paramMap = setParam();
        paramMap.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(BIND_INTFC_CRAT_DT, procDt);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(STMT_GET_MDSE_DATA_FIND_BY_INTFC_CRAT_DT, paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int totalDeleteCount = 0;
            //delete partsData of batDt
            while (rs.next()) {
                inputCount++;
                inTMsgList.add(setDeleteValue(rs));
                if (this.commitCount == inTMsgList.size()) {
                    deletePartsDataFindByIntfcCratDt(inTMsgList);
                    inTMsgList.clear();
                    commit();
                    totalDeleteCount += this.commitCount;
                }
            }
            if (inTMsgList.size() > 0) {
                deletePartsDataFindByIntfcCratDt(inTMsgList);
                inTMsgList.clear();
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * insertPartsData
     */
    private void insertPartsData() {

        List<PRT_MSTR_WRKTMsg> inTMsgList = new ArrayList<PRT_MSTR_WRKTMsg>();

        //get parts Data
        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> paramMap = setParam();

        try {
            stmt = this.ssmLLClient.createPreparedStatement(STMT_GET_MDSE_INFO, paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int insertCount = 0;
            while (rs.next()) {
                inputCount++;
                inTMsgList.add(setCreateValue(rs));
                if (this.commitCount == inTMsgList.size()) {
                    insertCount = insertPartsData(inTMsgList);
                    commit();
                    inTMsgList.clear();
                    this.totalCommitCount += insertCount;
                    insertCount = 0;
                }
            }
            if (inTMsgList.size() > 0) {
                insertCount = insertPartsData(inTMsgList);
                commit();
                inTMsgList.clear();
                this.totalCommitCount += insertCount;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * insertPartsData
     * @param inMsgLst List<PRT_MSTR_WRKTMsg>
     */
    private int insertPartsData(List<PRT_MSTR_WRKTMsg> inMsgLst) {
        PRT_MSTR_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_MSTR_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NMAM0176E, new String[] {TBL_PRT_MSTR_WRK});
        }

        return insertCount;
    }

    /**
     * setParam
     * @return List<PRT_MSTR_WRKTMsg>
     */
    private Map<String, Object> setParam() {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        List<String> rgtnStsCdList = new ArrayList<String>();
        rgtnStsCdList.add(RGTN_STS.TEMPORARILY_SAVED);
        rgtnStsCdList.add(RGTN_STS.PENDING_COMPLETION);
        rgtnStsCdList.add(RGTN_STS.TERMINATED);

        List<String> scubeVndSysTpCdList = getVarCharConstValueList(NMAB108001Constant.VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD);
        paramMap.put(BIND_VND_SYS_TP_CD, scubeVndSysTpCdList);
        paramMap.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEFALUT);
        paramMap.put(BIND_IDX_1, IDX_1);
        paramMap.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        paramMap.put(BIND_RGTN_STS_CD_LIST, rgtnStsCdList);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            paramMap.put("exclMdseCdList", exclMdseList);
        }

        return paramMap;
    }

    /**
     * setCreateValue
     * @param rs ResultSet
     * @return PRT_MSTR_WRKTMsg 
     */
    private PRT_MSTR_WRKTMsg setCreateValue(ResultSet rs) {

        PRT_MSTR_WRKTMsg inParam = new PRT_MSTR_WRKTMsg();

        try {

            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);

            NMXC104001ConvertPartsMdseCdBean convMdseCdBean = new NMXC104001ConvertPartsMdseCdBean();
            // QC#30863 Add glblCmpyCd.
            convMdseCdBean.setGlblCmpyCd(this.glblCmpyCd);
            convMdseCdBean.setMode(NMXC104001ConvertPartsMdseCd.MODE_MDSE);
            convMdseCdBean.setMdseCd(rs.getString(MDSE_CD));
            convMdseCdBean.setCusaVndCd(rs.getString(CUSA_VND_CD));
            NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(convMdseCdBean);
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(inParam.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            String prodCtrlCd =  rs.getString(NMAB108001Constant.PROD_CTRL_CD_ARRY[prtMainProdCtrlNum.intValue()]);
            ZYPEZDItemValueSetter.setValue(inParam.prtMainProdCd, convertPrtMainProdCd(prodCtrlCd));

            if (ZYPCommonFunc.hasValue(rs.getString(P_CODE_PROD_LINE_VENDOR))) {
                ZYPEZDItemValueSetter.setValue(inParam.cincProdCd, rs.getString(P_CODE_PROD_LINE_VENDOR));
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.cincProdCd, VALUE_CINC_PROD_CD);
            }

            ZYPEZDItemValueSetter.setValue(inParam.prtDescTxt, rs.getString(MDSE_NM));
            ZYPEZDItemValueSetter.setValue(inParam.prtRusCd, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(inParam.prtVndDropFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inParam.prtAssetCd, VALUE_PRT_ASSET_CD);
            ZYPEZDItemValueSetter.setValue(inParam.dsctnFlg, rs.getString(DSCTN_FLG));
            ZYPEZDItemValueSetter.setValue(inParam.prtYfiFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inParam.prtDelFlg, rs.getString(DSCTN_FLG));
            ZYPEZDItemValueSetter.setValue(inParam.intfcStdCostTxt, rs.getString(THIS_MTH_TOT_STD_COST_AMT));
            ZYPEZDItemValueSetter.setValue(inParam.intfcWrtDownCostTxt, rs.getString(THIS_MTH_TOT_STD_COST_AMT));

            ZYPEZDItemValueSetter.setValue(inParam.intfcCcyCd, this.intfcCcyCd);

            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtSpecWt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inParam.intfcGlblVndCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(inParam.intfcLocalVndCd, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(inParam.intfcPackSnpQty, setQty(rs.getBigDecimal(VND_INCR_ORD_QTY)));
            ZYPEZDItemValueSetter.setValue(inParam.intfcSubDivQty, setQty(rs.getBigDecimal(VND_MIN_ORD_QTY)));
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtSaleDt, rs.getString(EZINTIME).substring(0, INTFC_PRT_SALE_DT_LENGTH));
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtEntryDt, rs.getString(EZINTIME).substring(0, INTFC_PRT_SALE_DT_LENGTH));
            ZYPEZDItemValueSetter.setValue(inParam.intfcCratDt, this.batDt);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    /**
     * setQty
     * @param qty BigDecimal
     * @return BigDecimal 
     */
    private BigDecimal setQty(BigDecimal qty) {
        if (qty == null) {
            return BigDecimal.ONE;
        }
        return qty;
    }

    /**
     * setDeleteValue
     * @param rs ResultSet
     * @return PRT_MSTR_WRKTMsg 
     */
    private PRT_MSTR_WRKTMsg setDeleteValue(ResultSet rs) {
        PRT_MSTR_WRKTMsg inParam = new PRT_MSTR_WRKTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtMdseCd, rs.getString(INTFC_PRT_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inParam.prtSizeTxt, rs.getString(PRT_SIZE_TXT));
            ZYPEZDItemValueSetter.setValue(inParam.intfcCratDt, rs.getString(INTFC_CRAT_DT));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    /**
     * deletePartsDataOfPastDt
     */
    private void deletePartsDataOfPastDt() {

        //get PreIntfcCratDt from PRT_MSTR_WRK
        String preIntfcCratDt = getPreIntfcCratDt();
        if (!ZYPCommonFunc.hasValue(preIntfcCratDt)) {
            return;
        }
        List<PRT_MSTR_WRKTMsg> inTMsgList = new ArrayList<PRT_MSTR_WRKTMsg>();

        //get partsData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(BIND_PRE_INTFC_CRAT_DT, preIntfcCratDt);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(STMT_GET_PAST_DATA, paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int totalDeleteCount = 0;
            while (rs.next()) {
                inputCount++;
                inTMsgList.add(setDeleteValue(rs));
                if (this.commitCount == inTMsgList.size()) {
                    deletePartsDataFindByIntfcCratDt(inTMsgList);
                    commit();
                    totalDeleteCount += this.commitCount;
                }
            }
            if (inTMsgList.size() > 0) {
                deletePartsDataFindByIntfcCratDt(inTMsgList);
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getPreIntfcCratDt
     */
    private String getPreIntfcCratDt() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_INTFC_CRAT_DT, this.batDt);

        return (String) ssmBatchClient.queryObject(STMT_GET_PRE_INTFC_CRAT_DT, ssmParam, execParam);
    }

    /**
     * deletePartsDataFindByIntfcCratDt
     * @param inMsgLst List<PRT_MSTR_WRKTMsg>
     */
    private void deletePartsDataFindByIntfcCratDt(List<PRT_MSTR_WRKTMsg> inMsgLst) {
        PRT_MSTR_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_MSTR_WRKTMsg[inMsgLst.size()];
        int execCnt = S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));
        if (execCnt != inMsgLst.size()) {
            throw new S21AbendException(NPAM1342E, new String[] {TBL_PRT_MSTR_WRK });
        }
    }

    /**
     * getVarCharConstValueList
     * @param varCharConstNm String
     * @return List<String>
     */
    private List<String> getVarCharConstValueList(String varCharConstNm) {

        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(varCharConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);

        varCharConstTMsg = (VAR_CHAR_CONSTTMsg) S21FastTBLAccessor.findByKey(varCharConstTMsg);

        if (null == varCharConstTMsg) {

            return null;

        } else {

            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();

            if (!ZYPCommonFunc.hasValue(varCharConstVal)) {

                return null;

            } else {

                return asList(varCharConstVal.split(","));
            }
        }
    }

    /**
     * convertCincProdCd
     * @param prodCtrlCd String
     * @return String
     */
    private String convertPrtMainProdCd(String prodCtrlCd) {
        if (!ZYPCommonFunc.hasValue(prodCtrlCd)) {
            return prodCtrlCd;
        }
        EZDItemAttribute digitPrtMainProdCdEz = new PRT_MSTR_WRKTMsg().getAttr("prtMainProdCd");
        int digitPrtMainProdCd = digitPrtMainProdCdEz.getDigit();
        if (prodCtrlCd.length() < digitPrtMainProdCd) {
            return prodCtrlCd;
        } else {
            return prodCtrlCd.substring(0, digitPrtMainProdCd);
        }
    }
}
