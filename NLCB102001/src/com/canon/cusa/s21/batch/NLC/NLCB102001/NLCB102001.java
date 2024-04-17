/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB102001;

import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.ASTERISK;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.BIZ_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_DSPL_RSN_CD_01;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_CMPY_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_CMPY_CATG_CD_INTERNAL;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_CMPY_CATG_CD_OTHER;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_ORD_CATG_CD_A1;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_ORD_CATG_CD_BZ;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_ORD_CATG_CD_ZZ;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_CATG_CD_A;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_CATG_CD_B;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_CATG_CD_F;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_CATG_CD_Z1;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_CATG_CD_Z3;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_CATG_CD_Z4;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CINC_GLBL_SHPG_METH_CD_OTHER;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.COMMA;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CPO_CARR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CPO_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.DEFULT_CARR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.DS_COND_CONST_VAL_TXT_04;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.DS_COND_CONST_VAL_TXT_05;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.EFF_FROM_DT_DEFALUT;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.FSR_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_0;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_1;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_10;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_2;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_3;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_4;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_5;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_6;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_7;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_8;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.IDX_9;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.INVTY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.INVTY_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.INVTY_TRX_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.INVTY_TRX_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.INVTY_TRX_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.MSG_ITEM_SHIP_METHODE;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.NLCM0125E;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.NLCM0127W;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.NUM_ONE;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.PRT_CHRG_IND_C;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.PRT_EXCL_INVTY_LOC_CD_CINC;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.PRT_INCL_TECH_INVTY_CINC_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.PRT_INCL_TECH_INVTY_CINC_FLG_DEF;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.SCUBE_IF_CINC_VND_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.SCUBE_IF_CINC_VND_CD_DEF;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.SHIP_FROM_LOC_CUST_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.SHIP_FROM_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.SHPG_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.SHPG_TRD_PTNR_SHPG_METH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.STK_OUT_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.STK_OUT_WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.STR_2;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.TRX_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.TRX_RSN_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_ITASC;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.VAR_CHAR_CONST_KEY_SCUBE_IF_STK_OUT;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.WHT_TO_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.WHT_TO_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.WHT_TO_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.WHT_TO_WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.WO_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.WO_TRD_PTNR_SHPG_METH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB102001.constant.NLCB102001Constant.ZZM9000E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.PRT_STK_OUT_RSLT_WRKTMsg;

import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Stock Out Data of the Day<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 10/22/2013   Hitachi         T.Tomita        Created         MEX-IF017
 * 11/22/2013   Hitachi         T.Tomita        Update          QC3159
 * 12/13/2013   CSAI            Y.Imazu         Update          QC3159
 * 05/23/2016   CITS            R.Shimamoto     Update          V2.0
 * 11/29/2016   CITS            R.Shimamoto     Update          QC#16242
 * 03/30/2018   CITS            Keiichi Masaki  Update          QC#25107
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 09/11/2018   CITS            T.Tokutomi      Update          QC#27589
 * 03/28/2019   CITS            T.Tokutomi      Update          QC#30963
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 02/04/2020   Fujitsu         S.Iidaka        Update          QC#55572
 * </pre>
 */
public class NLCB102001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Batch Date */
    private String batDt;

    /** Commit Number */
    private int commitNumber;

    /** total commit count */
    private int totalCommitCount;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SCUBE I/F CINC Vendor Code */
    private String scubeIfCincVndCd;

    /** Parts Include Technician Inventory CINC Flag */
    private String prtInclTechInvtyCincFlg;

    /** Parts Exclude Inventory Location Code CINC Map */
    private Map<String, String> prtExclInvtyLocCdCincMap;

    /** error message parts */
    private NPXC001001SendMailForErrorInfo sendMailForErrorInfo;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {

            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get the Batch Mode
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {

            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // get Batch Process Date
        this.batDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.totalCommitCount = 0;
        this.scubeIfCincVndCd = getScubeIfCincVndCd();
        this.prtInclTechInvtyCincFlg = getPrtInclTechInvtyCincFlg();
        this.prtExclInvtyLocCdCincMap = getVarCharConstMapData(PRT_EXCL_INVTY_LOC_CD_CINC);
        this.sendMailForErrorInfo = new NPXC001001SendMailForErrorInfo();
    }

    @Override
    protected void mainRoutine() {

        // Delete PRT_STK_OUT_RSLT_WRK data(For rerun)
        deletePrtStkOutRsltWrkOfBatDt();

        // Get Newest Past Work Creation Date
        String preIntfcCratDt = getPrtStkOutRsltWrkCratDt();

        // Insert PartsData into PRT_STK_OUT_RSLT_WRK
        insertPrtStkOutRsltWrkData(preIntfcCratDt);

        // Delete PRT_STK_OUT_RSLT_WRK data(Past Date)
        deletePrtStkOutRsltWrkOfPastDt(preIntfcCratDt);

    }

    @Override
    protected void termRoutine() {

        if (TERM_CD.WARNING_END.equals(termCd)) {

            // send mail
            sendMailForErrorInfo.sendMail(glblCmpyCd, BIZ_ID);
        }

        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLCB102001().executeBatch(NLCB102001.class.getSimpleName());
    }

    /**
     * Get Inventory Location Code of Out of Target
     * @param ket String
     * @return Map<String, String>
     */
    private Map<String, String> getVarCharConstMapData(String key) {

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(key, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(varCharConstVal)) {

            return new HashMap<String, String>();
        }

        String[] itemArray = varCharConstVal.split(COMMA);
        Map<String, String> rtnMap = new HashMap<String, String>();

        for (String item : itemArray) {

            if (!ZYPCommonFunc.hasValue(item)) {

                continue;
            }

            rtnMap.put(item, item);
        }

        return rtnMap;
    }

    /**
     * Get CINC Vendor Code
     * @return String
     */
    private String getScubeIfCincVndCd() {

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(SCUBE_IF_CINC_VND_CD, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(varCharConstVal)) {
            // START 05/30/2016 R.Shimamoto [V2.0 MOD]
            // throw new S21AbendException(ZZZM9026E, new String[]
            // {SCUBE_IF_CINC_VND_CD });
            varCharConstVal = SCUBE_IF_CINC_VND_CD_DEF;
            // END 05/30/2016 R.Shimamoto [V2.0 MOD]
        }

        return varCharConstVal;
    }

    /**
     * Get Tech Inventory Include Flag
     * @return String
     */
    private String getPrtInclTechInvtyCincFlg() {

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(PRT_INCL_TECH_INVTY_CINC_FLG, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(varCharConstVal) || (!ZYPConstant.FLG_ON_Y.equals(varCharConstVal) && !ZYPConstant.FLG_OFF_N.equals(varCharConstVal))) {
            // START 05/30/2016 R.Shimamoto [V2.0 MOD]
            // throw new S21AbendException(ZZZM9026E, new String[]
            // {PRT_INCL_TECH_INVTY_CINC_FLG });
            varCharConstVal = PRT_INCL_TECH_INVTY_CINC_FLG_DEF;
            // END 05/30/2016 R.Shimamoto [V2.0 MOD]
        }

        return varCharConstVal;
    }

    /**
     * Delete Work Data of Batch Date for Re-run
     * @throws SQLException
     */
    private void deletePrtStkOutRsltWrkOfBatDt() {

        List<PRT_STK_OUT_RSLT_WRKTMsg> inTMsgList = new ArrayList<PRT_STK_OUT_RSLT_WRKTMsg>();

        // get partsData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("intfcCratDt", this.batDt);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getPrtStkOutRsltWrkFindByBatDt", paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;

            // delete prtStkOutRsltWrk Data of batDt
            while (rs.next()) {

                inputCount++;
                inTMsgList.add(setDeleteValue(rs));

                if (this.commitNumber == inTMsgList.size()) {

                    deletePrtStkOutRsltWrkFindByCratDt(inTMsgList);
                    inTMsgList.clear();
                }
            }

            if (inTMsgList.size() > 0) {

                deletePrtStkOutRsltWrkFindByCratDt(inTMsgList);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Insert PartsData into PRT_STK_OUT_RSLT_WRK
     * @param preIntfcCratDt String
     * @throws SQLException
     */
    private void insertPrtStkOutRsltWrkData(String preIntfcCratDt) {

        List<PRT_STK_OUT_RSLT_WRKTMsg> inTMsgList = new ArrayList<PRT_STK_OUT_RSLT_WRKTMsg>();

        // get invtyTrx Data
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> paramMap = setParam(preIntfcCratDt);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getInvtyTrx", paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int commitCount = 0;

            PRT_STK_OUT_RSLT_WRKTMsg prtStkOutRsltWrkTMsg;

            while (rs.next()) {

                prtStkOutRsltWrkTMsg = setCreateValue(rs);

                if (prtStkOutRsltWrkTMsg == null) {

                    continue;
                }

                inputCount++;
                inTMsgList.add(prtStkOutRsltWrkTMsg);

                if (this.commitNumber == inTMsgList.size()) {

                    commitCount = insertPrtStkOutRsltWrk(inTMsgList);
                    inTMsgList = new ArrayList<PRT_STK_OUT_RSLT_WRKTMsg>();
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
            }

            if (inputCount != this.totalCommitCount) {

                commitCount = insertPrtStkOutRsltWrk(inTMsgList);
                this.totalCommitCount += commitCount;
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Insert PartsData into PRT_STK_OUT_RSLT_WRK
     * @param inMsgLst List<PRT_STK_OUT_RSLT_WRKTMsg>
     * @return int
     */
    private int insertPrtStkOutRsltWrk(List<PRT_STK_OUT_RSLT_WRKTMsg> inMsgLst) {

        PRT_STK_OUT_RSLT_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_STK_OUT_RSLT_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {

            throw new S21AbendException(NLCM0125E, new String[] {"PRT_STK_OUT_RSLT_WRK" });
        }

        commit();
        return insertCount;
    }

    /**
     * Set Parameter
     * @param preIntfcCratDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setParam(String preIntfcCratDt) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);

        paramMap.put("fromInvtyTrxDt", preIntfcCratDt);
        paramMap.put("toInvtyTrxDt", this.batDt);

        paramMap.put("trxCdSales", TRX.SALES);
        paramMap.put("trxRsnCdRegularSalesWithCost", TRX_RSN.REGULAR_SALES_WITH_COST);
        paramMap.put("trxRsnCdCreditAndRebill", TRX_RSN.CREDIT_AND_REBILL);
        paramMap.put("trxRsnCdReturn", TRX_RSN.RETURN);

        paramMap.put("trxCdMovement", TRX.MOVEMENT);
        paramMap.put("trxRsnCdWHTransferStockOut", TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);
        paramMap.put("trxRsnCdInternalWHTransferStockIn", TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN);

        paramMap.put("trxCdExpenseShipment", TRX.EXPENSE_SHIPMENT);
        paramMap.put("trxRsnCdExpenseShipment", TRX_RSN.EXPENSE_SHIPMENT);
        paramMap.put("trxRsnCdExpenseReturn", TRX_RSN.EXPENSE_RETURN);

        paramMap.put("trxCdAdjustment", TRX.ADJUSTMENT);
        paramMap.put("trxRsnCdAdjustment", TRX_RSN.ADJUSTMENT);
        paramMap.put("trxRsnCdDisposal", TRX_RSN.DISPOSAL);
        paramMap.put("trxRsnCdItemChangeStockOut", TRX_RSN.ITEM_CHANGE_STOCK_OUT);
        paramMap.put("trxRsnCdItemChangeStockIn", TRX_RSN.ITEM_CHANGE_STOCK_IN);
        /* 12/13/2013 CSAI Y.Imazu Add QC3159 START */
        paramMap.put("trxRsnCdVendorTransferStockOut", TRX_RSN.VENDOR_TRANSFER_STOCK_OUT);
        paramMap.put("trxRsnCdVendorTransferStockInfromVendor", TRX_RSN.VENDOR_TRANSFER_STOCK_IN_FROM_VENDOR);
        /* 12/13/2013 CSAI Y.Imazu Add QC3159 END */
        paramMap.put("trxRsnCdItemChangeStockOutfromVendor", TRX_RSN.ITEM_CHANGE_STOCK_OUT_FROM_VENDOR);
        paramMap.put("trxRsnCdItemChangeStockInfromVendor", TRX_RSN.ITEM_CHANGE_STOCK_IN_FROM_VENDOR);

        paramMap.put("trxCdInsuranceClaim", TRX.INSURANCE_CLAIM);
        paramMap.put("trxRsnCdLossOnShipment", TRX_RSN.LOSS_ON_SHIPMENT);
        paramMap.put("trxRsnCdLossOnReceiving", TRX_RSN.LOSS_ON_RECEIVING);

        paramMap.put("trxCdPartsUsage", TRX.PARTS_USAGE);
        paramMap.put("trxRsnCdPartsUsage", TRX_RSN.PARTS_USAGE);
        paramMap.put("trxRsnCdPartsUsageReturn", TRX_RSN.PARTS_USAGE_RETURN);

        paramMap.put("mdseRgtnTpCdS21Parts", MDSE_RGTN_TP.S21_PARTS);

        paramMap.put("mdseRgtnTpCdManual", MDSE_RGTN_TP.MANUAL);
        paramMap.put("mdseCatgCdParts", MDSE_CATG.PARTS);

        paramMap.put("vndCd", this.scubeIfCincVndCd);
        paramMap.put("carrCd", DEFULT_CARR_CD);

        // START 05/27/2016 R.Shimamoto [V2.0 ADD]
        paramMap.put("effFromDtDefalut", EFF_FROM_DT_DEFALUT);
        paramMap.put("numOne", NUM_ONE);
        paramMap.put("dsCondConstGrpId", VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        paramMap.put("cmpyInvtyFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("frtCondCd", ASTERISK);
        paramMap.put("dsCondConstGrpIdStkOut", VAR_CHAR_CONST_KEY_SCUBE_IF_STK_OUT);

        // get vndSysTpCdList
        String vndSysTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD, glblCmpyCd);
        String[] vndSysTpCdList = null;
        if (ZYPCommonFunc.hasValue(vndSysTpCd)) {

            vndSysTpCdList = vndSysTpCd.split(",");
        }

        paramMap.put("vndSysTpCdList", vndSysTpCdList);
        // END 05/27/2016 R.Shimamoto [V2.0 ADD]

        // 11/29/2016 QC#16242 Add.
        paramMap.put("shipFlg", ZYPConstant.FLG_ON_Y);

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            paramMap.put("exclMdseCdList", exclMdseList);
        }

        // Add Start 2020/02/04 QC#55572
        String scubeExclSwhCdList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(scubeExclSwhCdList)) {
            String[] exclSwhCdList = scubeExclSwhCdList.split(",");

            paramMap.put("exclSwhCdList", exclSwhCdList);
        }
        // Add End 2020/02/04 QC#55572

        return paramMap;
    }

    /**
     * Set Create Value
     * @param rs ResultSet
     * @return PRT_STK_OUT_RSLT_WRKTMsg
     * @throws SQLException
     */
    private PRT_STK_OUT_RSLT_WRKTMsg setCreateValue(ResultSet rs) {

        PRT_STK_OUT_RSLT_WRKTMsg resultMsg = null;

        try {

            String trxCd = rs.getString(TRX_CD);
            String trxRsnCd = rs.getString(TRX_RSN_CD);

            if (!ZYPCommonFunc.hasValue(trxCd) || !ZYPCommonFunc.hasValue(trxRsnCd)) {

                return resultMsg;
            }

            // Call Convert Parts MDSE Code
            NMXC104001ConvertPartsMdseCdBean convMdseCdBean = new NMXC104001ConvertPartsMdseCdBean();
            // QC#30963 Add glblCmpyCd
            convMdseCdBean.setGlblCmpyCd(this.glblCmpyCd);
            convMdseCdBean.setMode(NMXC104001ConvertPartsMdseCd.MODE_MDSE);
            convMdseCdBean.setMdseCd(rs.getString(MDSE_CD));
            // START 05/27/2016 R.Shimamoto [V2.0 ADD]
            convMdseCdBean.setCusaVndCd(rs.getString(CUSA_VND_CD));
            // END 05/27/2016 R.Shimamoto [V2.0 ADD]
            NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(convMdseCdBean);

            resultMsg = new PRT_STK_OUT_RSLT_WRKTMsg();

            String fromLocCd;
            String fromLocTpCd;
            String toLocCd;
            String toLocTpCd;

            // Stock Out
            if (isStockOut(trxCd, trxRsnCd)) {

                // START 05/24/2016 R.Shimamoto [V2.0 MOD]
                // fromLocCd = rs.getString("SHIP_FROM_LOC_CUST_CD");
                // fromLocTpCd = rs.getString("SHIP_FROM_LOC_TP_CD");
                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);
                // END 05/24/2016 R.Shimamoto [V2.0 MOD]

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // Credit & Rebill
            } else if (isCreditAndRebill(trxCd, trxRsnCd)) {

                // START 05/24/2016 R.Shimamoto [V2.0 MOD]
                // if (!isTargetInvtyLocCd(rs)) {
                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {
                    // END 05/24/2016 R.Shimamoto [V2.0 MOD]

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // START 05/25/2016 R.Shimamoto [V2.0 ADD]
                // EMSD Shipment Expense
            } else if (isEMSDExpense(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // Loan Shipment Expense
            } else if (isLoanExpense(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // Loan Shipment Drop Shipment
            } else if (isLoanDropShipment(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // Loan Shipment Intangible with Cost
            } else if (isLoanIntangibleWithCost(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // Rental Shipment Expense
            } else if (isRentalShipmentExpense(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);

                if (!isTargetLoc(fromLocCd, fromLocTpCd)) {

                    return null;
                }

                setWrkTMsgForStockOut(rs, resultMsg, convMdseCdBean);

                // END 05/25/2016 R.Shimamoto [V2.0 ADD]

                // Return
            } else if (isReturn(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForReturn(rs, resultMsg, convMdseCdBean);

                // Parts Usage Return
            } else if (isPartsUsageReturn(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForPartsUsageReturn(rs, resultMsg, convMdseCdBean);

                // WH Transfer
            } else if (isWHTransfer(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);
                toLocCd = rs.getString(WHT_TO_INVTY_LOC_CD);
                toLocTpCd = rs.getString(WHT_TO_LOC_TP_CD);

                // START 05/24/2016 R.Shimamoto [V2.0 ADD]
                String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
                String cincRcvGlblWhCd = getCincGlblWhCd(rs.getString(WHT_TO_RTL_WH_CD), rs.getString(WHT_TO_WH_OWNR_CD), rs.getString(WHT_TO_LOC_TP_CD));

                if (isTargetLoc(fromLocCd, fromLocTpCd) && isTargetLoc(toLocCd, toLocTpCd)) {

                    // WH Transfer pattern1 Different　Global WH
                    if (isTargetGlobalWH(rs, cincGlblWhCd, cincRcvGlblWhCd)) {

                        if (LOC_TP.TECHNICIAN.equals(fromLocTpCd) || LOC_TP.TECHNICIAN.equals(toLocTpCd)) {
                            // Different　Global WH From/To Tech
                            setWrkTMsgForWHTransferFromToTech(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                        } else {
                            // Different　Global WH From/To WH
                            setWrkTMsgForWHTransferFromToWH(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                        }

                    } else {

                        return null;
                    }

                    // WH Transfer pattern2
                } else if (isTargetLoc(fromLocCd, fromLocTpCd) && !isTargetLoc(toLocCd, toLocTpCd)) {

                    if (!LOC_TP.TECHNICIAN.equals(toLocTpCd)) {

                        setWrkTMsgForWHTransferToWH(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                    } else {

                        setWrkTMsgForWHTransferToTech(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);
                    }

                    // START 05/26/2016 R.Shimamoto [V2.0 DEL] WH
                    // } else if (!isTargetLoc(fromLocCd, fromLocTpCd)
                    // && isTargetLoc(toLocCd, toLocTpCd)) {
                    //
                    // if (!LOC_TP.TECHNICIAN.equals(fromLocTpCd)) {
                    //
                    // setWrkTMsgForWHTransferFromWH(rs, resultMsg,
                    // convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);
                    //
                    // } else {
                    //
                    // setWrkTMsgForWHTransferFromTech(rs, resultMsg,
                    // convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);
                    // }
                    // END 05/26/2016 R.Shimamoto [V2.0 DEL]

                } else {

                    return null;
                }

                // Internal Transfer
            } else if (isInternalTransfer(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(INVTY_LOC_CD);
                fromLocTpCd = rs.getString(LOC_TP_CD);
                toLocCd = rs.getString(WHT_TO_INVTY_LOC_CD);
                toLocTpCd = rs.getString(WHT_TO_LOC_TP_CD);

                // START 05/26/2016 R.Shimamoto [V2.0 ADD]
                String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
                String cincRcvGlblWhCd = getCincGlblWhCd(rs.getString(WHT_TO_RTL_WH_CD), rs.getString(WHT_TO_WH_OWNR_CD), rs.getString(WHT_TO_LOC_TP_CD));
                if (isTargetLoc(fromLocCd, fromLocTpCd) && isTargetLoc(toLocCd, toLocTpCd)) {

                    if (isTargetGlobalWH(rs, cincGlblWhCd, cincRcvGlblWhCd)) {

                        if (LOC_TP.TECHNICIAN.equals(fromLocTpCd) || LOC_TP.TECHNICIAN.equals(toLocTpCd)) {
                            // Different　Global WH To Tech
                            setWrkTMsgForInternalTransferDifferentToTech(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                        } else {
                            // Different　Global WH To WH
                            setWrkTMsgForInternalTransferDifferentToWH(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                        }

                    } else {

                        return null;
                    }

                    // Internal Transfer pattern2
                } else if (isTargetLoc(fromLocCd, fromLocTpCd) && !isTargetLoc(toLocCd, toLocTpCd)) {

                    if (!LOC_TP.TECHNICIAN.equals(toLocTpCd)) {

                        setWrkTMsgForInternalTransferToWH(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                    } else {

                        setWrkTMsgForInternalTransferToTech(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);
                    }
                    // START 05/26/2016 R.Shimamoto [V2.0 DEL]
                    // } else if (!isTargetLoc(fromLocCd, fromLocTpCd)
                    // && isTargetLoc(toLocCd, toLocTpCd)) {
                    //
                    // if (!LOC_TP.TECHNICIAN.equals(fromLocTpCd)) {
                    //
                    // setWrkTMsgForInternalTransferFromWH(rs,
                    // resultMsg, convMdseCdBean, cincGlblWhCd,
                    // cincRcvGlblWhCd);
                    //
                    // } else {
                    //
                    // setWrkTMsgForInternalTransferFromTech(rs,
                    // resultMsg, convMdseCdBean, cincGlblWhCd,
                    // cincRcvGlblWhCd);
                    // }
                    // END 05/26/2016 R.Shimamoto [V2.0 DEL]

                } else {

                    return null;
                }

                // START 05/26/2016 R.Shimamoto [V2.0 ADD]
                // Movement Showroom.
            } else if (isShowroom(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(SHIP_FROM_LOC_CUST_CD);
                fromLocTpCd = rs.getString(SHIP_FROM_LOC_TP_CD);
                toLocCd = rs.getString(WHT_TO_INVTY_LOC_CD);
                toLocTpCd = rs.getString(WHT_TO_LOC_TP_CD);

                String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
                String cincRcvGlblWhCd = getCincGlblWhCd(rs.getString(WHT_TO_RTL_WH_CD), rs.getString(WHT_TO_WH_OWNR_CD), rs.getString(WHT_TO_LOC_TP_CD));
                if (isTargetLoc(fromLocCd, fromLocTpCd) && isTargetLoc(toLocCd, toLocTpCd)) {

                    // Different　Global WH
                    if (isTargetGlobalWH(rs, cincGlblWhCd, cincRcvGlblWhCd)) {

                        setWrkTMsgForMoveShowroomTransfer(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                    } else {

                        return null;
                    }

                    // Movement Showroom pattern2
                } else if (isTargetLoc(fromLocCd, fromLocTpCd) && !isTargetLoc(toLocCd, toLocTpCd)) {

                    setWrkTMsgForMoveShowroomTransferToExc(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                } else {

                    return null;
                }

                // Movement Refurb Vendor.
            } else if (isRefurbVendor(trxCd, trxRsnCd)) {

                fromLocCd = rs.getString(SHIP_FROM_LOC_CUST_CD);
                fromLocTpCd = rs.getString(SHIP_FROM_LOC_TP_CD);
                toLocCd = rs.getString(WHT_TO_INVTY_LOC_CD);
                toLocTpCd = rs.getString(WHT_TO_LOC_TP_CD);

                String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
                String cincRcvGlblWhCd = getCincGlblWhCd(rs.getString(WHT_TO_RTL_WH_CD), rs.getString(WHT_TO_WH_OWNR_CD), rs.getString(WHT_TO_LOC_TP_CD));
                if (isTargetLoc(fromLocCd, fromLocTpCd) && isTargetLoc(toLocCd, toLocTpCd)) {

                    // Different　Global WH
                    if (isTargetGlobalWH(rs, cincGlblWhCd, cincRcvGlblWhCd)) {

                        setWrkTMsgForMoveRefurbVendorTransfer(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                    } else {

                        return null;
                    }

                } else if (isTargetLoc(fromLocCd, fromLocTpCd) && !isTargetLoc(toLocCd, toLocTpCd)) {

                    setWrkTMsgForMoveRefurbVendorTransferToExc(rs, resultMsg, convMdseCdBean, cincGlblWhCd, cincRcvGlblWhCd);

                } else {

                    return null;
                }

                // END 05/26/2016 R.Shimamoto [V2.0 ADD]

                // Disposal
            } else if (isDisposal(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForDisposal(rs, resultMsg, convMdseCdBean);

                // Item Change Stock Out
            } else if (isItemChangeStockOut(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForItemChangeStockOut(rs, resultMsg, convMdseCdBean);

                // Item Change Stock In
            } else if (isItemChangeStockIn(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForItemChangeStockIn(rs, resultMsg, convMdseCdBean);

                /* 05/26/2016 R.Shimamoto [V2.0 DEL] START */
                /* 12/13/2013 CSAI Y.Imazu Add QC3159 START */
                // // Work Order Stock Out to Vendor
                // } else if (isWorkOrderStockOutToVendor(trxCd,
                // trxRsnCd)) {
                //
                // if (isTargetInvtyLocCd(rs)) {
                //
                // return null;
                // }
                //
                // setWrkTMsgForWorkOrderStockOutToVendor(rs,
                // resultMsg, convMdseCdBean);
                //
                // // Work Order Stock Out from Vendor
                // } else if (isWorkOrderStockOutFromVendor(trxCd,
                // trxRsnCd)) {
                //
                // if (isTargetInvtyLocCd(rs) ||
                // !isWorkOrderCancel(rs)) {
                //
                // return null;
                // }
                //
                // setWrkTMsgForWorkOrderStockOutFromVendor(rs,
                // resultMsg, convMdseCdBean);
                // /* 12/13/2013 CSAI Y.Imazu Add QC3159 END */
                //
                /* 05/26/2016 R.Shimamoto [V2.0 DEL] END */
                // Work Order Stock Out(Kitting Item Change Stock-Out)
            } else if (isWorkOrderStockOut(trxCd, trxRsnCd)) {

                // START 05/31/2016 R.Shimamoto [V2.0 ADD]
                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }
                // END 05/31/2016 R.Shimamoto [V2.0 ADD]
                /* 12/13/2013 CSAI Y.Imazu Delete QC3159 START */
                // if (!isTargetWoInvtyLocCd(rs)) {
                //
                // return null;
                // }
                /* 12/13/2013 CSAI Y.Imazu Delete QC3159 END */

                setWrkTMsgForWorkOrderStockOut(rs, resultMsg, convMdseCdBean);

                // Work Order Stock In(Kitting Item Change Stock-In)
            } else if (isWorkOrderStockIn(trxCd, trxRsnCd)) {

                // START 05/31/2016 R.Shimamoto [V2.0 MOD]
                // if (!isTargetWoRcvInvtyLocCd(rs)) {
                if (!isTargetInvtyLocCd(rs)) {
                    // END 05/31/2016 R.Shimamoto [V2.0 MOD]

                    return null;
                }

                setWrkTMsgForWorkOrderStockIn(rs, resultMsg, convMdseCdBean);

                // Adjustment
            } else if (isAdjustment(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForAdjustment(rs, resultMsg, convMdseCdBean);
                // START 05/26/2016 R.Shimamoto [V2.0 ADD]
                // Cycle Count Adjustment
            } else if (isCycleCountAdjustment(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForAdjustment(rs, resultMsg, convMdseCdBean);

                // Physical Inventory Adjustment
            } else if (isPhysicalInventoryAdjustment(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForAdjustment(rs, resultMsg, convMdseCdBean);

                // Refurb Vendor Transfer Stock-Out from Vendor
            } else if (isRVStockOutFromVendor(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForAdjustment(rs, resultMsg, convMdseCdBean);

                // Refurb Expense Ship Out
            } else if (isRefurbExpenseShipOut(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForAdjustment(rs, resultMsg, convMdseCdBean);

                // Warehouse Transfer In-Transit Shortage Out
            } else if (isWHTInTransitShortageOut(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }
                // Do not differ Global WH
                String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
                String cincRcvGlblWhCd = getCincGlblWhCd(rs.getString(WHT_TO_RTL_WH_CD), rs.getString(WHT_TO_WH_OWNR_CD), rs.getString(WHT_TO_LOC_TP_CD));
                if (isTargetGlobalWH(rs, cincGlblWhCd, cincRcvGlblWhCd)) {

                    setWrkTMsgForAdjustment(rs, resultMsg, convMdseCdBean);

                } else {

                    return null;
                }

                // END 05/26/2016 R.Shimamoto [V2.0 ADD]

                // START 05/23/2016 R.Shimamoto [V2.0 DEL]
                // // Insurance Claim Loss on Shipping
                // } else if (isInsuranceClaimLossonShipping(trxCd,
                // trxRsnCd)) {
                //
                // if (!isTargetInvtyLocCd(rs)) {
                //
                // return null;
                // }
                //
                // setWrkTMsgForInsuranceClaimLossonShipping(rs,
                // resultMsg, convMdseCdBean);
                //
                // // Insurance Claim Loss on Receiving
                // } else if (isInsuranceClaimLossonReceiving(trxCd,
                // trxRsnCd)) {
                //
                // if (!isTargetInvtyLocCd(rs)) {
                //
                // return null;
                // }
                //
                // setWrkTMsgForInsuranceClaimLossonReceiving(rs,
                // resultMsg, convMdseCdBean);
                // END 05/23/2016 R.Shimamoto [V2.0 DEL]

                // Parts Usage
            } else if (isPartsUsage(trxCd, trxRsnCd)) {

                if (!isTargetPartsUsage()) {

                    return null;
                }

                setWrkTMsgForPartsUsage(rs, resultMsg, convMdseCdBean);

                // START 05/26/2016 R.Shimamoto [V2.0 ADD]
                // Reman Parts Usage
            } else if (isRemanPartsUsage(trxCd, trxRsnCd)) {

                if (!isTargetPartsUsage()) {

                    return null;
                }

                setWrkTMsgForRemanPartsUsage(rs, resultMsg, convMdseCdBean);

                // Reman Item Change
            } else if (isRemanItemChange(trxCd, trxRsnCd)) {

                if (!isTargetInvtyLocCd(rs)) {

                    return null;
                }

                setWrkTMsgForRemanItemChange(rs, resultMsg, convMdseCdBean);

                // END 05/26/2016 R.Shimamoto [V2.0 ADD]
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        // QC#26966 update.
        if (resultMsg != null) {
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd //
                    , setCincGlblOrdCatgCd(resultMsg.cincGlblWhCd.getValue()//
                            , resultMsg.cincShipToGlblCmpyCd.getValue()//
                            , resultMsg.cincRcvGlblWhCd.getValue()//
                            , resultMsg.cincGlblOrdCatgCd.getValue()));
        }

        return resultMsg;
    }

    /**
     * Set Inventory Transaction PK for Delete
     * @param rs ResultSet
     * @return PRT_STK_OUT_RSLT_WRKTMsg
     * @throws SQLException
     */
    private PRT_STK_OUT_RSLT_WRKTMsg setDeleteValue(ResultSet rs) {

        PRT_STK_OUT_RSLT_WRKTMsg inParam = new PRT_STK_OUT_RSLT_WRKTMsg();

        try {

            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(inParam.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        return inParam;
    }

    /**
     * Delete Work Data of Past Date
     * @param preIntfcCratDt String
     * @throws SQLException
     */
    private void deletePrtStkOutRsltWrkOfPastDt(String preIntfcCratDt) {

        List<PRT_STK_OUT_RSLT_WRKTMsg> inTMsgList = new ArrayList<PRT_STK_OUT_RSLT_WRKTMsg>();

        // get invtyTrxData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if (!ZYPCommonFunc.hasValue(preIntfcCratDt)) {

            return;
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("preIntfcCratDt", preIntfcCratDt);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getPrtStkOutRsltWrkOfPastDt", paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;

            while (rs.next()) {

                inputCount++;
                inTMsgList.add(setDeleteValue(rs));

                if (this.commitNumber == inTMsgList.size()) {

                    deletePrtStkOutRsltWrkFindByCratDt(inTMsgList);
                    inTMsgList.clear();
                }
            }

            if (inTMsgList.size() > 0) {

                deletePrtStkOutRsltWrkFindByCratDt(inTMsgList);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Get Newest Past Work Creation Date
     * @return String
     */
    private String getPrtStkOutRsltWrkCratDt() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("intfcCratDt", this.batDt);
        return (String) ssmBatchClient.queryObject("getPrtStkOutRsltWrkCratDt", ssmParam);
    }

    /**
     * Delete Work Data by Creation Date
     * @param inMsgLst List<PRT_STK_OUT_RSLT_WRKTMsg>
     */
    private void deletePrtStkOutRsltWrkFindByCratDt(List<PRT_STK_OUT_RSLT_WRKTMsg> inMsgLst) {

        PRT_STK_OUT_RSLT_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_STK_OUT_RSLT_WRKTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));
        commit();
    }

    /**
     * Check Stock Out Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isStockOut(String trxCd, String trxRsnCd) {

        if ((TRX.SALES.equals(trxCd) && TRX_RSN.REGULAR_SALES_CASH_LEASE_MDSE.equals(trxRsnCd))
                || (TRX.SALES.equals(trxCd) && TRX_RSN.REGULAR_SALES_CASH_LEASE_INIT_SUP.equals(trxRsnCd))) {
            return true;
        }
        if ((TRX.SALES.equals(trxCd) && TRX_RSN.REGULAR_SALES_CASH_LEASE_SUP.equals(trxRsnCd))
                || (TRX.SALES.equals(trxCd) && TRX_RSN.REGULAR_SALES_CASH_LEASE_CNTR_SUP.equals(trxRsnCd))) {
            return true;
        }
        if ((TRX.EXPENSE_SHIPMENT.equals(trxCd) && TRX_RSN.EXPENSE_SHIPMENT.equals(trxRsnCd))) {
            return true;
        }

        return false;
    }

    /**
     * Check Credit & Rebill Transaction
     * QC#27589 Update.
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isCreditAndRebill(String trxCd, String trxRsnCd) {

        // QC#27589 Update.
        List<String> creditAndRebillTrxList = Arrays.asList(//
                TRX.SALES + TRX_RSN.CREDIT_AND_REBILL //
                , TRX.SALES + TRX_RSN.CASH_LEASE_INIT_SUP_CREDIT //
                , TRX.SALES + TRX_RSN.CASH_LEASE_CNTR_SUP_CREDIT //
                , TRX.RENTAL_SHIPMENT + TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE_CREDIT //
                , TRX.EMSD_SHIPMENT + TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE_CREDIT //
        );

        if (creditAndRebillTrxList.contains(trxCd + trxRsnCd)) {
            return true;
        }

        return false;
    }

    // START 05/25/2016 R.Shimamoto [V2.0 ADD]
    /**
     * Check EMSD Shipment Stock-Out Expense Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isEMSDExpense(String trxCd, String trxRsnCd) {

        if (TRX.EMSD_SHIPMENT.equals(trxCd) && TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Loan Shipment Stock-Out Expense
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isLoanExpense(String trxCd, String trxRsnCd) {

        if (TRX.EXPENSE_SHIPMENT.equals(trxCd) && TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Drop Shipment Loan Stock-Out Expense
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isLoanDropShipment(String trxCd, String trxRsnCd) {

        if (TRX.EXPENSE_SHIPMENT.equals(trxCd) && TRX_RSN.EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Loan Stock-Out Expense Intangible with Cost
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isLoanIntangibleWithCost(String trxCd, String trxRsnCd) {

        if (TRX.EXPENSE_SHIPMENT.equals(trxCd) && TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Rental Shipment Stock-Out Expense
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isRentalShipmentExpense(String trxCd, String trxRsnCd) {

        if (TRX.RENTAL_SHIPMENT.equals(trxCd) && TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // END 05/25/2016 R.Shimamoto [V2.0 ADD]

    /**
     * Check Return Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isReturn(String trxCd, String trxRsnCd) {

        if ((TRX.SALES.equals(trxCd) && TRX_RSN.RETURN.equals(trxRsnCd)) || (TRX.EXPENSE_SHIPMENT.equals(trxCd) && TRX_RSN.EXPENSE_RETURN.equals(trxRsnCd))) {

            return true;
        }

        return false;
    }

    /**
     * Check Parts Usage Return Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isPartsUsageReturn(String trxCd, String trxRsnCd) {

        if (TRX.PARTS_USAGE.equals(trxCd) && TRX_RSN.PARTS_USAGE_RETURN.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check WH Transfer Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isWHTransfer(String trxCd, String trxRsnCd) {

        if (TRX.MOVEMENT.equals(trxCd) && TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Internal Transfer Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isInternalTransfer(String trxCd, String trxRsnCd) {
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // if (TRX.MOVEMENT.equals(trxCd) &&
        // TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN.equals(trxRsnCd)) {
        if (TRX.MOVEMENT.equals(trxCd) && TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {
            // END 05/26/2016 R.Shimamoto [V2.0 MOD]

            return true;
        }

        return false;
    }

    // START 05/26/2016 R.Shimamoto [V2.0 ADD]
    /**
     * Check Showroom Transfer Stock-Out
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isShowroom(String trxCd, String trxRsnCd) {

        if (TRX.MOVEMENT.equals(trxCd) && TRX_RSN.SHOWROOM_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Refurb Vendor Transfer Stock-Out
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isRefurbVendor(String trxCd, String trxRsnCd) {

        if (TRX.MOVEMENT.equals(trxCd) && TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // END 05/26/2016 R.Shimamoto [V2.0 ADD]

    /**
     * Check Disposal Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isDisposal(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.DISPOSAL.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Item Change Stock Out Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isItemChangeStockOut(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.ITEM_CHANGE_STOCK_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Item Change Stock In Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isItemChangeStockIn(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.ITEM_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // /* 12/13/2013 CSAI Y.Imazu Add QC3159 START */
    // /**
    // * Check Work Order Stock Out to Vendor Transaction
    // * @param trxCd String
    // * @param trxRsnCd String
    // * @return boolean
    // */
    // private boolean isWorkOrderStockOutToVendor(String trxCd,
    // String trxRsnCd) {
    //
    // if (TRX.MOVEMENT.equals(trxCd) &&
    // TRX_RSN.VENDOR_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {
    //
    // return true;
    // }
    //
    // return false;
    // }
    //
    // /**
    // * Check Work Order Stock Out from Vendor Transaction
    // * @param trxCd String
    // * @param trxRsnCd String
    // * @return boolean
    // */
    // private boolean isWorkOrderStockOutFromVendor(String trxCd,
    // String trxRsnCd) {
    //
    // if (TRX.MOVEMENT.equals(trxCd) &&
    // TRX_RSN.VENDOR_TRANSFER_STOCK_IN_FROM_VENDOR.equals(trxRsnCd))
    // {
    //
    // return true;
    // }
    //
    // return false;
    // }
    // /* 12/13/2013 CSAI Y.Imazu Add QC3159 END */
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    /**
     * Check Work Order Stock Out Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isWorkOrderStockOut(String trxCd, String trxRsnCd) {

        // START 05/26/2016 R.Shimamoto [V2.0 ADD]
        // if (TRX.ADJUSTMENT.equals(trxCd) &&
        // TRX_RSN.ITEM_CHANGE_STOCK_OUT_FROM_VENDOR.equals(trxRsnCd))
        // {
        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.KITTING_ITEM_CHANGE_STOCK_OUT.equals(trxRsnCd)) {
            // END 05/26/2016 R.Shimamoto [V2.0 ADD]

            return true;
        }

        return false;
    }

    /**
     * Check Work Order Stock In Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isWorkOrderStockIn(String trxCd, String trxRsnCd) {

        // START 05/26/2016 R.Shimamoto [V2.0 ADD]
        // if (TRX.ADJUSTMENT.equals(trxCd) &&
        // TRX_RSN.ITEM_CHANGE_STOCK_IN_FROM_VENDOR.equals(trxRsnCd))
        // {
        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.KITTING_ITEM_CHANGE_STOCK_IN.equals(trxRsnCd)) {
            // END 05/26/2016 R.Shimamoto [V2.0 ADD]

            return true;
        }

        return false;
    }

    /**
     * Check Adjustment Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isAdjustment(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.ADJUSTMENT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // START 05/26/2016 R.Shimamoto [V2.0 ADD]
    /**
     * Check Cycle Count Adjustment Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isCycleCountAdjustment(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.CYCLE_COUNT_ADJUSTMENT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Physical Inventory Adjustment Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isPhysicalInventoryAdjustment(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.PHYSICAL_INVENTORY_ADJUSTMENT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Refurb Vendor Transfer Stock-Out from Vendor Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isRVStockOutFromVendor(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Refurb Expense Ship Out Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isRefurbExpenseShipOut(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.REFURB_EXPENSE_SHIP_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Warehouse Transfer In-Transit Shortage Out Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isWHTInTransitShortageOut(String trxCd, String trxRsnCd) {

        if (TRX.ADJUSTMENT.equals(trxCd) && TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // END 05/26/2016 R.Shimamoto [V2.0 ADD]
    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // /**
    // * Check Insurance Claim Transaction (Loss on Shipment)
    // * @param trxCd String
    // * @param trxRsnCd String
    // * @return boolean
    // */
    // private boolean isInsuranceClaimLossonShipping(String trxCd,
    // String trxRsnCd) {
    //
    // if (TRX.INSURANCE_CLAIM.equals(trxCd) &&
    // TRX_RSN.LOSS_ON_SHIPMENT.equals(trxRsnCd)) {
    //
    // return true;
    // }
    //
    // return false;
    // }
    //
    // /**
    // * Check Insurance Claim Transaction (Loss on Receiving)
    // * @param trxCd String
    // * @param trxRsnCd String
    // * @return boolean
    // */
    // private boolean isInsuranceClaimLossonReceiving(String trxCd,
    // String trxRsnCd) {
    //
    // if (TRX.INSURANCE_CLAIM.equals(trxCd) &&
    // TRX_RSN.LOSS_ON_RECEIVING.equals(trxRsnCd)) {
    //
    // return true;
    // }
    //
    // return false;
    // }
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    /**
     * Check Parts Usage Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isPartsUsage(String trxCd, String trxRsnCd) {

        if (TRX.PARTS_USAGE.equals(trxCd) && TRX_RSN.PARTS_USAGE.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // START 05/26/2016 R.Shimamoto [V2.0 ADD]
    /**
     * Check Reman Parts Usage Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isRemanPartsUsage(String trxCd, String trxRsnCd) {

        if (TRX.REMANUFACTURING.equals(trxCd) && TRX_RSN.PARTS_USAGE_FOR_REMAN.equals(trxRsnCd) || TRX.REMANUFACTURING.equals(trxCd) && TRX_RSN.PARTS_USAGE_RETURN_FOR_REMAN.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * Check Reman Item Change Transaction
     * @param trxCd String
     * @param trxRsnCd String
     * @return boolean
     */
    private boolean isRemanItemChange(String trxCd, String trxRsnCd) {

        if (TRX.REMANUFACTURING.equals(trxCd) && TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT.equals(trxRsnCd) || TRX.REMANUFACTURING.equals(trxCd) && TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    // END 05/26/2016 R.Shimamoto [V2.0 ADD]

    /**
     * Check Target Inventory Location
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean isTargetInvtyLocCd(ResultSet rs) throws SQLException {

        if (prtExclInvtyLocCdCincMap.containsKey(rs.getString(INVTY_LOC_CD))) {

            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(prtInclTechInvtyCincFlg) && LOC_TP.TECHNICIAN.equals(rs.getString(LOC_TP_CD))) {

            return false;
        }

        return true;
    }

    /* 12/13/2013 CSAI Y.Imazu Delete QC3159 START */
    // /**
    // * Check Target Inventory Location (Work Order Ship)
    // * @param rs ResultSet
    // * @return boolean
    // * @throws SQLException
    // */
    // private boolean isTargetWoInvtyLocCd(ResultSet rs) throws
    // SQLException {
    //
    // if
    // (prtExclInvtyLocCdCincMap.containsKey(rs.getString("WO_INVTY_LOC_CD")))
    // {
    //
    // return false;
    // }
    //
    // if (ZYPConstant.FLG_OFF_N.equals(prtInclTechInvtyCincFlg) &&
    // LOC_TP.TECHNICIAN.equals(rs.getString("WO_INVTY_LOC_TP_CD"))) {
    //
    // return false;
    // }
    //
    // return true;
    // }
    /* 12/13/2013 CSAI Y.Imazu Delete QC3159 END */

    // START 05/31/2016 R.Shimamoto [V2.0 MOD]
    // /**
    // * Check Target Inventory Location (Work Order Receive)
    // * @param rs ResultSet
    // * @return boolean
    // * @throws SQLException
    // */
    // private boolean isTargetWoRcvInvtyLocCd(ResultSet rs) throws
    // SQLException {
    //
    // if
    // (prtExclInvtyLocCdCincMap.containsKey(rs.getString(WO_RCV_INVTY_LOC_CD)))
    // {
    //
    // return false;
    // }
    //
    // if (ZYPConstant.FLG_OFF_N.equals(prtInclTechInvtyCincFlg) &&
    // LOC_TP.TECHNICIAN.equals(rs.getString(WO_RCV_INVTY_LOC_TP_CD)))
    // {
    //
    // return false;
    // }
    //
    // return true;
    // }
    // END 05/31/2016 R.Shimamoto [V2.0 MOD]
    /**
     * Check Target Parts Usage
     * @return boolean
     */
    private boolean isTargetPartsUsage() {

        if (ZYPConstant.FLG_ON_Y.equals(prtInclTechInvtyCincFlg)) {

            return true;
        }

        return false;
    }

    /**
     * Check Target Location
     * @param locCd String
     * @param locTpCd String
     * @return boolean
     */
    private boolean isTargetLoc(String locCd, String locTpCd) {

        if (LOC_TP.TECHNICIAN.equals(locTpCd)) {

            if (ZYPConstant.FLG_ON_Y.equals(prtInclTechInvtyCincFlg)) {

                return true;
            }

            return false;
        }

        if (prtExclInvtyLocCdCincMap.containsKey(locCd)) {

            return false;
        }

        return true;
    }

    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // /* 12/13/2013 CSAI Y.Imazu Add QC3159 START */
    // /**
    // * Check Work Order Cancel
    // * @param rs ResultSet
    // * @return boolean
    // * @throws SQLException
    // */
    // private boolean isWorkOrderCancel(ResultSet rs) throws
    // SQLException {
    //
    // if
    // (NLXSceConst.SCE_ORD_TP_CD_KC.equals(rs.getString("SCE_ORD_TP_CD"))
    // ||
    // NLXSceConst.SCE_ORD_TP_CD_RC.equals(rs.getString("SCE_ORD_TP_CD")))
    // {
    //            
    // return true;
    // }
    //
    // return false;
    // }
    // /* 12/13/2013 CSAI Y.Imazu Add QC3159 END */
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    private void setWrkTMsgForStockOut(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        // START 05/24/2016 R.Shimamoto [V2.0 ADD]
        String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
        // END 05/24/2016 R.Shimamoto [V2.0 ADD]

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/24/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/24/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getInvtyGlblCmpyCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getInvtyGlblCmpyCd(rs));
        // START 05/24/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // getInvtyCincGlblCmpyCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_OTHER);
        // END 05/24/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getCpoCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, getCincGlblShpgCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForReturn(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        // START 05/24/2016 R.Shimamoto [V2.0 ADD]
        String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
        // END 05/24/2016 R.Shimamoto [V2.0 ADD]

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/24/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/24/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getInvtyGlblCmpyCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getInvtyGlblCmpyCd(rs));
        // START 05/24/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // getInvtyCincGlblCmpyCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_OTHER);
        // END 05/24/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/24/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getCpoCincGlblShpgMethCd(rs));
        // END 05/24/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, getCincGlblShpgCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForPartsUsageReturn(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        // START 05/24/2016 R.Shimamoto [V2.0 ADD]
        String cincGlblWhCd = getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD));
        // END 05/24/2016 R.Shimamoto [V2.0 ADD]

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/24/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/24/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getFsrGlblCmpyCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getFsrGlblCmpyCd(rs));
        // START 05/24/2016 R.Shimamoto [V2.0 ADD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // getFsrCincGlblCmpyCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        // END 05/24/2016 R.Shimamoto [V2.0 ADD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, getCincGlblShpgCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForWHTransferToWH(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
        // "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getInvtyCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getCPOTrdPtnrShpgMethCd(rs));
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_A);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForWHTransferToTech(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
        // "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getInvtyCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_A);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // private void setWrkTMsgForWHTransferFromWH(ResultSet rs,
    // PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String
    // cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd,
    // cincGlblWhCd);
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // cincRcvGlblWhCd);
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // getCincGlblCmpyCatgCdToWHT(rs));
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // // getInvtyCincGlblShpgMethCd(rs));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // getCPOTrdPtnrShpgMethCd(rs));
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_A);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // reverseSignForStkOutQty(rs.getBigDecimal("INVTY_TRX_QTY")));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    //
    // private void setWrkTMsgForWHTransferFromTech(ResultSet rs,
    // PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String
    // cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd,
    // cincGlblWhCd);
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // cincRcvGlblWhCd);
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // getCincGlblCmpyCatgCdToWHT(rs));
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // // START 05/25/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // // getInvtyCincGlblShpgMethCd(rs));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // getCPOTrdPtnrShpgMethCd(rs));
    // // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_A);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // reverseSignForStkOutQty(rs.getBigDecimal("INVTY_TRX_QTY")));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    // START 05/26/2016 R.Shimamoto [V2.0 ADD]
    private void setWrkTMsgForWHTransferFromToWH(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getCPOTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForWHTransferFromToTech(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForMoveShowroomTransfer(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getCPOTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForMoveShowroomTransferToExc(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getCPOTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_A);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForMoveRefurbVendorTransfer(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForMoveRefurbVendorTransferToExc(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_A);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // END 05/26/2016 R.Shimamoto [V2.0 ADD]

    private void setWrkTMsgForInternalTransferToWH(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
        // "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getInvtyCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_A);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForInternalTransferToTech(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
        // "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getInvtyCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_A);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_A1);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // private void setWrkTMsgForInternalTransferFromWH(ResultSet rs,
    // PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String
    // cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd,
    // cincGlblWhCd);
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // cincRcvGlblWhCd);
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // getCincGlblCmpyCatgCdToSHIP(rs));
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // // getInvtyCincGlblShpgMethCd(rs));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // getInvtyOrdTrdPtnrShpgMethCd(rs));
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_A);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // rs.getBigDecimal("INVTY_TRX_QTY"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    //
    // private void setWrkTMsgForInternalTransferFromTech(ResultSet
    // rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String
    // cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd,
    // cincGlblWhCd);
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
    // cincRcvGlblWhCd);
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // getCincGlblCmpyCatgCdToSHIP(rs));
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // // START 05/26/2016 R.Shimamoto [V2.0 MOD]
    // // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // // getInvtyCincGlblShpgMethCd(rs));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // getInvtyOrdTrdPtnrShpgMethCd(rs));
    // // END 05/26/2016 R.Shimamoto [V2.0 MOD]
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_A);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_A1);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // rs.getBigDecimal("INVTY_TRX_QTY"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    // START 05/26/2016 R.Shimamoto [V2.0 ADD]
    private void setWrkTMsgForInternalTransferDifferentToWH(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
        // "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getInvtyCincGlblShpgMethCd(rs));
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
        // CINC_GLBL_SHPG_CATG_CD_A);
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
        // CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_ORD_CATG_CD"));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForInternalTransferDifferentToTech(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, String cincGlblWhCd, String cincRcvGlblWhCd) throws SQLException {

        Map<String, Object> cincGlblCatgCd = getCincGlblCatgCdToWHT(rs);

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, cincGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd,
        // "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, cincRcvGlblWhCd);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_CMPY_CATG_CD"));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getInvtyCincGlblShpgMethCd(rs));
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
        // CINC_GLBL_SHPG_CATG_CD_A);
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
        // CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getInvtyOrdTrdPtnrShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, (String) cincGlblCatgCd.get("CINC_GLBL_ORD_CATG_CD"));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // END 05/26/2016 R.Shimamoto [2.0 ADD]
    private void setWrkTMsgForDisposal(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getShpgCincGlblShpgMethCd(rs));
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
        // getWoCincGlblShpgMethCd(rs));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_F);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, CINC_DSPL_RSN_CD_01);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForItemChangeStockOut(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getShpgCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_Z3);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForItemChangeStockIn(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_Z3);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // /* 12/13/2013 CSAI Y.Imazu Add QC3159 START */
    // private void setWrkTMsgForWorkOrderStockOutToVendor(ResultSet
    // rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws
    // SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // getWoCincGlblShpgMethCd(rs));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_Z4);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // reverseSignForStkOutQty(rs.getBigDecimal("INVTY_TRX_QTY")));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    //
    // private void setWrkTMsgForWorkOrderStockOutFromVendor(ResultSet
    // rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws
    // SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // CINC_GLBL_SHPG_METH_CD_OTHER);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_Z4);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // reverseSignForStkOutQty(rs.getBigDecimal("INVTY_TRX_QTY")));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    // /* 12/13/2013 CSAI Y.Imazu Add QC3159 END */
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    private void setWrkTMsgForWorkOrderStockOut(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD]
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, getWoCincGlblShpgMethCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_Z4);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForWorkOrderStockIn(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD];
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_Z4);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForAdjustment(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD];
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, CINC_GLBL_SHPG_CATG_CD_Z1);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // START 05/27/2016 R.Shimamoto [V2.0 DEL]
    // private void
    // setWrkTMsgForInsuranceClaimLossonShipping(ResultSet rs,
    // PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws
    // SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // CINC_GLBL_SHPG_METH_CD_OTHER);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // rs.getBigDecimal("INVTY_TRX_QTY"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    //
    // private void
    // setWrkTMsgForInsuranceClaimLossonReceiving(ResultSet rs,
    // PRT_STK_OUT_RSLT_WRKTMsg resultMsg,
    // NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws
    // SQLException {
    //
    // ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk,
    // rs.getBigDecimal("INVTY_TRX_PK"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt,
    // rs.getString("INVTY_TRX_DT"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
    // CINC_GLBL_CMPY_CATG_CD_INTERNAL);
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd,
    // convMdseCdBean.getXtrnlSysPrtCd());
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt,
    // convMdseCdBean.getXtrnlSysSize());
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd,
    // CINC_GLBL_SHPG_METH_CD_OTHER);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd,
    // CINC_GLBL_SHPG_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd,
    // CINC_GLBL_ORD_CATG_CD_ZZ);
    // ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd,
    // PRT_CHRG_IND_C);
    // ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt,
    // "");
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty,
    // rs.getBigDecimal("INVTY_TRX_QTY"));
    // ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt,
    // this.batDt);
    // }
    // END 05/27/2016 R.Shimamoto [V2.0 DEL]

    private void setWrkTMsgForPartsUsage(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        // END 05/26/2016 R.Shimamoto [V2.0 MOD];
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getFsrGlblCmpyCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getFsrGlblCmpyCd(rs));
        // START 05/26/2016 R.Shimamoto [V2.0 MOD]
        // ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd,
        // getFsrCincGlblCmpyCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        // END 05/26/2016 R.Shimamoto [V2.0 MOD];
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, getCincGlblShpgCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // START 05/26/2016 R.Shimamoto [V2.0 ADD]
    private void setWrkTMsgForRemanPartsUsage(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, getCincGlblShpgCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    private void setWrkTMsgForRemanItemChange(ResultSet rs, PRT_STK_OUT_RSLT_WRKTMsg resultMsg, NMXC104001ConvertPartsMdseCdBean convMdseCdBean) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, getCincGlblWhCd(rs.getString(STK_OUT_RTL_WH_CD), rs.getString(STK_OUT_WH_OWNR_CD), rs.getString(SHIP_FROM_LOC_TP_CD)));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutDt, rs.getString(INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_INTERNAL);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgCatgCd, getCincGlblShpgCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(resultMsg.cincBillToGlblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, PRT_CHRG_IND_C);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincDsplRsnCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQtySgnTxt, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcStkOutQty, rs.getBigDecimal(INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
    }

    // END 05/26/2016 R.Shimamoto [V2.0 ADD]

    private String getInvtyGlblCmpyCd(ResultSet rs) throws SQLException {

        return rs.getString(INVTY_GLBL_CMPY_CD);
    }

    private String getFsrGlblCmpyCd(ResultSet rs) throws SQLException {

        return rs.getString(FSR_GLBL_CMPY_CD);
    }

    // END 05/26/2016 R.Shimamoto [V2.0 ADD]
    // START 05/26/2016 R.Shimamoto [V2.0 DEL]
    // private String getInvtyCincGlblCmpyCatgCd(ResultSet rs) throws
    // SQLException {
    //
    // return
    // getCincGlblCmpyCatgCd(rs.getString("INVTY_BIZ_RELN_TP_CD"));
    // }
    //
    // private String getFsrCincGlblCmpyCatgCd(ResultSet rs) throws
    // SQLException {
    //
    // return
    // getCincGlblCmpyCatgCd(rs.getString("FSR_BIZ_RELN_TP_CD"));
    // }
    //    
    //
    // private String getCincGlblCmpyCatgCd(String bizRelnTpCd) {
    //
    // if (!ZYPCommonFunc.hasValue(bizRelnTpCd)) {
    //
    // return CINC_GLBL_CMPY_CATG_CD_OTHER;
    // }
    //
    // if (BIZ_RELN_TP.PARENTCOMP.equals(bizRelnTpCd) ||
    // BIZ_RELN_TP.INTERNCUST.equals(bizRelnTpCd)) {
    //
    // return CINC_GLBL_CMPY_CATG_CD_INTERNAL;
    //
    // } else if (BIZ_RELN_TP.SUBSIDIARY.equals(bizRelnTpCd)) {
    //
    // return CINC_GLBL_CMPY_CATG_CD_SUBSIDIARY;
    // }
    //
    // return CINC_GLBL_CMPY_CATG_CD_OTHER;
    // }
    // END 05/26/2016 R.Shimamoto [V2.0 DEL]

    private String getCpoCincGlblShpgMethCd(ResultSet rs) throws SQLException {

        // START 05/25/2016 R.Shimamoto [V2.0 MOD]
        // String shpgSvcLvlCd = rs.getString("CPO_SHPG_SVC_LVL_CD");
        // String trdPtnrShpgMethCd =
        // rs.getString("CPO_TRD_PTNR_SHPG_METH_CD");
        // return getCincGlblShpgMethCd(rs, shpgSvcLvlCd,
        // trdPtnrShpgMethCd);
        return getCPOTrdPtnrShpgMethCd(rs);
        // END 05/25/2016 R.Shimamoto [V2.0 MOD]
    }

    // START 05/26/2016 R.Shimamoto [V2.0 DEL]
    // private String getInvtyCincGlblShpgMethCd(ResultSet rs) throws
    // SQLException {
    //
    // String shpgSvcLvlCd = rs.getString("INVTY_SHPG_SVC_LVL_CD");
    // String trdPtnrShpgMethCd =
    // rs.getString("INVTY_TRD_PTNR_SHPG_METH_CD");
    // return getCincGlblShpgMethCd(rs, shpgSvcLvlCd,
    // trdPtnrShpgMethCd);
    // }
    // END 05/26/2016 R.Shimamoto [V2.0 DEL]

    private String getShpgCincGlblShpgMethCd(ResultSet rs) throws SQLException {

        String shpgSvcLvlCd = rs.getString(SHPG_SHPG_SVC_LVL_CD);
        String trdPtnrShpgMethCd = rs.getString(SHPG_TRD_PTNR_SHPG_METH_CD);
        return getCincGlblShpgMethCd(rs, shpgSvcLvlCd, trdPtnrShpgMethCd);
    }

    private String getWoCincGlblShpgMethCd(ResultSet rs) throws SQLException {

        String shpgSvcLvlCd = rs.getString(WO_SHPG_SVC_LVL_CD);
        String trdPtnrShpgMethCd = rs.getString(WO_TRD_PTNR_SHPG_METH_CD);
        return getCincGlblShpgMethCd(rs, shpgSvcLvlCd, trdPtnrShpgMethCd);
    }

    private String getCincGlblShpgMethCd(ResultSet rs, String shpgSvcLvlCd, String trdPtnrShpgMethCd) throws SQLException {

        if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {

            return CINC_GLBL_SHPG_METH_CD_OTHER;

        } else if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {

            setSendMailForErrorInfoForShpgMeth(rs);
            return CINC_GLBL_SHPG_METH_CD_OTHER;
        }

        return trdPtnrShpgMethCd;
    }

    private void setSendMailForErrorInfoForShpgMeth(ResultSet rs) throws SQLException {

        this.termCd = TERM_CD.WARNING_END;
        String[] errMsg = new String[] {MSG_ITEM_SHIP_METHODE, "INVTY_TRX_PK:" + rs.getString(INVTY_TRX_PK) + " MDSE_CD:" + rs.getString(MDSE_CD) };
        sendMailForErrorInfo.addErrMsgWithLogOutput(NLCM0127W, errMsg);
    }

    private String getCincGlblShpgCatgCd(String cincBillToGlblCmpyCd) {

        if (this.glblCmpyCd.equals(cincBillToGlblCmpyCd)) {

            return CINC_GLBL_SHPG_CATG_CD_B;
        }

        return CINC_GLBL_SHPG_CATG_CD_A;
    }

    private String getCincGlblOrdCatgCd(String cincBillToGlblCmpyCd) {

        if (this.glblCmpyCd.equals(cincBillToGlblCmpyCd)) {

            return CINC_GLBL_ORD_CATG_CD_ZZ;
        }

        return CINC_GLBL_ORD_CATG_CD_A1;
    }

    // START 06/03/2016 R.Shimamoto [V2.0 DEL]
    // private BigDecimal reverseSignForStkOutQty(BigDecimal
    // intfcStkOutQty) {
    //
    // return intfcStkOutQty.negate();
    // }
    // END 06/03/2016 R.Shimamoto [V2.0 DEL]

    // START 05/23/2016 R.Shimamoto [V2.0]
    /**
     * getCPOTrdPtnrShpgMethCd
     */
    private String getCPOTrdPtnrShpgMethCd(ResultSet rs) throws SQLException {

        String shpgSvcLclCd = rs.getString(CPO_SHPG_SVC_LVL_CD);
        String carrCd = rs.getString(CPO_CARR_CD);

        // priority 1
        if (!ZYPCommonFunc.hasValue(shpgSvcLclCd)) {
            return CINC_GLBL_SHPG_METH_CD_OTHER;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        String trdPtnrShpgMethCd = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        if (ZYPCommonFunc.hasValue(carrCd)) {
            // priority 2
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("shpgSvcLclCd", shpgSvcLclCd);
            queryParam.put("vndCd", this.scubeIfCincVndCd);
            queryParam.put("carrCd", carrCd);
            queryParam.put("frtCondCd", ASTERISK);
            trdPtnrShpgMethCd = (String) ssmBatchClient.queryObject("getTrdPtnrShpgMethCd", queryParam, execParam);
            if (ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
                return trdPtnrShpgMethCd;
            }
        }

        // priority 3
        queryParam.clear();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shpgSvcLclCd", shpgSvcLclCd);
        queryParam.put("vndCd", this.scubeIfCincVndCd);
        queryParam.put("carrCd", ASTERISK);
        queryParam.put("frtCondCd", ASTERISK);
        trdPtnrShpgMethCd = (String) ssmBatchClient.queryObject("getTrdPtnrShpgMethCd", queryParam);
        if (ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
            return trdPtnrShpgMethCd;
        }

        // priority 4
        if (!ZYPCommonFunc.hasValue(trdPtnrShpgMethCd)) {
            trdPtnrShpgMethCd = CINC_GLBL_SHPG_METH_CD_OTHER;
        }

        return trdPtnrShpgMethCd;
    }

    /**
     * getInvtyOrdTrdPtnrShpgMethCd
     */
    private String getInvtyOrdTrdPtnrShpgMethCd(ResultSet rs) throws SQLException {

        String shpgSvcLclCd = rs.getString(INVTY_SHPG_SVC_LVL_CD);

        // priority 1
        if (!ZYPCommonFunc.hasValue(shpgSvcLclCd)) {
            return CINC_GLBL_SHPG_METH_CD_OTHER;
        }

        // priority 2
        Map<String, Object> queryParam = new HashMap<String, Object>();
        String trdPtnrShpgMethCd = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shpgSvcLclCd", shpgSvcLclCd);
        queryParam.put("vndCd", this.scubeIfCincVndCd);
        queryParam.put("carrCd", ASTERISK);
        queryParam.put("frtCondCd", ASTERISK);
        trdPtnrShpgMethCd = (String) ssmBatchClient.queryObject("getTrdPtnrShpgMethCd", queryParam, execParam);

        return trdPtnrShpgMethCd;
    }

    /**
     * getCincGlblWhCd
     */
    private String getCincGlblWhCd(String rtlWhCd, String whOwnrCd, String locTpCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        String cincGlblWhCd = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (int i = 0; i < IDX_4; i++) {

            if (i == IDX_0) {
                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    // priority 1
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("rtlWhCd", rtlWhCd);
                    queryParam.put("whOwnrCd", ASTERISK);
                    queryParam.put("locTpCd", ASTERISK);
                }
            }
            if (i == IDX_1) {
                if (ZYPCommonFunc.hasValue(whOwnrCd)) {
                    // priority 2
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("rtlWhCd", ASTERISK);
                    queryParam.put("whOwnrCd", whOwnrCd);
                    queryParam.put("locTpCd", ASTERISK);
                }
            }
            if (i == IDX_2) {
                if (ZYPCommonFunc.hasValue(locTpCd)) {
                    // priority 3
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("rtlWhCd", ASTERISK);
                    queryParam.put("whOwnrCd", ASTERISK);
                    queryParam.put("locTpCd", locTpCd);
                }
            }
            if (i == IDX_3 || !ZYPCommonFunc.hasValue(rtlWhCd)) {
                // other
                queryParam.put("glblCmpyCd", this.glblCmpyCd);
                queryParam.put("rtlWhCd", ASTERISK);
                queryParam.put("whOwnrCd", ASTERISK);
                queryParam.put("locTpCd", ASTERISK);
            }

            cincGlblWhCd = (String) ssmBatchClient.queryObject("getCincGlblWhCd", queryParam, execParam);
            if (ZYPCommonFunc.hasValue(cincGlblWhCd)) {
                break;
            }
            queryParam.clear();
        }

        return cincGlblWhCd;
    }

    /**
     * getCincGlblCatgCdToWHT
     */
    private Map<String, Object> getCincGlblCatgCdToWHT(ResultSet rs) throws SQLException {

        Map<String, Object> cincGlblCatgCd = new HashMap<String, Object>();

        String fromRtlWhCd = rs.getString(STK_OUT_RTL_WH_CD);
        String fromWhOwnrCd = rs.getString(STK_OUT_WH_OWNR_CD);
        String fromLocTpCd = rs.getString(SHIP_FROM_LOC_TP_CD);
        String toRtlWhCd = rs.getString(WHT_TO_RTL_WH_CD);
        String toWhOwnrCd = rs.getString(WHT_TO_WH_OWNR_CD);
        String toLocTpCd = rs.getString(WHT_TO_LOC_TP_CD);

        Map<String, Object> queryParam = new HashMap<String, Object>();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (int i = 0; i < IDX_10; i++) {
            if (i == IDX_0) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case A1
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", fromRtlWhCd);
                    queryParam.put("fromWhOwnrCd", ASTERISK);
                    queryParam.put("fromLocTpCd", ASTERISK);
                    queryParam.put("toRtlWhCd", toRtlWhCd);
                    queryParam.put("toWhOwnrCd", ASTERISK);
                    queryParam.put("toLocTpCd", ASTERISK);
                }
            }
            if (i == IDX_1) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case A2
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", fromRtlWhCd);
                    queryParam.put("fromWhOwnrCd", ASTERISK);
                    queryParam.put("fromLocTpCd", ASTERISK);
                    queryParam.put("toRtlWhCd", ASTERISK);
                    queryParam.put("toWhOwnrCd", toWhOwnrCd);
                    queryParam.put("toLocTpCd", ASTERISK);
                }
            }
            if (i == IDX_2) {
                if (ZYPCommonFunc.hasValue(fromRtlWhCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case A3
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", fromRtlWhCd);
                    queryParam.put("fromWhOwnrCd", ASTERISK);
                    queryParam.put("fromLocTpCd", ASTERISK);
                    queryParam.put("toRtlWhCd", ASTERISK);
                    queryParam.put("toWhOwnrCd", ASTERISK);
                    queryParam.put("toLocTpCd", toLocTpCd);
                }
            }
            if (i == IDX_3) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case B1
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", ASTERISK);
                    queryParam.put("fromWhOwnrCd", fromWhOwnrCd);
                    queryParam.put("fromLocTpCd", ASTERISK);
                    queryParam.put("toRtlWhCd", toRtlWhCd);
                    queryParam.put("toWhOwnrCd", ASTERISK);
                    queryParam.put("toLocTpCd", ASTERISK);
                }
            }
            if (i == IDX_4) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case B2
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", ASTERISK);
                    queryParam.put("fromWhOwnrCd", fromWhOwnrCd);
                    queryParam.put("fromLocTpCd", ASTERISK);
                    queryParam.put("toRtlWhCd", ASTERISK);
                    queryParam.put("toWhOwnrCd", toWhOwnrCd);
                    queryParam.put("toLocTpCd", ASTERISK);
                }
            }
            if (i == IDX_5) {
                if (ZYPCommonFunc.hasValue(fromWhOwnrCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case B3
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", ASTERISK);
                    queryParam.put("fromWhOwnrCd", fromWhOwnrCd);
                    queryParam.put("fromLocTpCd", ASTERISK);
                    queryParam.put("toRtlWhCd", ASTERISK);
                    queryParam.put("toWhOwnrCd", ASTERISK);
                    queryParam.put("toLocTpCd", toLocTpCd);
                }
            }
            if (i == IDX_6) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toRtlWhCd)) {
                    // priority case C1
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", ASTERISK);
                    queryParam.put("fromWhOwnrCd", ASTERISK);
                    queryParam.put("fromLocTpCd", fromLocTpCd);
                    queryParam.put("toRtlWhCd", toRtlWhCd);
                    queryParam.put("toWhOwnrCd", ASTERISK);
                    queryParam.put("toLocTpCd", ASTERISK);
                }
            }
            if (i == IDX_7) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toWhOwnrCd)) {
                    // priority case C2
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", ASTERISK);
                    queryParam.put("fromWhOwnrCd", ASTERISK);
                    queryParam.put("fromLocTpCd", fromLocTpCd);
                    queryParam.put("toRtlWhCd", ASTERISK);
                    queryParam.put("toWhOwnrCd", toWhOwnrCd);
                    queryParam.put("toLocTpCd", ASTERISK);
                }
            }
            if (i == IDX_8) {
                if (ZYPCommonFunc.hasValue(fromLocTpCd) && ZYPCommonFunc.hasValue(toLocTpCd)) {
                    // priority case C3
                    queryParam.put("glblCmpyCd", this.glblCmpyCd);
                    queryParam.put("fromRtlWhCd", ASTERISK);
                    queryParam.put("fromWhOwnrCd", ASTERISK);
                    queryParam.put("fromLocTpCd", fromLocTpCd);
                    queryParam.put("toRtlWhCd", ASTERISK);
                    queryParam.put("toWhOwnrCd", ASTERISK);
                    queryParam.put("toLocTpCd", toLocTpCd);
                }
            }
            if (i == IDX_9 || !ZYPCommonFunc.hasValue(toRtlWhCd)) {
                // other
                queryParam.put("glblCmpyCd", this.glblCmpyCd);
                queryParam.put("fromRtlWhCd", ASTERISK);
                queryParam.put("fromWhOwnrCd", ASTERISK);
                queryParam.put("fromLocTpCd", ASTERISK);
                queryParam.put("toRtlWhCd", ASTERISK);
                queryParam.put("toWhOwnrCd", ASTERISK);
                queryParam.put("toLocTpCd", ASTERISK);
            }
            cincGlblCatgCd = (Map<String, Object>) ssmBatchClient.queryObject("getCincGlblCatgCd", queryParam, execParam);
            if (cincGlblCatgCd != null) {
                String catgCd = (String) cincGlblCatgCd.get(CINC_GLBL_CMPY_CATG_CD);
                if (ZYPCommonFunc.hasValue(catgCd)) {
                    break;
                }
            }

            queryParam.clear();
        }

        return cincGlblCatgCd;
    }

    /**
     * Check Target Global WH
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean isTargetGlobalWH(ResultSet rs, String fromGlobalWhCd, String toGlobalWhCd) throws SQLException {

        if (ZYPConstant.FLG_ON_Y.equals(rs.getString(DS_COND_CONST_VAL_TXT_04))) {

            if (ZYPConstant.FLG_ON_1.equals(rs.getString(DS_COND_CONST_VAL_TXT_05)) && !fromGlobalWhCd.equals(toGlobalWhCd)) {

                return true;
            } else if (STR_2.equals(rs.getString(DS_COND_CONST_VAL_TXT_05)) && fromGlobalWhCd.equals(toGlobalWhCd)) {

                return true;
            } else {

                return false;
            }
        } else {
            return false;
        }

    }

    // END 05/23/2016 R.Shimamoto [V2.0]

    // QC#26966 Add method.
    private String setCincGlblOrdCatgCd(String cincGlblWh, String rcvCincGlblCmpyCd, String rcvCincGlblWh, String cincGlblOrdCatgCd) {

        if (!CINC_GLBL_ORD_CATG_CD_A1.equals(cincGlblOrdCatgCd)) {
            return cincGlblOrdCatgCd;
        }

        String cincGlblWhCdOfItasca = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_ITASC, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(cincGlblWhCdOfItasca)) {
            cincGlblWhCdOfItasca = "ITASC";
        }

        if (!cincGlblWhCdOfItasca.equals(cincGlblWh) //
                && (!ZYPCommonFunc.hasValue(rcvCincGlblCmpyCd)//
                || !ZYPCommonFunc.hasValue(rcvCincGlblWh))) {
            return cincGlblOrdCatgCd;
        } else {
            return CINC_GLBL_ORD_CATG_CD_BZ;
        }
    }
}
