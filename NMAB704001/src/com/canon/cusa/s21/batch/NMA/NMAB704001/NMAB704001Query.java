package com.canon.cusa.s21.batch.NMA.NMAB704001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * SFDC Price Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/01   Fujitsu         Mz.Takahashi    Create          SOL#093(QC#20250)
 *</pre>
 */
public class NMAB704001Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final NMAB704001Query MY_INSTANCE = new NMAB704001Query();

    /**
     * Private constructor
     */
    private NMAB704001Query() {
        super();
    }

    /**
     * Get the ZZWB007001Query instance.
     * @return ZZWB007001Query instance
     */
    public static NMAB704001Query getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getWrk(BigDecimal upldCsvRqstPk, int startIndex, int endIndex) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("upldCsvRqstPk", upldCsvRqstPk.toPlainString());
        params.put("startIndex", startIndex);
        params.put("endIndex", endIndex);

        return getSsmEZDClient().queryObjectList("getWrk", params);
    }

    public void truncateSomPrcBookInfo() throws SQLException {
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> qParam = new HashMap<String, Object>();

        try {
            stmt = ssmLLClient.createPreparedStatement("truncateSomPrcBookInfo", qParam, ssmParam);
            rs = stmt.executeQuery();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
}
