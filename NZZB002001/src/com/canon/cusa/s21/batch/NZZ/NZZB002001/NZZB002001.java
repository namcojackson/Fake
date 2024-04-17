/**
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */

package com.canon.cusa.s21.batch.NZZ.NZZB002001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.db.ARC_RQSTTMsg;
import business.db.ARC_RQST_RECTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21OracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Setup Archive Request Batch
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2018   Hitachi         E.Kameishi      Create          N/A
 * </pre>
 */
public class NZZB002001 extends S21BatchMain implements ZYPConstant, NZZB002001Constant {


    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** procCount */
    private int procCount = 0;

    /** normalCnt */
    private int normalCnt = 0;

    /** errCnt */
    private int errCnt = 0;

    /** Batch Process Date */
    private String batProcDt = null;

    /** Batch Process Date */
    private String arcCondGrpId = null;


    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /* Initialization S21BatchMain */
        new NZZB002001().executeBatch(NZZB002001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {
        S21InfoLogOutput.println("initRoutine Method Start");

        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }
        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        this.arcCondGrpId = getUserVariable1();

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        try {
            execute();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    private void execute() throws SQLException {

        if (!hasValue(this.arcCondGrpId)) {
            return;
        }
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("arcCondGrpId", this.arcCondGrpId);

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ssmLLClient.createPreparedStatement("getArcCondList", param, ssmParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ARC_RQSTTMsg arcRqstTmsg = new ARC_RQSTTMsg();
                BigDecimal arRqstPk = S21OracleSeqAccessor.getSeqNumber("ARC_RQST_SQ");
                ZYPEZDItemValueSetter.setValue(arcRqstTmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(arcRqstTmsg.arcRqstPk, arRqstPk);
                ZYPEZDItemValueSetter.setValue(arcRqstTmsg.srcSchmNm, rs.getString("SRC_SCHM_NM"));
                ZYPEZDItemValueSetter.setValue(arcRqstTmsg.srcTblNm, rs.getString("ARC_COND_NM"));
                ZYPEZDItemValueSetter.setValue(arcRqstTmsg.recRetenStartYrMth, this.batProcDt.substring(0, 6));
                S21FastTBLAccessor.insert(arcRqstTmsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arcRqstTmsg.getReturnCode())) {
                    this.addCnt(1, 0);
                    rollback();
                    S21InfoLogOutput.println("Insert Error[Header] : " + rs.getString("ARC_COND_NM"));
                    return;
                } else {
                    this.addCnt(1, 1);
                }

                ARC_RQST_RECTMsg arcRqstRecTmsg = new ARC_RQST_RECTMsg();
                BigDecimal arRqstRecPk = S21OracleSeqAccessor.getSeqNumber("ARC_RQST_REC_SQ");
                ZYPEZDItemValueSetter.setValue(arcRqstRecTmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(arcRqstRecTmsg.arcRqstRecPk, arRqstRecPk);
                ZYPEZDItemValueSetter.setValue(arcRqstRecTmsg.arcRqstPk, arRqstPk);
                String condSqlTxt = "";
                for (int i = 1; i <= 5; i++) {
                    String arcCondRefTxt = "ARC_COND_REF_TXT_" + String.format("%02d", i);
                    if (hasValue(rs.getString(arcCondRefTxt))) {
                        condSqlTxt = condSqlTxt + rs.getString(arcCondRefTxt);
                    }
                }
                ZYPEZDItemValueSetter.setValue(arcRqstRecTmsg.condSqlTxt, condSqlTxt);
                S21FastTBLAccessor.insert(arcRqstRecTmsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arcRqstRecTmsg.getReturnCode())) {
                    this.addCnt(1, 0);
                    rollback();
                    S21InfoLogOutput.println("Insert Error[Detail] : " + rs.getString("ARC_COND_NM"));
                    return;
                } else {
                    this.addCnt(1, 1);
                    commit();
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void addCnt(int procCnt, int successCnt) {
        this.procCount = this.procCount + procCnt;
        this.normalCnt = this.normalCnt + successCnt;
        this.errCnt = this.errCnt + (procCnt - successCnt);
    }
}
