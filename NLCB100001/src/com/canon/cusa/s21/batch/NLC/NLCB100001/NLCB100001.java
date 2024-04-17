/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB100001;

import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.DS_COND_CONST_GRP_ID_SCUBE_IF_IN_TRNST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.EFF_FROM_DT_DEFALUT;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.LOC_STS_CD_LST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.LOC_TP_CD_AST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.LOC_TP_CD_LST_TRNST_FLG_N_DEFAULT;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.LOC_TP_CD_LST_TRNST_FLG_N_INCL_TECH;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.LOC_TP_CD_LST_TRNST_FLG_Y_DEFAULT;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.LOC_TP_CD_LST_TRNST_FLG_Y_INCL_TECH;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.MSG_ID_NLCM0065E;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.MSG_ID_ZZZM9025E;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.NLAM1295E;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.NLCM0131E;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.NUM_ONE;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.RTL_WH_CD_AST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VALUE_INTFC_QTY_SGN_TXT_MINUS;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VALUE_INTFC_QTY_SGN_TXT_PLUS;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VALUE_PRT_CHRG_IND;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VALUE_PRT_INVTY_CATG_CD_A1;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VALUE_PRT_INVTY_CATG_CD_AZ;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VALUE_PRT_INVTY_CATG_CD_C;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.WH_OWNR_CD_AST;
import static com.canon.cusa.s21.batch.NLC.NLCB100001.constant.NLCB100001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.PRT_INVTY_WRKTMsg;

import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * S-CUBE Inv. Master Info to CINC (WWABF304/312)<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/13/2013   Hitachi         K.Kishimoto     Created         Created
 * 11/20/2013   Hitachi         K.Kishimoto     Update          QC3148
 * 03/20/2014   CSAI            Y.Imazu         Update          ITG#509133
 * 05/16/2016   CITS            R.Shimamoto     Update          V2.0
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 03/28/2019   CITS            T.Tokutomi      Update          QC#30963
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 01/29/2020   Fujitsu         R.Nakamura      Update          QC#55572
 * </pre>
 */
public class NLCB100001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String batDt;

    /** Commit Number */
    private int commitNumber;

    /** total commit count */
    private int totalCommitCount;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** exclude INVTY_LOC_CD */
    private String[] excludeLocCdList = null;

    // START 11/20/2013 K.Kishimoto [QC3148, ADD]
    /** Parts Include Technician Inventory CINC Flag */
    private String prtInclTechInvtyCincFlg = null;

    // END 11/20/2013 K.Kishimoto [QC3148, ADD]

    //QC#26966 Add.
    /** Global Warehouse code COLUBUS */
    private String glblWhCdClmbus = null;

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

        // get exclude INVTY_LOC_CD
        String result = ZYPCodeDataUtil.getVarCharConstValue("PRT_EXCL_INVTY_LOC_CD_CINC", this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(result)) {

            this.excludeLocCdList = result.split(",");
        }

        // START 11/20/2013 K.Kishimoto [QC3148, ADD]
        // get Parts Include Technician Inventory CINC Flag
        this.prtInclTechInvtyCincFlg = ZYPCodeDataUtil.getVarCharConstValue("PRT_INCL_TECH_INVTY_CINC_FLG", this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(this.prtInclTechInvtyCincFlg)) {

            String[] tmp = {"PRT_INCL_TECH_INVTY_CINC_FLG" };
            throw new S21AbendException(MSG_ID_ZZZM9025E, tmp);
        }

        if (!ZYPConstant.FLG_ON_Y.equals(this.prtInclTechInvtyCincFlg) && !ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {

            String[] tmp = {"\"PRT_INCL_TECH_INVTY_CINC_FLG\"" };
            throw new S21AbendException(MSG_ID_NLCM0065E, tmp);
        }
        // END 11/20/2013 K.Kishimoto [QC3148, ADD]

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.totalCommitCount = 0;

        // QC#26966 Add.
        glblWhCdClmbus = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(glblWhCdClmbus)) {
            glblWhCdClmbus = "CLMBS";
        }
    }

    @Override
    protected void mainRoutine() {

        // delete PRT_INVTY_WRK data(For rerun)
        deletePartsDataOfBatDt();

        // insert PartsData into PRT_INVTY_WRK
        insertPartsData();

        // delete PRT_INVTY_WRK data(Past Date)
        deletePartsDataOfPastDt();
    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLCB100001().executeBatch(NLCB100001.class.getSimpleName());
    }

    private void deletePartsDataOfBatDt() {

        List<PRT_INVTY_WRKTMsg> inTMsgList = new ArrayList<PRT_INVTY_WRKTMsg>();

        // get partsData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("intfcCratDt", this.batDt);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getPrtInvtyWrkFindByIntfcCratDt", paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int totalDeleteCount = 0;

            // delete partsData of batDt

            while (rs.next()) {
                inputCount++;
                inTMsgList.add(setDeleteValue(rs));

                if (this.commitNumber == inTMsgList.size()) {

                    deletePrtInvtyWrkFindByIntfcCratDt(inTMsgList);
                    inTMsgList.clear();
                }
            }

            if (inTMsgList.size() > 0) {
                deletePrtInvtyWrkFindByIntfcCratDt(inTMsgList);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * @return Map<String, Object>
     */
    private void insertPartsData() {

        List<PRT_INVTY_WRKTMsg> inTMsgList = new ArrayList<PRT_INVTY_WRKTMsg>();

        // get parts Data
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        Map<String, Object> paramMap = setParam();

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrtInvty", paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int commitCount = 0;

            while (rs.next()) {

                inputCount++;
                inTMsgList.add(setCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {

                    commitCount = insertPartsData(inTMsgList);
//                    inTMsgList = new ArrayList<PRT_INVTY_WRKTMsg>();
                    inTMsgList.clear();
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
            }

            if (inputCount != this.totalCommitCount) {

                commitCount = insertPartsData(inTMsgList);
                this.totalCommitCount += commitCount;
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertPartsData(List<PRT_INVTY_WRKTMsg> inMsgLst) {

        PRT_INVTY_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_INVTY_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {

            throw new S21AbendException(NLAM1295E, new String[] {"PRT_INVTY_WRK" });
        }

        commit();
        return insertCount;
    }

    private Map<String, Object> setParam() {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        // START 11/20/2013 K.Kishimoto [QC3148, MOD]
        // paramMap.put("glblCmpyCd", this.glblCmpyCd);
        // paramMap.put("rgtnTpCdA", MDSE_RGTN_TP.S21_PARTS);
        // paramMap.put("rgtnTpCdB", MDSE_RGTN_TP.MANUAL);
        // paramMap.put("mdseCatgCd", MDSE_CATG.PARTS);
        // paramMap.put("rgtnStsCdA", RGTN_STS.TEMPORARILY_SAVED);
        // paramMap.put("rgtnStsCdB", RGTN_STS.PENDING_COMPLETION);
        // paramMap.put("rgtnStsCdC", RGTN_STS.TERMINATED);
        // paramMap.put("invtySnapShotDt", this.batDt);
        // paramMap.put("locStsCdA", LOC_STS.IN_TRANSIT);
        // paramMap.put("locStsCdB", LOC_STS.IN_TRANSIT_WH);
        // paramMap.put("locStsCdC", LOC_STS.DC_STOCK);
        // paramMap.put("locTpCdA", LOC_TP.WAREHOUSE);
        // paramMap.put("locTpCdB", LOC_TP.TECHNICIAN);
        // paramMap.put("stkStsCd", STK_STS.DEFECTIVE);
        // paramMap.put("invtySnapShotDt", this.batDt);
        // paramMap.put("excludeLocCdList", this.excludeLocCdList);
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("rgtnTpCdA", MDSE_RGTN_TP.S21_PARTS);
        paramMap.put("rgtnTpCdB", MDSE_RGTN_TP.MANUAL);
        paramMap.put("mdseCatgCd", MDSE_CATG.PARTS);
        paramMap.put("rgtnStsCdA", RGTN_STS.TEMPORARILY_SAVED);
        paramMap.put("rgtnStsCdB", RGTN_STS.PENDING_COMPLETION);
        paramMap.put("rgtnStsCdC", RGTN_STS.TERMINATED);
        paramMap.put("invtySnapShotDt", this.batDt);
        paramMap.put("locStsCdA", LOC_STS.IN_TRANSIT);
        paramMap.put("locStsCdB", LOC_STS.LOSS_ON_RECEIVING);
        paramMap.put("locStsCdC", LOC_STS.LOSS_ON_SHIPMENT);
        paramMap.put("locStsCdD", LOC_STS.INSURANCE_CLAIM_DAMAGED);

        // STR 05/16/2016 R.Shimamoto [V2.0 ADD]
        String cincGlblWhCdOther = getCincGlblWhCdOther();
        paramMap.put("cincGlblWhCdOther", cincGlblWhCdOther);

        paramMap.put("locStsCdE", LOC_STS.IN_TRANSIT_WH);
        paramMap.put("effFromDtDefalut", EFF_FROM_DT_DEFALUT);
        paramMap.put("rtlWhCdAst", RTL_WH_CD_AST);
        paramMap.put("whOwnrCdAst", WH_OWNR_CD_AST);
        paramMap.put("locTpCdAst", LOC_TP_CD_AST);

        paramMap.put("intfcInTrnstFlgN", ZYPConstant.FLG_OFF_N);
        paramMap.put("intfcInTrnstFlgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("cmpyInvtyFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("rwsOpenFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("numOne", NUM_ONE);

        paramMap.put("dsCondConstGrpId", VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD);
        paramMap.put("dsCondConstGrpIdTrnst", DS_COND_CONST_GRP_ID_SCUBE_IF_IN_TRNST);

        // get vndSysTpCdList
        String vndSysTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD, glblCmpyCd);
        String[] vndSysTpCdList = null;

        if (ZYPCommonFunc.hasValue(vndSysTpCd)) {

            vndSysTpCdList = vndSysTpCd.split(",");
        }
        paramMap.put("vndSysTpCdList", vndSysTpCdList);

        // END 05/16/2016 R.Shimamoto [V2.0 ADD]

        paramMap.put("locStsCdList", LOC_STS_CD_LST);

        if (ZYPConstant.FLG_ON_Y.equals(this.prtInclTechInvtyCincFlg)) {

            paramMap.put("locTpCdListA", LOC_TP_CD_LST_TRNST_FLG_Y_INCL_TECH);
            paramMap.put("locTpCdListB", LOC_TP_CD_LST_TRNST_FLG_N_INCL_TECH);

        } else {

            // STR 05/16/2016 R.Shimamoto [V2.0 ADD]
            paramMap.put("technician", LOC_TP.TECHNICIAN);
            // END 05/16/2016 R.Shimamoto [V2.0 ADD]

            paramMap.put("locTpCdListA", LOC_TP_CD_LST_TRNST_FLG_Y_DEFAULT);
            paramMap.put("locTpCdListB", LOC_TP_CD_LST_TRNST_FLG_N_DEFAULT);
        }

        paramMap.put("stkStsCd", STK_STS.DEFECTIVE);
        paramMap.put("excludeLocCdList", this.excludeLocCdList);
        paramMap.put("prtInvtyCatgA", VALUE_PRT_INVTY_CATG_CD_A1);
        paramMap.put("prtInvtyCatgB", VALUE_PRT_INVTY_CATG_CD_C);
        // END 11/20/2013 K.Kishimoto [QC3148, MOD]

        /* 03/20/2014 CSAI Y.Imazu Add ITG#509133 START */
        paramMap.put("trxSrcWholeSales", TRX_SRC_TP.WHOLE_SALES);
        paramMap.put("trxSalas", TRX.SALES);
        paramMap.put("trxExpense", TRX.EXPENSE_SHIPMENT);
        paramMap.put("shpgStsShipped", SHPG_STS.SHIPPED);
        paramMap.put("shpgStsArrived", SHPG_STS.ARRIVED);
        paramMap.put("shpgStsInstalled", SHPG_STS.INSTALLED);
        paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);
        paramMap.put("flgOffN", ZYPConstant.FLG_OFF_N);
        paramMap.put("prtInclTechInvtyCincFlg", this.prtInclTechInvtyCincFlg);
        /* 03/20/2014 CSAI Y.Imazu Add ITG#509133 END */
        paramMap.put("batProcDt", this.batDt); // QC#27375 add

        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            paramMap.put("exclMdseCdList", exclMdseList);
        }

        // Add Start 2020/01/30 QC#55572
        String scubeExclSwhCdList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(scubeExclSwhCdList)) {
            String[] exclSwhCdList = scubeExclSwhCdList.split(",");

            paramMap.put("exclSwhCdList", exclSwhCdList);
        }
        // Add End 2020/01/30 QC#55572

        return paramMap;
    }

    private PRT_INVTY_WRKTMsg setCreateValue(ResultSet rs) {

        PRT_INVTY_WRKTMsg inParam = new PRT_INVTY_WRKTMsg();

        try {

            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.intfcCratDt, this.batDt);
            // STAR 05/16/2016 R.Shimamoto [_V2.0 MOD]
            // ZYPEZDItemValueSetter.setValue(inParam.cincGlblWhCd,
            // VALUE_CINC_GLBL_WH_CD);
            ZYPEZDItemValueSetter.setValue(inParam.cincGlblWhCd, rs.getString("CINC_GLBL_WH_CD"));
            // END 05/16/2016 R.Shimamoto [_V2.0 MOD]
            ZYPEZDItemValueSetter.setValue(inParam.intfcInvtyDt, this.batDt);
            NMXC104001ConvertPartsMdseCdBean convMdseCdBean = new NMXC104001ConvertPartsMdseCdBean();
            // QC#30963 Add glblCmpyCd
            convMdseCdBean.setGlblCmpyCd(this.glblCmpyCd);
            convMdseCdBean.setMode(NMXC104001ConvertPartsMdseCd.MODE_MDSE);
            convMdseCdBean.setMdseCd(rs.getString("MDSE_CD"));
            // STAR 05/16/2016 R.Shimamoto [_V2.0 ADD]
            convMdseCdBean.setCusaVndCd(rs.getString("CUSA_VND_CD"));
            // END 05/16/2016 R.Shimamoto [_V2.0 ADD]
            NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(convMdseCdBean);
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(inParam.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(inParam.intfcInTrnstFlg, rs.getString("INTFC_IN_TRNST_FLG"));
            // START 11/20/2013 K.Kishimoto [QC3148, MOD]
            // ZYPEZDItemValueSetter.setValue(inParam.prtInvtyCatgCd,
            // VALUE_PRT_INVTY_CATG_CD);
            // QC#26966 Update.
            if (VALUE_PRT_INVTY_CATG_CD_A1.equals(rs.getString("PRT_INVTY_CATG_CD")) //
                    && !glblWhCdClmbus.equals(rs.getString("CINC_GLBL_WH_CD"))) {
                ZYPEZDItemValueSetter.setValue(inParam.prtInvtyCatgCd, VALUE_PRT_INVTY_CATG_CD_AZ);
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.prtInvtyCatgCd, rs.getString("PRT_INVTY_CATG_CD"));
            }
            // END 11/20/2013 K.Kishimoto [QC3148, MOD]
            ZYPEZDItemValueSetter.setValue(inParam.prtChrgInd, VALUE_PRT_CHRG_IND);
            ZYPEZDItemValueSetter.setValue(inParam.intfcInvtyStkQty, rs.getBigDecimal("INTFC_INVTY_QTY_TXT"));

            if (inParam.intfcInvtyStkQty.getValue().compareTo(BigDecimal.ZERO) < 0) {

                ZYPEZDItemValueSetter.setValue(inParam.intfcQtySgnTxt, VALUE_INTFC_QTY_SGN_TXT_MINUS);

            } else {

                ZYPEZDItemValueSetter.setValue(inParam.intfcQtySgnTxt, VALUE_INTFC_QTY_SGN_TXT_PLUS);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        return inParam;
    }

    private PRT_INVTY_WRKTMsg setDeleteValue(ResultSet rs) {

        PRT_INVTY_WRKTMsg inParam = new PRT_INVTY_WRKTMsg();

        try {

            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.intfcCratDt, rs.getString("INTFC_CRAT_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.cincGlblWhCd, rs.getString("CINC_GLBL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.intfcInvtyDt, rs.getString("INTFC_INVTY_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
            ZYPEZDItemValueSetter.setValue(inParam.intfcInTrnstFlg, rs.getString("INTFC_IN_TRNST_FLG"));
            ZYPEZDItemValueSetter.setValue(inParam.prtInvtyCatgCd, rs.getString("PRT_INVTY_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prtChrgInd, rs.getString("PRT_CHRG_IND"));

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        return inParam;
    }

    private void deletePartsDataOfPastDt() {

        List<PRT_INVTY_WRKTMsg> inTMsgList = new ArrayList<PRT_INVTY_WRKTMsg>();

        // get partsData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String preIntfcCratDt = getPreIntfcCratDt();

        if (!ZYPCommonFunc.hasValue(preIntfcCratDt)) {

            return;
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("preIntfcCratDt", preIntfcCratDt);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getPastData", paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int totalDeleteCount = 0;

            while (rs.next()) {

                inputCount++;
                inTMsgList.add(setDeleteValue(rs));

                if (this.commitNumber == inTMsgList.size()) {

                    deletePrtInvtyWrkFindByIntfcCratDt(inTMsgList);
                    totalDeleteCount += this.commitNumber;
                    inTMsgList.clear();
                }
            }

//            if (inputCount != totalDeleteCount) {
            if (inTMsgList.size() > 0) {

                deletePrtInvtyWrkFindByIntfcCratDt(inTMsgList);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private String getPreIntfcCratDt() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("intfcCratDt", this.batDt);
        return (String) ssmBatchClient.queryObject("getPreIntfcCratDt", ssmParam);
    }

    private void deletePrtInvtyWrkFindByIntfcCratDt(List<PRT_INVTY_WRKTMsg> inMsgLst) {

        PRT_INVTY_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_INVTY_WRKTMsg[inMsgLst.size()];
        int delCnt = S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));
        if (delCnt != inMsgLst.size()) {
            throw new S21AbendException(NLCM0131E,  new String[] {"PRT_INVTY_WRK"});
        }
        commit();
    }

    // START 06/03/2016 R.Shimamoto [V2.0 ADD]
    private String getCincGlblWhCdOther() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("rtlWhCd", "*");
        queryParam.put("whOwnrCd", "*");
        queryParam.put("locTpCd", "*");

        return (String) ssmBatchClient.queryObject("getCincGlblWhCdOther", queryParam);
    }
    // END 06/03/2016 R.Shimamoto [V2.0 ADD]
}
