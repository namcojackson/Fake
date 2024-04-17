/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC408001;

import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.CARR_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.COMMA;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.CPO_DPLY_LINE_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.CSA_RMA_RPT_WRK_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.CST_DEBUG_MSG_LVL;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.CTY_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DATE_TIME_FORMAT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DATE_TIME_FORMAT2;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DATE_TIME_FORMAT_14;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_COMMA;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_MSG_CTRL_TP_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_ORD_HDR_STS_CANCELED;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_ORD_HDR_STS_CLOSED;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_PROC_PGM_ID;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_PURGE_DATE;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_RWS_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_RWS_REF_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DB_PARAM_TRX_HDR_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DEBUG_LOG_HDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.DECIMAL_FORMAT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.ESC_PERIOD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_ADDR_01;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_ADDR_02;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_ADDR_03;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_ADDR_04;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_CTY_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_NM_01;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_POST_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_ST_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.IN_POUND_WT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.MDSE_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLAF0020_LINE_NUM_OF_1ST_PAGE;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLAF0020_LINE_NUM_OF_2ND_PAGE;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLAF0020_NUM_OF_DESC_LTR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLAF0020_PURGE_DT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2079E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2133E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2259E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2480E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2481E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2482E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2483E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2494W;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2495E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2496E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2497E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2498E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2499E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.NLZM2501E;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.PERIOD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.PKG_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.POST_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.PROC_PGM_ID;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RMA_COMMENT_LENGTH;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_CTY_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_LOC_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_POST_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_SCD_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_ST_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TO_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTRN_TRX_CMNT_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RWS_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RWS_QTY;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RWS_REF_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.SCHD_ETA_DT;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.SER_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.SPACE;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.ST_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.TRX_ORD_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.WH_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.RTL_WH_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_PSN_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001Constant.FROM_LOC_TEL_NUM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CSA_RMA_RPT_WRKTMsg;
import business.db.RPT_RMA_PRINT_LOGTMsg;
import business.parts.NLZC408001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MSG_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 *<pre>
 * NLZC4080:Create RMA Report Work API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2016   CITS            M.Naito         Create          N/A
 * 12/28/2016   CITS            Y.Fujii         Update          QC#16738
 * 02/08/2017   CITS            M.Naito         Update          QC#17498
 * 10/19/2017   CITS            T.Kikuhara      Update          QC#21195
 * 01/22/2018   CITS            K.Kameoka       Update          QC#22885
 * 04/16/2018   CITS            K.Kameoka       Update          QC#24216
 * 04/25/2018   CITS            K.Fukumura      Update          QC#25025
 *</pre>
 */
public class NLZC408001 extends S21ApiCommonBase {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Purge Date */
    private BigDecimal purgeDt = null;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** RMA Report Page Number */
    private BigDecimal curPageNum = BigDecimal.ONE;

    /** lineNumOf1stPage */
    private BigDecimal lineNumOf1stPage = null;

    /** lineNumOf2ndPage */
    private BigDecimal lineNumOf2ndPage = null;

    /** numOfDescLtr */
    private BigDecimal numOfDescLtr = null;

    /** S21SsmBatchClient instance. */
    private S21SsmBatchClient client = null;

    /**
     * <pre>Constructor</pre>
     * @param none
     * @throws none
     */
    public NLZC408001() {
        super();

        // initializes SSM Client.
        this.client = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Create PO Report API
     * Call execute(APAF007001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmMsg APAF007001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    public void execute(final List<NLZC408001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) throws SQLException {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Create PO Report API
     * </pre>
     * @param inpPrmPMsg APAF007001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @throws SQLException SQLException
     */
    public void execute(final NLZC408001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) throws SQLException {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);

        try {
            if (!checkInputCommon(msgMap)) {
                return;
            }
            // Remove Target Date Recored
            removeElanRptWrkRec(msgMap);

            //Insert RMA_RPT_WRK
            insertRmaRptWrk(inpPrmPMsg, msgMap);

            // Check CPO Status and Delete RPT_RMA_PRINT_LOG
            removeRmaPrintLogRec(msgMap);

        } finally {
            msgMap.flush();
        }
    }

    /**
     * common input parameter check
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputCommon(S21ApiMessageMap msgMap) {
        // input parameter PMsg
        NLZC408001PMsg inpPrmPMsg = (NLZC408001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd.getValue())) {
            this.addMsgId(msgMap, NLZM2259E);
            return false;
        }
        glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();
        // rwsRefNum
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.rwsRefNum.getValue())) {
            this.addMsgId(msgMap, NLZM2480E);
            return false;
        }
        //QC#21195 ADD START
        // rwsNum
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.rwsNum.getValue())) {
            this.addMsgId(msgMap, NLZM2133E);
            return false;
        }
        //QC#21195 ADD END
        // procPgmId
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.procPgmId.getValue())) {
            this.addMsgId(msgMap, NLZM2501E);
            return false;
        }
        // Get NumConstValue
        lineNumOf1stPage = ZYPCodeDataUtil.getNumConstValue(NLAF0020_LINE_NUM_OF_1ST_PAGE, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(lineNumOf1stPage)) {
            this.addMsgId(msgMap, NLZM2495E);
            return false;
        }
        lineNumOf2ndPage = ZYPCodeDataUtil.getNumConstValue(NLAF0020_LINE_NUM_OF_2ND_PAGE, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(lineNumOf2ndPage)) {
            this.addMsgId(msgMap, NLZM2496E);
            return false;
        }
        numOfDescLtr = ZYPCodeDataUtil.getNumConstValue(NLAF0020_NUM_OF_DESC_LTR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(numOfDescLtr) || BigDecimal.ZERO.compareTo(numOfDescLtr) == 0) {
            this.addMsgId(msgMap, NLZM2497E);
            return false;
        }

        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            this.addMsgId(msgMap, NLZM2079E);
            return false;
        }
        purgeDt = ZYPCodeDataUtil.getNumConstValue(NLAF0020_PURGE_DT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(purgeDt)) {
            this.addMsgId(msgMap, NLZM2481E);
            return false;
        }
        return true;
    }

    /**
     * Remove Purge Record
     */
    private void removeElanRptWrkRec(S21ApiMessageMap msgMap) throws SQLException {

        //Check Target Date Record
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        String purgeDtTm = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_14);
        purgeDtTm = ZYPDateUtil.addDays(purgeDtTm, purgeDt.negate().intValue());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(DB_PARAM_PURGE_DATE, purgeDtTm);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);
            preparedStatement = ssmLLClient.createPreparedStatement("getRemoveRec", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CSA_RMA_RPT_WRKTMsg tMsg = new CSA_RMA_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.csaRmaRptWrkPk, rs.getBigDecimal(CSA_RMA_RPT_WRK_PK));

                // Delete Target Date Record
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.addMsgId(msgMap, NLZM2499E);
                    return;
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    /**
     * insertRmaRptWrk
     * @param pMsg NLZC408001PMsg
     */
    private void insertRmaRptWrk(NLZC408001PMsg pMsg, S21ApiMessageMap msgMap) throws SQLException {

        // Get Main Data For RMA_RPT_WRK
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            //QC#21195 UPD START
            paramMap.put(DB_PARAM_RWS_NUM, pMsg.rwsNum.getValue());
            //QC#21195 UPD END
            paramMap.put(DB_PARAM_COMMA, COMMA);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRmaInfo", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            String trxOrdNum = null;
            String rtrnToCmntTxt = null;
            BigDecimal curLine = BigDecimal.ZERO;
            BigDecimal csaRmaRptLineNum = null;
            BigDecimal shipWtDplyTxt = null;
            BigDecimal totalRwsQty =  BigDecimal.ZERO;
            BigDecimal totalShipWt =  BigDecimal.ZERO;
            String totShipWtDplyTxt01 = null;
            String totShipWtDplyTxt02 = null;
            String totRtrnQtyDplyTxt = null;
            BigDecimal csaRmaRptWrkSq = null;
            BigDecimal rptPrintRqstSq = null;
            String rwsNum = null;
            String cpoOrdNum = null;
            String whCd = null;
            String carrCd = null;

            List<CSA_RMA_RPT_WRKTMsg> tMsgList =  new ArrayList<CSA_RMA_RPT_WRKTMsg>();

            int totalCount = 0;
            // Loop for Main data
            while (rs.next()) {
                totalCount++;
                // Sum totalRwsQty
                totalRwsQty = totalRwsQty.add(rs.getBigDecimal(RWS_QTY));
                // Sum shipWtDplyTxt
                shipWtDplyTxt = rs.getBigDecimal(IN_POUND_WT).multiply(rs.getBigDecimal(RWS_QTY));
                totalShipWt = totalShipWt.add(shipWtDplyTxt);

                // Set Page Number and current Line
                curLine = setPdfPageNumber(rs.getString(MDSE_DESC_SHORT_TXT), curLine);
                // Set csaRmaRptLineNum
                csaRmaRptLineNum = BigDecimal.valueOf(totalCount);

                // Get CSA_RMA_RPT_WRK_SQ
                csaRmaRptWrkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSA_RMA_RPT_WRK_SQ);
                if (totalCount == 1) {
                    rptPrintRqstSq = csaRmaRptWrkSq;
                    // Get CPO_ORD_NUM
                     trxOrdNum = rs.getString(TRX_ORD_NUM);
                     // Get RMA Comment
                     rtrnToCmntTxt = getRmaComment(trxOrdNum, msgMap);
                 }
                // Map Result Set Data To RMA_RPT_WRK
                tMsgList.add(setRmaRptData(rs, csaRmaRptWrkSq, rptPrintRqstSq, rtrnToCmntTxt, csaRmaRptLineNum, shipWtDplyTxt));
                rwsNum = rs.getString(RWS_NUM);
                cpoOrdNum = rs.getString(TRX_ORD_NUM);
                whCd = rs.getString(WH_CD);
                carrCd = rs.getString(CARR_CD);
            }

            if (totalCount != 0) {
                totRtrnQtyDplyTxt = new DecimalFormat(DECIMAL_FORMAT).format(totalRwsQty);
                totShipWtDplyTxt01 = new DecimalFormat(DECIMAL_FORMAT).format(totalShipWt);
                String[] totShipWtDplyTxt = totalShipWt.toPlainString().split(ESC_PERIOD);
                if (totShipWtDplyTxt.length > 1) {
                    totShipWtDplyTxt02 = PERIOD + totShipWtDplyTxt[1];
                }
                // Insert RMA_RPT_WRK
                insertRmaRptWrkRec(tMsgList, totRtrnQtyDplyTxt, totShipWtDplyTxt01, totShipWtDplyTxt02, msgMap);

                // Insert RPT_RMA_PRINT_LOG
                insertRmaPrintLogRec(pMsg, msgMap, rwsNum, cpoOrdNum);

                ZYPEZDItemValueSetter.setValue(pMsg.csaRmaRptPrintRqstSq, rptPrintRqstSq);
                ZYPEZDItemValueSetter.setValue(pMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(pMsg.carrCd, carrCd);
            } else {
                this.addMsgId(msgMap, NLZM2494W);
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    /**
     * setRmaRptData
     * @param rs ResultSet
     * @param elanRptWrkPk BigDecimal
     * @param prntSq BigDecimal
     * @return CSA_RMA_RPT_WRKTMsg
     */
    private CSA_RMA_RPT_WRKTMsg setRmaRptData(ResultSet rs, BigDecimal csaRmaRptWrkSq, BigDecimal rptPrintRqstSq, String rtrnToCmntTxt, BigDecimal csaRmaRptLineNum, BigDecimal shipWtDplyTxt) throws SQLException {

        CSA_RMA_RPT_WRKTMsg tMsg = new CSA_RMA_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.csaRmaRptWrkPk, csaRmaRptWrkSq);
        String shipToLocDplyNm = rs.getString(WH_CD);
        //QC#22885 MOD Start
//        if (ZYPCommonFunc.hasValue(rs.getString(SHIP_TO_LOC_NM))) {
//            shipToLocDplyNm = shipToLocDplyNm + SPACE +  rs.getString(SHIP_TO_LOC_NM);
//        }
        if (ZYPCommonFunc.hasValue(rs.getString(RTL_WH_NM))) {
            shipToLocDplyNm = shipToLocDplyNm + SPACE +  rs.getString(RTL_WH_NM);
        }
        //QC#22885 MOD  End
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocDplyNm, shipToLocDplyNm);
        String shipToLocDplyAddr01 = setFirstLineAddr(rs.getString(FIRST_LINE_ADDR), rs.getString(SCD_LINE_ADDR), rs.getString(THIRD_LINE_ADDR), rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocDplyAddr_01, shipToLocDplyAddr01);
        String shipToLocDplyAddr02 = setScdThirdFrthLineAddr(rs.getString(SCD_LINE_ADDR), rs.getString(THIRD_LINE_ADDR), rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocDplyAddr_02, shipToLocDplyAddr02);

        String shipToLocDplyAddr03 = outThirdLineAddr(rs.getString(CTY_ADDR), rs.getString(ST_CD), rs.getString(POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocDplyAddr_03, shipToLocDplyAddr03);
        ZYPEZDItemValueSetter.setValue(tMsg.rwsRefNum, rs.getString(RWS_REF_NUM));

        if (ZYPCommonFunc.hasValue(rs.getString(SCHD_ETA_DT))) {
            String schdEtaDtTxt = ZYPDateUtil.DateFormatter(rs.getString(SCHD_ETA_DT), ZYPDateUtil.TYPE_YYYYMMDD, DATE_TIME_FORMAT);
            ZYPEZDItemValueSetter.setValue(tMsg.schdEtaDtTxt, schdEtaDtTxt);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.fromLocNm_01, rs.getString(FROM_LOC_NM_01));
        String fromLocDplyAddr01 = setFirstLineAddr(rs.getString(FROM_LOC_ADDR_01), rs.getString(FROM_LOC_ADDR_02), rs.getString(FROM_LOC_ADDR_03), rs.getString(FROM_LOC_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.fromLocDplyAddr_01, fromLocDplyAddr01);

        String fromLocDplyAddr02 = setScdThirdFrthLineAddr(rs.getString(FROM_LOC_ADDR_02), rs.getString(FROM_LOC_ADDR_03), rs.getString(FROM_LOC_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.fromLocDplyAddr_02, fromLocDplyAddr02);

        String fromLocDplyAddr03 = outThirdLineAddr(rs.getString(FROM_LOC_CTY_ADDR), rs.getString(FROM_LOC_ST_CD), rs.getString(FROM_LOC_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fromLocDplyAddr_03, fromLocDplyAddr03);

        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        //QC#22885 MOD Start
        //ZYPEZDItemValueSetter.setValue(tMsg.rtrnToLocNm, rs.getString(RTRN_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToLocNm, rs.getString(RTL_WH_NM));
        //QC#22885 MOD End
        String rtrnToDplyAddr01 = setFirstLineAddr(rs.getString(RTRN_TO_FIRST_LINE_ADDR), rs.getString(RTRN_TO_SCD_LINE_ADDR), rs.getString(RTRN_TO_THIRD_LINE_ADDR), rs.getString(RTRN_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToDplyAddr_01, rtrnToDplyAddr01);

        String rtrnToDplyAddr02 = setScdThirdFrthLineAddr(rs.getString(RTRN_TO_SCD_LINE_ADDR), rs.getString(RTRN_TO_THIRD_LINE_ADDR), rs.getString(RTRN_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToDplyAddr_02, rtrnToDplyAddr02);

        String rtrnToDplyAddr03 = outThirdLineAddr(rs.getString(RTRN_TO_CTY_ADDR), rs.getString(RTRN_TO_ST_CD), rs.getString(RTRN_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToDplyAddr_03, rtrnToDplyAddr03);
        if (ZYPCommonFunc.hasValue(rtrnToCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCmntTxt, rtrnToCmntTxt);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, rs.getString(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDplyLineNum, rs.getString(CPO_DPLY_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnQtyDplyTxt, rs.getString(RWS_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, rs.getString(PKG_CD));

        String shipWtDplyTxt01 = new DecimalFormat(DECIMAL_FORMAT).format(shipWtDplyTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.shipWtDplyTxt_01, shipWtDplyTxt01);
        String[] shipWtDplyTxt02 = (shipWtDplyTxt.toPlainString()).split(ESC_PERIOD);
        if (shipWtDplyTxt02.length > 1) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipWtDplyTxt_02, PERIOD + shipWtDplyTxt02[1]);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.csaRmaRptPrintRqstSq, rptPrintRqstSq);
        ZYPEZDItemValueSetter.setValue(tMsg.csaRmaRptLineNum, csaRmaRptLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.csaRmaRptPgNum, this.curPageNum);

        //QC#24216 MOD Start
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnNm, rs.getString(FROM_LOC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTelNum, rs.getString(FROM_LOC_TEL_NUM));
        //QC#24216 MOD End

        return tMsg;
    }

    private String setFirstLineAddr(String firstLineAddr, String scdLineAddr, String thirdLineAddr, String frthLineAddr) {

        // check outThirdLineAddr
        String defultAddr = "";
        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            return firstLineAddr;
        }
        if (ZYPCommonFunc.hasValue(scdLineAddr)) {
            return scdLineAddr;
        }
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            return thirdLineAddr;
        }
        if (ZYPCommonFunc.hasValue(frthLineAddr)) {
            return frthLineAddr;
        }
        return defultAddr;
    }

    private String outThirdLineAddr(String ctyAddr, String stCd, String postCd) {

        // check outThirdLineAddr
        String outThirdLineAddr = "";
        // check ctyAddr
        if (ZYPCommonFunc.hasValue(ctyAddr)) {
            outThirdLineAddr = ctyAddr;
        }
        // check stCd
        if (ZYPCommonFunc.hasValue(stCd)) {
            if (ZYPCommonFunc.hasValue(outThirdLineAddr)) {
                outThirdLineAddr = outThirdLineAddr + COMMA;
            }
            outThirdLineAddr = outThirdLineAddr + stCd;
        }
        // check postCd
        if (ZYPCommonFunc.hasValue(postCd)) {
            if (ZYPCommonFunc.hasValue(outThirdLineAddr)) {
                outThirdLineAddr = outThirdLineAddr + SPACE;
            }
            outThirdLineAddr = outThirdLineAddr + postCd;
        }
        return outThirdLineAddr;
    }

    private String setScdThirdFrthLineAddr(String scdLineAddr, String thirdLineAddr, String frthLineAddr) {

        // check scdThirdFrthLineAddr
        String scdThirdFrthLineAddr = "";
        // check ScdLineAddr
        if (ZYPCommonFunc.hasValue(scdLineAddr)) {
            scdThirdFrthLineAddr = scdLineAddr;
        }
        // check ThirdLineAddr
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            if (ZYPCommonFunc.hasValue(scdThirdFrthLineAddr)) {
                scdThirdFrthLineAddr = scdThirdFrthLineAddr + SPACE;
            }
            scdThirdFrthLineAddr = scdThirdFrthLineAddr + thirdLineAddr;
        }
        // check FrthLineAddr
        if (ZYPCommonFunc.hasValue(frthLineAddr)) {
            if (ZYPCommonFunc.hasValue(scdThirdFrthLineAddr)) {
                scdThirdFrthLineAddr = scdThirdFrthLineAddr + SPACE;
            }
            scdThirdFrthLineAddr = scdThirdFrthLineAddr + frthLineAddr;
        }
        return scdThirdFrthLineAddr;
    }

    /**
     * insertRmaRptWrkRec
     * @param tMsgList List<CSA_RMA_RPT_WRKTMsg>
     * @param totalRwsQty String
     * @param totalShipWt_01 String
     * @param totalShipWt_02 String
     * @param msgMap S21ApiMessageMap
     */
    private void insertRmaRptWrkRec(List<CSA_RMA_RPT_WRKTMsg> tMsgList, String totalRwsQty, String totalShipWt01, String totalShipWt02, S21ApiMessageMap msgMap) {

        for (CSA_RMA_RPT_WRKTMsg data : tMsgList) {

            //Set TOT_CNT_CERT_TXT
            if (ZYPCommonFunc.hasValue(totalRwsQty)) {
                ZYPEZDItemValueSetter.setValue(data.totRtrnQtyDplyTxt, totalRwsQty);
            }
            //Set TOT_SHIP_WT_DPLY_TXT
            if (ZYPCommonFunc.hasValue(totalShipWt01)) {
                ZYPEZDItemValueSetter.setValue(data.totShipWtDplyTxt_01, totalShipWt01);
            }
            if (ZYPCommonFunc.hasValue(totalShipWt02)) {
                ZYPEZDItemValueSetter.setValue(data.totShipWtDplyTxt_02, totalShipWt02);
            }

            // Insert CSA_RMA_RPT_WRK
            EZDTBLAccessor.insert(data);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(data.getReturnCode())) {
                this.addMsgId(msgMap, NLZM2482E);
                return;
            }
        }
    }

    /**
     * Insert RPT_RMA_PRINT_LOG Record
     */
    private void insertRmaPrintLogRec(NLZC408001PMsg pMsg, S21ApiMessageMap msgMap, String rwsNum, String cpoOrdNum) {

        // Check Recored
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_PROC_PGM_ID, pMsg.procPgmId.getValue());
        ssmParam.put(DB_PARAM_RWS_NUM, rwsNum);
        ssmParam.put(DB_PARAM_RWS_REF_NUM, pMsg.rwsRefNum.getValue());
        BigDecimal result = (BigDecimal) this.client.queryObject("chkRmaPrintLogRec", ssmParam);
        if (ZYPCommonFunc.hasValue(result) && (BigDecimal.ZERO.compareTo(result) < 0)) {
            return;
        }

        // Insert RPT_RMA_PRINT_LOG Record
        RPT_RMA_PRINT_LOGTMsg tMsg = new RPT_RMA_PRINT_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, pMsg.procPgmId.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.rwsRefNum, pMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoOrdNum);
        String rptPrintTs = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT2);
        ZYPEZDItemValueSetter.setValue(tMsg.rptPrintTs, rptPrintTs);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            this.addMsgId(msgMap, NLZM2498E);
            return;
        }
    }

    /**
     * Remove RPT_RMA_PRINT_LOG Record
     */
    private void removeRmaPrintLogRec(S21ApiMessageMap msgMap) throws SQLException {

        //Check CPO Closed or Canceled Record
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(DB_PARAM_ORD_HDR_STS_CLOSED, ORD_HDR_STS.CLOSED);
        paramMap.put(DB_PARAM_ORD_HDR_STS_CANCELED, ORD_HDR_STS.CANCELLED);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRemoveRmaPrintLogRec", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                RPT_RMA_PRINT_LOGTMsg tMsg = new RPT_RMA_PRINT_LOGTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rwsRefNum, rs.getString(RWS_REF_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, rs.getString(RWS_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, rs.getString(PROC_PGM_ID));

                // Delete Target Date Record
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.addMsgId(msgMap, NLZM2483E);
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    /**
     * Get RMA Comment
     */
    private String getRmaComment(String cpoOrdNum, S21ApiMessageMap msgMap) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String rmaComment = null;

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(DB_PARAM_TRX_HDR_NUM, cpoOrdNum);
        paramMap.put(DB_PARAM_MSG_CTRL_TP_CD, MSG_CTRL_TP.RMA_COMMENT);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRtrnCmnt", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (!ZYPCommonFunc.hasValue(rmaComment)) {
                    rmaComment = rs.getString(RTRN_TRX_CMNT_TXT);
                } else {
                    rmaComment = rmaComment + SPACE + rs.getString(RTRN_TRX_CMNT_TXT);
                }
                if (rmaComment.length() > RMA_COMMENT_LENGTH) {
                    rmaComment = rmaComment.substring(0, RMA_COMMENT_LENGTH);
                    break;
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
        return rmaComment;
    }

    /**
     * SET PDF Page Number
     */
    private BigDecimal setPdfPageNumber(String mdseDescShortTxt, BigDecimal curLine) {

        BigDecimal maxLineNum = null;

        //private poRptPgNumで比較
        if (BigDecimal.ONE.compareTo(curPageNum) == 0) {
            maxLineNum = lineNumOf1stPage;
        } else {
            maxLineNum = lineNumOf2ndPage;
        }

        BigDecimal addLine = BigDecimal.ONE;
        // QC#25025 Start
        // if (ZYPCommonFunc.hasValue(mdseDescShortTxt)) {
        //    BigDecimal lineDescLength = new BigDecimal(mdseDescShortTxt.length());
        //    addLine = lineDescLength.divide(numOfDescLtr, 0, BigDecimal.ROUND_UP);
        // }
        // QC#25025 End

        // add line
        BigDecimal afterLine = curLine.add(addLine);

        // Page break decision
        BigDecimal returnLine = afterLine;
        if (maxLineNum.compareTo(afterLine) < 0) {
            curPageNum = curPageNum.add(BigDecimal.ONE);
            returnLine = addLine;
        }

        return returnLine;
    }

    /**
     * <pre>
     * Add Message ID to MessageMap, and print debug log.
     * </pre>
     * @param msgMap Message Manager
     * @param msgId String setting value for Message ID
     * @throws none
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        msgMap.addXxMsgId(msgId);

        printDebugLog(DEBUG_LOG_HDR + msgId);
    }

    /**
     * <pre>
     * Print debug log.
     * </pre>
     * @param debugMsg
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }

}
