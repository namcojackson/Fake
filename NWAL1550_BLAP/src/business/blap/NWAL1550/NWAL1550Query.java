/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1550;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1550Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2017/08/10   Fujitsu         S.Ohki          Update          S21_NA#20068
 *</pre>
 */
public final class NWAL1550Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1550Query MY_INSTANCE = new NWAL1550Query();

    /**
     * Private constructor
     */
    private NWAL1550Query() {
        super();
    }

    /**
     * Get the NWAL1550Query instance.
     * @return NWAL1550Query instance
     */
    public static NWAL1550Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Order Information And Pricing Summary.
     * @param bizMsg NWAL1550CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderInfoAndPricingSummary(NWAL1550CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryEZDMsg("getOrderInfoAndPricingSummary", params, bizMsg);
    }

    /**
     * Get Data Integrity Check Information.
     * @param bizMsg NWAL1550CMsg
     * @param verNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDataIntegrityCheckInfo(NWAL1550CMsg bizMsg, BigDecimal verNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("verNum", verNum);
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryEZDMsg("getDataIntegrityCheckInfo", params, bizMsg);
    }

    /**
     * Get Data Integrity Result.
     * @param bizMsg NWAL1550CMsg
     * @param chkRsltHdrPk String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDataIntegrityResult(NWAL1550CMsg bizMsg, BigDecimal chkRsltHdrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("chkRsltHdrPk", chkRsltHdrPk);
        // 2018/08/09 S21_NA#20068 Add Start
        params.put("configCatgCd", CONFIG_CATG.INBOUND);
        // 2018/08/09 S21_NA#20068 Add End
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryEZDMsgArray("getDataIntegrityResult", params, bizMsg.A);
    }

    /**
     * Get Version Number
     * @param bizMsg NWAL1550CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVersionNum(NWAL1550CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryObjectList("getVersionNum", params);
    }

    /**
     * Get Cpo_Dtl_V Not OpenFlag 'N'
     * @param bizMsg NWAL1550CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCpoDtlV(NWAL1550CMsg bizMsg, String openFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("msg", bizMsg);
        if (ZYPCommonFunc.hasValue(openFlg)) {
            params.put("openFlg", openFlg);
        }
        
        return getSsmEZDClient().queryObject("getCpoDtlV", params);
    }

    /**
     * Get DS_CPO_DI_CHK_RSLT_HDR By Version Number
     * @param bizMsg NWAL1550CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRsltHdrByVerNum(NWAL1550CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryObject("getRsltHdrByVerNum", params);
    }

    /**
     * Get DS_CPO_DI_CHK_RSLT_HDR Max Version Number
     * @param bizMsg NWAL1550CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRsltHdrMaxVerNum(NWAL1550CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryObject("getRsltHdrMaxVerNum", params);
    }

    /**
     * Get DS_CPO_DI_CHK_RSLT_DTL StatusCd
     * @param dsCpoDiChkRsltHdrPk String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRsltDtlStsCd(BigDecimal dsCpoDiChkRsltHdrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsCpoDiChkRsltHdrPk", dsCpoDiChkRsltHdrPk);
        params.put("diChkDtlStsCd", DI_CHK_DTL_STS.ACCEPTED);

        return getSsmEZDClient().queryObject("getRsltDtlStsCd", params);
    }

    /**
     * Get Hold PK
     * @param bizMsg NWAL1550CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldPk(NWAL1550CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // QC#17907 2017/03/09 Add Start
        // params.put("hldRsnCd", HLD_RSN_DI);
        params.put("hldRsnCd", HLD_RSN.DI_CHECK_HOLD);
        // QC#17907 2017/03/09 Add End
        params.put("relFlg", ZYPConstant.FLG_OFF_N);
        params.put("msg", bizMsg);

        return getSsmEZDClient().queryObjectList("getHldPk", params);
    }

}
