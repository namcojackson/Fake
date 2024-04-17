package business.blap.ZYPL0310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import business.blap.ZYPL0310.constant.ZYPL0310Constant;
import business.db.ATT_DATATMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZYPL0310Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final ZYPL0310Query myInstance = new ZYPL0310Query();

    /**
     * Constructor.
     */
    private ZYPL0310Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0030Query
     */
    public static ZYPL0310Query getInstance() {
        return myInstance;
    }

    /**
     * update ATT_DATA_CMNT_CLOD of ATT_DATA
     * @param cMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getAttCmnt(ZYPL0310CMsg cMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(ZYPL0310Constant.PRM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(ZYPL0310Constant.PRM_EZ_BUSINESS_ID, cMsg.ezBusinessID_I.getValue());
        ssmParam.put(ZYPL0310Constant.PRM_ATT_DATA_GRP, cMsg.attDataGrpTxt_I.getValue());

        // search execute
        return getSsmEZDClient().queryObjectList(ZYPL0310Constant.QUERY_SELECT, ssmParam);
    }

    /**
     * update ATT_DATA_CMNT_CLOD of ATT_DATA
     * @param cMsg
     * @return update result count
     */
    public int updAttCmnt(ZYPL0310CMsg cMsg, ATT_DATATMsg tMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(ZYPL0310Constant.PRM_GLBL_CMPY_CD, tMsg.glblCmpyCd.getValue());
        ssmParam.put(ZYPL0310Constant.PRM_ATT_DATA_PK, tMsg.attDataPk.getValueInt());
        ssmParam.put(ZYPL0310Constant.PRM_ATT_DATA_CMNT, cMsg.xxAttDataCmntTxt_I.getValue());

        return ZYPL0310QuerySupport.getClient(this.getClass()).update(ZYPL0310Constant.QUERY_UPDATE, ssmParam);
    }

    // Add Start 2019/05/09 QC#50015
    /**
     * <pre>
     * get ATT_DATA_DOC_TP list
     * </pre>
     * @param glblCmpyCd String
     * @param docTpCdConstVal String
     * @return S21SsmEZDResult query result object (List<Map<String, Object>))
     */
    public S21SsmEZDResult getAttDocTpCdList(String glblCmpyCd, String docTpCdConstVal) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        ArrayList<String> attDocTpCdList = null;
        if (ZYPCommonFunc.hasValue(docTpCdConstVal)) {
            String[] attDocTpCdArray = docTpCdConstVal.split(",");
            attDocTpCdList = new ArrayList(Arrays.asList(attDocTpCdArray));
            queryParam.put("attDocTpCdList", attDocTpCdList);
        }

        return getSsmEZDClient().queryObjectList("getAttDocTpCdList", queryParam);
    }
    // Add End 2019/05/09 QC#50015
}
