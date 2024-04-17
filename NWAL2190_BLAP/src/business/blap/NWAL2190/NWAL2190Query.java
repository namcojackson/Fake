/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2190;

import static business.blap.NWAL2190.constant.NWAL2190Constant.MTH_12;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/02   Fujitsu         S.Iidaka        Create          N/A
 *</pre>
 */
public final class NWAL2190Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2190Query MY_INSTANCE = new NWAL2190Query();

    /**
     * Private constructor
     */
    private NWAL2190Query() {
        super();
    }

    /**
     * Get the NWAL2190Query instance.
     * @return NWAL2190Query instance
     */
    public static NWAL2190Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get HeaderInfo From SCHD_CRAT_TMPL
     * @param bizMsg NWAL2190CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfoTmpl(NWAL2190CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoSvcConfigRefPk", bizMsg.cpoSvcConfigRefPk.getValue());

        String stmtId = "getHeaderInfoTmplForImport";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * Get HeaderInfo From SPLY_AGMT_PLN
     * @param bizMsg NWAL2190CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfoPln(NWAL2190CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", bizMsg.mdlId.getValue());
        params.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrLbCd", bizMsg.bllgMtrLbCd.getValue());
        params.put("prcListBandDescTxt", bizMsg.prcListBandDescTxt.getValue());
        params.put("prcCatgCd", bizMsg.prcCatgCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("termMthNum", bizMsg.shpgIntvlMthNum);

        params.put("svcConfigRefPk", bizMsg.cpoSvcConfigRefPk);
        params.put("svcLineNum", bizMsg.dsImptSvcLineNum);

        String stmtId = "getHeaderInfoPlnForImport";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * Get DetailInfo
     * @param bizMsg NWAL2190CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailInfo(NWAL2190CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoSvcConfigRefPk", bizMsg.cpoSvcConfigRefPk.getValue());
        params.put("numberOfMonths", MTH_12);
        params.put("termMthNum", bizMsg.shpgIntvlMthNum.getValue());
        params.put("schdCratTmplPk", bizMsg.schdCratTmplPk.getValue());

        String stmtId = "getDetailInfoForImport";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

}
