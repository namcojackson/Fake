/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB128001;

import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.AP_INV_AMT;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.AP_VND_INV_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.CM_INV_ACCT_DIST_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.INV_BOL_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.INV_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.INV_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.INV_LINE_SUB_TRX_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.INV_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.NFBM0044E;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.NFBM0199E;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.NFBM0207E;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.NFZM0027E;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.NFZM0028E;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_BR_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_CC_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_CH_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_CMPY_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_EXTN_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_PROD_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.SPLY_COA_PROJ_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.STR_00;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.STR_EMPTY;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.STR_NONE;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.VARCHAR_CONST_AJE_COA_DEF_VALUES;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.VARCHAR_CONST_AP_LINE_TP_ITEM;
import static com.canon.cusa.s21.batch.NFB.NFBB128001.constant.NFBB128001Constant.YYYYMMDD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AP_LSE_BO_WF_RQSTTMsg;
import business.db.CCYTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_DEF_ACCTTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.COA_ACCTTMsg;
import business.db.CPO_DTL_VTMsg;
import business.db.CPO_DTL_VTMsgArray;
import business.db.MDSETMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * AP Invoice Data Creation for LeaseBuyout
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Hitachi         K.Kojima        Create          N/A
 * 2016/03/16   Hitachi         K.Kojima        Update          CSA QC#5297
 * 2016/11/14   Hitachi         K.Kasai         Update          CSA QC#15785
 * 2016/12/12   Fujitsu         H.Ikeda         Update          CSA QC#15823
 * 2017/01/16   Fujitsu         T.Murai         Update          CSA QC#17091
 * 2018/03/22   CITS            T.Kikuhara      Update          CSA QC#20316
 * 2018/08/07   CITS            K.Ogino         Update          CSA QC#27280
 * 2018/09/05   Fujitsu         S.Ohki          Update          CSA QC#28040
 * </pre>
 */
public class NFBB128001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales date */
    private String batProcDate = null;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    private String varCharConstApLineTpItem;

    // START 2017/01/17 [QC#17091,ADD]
    /** VAR_CHAR_CONST : AJE_COA_DEF_VALUES */
    private String varCharConstAjeCoaDefVal;
    // END 2017/01/17 [QC#17091,ADD]

    @Override
    protected void initRoutine() {
        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0199E);
        }

        // "Batch Process Date"
        this.batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0207E, new String[] {"Batch Process Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get VAR_CHAR_CONST : AP_LINE_TP_ITEM
        varCharConstApLineTpItem = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_ITEM, glblCmpyCd);

        // START 2017/01/17 [QC#17091,ADD]
        // Get VAR_CHAR_CONST : AJE_COA_DEF_VALUES
        varCharConstAjeCoaDefVal = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AJE_COA_DEF_VALUES, glblCmpyCd);
        // END 2017/01/17 [QC#17091,ADD]
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AP_LSE_BO_WF_RQSTTMsg> rqstTMsgList = new ArrayList<AP_LSE_BO_WF_RQSTTMsg>();
        // START 2018/03/22 [QC#20316, DEL]
        //List<CM_INV_ACCT_HDRTMsg> headerTMsgList = new ArrayList<CM_INV_ACCT_HDRTMsg>();
        // END 2018/03/22 [QC#20316, DEL]
        List<CM_INV_ACCT_DISTTMsg> distTMsgList = new ArrayList<CM_INV_ACCT_DISTTMsg>();
        List<CM_AP_INV_HDRTMsg> cmApInvHdrList = new ArrayList<CM_AP_INV_HDRTMsg>();
        List<CM_AP_INV_DTLTMsg> cmApInvDtlList = new ArrayList<CM_AP_INV_DTLTMsg>();

        CM_DEF_ACCTTMsg defTMsg = getCmDefAcctTMsg();

        try {
            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", this.glblCmpyCd);
            stesParam.put("apDsWfStsCd", AP_DS_WF_STS.APPROVED);

            ps = this.ssmLLClient.createPreparedStatement("getLeaseBuyoutLine", stesParam, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {
                AP_LSE_BO_WF_RQSTTMsg rqstTMsg = getApLseBoWfRstTMsg(rs);
                MDSETMsg mdseTMsg = getMdseTMsg(rqstTMsg);
                // START 2016/11/14 [QC#15785, ADD]
                CPO_DTL_VTMsg dsCpoDtlVTMsg = getdsCpoDtlVTMsg(rqstTMsg);
                // END 2016/11/14 [QC#15785, ADD]
                // START 2018/03/22 [QC#20316, DEL]
                //CM_INV_ACCT_HDRTMsg headerTMsg = createHeaderTMsg(rqstTMsg, rs);
                // END 2018/03/22 [QC#20316, DEL]
                // START 2016/11/14 [QC#15785, MOD]
                CM_INV_ACCT_DISTTMsg distTMsg = createDistTMsg(rqstTMsg, rs, mdseTMsg, defTMsg, dsCpoDtlVTMsg);
                // END 2016/11/14 [QC#15785, MOD]

                CM_AP_INV_HDRTMsg cmApInvHdrTMsg = createCmApInvHdrTMsg(rqstTMsg, rs);
                CM_AP_INV_DTLTMsg cmApInvDtlTMsg = createCmApInvDtlTMsg(rqstTMsg, rs, dsCpoDtlVTMsg, cmApInvHdrTMsg);

                setValue(rqstTMsg.apDsWfStsCd, AP_DS_WF_STS.COMPLETED);

                rqstTMsgList.add(rqstTMsg);
                // START 2018/03/22 [QC#20316, DEL]
                //headerTMsgList.add(headerTMsg);
                // END 2018/03/22 [QC#20316, DEL]
                distTMsgList.add(distTMsg);
                cmApInvHdrList.add(cmApInvHdrTMsg);
                cmApInvDtlList.add(cmApInvDtlTMsg);

                if (this.commitNumber == rqstTMsgList.size()) {
                    // START 2018/03/22 [QC#20316, MOD]
                    //executeInsertUpdate(rqstTMsgList, headerTMsgList, distTMsgList, cmApInvHdrList, cmApInvDtlList);
                    executeInsertUpdate(rqstTMsgList, distTMsgList, cmApInvHdrList, cmApInvDtlList);
                    // END 2018/03/22 [QC#20316, MOD]
                    this.normalCount += rqstTMsgList.size();
                    rqstTMsgList = new ArrayList<AP_LSE_BO_WF_RQSTTMsg>();
                    // START 2018/03/22 [QC#20316, DEL]
                    //headerTMsgList = new ArrayList<CM_INV_ACCT_HDRTMsg>();
                    // END 2018/03/22 [QC#20316, DEL]
                    distTMsgList = new ArrayList<CM_INV_ACCT_DISTTMsg>();
                    cmApInvHdrList = new ArrayList<CM_AP_INV_HDRTMsg>();
                    cmApInvDtlList = new ArrayList<CM_AP_INV_DTLTMsg>();
                }
            }
            if (rqstTMsgList.size() > 0) {
                // START 2018/03/22 [QC#20316, MOD]
                //executeInsertUpdate(rqstTMsgList, headerTMsgList, distTMsgList, cmApInvHdrList, cmApInvDtlList);
                executeInsertUpdate(rqstTMsgList, distTMsgList, cmApInvHdrList, cmApInvDtlList);
                // END 2018/03/22 [QC#20316, MOD]
                this.normalCount += rqstTMsgList.size();
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB128001().executeBatch(NFBB128001.class.getSimpleName());
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
     * getApLseBoWfRstTMsg
     * @param rs ResultSet
     * @return AP_LSE_BO_WF_RQSTTMsg
     * @throws SQLException SQLException
     */
    private AP_LSE_BO_WF_RQSTTMsg getApLseBoWfRstTMsg(ResultSet rs) throws SQLException {
        AP_LSE_BO_WF_RQSTTMsg tmsg = new AP_LSE_BO_WF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.cpoOrdNum, rs.getString(CPO_ORD_NUM));
        // START 2016/03/16 K.Kojima [QC#5297,MOD]
        // setValue(tmsg.cpoOrdTpCd, rs.getString(CPO_ORD_TP_CD));
        // setValue(tmsg.cpoDtlLineNum,
        // rs.getString(CPO_DTL_LINE_NUM));
        // setValue(tmsg.cpoOrdTs, rs.getString(CPO_ORD_TS));
        // setValue(tmsg.dsOrdLineCatgCd,
        // rs.getString(DS_ORD_LINE_CATG_CD));
        // setValue(tmsg.apvlTpTxt, rs.getString(APVL_TP_TXT));
        setValue(tmsg.cpoDtlLineNum, rs.getString(CPO_DTL_LINE_NUM));
        setValue(tmsg.invNum, rs.getString(INV_NUM));
        setValue(tmsg.invBolNum, rs.getString(INV_BOL_NUM));
        setValue(tmsg.invLineNum, rs.getString(INV_LINE_NUM));
        setValue(tmsg.invLineSubNum, rs.getString(INV_LINE_SUB_NUM));
        setValue(tmsg.invLineSubTrxNum, rs.getString(INV_LINE_SUB_TRX_NUM));
        // END 2016/03/16 K.Kojima [QC#5297,MOD]
        return (AP_LSE_BO_WF_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tmsg);
    }

    // START 2018/03/22 [QC#20316, DEL]
    ///**
    // * createHeaderTMsg
    // * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
    // * @param rs ResultSet
    // * @return CM_INV_ACCT_HDRTMsg
    // * @throws SQLException SQLException
    // */
    //private CM_INV_ACCT_HDRTMsg createHeaderTMsg(AP_LSE_BO_WF_RQSTTMsg rqstTMsg, ResultSet rs) throws SQLException {
    //    CM_INV_ACCT_HDRTMsg tMsg = new CM_INV_ACCT_HDRTMsg();
    //    setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
    //    setValue(tMsg.apVndCd, rqstTMsg.vndCd);
    //    setValue(tMsg.apVndInvNum, rqstTMsg.apInvNum);
    //    setValue(tMsg.apVndInvSqNum, STR_00);
    //    setValue(tMsg.invDt, this.batProcDate);
    //    setValue(tMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
    //    setValue(tMsg.acInvTotCostAmt, rqstTMsg.apInvAmt);
    //    setValue(tMsg.coaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
    //    setValue(tMsg.coaBrCd, rs.getString(SPLY_COA_BR_CD));
    //    setValue(tMsg.coaCcCd, rs.getString(SPLY_COA_CC_CD));
    //    setValue(tMsg.coaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
    //    setValue(tMsg.coaProdCd, rs.getString(SPLY_COA_PROD_CD));
    //    setValue(tMsg.coaChCd, rs.getString(SPLY_COA_CH_CD));
    //    setValue(tMsg.coaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
    //    setValue(tMsg.coaProjCd, rs.getString(SPLY_COA_PROJ_CD));
    //    setValue(tMsg.coaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
    //    setValue(tMsg.apInvCatgCd, "05");
    //    setValue(tMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
    //    setValue(tMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
    //    setValue(tMsg.invAuthDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
    //    setValue(tMsg.prntVndCd, getPrntVndCd(tMsg.apVndCd.getValue()));
    //    setValue(tMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
    //    setValue(tMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
    //    return tMsg;
    //}
    // END 2018/03/22 [QC#20316, DEL]

    /**
     * createCmApInvHdrTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @return CM_AP_INV_HDRTMsg
     * @throws SQLException SQLException
     */
    private CM_AP_INV_HDRTMsg createCmApInvHdrTMsg(AP_LSE_BO_WF_RQSTTMsg rqstTMsg, ResultSet rs) throws SQLException {
        CM_AP_INV_HDRTMsg tMsg = new CM_AP_INV_HDRTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.apVndCd, rqstTMsg.vndCd);
        setValue(tMsg.apVndInvNum, rqstTMsg.apInvNum);
        setValue(tMsg.apVndInvSqNum, STR_00);
        setValue(tMsg.apInvTpCd, STR_00);
        setValue(tMsg.invDt, this.batProcDate);
        setValue(tMsg.ccyCd, rs.getString("CCY_CD"));
        setValue(tMsg.lastTrxDt, NFBCommonBusiness.getCmProcDt(this.glblCmpyCd));
        setValue(tMsg.acOcTotFobCostAmt, rs.getBigDecimal("AP_INV_AMT"));
        setValue(tMsg.acOcTotTaxAmt, BigDecimal.ZERO);
        setValue(tMsg.acOcTotGndCostAmt, tMsg.acOcTotFobCostAmt);
        setValue(tMsg.acOcTotGndInvAmt, tMsg.acOcTotFobCostAmt);
        setValue(tMsg.acScTotFobCostAmt, calcScFob(tMsg.acOcTotFobCostAmt.getValue(), tMsg.ccyCd.getValue(), tMsg.invDt.getValue()));
        setValue(tMsg.acScTotTaxAmt, BigDecimal.ZERO);
        setValue(tMsg.acScTotGndCostAmt, tMsg.acScTotFobCostAmt);
        setValue(tMsg.acScTotGndInvAmt, tMsg.acScTotFobCostAmt);
        setValue(tMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.poNum, STR_NONE);

        // START 2018/03/22 [QC#20316, ADD]
        setValue(tMsg.acInvTotCostAmt, rqstTMsg.apInvAmt);
        setValue(tMsg.apInvCatgCd, "05");
        setValue(tMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
        setValue(tMsg.invAuthDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        setValue(tMsg.prntVndCd, getPrntVndCd(tMsg.apVndCd.getValue()));
        setValue(tMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/03/22 [QC#20316, ADD]
        // QC#27280
        setValue(tMsg.vndPmtTermCd, rs.getString("VND_PMT_TERM_CD"));

        return tMsg;
    }

    /**
     * createCmApInvDtlTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @return CM_AP_INV_DTLTMsg
     * @throws SQLException SQLException
     */
    private CM_AP_INV_DTLTMsg createCmApInvDtlTMsg(AP_LSE_BO_WF_RQSTTMsg rqstTMsg, ResultSet rs, CPO_DTL_VTMsg cpoDtlVTMsg, CM_AP_INV_HDRTMsg hdrTmsg) throws SQLException {
        CM_AP_INV_DTLTMsg tMsg = new CM_AP_INV_DTLTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.apVndCd, rqstTMsg.vndCd);
        setValue(tMsg.apVndInvNum, rqstTMsg.apInvNum);
        setValue(tMsg.apVndInvSqNum, STR_00);
        setValue(tMsg.vndCd, rqstTMsg.vndCd);
        setValue(tMsg.vndInvNum, rqstTMsg.apInvNum);
        setValue(tMsg.mdseCd, rqstTMsg.mdseCd);
        setValue(tMsg.delyOrdNum, STR_NONE);
        setValue(tMsg.apInvTpCd, STR_00);
        setValue(tMsg.poNum, STR_NONE);
        setValue(tMsg.invQty, cpoDtlVTMsg.invQty);
        setValue(tMsg.acOcFobCostAmt, rs.getBigDecimal("AP_INV_AMT"));
        setValue(tMsg.acOcTaxAmt, BigDecimal.ZERO);
        setValue(tMsg.acOcTotCostAmt, tMsg.acOcFobCostAmt);
        setValue(tMsg.acOcTotInvAmt, tMsg.acOcFobCostAmt);
        setValue(tMsg.acScFobCostAmt, calcScFob(tMsg.acOcFobCostAmt.getValue(), hdrTmsg.ccyCd.getValue(), hdrTmsg.invDt.getValue()));
        setValue(tMsg.acScTaxAmt, BigDecimal.ZERO);
        setValue(tMsg.acScTotCostAmt, tMsg.acScFobCostAmt);
        setValue(tMsg.acScTotInvAmt, tMsg.acScFobCostAmt);
        setValue(tMsg.applyExchRate, getExchRate(hdrTmsg.ccyCd.getValue(), hdrTmsg.invDt.getValue()));
        setValue(tMsg.trnstJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.stkInJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.invRcvQty, BigDecimal.ZERO);
        setValue(tMsg.origScFobCostAmt, tMsg.acScFobCostAmt);

        if (BigDecimal.ZERO.compareTo(tMsg.invQty.getValue()) == 0) {
            setValue(tMsg.ocUnitExtCostAmt, BigDecimal.ZERO);
            setValue(tMsg.ocExtCostAmt, tMsg.acOcFobCostAmt);
            setValue(tMsg.scUnitExtCostAmt, BigDecimal.ZERO);
        } else {
            BigDecimal ocFobCostAmt = tMsg.acOcFobCostAmt.getValue();
            BigDecimal scFobCostAmt = tMsg.acScFobCostAmt.getValue();
            BigDecimal qty = tMsg.invQty.getValue();
            int scale = getCcyScale(hdrTmsg.ccyCd.getValue());
            setValue(tMsg.ocUnitExtCostAmt, ocFobCostAmt.divide(qty, scale, BigDecimal.ROUND_HALF_UP));
            setValue(tMsg.ocExtCostAmt, tMsg.acOcFobCostAmt);
            setValue(tMsg.scUnitExtCostAmt, scFobCostAmt.divide(qty, scale, BigDecimal.ROUND_HALF_UP));
        }

        setValue(tMsg.poQty, BigDecimal.ZERO);
        setValue(tMsg.thisMthFobCostAmt, BigDecimal.ZERO);
        setValue(tMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
        setValue(tMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
        setValue(tMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
        setValue(tMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
        setValue(tMsg.exchRate, BigDecimal.ZERO);
        tMsg.poOrdDtlLineNum.clear();
        // START 2018/03/22 [QC#20316, MOD]
        //setValue(tMsg.apVndInvLineNum, rs.getString("CM_INV_ACCT_DIST_LINE_NUM"));
        setValue(tMsg.apVndInvLineNum, rs.getString(AP_VND_INV_LINE_NUM));
        // END 2018/03/22 [QC#20316, MOD]
        setValue(tMsg.mdseDescShortTxt, cpoDtlVTMsg.mdseDescShortTxt);

        return tMsg;
    }

    /**
     * calcScFob
     * @param amt BigDecimal
     * @param ccyCd String
     * @param invDt String
     * @return ScFob Amount BigDecimal
     */
    private BigDecimal calcScFob(BigDecimal amt, String ccyCd, String invDt) {
        // Calculate SC-FOB

        BigDecimal exchRate = getExchRate(ccyCd, invDt);
        CCYTMsg ccyMsg = NFBCommonBusiness.getCcyInfo(this.glblCmpyCd, ccyCd);
        String acctArthTpCd = ccyMsg.acctArthTpCd.getValue();
        BigDecimal aftDeclPntDigitNum = ccyMsg.aftDeclPntDigitNum.getValue();

        return NFBCommonBusiness.calcStdAmt(amt, exchRate, acctArthTpCd, aftDeclPntDigitNum);
    }

    /**
     * getExchRate
     * @param ccyCd String
     * @param invDt String
     * @return exchRate BigDecimal
     */
    private BigDecimal getExchRate(String ccyCd, String invDt) {
        BigDecimal exchRate = NFBCommonBusiness.getAcctDlyActlExchRate(this.glblCmpyCd, ccyCd, invDt);
        String cmProcDt = NFBCommonBusiness.getCmProcDt(this.glblCmpyCd);
        if (exchRate == null) {
            exchRate = NFBCommonBusiness.getAcctDlyActlExchRate(this.glblCmpyCd, ccyCd, cmProcDt);
            if (exchRate == null) {
                exchRate = BigDecimal.ONE;
            }
        }
        return exchRate;
    }

    /**
     * createDistTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @param rs ResultSet
     * @param mdseTMsg MDSETMsg
     * @param defTMsg CM_DEF_ACCTTMsg
     * @param dsCpoDtlVTMsg CPO_DTL_VTMsg
     * @return CM_INV_ACCT_DISTTMsg
     * @throws SQLException SQLException
     */
    private CM_INV_ACCT_DISTTMsg createDistTMsg(AP_LSE_BO_WF_RQSTTMsg rqstTMsg, ResultSet rs, MDSETMsg mdseTMsg, CM_DEF_ACCTTMsg defTMsg, CPO_DTL_VTMsg dsCpoDtlVTMsg) throws SQLException {
        CM_INV_ACCT_DISTTMsg tMsg = new CM_INV_ACCT_DISTTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.apVndCd, rqstTMsg.vndCd);
        setValue(tMsg.apVndInvNum, rqstTMsg.apInvNum);
        setValue(tMsg.apVndInvSqNum, STR_00);
        setValue(tMsg.mdseCd, rqstTMsg.mdseCd);
        // START 2016/11/14 [QC#15785, ADD]
        if (dsCpoDtlVTMsg != null) {
            setValue(tMsg.mdseDescShortTxt, dsCpoDtlVTMsg.mdseDescShortTxt);
        }
        // END 2016/11/14 [QC#15785, ADD]
        setValue(tMsg.apInvTpCd, STR_00);
        setValue(tMsg.poNum, STR_NONE);
        setValue(tMsg.delyOrdNum, STR_NONE);
        // START 2016/11/14 [QC#15785, MOD]
        setValue(tMsg.invQty, BigDecimal.ZERO);
        if (dsCpoDtlVTMsg != null) {
            setValue(tMsg.invQty, dsCpoDtlVTMsg.invQty);
        }
        // END 2016/11/14 [QC#15785, MOD]
        setValue(tMsg.poQty, BigDecimal.ZERO);
        setValue(tMsg.invRcvQty, BigDecimal.ZERO);
        // START 2016/11/14 [QC#15785, MOD]
        setValue(tMsg.apBillQty, BigDecimal.ZERO);
        if (dsCpoDtlVTMsg != null) {
            setValue(tMsg.apBillQty, dsCpoDtlVTMsg.invQty);
        }
        // END 2016/11/14 [QC#15785, MOD]
        setValue(tMsg.apRejQty, BigDecimal.ZERO);

        // START 2018/03/22 [QC#20316, MOD]
        if (defTMsg != null) {
            setValue(tMsg.drCoaCmpyCd, defTMsg.cmCoaCmpyCd);
            setValue(tMsg.drCoaBrCd, defTMsg.cmCoaBrCd);
            setValue(tMsg.drCoaCcCd, defTMsg.cmCoaCcCd);
            setValue(tMsg.drCoaProdCd, defTMsg.cmCoaProdCd);
            setValue(tMsg.drCoaChCd, defTMsg.cmCoaChCd);
            setValue(tMsg.drCoaAfflCd, defTMsg.cmCoaAfflCd);
            setValue(tMsg.drCoaProjCd, defTMsg.cmCoaProjCd);
            setValue(tMsg.drCoaExtnCd, defTMsg.cmCoaExtnCd);
        }
        if (mdseTMsg != null) {
            setValue(tMsg.drCoaAcctCd, mdseTMsg.revCoaAcctCd);

            // START 2018/09/06 [QC#28040, ADD]
            String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, mdseTMsg.revCoaAcctCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.apAcctDescTxt, coaAcctDescTxt);

            EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
            int txtDigit = getDigitApInvDescTxt.getDigit();

            if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                ZYPEZDItemValueSetter.setValue(tMsg.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.apInvDescTxt, coaAcctDescTxt);
            }
            // END 2018/09/06 [QC#28040, ADD]
        }
        // END 2018/03/22 [QC#20316, MOD]

        setValue(tMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.acInvJrnlCostAmt, rs.getBigDecimal(AP_INV_AMT));

        setValue(tMsg.apLineTpCd, varCharConstApLineTpItem);
        // START 2018/03/22 [QC#20316, ADD]
        setValue(tMsg.apVndInvLineNum, rs.getString(AP_VND_INV_LINE_NUM));
        // END 2018/03/22 [QC#20316, ADD]
        // START 2016/11/14 [QC#15785, ADD]
        setValue(tMsg.cmInvAcctDistLineNum, rs.getString(CM_INV_ACCT_DIST_LINE_NUM));
        // END 2016/11/14 [QC#15785, ADD]

        // START 2018/03/22 [QC#20316, ADD]
        setValue(tMsg.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
        setValue(tMsg.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
        setValue(tMsg.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
        setValue(tMsg.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
        setValue(tMsg.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
        setValue(tMsg.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
        setValue(tMsg.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
        setValue(tMsg.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
        setValue(tMsg.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
        // END 2018/03/22 [QC#20316, ADD]
        return tMsg;
    }

    /**
     * getCmDefAcctTMsg -- Make Default Values
     * @return CM_DEF_ACCTTMsg
     */
    private CM_DEF_ACCTTMsg getCmDefAcctTMsg() {

        // START 2017/01/16 [QC#17091,MOD]
        // CM_DEF_ACCTTMsg tmsg = new CM_DEF_ACCTTMsg();
        // setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        // setValue(tmsg.cmDefAcctCd, CM_DEF_ACCT_CD_LEASE);
        // return (CM_DEF_ACCTTMsg) S21ApiTBLAccessor.findByKey(tmsg);
        CM_DEF_ACCTTMsg tmsg = new CM_DEF_ACCTTMsg();

        StringTokenizer st = new StringTokenizer(varCharConstAjeCoaDefVal, ",");

        int cnt = 0;
        String val;
        while (st.hasMoreTokens()) {
            val = st.nextToken();
            cnt++;
            switch (cnt) {
                case 1:
                    setValue(tmsg.cmCoaCmpyCd, val);
                    break;
                case 2:
                    setValue(tmsg.cmCoaBrCd, val);
                    break;
                case 3:
                    setValue(tmsg.cmCoaCcCd, val);
                    break;
                case 4:
                    break;
                case 5:
                    setValue(tmsg.cmCoaProdCd, val);
                    break;
                case 6:
                    setValue(tmsg.cmCoaChCd, val);
                    break;
                case 7:
                    setValue(tmsg.cmCoaAfflCd, val);
                    break;
                case 8:
                    setValue(tmsg.cmCoaProjCd, val);
                    break;
                case 9:
                    setValue(tmsg.cmCoaExtnCd, val);
                    break;
                default:
                    break;
            }
        }

        return tmsg;
        // END 2017/01/16 [QC#17091,MOD]
    }

    /**
     * getMdseTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @return MDSETMsg
     */
    private MDSETMsg getMdseTMsg(AP_LSE_BO_WF_RQSTTMsg rqstTMsg) {
        MDSETMsg tmsg = new MDSETMsg();
        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.mdseCd, rqstTMsg.mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    // START 2016/11/14 [QC#15785, ADD]

    /**
     * getdsCpoDtlVTMsg
     * @param rqstTMsg AP_LSE_BO_WF_RQSTTMsg
     * @return CPO_DTL_VTMsg
     */
    private CPO_DTL_VTMsg getdsCpoDtlVTMsg(AP_LSE_BO_WF_RQSTTMsg rqstTMsg) {
        CPO_DTL_VTMsg tmsg = new CPO_DTL_VTMsg();
        tmsg.setSQLID("503");
        tmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tmsg.setConditionValue("cpoOrdNum01", rqstTMsg.cpoOrdNum.getValue());
        tmsg.setConditionValue("cpoDtlLineNum01", rqstTMsg.cpoDtlLineNum.getValue());
        CPO_DTL_VTMsgArray outTMsgArray = (CPO_DTL_VTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
        if (outTMsgArray.length() == 0) {
            return null;
        }
        return outTMsgArray.no(0);
    }
    // END 2016/11/14 [QC#15785, ADD]

    /**
     * executeInsertUpdate
     * @param rqstTMsgList List<AP_LSE_BO_WF_RQSTTMsg>
     * @param distTMsgList List<CM_INV_ACCT_DISTTMsg>
     * @param cmApInvDtlList
     * @param cmApInvHdrList
     */
    private void executeInsertUpdate(//
            List<AP_LSE_BO_WF_RQSTTMsg> rqstTMsgList //
            // START 2018/03/22 [QC#20316, DEL]
            //, List<CM_INV_ACCT_HDRTMsg> headerTMsgList //
            // END 2018/03/22 [QC#20316, DEL]
            , List<CM_INV_ACCT_DISTTMsg> distTMsgList //
            , List<CM_AP_INV_HDRTMsg> cmApInvHdrList //
            , List<CM_AP_INV_DTLTMsg> cmApInvDtlList) {
        // START 2018/03/22 [QC#20316, DEL]
        //CM_INV_ACCT_HDRTMsg[] headerList = new CM_INV_ACCT_HDRTMsg[headerTMsgList.size()];
        // END 2018/03/22 [QC#20316, DEL]
        CM_INV_ACCT_DISTTMsg[] distList = new CM_INV_ACCT_DISTTMsg[distTMsgList.size()];
        AP_LSE_BO_WF_RQSTTMsg[] rqstList = new AP_LSE_BO_WF_RQSTTMsg[rqstTMsgList.size()];
        CM_AP_INV_HDRTMsg[] cmApInvHdrArray = new CM_AP_INV_HDRTMsg[cmApInvHdrList.size()];
        CM_AP_INV_DTLTMsg[] cmApInvDtlArray = new CM_AP_INV_DTLTMsg[cmApInvDtlList.size()];

        // START 2016/12/12 [QC#15823, MOD]
        int rCnt = 0;
        // insert CM_INV_ACCT_HDR Array
        // START 2018/03/22 [QC#20316, DEL]
        //rCnt = S21FastTBLAccessor.insert(headerTMsgList.toArray(headerList));
        //if (rCnt < 1) {
        //    throw new S21AbendException(NFZM0028E, new String[] {"CM_INV_ACCT_HDR" });
        //}
        // END 2018/03/22 [QC#20316, DEL]
        // insert CM_INV_ACCT_DIST Array
        rCnt = S21FastTBLAccessor.insert(distTMsgList.toArray(distList));
        if (rCnt < 1) {
            throw new S21AbendException(NFZM0028E, new String[] {"CM_INV_ACCT_DIST" });
        }
        // insert CM_AP_INV_HDR Array
        rCnt = S21FastTBLAccessor.insert(cmApInvHdrList.toArray(cmApInvHdrArray));
        if (rCnt < 1) {
            throw new S21AbendException(NFZM0028E, new String[] {"CM_AP_INV_HDR" });
        }
        // insert CM_AP_INV_DTL Array
        rCnt = S21FastTBLAccessor.insert(cmApInvDtlList.toArray(cmApInvDtlArray));
        if (rCnt < 1) {
            throw new S21AbendException(NFZM0028E, new String[] {"CM_AP_INV_DTL" });
        }
        // Update AP_LSE_BO_WF_RQST Array
        rCnt = S21FastTBLAccessor.update(rqstTMsgList.toArray(rqstList));
        if (rCnt < 1) {
            throw new S21AbendException(NFZM0027E, new String[] {"AP_LSE_BO_WF_RQST" });
        }
        // END 2016/12/12 [QC#15823, MOD]
        commit();
    }

    /**
     * getPrntVndCd
     * @param invVndCd String
     * @return String
     */
    private String getPrntVndCd(String invVndCd) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", this.glblCmpyCd);
            stesParam.put("invVndCd", invVndCd);
            ps = this.ssmLLClient.createPreparedStatement("getPrntVndCd", stesParam, execParam());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(PRNT_VND_CD);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return STR_EMPTY;
    }
    
    /**
     * Get the scale of Currency Code from CCY Master.
     * @param ccyCd
     * @return
     */
    private int getCcyScale(String ccyCd) {

        CCYTMsg tMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, ccyCd);
        tMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            throw new S21AbendException(NFBM0044E, new String[] {"Currency Code:" + ccyCd });
        }
        return tMsg.aftDeclPntDigitNum.getValueInt();
    }

    // START 2018/09/06 [QC#28040, ADD]
    /**
     * getCoaAcctDescTxt
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @retrun coaAcctDescTxt String
     */
    public static String getCoaAcctDescTxt(String glblCmpyCd, String coaAcctCd) {

        String rtnCoaAcctDescTxt = null;
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(coaAcctCd)) {
            return null;
        }
        COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, coaAcctCd);

        coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
        if (coaAcct != null) {
            rtnCoaAcctDescTxt = coaAcct.coaAcctDescTxt.getValue();
        }
        return rtnCoaAcctDescTxt;
    }
    // END 2018/09/06 [QC#28040, ADD]
}
