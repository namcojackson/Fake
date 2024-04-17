/*
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0090;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            Y.Imazu         Create          CSA
 *</pre>
 */
public final class NLCL0090Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0090Query myInstance = new NLCL0090Query();

    /**
     * Constructor.
     */
    private NLCL0090Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0090Query
     */
    public static NLCL0090Query getInstance() {
        return myInstance;
    }

    /**
     * searchInvtyOrd
     * @param bizMsg NLCL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchInvtyOrd(NLCL0090CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("invtyOrdNum", bizMsg.invtyOrdNum_BK.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("searchInvtyOrd", params);
    }

    /**
     * getMdseInfo
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseInfo(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseInfo", params);
    }

    /**
     * getSvcMachMstrInfo
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param serNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrInfo(String glblCmpyCd, String mdseCd, String serNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("serNum", serNum);
        params.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);

        return getSsmEZDClient().queryObject("getSvcMachMstrInfo", params);
    }

    /**
     * getConfigCompCnt
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigCompCnt(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        params.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);

        return getSsmEZDClient().queryObject("getConfigCompCnt", params);
    }

    /**
     * getNonSerSvcMachMstrList
     * @param bizMsg NLCL0090CMsg
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNonSerSvcMachMstrList(NLCL0090CMsg bizMsg, String mdseCd, int rownum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("invtyLocCd", bizMsg.invtyLocCd.getValue());
        params.put("locStsCd", bizMsg.locStsCd.getValue());
        params.put("stkStsCd", bizMsg.stkStsCd.getValue());
        params.put("mdseCd", mdseCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("rownum", rownum);
        params.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);

        return getSsmEZDClient().queryObjectList("getNonSerSvcMachMstrList", params);
    }

    /**
     * searchRwsSerial
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRwsSerial(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);

        return getSsmEZDClient().queryObjectList("searchRwsSerial", params);
    }
}