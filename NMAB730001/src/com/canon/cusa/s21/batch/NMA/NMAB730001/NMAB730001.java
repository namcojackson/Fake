/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB730001;

import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.MAX_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.NMAM8424E;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB730001.constant.NMAB730001Constant.ZZZM9026E;

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

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CATG_SYNC_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CATG_SYNC_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Price List Sync Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         M.Ohno          Create          N/A
 * 2016/08/22   Fujitsu         R.Nakamura      Update          QC#12532
 * 2016/08/30   Fujitsu         R.Nakamura      Update          QC#12532
 * 2019/01/11   Fujitsu         C.Hara          Update          QC#29869
 *</pre>
 */
public class NMAB730001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

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

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
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
        ssmParamGetMdse.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetMdse.put("procDt", this.procDt);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMdse", ssmParamGetMdse, execParam);
            rsSet = stmt.executeQuery();

            List<PRC_LIST_EQUIPTMsg> updateList = new ArrayList<PRC_LIST_EQUIPTMsg>();
            List<PRC_LIST_EQUIPTMsg> insertList = new ArrayList<PRC_LIST_EQUIPTMsg>();

            while (rsSet.next()) {

                // 2-1
                Map<String, Object> ssmParamGetPrcEquip = new HashMap<String, Object>();
                ssmParamGetPrcEquip.put("glblCmpyCd", this.glblCmpyCd);
                ssmParamGetPrcEquip.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);
                ssmParamGetPrcEquip.put("mdseCd", rsSet.getString("MDSE_CD"));
                ssmParamGetPrcEquip.put("prcListStruTpCd", PRC_LIST_STRU_TP.EQUIPMENT);
                ssmParamGetPrcEquip.put("prcCatgSyncSrcCd", PRC_CATG_SYNC_SRC.INVEMTORY_COST_FROZEN);
                ssmParamGetPrcEquip.put("procDt", this.procDt);
                ssmParamGetPrcEquip.put("pkgUomCd", rsSet.getString("BASE_PKG_UOM_CD"));

                List<Map< ? , ? >> prcEquipList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getPrcEquip", ssmParamGetPrcEquip);
                List<Map< ? , ? >> updateEquipList = new ArrayList<Map< ? , ? >>();
                List<Map< ? , ? >> insertEquipList = new ArrayList<Map< ? , ? >>();

                // make update and insert list
                for (Map< ? , ? > prcEquip : prcEquipList) {
                    if (prcEquip.get("PRC_FMLA_PK") != null) {
                        continue;
                    }
                    if (prcEquip.get("PRC_LIST_EQUIP_PK") == null) {
                        // Mod Start 2016/08/30
                        Map<String, Object> ssmParamGetPrcCatg = new HashMap<String, Object>();
                        ssmParamGetPrcCatg.put("glblCmpyCd", this.glblCmpyCd);
                        ssmParamGetPrcCatg.put("prcCatgCd", (String) prcEquip.get("DEST_PRC_CATG_CD"));
                        List<BigDecimal> prcCtgListCnt = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getPrcCatgCnt", ssmParamGetPrcCatg);
                        int prcCatgCntInt = prcCtgListCnt.get(0).intValue();

                        if (prcCatgCntInt > 0) {
                            insertEquipList.add(prcEquip);
                        }
                        // Mod End 2016/08/30
                    } else {
                        updateEquipList.add(prcEquip);
                    }
                }

                // 2-3 insert
                for (Map<?, ?> insertEquip : insertEquipList) {
                    BigDecimal amnt = calcPrcAmt(rsSet.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"), //
                            (String) insertEquip.get("PRC_CATG_SYNC_OP_CD"), //
                            (BigDecimal) insertEquip.get("PRC_FMLA_NUM"));
                    if (amnt == null) {
                        S21InfoLogOutput.println(NMAM8424E, new String[] {"MDSE_CD", rsSet.getString("MDSE_CD"), "PRC_CATG_CD", (String) insertEquip.get("DEST_PRC_CATG_CD") });
                        this.totalErrCount = this.totalErrCount + 1;
                        continue;
                    }
                    PRC_LIST_EQUIPTMsg inPrcListEquipTMsg = new PRC_LIST_EQUIPTMsg();
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.glblCmpyCd, this.glblCmpyCd);
                    BigDecimal prcListEquipPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ);
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPk, prcListEquipPk);
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcCatgCd, (String) insertEquip.get("DEST_PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyTpCd, PRC_QLFY_TP.ITEM_CODE);
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyValTxt, rsSet.getString("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.pkgUomCd, rsSet.getString("BASE_PKG_UOM_CD"));

                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPrcAmt, amnt);
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.openMktFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.effFromDt, ZYPDateUtil.addDays(this.procDt, 1));
                    inPrcListEquipTMsg.effThruDt.clear();
                    ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.delFlg, (String) ZYPConstant.FLG_OFF_N);

                    insertList.add(inPrcListEquipTMsg);
                }

                if (updateEquipList.size() > 0) {

                    // 2-2
                    String insertEffThruDt = null;
                    String insertEffFromDt = "00010101";
                    int i = 0;
                    int insertListIndex = 0;
                    Map< ? , ? > nowEquip = null;

                    for (Map< ? , ? > updateEquip : updateEquipList) {
                        if (i != 0 && checkChangeEquip(nowEquip, updateEquip)) {
                            // 2-2-2 insert
                            PRC_LIST_EQUIPTMsg insertTMsg = makeInsertMsg(rsSet, updateEquipList.get(insertListIndex), insertEffThruDt);
                            if (insertTMsg != null) {
                                insertList.add(insertTMsg);
                            }
                            insertEffThruDt = null;
                            insertEffFromDt = "00010101";
                            insertListIndex = i;
                        }
                        PRC_LIST_EQUIPTMsg prcListEquipTMsg = new PRC_LIST_EQUIPTMsg();
                        ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                        ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.prcListEquipPk, (BigDecimal) updateEquip.get("PRC_LIST_EQUIP_PK"));
                        prcListEquipTMsg = (PRC_LIST_EQUIPTMsg) EZDTBLAccessor.findByKeyForUpdate(prcListEquipTMsg);
                        String nextDt = ZYPDateUtil.addDays(this.procDt, 1);
                        String effFromDt = prcListEquipTMsg.effFromDt.getValue();
                        String effThruDt = prcListEquipTMsg.effThruDt.getValue();

                        if (effThruDt == null || effThruDt.isEmpty()) {
                            effThruDt = "99991231";
                        }

                        // 2-2-1 update
                        if (ZYPDateUtil.compare(nextDt, effFromDt) > 0) {
                            if (ZYPDateUtil.compare(nextDt, effThruDt) <= 0) {
                                if (ZYPDateUtil.compare(this.procDt, effThruDt) != 0) {
                                    ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.effThruDt, this.procDt);
                                    updateList.add(prcListEquipTMsg);
                                }
                            }
                            // update InsertIndex
                            if (ZYPDateUtil.compare(effFromDt, insertEffFromDt) >= 0) {
                                insertListIndex = i;
                                insertEffFromDt = effFromDt;
                            }
                        } else if (ZYPDateUtil.compare(nextDt, effFromDt) < 0) {
                            if (insertEffThruDt == null || ZYPDateUtil.compare(insertEffThruDt, effFromDt) >= 0) {
                                insertEffThruDt = ZYPDateUtil.addDays(effFromDt, -1);
                            }
                        } else {
                            ZYPEZDItemValueSetter.setValue(prcListEquipTMsg.delFlg, ZYPConstant.FLG_ON_Y);
                            updateList.add(prcListEquipTMsg);
                        }
                        nowEquip = updateEquip;
                        i++;
                    }

                    // 2-2-2 insert
                    PRC_LIST_EQUIPTMsg insertTMsg = makeInsertMsg(rsSet, updateEquipList.get(insertListIndex), insertEffThruDt);
                    if (insertTMsg != null) {
                        insertList.add(insertTMsg);
                    }
                }

                if (updateList.size() > 0) {
                    excecuteUpdate(updateList);
                    updateList.clear();
                    super.commit();
                }
                if (insertList.size() > 0) {
                    excecuteInsert(insertList);
                    insertList.clear();
                    super.commit();
                }
            }

            // Add Start 2016/08/30 QC#12532
            Map<String, Object> ssmParamGetPrcCatgSync = new HashMap<String, Object>();
            ssmParamGetPrcCatgSync.put("glblCmpyCd", this.glblCmpyCd);
            List<Map<?, ?>> prcCtgListSync = (List<Map<?, ?>>) this.ssmBatchClient.queryObjectList("getPrcCatgSyncList", ssmParamGetPrcCatgSync);

            List<Map<?, ?>> setFullPriceList = new ArrayList<Map<?, ?>>();
            for (Map<?, ?> prcList : prcCtgListSync) {
                Map<String, Object> ssmParamGetPrcCatg = new HashMap<String, Object>();
                ssmParamGetPrcCatg.put("glblCmpyCd", this.glblCmpyCd);
                ssmParamGetPrcCatg.put("prcCatgCd", (String) prcList.get("PRC_CATG_CD"));
                List<BigDecimal> prcCtgListCnt = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getPrcCatgCnt", ssmParamGetPrcCatg);
                int prcCatgCntInt = prcCtgListCnt.get(0).intValue();

                if (prcCatgCntInt == 0 && checkGetPriceList(setFullPriceList, (String) prcList.get("PRC_CATG_CD"))) {
                    setFullPriceList.add(prcList);
                }
            }

            if (setFullPriceList.size() > 0) {

                PreparedStatement stmtPrice = null;
                ResultSet rsSetPrice = null;
                // 2019/01/11 QC#29869 Add Start
                S21SsmExecutionParameter execParamPrice = new S21SsmExecutionParameter();
                execParamPrice.setFetchSize(DEFAULT_FETCH_SIZE);
                execParamPrice.setMaxRows(0);
                execParamPrice.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParamPrice.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE); 
                execParamPrice.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
                // 2019/01/11 QC#29869 Add End

                try {
                    Map<String, Object> ssmParamGetFullPrcEquip = new HashMap<String, Object>();
                    ssmParamGetFullPrcEquip.put("glblCmpyCd", this.glblCmpyCd);
                    ssmParamGetFullPrcEquip.put("rgtnCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                    // 2019/01/11 QC#29869 Mod Start
                    //stmtPrice = this.ssmLLClient.createPreparedStatement("getMdse", ssmParamGetFullPrcEquip, execParam);
                    stmtPrice = this.ssmLLClient.createPreparedStatement("getMdse", ssmParamGetFullPrcEquip, execParamPrice);
                    // 2019/01/11 QC#29869 Mod End
                    rsSetPrice = stmtPrice.executeQuery();

                    List<PRC_LIST_EQUIPTMsg> fullInsertList = new ArrayList<PRC_LIST_EQUIPTMsg>();

                    for (Map<?, ?> insertPrcCtg : setFullPriceList) {

                        rsSetPrice.beforeFirst(); // 2019/01/11 QC#29869 Add

                        while (rsSetPrice.next()) {

                            BigDecimal amnt = calcPrcAmt(rsSetPrice.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"), //
                                    (String) insertPrcCtg.get("PRC_CATG_SYNC_OP_CD"), //
                                    (BigDecimal) insertPrcCtg.get("PRC_FMLA_NUM"));
                            if (amnt == null) {
                                S21InfoLogOutput.println(NMAM8424E, new String[] {"MDSE_CD", rsSetPrice.getString("MDSE_CD"), "PRC_CATG_CD", (String) insertPrcCtg.get("DEST_PRC_CATG_CD") });
                                this.totalErrCount = this.totalErrCount + 1;
                                continue;
                            }

                            PRC_LIST_EQUIPTMsg inPrcListEquipTMsg = new PRC_LIST_EQUIPTMsg();
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.glblCmpyCd, this.glblCmpyCd);
                            BigDecimal prcListEquipPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ);
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPk, prcListEquipPk);
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcCatgCd, (String) insertPrcCtg.get("PRC_CATG_CD"));
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyTpCd, PRC_QLFY_TP.ITEM_CODE);
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyValTxt, rsSetPrice.getString("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.pkgUomCd, rsSetPrice.getString("BASE_PKG_UOM_CD"));

                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPrcAmt, amnt);
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.openMktFlg, ZYPConstant.FLG_OFF_N);
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.effFromDt, ZYPDateUtil.addDays(this.procDt, 1));
                            inPrcListEquipTMsg.effThruDt.clear();
                            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.delFlg, (String) ZYPConstant.FLG_OFF_N);

                            fullInsertList.add(inPrcListEquipTMsg);

                            if (fullInsertList.size() >= DEFAULT_COMMIT_UNIT) {
                                excecuteInsert(fullInsertList);
                                fullInsertList.clear();
                                super.commit();
                            }
                        }
                    }

                    if (fullInsertList.size() > 0) {
                        excecuteInsert(fullInsertList);
                        fullInsertList.clear();
                        super.commit();
                    }
                } catch (SQLException e) {
                    super.sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(stmtPrice, rsSetPrice);
                }
            }
            // Add End 2016/08/22 QC#12532

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

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB730001().executeBatch(NMAB730001.class.getSimpleName());

    }

    /**
     * calcPrcAmt
     * @param costAmt BigDecimal
     * @param opCd String
     * @param fmlaNum BigDecimal
     */
    private BigDecimal calcPrcAmt(BigDecimal costAmt, String opCd, BigDecimal fmlaNum) {
        if (costAmt == null) {
            return BigDecimal.ZERO;
        } else if (fmlaNum == null) {
            return costAmt;
        }
        BigDecimal returnAmt = BigDecimal.ZERO;
        if (PRC_CATG_SYNC_OP.MULTIPLY.equals(opCd)) {
            returnAmt = costAmt.multiply(fmlaNum);
        } else if (PRC_CATG_SYNC_OP.ADD.equals(opCd)) {
            returnAmt = costAmt.add(fmlaNum);
        } else if (PRC_CATG_SYNC_OP.DIVIDE.equals(opCd)) {
            if (BigDecimal.ZERO.compareTo(fmlaNum) == 0) {
                return BigDecimal.ZERO;
            }
            returnAmt = costAmt.divide(fmlaNum, 2, BigDecimal.ROUND_HALF_UP);
        } else if (PRC_CATG_SYNC_OP.SUBTRACT.equals(opCd)) {
            returnAmt = costAmt.subtract(fmlaNum);
        }

        returnAmt = returnAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (MAX_AMT.compareTo(returnAmt.abs()) <= 0) {
            this.termCd = TERM_CD.WARNING_END;
            return null;
        }

        return returnAmt;
    }

    /**
     * excecuteUpdate
     * @param updateList List<PRC_LIST_EQUIPTMsg>
     */
    private void excecuteUpdate(List<PRC_LIST_EQUIPTMsg> updateList) {

        int updateCount = S21FastTBLAccessor.update(updateList.toArray(new PRC_LIST_EQUIPTMsg[updateList.size()]));
        int updateListSize = updateList.size();

        if (updateCount != updateListSize) {
            this.totalErrCount += updateListSize - updateCount;
            super.rollback();
            throw new S21AbendException(NMAM0177E, new String[] {"PRC_LIST_EQUIP" });
        }

        this.totalNmlCount += updateListSize;
    }

    /**
     * excecuteInsert
     * @param insertList List<PRC_LIST_EQUIPTMsg>
     */
    private void excecuteInsert(List<PRC_LIST_EQUIPTMsg> insertList) {

        int insertCount = S21FastTBLAccessor.insert(insertList.toArray(new PRC_LIST_EQUIPTMsg[insertList.size()]));
        int insertListSize = insertList.size();

        if (insertCount != insertListSize) {
            this.totalErrCount += insertListSize - insertCount;
            super.rollback();
            throw new S21AbendException(NMAM0176E, new String[] {"PRC_LIST_EQUIP" });
        }

        this.totalNmlCount += insertListSize;
    }

    /**
     * excecuteInsert
     * @param insertList List<PRC_LIST_EQUIPTMsg>
     * @throws SQLException
     */
    private PRC_LIST_EQUIPTMsg makeInsertMsg(ResultSet rsSet, Map< ? , ? > updateEquip, String insertEffThruDt) throws SQLException {
        BigDecimal amnt = calcPrcAmt(rsSet.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"), // /
                (String) updateEquip.get("PRC_CATG_SYNC_OP_CD"), //
                (BigDecimal) updateEquip.get("PRC_FMLA_NUM"));
        if (amnt != null) {
            PRC_LIST_EQUIPTMsg inPrcListEquipTMsg = new PRC_LIST_EQUIPTMsg();
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal prcListEquipPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ);
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPk, prcListEquipPk);
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcCatgCd, (String) updateEquip.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipConfigNum, (BigDecimal) updateEquip.get("PRC_LIST_EQUIP_CONFIG_NUM"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipConfigNm, (String) updateEquip.get("PRC_LIST_EQUIP_CONFIG_NM"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyTpCd, PRC_QLFY_TP.ITEM_CODE);
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcQlfyValTxt, rsSet.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.pkgUomCd, (String) updateEquip.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcListEquipPrcAmt, amnt);
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.minPrcAmt, (BigDecimal) updateEquip.get("MIN_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.maxPrcAmt, (BigDecimal) updateEquip.get("MAX_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcTermUomCd, (String) updateEquip.get("PRC_TERM_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcTermAot, (BigDecimal) updateEquip.get("PRC_TERM_AOT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.custBidQty, (BigDecimal) updateEquip.get("CUST_BID_QTY"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.mlyPmtAmt, (BigDecimal) updateEquip.get("MLY_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.minLeasePmtAmt, (BigDecimal) updateEquip.get("MIN_LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.maxLeasePmtAmt, (BigDecimal) updateEquip.get("MAX_LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.prcFmlaPk, (BigDecimal) updateEquip.get("PRC_FMLA_PK"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.openMktFlg, (String) updateEquip.get("OPEN_MKT_FLG"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.quotRevAmt, (BigDecimal) updateEquip.get("QUOT_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.compCd, (String) updateEquip.get("COMP_CD"));
            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.effFromDt, ZYPDateUtil.addDays(this.procDt, 1));
            if (insertEffThruDt != null) {
                ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.effThruDt, insertEffThruDt);
            } else {
                inPrcListEquipTMsg.effThruDt.clear();
            }

            ZYPEZDItemValueSetter.setValue(inPrcListEquipTMsg.delFlg, (String) updateEquip.get("DEL_FLG"));
            return inPrcListEquipTMsg;
        } else {
            S21InfoLogOutput.println(NMAM8424E, new String[] {"MDSE_CD", rsSet.getString("MDSE_CD"), "PRC_CATG_CD", (String) updateEquip.get("PRC_CATG_CD") });
            this.totalErrCount = this.totalErrCount + 1;
            return null;
        }

    }

    /**
     * Changed Value Exist Check
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    private Boolean checkChangeValue(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Changed Value Exist Check
     * @param str1 BigDecimal
     * @param str2 BigDecimal
     * @return boolean
     */
    private Boolean checkChangeValue(BigDecimal str1, BigDecimal str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkChangeEquip
     * @param equip1 Map< ? , ? >
     * @param equip2 Map< ? , ? >
     * @return boolean
     */
    private Boolean checkChangeEquip(Map< ? , ? > equip1, Map< ? , ? > equip2) {
        if (checkChangeValue((String) equip1.get("PRC_CATG_CD"), (String) equip2.get("PRC_CATG_CD")) //
                || checkChangeValue((BigDecimal) equip1.get("PRC_LIST_EQUIP_CONFIG_NUM"), (BigDecimal) equip2.get("PRC_LIST_EQUIP_CONFIG_NUM")) //
                || checkChangeValue((String) equip1.get("PRC_LIST_EQUIP_CONFIG_NM"), (String) equip2.get("PRC_LIST_EQUIP_CONFIG_NM")) //
                || checkChangeValue((String) equip1.get("PRC_QLFY_TP_CD"), (String) equip2.get("PRC_QLFY_TP_CD")) //
                || checkChangeValue((String) equip1.get("PRC_QLFY_VAL_TXT"), (String) equip2.get("PRC_QLFY_VAL_TXT")) //
                || checkChangeValue((String) equip1.get("PKG_UOM_CD"), (String) equip2.get("PKG_UOM_CD")) //
                || checkChangeValue((String) equip1.get("PRC_TERM_UOM_CD"), (String) equip2.get("PRC_TERM_UOM_CD")) //
                || checkChangeValue((BigDecimal) equip1.get("PRC_TERM_AOT"), (BigDecimal) equip2.get("PRC_TERM_AOT")) //
        ) {
            return true;
        }
        return false;
    }

    // Add Start 2016/08/30 QC#12532
    /**
     * checkChangeEquip
     * @param setFullPriceList List<String>
     * @param priceList String
     * @return boolean
     */
    private Boolean checkGetPriceList(List<Map<?, ?>> setFullPriceList, String prcList) {

        for (Map<?, ?> getPriceList : setFullPriceList) {
            if (prcList.equals((String) getPriceList.get("PRC_CATG_CD"))) {
                return false;
            }
        }

        return true;
    }
    // Add End 2016/08/30 QC#12532
}
