/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0270;


import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public final class NFAL0270Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFAL0270Query INSTANCE = new NFAL0270Query();

    /**
     * Constructor.
     */
    private NFAL0270Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0410Query
     */
    public static NFAL0270Query getInstance() {
        return INSTANCE;
    }

    /**
     * getAllocation
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     * @param rowNum rowNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllocation(String glblCmpyCd, NFAL0270CMsg cMsg, NFAL0270SMsg sMsg, int rowNum) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("mdlGrpId", cMsg.mdlGrpId_H.getValue());
        prmMap.put("mdlGrpNm", cMsg.mdlGrpNm_H.getValue());
        prmMap.put("svcInvChrgTpCd", cMsg.svcInvChrgTpCd_H.getValue());
        prmMap.put("svcAllocTpCd", cMsg.svcAllocTpCd_H.getValue());
        prmMap.put("rowNum", rowNum);
        return getSsmEZDClient().queryObjectList("getAllocation", prmMap);
    }

    /**
     * getSvcInvChrgTpCdList
     * @param prmMap prmMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcInvChrgTpCdList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getSvcInvChrgTpCdList", prmMap);
    }

    /**
     * getDownloadData
     * @param ssmParam Map<String, Object>
     * @param sMsg NFAL0270SMsg
     * @return result
     */
    public S21SsmEZDResult getDownloadData(Map<String, Object> ssmParam, NFAL0270SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getDownloadData", ssmParam, sMsg.A);
    }
}
