/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1050;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL1050.common.NSAL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public final class NSAL1050Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1050Query INSTANCE = new NSAL1050Query();

    /**
     * Constructor.
     */
    private NSAL1050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1050Query
     */
    public static NSAL1050Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1050CMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1050CMsg cMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, cnt), cMsg.A);
    }

    /**
     * getDownloadData
     * @param cMsg NSAL1050CMsg
     * @param cnt int
     */
    public void getDownloadData(NSAL1050CMsg cMsg, int cnt) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL1050Query query = NSAL1050Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            stmtSelect = ssmLLClient.createPreparedStatement("getSearchData", getSsmParam(cMsg, cnt), execParam);

            rs = stmtSelect.executeQuery();
            NSAL1050CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * <pre>
     * cntAttribute
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult cntAttribute(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("cntAttribute", queryParam);
    }

    /**
     * getSsmParam
     * @param cMsg NSAL1050CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSsmParam(NSAL1050CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcTermAttrbDescTxt.getValue())) {
            params.put("svcTermAttrbDescTxt", cMsg.svcTermAttrbDescTxt.getValue());
        }
        // START 2017/01/20 K.Ochiai [QC#16331,MOD]
        params.put("activeFlg", cMsg.xxChkBox_H.getValue());
        // END 2017/01/20 K.Ochiai [QC#16331,MOD]
        params.put("slsDate", cMsg.slsDt.getValue());
        params.put("svcTermCondCatgActvFlg", ZYPConstant.FLG_ON_Y);
        params.put("hitDate", "99991231");
        params.put("rowNum", cnt);
        return params;
    }
}
