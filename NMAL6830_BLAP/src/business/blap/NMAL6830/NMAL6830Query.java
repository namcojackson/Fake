package business.blap.NMAL6830;

import java.util.HashMap;
import java.util.Map;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public final class NMAL6830Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NMAL6830Query MY_INSTANCE = new NMAL6830Query();

    /**
     * Constructor.
     */
    private NMAL6830Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6830Query
     */
    public static NMAL6830Query getInstance() {
        return MY_INSTANCE;
    }
    public S21SsmEZDResult getCostTypeList(NMAL6830CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getCostTypeList", ssmParam);
    }

    public S21SsmEZDResult getMdseUpdGroupList(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put("mdseCstUpdGrpTxt", cMsg.mdseCstUpdGrpTxt_H1.getValue());
        ssmParam.put("mdseCstUpdDescTxt", cMsg.mdseCstUpdDescTxt_H1.getValue());
        ssmParam.put("mdseCstUpdTpCd", cMsg.mdseCstUpdTpCd_H1.getValue());
        ssmParam.put("mdseCstUpdRefTxt", cMsg.mdseCstUpdRefTxt_H1.getValue());
        ssmParam.put("mdseCstUpdRefId", cMsg.mdseCstUpdRefId_H1.getValue());
        ssmParam.put("mdseCstUpdStsCd", cMsg.mdseCstUpdStsCd_H1.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getMdseUpdGroupList", ssmParam, sMsg.A);
    }
}
