package business.blap.ZZZL0040;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.ZZZL0040.common.ZZZL0040CommonLogic;
import business.blap.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0040Query extends S21SsmEZDQuerySupport implements ZZZL0040Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZZL0040Query myInstance = new ZZZL0040Query();
    private static final int DEF_BUF_SIZE = 1024;

	/**
	 * Constructor.
	 */
    private ZZZL0040Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZZL0040Query
	 */
    public static ZZZL0040Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getDataByAvrg
     * <dd>The method explanation: Get data from ONL_PROC_LOG
     * <dd>Remarks:
     * @param   cMsg ZZZL0040CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getDataForAvrg(ZZZL0040CMsg cMsg) {
                
        S21SsmEZDResult result = null;
        Map<String, Object> param = new HashMap<String, Object>();

        if (cMsg.jvmNm_S.getValue().equals("All")) {
            cMsg.jvmNm_S.clear();
        }

        param.put("cMsg", cMsg);
        
        switch (cMsg.xxRadioBtn.getValueInt()) {
            case BIZ_PROC_TIME_RANK:
                result = getSsmEZDClient().queryObjectList("getAvrgBizProcTmRank", param);
                break;
            case BIZ_THROUGHPUT_RANK:
                result = getSsmEZDClient().queryObjectList("getAvrgThroughputRank", param);
                break;
            case GLBL_AREA_SIZE_RANK:
                result = getSsmEZDClient().queryObjectList("getAvrgGlblAreaSizeRank", param);
                break;
        }
        return result;
    }
    
    /**
     * Method name: getDataByAvrg
     * <dd>The method explanation: Get data from ONL_PROC_LOG
     * <dd>Remarks:
     * @param   cMsg ZZZL0040CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getDataForUnit(ZZZL0040CMsg cMsg) {
        
        S21SsmEZDResult result = null;
        Map<String, Object> param = new HashMap<String, Object>();

        if (cMsg.jvmNm_S.getValue().equals("All")) {
            cMsg.jvmNm_S.clear();
        }
        if (cMsg.xxTotCnt.getValue().equals(BigDecimal.ZERO)) {
            cMsg.xxTotCnt.clear();
        }
        param.put("cMsg", cMsg);
        
        switch (cMsg.xxRadioBtn.getValueInt()) {
            case BIZ_PROC_TIME_RANK:
                result = getSsmEZDClient().queryObjectList("getBizProcTmRank", param);
                break;
            case BIZ_THROUGHPUT_RANK:
                result = getSsmEZDClient().queryObjectList("getThroughputRank", param);
                break;
            case GLBL_AREA_SIZE_RANK:
                result = getSsmEZDClient().queryObjectList("getGlblAreaSizeRank", param);
                break;
        }
        return result;
    }
    
    /**
     * Method name: getJvmNm
     * <dd>The method explanation: Get data from ONL_PROC_LOG
     * <dd>Remarks:
     * @param   cMsg ZZZL0040CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getJvmNm(ZZZL0040CMsg cMsg) {
        
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("cMsg", cMsg);               
        return getSsmEZDClient().queryObjectList("getJvmNm", param);
    }
    
    public void createHistoryReport(ZZZL0040CMsg cMsg) {

        final Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("cMsg", cMsg);

        S21SsmBatchClient.getClient(this.getClass()).queryObject("getDataForExportExcel", param, new ReportCreator(cMsg));
    }
    
    private final class ReportCreator extends S21SsmVoidResultSetHandlerSupport {
        
        private ZZZL0040CMsg cMsg = null;
        private final String newline = System.getProperty("line.separator"); 
        
        public ReportCreator(ZZZL0040CMsg cMsg) {
            this.cMsg = cMsg;   
            
            // Building file name
            StringBuilder fileName = new StringBuilder();
            fileName.append("RerformanceHistoy");
            fileName.append("_");
            fileName.append(cMsg.bizStartTs.getValue());
            
            // set file path to write CSV file.
            cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".csv");
        }

        @Override
        protected void doProcessQueryResult(ResultSet result) throws SQLException {

            if (!result.next()) {
                cMsg.setMessageInfo("ZZZM9005W");
                return;
            }
            FileWriter fw = null;
            StringBuilder detail = new StringBuilder(DEF_BUF_SIZE);
            try {
                fw = new FileWriter(new File(cMsg.xxFileData.getTempFilePath()));
                detail.append("BUSINESS_ID").append(",");
                detail.append("BUSINESS_NAME").append(",");
                detail.append("EVENT_NAME").append(",");
                detail.append("EVENT_ELAPSED_TIME").append(",");
                detail.append("NUM_OF_EXECUTIONS").append(",");
                detail.append("SMSG_SIZE").append(",");
                detail.append("USER_ID").append(",");
                detail.append("USER_NAME").append(",");
                detail.append("OPERATION_DATE").append(",");
                detail.append("JVM_NM").append(newline);
                fw.write(detail.toString());
                detail.setLength(0);
                
              do {
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("BUSINESS_ID"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("BUSINESS_NAME"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("EVENT_NAME"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("EVENT_ELAPSED_TIME"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("NUM_OF_EXECUTIONS"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("SMSG_SIZE"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("USER_ID"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("USER_NAME"))).append(",");
                  detail.append(ZZZL0040CommonLogic.getDateforCsvFile(result.getString("OPERATION_DATE"))).append(",");
                  detail.append(ZZZL0040CommonLogic.nvl(result.getString("JVM_NM"))).append(newline);
                  fw.write(detail.toString());
                  detail.setLength(0);
              } while (result.next());
              
            } catch (IOException e) {

            } finally {
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
