/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3000;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL3000.constant.NLBL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *  Serial Number Entry NLBL3000Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 * 07/15/2016   CSAI            Y.Imazu         Update          QC#7334
 *</pre>
 */
public final class NLBL3000Query extends S21SsmEZDQuerySupport implements NLBL3000Constant {

    /**
     * Singleton instance.
     */
    private static final NLBL3000Query MYINSTANCE = new NLBL3000Query();

    /**
     * Constructor.
     */
    private NLBL3000Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3000Query
     */
    public static NLBL3000Query getInstance() {
        return MYINSTANCE;
    }

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * NLBL3000Query.xml=search
//     * @param ssmParam Map<String, Object>
//     * @param sMsg NLBL3000SMsg
//     * @return S21SsmEZDResult
//     */
//    @SuppressWarnings("boxing")
//    public S21SsmEZDResult search(Map<String, Object> ssmParam, NLBL3000SMsg sMsg) {
//
//        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, 0, sMsg.A.length(), sMsg.A);
//    }
//
//    /**
//     * NLBL3000Query.xml=getSerNum
//     * @param ssmParam Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    @SuppressWarnings("boxing")
//    public S21SsmEZDResult getSerNum(Map<String, Object> ssmParam) {
//
//        return getSsmEZDClient().queryObjectList("getSerNum", ssmParam);
//    }
    // 07/26/2013 R-OM031 Del End

    /* 11/28/2015 CSAI Y.Imazu Add CSA START */
    /**
     * getSvcMachMstr
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseSerNumRng(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getMdseSerNumRng", params);
    }

    /**
     * getSvcMachMstr
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, String serNum, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("serNum", serNum);
        params.put("mdseCd", mdseCd);
        params.put("svcMachMstrStsDup", SVC_MACH_MSTR_STS.DUPLICATE);
        params.put("svcMachMstrStsTrmn", SVC_MACH_MSTR_STS.TERMINATED);

        return getSsmEZDClient().queryObject("getSvcMachMstr", params);
    }

    /**
     * getDsAssetMstr
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAssetMstr(String glblCmpyCd, String serNum, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("serNum", serNum);
        params.put("mdseCd", mdseCd);
        params.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getDsAssetMstr", params);
    }
    /* 11/28/2015 CSAI Y.Imazu Add CSA END */

    /* 07/15/2016 CSAI Y.Imazu Add QC#7334 START */
    /**
     * getShipSerCnt
     * @param glblCmpyCd String
     * @param soNum String
     * @param serNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipSerCnt(String glblCmpyCd, String soNum, String serNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("soNum", soNum);
        params.put("serNum", serNum);

        return getSsmEZDClient().queryObject("getShipSerCnt", params);
    }

    /**
     * getRcvSerCnt
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param serNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcvSerCnt(String glblCmpyCd, String rwsNum, String serNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("serNum", serNum);

        return getSsmEZDClient().queryObject("getRcvSerCnt", params);
    }
    /* 07/15/2016 CSAI Y.Imazu Add QC#7334 END */

    /**
     * getTrxHdrNumForSO
     * @param glblCmpyCd String
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxHdrNumForSO(String glblCmpyCd, String soNum) { 
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("soNum", soNum);

        return getSsmEZDClient().queryObject("getTrxHdrNumForSO", params);
    }

    /**
     * getTrxHdrNumForRWS
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxHdrNumForRWS(String glblCmpyCd, String rwsNum) { 
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);

        return getSsmEZDClient().queryObject("getTrxHdrNumForRWS", params);
    }
    
    
    /**
     * getTrxHdrNumForRwsSubContract
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxHdrNumForRwsSubContract(String glblCmpyCd, String rwsNum) { 
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);

        return getSsmEZDClient().queryObject("getTrxHdrNumForRwsSubContract", params);
    }
}
