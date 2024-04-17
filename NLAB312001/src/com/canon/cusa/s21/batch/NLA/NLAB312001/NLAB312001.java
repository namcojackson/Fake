/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB312001;

import static com.canon.cusa.s21.batch.NLA.NLAB312001.constant.NLAB312001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.TECH_MDSE_LIST_WRKTMsg;
import business.db.TECH_MDSE_LIST_WRKTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TECH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NLAB312001 Populate Top500 Parts List Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/19/2015   Hitachi         J.Kim           Create          N/A
 * 05/05/2015   Fujitsu         S.Nakai         Update          CSA QC#7894
 * 08/24/2016   Hitachi         A.Kohinata      Update          CSA QC#13742
 * 08/25/2016   Hitachi         K.Kasai         Update          CSA QC#13805
 * 10/17/2016   Hitachi         N.Arai          Update          QC#15230
 * 01/05/2017   Hitachi         K.Ochiai        Update          QC#16459
 * 01/19/2017   Hitachi         K.Ochiai        Update          QC#16459
 * 05/23/2017   Hitachi         K.Kitachi       Update          QC#18374
 * 06/05/2017   Hitachi         K.Kitachi       Update          QC#18374
 * 2022/02/14   Hitachi         K.Kitachi       Update          QC#59695
 * 2022/03/09   Hitachi         K.Kitachi       Update          QC#59695
 * </pre>
 */
public class NLAB312001 extends S21BatchMain {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** ItemInfo List */
    private List<Map<String, String>> itemInfoList = null;

    /** Termination Code */
    private TERM_CD termCd;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Normal Count */
    private int normalCount;

    /** Commit Number */
    private int commitNumber = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate = null;

    /** process Date */
    private String processDate;

    // START 2017/06/05 K.Kitachi [QC#18374, DEL]
//    /** Recent Insert Date */
//    // mod start 2016/08/24 CSA Defect#13742
//    private String techMdseListWrkDt = "00000000";
//    // mod end 2016/08/24 CSA Defect#13742
//
//    /** Recent Insert Date Max Number */
//    private BigDecimal maxTechMdseListWrkNum = BigDecimal.ZERO;
    // END 2017/06/05 K.Kitachi [QC#18374, DEL]

    /** Recent Insert Date Max Number */
    private BigDecimal techMdseListWrkNum = BigDecimal.ZERO;

    // START 2022/02/14 K.Kitachi [QC#59695, ADD]
    /** All Send Flag */
    private String allSendFlg = null;
    // END 2022/02/14 K.Kitachi [QC#59695, ADD]

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLAM1118E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_SALES_DATE });
        }

        // START 2022/02/14 K.Kitachi [QC#59695, ADD]
        this.allSendFlg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_ALL_SEND_FLG, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.allSendFlg)) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_VAR_CHAR_CONST });
        }
        // END 2022/02/14 K.Kitachi [QC#59695, ADD]

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_NUMBER) {
            this.commitNumber = MAX_NUMBER;
        }

// START 2016/10/17 N.Arai [QC#15230, MOD]
//        this.processDate = ZYPDateUtil.addDays(this.salesDate, -1);
        // START 2022/03/09 K.Kitachi [QC#59695, DEL]
//        this.processDate = this.salesDate;
        // END 2022/03/09 K.Kitachi [QC#59695, DEL]
// END 2016/10/17 N.Arai [QC#15230, MOD]

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.itemInfoList = new ArrayList<Map<String, String>>();
        this.termCd = TERM_CD.NORMAL_END;

        // START 2022/03/09 K.Kitachi [QC#59695, ADD]
        this.processDate = getMaxPrtUsgRptWrkDt();
        if (!ZYPCommonFunc.hasValue(this.processDate) || ZYPDateUtil.getDiffDays(this.salesDate, this.processDate) > 1) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_PROCESS_DATE });
        }
        // END 2022/03/09 K.Kitachi [QC#59695, ADD]
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    private void doProcess() {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        ResultSet tmlwRs = null;
        PreparedStatement tmlwStmt = null;

        // START 2017/05/23 K.Kitachi [QC#18374, MOD]
//        // START 2016/12/26 [QC#16459, ADD]
//        Map<String, Object> delProcParam = new HashMap<String, Object>();
//        // START 2017/01/19 [QC#16459, MOD]
//        PreparedStatement delProcStmt = null;
//        ResultSet delProcRs = null;
//
//        Map<String, Object> delSkipParam = new HashMap<String, Object>();
//        PreparedStatement delSkipStmt = null;
//        ResultSet delSkipRs = null;
//        // END 2017/01/19 [QC#16459, MOD]
//        List<TECH_MDSE_LIST_WRKTMsg> delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
//        // END 2016/12/26 [QC#16459, ADD]
        PreparedStatement deleteStmt = null;
        ResultSet deleteRs = null;
        // END 2017/05/23 K.Kitachi [QC#18374, MOD]

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("processDate", this.processDate);
        param.put("incgFlg", ZYPConstant.FLG_ON_Y);
        param.put("inActive", MDSE_ITEM_STS.INACTIVE);
        param.put("rowNum", ROW_NUM);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // START 2016/08/25 [QC#13805, MOD]
            //stmt = this.ssmLLClient.createPreparedStatement("getItemPartsUsageList", param, execParam);
            stmt = this.ssmLLClient.createPreparedStatement("getItemPartsList", param, execParam);
            // END 2016/08/25 [QC#13805, MOD]
            rs = stmt.executeQuery();

            while (rs.next()) {
                this.itemInfoList.add(setItem(rs));
            }

            // START 2016/08/25 [QC#13805, DEL]
//            setItemPartsUsageInfo(itemInfoList);
//
//            List<String> techTocCdList = new ArrayList<String>();
//            List<Map<String, Object>> techTocCdMapList = new ArrayList<Map<String, Object>>();
//            for (int i = 0; i < this.itemInfoList.size(); i++) {
//
//                Map<String, String> itemInfo = itemInfoList.get(i);
//                String techTocCd = itemInfo.get(TECH_TOC_CD);
//                BigDecimal techTocCount = new BigDecimal(itemInfo.get(TECH_TOC_COUNT));
//                String rowNum = itemInfo.get(TTC_ROW_NUM);
//
//                if (ROW_NUM_1.equals(rowNum) && techTocCount.compareTo(ROW_NUM) < 0) {
//                    Map<String, Object> techTocCdMap = new HashMap<String, Object>();
//                    techTocCdList.add(techTocCd);
//                    techTocCdMap.put(TECH_TOC_CD, techTocCd);
//                    techTocCdMap.put(TECH_TOC_COUNT, itemInfo.get(TECH_TOC_COUNT));
//                    techTocCdMapList.add(techTocCdMap);
//                }
//            }
//
//            if (techTocCdList.size() != 0) {
//                getItemFromPartsOrder(techTocCdList, techTocCdMapList);
//            }
            // DEL 2016/08/25 [QC#13805, DEL]

            if (this.itemInfoList.size() != 0) {

                // START 2017/06/05 K.Kitachi [QC#18374, DEL]
//                Map<String, Object> tmlwParam = new HashMap<String, Object>();
//                tmlwParam.put("glblCmpyCd", this.glblCmpyCd);
//                tmlwParam.put("processed", TECH_PROC_STS.PROCESSED);
//
//                tmlwStmt = this.ssmLLClient.createPreparedStatement("getTechMdseListWrkDt", tmlwParam, execParam);
//                tmlwRs = tmlwStmt.executeQuery();
//
//                if (tmlwRs.next()) {
//                    this.techMdseListWrkDt = tmlwRs.getString(TECH_MDSE_LIST_WRK_DT);
//                    this.maxTechMdseListWrkNum = tmlwRs.getBigDecimal(TECH_MDSE_LIST_WRK_NUM);
//                }
                // END 2017/06/05 K.Kitachi [QC#18374, DEL]

                updateTechMdseListWrkTpsCD();

                insertTechMdseListWrk();

                // Difference Check and Update TECH_MDSE_LIST_WRK
                // mod start 2016/08/24 CSA Defect#13742
                //if (hasValue(this.techMdseListWrkDt)) {
                //    updateTechMdseListWrkTechProcStsCd();
                //}
                updateTechMdseListWrkTechProcStsCd();
                // mod end 2016/08/24 CSA Defect#13742
            }

            // START 2017/05/23 K.Kitachi [QC#18374, MOD]
//            // START 2017/01/05 [QC#16459, ADD]
//            // delete Processed Data
//            delProcParam.put("glblCmpyCd", this.glblCmpyCd);
//            delProcParam.put("maxDate", this.techMdseListWrkDt);
//            delProcParam.put("maxWorkNum", this.maxTechMdseListWrkNum);
//            delProcParam.put("techProcStsCd_P", TECH_PROC_STS.PROCESSED);
//            // START 2017/01/19 [QC#16459, MOD]
//            delProcStmt = this.ssmLLClient.createPreparedStatement("getDeleteProcPK", delProcParam, execParam);
//            delProcRs = delProcStmt.executeQuery();
//            while (delProcRs.next()) {
//                delTMsgList.add(setTechMdseListWrkPk(delProcRs));
//                if (this.commitNumber == delTMsgList.size()) {
//                    deleteTechMdseListWrk(delTMsgList);
//                    delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
//                }
//            }
//            if (delTMsgList.size() > 0) {
//                deleteTechMdseListWrk(delTMsgList);
//                delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
//            }
//
//            // delete Skip Data
//            delSkipParam.put("glblCmpyCd", this.glblCmpyCd);
//            delSkipParam.put("techProcStsCd_S", TECH_PROC_STS.SKIP);
//            delSkipStmt = this.ssmLLClient.createPreparedStatement("getDeleteSkipPK", delSkipParam, execParam);
//            delSkipRs = delSkipStmt.executeQuery();
//            while (delSkipRs.next()) {
//                delTMsgList.add(setTechMdseListWrkPk(delSkipRs));
//                if (this.commitNumber == delTMsgList.size()) {
//                    deleteTechMdseListWrk(delTMsgList);
//                    delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
//                }
//            }
//            // END 2017/01/19 [QC#16459, MOD]
//            if (delTMsgList.size() > 0) {
//                deleteTechMdseListWrk(delTMsgList);
//            }
//            // END 2017/01/05 [QC#16459, MOD]
            List<TECH_MDSE_LIST_WRKTMsg> delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
            deleteStmt = this.ssmLLClient.createPreparedStatement("getDeletePK", createDeleteParam(), execParam);
            deleteRs = deleteStmt.executeQuery();
            while (deleteRs.next()) {
                delTMsgList.add(setTechMdseListWrkPk(deleteRs));
                if (this.commitNumber == delTMsgList.size()) {
                    deleteTechMdseListWrk(delTMsgList);
                    delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
                }
            }
            if (delTMsgList.size() > 0) {
                deleteTechMdseListWrk(delTMsgList);
                delTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
            }
            // END 2017/05/23 K.Kitachi [QC#18374, MOD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            S21SsmLowLevelCodingClient.closeResource(tmlwStmt, tmlwRs);
            // START 2017/05/23 K.Kitachi [QC#18374, MOD]
//            // START 2017/01/19 [QC#16459, MOD]
//            S21SsmLowLevelCodingClient.closeResource(delProcStmt, delProcRs);
//            S21SsmLowLevelCodingClient.closeResource(delSkipStmt, delSkipRs);
//            // END 2017/01/19 [QC#16459, MOD]
            S21SsmLowLevelCodingClient.closeResource(deleteStmt, deleteRs);
            // END 2017/05/23 K.Kitachi [QC#18374, MOD]
        }
    }

    private Map<String, String> setItem(ResultSet rs) {

        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            resultMap.put(TECH_TOC_CD, rs.getString(TECH_TOC_CD));
            resultMap.put(MDSE_CD, rs.getString(MDSE_CD));
            resultMap.put(MDSE_NM, rs.getString(MDSE_NM));
            resultMap.put(SUPD_TO_MDSE_CD, rs.getString(SUPD_TO_MDSE_CD));
            // START 2016/08/25 [QC#13805, MOD]
//            resultMap.put(AVG_USG_NUM, rs.getString(AVG_USG_NUM));
//            resultMap.put(TECH_TOC_COUNT, rs.getString(TECH_TOC_COUNT));
            resultMap.put(AVG_USG_QTY, rs.getString(AVG_USG_QTY));
            resultMap.put(PRCH_REQ_QTY, rs.getString(PRCH_REQ_QTY));
            // END 2016/08/25 [QC#13805, MOD]
            resultMap.put(TTC_ROW_NUM, rs.getString(TTC_ROW_NUM));
            resultMap.put(SITE_SRVY_REQ_FLG, ZYPConstant.FLG_ON_Y);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return resultMap;
    }

    // START 2016/08/25 [QC#13805, DEL]
//    private void setItemPartsUsageInfo(List<Map<String, String>> resultList) {
//
//        this.itemInfoList = new ArrayList<Map<String, String>>();
//
//        for (Map<String, String> result : resultList) {
//            Map<String, String> resultMap = new HashMap<String, String>();
//            // Employee ID
//            resultMap.put(TECH_TOC_CD, result.get(TECH_TOC_CD));
//            // Item Number
//            resultMap.put(MDSE_CD, result.get(MDSE_CD));
//            // Item Description
//            resultMap.put(MDSE_NM, result.get(MDSE_NM));
//            // Supersede Item Number
//            resultMap.put(SUPD_TO_MDSE_CD, result.get(SUPD_TO_MDSE_CD));
//            // Average Qty
//            resultMap.put(AVG_USG_NUM, result.get(AVG_USG_NUM));
//            // Site Survey Flag
//            resultMap.put(SITE_SRVY_REQ_FLG, ZYPConstant.FLG_ON_Y);
//            // COUNT
//            resultMap.put(TECH_TOC_COUNT, result.get(TECH_TOC_COUNT));
//            // ROW_NUMBER
//            resultMap.put(TTC_ROW_NUM, result.get(TTC_ROW_NUM));
//            this.itemInfoList.add(resultMap);
//        }
//    }
//
//    private void getItemFromPartsOrder(List<String> techTocCdList, List<Map<String, Object>> techTocCdMapList) {
//
//        ResultSet rs = null;
//        PreparedStatement stmt = null;
//
//        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//        execParam.setFetchSize(this.commitNumber);
//        execParam.setMaxRows(0);
//        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("techTocCdList", techTocCdList);
//        param.put("mdseDcList", getMdseDcString());
//        param.put("processDate", this.processDate);
//        param.put("incgFlg", ZYPConstant.FLG_ON_Y);
//        param.put("inActive", MDSE_ITEM_STS.INACTIVE);
//        param.put("rowNum", ROW_NUM);
//
//        try {
//
//            stmt = this.ssmLLClient.createPreparedStatement("getItemPartsOrderList", param, execParam);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String techTocCd = rs.getString(RQST_TECH_TOC_CD);
//                BigDecimal ttcRowNum = new BigDecimal(rs.getString(TTC_ROW_NUM));
//                BigDecimal rowNumPo = getTechTocCount(techTocCd, techTocCdMapList);
//                if (rowNumPo.compareTo(ttcRowNum) >= 0) {
//                    this.itemInfoList.add(setItemPartsOrderList(rs));
//                }
//            }
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
//        }
//    }
//
//    private BigDecimal getTechTocCount(String techTocCd, List<Map<String, Object>> techTocCdMapList) {
//
//        BigDecimal ttcRowNum = BigDecimal.ZERO;
//        for (Map<String, Object> techTocCdMap : techTocCdMapList) {
//            if (techTocCd.equals(techTocCdMap.get(TECH_TOC_CD))) {
//                ttcRowNum = new BigDecimal(techTocCdMap.get(TECH_TOC_COUNT).toString());
//                break;
//            }
//        }
//        ttcRowNum = ROW_NUM.subtract(ttcRowNum);
//        return ttcRowNum;
//    }
//
//    private Map<String, String> setItemPartsOrderList(ResultSet rs) {
//
//        Map<String, String> itemPartOrderMap = new HashMap<String, String>();
//
//        try {
//            itemPartOrderMap.put(TECH_TOC_CD, rs.getString(RQST_TECH_TOC_CD));
//            itemPartOrderMap.put(MDSE_CD, rs.getString(MDSE_CD));
//            itemPartOrderMap.put(MDSE_NM, rs.getString(MDSE_NM));
//            itemPartOrderMap.put(SUPD_TO_MDSE_CD, rs.getString(SUPD_TO_MDSE_CD));
//            itemPartOrderMap.put(AVG_USG_NUM, rs.getString(PRCH_REQ_QTY));
//            itemPartOrderMap.put(SITE_SRVY_REQ_FLG, ZYPConstant.FLG_ON_Y);
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        }
//        return itemPartOrderMap;
//    }
//
//    private String[] getMdseDcString() {
//
//        String[] sb = new String[this.itemInfoList.size()];
//        for (int i = 0; i < this.itemInfoList.size(); i++) {
//            Map<String, String> result = itemInfoList.get(i);
//            sb[i] = result.get(MDSE_CD);
//        }
//        return sb;
//    }
    // END 2016/08/25 [QC#13805, DEL]

    private void updateTechMdseListWrkTpsCD() {

        List<TECH_MDSE_LIST_WRKTMsg> inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
        TECH_MDSE_LIST_WRKTMsg inParam = new TECH_MDSE_LIST_WRKTMsg();
        inParam.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inParam.setConditionValue("techProcStsCd01", TECH_PROC_STS.WAITING_FOR_PROCESS);
        inParam.setSQLID(SQLID);
        TECH_MDSE_LIST_WRKTMsgArray findTmlwArray = (TECH_MDSE_LIST_WRKTMsgArray) EZDTBLAccessor.findByCondition(inParam);

        for (int i = 0; i < findTmlwArray.getValidCount(); i++) {

            TECH_MDSE_LIST_WRKTMsg tmlwTMsg = (TECH_MDSE_LIST_WRKTMsg) findTmlwArray.get(i);
            TECH_MDSE_LIST_WRKTMsg inParamTMsg = new TECH_MDSE_LIST_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(inParamTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParamTMsg.techMdseListWrkPk, tmlwTMsg.techMdseListWrkPk);

            TECH_MDSE_LIST_WRKTMsg updateTmlwTMsg = (TECH_MDSE_LIST_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(inParamTMsg);
            ZYPEZDItemValueSetter.setValue(updateTmlwTMsg.techProcStsCd, TECH_PROC_STS.SKIP);
            inTMsgList.add(updateTmlwTMsg);

            if (this.commitNumber == inTMsgList.size() || i == (findTmlwArray.getValidCount() - 1)) {
                updateTechMdseListWrk(inTMsgList);
                inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
            }
        }
    }

    private void insertTechMdseListWrk() {

        List<TECH_MDSE_LIST_WRKTMsg> inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();

        this.getTechMdseListWrkNum();

        for (int i = 0; i < itemInfoList.size(); i++) {

            TECH_MDSE_LIST_WRKTMsg tmlwTMsg = new TECH_MDSE_LIST_WRKTMsg();
            Map<String, String> itemInfo = itemInfoList.get(i);
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.techMdseListWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal("TECH_MDSE_LIST_WRK_SQ"));
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.techMdseListWrkDt, this.processDate);
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.techMdseListWrkNum, this.techMdseListWrkNum);
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.techTocCd, itemInfo.get(TECH_TOC_CD));
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.mdseCd, itemInfo.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.mdseNm, itemInfo.get(MDSE_NM));
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.supdToMdseCd, itemInfo.get(SUPD_TO_MDSE_CD));
            // START 2016/08/25 [QC#13805, MOD]
            //ZYPEZDItemValueSetter.setValue(tmlwTMsg.avgUsgNum, new BigDecimal(itemInfo.get(AVG_USG_NUM)));
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.avgUsgNum, new BigDecimal(itemInfo.get(AVG_USG_QTY)));
            if (tmlwTMsg.avgUsgNum.getValueInt() == 0) {
                ZYPEZDItemValueSetter.setValue(tmlwTMsg.avgUsgNum, new BigDecimal(itemInfo.get(PRCH_REQ_QTY)));
            }
            // END 2016/08/25 [QC#13805, MOD]
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.siteSrvyReqFlg, itemInfo.get(SITE_SRVY_REQ_FLG));
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.techProcStsCd, TECH_PROC_STS.SKIP);
            // START 2022/02/14 K.Kitachi [QC#59695, ADD]
            ZYPEZDItemValueSetter.setValue(tmlwTMsg.delFlg, ZYPConstant.FLG_OFF_N);
            // END 2022/02/14 K.Kitachi [QC#59695, ADD]
            inTMsgList.add(tmlwTMsg);

            if (this.commitNumber == inTMsgList.size() || i == (itemInfoList.size() - 1)) {
                int commitCount = insertTechMdseListWrk(inTMsgList);
                inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
                this.normalCount += commitCount;
            }
        }
    }

    private int insertTechMdseListWrk(List<TECH_MDSE_LIST_WRKTMsg> inTMsgList) {

        TECH_MDSE_LIST_WRKTMsg[] inTMsgArray = new TECH_MDSE_LIST_WRKTMsg[inTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inTMsgArray));
        if (insertCount != inTMsgArray.length) {
            this.errorCount += inTMsgArray.length - insertCount;
        }
        commit();
        return insertCount;
    }

    private void updateTechMdseListWrk(List<TECH_MDSE_LIST_WRKTMsg> inTMsgList) {

        TECH_MDSE_LIST_WRKTMsg[] inTMsgArray = new TECH_MDSE_LIST_WRKTMsg[inTMsgList.size()];
        S21FastTBLAccessor.update(inTMsgList.toArray(inTMsgArray));
        return;
    }

    private BigDecimal getTechMdseListWrkNum() {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("techMdseListWrkDt", this.processDate);

        this.techMdseListWrkNum = (BigDecimal) this.ssmBatchClient.queryObject("getMaxTechMdseListWrkNum", param);

        if (this.techMdseListWrkNum == null) {
            this.techMdseListWrkNum = BigDecimal.ZERO;
        } else {
            this.techMdseListWrkNum = this.techMdseListWrkNum.add(BigDecimal.ONE);
        }

        return this.techMdseListWrkNum;
    }

    private void updateTechMdseListWrkTechProcStsCd() {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        List<TECH_MDSE_LIST_WRKTMsg> inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2017/06/05 K.Kitachi [QC#18374, DEL]
//        stesParam.put("techMdseListWrkDtP", this.techMdseListWrkDt);
//        stesParam.put("techMdseListWrkNumP", this.maxTechMdseListWrkNum);
        // END 2017/06/05 K.Kitachi [QC#18374, DEL]
        stesParam.put("techProStsCdP", TECH_PROC_STS.PROCESSED);
        stesParam.put("techMdseListWrkDtS", this.processDate);
        stesParam.put("techMdseListWrkNumS", this.techMdseListWrkNum);
        stesParam.put("techProStsCdS", TECH_PROC_STS.SKIP);
        // START 2022/02/14 K.Kitachi [QC#59695, ADD]
        stesParam.put("allSendFlg", this.allSendFlg);
        // END 2022/02/14 K.Kitachi [QC#59695, ADD]

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getDifferenceTmlwInfo", stesParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                TECH_MDSE_LIST_WRKTMsg inParamTMsg = new TECH_MDSE_LIST_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(inParamTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inParamTMsg.techMdseListWrkPk, rs.getBigDecimal(TECH_MDSE_LIST_WRK_PK));

                TECH_MDSE_LIST_WRKTMsg updateTmlwTMsg = (TECH_MDSE_LIST_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(inParamTMsg);
                ZYPEZDItemValueSetter.setValue(updateTmlwTMsg.techProcStsCd, TECH_PROC_STS.WAITING_FOR_PROCESS);
                // START 2022/02/14 K.Kitachi [QC#59695, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(DEL_FLG))) {
                    ZYPEZDItemValueSetter.setValue(updateTmlwTMsg.delFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(updateTmlwTMsg.techMdseListWrkDt, this.processDate);
                    ZYPEZDItemValueSetter.setValue(updateTmlwTMsg.techMdseListWrkNum, this.techMdseListWrkNum);
                }
                // END 2022/02/14 K.Kitachi [QC#59695, ADD]
                inTMsgList.add(updateTmlwTMsg);

                if (this.commitNumber == inTMsgList.size()) {
                    updateTechMdseListWrk(inTMsgList);
                    inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
                }
            }

            if (inTMsgList.size() > 0) {
                updateTechMdseListWrk(inTMsgList);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // START 2017/01/05 [QC#16459, ADD]
    private TECH_MDSE_LIST_WRKTMsg setTechMdseListWrkPk(ResultSet rs) {
        TECH_MDSE_LIST_WRKTMsg inParam = new TECH_MDSE_LIST_WRKTMsg();
        // START 2017/01/19 [QC#16459, MOD]
        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.techMdseListWrkPk, rs.getBigDecimal("TECH_MDSE_LIST_WRK_PK"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        // END 2017/01/19 [QC#16459, MOD]
        return inParam;
    }

    private void deleteTechMdseListWrk(List<TECH_MDSE_LIST_WRKTMsg> delTMsgList) {
        TECH_MDSE_LIST_WRKTMsg[] delMsgArray;
        delMsgArray = new TECH_MDSE_LIST_WRKTMsg[delTMsgList.size()];
        S21FastTBLAccessor.removePhysical(delTMsgList.toArray(delMsgArray));

        commit();
        return;

    }
    // END 2017/01/05 [QC#16459, ADD]

    // START 2017/05/23 K.Kitachi [QC#18374, ADD]
    private Map<String, Object> createDeleteParam(){
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("techProcStsCdW", TECH_PROC_STS.WAITING_FOR_PROCESS);
        ssmParam.put("techProcStsCdP", TECH_PROC_STS.PROCESSED);
        ssmParam.put("techProcStsCdS", TECH_PROC_STS.SKIP);
        ssmParam.put("sortNum1", SORT_NUM_1);
        ssmParam.put("sortNum2", SORT_NUM_2);
        ssmParam.put("sortNum3", SORT_NUM_3);
        ssmParam.put("sortNum4", SORT_NUM_4);
        return ssmParam;
    }
    // END 2017/05/23 K.Kitachi [QC#18374, ADD]

    // START 2022/03/09 K.Kitachi [QC#59695, ADD]
    private String getMaxPrtUsgRptWrkDt() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        return (String) this.ssmBatchClient.queryObject("getMaxPrtUsgRptWrkDt", param);
    }
    // END 2022/03/09 K.Kitachi [QC#59695, ADD]

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NLAB312001().executeBatch(NLAB312001.class.getSimpleName());
    }
}
