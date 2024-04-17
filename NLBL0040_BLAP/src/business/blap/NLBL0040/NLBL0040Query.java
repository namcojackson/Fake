package business.blap.NLBL0040;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NLBL0040.NLBL0040CMsg;
import business.blap.NLBL0040.NLBL0040Query;
import business.blap.NLBL0040.constant.NLBL0040Constant;
import business.db.AREA_LEAD_TMTMsg;
import business.db.TRNSP_LTTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * This class does the data base access processing by SSM. 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/29/2010   Fujitsu         D.Fukaya        Create          N/A
 * 09/09/2010   CSAI            D.Fukaya        Update          360
 *</pre>
 */
public final class NLBL0040Query extends S21SsmEZDQuerySupport implements
		NLBL0040Constant {
	

    /**
     * Singleton instance.
     */
    private static final NLBL0040Query myInstance = new NLBL0040Query();

    /**
     * Constructor.
     */
    private NLBL0040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0040Query
     */
    public static NLBL0040Query getInstance() {
        return myInstance;
    }


    /**
     * <pre>
     * execute SSM id="getWHList" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0040CMsg
     * @param dataSecurityAttrList List
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHList(NLBL0040CMsg cMsg, List dataSecurityAttrList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SSM_PARAM_CMSG, cMsg);
        ssmParam.put(SSM_PARAM_DATA_SECULITY_ATTR_LIST, dataSecurityAttrList);

        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WHLIST, ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getEffFromToList" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0040CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEffFromToList(NLBL0040CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SSM_PARAM_CMSG, cMsg);
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_EFF_FROM_TO_LIST, ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getEffFromToListForSearchSnapshot" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEffFromToListForSearchSnapshot(NLBL0040SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_EFF_FROM_TO_LIST_FOR_SEARCH_SNAPSHOT, sMsg, sMsg.C);
    }

    /**
     * <pre>
     * execute SSM id="getSTList" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0040CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStList(NLBL0040CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SSM_PARAM_CMSG, cMsg);
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_ST_LIST, ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getAreaLeadTmList" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAreaLeadTmList(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_AREA_LEAD_TIME_LIST, sMsg, sMsg.A);
    }
    
    /**
     * <pre>
     * execute SSM id="getAreaLeadTmListNoValue" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAreaLeadTmListNoValue(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_AREA_LEAD_TIME_LIST_NO_VALUE, sMsg, sMsg.A);
    }
    
    /**
     * <pre>
     * execute SSM id="getAreaLeadTmListForSearchSnapshot" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAreaLeadTmListForSearchSnapshot(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_AREA_LEAD_TIME_LIST_FOR_SEARCH_SNAPSHOT, sMsg, sMsg.S);
    }

    /**
     * <pre>
     * execute SSM id="getAreaLeadTmListForSearchSnapshot" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAreaLeadTmListForSubmitSnapshot(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_AREA_LEAD_TIME_LIST_FOR_SUBMIT_SNAPSHOT, sMsg, sMsg.T);
    }
    
    /**
     * <pre>
     * execute SSM id="getTrnspLtList" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrnspLtList(NLBL0040SMsg sMsg) {

       return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_TRNSP_LT_LIST, sMsg, sMsg.B);
    }
    
    /**
     * <pre>
     * execute SSM id="getTrnspLtListForDetailSnapshot" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrnspLtListForDetailSnapshot(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_TRNSP_LT_LIST_FOR_DETAIL_SNAPSHOT, sMsg, sMsg.X);
    }

    /**
     * <pre>
     * execute SSM id="getTrnspLtListForSubmitSnapshot" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrnspLtListForSubmitSnapshot(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_TRNSP_LT_LIST_FOR_SUBMIT_SNAPSHOT, sMsg, sMsg.Y);
    }
    
    /**
     * <pre>
     * execute SSM id="getAreaLeadTmListByWHAndEffFrom" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param tMsg AREA_LEAD_TMTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAreaLeadTmListByWHAndEffFrom(AREA_LEAD_TMTMsg tMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_AREA_LEAD_TM_LIST_BY_WH_AND_EFF_FROM, tMsg);
    }
    
    
    /**
     * <pre>
     * execute SSM id="getTrnspLtListByEffFrom" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param tMsg TRNSP_LTTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrnspLtListByEffFrom(TRNSP_LTTMsg tMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_TRNSP_LT_LIST_BY_EFF_FROM, tMsg);
    }
    
    // 09/09/2010 D.Fukaya append start
    /**
     * <pre>
     * execute SSM id="getTrnspLtListByPartialKeyForInsert" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrnspLtListByPartialKeyForInsert(NLBL0040SMsg sMsg) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        
        ssmParam.put("glblCmpyCd", sMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("whCd", sMsg.whCd_G1.getValue());
        ssmParam.put("effFromDt_old", sMsg.effFromDt_G1.getValue());
        ssmParam.put("stCd", sMsg.stCd_G2.getValue());
        ssmParam.put("shpgModeCd", sMsg.shpgModeCd_G2.getValue());
        ssmParam.put("effFromDt_new", sMsg.effFromDt_L1.getValue());
        ssmParam.put("effThruDt_new", sMsg.effThruDt_L1.getValue());
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_TRNSP_LT_LIST_BY_PARTIAL_KEY_FOR_INSERT, ssmParam);
    }
    // 09/09/2010 D.Fukaya append end

    /**
     * <pre>
     * execute SSM id="getCSVDownloadData" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCSVDownloadData(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_CSV_DOWNLOAD_DATA, sMsg);
    }
    
    /**
     * <pre>
     * execute SSM id="getCSVDownloadDataNoValue" in [NLBL0040Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCSVDownloadDataNoValue(NLBL0040SMsg sMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_CSV_DOWNLOAD_DATA_NO_VALUE, sMsg);
    }
}
