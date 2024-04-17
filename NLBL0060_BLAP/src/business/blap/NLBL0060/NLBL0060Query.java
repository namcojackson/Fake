/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0060;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL0060.constant.NLBL0060Constant;
import business.db.WH_LEAD_TMTMsg;

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
 * 2009/09/02   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0060Query extends S21SsmEZDQuerySupport implements
		NLBL0060Constant {
	
    /**
     * Singleton instance.
     */
    private static final NLBL0060Query myInstance = new NLBL0060Query();

    /**
     * Constructor.
     */
    private NLBL0060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0060Query
     */
    public static NLBL0060Query getInstance() {
        return myInstance;
    }
    // 2013/05/21 R-OM025 Inventory Transaction Delete Start
    /**
     * <pre>
     * execute SSM id="getWHList" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0060CMsg
     * @param dataSecurityAttrList List
     * @return S21SsmEZDResult
     */
//    public S21SsmEZDResult getWHList(NLBL0060SMsg sMsg, List dataSecurityAttrList) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(SSM_PARAM_SMSG, sMsg);
//        ssmParam.put(SSM_PARAM_DATA_SECULITY_ATTR_LIST, dataSecurityAttrList);
//
//        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WHLIST, ssmParam);
//    }
    // 2013/05/21 R-OM025 Inventory Transaction Delete End

    /**
     * <pre>
     * execute SSM id="getWHLeadTmList" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param cMsg NLBL0060CMsg
     * @param sMsg NLBL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmList(NLBL0060SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_WH_LEAD_TM_LIST, sMsg, sMsg.A);
    }
    
    /**
     * <pre>
     * execute SSM id="getWHLeadTmListForSearchSnapshot" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmListForSearchSnapshot(NLBL0060SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_WH_LEAD_TM_LIST_FOR_SEARCH_SNAPSHOT, sMsg, sMsg.X);
    }

    /**
     * <pre>
     * execute SSM id="getWHLeadTmListForSubmitSnapshot" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmListForSubmitSnapshot(NLBL0060SMsg sMsg) {
        
        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_WH_LEAD_TM_LIST_FOR_SUBMIT_SNAPSHOT, sMsg, sMsg.Y);
    }
    
    /**
     * <pre>
     * execute SSM id="getWHCnt" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0060SMsg
     * @param whCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHCnt(NLBL0060SMsg sMsg, String whCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SSM_PARAM_GLBL_CMPY_CD, sMsg.glblCmpyCd_G1.getValue());
        ssmParam.put(SSM_PARAM_WH_CD, whCd);
        
        return getSsmEZDClient().queryObject(SSM_ID_GET_WH_COUNT, ssmParam); 
    }
    
    /**
     * <pre>
     * execute SSM id="getWHLeadTmListForUpdateInsert" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param tMsg WH_LEAD_TMTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmListForUpdateInsert(WH_LEAD_TMTMsg tMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WH_LEAD_TM_LIST_FOR_UPDATE_INSERT, tMsg);
    }
    
    /**
     * <pre>
     * execute SSM id="getWHLeadTmListForUpdate" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param tMsg WH_LEAD_TMTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmListForUpdate(WH_LEAD_TMTMsg tMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WH_LEAD_TM_LIST_FOR_UPDATE, tMsg);
    }
    
    /**
     * <pre>
     * execute SSM id="getWHLeadTmListForDeleteInsert" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param tMsg WH_LEAD_TMTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmListForDeleteInsert(WH_LEAD_TMTMsg tMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WH_LEAD_TM_LIST_FOR_DELETE_INSERT, tMsg);
    }
    
    /**
     * <pre>
     * execute SSM id="getWHLeadTmListForDelete" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param tMsg WH_LEAD_TMTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHLeadTmListForDelete(WH_LEAD_TMTMsg tMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WH_LEAD_TM_LIST_FOR_DELETE, tMsg);
    }
    
    /**
     * <pre>
     * execute SSM id="getCSVDownloadData" in [NLBL0060Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCSVDownloadData(NLBL0060SMsg sMsg) {
        
        return getSsmEZDClient().queryObjectList(SSM_ID_GET_CSV_DOWNLOAD_DATA, sMsg);
    }
}
