package com.canon.cusa.s21.batch.NWA.NWAB910001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;

/**
 * <pre>
 * Creating Purge Order Target
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 06/16/2011   SRA             Y.Ando          Create          2269
 *</pre>
 */
public class NWAB910001 extends S21BatchMain {

	/** Global Company Code */
    private String glblCmpyCd;
    
    /** Term cd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;
    
    /** Purge Target Name */
    private String purgeTrgtNm;
    
    /** Processing Count */
    private int procCount = 0;

    /** Normal Count */
    private int normalCnt = 0;

    /** Error Count */
    private int errCnt = 0;
    
    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    
    /** SQL List */
    private List<String> sqlList = null;
    
    /**
     * 
     */
    public NWAB910001() {
        super();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.sqlList = new ArrayList<String>();
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NWAB910001().executeBatch(NWAB910001.class.getSimpleName());
	}
	
	@Override
	protected void initRoutine() {
		if (!isGlobalCompanyCode()) {
            S21InfoLogOutput.println("NWCM0008E", new String[] {"Global Company Code" });
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        // User Variable - 1 : Purge Target Name
        this.purgeTrgtNm = getUserVariable1();
        if (!hasValue(this.purgeTrgtNm)) {
            S21InfoLogOutput.println("Purge Target Name is null, the work table clear never process.");
            return;
        }
	}

	@Override
	protected void mainRoutine() {
		try {

            // ----------------------------------------
            // Search SQL from Archive Condition Table.
            // ----------------------------------------
            getBaseSQL();

            // ----------------------------------------
            // 1.Purge Work Table clear.
            // ----------------------------------------
            procPurgeWrkClear();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

	}

	@Override
	protected void termRoutine() {
		setTermState(this.termCd, this.normalCnt, this.errCnt, this.procCount);
	}
	
	private void getBaseSQL() throws SQLException {

        Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("pgId", NWAB910001.class.getSimpleName());
        param.put("purgeTrgtNm", this.purgeTrgtNm);

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = ssmLLClient.createPreparedStatement("getBaseSQLList", param, ssmParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                createSQL(rs.getString("SRC_TABLE_NM"), rs.getString("SRC_SCHM_NM"), rs.getString("BASE_SQL"));
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
	
	private void createSQL(String tableName, String schmNm, String baseSql) {

        String execSql = baseSql;
        execSql = execSql.replace("%1", schmNm);
        execSql = execSql.replace("%2", this.glblCmpyCd);
        execSql = execSql.replace("%3", NWAB910001.class.getSimpleName());

        this.sqlList.add(execSql);
    }
	
	private void procPurgeWrkClear() throws SQLException {
		S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        
        try {
            for (String execSql : sqlList) {
            	stmt = ssmLLClient.createPreparedStatement("deletePurgeWorkData", execSql, ssmParam);
            	procCount += stmt.executeUpdate();
            }
        } finally {
            normalCnt = procCount;
            S21SsmLowLevelCodingClient.closeResource(stmt, null);
        }
        
        
	}
	
	
	private boolean isGlobalCompanyCode() {

        this.glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(this.glblCmpyCd)) {
            return false;
        }

        return ZYPCodeDataUtil.hasCodeValue(GLBL_CMPY.class, this.glblCmpyCd, this.glblCmpyCd);
    }

}
