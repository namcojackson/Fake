/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB709001;

import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_ACTV_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_BASE_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_DEL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_CATG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_CATG_CD_EXCEPT;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_CATG_CD_SPECIFIC;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_LIST_EQUIP_CONFIG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_LIST_EQUIP_CONFIG_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_QLFY_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_QLFY_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_TERM_AOT;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PRC_TERM_UOM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.COL_RQST_STS_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.NMAM0043E;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.NMAM0072E;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB709001.constant.NMAB709001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.PRC_LIST_EQUIPTMsg;
import business.db.PRC_LIST_EQUIP_RQSTTMsg;
import business.db.PRC_LIST_EQUIP_TRX_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_STS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_STS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Item Supersessions Mass Add Price List Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CITS            S.Tanikawa      Create          N/A
 * 2016/05/06   CITS            S.Tanikawa      UPDATE          QC#7725
 *</pre>
 */
public class NMAB709001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** process date time */
    private String procDt = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();

        // Initialization of SQL Accessor
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParamGetMdse = new HashMap<String, Object>();
        ssmParamGetMdse.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamGetMdse.put(COL_RQST_STS_TP_CD, RQST_STS_TP.REQUESTED);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getRequest", ssmParamGetMdse, execParam);
            rsSet = stmt.executeQuery();

            PRC_LIST_EQUIP_RQSTTMsg updateRqstTMsg = new PRC_LIST_EQUIP_RQSTTMsg();
            List<PRC_LIST_EQUIPTMsg> insertPrcList = new ArrayList<PRC_LIST_EQUIPTMsg>();
            List<PRC_LIST_EQUIP_TRX_RQSTTMsg> insertTrxList = new ArrayList<PRC_LIST_EQUIP_TRX_RQSTTMsg>();

            while (rsSet.next()) {

                ZYPEZDItemValueSetter.setValue(updateRqstTMsg.prcListEquipRqstPk, rsSet.getBigDecimal("PRC_LIST_EQUIP_RQST_PK"));
                boolean isSuccess = true;
                String updPrcListsText = rsSet.getString("PRC_LISTS_DESC_TXT");
                String oldMdseCd = rsSet.getString("OLD_MDSE_CD");
                String prcListActTpCd = rsSet.getString("PRC_LIST_ACT_TP_CD");

                List<Map<String, Object>> prcEquipList = getPrcListEquip(updPrcListsText, oldMdseCd, prcListActTpCd);

                for (Map<String, Object> prcEquip : prcEquipList) {
                    // set value on PRC_LIST_EQUIP
                    PRC_LIST_EQUIPTMsg inPrcListEquipTMsg = new PRC_LIST_EQUIPTMsg();

                    // Price List Equipment Primary Key
                    BigDecimal prcListEquipPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ);
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPk, prcListEquipPk);
                    // Global Company Code
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.glblCmpyCd, this.glblCmpyCd);
                    // Price Category Code
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcCatgCd, (String) prcEquip.get("PRC_CATG_CD"));
                    // Price List Equipment Config Number
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipConfigNum, (BigDecimal) prcEquip.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                    // Price List Equipment Config Name
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipConfigNm, (String) prcEquip.get("PRC_LIST_EQUIP_CONFIG_NM"));
                    // Price Qualify Type Code
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyTpCd, (String) prcEquip.get("PRC_QLFY_TP_CD"));
                    // Price Qualify Value Text
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyValTxt, rsSet.getString("NEW_MDSE_CD"));
                    // Package Unit of Measure Code
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.pkgUomCd, (String) prcEquip.get("PKG_UOM_CD"));
                    // Price List Equipment Price Amount
                    if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("NEW_PRC_AMT")) && ZYPConstant.FLG_ON_Y.equals(rsSet.getString("RETAN_ORIG_PRC_FLG"))) {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPrcAmt, (BigDecimal) prcEquip.get("PRC_LIST_EQUIP_PRC_AMT"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPrcAmt, rsSet.getBigDecimal("NEW_PRC_AMT"));
                    }
                    // Minimum Price Amount
                    if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("NEW_MIN_PRC_AMT"))) {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.minPrcAmt, (BigDecimal) prcEquip.get("MIN_PRC_AMT"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.minPrcAmt, rsSet.getBigDecimal("NEW_MIN_PRC_AMT"));
                    }
                    // Max Price Amount
                    if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("NEW_MAX_PRC_AMT"))) {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.maxPrcAmt, (BigDecimal) prcEquip.get("MAX_PRC_AMT"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.maxPrcAmt, rsSet.getBigDecimal("NEW_MAX_PRC_AMT"));
                    }
                    // Price Term UOM Code
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcTermUomCd, (String) prcEquip.get("PRC_TERM_UOM_CD"));
                    // Price Term AOT
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcTermAot, (BigDecimal) prcEquip.get("PRC_TERM_AOT"));
                    // Customer Bid Quantity
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.custBidQty, (BigDecimal) prcEquip.get("CUST_BID_QTY"));
                    // Monthly Payment Amount
                    if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("NEW_MLY_PMT_AMT"))) {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.mlyPmtAmt, (BigDecimal) prcEquip.get("MLY_PMT_AMT"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.mlyPmtAmt, rsSet.getBigDecimal("NEW_MLY_PMT_AMT"));
                    }
                    // Minimum Lease Payment Amount
                    if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("NEW_MIN_LEASE_PMT_AMT"))) {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.minLeasePmtAmt, (BigDecimal) prcEquip.get("MIN_LEASE_PMT_AMT"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.minLeasePmtAmt, rsSet.getBigDecimal("NEW_MIN_LEASE_PMT_AMT"));
                    }
                    // Max Lease Payment Amount
                    if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("NEW_MAX_LEASE_PMT_AMT"))) {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.maxLeasePmtAmt, (BigDecimal) prcEquip.get("MAX_LEASE_PMT_AMT"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.maxLeasePmtAmt, rsSet.getBigDecimal("NEW_MAX_LEASE_PMT_AMT"));
                    }
                    // Price Formula Primary Key
                    inPrcListEquipTMsg.prcFmlaPk.clear();
                    // Open Market Flag
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.openMktFlg, (String) prcEquip.get("OPEN_MKT_FLG"));
                    // Quota Revenue Amount
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.quotRevAmt, (BigDecimal) prcEquip.get("QUOT_REV_AMT"));
                    // Compensation Code
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.compCd, (String) prcEquip.get("COMP_CD"));
                    // Effective From Date
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.effFromDt, this.procDt);
                    // Effective Through Date
                    inPrcListEquipTMsg.effThruDt.clear();
                    // Delete Flag
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.delFlg, ZYPConstant.FLG_OFF_N);

                    // set value on PRC_LIST_EQUIP_TRX
                    PRC_LIST_EQUIP_TRX_RQSTTMsg prcEquipTrxTMsg = new PRC_LIST_EQUIP_TRX_RQSTTMsg();

                    // Global Company Code
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.glblCmpyCd, this.glblCmpyCd);
                    // Price List Equipment Transaction Request Primary Key
                    BigDecimal prcListEquipTrxRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_TRX_RQST_SQ);
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.prcListEquipTrxRqstPk, prcListEquipTrxRqstPk);
                    // Price List Equipment Request Primary Key
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.prcListEquipRqstPk, rsSet.getBigDecimal("PRC_LIST_EQUIP_RQST_PK"));
                    // Price Category Code
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.prcCatgCd, (String) prcEquip.get("PRC_CATG_CD"));
                    // Old Merchandise Code
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.oldMdseCd, rsSet.getString("OLD_MDSE_CD"));
                    // Old Merchandise Description Short Text
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.oldMdseDescShortTxt, rsSet.getString("OLD_MDSE_DESC_SHORT_TXT"));
                    // New Merchandise Code
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMdseCd, rsSet.getString("NEW_MDSE_CD"));
                    // New Merchandise Description Short Text
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMdseDescShortTxt, rsSet.getString("NEW_MDSE_DESC_SHORT_TXT"));
                    // Last Update Date
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.lastUpdDt, rsSet.getString("LAST_UPD_DT"));
                    // Last Update User ID
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.lastUpdUsrId, rsSet.getString("LAST_UPD_USR_ID"));
                    // Retain Original Price Flag
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.retanOrigPrcFlg, rsSet.getString("RETAN_ORIG_PRC_FLG"));
                    // New Price Amount
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newPrcAmt, inPrcListEquipTMsg.prcListEquipPrcAmt);
                    // New Minimum Price Amount
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMinPrcAmt, inPrcListEquipTMsg.minPrcAmt);
                    // New Max Price Amount
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMaxPrcAmt, inPrcListEquipTMsg.maxPrcAmt);
                    // New Monthly Payment Amount
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMlyPmtAmt, inPrcListEquipTMsg.mlyPmtAmt);
                    // New Minimum Lease Payment Amount
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMinLeasePmtAmt, inPrcListEquipTMsg.minLeasePmtAmt);
                    // New Max Lease Payment Amount
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.newMaxLeasePmtAmt, inPrcListEquipTMsg.maxLeasePmtAmt);
                    // Request Result Status Type Code
                    ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltStsTpCd, RQST_RSLT_STS_TP.SUCCESS);

                    // error check: record duplicate
                    // UPDATE START 2016/05/06 QC#7725
                    // if (!checkDuplicateInPrcListEquip(inPrcListEquipTMsg)) {
                    if (!updateDuplicateInPrcListEquip(inPrcListEquipTMsg)) {
                        ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltStsTpCd, RQST_RSLT_STS_TP.ERROR);
                        String errMsg = S21MessageFunc.clspGetMessage(NMAM0072E, new String[] {"Logical Unique Key" });
                        ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltCmntTxt, errMsg);
                    }
                    // UPDATE END 2016/05/06 QC#7725

                    // error check: Min < Max Price
                    if (ZYPCommonFunc.hasValue(inPrcListEquipTMsg.minPrcAmt) && ZYPCommonFunc.hasValue(inPrcListEquipTMsg.maxPrcAmt)) {
                        if (inPrcListEquipTMsg.minPrcAmt.getValue().compareTo(inPrcListEquipTMsg.maxPrcAmt.getValue()) > 0) {
                            ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltStsTpCd, RQST_RSLT_STS_TP.ERROR);
                            String errMsg = S21MessageFunc.clspGetMessage(NMAM0043E, new String[] {"Min Price", "Max Price" });
                            ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltCmntTxt, errMsg);
                        }
                    }

                    // error check: Min < Max Lease Price
                    if (ZYPCommonFunc.hasValue(inPrcListEquipTMsg.minLeasePmtAmt) && ZYPCommonFunc.hasValue(inPrcListEquipTMsg.maxLeasePmtAmt)) {
                        if (inPrcListEquipTMsg.minLeasePmtAmt.getValue().compareTo(inPrcListEquipTMsg.maxLeasePmtAmt.getValue()) > 0) {
                            ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltStsTpCd, RQST_RSLT_STS_TP.ERROR);
                            String errMsg = S21MessageFunc.clspGetMessage(NMAM0043E, new String[] {"Lease Payment Min", "Lease Payment Max" });
                            ZYPEZDItemValueSetter.setValue(prcEquipTrxTMsg.rqstRsltCmntTxt, errMsg);
                        }
                    }

                    insertTrxList.add(prcEquipTrxTMsg);

                    if (RQST_RSLT_STS_TP.SUCCESS.equals(prcEquipTrxTMsg.rqstRsltStsTpCd.getValue())) {
                        insertPrcList.add(inPrcListEquipTMsg);
                    } else {
                        isSuccess = false;
                        this.totalErrCount += 1;
                    }
                }

                // insert into PRC_LIST_EQUIP
                if (insertPrcList.size() > 0) {
                    insertPrcListEquip(insertPrcList);
                    insertPrcList.clear();
                }
                // insert into PRC_LIST_EQUIP_TRX
                if (insertTrxList.size() > 0) {
                    insertPrcListEquipTrx(insertTrxList);
                    insertTrxList.clear();
                }

                // update PRC_LIST_EQUIP_RQST
                updatePrcListEquipRqst(updateRqstTMsg, isSuccess);
                updateRqstTMsg.clear();
                commit();
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    private List<Map<String, Object>> getPrcListEquip(String updPrcListsText, String oldMdseCd, String prcListActTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_PRC_QLFY_TP_CD, PRC_QLFY_TP.ITEM_CODE);
        queryParam.put(COL_PRC_QLFY_VAL_TXT, oldMdseCd);
        queryParam.put(COL_PKG_UOM_CD, PKG_UOM.EACH);
        queryParam.put(COL_BASE_PKG_UOM_CD, PKG_UOM.EACH);
        queryParam.put(COL_DEL_FLG, ZYPConstant.FLG_OFF_N);
        queryParam.put(COL_ACTV_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(COL_PROC_DATE, this.procDt);

        if (ZYPCommonFunc.hasValue(updPrcListsText)) {
            String[] prcLists = updPrcListsText.split(",", 0);
            String strId = "";
            for (int i = 0; i < prcLists.length; i++) {
                if (i != 0) {
                    strId += "','";
                }
                strId += prcLists[i];
            }

            if (PRC_LIST_ACT_TP.EXCEPT_ALL.equals(prcListActTpCd)) {
                queryParam.put(COL_PRC_CATG_CD_EXCEPT, strId);
            } else if (PRC_LIST_ACT_TP.SPECIFIC_PRICE_LIST_ONLY.equals(prcListActTpCd)) {
                queryParam.put(COL_PRC_CATG_CD_SPECIFIC, strId);
            }
        }

        List<Map<String, Object>> prcListEquip = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrcListEquip", queryParam);
        return prcListEquip;
    }

    // UPDATE START 2016/05/06 QC#7725
    private boolean updateDuplicateInPrcListEquip(PRC_LIST_EQUIPTMsg tMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_PRC_CATG_CD, tMsg.prcCatgCd.getValue());
        queryParam.put(COL_PRC_LIST_EQUIP_CONFIG_NUM, tMsg.prcListEquipConfigNum.getValue());
        queryParam.put(COL_PRC_LIST_EQUIP_CONFIG_NM, tMsg.prcListEquipConfigNm.getValue());
        queryParam.put(COL_PRC_QLFY_TP_CD, tMsg.prcQlfyTpCd.getValue());
        queryParam.put(COL_PRC_QLFY_VAL_TXT, tMsg.prcQlfyValTxt.getValue());
        queryParam.put(COL_PKG_UOM_CD, tMsg.pkgUomCd.getValue());
        queryParam.put(COL_PRC_TERM_UOM_CD, tMsg.prcTermUomCd.getValue());
        queryParam.put(COL_PRC_TERM_AOT, tMsg.prcTermAot.getValue());
        // queryParam.put(COL_EFF_FROM_DT, tMsg.effFromDt.getValue());
        // queryParam.put(COL_EFF_THRU_DT, tMsg.effThruDt.getValue());

        // Integer retCnt = (Integer) this.ssmBatchClient.queryObject("getCntPrcListEquip", queryParam);
        // if (retCnt > 0) {
        // return false;
        // }
        // return true;

        queryParam.put(COL_PROC_DATE, this.procDt);
        List<Map<String, Object>> prcListEquip = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplicatePrcListEquip", queryParam);

        for (Map<String, Object> prcEquip : prcListEquip) {
            PRC_LIST_EQUIPTMsg updtMsg = new PRC_LIST_EQUIPTMsg();
            ZYPEZDItemValueSetter.setValue(updtMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updtMsg.prcListEquipPk, (BigDecimal) prcEquip.get("PRC_LIST_EQUIP_PK"));

            updtMsg = (PRC_LIST_EQUIPTMsg) EZDTBLAccessor.findByKeyForUpdate(updtMsg);

            ZYPEZDItemValueSetter.setValue(updtMsg.effThruDt, ZYPDateUtil.addDays(this.procDt, -1));

            if (ZYPDateUtil.compare(updtMsg.effFromDt.getValue().toString(), updtMsg.effThruDt.getValue().toString()) < 0) {
                EZDTBLAccessor.update(updtMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updtMsg.getReturnCode())) {
                    super.rollback();
                    throw new S21AbendException(NMAM0177E, new String[] {"PRC_LIST_EQUIP" });
                }
            } else {
                return false;
            }
        }
        return true;
    }
    // UPDATE END 2016/05/06 QC#7725

    private void insertPrcListEquip(List<PRC_LIST_EQUIPTMsg> insertList) {

        for (PRC_LIST_EQUIPTMsg tMsg : insertList) {
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.totalErrCount += 1;
                super.rollback();
                throw new S21AbendException(NMAM0176E, new String[] {"PRC_LIST_EQUIP" });
            }
        }
        this.totalNmlCount += insertList.size();
    }

    private void insertPrcListEquipTrx(List<PRC_LIST_EQUIP_TRX_RQSTTMsg> insertList) {

        for (PRC_LIST_EQUIP_TRX_RQSTTMsg tMsg : insertList) {
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.totalErrCount += 1;
                super.rollback();
                throw new S21AbendException(NMAM0176E, new String[] {"PRC_LIST_EQUIP_TRX_RQST" });
            }
        }
        this.totalNmlCount += insertList.size();
    }

    private void updatePrcListEquipRqst(PRC_LIST_EQUIP_RQSTTMsg tMsg, boolean isSuccess) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (PRC_LIST_EQUIP_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        if (isSuccess) {
            ZYPEZDItemValueSetter.setValue(tMsg.rqstStsTpCd, RQST_STS_TP.COMPLETED);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.rqstStsTpCd, RQST_STS_TP.ERROR);
        }

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            this.totalErrCount += 1;
            super.rollback();
            throw new S21AbendException(NMAM0177E, new String[] {"PRC_LIST_EQUIP_RQST" });
        }
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB709001().executeBatch(NMAB709001.class.getSimpleName());
    }
}
