/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0720;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/08   Hitachi         T.Tsuchida      Update          QC#1606
 * 2021/10/01   CITS            T.Wada          Update          QC#59240
 * 2022/03/16   CITS            R.Cabral        Update          QC#59748
 *</pre>
 */
public final class NSAL0720Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0720Query INSTANCE = new NSAL0720Query();

    /**
     * Constructor.
     */
    private NSAL0720Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0720Query
     */
    public static NSAL0720Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0720_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrHdrInfo(Map<String, Object> ssmParam, NSAL0720_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrHdrInfo", ssmParam, aSMsgArray);
    }

    /**
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return BILL_TO_CUSTTMsg
     */
    public BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("047");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * get DS_CONTR_DTL_PK
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("bllgHldFlg", ZYPConstant.FLG_OFF_N);
        List<String> dsContrCtrlStsList = new ArrayList<String>();
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.CANCELLED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.TERMINATED);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsList);
        ssmParam.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
//        if (ZYPCommonFunc.hasValue(baseChrgFlg)) {
//            ssmParam.put("baseChrgFlg", baseChrgFlg);
//        }
//        if (ZYPCommonFunc.hasValue(usgChrgFlg)) {
//            ssmParam.put("usgChrgFlg", usgChrgFlg);
//        }
        return getSsmEZDClient().queryObjectList("getDsContrDtlPk", ssmParam);
    }

    // START 2022/03/16 R.Cabral [QC#59748, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlPkAgg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        List<String> dsContrCtrlStsList = new ArrayList<String>();
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.CANCELLED);
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.TERMINATED);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsList);
        ssmParam.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return getSsmEZDClient().queryObjectList("getDsContrDtlPkAgg", ssmParam);
    }
    // END   2022/03/16 R.Cabral [QC#59748, ADD]

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_CR_CARD_POTMsgArray
     */
    public DS_CONTR_CR_CARD_POTMsgArray getContrCrCardPo(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        tMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        DS_CONTR_CR_CARD_POTMsgArray tMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray;
        } else {
            return null;
        }
    }

    // add start 2016/07/27 CSA Defect#12001
    public String getLeaseAcctFlg(String glblCmpyCd, String billToCustCd) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getLeaseAcctFlg", ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }
    // add end 2016/07/27 CSA Defect#12001
    
    // QC#59240 Add Start
    public BigDecimal getInvoicedTermAmountForBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getInvoicedTermAmountForBase", param).getResultObject();
    }
    public String getMinUnbilledFromDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getMinUnbilledFromDt", param).getResultObject();
    }
    public String getDtAfterMaxInvThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getDtAfterMaxInvThruDt", param).getResultObject();
    }
    public BigDecimal getTotalAmtOfUninvBefFinInv(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("none", SKIP_RECOV_TP.NONE);
        return (BigDecimal) getSsmEZDClient().queryObject("getTotalAmtOfUninvBefFinInv", param).getResultObject();
    }

    // QC#59240 Add End
}
