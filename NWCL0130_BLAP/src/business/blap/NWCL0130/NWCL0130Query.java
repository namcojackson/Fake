package business.blap.NWCL0130;

import java.util.HashMap;
import java.util.Map;
import business.blap.NWCL0130.NWCL0130CMsg;
import business.blap.NWCL0130.NWCL0130Query;
import business.blap.NWCL0130.constant.NWCL0130Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3008
 *</pre>
 */
public class NWCL0130Query extends S21SsmEZDQuerySupport implements NWCL0130Constant {

    /**
     * Singleton instance.
     */
    private static final NWCL0130Query myInstance = new NWCL0130Query();
    
    /**
     * Constructor.
     */
    private NWCL0130Query() {
		super();
		//test
    }

    /**
     * Singleton instance getter.
     * @return NWCL0130Query
     */
    public static NWCL0130Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult search(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslRgnrProcCd", "00");  //un process
        ssmParam.put("CANON_E479_INV_SRCH_TBL", ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", glblCmpyCd));
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }
    
    public S21SsmEZDResult getConslBill(String glblCmpyCd, String conslBillNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        return getSsmEZDClient().queryObject("getConslBill", ssmParam);
    }
    
    public S21SsmEZDResult getInvInfo(String glblCmpyCd, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObject("getInvInfo", ssmParam);
    }
    
    public S21SsmEZDResult getBillInvReln(String glblCmpyCd, String conslBillNum, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObject("getBillInvReln", ssmParam);
    }
    public S21SsmEZDResult getBillInvBillToCust(String glblCmpyCd, String conslBillNum, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObject("getBillInvBillToCust", ssmParam);
    }
    
    public S21SsmEZDResult getEasyPackInvoice(String glblCmpyCd, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("easyPack1", "EASY_PACK1");
        ssmParam.put("easyPack1Return", "EASY_PACK1_RETURN");
        return getSsmEZDClient().queryObject("getEasyPackInvoice", ssmParam);
    }
    
    public S21SsmEZDResult getEasyPackBill(String glblCmpyCd, String conslBillNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        ssmParam.put("easyPack1", "EASY_PACK1");
        ssmParam.put("easyPack1Return", "EASY_PACK1_RETURN");
        return getSsmEZDClient().queryObject("getEasyPackBill", ssmParam);
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
//    public S21SsmEZDResult getDuplicateLine(String glblCmpyCd, String key, BigDecimal csmpContrPk) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("key", key);
//        ssmParam.put("csmpContrPk", csmpContrPk);
//        return getSsmEZDClient().queryObjectList("getDuplicateLine", ssmParam);
//    }
    public S21SsmEZDResult searchTotCnt(NWCL0130CMsg cMsg, String glblCmpyCd) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("conslRgnrProcCd", "00");  //un process
            ssmParam.put("CANON_E479_INV_SRCH_TBL", ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", glblCmpyCd));
            return getSsmEZDClient().queryObject("searchTotCnt", ssmParam);
    }
    public S21SsmEZDResult getInvInfoSpecialBill(String glblCmpyCd, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObject("getInvInfoSpecialBill", ssmParam);
    }
    public S21SsmEZDResult getConslBillGrp(String glblCmpyCd, String conslBillNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        return getSsmEZDClient().queryObjectList("getConslBillGrp", ssmParam);
    }
    public S21SsmEZDResult getOriginalConslBillForAdd(String glblCmpyCd, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObject("getOriginalConslBillForAdd", ssmParam);
    }
    public S21SsmEZDResult getProcessedBillInfoSpecialBill(String glblCmpyCd, String conslBillNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        ssmParam.put("CANON_E479_INV_SRCH_TBL", ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", glblCmpyCd));
        return getSsmEZDClient().queryObject("getProcessedBillInfoSpecialBill", ssmParam);
    }
    public S21SsmEZDResult getProcessedInvInfoSpecialBill(String glblCmpyCd, String invNum, String conslRgnrActTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        if (CONSL_RGNR_ACT_TP.ADD.equals(conslRgnrActTpCd)) {
            ssmParam.put("conslRgnrActTpCd", conslRgnrActTpCd);  //Add:02
        }
        ssmParam.put("CANON_E479_INV_SRCH_TBL", ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", glblCmpyCd));
        return getSsmEZDClient().queryObject("getProcessedInvInfoSpecialBill", ssmParam);
    }
    
}
