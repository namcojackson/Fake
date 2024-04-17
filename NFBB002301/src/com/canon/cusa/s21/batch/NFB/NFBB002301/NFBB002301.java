/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB002301;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_DTLTMsgArray;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_INV_PMT_HLDTMsg;
import business.db.CM_INV_PMT_HLDTMsgArray;
import business.db.CM_MDSE_INVTY_IMPT_HISTTMsg;
import business.db.CM_STK_INTMsg;
import business.parts.NFBCommonBusiness;
import business.parts.NPZC004001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import static com.canon.cusa.s21.batch.NFB.NFBB002301.NFBB002301Constant.*;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch imports Merchandise Stock-in records from INVTY_TRX table and insert records into CM_STK_IN table.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2009   CUSA            Y.Aikawa        Create          N/A
 * 12/01/2015   CUSA            Y.Aikawa        Update          N/A
 * 2016/12/14   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/05/16   CITS            S.Endo          Update          RS#11
 * 2017/12/12   Hitachi         E.Kameishi      Update          QC#23052
 * 2019/09/17   Hitachi         Y.Takeno        Update          QC#51820
 * 2019/11/07   Fujitsu         H.Ikeda         Update          QC#54488
 * 2019/12/03   Fujitsu         H.Nagashima     Update          QC#54853
 * 2020/01/09   Fujitsu         M.Ishii         Update          QC#55230
 * </pre>
 */
public class NFBB002301 extends S21BatchMain {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;


    /** Termination Code */
    private TERM_CD termCd;

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** INVTY_TRX_PK For update */
    private BigDecimal bdUpdateHistoryPk;

    /** Original CM_HIST_TS */
    private String strOriginalHistoryTs = "0";

    /** New CM_HIST_TS */
    private String strNewHistoryTs = "0";

    /** CM_PROC_DT */
    private String cmProcDt = null;

    /** For handling CM_STK_IN Bulk TBL Accessor */
    private CM_STK_INTMsg[] cmStkInTMsgs = new CM_STK_INTMsg[INT_BULK_COM_LIM];

    /** CM_STK_IN Bulk Insert Count */
    private int iCntCmStkIn;

    /** Commit Count (CM_STK_IN) */
    private int cmStkInCommitCount;

    // START 2019/09/17 [QC#51820, ADD]
    /** CM_STK_IN Bulk Insert Count */
    private int iUpdateCntCmStkIn;

    /** Commit Count (CM_STK_IN) */
    private int cmStkInUpdateCommitCount;
    // END   2019/09/17 [QC#51820, ADD]

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        // Initialize SSM Batch client.
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Get User Profile Service Instance
        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        // Get Global Company Code
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        // Set Term Code
        this.termCd = TERM_CD.NORMAL_END;

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");
        // START 2020/01/09 M.Ishii [QC#55230, MOD]
        // START 2017/12/12 E.Kameishi [QC#23052, MOD]
        // Get CM_PROC_DT from CM_PROC_DT table.
        //getCmProcDt();
//        cmProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        cmProcDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        // END 2017/12/12 E.Kameishi [QC#23052, MOD]
        // END 2020/01/09 M.Ishii [QC#55230, MOD]

        // START 2019/09/17 [QC#51820, ADD]
        Map<String, String> queryParamUpdateCmStkIn = new HashMap<String, String>();
        queryParamUpdateCmStkIn.put("glblCmpyCd", this.glblCmpyCd);
        queryParamUpdateCmStkIn.put("procDt", cmProcDt);
        queryParamUpdateCmStkIn.put("dsPoTpCd", DS_PO_TP.BLANKET_PO);
        iUpdateCntCmStkIn = 0;
        cmStkInUpdateCommitCount = 0;

        Boolean bRet0 = (Boolean) ssmBatchClient.queryObject(UPDATE_CM_STK_IN, queryParamUpdateCmStkIn, new UpdateCmStkInHandler());
        if (bRet0 == Boolean.TRUE) {
            if (iUpdateCntCmStkIn > 0) {
                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmStkInTMsgs, iUpdateCntCmStkIn);
                if (iRet > 0) {
                    cmStkInUpdateCommitCount = cmStkInUpdateCommitCount + iRet;
                } else {
                    cmStkInUpdateCommitCount = 0;
                    throw new S21AbendException(NFBM0028E);
                }
            }
            commit();
        } else {
            rollback();
        }
        cmStkInTMsgs = new CM_STK_INTMsg[INT_BULK_COM_LIM];
        // END   2019/09/17 [QC#51820, ADD]

        // ** Get IMPT_INV_PK from CM_MDSE_INVTY_IMPT_HIST table.
        Map<String, String> queryMapCmMdseInvtyImptHist = new HashMap<String, String>();
        queryMapCmMdseInvtyImptHist.put("glblCmpyCd", glblCmpyCd);

        Boolean bRet1 = (Boolean) ssmBatchClient.queryObject("getCmHistTsFromHistory", queryMapCmMdseInvtyImptHist, new CmMdseInvtyImptHistRsHandler());

        if (bRet1 == Boolean.TRUE) {

            // Get Merchandise Stock-in records from INVTY_TRX table
            // and insert records into CM_STK_IN table.
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("cmHistTs", strOriginalHistoryTs);

            if (GLBL_CMPY.CBS_EAST_INC.equals(this.glblCmpyCd)) {
                queryParam.put("csaFlg", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("csaFlg", null);
            }

            // START 2019/11/07 [QC#54488, ADD]
            queryParam.put("invtyTrxDt", getProcMonth());
            // END   2019/11/07 [QC#54488, ADD]

            Boolean bRet2 = (Boolean) ssmBatchClient.queryObject(SELECT_CM_STK_IN, queryParam, new SelectCmStkInHandler());

            if (bRet2 == Boolean.TRUE) {

                if (iCntCmStkIn > 0) {
                    int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmStkInTMsgs, iCntCmStkIn);
                    if (iRet > 0) {
                        cmStkInCommitCount = cmStkInCommitCount + iRet;
                    } else {
                        cmStkInCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }
                }

                if (cmStkInCommitCount > 0) {
                    // Insert records into CM_MDSE_INVTY_IMPT_HIST
                    // table.
                    insertCmMdseInvtyImptHist();
                }
                commit();
            } else {
                rollback();
            }
        }
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    // START 2019/11/07 [QC#54488, ADD]
    /**
     * getProcMonth
     * 
     * @return String
     */
    private String getProcMonth () {
        String rtnStr = "";
        if (ZYPCommonFunc.hasValue(cmProcDt) && cmProcDt.length() >= 6) {
            rtnStr = cmProcDt.substring(0,6);
        }
        return rtnStr;
    }
    // END   2019/11/07 [QC#54488, ADD]

    /**
     * Private class: SelectCmStkIn
     */
    private class SelectCmStkInHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                CM_STK_INTMsg cmStkIn = new CM_STK_INTMsg();
                ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInPk, rs.getBigDecimal(CM_STK_IN_PK));
                ZYPEZDItemValueSetter.setValue(cmStkIn.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmStkIn.vndCd, rs.getString(VND_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invtyLocCd, rs.getString(INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.delyOrdNum, rs.getString(DELY_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.prodLineProdCtrlCd, rs.getString(PROD_LINE_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.mdseOrPrtCd, rs.getString(MDSE_OR_PRT_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.vndRefNum, rs.getString(PO_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.imptDoSoNum, rs.getString(IMPT_DO_SO_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.acctRecCnt, rs.getBigDecimal(ACCT_REC_CNT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInErrFlg, rs.getString(STK_IN_ERR_FLG));
                ZYPEZDItemValueSetter.setValue(cmStkIn.vndInvNum, rs.getString(VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.vndInvSqNum, rs.getString(VND_INV_SQ_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invRcvDt, cmProcDt);
                ZYPEZDItemValueSetter.setValue(cmStkIn.coaProdCd, rs.getString(COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.acctYrMth, rs.getString(ACCT_YR_MTH));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invLocRcvDt, rs.getString(INV_LOC_RCV_DT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invSendCd, rs.getString(INV_SEND_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.lcDaNum, rs.getString(LC_DA_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.vndShipViaPrchCd, rs.getString(VND_SHIP_VIA_PRCH_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.actlFobCd, rs.getString(ACTL_FOB_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.acctShpgTermCd, rs.getString(ACCT_SHPG_TERM_CD));
                cmStkIn.origDelyOrdNum.clear();
                cmStkIn.origVndInvNum.clear();
                cmStkIn.origVndInvSqNum.clear();
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInRsnDescTxt, rs.getString(STK_IN_RSN_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invScFobCostAmt, rs.getBigDecimal(INV_SC_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invScFrtCostAmt, rs.getBigDecimal(INV_SC_FRT_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invScInsCostAmt, rs.getBigDecimal(INV_SC_INS_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invOcFobCostAmt, rs.getBigDecimal(INV_OC_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invOcFrtCostAmt, rs.getBigDecimal(INV_OC_FRT_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invOcInsCostAmt, rs.getBigDecimal(INV_OC_INS_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invQty, rs.getBigDecimal(INV_QTY));
                ZYPEZDItemValueSetter.setValue(cmStkIn.fobCcyCd, rs.getString(FOB_CCY_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.frtCcyCd, rs.getString(FRT_CCY_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.insCcyCd, rs.getString(INS_CCY_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.rcvQty, rs.getBigDecimal(RCV_QTY));
                ZYPEZDItemValueSetter.setValue(cmStkIn.slsPrmoQty, rs.getBigDecimal(SLS_PRMO_QTY));
                ZYPEZDItemValueSetter.setValue(cmStkIn.acMdseFobCostAmt, rs.getBigDecimal(AC_MDSE_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.plnScFobCostAmt, rs.getBigDecimal(PLN_SC_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.plnScFrtCostAmt, rs.getBigDecimal(PLN_SC_FRT_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.plnScInsCostAmt, rs.getBigDecimal(PLN_SC_INS_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.plnScDtyCostAmt, rs.getBigDecimal(PLN_SC_DTY_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.plnScOthCostAmt, rs.getBigDecimal(PLN_SC_OTH_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInScFobCostAmt, rs.getBigDecimal(STK_IN_SC_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInScFrtCostAmt, rs.getBigDecimal(STK_IN_SC_FRT_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInScInsCostAmt, rs.getBigDecimal(STK_IN_SC_INS_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInScDtyCostAmt, rs.getBigDecimal(STK_IN_SC_DTY_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInScOthCostAmt, rs.getBigDecimal(STK_IN_SC_OTH_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.slsPrmoScFobCostAmt, rs.getBigDecimal(SLS_PRMO_SC_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.slsPrmoScFrtCostAmt, rs.getBigDecimal(SLS_PRMO_SC_FRT_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.slsPrmoScInsCostAmt, rs.getBigDecimal(SLS_PRMO_SC_INS_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.slsPrmoScDtyCostAmt, rs.getBigDecimal(SLS_PRMO_SC_DTY_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.slsPrmoScOthCostAmt, rs.getBigDecimal(SLS_PRMO_SC_OTH_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.psetVndFlg, rs.getString(PSET_VND_FLG));
                ZYPEZDItemValueSetter.setValue(cmStkIn.invAsgFlg, rs.getString(INV_ASG_FLG));
                cmStkIn.invAsgDt.clear();
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInItemDelFlg, rs.getString(STK_IN_ITEM_DEL_FLG));
                cmStkIn.stkInItemDelDt.clear();
                ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInCpltFlg, ZYPConstant.FLG_OFF_N);
                cmStkIn.cmStkInCpltDt.clear();
                ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInOnlProcFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmStkIn.psetAsgFlg, rs.getString(PSET_ASG_FLG));
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(PSET_ASG_FLG))) {
                    ZYPEZDItemValueSetter.setValue(cmStkIn.psetAsgDt, cmProcDt);
                }
                ZYPEZDItemValueSetter.setValue(cmStkIn.trxCd, rs.getString(TRX_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.trxRsnCd, rs.getString(TRX_RSN_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.poOrdNum, rs.getString(PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.poOrdDtlLineNum, rs.getString(PO_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.vndRtrnNum, rs.getString(VND_RTRN_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.trxLineNum, rs.getString(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInVarCalcFlg, rs.getString(CM_STK_IN_VAR_CALC_FLG));
                ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInVarFrceCalcFlg, rs.getString(CM_STK_IN_VAR_FRCE_CALC_FLG));
                ZYPEZDItemValueSetter.setValue(cmStkIn.stkInAsgErrRsnCd, rs.getString(STK_IN_ASG_ERR_RSN_CD));
                ZYPEZDItemValueSetter.setValue(cmStkIn.poNum, rs.getString(PO_NUM));
                ZYPEZDItemValueSetter.setValue(cmStkIn.poQty, rs.getBigDecimal(PO_QTY));
                ZYPEZDItemValueSetter.setValue(cmStkIn.thisMthFobCostAmt, rs.getBigDecimal(THIS_MTH_FOB_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.entDealNetUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.entPoDtlDealNetAmt, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.entFuncNetUnitPrcAmt, rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.entPoDtlFuncNetAmt, rs.getBigDecimal(ENT_PO_DTL_FUNC_NET_AMT));
                ZYPEZDItemValueSetter.setValue(cmStkIn.exchRate, rs.getBigDecimal(EXCH_RATE));
                // START 2019/12/03 [QC#54853, ADD]
                ZYPEZDItemValueSetter.setValue(cmStkIn.invtyTrxPk, rs.getBigDecimal(INVTY_TRX_PK));
                // END   2019/12/03 [QC#54853, ADD]

                cmStkInTMsgs[iCntCmStkIn] = cmStkIn;
                iCntCmStkIn++;
                if (iCntCmStkIn == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.insert(cmStkInTMsgs);
                    if (iRet > 0) {
                        cmStkInTMsgs = new CM_STK_INTMsg[INT_BULK_COM_LIM];
                        cmStkInCommitCount = cmStkInCommitCount + iRet;
                        iCntCmStkIn = 0;
                    } else {
                        cmStkInCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }
                }

                // If the last record of CM_IF_MDSE_PSET_INV_HDR
                // table
                // finished, Get INVTY_TRX_PK to update
                // CM_MDSE_INVTY_IMPT_HIST table.
                if (rs.isLast()) {
                    strNewHistoryTs = rs.getString(EZINTIME);
                    bdUpdateHistoryPk = rs.getBigDecimal(INVTY_TRX_PK);
                }

            }
            return Boolean.TRUE;
        }
    }

    /**
     * <pre>
     *  Get Original INVTY_TRX_PK from CM_MDSE_INVTY_IMPT_HIST table.
     * </pre>
     */
    private class CmMdseInvtyImptHistRsHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            if (rs.next()) {
                if (ZYPCommonFunc.hasValue(rs.getString(CM_HIST_TS))) {
                    strOriginalHistoryTs = rs.getString(CM_HIST_TS);
                }

                if (rs.next()) {
                    // Unexpected Error
                    // History record count must be just 1.
                    throw new S21AbendException(NFBM0028E);
                }
                return Boolean.TRUE;
            } else {
                // If there is no records, set 0 up.
                strOriginalHistoryTs = "0";
                return Boolean.TRUE;
            }

        }
    }

    /**
     * <pre>
     *  Insert INVTY_TRX_PK of CM_MDSE_INVTY_IMPT_HIST table. 
     * </pre>
     */
    protected void insertCmMdseInvtyImptHist() {

        CM_MDSE_INVTY_IMPT_HISTTMsg cmMdseInvtyImptHist = new CM_MDSE_INVTY_IMPT_HISTTMsg();

        cmMdseInvtyImptHist.glblCmpyCd.setValue(glblCmpyCd);
        cmMdseInvtyImptHist.invtyTrxPk.setValue(bdUpdateHistoryPk);
        cmMdseInvtyImptHist.cmProcDt.setValue(cmProcDt);
        cmMdseInvtyImptHist.cmHistTs.setValue(strNewHistoryTs);

        EZDTBLAccessor.insert(cmMdseInvtyImptHist);
        // START 2016/12/14 [QC#15823, ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmMdseInvtyImptHist.getReturnCode())) {
            throw new S21AbendException(NFZM0028E, new String[]{"CM_MDSE_INVTY_IMPT_HIST"});
        }
        // END   2016/12/14 [QC#15823, ADD]
    }
    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        // START 2019/09/17 [QC#51820, MOD]
        // setTermState(this.termCd, cmStkInCommitCount, 0, 0);
        setTermState(this.termCd, cmStkInUpdateCommitCount + cmStkInCommitCount, 0, 0);
        // END   2019/09/17 [QC#51820, MOD]

        S21InfoLogOutput.println("termRoutine Method End");

    }

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFBB002301().executeBatch(NFBB002301.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");

    }

    // START 2019/09/17 [QC#51820, ADD]
    /**
     * Private class: UpdateCmStkInHandler
     */
    private class UpdateCmStkInHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                CM_STK_INTMsg cmStkInTMsg = new CM_STK_INTMsg();
                ZYPEZDItemValueSetter.setValue(cmStkInTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmStkInTMsg.cmStkInPk, rs.getBigDecimal(CM_STK_IN_PK));
                cmStkInTMsg = (CM_STK_INTMsg) EZDTBLAccessor.findByKeyForUpdate(cmStkInTMsg);
                if (cmStkInTMsg != null) {
                    updateCmStkIn(rs, cmStkInTMsg);
                    updateAuthorizedAPInvoices(rs);
                }
            }
            return Boolean.TRUE;
        }

        private void updateCmStkIn(ResultSet rs, CM_STK_INTMsg cmStkInTMsg) throws SQLException {
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.poQty, rs.getBigDecimal(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.thisMthFobCostAmt, rs.getBigDecimal(THIS_MTH_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.entDealNetUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.entPoDtlDealNetAmt, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.entFuncNetUnitPrcAmt, rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.entPoDtlFuncNetAmt, rs.getBigDecimal(ENT_PO_DTL_FUNC_NET_AMT));
            ZYPEZDItemValueSetter.setValue(cmStkInTMsg.exchRate, rs.getBigDecimal(EXCH_RATE));

            cmStkInTMsgs[iUpdateCntCmStkIn] = cmStkInTMsg;
            iUpdateCntCmStkIn++;
            if (iUpdateCntCmStkIn == INT_BULK_COM_LIM) {
                int iRet = S21FastTBLAccessor.update(cmStkInTMsgs);
                if (iRet > 0) {
                    cmStkInTMsgs = new CM_STK_INTMsg[INT_BULK_COM_LIM];
                    cmStkInUpdateCommitCount = cmStkInUpdateCommitCount + iRet;
                    iUpdateCntCmStkIn = 0;
                } else {
                    cmStkInUpdateCommitCount = 0;
                    throw new S21AbendException(NFBM0028E);
                }
            }
        }

        private void updateAuthorizedAPInvoices(ResultSet rs) throws SQLException {
            String poOrdNum = rs.getString(PO_ORD_NUM);
            String poOrdDtlLineNum = rs.getString(PO_ORD_DTL_LINE_NUM);

            for (Map<String, Object> keyMap : findAuthorizedApInvoices(poOrdNum, poOrdDtlLineNum)) {
                String apVndCd       = (String) keyMap.get(AP_VND_CD);
                String apVndInvNum   = (String) keyMap.get(AP_VND_INV_NUM);
                String apVndInvSqNum = (String) keyMap.get(AP_VND_INV_SQ_NUM);

                // Hold AP Invoice
                holdApInvoice(apVndCd, apVndInvNum, apVndInvSqNum);

                // call PO Status Update API (cancel Invoiced Qty)
                CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgAry = findCmApInvDtlList(apVndCd, apVndInvNum, apVndInvSqNum);
                for (int idx = 0 ; idx < cmApInvDtlTMsgAry.getValidCount() ; idx++) {
                    CM_AP_INV_DTLTMsg cmApInvDtlTMsg = (CM_AP_INV_DTLTMsg) cmApInvDtlTMsgAry.get(idx);
                    String dtlPoNum = cmApInvDtlTMsg.poNum.getValue();
                    String dtlPoOrdDtlLineNum = cmApInvDtlTMsg.poOrdDtlLineNum.getValue();
                    BigDecimal dtlInvQty = cmApInvDtlTMsg.invQty.getValue();

                    if (!ZYPCommonFunc.hasValue(dtlPoNum) || !ZYPCommonFunc.hasValue(dtlPoOrdDtlLineNum)) {
                        continue;
                    }

                    if (BigDecimal.ZERO.compareTo(dtlInvQty) == 0) {
                        continue;
                    }

                    String msgId = updatePoInformation(dtlPoNum, dtlPoOrdDtlLineNum, dtlInvQty.negate());
                    if (msgId != null) {
                        throw new S21AbendException(msgId);
                    }
                }
            }
        }
    }

    private List<Map<String, Object>> findAuthorizedApInvoices(String poNum, String poOrdDtlLineNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poNum", poNum);
        queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        queryParam.put("acctInvStsCd", ACCT_INV_STS.AUTHORIZED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(FIND_AUTHORIZED_AP_INVOICES, queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<Map<String, Object>>();
    }

    private void holdApInvoice(String apVndCd, String apVndInvNum, String apVndInvSqNum) {

        // update CM_INV_PMT_HLD
        CM_INV_PMT_HLDTMsg param = new CM_INV_PMT_HLDTMsg();
        param.setSQLID("003");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("apVndCd01", apVndCd);
        param.setConditionValue("apVndInvNum01", apVndInvNum);
        param.setConditionValue("apVndInvSqNum01", apVndInvSqNum);
        param.setConditionValue("pmtHldFlg01", ZYPConstant.FLG_OFF_N);
        param.setConditionValue("pmtHldCd01", PMT_HLD.THEREFORE);
        CM_INV_PMT_HLDTMsgArray holdArray = (CM_INV_PMT_HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
        if (holdArray != null) {
            for (int i = 0 ; i < holdArray.getValidCount() ; i++) {
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = holdArray.no(i);

                if (PMT_HLD.SUCCESS.equals(cmInvPmtHldTMsg.pmtHldCd.getValue())) {
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, PSN_CD_NFBB0023);
                cmInvPmtHldTMsg.pmtHldRelDt.clear();
                cmInvPmtHldTMsg.pmtHldRelRsnCd.clear();
                cmInvPmtHldTMsg.pmtHldRelPsnCd.clear();
                cmInvPmtHldTMsg.pmtHldRelCmntTxt.clear();
                S21FastTBLAccessor.update(cmInvPmtHldTMsg);
            }
        }

        // Update CM_AP_INV_HDR
        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, apVndCd);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, apVndInvNum);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, apVndInvSqNum);

        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
        if (cmApInvHdrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(cmApInvHdrTMsg);
        }
    }

    /**
     * Find Cost Management Accounts Payable Invoice Detail
     * @param apVndCd String
     * @param apVndInvNum String
     * @param apVndInvSqNum String
     * @return CM_AP_INV_DTLTMsgArray
     */
    private CM_AP_INV_DTLTMsgArray findCmApInvDtlList(String apVndCd, String apVndInvNum, String apVndInvSqNum) {
        CM_AP_INV_DTLTMsg inMsg = new CM_AP_INV_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("apVndCd01", apVndCd);
        inMsg.setConditionValue("apVndInvNum01", apVndInvNum);
        inMsg.setConditionValue("apVndInvSqNum01", apVndInvSqNum);
        return (CM_AP_INV_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * updatePoInformation
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param poInvQty BigDecimal
     * @return xxMsgId String
     */
    private String updatePoInformation(String poOrdNum, String poOrdDtlLineNum, BigDecimal poInvQty) {
        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, poInvQty);
        NPZC004001 apiPOUpdate = new NPZC004001();
        apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // Error
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)
                && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)
                ) {
                    return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                }
            }
        }
        return null;
    }
    // END   2019/09/17 [QC#51820, ADD]

    // START 2017/12/12 E.Kameishi [QC#23052, DEL]
//    /**
//     * <pre>
//     * Get CM_PROC_DT from CM_PROC_DT table.
//     * </pre>
//     * 
//     * @param
//     */
//    private boolean getCmProcDt() {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//
//        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_PROC_DT, queryParam, new SelectProcDtRsHandler());
//
//        return bRet;
//
//    }
//
//    /**
//     * <pre>
//     * Check if there is a record in CM_PROC_DT table.
//     * </pre>
//     */
//    private class SelectProcDtRsHandler extends S21SsmBooleanResultSetHandlerSupport {
//        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
//
//            if (rs.next()) {
//                cmProcDt = rs.getString(CM_PROC_DT);
//            }
//
//            return true;
//
//        }
//    }
    // END 2017/12/12 E.Kameishi [QC#23052, DEL]

}
