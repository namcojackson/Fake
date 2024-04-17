package business.blap.NWAL2320;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL2200.NWAL2200Query;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 * 01/19/2017   Fujitsu         M.Ohno          Update          S21_NA#16836
 * 2018/12/14   Fujitsu         K.Kato          Update          QC#29315
 *</pre>
 */
public final class NWAL2320Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2320Query MY_INSTANCE = new NWAL2320Query();

    /**
     * Private constructor
     */
    private NWAL2320Query() {
        super();
    }

    /**
     * Get the NWAL2320Query instance.
     * @return NWAL2320Query instance
     */
    public static NWAL2320Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getDsOrdCatgCd
     * @param glblCmpyCd String
     * @param dsOrdCatgDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdCatgCd(String glblCmpyCd, String dsOrdCatgDescTxt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdCatgDescTxt", dsOrdCatgDescTxt);

        return getSsmEZDClient().queryObject("getDsOrdCatgCd", ssmParam);
    }

    /**
     * getDsOrdLineCatgCd
     * @param glblCmpyCd String
     * @param dsOrdLineCatgDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdLineCatgCd(String glblCmpyCd, String dsOrdLineCatgDescTxt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdLineCatgDescTxt", dsOrdLineCatgDescTxt);

        return getSsmEZDClient().queryObject("getDsOrdLineCatgCd", ssmParam);
    }

    /**
     * getDsOrdTpCd
     * @param glblCmpyCd String
     * @param dsOrdTpDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdTpCd(String glblCmpyCd, String dsOrdTpDescTxt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpDescTxt", dsOrdTpDescTxt);

        return getSsmEZDClient().queryObject("getDsOrdTpCd", ssmParam);
    }

    /**
     * getTocInfo
     * @param glblCmpyCd String
     * @param tocNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTocInfo(String glblCmpyCd, String tocNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("tocNm", tocNm);

        return getSsmEZDClient().queryObject("getTocInfo", ssmParam);
    }

    /**
     * getRtlWhCd
     * @param glblCmpyCd String
     * @param rtlWhNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhCd(String glblCmpyCd, String rtlWhNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhNm", rtlWhNm);

        return getSsmEZDClient().queryObject("getRtlWhCd", ssmParam);
    }

    /**
     * getRtlSwhCd
     * @param glblCmpyCd String
     * @param rtlSwhNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwhCd(String glblCmpyCd, String rtlSwhNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlSwhNm", rtlSwhNm);

        return getSsmEZDClient().queryObject("getRtlSwhCd", ssmParam);
    }

    /**
     * getRtrnRsnCd
     * @param glblCmpyCd String
     * @param rtrnRsnDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtrnRsnCd(String glblCmpyCd, String rtrnRsnDescTxt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtrnRsnDescTxt", rtrnRsnDescTxt);

        return getSsmEZDClient().queryObject("getRtrnRsnCd", ssmParam);
    }

    /**
     * getMdseNm
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseNm(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseNm", ssmParam);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxDsOrdPosnNum(String glblCmpyCd, String cpoOrdNum, String configCatgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("configCatgCd", configCatgCd);

        return getSsmEZDClient().queryObject("getMaxDsOrdPosnNum", ssmParam);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxCpoDtlLineNum(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        return getSsmEZDClient().queryObject("getMaxCpoDtlLineNum", ssmParam);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxDsCpoRtrnLineNum(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        return getSsmEZDClient().queryObject("getMaxDsCpoRtrnLineNum", ssmParam);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @return List< String >
     */
    public List< String > getCoaMdseTpList(String glblCmpyCd) {

        List<String> retList = new ArrayList<String>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mainMach", "MAIN_MACH");
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCoaMdseTpList(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return retList;
        }

        List< ? > resultList = (List< ? >) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {
            retList.add((String) resultList.get(i));
        }

        return retList;
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgSerTakeFlgFromAllMdse(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getShpgSerTakeFlgFromAllMdse", ssmParam);
    }

    // 2018/12/12 QC#29315 Add Start
    /**
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return String
     */
    public String getLocNum(String glblCmpyCd, String shipToCustCd) {
        String locNum = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("shipToCustCd", shipToCustCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getLocNumFromShipTo", ssmParam);

        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            locNum = (String) resultMap.get("LOC_NUM");
        }

        return locNum;
    }
    // 2018/12/12 QC#29315 Add End

}
