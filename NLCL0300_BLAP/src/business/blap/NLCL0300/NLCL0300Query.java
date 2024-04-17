/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 * 2022/02/15   CITS            A.Marte         Update          QC#59705
 *</pre>
 */
package business.blap.NLCL0300;

import java.util.Map;

import business.blap.NLCL0300.NLCL0300CMsg;
import business.blap.NLCL0300.NLCL0300Query;
import business.blap.NLCL0300.NLCL0300SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2019/08/13   CITS            M.Naito         Update          QC#52185
 *</pre>
 */

public final class NLCL0300Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0300Query myInstance = new NLCL0300Query();

    /**
     * Constructor.
     */
    private NLCL0300Query() {
        super();
    }

    public static NLCL0300Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getConfigChangeOrder(NLCL0300CMsg bizMsg, NLCL0300SMsg globalMsg, Map<String, Object> prmMap) {
        return getSsmEZDClient().queryEZDMsgArray("getConfigChangeOrder", prmMap, bizMsg.A);
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

    public S21SsmEZDResult getConfigList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getConfigList", prmMap);
    }

    public S21SsmEZDResult getModelConfigList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getModelConfigList", prmMap);
    }

    public S21SsmEZDResult getRwsSerApiParameterList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getRwsSerApiParameterList", prmMap);
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

    // START 2019/08/13 M.Naito [QC#52185,MOD]
    public S21SsmEZDResult getOrderBySvcConfigMstrPk(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getOrderBySvcConfigMstrPk", prmMap);
    }
    // END 2019/08/13 M.Naito [QC#52185,MOD]

    // START 2022/02/15 A.Marte [QC#59705,ADD]
    public S21SsmEZDResult countAvailNonSerialSingleItem(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("countAvailNonSerialSingleItem", prmMap);
    }
    // END 2022/02/15 A.Marte [QC#59705,ADD]
}
