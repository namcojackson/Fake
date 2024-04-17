/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB265004;

import static com.canon.cusa.s21.batch.NMA.NMAB265004.Constant.NMAB265004Constant.ZZZM9026E;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import com.canon.cusa.s21.batch.NMA.NMAB265004.Constant.NMAB265004Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         K.Minamide      Create          N/A
 * 2015/01/21   Fujitsu         K.Koitabashi    Update          QC#3467, 3470
 * 2016/03/17   Fujitsu         H.Ito           Update          QC#4443
 * 2016/03/24   Fujitsu         N.Sugiura       Update          QC#5913
 * 2016/06/14   Fujitsu         C.Yokoi         Update          QC#9865
 * 2016/07/06   Fujitsu         R.Nakamura      Update          QC#5909
 * 2016/07/12   Fujitsu         N.Sugiura       Update          QC#8948
 * 2016/08/08   Fujitsu         C.Yokoi         Update          QC#11902
 * 2016/08/16   SRAA            Y.Chen          Update          QC#12831
 * 2016/08/24   Fujitsu         C.Yokoi         Update          QC#12063, 13277
 * 2016/10/03   Hitachi         Y.Takeno        Update          QC#14870
 * 2016/10/05   Hitachi         Y.Takeno        Update          QC#14870-1
 * 2016/10/26   Fujitsu         C.Yokoi         Update          QC#15360
 * 2016/11/04   Fujitsu         N.Aoyama        Update          QC#15537
 * 2016/11/08   Fujitsu         T.Yoshida       Update          QC#15767-1
 * 2016/11/17   Fujitsu         T.Yoshida       Update          QC#15767-6
 * 2016/11/21   Fujitsu         T.Yoshida       Update          QC#15767-7
 * 2016/11/22   Fujitsu         T.Yoshida       Update          QC#15767-8
 * 2017/04/19   Fujitsu         k.Fujita        Update          RS#8275
 *</pre>
 */
public class NMAB265004 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Commit Transaction Count */
    private int commitTransactionCount = 0;

    /** Direct Sales Account Type */
    private String directSalesAccountType = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** sales date time */
    private String slsDt = null;

    /** SSM Client Custom */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB265004().executeBatch(NMAB265004.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitTransactionCount = getCommitCount();
        if (this.commitTransactionCount < 0) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Commit Transaction Count" });
        }

        if (getUserVariable3() != null) {
            this.directSalesAccountType = super.getUserVariable3();
        } else {
            throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
        }

        // Initialization of SSM Custom
        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());

        this.slsDt = ZYPDateUtil.getSalesDate();
    }

    @Override
    protected void mainRoutine() {

        this.setCancelFlagManual(true);
        this.setCancelFlagManual(false);
        
        this.makeTempSalesRepRoleAsg();
        this.makeTempSpecialistRoleAsg();
        this.insertAcctTrtyResrcAsg();

        deletePhysicalManual();
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    private void makeTempSalesRepRoleAsg() {

        // ---Insert
        Map<String, Object> ssmGetLocationParam = makeTrtyRAsgSsmParam();
        ssmGetLocationParam.put("salseRepFlag", ZYPConstant.FLG_ON_Y);

        // ------1.Rank
        ssmGetLocationParam.put("nonSlsRepFlgs", ZYPConstant.FLG_OFF_N); // is 'non' N
        ssmGetLocationParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        ssmGetLocationParam.put("orgRankNumMax", NMAB265004Constant.ORG_RANK_NUM_MAX);
        // ------2.LineBizTpCd
        ssmGetLocationParam.put("rnkEligFlg", ZYPConstant.FLG_ON_Y);
        // ------4.Location
        ssmGetLocationParam.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("activeFlag", ZYPConstant.FLG_ON_Y);
        // ------5.Resource
        ssmGetLocationParam.put("salesRepFlg", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("slsDt", this.slsDt);
        ssmGetLocationParam.put("maxDt", "99991231");
        ssmGetLocationParam.put("bizArea_Sales", BIZ_AREA_ORG.SALES);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // ------6.isManual
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);
        ssmGetLocationParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        int insertCount = this.ssmBatchClientCustom.insert("insertTrtyRoleAsgList", ssmGetLocationParam);
        this.totalCount += insertCount;
        this.totalNmlCount += insertCount;
        commit();
    }

    private void makeTempSpecialistRoleAsg() {

        // ---Insert
        Map<String, Object> ssmGetLocationParam = makeTrtyRAsgSsmParam();
        ssmGetLocationParam.put("specialistFlag", ZYPConstant.FLG_ON_Y);

        // ------1.Rank
        ssmGetLocationParam.put("nonSlsRepFlgs", ZYPConstant.FLG_ON_Y); // is 'non' Y
        ssmGetLocationParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        // ------2.LineBizTpCd
        ssmGetLocationParam.put("rnkEligFlg", ZYPConstant.FLG_OFF_N);
        // ------4.Location
        ssmGetLocationParam.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("activeFlag", ZYPConstant.FLG_ON_Y);
        // ------5.Resource
        ssmGetLocationParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("slsDt", this.slsDt);
        ssmGetLocationParam.put("maxDt", "99991231");
        ssmGetLocationParam.put("bizArea_Sales", BIZ_AREA_ORG.SALES);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // ------6.isManual
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);
        ssmGetLocationParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        int insertCount = this.ssmBatchClientCustom.insert("insertTrtyRoleAsgList", ssmGetLocationParam);
        this.totalCount += insertCount;
        this.totalNmlCount += insertCount;
        commit();
    }

    private void insertAcctTrtyResrcAsg() {

        Map<String, Object> ssmGetLocationParam = makeTrtyResrcAsgBaseSsmParam();
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);

        this.ssmBatchClientCustom.insert("insertAcctTrtyResrcAsg", ssmGetLocationParam);
        commit();
    }

    private void setCancelFlagManual(boolean isSalesRep) {

        // Set Cancel Flag Manual every dsAcctTp salesRep/Specialist Customer/Prospect
        Map<String, Object> ssmSetCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();
        ssmSetCancelFlagManualRoleAsignListParam.put("dsAcctTp", this.directSalesAccountType);
        if (isSalesRep) {
              ssmSetCancelFlagManualRoleAsignListParam.put("salesRepFlg", ZYPConstant.FLG_ON_Y);
        } else {
              ssmSetCancelFlagManualRoleAsignListParam.put("salesRepFlg", ZYPConstant.FLG_OFF_N);
        }
        int updateCount = this.ssmBatchClientCustom.update("setCancelFlagManualRoleAsg", ssmSetCancelFlagManualRoleAsignListParam);
    }

    private void deletePhysicalManual() {
        // Delete Manual
        Map<String, Object> ssmDeleteCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();
        this.ssmBatchClientCustom.delete("deleteCancelFlagManualRoleAsg", ssmDeleteCancelFlagManualRoleAsignListParam);
        commit();
    }

    private Map<String, Object> makeTrtyRAsgSsmParam() {
        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        Map<String, Object> ssmGetLocationParam = new HashMap<String, Object>();

        if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "PROS_TRTY_ROLE_ASG");
        } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "ACCT_TRTY_ROLE_ASG");
        }

        ssmGetLocationParam.put("glblCmpyCd", glblCmpyCd);
        ssmGetLocationParam.put("slsDt", slsDt);
        ssmGetLocationParam.put("ezintime", dateTime);
        ssmGetLocationParam.put("ezintimezone", upTimeZone);
        ssmGetLocationParam.put("ezincompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezinuserid", userId);
        ssmGetLocationParam.put("ezinaplid", upPgId);

        ssmGetLocationParam.put("ezuptime", dateTime);
        ssmGetLocationParam.put("ezuptimezone", upTimeZone);
        ssmGetLocationParam.put("ezupcompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezupuserid", userId);
        ssmGetLocationParam.put("ezupaplid", upPgId);

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("customerFlag", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("prospectFlag", ZYPConstant.FLG_ON_Y);
        }

        return ssmGetLocationParam;
    }

    private Map<String, Object> makeTrtyResrcAsgBaseSsmParam() {
        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        Map<String, Object> ssmGetLocationParam = new HashMap<String, Object>();

        if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "PROS_TRTY_RESRC_ASG");
        } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "ACCT_TRTY_RESRC_ASG");
        }

        ssmGetLocationParam.put("glblCmpyCd", glblCmpyCd);
        ssmGetLocationParam.put("slsDt", slsDt);
        ssmGetLocationParam.put("ezintime", dateTime);
        ssmGetLocationParam.put("ezintimezone", upTimeZone);
        ssmGetLocationParam.put("ezincompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezinuserid", userId);
        ssmGetLocationParam.put("ezinaplid", upPgId);

        ssmGetLocationParam.put("ezuptime", dateTime);
        ssmGetLocationParam.put("ezuptimezone", upTimeZone);
        ssmGetLocationParam.put("ezupcompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezupuserid", userId);
        ssmGetLocationParam.put("ezupaplid", upPgId);

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("customerFlag", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("prospectFlag", ZYPConstant.FLG_ON_Y);
        }

        return ssmGetLocationParam;
    }
}
