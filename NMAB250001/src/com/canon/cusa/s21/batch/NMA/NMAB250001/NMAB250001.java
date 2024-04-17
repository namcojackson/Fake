/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB250001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ORG_RESRC_RELNTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NMAB2500 Adjustment Rep Assign Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/18   Fujitsu         M.Ohno          Create          QC#54726
 */
public class NMAB250001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** process date time */
    private String procDt = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NMAB250001Constant.ZZZM9025E, new String[] {NMAB250001Constant.MSG_GLBL_CMPY_CD });
        }

        // value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(NMAB250001Constant.ZZZM9026E, new String[] {NMAB250001Constant.MSG_GLBL_CMPY_CD });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        List<Map<String, Object>> trtyResrcList = getTargetTrtyResrc();
        String beforeOrgCd = "";

        for (Map<String, Object> trtyResrc : trtyResrcList) {
            if (beforeOrgCd.equals((String) trtyResrc.get("TRTY_ORG_CD"))) {
                continue;
            }

            List<Map<String, Object>> adjustmentRepList = getAdjustmentRep((String) trtyResrc.get("ORG_ORG_CD"));

            if (adjustmentRepList == null || adjustmentRepList.size() != 1) {
                continue;
            }

            beforeOrgCd = (String) trtyResrc.get("TRTY_ORG_CD");

            Map<String, Object> adjustmentRep = adjustmentRepList.get(0);
            DS_ORG_RESRC_RELNTMsg insertTMsg = new DS_ORG_RESRC_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, (String) trtyResrc.get("TRTY_ORG_CD"));
            ZYPEZDItemValueSetter.setValue(insertTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, (String) adjustmentRep.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(insertTMsg.orgFuncRoleTpCd, (String) adjustmentRep.get("ORG_FUNC_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(insertTMsg.effFromDt, ZYPDateUtil.addDays(this.procDt, 1));
            ZYPEZDItemValueSetter.setValue(insertTMsg.gnrnTpCd, GNRN_TP.CURRENT);
            ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, ZYPConstant.FLG_OFF_N);
            S21FastTBLAccessor.insert(insertTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                rollback();
                this.totalErrCount++;
                throw new S21AbendException(NMAB250001Constant.MMAM0003E, new String[] {insertTMsg.getTableName() });
            }

            this.totalNmlCount++;
            commit();
        }

    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB250001().executeBatch(NMAB250001.class.getSimpleName());
    }

    private List<Map<String, Object>> getTargetTrtyResrc() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procDt", this.procDt);
        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpTerminate", GNRN_TP.TERMINATED);
        ssmParam.put("gnrnTpPast", GNRN_TP.PAST);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTargetTrtyResrc", ssmParam);
    }

    private List<Map<String, Object>> getAdjustmentRep(String orgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("orgCd", orgCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("adjustmentText", NMAB250001Constant.ADJUSTMENT_TXT);
        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAdjustmentRep", ssmParam);
    }
}
