package com.canon.cusa.s21.batch.NFB.NFBB115001;

import static java.util.Arrays.asList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CM_MAINT_INVTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 *
 * <pre>
 * Update PAID status for AP Invoice Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */

public class NFBB115001 extends S21BatchMain implements NFBB115001Constant {
    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Commit Count (CM_MAINT_INV) */
    private int cmMaintInvCommitCount;
    /** Total Commit Count */
    private int totalCommitCount;

    /** For handling CM_MAINT_INV Bulk TBL Accessor */
    private CM_MAINT_INVTMsg[] cmMaintInvTMsgs;

    /** CM_MAINT_INV Bulk Update Count */
    private int iCntCmMaintInv;
    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB115001().executeBatch(NFBB115001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Get CM_PROC_DT */
        this.cmProcDt = NFBCommonBusiness.getCmProcDt(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cmProcDt)) {
            throw new S21AbendException(NFBM0028E);
        }
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize Bulk TBL Accessor */
        initBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
    }

    @Override
    protected void mainRoutine() {
        // Get NFBB115001_AP_MAINT_INV_STS_CD from VAR_CHAR_CONST table.
        List<String> listApMaintInvStsCd = getApMaintInvStsCdCondValue();
        if (listApMaintInvStsCd == null) {
            throw new S21AbendException(NFBM0028E.toString());
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        queryParam.put("listApMaintInvStsCd", listApMaintInvStsCd);
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD, queryParam, new SelectRecordHandler());
        if (bRet == Boolean.TRUE) {
            if (iCntCmMaintInv > 0) {
                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmMaintInvTMsgs, iCntCmMaintInv);
                if (iRet > 0) {
                    cmMaintInvCommitCount = cmMaintInvCommitCount + iRet;
                } else {
                    cmMaintInvCommitCount = 0;
                    throw new S21AbendException(NFBM0028E);
                }
            }
            commit();
        } else {
            rollback();
        }
    }
    @Override
    protected void termRoutine() {
        this.totalCommitCount = cmMaintInvCommitCount;
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, totalCommitCount, 0, 0);
    }
    /**
     * Private class: SelectRecordHandler
     */
    private class SelectRecordHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                // Update CM_MAINT_INV record
                CM_MAINT_INVTMsg cmMaintInvTMsg = new CM_MAINT_INVTMsg();
                ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apInvNum, rs.getString(AP_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apVndCd, rs.getString(AP_VND_CD));
                S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvTMsg);
                cmMaintInvTMsg.apMaintInvStsCd.setValue(rs.getString(ACCT_INV_STS_CD));
                cmMaintInvTMsgs[iCntCmMaintInv] = cmMaintInvTMsg;
                iCntCmMaintInv++;
                if (iCntCmMaintInv == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.update(cmMaintInvTMsgs);
                    if (iRet > 0) {
                        cmMaintInvTMsgs = new CM_MAINT_INVTMsg[INT_BULK_COM_LIM];
                        cmMaintInvCommitCount = cmMaintInvCommitCount + iRet;
                        iCntCmMaintInv = 0;
                    } else {
                        cmMaintInvCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }
                }
            }
            return Boolean.TRUE;
        }
    }
    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.cmMaintInvCommitCount = 0;
        this.totalCommitCount = 0;
    }
    /**
     * Initialize Bulk TBL Accessor.
     */
    private void initBulkTBLAccessor() {
        this.cmMaintInvTMsgs = new CM_MAINT_INVTMsg[INT_BULK_COM_LIM];
    }
    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntCmMaintInv = 0;
    }

    /**
     * Get NFBB115001_AP_MAINT_INV_STS_CD from VAR_CHAR_CONST table.
     * @return List<String>
     */
    private List<String> getApMaintInvStsCdCondValue() {
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBB115001_AP_MAINT_INV_STS_CD, this.glblCmpyCd);
        List<String> listCrDrRsnCd = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(varCharConstVal)) {
            listCrDrRsnCd =asList(varCharConstVal.split(","));
        } else {
            listCrDrRsnCd = null;
        }
        return listCrDrRsnCd;
    }

}
