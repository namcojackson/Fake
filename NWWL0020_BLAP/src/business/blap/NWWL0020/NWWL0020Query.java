/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMimeSourceItem;
import parts.dbcommon.EZDDBRetryRequestException;
import business.db.NTFY_HDRTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWWL0020Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public final class NWWL0020Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWWL0020Query MY_INSTANCE = new NWWL0020Query();

    /**
     * Private constructor
     */
    private NWWL0020Query() {
        super();
    }

    /**
     * Get the NWWL0020Query instance.
     * @return NWWL0020Query instance
     */
    public static NWWL0020Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyActionList(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyHdrPk", bizMsg.ntfyHdrPk_H0);

        return getSsmEZDClient().queryEZDMsgArray("getNtfyActionList", params, glblMsg.A);
    }

    /**
     * Execute SQL
     * @param sql String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult execSql(String sql) {
        return getSsmEZDClient().queryObject("execSql", sql);
    }

    /**
     * create CSV file for download by Scrn01.
     * @param xxFileData EZDCMimeSourceItem
     * @param ntfyHdrTMsg NTFY_HDRTMsg
     * @param sqlTxt String
     * @return true/success, false/failure
     */
    public boolean createCsvFile(EZDCMimeSourceItem xxFileData, NTFY_HDRTMsg ntfyHdrTMsg, String sqlTxt) {

        return (Boolean) getSsmBatchClient().queryObject("execSql", sqlTxt, new CsvFileCreator(xxFileData, ntfyHdrTMsg));
    }

    /**
     * check SQL Statement.
     * @param sql SQL
     * @return SQL Ignored Message
     */
    public String checkSqlStatement(String sql) {

        String sqlIgnoredMsg = null;

        try {
            getSsmBatchClient().queryObject("execSql", sql);
        } catch (Exception e) {
            if (e instanceof EZDDBRetryRequestException) {
                Throwable th = ((EZDDBRetryRequestException) e).getCause();
                if (th instanceof SQLException) {
                    sqlIgnoredMsg = ((SQLException) th).getMessage();
                } else {
                    throw (RuntimeException) th;
                }
            } else {
                throw (RuntimeException) e;
            }
        }

        return sqlIgnoredMsg;
    }

    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }
}
