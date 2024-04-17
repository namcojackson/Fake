/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.PO_BIZ_PROC_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Create the PO History.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   CITS            T.Tokuomi       Create
 * 2016/05/06   CSAI            K.Lee           Update          QC#5762
 *</pre>
 */
public class NPXC001001CreatePOHistory {

    /**
     * DEBUG_MSG_LVL : 8
     */
    private static final int DEBUG_MSG_LVL = 8;

    /**
     * PROGRAM_ID
     */
    private static final String PROGRAM_ID = "NPXC001001CreatePOHistory";

    /**
     * FORMAT_TIMESTAMP
     */
    private static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmss";

    /**
     * MAX_LENGTH_SERNUMLIST
     */
    private static final int MAX_LENGTH_SERNUMLIST = 3000;

    /**
     * MAX_LENGTH_USER_ID
     */
    private static final int MAX_LENGTH_USERID = 8;

    /**
     * Create the PO History
     * @param glblCmpyCode Global Company Code
     * @param eventId Event ID
     * @param poOrdNum PO Order Number
     * @param poOrdDtlLineNum PO Order Detail Line Number
     * @return Return Code (0:Normal, other:Error)
     */
    public static int createPOHistory(String glblCmpyCode //
            , String eventId//
            , String poOrdNum//
            , String poOrdDtlLineNum) {

        // Param check
        if (!ZYPCommonFunc.hasValue(glblCmpyCode)) {
            debugLog("no input parameter GLBL_CMPY_CD");
            return 1;
        }
        if (!ZYPCommonFunc.hasValue(eventId)) {
            debugLog("no input parameter Event ID");
            return 1;
        }
        if (!ZYPCommonFunc.hasValue(poOrdNum)) {
            debugLog("no input parameter PO_ORD_NUM");
            return 1;
        }

        // Get PO Header
        S21SsmEZDResult poHeaderRslt = NPXC001001CreatePOHistoryQuery.getInstance().getPoHeader(glblCmpyCode, poOrdNum);

        if (!poHeaderRslt.isCodeNormal()) {
            debugLog("No Data PO. PO#[" + poOrdNum + "]");
            return 1;
        }

        Map<String, Object> poHdr = (Map<String, Object>) poHeaderRslt.getResultObject();

        // Get PO Detail
        S21SsmEZDResult poDetailRslt = NPXC001001CreatePOHistoryQuery.getInstance().getPoDetail(glblCmpyCode, poOrdNum, poOrdDtlLineNum);

        if (!poDetailRslt.isCodeNormal()) {
            debugLog("No Data PO Dtl. PO#[" + poOrdNum + "]. PO_LINE#[" + poOrdDtlLineNum + "]");
            return 1;
        }

        List<Map<String, Object>> poDtls = (List<Map<String, Object>>) poDetailRslt.getResultObject();
        String serNum = null;
        Map<String, Object> poDtl = null;

        // Use First Record. Serial# = ser001,ser002....
        for (Map<String, Object> m : poDtls) {
            if (poDtl == null) {
                poDtl = m;
            }
            if (serNum == null) {
                serNum = (String) m.get("SER_NUM");
            } else {
                serNum = ", " + (String) m.get("SER_NUM");
            }
        }

        if (serNum != null && serNum.length() > MAX_LENGTH_SERNUMLIST) {
            serNum = serNum.substring(0, serNum.lastIndexOf(","));
            debugLog("Warning. Serial# list over.");
        }

        // Get UserInfo
        String usrId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        if (usrId.length() > MAX_LENGTH_USERID) {
            usrId = usrId.substring(0, MAX_LENGTH_USERID);
        }
        String sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP);

        // Insert PO History
        PO_BIZ_PROC_LOGTMsg poHist = new PO_BIZ_PROC_LOGTMsg();

        if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(poHist.glblCmpyCd, glblCmpyCode);
            ZYPEZDItemValueSetter.setValue(poHist.poBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("PO_BIZ_PROC_LOG_SQ"));
            ZYPEZDItemValueSetter.setValue(poHist.eventId, eventId);
            ZYPEZDItemValueSetter.setValue(poHist.poBizProcLogUpdTs, sysTs);
            ZYPEZDItemValueSetter.setValue(poHist.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(poHist.poOrdDtlLineNum, poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(poHist.dispPoDtlLineNum, (String) poDtl.get("DISP_PO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.poSubmtPsnCd, usrId);
            ZYPEZDItemValueSetter.setValue(poHist.poHdrStsCd, (String) poHdr.get("PO_HDR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.poApvlStsCd, (String) poHdr.get("PO_APVL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.poApvlTs, (String) poHdr.get("PO_APVL_TS"));
            ZYPEZDItemValueSetter.setValue(poHist.poApvlPsnCd, (String) poHdr.get("PO_APVL_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.hdrPoStsCd, (String) poHdr.get("PO_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.prntVndCd, (String) poHdr.get("PRNT_VND_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.prntVndNm, (String) poHdr.get("PRNT_VND_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.vndCd, (String) poHdr.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.vndNm, (String) poHdr.get("VND_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.prchGrpCd, (String) poHdr.get("PRCH_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.lineBizTpCd, (String) poHdr.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.frtCondCd, (String) poDtl.get("FRT_COND_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.shpgSvcLvlCd, (String) poDtl.get("SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.carrCd, (String) poDtl.get("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.carrAcctNum, (String) poDtl.get("CARR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.poOrdCmntTxt, (String) poHdr.get("PO_ORD_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(poHist.vndIssOrdNum, (String) poHdr.get("VND_ISS_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.poSendFlg, (String) poHdr.get("PO_SEND_FLG"));
            ZYPEZDItemValueSetter.setValue(poHist.hdrPoSendTs, (String) poHdr.get("PO_SEND_TS"));
            ZYPEZDItemValueSetter.setValue(poHist.poPrintFlg, (String) poHdr.get("PO_PRINT_FLG"));
            ZYPEZDItemValueSetter.setValue(poHist.trsmtMethTpCd, (String) poHdr.get("TRSMT_METH_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.sendPoFaxNum, (String) poHdr.get("SEND_PO_FAX_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.sendPoEmlAddr, (String) poHdr.get("SEND_PO_EML_ADDR"));
            ZYPEZDItemValueSetter.setValue(poHist.poLineStsCd, (String) poDtl.get("PO_LINE_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.dtlPoStsCd, (String) poDtl.get("PO_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.invtyLocCd, (String) poDtl.get("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.destRtlWhCd, (String) poDtl.get("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.destRtlSwhCd, (String) poDtl.get("DEST_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.srcRtlWhCd, (String) poDtl.get("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.srcRtlSwhCd, (String) poDtl.get("SRC_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.shipToCustCd, (String) poDtl.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.shipToAcctNm, (String) poDtl.get("SHIP_TO_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.shipToLocNm, (String) poDtl.get("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.rqstRcvDt, (String) poDtl.get("RQST_RCV_DT"));
            ZYPEZDItemValueSetter.setValue(poHist.rqstRcvTm, (String) poDtl.get("RQST_RCV_TM"));
            ZYPEZDItemValueSetter.setValue(poHist.fromStkStsCd, (String) poDtl.get("FROM_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.toStkStsCd, (String) poDtl.get("TO_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.poMatchTpCd, (String) poDtl.get("PO_MATCH_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.mdseCd, (String) poDtl.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.origMdseCd, (String) poDtl.get("ORIG_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.aslMdseCd, (String) poDtl.get("ASL_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.poQty, (BigDecimal) poDtl.get("PO_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poDispQty, (BigDecimal) poDtl.get("PO_DISP_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poRcvQty, (BigDecimal) poDtl.get("PO_RCV_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poCancQty, (BigDecimal) poDtl.get("PO_CANC_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poBalQty, (BigDecimal) poDtl.get("PO_BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.ordQty, (BigDecimal) poDtl.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poInvQty, (BigDecimal) poDtl.get("PO_INV_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poInvBalQty, (BigDecimal) poDtl.get("PO_INV_BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(poHist.poDispUomCd, (String) poDtl.get("PO_DISP_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.thisMthFobCostAmt, (BigDecimal) poDtl.get("THIS_MTH_FOB_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(poHist.aslDtlPk, (BigDecimal) poDtl.get("ASL_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(poHist.aslUnitPrcAmt, (BigDecimal) poDtl.get("ASL_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(poHist.entDealNetUnitPrcAmt, (BigDecimal) poDtl.get("ENT_DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(poHist.entPoDtlDealNetAmt, (BigDecimal) poDtl.get("ENT_PO_DTL_DEAL_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(poHist.entFuncNetUnitPrcAmt, (BigDecimal) poDtl.get("ENT_FUNC_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(poHist.entPoDtlFuncNetAmt, (BigDecimal) poDtl.get("ENT_PO_DTL_FUNC_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(poHist.ccyCd, (String) poDtl.get("CCY_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.exchRate, (BigDecimal) poDtl.get("EXCH_RATE"));
            ZYPEZDItemValueSetter.setValue(poHist.poOrdDtlCmntTxt, (String) poDtl.get("PO_ORD_DTL_CMNT_TXT"));
            if (serNum != null) {
                ZYPEZDItemValueSetter.setValue(poHist.serNumListTxt, serNum);
            }
            ZYPEZDItemValueSetter.setValue(poHist.svcConfigMstrPk, (BigDecimal) poDtl.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(poHist.origPoOrdNum, (String) poDtl.get("ORIG_PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.origPoOrdDtlLineNum, (String) poDtl.get("ORIG_PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.origDispPoDtlLineNum, (String) poDtl.get("ORIG_DISP_PO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.vndPoAckStsCd, (String) poDtl.get("VND_PO_ACK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.vndInvtyLocCd, (String) poDtl.get("VND_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.vndIssPoOrdNum, (String) poDtl.get("VND_ISS_PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.dtlPoSendTs, (String) poDtl.get("PO_SEND_TS"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaCmpyCd, (String) poDtl.get("CHRG_COA_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaBrCd, (String) poDtl.get("CHRG_COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaCcCd, (String) poDtl.get("CHRG_COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaAcctCd, (String) poDtl.get("CHRG_COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaProdCd, (String) poDtl.get("CHRG_COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaChCd, (String) poDtl.get("CHRG_COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaAfflCd, (String) poDtl.get("CHRG_COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaProjCd, (String) poDtl.get("CHRG_COA_PROJ_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.chrgCoaExtnCd, (String) poDtl.get("CHRG_COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaCmpyCd, (String) poDtl.get("ACRL_COA_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaBrCd, (String) poDtl.get("ACRL_COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaCcCd, (String) poDtl.get("ACRL_COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaAcctCd, (String) poDtl.get("ACRL_COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaProdCd, (String) poDtl.get("ACRL_COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaChCd, (String) poDtl.get("ACRL_COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaAfflCd, (String) poDtl.get("ACRL_COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaProjCd, (String) poDtl.get("ACRL_COA_PROJ_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.acrlCoaExtnCd, (String) poDtl.get("ACRL_COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaCmpyCd, (String) poDtl.get("VAR_COA_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaBrCd, (String) poDtl.get("VAR_COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaCcCd, (String) poDtl.get("VAR_COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaAcctCd, (String) poDtl.get("VAR_COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaProdCd, (String) poDtl.get("VAR_COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaChCd, (String) poDtl.get("VAR_COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaAfflCd, (String) poDtl.get("VAR_COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaProjCd, (String) poDtl.get("VAR_COA_PROJ_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.varCoaExtnCd, (String) poDtl.get("VAR_COA_EXTN_CD"));

        } else {
            ZYPEZDItemValueSetter.setValue(poHist.glblCmpyCd, glblCmpyCode);
            ZYPEZDItemValueSetter.setValue(poHist.poBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("PO_BIZ_PROC_LOG_SQ"));
            ZYPEZDItemValueSetter.setValue(poHist.eventId, eventId);
            ZYPEZDItemValueSetter.setValue(poHist.poBizProcLogUpdTs, sysTs);
            ZYPEZDItemValueSetter.setValue(poHist.poOrdNum, poOrdNum);

            ZYPEZDItemValueSetter.setValue(poHist.poSubmtPsnCd, usrId);
            ZYPEZDItemValueSetter.setValue(poHist.poHdrStsCd, (String) poHdr.get("PO_HDR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.poApvlStsCd, (String) poHdr.get("PO_APVL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.poApvlTs, (String) poHdr.get("PO_APVL_TS"));
            ZYPEZDItemValueSetter.setValue(poHist.poApvlPsnCd, (String) poHdr.get("PO_APVL_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.hdrPoStsCd, (String) poHdr.get("PO_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.prntVndCd, (String) poHdr.get("PRNT_VND_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.prntVndNm, (String) poHdr.get("PRNT_VND_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.vndCd, (String) poHdr.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.vndNm, (String) poHdr.get("VND_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.prchGrpCd, (String) poHdr.get("PRCH_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.lineBizTpCd, (String) poHdr.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.frtCondCd, (String) poDtl.get("FRT_COND_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.shpgSvcLvlCd, (String) poDtl.get("SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.carrCd, (String) poDtl.get("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.carrAcctNum, (String) poDtl.get("CARR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.poOrdCmntTxt, (String) poHdr.get("PO_ORD_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(poHist.vndIssOrdNum, (String) poHdr.get("VND_ISS_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.poSendFlg, (String) poHdr.get("PO_SEND_FLG"));
            ZYPEZDItemValueSetter.setValue(poHist.hdrPoSendTs, (String) poHdr.get("PO_SEND_TS"));
            ZYPEZDItemValueSetter.setValue(poHist.poPrintFlg, (String) poHdr.get("PO_PRINT_FLG"));
            ZYPEZDItemValueSetter.setValue(poHist.trsmtMethTpCd, (String) poHdr.get("TRSMT_METH_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.sendPoFaxNum, (String) poHdr.get("SEND_PO_FAX_NUM"));
            ZYPEZDItemValueSetter.setValue(poHist.sendPoEmlAddr, (String) poHdr.get("SEND_PO_EML_ADDR"));

            ZYPEZDItemValueSetter.setValue(poHist.destRtlWhCd, (String) poDtl.get("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.srcRtlWhCd, (String) poDtl.get("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.shipToCustCd, (String) poDtl.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.shipToAcctNm, (String) poDtl.get("SHIP_TO_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.shipToLocNm, (String) poDtl.get("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(poHist.rqstRcvDt, (String) poDtl.get("RQST_RCV_DT"));
            ZYPEZDItemValueSetter.setValue(poHist.rqstRcvTm, (String) poDtl.get("RQST_RCV_TM"));

            ZYPEZDItemValueSetter.setValue(poHist.ccyCd, (String) poDtl.get("CCY_CD"));
            ZYPEZDItemValueSetter.setValue(poHist.exchRate, (BigDecimal) poDtl.get("EXCH_RATE"));
        }

        EZDTBLAccessor.insert(poHist);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poHist.getReturnCode())) {
            debugLog("insert error. [PO_BIZ_PROC_LOG]");
            return 1;
        }

        // regist the history
        return 0;
    }

    /**
     * @param logmsg String
     */
    private static void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, PROGRAM_ID + logmsg, NPXC001001CreatePOHistory.class);
    }
}
