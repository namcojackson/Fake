package business.blap.NMAL7120;

import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.sql.ResultSet;

import business.blap.NMAL7120.NMAL7120CMsg;
import business.blap.NMAL7120.NMAL7120Query;
import business.blap.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3008
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 *</pre>
 */
public class NMAL7120Query extends S21SsmEZDQuerySupport implements NMAL7120Constant {

    /**
     * Singleton instance.
     */
    private static final NMAL7120Query myInstance = new NMAL7120Query();
    
    /**
     * Constructor.
     */
    private NMAL7120Query() {
		super();
		//test
    }

    /**
     * Singleton instance getter.
     * @return NMAL7120Query
     */
    public static NMAL7120Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult search(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        
        ssmParam.put("rowNum", cMsg.xxDtlCnt_H1.getValueInt() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNm", cMsg.dsAcctNm_H1.getValue());  //Account Name
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());  //Account Number
        ssmParam.put("csmpNum", cMsg.csmpNum_H1.getValue());  //CSMP#
        ssmParam.put("dlrRefNum", cMsg.dlrRefNum_H1.getValue());  //CSA#
        ssmParam.put("prcCatgNm", cMsg.prcCatgNm_H1.getValue());  //CSMP Level(Price Category Name)
        ssmParam.put("prcContrNum", cMsg.prcContrNum_H1.getValue());  //Contract#
        ssmParam.put("effFromDt", cMsg.effFromDt_H1.getValue());  //Effective Date From
        ssmParam.put("erlTrmnDt", cMsg.effThruDt_H1.getValue());  //Expiration Date
        ssmParam.put("csmpContrActvFlg", cMsg.csmpContrActvFlg_H1.getValue());  //Status
        ssmParam.put("expiredFlg", cMsg.xxModeNm13Txt_H1.getValue());  //Expired Flag
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());  //Sales Date
        ssmParam.put("rtlDlrCd", cMsg.rtlDlrCd_H1.getValue());  //Dealer Code
        ssmParam.put("rnwCsmpNum", cMsg.rnwCsmpNum_H1.getValue());  //Renewed CSMP#
        if ("Y".equals(cMsg.xxChkBox_H1.getValue())) {
	        ssmParam.put("noCSMP", "Y");  //No CSMP
        }
        ssmParam.put("prcListTpCd01", PRC_LIST_TP_CD_CSMP); //S21_NA ADD QC#20206(L3#269)
        ssmParam.put("prcListTpCd02", PRC_LIST_TP_CD_CSMP_OFFER); //S21_NA ADD QC#20206(L3#269)

        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }
    
    public Boolean searchForCSV(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg, String glblCmpyCd, S21SsmBooleanResultSetHandlerSupport rsSupport) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNm", cMsg.dsAcctNm_H1.getValue());  //Account Name
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());  //Account Number
        ssmParam.put("csmpNum", cMsg.csmpNum_H1.getValue());  //CSMP#
        ssmParam.put("dlrRefNum", cMsg.dlrRefNum_H1.getValue());  //CSA#
        ssmParam.put("prcCatgNm", cMsg.prcCatgNm_H1.getValue());  //CSMP Level(Price Category Name)
        ssmParam.put("prcContrNum", cMsg.prcContrNum_H1.getValue());  //Contract#
        ssmParam.put("effFromDt", cMsg.effFromDt_H1.getValue());  //Effective Date From
        ssmParam.put("erlTrmnDt", cMsg.effThruDt_H1.getValue());  //Expiration Date
        ssmParam.put("csmpContrActvFlg", cMsg.csmpContrActvFlg_H1.getValue());  //Status
        ssmParam.put("expiredFlg", cMsg.xxModeNm13Txt_H1.getValue());  //Expired Flag
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());  //Sales Date
        ssmParam.put("rtlDlrCd", cMsg.rtlDlrCd_H1.getValue());  //Dealer Code
        ssmParam.put("rnwCsmpNum", cMsg.rnwCsmpNum_H1.getValue());  //Renewed CSMP#
        if ("Y".equals(cMsg.xxChkBox_H1.getValue())) {
	        ssmParam.put("noCSMP", "Y");  //No CSMP
        }
        ssmParam.put("prcListTpCd01", PRC_LIST_TP_CD_CSMP); //S21_NA ADD QC#20206(L3#269)
        ssmParam.put("prcListTpCd02", PRC_LIST_TP_CD_CSMP_OFFER); //S21_NA ADD QC#20206(L3#269)
        
        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        
        return (Boolean) ssmBatchClient.queryObject("searchForCSV", ssmParam, ssmExecParam, rsSupport);
    }
    
    public S21SsmEZDResult getDsAcctCust(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsAcctCust", ssmParam);
    }
    public S21SsmEZDResult getPrcCatg(String glblCmpyCd, String prcCatgNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgNm", prcCatgNm);
        return getSsmEZDClient().queryObjectList("getPrcCatg", ssmParam);
    }
    public S21SsmEZDResult getPrcContract(String glblCmpyCd, String prcContrNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcContrNum", prcContrNum);
        return getSsmEZDClient().queryObjectList("getPrcContract", ssmParam);
    }
    public S21SsmEZDResult getCoaBr(String glblCmpyCd, String coaBrCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("coaBrCd", coaBrCd);
        return getSsmEZDClient().queryObjectList("getCoaBr", ssmParam);
    }
    public S21SsmEZDResult searchTotCnt(NMAL7120CMsg cMsg, String glblCmpyCd) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("rowNum", cMsg.xxDtlCnt_H1.getValueInt() + 1);
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsAcctNm", cMsg.dsAcctNm_H1.getValue());  //Account Name
            ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());  //Account Number
            ssmParam.put("csmpNum", cMsg.csmpNum_H1.getValue());  //CSMP#
            ssmParam.put("dlrRefNum", cMsg.dlrRefNum_H1.getValue());  //CSA#
            ssmParam.put("prcCatgNm", cMsg.prcCatgNm_H1.getValue());  //CSMP Level(Price Category Name)
            ssmParam.put("prcContrNum", cMsg.prcContrNum_H1.getValue());  //Contract#
            ssmParam.put("effFromDt", cMsg.effFromDt_H1.getValue());  //Effective Date From
            ssmParam.put("erlTrmnDt", cMsg.effThruDt_H1.getValue());  //Expiration Date
            ssmParam.put("csmpContrActvFlg", cMsg.csmpContrActvFlg_H1.getValue());  //Status
            ssmParam.put("expiredFlg", cMsg.xxModeNm13Txt_H1.getValue());  //Expired Flag
            ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());  //Sales Date
            ssmParam.put("rtlDlrCd", cMsg.rtlDlrCd_H1.getValue());  //Dealer Code
            ssmParam.put("rnwCsmpNum", cMsg.rnwCsmpNum_H1.getValue());  //Renewed CSMP#
            if ("Y".equals(cMsg.xxChkBox_H1.getValue())) {
    	        ssmParam.put("noCSMP", "Y");  //No CSMP
            }
            ssmParam.put("prcListTpCd01", PRC_LIST_TP_CD_CSMP); //S21_NA ADD QC#20206(L3#269)
            ssmParam.put("prcListTpCd02", PRC_LIST_TP_CD_CSMP_OFFER); //S21_NA ADD QC#20206(L3#269)
            return getSsmEZDClient().queryObject("searchTotCnt", ssmParam);
    }
    public S21SsmEZDResult getDBDuplicated(String glblCmpyCd, NMAL7120_ASMsg line, 
    		List<BigDecimal> targetDeleteList, List<BigDecimal> excludeScreenMsgList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", line.dsAcctNum_A1.getValue());
        if (ZYPCommonFunc.hasValue(line.csmpNum_A1)) ssmParam.put("csmpNum", line.csmpNum_A1.getValue());
        if (ZYPCommonFunc.hasValue(line.dlrRefNum_A1)) ssmParam.put("dlrRefNum", line.dlrRefNum_A1.getValue());
        ssmParam.put("effFromDt", line.effFromDt_A1.getValue());
        if (ZYPCommonFunc.hasValue(line.effThruDt_A1)) {
        	ssmParam.put("effThruDt", line.effThruDt_A1.getValue());
        } else {
        	ssmParam.put("effThruDt", "99991231");
        }
        if (targetDeleteList != null && !targetDeleteList.isEmpty()) {
            ssmParam.put("targetDeleteFlg", "Y");
        }
        ssmParam.put("targetDeleteList", targetDeleteList);
        
        if (excludeScreenMsgList != null && !excludeScreenMsgList.isEmpty()) {
            ssmParam.put("excludeScreenMsgFlg", "Y");
        }
        ssmParam.put("excludeScreenMsgList", excludeScreenMsgList);
        return getSsmEZDClient().queryObject("getDBDuplicated", ssmParam);
    }
}
