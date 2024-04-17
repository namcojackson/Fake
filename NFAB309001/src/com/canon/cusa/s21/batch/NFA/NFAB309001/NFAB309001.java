/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB309001;

import static com.canon.cusa.s21.batch.NFA.NFAB309001.NFAB309001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AJE_INV_LINE_ALLOCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Service Allocation Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/23   Hitachi         S.Nakatani      Create          QC#61387
 * </pre>
 */
public class NFAB309001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Process Date */
    private String slsDt = "";

    /** Total Count */
    private int totalCount;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    @Override
    protected void initRoutine() {
        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFAM0052E, new String[] {"Global Company Code" });
        }

        // "Sales Date"
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFAM0052E, new String[] {"Sales Date" });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.totalCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getAjeInvMdlGrpAlloc", execParam);
            rsSet = stmt.executeQuery();
            String mode = "";

            while (rsSet.next()) {
                totalCount++;

                mode = rsSet.getString("XX_MODE");
                if (mode.equals(INSERT_MODE)) {
                    insertAjeInvLineAlloc(rsSet);
                } else if (mode.equals(UPDATE_MODE)) {
                    updateAjeInvLineAlloc(rsSet);
                } else if (mode.equals(DELETE_MODE)) {
                    deleteAjeInvLineAlloc(rsSet);
                }

                commit();
                normalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setRecordCount(this.normalCount, this.errorCount, this.totalCount);
        setTermState(termCd);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFAB309001().executeBatch(NFAB309001.class.getSimpleName());
    }

    private void insertAjeInvLineAlloc(ResultSet rsSet) throws SQLException {
        AJE_INV_LINE_ALLOCTMsg inMsg = new AJE_INV_LINE_ALLOCTMsg();

        // from AUTO_SQ_TEN_DIGIT
        String uniqueIdentifier = ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, "AJE_INV_LINE_ALLOC_CD");
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.ezCancelFlag, "0");
        setValue(inMsg.ajeInvLineAllocCd, uniqueIdentifier);
        String nmTxt = createNmTxt(rsSet.getString("SVC_ALLOC_TP_NM"), rsSet.getString("T_MDL_NM"));
        setValue(inMsg.ajeInvLineAllocNm, nmTxt);
        setValue(inMsg.ajeInvLineAllocDescTxt, nmTxt);
        setValue(inMsg.svcAllocTpCd, rsSet.getString("SVC_ALLOC_TP_CD"));
        setValue(inMsg.mdlId, rsSet.getBigDecimal("MDL_ID"));
        String chrgTp = rsSet.getString("SVC_INV_CHRG_TP_CD");
        setValue(inMsg.svcInvChrgTpCd, chrgTp);
        setValue(inMsg.equipAllocPct, rsSet.getBigDecimal("EQUIP_ALLOC_PCT"));
        setValue(inMsg.svcAllocPct, rsSet.getBigDecimal("SVC_ALLOC_PCT"));
        setValue(inMsg.splyAllocPct, rsSet.getBigDecimal("SPLY_ALLOC_PCT"));
        String acctgRuleCd = mapAcctgRuleCd(chrgTp);
        setValue(inMsg.equipAcctgRuleCd, acctgRuleCd);
        setValue(inMsg.svcAcctgRuleCd, acctgRuleCd);
        setValue(inMsg.splyAcctgRuleCd, acctgRuleCd);
        setValue(inMsg.equipTrxCd, TRX_CD);
        setValue(inMsg.equipTrxRsnCd, mapTrxRsnCd(chrgTp, EQUIP));
        setValue(inMsg.svcTrxCd, TRX_CD);
        setValue(inMsg.svcTrxRsnCd, mapTrxRsnCd(chrgTp, SVC));
        setValue(inMsg.splyTrxCd, TRX_CD);
        setValue(inMsg.splyTrxRsnCd, mapTrxRsnCd(chrgTp, SPLY));
        setValue(inMsg.addlChrgFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NFAM0208E, new String[] {"AJE_INV_LINE_ALLOC", rsSet.getString("MDL_ID") });
        }
    }

    private void updateAjeInvLineAlloc(ResultSet rsSet) throws SQLException {
        AJE_INV_LINE_ALLOCTMsg reqMsg = new AJE_INV_LINE_ALLOCTMsg();
        setValue(reqMsg.glblCmpyCd, glblCmpyCd);
        String ajeInvLineAllocCd = getAjeInvLineAllocCd(rsSet.getString("SVC_ALLOC_TP_CD"), rsSet.getString("MDL_ID"), rsSet.getString("SVC_INV_CHRG_TP_CD"));
        setValue(reqMsg.ajeInvLineAllocCd, ajeInvLineAllocCd);
        setValue(reqMsg.svcAllocTpCd, rsSet.getString("SVC_ALLOC_TP_CD"));
        setValue(reqMsg.mdlId, rsSet.getBigDecimal("MDL_ID"));
        String chrgTp = rsSet.getString("SVC_INV_CHRG_TP_CD");
        setValue(reqMsg.svcInvChrgTpCd, chrgTp);

        AJE_INV_LINE_ALLOCTMsg inMsg = (AJE_INV_LINE_ALLOCTMsg) EZDTBLAccessor.findByKey(reqMsg);
        setValue(inMsg.equipAllocPct, rsSet.getBigDecimal("EQUIP_ALLOC_PCT"));
        setValue(inMsg.svcAllocPct, rsSet.getBigDecimal("SVC_ALLOC_PCT"));
        setValue(inMsg.splyAllocPct, rsSet.getBigDecimal("SPLY_ALLOC_PCT"));
        setValue(inMsg.equipTrxCd, TRX_CD);
        setValue(inMsg.equipTrxRsnCd, mapTrxRsnCd(chrgTp, EQUIP));
        setValue(inMsg.svcTrxCd, TRX_CD);
        setValue(inMsg.svcTrxRsnCd, mapTrxRsnCd(chrgTp, SVC));
        setValue(inMsg.splyTrxCd, TRX_CD);
        setValue(inMsg.splyTrxRsnCd, mapTrxRsnCd(chrgTp, SPLY));
        setValue(inMsg.addlChrgFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.update(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NFAM0208E, new String[] {"AJE_INV_LINE_ALLOC", rsSet.getString("MDL_ID") });
        }
    }

    private void deleteAjeInvLineAlloc(ResultSet rsSet) throws SQLException {
        AJE_INV_LINE_ALLOCTMsg delete = new AJE_INV_LINE_ALLOCTMsg();
        setValue(delete.glblCmpyCd, glblCmpyCd);
        setValue(delete.svcAllocTpCd, rsSet.getString("SVC_ALLOC_TP_CD"));
        setValue(delete.mdlId, rsSet.getBigDecimal("MDL_ID"));
        setValue(delete.svcInvChrgTpCd, rsSet.getString("SVC_INV_CHRG_TP_CD"));
        EZDFastTBLAccessor.logicalRemoveByPartialValue(delete, new String[] {"glblCmpyCd", "svcAllocTpCd", "mdlId", "svcInvChrgTpCd" });
        String ezRet = delete.getReturnCode();

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ezRet)) {
            throw new S21AbendException(NFAM0208E, new String[] {"AJE_INV_LINE_ALLOC", rsSet.getString("MDL_ID") });
        }
    }

    private String getAjeInvLineAllocCd(String svcAllocTpCd, String mdlId, String svcInvChrgTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcAllocTpCd", svcAllocTpCd);
        ssmParam.put("mdlId", mdlId);
        ssmParam.put("svcInvChrgTpCd", svcInvChrgTpCd);
        String ajeInvLineAllocCd = (String) this.ssmBatchClient.queryObject("getAjeInvLineAllocCd", ssmParam);
        if ((ajeInvLineAllocCd != null) && (!ajeInvLineAllocCd.isEmpty())) {
            return ajeInvLineAllocCd;
        } else {
            return null;
        }
    }

    private String createNmTxt(String svcAllocTpNm, String mdlNm) {
        mdlNm = "\"" + mdlNm + "\"";
        String nmTxt = svcAllocTpNm + "-" + mdlNm;
        if (nmTxt.length() <= NM_TXT_MAX_LENGTH) {
            return nmTxt;
        } else {
            return nmTxt.substring(0, NM_TXT_MAX_LENGTH);
        }
    }

    private String mapAcctgRuleCd(String chrgTp) {
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(chrgTp)) {
            return DFRD_ACCTG_RULE.DEFERRED;
        }
        return DFRD_ACCTG_RULE.IMMEDIATE;
    }

    private String mapTrxRsnCd(String chrgTp, String trgt) {
        String trxRsnCd = "";
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(chrgTp)) {
            if (EQUIP.equals(trgt)) {
                trxRsnCd = TRX_RSN.SVC_EQUIP_REV_BASE;
            } else if (SVC.equals(trgt)) {
                trxRsnCd = TRX_RSN.SVC_SVC_REV_BASE;
            } else if (SPLY.equals(trgt)) {
                trxRsnCd = TRX_RSN.SVC_SUP_REV_BASE;
            }
        }
        if (SVC_INV_CHRG_TP.METER_CHARGE.equals(chrgTp)) {
            if (EQUIP.equals(trgt)) {
                trxRsnCd = TRX_RSN.SVC_EQUIP_REV_BASE;
            } else if (SVC.equals(trgt)) {
                trxRsnCd = TRX_RSN.SVC_SVC_REV_USG;
            } else if (SPLY.equals(trgt)) {
                trxRsnCd = TRX_RSN.SVC_SUP_REV_USG;
            }
        }
        return trxRsnCd;
    }
}
