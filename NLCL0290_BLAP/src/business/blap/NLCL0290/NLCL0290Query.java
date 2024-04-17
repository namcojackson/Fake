/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0290;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2017/01/11   CITS            Y.Fujii         Update          QC#16674-2
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 * 2018/12/03   CITS            T.Hakodate      Update          QC#29172
 * 2019/05/09   CITS            T.Tokutomi      Update          QC#50008
 * 2019/06/10   CITS            M.Naito         Update          QC#50751
 * 2021/03/08   CITS            A.Marte         Update          QC#58503
 * 2022/10/21   Hitachi         N.Takatsu       Update          QC#60603
 *</pre>
 */
public final class NLCL0290Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0290Query myInstance = new NLCL0290Query();

    /**
     * Constructor.
     */
    private NLCL0290Query() {
        super();
    }

    public static NLCL0290Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getAdjustmentOrder(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, Map<String, Object> prmMap) {
        return getSsmEZDClient().queryEZDMsgArray("getAdjustmentOrder", prmMap, globalMsg.A);
    }

    // QC:18490
    public S21SsmEZDResult getAddConfig(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, Map<String, Object> prmMap) {
        return getSsmEZDClient().queryEZDMsgArray("getAddConfig", prmMap, globalMsg.A);
    }

    public S21SsmEZDResult getAdjTrxTypeList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getAdjTrxTypeList", prmMap);
    }

    public S21SsmEZDResult getLocStsList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getLocStsList", prmMap);
    }

    public S21SsmEZDResult getStkStsList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getStkStsList", prmMap);
    }

    public S21SsmEZDResult getAdjAcctAliasList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getAdjAcctAliasList", prmMap);
    }

    public S21SsmEZDResult getAdjAcctAliasMap(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getAdjAcctAliasMap", prmMap);
    }

    public S21SsmEZDResult getRtlSwhList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getRtlSwhList", prmMap);
    }

    public S21SsmEZDResult getRtlWhMap(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getRtlWhMap", prmMap);
    }

    public S21SsmEZDResult getMdseMap(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getMdseMap", prmMap);
    }

    public S21SsmEZDResult getIbMap(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getIbMap", prmMap);
    }

    public S21SsmEZDResult getAdjTrxTpCd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getAdjTrxTpCd", prmMap);
    }

    public S21SsmEZDResult getLocStsCd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getLocStsCd", prmMap);
    }

    public S21SsmEZDResult getStkStsCd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getStkStsCd", prmMap);
    }

    public S21SsmEZDResult getAdjCatgCd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getAdjCatgCd", prmMap);
    }

    public S21SsmEZDResult countSubWarehouseTransferControl(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("countSubWarehouseTransferControl", prmMap);
    }

    public S21SsmEZDResult getAdjAcctAliasNm(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getAdjAcctAliasNm", prmMap);
    }
    // QC:18472 Start
    public S21SsmEZDResult checkAllowAccount(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("checkAllowAccount", prmMap);
    }

    public S21SsmEZDResult checkConfig(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("checkConfig", prmMap);
    }

    // QC:18472 End

    // QC#29172 add start
    public S21SsmEZDResult getInvtyOrdInfoList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getInvtyOrdInfoList", prmMap);
    }

    public S21SsmEZDResult getSerNumList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getSerNumList", prmMap);
    }

    public S21SsmEZDResult getSvcMachMstrInfo(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getSvcMachMstrInfo", prmMap);
    }

    public S21SsmEZDResult searchConfig(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("searchConfig", prmMap);
    }

    public S21SsmEZDResult searchNonConfig(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("searchNonConfig", prmMap);
    }

    public S21SsmEZDResult getRwsDetailList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getRwsDetailList", prmMap);
    }

    public S21SsmEZDResult getRwsSerNumList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getRwsSerNumList", prmMap);
    }

    public S21SsmEZDResult getSerNumListFromInvtyOrd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getSerNumListFromInvtyOrd", prmMap);
    }

    public S21SsmEZDResult getToStkStsCd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getToStkStsCd", prmMap);
    }
    // START 2022/10/21 N.Takatsu[QC#60071, ADD]
    public S21SsmEZDResult countSubWarehouseTransferControlList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("countSubWarehouseTransferControlList", prmMap);
    }
    // END 2022/10/21 N.Takatsu[QC#60071, ADD]

    // START 2021/03/08 A.Marte [QC#58503, ADD]
    /**
     * getInvtyLocCd
     * @param prmMap Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyLocCd(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getInvtyLocCd", prmMap);
    }
    // END 2021/03/08 A.Marte [QC#58503, ADD]

    // QC#29172 add end

    // START 2019/06/10 M.Naito [QC#50751,ADD]
    public S21SsmEZDResult cntInvtyDtlDly(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("cntInvtyDtlDly", prmMap);
    }
    // END 2019/06/10 M.Naito [QC#50751,ADD]

    // QC#50008 Add method.
    /**
     * <pre>
     * Count Tech or Customer site.
     * </pre>
     * @param glblCmpyCd String(Not Null)
     * @param rtlWhCd String(Not Null)
     * @return count Integer (count=0 is not Tech or Customer)
     */
    public Integer cntTechOrCustomersite(String glblCmpyCd, String rtlWhCd) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("rtlWhCd", rtlWhCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("cntTechOrCustomersite", prmMap);

        if (result.isCodeNormal()) {
            return (Integer) result.getResultObject();
        } else {
            return 0;
        }
    }

    // QC#50008 Add method.
    /**
     * <pre>
     * get FSR Email Address
     * </pre>
     * @param glblCmpyCd String(Not Null)
     * @param rtlWhCd String(Not Null)
     * @return eMailAddress String(Null able)
     */
    public String getFsrEmailAddress(String glblCmpyCd, String rtlWhCd) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("rtlWhCd", rtlWhCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getFsrEmailAddress", prmMap);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    // QC#50008 Add method.
    /**
     * <pre>
     * get Tech Email Address
     * </pre>
     * @param glblCmpyCd String(Not Null)
     * @param rtlWhCd String(Not Null)
     * @return eMailAddress String(Null able)
     */
    public String getTechEmailAddress(String glblCmpyCd, String rtlWhCd) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("rtlWhCd", rtlWhCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getTechEmailAddress", prmMap);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    // QC#50008 Add method.
    /**
     * <pre>
     * get Tech Email Address
     * </pre>
     * @param glblCmpyCd String(Not Null)
     * @param rtlWhCd String(Not Null)
     * @return eMailAddress String(Null able)
     */
    public String getAltEmailAddress(String glblCmpyCd, String rtlWhCd) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("rtlWhCd", rtlWhCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getAltEmailAddress", prmMap);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    // QC#50008 Add method.
    /**
     * <pre>
     * get FSR Person full name
     * </pre>
     * @param glblCmpyCd String(Not Null)
     * @param rtlWhCd String(Not Null)
     * @return FSR full name String(Null able)
     */
    public String getFsrPersonName(String glblCmpyCd, String rtlWhCd) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("rtlWhCd", rtlWhCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getFsrPersonName", prmMap);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    // QC#50008 Add method.
    /**
     * <pre>
     * get Package Unit of Measure name
     * </pre>
     * @param glblCmpyCd String(Not Null)
     * @param pkgUomCd String(Not Null)
     * @return Package Unit of Measure name String(Null able)
     */
    public String getMdsePrimPkgUomCd(String glblCmpyCd, String mdseCd) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("mdseCd", mdseCd);
        prmMap.put("flgOnY", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getMdsePrimPkgUomCd", prmMap);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }
}
