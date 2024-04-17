/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7070;

import static business.blap.NMAL7070.constant.NMAL7070Constant.BIZ_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NMAL7070Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7070Query MY_INSTANCE = new NMAL7070Query();

    /**
     * Private constructor
     */
    private NMAL7070Query() {
        super();
    }

    /**
     * Get the NMAL7070Query instance.
     * @return NMAL7070Query instance
     */
    public static NMAL7070Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getPriceCategoryCode
     * @param bizMsg NMAL7070SMsg
     * @param glblMsg NMAL7070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceCategoryCode(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("effFromDt", bizMsg.effFromDt.getValue());
        params.put("effThruDt", bizMsg.effThruDt.getValue());
        params.put("prcListStruTpCd", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);
        params.put("acctNum", bizMsg.dsAcctNum.getValue());
        params.put("acctNm", bizMsg.dsAcctNm.getValue());
        params.put("bizTpCd", bizMsg.lineBizTpCd.getValue());
        params.put("CSMPNum", bizMsg.csmpNum.getValue());
        params.put("brCd", bizMsg.coaBrCd.getValue());
        return getSsmEZDClient().queryObjectList("getPriceCategoryCode", params);
    }

    /**
     * getSupplyAgreementPlan
     * @param bizMsg NMAL7070SMsg
     * @param glblMsg NMAL7070SMsg
     * @param prcCatgCdList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplyAgreementPlan(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg, List<String> prcCatgCdList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("effFromDt", bizMsg.effFromDt.getValue());
        params.put("effThruDt", bizMsg.effThruDt.getValue());
        params.put("plnNm", bizMsg.splyAgmtPlnNm.getValue());
        params.put("plnShortNm", bizMsg.splyAgmtPlnShortNm.getValue());
        params.put("plnDescTxt", bizMsg.splyAgmtPlnDescTxt.getValue());
        params.put("plnTp", bizMsg.splyAgmtPlnTpCd.getValue());
        params.put("docTp", bizMsg.splyAgmtDocTpCd.getValue());
        params.put("plnSts", bizMsg.splyAgmtPlnStsCd.getValue());
        params.put("salesDt", ZYPDateUtil.getSalesDate());
        if (prcCatgCdList != null) {
            String[] prcCatgCd = (String[]) prcCatgCdList.toArray(new String[0]);
            params.put("prcCatgCd", prcCatgCd);
        } else {
            params.put("prcCatgCd", null);
        }
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getSupplyAgreementPlan", params);
    }

}
