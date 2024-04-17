/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB035001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.parts.NSZC078001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC078001.NSZC078001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Supply Abuse Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Hitachi         T.Harada        Create
 * 02/16/2016   Hitachi         A.Kohinata      Update          QC3373
 * 03/18/2016   Hitachi         A.Kohinata      Update          QC#5707
 * 06/30/2016   Hitachi         Y.Tsuchimoto    Update          QC#10692
 * 10/19/2016   Hitachi         A.Kohinata      Update          QC#15344
 * 11/02/2016   Hitachi         T.Mizuki        Update          QC#15122
 * 06/19/2017   Hitachi         Y.Osawa         Update          QC#19256
 * 12/19/2017   Hitachi         U.Kim           Update          QC#21527
 * 02/01/2018   Hitachi         U.Kim           Update          QC#23686
 * 2018/09/07   Hitachi         K.Kojima        Update          QC#28130
 * 2018/10/31   Fujitsu         M.Yamada        Update          QC#28954
 * </pre>
 */
public class NSAB035001 extends S21BatchMain {
    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** salesDate Date */
    private String salesDate = null;

    /** procDate Date */
    private String divideNum = null;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    // START 2018/02/01 U.Kim [QC#23686, ADD]
    /** User Variable1 */
    private String usrVar2;

    /** Multi Count */
    private BigDecimal multiCnt;
    // END 2018/02/01 U.Kim [QC#23686, ADD]

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Execution Parameter */
    private S21SsmExecutionParameter ssmExecParam = null;

    /** Message : A value is not set in the required parameter field. Field Name: [@] */
    public static final String NSZM0589E = "NSZM0589E";

    // START 2018/02/01 U.Kim [QC#23686, ADD]
    /**  Message : @ cannot be obtained. */
    private static final String NSZM0392E = "NSZM0392E";
    // END 2018/02/01 U.Kim [QC#23686, ADD]

    /** Message : Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    // START 2016/02/16 A.Kohinata [QC3373, DEL]
//    /** Message : Error Message Text: Process Date */
//    public static final String MSG_TXT_PROC_DATE = "Process Date";
    // END 2016/02/16 A.Kohinata [QC3373, DEL]

    // START 2016/02/16 A.Kohinata [QC3373, ADD]
    /** Message : Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** PROGRAM ID */
    public static final String PROGRAM_ID = "NSAB035001";
    // END 2016/02/16 A.Kohinata [QC3373, ADD]

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** THRU_DT_LIMIT */
    public static final String THRU_DT_LIMIT = "29991231";

    // mod start 2016/11/02 CSA QC#15122
    /** DTL_CNT_LIMIT:2000 */
    public static final int DTL_CNT_LIMIT = 2000;
    // mod end 2016/11/02 CSA QC#15122

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB035001().executeBatch(NSAB035001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }
        // START 2016/02/16 A.Kohinata [QC3373, MOD]
//        this.procDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
//            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_PROC_DATE });
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_SALES_DATE });
        }
        // END 2016/02/16 A.Kohinata [QC3373, MOD]
        this.divideNum = getUserVariable1();

        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(this.commitNumber);
        ssmExecParam.setMaxRows(0);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // START 2018/02/01 U.Kim [QC#23686, ADD]
        this.usrVar2 = getUserVariable2();
        if (!hasValue(usrVar2)) {
            throw new S21AbendException(NSZM0392E, new String[] {"User Variable2"});
        }

        this.multiCnt = ZYPCodeDataUtil.getNumConstValue("NSAB0350MULTI_CONTR_CALC_CNT", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.multiCnt)) {
            this.multiCnt = BigDecimal.ONE;
        }
        // EnD 2018/02/01 U.Kim [QC#23686, ADD]

    }

    @Override
    protected void mainRoutine() {
        createSupplyAbuse();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            // START 2016/06/30 [QC#10692, MOD]
            this.termCd = TERM_CD.WARNING_END;
            // END   2016/06/30 [QC#10692, MOD]
        }else{
            this.termCd = TERM_CD.NORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private void createSupplyAbuse() {
        S21InfoLogOutput.println("createSupplyAbuse Method Start");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Map<String, Object> param = setSupplyListParam();
            ps = this.ssmLLClient.createPreparedStatement("getSupplyList", param, ssmExecParam);
            rs = ps.executeQuery();

            NSZC078001PMsg pmsg = new NSZC078001PMsg();
            int dtlCnt = 0;
            BigDecimal dsContrPkBef = null;
            BigDecimal dsContrPk = null;
            BigDecimal svcMachMstrPk = null;

            while (rs.next()) {

                dsContrPk = rs.getBigDecimal("DS_CONTR_PK");
                svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");

                // mod start 2016/11/02 CSA QC#15122
                // START 2018/09/07 K.Kojima [QC#28130,MOD]
                // if (dsContrPkBef != null && !dsContrPk.equals(dsContrPkBef) || dtlCnt == DTL_CNT_LIMIT) {
                if (dsContrPkBef != null && dsContrPk.compareTo(dsContrPkBef) != 0 || dtlCnt == DTL_CNT_LIMIT) {
                // END 2018/09/07 K.Kojima [QC#28130,MOD]
                // mod end 2016/11/02 CSA QC#15122
                    callSupplyAbuseApi(pmsg);
                    pmsg.clear();
                    dtlCnt = 0;
                }

                // START 2018/09/07 K.Kojima [QC#28130,ADD]
                dsContrPkBef = dsContrPk;
                // END 2018/09/07 K.Kojima [QC#28130,ADD]

                setValue(pmsg.svcMachMstrPkList.no(dtlCnt).svcMachMstrPk, svcMachMstrPk);
                setValue(pmsg.svcMachMstrPkList.no(dtlCnt).dsContrPk, dsContrPk); // QC#28954
                pmsg.svcMachMstrPkList.setValidCount(dtlCnt + 1);
                dtlCnt++;
            }
            if (pmsg.svcMachMstrPkList.getValidCount() > 0) {
                callSupplyAbuseApi(pmsg);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        S21InfoLogOutput.println("createSupplyAbuse Method End");
    }

    private Map<String, Object> setSupplyListParam() {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(this.divideNum)) {
            param.put("dsContrNum", "%" + this.divideNum );
        }
        param.put("slsDt", this.salesDate);
        param.put("thruDtLimit", THRU_DT_LIMIT);
        param.put("featSply", SVC_COV_FEAT.SPLY_INCL);
        param.put("featLaser", SVC_COV_FEAT.LASER_PLUS_CONTR);
        // add start 2016/10/19 CSA Defect#15344
        param.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        // add end 2016/10/19 CSA Defect#15344
        param.put("dsContrStsOrder", DS_CONTR_STS.ORDER);
        // START 2017/06/19 Y.Osawa [QC#19256,ADD]
        param.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 Y.Osawa [QC#19256,ADD]
        // START 2017/12/19 U.Kim [QC#21527,ADD]
        param.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2017/12/19 U.Kim [QC#21527,ADD]
        // START 2018/02/01 U.Kim [QC#23686, ADD]
        param.put("multiCnt", this.multiCnt);
        param.put("usrVar2", this.usrVar2);
        // END 2018/02/01 U.Kim [QC#23686, ADD]

        return param;
    }

    private void callSupplyAbuseApi(NSZC078001PMsg pmsg) {

        int dtlCnt = pmsg.svcMachMstrPkList.getValidCount();
        setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pmsg.procDt, this.salesDate);

        NSZC078001 supplyAbuseApi = new NSZC078001();
        supplyAbuseApi.execute(pmsg, ONBATCH_TYPE.BATCH);

        if (pmsg.xxMsgIdList.getValidCount() == 0) {
            commit();
            this.normalCount = this.normalCount + dtlCnt;
        } else {
            rollback();
            this.errorCount = this.errorCount + dtlCnt;
        }
    }
}
