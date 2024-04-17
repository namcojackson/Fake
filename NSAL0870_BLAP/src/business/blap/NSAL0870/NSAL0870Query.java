/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0870;

import static business.blap.NSAL0870.constant.NSAL0870Constant.DS_MSG_TRX_NM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0870.common.NSAL0870CommonLogic;

import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public final class NSAL0870Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0870Query INSTANCE = new NSAL0870Query();

    /**
     * Constructor.
     */
    private NSAL0870Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0870Query
     */
    public static NSAL0870Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    /**
     * getDownloadData
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     * @param cnt int
     */
    public void getDownloadData(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg, int cnt) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0870Query query = NSAL0870Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            stmtSelect = ssmLLClient.createPreparedStatement("getDownloadData", getSsmParam(cMsg, sMsg, cnt), execParam);

            rs = stmtSelect.executeQuery();
            NSAL0870CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * getSsmParam
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSsmParam(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mtrReadSrcTpCd", cMsg.mtrReadSrcTpCd_H.getValue());
        params.put("serNum", cMsg.serNum_H.getValue());
        params.put("dsMtrProcStsCd", cMsg.dsMtrProcStsCd_H.getValue());
        params.put("mtrReadDt_FR", cMsg.mtrReadDt_FH.getValue());
        params.put("mtrReadDt_TO", cMsg.mtrReadDt_TH.getValue());
        params.put("rowNum", cnt);
        params.put("dsMsgTrxNm", DS_MSG_TRX_NM);
        return params;
    }
}
