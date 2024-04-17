/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */package com.canon.cusa.s21.batch.NPA.NPAB165001;

import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.MAX_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.MSG_TXT_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPAB1650_NPAL1260_TARGET_POD;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPAB1650_PO_HDR;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPAB1650_PO_LINE;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPAB1650_PRCH_HDR;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPAB1650_PRCH_LINE;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPAM0020E;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.NPZM0001E;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.PREMIUM_RUSH_CONDITION_TPL_CARR_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.RUSH_CONDITION_TPL_CARR_NM_FEDEX;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.RUSH_CONDITION_TPL_CARR_NM_UPS;
import static com.canon.cusa.s21.batch.NPA.NPAB165001.constant.NPAB165001Constant.VAR_CHAR_POD_UPD_STS_CD;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.TECH_RQST_LIST_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TECH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PO_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Create Open Tech Request Work Data Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   Hitachi         T.Iwamoto       Create          NA
 * 06/29/2016   Hitachi         T.Iwamoto       Update          QC#10113,10114
 * 08/29/2016   Hitachi         K.Yamada        Update          QC#13891
 * 11/18/2016   Hitachi         A.Kohinata      Update          QC#15980
 * 2016/11/22   Hitachi         K.Kojima        Update          QC#16033
 * 2016/12/02   Hitachi         T.Tomita        Update          QC#16332
 * 2017/01/13   Hitachi         T.Tomita        Update          QC#17032
 * 2017/02/16   Hitachi         K.Kojima        Update          QC#17111
 * 2017/11/13   CITS            M.Naito         Update          QC#18572
 * 2018/05/21   Hitachi         K.Kojima        Update          QC#22220
 * 2019/04/26   CITS            K.Ogino         Update          QC#31201
 * 2019/05/21   CITS            K.Ogino         Update          QC#50360
 * 2019/07/23   CITS            K.Ogino         Update          QC#51816
 * 2023/10/26   Hitachi         T.Kuroda        Update          QC#61494
 * </pre>
 */
public class NPAB165001 extends S21BatchMain {
    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String salesDate;

    /** Pod update Status Cds */
    private String[] podUpdStss;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * Initialize method.
     */
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NPZM0001E);
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NPAM0020E, new String[] {MSG_TXT_SALES_DATE });
        }

        // QC#31201
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_POD_UPD_STS_CD, getGlobalCompanyCode());
        if (constValue != null) {
            this.podUpdStss = constValue.split(",");
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB165001().executeBatch(NPAB165001.class.getSimpleName());
    }

    private void doProcess() {
        Map<String, String> deleteParam = new HashMap<String, String>();
        List<TECH_RQST_LIST_WRKTMsg> deleteTMsgList = new ArrayList<TECH_RQST_LIST_WRKTMsg>();
        PreparedStatement deleteStmt = null;
        ResultSet deleteRs = null;

        List<TECH_RQST_LIST_WRKTMsg> inTMsgList = new ArrayList<TECH_RQST_LIST_WRKTMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // get Insert TECH_RQST_LIST_WRK
            stmt = this.ssmLLClient.createPreparedStatement("getTechnicianRequestList", setTechnicianRequestListSearchCondition(), execParam);
            rs = stmt.executeQuery();

            // get Delete TECH_RQST_LIST_WRK
            deleteParam.put("glblCmpyCd", this.glblCmpyCd);
            // del start 2016/11/18 CSA Defect#15980
            //deleteParam.put("techProcStsP", TECH_PROC_STS.PROCESSED);
            // del end 2016/11/18 CSA Defect#15980

            deleteStmt = this.ssmLLClient.createPreparedStatement("getDeleteList", deleteParam, execParam);
            deleteRs = deleteStmt.executeQuery();
            while (deleteRs.next()) {
                deleteTMsgList.add(setTechnicianRequestListWorkPk(deleteRs));
                if (this.commitNumber == deleteTMsgList.size()) {
                    deleteTechnicianRequestListWork(deleteTMsgList);
                    deleteTMsgList = new ArrayList<TECH_RQST_LIST_WRKTMsg>();
                }
            }
            if (deleteTMsgList.size() > 0) {
                deleteTechnicianRequestListWork(deleteTMsgList);
            }

            // Insert TECH_RQST_LIST_WRK
            int commitCount = 0;
            while (rs.next()) {
                inTMsgList.add(setTechnicianRequestListWorkCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertTechnicianRequestListWork(inTMsgList);
                    inTMsgList = new ArrayList<TECH_RQST_LIST_WRKTMsg>();
                    this.normalCount += commitCount;
                    commitCount = 0;
                }
            }
            if (inTMsgList.size() > 0) {
                commitCount = insertTechnicianRequestListWork(inTMsgList);
                this.normalCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(deleteStmt, deleteRs);
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> setTechnicianRequestListSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("slsDt", this.salesDate);
        inParam.put("maxDt", MAX_DATE);
        inParam.put("prchReqRecTpCd", PRCH_REQ_REC_TP.TECH_REQUEST);
        inParam.put("NPAB1650_PrchHdr", NPAB1650_PRCH_HDR);
        inParam.put("NPAB1650_PrchLine", NPAB1650_PRCH_LINE);
        inParam.put("poStsCdConf", PO_STS.PO_CONFIRMED);
        inParam.put("poHdrStsCd", PO_HDR_STS.OPEN);
        // add start 2016/12/02 CSA Defect#16332
        inParam.put("poStsCdPoConf", PO_STS.PO_CONFIRMED);
        // add end 2016/12/02 CSA Defect#16332
        inParam.put("NPAB1650_PoHdr", NPAB1650_PO_HDR);
        inParam.put("NPAB1650_PoLine", NPAB1650_PO_LINE);
        // add start 2017/01/13 CSA Defect#17032
        List<String> techProcStsCdList = new ArrayList<String>();
        techProcStsCdList.add(TECH_PROC_STS.PROCESSED);
        techProcStsCdList.add(TECH_PROC_STS.ERROR);
        inParam.put("techProcStsCdList", techProcStsCdList);
        // add end 2017/01/13 CSA Defect#17032
        inParam.put("techProcStsW", TECH_PROC_STS.WAITING_FOR_PROCESS);
        inParam.put("techProcStsP", TECH_PROC_STS.PROCESSED);
        // START 2016/11/22 K.Kojima [QC#16033,ADD]
        inParam.put("prchReqStsClosed", PRCH_REQ_STS.CLOSED);
        // END 2016/11/22 K.Kojima [QC#16033,ADD]
        // START 2017/02/16 K.Kojima [QC#17111,ADD]
        inParam.put("procRTpSupplier", PROCR_TP.SUPPLIER);
        // END 2017/02/16 K.Kojima [QC#17111,ADD]
        List<String> prchReqStsList = new ArrayList<String>(2);
        prchReqStsList.add(PRCH_REQ_STS.CLOSED);
        prchReqStsList.add(PRCH_REQ_STS.CANCELLED);
        inParam.put("prchReqStsList", prchReqStsList);
        List<String> poHdrStsList = new ArrayList<String>(2);
        poHdrStsList.add(PO_HDR_STS.CLOSED);
        poHdrStsList.add(PO_HDR_STS.CANCELLED);
        inParam.put("poHdrStsList", poHdrStsList);
        // START 2017/11/13 M.Naito [QC#18572,ADD]
        inParam.put("rwsStsCanceled", RWS_STS.CANCELED);
        inParam.put("prchReqLineTpInsPo", PRCH_REQ_LINE_TP.INSOURCED_PO);
        // END 2017/11/13 M.Naito [QC#18572,ADD]
        // START 2018/05/21 K.Kojima [QC#22220,ADD]
        inParam.put("prchReqLineStsCancelled", PRCH_REQ_LINE_STS.CANCELLED);
        // END 2018/05/21 K.Kojima [QC#22220,ADD]
        // QC#31201
        List<String> podUpdStsList = null;
        if (podUpdStss != null) {
            podUpdStsList = Arrays.asList(podUpdStss);
            inParam.put("podUpdStsList", podUpdStsList);
        }
        List<String> ackStsList = Arrays.asList(new String[]{VND_PO_ACK_STS.ITEM_BACKORDERED});
        inParam.put("ackStsList", ackStsList);
        BigDecimal ltDays = ZYPCodeDataUtil.getNumConstValue("PO_ACK_LT_DAY", getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(ltDays)) {
            ltDays = BigDecimal.ZERO;
        }
        inParam.put("days", ltDays);
        // QC#51816
        inParam.put("prchReqLineStsClosed", PRCH_REQ_LINE_STS.CLOSED);
        inParam.put("prchReqLineStsCancelled", PRCH_REQ_LINE_STS.CANCELLED);
        inParam.put("prchReqLineStsReceived", PRCH_REQ_LINE_STS.RECEIVED);
        inParam.put("prchReqLineStsShipped", PRCH_REQ_LINE_STS.SHIPPED);
        inParam.put("prchReqLineStsPartiallyReceived", PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);
        inParam.put("prchReqLineStsPartiallyShipped", PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
        // START 2023/10/26 T.Kuroda [QC#61494, ADD]
        inParam.put("prchReqTpCdPremiumRush", PRCH_REQ_TP.PREMIUM_RUSH);
        inParam.put("prchReqTpCdRush", PRCH_REQ_TP.RUSH);
        inParam.put("premiumRushConditionTplCarrNm", PREMIUM_RUSH_CONDITION_TPL_CARR_NM);
        List<String> rushConditionTplCarrNmList =
            Arrays.asList(new String[]{RUSH_CONDITION_TPL_CARR_NM_FEDEX, RUSH_CONDITION_TPL_CARR_NM_UPS});
        inParam.put("rushConditionTplCarrNmList", rushConditionTplCarrNmList);
        List<String> ediStsCdList =
            Arrays.asList(ZYPCodeDataUtil.getVarCharConstValue(NPAB1650_NPAL1260_TARGET_POD, this.glblCmpyCd).split(","));
        inParam.put("ediStsCdList", ediStsCdList);
        inParam.put("prchReqRecTpCdRequisition", PRCH_REQ_REC_TP.PO_REQUISITION);
        // END 2023/10/26 T.Kuroda [QC#61494, ADD]
        return inParam;
    }

    private TECH_RQST_LIST_WRKTMsg setTechnicianRequestListWorkCreateValue(ResultSet rs) {
        TECH_RQST_LIST_WRKTMsg inParam = new TECH_RQST_LIST_WRKTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.techRqstListWrkPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_RQST_LIST_WRK_SQ));
            ZYPEZDItemValueSetter.setValue(inParam.techRqstListWrkDt, this.salesDate);
            // add start 2016/08/29 CSA Defect#13891
            ZYPEZDItemValueSetter.setValue(inParam.techTocCd, rs.getString("TECH_TOC_CD"));
            // add end 2016/08/29 CSA Defect#13891
            ZYPEZDItemValueSetter.setValue(inParam.destInvtyLocCd, rs.getString("DEST_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqNum, rs.getString("PRCH_REQ_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqLineNum, rs.getString("PRCH_REQ_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqLineSubNum, rs.getBigDecimal("PRCH_REQ_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.svcTaskNum, rs.getString("SVC_TASK_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.shipDtTmTs, rs.getString("SHIP_DT_TM_TS"));
            ZYPEZDItemValueSetter.setValue(inParam.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(inParam.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(inParam.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(inParam.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(inParam.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqStsDescTxt, rs.getString("PRCH_REQ_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(inParam.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqLineStsDescTxt, rs.getString("PRCH_REQ_LINE_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(inParam.srcInvtyLocCd, rs.getString("SRC_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.carrTpNm, rs.getString("CARR_TP_NM"));
            String rqstRcvDt = rs.getString("RQST_RCV_DT");
            if (ZYPCommonFunc.hasValue(rqstRcvDt) && rqstRcvDt.length() > 8) {
                rqstRcvDt = rqstRcvDt.substring(0, 8);
            }
            ZYPEZDItemValueSetter.setValue(inParam.rqstRcvDt, rqstRcvDt);
            // START 2023/10/26 T.Kuroda [QC#61494, MOD]
            //// QC#50360
            //ZYPEZDItemValueSetter.setValue(inParam.rqstRcvTm, rs.getString("RQST_RCV_TM"));
            String rqstRcvTm = rs.getString("RQST_RCV_TM");
            if (ZYPCommonFunc.hasValue(rqstRcvTm) && rqstRcvTm.length() > 6) {
                // substrings "hhmmss"
                rqstRcvTm = rqstRcvTm.substring(8, 14);
            }
            ZYPEZDItemValueSetter.setValue(inParam.rqstRcvTm, rqstRcvTm);
            // END 2023/10/26 T.Kuroda [QC#61494, MOD]
            ZYPEZDItemValueSetter.setValue(inParam.prchReqQty, rs.getBigDecimal("PRCH_REQ_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.shipQty, rs.getBigDecimal("SHIP_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.rwsPutAwayQty, rs.getBigDecimal("RWS_PUT_AWAY_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.backToTechQty, rs.getBigDecimal("BACK_TO_TECH_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.proNum, rs.getString("PRO_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.carrTrkUrl, rs.getString("CARR_TRK_URL"));
            ZYPEZDItemValueSetter.setValue(inParam.locNm, rs.getString("LOC_NM"));
            // mod start 2016/06/29 CSA Defect#10114
            ZYPEZDItemValueSetter.setValue(inParam.rtlWhNm, rs.getString("RTL_WH_NM"));
            // mod end 2016/06/29 CSA Defect#10114
            // START 2017/11/13 M.Naito [QC#18572,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.techRqstSoNum, rs.getString("TECH_RQST_SO_NUM"));
            // END 2017/11/13 M.Naito [QC#18572,ADD]

            ZYPEZDItemValueSetter.setValue(inParam.techProcStsCd, rs.getString("TECH_PROC_STS_CD"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private int insertTechnicianRequestListWork(List<TECH_RQST_LIST_WRKTMsg> inMsgLst) {
        TECH_RQST_LIST_WRKTMsg[] inMsgArray;
        inMsgArray = new TECH_RQST_LIST_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.errorCount += inMsgArray.length - insertCount;
        }
        commit();
        return insertCount;
    }

    private TECH_RQST_LIST_WRKTMsg setTechnicianRequestListWorkPk(ResultSet rs) {
        TECH_RQST_LIST_WRKTMsg inParam = new TECH_RQST_LIST_WRKTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.techRqstListWrkPk, rs.getBigDecimal("TECH_RQST_LIST_WRK_PK"));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private void deleteTechnicianRequestListWork(List<TECH_RQST_LIST_WRKTMsg> inMsgLst) {
        TECH_RQST_LIST_WRKTMsg[] inMsgArray;
        inMsgArray = new TECH_RQST_LIST_WRKTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));

        commit();
        return;
    }
}
