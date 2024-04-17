/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB703001;

import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.COMPDTMAX;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.COMPDTMAX_VALUE;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.COPY_RSLT_TP_CD_01;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.DELFLG_N;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.EXTN_KEY;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.EX_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.GLOBAL_CMPY_CD_01;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.GLOBAL_CMPY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.MAX_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.MAX_SORT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.MESSAGE_PARAM_CALC;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM0072E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM8020E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM8061E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM8464E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NMAM8465E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.NZZM0003E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_CATG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_CATG_CD_01;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_CATG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_EQUIP_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_ADDL_CHRG;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_EQUIP;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_LEASE_RATE;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_LEASE_RTRN;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_SPLY;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_SVC;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_SVC_TIER;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRC_LIST_TABLE_TI_VAL;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.PRICE_LIST_TABLE_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB703001.constant.NMAB703001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.COPY_PRC_LIST_RQSTTMsg;
import business.db.COPY_PRC_LIST_RQSTTMsgArray;
import business.db.DS_PRC_CATG_SUBTMsg;
import business.db.DS_PRC_CATG_SUBTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CUST_AUDCTMsg;
import business.db.PRC_EQUIP_CONTR_ATTRBTMsg;
import business.db.PRC_GENL_CONTR_ATTRBTMsg;
import business.db.PRC_LIST_ADDL_CHRGTMsg;
import business.db.PRC_LIST_EQUIPTMsg;
import business.db.PRC_LIST_EQUIP_DTLTMsg;
import business.db.PRC_LIST_LEASE_RATETMsg;
import business.db.PRC_LIST_LEASE_RTRNTMsg;
import business.db.PRC_LIST_SPLY_PGMTMsg;
import business.db.PRC_LIST_SVCTMsg;
import business.db.PRC_LIST_SVC_TIERTMsg;
import business.db.PRC_LIST_TI_VALTMsg;
import business.db.PRC_SVC_CONTR_ATTRBTMsg;
import business.db.PRC_SVC_TIER_CONTR_ATTRBTMsg;
import business.db.PRC_TRX_AUDCTMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COPY_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EFF_APPLY_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CALC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PCT_AMT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NMAB7030 Copy Price List Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Fujitsu         M.Suzuki        Create          N/A
 * 2016/05/23   Fujitsu         M.Suzuki        Update          QC#7769
 * 2018/08/23   Fujitsu         M.Yamada        Update          QC#27264
 *</pre>
 */
public class NMAB703001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** commitUnit */
    private int commitUnit = 0;

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

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB703001().executeBatch(NMAB703001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            S21InfoLogOutput.println(ZZZM9025E, new String[] {"Global Company Code" });
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            S21InfoLogOutput.println(ZZZM9026E, new String[] {"Global Company Code" });
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {

        // COPY_PRC_LIST_RQST
        COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg = new COPY_PRC_LIST_RQSTTMsg();
        copyListRqstTmsg.setSQLID("001");
        copyListRqstTmsg.setConditionValue(COPY_RSLT_TP_CD_01, COPY_RSLT_TP.SUBMITTED);
        copyListRqstTmsg.setConditionValue(GLOBAL_CMPY_CD_01, glblCmpyCd);
        COPY_PRC_LIST_RQSTTMsgArray copyListRqstArray = (COPY_PRC_LIST_RQSTTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(copyListRqstTmsg);

        if (copyListRqstArray.length() == 0) {
            return;
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            for (int i = 0; i < copyListRqstArray.length(); i++) {
                copyListRqstTmsg = (COPY_PRC_LIST_RQSTTMsg) copyListRqstArray.get(i);
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
                ssmParam.put(PRC_CATG_NM, copyListRqstTmsg.oldPrcListNm);
                Map<String, Object> struMap = NWZC150001Common.autoCast(this.ssmBatchClient.queryObject("getPriceListStruType", ssmParam));
                if (struMap == null) {
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NZZM0003E, null);
                    commitTable();
                    continue;
                }
                String struTpCd = (String) struMap.get("PRC_LIST_STRU_TP_CD");
                String prcCatgCd = (String) struMap.get("PRC_CATG_CD");

                if (isExistPrcCatgNm(copyListRqstTmsg)) {
                    continue;
                }

                if (isHeaderDetailPeriodOver(struTpCd, copyListRqstTmsg)) {
                    continue;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) //
                        || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt) // QC#27264
                            && isDetailDuplicate(copyListRqstTmsg, prcCatgCd, struTpCd)) {
                        continue;
                    }
                }

                // Header
                String newPrcCatgCd = ZYPExtnNumbering.getUniqueID(this.glblCmpyCd, EXTN_KEY);
                if (ZYPConstant.FLG_ON_Y.equals(copyListRqstTmsg.mainPrcListInfoFlg.getValue())) {
                    // PRC_CATG
                    if (!copyPrcCatg(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }

                    // DS_PRC_CATG_SUB
                    if (!copyDsPrcCatgSub(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }
                } else {
                    //2016/05/25 QC#7769------
//                    termCd = TERM_CD.ABNORMAL_END;
//                    S21InfoLogOutput.println(NMAM8020E);
//                    throw new S21AbendException(NMAM8020E);
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM8020E, null);
                    commitTable();
                    continue;
                    //2016/05/25 QC#7769------
                }

                // PRC_CUST_AUDC
                if (!copyPrcCustAudc(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                    continue;
                }

                // PRC_TRX_AUDC
                if (!copyPrcTrxAudc(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                    continue;
                }

                if (PRC_LIST_STRU_TP.EQUIPMENT.equals(struTpCd)) { //2016/05/23 QC#7769------
                    // PRC_GENL_CONTR_ATTRB
                    if (!copyPrcGenlCntrAttrb(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }

                    // PRC_EQUIP_CONTR_ATTRB
                    if (!copyPrcEquipCntrAttrb(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }
                } else if (PRC_LIST_STRU_TP.SERVICE.equals(struTpCd)) { //2016/05/23 QC#7769------
                    // PRC_GENL_CONTR_ATTRB
                    if (!copyPrcGenlCntrAttrb(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }

                    // PRC_SVC_CONTR_ATTRB
                    if (!copyPrcSvcCntrAttrb(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }

                } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(struTpCd)) { //2016/05/23 QC#7769------
                    // PRC_SVC_TIER_CONTR_ATTRB
                    if (!copyPrcSvcTierAttrb(copyListRqstTmsg, prcCatgCd, newPrcCatgCd)) {
                        continue;
                    }
                }

                if (ZYPConstant.FLG_ON_Y.equals(copyListRqstTmsg.copyPrcFlg.getValue())) {

                    if (PRC_LIST_STRU_TP.EQUIPMENT.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_EQUIP
                        if (!copyPrcListEquip(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.SERVICE.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_SVC
                        if (!copyPrcListSvc(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_SVC_TIER
                        if (!copyPrcListSvcTier(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_ADDL_CHRG
                        if (!copyPrcListAddlChrg(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_SPLY_PGM
                        if (!copyPrcListSplyPgm(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.LEASE_RATES.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_LEASE_RATE
                        if (!copyPrcListLeaseRate(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_LEASE_RTRN
                        if (!copyPrcListLeaseRtrn(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }

                    } else if (PRC_LIST_STRU_TP.TRADE_INS.equals(struTpCd)) { //2016/05/23 QC#7769------
                        // PRC_LIST_TI_VAL
                        if (!copyPrcListTiVal(copyListRqstTmsg, prcCatgCd, newPrcCatgCd, execParam)) {
                            continue;
                        }
                    }
                }

                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.SUCCESS, null, null);
                commitTable();
                totalNmlCount++;

            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private boolean isDetailDuplicate(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String struTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
//        ssmParam.put(EFF_FROM_DT, copyListRqstTmsg.effFromDt); // QC#27264
        ssmParam.put(EFF_FROM_DT, this.batProcDt); // QC#27264
        ssmParam.put(EFF_THRU_DT, this.batProcDt); // QC#27264
        ssmParam.put(ROW_NUM, 1);
        ssmParam.put(EX_COUNT, 1);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        ssmParam.put(COMPDTMAX, COMPDTMAX_VALUE);

        String sqlId = null;
        //2016/05/23 QC#7769 start------
        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(struTpCd)) {
            sqlId = "checkEquipDuplicate";
        } else if (PRC_LIST_STRU_TP.SERVICE.equals(struTpCd)) {
            sqlId = "checkSvcDuplicate";
        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(struTpCd)) {
            sqlId = "checkSvcTierDuplicate";
        } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(struTpCd)) {
            sqlId = "checkAddlChrgDuplicate";
        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(struTpCd)) {
            sqlId = "checkSplyPgmDuplicate";
        } else if (PRC_LIST_STRU_TP.LEASE_RATES.equals(struTpCd)) {
            sqlId = "checkLeaseRateDuplicate";
        } else if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(struTpCd)) {
            sqlId = "checkLeaseRtrnDuplicate";
        } else if (PRC_LIST_STRU_TP.TRADE_INS.equals(struTpCd)) {
            sqlId = "checkTiValDuplicate";
        }
        //2016/05/23 QC#7769 end------

        Map<String, Object> detailMap = NWZC150001Common.autoCast(this.ssmBatchClient.queryObject(sqlId, ssmParam));
        if (detailMap != null) {
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0072E, new String[] {"Effective Date" });
            commitTable();
            return true;
        } else {
            return false;
        }
    }

    private boolean isHeaderDetailPeriodOver(String struTpCd, COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg) {
        String tableName = getPeriodCheckTableName(struTpCd);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_NM, copyListRqstTmsg.oldPrcListNm);
        ssmParam.put(PRICE_LIST_TABLE_NM, tableName);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        Map<String, Object> periodMap = NWZC150001Common.autoCast(this.ssmBatchClient.queryObject("getPriceListPeriod", ssmParam));
        String headerFrom = (String) periodMap.get("HEADER_FROM");
        String detailFrom = (String) periodMap.get("DETAIL_FROM");

        if (EFF_APPLY_LVL_TP.HEADER_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
            headerFrom = copyListRqstTmsg.effFromDt.getValue();
        } else if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
            detailFrom = copyListRqstTmsg.effFromDt.getValue();
        } else if (EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
            headerFrom = copyListRqstTmsg.effFromDt.getValue();
            detailFrom = copyListRqstTmsg.effFromDt.getValue();
        }

        if (ZYPCommonFunc.hasValue(detailFrom)) {
            if (headerFrom.compareTo(detailFrom) > 0) {
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM8061E, new String[] {"Effective Date From(Price List Value Level)", "Effective Date From(Header Level)" });
                commitTable();
                return true;
            }
        }
        return false;
    }

    private void commitTable() {
        super.commit();
    }

    private void rollbackTable() {
        super.rollback();
    }

    private boolean copyPrcCatg(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd) {

        // PRC_CATG
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);
        prcCatgTMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatgTMsg);
        if (prcCatgTMsg == null) {
            rollbackTable();
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Category", prcCatgCd) });
            commitTable();
            return false;
        }
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, newPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgNm, copyListRqstTmsg.newPrcListNm);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgDescTxt, copyListRqstTmsg.newPrcListNm);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcListDplyNm, copyListRqstTmsg.newPrcListNm);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.actvFlg, copyListRqstTmsg.actvFlg);
        setEffdate(copyListRqstTmsg, prcCatgTMsg);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);

        //2016/05/23 QC#7769 start------
        //BigDecimal maxSortNum = (BigDecimal) this.ssmBatchClient.queryObject("getPrcCatgMaxSortNum", params);
        Map<String, Object> maxSortMap = NWZC150001Common.autoCast(this.ssmBatchClient.queryObject("getPrcCatgMaxSortNum", params));
        BigDecimal maxSortNum = null;
        if (maxSortMap == null || maxSortMap.get("MAX_SORT_NUM") == null) {
            maxSortNum = BigDecimal.ONE;
        } else {
            maxSortNum = (BigDecimal) maxSortMap.get("MAX_SORT_NUM");
        }
        //2016/05/23 QC#7769 end------
        if (maxSortNum.intValue() >= MAX_SORT_NUM.intValue()) {
            maxSortNum = MAX_SORT_NUM;
        } else {
            maxSortNum = maxSortNum.add(BigDecimal.ONE);
        }
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgSortNum, maxSortNum);

        EZDTBLAccessor.create(prcCatgTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcCatgTMsg.getReturnCode())) {
            rollbackTable();
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Category", prcCatgCd) });
            commitTable();
            return false;
        }

        return true;
    }

    private String getMessageParam(String tableNm, String prcCatgCd) {
        StringBuilder sb = new StringBuilder();
        sb.append(tableNm);
        sb.append(":Primarykey:");
        sb.append(prcCatgCd);

        return sb.toString();
    }

    private boolean copyDsPrcCatgSub(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd) {

        // DS_PRC_CATG_SUB
        DS_PRC_CATG_SUBTMsg tmsg = new DS_PRC_CATG_SUBTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue(PRC_CATG_CD_01, prcCatgCd);
        tmsg.setConditionValue(GLOBAL_CMPY_CD_01, glblCmpyCd);
        DS_PRC_CATG_SUBTMsgArray copyListRqstArray = (DS_PRC_CATG_SUBTMsgArray) EZDTBLAccessor.findByCondition(tmsg);

        for (int i = 0; i < copyListRqstArray.length(); i++) {

            tmsg = (DS_PRC_CATG_SUBTMsg) copyListRqstArray.get(i);
            DS_PRC_CATG_SUBTMsg dsPrcCatgSubTMsg = new DS_PRC_CATG_SUBTMsg();
            ZYPEZDItemValueSetter.setValue(dsPrcCatgSubTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsPrcCatgSubTMsg.dsPrcCatgSubPk, tmsg.dsPrcCatgSubPk);
            dsPrcCatgSubTMsg = (DS_PRC_CATG_SUBTMsg) EZDTBLAccessor.findByKey(dsPrcCatgSubTMsg);
            if (dsPrcCatgSubTMsg == null) {
                rollbackTable();
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Direct Sales Price Category Sub", tmsg.dsPrcCatgSubPk.getValue().toString()) });
                commitTable();
                return false;
            }
            ZYPEZDItemValueSetter.setValue(dsPrcCatgSubTMsg.prcCatgCd, newPrcCatgCd);
            BigDecimal newSubPrcCatgpk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_PRC_CATG_SUB_SQ);
            ZYPEZDItemValueSetter.setValue(dsPrcCatgSubTMsg.dsPrcCatgSubPk, newSubPrcCatgpk);
            EZDTBLAccessor.create(dsPrcCatgSubTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsPrcCatgSubTMsg.getReturnCode())) {
                rollbackTable();
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Direct Sales Price Category Sub", tmsg.dsPrcCatgSubPk.getValue().toString()) });
                commitTable();
                return false;
            }
        }

        return true;
    }

    private void setEffdate(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, PRC_CATGTMsg prcCatgTMsg) {
        if (EFF_APPLY_LVL_TP.HEADER_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) //
                || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
            if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(prcCatgTMsg.effFromDt, copyListRqstTmsg.effFromDt);
            }
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.effThruDt, copyListRqstTmsg.effToDt);
        }
    }

    private boolean copyPrcCustAudc(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_CUST_AUDC(AccessType:No)
        if (copyListRqstTmsg.prcListAccsTpFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
            PRC_CUST_AUDCTMsg prcCustAudcTMsg = new PRC_CUST_AUDCTMsg();
            prcCustAudcTMsg.pubFlg_01.setValue(ZYPConstant.FLG_ON_Y);
            prcCustAudcTMsg.defPrcListFlg.setValue(ZYPConstant.FLG_OFF_N);
            prcCustAudcTMsg.reqFlg.setValue(ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.glblCmpyCd, this.glblCmpyCd);

            if (EFF_APPLY_LVL_TP.HEADER_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                PRC_CATGTMsg prcCatgTMsg = null;
                if (!ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt) || !ZYPCommonFunc.hasValue(copyListRqstTmsg.effToDt)) {
                    prcCatgTMsg = new PRC_CATGTMsg();
                    ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);
                    prcCatgTMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatgTMsg);
                    if (prcCatgTMsg == null) {
                        rollbackTable();
                        updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Direct Sales Price Category", prcCatgCd) });
                        commitTable();
                        return false;
                    }
                }

                if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                    ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.effFromDt, prcCatgTMsg.effFromDt);
                }

                if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effToDt)) {
                    ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.effThruDt, copyListRqstTmsg.effToDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.effThruDt, prcCatgTMsg.effThruDt);
                }
            }

            BigDecimal prcCustAudcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CUST_AUDC_SQ);
            ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.prcCatgCd, newPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.prcCustAudcPk, prcCustAudcPk);
            EZDTBLAccessor.create(prcCustAudcTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcCustAudcTMsg.getReturnCode())) {
                rollbackTable();
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Customer Audience", newPrcCatgCd) });
                commitTable();
                return false;
            }
            return true;
        }

        // PRC_CUST_AUDC(AccessType:Yes)
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcCustAudcPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_CUST_AUDCTMsg prcCustAudcTMsg = new PRC_CUST_AUDCTMsg();
                ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.prcCustAudcPk, rsSet.getBigDecimal("PRC_CUST_AUDC_PK"));
                prcCustAudcTMsg = (PRC_CUST_AUDCTMsg) EZDTBLAccessor.findByKey(prcCustAudcTMsg);
                if (prcCustAudcTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Customer Audience", rsSet.getBigDecimal("PRC_CUST_AUDC_PK").toString()) });
                    commitTable();
                    return false;
                }
                setEffDate(copyListRqstTmsg, prcCustAudcTMsg);

                BigDecimal prcCustAudcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CUST_AUDC_SQ);
                ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.prcCustAudcPk, prcCustAudcPk);

                EZDTBLAccessor.create(prcCustAudcTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcCustAudcTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Customer Audience", rsSet.getBigDecimal("PRC_CUST_AUDC_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

        return true;

    }

    private void setEffDate(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, PRC_CUST_AUDCTMsg prcCustAudcTMsg) {
        if (EFF_APPLY_LVL_TP.HEADER_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {

            if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.effFromDt, copyListRqstTmsg.effFromDt);
            }
            ZYPEZDItemValueSetter.setValue(prcCustAudcTMsg.effThruDt, copyListRqstTmsg.effToDt);
        }
    }

    private boolean copyPrcTrxAudc(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_TRX_AUDC
        if (ZYPConstant.FLG_OFF_N.equals(copyListRqstTmsg.prcListAccsTpFlg.getValue())) {
            return true;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcTrxAudcPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {

                PRC_TRX_AUDCTMsg prcTrxAudicTMsg = new PRC_TRX_AUDCTMsg();
                ZYPEZDItemValueSetter.setValue(prcTrxAudicTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcTrxAudicTMsg.prcTrxAudcPk, rsSet.getBigDecimal("PRC_TRX_AUDC_PK"));
                prcTrxAudicTMsg = (PRC_TRX_AUDCTMsg) EZDTBLAccessor.findByKey(prcTrxAudicTMsg);
                if (prcTrxAudicTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Transaction Audience", rsSet.getBigDecimal("PRC_TRX_AUDC_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.HEADER_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcTrxAudicTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }
                    ZYPEZDItemValueSetter.setValue(prcTrxAudicTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal prcTrxAudcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_TRX_AUDC_SQ);
                ZYPEZDItemValueSetter.setValue(prcTrxAudicTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcTrxAudicTMsg.prcTrxAudcPk, prcTrxAudcPk);
                EZDTBLAccessor.create(prcTrxAudicTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcTrxAudicTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Transaction Audience", rsSet.getBigDecimal("PRC_TRX_AUDC_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

        return true;

    }

    private boolean copyPrcListEquip(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_EQUIP
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcLstEquipPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_EQUIPTMsg prcEquipTMsg = new PRC_LIST_EQUIPTMsg();
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcListEquipPk, rsSet.getBigDecimal("PRC_LIST_EQUIP_PK"));
                prcEquipTMsg = (PRC_LIST_EQUIPTMsg) EZDTBLAccessor.findByKey(prcEquipTMsg);
                if (prcEquipTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Equipment", rsSet.getBigDecimal("PRC_LIST_EQUIP_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcEquipTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }
                    ZYPEZDItemValueSetter.setValue(prcEquipTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal newPrcListEquipPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcListEquipPk, newPrcListEquipPk);

                if (ZYPCommonFunc.hasValue(copyListRqstTmsg.copyPrcAmtRate.getValue()) && ZYPCommonFunc.hasValue(prcEquipTMsg.prcListEquipPrcAmt)) {
                    BigDecimal amt = calcPrice(copyListRqstTmsg, prcEquipTMsg.prcListEquipPrcAmt.getValue());
                    if (isOverflow(amt)) {
                        rollbackTable();
                        updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM8464E, new String[] {MESSAGE_PARAM_CALC });
                        commitTable();
                        return false;
                    }
                    if (isNegative(amt)) {
                        rollbackTable();
                        updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM8465E, new String[] {MESSAGE_PARAM_CALC });
                        commitTable();
                        return false;
                    }
                    ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcListEquipPrcAmt, amt);
                }

                EZDTBLAccessor.create(prcEquipTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcEquipTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Equipment", rsSet.getBigDecimal("PRC_LIST_EQUIP_PK").toString()) });
                    commitTable();
                    return false;
                }

                // PRC_LIST_EQUIP_DT
                if (!copyPrcListEquipDtl(copyListRqstTmsg, newPrcCatgCd, rsSet.getBigDecimal("PRC_LIST_EQUIP_PK"), newPrcListEquipPk, execParam)) {
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

        return true;

    }

    private boolean copyPrcListSvc(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_SVC
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcLstSvc", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_SVCTMsg prcSvcTMsg = new PRC_LIST_SVCTMsg();
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.prcListSvcPk, rsSet.getBigDecimal("PRC_LIST_SVC_PK"));
                prcSvcTMsg = (PRC_LIST_SVCTMsg) EZDTBLAccessor.findByKey(prcSvcTMsg);
                if (prcSvcTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Values/Transaction", rsSet.getBigDecimal("PRC_LIST_SVC_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcSvcTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }
                    ZYPEZDItemValueSetter.setValue(prcSvcTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal prcListSvcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SVC_SQ);
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.prcListSvcPk, prcListSvcPk);
                EZDTBLAccessor.create(prcSvcTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcSvcTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Values/Transaction", rsSet.getBigDecimal("PRC_LIST_SVC_PK").toString()) });
                    commitTable();
                    return false;
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

        return true;
    }

    private boolean copyPrcListSvcTier(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_SVC_TIER
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcLstSvcTier", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_SVC_TIERTMsg prcSvcTMsg = new PRC_LIST_SVC_TIERTMsg();
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.prcListSvcTierPk, rsSet.getBigDecimal("PRC_LIST_SVC_TIER_PK"));
                prcSvcTMsg = (PRC_LIST_SVC_TIERTMsg) EZDTBLAccessor.findByKey(prcSvcTMsg);
                if (prcSvcTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Service Tier", rsSet.getBigDecimal("PRC_LIST_SVC_TIER_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcSvcTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }

                    ZYPEZDItemValueSetter.setValue(prcSvcTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal prcListSvcTierPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SVC_TIER_SQ);
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcSvcTMsg.prcListSvcTierPk, prcListSvcTierPk);

                EZDTBLAccessor.create(prcSvcTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcSvcTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Service Tier", rsSet.getBigDecimal("PRC_LIST_SVC_TIER_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return true;
    }

    private boolean copyPrcListAddlChrg(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_ADDL_CHRG
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcLstAddlChrg", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_ADDL_CHRGTMsg prcAddlChrgTMsg = new PRC_LIST_ADDL_CHRGTMsg();
                ZYPEZDItemValueSetter.setValue(prcAddlChrgTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcAddlChrgTMsg.prcListAddlChrgPk, rsSet.getBigDecimal("PRC_LIST_ADDL_CHRG_PK"));
                prcAddlChrgTMsg = (PRC_LIST_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(prcAddlChrgTMsg);
                if (prcAddlChrgTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Additional Charge", rsSet.getBigDecimal("PRC_LIST_ADDL_CHRG_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcAddlChrgTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }

                    ZYPEZDItemValueSetter.setValue(prcAddlChrgTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal prcListSvcTierPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_ADDL_CHRG_SQ);
                ZYPEZDItemValueSetter.setValue(prcAddlChrgTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcAddlChrgTMsg.prcListAddlChrgPk, prcListSvcTierPk);

                EZDTBLAccessor.create(prcAddlChrgTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcAddlChrgTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Additional Charge", rsSet.getBigDecimal("PRC_LIST_ADDL_CHRG_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return true;
    }

    private boolean copyPrcListSplyPgm(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_SPLY_PGM
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSplyPgmPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_SPLY_PGMTMsg prcListSplyTMsg = new PRC_LIST_SPLY_PGMTMsg();
                ZYPEZDItemValueSetter.setValue(prcListSplyTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcListSplyTMsg.prcListSplyPgmPk, rsSet.getBigDecimal("PRC_LIST_SPLY_PGM_PK"));
                prcListSplyTMsg = (PRC_LIST_SPLY_PGMTMsg) EZDTBLAccessor.findByKey(prcListSplyTMsg);
                if (prcListSplyTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Supply Program", rsSet.getBigDecimal("PRC_LIST_SPLY_PGM_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcListSplyTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }

                    ZYPEZDItemValueSetter.setValue(prcListSplyTMsg.effThruDt, copyListRqstTmsg.effToDt);

                }

                BigDecimal prcListSvcTierPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SPLY_PGM_SQ);
                ZYPEZDItemValueSetter.setValue(prcListSplyTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcListSplyTMsg.prcListSplyPgmPk, prcListSvcTierPk);

                EZDTBLAccessor.create(prcListSplyTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcListSplyTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Supply Program", rsSet.getBigDecimal("PRC_LIST_SPLY_PGM_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return true;
    }

    private boolean copyPrcListLeaseRtrn(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_LEASE_RTRN
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListLeaseRtrnPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_LEASE_RTRNTMsg prcListLeaseRtrnTMsg = new PRC_LIST_LEASE_RTRNTMsg();
                ZYPEZDItemValueSetter.setValue(prcListLeaseRtrnTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcListLeaseRtrnTMsg.prcListLeaseRtrnPk, rsSet.getBigDecimal("PRC_LIST_LEASE_RTRN_PK"));
                prcListLeaseRtrnTMsg = (PRC_LIST_LEASE_RTRNTMsg) EZDTBLAccessor.findByKey(prcListLeaseRtrnTMsg);
                if (prcListLeaseRtrnTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Lease Return", rsSet.getBigDecimal("PRC_LIST_LEASE_RTRN_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcListLeaseRtrnTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }

                    ZYPEZDItemValueSetter.setValue(prcListLeaseRtrnTMsg.effThruDt, copyListRqstTmsg.effToDt);

                }

                BigDecimal prcListSvcTierPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_LEASE_RTRN_SQ);
                ZYPEZDItemValueSetter.setValue(prcListLeaseRtrnTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcListLeaseRtrnTMsg.prcListLeaseRtrnPk, prcListSvcTierPk);

                EZDTBLAccessor.create(prcListLeaseRtrnTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcListLeaseRtrnTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Lease Return", rsSet.getBigDecimal("PRC_LIST_LEASE_RTRN_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return true;
    }

    private boolean copyPrcListTiVal(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_TI_VAL
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListTiValPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_TI_VALTMsg prcListTiValTMsg = new PRC_LIST_TI_VALTMsg();
                ZYPEZDItemValueSetter.setValue(prcListTiValTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcListTiValTMsg.prcListTiValPk, rsSet.getBigDecimal("PRC_LIST_TI_VAL_PK"));
                prcListTiValTMsg = (PRC_LIST_TI_VALTMsg) EZDTBLAccessor.findByKey(prcListTiValTMsg);
                if (prcListTiValTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Trade-In Value", rsSet.getBigDecimal("PRC_LIST_TI_VAL_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcListTiValTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }

                    ZYPEZDItemValueSetter.setValue(prcListTiValTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal prcListSvcTierPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_TI_VAL_SQ);
                ZYPEZDItemValueSetter.setValue(prcListTiValTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcListTiValTMsg.prcListTiValPk, prcListSvcTierPk);

                EZDTBLAccessor.create(prcListTiValTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcListTiValTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Trade-In Value", rsSet.getBigDecimal("PRC_LIST_TI_VAL_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return true;
    }

    private boolean copyPrcListLeaseRate(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd, S21SsmExecutionParameter execParam) throws SQLException {

        // PRC_LIST_LEASE_RATE
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_CD, prcCatgCd);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        // QC#27264
        if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
            ssmParam.put(EFF_FROM_DT, this.batProcDt);
            ssmParam.put(EFF_THRU_DT, this.batProcDt);
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListLeaseRatePk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_LEASE_RATETMsg prcListReaseTMsg = new PRC_LIST_LEASE_RATETMsg();
                ZYPEZDItemValueSetter.setValue(prcListReaseTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcListReaseTMsg.prcListLeaseRatePk, rsSet.getBigDecimal("PRC_LIST_LEASE_RATE_PK"));
                prcListReaseTMsg = (PRC_LIST_LEASE_RATETMsg) EZDTBLAccessor.findByKey(prcListReaseTMsg);
                if (prcListReaseTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Lease Rate", rsSet.getBigDecimal("PRC_LIST_LEASE_RATE_PK").toString()) });
                    commitTable();
                    return false;
                }

                if (EFF_APPLY_LVL_TP.DETAIL_ONLY.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue()) || EFF_APPLY_LVL_TP.HEADER_AND_DETAIL.equals(copyListRqstTmsg.applyLvlEffDtTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(copyListRqstTmsg.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prcListReaseTMsg.effFromDt, copyListRqstTmsg.effFromDt);
                    }
                    ZYPEZDItemValueSetter.setValue(prcListReaseTMsg.effThruDt, copyListRqstTmsg.effToDt);
                }

                BigDecimal prcListReaseRatePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_LEASE_RATE_SQ);
                ZYPEZDItemValueSetter.setValue(prcListReaseTMsg.prcCatgCd, newPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(prcListReaseTMsg.prcListLeaseRatePk, prcListReaseRatePk);

                EZDTBLAccessor.create(prcListReaseTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcListReaseTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Lease Rate", rsSet.getBigDecimal("PRC_LIST_LEASE_RATE_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        return true;
    }

    private boolean isOverflow(BigDecimal amt) {
        BigDecimal returnAmt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (MAX_AMT.compareTo(returnAmt.abs()) <= 0) {
            return true;
        }
        return false;
    }

    private boolean copyPrcListEquipDtl(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String newPrcCatgCd, BigDecimal prcListEquipPk, BigDecimal newprcListEquipPk, S21SsmExecutionParameter execParam) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_LIST_EQUIP_PK, prcListEquipPk);
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcLstEquipDtl", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                PRC_LIST_EQUIP_DTLTMsg prcEquipTMsg = new PRC_LIST_EQUIP_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcListEquipDtlPk, rsSet.getBigDecimal("PRC_LIST_EQUIP_DTL_PK"));
                prcEquipTMsg = (PRC_LIST_EQUIP_DTLTMsg) EZDTBLAccessor.findByKey(prcEquipTMsg);
                if (prcEquipTMsg == null) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NZZM0003E, null);
                    commitTable();
                    return false;
                }

                BigDecimal prcListEquipDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_DTL_SQ);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcListEquipDtlPk, prcListEquipDtlPk);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcListEquipPk, newprcListEquipPk);
                ZYPEZDItemValueSetter.setValue(prcEquipTMsg.prcCatgCd, newPrcCatgCd);

                EZDTBLAccessor.create(prcEquipTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prcEquipTMsg.getReturnCode())) {
                    rollbackTable();
                    updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price List Equipment Detail", rsSet.getBigDecimal("PRC_LIST_EQUIP_DTL_PK").toString()) });
                    commitTable();
                    return false;
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

        return true;
    }

    private boolean copyPrcGenlCntrAttrb(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd) {

        // PRC_GENL_CONTR_ATTRB
        PRC_GENL_CONTR_ATTRBTMsg attbTMsg = new PRC_GENL_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(attbTMsg.glblCmpyCd, this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(copyListRqstTmsg.copyAttrbTrxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, prcCatgCd);
            attbTMsg = (PRC_GENL_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(attbTMsg);
            if (attbTMsg == null) {
                rollbackTable();
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price General Contract Attribute", prcCatgCd) });
                commitTable();
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(attbTMsg.notToExcdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.allwPrcDevnFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, newPrcCatgCd);
        EZDTBLAccessor.create(attbTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(attbTMsg.getReturnCode())) {
            rollbackTable();
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price General Contract Attribute", prcCatgCd) });
            commitTable();
            return false;
        }

        return true;
    }

    private boolean copyPrcEquipCntrAttrb(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd) {

        // PRC_EQUIP_CONTR_ATTRB
        PRC_EQUIP_CONTR_ATTRBTMsg attbTMsg = new PRC_EQUIP_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(attbTMsg.glblCmpyCd, this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(copyListRqstTmsg.copyAttrbTrxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, prcCatgCd);
            attbTMsg = (PRC_EQUIP_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(attbTMsg);
            if (attbTMsg == null) {
                rollbackTable();
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Equipment Contract Attribute", prcCatgCd) });
                commitTable();
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(attbTMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.addlShpgFeeInclFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.reloInclFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.notExcdContrPrcFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, newPrcCatgCd);
        EZDTBLAccessor.create(attbTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(attbTMsg.getReturnCode())) {
            rollbackTable();
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Equipment Contract Attribute", prcCatgCd) });
            commitTable();
            return false;
        }

        return true;
    }

    private boolean copyPrcSvcCntrAttrb(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd) {

        // PRC_SVC_CONTR_ATTRB
        PRC_SVC_CONTR_ATTRBTMsg attbTMsg = new PRC_SVC_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(attbTMsg.glblCmpyCd, this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(copyListRqstTmsg.copyAttrbTrxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, prcCatgCd);
            attbTMsg = (PRC_SVC_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(attbTMsg);
            if (attbTMsg == null) {
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Service Contract Attribute", prcCatgCd) });
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(attbTMsg.wavBookPrcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.wavAllFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.allowNewAggrContrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.allowNewFleetContrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.allowAddAggrContrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.allowAddFleetContrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(attbTMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, newPrcCatgCd);
        EZDTBLAccessor.create(attbTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(attbTMsg.getReturnCode())) {
            rollbackTable();
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Service Contract Attribute", prcCatgCd) });
            commitTable();
            return false;
        }

        return true;
    }

    private boolean copyPrcSvcTierAttrb(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String prcCatgCd, String newPrcCatgCd) {

        // PRC_SVC_TIER_CONTR_ATTRB
        PRC_SVC_TIER_CONTR_ATTRBTMsg attbTMsg = new PRC_SVC_TIER_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(attbTMsg.glblCmpyCd, this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(copyListRqstTmsg.copyAttrbTrxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, prcCatgCd);
            attbTMsg = (PRC_SVC_TIER_CONTR_ATTRBTMsg) EZDTBLAccessor.findByKey(attbTMsg);
            if (attbTMsg == null) {
                updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Service Tier Contract Attribute", prcCatgCd) });
                return false;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(attbTMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(attbTMsg.prcCatgCd, newPrcCatgCd);
        EZDTBLAccessor.create(attbTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(attbTMsg.getReturnCode())) {
            rollbackTable();
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0176E, new String[] {getMessageParam("Price Service Tier Contract Attribute", prcCatgCd) });
            commitTable();
            return false;
        }
        return true;
    }

    private boolean isNegative(BigDecimal amt) {
        if (amt.compareTo(BigDecimal.ZERO) < 0) {
            return true;
        }
        return false;
    }

    private BigDecimal calcPrice(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, BigDecimal amt) {

        BigDecimal rate = copyListRqstTmsg.copyPrcAmtRate.getValue();
        if (PRC_CALC_TP.MARK_UP_ALL.equals(copyListRqstTmsg.prcCalcTpCd.getValue())) {
            if (PRC_PCT_AMT_TP.PERCENT.equals(copyListRqstTmsg.prcPctAmtTpCd.getValue())) {
                BigDecimal amtValue = amt.multiply(rate);
                amt = amt.add(amtValue);
            } else if (PRC_PCT_AMT_TP.VALUE.equals(copyListRqstTmsg.prcPctAmtTpCd.getValue())) {
                amt = amt.add(rate);
            }

        } else if (PRC_CALC_TP.DISCOUNT.equals(copyListRqstTmsg.prcCalcTpCd.getValue())) {
            if (PRC_PCT_AMT_TP.PERCENT.equals(copyListRqstTmsg.prcPctAmtTpCd.getValue())) {
                BigDecimal amtValue = amt.multiply(rate);
                amt = amt.subtract(amtValue);
            } else if (PRC_PCT_AMT_TP.VALUE.equals(copyListRqstTmsg.prcPctAmtTpCd.getValue())) {
                amt = amt.subtract(rate);
            }
        }
        amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
        return amt;
    }

    private boolean isExistPrcCatgNm(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, this.glblCmpyCd);
        ssmParam.put(PRC_CATG_NM, copyListRqstTmsg.newPrcListNm.getValue());
        ssmParam.put(DELFLG_N, ZYPConstant.FLG_OFF_N);
        Map<String, Object> prcCatgNmMap = NWZC150001Common.autoCast(this.ssmBatchClient.queryObject("checkExistPrcCatgNm", ssmParam));
        if (prcCatgNmMap != null) {
            updateCopyResultStatus(copyListRqstTmsg, COPY_RSLT_TP.ERROR, NMAM0072E, new String[] {copyListRqstTmsg.newPrcListNm.getValue() });
            commitTable();
            return true;
        }

        return false;
    }

    private String getPeriodCheckTableName(String struTpCd) {
        String tableName = "";
        //2016/05/23 QC#7769 start------
        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_EQUIP;
        } else if (PRC_LIST_STRU_TP.SERVICE.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_SVC;
        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_SVC_TIER;
        } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_ADDL_CHRG;
        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_SPLY;
        } else if (PRC_LIST_STRU_TP.LEASE_RATES.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_LEASE_RATE;
        } else if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_LEASE_RTRN;
        } else if (PRC_LIST_STRU_TP.TRADE_INS.equals(struTpCd)) {
            tableName = PRC_LIST_TABLE_TI_VAL;
        }
        //2016/05/23 QC#7769 end------
        return tableName;
    }

    private void updateCopyResultStatus(COPY_PRC_LIST_RQSTTMsg copyListRqstTmsg, String status, String messageID, String[] param) {

        if (COPY_RSLT_TP.ERROR.equals(status)) {
            if (ZYPCommonFunc.hasValue(messageID)) {
                ZYPEZDItemValueSetter.setValue(copyListRqstTmsg.copyRsltCmntTxt, S21MessageFunc.clspGetMessage(messageID, param));
            }
            totalErrCount++;
            termCd = TERM_CD.WARNING_END;
        }
        if (ZYPCommonFunc.hasValue(messageID)) {
            S21InfoLogOutput.println(messageID, param);
        }
        ZYPEZDItemValueSetter.setValue(copyListRqstTmsg.copyRsltTpCd, status);
        S21FastTBLAccessor.update(copyListRqstTmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(copyListRqstTmsg.getReturnCode())) {
            termCd = TERM_CD.ABNORMAL_END;
            rollbackTable();
            throw new S21AbendException(NMAM0177E, new String[] {"COPY_PRC_LIST_RQST" });
        }
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

}
