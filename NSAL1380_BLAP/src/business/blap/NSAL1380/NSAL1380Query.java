/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1380;

import static business.blap.NSAL1380.constant.NSAL1380Constant.MTH_12;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Agreement Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 * 2017/10/16   Hitachi         Y.Takeno        Update          QC#20001
 * 2018/09/10   Fujitsu         K.Ishizuka      Update          QC#28104
 *</pre>
 */
public final class NSAL1380Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1380Query MY_INSTANCE = new NSAL1380Query();

    /**
     * Private constructor
     */
    private NSAL1380Query() {
        super();
    }

    /**
     * Get the NSAL1380Query instance.
     * @return NSAL1380Query instance
     */
    public static NSAL1380Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get HeaderInfo From SCHD_CRAT_TMPL
     * @param bizMsg NSAL1380CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfoTmpl(NSAL1380CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());

        String stmtId = "getHeaderInfoTmpl";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * Get HeaderInfo From SPLY_AGMT_PLN
     * @param bizMsg NSAL1380CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfoPln(NSAL1380CMsg bizMsg) {
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
        params.put("termMthAot", bizMsg.termMthAot.getValue());
        params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());
        params.put("svcLineNum", bizMsg.shellLineNum.getValue());

        String stmtId = "getHeaderInfoPln";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * Get DetailInfo
     * @param bizMsg NSAL1380CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailInfo(NSAL1380CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());
        params.put("numberOfMonths", MTH_12);
        params.put("termMthAot", bizMsg.termMthAot.getValue());
        params.put("schdCratTmplPk", bizMsg.schdCratTmplPk.getValue());

        String stmtId = "getDetailInfo";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    // START 2017/10/16 [QC#20001, ADD]
    /**
     * getSchdCratTmplPkByDsContrDtlGrpNum
     * @param bizMsg NSAL1380CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdCratTmplPkByDsContrDtlGrpNum(NSAL1380CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrDtlGrpNum)) {
            params.put("dsContrDtlGrpNum", bizMsg.dsContrDtlGrpNum.getValue());
            params.put("dsContrDtlPk", bizMsg.dsContrDtlGrpNum.getValue());
        } else {
            params.put("dsContrDtlGrpNum", bizMsg.dsContrDtlPk.getValue());
            params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());
        }

        String stmtId = "getSchdCratTmplPkByDsContrDtlGrpNum";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }
    // END   2017/10/16 [QC#20001, ADD]
    // Add Start 2018/09/05 QC#28055
    public String getScrtEntAvalFlg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("order", DS_CONTR_DTL_STS.ORDER);
        params.put("partiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("arrived", SHPG_STS.ARRIVED);
        return (String) getSsmEZDClient().queryObject("getScrtEntAvalFlg", params).getResultObject();
    }
    // Add End 2018/09/05 QC#28055
    
    // 2018/09/10 S21_NA#28104 Add Start
    /**
     * getSvcTermCondAttrbPk
     * @param bizMsg NSAL1380CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTermCondAttrbPk(NSAL1380CMsg bizMsg, BigDecimal dsContrDtlPk) {
        
        BigDecimal capQtyPk = ZYPCodeDataUtil.getNumConstValue("SVC_TERM_COND_ATTR_CAP_QTY_PK", getGlobalCompanyCode());
        if(!ZYPCommonFunc.hasValue(capQtyPk)){
            return null;
        }
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("svcTermCondAttrbPk", capQtyPk);

        String stmtId = "getSvcTermCondAttrbPk";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }
    // 2018/09/10 S21_NA#28104 Add End
}
