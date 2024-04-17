/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB080001;

import static com.canon.cusa.s21.batch.NSA.NSAB080001.constant.NSAB080001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NSA.NSAB080001.constant.NSAB080001Constant.NSZM0392E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.FRT_CHRG_REPL_INFOTMsg;
import business.db.FRT_CHRG_REPL_DTLTMsg;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.db.SVC_INV_LINE_MTRTMsg;
import business.db.SVC_INV_LINE_XS_MTRTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Service Invoice Number Setting Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/30   Hitachi         K.Kojima        Create          QC#23685
 * 2018/06/29   Hitachi         K.Kojima        Update          QC#23685-1
 * 2018/07/19   Hitachi         K.Kojima        Update          QC#27292
 * 2018/10/19   Fujitsu         M.Yamada        Update          QC#28853
 * 2023/01/17   Hitachi         D.Yoshida       Update          QC#61468
 *</pre>
 */
public class NSAB080001 extends S21BatchMain {

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Normal Count */
    private int normalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** Global Company Code */
    private String glblCmpyCd;

    private final String[] condListForSvcInv = new String[] {"glblCmpyCd", "svcInvNum", "tempSvcInvNumFlg" };

    private final String[] updtListForSvcInv = new String[] {"svcInvNum", "tempSvcInvNumFlg" };

    private final String[] condList = new String[] {"glblCmpyCd", "svcInvNum" };

    private final String[] updtList = new String[] {"svcInvNum" };

    // START 2018/07/19 K.Kojima [QC#27292,ADD]
    private final String[] condListForCredit = new String[] {"glblCmpyCd", "crSvcInvNum" };

    private final String[] updtListForCredit = new String[] {"crSvcInvNum" };

    private final String[] condListForRebill = new String[] {"glblCmpyCd", "rebilSvcInvNum" };

    private final String[] updtListForRebill = new String[] {"rebilSvcInvNum" };

    // END 2018/07/19 K.Kojima [QC#27292,ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB080001().executeBatch(NSAB080001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        this.normalRecCnt = 0;
        this.errRecCnt = 0;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0392E, new String[] {"GLBL_CMPY_CD" });
        }
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getRegSvcInvKey();
            rs = ps.executeQuery();
            while (rs.next()) {
                if (!changeSvcInvNum(rs.getBigDecimal("SVC_INV_PK"), rs.getString("SVC_INV_NUM"), rs.getString("AUTO_SEQ_CD"))) {
                    rollback();
                    errRecCnt++;
                    continue;
                }
                commit();
                normalRecCnt++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(TERM_CD.NORMAL_END, normalRecCnt, errRecCnt, normalRecCnt + errRecCnt);
    }

    private PreparedStatement getRegSvcInvKey() throws SQLException {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", this.glblCmpyCd);
        prm.put("invTpCreditMemo", INV_TP.CREDIT_MEMO);
        prm.put("svcInvChrgTpBase", SVC_INV_CHRG_TP.BASE_CHARGE);
        prm.put("svcInvChrgTpMeter", SVC_INV_CHRG_TP.METER_CHARGE);
        return ssmLLClient.createPreparedStatement("getTargetSvcInv", prm, getExecParam());
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private boolean changeSvcInvNum(BigDecimal svcInvPk, String svcInvNum, String autoSeqCd) {
        // Set New SVC_INV_NUM
        String newSvcInvNum = ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, autoSeqCd);

        // SVC_INV
        if (!updateSvcInv(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_INV_LINE
        if (!updateSvcInvLine(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_INV_LINE_ALLOC
        if (!updateSvcInvLineAlloc(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_INV_LINE_MTR
        if (!updateSvcInvLineMtr(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_INV_LINE_XS_MTR
        if (!updateSvcInvLineXsMtr(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_CR_REBIL_DTL(Credit)
        if (!updateSvcCrRebilDtlForCredit(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_CR_REBIL_DTL(Rebill)
        if (!updateSvcCrRebilDtlForRebill(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // DS_CONTR_BLLG_SCHD
        if (!updateDsContrBllgSchd(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // SVC_PHYS_MTR_READ    QC#28853
        if (!updateSvcPhysMtrRead(svcInvNum, newSvcInvNum)) {
            return false;
        }

        // START 2024/01/17 [QC#61468, ADD]
        if (!updateFrtChrgReplInfo(svcInvNum, newSvcInvNum)) {
            return false;
        }
        
        if (!updateFrtChrgReplDtl(svcInvNum, newSvcInvNum)) {
            return false;
        }
        
        // END   2024/01/17 [QC#61468, ADD]
        return true;
    }

    private boolean updateSvcInv(String svcInvNum, String newSvcInvNum) {
        SVC_INVTMsg condMsg = new SVC_INVTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);
        ZYPEZDItemValueSetter.setValue(condMsg.tempSvcInvNumFlg, ZYPConstant.FLG_ON_Y);

        SVC_INVTMsg updtMsg = new SVC_INVTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);
        ZYPEZDItemValueSetter.setValue(updtMsg.tempSvcInvNumFlg, ZYPConstant.FLG_OFF_N);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condListForSvcInv, updtMsg, this.updtListForSvcInv);
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateSvcInvLine(String svcInvNum, String newSvcInvNum) {
        SVC_INV_LINETMsg condMsg = new SVC_INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        SVC_INV_LINETMsg updtMsg = new SVC_INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateSvcInvLineAlloc(String svcInvNum, String newSvcInvNum) {
        SVC_INV_LINE_ALLOCTMsg condMsg = new SVC_INV_LINE_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        SVC_INV_LINE_ALLOCTMsg updtMsg = new SVC_INV_LINE_ALLOCTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateSvcInvLineMtr(String svcInvNum, String newSvcInvNum) {
        SVC_INV_LINE_MTRTMsg condMsg = new SVC_INV_LINE_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        SVC_INV_LINE_MTRTMsg updtMsg = new SVC_INV_LINE_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateSvcInvLineXsMtr(String svcInvNum, String newSvcInvNum) {
        SVC_INV_LINE_XS_MTRTMsg condMsg = new SVC_INV_LINE_XS_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        SVC_INV_LINE_XS_MTRTMsg updtMsg = new SVC_INV_LINE_XS_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateSvcCrRebilDtlForCredit(String svcInvNum, String newSvcInvNum) {
        SVC_CR_REBIL_DTLTMsg condMsg = new SVC_CR_REBIL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.crSvcInvNum, svcInvNum);

        SVC_CR_REBIL_DTLTMsg updtMsg = new SVC_CR_REBIL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.crSvcInvNum, newSvcInvNum);

        // START 2018/07/19 K.Kojima [QC#27292,MOD]
        // int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condListForCredit, updtMsg, this.updtListForCredit);
        // END 2018/07/19 K.Kojima [QC#27292,MOD]
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateSvcCrRebilDtlForRebill(String svcInvNum, String newSvcInvNum) {
        SVC_CR_REBIL_DTLTMsg condMsg = new SVC_CR_REBIL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.rebilSvcInvNum, svcInvNum);

        SVC_CR_REBIL_DTLTMsg updtMsg = new SVC_CR_REBIL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.rebilSvcInvNum, newSvcInvNum);

        // START 2018/07/19 K.Kojima [QC#27292,MOD]
        // int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condListForRebill, updtMsg, this.updtListForRebill);
        // END 2018/07/19 K.Kojima [QC#27292,MOD]
        if (result == -1) {
            return false;
        }
        return true;
    }

    private boolean updateDsContrBllgSchd(String svcInvNum, String newSvcInvNum) {
        DS_CONTR_BLLG_SCHDTMsg condMsg = new DS_CONTR_BLLG_SCHDTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        DS_CONTR_BLLG_SCHDTMsg updtMsg = new DS_CONTR_BLLG_SCHDTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }

    // QC#28853
    private boolean updateSvcPhysMtrRead(String svcInvNum, String newSvcInvNum) {
        SVC_PHYS_MTR_READTMsg condMsg = new SVC_PHYS_MTR_READTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        SVC_PHYS_MTR_READTMsg updtMsg = new SVC_PHYS_MTR_READTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }

    // START 2024/01/17 [QC#61468, ADD]
    private boolean updateFrtChrgReplInfo(String svcInvNum, String newSvcInvNum) {
        FRT_CHRG_REPL_INFOTMsg condMsg = new FRT_CHRG_REPL_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        FRT_CHRG_REPL_INFOTMsg updtMsg = new FRT_CHRG_REPL_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }
    
    private boolean updateFrtChrgReplDtl(String svcInvNum, String newSvcInvNum) {
        FRT_CHRG_REPL_DTLTMsg condMsg = new FRT_CHRG_REPL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.svcInvNum, svcInvNum);

        FRT_CHRG_REPL_DTLTMsg updtMsg = new FRT_CHRG_REPL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(updtMsg.svcInvNum, newSvcInvNum);

        int result = S21FastTBLAccessor.updateByPartialValue(condMsg, this.condList, updtMsg, this.updtList);
        if (result == -1) {
            return false;
        }
        return true;
    }    
    // END   2024/01/17 [QC#61468, ADD]

}
