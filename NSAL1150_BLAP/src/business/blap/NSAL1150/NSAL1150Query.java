/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1150;

import static business.blap.NSAL1150.constant.NSAL1150Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL1150.common.NSAL1150CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Watch Used / Allowed
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public final class NSAL1150Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1150Query INSTANCE = new NSAL1150Query();

    /**
     * Constructor.
     */
    private NSAL1150Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1150Query
     */
    public static NSAL1150Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcTermCondAttrbPk
     * @param cMsg NSAL1150CMsg
     * @return S21SsmEZDResult
     */
    public BigDecimal getSvcTermCondAttrbPk(NSAL1150CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("limitThruDt", LIMIT_THRU_DT);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("svcTermCondAttrbNm", cMsg.svcTermCondAttrbNm.getValue());

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getSvcTermCondAttrbPk", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (BigDecimal) rs.getResultObject();
    }

    /**
     * getSearchData
     * @param cMsg NSAL1150CMsg
     * @param sMsg NSAL1150SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getSearchDataForCSV
     * @param stmtSelect PreparedStatement
     * @param cMsg NSAL1150CMsg
     * @param cnt int
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet getSearchDataForCSV(PreparedStatement stmtSelect, NSAL1150CMsg cMsg, int cnt) throws SQLException {
        NSAL1150Query query = NSAL1150Query.getInstance();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());
        stmtSelect = ssmLLClient.createPreparedStatement("getSearchData", getSsmParam(cMsg, cnt), execParam);
        return stmtSelect.executeQuery();
    }

    /**
     * getSsmParam
     * @param cMsg NSAL1150CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL1150CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("rowNum", cnt);

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("limitThruDt", LIMIT_THRU_DT);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("svcTermCondAttrbPk", cMsg.svcTermCondAttrbPk.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.condSqlTxt_CU.getValue())) {
            params.put("shipToCustAcctCdArray", NSAL1150CommonLogic.convertCSVtoList(cMsg.condSqlTxt_CU.getValue()));
        }
        if (ZYPCommonFunc.hasValue(cMsg.condSqlTxt_CO.getValue())) {
            params.put("dsContrNumArray", NSAL1150CommonLogic.convertCSVtoList(cMsg.condSqlTxt_CO.getValue()));
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm.getValue())) {
            params.put("dsAcctNm", cMsg.dsAcctNm.getValue());
        }
        params.put("cancelled", DS_CONTR_DTL_STS.CANCELLED);
        params.put("effective", DS_CONTR_STS.EFFECTIVE);
        params.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        params.put("instaled", SVC_MACH_MSTR_STS.INSTALLED);

        params.put("totalTitle", cMsg.xxScrItem20Txt_TT.getValue());
        params.put("ContrNumTitle", cMsg.xxScrItem20Txt_TC.getValue());
        params.put("maxPct", MAX_PCT);

        return params;
    }

}
