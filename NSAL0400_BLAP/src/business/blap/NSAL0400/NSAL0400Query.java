/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0400;

import static business.blap.NSAL0400.constant.NSAL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         M.Yamada        Create          N/A
 * 2016/01/17   Hitachi         K.Yamada        Update          CSA Modify
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/08/01   Hitachi         A.Kohinata      Update          QC#2853
 * 2017/11/20   Hitachi         K.Yamada        Update          QC#22654
 * 2018/05/28   Hitachi         U.Kim           Update          QC#25933
 * 2019/05/10   Hitachi         A.Kohinata      Update          QC#50298
 * 2019/12/19   Hitachi         K.Kitachi       Update          QC#55155
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/11/02   CITS            L.Mandanas      Update          QC#60652
 * 2023/05/29   CITS            T.Kojima        Update          QC#61529
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 *</pre>
 */
public final class NSAL0400Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0400Query INSTANCE = new NSAL0400Query();

    /**
     * Private constructor
     */
    private NSAL0400Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0400Query singleton instance
     */
    public static NSAL0400Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcMemoRsnList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMemoRsnList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("terminationNote", SVC_MEMO_TP.TERMINATION_NOTE);
        params.put("svcMemoRsnDescTxtlength", SVC_MEMO_RSN_DESC_TXT_LN);

        return getSsmEZDClient().queryObjectList("getSvcMemoRsnList", params);
    }

    /**
     * getDtlData
     * @param bizMsg NSAL0400CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDtlData(NSAL0400CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();

        for (int i = 0; i < bizMsg.P.getValidCount(); i++) {
            dsContrPkList.add(bizMsg.P.no(i).dsContrPk_P1.getValue());
            if (ZYPCommonFunc.hasValue(bizMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(bizMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }

        params.put("dsContrPkList", dsContrPkList.toArray());
        if (!dsContrDtlPkList.isEmpty()) {
            params.put("existContrDtlPk", ZYPConstant.FLG_ON_Y);
            params.put("dsContrDtlPkList", dsContrDtlPkList);
        }
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]
        // Mod Start 2017/11/20 QC#22654
        params.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        // Mod End 2017/11/20 QC#22654
        params.put("fleet", DS_CONTR_CATG.FLEET);
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("terminationNote", SVC_MEMO_TP.TERMINATION_NOTE);
        params.put("limitCnt", bizMsg.A.length() + 1);
        // add start 2019/05/10 QC#50298
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // add end 2019/05/10 QC#50298
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        params.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        return getSsmEZDClient().queryEZDMsgArray("getDtlData", params, bizMsg.A);

    }

    /**
     * getInvoicedBllgSchd
     * @param dsContrDtlPk BigDecimal
     * @param contrCloDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoicedBllgSchd(BigDecimal dsContrDtlPk, String contrCloDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contrCloDt", contrCloDt);
        params.put("getInvoicedSchd", FLG_ON_Y);
        params.put("close", DS_BLLG_SCHD_STS.CLOSE);

        return getSsmEZDClient().queryObjectList("getBllgSchd", params);
    }

    /**
     * chkProratedSchd
     * @param dsContrDtlPk BigDecimal
     * @param contrCloDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkProratedSchd(BigDecimal dsContrDtlPk, String contrCloDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contrCloDt", contrCloDt);
        params.put("chkProrated", FLG_ON_Y);
        params.put("close", DS_BLLG_SCHD_STS.CLOSE);

        return getSsmEZDClient().queryObjectList("getBllgSchd", params);
    }

    /**
     * getNotPrrtSchd
     * @param dsContrDtlPk BigDecimal
     * @param contrCloDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNotPrrtSchd(BigDecimal dsContrDtlPk, String contrCloDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contrCloDt", contrCloDt);
        params.put("getNotPrrtSchd", FLG_ON_Y);
        params.put("close", DS_BLLG_SCHD_STS.CLOSE);

        return getSsmEZDClient().queryObjectList("getBllgSchd", params);
    }

    /**
     * getPrrtBllgSchd
     * @param dsContrDtlPk BigDecimal
     * @param contrCloDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrrtBllgSchd(BigDecimal dsContrDtlPk, String contrCloDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contrCloDt", contrCloDt);
        params.put("close", DS_BLLG_SCHD_STS.CLOSE);

        return getSsmEZDClient().queryObjectList("getPrrtBllgSchd", params);
    }

    /**
     * countSvcMemo
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSvcMemo(BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("terminationNote", SVC_MEMO_TP.TERMINATION_NOTE);

        return getSsmEZDClient().queryObject("countSvcMemo", params);
    }

    // START 2016/02/19 T.Aoyagi [QC3694, ADD]
    /**
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNonDispFleetMachCnt(BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrDtlPkList", dsContrDtlPkList);
        params.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
        // START 2019/12/19 K.Kitachi [QC#55155, ADD]
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ORDER);
        // END 2019/12/19 K.Kitachi [QC#55155, ADD]
        params.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return getSsmEZDClient().queryObject("getNonDispFleetMachCnt", params);
    }
    // END 2016/02/19 T.Aoyagi [QC3694, ADD]

    // add start 2016/08/01 CSA Defect#2853
    /**
     * getMtrReadCnt
     * @param dsContrDtlPk BigDecimal
     * @param contrCloDt String
     * @param limitDt BigDecimal
     * @return Integer
     */
    public Integer getMtrReadCnt(BigDecimal dsContrDtlPk, String contrCloDt, BigDecimal limitDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contrCloDt", contrCloDt);
        params.put("limitDt", limitDt.toString());
        params.put("dsMtrReadTpGrpB", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        params.put("dateFormat", DATE_FMT);

        return (Integer) getSsmEZDClient().queryObject("getMtrReadCnt", params).getResultObject();
    }
    // add end 2016/08/01 CSA Defect#2853

    // START 2018/05/28 U.Kim [QC#, ADD]
    /**
     * @param dsContrPk
     * @return BigDecial
     */
    public List<Map<String, Object>> getAccessoryInfo(BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        List<String> dsContrDtlStsList = new ArrayList<String>();
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.CANCELLED);
        // START 2019/12/19 K.Kitachi [QC#55155, ADD]
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.ORDER);
        // END 2019/12/19 K.Kitachi [QC#55155, ADD]
        params.put("dsContrDtlStsList", dsContrDtlStsList);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getAccessoryInfo", params);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    /**
     * @param dsContrPk
     * @return BigDecial
     */
    public Map<String, Object> getMachineInfo(BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        List<String> dsContrDtlStsList = new ArrayList<String>();
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.CANCELLED);
        // START 2019/12/19 K.Kitachi [QC#55155, ADD]
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.ORDER);
        // END 2019/12/19 K.Kitachi [QC#55155, ADD]
        params.put("dsContrDtlStsList", dsContrDtlStsList);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getMachineInfo", params);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (Map<String, Object>) ssmResult.getResultObject();
    }
    // END 2018/05/28 U.Kim [QC#, ADD]
    // START 2022/11/02 L.Mandanas [QC#60652, ADD]
    /**
     * getBillToAndShipTo
     * @param dsContrDtlPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getBillToAndShipTo(BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getBillToAndShipTo", params).getResultObject();
    }

    /**
     * getInvalidSellToCust
     * @param billToCustCd String
     * @param shipToCustCd String
     * @return List<String>
     */
    public List<String> getInvalidSellToCust(String billToCustCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (List<String>) getSsmEZDClient().queryObjectList("getInvalidSellToCust", params).getResultObject();
    }

    /**
     * getInvalidBillToCust
     * @param billToCustCd String
     * @return String
     */
    public String getInvalidBillToCust(String billToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (String) getSsmEZDClient().queryObject("getInvalidBillToCust", params).getResultObject();
    }

    /**
     * getInvalidShipToCust
     * @param shipToCustCd String 
     * @return String
     */
    public String getInvalidShipToCust(String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (String) getSsmEZDClient().queryObject("getInvalidShipToCust", params).getResultObject();
    }
    // END 2022/11/02 L.Mandanas [QC#60652, ADD]

    // START 2023/05/29 T.Kojima [QC#61529, ADD]
    /**
     * countCreditRebill
     * @param dsContrPk BigDecimal 
     * @return Integer
     */
    public Integer countCreditRebill(BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", dsContrPk);

        List<String> dsBlldSchdStsList = new ArrayList<String>();
        dsBlldSchdStsList.add(DS_BLLG_SCHD_STS.BILLED);
        dsBlldSchdStsList.add(DS_BLLG_SCHD_STS.OPEN);
        params.put("dsBlldSchdStsList", dsBlldSchdStsList);

        List<String> dsBlldSchdTpList = new ArrayList<String>();
        dsBlldSchdTpList.add(DS_BLLG_SCHD_TP.CREDIT_CREDIT_AND_REBILL);
        dsBlldSchdTpList.add(DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        params.put("dsBlldSchdTpList", dsBlldSchdTpList);

        return (Integer) getSsmEZDClient().queryObject("countCreditRebill", params).getResultObject();
    }
    // END 2023/05/29 T.Kojima [QC#61529, ADD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /**
     * getTerminationContract
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param mtrReadDt String
     * @param limitDt BigDecimal
     * @return Integer
     */
    public Integer getTerminationContract(String glblCmpyCd, BigDecimal dsContrDtlPk, String contrCloDt, BigDecimal limitDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("contrCloDt", contrCloDt);
        params.put("limitDt", limitDt);
        params.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        params.put("invFlg", ZYPConstant.FLG_ON_Y);
        params.put("dsMtrReadTpCd", DS_MTR_READ_TP.FINAL_METER_READING);
        params.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        return (Integer) getSsmEZDClient().queryObject("getTerminationContract", params).getResultObject();
    }
    // END   2024/03/26 K.Watanabe [QC#63549, ADD]
}
