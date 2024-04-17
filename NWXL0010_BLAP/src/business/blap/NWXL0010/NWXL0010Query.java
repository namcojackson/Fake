/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NWXL0010;

import static business.blap.NWXL0010.common.NWXL0010CommonLogic.getUserProfService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMimeSourceItem;
import parts.dbcommon.EZDDBRetryRequestException;
import business.blap.NWXL0010.constant.NWXL0010Constant;
import business.db.RPT_SQLTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class NWXL0010Query extends S21SsmEZDQuerySupport implements NWXL0010Constant {

    private static final NWXL0010Query MY_INSTANCE = new NWXL0010Query();

    private NWXL0010Query() {
        super();
    }
    
    public static NWXL0010Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * check SQL Statement.
     * 
     * @param   sql     SQL
     * @return  SQL Ignored Message
     */
    public String checkSqlStatement(String sql) {

        String sqlIgnoredMsg = null;

        try {
            getSsmBatchClient().queryObject("execRptSQL", sql);
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

    /**
     * create CSV file for download by Scrn00.
     * 
     * @param   xxFileData  EZDCMimeSourceItem
     * @param   rptSqlTMsg  RPT_SQLTMsg
     * @return  true/success,   false/failure
     */
    boolean createCsvFile(EZDCMimeSourceItem xxFileData, RPT_SQLTMsg rptSqlTMsg) {
        
        final String rptSqlTxt = new RptSqlTxtAccessor(rptSqlTMsg).getRptSqlTxt();
        
        return (Boolean) getSsmBatchClient().queryObject("execRptSQL", rptSqlTxt, new CsvFileCreator(xxFileData, rptSqlTMsg));
    }

    /**
     * create CSV file for download by Scrn01.
     * 
     * @param   xxFileData  EZDCMimeSourceItem
     * @param   rptSqlTMsg  RPT_SQLTMsg
     * @param   rptSqlTxt   RPT_SQL_TXT
     * @return  true/success,   false/failure
     */
    boolean createCsvFile(EZDCMimeSourceItem xxFileData, RPT_SQLTMsg rptSqlTMsg, String rptSqlTxt) {
        
        return (Boolean) getSsmBatchClient().queryObject("execRptSQL", rptSqlTxt, new CsvFileCreator(xxFileData, rptSqlTMsg));
    }

    /**
     * get a 'RPT_SQL' record.
     * 
     * @param   cMsg            NWXL0010CMsg
     * @param   ezcancelflag    EZcancelflag
     * @return  S21SsmEZDResult
     */
    S21SsmEZDResult getRptSqlInfo(NWXL0010CMsg cMsg, EZcancelflag ezcancelflag) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",   getGlobalCompanyCode());
        ssmParam.put("cMsg",         cMsg);
        ssmParam.put("funcIdList",   getAuthorizedFunctionCodeListForBizAppId());
        ssmParam.put("ezcancelflag", ezcancelflag.getValue());

        return getSsmEZDClient().queryEZDMsg("getRptSqlInfo", ssmParam, cMsg);
    }

    /**
     * get 'RPT_SQL' records.
     * 
     * @param   cMsg        NWXL0010CMsg
     * @param   sMsgArray   NWXL0010_ASMsgArray
     * @return  S21SsmEZDResult
     */
    S21SsmEZDResult getRptSqlInfoList(NWXL0010CMsg cMsg, NWXL0010_ASMsgArray sMsgArray) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg",       cMsg);
        ssmParam.put("funcIdList", getAuthorizedFunctionCodeListForBizAppId());

        return getSsmEZDClient().queryEZDMsgArray("getRptSqlInfoList", ssmParam, sMsgArray);
    }

    /**
     * get 'Rpt Download History' records.
     * 
     * @param   cMsg        NWXL0010_ACMsg
     * @param   sMsgArray   NWXL0010_BSMsgArray
     * @return  S21SsmEZDResult
     */
    S21SsmEZDResult getRptDownloadHistoryList(NWXL0010_ACMsg selectedLineCMsg, NWXL0010_BSMsgArray sMsgArray) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg",  selectedLineCMsg);
        ssmParam.put("bizId", MY_BIZ_ID);
        
        return getSsmEZDClient().queryEZDMsgArray("getRptDownloadHistoryList", ssmParam, sMsgArray);
    }

    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }
    
    private static List<String> getAuthorizedFunctionCodeListForBizAppId() {
        return getUserProfService().getAuthorizedFunctionCodeListForBizAppId(MY_BIZ_ID);
    }

    /**
     * EZCANCELFLAG.
     * 
     * @author K.Tajima
     */
    enum EZcancelflag {

        AVAILABLE("0"),

        UN_AVAILABLE("1");

        private final String value;

        private EZcancelflag(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
