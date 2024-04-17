package business.blap.NWAL2040;

import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.blap.NWAL2040.NWAL2040Query;
import business.blap.NWAL2040.constant.NWAL2040Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/23   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 * 2017/09/25   Fujitsu         T.Ogura         Update          QC#21369
 *</pre>
 */
public class NWAL2040Query extends S21SsmEZDQuerySupport implements NWAL2040Constant {

    /**
     * Singleton instance.
     */
    private static final NWAL2040Query myInstance = new NWAL2040Query();
    
    /**
     * Constructor.
     */
    private NWAL2040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2040Query
     */
    public static NWAL2040Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult setInitialDefWhMapTmplPulldown(NWAL2040CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("setInitialDefWhMapTmplPulldown", ssmParam);
    }
    
    public S21SsmEZDResult setInitialOrdLineSrcPulldown(NWAL2040CMsg cMsg, String glblCmpyCd, String ordLineSrcCatgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordLineSrcCatgCd", ordLineSrcCatgCd);
        return getSsmEZDClient().queryObjectList("setInitialOrdLineSrcPulldown", ssmParam);
    }

    public S21SsmEZDResult setInitialOrdLineSrcPulldownIE(NWAL2040CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordLineSrcCatgCd_I", ORD_LINE_SRC_CATG.INTERNAL);
        ssmParam.put("ordLineSrcCatgCd_E", ORD_LINE_SRC_CATG.EXTERNAL);
        return getSsmEZDClient().queryObjectList("setInitialOrdLineSrcPulldownIE", ssmParam);
    }

    public S21SsmEZDResult setInitialMdseItemClsTpPulldown(NWAL2040CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("setInitialMdseItemClsTpPulldown", ssmParam);
    }
    
    public S21SsmEZDResult searchMapSrcCatg(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.B.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryEZDMsgArray( "searchMapSrcCatg", ssmParam, sMsg.B);
    }
    
    public S21SsmEZDResult searchMapTmplQlfy(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("defWhMapTmplCd", cMsg.defWhMapTmplCd_H1.getValue());
        return getSsmEZDClient().queryEZDMsgArray( "searchMapTmplQlfy", ssmParam, sMsg.A);
    }

    // 2017/09/12 QC#19805 Add Start
    // 2017/09/25 QC#21369 Mod Start
//    public S21SsmEZDResult searchMapTmplQlfyRMA(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, String glblCmpyCd) {
    public S21SsmEZDResult searchMapTmplQlfyRMA(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg, String glblCmpyCd, boolean FilterFlg) {
    // 2017/09/25 QC#21369 Mod End
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.E.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // 2017/09/25 QC#21369 Mod Start
//        ssmParam.put("defWhMapTmplCd", cMsg.defWhMapTmplCd_H1.getValue());
        if (FilterFlg) {
            ssmParam.put("defWhMapTmplCd", cMsg.defWhMapTmplCd_H1.getValue());
        }
        // 2017/09/25 QC#21369 Mod End
        return getSsmEZDClient().queryEZDMsgArray( "searchMapTmplQlfyRMA", ssmParam, sMsg.E);
    }
    // 2017/09/12 QC#19805 Add End

    public S21SsmEZDResult getDsOrdCatgAndDsOrdTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        return getSsmEZDClient().queryObject("getDsOrdCatgAndDsOrdTp", ssmParam);
    }
    
    public S21SsmEZDResult getDsOrdCatg(String glblCmpyCd, String dsOrdCatgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        return getSsmEZDClient().queryObject("getDsOrdCatg", ssmParam);
    }
    
    public S21SsmEZDResult getPhysWh(String glblCmpyCd, String physWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("physWhCd", physWhCd);
        return getSsmEZDClient().queryObject("getPhysWh", ssmParam);
    }
    
    public S21SsmEZDResult getRtlSwhFromName(String glblCmpyCd, String rtlSwhNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlSwhNm", rtlSwhNm);
        return getSsmEZDClient().queryObject("getRtlSwhFromName", ssmParam);
    }
    public S21SsmEZDResult getInternalOrderLineSource(String glblCmpyCd, String ordLineSrcCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordLineSrcCd", ordLineSrcCd);
        return getSsmEZDClient().queryObject("getInternalOrderLineSource", ssmParam);
    }
    public S21SsmEZDResult setDsOrdTpPulldown(String glblCmpyCd, String dsOrdCatgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        return getSsmEZDClient().queryObjectList("setDsOrdTpPulldown", ssmParam);
    }

    // START 2016/03/24 K.Kojima [UT#56,ADD]
    /**
     * searchMapTmplQlfyForCSV
     * @param stmtSelect PreparedStatement
     * @param glblCmpyCd String
     * @param cnt int
     * @param dplyTabName String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    // 2017/09/12 QC#19805 Mod Start
//    public ResultSet searchMapTmplQlfyForCSV(PreparedStatement stmtSelect, String glblCmpyCd, int cnt) throws SQLException {
    public ResultSet searchMapTmplQlfyForCSV(PreparedStatement stmtSelect, String glblCmpyCd, int cnt, String dplyTabName) throws SQLException {
    // 2017/09/12 QC#19805 Mod End
        NWAL2040Query query = NWAL2040Query.getInstance();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rowNum", cnt);
        params.put("mdseItemClsTpCdAst", MDSE_ITEM_CLS_TP_CD_AST);
        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(dplyTabName)) {
        // 2017/09/12 QC#19805 Add End
            stmtSelect = ssmLLClient.createPreparedStatement("searchMapTmplQlfyForCSV", params, execParam);
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(dplyTabName)) {
            stmtSelect = ssmLLClient.createPreparedStatement("searchMapTmplQlfyRMAForCSV", params, execParam);
        }
        // 2017/09/12 QC#19805 Add End
        return stmtSelect.executeQuery();
    }
    // END 2016/03/24 K.Kojima [UT#56,ADD]

}
