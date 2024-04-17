package business.blap.NLBL0110;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NLBL0110.constant.NLBL0110Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DT_ATTRB;
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
 * 2010/02/04   Fujitsu         S.Uehara        Create          N/A
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public final class NLBL0110Query extends S21SsmEZDQuerySupport implements NLBL0110Constant {

    /**
     * Singleton instance.
     */
    private static final NLBL0110Query MY_INSTANCE = new NLBL0110Query();

    /**
     * Constructor.
     */
    private NLBL0110Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0110Query
     */
    public static NLBL0110Query getInstance() {
        return MY_INSTANCE;
    }

    // 2013/05/24 R-OM025 Inventory Transaction Delete Start
//    /**
//     * <pre>
//     * execute SSM id="getWHList" in [NLBL0110Query.xml]
//     * </pre>
//     * 
//     * @param sMsg NLBL0110SMsg
//     * @param dataSecurityAttrList List
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getWHList(NLBL0110SMsg sMsg, List dataSecurityAttrList) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(SSM_PARAM_SMSG, sMsg);
//        ssmParam.put(SSM_PARAM_DATA_SECULITY_ATTR_LIST, dataSecurityAttrList);
//
//        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WHLIST, ssmParam);
//    }
    // 2013/05/24 R-OM025 Inventory Transaction Delete End

    /**
     * <pre>
     * execute SSM id="getWHBizDaysOfEndOfMonth" in [NLBL0110Query.xml]
     * </pre>
     * 
     * @param sMsg NLBL0110SMsg
     * @param dataSecurityAttrList List
     * @return S21SsmEZDResult
     */
    // 2013/05/24 R-OM025 Inventory Transaction Mod Start
//    public S21SsmEZDResult getWHBizDaysOfEndOfMonth(NLBL0110SMsg sMsg) {
//
//        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_WH_BIZ_DAYS_END_OF_MONTH, sMsg, sMsg.A);
//    }
    public S21SsmEZDResult getWHBizDaysOfEndOfMonth(NLBL0110SMsg sMsg, List dataSecurityAttrList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SSM_PARAM_SMSG, sMsg);
        ssmParam.put(SSM_PARAM_DATA_SECULITY_ATTR_LIST, dataSecurityAttrList);

        return getSsmEZDClient().queryEZDMsgArray(SSM_ID_GET_WH_BIZ_DAYS_END_OF_MONTH, ssmParam, sMsg.A);
    }
    // 2013/05/24 R-OM025 Inventory Transaction Mod End

    /**
     * <pre>
     * execute SSM id="getCalTpCd" in [NLBL0110Query.xml]
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param calSubTpCd String
     * @param calMultCd String
     * @param date String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCalTpCd(String glblCmpyCd, String calSubTpCd, String calMultCd, String date) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("calSubTpCd", calSubTpCd);
        ssmParam.put("calMultCd", calMultCd);
        ssmParam.put("dtAttrbCd", DT_ATTRB.BUSINESS);
        ssmParam.put("calDt", date + "%");

        return getSsmEZDClient().queryObject(SSM_ID_GET_CAL_TP_CD, ssmParam);
    }

     // 2013/05/24 R-OM025 Inventory Transaction Delete Start
//    /**
//     * <pre>
//     * execute SSM id="getWHEndMthForUpdate" in [NLBL0110Query.xml]
//     * </pre>
//     * 
//     * @param sMsg NLBL0110SMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getWHEndMthForUpdate(NLBL0110SMsg sMsg) {
//
//        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WH_END_MTH_FOR_UPDATE, sMsg);
//    }
    // 2013/05/24 R-OM025 Inventory Transaction Delete End

    // 2013/05/24 R-OM025 Inventory Transaction Delete Start
//    /**
//     * <pre>
//     * execute SSM id="getWHBizDaysOfEndOfMonth" in [NLBL0110Query.xml]
//     * </pre>
//     * 
//     * @param sMsg NLBL0110SMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getWHEndMthForSubmitSnapshot(NLBL0110SMsg sMsg) {
//
//        return getSsmEZDClient().queryObjectList(SSM_ID_GET_WH_BIZ_DAYS_END_OF_MONTH, sMsg);
//    }
    // 2013/05/24 R-OM025 Inventory Transaction Delete End


}
